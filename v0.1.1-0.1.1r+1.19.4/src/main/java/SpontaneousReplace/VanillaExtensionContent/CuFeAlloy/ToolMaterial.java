package SpontaneousReplace.VanillaExtensionContent.CuFeAlloy;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.recipe.Ingredient;

import static SpontaneousReplace.VanillaExtensionContent.CuFeAlloy.Items.CUFE_ALLOY_INGOT;
import static SpontaneousReplace.VanillaExtensionContent.CuFeAlloy.Tool.*;

/**
 * <b style="color:FFC800"><font size="+2">ToolMaterial：铜铁工具材料类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加铜铁工具材料与属性</font></b></i></p>
 * <p><i><b style="color:FFC800"><font size="+1">基础属性只比铁制工具略高，但是拥有精铜工具的附魔等级</font></b></i></p>
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
     * @return 开采等级: 铁
     */
    @Override
    public int getMiningLevel() {
        return MiningLevels.IRON;
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
     * @return 修复物品: 铜铁锭
     */
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(CUFE_ALLOY_INGOT);
    }
}
