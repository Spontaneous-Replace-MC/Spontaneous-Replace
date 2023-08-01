package pres.saikel_orado.spontaneous_replace.mod.mixin.variant.general.block;

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
import pres.saikel_orado.spontaneous_replace.mod.variant.general.block.eerierind.LeveledEerieRind;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.*;
import static pres.saikel_orado.spontaneous_replace.mod.variant.general.block.eerierind.EerieRind.MAX_LEVEL;

/**
 * <b style="color:FFC800"><font size="+2">EerieRind：阴森木壳混入</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">使下方放置炼药锅的功能正常可用</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/23 17:45
 */
abstract class EerieRind {
    @Mixin(AbstractBlock.class)
    abstract static class Cauldron {
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
    abstract static class WaterCauldron {
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
                    int upLevel = state.get(LeveledEerieRind.LEVEL) + neighborState.get(LeveledEerieRind.LEVEL) - MAX_LEVEL;
                    world.setBlockState(pos, state.with(LeveledEerieRind.LEVEL, downLevel), 3);
                    if (upLevel > 0)
                        world.setBlockState(neighborPos, WATER_EERIE_RIND.getDefaultState().with(LeveledEerieRind.LEVEL, upLevel), 3);
                    else world.setBlockState(neighborPos, EERIE_RIND.getDefaultState(), 3);
                }
            }
        }
    }

    @Mixin(AbstractBlock.class)
    abstract static class PowderSnowCauldron {
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
                    int posLevel = state.get(LeveledEerieRind.LEVEL) + neighborState.get(LeveledEerieRind.LEVEL) - MAX_LEVEL;
                    world.setBlockState(pos, state.with(LeveledEerieRind.LEVEL, upLevel), 3);
                    if (posLevel > 0)
                        world.setBlockState(neighborPos, POWDER_SNOW_EERIE_RIND.getDefaultState().with(LeveledEerieRind.LEVEL, posLevel), 3);
                    else world.setBlockState(neighborPos, EERIE_RIND.getDefaultState(), 3);
                }
            }
        }
    }

    @Mixin(AbstractBlock.class)
    abstract static class LavaCauldron {
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
