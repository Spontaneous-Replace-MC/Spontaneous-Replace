package pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.armor;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import pres.saikel_orado.spontaneous_replace.mod.util.SRArmor;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RangedRelated.EQUIP_ARROWPROOF_VEST;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.COMPOSITE_FABRIC;

/**
 * <b style="color:FFC800"><font size="+2">ArrowproofVest：防箭衣</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加一种特殊的投掷物防御装备</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/4/19 16:46
 */
public class ArrowproofVest extends SRArmor {
    public static final ArrowproofVest ARROWPROOF_VEST_MATERIAL = new ArrowproofVest();
    public static final int DEFAULT_COLOR = 0xFFFFB3;
    public static final String ID = "arrowproof_vest";
    public static final int DURABILITY = 10;
    public static final int ENCHANTABILITY = 9;
    public static final float TOUGHNESS = 5;
    public static final float KNOCK_BACK_RESISTANCE = 0;

    /**
     * <p>构建防箭衣</p>
     * <p></p>
     * <p>盔甲 id&emsp;&emsp;&emsp;&emsp;{@value ID}</p>
     * <p>修复材料&emsp;&emsp;&emsp;&emsp;复合面料</p>
     * <p>装备音效&emsp;&emsp;&emsp;&emsp;防箭衣</p>
     * <p>盔甲耐久系数&emsp;&emsp;{@value DURABILITY}</p>
     * <p>盔甲护甲值系数&emsp;7 | 1 3 2 1</p>
     * <p>附魔能力&emsp;&emsp;&emsp;&emsp;{@value ENCHANTABILITY}</p>
     * <p>盔甲韧性&emsp;&emsp;&emsp;&emsp;{@value TOUGHNESS}</p>
     * <p>击退抗性&emsp;&emsp;&emsp;&emsp;{@value KNOCK_BACK_RESISTANCE}</p>
     */
    private ArrowproofVest() {
        super(ID, Ingredient.ofItems(COMPOSITE_FABRIC),
                EQUIP_ARROWPROOF_VEST,
                DURABILITY,
                getProtectionAmount(),
                ENCHANTABILITY,
                TOUGHNESS,
                KNOCK_BACK_RESISTANCE);
    }

    /**
     * 盔甲护甲值系数
     *
     * @return 7 | 1 3 2 1
     */
    public static int[] getProtectionAmount() {
        return new int[]{1, 3, 2, 1};
    }

    @Override
    public ArmorItem createChestPlate(Item.Settings settings) {
        return new ArrowproofVestItem(this, ArmorItem.Type.CHESTPLATE, settings);
    }

    private static final class ArrowproofVestItem extends DyeableArmorItem {
        /**
         * 构建防箭衣
         */
        public ArrowproofVestItem(ArmorMaterial armorMaterial, Type type, Settings settings) {
            super(armorMaterial, type, settings);
        }

        /**
         * 设置默认染色
         */
        @Override
        public int getColor(ItemStack stack) {
            int color = super.getColor(stack);
            return color == DEFAULT_COLOR ? ArrowproofVest.DEFAULT_COLOR : color;
        }
    }
}
