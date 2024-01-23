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

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import pers.saikel0rado1iu.silk.api.item.CustomEnchantment;
import pers.saikel0rado1iu.silk.api.item.tool.weapon.ranged.Bow;
import pers.saikel0rado1iu.silk.api.registry.gen.data.criterion.RangedKilledEntityCriterion;
import pers.saikel0rado1iu.silk.api.registry.gen.data.criterion.SilkCriteria;
import pers.saikel0rado1iu.silk.util.TickUtil;
import pers.saikel0rado1iu.sr.vanilla.ranged.projectile.stoneball.StoneballEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static pers.saikel0rado1iu.sr.data.Items.STONEBALL;
import static pers.saikel0rado1iu.sr.data.SoundEvents.SLINGSHOT_THROW;

/**
 * <h2 style="color:FFC800">丫弹弓</h2>
 * <p style="color:FFC800">添加一种娱乐性质的远程武器</p>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class Slingshot extends Bow implements CustomEnchantment {
	public Slingshot(Settings settings) {
		super(settings);
	}
	
	@NotNull
	private static ThrownItemEntity getThrownItemEntity(World world, LivingEntity user, ItemStack usePellet) {
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
		return pelletEntity;
	}
	
	@Override
	public float getUsingMovementMultiple() {
		return 1;
	}
	
	@Override
	public float getMaxProjectileSpeed() {
		return 2;
	}
	
	@Override
	public int getMaxPullTicks() {
		return TickUtil.getTick(0.5F);
	}
	
	@Override
	public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
		// 检查用户使用者是否为玩家
		if (!(user instanceof PlayerEntity player)) return;
		// 检查是否在创造模式或者拥有“无限”附魔
		boolean inCreateOrInfinity = player.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
		// 获取弹丸
		ItemStack useProjectile = user.getProjectileType(stack);
		// 检查玩家是否有弹丸，如果没有弹丸但在创造模式或者拥有“无限”附魔则使用默认弹丸
		if (!useProjectile.isEmpty() || inCreateOrInfinity) {
			if (useProjectile.isEmpty()) useProjectile = new ItemStack(STONEBALL);
		}
		if (!useProjectile.isEmpty() || inCreateOrInfinity) {
			// 获取弹丸已使用游戏刻
			int usedTicks = this.getMaxUseTime(stack) - remainingUseTicks;
			// 获取弹弓拉弓进度
			float pullProgress = getPullProgress(usedTicks);
			// 如果拉弓进度不小于 0.1
			if (pullProgress < 0.1) return;
			// 如果在创造模式或者拥有“无限”附魔以及弹丸是默认弹丸
			boolean inCreateOrInfinityAndDefaultBullet = inCreateOrInfinity && useProjectile.isOf(STONEBALL);
			// 在服务器端运行
			if (!world.isClient) {
				ThrownItemEntity pelletEntity = getThrownItemEntity(world, user, useProjectile);
				RangedKilledEntityCriterion.putRangedNbt(pelletEntity, stack);
				// 设置弹丸速度
				pelletEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, pullProgress * getMaxProjectileSpeed(), 1.0F);
				
				// 设置“力量”效果
				int powerLevel = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
				if (powerLevel > 0) {
					float addSpeed = (float) (powerLevel * 0.2 + 1);
					pelletEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, pullProgress * getMaxProjectileSpeed() * addSpeed, 1.0F);
				}
				
				// 设置“冲击”效果
				int punchLevel = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
				if (punchLevel > 0 && pelletEntity instanceof StoneballEntity) {
					((StoneballEntity) pelletEntity).setPunchLevel(punchLevel, pelletEntity.getVelocity());
				}
				
				// 设置工具损伤
				stack.damage(1, player, LivingEntity.getSlotForHand(player.getActiveHand()));
				
				// 生成弹丸实体
				world.spawnEntity(pelletEntity);
				if (player instanceof ServerPlayerEntity serverPlayer)
					SilkCriteria.SHOT_PROJECTILE_CRITERION.trigger(serverPlayer, stack, pelletEntity, 1);
			}
			
			// 播放音效
			world.playSound(null, player.getX(), player.getY(), player.getZ(), SLINGSHOT_THROW, SoundCategory.PLAYERS, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
			if (!inCreateOrInfinityAndDefaultBullet && !player.getAbilities().creativeMode) {
				useProjectile.decrement(1);
				if (useProjectile.isEmpty()) {
					player.getInventory().removeOne(useProjectile);
				}
			}
			
			player.incrementStat(Stats.USED.getOrCreateStat(this));
		}
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		// 获取使用物品
		ItemStack itemStack = user.getStackInHand(hand);
		// 检查是否在创造模式或者拥有“无限”附魔
		boolean inCreateOrInfinity = user.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, itemStack) > 0;
		// 获取弹丸
		ItemStack userProjectile = user.getProjectileType(itemStack);
		// 检查玩家是否有弹丸，如果没有弹丸但在创造模式或者拥有“无限”附魔则使用默认弹丸
		if (!userProjectile.isEmpty() || inCreateOrInfinity) {
			if (userProjectile.isEmpty()) userProjectile = new ItemStack(STONEBALL);
		}
		setProjectileId(itemStack, userProjectile);
		
		return super.use(world, user, hand);
	}
	
	@Override
	public Predicate<ItemStack> getProjectiles() {
		return (stack) -> stack.isOf(STONEBALL)
				|| stack.isOf(Items.EGG)
				|| stack.isOf(Items.SNOWBALL)
				|| stack.isOf(Items.ENDER_PEARL)
				|| stack.isOf(Items.LINGERING_POTION)
				|| stack.isOf(Items.SPLASH_POTION);
	}
	
	/**
	 * 设置弹丸 ID 的 NBT 以供 JSON 渲染使用
	 */
	@Override
	public void setProjectileId(ItemStack stack, ItemStack useProjectile) {
		NbtCompound nbtCompound = stack.getOrCreateNbt();
		nbtCompound.putFloat(PROJECTILE_ID_KEY, 0);
		if (useProjectile != null) {
			if (useProjectile.isOf(STONEBALL)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0);
			else if (useProjectile.isOf(Items.EGG)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0.1F);
			else if (useProjectile.isOf(Items.SNOWBALL)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0.2F);
			else if (useProjectile.isOf(Items.ENDER_PEARL)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0.3F);
			else if (useProjectile.isOf(Items.SPLASH_POTION)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0.4F);
			else if (useProjectile.isOf(Items.LINGERING_POTION)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0.5F);
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
		return Map.of(0.0F, "stoneball");
	}
	
	/**
	 * <p>丫弹弓可用附魔：</p>
	 * <p>无限、力量(提升 100%+等级*20% 的弹丸速度)、击退(只对石弹有效)、耐久、消失诅咒、经验修补</p>
	 */
	@Override
	public List<Enchantment> getEnchantments() {
		return Arrays.asList(Enchantments.INFINITY,
				Enchantments.POWER,
				Enchantments.PUNCH,
				Enchantments.UNBREAKING,
				Enchantments.VANISHING_CURSE,
				Enchantments.MENDING);
	}
}
