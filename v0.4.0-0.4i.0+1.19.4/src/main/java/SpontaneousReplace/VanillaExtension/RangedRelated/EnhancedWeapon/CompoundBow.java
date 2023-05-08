package SpontaneousReplace.VanillaExtension.RangedRelated.EnhancedWeapon;

import SpontaneousReplace.Generic.SRBow;
import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static SpontaneousReplace.Generic.SRData.MOD_ID;

/**
 * <b style="color:FFC800"><font size="+2">CompoundBow：复合弓</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加一种性能极佳的强力远程武器</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/27 17:08
 */
public class CompoundBow extends SRBow {
    public static final double MAX_PULL_TIME = 1.5;
    public static final double BASIC_DAMAGE_MULTIPLE = 2;
    public static final double PULLING_SCALE_MULTIPLE = 0.5;
    public static final float MAX_ARROW_SPEED = 6;
    public static final int RANGE = BowItem.RANGE * 2;
    public static final int MAX_DAMAGE = Items.BOW.getMaxDamage() * 5;
    public static ItemStack useArrow = null;

    /**
     * <p>复合弓可用弹丸：</p>
     * <p>箭、药箭、光灵箭</p>
     */
    public static final Predicate<ItemStack> COMPOUND_BOW_PROJECTILES =
            (stack) -> stack.isOf(Items.ARROW)
                    || stack.isOf(Items.TIPPED_ARROW)
                    || stack.isOf(Items.SPECTRAL_ARROW);
    /**
     * <p>复合弓可用附魔：</p>
     * <p>无限、火矢、力量、击退、耐久、消失诅咒、经验修补</p>
     */
    public static final List<Enchantment> COMPOUND_BOW_ENCHANTMENTS = new ArrayList<>(
            Arrays.asList(Enchantments.INFINITY,
                    Enchantments.FLAME,
                    Enchantments.POWER,
                    Enchantments.PUNCH,
                    Enchantments.UNBREAKING,
                    Enchantments.VANISHING_CURSE,
                    Enchantments.MENDING)
    );

    /**
     * 构建复合弓
     */
    public CompoundBow(Settings settings) {
        super(settings, COMPOUND_BOW_PROJECTILES, DEFAULT_USING_SPEED, DEFAULT_MAX_USE_TIME, MAX_PULL_TIME, MAX_ARROW_SPEED, BASIC_DAMAGE_MULTIPLE, RANGE);
    }

