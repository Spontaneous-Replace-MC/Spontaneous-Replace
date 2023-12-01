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

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.source.MultiNoiseBiomeSource;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.WorldPreset;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import pers.saikel0rado1iu.silk.gen.world.SilkWorldPreset;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;

/**
 * <p><b style="color:FFC800"><font size="+1">自然更替的世界预设</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public final class WorldPresets extends SilkWorldPreset {
	public static final RegistryKey<WorldPreset> CLASSIC = register(SpontaneousReplace.DATA, "classic");
	public static final RegistryKey<WorldPreset> SNAPSHOT = register(SpontaneousReplace.DATA, "snapshot");
	
	@Override
	public void bootstrap(Registerable<WorldPreset> registerable) {
		Registrar registrar = new SilkWorldPreset.Registrar(registerable);
		registrar.register(CLASSIC, new DimensionOptions(registrar.overworldDimensionType,
				new NoiseChunkGenerator(MultiNoiseBiomeSource.create(registrar.multiNoisePresetLookup.getOrThrow(MultiNoiseBiomeSourceParameterLists.CLASSIC)),
						registrar.chunkGeneratorSettingsLookup.getOrThrow(ChunkGeneratorSettings.CLASSIC))));
		registrar.register(SNAPSHOT, new DimensionOptions(registrar.overworldDimensionType,
				new NoiseChunkGenerator(MultiNoiseBiomeSource.create(registrar.multiNoisePresetLookup.getOrThrow(MultiNoiseBiomeSourceParameterLists.SNAPSHOT)),
						registrar.chunkGeneratorSettingsLookup.getOrThrow(ChunkGeneratorSettings.SNAPSHOT))));
	}
}
