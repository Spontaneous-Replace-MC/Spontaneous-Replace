package SpontaneousReplace.Generic;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * <b style="color:FFC800"><font size="+2">SRData：自然更替数据</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">负责模组大部分内容可能用到的特殊数据</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 7.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class SRData {
    /**
     * 禁止实例化 SRData 模组数据类
     */
    private SRData() {
        throw new Error("请检查是否实例化 SRData 模组数据类");
    }

    public static final String MOD_ID = "spontaneous_replace";
    public static final String MOD_OFFICIAL_WEB = "https://www.patreon.com/GameGeek_Saikel";
    public static final String MOD_SUPPORT_WEB = "https://www.patreon.com/GameGeek_Saikel";
    public static final String MOD_SUPPORT_WEB_OF_CN = "https://afdian.net/a/GameGeek_Saikel";
    public static final Item SR_ICON = new Item(new FabricItemSettings());
    public static final int TICK = 20;
    public static final float POS_SHIFTING = 0.5F;
    public static final float PROJECTILE_BOX = 0.25F;
    public static final int PROJECTILE_RANGE = 4;
    public static final int PROJECTILE_UPDATE_RATE = 20;

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
     * 获取刷怪蛋 ID
     *
     * @param id 生物 ID
     * @return 刷怪蛋 ID
     */
    public static String getEgg(String id) {
        return id + "_spawn_egg";
    }

    /**
     * 注册特殊数据
     */
    public static void register() {
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "sr_icon"), SR_ICON);
    }
}