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

package pers.saikel0rado1iu.sr.variant.spider.world.gen.biome;

import net.minecraft.block.BlockState;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.jetbrains.annotations.Nullable;
import pers.saikel0rado1iu.silk.gen.world.SilkBiomeKey;
import pers.saikel0rado1iu.silk.gen.world.chunk.CustomChunkGenerator;
import pers.saikel0rado1iu.silk.util.world.upgrade.data.ChunkStorageData;
import pers.saikel0rado1iu.sr.PerlinNoise;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;

import java.util.function.Function;

/**
 * <h2 style="color:FFC800">自然更替的蜘蛛群系</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class SpiderBiomeKeys extends SilkBiomeKey {
	public static final RegistryKey<Biome> CREEPY_SPIDER_FOREST = register(SpontaneousReplace.DATA, "creepy_spider_forest");
	
	@Override
	public void bootstrap(Registerable<Biome> registerable) {
		RegistryEntryLookup<PlacedFeature> featureLookup = registerable.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
		RegistryEntryLookup<ConfiguredCarver<?>> carverLookup = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);
		registerable.register(CREEPY_SPIDER_FOREST, CreepySpiderForestBiome.create(featureLookup, carverLookup));
	}
	
	public interface Classic {
		static BiomeSource getBiome(BlockPos pos, BiomeSource biomeSource, Function<RegistryKey<Biome>, FixedBiomeSource> getVariantBiomeSource) {
			return Data.canSetBiome(pos) ? getVariantBiomeSource.apply(CREEPY_SPIDER_FOREST) : biomeSource;
		}
		
		static BlockState setTerrainNoise(BlockPos pos, BlockState originBlock, int estimateSurfaceHeight, ChunkGeneratorSettings settings, @Nullable RegistryKey<Biome> biome) {
			final int biomeBaseHeight = settings.seaLevel() + 3;
			final long seed = CustomChunkGenerator.getSeed();
			if (!Data.isInBiome(pos.getX(), pos.getY(), pos.getZ(), seed)) return originBlock;
			if (estimateSurfaceHeight < biomeBaseHeight) {
				int noiseX = (int) Math.abs(Data.sizeRadius + (pos.getX() - Data.posX));
				int noiseZ = (int) Math.abs(Data.sizeRadius + (pos.getZ() - Data.posZ));
				final float ratio = Data.getBiomeRatio(pos.getX(), pos.getZ(), seed, Data.getNoise(seed)[noiseX][noiseZ]);
				if (pos.getY() < (ratio * biomeBaseHeight + (1 - ratio) * estimateSurfaceHeight)) return settings.defaultBlock();
			}
			return originBlock;
		}
		
		static boolean canFlushChunk(BlockPos pos, ChunkStorageData chunk, String version) {
			return false;
		}
		
		static boolean canUpgradeChunk(BlockPos pos, ChunkStorageData chunk, String version) {
			return false;
		}
		
		static boolean canDowngradeChunk(BlockPos pos, ChunkStorageData chunk, String version) {
			return false;
		}
		
		class Data {
			public static final int POS_BASE_RADIUS = 2000;
			public static final int POS_SHIFTING_RADIUS = 200;
			public static final int SIZE_BASE_RADIUS = 200;
			public static final int SIZE_SHIFTING_RADIUS = 30;
			public static final int NOISE_OCTAVES = (int) (Math.log((SIZE_BASE_RADIUS + SIZE_SHIFTING_RADIUS) * 2) / Math.log(2)) + 1;
			public static final int NOISE_SIZE = (int) Math.pow(2, NOISE_OCTAVES);
			public static final float NOISE_BIAS = 0.01f;
			public static final int CENTER_X = 0;
			public static final int CENTER_Z = 0;
			public static long seed = 0;
			private static float[][] noise = null;
			private static int posX = 0;
			private static int posZ = 0;
			private static long sizeRadius = 0;
			
			private static float[][] getNoise(long seed) {
				if (null == noise) noise = new PerlinNoise(seed, NOISE_SIZE, NOISE_OCTAVES - 1, NOISE_BIAS).perlinNoise();
				return noise;
			}
			
			private static float getBiomeRatio(int x, int z, long seed, float origin) {
				float ratio = 0;
				float distance = distanceFromBiomeCenterRatio(x, 0, z, seed);
				if (distance > 0.3) {
					float r = (1 - distance) / 0.7F;
					ratio = r * 0.3F + (1 - r) * 0.5F;
				}
				if (origin > ratio) return 1;
				return (origin / ratio) * 0.9F + (1 - (origin / ratio)) * 0.1F;
			}
			
			private static boolean canSetNoise(int x, int z, long seed) {
				int noiseX = (int) Math.abs(sizeRadius + (x - posX));
				int noiseZ = (int) Math.abs(sizeRadius + (z - posZ));
				float ratio = 0;
				float distance = distanceFromBiomeCenterRatio(x, 0, z, seed);
				if (distance > 0.3) {
					float r = (1 - distance) / 0.7F;
					ratio = r * 0.3F + (1 - r) * 0.5F;
				}
				return !(noiseX < 0 || noiseX >= NOISE_SIZE)
						&& !(noiseZ < 0 || noiseZ >= NOISE_SIZE)
						&& getNoise(seed)[noiseX][noiseZ] > ratio;
			}
			
			private static float distanceFromBiomeCenterRatio(int x, int y, int z, long seed) {
				if (0 == posX && 0 == posZ && 0 == sizeRadius) {
					long posRadius = seed > 0 ? POS_BASE_RADIUS + seed % POS_SHIFTING_RADIUS
							: POS_BASE_RADIUS - seed % POS_SHIFTING_RADIUS;
					double radian = Math.toRadians(seed % 360);
					posX = (int) (CENTER_X + posRadius * Math.cos(radian));
					posZ = (int) (CENTER_Z + posRadius * Math.sin(radian));
					sizeRadius = seed > 0 ? SIZE_BASE_RADIUS + seed % SIZE_SHIFTING_RADIUS
							: SIZE_BASE_RADIUS - seed % SIZE_SHIFTING_RADIUS;
				}
				return (float) ((Math.pow(x - posX, 2) + Math.pow(z - posZ, 2)) / Math.pow(sizeRadius, 2));
			}
			
			private static boolean isInBiome(int x, int y, int z, long seed) {
				return distanceFromBiomeCenterRatio(x, y, z, seed) <= 1;
			}
			
			private static boolean canSetBiome(BlockPos pos) {
				final int x = pos.getX();
				final int y = pos.getY();
				final int z = pos.getZ();
				final long seed = CustomChunkGenerator.getSeed();
				return isInBiome(x, y, z, seed) && canSetNoise(x, z, seed);
			}
		}
	}
	
	public interface Snapshot {
		static BiomeSource getBiome(BlockPos pos, BiomeSource biomeSource, Function<RegistryKey<Biome>, FixedBiomeSource> getVariantBiomeSource) {
			return Classic.getBiome(pos, biomeSource, getVariantBiomeSource);
		}
		
		static BlockState setTerrainNoise(BlockPos pos, BlockState originBlock, int estimateSurfaceHeight, ChunkGeneratorSettings settings, @Nullable RegistryKey<Biome> biome) {
			return Classic.setTerrainNoise(pos, originBlock, estimateSurfaceHeight, settings, biome);
		}
		
		static boolean canFlushChunk(BlockPos pos, ChunkStorageData chunk, String version) {
			return canUpgradeChunk(pos, chunk, version);
		}
		
		static boolean canUpgradeChunk(BlockPos pos, ChunkStorageData chunk, String version) {
			return Classic.Data.isInBiome(pos.getX(), pos.getY(), pos.getZ(), Classic.Data.seed);
		}
		
		static boolean canDowngradeChunk(BlockPos pos, ChunkStorageData chunk, String version) {
			return canUpgradeChunk(pos, chunk, version);
		}
	}
}
