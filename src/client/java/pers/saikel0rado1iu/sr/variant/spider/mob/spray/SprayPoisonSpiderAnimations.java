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

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderAnimations;

/**
 * <p><b style="color:FFC800"><font size="+1">喷吐毒蛛动画类</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public interface SprayPoisonSpiderAnimations extends SpiderAnimations {
	/**
	 * SPRAY: 喷毒动画 | 单次 | 0.5s
	 */
	// region Animation SPRAY = Animation.Builder.create(0.5f).build();
	Animation SPRAY = Animation.Builder.create(0.5f)
			.addBoneAnimation("body",
					new Transformation(Transformation.Targets.TRANSLATE,
							new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createTranslationalVector(0f, 2f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("body",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createRotationalVector(45f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("head",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createRotationalVector(-25f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("rightChelicera",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createRotationalVector(-45f, -15f, 45f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("leftChelicera",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createRotationalVector(-45f, 15f, -45f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("abdomen",
					new Transformation(Transformation.Targets.TRANSLATE,
							new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createTranslationalVector(0f, 0f, 7f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("abdomen",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createRotationalVector(80f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("rightHindLeg",
					new Transformation(Transformation.Targets.TRANSLATE,
							new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createTranslationalVector(0f, 2f, 1f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("rightHindLeg",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createRotationalVector(0f, -7.5f, -5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("leftHindLeg",
					new Transformation(Transformation.Targets.TRANSLATE,
							new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createTranslationalVector(0f, 2f, 1f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("leftHindLeg",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createRotationalVector(0f, 7.5f, 5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("rightMiddleLeg",
					new Transformation(Transformation.Targets.TRANSLATE,
							new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createTranslationalVector(0f, 1f, 1f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("rightMiddleLeg",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createRotationalVector(0f, -5f, -2.5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("leftMiddleLeg",
					new Transformation(Transformation.Targets.TRANSLATE,
							new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createTranslationalVector(0f, 1f, 1f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("leftMiddleLeg",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createRotationalVector(0f, 5f, 2.5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("rightMiddleFrontLeg",
					new Transformation(Transformation.Targets.TRANSLATE,
							new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createTranslationalVector(0f, 0f, 1f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("rightMiddleFrontLeg",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createRotationalVector(0f, -4f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("leftMiddleFrontLeg",
					new Transformation(Transformation.Targets.TRANSLATE,
							new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createTranslationalVector(0f, 0f, 1f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("leftMiddleFrontLeg",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createRotationalVector(0f, 4f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("rightFrontLeg",
					new Transformation(Transformation.Targets.TRANSLATE,
							new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createTranslationalVector(-1f, -1f, 1f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("rightFrontLeg",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createRotationalVector(0f, -7.5f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("leftFrontLeg",
					new Transformation(Transformation.Targets.TRANSLATE,
							new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createTranslationalVector(1f, -1f, 1f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("leftFrontLeg",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.1f, AnimationHelper.createRotationalVector(0f, 7.5f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.build();
	// endregion
}