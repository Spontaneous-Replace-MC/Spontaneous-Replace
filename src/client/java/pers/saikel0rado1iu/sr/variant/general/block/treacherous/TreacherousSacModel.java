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

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

import static pers.saikel0rado1iu.sr.variant.general.block.treacherous.TreacherousSacAnimations.EXPLODE;

/**
 * <h2 style="color:FFC800">诡谲囊模型</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class TreacherousSacModel<T extends TreacherousSacEntity> extends SinglePartEntityModel<T> {
	protected final ModelPart root;
	protected final ModelPart main;
	protected final ModelPart bone;
	protected final ModelPart bone2;
	protected final ModelPart bone3;
	protected final ModelPart bone4;
	protected final ModelPart bone5;
	protected final ModelPart bone6;
	protected final ModelPart bone7;
	protected final ModelPart bone8;
	protected final ModelPart bone9;
	
	public TreacherousSacModel(ModelPart root) {
		this.root = root;
		
		main = root.getChild("main");
		bone = main.getChild("bone");
		bone2 = main.getChild("bone2");
		bone3 = main.getChild("bone3");
		bone4 = main.getChild("bone4");
		bone5 = main.getChild("bone5");
		bone6 = main.getChild("bone6");
		bone7 = main.getChild("bone7");
		bone8 = main.getChild("bone8");
		bone9 = main.getChild("bone9");
	}
	
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		main.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -13.0F, -6.0F));
		main.addChild("bone2", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, -3.0F, 5.0F));
		main.addChild("bone3", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -13.0F, 5.0F));
		main.addChild("bone4", ModelPartBuilder.create().uv(2, 8).cuboid(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(5.5F, -2.5F, -5.5F));
		main.addChild("bone5", ModelPartBuilder.create().uv(2, 8).cuboid(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(5.5F, -2.5F, 6.5F));
		main.addChild("bone6", ModelPartBuilder.create().uv(2, 8).cuboid(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.5F, -5.5F, -3.5F));
		main.addChild("bone7", ModelPartBuilder.create().uv(52, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-7.0F, -11.0F, 3.0F));
		main.addChild("bone8", ModelPartBuilder.create().uv(52, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(7.0F, -11.0F, -3.0F));
		main.addChild("bone9", ModelPartBuilder.create().uv(52, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-7.0F, -1.0F, -7.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}
	
	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		main.xScale = main.yScale = main.zScale = 1;
		bone.xScale = bone.yScale = bone.zScale = 1;
		bone2.xScale = bone2.yScale = bone2.zScale = 1;
		bone3.xScale = bone3.yScale = bone3.zScale = 1;
		bone4.xScale = bone4.yScale = bone4.zScale = 1;
		bone5.xScale = bone5.yScale = bone5.zScale = 1;
		bone6.xScale = bone6.yScale = bone6.zScale = 1;
		bone7.xScale = bone7.yScale = bone7.zScale = 1;
		bone8.xScale = bone8.yScale = bone8.zScale = 1;
		bone9.xScale = bone9.yScale = bone9.zScale = 1;
		updateAnimation(entity.explodeAnimationState, EXPLODE, animationProgress);
	}
	
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		getPart().render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
	
	@Override
	public ModelPart getPart() {
		return root;
	}
}
