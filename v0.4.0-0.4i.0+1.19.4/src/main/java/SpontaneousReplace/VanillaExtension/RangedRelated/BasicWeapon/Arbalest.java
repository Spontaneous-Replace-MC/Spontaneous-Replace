package SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon;

import SpontaneousReplace.Generic.SRCrossbow;
import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static SpontaneousReplace.Generic.SRData.MOD_ID;

/**
 * <b style="color:FFC800"><font size="+2">Arbalest：劲弩</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">一种比普通弩更强的基础远程武器</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/28 0:41
 */
public class Arbalest extends SRCrossbow {
    public static final double MAX_USE_TIME = 1;
    public static final double BASIC_DAMAGE_MULTIPLE = 1.5;
    public static final double CHARGED_SCALE_MULTIPLE = 0.85;
    public static final float MAX_ARROW_SPEED = 4.5F;
    public static final int RANGE = CrossbowItem.RANGE;
    public static final int MAX_DAMAGE = Items.CROSSBOW.getMaxDamage() * 2;

    /**
     * <p>劲弩可用弹丸：</p>
     * <p>箭、药箭、光灵箭、烟花火箭</p>
     */
    public static final Predicate<ItemStack> ARBALEST_PROJECTILES =
            (stack) -> stack.isOf(Items.ARROW)
                    || stack.isOf(Items.TIPPED_ARROW)
                    || stack.isOf(Items.SPECTRAL_ARROW)
                    || stack.isOf(Items.FIREWORK_ROCKET);
    /**
     * <p>劲弩可用附魔：</p>
     * <p>多重射击、穿透、快速装填、耐久、消失诅咒、经验修补</p>
     */
    public static final List<Enchantment> ARBALEST_ENCHANTMENTS = new ArrayList<>(
            Arrays.asList(Enchantments.MULTISHOT,
                    Enchantments.PIERCING,
                    Enchantments.QUICK_CHARGE,
                    Enchantments.UNBREAKING,
                    Enchantments.VANISHING_CURSE,
                    Enchantments.MENDING)
    );

