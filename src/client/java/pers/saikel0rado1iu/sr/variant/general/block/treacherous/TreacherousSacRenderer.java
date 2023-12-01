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

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;

import static pers.saikel0rado1iu.sr.data.client.ModelLayers.TREACHEROUS_SAC_LAYER;

/**
 * <p><b style="color:FFC800"><font size="+1">诡谲囊渲染器</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public class TreacherousSacRenderer<T extends TreacherousSacEntity> extends LivingEntityRenderer<T, TreacherousSacModel<T>> {
	/**
	 * 不渲染实体阴影
	 */
	public TreacherousSacRenderer(EntityRendererFactory.Context ctx) {
		super(ctx, new TreacherousSacModel<>(ctx.getPart(TREACHEROUS_SAC_LAYER)), 0);
	}
	
	@SuppressWarnings("unused")
	protected TreacherousSacRenderer(EntityRendererFactory.Context ctx, TreacherousSacModel<T> model, float shadowRadius) {
		super(ctx, model, shadowRadius);
	}
	
	/**
	 * 取消显示名称
	 */
	@Override
	protected boolean hasLabel(T livingEntity) {
		return false;
	}
	
	@Override
	public Identifier getTexture(T entity) {
		return new Identifier(SpontaneousReplace.DATA.getId(), "textures/entity/treacherous_sac.png");
	}
}
