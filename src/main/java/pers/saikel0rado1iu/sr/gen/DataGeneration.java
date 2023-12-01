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

package pers.saikel0rado1iu.sr.gen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import pers.saikel0rado1iu.silk.gen.ModDataGeneration;
import pers.saikel0rado1iu.silk.gen.world.SilkWorldGenerator;
import pers.saikel0rado1iu.sr.gen.data.*;
import pers.saikel0rado1iu.sr.gen.world.WorldGenerator;

/**
 * <p><b style="color:FFC800"><font size="+1">自然更替数据生成器</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public final class DataGeneration extends ModDataGeneration {
	@Override
	public void datagen(FabricDataGenerator.Pack pack) {
		pack.addProvider(TagGeneration.Item::new);
		pack.addProvider(TagGeneration.Block::new);
		pack.addProvider(TagGeneration.Biome::new);
		pack.addProvider(TagGeneration.EntityType::new);
		pack.addProvider(TagGeneration.WorldPreset::new);
		pack.addProvider(RecipeGenerator::new);
		pack.addProvider(LootTableGenerator.Block::new);
		pack.addProvider(LootTableGenerator.Entity::new);
		pack.addProvider(AdvancementGenerator::new);
		pack.addProvider(ModelGenerator::new);
		pack.addProvider(LocalizationGenerator.EnUs::new);
		pack.addProvider(LocalizationGenerator.ZhCn::new);
		pack.addProvider(LocalizationGenerator.ZhHk::new);
		pack.addProvider(LocalizationGenerator.ZhTw::new);
	}
	
	@Override
	public FabricDataGenerator.Pack.RegistryDependentFactory<SilkWorldGenerator> worldGen() {
		return WorldGenerator::new;
	}
}
