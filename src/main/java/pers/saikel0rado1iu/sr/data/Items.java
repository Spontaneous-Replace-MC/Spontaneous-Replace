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

package pers.saikel0rado1iu.sr.data;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import pers.saikel0rado1iu.silk.api.registry.SilkItem;
import pers.saikel0rado1iu.silk.util.PlayerUtil;
import pers.saikel0rado1iu.silk.util.TickUtil;
import pers.saikel0rado1iu.sr.vanilla.aucu.AuCuArmor;
import pers.saikel0rado1iu.sr.vanilla.aucu.AuCuTool;
import pers.saikel0rado1iu.sr.vanilla.copper.RefinedCopperArmor;
import pers.saikel0rado1iu.sr.vanilla.copper.RefinedCopperTool;
import pers.saikel0rado1iu.sr.vanilla.cufe.CuFeArmor;
import pers.saikel0rado1iu.sr.vanilla.cufe.CuFeTool;
import pers.saikel0rado1iu.sr.vanilla.ranged.*;
import pers.saikel0rado1iu.sr.vanilla.ranged.armor.ArrowproofVest;
import pers.saikel0rado1iu.sr.vanilla.ranged.projectile.steelarrow.SteelArrowItem;
import pers.saikel0rado1iu.sr.vanilla.ranged.projectile.stoneball.StoneballItem;
import pers.saikel0rado1iu.sr.vanilla.steel.SteelArmor;
import pers.saikel0rado1iu.sr.vanilla.steel.SteelTool;
import pers.saikel0rado1iu.sr.variant.general.block.eerie.EerieRindBehavior;
import pers.saikel0rado1iu.sr.variant.spider.equipment.SpiderLeatherArmor;
import pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderData;
import pers.saikel0rado1iu.sr.variant.spider.mob.guard.GuardSpiderData;
import pers.saikel0rado1iu.sr.variant.spider.mob.larva.SpiderLarvaData;
import pers.saikel0rado1iu.sr.variant.spider.mob.spray.SprayPoisonSpiderData;
import pers.saikel0rado1iu.sr.variant.spider.mob.weaving.WeavingWebSpiderData;

import static pers.saikel0rado1iu.sr.data.EntityTypes.*;

