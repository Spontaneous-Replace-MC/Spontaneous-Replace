package SpontaneousReplace.Generic;

/**
 * <b style="color:FFC800"><font size="+2">SRTools：自然更替通用工具类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">辅助工具的创建的数据直观和清晰</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/2/17 10:37
 */
public abstract class SRTools {
    /**
     * 禁止实例化 SRTools 工具辅助类
     */
    private SRTools() {
        throw new Error("请检查是否实例化 SRTools 工具辅助类");
    }

    /**
     * 基础伤害
     */
    public static final int BASE_DAMAGE = 1;

    /**
     * 基础攻速
     */
    public static final int BASE_SPEED = 4;

    /**
     * 材料增伤
     */
    public static float MATERIAL_DAMAGE = 0;

    /**
     * 获取材料增伤
     *
     * @param damage 增伤
     * @return 增伤
     */
    public static float getMaterialDamage(float damage) {
        return MATERIAL_DAMAGE = damage;
    }

    /**
     * 获取伤害
     *
     * @param damage 攻击伤害
     * @return 实际设置数据
     */
    public static float getDamage(float damage) {
        return damage - BASE_DAMAGE - MATERIAL_DAMAGE;
    }

    /**
     * 获取攻速
     *
     * @param speed 攻击速度
     * @return 实际设置攻速
     */
    public static float getSpeed(float speed) {
        return speed - BASE_SPEED;
    }
}
