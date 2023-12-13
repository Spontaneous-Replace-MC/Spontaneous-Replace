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

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.PlacedFeature;
import pers.saikel0rado1iu.silk.gen.world.SilkPlacedFeature;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;

import static net.minecraft.world.gen.feature.PlacedFeatures.wouldSurvive;

/**
 * <h2 style="color:FFC800">自然更替变种的已放置的地物</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class VariantPlacedFeatures extends SilkPlacedFeature {
	public static final RegistryKey<PlacedFeature> EERIE_TREE = register(SpontaneousReplace.DATA, "eerie_tree");
	public static final RegistryKey<PlacedFeature> TREACHEROUS_TREE = register(SpontaneousReplace.DATA, "treacherous_tree");
	
	@Override
	public void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
		register(featureRegisterable, EERIE_TREE, featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(VariantConfiguredFeatures.EERIE_TREE), wouldSurvive(Blocks.DARK_OAK_SAPLING));
		register(featureRegisterable, TREACHEROUS_TREE, featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(VariantConfiguredFeatures.TREACHEROUS_TREE), wouldSurvive(Blocks.DARK_OAK_SAPLING));
	}
}
