package SpontaneousReplace.VanillaExtension;

/**
 * <b style="color:FFC800"><font size="+2">Server：原版拓展服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册模组原版拓展的服务端</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Server {
    public static void register() {
        SpontaneousReplace.VanillaExtension.RefinedCopper.Server.register();
        SpontaneousReplace.VanillaExtension.CuFeAlloy.Server.register();
        SpontaneousReplace.VanillaExtension.AuCuAlloy.Server.register();
        SpontaneousReplace.VanillaExtension.Steel.Server.register();
        SpontaneousReplace.VanillaExtension.RangedRelated.Server.register();
    }
}
