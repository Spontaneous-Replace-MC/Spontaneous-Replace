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

package pers.saikel0rado1iu.sr.variant.spider.mob.spray;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderRenderer;

import static pers.saikel0rado1iu.sr.data.client.ModelLayers.SPRAY_POISON_SPIDER_LAYER;

/**
 * <p><b style="color:FFC800"><font size="+1">喷吐毒蛛渲染类</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public class SprayPoisonSpiderRenderer<T extends SprayPoisonSpiderEntity> extends SpiderRenderer<T> {
	/**
	 * 构建渲染
	 */
	public SprayPoisonSpiderRenderer(EntityRendererFactory.Context context) {
		super(context, new SprayPoisonSpiderModel<>(context.getPart(SPRAY_POISON_SPIDER_LAYER)), SprayPoisonSpiderData.MODEL_SHADOW);
		this.addFeature(new SprayPoisonSpiderEyes<>(this));
	}
	
	/**
	 * 调整模型缩放
	 */
	@Override
	protected void scale(T entity, MatrixStack matrices, float amount) {
		matrices.scale(SprayPoisonSpiderData.MODEL_SCALE, SprayPoisonSpiderData.MODEL_SCALE, SprayPoisonSpiderData.MODEL_SCALE);
	}
	
	@Override
	public String getTextureId() {
		return SprayPoisonSpiderData.ID;
	}
}
