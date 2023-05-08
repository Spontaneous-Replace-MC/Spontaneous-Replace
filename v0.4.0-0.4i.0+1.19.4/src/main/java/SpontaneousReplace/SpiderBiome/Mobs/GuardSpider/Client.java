package SpontaneousReplace.SpiderBiome.Mobs.GuardSpider;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.SpiderBiome.Mobs.GuardSpider.Data.ID;

/**
 * <b style="color:FFC800"><font size="+2">Client：蜘蛛卫兵客户端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">蜘蛛卫兵的客户端内容注册</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2022/12/26 ~ 2023/01/05
 */
@Environment(EnvType.CLIENT)
public abstract class Client {
    public static final EntityModelLayer GUARD_SPIDER_LAYER = new EntityModelLayer(new Identifier(MOD_ID, ID), "main");

    public static void register() {
        EntityRendererRegistry.register(Server.GUARD_SPIDER, Renderer::new);
        EntityModelLayerRegistry.registerModelLayer(GUARD_SPIDER_LAYER, Model::getTexturedModelData);
    }
}
