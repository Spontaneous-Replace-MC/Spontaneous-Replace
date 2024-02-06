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

import net.minecraft.block.BlockState;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import org.jetbrains.annotations.Nullable;
import pers.saikel0rado1iu.silk.gen.world.SilkBiomeKey;
import pers.saikel0rado1iu.silk.util.world.upgrade.data.ChunkStorageData;
import pers.saikel0rado1iu.sr.variant.spider.world.gen.biome.SpiderBiomeKeys;

import java.util.function.Function;

/**
 * <h2 style="color:FFC800">自然更替的生物群系</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class BiomeKeys extends SilkBiomeKey {
	@Override
	public void bootstrap(Registerable<Biome> registerable) {
		new SpiderBiomeKeys().bootstrap(registerable);
	}
	
	public interface Classic {
		static BiomeSource getBiome(BlockPos pos, BiomeSource biomeSource, Function<RegistryKey<Biome>, FixedBiomeSource> getVariantBiomeSource) {
			return SpiderBiomeKeys.Classic.getBiome(pos, biomeSource, getVariantBiomeSource);
		}
		
		static BlockState setTerrainNoise(BlockPos pos, BlockState originBlock, int estimateSurfaceHeight, ChunkGeneratorSettings settings, @Nullable RegistryKey<Biome> biome) {
			return SpiderBiomeKeys.Classic.setTerrainNoise(pos, originBlock, estimateSurfaceHeight, settings, biome);
		}
		
		static boolean canFlushChunk(BlockPos pos, ChunkStorageData chunk, String version) {
			return SpiderBiomeKeys.Classic.canFlushChunk(pos, chunk, version);
		}
		
		static boolean canUpgradeChunk(BlockPos pos, ChunkStorageData chunk, String version) {
			return SpiderBiomeKeys.Classic.canUpgradeChunk(pos, chunk, version);
		}
		
		static boolean canDowngradeChunk(BlockPos pos, ChunkStorageData chunk, String version) {
			return SpiderBiomeKeys.Classic.canDowngradeChunk(pos, chunk, version);
		}
	}
	
	public interface Snapshot {
		static BiomeSource getBiome(BlockPos pos, BiomeSource biomeSource, Function<RegistryKey<Biome>, FixedBiomeSource> getVariantBiomeSource) {
			return SpiderBiomeKeys.Snapshot.getBiome(pos, biomeSource, getVariantBiomeSource);
		}
		
		static BlockState setTerrainNoise(BlockPos pos, BlockState originBlock, int estimateSurfaceHeight, ChunkGeneratorSettings settings, @Nullable RegistryKey<Biome> biome) {
			return SpiderBiomeKeys.Snapshot.setTerrainNoise(pos, originBlock, estimateSurfaceHeight, settings, biome);
		}
		
		static boolean canFlushChunk(BlockPos pos, ChunkStorageData chunk, String version) {
			return SpiderBiomeKeys.Snapshot.canFlushChunk(pos, chunk, version);
		}
		
		static boolean canUpgradeChunk(BlockPos pos, ChunkStorageData chunk, String version) {
			return SpiderBiomeKeys.Snapshot.canUpgradeChunk(pos, chunk, version);
		}
		
		static boolean canDowngradeChunk(BlockPos pos, ChunkStorageData chunk, String version) {
			return SpiderBiomeKeys.Snapshot.canDowngradeChunk(pos, chunk, version);
		}
	}
}
