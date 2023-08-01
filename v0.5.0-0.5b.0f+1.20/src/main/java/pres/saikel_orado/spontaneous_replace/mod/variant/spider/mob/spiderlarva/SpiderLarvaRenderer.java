package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spiderlarva;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderRenderer;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.SR_ID;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.Client.SPIDER_LARVA_LAYER;

/**
 * <b style="color:FFC800"><font size="+2">SpiderLarvaRenderer：幼蛛渲染类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">幼蛛的所有渲染设置</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/21 10:58
 */
@Environment(EnvType.CLIENT)
public class SpiderLarvaRenderer<T extends SpiderLarvaEntity> extends SRSpiderRenderer<T> {
    /**
     * 注册渲染器
     *
     * @param context 实体渲染器工厂
     */
    public SpiderLarvaRenderer(EntityRendererFactory.Context context) {
        super(context, new SpiderLarvaModel<>(context.getPart(SPIDER_LARVA_LAYER)), SpiderLarvaData.MODEL_SHADOW);
        addFeature(new SpiderLarvaEyes<>(this));
    }

    /**
     * 调整模型缩放
     */
    @Override
    protected void scale(T entity, MatrixStack matrices, float amount) {
        matrices.scale(SpiderLarvaData.MODEL_SCALE, SpiderLarvaData.MODEL_SCALE, SpiderLarvaData.MODEL_SCALE);
    }

    @Override
    public Identifier getTexture(T spiderEntity) {
        return new Identifier(SR_ID, "textures/entity/spider/" + SpiderLarvaData.ID + ".png");
    }
}
