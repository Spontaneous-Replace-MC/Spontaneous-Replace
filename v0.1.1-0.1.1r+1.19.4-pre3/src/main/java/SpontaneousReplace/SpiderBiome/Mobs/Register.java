package SpontaneousReplace.SpiderBiome.Mobs;

/**
 * <b style="color:FFC800"><font size="+2">Register：蜘蛛生物服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册蜘蛛生物的服务端内容</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2022/12/26 ~ 2023/01/05
 */
public abstract class Register {
    public static void register() {
        SpontaneousReplace.SpiderBiome.Mobs.GuardSpider.Register.register();
        SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Register.register();
        SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider.Register.register();
    }
}
