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

import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;
import pers.saikel0rado1iu.silk.api.item.armor.Armor;
import pers.saikel0rado1iu.sr.vanilla.copper.RefinedCopperArmor;

import static pers.saikel0rado1iu.sr.data.Items.CUFE_ALLOY_INGOT;
import static pers.saikel0rado1iu.sr.data.SoundEvents.EQUIP_CUFE_ALLOY;

/**
 * <h2 style="color:FFC800">铜铁盔甲</font></b></p>
 * <p style="color:FFC800">基础属性只比铁制盔甲略高，但是拥有精铜的盔甲韧性和附魔等级</p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface CuFeArmor extends Armor {
	CuFeArmor MATERIAL = new CuFeArmor() {
	};
	
	@Override
	default @NotNull String getId() {
		return "cufe_alloy";
	}
	
	@Override
	default int getDurability() {
		return 20;
	}
	
	@Override
	default int[] getProtection() {
		return new int[]{2, 6, 5, 2};
	}
	
	@Override
	default float getKnockBackResistance() {
		return 0;
	}
	
	@Override
	default int getEnchantability() {
		return RefinedCopperArmor.MATERIAL.getEnchantability();
	}
	
	@Override
	default SoundEvent getEquipSound() {
		return EQUIP_CUFE_ALLOY;
	}
	
	@Override
	default Ingredient getRepairIngredient() {
		return Ingredient.ofItems(CUFE_ALLOY_INGOT);
	}
	
	@Override
	default float getToughness() {
		return RefinedCopperArmor.MATERIAL.getToughness() * 2;
	}
}
