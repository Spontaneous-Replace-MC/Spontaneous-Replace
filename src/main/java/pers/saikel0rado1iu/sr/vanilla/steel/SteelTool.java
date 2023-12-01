/*
 * MIT License
 *
 * Copyright (c) 2023 GameGeek-Saikel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package pers.saikel0rado1iu.sr.vanilla.steel;

import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import pers.saikel0rado1iu.silk.api.item.tool.Tool;
import pers.saikel0rado1iu.sr.vanilla.cufe.CuFeTool;

import static pers.saikel0rado1iu.sr.data.Items.STEEL_INGOT;

/**
 * <p><b style="color:FFC800"><font size="+1">钢制工具</font></b></p>
 * <p style="color:FFC800">基础属性只比钻石工具略低，但挖掘等级还是铁，但是拥有铁质工具的附魔等级</p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public interface SteelTool extends Tool {
	SteelTool MATERIAL = new SteelTool() {
	};
	
	@Override
	default float getMaterialDamage() {
		return ToolMaterials.DIAMOND.getAttackDamage();
	}
	
	@Override
	default int getDurability() {
		return ToolMaterials.DIAMOND.getDurability() - CuFeTool.MATERIAL.getDurability();
	}
	
	@Override
	default float getMiningSpeedMultiplier() {
		return (ToolMaterials.IRON.getMiningSpeedMultiplier() + ToolMaterials.DIAMOND.getMiningSpeedMultiplier()) / 2;
	}
	
	@Override
	default int getMiningLevel() {
		return ToolMaterials.IRON.getMiningLevel();
	}
	
	@Override
	default int getEnchantability() {
		return ToolMaterials.IRON.getEnchantability();
	}
	
	@Override
	default Ingredient getRepairIngredient() {
		return Ingredient.ofItems(STEEL_INGOT);
	}
}
