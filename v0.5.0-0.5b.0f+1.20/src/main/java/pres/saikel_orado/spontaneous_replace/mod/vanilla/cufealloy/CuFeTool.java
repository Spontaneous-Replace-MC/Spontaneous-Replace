package pres.saikel_orado.spontaneous_replace.mod.vanilla.cufealloy;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.recipe.Ingredient;
import pres.saikel_orado.spontaneous_replace.mod.util.SRTool;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.CuFeAlloy.CUFE_ALLOY_INGOT;

/**
 * <b style="color:FFC800"><font size="+2">CuFeTool：铜铁工具类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">基础属性只比铁制工具略高，但是拥有精铜工具的附魔等级</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/5
 */
public final class CuFeTool extends SRTool {
    public static final CuFeTool CUFE_TOOL = new CuFeTool();
    public static final float MATERIAL_DAMAGE = 2;
    public static final int DURABILITY = 500;
    public static final int ENCHANTABILITY = 18;
    public static final int MINING_LEVEL = MiningLevels.IRON;
    public static final float MINING_SPEED_MULTIPLIER = 6;

    /**
     * <p>构建工具</p>
     * <p></p>
     * <p>修复物品&emsp;&emsp;&emsp;铜铁锭</p>
     * <p>材料增伤&emsp;&emsp;&emsp;{@value MATERIAL_DAMAGE}</p>
     * <p>工具耐久&emsp;&emsp;&emsp;{@value DURABILITY}</p>
     * <p>附魔能力&emsp;&emsp;&emsp;{@value ENCHANTABILITY}</p>
     * <p>开采等级&emsp;&emsp;&emsp;{@value MINING_LEVEL}</p>
     * <p>开采速度倍率&emsp;{@value MINING_SPEED_MULTIPLIER}</p>
     */
    private CuFeTool() {
        super(Ingredient.ofItems(CUFE_ALLOY_INGOT),
                MATERIAL_DAMAGE,
                DURABILITY,
                ENCHANTABILITY,
                MINING_LEVEL,
                MINING_SPEED_MULTIPLIER);
    }
}

