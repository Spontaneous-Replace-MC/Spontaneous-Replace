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

package pers.saikel0rado1iu.sr.variant.general.world.gen.feature;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import pers.saikel0rado1iu.silk.gen.world.SilkConfiguredFeature;
import pers.saikel0rado1iu.sr.data.Blocks;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;
import pers.saikel0rado1iu.sr.variant.general.world.gen.foliage.EerieFoliagePlacer;
import pers.saikel0rado1iu.sr.variant.general.world.gen.foliage.TreacherousFoliagePlacer;
import pers.saikel0rado1iu.sr.variant.general.world.gen.treedecorator.EerieTreeDecorator;
import pers.saikel0rado1iu.sr.variant.general.world.gen.trunk.TreacherousTrunkPlacer;

import java.util.List;

/**
 * <p><b style="color:FFC800"><font size="+1">自然更替变种的已配置的地物</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public class VariantConfiguredFeatures extends SilkConfiguredFeature {
	public static final RegistryKey<ConfiguredFeature<?, ?>> EERIE_TREE = register(SpontaneousReplace.DATA, "eerie_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> TREACHEROUS_TREE = register(SpontaneousReplace.DATA, "treacherous_tree");
	
	@Override
	public void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
		register(featureRegisterable, EERIE_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
				BlockStateProvider.of(Blocks.EERIE_RIND), new StraightTrunkPlacer(5, 1, 0),
				BlockStateProvider.of(Blocks.EERIE_BOUGH), new EerieFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), 7),
				new TwoLayersFeatureSize(1, 0, 1))
				.decorators(List.of(EerieTreeDecorator.INSTANCE))
				.dirtProvider(BlockStateProvider.of(Blocks.EERIE_REGOLITH))
				.ignoreVines().build());
		register(featureRegisterable, TREACHEROUS_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
				BlockStateProvider.of(Blocks.TREACHEROUS_SAC), new TreacherousTrunkPlacer(5, 2, 0),
				BlockStateProvider.of(Blocks.TREACHEROUS_VINES_PLANT), new TreacherousFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.dirtProvider(BlockStateProvider.of(Blocks.TREACHEROUS_SLUDGE))
				.ignoreVines().build());
	}
}
