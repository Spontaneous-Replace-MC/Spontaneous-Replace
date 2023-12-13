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

package pers.saikel0rado1iu.sr.vanilla.aucu;

import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import pers.saikel0rado1iu.silk.api.item.tool.Tool;

import static pers.saikel0rado1iu.sr.data.Items.AUCU_ALLOY_INGOT;

/**
 * <h2 style="color:FFC800">金铜工具</font></b></p>
 * <p style="color:FFC800">基础属性在石制工具左右，但是拥有极致的附魔等级，附魔等级甚至比金高</p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface AuCuTool extends Tool {
	AuCuTool MATERIAL = new AuCuTool() {
	};
	
	@Override
	default float getMaterialDamage() {
		return ToolMaterials.STONE.getAttackDamage();
	}
	
	@Override
	default int getDurability() {
		return ToolMaterials.STONE.getDurability();
	}
	
	@Override
	default float getMiningSpeedMultiplier() {
		return ToolMaterials.GOLD.getMiningSpeedMultiplier();
	}
	
	@Override
	default int getMiningLevel() {
		return ToolMaterials.STONE.getMiningLevel();
	}
	
	@Override
	default int getEnchantability() {
		return ToolMaterials.GOLD.getEnchantability() + 5;
	}
	
	@Override
	default Ingredient getRepairIngredient() {
		return Ingredient.ofItems(AUCU_ALLOY_INGOT);
	}
}
