/*
 * MIT License
 *
 * Copyright (c) 2023 GameGeek-Saikel
 *
 * Permission stream hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software stream
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

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.biome.source.MultiNoiseBiomeSource;
import net.minecraft.world.biome.source.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.WorldPreset;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import pers.saikel0rado1iu.silk.gen.world.SilkWorldPreset;
import pers.saikel0rado1iu.silk.gen.world.chunk.CustomChunkGenerator;
import pers.saikel0rado1iu.silk.gen.world.chunk.SilkNoiseChunkGenerator;
import pers.saikel0rado1iu.silk.gen.world.chunk.UpgradeChunkGenerator;
import pers.saikel0rado1iu.silk.util.world.upgrade.data.ChunkStorageData;
import pers.saikel0rado1iu.sr.gen.world.BiomeKeys;
import pers.saikel0rado1iu.sr.gen.world.MultiNoiseBiomeSourceParameterLists;
import pers.saikel0rado1iu.sr.variant.spider.world.gen.biome.SpiderBiomeKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static pers.saikel0rado1iu.sr.gen.world.ChunkGeneratorSettings.CLASSIC;

/**
 * <h2 style="color:FFC800">自然更替经典的特殊区块生成器</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class ClassicChunkGenerator extends SilkNoiseChunkGenerator implements CustomChunkGenerator, UpgradeChunkGenerator {
	public static final String VERSION = "1";
	public static final Codec<ClassicChunkGenerator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
					BiomeSource.CODEC.fieldOf("biome_source").forGetter(generator -> generator.biomeSource),
					Codecs.createStrictOptionalFieldCodec(FixedBiomeSource.CODEC.listOf(), "fixed_biome_sources", List.of()).forGetter(generator -> generator.variantBiomeSources),
					ChunkGeneratorSettings.REGISTRY_CODEC.fieldOf("settings").forGetter(NoiseChunkGenerator::getSettings),
					Codecs.createStrictOptionalFieldCodec(Codec.STRING, VERSION_FIELD, NON_VERSION).forGetter(ClassicChunkGenerator::getVersion))
			.apply(instance, instance.stable(ClassicChunkGenerator::new)));
	public final List<FixedBiomeSource> variantBiomeSources;
	private final String version;
	
	public ClassicChunkGenerator(BiomeSource biomeSource, List<FixedBiomeSource> variantBiomeSources, RegistryEntry<ChunkGeneratorSettings> settings, String version) {
		super(biomeSource, settings);
		this.version = version;
		this.variantBiomeSources = variantBiomeSources;
	}
	
	public ClassicChunkGenerator(BiomeSource biomeSource, List<FixedBiomeSource> variantBiomeSources, RegistryEntry<ChunkGeneratorSettings> settings) {
		this(biomeSource, variantBiomeSources, settings, VERSION);
	}
	
	public static ClassicChunkGenerator getInstance(DynamicRegistryManager.Immutable registryManager) {
		RegistryEntry<MultiNoiseBiomeSourceParameterList> parameters = registryManager.get(RegistryKeys.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST)
				.getEntry(MultiNoiseBiomeSourceParameterLists.CLASSIC).orElseThrow();
		RegistryEntry<Biome> biome = registryManager.get(RegistryKeys.BIOME).getEntry(SpiderBiomeKeys.CREEPY_SPIDER_FOREST).orElseThrow();
		RegistryEntry<ChunkGeneratorSettings> settings = registryManager.get(RegistryKeys.CHUNK_GENERATOR_SETTINGS).getEntry(CLASSIC).orElseThrow();
		return new ClassicChunkGenerator(MultiNoiseBiomeSource.create(parameters), List.of(new FixedBiomeSource(biome)), settings);
	}
	
	public static ClassicChunkGenerator getInstance(Registerable<WorldPreset> registerable, SilkWorldPreset.Registrar registrar) {
		RegistryEntryLookup<Biome> biomeRegistrar = registerable.getRegistryLookup(RegistryKeys.BIOME);
		RegistryEntry<MultiNoiseBiomeSourceParameterList> parameters = registrar.multiNoisePresetLookup
				.getOrThrow(MultiNoiseBiomeSourceParameterLists.CLASSIC);
		RegistryEntry<Biome> biome = biomeRegistrar.getOrThrow(SpiderBiomeKeys.CREEPY_SPIDER_FOREST);
		RegistryEntry<ChunkGeneratorSettings> settings = registrar.chunkGeneratorSettingsLookup.getOrThrow(CLASSIC);
		return new SnapshotChunkGenerator(MultiNoiseBiomeSource.create(parameters), List.of(new FixedBiomeSource(biome)), settings);
	}
	
	public static void register(RegistryKey<WorldPreset> worldPreset, Registerable<WorldPreset> registerable) {
		SilkWorldPreset.Registrar registrar = new SilkWorldPreset.Registrar(registerable);
		new DimensionOptions(registrar.overworldDimensionType, getInstance(registerable, registrar));
		registrar.register(worldPreset, new DimensionOptions(registrar.overworldDimensionType,
				new NoiseChunkGenerator(MultiNoiseBiomeSource.create(registrar.multiNoisePresetLookup.getOrThrow(MultiNoiseBiomeSourceParameterLists.CLASSIC)),
						registrar.chunkGeneratorSettingsLookup.getOrThrow(pers.saikel0rado1iu.sr.gen.world.ChunkGeneratorSettings.CLASSIC))));
	}
	
	/**
	 * 用于提供生成器版本以供判断，需要在 {@link Codec} 中添加此参数，这样才能正确保存版本号
	 */
	@Override
	public String getVersion() {
		return version;
	}
	
	/**
	 * 用于判断生成器版本号的方法
	 *
	 * @param savedVersion 已保存在存档中的版本号
	 * @return -1 为小于；0 为等于；1 为大于
	 */
	@Override
	public int compareVersion(String savedVersion) {
		if (!StringUtils.isNumeric(savedVersion)) return -1;
		return Integer.compare(Integer.parseInt(savedVersion), Integer.parseInt(VERSION));
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
	
	/**
	 * 通过块坐标解析 {@link ChunkStorageData} 的数据进行判断是否可刷新坐标中的区块
	 *
	 * @param pos     需判断块坐标
	 * @param chunk   区块数据
	 * @param version 区块当前的生成器版本
	 * @return 是否可刷新区块
	 */
	@Override
	public boolean canFlushChunk(BlockPos pos, ChunkStorageData chunk, String version) {
		return false;
	}
	
	/**
	 * 通过块坐标解析 {@link ChunkStorageData} 的数据进行判断是否可升级坐标中的区块
	 *
	 * @param pos     需判断块坐标
	 * @param chunk   区块数据
	 * @param version 区块当前的生成器版本
	 * @return 是否可升级区块
	 */
	@Override
	public boolean canUpgradeChunk(BlockPos pos, ChunkStorageData chunk, String version) {
		return false;
	}
	
	/**
	 * 通过块坐标解析 {@link ChunkStorageData} 的数据进行判断是否可降级坐标中的区块
	 *
	 * @param pos     需判断块坐标
	 * @param chunk   区块数据
	 * @param version 区块当前的生成器版本
	 * @return 是否可降级区块
	 */
	@Override
	public boolean canDowngradeChunk(BlockPos pos, ChunkStorageData chunk, String version) {
		return false;
	}
	
	public FixedBiomeSource getVariantBiomeSource(RegistryKey<Biome> biome) {
		FixedBiomeSource fixedBiomeSource = null;
		for (FixedBiomeSource biomeSource : variantBiomeSources) {
			fixedBiomeSource = biomeSource;
			for (RegistryEntry<Biome> entry : biomeSource.getBiomes()) {
				if (biome.equals(entry.getKey().orElseThrow())) return biomeSource;
			}
		}
		return fixedBiomeSource;
	}
	
	@Override
	public @Nullable Pair<BlockPos, RegistryEntry<Biome>> locateBiome(BlockPos origin, int radius, int horizontalBlockCheckInterval, int verticalBlockCheckInterval, Predicate<RegistryEntry<Biome>> predicate, MultiNoiseUtil.MultiNoiseSampler noiseSampler, ServerWorld world) {
		Supplier<Boolean> notVariantBiomes = () -> {
			for (FixedBiomeSource source : variantBiomeSources) for (RegistryEntry<Biome> entry : source.getBiomes()) if (predicate.test(entry)) return false;
			return true;
		};
		if (notVariantBiomes.get())
			return CustomChunkGenerator.super.locateBiome(origin, radius, horizontalBlockCheckInterval, verticalBlockCheckInterval, predicate, noiseSampler, world);
		List<Integer> xs = new ArrayList<>();
		List<Integer> zs = new ArrayList<>();
		for (int radian = 0; radian < 360; radian++) {
			for (int count = 0; count < radius; count += horizontalBlockCheckInterval) {
				xs.add((int) (origin.getX() + count * Math.cos(radian)));
				zs.add((int) (origin.getZ() + count * Math.sin(radian)));
			}
		}
		Function<BlockPos, Pair<BlockPos, RegistryEntry<Biome>>> getPair = pos -> {
			if (getBiomeSource(pos) != biomeSource && !getBiomeSource(pos).getBiomes().stream().filter(predicate).collect(Collectors.toUnmodifiableSet()).isEmpty()) {
				for (int y = world.getHeight(); y > world.getDimension().minY(); y--) {
					if (!world.getBlockState(new BlockPos(pos.getX(), y, pos.getZ())).isAir())
						return Pair.of(new BlockPos(pos.getX(), y + 1, pos.getZ()), getBiomeSource(pos).getBiome(pos.getX(), y + 1, pos.getZ(), noiseSampler));
				}
			}
			return null;
		};
		Pair<BlockPos, RegistryEntry<Biome>> pair;
		for (int x : xs) for (int z : zs) if ((pair = getPair.apply(new BlockPos(x, 0, z))) != null) return pair;
		return null;
	}
	
	/**
	 * 通过在世界中的块坐标判断生物群系，用于设置以特殊方法而非原版方法生成生物群系
	 *
	 * @param pos 块坐标
	 * @return 在坐标上应生成的生物群系源
	 */
	@Override
	public BiomeSource getBiomeSource(BlockPos pos) {
		return BiomeKeys.getBiome(pos, biomeSource, this::getVariantBiomeSource);
	}
	
	/**
	 * 修改地形噪声的一种方法，用于设置以特殊方法而非原版方法生成地形噪声
	 *
	 * @param pos                   块坐标
	 * @param originBlock           原始块
	 * @param estimateSurfaceHeight 预期表面高度
	 * @return 应设置的噪声块
	 */
	@Override
	public BlockState setTerrainNoise(BlockPos pos, BlockState originBlock, int estimateSurfaceHeight) {
		RegistryKey<Biome> biomeKey = null;
		BiomeSource source = getBiomeSource(pos);
		if (source != biomeSource) for (RegistryEntry<Biome> entry : source.getBiomes()) if (entry.getKey().isPresent()) biomeKey = entry.getKey().get();
		return BiomeKeys.setTerrainNoise(pos, originBlock, estimateSurfaceHeight, getSettings().value(), biomeKey);
	}
}
