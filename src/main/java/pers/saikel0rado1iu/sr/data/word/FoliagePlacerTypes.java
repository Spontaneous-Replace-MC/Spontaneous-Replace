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

package pers.saikel0rado1iu.sr.data.word;

import net.minecraft.world.gen.foliage.FoliagePlacerType;
import pers.saikel0rado1iu.silk.api.registry.gen.world.SilkFoliagePlacerType;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;
import pers.saikel0rado1iu.sr.variant.general.world.gen.foliage.EerieFoliagePlacer;
import pers.saikel0rado1iu.sr.variant.general.world.gen.foliage.TreacherousFoliagePlacer;
import pers.saikel0rado1iu.sr.variant.spider.world.gen.foliage.CobwebbyOakFoliagePlacer;

/**
 * <p><b style="color:FFC800"><font size="+1">自然更替的所有树叶放置器类型</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public final class FoliagePlacerTypes extends SilkFoliagePlacerType {
	public static final FoliagePlacerType<EerieFoliagePlacer> EERIE_BOUGH_FOLIAGE_PLACER = builder(new FoliagePlacerType<>(EerieFoliagePlacer.CODEC))
			.build(SpontaneousReplace.DATA, "eerie_bough");
	public static final FoliagePlacerType<TreacherousFoliagePlacer> TREACHEROUS_FOLIAGE_PLACER = builder(new FoliagePlacerType<>(TreacherousFoliagePlacer.CODEC))
			.build(SpontaneousReplace.DATA, "treacherous_vines");
	public static final FoliagePlacerType<CobwebbyOakFoliagePlacer> COBWEBBY_OAK_FOLIAGE_PLACER = builder(new FoliagePlacerType<>(CobwebbyOakFoliagePlacer.CODEC))
			.build(SpontaneousReplace.DATA, "cobwebby_oak_foliage_placer");
}
