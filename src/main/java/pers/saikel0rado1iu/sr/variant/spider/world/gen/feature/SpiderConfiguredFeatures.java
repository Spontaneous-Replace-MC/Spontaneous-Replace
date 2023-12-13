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

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import pers.saikel0rado1iu.silk.gen.world.SilkConfiguredFeature;
import pers.saikel0rado1iu.sr.data.Blocks;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;
import pers.saikel0rado1iu.sr.data.word.Features;
import pers.saikel0rado1iu.sr.variant.general.world.gen.feature.VariantPlacedFeatures;
import pers.saikel0rado1iu.sr.variant.spider.world.gen.foliage.CobwebbyOakFoliagePlacer;

import java.util.List;
import java.util.OptionalInt;

/**
 * <h2 style="color:FFC800">蜘蛛群系的已配置的地物</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class SpiderConfiguredFeatures extends SilkConfiguredFeature {
	public static final RegistryKey<ConfiguredFeature<?, ?>> COBWEB = register(SpontaneousReplace.DATA, "cobweb");
	public static final RegistryKey<ConfiguredFeature<?, ?>> STICKY_COMPACT_COBWEB = register(SpontaneousReplace.DATA, "sticky_compact_cobweb");
	public static final RegistryKey<ConfiguredFeature<?, ?>> SPIDER_CHRYSALIS = register(SpontaneousReplace.DATA, "spider_chrysalis");
	public static final RegistryKey<ConfiguredFeature<?, ?>> SPIDER_EGG_COCOON = register(SpontaneousReplace.DATA, "spider_egg_cocoon");
	public static final RegistryKey<ConfiguredFeature<?, ?>> COBWEBBY_DARK_OAK = register(SpontaneousReplace.DATA, "cobwebby_dark_oak");
	public static final RegistryKey<ConfiguredFeature<?, ?>> CREEPY_SPIDER_FOREST_VEGETATION = register(SpontaneousReplace.DATA, "creepy_spider_forest_vegetation");
	
	@Override
	public void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
		register(featureRegisterable, COBWEB, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(net.minecraft.block.Blocks.COBWEB)));
		register(featureRegisterable, STICKY_COMPACT_COBWEB, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.STICKY_COMPACT_COBWEB)));
		register(featureRegisterable, SPIDER_CHRYSALIS, Features.SIMPLE_COBWEBBY_BLOCK, new SimpleCobwebbyBlockFeature.Config(BlockStateProvider.of(Blocks.SPIDER_CHRYSALIS)));
		register(featureRegisterable, SPIDER_EGG_COCOON, Features.SIMPLE_COBWEBBY_BLOCK, new SimpleCobwebbyBlockFeature.Config(BlockStateProvider.of(Blocks.SPIDER_EGG_COCOON)));
		register(featureRegisterable, COBWEBBY_DARK_OAK, Feature.TREE, new TreeFeatureConfig.Builder(
				BlockStateProvider.of(net.minecraft.block.Blocks.DARK_OAK_LOG), new DarkOakTrunkPlacer(6, 2, 1),
				BlockStateProvider.of(net.minecraft.block.Blocks.DARK_OAK_LEAVES), new CobwebbyOakFoliagePlacer(ConstantIntProvider.create(0),
				ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))
				.decorators(List.of(new TrunkVineTreeDecorator(), new LeavesVineTreeDecorator(0.25F)))
				.build());
		register(featureRegisterable, CREEPY_SPIDER_FOREST_VEGETATION, Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(
				new RandomFeatureEntry(featureRegisterable.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(VariantPlacedFeatures.EERIE_TREE), 0.1F),
				new RandomFeatureEntry(featureRegisterable.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(VariantPlacedFeatures.TREACHEROUS_TREE), 0.1F),
				new RandomFeatureEntry(featureRegisterable.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(SpiderPlacedFeatures.COBWEBBY_DARK_OAK), 0.8F)),
				featureRegisterable.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(SpiderPlacedFeatures.COBWEBBY_DARK_OAK)));
	}
}
