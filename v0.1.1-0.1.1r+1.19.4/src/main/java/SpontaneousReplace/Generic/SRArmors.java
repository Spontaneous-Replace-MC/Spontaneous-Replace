package SpontaneousReplace.Generic;

import net.minecraft.item.ArmorItem;

/**
 * <b style="color:FFC800"><font size="+2">SRArmors：自然更替通用盔甲类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">辅助盔甲的创建的数据直观和清晰</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/2/17 10:37
 */
public abstract class SRArmors {
    /**
     * 禁止实例化 SRArmors 盔甲辅助类
     */
    private SRArmors() {
        throw new Error("请检查是否实例化 SRArmors 盔甲辅助类");
    }

    /**
     * 基础耐久基数
     */
    public static final int[] DURABILITY_BASE = {11, 16, 15, 13};

    /**
     * 获取装备耐久值
     *
     * @param type        装备槽
     * @param coefficient 耐久系数
     * @return 实际耐久值
     */
    public static int getDurability(ArmorItem.Type type, int coefficient) {
        return DURABILITY_BASE[type.getEquipmentSlot().getEntitySlotId()] * coefficient;
    }

    /**
     * 获取装备护甲值
     *
     * @param type 装备槽
     * @param head 头盔
     * @param body 胸甲
     * @param legs 护腿
     * @param feet 靴子
     * @return 实际护甲值
     */
    public static int getProtection(ArmorItem.Type type, int head, int body, int legs, int feet) {
        return new int[]{feet, legs, body, head}[type.getEquipmentSlot().getEntitySlotId()];
    }

    /**
     * 获取击退抗性
     *
     * @param kr 击退抗性
     * @return 击退抗性
     */
    public static float getKnockBackResistance(float kr) {
        return kr / 10;
    }
}
