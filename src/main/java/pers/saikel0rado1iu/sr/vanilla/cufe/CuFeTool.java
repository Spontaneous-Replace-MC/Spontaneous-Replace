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

package pers.saikel0rado1iu.sr.vanilla.cufe;

import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import pers.saikel0rado1iu.silk.api.item.tool.Tool;
import pers.saikel0rado1iu.sr.vanilla.copper.RefinedCopperTool;

import static pers.saikel0rado1iu.sr.data.Items.CUFE_ALLOY_INGOT;

/**
 * <h2 style="color:FFC800">铜铁工具</font></b></p>
 * <p style="color:FFC800">基础属性只比铁制工具略高，但是拥有精铜工具的附魔等级</p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface CuFeTool extends Tool {
	CuFeTool MATERIAL = new CuFeTool() {
	};
	
	@Override
	default float getMaterialDamage() {
		return 2;
	}
	
	@Override
	default int getDurability() {
		return ToolMaterials.IRON.getDurability() * 2;
	}
	
	@Override
	default float getMiningSpeedMultiplier() {
		return ToolMaterials.IRON.getMiningSpeedMultiplier();
	}
	
	@Override
	default int getMiningLevel() {
		return ToolMaterials.IRON.getMiningLevel();
	}
	
	@Override
	default int getEnchantability() {
		return RefinedCopperTool.MATERIAL.getEnchantability();
	}
	
	@Override
	default Ingredient getRepairIngredient() {
		return Ingredient.ofItems(CUFE_ALLOY_INGOT);
	}
}
