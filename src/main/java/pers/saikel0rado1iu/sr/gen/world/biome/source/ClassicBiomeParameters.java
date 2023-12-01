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

package pers.saikel0rado1iu.sr.gen.world.biome.source;

import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import pers.saikel0rado1iu.silk.gen.world.biome.source.SilkVanillaBiomeParameters;
import pers.saikel0rado1iu.sr.variant.spider.world.gen.biome.SpiderBiomeKeys;

import java.util.function.Consumer;

/**
 * <p><b style="color:FFC800"><font size="+1">自然更替的经典生物群系参数</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public class ClassicBiomeParameters extends SilkVanillaBiomeParameters {
	public final MultiNoiseUtil.ParameterRange defaultParameter = MultiNoiseUtil.ParameterRange.of(-1.0f, 1.0f);
	public final MultiNoiseUtil.ParameterRange nearInlandContinentalness = MultiNoiseUtil.ParameterRange.of(-0.11f, 0.03f);
	public final MultiNoiseUtil.ParameterRange farInlandContinentalness = MultiNoiseUtil.ParameterRange.of(0.3f, 1.0f);
	public final MultiNoiseUtil.ParameterRange riverContinentalness = MultiNoiseUtil.ParameterRange.of(-0.11f, 0.55f);
	
	@Override
	public void writeOverworldBiomeParameters(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters) {
		writeBiomeParameters(parameters, MultiNoiseUtil.ParameterRange.combine(getTemperatureParameters()[1], getTemperatureParameters()[2]), defaultParameter,
				MultiNoiseUtil.ParameterRange.combine(nearInlandContinentalness, farInlandContinentalness), getErosionParameters()[6],
				MultiNoiseUtil.ParameterRange.of(-1.0f, -0.93333334f), 0.0f, SpiderBiomeKeys.CREEPY_SPIDER_FOREST);
		writeBiomeParameters(parameters, MultiNoiseUtil.ParameterRange.combine(getTemperatureParameters()[1], getTemperatureParameters()[2]), defaultParameter,
				MultiNoiseUtil.ParameterRange.combine(nearInlandContinentalness, farInlandContinentalness), getErosionParameters()[6],
				MultiNoiseUtil.ParameterRange.of(-0.4f, -0.26666668f), 0.0f, SpiderBiomeKeys.CREEPY_SPIDER_FOREST);
		writeBiomeParameters(parameters, MultiNoiseUtil.ParameterRange.combine(getTemperatureParameters()[1], getTemperatureParameters()[2]), defaultParameter,
				MultiNoiseUtil.ParameterRange.combine(nearInlandContinentalness, farInlandContinentalness), getErosionParameters()[6],
				MultiNoiseUtil.ParameterRange.of(-0.26666668f, -0.05f), 0.0f, net.minecraft.world.biome.BiomeKeys.SWAMP);
		writeBiomeParameters(parameters, MultiNoiseUtil.ParameterRange.combine(getTemperatureParameters()[1], getTemperatureParameters()[2]), defaultParameter,
				MultiNoiseUtil.ParameterRange.combine(riverContinentalness, farInlandContinentalness), getErosionParameters()[6],
				MultiNoiseUtil.ParameterRange.of(-0.05f, 0.05f), 0.0f, net.minecraft.world.biome.BiomeKeys.SWAMP);
		writeBiomeParameters(parameters, MultiNoiseUtil.ParameterRange.combine(getTemperatureParameters()[1], getTemperatureParameters()[2]), defaultParameter,
				MultiNoiseUtil.ParameterRange.combine(nearInlandContinentalness, farInlandContinentalness), getErosionParameters()[6],
				MultiNoiseUtil.ParameterRange.of(0.05f, 0.26666668f), 0.0f, net.minecraft.world.biome.BiomeKeys.SWAMP);
		writeBiomeParameters(parameters, MultiNoiseUtil.ParameterRange.combine(getTemperatureParameters()[1], getTemperatureParameters()[2]), defaultParameter,
				MultiNoiseUtil.ParameterRange.combine(nearInlandContinentalness, farInlandContinentalness), getErosionParameters()[6],
				MultiNoiseUtil.ParameterRange.of(0.26666668f, 0.4f), 0.0f, SpiderBiomeKeys.CREEPY_SPIDER_FOREST);
		writeBiomeParameters(parameters, MultiNoiseUtil.ParameterRange.combine(getTemperatureParameters()[1], getTemperatureParameters()[2]), defaultParameter,
				MultiNoiseUtil.ParameterRange.combine(nearInlandContinentalness, farInlandContinentalness), getErosionParameters()[6],
				MultiNoiseUtil.ParameterRange.of(0.93333334f, 1.0f), 0.0f, SpiderBiomeKeys.CREEPY_SPIDER_FOREST);
		super.writeOverworldBiomeParameters(parameters);
	}
	
	public void writeBiomeParameters(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey<Biome> biome) {
		parameters.accept(Pair.of(MultiNoiseUtil.createNoiseHypercube(temperature, humidity, continentalness, erosion, MultiNoiseUtil.ParameterRange.of(0.0f), weirdness, offset), biome));
		parameters.accept(Pair.of(MultiNoiseUtil.createNoiseHypercube(temperature, humidity, continentalness, erosion, MultiNoiseUtil.ParameterRange.of(1.0f), weirdness, offset), biome));
	}
}
