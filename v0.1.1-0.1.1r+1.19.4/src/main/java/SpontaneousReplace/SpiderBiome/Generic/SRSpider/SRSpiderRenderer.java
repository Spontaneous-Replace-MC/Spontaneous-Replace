package SpontaneousReplace.SpiderBiome.Generic.SRSpider;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;

/**
 * <b style="color:FFC800"><font size="+2">SRSpiderRenderer：《自然更替》蜘蛛渲染类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">《自然更替》蜘蛛的基础渲染设置</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/2/18 15:23
 */
@Environment(EnvType.CLIENT)
public abstract class SRSpiderRenderer<Spider extends SRSpiderEntity>
        extends MobEntityRenderer<Spider, SRSpiderModel<Spider>> {
    /**
     * 注册渲染器
     *
     * @param context 实体渲染器工厂
     * @param shadow  阴影半径
     */
    public SRSpiderRenderer(EntityRendererFactory.Context context, SRSpiderModel<Spider> model, float shadow) {
        super(context, model, shadow);
    }
}