package SpontaneousReplace.VanillaExtensionContent;

/**
 * <b style="color:FFC800"><font size="+2">Register：原版拓展内容服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册模组原版拓展内容的服务端</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Register {
    public static void register() {
        SpontaneousReplace.VanillaExtensionContent.RefinedCopper.Register.register();
        SpontaneousReplace.VanillaExtensionContent.CuFeAlloy.Register.register();
        SpontaneousReplace.VanillaExtensionContent.AuCuAlloy.Register.register();
        SpontaneousReplace.VanillaExtensionContent.Steel.Register.register();
    }
}
