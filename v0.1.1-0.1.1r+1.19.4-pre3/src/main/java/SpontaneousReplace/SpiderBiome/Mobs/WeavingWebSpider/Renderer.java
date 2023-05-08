package SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider;

import SpontaneousReplace.SpiderBiome.Generic.SRSpider.SRSpiderRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider.Data.ID;
import static SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider.Data.MODEL_SHADOW;

/**
 * <b style="color:FFC800"><font size="+2">Renderer：织网蜘蛛渲染类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">织网蜘蛛的所有渲染设置</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/1/18 19:42
 */
@Environment(EnvType.CLIENT)
public class Renderer<T extends Entity> extends SRSpiderRenderer<T> {
    /**
     * 构建渲染
     */
    public Renderer(EntityRendererFactory.Context context) {
        super(context, new Model<>(context.getPart(Client.WEAVING_WEB_SPIDER_LAYER)), MODEL_SHADOW);
        this.addFeature(new Eyes<>(this));
    }


    @Override
    public Identifier getTexture(T spiderEntity) {
        return new Identifier(MOD_ID, "textures/entity/spider/" + ID + ".png");
    }
}
