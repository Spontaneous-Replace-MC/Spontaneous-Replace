package SpontaneousReplace.VanillaExtensionContent.AuCuAlloy;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.recipe.Ingredient;

import static SpontaneousReplace.VanillaExtensionContent.AuCuAlloy.Items.AUCU_ALLOY_INGOT;
import static SpontaneousReplace.VanillaExtensionContent.AuCuAlloy.Tool.*;

/**
 * <b style="color:FFC800"><font size="+2">ToolMaterial：金铜工具材料类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加金铜工具材料与属性</font></b></i></p>
 * <p><i><b style="color:FFC800"><font size="+1">基础属性在石制工具左右，但是拥有极致的附魔等级，附魔等级甚至比金高 5 点</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public class ToolMaterial implements net.minecraft.item.ToolMaterial {
    /**
     * 获取工具耐久
     *
     * @return 工具耐久
     */
    @Override
    public int getDurability() {
        return DURABILITY;
    }

    /**
     * 获取开采速度倍率
     *
     * @return 开采速度倍率
     */
    @Override
    public float getMiningSpeedMultiplier() {
        return MINING_SPEED_MULTIPLIER;
    }

    /**
     * 获取材料增伤
     *
     * @return 材料增伤
     */
    @Override
    public float getAttackDamage() {
        return MATERIAL_DAMAGE;
    }

    /**
     * 获取开采等级
     *
     * @return 开采等级: 石
     */
    @Override
    public int getMiningLevel() {
        return MiningLevels.STONE;
    }

    /**
     * 获取附魔等能力
     *
     * @return 附魔能力
     */
    @Override
    public int getEnchantability() {
        return ENCHANTABILITY;
    }

    /**
     * 获取修复物品
     *
     * @return 修复物品: 金铜锭
     */
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(AUCU_ALLOY_INGOT);
    }
}
