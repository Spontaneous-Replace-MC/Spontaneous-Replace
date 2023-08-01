package pres.saikel_orado.spontaneous_replace.mod.vanilla.aucualloy;

import net.minecraft.recipe.Ingredient;
import pres.saikel_orado.spontaneous_replace.mod.util.SRArmor;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.AuCuAlloy.AUCU_ALLOY_INGOT;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.AuCuAlloy.EQUIP_AUCU_ALLOY;

/**
 * <b style="color:FFC800"><font size="+2">AuCuArmor：金铜盔甲类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">基础属性只比金制盔甲略高，但是拥有极致的附魔等级，附魔等级甚至比金高 5 点</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/5
 */
public final class AuCuArmor extends SRArmor {
    public static final AuCuArmor AUCU_ARMOR = new AuCuArmor();
    public static final String ID = "aucu_alloy";
    public static final int DURABILITY = 10;
    public static final int ENCHANTABILITY = 30;
    public static final float TOUGHNESS = 0;
    public static final float KNOCK_BACK_RESISTANCE = 0;

    /**
     * <p>构建盔甲类</p>
     * <p></p>
     * <p>盔甲 id&emsp;&emsp;&emsp;&emsp;{@value ID}</p>
     * <p>修复材料&emsp;&emsp;&emsp;&emsp;金铜锭</p>
     * <p>装备音效&emsp;&emsp;&emsp;&emsp;金铜盔甲</p>
     * <p>盔甲耐久系数&emsp;&emsp;{@value DURABILITY}</p>
     * <p>盔甲护甲值系数&emsp;12 | 2 5 4 1</p>
     * <p>附魔能力&emsp;&emsp;&emsp;&emsp;{@value ENCHANTABILITY}</p>
     * <p>盔甲韧性&emsp;&emsp;&emsp;&emsp;{@value TOUGHNESS}</p>
     * <p>击退抗性&emsp;&emsp;&emsp;&emsp;{@value KNOCK_BACK_RESISTANCE}</p>
     */
    private AuCuArmor() {
        super(ID, Ingredient.ofItems(AUCU_ALLOY_INGOT),
                EQUIP_AUCU_ALLOY,
                DURABILITY,
                getProtectionAmount(),
                ENCHANTABILITY,
                TOUGHNESS,
                KNOCK_BACK_RESISTANCE);
    }

    /**
     * 盔甲护甲值系数
     *
     * @return 12 | 2 5 4 1
     */
    public static int[] getProtectionAmount() {
        return new int[]{2, 5, 4, 1};
    }
}
