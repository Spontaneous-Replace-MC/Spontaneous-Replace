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
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import static pers.saikel0rado1iu.sr.data.Blocks.*;

/**
 * <p><b style="color:FFC800"><font size="+1">含岩浆阴森木壳</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/885Block.NOTIFY_ALL11Block.NOTIFY_ALL8?s=64&v=4"><p>
 */
public class LavaEerieRind extends AbstractCauldronBlock implements EerieRindShapeFix {
	public static final MapCodec<LavaEerieRind> CODEC = createCodec(LavaEerieRind::new);
	
	public LavaEerieRind(Settings settings) {
		super(settings, EerieRindBehavior.LAVA_BEHAVIOR);
	}
	
	/**
	 * 阴森木极寒，如在阴森木壳中含有岩浆，实体则不会冻伤也不会烧伤
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		super.onEntityCollision(state, world, pos, entity);
	}
	
	/**
	 * <p>如果上方方块变为含非岩浆壳则传递熄灭</p>
	 * <p>如果下方方块变为空气则传递岩浆到下方</p>
	 * <p>如果下方方块变为空壳则传递岩浆到下方</p>
	 */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.UP && (neighborState.isOf(WATER_EERIE_RIND) || neighborState.isOf(POWDER_SNOW_EERIE_RIND))) {
			world.setBlockState(neighborPos, EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			world.playSound(null, neighborPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
		}
		if (direction != Direction.DOWN) return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
		if (neighborState.isAir()) {
			world.setBlockState(pos, EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			world.setBlockState(neighborPos, Blocks.LAVA.getDefaultState(), Block.NOTIFY_ALL);
		}
		if (neighborState.isOf(EERIE_RIND)) {
			world.setBlockState(pos, EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			world.setBlockState(neighborPos, state, Block.NOTIFY_ALL);
		}
		if (neighborState.isOf(Blocks.CAULDRON)) {
			world.setBlockState(pos, EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			world.setBlockState(neighborPos, Blocks.LAVA_CAULDRON.getDefaultState(), Block.NOTIFY_ALL);
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	@Override
	protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
		return null;
	}
	
	@Override
	protected double getFluidHeight(BlockState state) {
		return 0.9375;
	}
	
	@Override
	public boolean isFull(BlockState state) {
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return Block.NOTIFY_ALL;
	}
	
	@Override
	public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
		return getEerieRindRaycastShape();
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return getEerieRindOutlineShape();
	}
}
