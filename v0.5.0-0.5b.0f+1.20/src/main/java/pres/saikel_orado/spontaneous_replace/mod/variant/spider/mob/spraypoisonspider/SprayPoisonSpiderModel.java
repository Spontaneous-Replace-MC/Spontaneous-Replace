package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderModel;

import static pres.saikel_orado.spontaneous_replace.java.Functions.toRadians;

/**
 * <b style="color:FFC800"><font size="+2">SprayPoisonSpiderModel：喷吐毒蛛模型类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">喷吐毒蛛的实体模型和动作分配</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/01/06
 */
@Environment(EnvType.CLIENT)
public class SprayPoisonSpiderModel<S extends SprayPoisonSpiderEntity> extends SRSpiderModel<S> {
    public SprayPoisonSpiderModel(ModelPart root) {
        super(root);
    }

    /**
     * 设置鳌肢对称
     */
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();

        getBodyTexturedModelData(modelData);
        getLegsTexturedModelData(modelData);

        ModelPartData head = modelData.getRoot().getChild("base").getChild("body").getChild("head");
        head.addChild("rightJaw", ModelPartBuilder.create().uv(56, 1).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 0.0F, -8.0F));
        head.addChild("leftJaw", ModelPartBuilder.create().uv(56, 6).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 0.0F, -8.0F));
        head.addChild("rightChelicera", ModelPartBuilder.create().uv(36, 20).cuboid(-9.0F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -2.0F, -1.0F));
        ModelPartData leftChelicera = head.addChild("leftChelicera", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, -2.0F, -1.0F));
        leftChelicera.addChild("leftChelicera_rotate", ModelPartBuilder.create().uv(36, 20).cuboid(-5.0F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, 0.0F, 0.0F, 0.0F, toRadians(180)));

        return TexturedModelData.of(modelData, 64, 32);
    }

    /**
     * 注册喷涂动作
     */
    @Override
    public void setAngles(S entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
        super.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch);
        updateAnimation(entity.walkingAnimationState, SprayPoisonSpiderAnimations.WALK, ageInTicks);
        updateAnimation(entity.swimmingAnimationState, SprayPoisonSpiderAnimations.SWIM, ageInTicks);
        updateAnimation(entity.climbingAnimationState, SprayPoisonSpiderAnimations.CLIMB, ageInTicks);
        updateAnimation(entity.jumpingAnimationState, SprayPoisonSpiderAnimations.JUMP, ageInTicks);
        updateAnimation(entity.attackingAnimationState, SprayPoisonSpiderAnimations.ATTACK, ageInTicks);
        updateAnimation(entity.dyingAnimationState, SprayPoisonSpiderAnimations.DEATH, ageInTicks);
        updateAnimation(entity.sprayingAnimationState, SprayPoisonSpiderAnimations.SPRAY, ageInTicks);
    }
}
