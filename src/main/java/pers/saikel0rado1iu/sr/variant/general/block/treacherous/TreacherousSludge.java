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

package pers.saikel0rado1iu.sr.variant.general.block.treacherous;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import pers.saikel0rado1iu.silk.api.block.InfectSapling;
import pers.saikel0rado1iu.silk.api.block.PlantableBlock;
import pers.saikel0rado1iu.sr.variant.general.world.gen.feature.VariantConfiguredFeatures;

import static net.minecraft.block.Blocks.AIR;

/**
 * <h2 style="color:FFC800">诡谲污泥</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class TreacherousSludge extends PlantableBlock implements InfectSapling {
	public TreacherousSludge(Settings settings) {
		super(settings);
	}
	
	@Override
	public void generate(ServerWorld serverWorld, BlockPos blockPos, BlockState blockState, Random random, SaplingBlock saplingBlock) {
		serverWorld.setBlockState(blockPos, AIR.getDefaultState());
		ConfiguredFeature<?, ?> registryEntry = serverWorld.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE)
				.getEntry(VariantConfiguredFeatures.TREACHEROUS_TREE).orElseThrow().value();
		TreeFeatureConfig config = (TreeFeatureConfig) registryEntry.config();
		registryEntry.generate(serverWorld, serverWorld.getChunkManager().getChunkGenerator(), random, blockPos);
		for (int x = 0; x < 4; x++) {
			for (int z = 0; z < 4; z++) {
				BlockPos pos = blockPos.add(x - 1, 0, z - 1).down();
				if (serverWorld.getBlockState(pos.up()).isOf(config.trunkProvider.get(random, pos).getBlock()))
					serverWorld.setBlockState(pos, AIR.getDefaultState());
			}
		}
		serverWorld.setBlockState(blockPos.down(), config.dirtProvider.get(random, blockPos));
	}
}
