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

package pers.saikel0rado1iu.sr.variant.general.block.eerie;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;

import static pers.saikel0rado1iu.silk.api.registry.SilkEntityType.POS_SHIFTING;
import static pers.saikel0rado1iu.sr.data.Blocks.EERIE_RIND;

/**
 * <h2 style="color:FFC800">可调整液面阴森木壳</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public abstract class LeveledEerieRind extends LeveledCauldronBlock {
	public LeveledEerieRind(Biome.Precipitation precipitation, CauldronBehavior.CauldronBehaviorMap behaviorMap, AbstractBlock.Settings settings) {
		super(precipitation, behaviorMap, settings);
	}
	
	public static void decrementFluidLevel(BlockState state, World world, BlockPos pos) {
		int i = state.get(LEVEL) - 1;
		BlockState blockState = i == 0 ? EERIE_RIND.getDefaultState() : state.with(LEVEL, i);
		world.setBlockState(pos, blockState);
		world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
	}
	
	/**
	 * 随机灵魂粒子效果
	 */
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (random.nextInt(3) == 0) {
			java.util.Random randomValue = new java.util.Random();
			world.addParticle(ParticleTypes.SCULK_SOUL,
					pos.getX() + POS_SHIFTING + randomValue.nextDouble(-1, 1),
					pos.getY() + POS_SHIFTING + randomValue.nextDouble(-1, 1),
					pos.getZ() + POS_SHIFTING + randomValue.nextDouble(-1, 1),
					randomValue.nextDouble(-0.07, 0.07),
					randomValue.nextDouble(0, 0.1),
					randomValue.nextDouble(-0.07, 0.07));
		}
	}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!world.isClient && entity.isOnFire() && this.isEntityTouchingFluid(state, pos, entity)) {
			entity.extinguish();
			if (entity.canModifyAt(world, pos)) {
				this.onFireCollision(state, world, pos);
			}
		}
	}
	
	protected void onFireCollision(BlockState state, World world, BlockPos pos) {
		world.setBlockState(pos, state);
	}
}
