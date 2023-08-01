package pres.saikel_orado.spontaneous_replace.mod.util;

import com.google.common.collect.Lists;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import pres.saikel_orado.spontaneous_replace.mod.data.SRData;

import java.util.List;
import java.util.function.Predicate;

import static pres.saikel_orado.spontaneous_replace.mod.util.SRUtil.getTick;

/**
 * <b style="color:FFC800"><font size="+2">SRCrossbow：自然更替通用弩类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">辅助弩的创建的数据直观和清晰</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 5.0
 * | 创建于 2023/4/24 18:06
 */
public abstract class SRCrossbow extends RangedWeaponItem implements Vanishable {
    public static final double DEFAULT_USING_SPEED = SRData.Player.SNEAK_SPEED;
    public static final double DEFAULT_MAX_USE_TIME = 1.25;
    public static final double DEFAULT_MAX_BULLET_SPEED = 3.15;
    public static final double DEFAULT_DAMAGE_MULTIPLE = DEFAULT_MAX_BULLET_SPEED;
    public static final int DEFAULT_RANGE = CrossbowItem.RANGE;
    public final Predicate<ItemStack> PROJECTILES;
    public final List<Enchantment> ENCHANTMENTS;
    public final double USING_SPEED;
    public final int MAX_USE_TICKS;
    public final double MAX_BULLET_SPEED;
    public final double DAMAGE_MULTIPLE;
    public final int RANGE;
    protected boolean charged = false;
    protected boolean loaded = false;
    /**
     * 上刻速度
     */
    protected static double prevSpeed = 0;
    protected static double prev2Speed = 0;

    /**
     * 构建自然更替通用弩类
     *
     * @param settings            物品设置
     * @param predicate           可使用弹药
     * @param enchantmentList     可用附魔
     * @param usingSpeed          使用时速度
     * @param maxUseTime          最大张弩时间
     * @param maxBulletSpeed      最大弹药速度
     * @param basicDamageMultiple 基础伤害倍数
     * @param range               范围
     */
    public SRCrossbow(Settings settings, Predicate<ItemStack> predicate, List<Enchantment> enchantmentList, double usingSpeed, double maxUseTime, double maxBulletSpeed, double basicDamageMultiple, int range) {
        super(settings);
        PROJECTILES = predicate;
        ENCHANTMENTS = enchantmentList;
        USING_SPEED = usingSpeed;
        MAX_USE_TICKS = getTick(maxUseTime);
        MAX_BULLET_SPEED = maxBulletSpeed;
        DAMAGE_MULTIPLE = basicDamageMultiple / (maxBulletSpeed / DEFAULT_DAMAGE_MULTIPLE);
        RANGE = range;
    }

    /**
     * 使用默认参数构建自然更替通用弩类
     *
     * @param settings        物品设置
     * @param predicate       可使用弹药
     * @param enchantmentList 可用附魔
     */
    public SRCrossbow(Settings settings, Predicate<ItemStack> predicate, List<Enchantment> enchantmentList) {
        super(settings);
        PROJECTILES = predicate;
        ENCHANTMENTS = enchantmentList;
        USING_SPEED = DEFAULT_USING_SPEED;
        MAX_USE_TICKS = getTick(DEFAULT_MAX_USE_TIME);
        MAX_BULLET_SPEED = DEFAULT_MAX_BULLET_SPEED;
        DAMAGE_MULTIPLE = 1;
        RANGE = DEFAULT_RANGE;
    }

