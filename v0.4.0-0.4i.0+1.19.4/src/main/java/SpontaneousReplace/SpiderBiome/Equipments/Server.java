package SpontaneousReplace.SpiderBiome.Equipments;

/**
 * <b style="color:FFC800"><font size="+2">Server：蜘蛛装备服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册蜘蛛装备的服务端内容</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/3/26 16:44
 */
public abstract class Server {
    public static void register() {
        // 注册蜘蛛皮甲
        SpiderLeatherArmor.register();
    }
}
