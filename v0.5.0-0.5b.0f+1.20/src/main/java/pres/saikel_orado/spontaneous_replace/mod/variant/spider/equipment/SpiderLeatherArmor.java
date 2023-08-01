package pres.saikel_orado.spontaneous_replace.mod.variant.spider.equipment;

import net.minecraft.recipe.Ingredient;
import pres.saikel_orado.spontaneous_replace.mod.util.SRArmor;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.EQUIP_SPIDER_LEATHER_ARMOR;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.SPIDER_LEATHER;

/**
 * <b style="color:FFC800"><font size="+2">SpiderLeatherArmor：蜘蛛护皮套装</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">蜘蛛护皮套装</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/3/26 18:19
 */
public final class SpiderLeatherArmor extends SRArmor {
    public static final SpiderLeatherArmor SPIDER_LEATHER_ARMOR = new SpiderLeatherArmor();
    public static final String ID = "spider_leather_tunic";
    public static final int DURABILITY = 6;
    public static final int ENCHANTABILITY = 15;
    public static final float TOUGHNESS = 0;
    public static final float KNOCK_BACK_RESISTANCE = 0;

    /**
     * <p>构建盔甲类</p>
     * <p></p>
     * <p>盔甲 id&emsp;&emsp;&emsp;&emsp;{@value ID}</p>
     * <p>修复材料&emsp;&emsp;&emsp;&emsp;蜘蛛护皮</p>
     * <p>装备音效&emsp;&emsp;&emsp;&emsp;蜘蛛皮甲</p>
     * <p>盔甲耐久系数&emsp;&emsp;{@value DURABILITY}</p>
     * <p>盔甲护甲值系数&emsp;10 | 2 4 3 1</p>
     * <p>附魔能力&emsp;&emsp;&emsp;&emsp;{@value ENCHANTABILITY}</p>
     * <p>盔甲韧性&emsp;&emsp;&emsp;&emsp;{@value TOUGHNESS}</p>
     * <p>击退抗性&emsp;&emsp;&emsp;&emsp;{@value KNOCK_BACK_RESISTANCE}</p>
     */
    private SpiderLeatherArmor() {
        super(ID, Ingredient.ofItems(SPIDER_LEATHER),
                EQUIP_SPIDER_LEATHER_ARMOR,
                DURABILITY,
                getProtectionAmount(),
                ENCHANTABILITY,
                TOUGHNESS,
                KNOCK_BACK_RESISTANCE);
    }

    /**
     * 盔甲护甲值系数
     *
     * @return 10 | 2 4 3 1
     */
    public static int[] getProtectionAmount() {
        return new int[]{2, 4, 3, 1};
    }
}