    /**
     * 获取使用操作：弩
     */
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }

    /**
     * 获得持有的弹药
     */
    @Override
    public Predicate<ItemStack> getHeldProjectiles() {
        return PROJECTILES;
    }

    /**
     * 获取可使用弹药
     */
    @Override
    public Predicate<ItemStack> getProjectiles() {
        return PROJECTILES;
    }

    /**
     * 获取最大使用游戏刻
     */
    protected int getMaxUseTicks() {
        return MAX_USE_TICKS;
    }

    /**
     * 获取张弩时间
     */
    public int getPullTime(ItemStack stack) {
        // 设置“快速装填”效果
        int quickChargeLevel = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
        return quickChargeLevel == 0 ? getMaxUseTicks() : getMaxUseTicks() - getMaxUseTicks() / 5 * quickChargeLevel;
    }

    /**
     * 获取最大使用时间
     */
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return getPullTime(stack) + 3;
    }

    /**
     * 获取张弩进度
     */
    public float getPullProgress(int useTicks, ItemStack stack) {
        float progress = (float) useTicks / (float) getPullTime(stack);
        return Math.min(progress, 1.0F);
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
        // 如果已装填
        if (isCharged(itemStack)) {
            // 发射所有
            shootAll(world, user, hand, itemStack, getSpeed(itemStack), 1.0F);
            // 设置未装填
            setCharged(itemStack, false);
            return TypedActionResult.consume(itemStack);
            // 如果使用者有弹药
        } else if (!user.getProjectileType(itemStack).isEmpty()) {
            // 但未装填
            if (!isCharged(itemStack)) {
                charged = false;
                loaded = false;
                user.setCurrentHand(hand);
            }
            return TypedActionResult.consume(itemStack);
            // 如果未装填
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

    /**
     * 设置使用中每刻操作，用于修改使用时速度等
     */
    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        // 如果在服务端
        if (!world.isClient) {
            // 设置“快速装填”音效
            int quickChargeLevel = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
            SoundEvent soundEvent = getQuickChargeSound(quickChargeLevel);
            SoundEvent soundEvent2 = quickChargeLevel == 0 ? getLoadingSound() : null;
            // 获取张弩进度
            float pullProgress = getPullProgress(stack.getMaxUseTime() - remainingUseTicks, stack);
            if (pullProgress < 0.2F) {
                charged = false;
                loaded = false;
            }

            if (pullProgress >= 0.2F && !charged) {
                charged = true;
                if (soundEvent != null)
                    world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, SoundCategory.PLAYERS, 0.5F, 1.0F);
            }

            if (pullProgress >= 0.5F && soundEvent2 != null && !loaded) {
                loaded = true;
                world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent2, SoundCategory.PLAYERS, 0.5F, 1.0F);
            }
        }

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
     * 设置停止使用时的操作，如发射箭等
     */
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        // 获取已使用游戏刻
        int usedTicks = getMaxUseTime(stack) - remainingUseTicks;
        // 获取张弩进度
        float pullProgress = getPullProgress(usedTicks, stack);
        // 如果张弩进度 ≥ 1 且未装填并装填所有弹药
        if (pullProgress >= 1.0F && !isCharged(stack) && loadProjectiles(user, stack)) {
            // 设置已装填
            setCharged(stack, true);
            // 获取声音类别
            SoundCategory soundCategory = user instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
            // 播放弩装填结束音效
            world.playSound(null, user.getX(), user.getY(), user.getZ(), getLoadingEndSound(), soundCategory, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        }
    }

    /**
     * 在释放时使用弩
     */
    @Override
    public boolean isUsedOnRelease(ItemStack stack) {
        return stack.isOf(this);
    }

    /**
     * 追加工具提示
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        // 获取弹药
        List<ItemStack> projectiles = getProjectiles(stack);
        // 如果已装填且弹药不为空
        if (isCharged(stack) && !projectiles.isEmpty()) {
            ItemStack bullet = projectiles.get(0);
            // 添加工具提示
            tooltip.add(Text.translatable("item.minecraft.crossbow.projectile").append(ScreenTexts.SPACE).append(bullet.toHoverableText()));
            // 如果是高级上下文以及弹药为烟花火箭
            if (context.isAdvanced() && bullet.isOf(Items.FIREWORK_ROCKET)) {
                List<Text> texts = Lists.newArrayList();
                // 烟花火箭添加工具提示
                Items.FIREWORK_ROCKET.appendTooltip(bullet, world, texts, context);
                // 如果提示列表不为空
                if (!texts.isEmpty()) {
                    // 将所有参数替换并添加
                    texts.replaceAll(text -> Text.literal("  ").append(text).formatted(Formatting.GRAY));
                    tooltip.addAll(texts);
                }
            }

        }
    }

    /**
     * 获取可使用弹药
     */
    protected static List<ItemStack> getProjectiles(ItemStack crossbow) {
        // 获取写在 NBT 中的弹药
        List<ItemStack> list = Lists.newArrayList();
        NbtCompound nbtCompound = crossbow.getNbt();
        if (nbtCompound != null && nbtCompound.contains("ChargedProjectiles", 9)) {
            NbtList nbtList = nbtCompound.getList("ChargedProjectiles", 10);
            if (nbtList != null) {
                for (int i = 0; i < nbtList.size(); ++i) {
                    NbtCompound nbtCompound2 = nbtList.getCompound(i);
                    list.add(ItemStack.fromNbt(nbtCompound2));
                }
            }
        }

        return list;
    }

    /**
     * 设置弹药
     */
    protected static void setProjectile(ItemStack crossbow, ItemStack projectile) {
        NbtCompound nbtCompound = crossbow.getOrCreateNbt();
        NbtList nbtList;
        if (nbtCompound.contains("ChargedProjectiles", 9)) {
            nbtList = nbtCompound.getList("ChargedProjectiles", 10);
        } else {
            nbtList = new NbtList();
        }

        NbtCompound nbtCompound2 = new NbtCompound();
        projectile.writeNbt(nbtCompound2);
        nbtList.add(nbtCompound2);
        nbtCompound.put("ChargedProjectiles", nbtList);
    }

    /**
     * 是否已装填
     */
    public static boolean isCharged(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound != null && nbtCompound.getBoolean("Charged");
    }

    /**
     * 设置是否已装填
     */
    public static void setCharged(ItemStack stack, boolean charged) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putBoolean("Charged", charged);
    }

    /**
     * 获取速度
     */
    protected float getSpeed(ItemStack stack) {
        return (float) (isHasProjectile(stack, Items.FIREWORK_ROCKET) ? MAX_BULLET_SPEED / 2 : MAX_BULLET_SPEED);
    }

    /**
     * 是否拥有某种弹药
     */
    public static boolean isHasProjectile(ItemStack crossbow, Item projectile) {
        return getProjectiles(crossbow).stream().anyMatch((stack) -> stack.isOf(projectile));
    }

    /**
     * 发射所有
     */
    public void shootAll(World world, LivingEntity entity, Hand hand, ItemStack stack, float speed, float divergence) {
        // 获取弹药
        List<ItemStack> projectiles = getProjectiles(stack);
        // 获取所有音高
        float[] soundPitches = getSoundPitches(entity.getRandom());

        // 发射所有弹药
        for (int i = 0; i < projectiles.size(); ++i) {
            ItemStack itemStack = projectiles.get(i);
            // 如果实体为玩家且在创造模式
            boolean isPlayerAndInCreative = entity instanceof PlayerEntity && ((PlayerEntity) entity).getAbilities().creativeMode;
            // 设置“多重射击”的不同角度弹药发射
            if (!itemStack.isEmpty()) {
                switch (i) {
                    case 0 ->
                            shoot(world, entity, hand, stack, itemStack, soundPitches[i], isPlayerAndInCreative, speed, divergence, 0.0F);
                    case 1 ->
                            shoot(world, entity, hand, stack, itemStack, soundPitches[i], isPlayerAndInCreative, speed, divergence, -10.0F);
                    case 2 ->
                            shoot(world, entity, hand, stack, itemStack, soundPitches[i], isPlayerAndInCreative, speed, divergence, 10.0F);
                }
            }
        }
        // 射击后操作
        postShoot(world, entity, stack);
    }

    /**
     * 发射后
     */
    protected void postShoot(World world, LivingEntity entity, ItemStack stack) {
        // 如果实体为玩家实体
        if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
            // 在服务端操作
            if (!world.isClient)
                // 触发弩的射击
                Criteria.SHOT_CROSSBOW.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
        }
        // 清除所有弹药
        clearProjectiles(stack);
    }

    /**
     * 清除弹药
     */
    protected static void clearProjectiles(ItemStack crossbow) {
        // 清除写在 NBT 中的弹药数据
        NbtCompound nbtCompound = crossbow.getNbt();
        if (nbtCompound != null) {
            NbtList nbtList = nbtCompound.getList("ChargedProjectiles", 9);
            nbtList.clear();
            nbtCompound.put("ChargedProjectiles", nbtList);
        }

    }

    /**
     * 创建箭矢
     */
    protected PersistentProjectileEntity createArrow(World world, LivingEntity entity, ItemStack crossbow, ItemStack arrow) {
        // 创建箭实体
        ArrowItem arrowItem = (ArrowItem) (arrow.getItem() instanceof ArrowItem ? arrow.getItem() : Items.ARROW);
        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, arrow, entity);
        // 如果是玩家则设置 “Critical”
        if (entity instanceof PlayerEntity)
            persistentProjectileEntity.setCritical(true);
        // 设置声音
        persistentProjectileEntity.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
        // 设置由弩射击
        persistentProjectileEntity.setShotFromCrossbow(true);
        // 设置“穿透”效果
        int piercingLevel = EnchantmentHelper.getLevel(Enchantments.PIERCING, crossbow);
        if (piercingLevel > 0)
            persistentProjectileEntity.setPierceLevel((byte) piercingLevel);

        return persistentProjectileEntity;
    }

    /**
     * 装填所有弹药
     */
    protected abstract boolean loadProjectiles(LivingEntity shooter, ItemStack projectile);

    /**
     * 获取所有音高
     */
    protected static float[] getSoundPitches(Random random) {
        boolean randomBool = random.nextBoolean();
        return new float[]{1.0F, getSoundPitch(randomBool, random), getSoundPitch(!randomBool, random)};
    }

    /**
     * 获取音高
     */
    protected static float getSoundPitch(boolean flag, Random random) {
        float pitch = flag ? 0.63F : 0.43F;
        return 1.0F / (random.nextFloat() * 0.5F + 1.8F) + pitch;
    }

    /**
     * 获取“快速装填”音效
     */
    protected abstract SoundEvent getQuickChargeSound(int level);

    /**
     * 获取装填中音效
     */
    protected abstract SoundEvent getLoadingSound();

    /**
     * 获取装填结束音效
     */
    protected abstract SoundEvent getLoadingEndSound();

    /**
     * 发射
     */
    protected abstract void shoot(World world, LivingEntity shooter, Hand hand, ItemStack crossbow, ItemStack projectile, float soundPitch, boolean creative, float speed, float divergence, float simulated);

    /**
     * 设置弹丸 ID 的 NBT 以供 JSON 渲染使用
     */
    public abstract void setBulletId(ItemStack stack, ItemStack useBullet);

    /**
     * 获取 NBT 弹丸 ID 以供 JSON 渲染使用
     */
    public abstract float getBulletId(ItemStack stack);
}
