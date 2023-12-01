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

package pers.saikel0rado1iu.sr.data.client;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import pers.saikel0rado1iu.silk.api.registry.SilkBlock;

import static pers.saikel0rado1iu.sr.data.Blocks.*;

/**
 * <p><b style="color:FFC800"><font size="+1">自然更替的所有方块的客户端注册</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public class Blocks extends SilkBlock {
	static {
		clientRegister(WATER_EERIE_RIND, waterEerieRind -> ColorProviderRegistry.BLOCK.register(((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getWaterColor(world, pos) : -1), waterEerieRind));
		clientRegister(TREACHEROUS_VINES_PLANT, treacherousVinesPlant -> BlockRenderLayerMap.INSTANCE.putBlock(treacherousVinesPlant, RenderLayer.getCutout()));
		clientRegister(TREACHEROUS_VINES, treacherousVines -> BlockRenderLayerMap.INSTANCE.putBlock(treacherousVines, RenderLayer.getCutout()));
		clientRegister(GOSSAMER_CARPET, gossamerCarpet -> BlockRenderLayerMap.INSTANCE.putBlock(gossamerCarpet, RenderLayer.getCutout()));
		clientRegister(GOSSAMERY_LEAVES, gossameryLeaves -> {
			BlockRenderLayerMap.INSTANCE.putBlock(gossameryLeaves, RenderLayer.getCutoutMipped());
			ColorProviderRegistry.BLOCK.register(((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor()), gossameryLeaves);
			ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> FoliageColors.getDefaultColor()), gossameryLeaves);
		});
		clientRegister(STICKY_COMPACT_COBWEB, stickyCompactCobweb -> BlockRenderLayerMap.INSTANCE.putBlock(stickyCompactCobweb, RenderLayer.getCutout()));
	}
}
