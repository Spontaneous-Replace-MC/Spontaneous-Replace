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

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.Entity;

/**
 * <h2 style="color:FFC800">毒素模型类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class ToxinModel<T extends Entity> extends SinglePartEntityModel<T> {
	/**
	 * 根组件
	 */
	protected final ModelPart root;
	
	/**
	 * 构建模型
	 *
	 * @param root 根组件
	 */
	public ToxinModel(ModelPart root) {
		this.root = root;
	}
	
	/**
	 * 获取纹理，模型
	 *
	 * @return 纹理大小
	 */
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 0)
						.cuboid(-4.0f, 0.0f, 0.0f, 2.0f, 2.0f, 2.0f)
						.cuboid(0.0f, -4.0f, 0.0f, 2.0f, 2.0f, 2.0f)
						.cuboid(0.0f, 0.0f, -4.0f, 2.0f, 2.0f, 2.0f)
						.cuboid(0.0f, 0.0f, 0.0f, 2.0f, 2.0f, 2.0f)
						.cuboid(2.0f, 0.0f, 0.0f, 2.0f, 2.0f, 2.0f)
						.cuboid(0.0f, 2.0f, 0.0f, 2.0f, 2.0f, 2.0f)
						.cuboid(0.0f, 0.0f, 2.0f, 2.0f, 2.0f, 2.0f),
				ModelTransform.NONE);
		return TexturedModelData.of(modelData, 8, 4);
	}
	
	/**
	 * 设置角度
	 */
	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}
	
	/**
	 * 获取组件
	 *
	 * @return 根组件
	 */
	@Override
	public ModelPart getPart() {
		return root;
	}
}

