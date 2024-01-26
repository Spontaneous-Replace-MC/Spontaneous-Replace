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

package pers.saikel0rado1iu.sr.gen.world.chunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.biome.source.MultiNoiseBiomeSource;
import net.minecraft.world.biome.source.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.WorldPreset;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import pers.saikel0rado1iu.silk.gen.world.SilkWorldPreset;
import pers.saikel0rado1iu.sr.gen.world.MultiNoiseBiomeSourceParameterLists;
import pers.saikel0rado1iu.sr.variant.spider.world.gen.biome.SpiderBiomeKeys;

import java.util.List;

import static pers.saikel0rado1iu.sr.gen.world.ChunkGeneratorSettings.SNAPSHOT;

/**
 * <h2 style="color:FFC800">自然更替快照的特殊区块生成器</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class SnapshotChunkGenerator extends ClassicChunkGenerator {
	public static final String VERSION = "1";
	public static final Codec<SnapshotChunkGenerator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
					BiomeSource.CODEC.fieldOf("biome_source").forGetter(generator -> generator.biomeSource),
					Codecs.createStrictOptionalFieldCodec(FixedBiomeSource.CODEC.listOf(), "fixed_biome_sources", List.of()).forGetter(generator -> generator.variantBiomeSources),
					ChunkGeneratorSettings.REGISTRY_CODEC.fieldOf("settings").forGetter(NoiseChunkGenerator::getSettings),
					Codecs.createStrictOptionalFieldCodec(Codec.STRING, VERSION_FIELD, NON_VERSION).forGetter(SnapshotChunkGenerator::getVersion))
			.apply(instance, instance.stable(SnapshotChunkGenerator::new)));
	
	public SnapshotChunkGenerator(BiomeSource biomeSource, List<FixedBiomeSource> variantBiomeSources, RegistryEntry<ChunkGeneratorSettings> settings, String version) {
		super(biomeSource, variantBiomeSources, settings, version);
	}
	
	public SnapshotChunkGenerator(BiomeSource biomeSource, List<FixedBiomeSource> variantBiomeSources, RegistryEntry<ChunkGeneratorSettings> settings) {
		this(biomeSource, variantBiomeSources, settings, VERSION);
	}
	
	public static SnapshotChunkGenerator getInstance(DynamicRegistryManager.Immutable registryManager) {
		RegistryEntry<MultiNoiseBiomeSourceParameterList> parameters = registryManager.get(RegistryKeys.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST)
				.getEntry(MultiNoiseBiomeSourceParameterLists.SNAPSHOT).orElseThrow();
		RegistryEntry<Biome> biome = registryManager.get(RegistryKeys.BIOME).getEntry(SpiderBiomeKeys.CREEPY_SPIDER_FOREST).orElseThrow();
		RegistryEntry<ChunkGeneratorSettings> settings = registryManager.get(RegistryKeys.CHUNK_GENERATOR_SETTINGS).getEntry(SNAPSHOT).orElseThrow();
		return new SnapshotChunkGenerator(MultiNoiseBiomeSource.create(parameters), List.of(new FixedBiomeSource(biome)), settings);
	}
	
	public static SnapshotChunkGenerator getInstance(Registerable<WorldPreset> registerable, SilkWorldPreset.Registrar registrar) {
		RegistryEntryLookup<Biome> biomeRegistrar = registerable.getRegistryLookup(RegistryKeys.BIOME);
		RegistryEntry<MultiNoiseBiomeSourceParameterList> parameters = registrar.multiNoisePresetLookup
				.getOrThrow(MultiNoiseBiomeSourceParameterLists.SNAPSHOT);
		RegistryEntry<Biome> biome = biomeRegistrar.getOrThrow(SpiderBiomeKeys.CREEPY_SPIDER_FOREST);
		RegistryEntry<ChunkGeneratorSettings> settings = registrar.chunkGeneratorSettingsLookup.getOrThrow(SNAPSHOT);
		return new SnapshotChunkGenerator(MultiNoiseBiomeSource.create(parameters), List.of(new FixedBiomeSource(biome)), settings);
	}
	
	public static void register(RegistryKey<WorldPreset> worldPreset, Registerable<WorldPreset> registerable) {
		SilkWorldPreset.Registrar registrar = new SilkWorldPreset.Registrar(registerable);
		registrar.register(worldPreset, new DimensionOptions(registrar.overworldDimensionType, getInstance(registerable, registrar)));
	}
	
	@Override
	protected Codec<? extends ChunkGenerator> getCodec() {
		return CODEC;
	}
	
	/**
	 * 用于提供区块生成器的 {@link Codec}
	 */
	@Override
	public Codec<? extends ChunkGenerator> codec() {
		return getCodec();
	}
}
