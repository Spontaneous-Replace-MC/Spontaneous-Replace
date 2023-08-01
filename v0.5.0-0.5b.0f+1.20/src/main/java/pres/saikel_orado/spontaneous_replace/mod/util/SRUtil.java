package pres.saikel_orado.spontaneous_replace.mod.util;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.TICK;

/**
 * <b style="color:FFC800"><font size="+2">SRUtil：自然更替实用工具类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">将常用的 MC 操作整理成函数使用</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/24 15:10
 */
public final class SRUtil {
    /**
     * 获取游戏刻
     *
     * @param seconds 所需秒数
     * @return Minecraft 游戏刻
     */
    public static int getTick(double seconds) {
        return (int) (seconds * TICK);
    }

    /**
     * 获取秒数
     *
     * @param ticks 所需秒数
     * @return Minecraft 游戏刻
     */
    public static float getSeconds(double ticks) {
        return (float) (ticks / TICK);
    }

    /**
     * 获取刷怪蛋 ID
     *
     * @param id 生物 ID
     * @return 刷怪蛋 ID
     */
    public static String getEgg(String id) {
        return id + "_spawn_egg";
    }

    /**
     * 获取饱和度比值
     *
     * @param saturation 回复饱和度
     * @return 回复饱和度对于总饱和度的 0 ~ 1 比值
     */
    public static float getSaturationRatio(double saturation) {
        return (int) (saturation / 20);
    }
}
