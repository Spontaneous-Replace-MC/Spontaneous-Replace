package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spiderlarva;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderModel;

/**
 * <b style="color:FFC800"><font size="+2">SpiderLarvaModel：幼蛛模型类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">幼蛛的实体模型和动作分配</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/21 10:58
 */
@Environment(EnvType.CLIENT)
public class SpiderLarvaModel<S extends SpiderLarvaEntity> extends SRSpiderModel<S> {
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
