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

package pers.saikel0rado1iu.sr.variant.spider.mob.larva;

import net.minecraft.client.model.ModelPart;
import pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderModel;

/**
 * <h2 style="color:FFC800">幼蛛模型类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class SpiderLarvaModel<S extends SpiderLarvaEntity> extends SpiderModel<S> {
	/**
	 * 构建添加模组组件
	 *
	 * @param root 根组件
	 */
	public SpiderLarvaModel(ModelPart root) {
		super(root);
	}
	
	/**
	 * 设置动画
	 */
	@Override
	public void setAngles(S entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
		head.xScale = head.yScale = head.zScale = 1.5F;
		super.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch);
		updateAnimation(entity.walkingAnimationState, SpiderLarvaAnimations.WALK, ageInTicks);
		updateAnimation(entity.swimmingAnimationState, SpiderLarvaAnimations.SWIM, ageInTicks);
		updateAnimation(entity.climbingAnimationState, SpiderLarvaAnimations.CLIMB, ageInTicks);
		updateAnimation(entity.jumpingAnimationState, SpiderLarvaAnimations.JUMP, ageInTicks);
		updateAnimation(entity.attackingAnimationState, SpiderLarvaAnimations.ATTACK, ageInTicks);
		updateAnimation(entity.dyingAnimationState, SpiderLarvaAnimations.DEATH, ageInTicks);
	}
}

