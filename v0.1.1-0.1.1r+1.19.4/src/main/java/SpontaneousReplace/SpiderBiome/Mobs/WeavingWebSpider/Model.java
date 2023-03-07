package SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider;

import SpontaneousReplace.SpiderBiome.Generic.SRSpider.SRSpiderModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;

import static JavaGeneric.Functions.toRadians;

/**
 * <b style="color:FFC800"><font size="+2">Model：织网蜘蛛模型类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">织网蜘蛛的实体模型和动作分配</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/1/18 19:41
 */
@Environment(value = EnvType.CLIENT)
public class Model<Spider extends Entity> extends SRSpiderModel<Spider> {
    /**
     * 构建添加模组组件
     *
     * @param root 根组件
     */
    public Model(ModelPart root) {
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
        leftHindLeg.addChild("leftHindLeg_rotate", ModelPartBuilder.create().uv(18, 0).cuboid(-8.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, toRadians(180)));
        base.addChild("rightMiddleLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, 1.0F));
        ModelPartData leftMiddleLeg = base.addChild("leftMiddleLeg", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 0.0F, 1.0F));
        leftMiddleLeg.addChild("leftMiddleLeg_rotate", ModelPartBuilder.create().uv(18, 0).cuboid(-8.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, toRadians(180)));
        base.addChild("rightMiddleFrontLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, -2.0F));
        ModelPartData leftMiddleFrontLeg = base.addChild("leftMiddleFrontLeg", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 0.0F, -2.0F));
        leftMiddleFrontLeg.addChild("leftMiddleFrontLeg_rotate", ModelPartBuilder.create().uv(18, 0).cuboid(-8.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, toRadians(180)));
        base.addChild("rightFrontLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, -5.0F));
        ModelPartData leftFrontLeg = base.addChild("leftFrontLeg", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 0.0F, -5.0F));
        leftFrontLeg.addChild("leftFrontLeg_rotate", ModelPartBuilder.create().uv(18, 0).cuboid(-8.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, toRadians(180)));

        return TexturedModelData.of(modelData, 64, 32);
    }

    /**
     * 设置动画
     */
    @Override
    public void setAngles(Spider entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
        super.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch);
        updateAnimation(entity.walkingAnimationState, Animations.WALK, ageInTicks);
        updateAnimation(entity.swimmingAnimationState, Animations.SWIM, ageInTicks);
        updateAnimation(entity.climbingAnimationState, Animations.CLIMB, ageInTicks);
        updateAnimation(entity.jumpingAnimationState, Animations.JUMP, ageInTicks);
        updateAnimation(entity.attackingAnimationState, Animations.ATTACK, ageInTicks);
        updateAnimation(entity.dyingAnimationState, Animations.DEATH, ageInTicks);
        updateAnimation(entity.weavingAnimationState, Animations.WEAVE, ageInTicks);
    }
}
