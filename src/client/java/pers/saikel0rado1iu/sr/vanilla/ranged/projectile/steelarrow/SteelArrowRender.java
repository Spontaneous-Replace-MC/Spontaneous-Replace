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

package pers.saikel0rado1iu.sr.vanilla.ranged.projectile.steelarrow;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;

/**
 * <h2 style="color:FFC800">钢箭渲染类</h2>
 * <p style="color:FFC800">渲染钢箭实体</p>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class SteelArrowRender extends EntityRenderer<SteelArrowEntity> {
	public static final Identifier TEXTURE = new Identifier(SpontaneousReplace.DATA.getId(), "textures/entity/steel_arrow.png");
	
	public SteelArrowRender(EntityRendererFactory.Context context) {
		super(context);
	}
	
	@Override
	public void render(SteelArrowEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		matrixStack.push();
		matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, entity.prevYaw, entity.getYaw()) - 90.0F));
		matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, entity.prevPitch, entity.getPitch())));
		float s = (float) entity.shake - g;
		if (s > 0) matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-MathHelper.sin(s * 3.0F) * s));
		
		matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45.0F));
		matrixStack.scale(0.075F, 0.075F, 0.075F);
		matrixStack.translate(-4.0F, 0.0F, 0.0F);
		VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(this.getTexture(entity)));
		MatrixStack.Entry entry = matrixStack.peek();
		Matrix4f matrix4f = entry.getPositionMatrix();
		Matrix3f matrix3f = entry.getNormalMatrix();
		vertex(matrix4f, matrix3f, vertexConsumer, -7, -2, -2, 0.0F, 0.15625F, -1, 0, 0, i);
		vertex(matrix4f, matrix3f, vertexConsumer, -7, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, i);
		vertex(matrix4f, matrix3f, vertexConsumer, -7, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, i);
		vertex(matrix4f, matrix3f, vertexConsumer, -7, 2, -2, 0.0F, 0.3125F, -1, 0, 0, i);
		vertex(matrix4f, matrix3f, vertexConsumer, -7, 2, -2, 0.0F, 0.15625F, 1, 0, 0, i);
		vertex(matrix4f, matrix3f, vertexConsumer, -7, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, i);
		vertex(matrix4f, matrix3f, vertexConsumer, -7, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, i);
		vertex(matrix4f, matrix3f, vertexConsumer, -7, -2, -2, 0.0F, 0.3125F, 1, 0, 0, i);
		
		for (int u = 0; u < 4; ++u) {
			matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
			vertex(matrix4f, matrix3f, vertexConsumer, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, i);
			vertex(matrix4f, matrix3f, vertexConsumer, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, i);
			vertex(matrix4f, matrix3f, vertexConsumer, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, i);
			vertex(matrix4f, matrix3f, vertexConsumer, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, i);
		}
		
		matrixStack.pop();
		super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
	}
	
	public void vertex(Matrix4f positionMatrix, Matrix3f normalMatrix, VertexConsumer vertexConsumer, int x, int y, int z, float u, float v, int normalX, int normalZ, int normalY, int light) {
		vertexConsumer.vertex(positionMatrix, (float) x, (float) y, (float) z).color(255, 255, 255, 255).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, (float) normalX, (float) normalY, (float) normalZ).next();
	}
	
	@Override
	public Identifier getTexture(SteelArrowEntity spectralArrowEntity) {
		return TEXTURE;
	}
}
