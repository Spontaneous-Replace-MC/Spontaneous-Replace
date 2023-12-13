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

package pers.saikel0rado1iu.sr.mixin.general.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pers.saikel0rado1iu.sr.variant.general.block.eerie.EerieRind;
import pers.saikel0rado1iu.sr.variant.general.block.eerie.LeveledEerieRind;

import static pers.saikel0rado1iu.sr.data.Blocks.*;
import static pers.saikel0rado1iu.sr.data.Tags.Block.EERIE_RINDS;

/**
 * <h2 style="color:FFC800">阴森木壳混入</font></b></p>
 * <p style="color:FFC800">使下方放置炼药锅的功能正常可用</p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
interface EerieRindMixin {
	@Mixin(AbstractBlock.class)
	abstract class Cauldron {
		@Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"))
		private void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
			if (!state.isOf(Blocks.CAULDRON)) return;
			if (direction != Direction.UP || !neighborState.isIn(EERIE_RINDS)) return;
			if (neighborState.isOf(EERIE_RIND)) return;
			else if (neighborState.isOf(WATER_EERIE_RIND))
				world.setBlockState(pos, Blocks.WATER_CAULDRON.getDefaultState().with(LeveledEerieRind.LEVEL, neighborState.get(LeveledEerieRind.LEVEL)), 3);
			else if (neighborState.isOf(POWDER_SNOW_EERIE_RIND))
				world.setBlockState(pos, Blocks.POWDER_SNOW_CAULDRON.getDefaultState().with(LeveledEerieRind.LEVEL, neighborState.get(LeveledEerieRind.LEVEL)), 3);
			else if (neighborState.isOf(LAVA_EERIE_RIND))
				world.setBlockState(pos, Blocks.LAVA_CAULDRON.getDefaultState(), 3);
			world.setBlockState(neighborPos, EERIE_RIND.getDefaultState(), 3);
		}
	}
	
	@Mixin(AbstractBlock.class)
	abstract class WaterCauldron {
		@Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"))
		private void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
			if (!state.isOf(Blocks.WATER_CAULDRON)) return;
			if (direction == Direction.UP) {
				if (neighborState.isOf(LAVA_EERIE_RIND)) {
					world.setBlockState(pos, Blocks.LAVA_CAULDRON.getDefaultState(), 3);
					world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
					world.setBlockState(neighborPos, EERIE_RIND.getDefaultState(), 3);
				} else if (neighborState.isOf(WATER_EERIE_RIND) || neighborState.isOf(POWDER_SNOW_EERIE_RIND)) {
					int downLevel = Math.min(3, state.get(LeveledEerieRind.LEVEL) + neighborState.get(LeveledEerieRind.LEVEL));
					int upLevel = state.get(LeveledEerieRind.LEVEL) + neighborState.get(LeveledEerieRind.LEVEL) - EerieRind.MAX_LEVEL;
					world.setBlockState(pos, state.with(LeveledEerieRind.LEVEL, downLevel), 3);
					if (upLevel > 0)
						world.setBlockState(neighborPos, WATER_EERIE_RIND.getDefaultState().with(LeveledEerieRind.LEVEL, upLevel), 3);
					else world.setBlockState(neighborPos, EERIE_RIND.getDefaultState(), 3);
				}
			}
		}
	}
	
	@Mixin(AbstractBlock.class)
	abstract class PowderSnowCauldron {
		@Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"))
		private void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
			if (!state.isOf(Blocks.POWDER_SNOW_CAULDRON)) return;
			if (direction == Direction.UP) {
				if (neighborState.isOf(LAVA_EERIE_RIND)) {
					world.setBlockState(pos, Blocks.LAVA_CAULDRON.getDefaultState(), 3);
					world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
					world.setBlockState(neighborPos, EERIE_RIND.getDefaultState(), 3);
				} else if (neighborState.isOf(WATER_EERIE_RIND) || neighborState.isOf(POWDER_SNOW_EERIE_RIND)) {
					int upLevel = Math.min(3, state.get(LeveledEerieRind.LEVEL) + neighborState.get(LeveledEerieRind.LEVEL));
					int posLevel = state.get(LeveledEerieRind.LEVEL) + neighborState.get(LeveledEerieRind.LEVEL) - EerieRind.MAX_LEVEL;
					world.setBlockState(pos, state.with(LeveledEerieRind.LEVEL, upLevel), 3);
					if (posLevel > 0)
						world.setBlockState(neighborPos, POWDER_SNOW_EERIE_RIND.getDefaultState().with(LeveledEerieRind.LEVEL, posLevel), 3);
					else world.setBlockState(neighborPos, EERIE_RIND.getDefaultState(), 3);
				}
			}
		}
	}
	
	@Mixin(AbstractBlock.class)
	abstract class LavaCauldron {
		@Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"))
		private void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
			if (!state.isOf(Blocks.LAVA_CAULDRON)) return;
			if (direction == Direction.UP && (neighborState.isOf(WATER_EERIE_RIND) || neighborState.isOf(POWDER_SNOW_EERIE_RIND))) {
				world.setBlockState(neighborPos, EERIE_RIND.getDefaultState(), 3);
				world.playSound(null, neighborPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
			}
		}
	}
}
