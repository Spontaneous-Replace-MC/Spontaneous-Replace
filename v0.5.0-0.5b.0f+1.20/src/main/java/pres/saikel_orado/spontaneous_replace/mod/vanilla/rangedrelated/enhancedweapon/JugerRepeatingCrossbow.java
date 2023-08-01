package pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon;

import com.google.common.collect.Lists;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import pres.saikel_orado.spontaneous_replace.mod.util.SRCrossbow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.Advancements.ARROWSTORM;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RangedRelated.*;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRUtil.getTick;

/**
 * <b style="color:FFC800"><font size="+2">JugerRepeatingCrossbow：诸葛连弩</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加一种强力的近战连发远程物品</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 6.0
 * | 创建于 2023/4/30 12:38
 */
public class JugerRepeatingCrossbow extends SRCrossbow {
    public static final double MAX_USE_TIME = 10;
    public static final double BASIC_DAMAGE_MULTIPLE = 1.25;
    public static final float MAX_ARROW_SPEED = 3.5F;
    public static final int RANGE = CrossbowItem.RANGE / 2;
    public static final int JUGER_REPEATING_CROSSBOW_MAX_DAMAGE = Items.CROSSBOW.getMaxDamage() * 3;
    public static final int MAX_CHARGED_BULLET_NUM = 10;
    private int maxUseTicks = 0;

    /**
     * <p>诸葛连弩可用弹丸：</p>
     * <p>箭、药箭、光灵箭、烟花火箭</p>
     */
    public static final Predicate<ItemStack> JUGER_REPEATING_CROSSBOW_PROJECTILES =
            (stack) -> stack.isOf(Items.ARROW)
                    || stack.isOf(Items.TIPPED_ARROW)
                    || stack.isOf(Items.SPECTRAL_ARROW)
                    || stack.isOf(Items.FIREWORK_ROCKET);
    /**
     * <p>诸葛连弩可用附魔：</p>
     * <p>多重射击、快速装填、耐久、消失诅咒、经验修补</p>
     */
    public static final List<Enchantment> JUGER_REPEATING_CROSSBOW_ENCHANTMENTS = new ArrayList<>(
            Arrays.asList(Enchantments.MULTISHOT,
                    Enchantments.QUICK_CHARGE,
                    Enchantments.UNBREAKING,
                    Enchantments.VANISHING_CURSE,
                    Enchantments.MENDING)
    );

    /**
     * 构建诸葛连弩
     */
    public JugerRepeatingCrossbow(Settings settings) {
        super(settings,
                JUGER_REPEATING_CROSSBOW_PROJECTILES,
                JUGER_REPEATING_CROSSBOW_ENCHANTMENTS,
                DEFAULT_USING_SPEED,
                MAX_USE_TIME,
                MAX_ARROW_SPEED,
                BASIC_DAMAGE_MULTIPLE,
                RANGE);
    }

    /**
     * 获取最大使用游戏刻
     */
    @Override
    protected int getMaxUseTicks() {
        return maxUseTicks;
    }

    /**
     * 设置停止使用时的操作，如发射箭等
     */
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        // 到了每个弹药可装填的时间则装填每个弹药
        for (int count = 1; count <= getChargedBulletNum(stack) && !isCharged(stack); count++)
            loadProjectiles(user, stack);

