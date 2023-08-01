package pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.basicweapon;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.*;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import pres.saikel_orado.spontaneous_replace.mod.util.SRBow;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.projectile.stoneball.StoneballEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.Player;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RangedRelated.*;

/**
 * <b style="color:FFC800"><font size="+2">Slingshot：丫弹弓</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加一种娱乐性质的远程武器</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 5.0
 * | 创建于 2023/4/22 14:08
 */
public class Slingshot extends SRBow {
    public static final double MAX_PULL_TIME = 0.5;
    public static final double USING_SPEED = Player.WALK_SPEED;
    public static final float MAX_PELLET_SPEED = 2;
    public static final int RANGE = BowItem.RANGE / 2;
    public static final int SLINGSHOT_MAX_DAMAGE = Items.BOW.getMaxDamage() / 2;

    /**
     * <p>丫弹弓可用弹丸：</p>
     * <p>石弹、鸡蛋、雪球、末影珍珠、喷溅药水、滞留药水</p>
     */
    public static final Predicate<ItemStack> SLINGSHOT_PROJECTILES = (stack) -> stack.isOf(STONEBALL) || stack.isOf(Items.EGG) || stack.isOf(Items.SNOWBALL) || stack.isOf(Items.ENDER_PEARL) || stack.isOf(Items.LINGERING_POTION) || stack.isOf(Items.SPLASH_POTION);
    /**
     * <p>丫弹弓可用附魔：</p>
     * <p>无限、力量(提升 100%+等级*20% 的弹丸速度)、击退(只对石弹有效)、耐久、消失诅咒、经验修补</p>
     */
    public static final List<Enchantment> SLINGSHOT_ENCHANTMENTS = new ArrayList<>(Arrays.asList(Enchantments.INFINITY, Enchantments.POWER, Enchantments.PUNCH, Enchantments.UNBREAKING, Enchantments.VANISHING_CURSE, Enchantments.MENDING));

    /**
     * 构建丫弹弓
     */
    public Slingshot(Settings settings) {
        super(settings,
                SLINGSHOT_PROJECTILES,
                SLINGSHOT_ENCHANTMENTS,
                USING_SPEED,
                DEFAULT_MAX_USE_TIME,
                MAX_PULL_TIME,
                MAX_PELLET_SPEED,
                DEFAULT_DAMAGE_MULTIPLE,
                RANGE);
    }

