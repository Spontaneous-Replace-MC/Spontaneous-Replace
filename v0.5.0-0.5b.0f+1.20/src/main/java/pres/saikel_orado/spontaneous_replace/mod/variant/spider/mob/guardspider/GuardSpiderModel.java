package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.guardspider;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderModel;

/**
 * <b style="color:FFC800"><font size="+2">GuardSpiderModel：蜘蛛卫兵模型类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">蜘蛛卫兵的实体模型和动作分配</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2022/12/26 ~ 2023/01/05
 */
@Environment(EnvType.CLIENT)
public class GuardSpiderModel<S extends GuardSpiderEntity> extends SRSpiderModel<S> {
    /**
     * 构建添加模组组件
     *
     * @param root 根组件
     */
    public GuardSpiderModel(ModelPart root) {
        super(root);
    }

    /**
     * 设置动画
     */
    @Override
    public void setAngles(S entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
        super.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch);
        updateAnimation(entity.walkingAnimationState, GuardSpiderAnimations.WALK, ageInTicks);
        updateAnimation(entity.swimmingAnimationState, GuardSpiderAnimations.SWIM, ageInTicks);
        updateAnimation(entity.climbingAnimationState, GuardSpiderAnimations.CLIMB, ageInTicks);
        updateAnimation(entity.jumpingAnimationState, GuardSpiderAnimations.JUMP, ageInTicks);
        updateAnimation(entity.attackingAnimationState, GuardSpiderAnimations.ATTACK, ageInTicks);
        updateAnimation(entity.dyingAnimationState, GuardSpiderAnimations.DEATH, ageInTicks);
    }
}
