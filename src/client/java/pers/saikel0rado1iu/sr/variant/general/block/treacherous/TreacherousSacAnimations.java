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

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

/**
 * <h2 style="color:FFC800">诡谲囊动画类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface TreacherousSacAnimations {
	/**
	 * EXPLODE: 爆炸动画 | 一次停止 | 1s
	 */
	// region Animation EXPLODE = Animation.Builder.create(1f);
	Animation EXPLODE = Animation.Builder.create(1f)
			.addBoneAnimation("bone9",
					new Transformation(Transformation.Targets.SCALE,
							new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(0.3433333f, AnimationHelper.createScalingVector(1.75f, 1.75f, 1.75f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(1f, AnimationHelper.createScalingVector(2f, 2f, 2f),
									Transformation.Interpolations.CUBIC)))
			.addBoneAnimation("bone",
					new Transformation(Transformation.Targets.SCALE,
							new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(0.3433333f, AnimationHelper.createScalingVector(1.75f, 1.75f, 1.75f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(1f, AnimationHelper.createScalingVector(2f, 2f, 2f),
									Transformation.Interpolations.CUBIC)))
			.addBoneAnimation("bone2",
					new Transformation(Transformation.Targets.SCALE,
							new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(0.3433333f, AnimationHelper.createScalingVector(1.75f, 1.75f, 1.75f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(1f, AnimationHelper.createScalingVector(2f, 2f, 2f),
									Transformation.Interpolations.CUBIC)))
			.addBoneAnimation("bone3",
					new Transformation(Transformation.Targets.SCALE,
							new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(0.3433333f, AnimationHelper.createScalingVector(1.75f, 1.75f, 1.75f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(1f, AnimationHelper.createScalingVector(2f, 2f, 2f),
									Transformation.Interpolations.CUBIC)))
			.addBoneAnimation("bone4",
					new Transformation(Transformation.Targets.SCALE,
							new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(0.3433333f, AnimationHelper.createScalingVector(1.75f, 1.75f, 1.75f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(1f, AnimationHelper.createScalingVector(2f, 2f, 2f),
									Transformation.Interpolations.CUBIC)))
			.addBoneAnimation("bone5",
					new Transformation(Transformation.Targets.SCALE,
							new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(0.3433333f, AnimationHelper.createScalingVector(1.75f, 1.75f, 1.75f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(1f, AnimationHelper.createScalingVector(2f, 2f, 2f),
									Transformation.Interpolations.CUBIC)))
			.addBoneAnimation("bone6",
					new Transformation(Transformation.Targets.SCALE,
							new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(0.3433333f, AnimationHelper.createScalingVector(1.75f, 1.75f, 1.75f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(1f, AnimationHelper.createScalingVector(2f, 2f, 2f),
									Transformation.Interpolations.CUBIC)))
			.addBoneAnimation("bone7",
					new Transformation(Transformation.Targets.SCALE,
							new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(0.3433333f, AnimationHelper.createScalingVector(1.75f, 1.75f, 1.75f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(1f, AnimationHelper.createScalingVector(2f, 2f, 2f),
									Transformation.Interpolations.CUBIC)))
			.addBoneAnimation("bone8",
					new Transformation(Transformation.Targets.SCALE,
							new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(0.3433333f, AnimationHelper.createScalingVector(1.75f, 1.75f, 1.75f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(1f, AnimationHelper.createScalingVector(2f, 2f, 2f),
									Transformation.Interpolations.CUBIC)))
			.addBoneAnimation("main",
					new Transformation(Transformation.Targets.SCALE,
							new Keyframe(0.75f, AnimationHelper.createScalingVector(1f, 1f, 1f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(0.9167666f, AnimationHelper.createScalingVector(1.05f, 1.05f, 1.05f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(1f, AnimationHelper.createScalingVector(1.2f, 1.2f, 1.2f),
									Transformation.Interpolations.CUBIC),
							new Keyframe(1.0416767f, AnimationHelper.createScalingVector(1.5f, 1.5f, 1.5f),
									Transformation.Interpolations.CUBIC))).build();
	// endregion
}