    /**
     * 构建劲弩
     */
    public Arbalest(Settings settings) {
        super(settings, ARBALEST_PROJECTILES, DEFAULT_USING_SPEED, MAX_USE_TIME, MAX_ARROW_SPEED, BASIC_DAMAGE_MULTIPLE, RANGE);
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
                bullet = new ItemStack(Items.ARROW);
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
            // 如果在创造模式且弹药为箭
            boolean inCreativeAndIsArrow = creative && projectile.getItem() instanceof ArrowItem;
            ItemStack bullet;
            // 如果不是(在创造模式且弹药为箭)并不在创造模式、不是多重射击
            if (!inCreativeAndIsArrow && !creative && !simulated) {
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
     * 发射
     */
    @Override
    protected void shoot(World world, LivingEntity shooter, Hand hand, ItemStack crossbow, ItemStack projectile, float soundPitch, boolean creative, float speed, float divergence, float simulated) {
        // 在服务端操作
        if (!world.isClient) {
            // 如果弹药是烟花火箭
            boolean bulletIsFireworkRocket = projectile.isOf(Items.FIREWORK_ROCKET);
            ProjectileEntity projectileEntity;
            // 构建弹丸实体
            if (bulletIsFireworkRocket)
                projectileEntity = new FireworkRocketEntity(world, projectile, shooter, shooter.getX(), shooter.getEyeY() - 0.15, shooter.getZ(), true);
            else {
                projectileEntity = createArrow(world, shooter, crossbow, projectile);
                // 设置基础伤害增加
                ((PersistentProjectileEntity) projectileEntity).setDamage(
                        ((PersistentProjectileEntity) projectileEntity).getDamage() * DAMAGE_MULTIPLE);
                // 如果在创造模式且偏移不为 0
                if (creative || simulated != 0.0F)
                    // 设置为创造模式不可拾起
                    ((PersistentProjectileEntity) projectileEntity).pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            }

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
            crossbow.damage(bulletIsFireworkRocket ? 3 : 1, shooter, (entity) -> entity.sendToolBreakStatus(hand));
            // 生成弹药
            world.spawnEntity(projectileEntity);
            // 播放音效
            world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SHOOT, SoundCategory.PLAYERS, 1.0F, soundPitch);
        }
    }

    /**
     * 获取“快速装填”音效
     */
    @Override
    protected SoundEvent getQuickChargeSound(int level) {
        return switch (level) {
            case 1 -> QUICK_CHARGE_1;
            case 2 -> QUICK_CHARGE_2;
            case 3 -> QUICK_CHARGE_3;
            default -> LOADING_START;
        };
    }

    /**
     * 获取装填中音效
     */
    @Override
    protected SoundEvent getLoadingSound() {
        return LOADING_MIDDLE;
    }

    /**
     * 获取装填结束音效
     */
    @Override
    protected SoundEvent getLoadingEndSound() {
        return LOADING_END;
    }

    /**
     * 获取弹丸 ID 以供劲弩 JSON 渲染使用
     */
    @Override
    public float getBulletId(ItemStack stack) {
        if (!getProjectiles(stack).isEmpty()) {
            ItemStack useBullet = getProjectiles(stack).get(0);
            if (useBullet.isOf(Items.ARROW))
                return 0;
            else if (useBullet.isOf(Items.TIPPED_ARROW))
                return 0.1F;
            else if (useBullet.isOf(Items.SPECTRAL_ARROW))
                return 0.2F;
            else if (useBullet.isOf(Items.FIREWORK_ROCKET))
                return 0.3F;
        }
        return 0;
    }

    /**
     * 劲弩装填开始音效 ID
     */
    public static final Identifier LOADING_START_ID = new Identifier(MOD_ID + ":vanilla_extension.arbalest.loading.start");
    /**
     * 劲弩装填开始音效
     */
    public static SoundEvent LOADING_START = SoundEvent.of(LOADING_START_ID);
    /**
     * 劲弩装填中音效 ID
     */
    public static final Identifier LOADING_MIDDLE_ID = new Identifier(MOD_ID + ":vanilla_extension.arbalest.loading.middle");
    /**
     * 劲弩装填中音效
     */
    public static SoundEvent LOADING_MIDDLE = SoundEvent.of(LOADING_MIDDLE_ID);
    /**
     * 劲弩装填结束音效 ID
     */
    public static final Identifier LOADING_END_ID = new Identifier(MOD_ID + ":vanilla_extension.arbalest.loading.end");
    /**
     * 劲弩装填结束音效
     */
    public static SoundEvent LOADING_END = SoundEvent.of(LOADING_END_ID);
    /**
     * 劲弩发射音效 ID
     */
    public static final Identifier SHOOT_ID = new Identifier(MOD_ID + ":vanilla_extension.arbalest.shoot");
    /**
     * 劲弩发射音效
     */
    public static SoundEvent SHOOT = SoundEvent.of(SHOOT_ID);
    /**
     * 劲弩快速装填Ⅰ音效 ID
     */
    public static final Identifier QUICK_CHARGE_1_ID = new Identifier(MOD_ID + ":vanilla_extension.arbalest.quick_charge.1");
    /**
     * 劲弩快速装填Ⅰ音效
     */
    public static SoundEvent QUICK_CHARGE_1 = SoundEvent.of(QUICK_CHARGE_1_ID);
    /**
     * 劲弩快速装填Ⅱ音效 ID
     */
    public static final Identifier QUICK_CHARGE_2_ID = new Identifier(MOD_ID + ":vanilla_extension.arbalest.quick_charge.2");
    /**
     * 劲弩快速装填Ⅱ音效
     */
    public static SoundEvent QUICK_CHARGE_2 = SoundEvent.of(QUICK_CHARGE_2_ID);
    /**
     * 劲弩快速装填Ⅲ音效 ID
     */
    public static final Identifier QUICK_CHARGE_3_ID = new Identifier(MOD_ID + ":vanilla_extension.arbalest.quick_charge.3");
    /**
     * 劲弩快速装填Ⅲ音效
     */
    public static SoundEvent QUICK_CHARGE_3 = SoundEvent.of(QUICK_CHARGE_3_ID);

    /**
     * <p>劲弩：</p>
     * <p>攻击力为弩的 1.5 倍</p>
     * <p>张弩用时为 1 秒</p>
     * <p>拉弓缩放为 0.85</p>
     * <p>最大出箭速度为 4.5</p>
     * <p>使用速度为默认速度</p>
     * <p>耐久为弩的 2 倍</p>
     */
    public static final Arbalest ARBALEST = new Arbalest(new Item.Settings().maxDamageIfAbsent(MAX_DAMAGE));

    /**
     * 注册劲弩
     */
    public static void register() {
        // 注册劲弩
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "arbalest"), ARBALEST);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(ARBALEST));
        // 注册劲弩谓词
        ModelPredicateProviderRegistry.register(ARBALEST, new Identifier("pulling"), (stack, world, entity, seed) -> {
            if (entity == null)
                return 0;
            return entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0;
        });
        ModelPredicateProviderRegistry.register(ARBALEST, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null)
                return 0.0F;
            return entity.getActiveItem() != stack ? 0.0F : ((SRCrossbow) stack.getItem()).getPullProgress(stack.getMaxUseTime() - entity.getItemUseTimeLeft(), stack);
        });
        ModelPredicateProviderRegistry.register(ARBALEST, new Identifier("charged"), (stack, world, entity, seed) -> {
            if (entity == null)
                return 0;
            return isCharged(stack) ? 1 : 0;
        });
        ModelPredicateProviderRegistry.register(ARBALEST, new Identifier("bullet"), (stack, world, entity, seed) -> {
            if (entity == null)
                return 0;
            return ((Arbalest) stack.getItem()).getBulletId(stack);
        });
        // 注册劲弩音效
        Registry.register(Registries.SOUND_EVENT, LOADING_START_ID, LOADING_START);
        Registry.register(Registries.SOUND_EVENT, LOADING_MIDDLE_ID, LOADING_MIDDLE);
        Registry.register(Registries.SOUND_EVENT, LOADING_END_ID, LOADING_END);
        Registry.register(Registries.SOUND_EVENT, SHOOT_ID, SHOOT);
        Registry.register(Registries.SOUND_EVENT, QUICK_CHARGE_1_ID, QUICK_CHARGE_1);
        Registry.register(Registries.SOUND_EVENT, QUICK_CHARGE_2_ID, QUICK_CHARGE_2);
        Registry.register(Registries.SOUND_EVENT, QUICK_CHARGE_3_ID, QUICK_CHARGE_3);
    }
}
