package SpontaneousReplace.SpiderBiome.Block.StickyCompactCobweb;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static SpontaneousReplace.SpiderBiome.Block.StickyCompactCobweb.Register.STICKY_COMPACT_COBWEB;

/**
 * <b style="color:FFC800"><font size="+2">Client：粘密蛛网客户端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">粘密蛛网的客户端内容注册</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/1/25 15:36
 */
@Environment(EnvType.CLIENT)
public abstract class Client {
    public static void register() {
        BlockRenderLayerMap.INSTANCE.putBlock(STICKY_COMPACT_COBWEB, RenderLayer.getCutout());
    }
}
