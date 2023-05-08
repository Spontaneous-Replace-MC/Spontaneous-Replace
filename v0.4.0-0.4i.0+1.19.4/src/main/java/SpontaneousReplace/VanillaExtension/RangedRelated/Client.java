package SpontaneousReplace.VanillaExtension.RangedRelated;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * <b style="color:FFC800"><font size="+2">Client：远程相关客户端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册远程相关客户端内容的类</font></b></i></p>
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
        SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon.Client.register();
        SpontaneousReplace.VanillaExtension.RangedRelated.EnhancedWeapon.Client.register();
    }
}
