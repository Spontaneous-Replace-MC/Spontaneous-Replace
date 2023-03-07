package SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider.Data.ID;

/**
 * <b style="color:FFC800"><font size="+2">Client：织网蜘蛛客户端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">织网蜘蛛的客户端内容注册</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/1/18 19:40
 */
@Environment(value = EnvType.CLIENT)
public abstract class Client {
    public static final EntityModelLayer WEAVING_WEB_SPIDER_LAYER = new EntityModelLayer(new Identifier(MOD_ID, ID), "main");

    public static void register() {
        EntityRendererRegistry.register(Register.WEAVING_WEB_SPIDER, Renderer::new);
        EntityModelLayerRegistry.registerModelLayer(WEAVING_WEB_SPIDER_LAYER, Model::getTexturedModelData);
    }
}
