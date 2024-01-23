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

package pers.saikel0rado1iu.sr.variant.general.block.treacherous;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import pers.saikel0rado1iu.silk.util.ParticleUtil;
import pers.saikel0rado1iu.sr.data.StatusEffects;

import java.util.Random;
import java.util.function.ToIntFunction;

import static pers.saikel0rado1iu.silk.api.registry.SilkEntityType.POS_SHIFTING;
import static pers.saikel0rado1iu.sr.variant.general.VariantData.TREACHEROUS_PLANT_STABILITY;

/**
 * <h2 style="color:FFC800">诡谲囊</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class TreacherousSac extends Block {
	public static final ToIntFunction<BlockState> TREACHEROUS_SAC_LUMINANCE = (state) -> 5;
	
	public TreacherousSac(Settings settings) {
		super(settings);
	}
	
	/**
	 * 诡谲囊爆炸操作
	 */
	public static void sacUnstable(World world, BlockPos pos) {
		sacUnstable(world, pos, null);
	}
	
	/**
	 * 诡谲囊爆炸操作
	 */
	protected static void sacUnstable(World world, BlockPos pos, @Nullable LivingEntity detonator) {
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL_AND_REDRAW);
		TreacherousSacEntity treacherousSacEntity = new TreacherousSacEntity(world, detonator);
		treacherousSacEntity.refreshPositionAndAngles(pos, 0, 0);
		((ServerWorld) world).spawnEntityAndPassengers(treacherousSacEntity);
	}
	
	/**
	 * 随机酸气粒子效果
	 */
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (random.nextInt(3) == 0) {
			java.util.Random randomValue = new java.util.Random();
			ParticleUtil.addEffectParticle(world, StatusEffects.ACIDIZE.value(),
					pos.getX() + POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5),
					pos.getY() + POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5),
					pos.getZ() + POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5));
		}
	}
	
	/**
	 * 如果在爆炸中被摧毁则继续爆炸产生连锁反应
	 */
	@Override
	public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
		if (!world.isClient) sacUnstable(world, pos);
	}
	
	/**
	 * 如果在方块上跳跃则有概率会爆炸
	 */
	@Override
	public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		if (!world.isClient && world.random.nextFloat() < fallDistance - 0.5F
				&& entity instanceof LivingEntity
				&& entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F
				&& new Random().nextInt(TREACHEROUS_PLANT_STABILITY) == 0) {
			sacUnstable(world, pos, (LivingEntity) entity);
		}
		super.onLandedUpon(world, state, pos, entity, fallDistance);
	}
	
	/**
	 * 如果被斧右键使用则会爆炸
	 */
	@Override
	public ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (stack.getItem() instanceof AxeItem) {
			if (!world.isClient) {
				stack.damage(1, player, LivingEntity.getSlotForHand(hand));
				sacUnstable(world, pos, player);
			}
			
			player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
			return ItemActionResult.success(world.isClient);
		}
		return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
	}
	
	/**
	 * 如果被弹射物击中
	 */
	@Override
	public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
		BlockPos blockPos = hit.getBlockPos();
		Entity entity = projectile.getOwner();
		if (projectile instanceof PersistentProjectileEntity && projectile.canModifyAt(world, blockPos)) {
			if (!world.isClient) sacUnstable(world, blockPos, entity instanceof LivingEntity ? (LivingEntity) entity : null);
		}
	}
	
	/**
	 * 爆炸不掉落
	 */
	@Override
	public boolean shouldDropItemsOnExplosion(Explosion explosion) {
		return false;
	}
}
