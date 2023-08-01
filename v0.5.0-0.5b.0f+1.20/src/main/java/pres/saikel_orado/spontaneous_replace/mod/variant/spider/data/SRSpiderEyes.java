package pres.saikel_orado.spontaneous_replace.mod.variant.spider.data;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.SR_ID;

/**
 * <b style="color:FFC800"><font size="+2">SRSpiderEyes：《自然更替》蜘蛛眼部发光类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">《自然更替》蜘蛛的基础眼部发光纹理设置</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/2/18 15:22
 */
@Environment(EnvType.CLIENT)
public class SRSpiderEyes<S extends SRSpiderEntity, M extends SRSpiderModel<S>>
        extends EyesFeatureRenderer<S, M> {
    public SRSpiderEyes(FeatureRendererContext<S, M> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return RenderLayer.getEyes(new Identifier(SR_ID, "textures/entity/spider/eyes.png"));
    }
}
