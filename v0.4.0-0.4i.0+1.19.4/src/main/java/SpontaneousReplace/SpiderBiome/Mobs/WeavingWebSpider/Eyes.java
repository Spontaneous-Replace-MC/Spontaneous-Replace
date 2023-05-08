package SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider;

import SpontaneousReplace.SpiderBiome.Generic.SRSpider.SRSpiderEntity;
import SpontaneousReplace.SpiderBiome.Generic.SRSpider.SRSpiderEyes;
import SpontaneousReplace.SpiderBiome.Generic.SRSpider.SRSpiderModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;

/**
 * <b style="color:FFC800"><font size="+2">Eyes：织网蜘蛛眼部发光类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">织网蜘蛛的眼部发光纹理设置</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/1/18 19:41
 */
@Environment(value = EnvType.CLIENT)
public class Eyes<T extends SRSpiderEntity, M extends SRSpiderModel<T>> extends SRSpiderEyes<T, M> {
    public Eyes(FeatureRendererContext<T, M> featureRendererContext) {
        super(featureRendererContext);
    }
}