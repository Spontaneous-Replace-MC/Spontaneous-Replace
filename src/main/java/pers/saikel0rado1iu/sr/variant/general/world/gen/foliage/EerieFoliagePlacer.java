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

package pers.saikel0rado1iu.sr.variant.general.world.gen.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import pers.saikel0rado1iu.sr.data.word.FoliagePlacerTypes;
import pers.saikel0rado1iu.sr.variant.general.block.eerie.EerieBough;

import static pers.saikel0rado1iu.sr.data.Blocks.EERIE_BOUGH;

/**
 * <h2 style="color:FFC800">阴森木枝树叶放置器</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class EerieFoliagePlacer extends BlobFoliagePlacer {
	public static final Codec<EerieFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
			fillFoliagePlacerFields(instance)
					.and(Codec.intRange(0, 16).fieldOf("height").forGetter(placer -> placer.height))
					.apply(instance, EerieFoliagePlacer::new));
	
	public EerieFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
		super(radius, offset, height);
	}
	
	@Override
	protected FoliagePlacerType<?> getType() {
		return FoliagePlacerTypes.EERIE_BOUGH_FOLIAGE_PLACER;
	}
	
	@Override
	protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
		BlockPos center = treeNode.getCenter().down(3);
		for (int count = 0; count < foliageHeight * radius * radius * 5; count++) {
			BlockPos pos = new BlockPos(
					center.getX() + random.nextBetween(-radius, radius),
					center.getY() + random.nextInt(foliageHeight),
					center.getZ() + random.nextBetween(-radius, radius));
			if (EERIE_BOUGH.canPlaceAt(((WorldView) world).getBlockState(pos), (WorldView) world, pos)) {
				BlockState blockState = config.foliageProvider.get(random, pos);
				if (blockState.contains(Properties.WATERLOGGED)) {
					blockState = blockState.with(Properties.WATERLOGGED, world.testFluidState(pos, fluidState -> fluidState.isEqualAndStill(Fluids.WATER)));
				}
				if (blockState.getBlock() instanceof EerieBough) {
					placer.placeBlock(pos, EERIE_BOUGH.withConnectionProperties((BlockView) world, pos));
					for (Direction direction : Direction.Type.HORIZONTAL) {
						BlockPos directionPos = pos.offset(direction);
						BlockState directionState = ((WorldView) world).getBlockState(directionPos);
						if (directionState.getBlock() instanceof EerieBough)
							placer.placeBlock(directionPos, EERIE_BOUGH.withConnectionProperties((BlockView) world, directionPos));
					}
					for (Direction direction : Direction.Type.VERTICAL) {
						BlockPos directionPos = pos.offset(direction);
						BlockState directionState = ((WorldView) world).getBlockState(directionPos);
						if (directionState.getBlock() instanceof EerieBough)
							placer.placeBlock(directionPos, EERIE_BOUGH.withConnectionProperties((BlockView) world, directionPos));
					}
				}
			}
		}
	}
}
