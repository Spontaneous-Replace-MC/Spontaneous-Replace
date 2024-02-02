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

package pers.saikel0rado1iu.sr.vanilla.copper;

import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;
import pers.saikel0rado1iu.silk.api.item.armor.Armor;

import static pers.saikel0rado1iu.sr.data.Items.REFINED_COPPER_INGOT;
import static pers.saikel0rado1iu.sr.data.SoundEvents.EQUIP_REFINED_COPPER;

/**
 * <h2 style="color:FFC800">精铜盔甲</h2>
 * <p style="color:FFC800">基础属性在锁链盔甲与铁制盔甲之间，但耐久小于二者，但是拥有少量的盔甲韧性和只比金属性低地附魔等级</p>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface RefinedCopperArmor extends Armor {
	RefinedCopperArmor MATERIAL = new RefinedCopperArmor() {
	};
	
	@Override
	default @NotNull String getId() {
		return "refined_copper";
	}
	
	@Override
	default int getDurability() {
		return 12;
	}
	
	@Override
	default int[] getProtection() {
		return new int[]{2, 5, 4, 2};
	}
	
	@Override
	default float getKnockBackResistance() {
		return 0;
	}
	
	@Override
	default int getEnchantability() {
		return ArmorMaterials.GOLD.value().getEnchantability() - 5;
	}
	
	@Override
	default RegistryEntry<SoundEvent> getEquipSound() {
		return EQUIP_REFINED_COPPER;
	}
	
	@Override
	default Ingredient getRepairIngredient() {
		return Ingredient.ofItems(REFINED_COPPER_INGOT);
	}
	
	@Override
	default float getToughness() {
		return ArmorMaterials.CHAIN.value().getToughness() + 0.5F;
	}
}
