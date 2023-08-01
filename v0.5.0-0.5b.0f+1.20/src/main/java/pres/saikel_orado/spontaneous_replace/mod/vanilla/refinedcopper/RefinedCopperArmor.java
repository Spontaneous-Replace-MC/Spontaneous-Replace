package pres.saikel_orado.spontaneous_replace.mod.vanilla.refinedcopper;

import net.minecraft.recipe.Ingredient;
import pres.saikel_orado.spontaneous_replace.mod.util.SRArmor;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RefinedCopper.EQUIP_REFINED_COPPER;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RefinedCopper.REFINED_COPPER_INGOT;

/**
 * <b style="color:FFC800"><font size="+2">RefinedCopperArmor：精铜盔甲类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">基础属性在锁链盔甲与铁制盔甲之间，但耐久小于二者，但是拥有少量的盔甲韧性和只比金属性低地附魔等级</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/5
 */
public final class RefinedCopperArmor extends SRArmor {
    public static final RefinedCopperArmor COPPER_ARMOR = new RefinedCopperArmor();
    public static final String ID = "refined_copper";
    public static final int DURABILITY = 12;
    public static final int ENCHANTABILITY = 20;
    public static final float TOUGHNESS = 0.5F;
    public static final float KNOCK_BACK_RESISTANCE = 0;

    /**
     * <p>构建盔甲类</p>
     * <p></p>
     * <p>盔甲 id&emsp;&emsp;&emsp;&emsp;{@value ID}</p>
     * <p>修复材料&emsp;&emsp;&emsp;&emsp;精铜锭</p>
     * <p>装备音效&emsp;&emsp;&emsp;&emsp;精铜盔甲</p>
     * <p>盔甲耐久系数&emsp;&emsp;{@value DURABILITY}</p>
     * <p>盔甲护甲值系数&emsp;13 | 2 5 4 2</p>
     * <p>附魔能力&emsp;&emsp;&emsp;&emsp;{@value ENCHANTABILITY}</p>
     * <p>盔甲韧性&emsp;&emsp;&emsp;&emsp;{@value TOUGHNESS}</p>
     * <p>击退抗性&emsp;&emsp;&emsp;&emsp;{@value KNOCK_BACK_RESISTANCE}</p>
     */
    private RefinedCopperArmor() {
        super(ID, Ingredient.ofItems(REFINED_COPPER_INGOT),
                EQUIP_REFINED_COPPER,
                DURABILITY,
                getProtectionAmount(),
                ENCHANTABILITY,
                TOUGHNESS,
                KNOCK_BACK_RESISTANCE);
    }

    /**
     * 盔甲护甲值系数
     *
     * @return 13 | 2 5 4 2
     */
    public static int[] getProtectionAmount() {
        return new int[]{2, 5, 4, 2};
    }
}
