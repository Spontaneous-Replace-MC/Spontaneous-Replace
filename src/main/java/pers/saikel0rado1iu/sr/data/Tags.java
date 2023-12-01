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

package pers.saikel0rado1iu.sr.data;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

/**
 * <p><b style="color:FFC800"><font size="+1">自然更替的所有的标签</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public interface Tags {
	interface Block {
		/**
		 * <p>方块标签：阴森木壳</p>
		 * <p>在此标签中的方块会被识别为阴森木壳的一种</p>
		 */
		TagKey<net.minecraft.block.Block> EERIE_RINDS = TagKey.of(RegistryKeys.BLOCK, new Identifier(SpontaneousReplace.DATA.getId(), "eerie_rinds"));
		/**
		 * <p>方块标签：丝化方块</p>
		 * <p>在此标签中的方块会被识别为丝化方块的一种</p>
		 */
		TagKey<net.minecraft.block.Block> COBWEBBY_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(SpontaneousReplace.DATA.getId(), "cobwebby_blocks"));
	}
	
	interface Biome {
		/**
		 * <p>生物群系标签：是蜘蛛群系</p>
		 * <p>在此标签中的方块会被识别为蜘蛛群系的一种</p>
		 */
		TagKey<net.minecraft.world.biome.Biome> IS_SPIDER_BIOME = TagKey.of(RegistryKeys.BIOME, new Identifier(SpontaneousReplace.DATA.getId(), "is_spider_biome"));
	}
}
