package pres.saikel_orado.spontaneous_replace.mod.variant.general.block.treacheroussac;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

/**
 * <b style="color:FFC800"><font size="+2">TreacherousSacModel：诡谲囊模型</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">处理诡谲囊爆炸时的模型与动画</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/20 10:08
 */
@Environment(EnvType.CLIENT)
public class TreacherousSacModel<T extends TreacherousSacEntity> extends SinglePartEntityModel<T> {
    /**
     * EXPLODE: 爆炸动画 | 一次停止 | 1s
     */
    // region protected static final Animation EXPLODE = Animation.Builder.create(1f);
    protected static final Animation EXPLODE = Animation.Builder.create(1f)
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