        if (getChargedBulletNum(stack) > 0 && !isCharged(stack)) {
            // 设置已装填
            setCharged(stack, true);
            // 获取声音类别
            SoundCategory soundCategory = user instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
            // 播放弩装填结束音效
            world.playSound(null, user.getX(), user.getY(), user.getZ(), getLoadingEndSound(), soundCategory, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        }
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
        maxUseTicks = getTick(getCanLoadPulletNum(user, itemStack));
        setBulletId(itemStack, user.getProjectileType(itemStack));
        // 如果已装填
        if (isCharged(itemStack)) {
            // 发射所有
            shootAll(world, user, hand, itemStack, getSpeed(itemStack), 1.0F);
            setIsShoot(itemStack, true);
            // 设置装填数量
            setChargedBulletNum(itemStack, getChargedBulletNum(itemStack) - 1);
            // 设置未装填
            if (getChargedBulletNum(itemStack) == 0)
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
     * 取消发射状态
     */
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        setIsShoot(stack, false);
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    /**
     * 设置上弹音效与装填数量
     */
    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        int canLoadPulletNum = getCanLoadPulletNum(user, stack);
        double pullProgress = getPullProgress(getMaxUseTime(stack) - remainingUseTicks, stack) * canLoadPulletNum;
        // 播放上弹音效
        if (pullProgress % 1 == 0.5 && pullProgress <= canLoadPulletNum)
            world.playSound(null, user.getX(), user.getY(), user.getZ(), JUGER_REPEATING_CROSSBOW_LOADING_START, SoundCategory.PLAYERS, 1.0F, 1);
        // 设置装填数量
        if (!world.isClient && !isCharged(stack) && pullProgress % 1 == 0 && pullProgress != 0 && getChargedBulletNum(stack) < canLoadPulletNum)
            setChargedBulletNum(stack, getChargedBulletNum(stack) + 1);
        super.usageTick(world, user, stack, remainingUseTicks);
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
     * 发射所有
     */
    @Override
    public void shootAll(World world, LivingEntity entity, Hand hand, ItemStack stack, float speed, float divergence) {
        // 获取弹药与单发弹药数量
        List<ItemStack> projectiles = getProjectiles(stack);
        int num = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, stack) == 0 ? 1 : 3;
        // 获取所有音高
        float[] soundPitches = getSoundPitches(entity.getRandom());

        // 发射所有弹药
        for (int i = 0; i < num; ++i) {
            ItemStack itemStack = projectiles.get(i);
            // 如果实体为玩家且在创造模式
            boolean isPlayerAndInCreative = entity instanceof PlayerEntity && ((PlayerEntity) entity).getAbilities().creativeMode;
            // 设置“多重射击”的不同角度弹药发射
            if (!itemStack.isEmpty()) {
                if (i == 0)
                    shoot(world, entity, hand, stack, itemStack, soundPitches[i], isPlayerAndInCreative, speed, divergence, 0);
                else
                    shoot(world, entity, hand, stack, itemStack, soundPitches[i], isPlayerAndInCreative, speed, divergence, 1);
            }
        }
        // 射击后操作
        postShoot(world, entity, stack);
    }

    /**
     * 发射
     */
    @Override
    protected void shoot(World world, LivingEntity shooter, Hand hand, ItemStack crossbow, ItemStack projectile, float soundPitch, boolean creative, float speed, float divergence, float isCopy) {
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
                if (creative || isCopy != 0)
                    // 设置为创造模式不可拾起
                    ((PersistentProjectileEntity) projectileEntity).pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            }

            // 如果使用者是弩使用者
            if (shooter instanceof CrossbowUser crossbowUser)
                // 弩使用者发射
                crossbowUser.shoot(crossbowUser.getTarget(), crossbow, projectileEntity, 0);
            else {
                // 设置弹药速度
                Vec3d vec3d = shooter.getOppositeRotationVector(1.0F);
                Quaternionf quaternionf = (new Quaternionf()).setAngleAxis(0, vec3d.x, vec3d.y, vec3d.z);
                Vec3d vec3d2 = shooter.getRotationVec(1.0F);
                Vector3f vector3f = vec3d2.toVector3f().rotate(quaternionf);
                Random random = new Random();
                projectileEntity.setVelocity(
                        vector3f.x() + random.nextFloat(-0.1F, 0.1F),
                        vector3f.y() + random.nextFloat(-0.1F, 0.1F),
                        vector3f.z() + random.nextFloat(-0.1F, 0.1F),
                        speed, divergence);
            }

            // 设置弩损伤
            crossbow.damage(bulletIsFireworkRocket ? 3 : 1, shooter, (entity) -> entity.sendToolBreakStatus(hand));
            // 生成弹药
            world.spawnEntity(projectileEntity);
            // 播放音效
            world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), JUGER_REPEATING_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, soundPitch);
            world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), getLoadingEndSound(), SoundCategory.PLAYERS, 1.0F, soundPitch);
            // 设置”箭雨！“成就
            if (shooter instanceof PlayerEntity player) {
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putInt(ARROWSTORM, 1);
                player.writeCustomDataToNbt(nbtCompound);
            }
        }
    }

    /**
     * 发射后
     */
    @Override
    protected void postShoot(World world, LivingEntity entity, ItemStack stack) {
        // 如果实体为玩家实体
        if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
            // 在服务端操作
            if (!world.isClient)
                // 触发弩的射击
                Criteria.SHOT_CROSSBOW.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
        }
        // 清除弹药
        if (getChargedBulletNum(stack) == 1)
            clearProjectiles(stack);
    }

    /**
     * 设置”突击手“成就
     */
    @Override
    protected PersistentProjectileEntity createArrow(World world, LivingEntity entity, ItemStack crossbow, ItemStack arrow) {
        PersistentProjectileEntity arrowEntity = super.createArrow(world, entity, crossbow, arrow);
        NbtCompound nbtCompound = new NbtCompound();
        nbtCompound.putBoolean("JgShot", true);
        arrowEntity.writeCustomDataToNbt(nbtCompound);
        return arrowEntity;
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
     * 设置已装填弹药数
     *
     * @param value 设置值
     */
    public void setChargedBulletNum(ItemStack stack, int value) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putInt("ChargedBulletNum", value);
    }

    /**
     * 获取已装填弹药数
     *
     * @return 设置后的已装填弹药数
     */
    public static int getChargedBulletNum(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound != null ? nbtCompound.getInt("ChargedBulletNum") : 0;
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
            int num = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, stack) == 0 ? 1 : 3;
            // 添加工具提示
            tooltip.add(Text.translatable("item.minecraft.crossbow.projectile")
                    .append(ScreenTexts.SPACE).append(bullet.toHoverableText())
                    // 显示弹药数量
                    .append(" x ").append(String.valueOf(getChargedBulletNum(stack) * num)));
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
     * 获取能装填弹药数
     */
    public int getCanLoadPulletNum(LivingEntity user, ItemStack stack) {
        if (user instanceof PlayerEntity player && !player.isCreative())
            return Math.min(player.getInventory().count(player.getProjectileType(stack).getItem()), MAX_CHARGED_BULLET_NUM);
        return MAX_CHARGED_BULLET_NUM;
    }

    /**
     * 获取“快速装填”音效
     */
    @Override
    protected SoundEvent getQuickChargeSound(int level) {
        return null;
    }

    /**
     * 获取装填中音效
     */
    @Override
    protected SoundEvent getLoadingSound() {
        return null;
    }

    /**
     * 获取装填结束音效
     */
    @Override
    protected SoundEvent getLoadingEndSound() {
        return JUGER_REPEATING_CROSSBOW_LOADING_END;
    }

    /**
     * 设置是否射击的 NBT 以供 JSON 渲染使用
     */
    public static void setIsShoot(ItemStack stack, boolean isShoot) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putBoolean("IsShoot", isShoot);
        nbtCompound.getBoolean("IsShoot");
    }

    /**
     * 获取 NBT 是否射击以供 JSON 渲染使用
     */
    public static boolean getIsShoot(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound != null && nbtCompound.getBoolean("IsShoot");
    }

    /**
     * 设置弹丸 ID 的 NBT 以供 JSON 渲染使用
     */
    @Override
    public void setBulletId(ItemStack stack, ItemStack useBullet) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putFloat("Bullet", 0);
        if (useBullet != null) {
            if (useBullet.isOf(Items.ARROW))
                nbtCompound.putFloat("Bullet", 0);
            else if (useBullet.isOf(Items.TIPPED_ARROW))
                nbtCompound.putFloat("Bullet", 0.1F);
            else if (useBullet.isOf(Items.SPECTRAL_ARROW))
                nbtCompound.putFloat("Bullet", 0.2F);
            else if (useBullet.isOf(Items.FIREWORK_ROCKET))
                nbtCompound.putFloat("Bullet", 0.3F);
        }
        nbtCompound.getFloat("Bullet");
    }

    /**
     * 获取 NBT 弹丸 ID 以供 JSON 渲染使用
     */
    @Override
    public float getBulletId(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound != null ? nbtCompound.getFloat("Bullet") : 0;
    }
}
