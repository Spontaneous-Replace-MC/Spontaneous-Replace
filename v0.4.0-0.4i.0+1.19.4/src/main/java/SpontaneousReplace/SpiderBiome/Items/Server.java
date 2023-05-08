package SpontaneousReplace.SpiderBiome.Items;

/**
 * <b style="color:FFC800"><font size="+2">Server：蜘蛛物品服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册蜘蛛物品的服务端内容</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/3/25 15:34
 */
public abstract class Server {
    public static void register() {
        Drops.register();
        Ingredients.register();
    }
}
