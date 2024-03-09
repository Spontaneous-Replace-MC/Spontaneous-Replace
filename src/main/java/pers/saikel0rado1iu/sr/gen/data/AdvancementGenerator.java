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
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtString;
import net.minecraft.potion.Potions;
import net.minecraft.predicate.DamagePredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.*;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import pers.saikel0rado1iu.silk.api.entity.SilkEntityTypeTags;
import pers.saikel0rado1iu.silk.api.registry.gen.data.criterion.RangedKilledEntityCriterion;
import pers.saikel0rado1iu.silk.api.registry.gen.data.criterion.ShotProjectileCriterion;
import pers.saikel0rado1iu.silk.gen.data.SilkAdvancement;
import pers.saikel0rado1iu.sr.data.EntityTypes;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;
import pers.saikel0rado1iu.sr.data.StatusEffects;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

import static pers.saikel0rado1iu.sr.data.Items.*;

/**
 * <h2 style="color:FFC800">自然更替的进度生成器</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class AdvancementGenerator extends FabricAdvancementProvider {
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> VANILLA_ROOT;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_A_NEW_METAL;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_A_REFINED_COPPER;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_A_REFINED_COPPER_PRODUCT;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_A_ALLOY;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> UPGRADE_IRON_PICKAXE;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> UPGRADE_IRON_ARMOR;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_A_STEEL_PRODUCT;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_ALL_STEEL_ARMORS;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_ALL_NEW_METALS;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_A_NEW_RANGED;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_A_ARROWPROOF_VEST;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> USE_SLINGSHOT;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> USE_SLINGSHOT_WITH_ENDER_PEARL;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> USE_SLINGSHOT_WITH_POTION;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_A_JUGER_REPEATING_CROSSBOW;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_LEGEND_JUGER_REPEATING_CROSSBOW;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> USE_JUGER_REPEATING_CROSSBOW_SHOT_1000_ARROWS;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> USE_JUGER_REPEATING_CROSSBOW_KILL_100_MONSTERS;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_A_MARKS_CROSSBOW;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> MARKSMAN;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> SNIPING;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_LEGEND_MARKS_CROSSBOW;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_ALL_BASIC_RANGED;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_ALL_ENHANCED_RANGED;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_ALL_RANGED;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> SR_ROOT;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> FIND_A_SPIDER_BIOME;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> KILL_A_NEW_SPIDER;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> SHOT_SPRAY_POISON_SPIDER;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_A_DEPOISON_SPIDER_LEG;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> HAVE_EFFECT_SPIDER_CAMOUFLAGE;
	public static final Function<RegistryWrapper.WrapperLookup, AdvancementEntry> KILL_ALL_SPIDERS;
	private static final ItemStack POTION_STACK = PotionContentsComponent.createStack(Items.POTION, Potions.HEALING);
	private static final ItemStack POISON_ARROW_STACK = PotionContentsComponent.createStack(Items.TIPPED_ARROW, Potions.POISON);
	private static final String VANILLA_PATH = "vanilla";
	private static final String SR_PATH = SpontaneousReplace.DATA.getId();
	
	static {
		VANILLA_ROOT = registryLookup -> Advancement.Builder.create()
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
		HAVE_A_NEW_METAL = registryLookup -> Advancement.Builder.create()
				.parent(VANILLA_ROOT.apply(registryLookup))
				.display(COPPER_FOR_SMELTING_INGOT,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_new_metal"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_new_metal"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
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
		HAVE_A_REFINED_COPPER = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_NEW_METAL.apply(registryLookup))
				.display(REFINED_COPPER_INGOT,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_refined_copper"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_refined_copper"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
				.criterion(RecipeProvider.hasItem(REFINED_COPPER_INGOT), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_INGOT))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_refined_copper"));
		HAVE_A_REFINED_COPPER_PRODUCT = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_REFINED_COPPER.apply(registryLookup))
				.display(REFINED_COPPER_SWORD,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_refined_copper_product"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_refined_copper_product"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
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
		HAVE_A_ALLOY = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_NEW_METAL.apply(registryLookup))
				.display(CUFE_ALLOY_INGOT,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_alloy"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_alloy"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
				.criterion(RecipeProvider.hasItem(CUFE_ALLOY_INGOT), InventoryChangedCriterion.Conditions.items(CUFE_ALLOY_INGOT))
				.criterion(RecipeProvider.hasItem(AUCU_ALLOY_INGOT), InventoryChangedCriterion.Conditions.items(AUCU_ALLOY_INGOT))
				.criterion(RecipeProvider.hasItem(STEEL_INGOT), InventoryChangedCriterion.Conditions.items(STEEL_INGOT))
				.requirements(AdvancementRequirements.anyOf(List.of(
						RecipeProvider.hasItem(CUFE_ALLOY_INGOT),
						RecipeProvider.hasItem(AUCU_ALLOY_INGOT),
						RecipeProvider.hasItem(STEEL_INGOT))))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_alloy"));
		UPGRADE_IRON_PICKAXE = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_ALLOY.apply(registryLookup))
				.display(CUFE_ALLOY_PICKAXE,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "upgrade_iron_pickaxe"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "upgrade_iron_pickaxe"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
				.criterion(RecipeProvider.hasItem(CUFE_ALLOY_PICKAXE), InventoryChangedCriterion.Conditions.items(CUFE_ALLOY_PICKAXE))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/upgrade_iron_pickaxe"));
		UPGRADE_IRON_ARMOR = registryLookup -> Advancement.Builder.create()
				.parent(UPGRADE_IRON_PICKAXE.apply(registryLookup))
				.display(CUFE_ALLOY_CHESTPLATE,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "upgrade_iron_armor"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "upgrade_iron_armor"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
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
		HAVE_A_STEEL_PRODUCT = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_ALLOY.apply(registryLookup))
				.display(STEEL_SWORD,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_steel_product"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_steel_product"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
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
		HAVE_ALL_STEEL_ARMORS = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_STEEL_PRODUCT.apply(registryLookup))
				.display(STEEL_CHESTPLATE,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_all_steel_armors"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_all_steel_armors"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
				.criterion(RecipeProvider.hasItem(STEEL_HELMET), InventoryChangedCriterion.Conditions.items(STEEL_HELMET))
				.criterion(RecipeProvider.hasItem(STEEL_CHESTPLATE), InventoryChangedCriterion.Conditions.items(STEEL_CHESTPLATE))
				.criterion(RecipeProvider.hasItem(STEEL_LEGGINGS), InventoryChangedCriterion.Conditions.items(STEEL_LEGGINGS))
				.criterion(RecipeProvider.hasItem(STEEL_BOOTS), InventoryChangedCriterion.Conditions.items(STEEL_BOOTS))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_all_steel_armors"));
		HAVE_ALL_NEW_METALS = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_NEW_METAL.apply(registryLookup))
				.display(PIG_IRON,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_all_new_metals"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_all_new_metals"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
				.criterion(RecipeProvider.hasItem(REFINED_COPPER_INGOT), InventoryChangedCriterion.Conditions.items(REFINED_COPPER_INGOT))
				.criterion(RecipeProvider.hasItem(CUFE_ALLOY_INGOT), InventoryChangedCriterion.Conditions.items(CUFE_ALLOY_INGOT))
				.criterion(RecipeProvider.hasItem(AUCU_ALLOY_INGOT), InventoryChangedCriterion.Conditions.items(AUCU_ALLOY_INGOT))
				.criterion(RecipeProvider.hasItem(STEEL_INGOT), InventoryChangedCriterion.Conditions.items(STEEL_INGOT))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_all_new_metals"));
		HAVE_A_NEW_RANGED = registryLookup -> Advancement.Builder.create()
				.parent(VANILLA_ROOT.apply(registryLookup))
				.display(RECURVE_BOW,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_new_ranged"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_new_ranged"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
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
		HAVE_A_ARROWPROOF_VEST = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_NEW_RANGED.apply(registryLookup))
				.display(ARROWPROOF_VEST,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_arrowproof_vest"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_arrowproof_vest"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
				.criterion(RecipeProvider.hasItem(ARROWPROOF_VEST), InventoryChangedCriterion.Conditions.items(ARROWPROOF_VEST))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_arrowproof_vest"));
		USE_SLINGSHOT = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_NEW_RANGED.apply(registryLookup))
				.display(SLINGSHOT,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "use_slingshot"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "use_slingshot"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
				.criterion("use_slingshot", ShotProjectileCriterion.Conditions.ranged(SLINGSHOT).build().create())
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/use_slingshot"));
		USE_SLINGSHOT_WITH_ENDER_PEARL = registryLookup -> Advancement.Builder.create()
				.parent(USE_SLINGSHOT.apply(registryLookup))
				.display(Items.ENDER_PEARL,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "use_slingshot_with_ender_pearl"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "use_slingshot_with_ender_pearl"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
				.criterion("use_slingshot", ShotProjectileCriterion.Conditions.ranged(SLINGSHOT).projectile(Optional.of(EntityPredicate.Builder.create().type(EntityType.ENDER_PEARL).build())).build().create())
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/use_slingshot_with_ender_pearl"));
		USE_SLINGSHOT_WITH_POTION = registryLookup -> Advancement.Builder.create()
				.parent(USE_SLINGSHOT.apply(registryLookup))
				.display(new AdvancementDisplay(
						POTION_STACK,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "use_slingshot_with_potion"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "use_slingshot_with_potion"),
						Optional.empty(),
						AdvancementFrame.TASK,
						true,
						true,
						false))
				.criterion("use_slingshot", ShotProjectileCriterion.Conditions.ranged(SLINGSHOT).projectile(Optional.of(EntityPredicate.Builder.create().type(EntityType.POTION).build())).build().create())
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/use_slingshot_with_potion"));
		HAVE_A_JUGER_REPEATING_CROSSBOW = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_NEW_RANGED.apply(registryLookup))
				.display(JUGER_REPEATING_CROSSBOW,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_juger_repeating_crossbow"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_juger_repeating_crossbow"),
						null,
						AdvancementFrame.GOAL,
						true,
						true,
						false)
				.criterion(RecipeProvider.hasItem(JUGER_REPEATING_CROSSBOW), InventoryChangedCriterion.Conditions.items(JUGER_REPEATING_CROSSBOW))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_juger_repeating_crossbow"));
		HAVE_LEGEND_JUGER_REPEATING_CROSSBOW = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_JUGER_REPEATING_CROSSBOW.apply(registryLookup))
				.display(JUGER_REPEATING_CROSSBOW,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_legend_juger_repeating_crossbow"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_legend_juger_repeating_crossbow"),
						null,
						AdvancementFrame.CHALLENGE,
						true,
						true,
						true)
				.criterion(RecipeProvider.hasItem(JUGER_REPEATING_CROSSBOW), InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().items(JUGER_REPEATING_CROSSBOW)
						.enchantment(new EnchantmentPredicate(Enchantments.MULTISHOT, NumberRange.IntRange.exactly(1)))
						.enchantment(new EnchantmentPredicate(Enchantments.QUICK_CHARGE, NumberRange.IntRange.exactly(3)))
						.enchantment(new EnchantmentPredicate(Enchantments.UNBREAKING, NumberRange.IntRange.exactly(5))).build()))
				.rewards(AdvancementRewards.Builder.experience(100))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_legend_juger_repeating_crossbow"));
		USE_JUGER_REPEATING_CROSSBOW_SHOT_1000_ARROWS = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_JUGER_REPEATING_CROSSBOW.apply(registryLookup))
				.display(Items.ARROW,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "use_juger_repeating_crossbow_shot_1000_arrows"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "use_juger_repeating_crossbow_shot_1000_arrows"),
						null,
						AdvancementFrame.CHALLENGE,
						true,
						true,
						true)
				.criterion("use_juger_repeating_crossbow_shot_1000_arrows", ShotProjectileCriterion.Conditions.ranged(JUGER_REPEATING_CROSSBOW).count(NumberRange.IntRange.atLeast(1000)).build().create())
				.rewards(AdvancementRewards.Builder.experience(100))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/use_juger_repeating_crossbow_shot_1000_arrows"));
		USE_JUGER_REPEATING_CROSSBOW_KILL_100_MONSTERS = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_JUGER_REPEATING_CROSSBOW.apply(registryLookup))
				.display(Items.SKELETON_SKULL,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "use_juger_repeating_crossbow_kill_100_monsters"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "use_juger_repeating_crossbow_kill_100_monsters"),
						null,
						AdvancementFrame.CHALLENGE,
						true,
						true,
						true)
				.criterion("use_juger_repeating_crossbow_kill_100_monsters", RangedKilledEntityCriterion.Conditions.ranged(JUGER_REPEATING_CROSSBOW)
						.target(EntityPredicate.Builder.create().type(SilkEntityTypeTags.MONSTERS).build()).killed(NumberRange.IntRange.atLeast(100)).build().create())
				.rewards(AdvancementRewards.Builder.experience(100))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/use_juger_repeating_crossbow_kill_100_monsters"));
		HAVE_A_MARKS_CROSSBOW = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_NEW_RANGED.apply(registryLookup))
				.display(MARKS_CROSSBOW,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_marks_crossbow"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_marks_crossbow"),
						null,
						AdvancementFrame.GOAL,
						true,
						true,
						false)
				.criterion(RecipeProvider.hasItem(MARKS_CROSSBOW), InventoryChangedCriterion.Conditions.items(MARKS_CROSSBOW))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_a_marks_crossbow"));
		MARKSMAN = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_MARKS_CROSSBOW.apply(registryLookup))
				.display(Items.TARGET,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "marksman"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "marksman"),
						null,
						AdvancementFrame.CHALLENGE,
						true,
						true,
						true)
				.criterion("bullseye", TargetHitCriterion.Conditions.create(NumberRange.IntRange.exactly(15),
						Optional.of(LootContextPredicate.create(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().distance(DistancePredicate.horizontal(NumberRange.DoubleRange.atLeast(200)))).build()))))
				.rewards(AdvancementRewards.Builder.experience(100))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/marksman"));
		SNIPING = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_MARKS_CROSSBOW.apply(registryLookup))
				.display(STEEL_ARROW,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "sniping"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "sniping"),
						null,
						AdvancementFrame.CHALLENGE,
						true,
						true,
						true)
				.criterion("killed_stray", OnKilledCriterion.Conditions.createEntityKilledPlayer(Optional.of(EntityPredicate.Builder.create().type(EntityType.STRAY).distance(DistancePredicate.horizontal(NumberRange.DoubleRange.exactly(100))).build())))
				.rewards(AdvancementRewards.Builder.experience(100))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/sniping"));
		HAVE_LEGEND_MARKS_CROSSBOW = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_MARKS_CROSSBOW.apply(registryLookup))
				.display(MARKS_CROSSBOW,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_legend_marks_crossbow"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_legend_marks_crossbow"),
						null,
						AdvancementFrame.CHALLENGE,
						true,
						true,
						true)
				.criterion(RecipeProvider.hasItem(MARKS_CROSSBOW), InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().items(MARKS_CROSSBOW)
						.enchantment(new EnchantmentPredicate(Enchantments.PIERCING, NumberRange.IntRange.exactly(4)))
						.enchantment(new EnchantmentPredicate(Enchantments.QUICK_CHARGE, NumberRange.IntRange.exactly(3)))
						.enchantment(new EnchantmentPredicate(Enchantments.UNBREAKING, NumberRange.IntRange.exactly(5))).build()))
				.rewards(AdvancementRewards.Builder.experience(100))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_legend_marks_crossbow"));
		HAVE_ALL_BASIC_RANGED = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_NEW_RANGED.apply(registryLookup))
				.display(ARBALEST,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_all_basic_ranged"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_all_basic_ranged"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
				.criterion(RecipeProvider.hasItem(SLINGSHOT), InventoryChangedCriterion.Conditions.items(SLINGSHOT))
				.criterion(RecipeProvider.hasItem(RECURVE_BOW), InventoryChangedCriterion.Conditions.items(RECURVE_BOW))
				.criterion(RecipeProvider.hasItem(ARBALEST), InventoryChangedCriterion.Conditions.items(ARBALEST))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_all_basic_ranged"));
		HAVE_ALL_ENHANCED_RANGED = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_ALL_BASIC_RANGED.apply(registryLookup))
				.display(JUGER_REPEATING_CROSSBOW,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_all_enhanced_ranged"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_all_enhanced_ranged"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
				.criterion(RecipeProvider.hasItem(COMPOUND_BOW), InventoryChangedCriterion.Conditions.items(COMPOUND_BOW))
				.criterion(RecipeProvider.hasItem(JUGER_REPEATING_CROSSBOW), InventoryChangedCriterion.Conditions.items(JUGER_REPEATING_CROSSBOW))
				.criterion(RecipeProvider.hasItem(MARKS_CROSSBOW), InventoryChangedCriterion.Conditions.items(MARKS_CROSSBOW))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_all_enhanced_ranged"));
		HAVE_ALL_RANGED = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_ALL_ENHANCED_RANGED.apply(registryLookup))
				.display(MARKS_CROSSBOW,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_all_ranged"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_all_ranged"),
						null,
						AdvancementFrame.CHALLENGE,
						true,
						true,
						true)
				.criterion(RecipeProvider.hasItem(Items.BOW), InventoryChangedCriterion.Conditions.items(Items.BOW))
				.criterion(RecipeProvider.hasItem(Items.CROSSBOW), InventoryChangedCriterion.Conditions.items(Items.CROSSBOW))
				.criterion(RecipeProvider.hasItem(SLINGSHOT), InventoryChangedCriterion.Conditions.items(SLINGSHOT))
				.criterion(RecipeProvider.hasItem(RECURVE_BOW), InventoryChangedCriterion.Conditions.items(RECURVE_BOW))
				.criterion(RecipeProvider.hasItem(ARBALEST), InventoryChangedCriterion.Conditions.items(ARBALEST))
				.criterion(RecipeProvider.hasItem(COMPOUND_BOW), InventoryChangedCriterion.Conditions.items(COMPOUND_BOW))
				.criterion(RecipeProvider.hasItem(JUGER_REPEATING_CROSSBOW), InventoryChangedCriterion.Conditions.items(JUGER_REPEATING_CROSSBOW))
				.criterion(RecipeProvider.hasItem(MARKS_CROSSBOW), InventoryChangedCriterion.Conditions.items(MARKS_CROSSBOW))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), VANILLA_PATH + "/have_all_ranged"));
		SR_ROOT = registryLookup -> Advancement.Builder.create()
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
		FIND_A_SPIDER_BIOME = registryLookup -> Advancement.Builder.create()
				.parent(SR_ROOT.apply(registryLookup))
				.display(COBWEBBY_SOIL,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "find_a_spider_biome"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "find_a_spider_biome"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
				.criterion("biome", TickCriterion.Conditions.createLocation(Optional.of(EntityPredicate.Builder.create().location(LocationPredicate.Builder.createBiome(registryLookup.getWrapperOrThrow(RegistryKeys.BIOME).getOrThrow(BiomeKeys.BADLANDS))).build())))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), SR_PATH + "/find_a_spider_biome"));
		KILL_A_NEW_SPIDER = registryLookup -> Advancement.Builder.create()
				.parent(FIND_A_SPIDER_BIOME.apply(registryLookup))
				.display(SPIDER_LEG,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "kill_a_new_spider"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "kill_a_new_spider"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
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
		SHOT_SPRAY_POISON_SPIDER = registryLookup -> Advancement.Builder.create()
				.parent(KILL_A_NEW_SPIDER.apply(registryLookup))
				.display(new AdvancementDisplay(
						POISON_ARROW_STACK,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "shot_spray_poison_spider"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "shot_spray_poison_spider"),
						Optional.empty(),
						AdvancementFrame.TASK,
						true,
						true,
						false))
				.criterion("shot_spray_poison_spider", PlayerHurtEntityCriterion.Conditions.create(
						Optional.of(DamagePredicate.Builder.create().sourceEntity(EntityPredicate.Builder.create().type(EntityType.ARROW).build()).build()),
						Optional.of(EntityPredicate.Builder.create().type(EntityTypes.SPRAY_POISON_SPIDER).build())))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), SR_PATH + "/shot_spray_poison_spider"));
		HAVE_A_DEPOISON_SPIDER_LEG = registryLookup -> Advancement.Builder.create()
				.parent(KILL_A_NEW_SPIDER.apply(registryLookup))
				.display(DEPOISON_SPIDER_LEG,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_a_depoison_spider_leg"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_a_depoison_spider_leg"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
				.criterion(RecipeProvider.hasItem(DEPOISON_SPIDER_LEG), InventoryChangedCriterion.Conditions.items(DEPOISON_SPIDER_LEG))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), SR_PATH + "/have_a_depoison_spider_leg"));
		HAVE_EFFECT_SPIDER_CAMOUFLAGE = registryLookup -> Advancement.Builder.create()
				.parent(HAVE_A_DEPOISON_SPIDER_LEG.apply(registryLookup))
				.display(SPIDER_EGG_COCOON,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "have_effect_spider_camouflage"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "have_effect_spider_camouflage"),
						null,
						AdvancementFrame.TASK,
						true,
						true,
						false)
				.criterion(Objects.requireNonNull(Registries.STATUS_EFFECT.getId(StatusEffects.SPIDER_CAMOUFLAGE.value())).toString(), EffectsChangedCriterion.Conditions
						.create(EntityEffectPredicate.Builder.create().addEffect(StatusEffects.SPIDER_CAMOUFLAGE)))
				.build(new Identifier(SpontaneousReplace.DATA.getId(), SR_PATH + "/have_effect_spider_camouflage"));
		KILL_ALL_SPIDERS = registryLookup -> Advancement.Builder.create()
				.parent(KILL_A_NEW_SPIDER.apply(registryLookup))
				.display(Items.SPIDER_SPAWN_EGG,
						SilkAdvancement.getAdvancementTitle(SpontaneousReplace.DATA, "kill_all_spiders"),
						SilkAdvancement.getAdvancementDescription(SpontaneousReplace.DATA, "kill_all_spiders"),
						null,
						AdvancementFrame.CHALLENGE,
						true,
						true,
						false)
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
	}
	
	public AdvancementGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(output, registryLookup);
	}
	
	@Override
	public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
		generateVanillaAdvancement(registryLookup, consumer);
		generateSrAdvancement(registryLookup, consumer);
	}
	
	private void generateVanillaAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
		consumer.accept(VANILLA_ROOT.apply(registryLookup));
		consumer.accept(HAVE_A_NEW_METAL.apply(registryLookup));
		consumer.accept(HAVE_A_REFINED_COPPER.apply(registryLookup));
		consumer.accept(HAVE_A_REFINED_COPPER_PRODUCT.apply(registryLookup));
		consumer.accept(HAVE_A_ALLOY.apply(registryLookup));
		consumer.accept(UPGRADE_IRON_PICKAXE.apply(registryLookup));
		consumer.accept(UPGRADE_IRON_ARMOR.apply(registryLookup));
		consumer.accept(HAVE_A_STEEL_PRODUCT.apply(registryLookup));
		consumer.accept(HAVE_ALL_STEEL_ARMORS.apply(registryLookup));
		consumer.accept(HAVE_ALL_NEW_METALS.apply(registryLookup));
		consumer.accept(HAVE_A_NEW_RANGED.apply(registryLookup));
		consumer.accept(HAVE_A_ARROWPROOF_VEST.apply(registryLookup));
		consumer.accept(USE_SLINGSHOT.apply(registryLookup));
		consumer.accept(USE_SLINGSHOT_WITH_ENDER_PEARL.apply(registryLookup));
		consumer.accept(USE_SLINGSHOT_WITH_POTION.apply(registryLookup));
		consumer.accept(HAVE_A_JUGER_REPEATING_CROSSBOW.apply(registryLookup));
		consumer.accept(HAVE_LEGEND_JUGER_REPEATING_CROSSBOW.apply(registryLookup));
		consumer.accept(USE_JUGER_REPEATING_CROSSBOW_SHOT_1000_ARROWS.apply(registryLookup));
		consumer.accept(USE_JUGER_REPEATING_CROSSBOW_KILL_100_MONSTERS.apply(registryLookup));
		consumer.accept(HAVE_A_MARKS_CROSSBOW.apply(registryLookup));
		consumer.accept(HAVE_LEGEND_MARKS_CROSSBOW.apply(registryLookup));
		consumer.accept(MARKSMAN.apply(registryLookup));
		consumer.accept(SNIPING.apply(registryLookup));
		consumer.accept(HAVE_ALL_BASIC_RANGED.apply(registryLookup));
		consumer.accept(HAVE_ALL_ENHANCED_RANGED.apply(registryLookup));
		consumer.accept(HAVE_ALL_RANGED.apply(registryLookup));
	}
	
	private void generateSrAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
		consumer.accept(SR_ROOT.apply(registryLookup));
		consumer.accept(FIND_A_SPIDER_BIOME.apply(registryLookup));
		consumer.accept(KILL_A_NEW_SPIDER.apply(registryLookup));
		consumer.accept(SHOT_SPRAY_POISON_SPIDER.apply(registryLookup));
		consumer.accept(HAVE_A_DEPOISON_SPIDER_LEG.apply(registryLookup));
		consumer.accept(HAVE_EFFECT_SPIDER_CAMOUFLAGE.apply(registryLookup));
		consumer.accept(KILL_ALL_SPIDERS.apply(registryLookup));
	}
}
