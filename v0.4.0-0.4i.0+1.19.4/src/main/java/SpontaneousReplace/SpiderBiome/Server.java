package SpontaneousReplace.SpiderBiome;

/**
 * <b style="color:FFC800"><font size="+2">Server: 蜘蛛群系服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册蜘蛛群系服务端内容的类</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2023/1/8 15:33
 */
public abstract class Server {
    public static void register() {
        SpontaneousReplace.SpiderBiome.Blocks.Server.register();
        SpontaneousReplace.SpiderBiome.Mobs.Server.register();
        SpontaneousReplace.SpiderBiome.Items.Server.register();
        SpontaneousReplace.SpiderBiome.Equipments.Server.register();
        temporary_spider_spawn.register();
    }
}
