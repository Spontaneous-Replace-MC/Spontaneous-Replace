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

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import pers.saikel0rado1iu.silk.gen.world.SilkMultiNoiseBiomeSourceParameterList;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;
import pers.saikel0rado1iu.sr.gen.world.biome.source.ClassicBiomeParameters;
import pers.saikel0rado1iu.sr.gen.world.biome.source.SnapshotBiomeParameters;

import java.util.function.Function;

/**
 * <h2 style="color:FFC800">自然更替的多重噪声生物群系源参数列表</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class MultiNoiseBiomeSourceParameterLists extends SilkMultiNoiseBiomeSourceParameterList {
	public static final RegistryKey<MultiNoiseBiomeSourceParameterList> CLASSIC = register(SpontaneousReplace.DATA, "classic");
	public static final RegistryKey<MultiNoiseBiomeSourceParameterList> SNAPSHOT = register(SpontaneousReplace.DATA, "snapshot");
	
	@Override
	public void bootstrap(Registerable<MultiNoiseBiomeSourceParameterList> registerable) {
		RegistryEntryLookup<Biome> registryEntryLookup = registerable.getRegistryLookup(RegistryKeys.BIOME);
		registerable.register(CLASSIC, new MultiNoiseBiomeSourceParameterList(Preset.CLASSIC, registryEntryLookup));
		registerable.register(SNAPSHOT, new MultiNoiseBiomeSourceParameterList(Preset.SNAPSHOT, registryEntryLookup));
	}
	
	public static final class Preset {
		public static final MultiNoiseBiomeSourceParameterList.Preset CLASSIC = new MultiNoiseBiomeSourceParameterList.Preset(new Identifier(SpontaneousReplace.DATA.getId(), "classic"), new SilkBiomeSourceFunction() {
			@Override
			public <T> MultiNoiseUtil.Entries<T> apply(Function<RegistryKey<Biome>, T> biomeEntryGetter) {
				ImmutableList.Builder<Pair<MultiNoiseUtil.NoiseHypercube, T>> builder = ImmutableList.builder();
				new ClassicBiomeParameters().writeOverworldBiomeParameters(pair -> builder.add(pair.mapSecond(biomeEntryGetter)));
				return new MultiNoiseUtil.Entries<>(builder.build());
			}
		});
		public static final MultiNoiseBiomeSourceParameterList.Preset SNAPSHOT = new MultiNoiseBiomeSourceParameterList.Preset(new Identifier(SpontaneousReplace.DATA.getId(), "snapshot"), new SilkBiomeSourceFunction() {
			@Override
			public <T> MultiNoiseUtil.Entries<T> apply(Function<RegistryKey<Biome>, T> biomeEntryGetter) {
				ImmutableList.Builder<Pair<MultiNoiseUtil.NoiseHypercube, T>> builder = ImmutableList.builder();
				new SnapshotBiomeParameters().writeOverworldBiomeParameters(pair -> builder.add(pair.mapSecond(biomeEntryGetter)));
				return new MultiNoiseUtil.Entries<>(builder.build());
			}
		});
	}
}
