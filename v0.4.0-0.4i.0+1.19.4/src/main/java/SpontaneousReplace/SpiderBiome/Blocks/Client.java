package SpontaneousReplace.SpiderBiome.Blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * <b style="color:FFC800"><font size="+2">Client：蜘蛛群系方块客户端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册蜘蛛群系方块的客户端内容</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/1/24 12:03
 */
@Environment(EnvType.CLIENT)
public abstract class Client {
    public static void register() {
        SpontaneousReplace.SpiderBiome.Blocks.StickyCompactCobweb.Client.register();
    }
}
