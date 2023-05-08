package SpontaneousReplace;

import SpontaneousReplace.Generic.SRData;

/**
 * <b style="color:FFC800"><font size="+2">ServerRegistry：自然更替服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册模组服务端内容的类</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 5.0
 * | 创建于 2022/11/14 ~ 2022/12/8
 */
public abstract class ServerRegistry {
    public static void register() {
        SRData.register();
        SpontaneousReplace.VanillaExtension.Server.register();
        SpontaneousReplace.SpiderBiome.Server.register();
        SpontaneousReplace.RecipeItem.Server.register();
        SpontaneousReplace.Effects.Server.register();
    }
}
