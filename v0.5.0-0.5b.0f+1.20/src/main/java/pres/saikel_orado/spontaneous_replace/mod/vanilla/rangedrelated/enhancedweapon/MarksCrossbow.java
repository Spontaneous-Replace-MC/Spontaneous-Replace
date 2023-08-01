package pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import pres.saikel_orado.spontaneous_replace.mod.data.SRData;
import pres.saikel_orado.spontaneous_replace.mod.util.SRCrossbow;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.projectile.fullpowersteelarrow.FullPowerSteelArrowItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RangedRelated.*;

/**
 * <b style="color:FFC800"><font size="+2">MarksCrossbow：神臂弩</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加一种极大威力的超远程攻击武器</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 6.0
 * | 创建于 2023/5/1 22:00
 */
public class MarksCrossbow extends SRCrossbow {
    public static final double MAX_USE_TIME = 10;
    public static final double USING_SPEED = -SRData.Player.SNEAK_SPEED;
    public static final double BASIC_DAMAGE_MULTIPLE = 5;
    public static final double CHARGED_SCALE_MULTIPLE = 0.1;
    public static final float MAX_ARROW_SPEED = 10F;
    public static final int RANGE = CrossbowItem.RANGE * 3;
    public static final int MARKS_CROSSBOW_MAX_DAMAGE = Items.CROSSBOW.getMaxDamage() * 3;
    public boolean canStop = false;
    public double prevX;
    public double prevZ;

    /**
     * 神臂弩可用弹丸：全威力钢箭
     */
    public static final Predicate<ItemStack> MARKS_CROSSBOW_PROJECTILES =
            (stack) -> stack.isOf(FULL_POWER_STEEL_ARROW);
    /**
     * <p>神臂弩可用附魔：</p>
     * <p>穿透、快速装填、耐久、消失诅咒、经验修补</p>
     */
    public static final List<Enchantment> MARKS_CROSSBOW_ENCHANTMENTS = new ArrayList<>(
            Arrays.asList(Enchantments.PIERCING,
                    Enchantments.QUICK_CHARGE,
                    Enchantments.UNBREAKING,
                    Enchantments.VANISHING_CURSE,
                    Enchantments.MENDING)
    );

    /**
     * 构建神臂弩
     */
    public MarksCrossbow(Settings settings) {
        super(settings,
                MARKS_CROSSBOW_PROJECTILES,
                MARKS_CROSSBOW_ENCHANTMENTS,
                USING_SPEED,
                MAX_USE_TIME,
                MAX_ARROW_SPEED,
                BASIC_DAMAGE_MULTIPLE,
                RANGE);
    }

