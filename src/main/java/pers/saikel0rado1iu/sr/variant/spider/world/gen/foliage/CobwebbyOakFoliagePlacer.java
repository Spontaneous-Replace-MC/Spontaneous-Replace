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

package pers.saikel0rado1iu.sr.variant.spider.world.gen.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.DarkOakFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import pers.saikel0rado1iu.sr.data.Blocks;
import pers.saikel0rado1iu.sr.data.word.FoliagePlacerTypes;

/**
 * <h2 style="color:FFC800">丝化橡木树叶放置器</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class CobwebbyOakFoliagePlacer extends DarkOakFoliagePlacer {
	public static final Codec<CobwebbyOakFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
			fillFoliagePlacerFields(instance).apply(instance, CobwebbyOakFoliagePlacer::new));
	
	public CobwebbyOakFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}
	
	@Override
	protected FoliagePlacerType<?> getType() {
		return FoliagePlacerTypes.COBWEBBY_OAK_FOLIAGE_PLACER;
	}
	
	/**
	 * 这是用于生成树叶的主要方法。
	 */
	@Override
	protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
		super.generate(world, placer, random, config, trunkHeight, treeNode, foliageHeight, radius, offset);
	}
	
	@Override
	protected void generateSquare(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, BlockPos centerPos, int radius, int y, boolean giantTrunk) {
		int i = giantTrunk ? 1 : 0;
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		
		for (int j = -radius; j <= radius + i; ++j) {
			for (int k = -radius; k <= radius + i; ++k) {
				if (!this.isPositionInvalid(random, j, y, k, radius, giantTrunk)) {
					mutable.set(centerPos, j, y, k);
					if (random.nextInt() % 3 == 0) {
						placeFoliageBlock(world, placer, random,
								new TreeFeatureConfig.Builder(
										null, null,
										BlockStateProvider.of(Blocks.GOSSAMERY_LEAVES), new CobwebbyOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
										null)
										.build(),
								mutable);
					} else {
						placeFoliageBlock(world, placer, random, config, mutable);
					}
				}
			}
		}
		
	}
}
