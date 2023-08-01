package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spiderlarva;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderEntity;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderEyes;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderModel;

/**
 * <b style="color:FFC800"><font size="+2">SpiderLarvaEyes：幼蛛眼部发光类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">幼蛛的眼部发光纹理设置</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/21 10:55
 */
@Environment(EnvType.CLIENT)
public class SpiderLarvaEyes <T extends SRSpiderEntity, M extends SRSpiderModel<T>> extends SRSpiderEyes<T, M> {
    public SpiderLarvaEyes(FeatureRendererContext<T, M> featureRendererContext) {
        super(featureRendererContext);
    }
}