    /**
     * 设置停止使用时的操作，如弹丸发射等
     */
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        // 检查用户使用者是否为玩家
        if (user instanceof PlayerEntity playerEntity) {
            // 检查是否在创造模式或者拥有“无限”附魔
            boolean inCreateOrInfinity = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            // 获取弹丸
            ItemStack usePellet = user.getProjectileType(stack);
            // 检查玩家是否有弹丸，如果没有弹丸但在创造模式或者拥有“无限”附魔则使用默认弹丸
            if (!usePellet.isEmpty() || inCreateOrInfinity) {
                if (usePellet.isEmpty()) usePellet = new ItemStack(STONEBALL);
            }
            if (!usePellet.isEmpty() || inCreateOrInfinity) {
                // 获取弹丸已使用游戏刻
                int usedTicks = this.getMaxUseTime(stack) - remainingUseTicks;
                // 获取弹弓拉弓进度
                float pullProgress = getPullProgress(usedTicks);
                // 如果拉弓进度不小于 0.1
                if (!((double) pullProgress < 0.1)) {
                    // 如果在创造模式或者拥有“无限”附魔以及弹丸是默认弹丸
                    boolean inCreateOrInfinityAndDefaultBullet = inCreateOrInfinity && usePellet.isOf(STONEBALL);
                    // 在服务器端运行
                    if (!world.isClient) {
                        ThrownItemEntity pelletEntity;
                        // 获取弹丸实体
                        if (usePellet.isOf(Items.EGG)) pelletEntity = new EggEntity(world, user);
                        else if (usePellet.isOf(Items.SNOWBALL)) pelletEntity = new SnowballEntity(world, user);
                        else if (usePellet.isOf(Items.ENDER_PEARL)) pelletEntity = new EnderPearlEntity(world, user);
                        else if (usePellet.isOf(Items.LINGERING_POTION)) {
                            pelletEntity = new PotionEntity(world, user);
                            pelletEntity.setItem(usePellet);
                        } else if (usePellet.isOf(Items.SPLASH_POTION)) {
                            pelletEntity = new PotionEntity(world, user);
                            pelletEntity.setItem(usePellet);
                        } else pelletEntity = new StoneballEntity(world, user);
                        // 设置弹丸速度
                        pelletEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, pullProgress * MAX_PELLET_SPEED, 1.0F);

                        // 设置“力量”效果
                        int powerLevel = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                        if (powerLevel > 0) {
                            float addSpeed = (float) (powerLevel * 0.2 + 1);
                            pelletEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, pullProgress * MAX_PELLET_SPEED * addSpeed, 1.0F);
                        }

                        // 设置“冲击”效果
                        int punchLevel = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                        if (punchLevel > 0 && pelletEntity instanceof StoneballEntity) {
                            ((StoneballEntity) pelletEntity).setPunchLevel(punchLevel, pelletEntity.getVelocity());
                        }

                        // 设置工具损伤
                        stack.damage(1, playerEntity, (player) -> player.sendToolBreakStatus(playerEntity.getActiveHand()));

                        // 生成弹丸实体
                        world.spawnEntity(pelletEntity);
                    }

                    // 播放音效
                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SLINGSHOT_THROW, SoundCategory.PLAYERS, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                    if (!inCreateOrInfinityAndDefaultBullet && !playerEntity.getAbilities().creativeMode) {
                        usePellet.decrement(1);
                        if (usePellet.isEmpty()) {
                            playerEntity.getInventory().removeOne(usePellet);
                        }
                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
    }

    /**
     * 在开始使用丫弹弓时获取弹丸
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // 获取使用物品
        ItemStack itemStack = new ItemStack(SLINGSHOT);
        for (ItemStack stack : user.getHandItems()) {
            if (stack.isOf(SLINGSHOT)) itemStack = stack;
        }
        // 检查是否在创造模式或者拥有“无限”附魔
        boolean inCreateOrInfinity = user.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, itemStack) > 0;
        // 获取弹丸
        ItemStack usePellet = user.getProjectileType(itemStack);
        // 检查玩家是否有弹丸，如果没有弹丸但在创造模式或者拥有“无限”附魔则使用默认弹丸
        if (!usePellet.isEmpty() || inCreateOrInfinity) {
            if (usePellet.isEmpty()) usePellet = new ItemStack(STONEBALL);
        }
        setBulletId(itemStack, usePellet);

        return super.use(world, user, hand);
    }

    /**
     * 设置弹丸 ID 的 NBT 以供 JSON 渲染使用
     */
    @Override
    public void setBulletId(ItemStack stack, ItemStack usePellet) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putFloat("Bullet", 0);
        if (usePellet != null) {
            if (usePellet.isOf(STONEBALL)) nbtCompound.putFloat("Pellet", 0);
            else if (usePellet.isOf(Items.EGG)) nbtCompound.putFloat("Pellet", 0.1F);
            else if (usePellet.isOf(Items.SNOWBALL)) nbtCompound.putFloat("Pellet", 0.2F);
            else if (usePellet.isOf(Items.ENDER_PEARL)) nbtCompound.putFloat("Pellet", 0.3F);
            else if (usePellet.isOf(Items.LINGERING_POTION)) nbtCompound.putFloat("Pellet", 0.4F);
            else if (usePellet.isOf(Items.SPLASH_POTION)) nbtCompound.putFloat("Pellet", 0.5F);
        }
        nbtCompound.getFloat("Pellet");
    }

    /**
     * 获取 NBT 弹丸 ID 以供 JSON 渲染使用
     */
    @Override
    public float getBulletId(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound != null ? nbtCompound.getFloat("Pellet") : 0;
    }
}
