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

package pers.saikel0rado1iu.sr.gen.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.nbt.NbtString;
import net.minecraft.predicate.DamagePredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import pers.saikel0rado1iu.silk.api.entity.SilkEntityTypeTags;
import pers.saikel0rado1iu.silk.api.registry.gen.data.criterion.RangedKilledEntityCriterion;
import pers.saikel0rado1iu.silk.api.registry.gen.data.criterion.ShotProjectileCriterion;
import pers.saikel0rado1iu.silk.gen.data.SilkAdvancement;
import pers.saikel0rado1iu.sr.data.EntityTypes;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static pers.saikel0rado1iu.sr.data.Items.*;

/**
 * <p><b style="color:FFC800"><font size="+1">自然更替的进度生成器</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public final class AdvancementGenerator extends FabricAdvancementProvider {
	private static final ItemStack POTION_STACK = new ItemStack(Items.POTION);
	private static final ItemStack POISON_ARROW_STACK = new ItemStack(Items.TIPPED_ARROW);
	private static final String VANILLA_PATH = "vanilla";
	public static final AdvancementEntry VANILLA_ROOT = Advancement.Builder.create()
			.display(Items.CRAFTING_TABLE,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, VANILLA_PATH + ".root"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, VANILLA_PATH + ".root"),
					new Identifier("textures/block/andesite.png"),
					AdvancementFrame.TASK,
					false,
					false,
					false)
			.criterion(RecipeProvider.hasItem(Items.CRAFTING_TABLE), InventoryChangedCriterion.Conditions.items(Items.CRAFTING_TABLE))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/root"));
	public static final AdvancementEntry HAVE_A_NEW_METAL = Advancement.Builder.create()
			.display(COPPER_FOR_SMELTING_INGOT,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_new_metal"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_new_metal"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(VANILLA_ROOT)
			.criterion(RecipeProvider.hasItem(COPPER_FOR_SMELTING_INGOT), InventoryChangedCriterion.Conditions.items(COPPER_FOR_SMELTING_INGOT))
			.criterion(RecipeProvider.hasItem(CUFE_ALLOY), InventoryChangedCriterion.Conditions.items(CUFE_ALLOY))
			.criterion(RecipeProvider.hasItem(AUCU_ALLOY), InventoryChangedCriterion.Conditions.items(AUCU_ALLOY))
			.criterion(RecipeProvider.hasItem(PIG_IRON), InventoryChangedCriterion.Conditions.items(PIG_IRON))
			.requirements(AdvancementRequirements.anyOf(List.of(
					RecipeProvider.hasItem(COPPER_FOR_SMELTING_INGOT),
					RecipeProvider.hasItem(CUFE_ALLOY),
					RecipeProvider.hasItem(AUCU_ALLOY),
					RecipeProvider.hasItem(PIG_IRON))))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_new_metal"));
	public static final AdvancementEntry HAVE_A_REFINED_COPPER = Advancement.Builder.create()
			.display(REFINED_COPPER_INGOT,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_refined_copper"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_refined_copper"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(HAVE_A_NEW_METAL)
			.criterion(RecipeProvider.hasItem(REFINED_COPPER_INGOT), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_INGOT))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_refined_copper"));
	public static final AdvancementEntry HAVE_A_REFINED_COPPER_PRODUCT = Advancement.Builder.create()
			.display(REFINED_COPPER_SWORD,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_refined_copper_product"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_refined_copper_product"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(HAVE_A_REFINED_COPPER)
			.criterion(RecipeProvider.hasItem(REFINED_COPPER_SHOVEL), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_SHOVEL))
			.criterion(RecipeProvider.hasItem(REFINED_COPPER_PICKAXE), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_PICKAXE))
			.criterion(RecipeProvider.hasItem(REFINED_COPPER_AXE), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_AXE))
			.criterion(RecipeProvider.hasItem(REFINED_COPPER_HOE), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_HOE))
			.criterion(RecipeProvider.hasItem(REFINED_COPPER_SWORD), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_SWORD))
			.criterion(RecipeProvider.hasItem(REFINED_COPPER_HELMET), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_HELMET))
			.criterion(RecipeProvider.hasItem(REFINED_COPPER_CHESTPLATE), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_CHESTPLATE))
			.criterion(RecipeProvider.hasItem(REFINED_COPPER_LEGGINGS), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_LEGGINGS))
			.criterion(RecipeProvider.hasItem(REFINED_COPPER_BOOTS), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_BOOTS))
			.requirements(AdvancementRequirements.anyOf(List.of(
					RecipeProvider.hasItem(REFINED_COPPER_SHOVEL),
					RecipeProvider.hasItem(REFINED_COPPER_PICKAXE),
					RecipeProvider.hasItem(REFINED_COPPER_AXE),
					RecipeProvider.hasItem(REFINED_COPPER_HOE),
					RecipeProvider.hasItem(REFINED_COPPER_SWORD),
					RecipeProvider.hasItem(REFINED_COPPER_HELMET),
					RecipeProvider.hasItem(REFINED_COPPER_CHESTPLATE),
					RecipeProvider.hasItem(REFINED_COPPER_LEGGINGS),
					RecipeProvider.hasItem(REFINED_COPPER_BOOTS))))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_refined_copper_product"));
	public static final AdvancementEntry HAVE_A_ALLOY = Advancement.Builder.create()
			.display(CUFE_ALLOY_INGOT,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_alloy"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_alloy"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(HAVE_A_NEW_METAL)
			.criterion(RecipeProvider.hasItem(REFINED_COPPER_INGOT), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_INGOT))
			.criterion(RecipeProvider.hasItem(CUFE_ALLOY_INGOT), InventoryChangedCriterion.Conditions.items(CUFE_ALLOY_INGOT))
			.criterion(RecipeProvider.hasItem(AUCU_ALLOY_INGOT), InventoryChangedCriterion.Conditions.items(AUCU_ALLOY_INGOT))
			.criterion(RecipeProvider.hasItem(STEEL_INGOT), InventoryChangedCriterion.Conditions.items(STEEL_INGOT))
			.requirements(AdvancementRequirements.anyOf(List.of(
					RecipeProvider.hasItem(REFINED_COPPER_INGOT),
					RecipeProvider.hasItem(CUFE_ALLOY_INGOT),
					RecipeProvider.hasItem(AUCU_ALLOY_INGOT),
					RecipeProvider.hasItem(STEEL_INGOT))))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_alloy"));
	public static final AdvancementEntry UPGRADE_IRON_PICKAXE = Advancement.Builder.create()
			.display(CUFE_ALLOY_PICKAXE,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "upgrade_iron_pickaxe"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "upgrade_iron_pickaxe"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(HAVE_A_ALLOY)
			.criterion(RecipeProvider.hasItem(CUFE_ALLOY_PICKAXE), InventoryChangedCriterion.Conditions.items(CUFE_ALLOY_PICKAXE))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/upgrade_iron_pickaxe"));
	public static final AdvancementEntry UPGRADE_IRON_ARMOR = Advancement.Builder.create()
			.display(CUFE_ALLOY_CHESTPLATE,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "upgrade_iron_armor"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "upgrade_iron_armor"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(UPGRADE_IRON_PICKAXE)
			.criterion(RecipeProvider.hasItem(CUFE_ALLOY_HELMET), InventoryChangedCriterion.Conditions.items(CUFE_ALLOY_HELMET))
			.criterion(RecipeProvider.hasItem(CUFE_ALLOY_CHESTPLATE), InventoryChangedCriterion.Conditions.items(CUFE_ALLOY_CHESTPLATE))
			.criterion(RecipeProvider.hasItem(CUFE_ALLOY_LEGGINGS), InventoryChangedCriterion.Conditions.items(CUFE_ALLOY_LEGGINGS))
			.criterion(RecipeProvider.hasItem(CUFE_ALLOY_BOOTS), InventoryChangedCriterion.Conditions.items(CUFE_ALLOY_BOOTS))
			.requirements(AdvancementRequirements.anyOf(List.of(
					RecipeProvider.hasItem(CUFE_ALLOY_HELMET),
					RecipeProvider.hasItem(CUFE_ALLOY_CHESTPLATE),
					RecipeProvider.hasItem(CUFE_ALLOY_LEGGINGS),
					RecipeProvider.hasItem(CUFE_ALLOY_BOOTS))))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/upgrade_iron_armor"));
	public static final AdvancementEntry HAVE_A_STEEL_PRODUCT = Advancement.Builder.create()
			.display(STEEL_SWORD,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_steel_product"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_steel_product"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(HAVE_A_ALLOY)
			.criterion(RecipeProvider.hasItem(STEEL_SHOVEL), InventoryChangedCriterion.Conditions.items(STEEL_SHOVEL))
			.criterion(RecipeProvider.hasItem(STEEL_PICKAXE), InventoryChangedCriterion.Conditions.items(STEEL_PICKAXE))
			.criterion(RecipeProvider.hasItem(STEEL_AXE), InventoryChangedCriterion.Conditions.items(STEEL_AXE))
			.criterion(RecipeProvider.hasItem(STEEL_HOE), InventoryChangedCriterion.Conditions.items(STEEL_HOE))
			.criterion(RecipeProvider.hasItem(STEEL_SWORD), InventoryChangedCriterion.Conditions.items(STEEL_SWORD))
			.criterion(RecipeProvider.hasItem(STEEL_HELMET), InventoryChangedCriterion.Conditions.items(STEEL_HELMET))
			.criterion(RecipeProvider.hasItem(STEEL_CHESTPLATE), InventoryChangedCriterion.Conditions.items(STEEL_CHESTPLATE))
			.criterion(RecipeProvider.hasItem(STEEL_LEGGINGS), InventoryChangedCriterion.Conditions.items(STEEL_LEGGINGS))
			.criterion(RecipeProvider.hasItem(STEEL_BOOTS), InventoryChangedCriterion.Conditions.items(STEEL_BOOTS))
			.requirements(AdvancementRequirements.anyOf(List.of(
					RecipeProvider.hasItem(STEEL_SHOVEL),
					RecipeProvider.hasItem(STEEL_PICKAXE),
					RecipeProvider.hasItem(STEEL_AXE),
					RecipeProvider.hasItem(STEEL_HOE),
					RecipeProvider.hasItem(STEEL_SWORD),
					RecipeProvider.hasItem(STEEL_HELMET),
					RecipeProvider.hasItem(STEEL_CHESTPLATE),
					RecipeProvider.hasItem(STEEL_LEGGINGS),
					RecipeProvider.hasItem(STEEL_BOOTS))))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_steel_product"));
	public static final AdvancementEntry HAVE_ALL_STEEL_ARMORS = Advancement.Builder.create()
			.display(STEEL_CHESTPLATE,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_all_steel_armors"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_all_steel_armors"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(HAVE_A_STEEL_PRODUCT)
			.criterion(RecipeProvider.hasItem(STEEL_HELMET), InventoryChangedCriterion.Conditions.items(STEEL_HELMET))
			.criterion(RecipeProvider.hasItem(STEEL_CHESTPLATE), InventoryChangedCriterion.Conditions.items(STEEL_CHESTPLATE))
			.criterion(RecipeProvider.hasItem(STEEL_LEGGINGS), InventoryChangedCriterion.Conditions.items(STEEL_LEGGINGS))
			.criterion(RecipeProvider.hasItem(STEEL_BOOTS), InventoryChangedCriterion.Conditions.items(STEEL_BOOTS))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_all_steel_armors"));
	public static final AdvancementEntry HAVE_ALL_NEW_METALS = Advancement.Builder.create()
			.display(PIG_IRON,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_all_new_metals"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_all_new_metals"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(HAVE_A_NEW_METAL)
			.criterion(RecipeProvider.hasItem(REFINED_COPPER_INGOT), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_INGOT))
			.criterion(RecipeProvider.hasItem(CUFE_ALLOY_INGOT), InventoryChangedCriterion.Conditions.items(CUFE_ALLOY_INGOT))
			.criterion(RecipeProvider.hasItem(AUCU_ALLOY_INGOT), InventoryChangedCriterion.Conditions.items(AUCU_ALLOY_INGOT))
			.criterion(RecipeProvider.hasItem(STEEL_INGOT), InventoryChangedCriterion.Conditions.items(STEEL_INGOT))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_all_new_metals"));
	public static final AdvancementEntry HAVE_A_NEW_RANGED = Advancement.Builder.create()
			.display(RECURVE_BOW,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_new_ranged"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_new_ranged"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(VANILLA_ROOT)
			.criterion(RecipeProvider.hasItem(SLINGSHOT), InventoryChangedCriterion.Conditions.items(SLINGSHOT))
			.criterion(RecipeProvider.hasItem(RECURVE_BOW), InventoryChangedCriterion.Conditions.items(RECURVE_BOW))
			.criterion(RecipeProvider.hasItem(ARBALEST), InventoryChangedCriterion.Conditions.items(ARBALEST))
			.criterion(RecipeProvider.hasItem(COMPOUND_BOW), InventoryChangedCriterion.Conditions.items(COMPOUND_BOW))
			.criterion(RecipeProvider.hasItem(JUGER_REPEATING_CROSSBOW), InventoryChangedCriterion.Conditions.items(JUGER_REPEATING_CROSSBOW))
			.criterion(RecipeProvider.hasItem(MARKS_CROSSBOW), InventoryChangedCriterion.Conditions.items(MARKS_CROSSBOW))
			.criterion(RecipeProvider.hasItem(ARROWPROOF_VEST), InventoryChangedCriterion.Conditions.items(ARROWPROOF_VEST))
			.requirements(AdvancementRequirements.anyOf(List.of(
					RecipeProvider.hasItem(SLINGSHOT),
					RecipeProvider.hasItem(RECURVE_BOW),
					RecipeProvider.hasItem(ARBALEST),
					RecipeProvider.hasItem(COMPOUND_BOW),
					RecipeProvider.hasItem(JUGER_REPEATING_CROSSBOW),
					RecipeProvider.hasItem(MARKS_CROSSBOW),
					RecipeProvider.hasItem(ARROWPROOF_VEST))))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_new_ranged"));
	public static final AdvancementEntry HAVE_A_ARROWPROOF_VEST = Advancement.Builder.create()
			.display(ARROWPROOF_VEST,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_arrowproof_vest"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_arrowproof_vest"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(HAVE_A_NEW_RANGED)
			.criterion(RecipeProvider.hasItem(ARROWPROOF_VEST), InventoryChangedCriterion.Conditions.items(ARROWPROOF_VEST))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_arrowproof_vest"));
	public static final AdvancementEntry USE_SLINGSHOT = Advancement.Builder.create()
			.display(SLINGSHOT,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "use_slingshot"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "use_slingshot"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(HAVE_A_NEW_RANGED)
			.criterion("use_slingshot", ShotProjectileCriterion.Conditions.ranged(SLINGSHOT).build().create())
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/use_slingshot"));
	public static final AdvancementEntry USE_SLINGSHOT_WITH_ENDER_PEARL = Advancement.Builder.create()
			.display(Items.ENDER_PEARL,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "use_slingshot_with_ender_pearl"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "use_slingshot_with_ender_pearl"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(USE_SLINGSHOT)
			.criterion("use_slingshot", ShotProjectileCriterion.Conditions.ranged(SLINGSHOT).projectile(Optional.of(EntityPredicate.Builder.create().type(EntityType.ENDER_PEARL).build())).build().create())
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/use_slingshot_with_ender_pearl"));
	public static final AdvancementEntry USE_SLINGSHOT_WITH_POTION = Advancement.Builder.create()
			.display(new AdvancementDisplay(
					POTION_STACK,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "use_slingshot_with_potion"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "use_slingshot_with_potion"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false))
			.parent(USE_SLINGSHOT)
			.criterion("use_slingshot", ShotProjectileCriterion.Conditions.ranged(SLINGSHOT).projectile(Optional.of(EntityPredicate.Builder.create().type(EntityType.POTION).build())).build().create())
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/use_slingshot_with_potion"));
	public static final AdvancementEntry HAVE_A_JUGER_REPEATING_CROSSBOW = Advancement.Builder.create()
			.display(JUGER_REPEATING_CROSSBOW,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_juger_repeating_crossbow"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_juger_repeating_crossbow"),
					null,
					AdvancementFrame.GOAL,
					true,
					true,
					false)
			.parent(HAVE_A_NEW_RANGED)
			.criterion(RecipeProvider.hasItem(JUGER_REPEATING_CROSSBOW), InventoryChangedCriterion.Conditions.items(JUGER_REPEATING_CROSSBOW))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_juger_repeating_crossbow"));
	public static final AdvancementEntry HAVE_LEGEND_JUGER_REPEATING_CROSSBOW = Advancement.Builder.create()
			.display(JUGER_REPEATING_CROSSBOW,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_legend_juger_repeating_crossbow"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_legend_juger_repeating_crossbow"),
					null,
					AdvancementFrame.CHALLENGE,
					true,
					true,
					true)
			.parent(HAVE_A_JUGER_REPEATING_CROSSBOW)
			.criterion(RecipeProvider.hasItem(JUGER_REPEATING_CROSSBOW), InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().items(JUGER_REPEATING_CROSSBOW)
					.enchantment(new EnchantmentPredicate(Enchantments.MULTISHOT, NumberRange.IntRange.exactly(1)))
					.enchantment(new EnchantmentPredicate(Enchantments.QUICK_CHARGE, NumberRange.IntRange.exactly(3)))
					.enchantment(new EnchantmentPredicate(Enchantments.UNBREAKING, NumberRange.IntRange.exactly(5))).build()))
			.rewards(AdvancementRewards.Builder.experience(100))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_legend_juger_repeating_crossbow"));
	public static final AdvancementEntry USE_JUGER_REPEATING_CROSSBOW_SHOT_1000_ARROWS = Advancement.Builder.create()
			.display(Items.ARROW,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "use_juger_repeating_crossbow_shot_1000_arrows"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "use_juger_repeating_crossbow_shot_1000_arrows"),
					null,
					AdvancementFrame.CHALLENGE,
					true,
					true,
					true)
			.parent(HAVE_A_JUGER_REPEATING_CROSSBOW)
			.criterion("use_juger_repeating_crossbow_shot_1000_arrows", ShotProjectileCriterion.Conditions.ranged(JUGER_REPEATING_CROSSBOW).count(NumberRange.IntRange.atLeast(1000)).build().create())
			.rewards(AdvancementRewards.Builder.experience(100))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/use_juger_repeating_crossbow_shot_1000_arrows"));
	public static final AdvancementEntry USE_JUGER_REPEATING_CROSSBOW_KILL_100_MONSTERS = Advancement.Builder.create()
			.display(Items.SKELETON_SKULL,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "use_juger_repeating_crossbow_kill_100_monsters"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "use_juger_repeating_crossbow_kill_100_monsters"),
					null,
					AdvancementFrame.CHALLENGE,
					true,
					true,
					true)
			.parent(HAVE_A_JUGER_REPEATING_CROSSBOW)
			.criterion("use_juger_repeating_crossbow_kill_100_monsters", RangedKilledEntityCriterion.Conditions.ranged(JUGER_REPEATING_CROSSBOW)
					.target(EntityPredicate.Builder.create().type(SilkEntityTypeTags.MONSTERS).build()).killed(NumberRange.IntRange.atLeast(100)).build().create())
			.rewards(AdvancementRewards.Builder.experience(100))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/use_juger_repeating_crossbow_kill_100_monsters"));
	public static final AdvancementEntry HAVE_A_MARKS_CROSSBOW = Advancement.Builder.create()
			.display(MARKS_CROSSBOW,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_marks_crossbow"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_marks_crossbow"),
					null,
					AdvancementFrame.GOAL,
					true,
					true,
					false)
			.parent(HAVE_A_NEW_RANGED)
			.criterion(RecipeProvider.hasItem(MARKS_CROSSBOW), InventoryChangedCriterion.Conditions.items(MARKS_CROSSBOW))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_marks_crossbow"));
	public static final AdvancementEntry MARKSMAN = Advancement.Builder.create()
			.display(Items.TARGET,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "marksman"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "marksman"),
					null,
					AdvancementFrame.CHALLENGE,
					true,
					true,
					true)
			.parent(HAVE_A_MARKS_CROSSBOW)
			.criterion("bullseye", TargetHitCriterion.Conditions.create(NumberRange.IntRange.exactly(15),
					Optional.of(LootContextPredicate.create(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().distance(DistancePredicate.horizontal(NumberRange.DoubleRange.atLeast(200)))).build()))))
			.rewards(AdvancementRewards.Builder.experience(100))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/marksman"));
	public static final AdvancementEntry SNIPING = Advancement.Builder.create()
			.display(STEEL_ARROW,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "sniping"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "sniping"),
					null,
					AdvancementFrame.CHALLENGE,
					true,
					true,
					true)
			.parent(HAVE_A_MARKS_CROSSBOW)
			.criterion("killed_stray", OnKilledCriterion.Conditions.createEntityKilledPlayer(Optional.of(EntityPredicate.Builder.create().type(EntityType.STRAY).distance(DistancePredicate.horizontal(NumberRange.DoubleRange.exactly(100))).build())))
			.rewards(AdvancementRewards.Builder.experience(100))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/sniping"));
	public static final AdvancementEntry HAVE_LEGEND_MARKS_CROSSBOW = Advancement.Builder.create()
			.display(MARKS_CROSSBOW,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_legend_marks_crossbow"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_legend_marks_crossbow"),
					null,
					AdvancementFrame.CHALLENGE,
					true,
					true,
					true)
			.parent(HAVE_A_MARKS_CROSSBOW)
			.criterion(RecipeProvider.hasItem(MARKS_CROSSBOW), InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().items(MARKS_CROSSBOW)
					.enchantment(new EnchantmentPredicate(Enchantments.PIERCING, NumberRange.IntRange.exactly(4)))
					.enchantment(new EnchantmentPredicate(Enchantments.QUICK_CHARGE, NumberRange.IntRange.exactly(3)))
					.enchantment(new EnchantmentPredicate(Enchantments.UNBREAKING, NumberRange.IntRange.exactly(5))).build()))
			.rewards(AdvancementRewards.Builder.experience(100))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_legend_marks_crossbow"));
	public static final AdvancementEntry HAVE_ALL_BASIC_RANGED = Advancement.Builder.create()
			.display(ARBALEST,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_all_basic_ranged"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_all_basic_ranged"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(HAVE_A_NEW_RANGED)
			.criterion(RecipeProvider.hasItem(SLINGSHOT), InventoryChangedCriterion.Conditions.items(SLINGSHOT))
			.criterion(RecipeProvider.hasItem(RECURVE_BOW), InventoryChangedCriterion.Conditions.items(RECURVE_BOW))
			.criterion(RecipeProvider.hasItem(ARBALEST), InventoryChangedCriterion.Conditions.items(ARBALEST))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_all_basic_ranged"));
	public static final AdvancementEntry HAVE_ALL_ENHANCED_RANGED = Advancement.Builder.create()
			.display(JUGER_REPEATING_CROSSBOW,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_all_enhanced_ranged"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_all_enhanced_ranged"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(HAVE_ALL_BASIC_RANGED)
			.criterion(RecipeProvider.hasItem(COMPOUND_BOW), InventoryChangedCriterion.Conditions.items(COMPOUND_BOW))
			.criterion(RecipeProvider.hasItem(JUGER_REPEATING_CROSSBOW), InventoryChangedCriterion.Conditions.items(JUGER_REPEATING_CROSSBOW))
			.criterion(RecipeProvider.hasItem(MARKS_CROSSBOW), InventoryChangedCriterion.Conditions.items(MARKS_CROSSBOW))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_all_enhanced_ranged"));
	public static final AdvancementEntry HAVE_ALL_RANGED = Advancement.Builder.create()
			.display(MARKS_CROSSBOW,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_all_ranged"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_all_ranged"),
					null,
					AdvancementFrame.CHALLENGE,
					true,
					true,
					true)
			.parent(HAVE_ALL_ENHANCED_RANGED)
			.criterion(RecipeProvider.hasItem(Items.BOW), InventoryChangedCriterion.Conditions.items(Items.BOW))
			.criterion(RecipeProvider.hasItem(Items.CROSSBOW), InventoryChangedCriterion.Conditions.items(Items.CROSSBOW))
			.criterion(RecipeProvider.hasItem(SLINGSHOT), InventoryChangedCriterion.Conditions.items(SLINGSHOT))
			.criterion(RecipeProvider.hasItem(RECURVE_BOW), InventoryChangedCriterion.Conditions.items(RECURVE_BOW))
			.criterion(RecipeProvider.hasItem(ARBALEST), InventoryChangedCriterion.Conditions.items(ARBALEST))
			.criterion(RecipeProvider.hasItem(COMPOUND_BOW), InventoryChangedCriterion.Conditions.items(COMPOUND_BOW))
			.criterion(RecipeProvider.hasItem(JUGER_REPEATING_CROSSBOW), InventoryChangedCriterion.Conditions.items(JUGER_REPEATING_CROSSBOW))
			.criterion(RecipeProvider.hasItem(MARKS_CROSSBOW), InventoryChangedCriterion.Conditions.items(MARKS_CROSSBOW))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_all_ranged"));
	private static final String SR_PATH = SpontaneousReplace.DATA.getId();
	public static final AdvancementEntry SR_ROOT = Advancement.Builder.create()
			.display(ICON,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, SR_PATH + ".root"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, SR_PATH + ".root"),
					new Identifier("textures/block/calcite.png"),
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.criterion("root", TickCriterion.Conditions.createLocation(Optional.of(EntityPredicate.Builder.create().build())))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), SR_PATH + "/root"));
	public static final AdvancementEntry FIND_A_SPIDER_BIOME = Advancement.Builder.create()
			.display(COBWEBBY_SOIL,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "find_a_spider_biome"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "find_a_spider_biome"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(SR_ROOT)
			.criterion("biome", TickCriterion.Conditions.createLocation(Optional.of(EntityPredicate.Builder.create().location(LocationPredicate.Builder.createBiome(BiomeKeys.BADLANDS)).build())))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), SR_PATH + "/find_a_spider_biome"));
	public static final AdvancementEntry KILL_A_NEW_SPIDER = Advancement.Builder.create()
			.display(SPIDER_LEG,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "kill_a_new_spider"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "kill_a_new_spider"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false)
			.parent(FIND_A_SPIDER_BIOME)
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.SPIDER_LARVA).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.SPIDER_LARVA)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.GUARD_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.GUARD_SPIDER)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.SPRAY_POISON_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.SPRAY_POISON_SPIDER)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.WEAVING_WEB_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.WEAVING_WEB_SPIDER)))
			.requirements(AdvancementRequirements.anyOf(List.of(
					Registries.ENTITY_TYPE.getId(EntityTypes.SPIDER_LARVA).toString(),
					Registries.ENTITY_TYPE.getId(EntityTypes.GUARD_SPIDER).toString(),
					Registries.ENTITY_TYPE.getId(EntityTypes.SPRAY_POISON_SPIDER).toString(),
					Registries.ENTITY_TYPE.getId(EntityTypes.WEAVING_WEB_SPIDER).toString())))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), SR_PATH + "/kill_a_new_spider"));
	public static final AdvancementEntry SHOT_SPRAY_POISON_SPIDER = Advancement.Builder.create()
			.display(new AdvancementDisplay(
					POISON_ARROW_STACK,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "shot_spray_poison_spider"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "shot_spray_poison_spider"),
					null,
					AdvancementFrame.TASK,
					true,
					true,
					false))
			.parent(KILL_A_NEW_SPIDER)
			.criterion("shot_spray_poison_spider", PlayerHurtEntityCriterion.Conditions.create(
					Optional.of(DamagePredicate.Builder.create().sourceEntity(EntityPredicate.Builder.create().type(EntityType.ARROW).build()).build()),
					Optional.of(EntityPredicate.Builder.create().type(EntityTypes.SPRAY_POISON_SPIDER).build())))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), SR_PATH + "/shot_spray_poison_spider"));
	public static final AdvancementEntry KILL_ALL_SPIDERS = Advancement.Builder.create()
			.display(Items.SPIDER_SPAWN_EGG,
					SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "kill_all_spiders"),
					SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "kill_all_spiders"),
					null,
					AdvancementFrame.CHALLENGE,
					true,
					true,
					false)
			.parent(KILL_A_NEW_SPIDER)
			.criterion(Registries.ENTITY_TYPE.getId(EntityType.SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityType.SPIDER)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityType.CAVE_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityType.CAVE_SPIDER)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.SPIDER_LARVA).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.SPIDER_LARVA)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.GUARD_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.GUARD_SPIDER)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.SPRAY_POISON_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.SPRAY_POISON_SPIDER)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.WEAVING_WEB_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.WEAVING_WEB_SPIDER)))
			.build(new Identifier(SpontaneousReplace.DATA.getId(), SR_PATH + "/kill_all_spiders"));
	
	static {
		POTION_STACK.setSubNbt("Potion", NbtString.of("healing"));
		POISON_ARROW_STACK.setSubNbt("Potion", NbtString.of("poison"));
	}
	
	public AdvancementGenerator(FabricDataOutput dataGenerator) {
		super(dataGenerator);
	}
	
	@Override
	public void generateAdvancement(Consumer<AdvancementEntry> consumer) {
		generateVanillaAdvancement(consumer);
		generateSrAdvancement(consumer);
	}
	
	private void generateVanillaAdvancement(Consumer<AdvancementEntry> consumer) {
		consumer.accept(VANILLA_ROOT);
		consumer.accept(HAVE_A_NEW_METAL);
		consumer.accept(HAVE_A_REFINED_COPPER);
		consumer.accept(HAVE_A_REFINED_COPPER_PRODUCT);
		consumer.accept(HAVE_A_ALLOY);
		consumer.accept(UPGRADE_IRON_PICKAXE);
		consumer.accept(UPGRADE_IRON_ARMOR);
		consumer.accept(HAVE_A_STEEL_PRODUCT);
		consumer.accept(HAVE_ALL_STEEL_ARMORS);
		consumer.accept(HAVE_ALL_NEW_METALS);
		consumer.accept(HAVE_A_NEW_RANGED);
		consumer.accept(HAVE_A_ARROWPROOF_VEST);
		consumer.accept(USE_SLINGSHOT);
		consumer.accept(USE_SLINGSHOT_WITH_ENDER_PEARL);
		consumer.accept(USE_SLINGSHOT_WITH_POTION);
		consumer.accept(HAVE_A_JUGER_REPEATING_CROSSBOW);
		consumer.accept(HAVE_LEGEND_JUGER_REPEATING_CROSSBOW);
		consumer.accept(USE_JUGER_REPEATING_CROSSBOW_SHOT_1000_ARROWS);
		consumer.accept(USE_JUGER_REPEATING_CROSSBOW_KILL_100_MONSTERS);
		consumer.accept(HAVE_A_MARKS_CROSSBOW);
		consumer.accept(HAVE_LEGEND_MARKS_CROSSBOW);
		consumer.accept(MARKSMAN);
		consumer.accept(SNIPING);
		consumer.accept(HAVE_ALL_BASIC_RANGED);
		consumer.accept(HAVE_ALL_ENHANCED_RANGED);
		consumer.accept(HAVE_ALL_RANGED);
	}
	
	private void generateSrAdvancement(Consumer<AdvancementEntry> consumer) {
		consumer.accept(SR_ROOT);
		consumer.accept(FIND_A_SPIDER_BIOME);
		consumer.accept(KILL_A_NEW_SPIDER);
		consumer.accept(SHOT_SPRAY_POISON_SPIDER);
		consumer.accept(KILL_ALL_SPIDERS);
	}
}
