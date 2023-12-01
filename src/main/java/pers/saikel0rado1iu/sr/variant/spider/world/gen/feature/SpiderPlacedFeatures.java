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

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import pers.saikel0rado1iu.silk.gen.world.SilkPlacedFeature;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;

import static net.minecraft.world.gen.feature.PlacedFeatures.wouldSurvive;

/**
 * <p><b style="color:FFC800"><font size="+1">蜘蛛群系的已放置的地物</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public final class SpiderPlacedFeatures extends SilkPlacedFeature {
	public static final RegistryKey<PlacedFeature> COBWEB = register(SpontaneousReplace.DATA, "cobweb");
	public static final RegistryKey<PlacedFeature> STICKY_COMPACT_COBWEB = register(SpontaneousReplace.DATA, "sticky_compact_cobweb");
	public static final RegistryKey<PlacedFeature> SPIDER_CHRYSALIS = register(SpontaneousReplace.DATA, "spider_chrysalis");
	public static final RegistryKey<PlacedFeature> SPIDER_EGG_COCOON = register(SpontaneousReplace.DATA, "spider_egg_cocoon");
	public static final RegistryKey<PlacedFeature> COBWEBBY_DARK_OAK = register(SpontaneousReplace.DATA, "cobwebby_dark_oak");
	public static final RegistryKey<PlacedFeature> CREEPY_SPIDER_FOREST_VEGETATION = register(SpontaneousReplace.DATA, "creepy_spider_forest_vegetation");
	
	@Override
	public void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
		register(featureRegisterable, COBWEB, featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(SpiderConfiguredFeatures.COBWEB),
				BiomePlacementModifier.of(), CountPlacementModifier.of(3), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR));
		register(featureRegisterable, STICKY_COMPACT_COBWEB, featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(SpiderConfiguredFeatures.STICKY_COMPACT_COBWEB),
				BiomePlacementModifier.of(), CountPlacementModifier.of(3), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(1), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR));
		register(featureRegisterable, SPIDER_CHRYSALIS, featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(SpiderConfiguredFeatures.SPIDER_CHRYSALIS),
				BiomePlacementModifier.of(), CountPlacementModifier.of(1), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR));
		register(featureRegisterable, SPIDER_EGG_COCOON, featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(SpiderConfiguredFeatures.SPIDER_EGG_COCOON),
				BiomePlacementModifier.of(), CountPlacementModifier.of(1), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR));
		register(featureRegisterable, COBWEBBY_DARK_OAK, featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(SpiderConfiguredFeatures.COBWEBBY_DARK_OAK), wouldSurvive(Blocks.DARK_OAK_SAPLING));
		register(featureRegisterable, CREEPY_SPIDER_FOREST_VEGETATION, featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(SpiderConfiguredFeatures.CREEPY_SPIDER_FOREST_VEGETATION),
				CountPlacementModifier.of(8), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
	}
}
