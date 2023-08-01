package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider.toxin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;

/**
 * <b style="color:FFC800"><font size="+2">ToxinModel：毒素模型类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">毒素的实体模型和动作分配</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/01/07
 */
@Environment(EnvType.CLIENT)
public class ToxinModel<T extends net.minecraft.entity.Entity>
        extends SinglePartEntityModel<T> {
    /**
     * 根组件
     */
    protected final ModelPart root;

    /**
     * 构建模型
     *
     * @param root 根组件
     */
    public ToxinModel(ModelPart root) {
        this.root = root;
    }

    /**
     * 获取纹理，模型
     *
     * @return 纹理大小
     */
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 0)
                        .cuboid(-4.0f, 0.0f, 0.0f, 2.0f, 2.0f, 2.0f)
                        .cuboid(0.0f, -4.0f, 0.0f, 2.0f, 2.0f, 2.0f)
                        .cuboid(0.0f, 0.0f, -4.0f, 2.0f, 2.0f, 2.0f)
                        .cuboid(0.0f, 0.0f, 0.0f, 2.0f, 2.0f, 2.0f)
                        .cuboid(2.0f, 0.0f, 0.0f, 2.0f, 2.0f, 2.0f)
                        .cuboid(0.0f, 2.0f, 0.0f, 2.0f, 2.0f, 2.0f)
                        .cuboid(0.0f, 0.0f, 2.0f, 2.0f, 2.0f, 2.0f),
                ModelTransform.NONE);
        return TexturedModelData.of(modelData, 8, 4);
    }

    /**
     * 设置角度
     */
    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    /**
     * 获取组件
     *
     * @return 根组件
     */
    @Override
    public ModelPart getPart() {
        return root;
    }
}
