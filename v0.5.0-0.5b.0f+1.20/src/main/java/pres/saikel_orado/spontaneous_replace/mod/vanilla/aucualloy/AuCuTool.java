package pres.saikel_orado.spontaneous_replace.mod.vanilla.aucualloy;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.recipe.Ingredient;
import pres.saikel_orado.spontaneous_replace.mod.util.SRTool;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.AuCuAlloy.AUCU_ALLOY_INGOT;

/**
 * <b style="color:FFC800"><font size="+2">AuCuTool：金铜工具类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">基础属性在石制工具左右，但是拥有极致的附魔等级，附魔等级甚至比金高</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/5
 */
public final class AuCuTool extends SRTool {
    public static final AuCuTool AUCU_TOOL = new AuCuTool();
    public static final float MATERIAL_DAMAGE = 1;
    public static final int DURABILITY = 131;
    public static final int ENCHANTABILITY = 27;
    public static final int MINING_LEVEL = MiningLevels.STONE;
    public static final float MINING_SPEED_MULTIPLIER = 10;

    /**
     * <p>构建工具</p>
     * <p></p>
     * <p>修复物品&emsp;&emsp;&emsp;金铜锭</p>
     * <p>材料增伤&emsp;&emsp;&emsp;{@value MATERIAL_DAMAGE}</p>
     * <p>工具耐久&emsp;&emsp;&emsp;{@value DURABILITY}</p>
     * <p>附魔能力&emsp;&emsp;&emsp;{@value ENCHANTABILITY}</p>
     * <p>开采等级&emsp;&emsp;&emsp;{@value MINING_LEVEL}</p>
     * <p>开采速度倍率&emsp;{@value MINING_SPEED_MULTIPLIER}</p>
     */
    private AuCuTool() {
        super(Ingredient.ofItems(AUCU_ALLOY_INGOT),
                MATERIAL_DAMAGE,
                DURABILITY,
                ENCHANTABILITY,
                MINING_LEVEL,
                MINING_SPEED_MULTIPLIER);
    }
}