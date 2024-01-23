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

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WeepingVinesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pers.saikel0rado1iu.silk.util.ParticleUtil;
import pers.saikel0rado1iu.silk.util.TickUtil;
import pers.saikel0rado1iu.sr.data.StatusEffects;

import java.util.Random;
import java.util.function.ToIntFunction;

import static pers.saikel0rado1iu.silk.api.registry.SilkEntityType.POS_SHIFTING;
import static pers.saikel0rado1iu.sr.data.Blocks.TREACHEROUS_VINES_PLANT;
import static pers.saikel0rado1iu.sr.variant.general.VariantData.TREACHEROUS_PLANT_STABILITY;

/**
 * <h2 style="color:FFC800">诡谲藤</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class TreacherousVines extends WeepingVinesBlock {
	public static final ToIntFunction<BlockState> TREACHEROUS_VINES_LUMINANCE = (state) -> 7;
	
	public TreacherousVines(Settings settings) {
		super(settings);
	}
	
	@Override
	protected Block getPlant() {
		return TREACHEROUS_VINES_PLANT;
	}
	
	/**
	 * 随机酸气粒子效果
	 */
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (random.nextInt(5) == 0) {
			java.util.Random randomValue = new java.util.Random();
			ParticleUtil.addEffectParticle(world, StatusEffects.ACIDIZE.value(),
					pos.getX() + POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5),
					pos.getY() + POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5),
					pos.getZ() + POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5));
		}
	}
	
	/**
	 * 玩家接触会中毒
	 */
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		super.onEntityCollision(state, world, pos, entity);
		if (!world.isClient && entity instanceof LivingEntity living
				&& new Random().nextInt((int) Math.pow(TREACHEROUS_PLANT_STABILITY, 3)) == 0)
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.ACIDIZE, TickUtil.getTick(3)));
	}
}
