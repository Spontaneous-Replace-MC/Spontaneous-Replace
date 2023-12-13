/*
 * MIT License
 *
 * Copyright (c) 202Block.NOTIFY_ALL GameGeek-Saikel
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

package pers.saikel0rado1iu.sr.variant.general.block.eerie;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import static pers.saikel0rado1iu.silk.api.registry.SilkEntityType.POS_SHIFTING;
import static pers.saikel0rado1iu.sr.data.Blocks.*;
import static pers.saikel0rado1iu.sr.data.Tags.Block.EERIE_RINDS;

/**
 * <h2 style="color:FFC800">阴森木壳</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/885Block.NOTIFY_ALL11Block.NOTIFY_ALL8?s=64&v=4"><p>
 */
public class EerieRind extends AbstractCauldronBlock implements EerieRindShapeFix {
	public static final MapCodec<EerieRind> CODEC = createCodec(EerieRind::new);
	public static final int MAX_LEVEL = Block.NOTIFY_ALL;
	private static final float FILL_WITH_RAIN_CHANCE = 0.05F;
	private static final float FILL_WITH_SNOW_CHANCE = 0.1F;
	
	public EerieRind(AbstractBlock.Settings settings) {
		super(settings, EerieRindBehavior.EMPTY_BEHAVIOR);
	}
	
	/**
	 * 可以充满降水
	 */
	protected static boolean canFillWithPrecipitation(World world, Biome.Precipitation precipitation) {
		if (precipitation == Biome.Precipitation.RAIN) {
			return world.getRandom().nextFloat() < FILL_WITH_RAIN_CHANCE;
		} else if (precipitation == Biome.Precipitation.SNOW) {
			return world.getRandom().nextFloat() < FILL_WITH_SNOW_CHANCE;
		} else {
			return false;
		}
	}
	
