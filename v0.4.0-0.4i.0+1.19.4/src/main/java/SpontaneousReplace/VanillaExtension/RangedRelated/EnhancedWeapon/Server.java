package SpontaneousReplace.VanillaExtension.RangedRelated.EnhancedWeapon;

/**
 * <b style="color:FFC800"><font size="+2">Server：增强远程武器服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册增强远程武器服务端内容的类</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/27 17:09
 */
public abstract class Server {
    public static void register() {
        CompoundBow.register();
        JuGerRepeatingCrossbow.register();
        MarksCrossbow.register();
    }
}
