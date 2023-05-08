package SpontaneousReplace.SpiderBiome.Mobs.GuardSpider;

import SpontaneousReplace.SpiderBiome.Generic.SRSpider.SRSpiderModel;
import SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Animations;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;

/**
 * <b style="color:FFC800"><font size="+2">Model：蜘蛛卫兵模型类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">蜘蛛卫兵的实体模型和动作分配</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2022/12/26 ~ 2023/01/05
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
     * 设置组件大小，纹理偏移和模型数据
     *
     * @return 纹理大小
     */
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();

        getBodyTexturedModelData(modelData);
        getHeadTexturedModelData(modelData);
        getLegsTexturedModelData(modelData);

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
    }
}