	/**
	 * 阴森木极寒，在阴森木壳中的实体会冻伤
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this)) {
			entity.slowMovement(state, new Vec3d(0.9, 1.5, 0.9));
		}
		
		entity.setInPowderSnow(true);
		entity.setFrozenTicks(entity.getFrozenTicks() + 2);
		if (!world.isClient) {
			if (entity.isOnFire() && (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) || entity instanceof PlayerEntity) && entity.canModifyAt(world, pos)) {
				world.breakBlock(pos, false);
			}
			entity.setOnFire(false);
			if (entity.isFrozen()) entity.damage(entity.getDamageSources().freeze(), 1);
		}
	}
	
	/**
	 * 会随机变成冻成冰
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.randomTick(state, world, pos, random);
		for (Direction direction : DIRECTIONS) {
			if (world.getBlockState(pos.offset(direction)).isOf(Blocks.WATER) && random.nextBoolean())
				world.setBlockState(pos.offset(direction), Blocks.ICE.getDefaultState());
		}
	}
	
	/**
	 * 有随机刻
	 */
	@Override
	public boolean hasRandomTicks(BlockState state) {
		return true;
	}
	
	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		super.onPlaced(world, pos, state, placer, itemStack);
		BlockPos upPos = pos.up();
		BlockState upState = world.getBlockState(upPos);
		if (upState.isOf(Blocks.LAVA)) {
			world.setBlockState(pos, LAVA_EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			world.setBlockState(upPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
		} else if (upState.isOf(Blocks.WATER)) {
			world.setBlockState(pos, WATER_EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			world.setBlockState(upPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
		} else if (upState.isOf(Blocks.POWDER_SNOW)) {
			world.setBlockState(pos, POWDER_SNOW_EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			world.setBlockState(upPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState
			neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction != Direction.UP)
			return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
		BlockPos downPos = pos.down();
		BlockState downBlock = world.getBlockState(downPos);
		if ((neighborState.isIn(EERIE_RINDS) || neighborState.isOf(Blocks.LAVA) || neighborState.isOf(Blocks.WATER) || neighborState.isOf(Blocks.POWDER_SNOW))
				&& !neighborState.isOf(EERIE_RIND)) {
			if (neighborState.isOf(Blocks.LAVA)) {
				world.setBlockState(pos, LAVA_EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
				world.setBlockState(neighborPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
			} else if (neighborState.isOf(Blocks.WATER)) {
				world.setBlockState(pos, WATER_EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
				world.setBlockState(neighborPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
			} else if (neighborState.isOf(Blocks.POWDER_SNOW)) {
				world.setBlockState(pos, POWDER_SNOW_EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
				world.setBlockState(neighborPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
			} else {
				world.setBlockState(pos, neighborState, Block.NOTIFY_ALL);
				world.setBlockState(neighborPos, state, Block.NOTIFY_ALL);
			}
		}
		if (downBlock.isOf(Blocks.LAVA)) {
			if (neighborState.isOf(WATER_EERIE_RIND) || neighborState.isOf(POWDER_SNOW_EERIE_RIND)) {
				world.setBlockState(downPos, Blocks.OBSIDIAN.getDefaultState(), Block.NOTIFY_ALL);
				world.playSound(null, downPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
				world.setBlockState(pos, getDefaultState(), Block.NOTIFY_ALL);
			}
		} else if (downBlock.isOf(Blocks.WATER)) {
			if (neighborState.isOf(LAVA_EERIE_RIND)) {
				world.setBlockState(downPos, Blocks.COBBLESTONE.getDefaultState(), Block.NOTIFY_ALL);
				world.playSound(null, downPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
				world.setBlockState(pos, getDefaultState(), Block.NOTIFY_ALL);
			} else if (neighborState.isOf(POWDER_SNOW_EERIE_RIND)) {
				world.setBlockState(pos, WATER_EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			}
		} else if (downBlock.isOf(Blocks.POWDER_SNOW)) {
			if (neighborState.isOf(LAVA_EERIE_RIND)) {
				world.setBlockState(downPos, Blocks.LAVA.getDefaultState(), Block.NOTIFY_ALL);
				world.playSound(null, downPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
				world.setBlockState(pos, getDefaultState(), Block.NOTIFY_ALL);
			} else if (neighborState.isOf(WATER_EERIE_RIND)) {
				world.setBlockState(pos, POWDER_SNOW_EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			}
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	/**
	 * 随机灵魂粒子效果
	 */
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (random.nextInt(Block.NOTIFY_ALL) == 0) {
			java.util.Random randomValue = new java.util.Random();
			world.addParticle(ParticleTypes.SCULK_SOUL,
					pos.getX() + POS_SHIFTING + randomValue.nextDouble(-1, 1),
					pos.getY() + POS_SHIFTING + randomValue.nextDouble(-1, 1),
					pos.getZ() + POS_SHIFTING + randomValue.nextDouble(-1, 1),
					randomValue.nextDouble(-0.07, 0.07),
					randomValue.nextDouble(0, 0.1),
					randomValue.nextDouble(-0.07, 0.07));
		}
	}
	
	/**
	 * 是否满了
	 */
	@Override
	public boolean isFull(BlockState state) {
		return false;
	}
	
	/**
	 * <p>降水游戏刻</p>
	 * <p>如果底下有完整方块则可以操作</p>
	 */
	@Override
	public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
		if (!world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP))
			return;
		if (canFillWithPrecipitation(world, precipitation)) {
			if (precipitation == Biome.Precipitation.RAIN) {
				world.setBlockState(pos, WATER_EERIE_RIND.getDefaultState());
				world.emitGameEvent(null, GameEvent.BLOCK_CHANGE, pos);
			} else if (precipitation == Biome.Precipitation.SNOW) {
				world.setBlockState(pos, POWDER_SNOW_EERIE_RIND.getDefaultState());
				world.emitGameEvent(null, GameEvent.BLOCK_CHANGE, pos);
			}
		}
	}
	
	/**
	 * 可以通过滴水石锥填充
	 */
	@Override
	protected boolean canBeFilledByDripstone(Fluid fluid) {
		return true;
	}
	
	/**
	 * 如果底下有完整方块则可以通过滴水石锥填充
	 */
	@Override
	protected void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid) {
		if (!world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP))
			return;
		BlockState blockState;
		if (fluid == Fluids.WATER) {
			blockState = WATER_EERIE_RIND.getDefaultState();
			world.setBlockState(pos, blockState);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
			world.syncWorldEvent(1047, pos, 0);
		} else if (fluid == Fluids.LAVA) {
			blockState = LAVA_EERIE_RIND.getDefaultState();
			world.setBlockState(pos, blockState);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
			world.syncWorldEvent(1046, pos, 0);
		}
	}
	
	@Override
	public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
		return getEerieRindRaycastShape();
	}
	
	@Override
	protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
		return CODEC;
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return getEerieRindOutlineShape();
	}
}
