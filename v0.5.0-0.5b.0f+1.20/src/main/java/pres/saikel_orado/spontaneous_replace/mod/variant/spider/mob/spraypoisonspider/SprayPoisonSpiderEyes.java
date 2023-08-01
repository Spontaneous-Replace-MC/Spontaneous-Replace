package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider;

import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderEntity;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderEyes;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;

/**
 * <b style="color:FFC800"><font size="+2">SprayPoisonSpiderEyes：喷吐毒蛛眼部发光类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">喷吐毒蛛的眼部发光纹理设置</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/01/06
 */
@Environment(EnvType.CLIENT)
public class SprayPoisonSpiderEyes<T extends SRSpiderEntity, M extends SRSpiderModel<T>> extends SRSpiderEyes<T, M> {
    public SprayPoisonSpiderEyes(FeatureRendererContext<T, M> featureRendererContext) {
        super(featureRendererContext);
    }
}