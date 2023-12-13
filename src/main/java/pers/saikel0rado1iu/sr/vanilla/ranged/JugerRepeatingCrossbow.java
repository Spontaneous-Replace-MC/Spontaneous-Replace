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

import com.google.common.collect.Lists;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
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
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import pers.saikel0rado1iu.silk.api.item.CustomEnchantment;
import pers.saikel0rado1iu.silk.api.item.tool.weapon.ranged.Crossbow;
import pers.saikel0rado1iu.silk.util.TickUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static pers.saikel0rado1iu.sr.data.SoundEvents.*;

/**
 * <h2 style="color:FFC800">诸葛连弩</font></b></p>
 * <p style="color:FFC800">添加一种强力的近战连发远程物品</p>
 * <style="color:FFC800">
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
	 * 清除弹药
	 */
	protected static void clearProjectiles(ItemStack crossbow) {
		// 清除写在 NBT 中的弹药数据
		NbtCompound nbtCompound = crossbow.getNbt();
		if (nbtCompound != null) {
			NbtList nbtList = nbtCompound.getList("ChargedProjectileNum", NbtElement.LIST_TYPE);
			nbtList.clear();
			nbtCompound.put("ChargedProjectileNum", nbtList);
		}
	}
	
	/**
	 * 获取已装填弹药数
	 *
	 * @return 设置后的已装填弹药数
	 */
	public static int getChargedProjectileNum(ItemStack stack) {
		NbtCompound nbtCompound = stack.getNbt();
		return nbtCompound != null ? nbtCompound.getInt("ChargedProjectileNum") : 0;
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
	 * 发射后
	 */
	@Override
	protected void postShootProjectile(World world, LivingEntity entity, ItemStack stack) {
		// 如果实体为玩家实体
		if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
			// 触发弩的射击
			if (!world.isClient) Criteria.SHOT_CROSSBOW.trigger(serverPlayerEntity, stack);
			serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
		}
		// 清除弹药
		if (getChargedProjectileNum(stack) == 1) clearProjectiles(stack);
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
		for (int count = 1; count <= getChargedProjectileNum(stack) && !isCharged(stack); count++) loadAllProjectile(user, stack);
		
		if (getChargedProjectileNum(stack) > 0 && !isCharged(stack)) {
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
		// 默认操作
		ItemStack itemStack = user.getStackInHand(hand);
		maxUseTicks = TickUtil.getTick(getCanLoadPulletNum(user, itemStack));
		setProjectileId(itemStack, user.getProjectileType(itemStack));
		// 如果已装填
		if (isCharged(itemStack)) {
			// 发射所有
			shootAllProjectile(world, user, hand, itemStack, getMaxProjectileSpeed(itemStack), getFiringError());
			setIsShoot(itemStack, true);
			// 设置装填数量
			setChargedProjectileNum(itemStack, getChargedProjectileNum(itemStack) - 1);
			// 设置未装填
			if (getChargedProjectileNum(itemStack) <= 0) setCharged(itemStack, false);
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
		double pullProgress = getUsingProgress(getMaxUseTime(stack) - remainingUseTicks, stack) * canLoadPulletNum;
		// 播放上弹音效
		if (pullProgress % 1 == 0.5 && pullProgress <= canLoadPulletNum)
			world.playSound(null, user.getX(), user.getY(), user.getZ(), JUGER_REPEATING_CROSSBOW_LOADING_START, SoundCategory.PLAYERS, 1.0F, 1);
		// 设置装填数量
		if (!world.isClient && !isCharged(stack) && pullProgress % 1 == 0 && pullProgress != 0 && getChargedProjectileNum(stack) < canLoadPulletNum)
			setChargedProjectileNum(stack, getChargedProjectileNum(stack) + 1);
		super.usageTick(world, user, stack, remainingUseTicks);
	}
	
	/**
	 * 发射所有
	 */
	@Override
	public void shootAllProjectile(World world, LivingEntity entity, Hand hand, ItemStack stack, float speed, float divergence) {
		// 获取弹药与单发弹药数量
		List<ItemStack> projectiles = getAllProjectile(stack);
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
				if (i == 0) shootProjectile(world, entity, hand, stack, itemStack, soundPitches[i], isPlayerAndInCreative, speed, divergence, 0);
				else shootProjectile(world, entity, hand, stack, itemStack, soundPitches[i], isPlayerAndInCreative, speed, divergence, 1);
			}
		}
		// 射击后操作
		postShootProjectile(world, entity, stack);
	}
	
	/**
	 * 设置已装填弹药数
	 *
	 * @param value 设置值
	 */
	public void setChargedProjectileNum(ItemStack stack, int value) {
		NbtCompound nbtCompound = stack.getOrCreateNbt();
		nbtCompound.putInt("ChargedProjectileNum", value);
	}
	
	/**
	 * 追加工具提示
	 */
	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		// 获取弹药
		List<ItemStack> projectiles = getAllProjectile(stack);
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
		NbtCompound nbtCompound = stack.getOrCreateNbt();
		nbtCompound.putFloat(PROJECTILE_ID_KEY, 0);
		if (useProjectile != null) {
			if (useProjectile.isOf(Items.ARROW)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0);
			else if (useProjectile.isOf(Items.TIPPED_ARROW)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0.1F);
			else if (useProjectile.isOf(Items.SPECTRAL_ARROW)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0.2F);
			else if (useProjectile.isOf(Items.FIREWORK_ROCKET)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0.3F);
		}
	}
	
	/**
	 * 获取 NBT 弹丸 ID 以供 JSON 渲染使用
	 */
	@Override
	public float getProjectileId(ItemStack stack) {
		NbtCompound nbtCompound = stack.getNbt();
		return nbtCompound != null ? nbtCompound.getFloat(PROJECTILE_ID_KEY) : 0;
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
}
