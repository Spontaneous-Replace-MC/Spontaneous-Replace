package SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider;

import SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Toxin.ToxinModel;
import SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Toxin.ToxinRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.SpitParticle;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Data.ID;
import static SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Server.TOXIN;
import static SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Server.TOXIN_PARTICLE;

/**
 * <b style="color:FFC800"><font size="+2">Client：喷吐毒蛛客户端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">喷吐毒蛛的客户端内容注册</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/01/06
 */
@Environment(EnvType.CLIENT)
public class Client {
    /**
     * 喷吐毒蛛
     */
    public static final EntityModelLayer SPRAY_POISON_SPIDER_LAYER = new EntityModelLayer(new Identifier(MOD_ID, ID), "main");
    /**
     * 毒素
     */
    public static final EntityModelLayer TOXIN_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "spider_toxin_projectile"), "main");

    public static void register() {
        // 注册喷吐毒蛛
        EntityRendererRegistry.register(Server.SPRAY_POISON_SPIDER, Renderer::new);
        EntityModelLayerRegistry.registerModelLayer(SPRAY_POISON_SPIDER_LAYER, Model::getTexturedModelData);
        // 注册弹射物毒素
        EntityRendererRegistry.register(TOXIN, ToxinRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(TOXIN_LAYER, ToxinModel::getTexturedModelData);
        ParticleFactoryRegistry.getInstance().register(TOXIN_PARTICLE, SpitParticle.Factory::new);
    }
}
