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

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;
import pers.saikel0rado1iu.silk.api.item.PiglinIgnore;
import pers.saikel0rado1iu.silk.api.item.armor.Armor;

import java.util.Optional;
import java.util.Set;

import static pers.saikel0rado1iu.sr.data.Items.AUCU_ALLOY_INGOT;
import static pers.saikel0rado1iu.sr.data.SoundEvents.EQUIP_AUCU_ALLOY;

/**
 * <h2 style="color:FFC800">金铜盔甲</font></b></p>
 * <p style="color:FFC800">基础属性只比金制盔甲略高，但是拥有极致的附魔等级，附魔等级甚至比金高 5 点</p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface AuCuArmor extends Armor, PiglinIgnore {
	AuCuArmor MATERIAL = new AuCuArmor() {
	};
	
	@Override
	default @NotNull String getId() {
		return "aucu_alloy";
	}
	
	@Override
	default int getDurability() {
		return 10;
	}
	
	@Override
	default int[] getProtection() {
		return new int[]{2, 5, 4, 1};
	}
	
	@Override
	default float getKnockBackResistance() {
		return 0;
	}
	
	@Override
	default int getEnchantability() {
		return ArmorMaterials.GOLD.getEnchantability() + 5;
	}
	
	@Override
	default SoundEvent getEquipSound() {
		return EQUIP_AUCU_ALLOY;
	}
	
	@Override
	default Ingredient getRepairIngredient() {
		return Ingredient.ofItems(AUCU_ALLOY_INGOT);
	}
	
	@Override
	default float getToughness() {
		return ArmorMaterials.GOLD.getToughness();
	}
	
	/**
	 * 返回有效的装备槽位集合
	 *
	 * @return 如果值为 {@link Optional#empty()}，则意味着在任意物品栏内皆能生效
	 */
	@Override
	default Optional<Set<EquipmentSlot>> getEffectiveEquipmentSlot() {
		return Optional.of(Set.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET));
	}
}
