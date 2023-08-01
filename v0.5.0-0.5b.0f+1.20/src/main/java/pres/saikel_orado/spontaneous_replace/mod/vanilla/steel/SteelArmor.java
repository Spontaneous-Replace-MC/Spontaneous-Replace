package pres.saikel_orado.spontaneous_replace.mod.vanilla.steel;

import net.minecraft.recipe.Ingredient;
import pres.saikel_orado.spontaneous_replace.mod.util.SRArmor;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.Steel.EQUIP_STEEL;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.Steel.STEEL_INGOT;

/**
 * <b style="color:FFC800"><font size="+2">SteelArmor：钢制盔甲类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">基础属性只比钻石盔甲略低，但是拥有下届合金的附魔等级和少量的击退抗性</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/5
 */
public final class SteelArmor extends SRArmor {
    public static final SteelArmor STEEL_ARMOR = new SteelArmor();
    public static final String ID = "steel";
    public static final int DURABILITY = 28;
    public static final int ENCHANTABILITY = 15;
    public static final float TOUGHNESS = 2;
    public static final float KNOCK_BACK_RESISTANCE = 0.5F;

    /**
     * <p>构建盔甲类</p>
     * <p></p>
     * <p>盔甲 id&emsp;&emsp;&emsp;&emsp;{@value ID}</p>
     * <p>修复材料&emsp;&emsp;&emsp;&emsp;钢锭</p>
     * <p>装备音效&emsp;&emsp;&emsp;&emsp;钢制盔甲</p>
     * <p>盔甲耐久系数&emsp;&emsp;{@value DURABILITY}</p>
     * <p>盔甲护甲值系数&emsp;18 | 3 7 6 2</p>
     * <p>附魔能力&emsp;&emsp;&emsp;&emsp;{@value ENCHANTABILITY}</p>
     * <p>盔甲韧性&emsp;&emsp;&emsp;&emsp;{@value TOUGHNESS}</p>
     * <p>击退抗性&emsp;&emsp;&emsp;&emsp;{@value KNOCK_BACK_RESISTANCE}</p>
     */
    private SteelArmor() {
        super(ID, Ingredient.ofItems(STEEL_INGOT),
                EQUIP_STEEL,
                DURABILITY,
                getProtectionAmount(),
                ENCHANTABILITY,
                TOUGHNESS,
                KNOCK_BACK_RESISTANCE);
    }

    /**
     * 盔甲护甲值系数
     *
     * @return 18 | 3 7 6 2
     */
    public static int[] getProtectionAmount() {
        return new int[]{3, 7, 6, 2};
    }
}
