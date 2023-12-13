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

package pers.saikel0rado1iu.sr.variant.spider.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import pers.saikel0rado1iu.sr.data.Blocks;
import pers.saikel0rado1iu.sr.data.Tags;
import pers.saikel0rado1iu.sr.variant.spider.block.SpiderChrysalis;
import pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderData;

import java.util.function.Supplier;

/**
 * <h2 style="color:FFC800">简单丝化块地物类型</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class SimpleCobwebbyBlockFeature extends Feature<SimpleCobwebbyBlockFeature.Config> {
	public SimpleCobwebbyBlockFeature(Codec<Config> configCodec) {
		super(configCodec);
	}
	
	@Override
	public boolean generate(FeatureContext<Config> context) {
		StructureWorldAccess world = context.getWorld();
		BlockPos origin = context.getOrigin();
		Random random = context.getRandom();
		Config config = context.getConfig();
		BlockPos downPos = origin.down();
		
		if (world.getBlockState(downPos).isIn(Tags.Block.COBWEBBY_BLOCKS)) return false;
		BlockState state = config.block.get(random, origin);
		if (state.getBlock() instanceof SpiderChrysalis) {
			Supplier<Direction> directionSupplier = () -> switch (random.nextInt() % 4) {
				case 1 -> Direction.EAST;
				case 2 -> Direction.SOUTH;
				case 3 -> Direction.WEST;
				default -> Direction.NORTH;
			};
			state = Blocks.SPIDER_CHRYSALIS.getDefaultState().with(SpiderData.CHRYSALIS_STYLE, SpiderChrysalis.getRandomStyle()).with(Properties.HORIZONTAL_FACING, directionSupplier.get());
			if (SpiderChrysalis.isDoubleBlock(state.get(SpiderData.CHRYSALIS_STYLE))) {
				BlockPos blockPos = origin.up();
				Direction blockState = Direction.UP;
				if (state.get(Properties.VERTICAL_DIRECTION) == Direction.DOWN) {
					blockPos = origin.down();
					blockState = Direction.DOWN;
				}
				if (state.get(SpiderData.CHRYSALIS_STYLE) == SpiderData.ChrysalisStyle.DEFAULT)
					world.setBlockState(blockPos, state.with(SpiderData.CHRYSALIS_STYLE, SpiderData.ChrysalisStyle.PLACEHOLDER_SHORT).with(Properties.VERTICAL_DIRECTION, blockState), Block.NOTIFY_ALL);
				else
					world.setBlockState(blockPos, state.with(SpiderData.CHRYSALIS_STYLE, SpiderData.ChrysalisStyle.PLACEHOLDER).with(Properties.VERTICAL_DIRECTION, blockState), Block.NOTIFY_ALL);
			}
		}
		world.setBlockState(origin, state, Block.NOTIFY_ALL);
		if (world.getBlockState(downPos).isIn(BlockTags.LEAVES))
			world.setBlockState(downPos, Blocks.GOSSAMERY_LEAVES.getDefaultState(), Block.NOTIFY_ALL);
		else world.setBlockState(downPos, Blocks.COBWEBBY_SOIL.getDefaultState(), Block.NOTIFY_ALL);
		Position position = Blocks.COBWEBBY_SOIL.getSpreadableRange();
		for (int x = 0; x < position.getX(); x++) {
			for (int y = 0; y < position.getY(); y++) {
				for (int z = 0; z < position.getZ(); z++) {
					BlockPos pos = downPos.add(x - 1, y - 1, z - 1);
					if (Blocks.COBWEBBY_SOIL.canSpread(Blocks.COBWEBBY_SOIL.getDefaultState(), world, pos))
						world.setBlockState(pos.up(), Blocks.COBWEBBY_SOIL.getSpreadableBlockState(null, downPos), Block.NOTIFY_ALL);
				}
			}
		}
		
		return true;
	}
	
	public record Config(BlockStateProvider block) implements FeatureConfig {
		public static final Codec<Config> CODEC = RecordCodecBuilder.create(instance ->
				instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("block").forGetter(Config::block)).apply(instance, Config::new));
	}
}