    /**
     * 神臂弩的动作使用弓的动作
     */
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    /**
     * 设置取消射击结束
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!isCharged(user.getStackInHand(hand)))
            setShootEnd(user.getStackInHand(hand), false);
        return super.use(world, user, hand);
    }

    /**
     * 设置如果手持已装填神臂弩则无法移动
     */
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient) {
            ItemStack mainHandStack = ((PlayerEntity) entity).getMainHandStack();
            ItemStack offHandStack = ((PlayerEntity) entity).getOffHandStack();
            if (((PlayerEntity) entity).getActiveItem().isOf(MARKS_CROSSBOW)
                    || (mainHandStack.isOf(MARKS_CROSSBOW) && SRCrossbow.isCharged(mainHandStack))
                    || (offHandStack.isOf(MARKS_CROSSBOW) && SRCrossbow.isCharged(offHandStack))) {
                entity.setSprinting(false);
                if (canStop)
                    entity.setPos(prevX, entity.getY(), prevZ);
                else {
                    canStop = true;
                    prevX = entity.getX();
                    prevZ = entity.getZ();
                }
            } else canStop = false;
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    /**
     * 装填所有弹药
     */
    @Override
    protected boolean loadProjectiles(LivingEntity shooter, ItemStack projectile) {
        // 获取是否有“多重射击”
        int multishotLevel = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, projectile);
        // 获取弹药数
        int bulletNum = multishotLevel == 0 ? 1 : 3;
        // 如果实体为玩家且在创造模式
        boolean isPlayerAndInCreative = shooter instanceof PlayerEntity && ((PlayerEntity) shooter).getAbilities().creativeMode;
        // 获取弹药
        ItemStack bullet = shooter.getProjectileType(projectile);
        ItemStack bulletCopy = bullet.copy();

        for (int k = 0; k < bulletNum; ++k) {
            // 如果有多重射击则多重射击弹药复制主弹药
            if (k > 0)
                bullet = bulletCopy.copy();
            // 如果没有弹药且在创造模式
            if (bullet.isEmpty() && isPlayerAndInCreative) {
                bullet = new ItemStack(FULL_POWER_STEEL_ARROW);
                bulletCopy = bullet.copy();
            }
            // 如果无法装填弹药
            if (!loadProjectile(shooter, projectile, bullet, k > 0, isPlayerAndInCreative))
                return false;
        }
        return true;
    }

    /**
     * 装填弹药
     */
    protected boolean loadProjectile(LivingEntity shooter, ItemStack crossbow, ItemStack projectile, boolean simulated, boolean creative) {
        // 如果没弹药
        if (projectile.isEmpty())
            return false;
        else {
            ItemStack bullet;
            // 如果不在创造模式、不是多重射击
            if (!creative && !simulated) {
                // 设置物品分离
                bullet = projectile.split(1);
                // 如果弹药不为空且射手为玩家则移除一个弹药
                if (projectile.isEmpty() && shooter instanceof PlayerEntity)
                    ((PlayerEntity) shooter).getInventory().removeOne(projectile);
            } else
                // 弹药就为弹药
                bullet = projectile.copy();
            // 设置弹药
            setProjectile(crossbow, bullet);
            return true;
        }
    }

    /**
     * 修改精准度模糊属性使得神臂弩的精准度更高
     */
    @Override
    public void shootAll(World world, LivingEntity entity, Hand hand, ItemStack stack, float speed, float divergence) {
        setShootEnd(stack, true);
        super.shootAll(world, entity, hand, stack, speed, 0.3F);
    }

    /**
     * 发射
     */
    @Override
    protected void shoot(World world, LivingEntity shooter, Hand hand, ItemStack crossbow, ItemStack projectile, float soundPitch, boolean creative, float speed, float divergence, float simulated) {
        // 在服务端操作
        if (!world.isClient) {
            PersistentProjectileEntity projectileEntity;
            // 构建弹丸实体
            projectileEntity = createArrow(world, shooter, crossbow, projectile);
            // 设置基础伤害增加
            projectileEntity.setDamage(projectileEntity.getDamage() * DAMAGE_MULTIPLE);
            // 如果在创造模式且偏移不为 0
            if (creative || simulated != 0.0F)
                // 设置为创造模式不可拾起
                projectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;

            // 如果使用者是弩使用者
            if (shooter instanceof CrossbowUser crossbowUser)
                // 弩使用者发射
                crossbowUser.shoot(crossbowUser.getTarget(), crossbow, projectileEntity, simulated);
            else {
                // 设置弹药速度
                Vec3d vec3d = shooter.getOppositeRotationVector(1.0F);
                Quaternionf quaternionf = (new Quaternionf()).setAngleAxis(simulated * 0.017453292F, vec3d.x, vec3d.y, vec3d.z);
                Vec3d vec3d2 = shooter.getRotationVec(1.0F);
                Vector3f vector3f = vec3d2.toVector3f().rotate(quaternionf);
                projectileEntity.setVelocity(vector3f.x(), vector3f.y(), vector3f.z(), speed, divergence);
            }

            // 设置弩损伤
            crossbow.damage(1, shooter, (entity) -> entity.sendToolBreakStatus(hand));
            // 生成弹药
            world.spawnEntity(projectileEntity);
            // 播放音效
            world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), MARKS_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, soundPitch);
        }
    }

    /**
     * 创建箭矢
     */
    @Override
    protected PersistentProjectileEntity createArrow(World world, LivingEntity entity, ItemStack crossbow, ItemStack arrow) {
        // 创建箭实体
        FullPowerSteelArrowItem arrowItem = (FullPowerSteelArrowItem) (arrow.getItem() instanceof FullPowerSteelArrowItem ? arrow.getItem() : FULL_POWER_STEEL_ARROW);
        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, entity);
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
     * 获取“快速装填”音效
     */
    @Override
    protected SoundEvent getQuickChargeSound(int level) {
        return switch (level) {
            case 1 -> MARKS_CROSSBOW_QUICK_CHARGE_1;
            case 2 -> MARKS_CROSSBOW_QUICK_CHARGE_2;
            case 3 -> MARKS_CROSSBOW_QUICK_CHARGE_3;
            default -> MARKS_CROSSBOW_LOADING_START;
        };
    }

    /**
     * 获取装填中音效
     */
    @Override
    protected SoundEvent getLoadingSound() {
        return MARKS_CROSSBOW_LOADING_MIDDLE;
    }

    /**
     * 获取装填结束音效
     */
    @Override
    protected SoundEvent getLoadingEndSound() {
        return MARKS_CROSSBOW_LOADING_END;
    }

    /**
     * 是否已发射完毕
     */
    public static boolean isShootEnd(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound != null && nbtCompound.getBoolean("ShootEnd");
    }

    /**
     * 设置是否已发射完毕
     */
    public static void setShootEnd(ItemStack stack, boolean end) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putBoolean("ShootEnd", end);
    }

    /**
     * 设置弹丸 ID 的 NBT 以供 JSON 渲染使用
     */
    @Override
    public void setBulletId(ItemStack stack, ItemStack useBullet) {
    }

    /**
     * 获取弹丸 ID 以供神臂弩 JSON 渲染使用
     */
    @Override
    public float getBulletId(ItemStack stack) {
        return 0;
    }
}
