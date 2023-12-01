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

import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;
import pers.saikel0rado1iu.silk.api.item.armor.Armor;

import static pers.saikel0rado1iu.sr.data.Items.STEEL_INGOT;
import static pers.saikel0rado1iu.sr.data.SoundEvents.EQUIP_STEEL;

/**
 * <p><b style="color:FFC800"><font size="+1">钢制盔甲</font></b></p>
 * <p style="color:FFC800">基础属性只比钻石盔甲略低，但是拥有下届合金的附魔等级和少量的击退抗性</p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public interface SteelArmor extends Armor {
	SteelArmor MATERIAL = new SteelArmor() {
	};
	
	@Override
	default @NotNull String getId() {
		return "steel";
	}
	
	@Override
	default int getDurability() {
		return 28;
	}
	
	@Override
	default int[] getProtection() {
		return new int[]{3, 7, 6, 2};
	}
	
	@Override
	default float getKnockBackResistance() {
		return 0.5F;
	}
	
	@Override
	default int getEnchantability() {
		return ArmorMaterials.NETHERITE.getEnchantability();
	}
	
	@Override
	default SoundEvent getEquipSound() {
		return EQUIP_STEEL;
	}
	
	@Override
	default Ingredient getRepairIngredient() {
		return Ingredient.ofItems(STEEL_INGOT);
	}
	
	@Override
	default float getToughness() {
		return ArmorMaterials.DIAMOND.getToughness() - 0.5F;
	}
}
