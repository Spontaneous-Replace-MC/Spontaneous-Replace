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
import net.minecraft.block.AbstractBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import pers.saikel0rado1iu.sr.data.word.FoliagePlacerTypes;

import java.util.HashSet;
import java.util.Set;

/**
 * <h2 style="color:FFC800">诡谲木树叶放置器</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class TreacherousFoliagePlacer extends FoliagePlacer {
	public static final Codec<TreacherousFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
			fillFoliagePlacerFields(instance).apply(instance, TreacherousFoliagePlacer::new));
	
	public TreacherousFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}
	
	@Override
	protected FoliagePlacerType<?> getType() {
		return FoliagePlacerTypes.TREACHEROUS_FOLIAGE_PLACER;
	}
	
	/**
	 * 这是用于生成树叶的主要方法。
	 */
	@Override
	protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
		BlockPos center = treeNode.getCenter().down(2);
		Set<BlockPos> placePosSet = new HashSet<>();
		placePosSet.add(center.north());
		placePosSet.add(center.north().east());
		placePosSet.add(center.south(2));
		placePosSet.add(center.south(2).east());
		placePosSet.add(center.east(2));
		placePosSet.add(center.east(2).south());
		placePosSet.add(center.west());
		placePosSet.add(center.west().south());
		for (BlockPos placePos : placePosSet) {
			BlockPos pos = placePos;
			while (world.testBlockState(pos, AbstractBlock.AbstractBlockState::isAir)) {
				placeFoliageBlock(world, placer, random, config, pos);
				pos = pos.down();
			}
		}
	}
	
	@Override
	public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
		return 0;
	}
	
	/**
	 * 用于在创建叶子正方形时排除某些位置，例如角。
	 */
	@Override
	protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
		return false;
	}
}
