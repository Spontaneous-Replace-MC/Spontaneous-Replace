package SpontaneousReplace.VanillaExtension.RangedRelated;

import SpontaneousReplace.VanillaExtension.RangedRelated.DefensiveArmor.ArrowproofVest;

/**
 * <b style="color:FFC800"><font size="+2">Server：远程系列服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册模组远程系列的服务端</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/19 16:25
 */
public abstract class Server {
    public static void register() {
        SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon.Stoneball.Server.register();
        SpontaneousReplace.VanillaExtension.RangedRelated.EnhancedWeapon.FullPowerSteelArrow.Server.register();
        SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon.Server.register();
        SpontaneousReplace.VanillaExtension.RangedRelated.EnhancedWeapon.Server.register();
        ArrowproofVest.register();
    }
}