/**
 * <h2 style="color:FFC800">自然更替的所有的物品</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class Items extends SilkItem {
	public static final Item ICON = builder(new Item(new Item.Settings())).build(SpontaneousReplace.DATA, "icon");
	public static final Item COPPER_FOR_SMELTING_INGOT = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "copper_for_smelting_ingot");
	public static final BlockItem COPPER_FOR_SMELTING_INGOT_BLOCK = builder(new BlockItem(Blocks.COPPER_FOR_SMELTING_INGOT_BLOCK, new Item.Settings()))
			.group(ItemGroups.BUILDING_BLOCKS).build(SpontaneousReplace.DATA, "copper_for_smelting_ingot_block");
	public static final Item REFINED_COPPER_INGOT = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "refined_copper_ingot");
	public static final Item REFINED_COPPER_NUGGET = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "refined_copper_nugget");
	public static final BlockItem REFINED_COPPER_BLOCK = builder(new BlockItem(Blocks.REFINED_COPPER_BLOCK, new Item.Settings()))
			.group(ItemGroups.BUILDING_BLOCKS).build(SpontaneousReplace.DATA, "refined_copper_block");
	public static final ShovelItem REFINED_COPPER_SHOVEL = builder(RefinedCopperTool.MATERIAL.createShovel(3.5F, new Item.Settings()))
			.group(ItemGroups.TOOLS).build(SpontaneousReplace.DATA, "refined_copper_shovel");
	public static final PickaxeItem REFINED_COPPER_PICKAXE = builder(RefinedCopperTool.MATERIAL.createPickaxe(3, new Item.Settings()))
			.group(ItemGroups.TOOLS).build(SpontaneousReplace.DATA, "refined_copper_pickaxe");
	public static final AxeItem REFINED_COPPER_AXE = builder(RefinedCopperTool.MATERIAL.createAxe(9, 0.9F, new Item.Settings()))
			.group(ItemGroups.TOOLS, ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "refined_copper_axe");
	public static final HoeItem REFINED_COPPER_HOE = builder(RefinedCopperTool.MATERIAL.createHoe(2, new Item.Settings()))
			.group(ItemGroups.TOOLS).build(SpontaneousReplace.DATA, "refined_copper_hoe");
	public static final SwordItem REFINED_COPPER_SWORD = builder(RefinedCopperTool.MATERIAL.createSword(6, new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "refined_copper_sword");
	public static final ArmorItem REFINED_COPPER_HELMET = builder(RefinedCopperArmor.MATERIAL.createHelmet(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "refined_copper_helmet");
	public static final ArmorItem REFINED_COPPER_CHESTPLATE = builder(RefinedCopperArmor.MATERIAL.createChestplate(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "refined_copper_chestplate");
	public static final ArmorItem REFINED_COPPER_LEGGINGS = builder(RefinedCopperArmor.MATERIAL.createLeggings(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "refined_copper_leggings");
	public static final ArmorItem REFINED_COPPER_BOOTS = builder(RefinedCopperArmor.MATERIAL.createBoots(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "refined_copper_boots");
	public static final Item CUFE_ALLOY = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "cufe_alloy");
	public static final BlockItem CUFE_ALLOY_BLOCK = builder(new BlockItem(Blocks.CUFE_ALLOY_BLOCK, new Item.Settings()))
			.group(ItemGroups.BUILDING_BLOCKS).build(SpontaneousReplace.DATA, "cufe_alloy_block");
	public static final Item CUFE_ALLOY_INGOT = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "cufe_alloy_ingot");
	public static final Item CUFE_ALLOY_NUGGET = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "cufe_alloy_nugget");
	public static final BlockItem CUFE_BLOCK = builder(new BlockItem(Blocks.CUFE_BLOCK, new Item.Settings()))
			.group(ItemGroups.BUILDING_BLOCKS).build(SpontaneousReplace.DATA, "cufe_block");
	public static final ShovelItem CUFE_ALLOY_SHOVEL = builder(CuFeTool.MATERIAL.createShovel(4.5F, new Item.Settings()))
			.group(ItemGroups.TOOLS).build(SpontaneousReplace.DATA, "cufe_alloy_shovel");
	public static final PickaxeItem CUFE_ALLOY_PICKAXE = builder(CuFeTool.MATERIAL.createPickaxe(4, new Item.Settings()))
			.group(ItemGroups.TOOLS).build(SpontaneousReplace.DATA, "cufe_alloy_pickaxe");
	public static final AxeItem CUFE_ALLOY_AXE = builder(CuFeTool.MATERIAL.createAxe(9, 0.9F, new Item.Settings()))
			.group(ItemGroups.TOOLS, ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "cufe_alloy_axe");
	public static final HoeItem CUFE_ALLOY_HOE = builder(CuFeTool.MATERIAL.createHoe(3, new Item.Settings()))
			.group(ItemGroups.TOOLS).build(SpontaneousReplace.DATA, "cufe_alloy_hoe");
	public static final SwordItem CUFE_ALLOY_SWORD = builder(CuFeTool.MATERIAL.createSword(6, new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "cufe_alloy_sword");
	public static final ArmorItem CUFE_ALLOY_HELMET = builder(CuFeArmor.MATERIAL.createHelmet(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "cufe_alloy_helmet");
	public static final ArmorItem CUFE_ALLOY_CHESTPLATE = builder(CuFeArmor.MATERIAL.createChestplate(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "cufe_alloy_chestplate");
	public static final ArmorItem CUFE_ALLOY_LEGGINGS = builder(CuFeArmor.MATERIAL.createLeggings(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "cufe_alloy_leggings");
	public static final ArmorItem CUFE_ALLOY_BOOTS = builder(CuFeArmor.MATERIAL.createBoots(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "cufe_alloy_boots");
	public static final Item AUCU_ALLOY = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "aucu_alloy");
	public static final BlockItem AUCU_ALLOY_BLOCK = builder(new BlockItem(Blocks.AUCU_ALLOY_BLOCK, new Item.Settings()))
			.group(ItemGroups.BUILDING_BLOCKS).build(SpontaneousReplace.DATA, "aucu_alloy_block");
	public static final Item AUCU_ALLOY_INGOT = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "aucu_alloy_ingot");
	public static final Item AUCU_ALLOY_NUGGET = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "aucu_alloy_nugget");
	public static final BlockItem AUCU_BLOCK = builder(new BlockItem(Blocks.AUCU_BLOCK, new Item.Settings()))
			.group(ItemGroups.BUILDING_BLOCKS).build(SpontaneousReplace.DATA, "aucu_block");
	public static final ShovelItem AUCU_ALLOY_SHOVEL = builder(AuCuTool.MATERIAL.createShovel(3.5F, new Item.Settings()))
			.group(ItemGroups.TOOLS).build(SpontaneousReplace.DATA, "aucu_alloy_shovel");
	public static final PickaxeItem AUCU_ALLOY_PICKAXE = builder(AuCuTool.MATERIAL.createPickaxe(2, new Item.Settings()))
			.group(ItemGroups.TOOLS).build(SpontaneousReplace.DATA, "aucu_alloy_pickaxe");
	public static final AxeItem AUCU_ALLOY_AXE = builder(AuCuTool.MATERIAL.createAxe(9, 0.8F, new Item.Settings()))
			.group(ItemGroups.TOOLS, ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "aucu_alloy_axe");
	public static final HoeItem AUCU_ALLOY_HOE = builder(AuCuTool.MATERIAL.createHoe(2, new Item.Settings()))
			.group(ItemGroups.TOOLS).build(SpontaneousReplace.DATA, "aucu_alloy_hoe");
	public static final SwordItem AUCU_ALLOY_SWORD = builder(AuCuTool.MATERIAL.createSword(5, new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "aucu_alloy_sword");
	public static final ArmorItem AUCU_ALLOY_HELMET = builder(AuCuArmor.MATERIAL.createHelmet(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "aucu_alloy_helmet");
	public static final ArmorItem AUCU_ALLOY_CHESTPLATE = builder(AuCuArmor.MATERIAL.createChestplate(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "aucu_alloy_chestplate");
	public static final ArmorItem AUCU_ALLOY_LEGGINGS = builder(AuCuArmor.MATERIAL.createLeggings(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "aucu_alloy_leggings");
	public static final ArmorItem AUCU_ALLOY_BOOTS = builder(AuCuArmor.MATERIAL.createBoots(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "aucu_alloy_boots");
	public static final Item CLEAN_COAL = builder(new Item(new Item.Settings())).otherRegister(item -> FuelRegistry.INSTANCE.add(item, FuelRegistry.INSTANCE.get(net.minecraft.item.Items.COAL) * 4))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "clean_coal");
	public static final Item PIG_IRON = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "pig_iron");
	public static final BlockItem PIG_IRON_BLOCK = builder(new BlockItem(Blocks.PIG_IRON_BLOCK, new Item.Settings()))
			.group(ItemGroups.BUILDING_BLOCKS).build(SpontaneousReplace.DATA, "pig_alloy_block");
	public static final Item STEEL_INGOT = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "steel_ingot");
	public static final BlockItem STEEL_BLOCK = builder(new BlockItem(Blocks.STEEL_BLOCK, new Item.Settings()))
			.group(ItemGroups.BUILDING_BLOCKS).build(SpontaneousReplace.DATA, "steel_block");
	public static final ShovelItem STEEL_SHOVEL = builder(SteelTool.MATERIAL.createShovel(5.5F, new Item.Settings()))
			.group(ItemGroups.TOOLS).build(SpontaneousReplace.DATA, "steel_shovel");
	public static final PickaxeItem STEEL_PICKAXE = builder(SteelTool.MATERIAL.createPickaxe(5, new Item.Settings()))
			.group(ItemGroups.TOOLS).build(SpontaneousReplace.DATA, "steel_pickaxe");
	public static final AxeItem STEEL_AXE = builder(SteelTool.MATERIAL.createAxe(9, 0.9F, new Item.Settings()))
			.group(ItemGroups.TOOLS, ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "steel_axe");
	public static final HoeItem STEEL_HOE = builder(SteelTool.MATERIAL.createHoe(4, new Item.Settings()))
			.group(ItemGroups.TOOLS).build(SpontaneousReplace.DATA, "steel_hoe");
	public static final SwordItem STEEL_SWORD = builder(SteelTool.MATERIAL.createSword(7, new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "steel_sword");
	public static final ArmorItem STEEL_HELMET = builder(SteelArmor.MATERIAL.createHelmet(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "steel_helmet");
	public static final ArmorItem STEEL_CHESTPLATE = builder(SteelArmor.MATERIAL.createChestplate(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "steel_chestplate");
	public static final ArmorItem STEEL_LEGGINGS = builder(SteelArmor.MATERIAL.createLeggings(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "steel_leggings");
	public static final ArmorItem STEEL_BOOTS = builder(SteelArmor.MATERIAL.createBoots(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "steel_boots");
	public static final StoneballItem STONEBALL = builder(new StoneballItem(new Item.Settings().maxCount(net.minecraft.item.Items.SNOWBALL.getMaxCount())))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "stoneball");
	public static final SteelArrowItem STEEL_ARROW = builder(new SteelArrowItem(new Item.Settings().maxCount(4)))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "steel_arrow");
	public static final Slingshot SLINGSHOT = builder(new Slingshot(new Item.Settings().maxDamageIfAbsent(net.minecraft.item.Items.BOW.getMaxDamage() / 2)))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "slingshot");
	public static final RecurveBow RECURVE_BOW = builder(new RecurveBow(new Item.Settings().maxDamageIfAbsent(net.minecraft.item.Items.BOW.getMaxDamage() * 2)))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "recurve_bow");
	public static final Arbalest ARBALEST = builder(new Arbalest(new Item.Settings().maxDamageIfAbsent(net.minecraft.item.Items.CROSSBOW.getMaxDamage() * 2)))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "arbalest");
	public static final CompoundBow COMPOUND_BOW = builder(new CompoundBow(new Item.Settings().maxDamageIfAbsent(net.minecraft.item.Items.BOW.getMaxDamage() * 5)))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "compound_bow");
	public static final JugerRepeatingCrossbow JUGER_REPEATING_CROSSBOW = builder(new JugerRepeatingCrossbow(new Item.Settings().maxDamageIfAbsent(net.minecraft.item.Items.BOW.getMaxDamage() * 3).fireproof()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "juger_repeating_crossbow");
	public static final MarksCrossbow MARKS_CROSSBOW = builder(new MarksCrossbow(new Item.Settings().maxDamageIfAbsent(net.minecraft.item.Items.BOW.getMaxDamage() * 3).fireproof()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "marks_crossbow");
	public static final ArrowproofVest.Item ARROWPROOF_VEST = builder(ArrowproofVest.MATERIAL.createChestplate(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "arrowproof_vest");
	public static final BlockItem EERIE_REGOLITH = builder(new BlockItem(Blocks.EERIE_REGOLITH, new Item.Settings()))
			.group(ItemGroups.NATURAL).build(SpontaneousReplace.DATA, "eerie_regolith");
	public static final BlockItem TREACHEROUS_SLUDGE = builder(new BlockItem(Blocks.TREACHEROUS_SLUDGE, new Item.Settings()))
			.group(ItemGroups.NATURAL).build(SpontaneousReplace.DATA, "treacherous_sludge");
	public static final BlockItem COBWEBBY_SOIL = builder(new BlockItem(Blocks.COBWEBBY_SOIL, new Item.Settings()))
			.group(ItemGroups.NATURAL).build(SpontaneousReplace.DATA, "cobwebby_soil");
	public static final BlockItem EERIE_RIND = builder(new BlockItem(Blocks.EERIE_RIND, new Item.Settings().fireproof())).otherRegister(blockItem -> {
				EerieRindBehavior.registerBehavior();
				Item.BLOCK_ITEMS.put(Blocks.WATER_EERIE_RIND, blockItem);
				Item.BLOCK_ITEMS.put(Blocks.POWDER_SNOW_EERIE_RIND, blockItem);
				Item.BLOCK_ITEMS.put(Blocks.ICE_EERIE_RIND, blockItem);
				Item.BLOCK_ITEMS.put(Blocks.LAVA_EERIE_RIND, blockItem);
			})
			.group(ItemGroups.NATURAL).build(SpontaneousReplace.DATA, "eerie_rind");
	public static final BlockItem EERIE_BOUGH = builder(new BlockItem(Blocks.EERIE_BOUGH, new Item.Settings().fireproof()))
			.group(ItemGroups.NATURAL).build(SpontaneousReplace.DATA, "eerie_bough");
	public static final BlockItem TREACHEROUS_SAC = builder(new BlockItem(Blocks.TREACHEROUS_SAC, new Item.Settings()))
			.group(ItemGroups.NATURAL).build(SpontaneousReplace.DATA, "treacherous_sac");
	public static final BlockItem TREACHEROUS_VINES = builder(new BlockItem(Blocks.TREACHEROUS_VINES, new Item.Settings()))
			.group(ItemGroups.NATURAL).build(SpontaneousReplace.DATA, "treacherous_vines");
	public static final BlockItem GOSSAMER_CARPET = builder(new BlockItem(Blocks.GOSSAMER_CARPET, new Item.Settings()))
			.group(ItemGroups.NATURAL).build(SpontaneousReplace.DATA, "gossamer_carpet");
	public static final BlockItem GOSSAMERY_LEAVES = builder(new BlockItem(Blocks.GOSSAMERY_LEAVES, new Item.Settings()))
			.group(ItemGroups.NATURAL).build(SpontaneousReplace.DATA, "gossamery_leaves");
	public static final BlockItem STICKY_COMPACT_COBWEB = builder(new BlockItem(Blocks.STICKY_COMPACT_COBWEB, new Item.Settings()))
			.group(ItemGroups.NATURAL).build(SpontaneousReplace.DATA, "sticky_compact_cobweb");
	public static final BlockItem SPIDER_CHRYSALIS = builder(new BlockItem(Blocks.SPIDER_CHRYSALIS, new Item.Settings()))
			.group(ItemGroups.NATURAL).build(SpontaneousReplace.DATA, "spider_chrysalis");
	public static final BlockItem SPIDER_EGG_COCOON = builder(new BlockItem(Blocks.SPIDER_EGG_COCOON, new Item.Settings()))
			.group(ItemGroups.NATURAL).build(SpontaneousReplace.DATA, "spider_egg_cocoon");
	public static final FoodComponent SPIDER_LEG_FOOD_COMPONENT = new FoodComponent.Builder().meat().hunger(4)
			.statusEffect(new StatusEffectInstance(StatusEffects.POISON, TickUtil.getTick(10), 1), 1).build();
	public static final Item SPIDER_LEG = builder(new Item(new Item.Settings().food(SPIDER_LEG_FOOD_COMPONENT)))
			.group(ItemGroups.FOOD_AND_DRINK, ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "spider_leg");
	public static final Item SPIDER_LEATHER = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "spider_leather");
	public static final Item SPIDER_FANG = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "spider_fang");
	public static final Item COMPACT_GOSSAMER = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "compact_gossamer");
	public static final Item STICKY_COMPACT_GOSSAMER = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "sticky_compact_gossamer");
	public static final FoodComponent DEPOISON_SPIDER_LEG_FOOD_COMPONENT = new FoodComponent.Builder().meat().hunger(5).saturationModifier(PlayerUtil.getSaturationRatio(3))
			.statusEffect(new StatusEffectInstance(StatusEffects.POISON, TickUtil.getTick(5), 0), 0.25F).build();
	public static final Item DEPOISON_SPIDER_LEG = builder(new Item(new Item.Settings().food(DEPOISON_SPIDER_LEG_FOOD_COMPONENT)))
			.group(ItemGroups.FOOD_AND_DRINK, ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "depoison_spider_leg");
	public static final Item COMPACT_STRING = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "compact_string");
	public static final Item COMPOSITE_STRING = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "composite_string");
	public static final Item COMPOSITE_FABRIC = builder(new Item(new Item.Settings()))
			.group(ItemGroups.INGREDIENTS).build(SpontaneousReplace.DATA, "composite_fabric");
	public static final ArmorItem SPIDER_LEATHER_CAP = builder(SpiderLeatherArmor.MATERIAL.createHelmet(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "spider_leather_cap");
	public static final ArmorItem SPIDER_LEATHER_TUNIC = builder(SpiderLeatherArmor.MATERIAL.createChestplate(new Item.Settings()))
			.group(ItemGroups.COMBAT).build(SpontaneousReplace.DATA, "spider_leather_tunic");
	public static final SpawnEggItem SPIDER_LARVA_SPAWN_EGG = builder(new SpawnEggItem(SPIDER_LARVA, SpiderLarvaData.SPIDER_SKIN_COLOR, SpiderData.SPIDER_EYES_COLOR, new Item.Settings()))
			.group(ItemGroups.SPAWN_EGGS).build(SpontaneousReplace.DATA, SpiderLarvaData.ID);
	public static final SpawnEggItem GUARD_SPIDER_SPAWN_EGG = builder(new SpawnEggItem(GUARD_SPIDER, GuardSpiderData.SPIDER_SKIN_COLOR, SpiderData.SPIDER_EYES_COLOR, new Item.Settings()))
			.group(ItemGroups.SPAWN_EGGS).build(SpontaneousReplace.DATA, GuardSpiderData.ID);
	public static final SpawnEggItem SPRAY_POISON_SPIDER_SPAWN_EGG = builder(new SpawnEggItem(SPRAY_POISON_SPIDER, SprayPoisonSpiderData.SPIDER_SKIN_COLOR, SpiderData.SPIDER_EYES_COLOR, new Item.Settings()))
			.group(ItemGroups.SPAWN_EGGS).build(SpontaneousReplace.DATA, SprayPoisonSpiderData.ID);
	public static final SpawnEggItem WEAVING_WEB_SPIDER_SPAWN_EGG = builder(new SpawnEggItem(WEAVING_WEB_SPIDER, WeavingWebSpiderData.SPIDER_SKIN_COLOR, SpiderData.SPIDER_EYES_COLOR, new Item.Settings()))
			.group(ItemGroups.SPAWN_EGGS).build(SpontaneousReplace.DATA, WeavingWebSpiderData.ID);
}
