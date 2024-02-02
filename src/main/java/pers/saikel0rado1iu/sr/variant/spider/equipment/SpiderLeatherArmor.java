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

package pers.saikel0rado1iu.sr.variant.spider.equipment;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;
import pers.saikel0rado1iu.silk.api.item.WithStatusEffects;
import pers.saikel0rado1iu.silk.api.item.armor.Armor;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static pers.saikel0rado1iu.sr.data.Items.*;
import static pers.saikel0rado1iu.sr.data.SoundEvents.EQUIP_SPIDER_LEATHER_ARMOR;
import static pers.saikel0rado1iu.sr.data.StatusEffects.SPIDER_CAMOUFLAGE;

/**
 * <h2 style="color:FFC800">蜘蛛护皮套装</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface SpiderLeatherArmor extends Armor, WithStatusEffects {
	SpiderLeatherArmor MATERIAL = new SpiderLeatherArmor() {
	};
	
	/**
	 * 返回有效的装备槽位集合
	 *
	 * @return 如果值为 {@link Optional#empty()}，则意味着在任意物品栏内皆能生效
	 */
	@Override
	default Optional<Set<EquipmentSlot>> getEffectiveEquipmentSlot() {
		return Optional.of(Set.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST));
	}
	
	/**
	 * @return {@link Map} 中的 {@link StatusEffect} 为添加的状态效果，{@link Integer} 为状态效果的最大等级
	 */
	@Override
	default @NotNull Map<StatusEffect, Integer> getStatusEffects() {
		return Map.of(SPIDER_CAMOUFLAGE.value(), 1);
	}
	
	/**
	 * 获取每个状态效果每次的叠加等级
	 *
	 * @return {@link Map} 中的 {@link StatusEffect} 为添加的状态效果，
	 * {@link Float} 为状态效果每次的递增等级，小于 0 则递减，等于 0 则不变
	 */
	@Override
	default @NotNull Map<StatusEffect, Float> getStatusEffectsStackingLevel() {
		return Map.of(SPIDER_CAMOUFLAGE.value(), 0F);
	}
	
	/**
	 * 获取效果套装，效果套装中的物品组被识别为相同的 {@link Item}，
	 * 但只有 {@link WithStatusEffects} 才能进行效果判断
	 *
	 * @return {@link Map} 中的 {@link StatusEffect} 为添加的状态效果；
	 * {@link Optional} 为 {@link Optional#empty()} 则说明没有套装效果，
	 * {@link Map} 为当前效果的套装物品组，包括自身或不包括自身都会将自身包括到此物品组。
	 * {@link Item} 为套装物品，如果 {@link Item} 为 {@link WithStatusEffects} 则忽略 {@link Set} 内容，
	 */
	@Override
	default @NotNull Map<StatusEffect, Optional<Map<Item, Optional<Set<EquipmentSlot>>>>> getStatusEffectsKit() {
		return Map.of(SPIDER_CAMOUFLAGE.value(), Optional.of(Map.of(SPIDER_LEATHER_CAP, Optional.of(Set.of(EquipmentSlot.HEAD)), SPIDER_LEATHER_TUNIC, Optional.of(Set.of(EquipmentSlot.CHEST)))));
	}
	
	/**
	 * 获取套装触发阈值，套装效果意味着玩家需要集齐一套物品才能触发状态效果
	 *
	 * @return {@link Map} 中的 {@link StatusEffect} 为添加的状态效果，
	 * 如果 {@link WithStatusEffects#getStatusEffectsKit()} 中不存在效果套装则为需要多少个自己；
	 * {@link Optional} 为 {@link Optional#empty()} 则说明需要集齐套装中所有物品，
	 * {@link Integer} 为最少需要的套装中物品数量，如果大于套装数量则为需要一套套装，小于 1 则视为需要 1 个物品
	 */
	@Override
	default @NotNull Map<StatusEffect, Optional<Integer>> getKitTriggerThreshold() {
		return Map.of(SPIDER_CAMOUFLAGE.value(), Optional.empty());
	}
	
	@Override
	default @NotNull String getId() {
		return "spider_leather";
	}
	
	@Override
	default int getDurability() {
		return 6;
	}
	
	@Override
	default int[] getProtection() {
		return new int[]{2, 4, 3, 1};
	}
	
	@Override
	default float getKnockBackResistance() {
		return 0;
	}
	
	@Override
	default int getEnchantability() {
		return ArmorMaterials.LEATHER.value().getEnchantability();
	}
	
	@Override
	default RegistryEntry<SoundEvent> getEquipSound() {
		return EQUIP_SPIDER_LEATHER_ARMOR;
	}
	
	@Override
	default Ingredient getRepairIngredient() {
		return Ingredient.ofItems(SPIDER_LEATHER);
	}
	
	@Override
	default float getToughness() {
		return 0;
	}
}