    /**
     * 设置停止使用时的操作，如发射箭等
     */
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        // 检查用户使用者是否为玩家
        if (user instanceof PlayerEntity playerEntity) {
            // 检查是否在创造模式或者拥有“无限”附魔
            boolean inCreateOrInfinity = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            // 检查玩家是否有弹丸，如果没有弹丸则不操作
            if (!useArrow.isEmpty() || inCreateOrInfinity) {
                // 获取弹丸已使用游戏刻
                int usedTicks = this.getMaxUseTime(stack) - remainingUseTicks;
                // 获取弹弓拉弓进度
                float pullProgress = getPullProgress(usedTicks);
                // 如果拉弓进度不小于 0.1
                if (!((double) pullProgress < 0.1)) {
                    // 如果在创造模式或者拥有“无限”附魔以及弹丸是默认弹丸
                    boolean inCreateOrInfinityAndDefaultBullet = inCreateOrInfinity && useArrow.isOf(Items.ARROW);
                    if (!world.isClient) {
                        // 创建箭实体
                        ArrowItem arrowItem = (ArrowItem) (useArrow.getItem() instanceof ArrowItem ? useArrow.getItem() : Items.ARROW);
                        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, useArrow, playerEntity);
                        // 设置速度速度
                        persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, pullProgress * MAX_ARROW_SPEED, 1.0F);
                        // 设置基础伤害增加
                        persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() * DAMAGE_MULTIPLE);
                        if (pullProgress == 1.0F)
                            persistentProjectileEntity.setCritical(true);

                        // 设置“力量”效果
                        int powerLevel = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                        if (powerLevel > 0)
                            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double) powerLevel * 0.5 + 0.5);

                        // 设置“冲击”效果
                        int punchLevel = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                        if (punchLevel > 0)
                            persistentProjectileEntity.setPunch(punchLevel);

                        // 设置“火矢”效果
                        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0)
                            persistentProjectileEntity.setOnFireFor(100);

                        // 设置工具损伤
                        stack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
                        if (inCreateOrInfinityAndDefaultBullet || playerEntity.getAbilities().creativeMode && (useArrow.isOf(Items.SPECTRAL_ARROW) || useArrow.isOf(Items.TIPPED_ARROW))) {
                            persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                        }

                        // 生成弹丸实体
                        world.spawnEntity(persistentProjectileEntity);
                    }

                    // 播放音效
                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + pullProgress * 0.5F);
                    if (!inCreateOrInfinityAndDefaultBullet && !playerEntity.getAbilities().creativeMode) {
                        useArrow.decrement(1);
                        if (useArrow.isEmpty()) {
                            playerEntity.getInventory().removeOne(useArrow);
                        }
                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
    }

    /**
     * 在开始使用复合弓时获取弹丸
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // 获取使用物品
        ItemStack itemStack = null;
        for (ItemStack stack : user.getHandItems()) {
            if (stack.isOf(COMPOUND_BOW))
                itemStack = stack;
        }
        // 检查是否在创造模式或者拥有“无限”附魔
        boolean inCreateOrInfinity = user.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, itemStack) > 0;
        // 获取弹丸
        useArrow = user.getProjectileType(itemStack);
        // 检查玩家是否有箭，如果没有箭但在创造模式或者拥有“无限”附魔则使用箭
        if (!useArrow.isEmpty() || inCreateOrInfinity) {
            if (useArrow.isEmpty())
                useArrow = new ItemStack(Items.ARROW);
        }
        return super.use(world, user, hand);
    }

    /**
     * 获取弹丸 ID 以供复合弓 JSON 渲染使用
     */
    @Override
    public float getBulletId() {
        if (useArrow.isOf(Items.ARROW))
            return 0;
        else if (useArrow.isOf(Items.TIPPED_ARROW))
            return 0.1F;
        else if (useArrow.isOf(Items.SPECTRAL_ARROW))
            return 0.2F;
        return 0;
    }

    /**
     * <p>复合弓：</p>
     * <p>攻击力为弓的 2 倍</p>
     * <p>拉弓用时为 1.5 秒</p>
     * <p>拉弓缩放为 0.5</p>
     * <p>最大出箭速度为 6</p>
     * <p>使用速度为默认使用速度</p>
     * <p>耐久为弓的 2 倍</p>
     */
    public static final CompoundBow COMPOUND_BOW = new CompoundBow(new Settings().maxDamageIfAbsent(MAX_DAMAGE));

    /**
     * 注册复合弓
     */
    public static void register() {
        // 注册复合弓
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "compound_bow"), COMPOUND_BOW);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(COMPOUND_BOW));
        // 注册复合弓谓词
        ModelPredicateProviderRegistry.register(COMPOUND_BOW, new Identifier("pulling"), (stack, world, entity, seed) -> {
            if (entity == null)
                return 0;
            return entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0;
        });
        ModelPredicateProviderRegistry.register(COMPOUND_BOW, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null)
                return 0.0F;
            return entity.getActiveItem() != stack ? 0.0F : ((SRBow) stack.getItem()).getPullProgress(stack.getMaxUseTime() - entity.getItemUseTimeLeft());
        });
        ModelPredicateProviderRegistry.register(COMPOUND_BOW, new Identifier("arrow"), (stack, world, entity, seed) -> {
            if (entity == null)
                return 0;
            return entity.getActiveItem() != stack ? 0 : ((CompoundBow) stack.getItem()).getBulletId();
        });
    }
}
