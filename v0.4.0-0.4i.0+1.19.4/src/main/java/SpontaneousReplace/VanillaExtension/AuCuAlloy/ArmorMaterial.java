package SpontaneousReplace.VanillaExtension.AuCuAlloy;

import SpontaneousReplace.Generic.SRArmors;
import net.minecraft.item.ArmorItem;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

import static SpontaneousReplace.VanillaExtension.AuCuAlloy.Armor.*;
import static SpontaneousReplace.VanillaExtension.AuCuAlloy.Items.AUCU_ALLOY_INGOT;

/**
 * <b style="color:FFC800"><font size="+2">ArmorMaterial：金铜盔甲材料类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加金铜盔甲材料与属性</font></b></i></p>
 * <p><i><b style="color:FFC800"><font size="+1">基础属性只比金制盔甲略高，但是拥有极致的附魔等级，附魔等级甚至比金高 5 点</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public class ArmorMaterial implements net.minecraft.item.ArmorMaterial {
    /**
     * 获取盔甲耐久
     *
     * @param type 装备槽
     * @return 盔甲耐久值
     */
    @Override
    public int getDurability(ArmorItem.Type type) {
        return SRArmors.getDurability(type, DURABILITY);
    }

    /**
     * 获取盔甲护甲值
     *
     * @param type 装备槽
     * @return 盔甲护甲值
     */
    @Override
    public int getProtection(ArmorItem.Type type) {
        return SRArmors.getProtection(type,
                ProtectionAmount.HEAD,
                ProtectionAmount.BODY,
                ProtectionAmount.LEGS,
                ProtectionAmount.FEET);
    }

    /**
     * 获取附魔能力
     *
     * @return 附魔能力
     */
    @Override
    public int getEnchantability() {
        return ENCHANTABILITY;
    }

    /**
     * 获取盔甲音效
     *
     * @return 盔甲音效: 金铜盔甲
     */
    @Override
    public SoundEvent getEquipSound() {
        return EQUIP_AUCU_ALLOY;
    }

    /**
     * 获取修复材料
     *
     * @return 修复材料: 金铜锭
     */
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(AUCU_ALLOY_INGOT);
    }

    /**
     * 获取盔甲名称
     *
     * @return 盔甲名称: aucu_alloy
     */
    @Override
    public String getName() {
        return "aucu_alloy";
    }

    /**
     * 获取盔甲韧性
     *
     * @return 盔甲韧性
     */
    @Override
    public float getToughness() {
        return TOUGHNESS;
    }

    /**
     * 获取击退抗性
     *
     * @return 击退抗性
     */
    @Override
    public float getKnockbackResistance() {
        return SRArmors.getKnockBackResistance(KNOCK_BACK_RESISTANCE);
    }
}
