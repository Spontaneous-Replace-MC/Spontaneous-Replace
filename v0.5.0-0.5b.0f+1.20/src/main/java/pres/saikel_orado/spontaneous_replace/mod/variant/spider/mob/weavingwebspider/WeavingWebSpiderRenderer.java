package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.weavingwebspider;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderRenderer;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.SR_ID;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.Client.WEAVING_WEB_SPIDER_LAYER;
import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.weavingwebspider.WeavingWebSpiderData.ID;
import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.weavingwebspider.WeavingWebSpiderData.MODEL_SHADOW;

/**
 * <b style="color:FFC800"><font size="+2">WeavingWebSpiderRenderer：织网蜘蛛渲染类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">织网蜘蛛的所有渲染设置</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/1/18 19:42
 */
@Environment(EnvType.CLIENT)
public class WeavingWebSpiderRenderer<T extends WeavingWebSpiderEntity> extends SRSpiderRenderer<T> {
    /**
     * 构建渲染
     */
    public WeavingWebSpiderRenderer(EntityRendererFactory.Context context) {
        super(context, new WeavingWebSpiderModel<>(context.getPart(WEAVING_WEB_SPIDER_LAYER)), MODEL_SHADOW);
        this.addFeature(new WeavingWebSpiderEyes<>(this));
    }


    @Override
    public Identifier getTexture(T spiderEntity) {
        return new Identifier(SR_ID, "textures/entity/spider/" + ID + ".png");
    }
}
