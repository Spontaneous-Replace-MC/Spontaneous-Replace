package SpontaneousReplace.SpiderBiome.Mobs;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * <b style="color:FFC800"><font size="+2">Client：蜘蛛生物客户端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册蜘蛛生物的客户端内容</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2022/12/26 ~ 2023/01/05
 */
@Environment(EnvType.CLIENT)
public class Client {
    public static void register() {
        SpontaneousReplace.SpiderBiome.Mobs.GuardSpider.Client.register();
        SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Client.register();
        SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider.Client.register();
    }
}
