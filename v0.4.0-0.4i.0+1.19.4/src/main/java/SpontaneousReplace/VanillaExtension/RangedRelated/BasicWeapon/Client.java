package SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

import static SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon.Stoneball.Server.STONEBALL_ENTITY;

/**
 * <b style="color:FFC800"><font size="+2">Client：基础远程武器客户端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册基础远程武器客户端内容的类</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/24 22:41
 */
@Environment(EnvType.CLIENT)
public abstract class Client {
    public static void register() {
        // 注册石弹渲染
        EntityRendererRegistry.register(STONEBALL_ENTITY, FlyingItemEntityRenderer::new);
    }
}
