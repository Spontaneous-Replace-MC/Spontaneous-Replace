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

package pers.saikel0rado1iu.sr.variant.spider.mob.weaving;

import net.minecraft.client.model.*;
import pers.saikel0rado1iu.silk.util.MathUtil;
import pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderModel;

/**
 * <h2 style="color:FFC800">织网蜘蛛模型类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class WeavingWebSpiderModel<S extends WeavingWebSpiderEntity> extends SpiderModel<S> {
	/**
	 * 构建添加模组组件
	 *
	 * @param root 根组件
	 */
	public WeavingWebSpiderModel(ModelPart root) {
		super(root);
	}
	
	/**
	 * 获取纹理，模型数据
	 */
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		
		getBodyTexturedModelData(modelData);
		getHeadTexturedModelData(modelData);
		
		ModelPartData base = modelData.getRoot().getChild("base");
		base.addChild("rightHindLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, 4.0F));
		ModelPartData leftHindLeg = base.addChild("leftHindLeg", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 0.0F, 4.0F));
		leftHindLeg.addChild("leftHindLeg_rotate", ModelPartBuilder.create().uv(18, 0).cuboid(-8.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, MathUtil.toRadians(180)));
		base.addChild("rightMiddleLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, 1.0F));
		ModelPartData leftMiddleLeg = base.addChild("leftMiddleLeg", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 0.0F, 1.0F));
		leftMiddleLeg.addChild("leftMiddleLeg_rotate", ModelPartBuilder.create().uv(18, 0).cuboid(-8.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, MathUtil.toRadians(180)));
		base.addChild("rightMiddleFrontLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, -2.0F));
		ModelPartData leftMiddleFrontLeg = base.addChild("leftMiddleFrontLeg", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 0.0F, -2.0F));
		leftMiddleFrontLeg.addChild("leftMiddleFrontLeg_rotate", ModelPartBuilder.create().uv(18, 0).cuboid(-8.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, MathUtil.toRadians(180)));
		base.addChild("rightFrontLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, -5.0F));
		ModelPartData leftFrontLeg = base.addChild("leftFrontLeg", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 0.0F, -5.0F));
		leftFrontLeg.addChild("leftFrontLeg_rotate", ModelPartBuilder.create().uv(18, 0).cuboid(-8.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, MathUtil.toRadians(180)));
		
		return TexturedModelData.of(modelData, 64, 32);
	}
	
	/**
	 * 设置动画
	 */
	@Override
	public void setAngles(S entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
		super.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch);
		updateAnimation(entity.walkingAnimationState, WeavingWebSpiderAnimations.WALK, ageInTicks);
		updateAnimation(entity.swimmingAnimationState, WeavingWebSpiderAnimations.SWIM, ageInTicks);
		updateAnimation(entity.climbingAnimationState, WeavingWebSpiderAnimations.CLIMB, ageInTicks);
		updateAnimation(entity.jumpingAnimationState, WeavingWebSpiderAnimations.JUMP, ageInTicks);
		updateAnimation(entity.attackingAnimationState, WeavingWebSpiderAnimations.ATTACK, ageInTicks);
		updateAnimation(entity.dyingAnimationState, WeavingWebSpiderAnimations.DEATH, ageInTicks);
		updateAnimation(entity.weavingAnimationState, WeavingWebSpiderAnimations.WEAVE, ageInTicks);
	}
}
