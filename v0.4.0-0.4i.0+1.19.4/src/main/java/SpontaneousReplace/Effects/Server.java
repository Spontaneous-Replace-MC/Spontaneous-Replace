package SpontaneousReplace.Effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;

/**
 * <b style="color:FFC800"><font size="+2">Server：状态效果服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册状态效果服务端内容的类</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/12 12:16
 */
public abstract class Server {
    /**
     * 蜘蛛伪装状态效果
     */
    public static final StatusEffect SPIDER_CAMOUFLAGE = new SpiderCamouflage();

    public static void register() {
        // 注册蜘蛛伪装状态效果
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "spider_camouflage"), SPIDER_CAMOUFLAGE);
    }
}
