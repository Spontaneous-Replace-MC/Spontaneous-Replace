package SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon;

/**
 * <b style="color:FFC800"><font size="+2">Server：基础远程武器服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册基础远程武器的服务端</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/22 14:12
 */
public abstract class Server {
    public static void register() {
        Slingshot.register();
        RecurveBow.register();
        Arbalest.register();
    }
}
