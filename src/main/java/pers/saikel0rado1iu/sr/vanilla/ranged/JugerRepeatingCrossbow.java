/*
 * MIT License
 *
 * Copyright (c) 2023 GameGeek-Saikel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package pers.saikel0rado1iu.sr.vanilla.ranged;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import pers.saikel0rado1iu.silk.api.item.CustomEnchantment;
import pers.saikel0rado1iu.silk.api.item.tool.weapon.ranged.Crossbow;
import pers.saikel0rado1iu.silk.util.TickUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static pers.saikel0rado1iu.silk.ropestick.DataComponentTypes.PROJECTILE_ID;
import static pers.saikel0rado1iu.sr.data.DataComponentTypes.CHARGED_PROJECTILE_NUM;
import static pers.saikel0rado1iu.sr.data.DataComponentTypes.IS_SHOOT;
import static pers.saikel0rado1iu.sr.data.SoundEvents.*;

/**
 * <h2 style="color:FFC800">诸葛连弩</h2>
 * <p style="color:FFC800">添加一种强力的近战连发远程物品</p>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class JugerRepeatingCrossbow extends Crossbow implements CustomEnchantment {
	public static final int MAX_CHARGED_BULLET_NUM = 10;
	protected int maxUseTicks = 0;
	
	public JugerRepeatingCrossbow(Settings settings) {
		super(settings);
	}
	
	/**
	 * 获取 NBT 是否射击以供 JSON 渲染使用
	 */
	public static boolean getIsShoot(ItemStack stack) {
		return stack.getOrDefault(IS_SHOOT, new IsShootComponent(false)).isShoot;
	}
	
	/**
	 * 设置是否射击的 NBT 以供 JSON 渲染使用
	 */
	public static void setIsShoot(ItemStack stack, boolean isShoot) {
		stack.set(IS_SHOOT, new IsShootComponent(isShoot));
	}
	
	/**
	 * 获取已装填弹药数
	 *
	 * @return 设置后的已装填弹药数
	 */
	public static int getChargedProjectileNum(ItemStack stack) {
		return stack.getOrDefault(CHARGED_PROJECTILE_NUM, new ChargedProjectileNumComponent(0)).num;
	}
	
	/**
	 * 设置已装填弹药数
	 *
	 * @param value 设置值
	 */
	public void setChargedProjectileNum(ItemStack stack, int value) {
		stack.set(CHARGED_PROJECTILE_NUM, new ChargedProjectileNumComponent(value));
	}
	
	@Override
	public float getFiringError() {
		return 5;
	}
	
	@Override
	public float getMaxProjectileSpeed() {
		return 3.5F;
	}
	
	@Override
	public float getMaxDamageMultiple() {
		return 1.25F;
	}
	
	@Override
	public int getMaxUseTicks() {
		return maxUseTicks;
	}
	
	@Override
	public int getRange() {
		return super.getRange() / 2;
	}
	
	/**
	 * 设置停止使用时的操作，如发射箭等
	 */
	@Override
	public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
		// 到了每个弹药可装填的时间则装填每个弹药
		for (int count = 0; count < getChargedProjectileNum(stack); count++) loadAllProjectile(user, stack);
		
		if (getChargedProjectileNum(stack) > 0) {
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
		// 获取玩家手中的物品堆
		ItemStack itemStack = user.getStackInHand(hand);
		maxUseTicks = TickUtil.getTick(getCanLoadPulletNum(user, itemStack));
		setProjectileId(itemStack, user.getProjectileType(itemStack));
		
		// 获取物品堆中的ChargedProjectilesComponent组件
		ChargedProjectilesComponent chargedProjectilesComponent = itemStack.get(DataComponentTypes.CHARGED_PROJECTILES);
		
		// 如果ChargedProjectilesComponent组件不为空且不为空的话
		if (chargedProjectilesComponent != null && !chargedProjectilesComponent.isEmpty() && getChargedProjectileNum(itemStack) != 0) {
			// 发射所有已充能的弹药
			shootAll(world, user, hand, itemStack, getMaxProjectileSpeed(chargedProjectilesComponent), getFiringError(), null);
			setIsShoot(itemStack, true);
			// 设置装填数量
			setChargedProjectileNum(itemStack, getChargedProjectileNum(itemStack) - 1);
			// 返回表示消耗了物品堆的TypedActionResult对象
			return TypedActionResult.consume(itemStack);
		}
		
		// 如果玩家手中的物品堆不为空的话
		if (!user.getProjectileType(itemStack).isEmpty()) {
			// 但未装填
			if (getChargedProjectileNum(itemStack) == 0 || (chargedProjectilesComponent != null && chargedProjectilesComponent.getProjectiles().isEmpty())) {
				charged = false;
				loaded = false;
				// 设置玩家当前的手为当前手
				user.setCurrentHand(hand);
			}
			return TypedActionResult.consume(itemStack);
		}
		
		return TypedActionResult.fail(itemStack);
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
		double pullProgress = getUsingProgress(getMaxUseTime(stack) - remainingUseTicks, stack) * canLoadPulletNum;
		// 播放上弹音效
		if (pullProgress % 1 == 0.5 && pullProgress <= canLoadPulletNum)
			world.playSound(null, user.getX(), user.getY(), user.getZ(), JUGER_REPEATING_CROSSBOW_LOADING_START, SoundCategory.PLAYERS, 1.0F, 1);
		// 设置装填数量
		if (!world.isClient && !isCharged(stack) && pullProgress % 1 == 0 && pullProgress != 0 && getChargedProjectileNum(stack) < canLoadPulletNum)
			setChargedProjectileNum(stack, getChargedProjectileNum(stack) + 1);
		super.usageTick(world, user, stack, remainingUseTicks);
	}
	
	@Override
	protected boolean loadAllProjectile(LivingEntity shooter, ItemStack crossbow) {
		List<ItemStack> list = CrossbowItem.load(crossbow, shooter.getProjectileType(crossbow), shooter);
		if (list.isEmpty()) return false;
		crossbow.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.of(Lists.newArrayList(Iterables.concat(list,
				crossbow.getOrDefault(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT).getProjectiles()))));
		return true;
	}
	
	/**
	 * 发射所有
	 */
	@Override
	protected void shootAll(World world, LivingEntity shooter, Hand hand, ItemStack stack, List<ItemStack> projectiles, float speed, float divergence, boolean critical, @Nullable LivingEntity target) {
		// 基础角度增量
		float baseAngleIncrement = 20F / Math.max(1, projectiles.size() - 1);
		// 起始角度
		float startAngle = (float) ((projectiles.size() - 1) % 2) * baseAngleIncrement / 2;
		for (int i = 0; i < (EnchantmentHelper.getLevel(Enchantments.MULTISHOT, stack) == 0 ? 1 : 3) && i < projectiles.size(); ++i) {
			ItemStack projectileStack = projectiles.get(i);
			if (projectileStack.isEmpty()) continue;
			// 对武器进行损伤
			stack.damage(getWeaponStackDamage(projectileStack), shooter, LivingEntity.getSlotForHand(hand));
			// 创建投射物实体
			ProjectileEntity projectileEntity = createArrowEntity(world, shooter, stack, projectileStack, critical);
			// 射出投射物
			shoot(shooter, projectileEntity, i, speed, divergence, startAngle, target);
			// 在世界中生成投射物实体
			world.spawnEntity(projectileEntity);
		}
	}
	
	/**
	 * 发射后
	 */
	@Override
	public void shootAll(World world, LivingEntity shooter, Hand hand, ItemStack stack, float speed, float divergence, @Nullable LivingEntity livingEntity) {
		if (world.isClient()) return;
		ChargedProjectilesComponent chargedProjectilesComponent = stack.getOrDefault(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);
		if (chargedProjectilesComponent == null || chargedProjectilesComponent.isEmpty()) return;
		shootAll(world, shooter, hand, stack, chargedProjectilesComponent.getProjectiles(), speed, divergence, shooter instanceof PlayerEntity, livingEntity);
		if (shooter instanceof ServerPlayerEntity serverPlayerEntity) {
			Criteria.SHOT_CROSSBOW.trigger(serverPlayerEntity, stack);
			serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
		}
		List<ItemStack> itemStacks = chargedProjectilesComponent.getProjectiles();
		for (int i = 0; i < (EnchantmentHelper.getLevel(Enchantments.MULTISHOT, stack) == 0 ? 1 : 3) && !itemStacks.isEmpty(); i++) itemStacks.remove(0);
		stack.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.of(itemStacks));
	}
	
	/**
	 * 追加工具提示
	 */
	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
		if (chargedProjectilesComponent == null || chargedProjectilesComponent.isEmpty()) return;
		// 获取弹药
		List<ItemStack> projectiles = chargedProjectilesComponent.getProjectiles();
		// 如果已装填且弹药不为空
		if (isCharged(stack) && !projectiles.isEmpty()) {
			ItemStack bullet = projectiles.get(0);
			int num = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, stack) == 0 ? 1 : 3;
			// 添加工具提示
			tooltip.add(Text.translatable("item.minecraft.crossbow.projectile")
					.append(ScreenTexts.SPACE).append(bullet.toHoverableText())
					// 显示弹药数量
					.append(" x ").append(String.valueOf(getChargedProjectileNum(stack) * num)));
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
	
	@Override
	protected SoundEvent getQuickChargeSound(int stage) {
		return null;
	}
	
	@Override
	protected SoundEvent getLoadingSound() {
		return null;
	}
	
	@Override
	protected SoundEvent getLoadingEndSound() {
		return JUGER_REPEATING_CROSSBOW_LOADING_END;
	}
	
	@Override
	protected SoundEvent getShootSound() {
		return JUGER_REPEATING_CROSSBOW_SHOOT;
	}
	
	/**
	 * 设置弹丸 ID 的 NBT 以供 JSON 渲染使用
	 */
	@Override
	public void setProjectileId(ItemStack stack, ItemStack useProjectile) {
		if (useProjectile != null) {
			if (useProjectile.isOf(Items.ARROW)) stack.set(PROJECTILE_ID, new ProjectileIdComponent(0));
			else if (useProjectile.isOf(Items.TIPPED_ARROW)) stack.set(PROJECTILE_ID, new ProjectileIdComponent(0.1F));
			else if (useProjectile.isOf(Items.SPECTRAL_ARROW)) stack.set(PROJECTILE_ID, new ProjectileIdComponent(0.2F));
			else if (useProjectile.isOf(Items.FIREWORK_ROCKET)) stack.set(PROJECTILE_ID, new ProjectileIdComponent(0.3F));
		}
	}
	
	/**
	 * 获取 NBT 弹丸 ID 以供 JSON 渲染使用
	 */
	@Override
	public float getProjectileId(ItemStack stack) {
		return stack.getOrDefault(PROJECTILE_ID, new ProjectileIdComponent(0)).projectileId();
	}
	
	/**
	 * 获取所有弹丸的 ID 与他们所对应的名称以供模型生成
	 */
	@Override
	public Map<Float, String> getAllProjectile() {
		return Map.of(0.0F, "arrow", 0.3F, "firework");
	}
	
	/**
	 * <p>诸葛连弩可用附魔：</p>
	 * <p>多重射击、快速装填、耐久、消失诅咒、经验修补</p>
	 */
	@Override
	public List<Enchantment> getEnchantments() {
		return Arrays.asList(Enchantments.MULTISHOT,
				Enchantments.QUICK_CHARGE,
				Enchantments.UNBREAKING,
				Enchantments.VANISHING_CURSE,
				Enchantments.MENDING);
	}
	
	public record ChargedProjectileNumComponent(@Final int num) {
		public static final Codec<ChargedProjectileNumComponent> CODEC = Codec.INT.xmap(ChargedProjectileNumComponent::new, ChargedProjectileNumComponent::num);
	}
	
	public record IsShootComponent(@Final boolean isShoot) {
		public static final Codec<IsShootComponent> CODEC = Codec.BOOL.xmap(IsShootComponent::new, IsShootComponent::isShoot);
	}
}
