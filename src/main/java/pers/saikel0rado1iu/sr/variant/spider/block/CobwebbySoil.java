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

package pers.saikel0rado1iu.sr.variant.spider.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.world.WorldView;
import pers.saikel0rado1iu.silk.api.block.SpreadableSoilBlock;
import pers.saikel0rado1iu.sr.data.Blocks;

/**
 * <h2 style="color:FFC800">丝化土</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class CobwebbySoil extends SpreadableSoilBlock {
	public static final MapCodec<CobwebbySoil> CODEC = createCodec(CobwebbySoil::new);
	
	public CobwebbySoil(Settings settings) {
		super(settings);
	}
	
	@Override
	protected MapCodec<? extends SpreadableBlock> getCodec() {
		return CODEC;
	}
	
	@Override
	public Position getSpreadableRange() {
		return new Position() {
			@Override
			public double getX() {
				return 3;
			}
			
			@Override
			public double getY() {
				return 3;
			}
			
			@Override
			public double getZ() {
				return 3;
			}
		};
	}
	
	@Override
	public boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
		return world.getBlockState(pos.up()).isAir() && (world.getBlockState(pos).isIn(BlockTags.DIRT) || world.getBlockState(pos).isIn(BlockTags.LEAVES))
				&& canSurvive(state, world, pos) && !world.getFluidState(pos.up()).isIn(FluidTags.WATER);
	}
	
	@Override
	protected BlockPos getSpreadableOffset(BlockPos original) {
		return original.up();
	}
	
	@Override
	public BlockState getSpreadableBlockState(ServerWorld world, BlockPos pos) {
		return Blocks.GOSSAMER_CARPET.getDefaultState();
	}
	
	@Override
	protected BlockState getDegeneratedBlockState() {
		return getDefaultState();
	}
}
