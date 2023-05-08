package SpontaneousReplace.SpiderBiome.Generic.SRSpider;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;

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
@Environment(value = EnvType.CLIENT)
public class SRSpiderEyes<Spider extends SRSpiderEntity, Model extends SRSpiderModel<Spider>>
        extends EyesFeatureRenderer<Spider, Model> {
    public SRSpiderEyes(FeatureRendererContext<Spider, Model> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return RenderLayer.getEyes(new Identifier(MOD_ID, "textures/entity/spider/eyes.png"));
    }
}
