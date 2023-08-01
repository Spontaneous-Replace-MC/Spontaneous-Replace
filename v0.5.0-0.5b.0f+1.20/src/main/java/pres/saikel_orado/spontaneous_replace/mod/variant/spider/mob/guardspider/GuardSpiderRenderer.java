package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.guardspider;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderRenderer;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.SR_ID;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.Client.GUARD_SPIDER_LAYER;

/**
 * <b style="color:FFC800"><font size="+2">GuardSpiderRenderer：蜘蛛卫兵渲染类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">蜘蛛卫兵的所有渲染设置</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2022/12/26 ~ 2023/01/05
 */
@Environment(EnvType.CLIENT)
public class GuardSpiderRenderer<T extends GuardSpiderEntity> extends SRSpiderRenderer<T> {
    /**
     * 构建渲染类
     */
    public GuardSpiderRenderer(EntityRendererFactory.Context context) {
        super(context, new GuardSpiderModel<>(context.getPart(GUARD_SPIDER_LAYER)), GuardSpiderData.MODEL_SHADOW);
        addFeature(new GuardSpiderEyes<>(this));
    }

    /**
     * 调整模型缩放
     */
    @Override
    protected void scale(T entity, MatrixStack matrices, float amount) {
        matrices.scale(GuardSpiderData.MODEL_SCALE, GuardSpiderData.MODEL_SCALE, GuardSpiderData.MODEL_SCALE);
    }

    @Override
    public Identifier getTexture(T spiderEntity) {
        return new Identifier(SR_ID, "textures/entity/spider/" + GuardSpiderData.ID + ".png");
    }
}
