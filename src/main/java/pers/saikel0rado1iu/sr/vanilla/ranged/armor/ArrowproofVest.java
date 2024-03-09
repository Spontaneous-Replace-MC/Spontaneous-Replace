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

package pers.saikel0rado1iu.sr.vanilla.ranged.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.NotNull;
import pers.saikel0rado1iu.silk.api.item.WithProjectileProtection;
import pers.saikel0rado1iu.silk.api.item.armor.Armor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static pers.saikel0rado1iu.sr.data.Items.COMPOSITE_FABRIC;
import static pers.saikel0rado1iu.sr.data.SoundEvents.EQUIP_ARROWPROOF_VEST;

/**
 * <h2 style="color:FFC800">防箭衣</h2>
 * <p style="color:FFC800">添加一种特殊的投掷物防御装备</p>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface ArrowproofVest extends Armor, WithProjectileProtection {
	int COLOR = ColorHelper.Argb.fullAlpha(0xFFFFB3);
	ArrowproofVest MATERIAL = new ArrowproofVest() {
	};
	
	@Override
	default @NotNull String getId() {
		return "arrowproof_vest";
	}
	
	@Override
	default List<ArmorMaterial.Layer> getLayers() {
		return List.of(new ArmorMaterial.Layer(new Identifier(getId()), "", true),
				new ArmorMaterial.Layer(new Identifier(getId()), "_overlay", false));
	}
	
	@Override
	default int getDurability() {
		return 10;
	}
	
	@Override
	default int[] getProtection() {
		return new int[]{1, 3, 2, 1};
	}
	
	@Override
	default float getKnockBackResistance() {
		return 0;
	}
	
	@Override
	default int getEnchantability() {
		return ArmorMaterials.TURTLE.value().getEnchantability();
	}
	
	@Override
	default RegistryEntry<SoundEvent> getEquipSound() {
		return EQUIP_ARROWPROOF_VEST;
	}
	
	@Override
	default Ingredient getRepairIngredient() {
		return Ingredient.ofItems(COMPOSITE_FABRIC);
	}
	
	@Override
	default float getToughness() {
		return 5;
	}
	
	/**
	 * 设置弹射物保护的保护伤害处理
	 *
	 * @param originalAmount 原始伤害
	 * @return 处理后伤害
	 */
	@Override
	default float getPrPrAmount(float originalAmount) {
		return originalAmount / 2;
	}
	
	/**
	 * 设置此效果的可叠加次数
	 *
	 * @return 如果值为 {@link Optional#empty()}，则此效果可无限叠加
	 */
	@Override
	default Optional<Integer> getPrPrStackingCount() {
		return Optional.of(0);
	}
	
	/**
	 * 返回有效的装备槽位集合
	 *
	 * @return 如果值为 {@link Optional#empty()}，则意味着在任意物品栏内皆能生效
	 */
	@Override
	default Optional<Set<EquipmentSlot>> getEffectiveEquipmentSlot() {
		return Optional.of(Set.of(EquipmentSlot.CHEST));
	}
}
