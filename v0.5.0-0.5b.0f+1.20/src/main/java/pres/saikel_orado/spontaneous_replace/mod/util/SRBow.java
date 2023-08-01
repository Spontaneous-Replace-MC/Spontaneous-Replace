package pres.saikel_orado.spontaneous_replace.mod.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.Vanishable;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import pres.saikel_orado.spontaneous_replace.mod.data.SRData;

import java.util.List;
import java.util.function.Predicate;

import static pres.saikel_orado.spontaneous_replace.mod.util.SRUtil.getTick;

/**
 * <b style="color:FFC800"><font size="+2">SRBow：自然更替通用弓类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">辅助弓的创建的数据直观和清晰</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 5.0
 * | 创建于 2023/4/24 18:06
 */
public abstract class SRBow extends RangedWeaponItem implements Vanishable {
    public static final double DEFAULT_USING_SPEED = SRData.Player.SNEAK_SPEED;
    public static final double DEFAULT_MAX_USE_TIME = 60 * 60;
    public static final double DEFAULT_MAX_PULL_TIME = 1;
    public static final double DEFAULT_DAMAGE_MULTIPLE = 3;
    public static final int DEFAULT_RANGE = BowItem.RANGE;
    public final Predicate<ItemStack> PROJECTILES;
    public final List<Enchantment> ENCHANTMENTS;
    public final double USING_SPEED;
    public final int MAX_USE_TICKS;
    public final int MAX_PULL_TICKS;
    public final double DAMAGE_MULTIPLE;
    public final int RANGE;
    /**
     * 上刻速度
     */
    protected static double prevSpeed = 0;
    protected static double prev2Speed = 0;

    /**
     * 构建自然更替通用弓类
     *
     * @param settings            物品设置
     * @param predicate           可使用弹药
     * @param enchantmentList     可用附魔
     * @param usingSpeed          使用时速度
     * @param maxUseTime          最大使用时间
     * @param maxPullTime         最大拉弓时间
     * @param maxBulletSpeed      最大子弹速度
     * @param basicDamageMultiple 基础伤害倍数
     * @param range               范围
     */
    public SRBow(Settings settings, Predicate<ItemStack> predicate, List<Enchantment> enchantmentList, double usingSpeed, double maxUseTime, double maxPullTime, double maxBulletSpeed, double basicDamageMultiple, int range) {
        super(settings);
        PROJECTILES = predicate;
        ENCHANTMENTS = enchantmentList;
        USING_SPEED = usingSpeed;
        MAX_USE_TICKS = getTick(maxUseTime);
        MAX_PULL_TICKS = getTick(maxPullTime);
        DAMAGE_MULTIPLE = basicDamageMultiple / (maxBulletSpeed / DEFAULT_DAMAGE_MULTIPLE);
        RANGE = range;
    }

    /**
     * 使用默认参数构建自然更替通用弓类
     *
     * @param settings        物品设置
     * @param predicate       可使用弹药
     * @param enchantmentList 可用附魔
     */
    public SRBow(Settings settings, Predicate<ItemStack> predicate, List<Enchantment> enchantmentList) {
        super(settings);
        PROJECTILES = predicate;
        ENCHANTMENTS = enchantmentList;
        USING_SPEED = DEFAULT_USING_SPEED;
        MAX_USE_TICKS = getTick(DEFAULT_MAX_USE_TIME);
        MAX_PULL_TICKS = getTick(DEFAULT_MAX_PULL_TIME);
        DAMAGE_MULTIPLE = 1;
        RANGE = DEFAULT_RANGE;
    }

    /**
     * 获取使用操作：弓
     */
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    /**
     * 获取可使用弹药
     */
    @Override
    public Predicate<ItemStack> getProjectiles() {
        return PROJECTILES;
    }

    /**
     * 获取最大使用时间
     */
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return MAX_USE_TICKS;
    }

    /**
     * 获取拉弓进度
     */
    public float getPullProgress(int useTicks) {
        return (useTicks > MAX_PULL_TICKS) ? 1 : (float) useTicks / MAX_PULL_TICKS;
    }

    /**
     * 获取范围
     */
    @Override
    public int getRange() {
        return RANGE;
    }

    /**
     * 设置使用前操作
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // 速度参数重置
        prevSpeed = 0;
        prev2Speed = 0;
        // 默认操作
        ItemStack itemStack = user.getStackInHand(hand);
        boolean bl = !user.getProjectileType(itemStack).isEmpty();
        if (!user.getAbilities().creativeMode && !bl) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    /**
     * 设置使用中每刻操作，用于修改使用时速度
     */
    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        // 如果为客户端
        if (world.isClient && Math.abs(USING_SPEED) != SRData.Player.SNEAK_SPEED) {
            double speed = SRData.Player.getVelocitySpeed(user.getVelocity());
            double speedMultiply;
            double speedDifferential = prevSpeed / speed;
            // 如果玩家在潜行则修改前行速度倍率
            speedMultiply = user.isSneaking()
                    ? (USING_SPEED / SRData.Player.WALK_SPEED) * SRData.Player.SNEAK_SPEED / speed
                    : USING_SPEED / speed;
            if (!Double.isInfinite(speedMultiply) && !Double.isNaN(speedMultiply)) {
                //  如果使用时速度大于前行速度并在减速则不使用速度修改
                if (Math.abs(USING_SPEED) > SRData.Player.SNEAK_SPEED) {
                    if (speedDifferential < 1.01 || Math.abs(prev2Speed - speed) < 0.2)
                        user.move(MovementType.SELF, user.getVelocity().multiply(speedMultiply, 1, speedMultiply));
                    //  如果使用时速度小于前行速度并在加速则不使用速度修改
                } else if (Math.abs(USING_SPEED) < SRData.Player.SNEAK_SPEED) {
                    if (speedDifferential > 0.99 || Math.abs(prev2Speed - speed) < 0.2)
                        user.move(MovementType.SELF, user.getVelocity().multiply(speedMultiply, 1, speedMultiply));
                }
            }
            // 给上一个速度参数赋值
            prev2Speed = prevSpeed;
            prevSpeed = speed;
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    /**
     * 设置弹丸 ID 的 NBT 以供 JSON 渲染使用
     */
    public abstract void setBulletId(ItemStack stack, ItemStack useBullet);

    /**
     * 获取 NBT 弹丸 ID 以供 JSON 渲染使用
     */
    public abstract float getBulletId(ItemStack stack);
}
