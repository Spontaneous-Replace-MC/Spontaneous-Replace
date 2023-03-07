package SpontaneousReplace.SpiderBiome.Block;

/**
 * <b style="color:FFC800"><font size="+2">Register：蜘蛛群系方块服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册蜘蛛群系方块的服务端内容</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/1/24 12:03
 */
public abstract class Register {
    public static void register() {
        SpontaneousReplace.SpiderBiome.Block.StickyCompactCobweb.Register.register();
        SpontaneousReplace.SpiderBiome.Block.SpiderChrysalis.Register.register();
        SpontaneousReplace.SpiderBiome.Block.SpiderEggCocoon.Register.register();
    }
}