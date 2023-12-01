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

package pers.saikel0rado1iu.sr.gen.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctions;
import net.minecraft.world.gen.noise.NoiseRouter;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import pers.saikel0rado1iu.silk.gen.world.SilkChunkGeneratorSetting;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;
import pers.saikel0rado1iu.sr.gen.world.biome.source.ClassicBiomeParameters;

/**
 * <p><b style="color:FFC800"><font size="+1">自然更替的区块生成器设置</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public final class ChunkGeneratorSettings extends SilkChunkGeneratorSetting {
	public static final RegistryKey<net.minecraft.world.gen.chunk.ChunkGeneratorSettings> CLASSIC = register(SpontaneousReplace.DATA, "classic");
	public static final RegistryKey<net.minecraft.world.gen.chunk.ChunkGeneratorSettings> SNAPSHOT = register(SpontaneousReplace.DATA, "snapshot");
	
	public static net.minecraft.world.gen.chunk.ChunkGeneratorSettings createSurfaceSettings(Registerable<?> registerable, boolean amplified, boolean largeBiomes) {
		return new net.minecraft.world.gen.chunk.ChunkGeneratorSettings(SilkGenerationShapeConfig.SURFACE, Blocks.STONE.getDefaultState(), Blocks.WATER.getDefaultState(),
				SrDensityFunctions.createSurfaceNoiseRouter(registerable.getRegistryLookup(RegistryKeys.DENSITY_FUNCTION), registerable.getRegistryLookup(RegistryKeys.NOISE_PARAMETERS), largeBiomes, amplified),
				VanillaSurfaceRules.createOverworldSurfaceRule(), (new ClassicBiomeParameters()).getSpawnSuitabilityNoises(), 63, false, true, true, false);
	}
	
	@Override
	public void bootstrap(Registerable<net.minecraft.world.gen.chunk.ChunkGeneratorSettings> registerable) {
		registerable.register(CLASSIC, ChunkGeneratorSettings.createSurfaceSettings(registerable, false, false));
		registerable.register(SNAPSHOT, ChunkGeneratorSettings.createSurfaceSettings(registerable, false, false));
	}
	
	public static class SrDensityFunctions extends DensityFunctions {
		public static NoiseRouter createSurfaceNoiseRouter(RegistryEntryLookup<DensityFunction> densityFunctionLookup, RegistryEntryLookup<DoublePerlinNoiseSampler.NoiseParameters> noiseParametersLookup, boolean largeBiomes, boolean amplified) {
			return DensityFunctions.createSurfaceNoiseRouter(densityFunctionLookup, noiseParametersLookup, largeBiomes, amplified);
		}
	}
}
