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
import net.minecraft.registry.RegistryWrapper;
import pers.saikel0rado1iu.silk.api.pack.DataPack;
import pers.saikel0rado1iu.silk.api.pack.ResourcesPack;
import pers.saikel0rado1iu.silk.gen.data.SilkLanguageProvider;
import pers.saikel0rado1iu.silk.util.TextUtil;
import pers.saikel0rado1iu.sr.data.*;
import pers.saikel0rado1iu.sr.gen.world.WorldPresets;

import java.util.concurrent.CompletableFuture;

import static pers.saikel0rado1iu.silk.util.TextUtil.widgetText;

/**
 * <h2 style="color:FFC800">自然更替的本地化生成器</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface LocalizationGenerator {
	final class EnUs extends SilkLanguageProvider {
		public EnUs(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
			super(dataOutput, "en_us", registryLookup);
		}
		
		@Override
		public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
			translationBuilder.add(getCommentKey("title"), "Origin Language is Simplified Chinese(zh_cn)");
			translationBuilder.add(getCommentKey("modmenu"), "Translatable Descriptions and Summaries in Mod Menu");
			translationBuilder.add(getLocalizationNameKey(SpontaneousReplace.DATA), "§6Spontaneous-Replace");
			translationBuilder.add(getLocalizationSummaryKey(SpontaneousReplace.DATA), "Provide players with enhanced vanilla adventures with new content");
			translationBuilder.add(getLocalizationDescriptionKey(SpontaneousReplace.DATA), """
					§lMod introduction:§r
					     The wind whimper, thunder roared and bolt scream. A certain energy in the world of Minecraft awakened the potential within the Mobs being.
					     The power of the five elements will lead you to the origin of everything. seek, explore, defeat or forgive them.
					     Whether you will become an admirer of all Mobs, or an endless slaughterer, everything is your choice...
					§lMod Vision:§r
					     I hope to make a gameplay mod that is based on the core of the vanilla game and does not destroy the vanilla gameplay. It is very difficult to develop on this basis. Whether an item is added, how to design data so as not to destroy the balance of the game, These are all points that developers need to consider.
					     If you think the mod is doing a good job, you are welcome to sponsor my project, or translate this mod, thank your very much!""");
			translationBuilder.add(getCommentKey("generic"), "Translation of general Mod components");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, ""), "Spontaneous-Replace");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.mod"), "Mod");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "target"), "0.5.X Target");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "target"), "    Continue to improve the new biological community, continue to add the original 0.5.0 plan did not achieve the content.");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "changelog"), "Changelog");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.synopsis"), "Synopsis");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "tab.synopsis.intro"), "§f§lMod introduction:");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.synopsis.intro"), """
					§r   The wind whimper, thunder roared and bolt scream. A certain energy in the world of Minecraft awakened the potential within the Mobs being.
					   The power of the five elements will lead you to the origin of everything. seek, explore, defeat or forgive them.
					   Whether you will become an admirer of all Mobs, or an endless slaughterer, everything is your choice...""");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "tab.synopsis.vision"), "§f§lMod Vision:");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.synopsis.vision"),
					"   I hope to make a gameplay mod that is based on the core of the vanilla game and does not destroy the vanilla gameplay. It is very difficult to develop on this basis. Whether an item is added, how to design data so as not to destroy the balance of the game, These are all points that developers need to consider.\n" +
							"   If you think the mod is doing a good job, you are welcome to sponsor my project, or translate this mod, thank your very much!");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.config"), "Setting");
			translationBuilder.add(widgetText(SpontaneousReplace.DATA, "homepage"), "Mod Website");
			translationBuilder.add(widgetText(SpontaneousReplace.DATA, "support"), "Support Us");
			translationBuilder.add(widgetText(SpontaneousReplace.DATA, "community"), "Mod Discord");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, ""), "Setting Spontaneous-Replace...");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, "update"), "Setting Mod Update");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, "update"), "");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, Configs.AUTO_SHOW_SETTINGS_BUTTON), "Auto Show Config Button");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, Configs.AUTO_SHOW_SETTINGS_BUTTON + ".on"), "Automatically close the option screen button if the Mod Menu is installed");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, Configs.AUTO_SHOW_SETTINGS_BUTTON + ".off"), "Always show the setting button in the options screen");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, Configs.DISABLE_WARNING_ADVICE), "Disable Warning Advice");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, Configs.DISABLE_WARNING_ADVICE + ".need_other_mod"), "Need Other Mod");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, Configs.DISABLE_WARNING_ADVICE + ".need_other_mod"), "Go to https://modrinth.com/mod/dcwa Install this Mod enable feature");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "generator.classic"), "Spontaneous-Replace:");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "generator.snapshot"), "SR Snapshot:");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "generator.non_version"), "Too Old");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "placeholder"), "This function has NOT been added YET, Please continue to pay ATTENTION to the homepage of this Mod to get UPDATES in time.");
			translationBuilder.add(getWorldPresetKey(WorldPresets.CLASSIC), "Spontaneous-Replace");
			translationBuilder.add(getWorldPresetKey(WorldPresets.SNAPSHOT), "SR Snapshot");
			translationBuilder.add(ResourcesPack.getName(SpontaneousReplace.DATA), "§6SR Default Resource");
			translationBuilder.add(DataPack.getName(SpontaneousReplace.DATA), "§6SR Default Data");
			translationBuilder.add(getCommentKey("effects"), "Translation of Status Effects");
			translationBuilder.add(StatusEffects.ACIDIZE.value(), "Acidize");
			translationBuilder.add(StatusEffects.SPIDER_CAMOUFLAGE.value(), "Spider Camouflage");
			translationBuilder.add(getCommentKey("death message"), "Translation of Death Message");
			translationBuilder.add("death.attack.acidize", "%1$s dissolved");
			translationBuilder.add("death.attack.acidize.player", "%1$s dissolved while fighting %2$s");
			translationBuilder.add("death.attack.spider_toxin", "%1$s failed to escape the %2$s's toxin");
			translationBuilder.add("death.attack.spider_toxin.item", "%1$s was toxined by %2$s using %3$s");
			translationBuilder.add(getCommentKey("itemGroup"), "Translation of item group");
			translationBuilder.add(ItemGroups.BUILDING_BLOCKS, "SR Building Blocks");
			translationBuilder.add(ItemGroups.COLORED_BLOCKS, "SR Colored Blocks");
			translationBuilder.add(ItemGroups.NATURAL, "SR Natural Blocks");
			translationBuilder.add(ItemGroups.FUNCTIONAL, "SR Functional Blocks");
			translationBuilder.add(ItemGroups.REDSTONE, "SR Redstone Blocks");
			translationBuilder.add(ItemGroups.TOOLS, "SR Tool & Utilities");
			translationBuilder.add(ItemGroups.COMBAT, "SR Combat");
			translationBuilder.add(ItemGroups.FOOD_AND_DRINK, "SR Food & Drinks");
			translationBuilder.add(ItemGroups.INGREDIENTS, "SR Ingredients");
			translationBuilder.add(ItemGroups.SPAWN_EGGS, "SR Spawn Eggs");
			translationBuilder.add(getCommentKey("vanilla"), "Translation of Vanilla Extension");
			translationBuilder.add(Items.COPPER_FOR_SMELTING_INGOT, "Copper For Smelting Ingot");
			translationBuilder.add(Items.REFINED_COPPER_INGOT, "Refined Copper Ingot");
			translationBuilder.add(Items.REFINED_COPPER_NUGGET, "Refined Copper Nugget");
			translationBuilder.add(Items.REFINED_COPPER_SHOVEL, "Refined Copper Shovel");
			translationBuilder.add(Items.REFINED_COPPER_PICKAXE, "Refined Copper Pickaxe");
			translationBuilder.add(Items.REFINED_COPPER_AXE, "Refined Copper Axe");
			translationBuilder.add(Items.REFINED_COPPER_HOE, "Refined Copper Hoe");
			translationBuilder.add(Items.REFINED_COPPER_SWORD, "Refined Copper Sword");
			translationBuilder.add(Items.REFINED_COPPER_HELMET, "Refined Copper Helmet");
			translationBuilder.add(Items.REFINED_COPPER_CHESTPLATE, "Refined Copper Chestplate");
			translationBuilder.add(Items.REFINED_COPPER_LEGGINGS, "Refined Copper Leggings");
			translationBuilder.add(Items.REFINED_COPPER_BOOTS, "Refined Copper Boots");
			translationBuilder.add(Items.CUFE_ALLOY, "CuFe Alloy");
			translationBuilder.add(Items.CUFE_ALLOY_INGOT, "CuFe Alloy Ingot");
			translationBuilder.add(Items.CUFE_ALLOY_NUGGET, "CuFe Alloy Nugget");
			translationBuilder.add(Items.CUFE_ALLOY_SHOVEL, "CuFe Alloy Shovel");
			translationBuilder.add(Items.CUFE_ALLOY_PICKAXE, "CuFe Alloy Pickaxe");
			translationBuilder.add(Items.CUFE_ALLOY_AXE, "CuFe Alloy Axe");
			translationBuilder.add(Items.CUFE_ALLOY_HOE, "CuFe Alloy Hoe");
			translationBuilder.add(Items.CUFE_ALLOY_SWORD, "CuFe Alloy Sword");
			translationBuilder.add(Items.CUFE_ALLOY_HELMET, "CuFe Alloy Helmet");
			translationBuilder.add(Items.CUFE_ALLOY_CHESTPLATE, "CuFe Alloy Chestplate");
			translationBuilder.add(Items.CUFE_ALLOY_LEGGINGS, "CuFe Alloy Leggings");
			translationBuilder.add(Items.CUFE_ALLOY_BOOTS, "CuFe Alloy Boots");
			translationBuilder.add(Items.AUCU_ALLOY, "AuCu Alloy");
			translationBuilder.add(Items.AUCU_ALLOY_INGOT, "AuCu Alloy Ingot");
			translationBuilder.add(Items.AUCU_ALLOY_NUGGET, "AuCu Alloy Nugget");
			translationBuilder.add(Items.AUCU_ALLOY_SHOVEL, "AuCu Alloy Shovel");
			translationBuilder.add(Items.AUCU_ALLOY_PICKAXE, "AuCu Alloy Pickaxe");
			translationBuilder.add(Items.AUCU_ALLOY_AXE, "AuCu Alloy Axe");
			translationBuilder.add(Items.AUCU_ALLOY_HOE, "AuCu Alloy Hoe");
			translationBuilder.add(Items.AUCU_ALLOY_SWORD, "AuCu Alloy Sword");
			translationBuilder.add(Items.AUCU_ALLOY_HELMET, "AuCu Alloy Helmet");
			translationBuilder.add(Items.AUCU_ALLOY_CHESTPLATE, "AuCu Alloy Chestplate");
			translationBuilder.add(Items.AUCU_ALLOY_LEGGINGS, "AuCu Alloy Leggings");
			translationBuilder.add(Items.AUCU_ALLOY_BOOTS, "AuCu Alloy Boots");
			translationBuilder.add(Items.CLEAN_COAL, "Clean Coal");
			translationBuilder.add(Items.PIG_IRON, "Pig Iron");
			translationBuilder.add(Items.STEEL_INGOT, "Steel Ingot");
			translationBuilder.add(Items.STEEL_SHOVEL, "Steel Shovel");
			translationBuilder.add(Items.STEEL_PICKAXE, "Steel Pickaxe");
			translationBuilder.add(Items.STEEL_AXE, "Steel Axe");
			translationBuilder.add(Items.STEEL_HOE, "Steel Hoe");
			translationBuilder.add(Items.STEEL_SWORD, "Steel Sword");
			translationBuilder.add(Items.STEEL_HELMET, "Steel Helmet");
			translationBuilder.add(Items.STEEL_CHESTPLATE, "Steel Chestplate");
			translationBuilder.add(Items.STEEL_LEGGINGS, "Steel Leggings");
			translationBuilder.add(Items.STEEL_BOOTS, "Steel Boots");
			translationBuilder.add(Items.STONEBALL, "Stoneball");
			translationBuilder.add(Items.STEEL_ARROW, "Steel Arrow");
			translationBuilder.add(Items.SLINGSHOT, "Slingshot");
			translationBuilder.add(Items.RECURVE_BOW, "Recurve Bow");
			translationBuilder.add(Items.ARBALEST, "Arbalest");
			translationBuilder.add(Items.COMPOUND_BOW, "Compound Bow");
			translationBuilder.add(Items.JUGER_REPEATING_CROSSBOW, "Ju-ger Repeating Crossbow");
			translationBuilder.add(Items.MARKS_CROSSBOW, "Marks-Crossbow");
			translationBuilder.add(Items.ARROWPROOF_VEST, "Arrowproof Vest");
			translationBuilder.add(Blocks.COPPER_FOR_SMELTING_INGOT_BLOCK, "Block of Copper For Smelting Ingot");
			translationBuilder.add(Blocks.REFINED_COPPER_BLOCK, "Block of Refined Copper");
			translationBuilder.add(Blocks.CUFE_ALLOY_BLOCK, "Block of CuFe Alloy");
			translationBuilder.add(Blocks.CUFE_BLOCK, "Block of CuFe");
			translationBuilder.add(Blocks.AUCU_ALLOY_BLOCK, "Block of AuCu Alloy");
			translationBuilder.add(Blocks.AUCU_BLOCK, "Block of AuCu");
			translationBuilder.add(Blocks.PIG_IRON_BLOCK, "Block of Pig Iron");
			translationBuilder.add(Blocks.STEEL_BLOCK, "Block of Steel");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_REFINED_COPPER.value()), "Refined copper armor clanks");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_CUFE_ALLOY.value()), "CuFe alloy armor clangs");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_AUCU_ALLOY.value()), "AuCu alloy armor clinks");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_STEEL.value()), "Steel armor cling");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.STONEBALL_THROW), "Stoneball flies");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.SLINGSHOT_THROW), "Pellet fired");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.ARBALEST_LOADING_START), "Arbalest charges up");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.ARBALEST_LOADING_END), "Arbalest loads");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.ARBALEST_SHOOT), "Arbalest fires");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.JUGER_REPEATING_CROSSBOW_LOADING_START), "Ju-ger repeating crossbow loads");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.JUGER_REPEATING_CROSSBOW_LOADING_END), "Ju-ger repeating crossbow charges up");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.JUGER_REPEATING_CROSSBOW_SHOOT), "Ju-ger repeating crossbow fires");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.MARKS_CROSSBOW_LOADING_START), "Marks-Crossbow charges up");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.MARKS_CROSSBOW_LOADING_END), "Marks-Crossbow loads");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.MARKS_CROSSBOW_SHOOT), "Marks-Crossbow fires");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_ARROWPROOF_VEST.value()), "Arrowproof vest rustles");
			translationBuilder.add(getCommentKey(SpontaneousReplace.DATA.getId()), "Translation of Spontaneous-Replace");
			translationBuilder.add(Blocks.EERIE_REGOLITH, "Eerie Regolith");
			translationBuilder.add(Blocks.TREACHEROUS_SLUDGE, "Treacherous Sludge");
			translationBuilder.add(Blocks.EERIE_RIND, "Eerie Rind");
			translationBuilder.add(Blocks.EERIE_BOUGH, "Eerie Bough");
			translationBuilder.add(Blocks.TREACHEROUS_SAC, "Treacherous Sac");
			translationBuilder.add(Blocks.TREACHEROUS_VINES, "Treacherous Vines");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.TREACHEROUS_SAC_BREAK), "Treacherous sac break");
			translationBuilder.add(getCommentKey(SpontaneousReplace.DATA.getId() + ".spider"), "Translation of Spontaneous-Replace Spider Part");
			translationBuilder.add(Items.SPIDER_LEG, "Spider Leg");
			translationBuilder.add(Items.SPIDER_LEATHER, "Spider Leather");
			translationBuilder.add(Items.SPIDER_FANG, "Spider Fang");
			translationBuilder.add(Items.COMPACT_GOSSAMER, "Compact Gossamer");
			translationBuilder.add(Items.STICKY_COMPACT_GOSSAMER, "Sticky Compact Gossamer");
			translationBuilder.add(Items.DEPOISON_SPIDER_LEG, "De-Poison Spider Leg");
			translationBuilder.add(Items.COMPACT_STRING, "Compact String");
			translationBuilder.add(Items.COMPOSITE_STRING, "Composite String");
			translationBuilder.add(Items.COMPOSITE_FABRIC, "Composite Fabric");
			translationBuilder.add(Items.SPIDER_LEATHER_CAP, "Spider Leather Cap");
			translationBuilder.add(Items.SPIDER_LEATHER_TUNIC, "Spider Leather Tunic");
			translationBuilder.add(EntityTypes.SPIDER_LARVA, "Spider Larva");
			translationBuilder.add(EntityTypes.GUARD_SPIDER, "Guard Spider");
			translationBuilder.add(EntityTypes.SPRAY_POISON_SPIDER, "Spray Poison Spider");
			translationBuilder.add(EntityTypes.WEAVING_WEB_SPIDER, "Weaving-Web Spider");
			translationBuilder.add(Items.SPIDER_LARVA_SPAWN_EGG, "Spider Larva Spawn Egg");
			translationBuilder.add(Items.GUARD_SPIDER_SPAWN_EGG, "Guard Spider Spawn Egg");
			translationBuilder.add(Items.SPRAY_POISON_SPIDER_SPAWN_EGG, "Spray Poison Spider Spawn Egg");
			translationBuilder.add(Items.WEAVING_WEB_SPIDER_SPAWN_EGG, "Weaving-Web Spider Spawn Egg");
			translationBuilder.add(Blocks.COBWEBBY_SOIL, "Cobwebby Soil");
			translationBuilder.add(Blocks.GOSSAMER_CARPET, "Gossamer Carpet");
			translationBuilder.add(Blocks.GOSSAMERY_LEAVES, "Gossamery Leaves");
			translationBuilder.add(Blocks.STICKY_COMPACT_COBWEB, "Sticky-Compact Cobweb");
			translationBuilder.add(Blocks.SPIDER_CHRYSALIS, "Spider Chrysalis");
			translationBuilder.add(Blocks.SPIDER_EGG_COCOON, "Spider Egg Cocoon");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.SPRAY_TOXIN), "Spray poison spider spray toxin");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_SPIDER_LEATHER_ARMOR.value()), "Spider leather armor sizzle");
			translationBuilder.add(getCommentKey("advancements"), "Translate of Advancements");
			translationBuilder.add(getCommentKey("advancements.vanilla"), "Translate of Minecraft Extension Advancements");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.VANILLA_ROOT.apply(registryLookup)), "Minecraft Extension");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.VANILLA_ROOT.apply(registryLookup)), "Play the vanilla extension content provided to you by Natural Replacement");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_NEW_METAL.apply(registryLookup)), "Bold Attempt");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_NEW_METAL.apply(registryLookup)), "Try to crafting a new metal");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_REFINED_COPPER.apply(registryLookup)), "USEFUL Copper, At LAST!");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_REFINED_COPPER.apply(registryLookup)), "Smelting a refined copper ingot");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_REFINED_COPPER_PRODUCT.apply(registryLookup)), "'Refined Copper' Age");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_REFINED_COPPER_PRODUCT.apply(registryLookup)), "Use a refined copper product");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_ALLOY.apply(registryLookup)), "Alloycraft");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_ALLOY.apply(registryLookup)), "Smelt an alloy ingot");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.UPGRADE_IRON_PICKAXE.apply(registryLookup)), "Upgrade Again");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.UPGRADE_IRON_PICKAXE.apply(registryLookup)), "Upgrade your iron pickaxe to CuFe alloy");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.UPGRADE_IRON_ARMOR.apply(registryLookup)), "Alloy Gear");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.UPGRADE_IRON_ARMOR.apply(registryLookup)), "Create CuFe alloy equipment to replace your old armor");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_STEEL_PRODUCT.apply(registryLookup)), "Industrial Age");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_STEEL_PRODUCT.apply(registryLookup)), "Use a steel product");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_STEEL_ARMORS.apply(registryLookup)), "Hearts Of Steel");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_STEEL_ARMORS.apply(registryLookup)), "Have a full set of steel armor");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_NEW_METALS.apply(registryLookup)), "Alchemist");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_NEW_METALS.apply(registryLookup)), "Crafting all new metals");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_NEW_RANGED.apply(registryLookup)), "Era-Replace");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_NEW_RANGED.apply(registryLookup)), "Try to crafting a new ranged equipment");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_ARROWPROOF_VEST.apply(registryLookup)), "Bulletproof Vest?");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_ARROWPROOF_VEST.apply(registryLookup)), "Crafting a projectile defense equipment");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_SLINGSHOT.apply(registryLookup)), "Can I Shoot Birds?");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_SLINGSHOT.apply(registryLookup)), "Use a slingshot");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_SLINGSHOT_WITH_ENDER_PEARL.apply(registryLookup)), "Portal 3");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_SLINGSHOT_WITH_ENDER_PEARL.apply(registryLookup)), "Use slingshot to launch a ender pearl");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_SLINGSHOT_WITH_POTION.apply(registryLookup)), "Avada Kedavra");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_SLINGSHOT_WITH_POTION.apply(registryLookup)), "Use slingshot to launch a potion");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "Magical Chinese Repeating Crossbow");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "Have a unique Chinese repeating crossbow");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_LEGEND_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "Empty The Magazine");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_LEGEND_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "Find the legend Ju-ger repeating crossbow");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_SHOT_1000_ARROWS.apply(registryLookup)), "Arrow Rain");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_SHOT_1000_ARROWS.apply(registryLookup)), "Shoot at least 1000 arrows with a Ju-ger repeating crossbow");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_KILL_100_MONSTERS.apply(registryLookup)), "Rifleman");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_KILL_100_MONSTERS.apply(registryLookup)), "Kill at least 100 monsters with a Ju-ger repeating crossbow");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_MARKS_CROSSBOW.apply(registryLookup)), "Magical Chinese Powerful Crossbow");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_MARKS_CROSSBOW.apply(registryLookup)), "Have a powerful Chinese ultra-long-range crossbow");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_LEGEND_MARKS_CROSSBOW.apply(registryLookup)), "Lock The Target");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_LEGEND_MARKS_CROSSBOW.apply(registryLookup)), "Find the legend marks-crossbow");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.MARKSMAN.apply(registryLookup)), "Marksman");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.MARKSMAN.apply(registryLookup)), "Hit the bullseye of a Target block from at least 200 meters away");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.SNIPING.apply(registryLookup)), "Sniping");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.SNIPING.apply(registryLookup)), "Kill a stray from at least 100 meters away");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_BASIC_RANGED.apply(registryLookup)), "Basic Ranged Weapon");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_BASIC_RANGED.apply(registryLookup)), "Have all basic ranged weapons");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_ENHANCED_RANGED.apply(registryLookup)), "Enhanced Ranged Weapon");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_ENHANCED_RANGED.apply(registryLookup)), "have all enhanced ranged weapons");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_RANGED.apply(registryLookup)), "Bow& Crossbow Collector");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_RANGED.apply(registryLookup)), "Have all ranged weapons");
			translationBuilder.add(getCommentKey("advancements." + SpontaneousReplace.DATA.getId()), "Translate of Spontaneous-Replace Advancements");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.SR_ROOT.apply(registryLookup)), "Spontaneous-Replace");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.SR_ROOT.apply(registryLookup)), "Play 'Spontaneous-Replace' Mod");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.FIND_A_SPIDER_BIOME.apply(registryLookup)), "Spider Biome");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.FIND_A_SPIDER_BIOME.apply(registryLookup)), "Contact to spontaneous evolving spider biome");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.KILL_A_NEW_SPIDER.apply(registryLookup)), "A New Spider?");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.KILL_A_NEW_SPIDER.apply(registryLookup)), "Kill a spontaneous-replace spider variant");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.SHOT_SPRAY_POISON_SPIDER.apply(registryLookup)), "Take This");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.SHOT_SPRAY_POISON_SPIDER.apply(registryLookup)), "Shoot a spray poison spider with an arrow");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_DEPOISON_SPIDER_LEG.apply(registryLookup)), "New Rotten Flesh");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_DEPOISON_SPIDER_LEG.apply(registryLookup)), "Cooking spider leg");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_EFFECT_SPIDER_CAMOUFLAGE.apply(registryLookup)), "Spider Camouflage");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_EFFECT_SPIDER_CAMOUFLAGE.apply(registryLookup)), "Equip the spider leather set to avoid triggering spider egg cocoons");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.KILL_ALL_SPIDERS.apply(registryLookup)), "Bane Of Arthropods");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.KILL_ALL_SPIDERS.apply(registryLookup)), "Kill all types of spiders");
		}
	}
	
	final class ZhCn extends SilkLanguageProvider {
		public ZhCn(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
			super(dataOutput, "zh_cn", registryLookup);
		}
		
		@Override
		public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
			translationBuilder.add(getCommentKey("title"), "原生语言");
			translationBuilder.add(getCommentKey("modmenu"), "Mod Menu 上可翻译的模组描述与摘要");
			translationBuilder.add(getLocalizationNameKey(SpontaneousReplace.DATA), "§6自然更替");
			translationBuilder.add(getLocalizationSummaryKey(SpontaneousReplace.DATA), "通过新内容为玩家提供加强的原版冒险");
			translationBuilder.add(getLocalizationDescriptionKey(SpontaneousReplace.DATA), """
					§l模组简介：§r
					　　狂风呜咽，雷轰电鸣…在·Minecraft·的世界上的某种能量唤醒了生物体内的潜能。
					　　五种元素的力量会带你走向一切的起源。寻找，探索，击败或饶恕他们。
					　　你是会成为众生物的敬仰者，还是无尽的屠杀者，一切皆是你的选择……
					§l模组愿景：§r
					　　我希望做一个基于原版游戏内核，不破坏原版游戏玩法的玩法类模组。在这基础上进行开发十分困难，一件物品是否加入，怎样设计数据才不会破坏游戏平衡性，这些都是开发者需要考虑的点。
					　　如果你觉得模组做的不错，欢迎对我的项目进行赞助，或者对此模组进行翻译，十分感谢你们!""");
			translationBuilder.add(getCommentKey("generic"), "通用模组组件翻译");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, ""), "自然更替");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.mod"), "模组");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "target"), "0.5.X 目标");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "target"), "　　继续完善新生物群系，继续添加原有·0.5.0·计划但未实现的内容");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "changelog"), "更新日志");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.synopsis"), "简介");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "tab.synopsis.intro"), "§f§l模组简介：");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.synopsis.intro"), """
					§r　　狂风呜咽，雷轰电鸣…在·Minecraft·的世界上的某种能量唤醒了生物体内的潜能。
					　　五种元素的力量会带你走向一切的起源。寻找，探索，击败或饶恕他们。
					　　你是会成为众生物的敬仰者，还是无尽的屠杀者，一切皆是你的选择……""");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "tab.synopsis.vision"), "§f§l模组愿景：");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.synopsis.vision"),
					"　　我希望做一个基于原版游戏内核，不破坏原版游戏玩法的玩法类模组。在这基础上进行开发十分困难，一件物品是否加入，怎样设计数据才不会破坏游戏平衡性，这些都是开发者需要考虑的点。\n" +
							"　　如果你觉得模组做的不错，欢迎对我的项目进行赞助，或者对此模组进行翻译，十分感谢你们!");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.config"), "设置");
			translationBuilder.add(widgetText(SpontaneousReplace.DATA, "homepage"), "模组官网");
			translationBuilder.add(widgetText(SpontaneousReplace.DATA, "support"), "支持我们");
			translationBuilder.add(widgetText(SpontaneousReplace.DATA, "community"), "官方社群");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, ""), "自然更替设置…");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, "update"), "模组更新设置");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, "update"), "");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, Configs.AUTO_SHOW_SETTINGS_BUTTON), "自动显示设置按钮");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, Configs.AUTO_SHOW_SETTINGS_BUTTON + ".on"), "如果安装模组菜单则自动关闭选项界面按钮");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, Configs.AUTO_SHOW_SETTINGS_BUTTON + ".off"), "始终在选项界面显示设置按钮");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, Configs.DISABLE_WARNING_ADVICE), "禁用“实验性设置”警告");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, Configs.DISABLE_WARNING_ADVICE + ".need_other_mod"), "需要其他模组");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, Configs.DISABLE_WARNING_ADVICE + ".need_other_mod"), "前往 https://modrinth.com/mod/dcwa 安装此模组开启功能");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "generator.classic"), "自然更替：");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "generator.snapshot"), "自然更替快照：");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "generator.non_version"), "过旧");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "placeholder"), "此功能尚未添加，请继续关注本模组首页以及时获取更新。");
			translationBuilder.add(getWorldPresetKey(WorldPresets.CLASSIC), "自然更替");
			translationBuilder.add(getWorldPresetKey(WorldPresets.SNAPSHOT), "自然更替快照");
			translationBuilder.add(ResourcesPack.getName(SpontaneousReplace.DATA), "§6「自然更替」默认资源包");
			translationBuilder.add(DataPack.getName(SpontaneousReplace.DATA), "§6「自然更替」默认数据包");
			translationBuilder.add(getCommentKey("effects"), "状态效果翻译");
			translationBuilder.add(StatusEffects.ACIDIZE.value(), "酸化");
			translationBuilder.add(StatusEffects.SPIDER_CAMOUFLAGE.value(), "蜘蛛伪装");
			translationBuilder.add(getCommentKey("death message"), "死亡消息翻译");
			translationBuilder.add("death.attack.acidize", "%1$s溶解了");
			translationBuilder.add("death.attack.acidize.player", "%1$s在与%2$s战斗时溶解了");
			translationBuilder.add("death.attack.spider_toxin", "%1$s没能躲过%2$s的毒素");
			translationBuilder.add("death.attack.spider_toxin.item", "%1$s被%2$s用%3$s发射的毒素杀死了");
			translationBuilder.add(getCommentKey("itemGroup"), "物品组翻译");
			translationBuilder.add(ItemGroups.BUILDING_BLOCKS, "「自然更替」建筑方块");
			translationBuilder.add(ItemGroups.COLORED_BLOCKS, "「自然更替」染色方块");
			translationBuilder.add(ItemGroups.NATURAL, "「自然更替」自然方块");
			translationBuilder.add(ItemGroups.FUNCTIONAL, "「自然更替」功能方块");
			translationBuilder.add(ItemGroups.REDSTONE, "「自然更替」红石方块");
			translationBuilder.add(ItemGroups.TOOLS, "「自然更替」工具与实用物品");
			translationBuilder.add(ItemGroups.COMBAT, "「自然更替」战斗用品");
			translationBuilder.add(ItemGroups.FOOD_AND_DRINK, "「自然更替」食物与饮品");
			translationBuilder.add(ItemGroups.INGREDIENTS, "「自然更替」原材料");
			translationBuilder.add(ItemGroups.SPAWN_EGGS, "「自然更替」刷怪蛋");
			translationBuilder.add(getCommentKey("vanilla"), "原版拓展翻译");
			translationBuilder.add(Items.COPPER_FOR_SMELTING_INGOT, "炼锭铜");
			translationBuilder.add(Items.REFINED_COPPER_INGOT, "精铜锭");
			translationBuilder.add(Items.REFINED_COPPER_NUGGET, "精铜粒");
			translationBuilder.add(Items.REFINED_COPPER_SHOVEL, "精铜锹");
			translationBuilder.add(Items.REFINED_COPPER_PICKAXE, "精铜镐");
			translationBuilder.add(Items.REFINED_COPPER_AXE, "精铜斧");
			translationBuilder.add(Items.REFINED_COPPER_HOE, "精铜锄");
			translationBuilder.add(Items.REFINED_COPPER_SWORD, "精铜剑");
			translationBuilder.add(Items.REFINED_COPPER_HELMET, "精铜头盔");
			translationBuilder.add(Items.REFINED_COPPER_CHESTPLATE, "精铜胸甲");
			translationBuilder.add(Items.REFINED_COPPER_LEGGINGS, "精铜护腿");
			translationBuilder.add(Items.REFINED_COPPER_BOOTS, "精铜靴子");
			translationBuilder.add(Items.CUFE_ALLOY, "铜铁合金");
			translationBuilder.add(Items.CUFE_ALLOY_INGOT, "铜铁锭");
			translationBuilder.add(Items.CUFE_ALLOY_NUGGET, "铜铁粒");
			translationBuilder.add(Items.CUFE_ALLOY_SHOVEL, "铜铁锹");
			translationBuilder.add(Items.CUFE_ALLOY_PICKAXE, "铜铁镐");
			translationBuilder.add(Items.CUFE_ALLOY_AXE, "铜铁斧");
			translationBuilder.add(Items.CUFE_ALLOY_HOE, "铜铁锄");
			translationBuilder.add(Items.CUFE_ALLOY_SWORD, "铜铁剑");
			translationBuilder.add(Items.CUFE_ALLOY_HELMET, "铜铁头盔");
			translationBuilder.add(Items.CUFE_ALLOY_CHESTPLATE, "铜铁胸甲");
			translationBuilder.add(Items.CUFE_ALLOY_LEGGINGS, "铜铁护腿");
			translationBuilder.add(Items.CUFE_ALLOY_BOOTS, "铜铁靴子");
			translationBuilder.add(Items.AUCU_ALLOY, "金铜合金");
			translationBuilder.add(Items.AUCU_ALLOY_INGOT, "金铜锭");
			translationBuilder.add(Items.AUCU_ALLOY_NUGGET, "金铜粒");
			translationBuilder.add(Items.AUCU_ALLOY_SHOVEL, "金铜锹");
			translationBuilder.add(Items.AUCU_ALLOY_PICKAXE, "金铜镐");
			translationBuilder.add(Items.AUCU_ALLOY_AXE, "金铜斧");
			translationBuilder.add(Items.AUCU_ALLOY_HOE, "金铜锄");
			translationBuilder.add(Items.AUCU_ALLOY_SWORD, "金铜剑");
			translationBuilder.add(Items.AUCU_ALLOY_HELMET, "金铜头盔");
			translationBuilder.add(Items.AUCU_ALLOY_CHESTPLATE, "金铜胸甲");
			translationBuilder.add(Items.AUCU_ALLOY_LEGGINGS, "金铜护腿");
			translationBuilder.add(Items.AUCU_ALLOY_BOOTS, "金铜靴子");
			translationBuilder.add(Items.CLEAN_COAL, "精煤");
			translationBuilder.add(Items.PIG_IRON, "生铁");
			translationBuilder.add(Items.STEEL_INGOT, "钢锭");
			translationBuilder.add(Items.STEEL_SHOVEL, "钢锹");
			translationBuilder.add(Items.STEEL_PICKAXE, "钢镐");
			translationBuilder.add(Items.STEEL_AXE, "钢斧");
			translationBuilder.add(Items.STEEL_HOE, "钢锄");
			translationBuilder.add(Items.STEEL_SWORD, "钢剑");
			translationBuilder.add(Items.STEEL_HELMET, "钢头盔");
			translationBuilder.add(Items.STEEL_CHESTPLATE, "钢胸甲");
			translationBuilder.add(Items.STEEL_LEGGINGS, "钢护腿");
			translationBuilder.add(Items.STEEL_BOOTS, "钢靴子");
			translationBuilder.add(Items.STONEBALL, "石弹");
			translationBuilder.add(Items.STEEL_ARROW, "钢箭");
			translationBuilder.add(Items.SLINGSHOT, "丫弹弓");
			translationBuilder.add(Items.RECURVE_BOW, "反曲弓");
			translationBuilder.add(Items.ARBALEST, "劲弩");
			translationBuilder.add(Items.COMPOUND_BOW, "复合弓");
			translationBuilder.add(Items.JUGER_REPEATING_CROSSBOW, "诸葛连弩");
			translationBuilder.add(Items.MARKS_CROSSBOW, "神臂弩");
			translationBuilder.add(Items.ARROWPROOF_VEST, "防箭衣");
			translationBuilder.add(Blocks.COPPER_FOR_SMELTING_INGOT_BLOCK, "炼锭铜块");
			translationBuilder.add(Blocks.REFINED_COPPER_BLOCK, "精铜块");
			translationBuilder.add(Blocks.CUFE_ALLOY_BLOCK, "铜铁合金块");
			translationBuilder.add(Blocks.CUFE_BLOCK, "铜铁块");
			translationBuilder.add(Blocks.AUCU_ALLOY_BLOCK, "金铜合金块");
			translationBuilder.add(Blocks.AUCU_BLOCK, "金铜块");
			translationBuilder.add(Blocks.PIG_IRON_BLOCK, "生铁块");
			translationBuilder.add(Blocks.STEEL_BLOCK, "钢块");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_REFINED_COPPER.value()), "精铜盔甲：嗙嘡");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_CUFE_ALLOY.value()), "铜铁盔甲：钪锵");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_AUCU_ALLOY.value()), "金铜盔甲：铤镗");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_STEEL.value()), "钢盔甲：叮嘡");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.STONEBALL_THROW), "石弹：飞出");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.SLINGSHOT_THROW), "丫弹弓：投掷");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.ARBALEST_LOADING_START), "劲弩：蓄力");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.ARBALEST_LOADING_END), "劲弩：装填");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.ARBALEST_SHOOT), "劲弩：发射");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.JUGER_REPEATING_CROSSBOW_LOADING_START), "诸葛连弩：装填");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.JUGER_REPEATING_CROSSBOW_LOADING_END), "诸葛连弩：张弩");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.JUGER_REPEATING_CROSSBOW_SHOOT), "诸葛连弩：发射");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.MARKS_CROSSBOW_LOADING_START), "神臂弩：蓄力");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.MARKS_CROSSBOW_LOADING_END), "神臂弩：装填");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.MARKS_CROSSBOW_SHOOT), "神臂弩：发射");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_ARROWPROOF_VEST.value()), "防箭衣：摩擦");
			translationBuilder.add(getCommentKey(SpontaneousReplace.DATA.getId()), "自然更替翻译");
			translationBuilder.add(Blocks.EERIE_REGOLITH, "阴森浮土");
			translationBuilder.add(Blocks.TREACHEROUS_SLUDGE, "诡谲污泥");
			translationBuilder.add(Blocks.EERIE_RIND, "阴森木壳");
			translationBuilder.add(Blocks.EERIE_BOUGH, "阴森木枝");
			translationBuilder.add(Blocks.TREACHEROUS_SAC, "诡谲囊");
			translationBuilder.add(Blocks.TREACHEROUS_VINES, "诡谲藤");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.TREACHEROUS_SAC_BREAK), "诡谲囊：破裂");
			translationBuilder.add(getCommentKey(SpontaneousReplace.DATA.getId() + ".spider"), "自然更替蜘蛛部分翻译");
			translationBuilder.add(Items.SPIDER_LEG, "蜘蛛腿");
			translationBuilder.add(Items.SPIDER_LEATHER, "蜘蛛护皮");
			translationBuilder.add(Items.SPIDER_FANG, "蜘蛛毒牙");
			translationBuilder.add(Items.COMPACT_GOSSAMER, "致密蛛丝");
			translationBuilder.add(Items.STICKY_COMPACT_GOSSAMER, "黏密蛛丝");
			translationBuilder.add(Items.DEPOISON_SPIDER_LEG, "去毒蛛腿");
			translationBuilder.add(Items.COMPACT_STRING, "致密丝线");
			translationBuilder.add(Items.COMPOSITE_STRING, "复合丝线");
			translationBuilder.add(Items.COMPOSITE_FABRIC, "复合面料");
			translationBuilder.add(Items.SPIDER_LEATHER_CAP, "蜘蛛皮帽");
			translationBuilder.add(Items.SPIDER_LEATHER_TUNIC, "蜘蛛皮甲");
			translationBuilder.add(EntityTypes.SPIDER_LARVA, "幼蛛");
			translationBuilder.add(EntityTypes.GUARD_SPIDER, "蜘蛛卫兵");
			translationBuilder.add(EntityTypes.SPRAY_POISON_SPIDER, "喷吐毒蛛");
			translationBuilder.add(EntityTypes.WEAVING_WEB_SPIDER, "织网蜘蛛");
			translationBuilder.add(Items.SPIDER_LARVA_SPAWN_EGG, "幼蛛刷怪蛋");
			translationBuilder.add(Items.GUARD_SPIDER_SPAWN_EGG, "蜘蛛卫兵刷怪蛋");
			translationBuilder.add(Items.SPRAY_POISON_SPIDER_SPAWN_EGG, "喷吐毒蛛刷怪蛋");
			translationBuilder.add(Items.WEAVING_WEB_SPIDER_SPAWN_EGG, "织网蜘蛛刷怪蛋");
			translationBuilder.add(Blocks.COBWEBBY_SOIL, "丝化土");
			translationBuilder.add(Blocks.GOSSAMER_CARPET, "覆地蛛丝");
			translationBuilder.add(Blocks.GOSSAMERY_LEAVES, "覆丝树叶");
			translationBuilder.add(Blocks.STICKY_COMPACT_COBWEB, "黏密蛛网");
			translationBuilder.add(Blocks.SPIDER_CHRYSALIS, "蜘蛛茧蛹");
			translationBuilder.add(Blocks.SPIDER_EGG_COCOON, "蜘蛛卵茧");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.SPRAY_TOXIN), "喷吐毒蛛：喷吐毒素");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_SPIDER_LEATHER_ARMOR.value()), "蜘蛛皮甲：摩挲");
			translationBuilder.add(getCommentKey("advancements"), "进度翻译");
			translationBuilder.add(getCommentKey("advancements.vanilla"), "Minecraft 拓展进度翻译");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.VANILLA_ROOT.apply(registryLookup)), "Minecraft 拓展");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.VANILLA_ROOT.apply(registryLookup)), "游玩来自《自然更替》为您提供的原版拓展");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_NEW_METAL.apply(registryLookup)), "大胆的尝试");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_NEW_METAL.apply(registryLookup)), "尝试制作一种新的金属");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_REFINED_COPPER.apply(registryLookup)), "有用的铜，终于！");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_REFINED_COPPER.apply(registryLookup)), "冶炼出一块精铜锭");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_REFINED_COPPER_PRODUCT.apply(registryLookup)), "“精铜”时代");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_REFINED_COPPER_PRODUCT.apply(registryLookup)), "使用精铜制品");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_ALLOY.apply(registryLookup)), "合金工艺");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_ALLOY.apply(registryLookup)), "冶炼出一块合金锭");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.UPGRADE_IRON_PICKAXE.apply(registryLookup)), "再次升级");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.UPGRADE_IRON_PICKAXE.apply(registryLookup)), "把你的铁镐升级成铜铁合金");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.UPGRADE_IRON_ARMOR.apply(registryLookup)), "合金装备");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.UPGRADE_IRON_ARMOR.apply(registryLookup)), "打造铜铁合金装备来替换你的老旧装甲");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_STEEL_PRODUCT.apply(registryLookup)), "工业时代");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_STEEL_PRODUCT.apply(registryLookup)), "使用钢铁制品");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_STEEL_ARMORS.apply(registryLookup)), "钢铁雄心");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_STEEL_ARMORS.apply(registryLookup)), "获得一整套钢铁盔甲");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_NEW_METALS.apply(registryLookup)), "炼金术师");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_NEW_METALS.apply(registryLookup)), "制作出所有新金属");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_NEW_RANGED.apply(registryLookup)), "时代更替");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_NEW_RANGED.apply(registryLookup)), "尝试制作一种新型的远程装备");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_ARROWPROOF_VEST.apply(registryLookup)), "防弹衣？");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_ARROWPROOF_VEST.apply(registryLookup)), "制作一种弹射物防御装备");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_SLINGSHOT.apply(registryLookup)), "能打鸟吗？");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_SLINGSHOT.apply(registryLookup)), "使用一次丫弹弓");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_SLINGSHOT_WITH_ENDER_PEARL.apply(registryLookup)), "传送门 3");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_SLINGSHOT_WITH_ENDER_PEARL.apply(registryLookup)), "使用丫弹弓发射末影珍珠");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_SLINGSHOT_WITH_POTION.apply(registryLookup)), "阿瓦达索命");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_SLINGSHOT_WITH_POTION.apply(registryLookup)), "使用丫弹弓发射药水瓶");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "神奇的中国连弩");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "拥有一种独特的中国连发弩");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_LEGEND_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "清空弹仓");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_LEGEND_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "找到传说中的诸葛连弩");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_SHOT_1000_ARROWS.apply(registryLookup)), "箭雨");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_SHOT_1000_ARROWS.apply(registryLookup)), "使用诸葛连弩发射至少1000支箭");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_KILL_100_MONSTERS.apply(registryLookup)), "突击手");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_KILL_100_MONSTERS.apply(registryLookup)), "使用诸葛连弩击杀至少100只怪物");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_MARKS_CROSSBOW.apply(registryLookup)), "神奇的中国劲弩");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_MARKS_CROSSBOW.apply(registryLookup)), "使用一种强力的中国超远程弩");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_LEGEND_MARKS_CROSSBOW.apply(registryLookup)), "目标锁定");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_LEGEND_MARKS_CROSSBOW.apply(registryLookup)), "找到传说中的神臂弩");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.MARKSMAN.apply(registryLookup)), "神射手");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.MARKSMAN.apply(registryLookup)), "从至少200米外射中标靶的靶心");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.SNIPING.apply(registryLookup)), "狙击");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.SNIPING.apply(registryLookup)), "从100米开外击杀一只流浪者");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_BASIC_RANGED.apply(registryLookup)), "基础远程武器");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_BASIC_RANGED.apply(registryLookup)), "拥有所有的基础型远程武器");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_ENHANCED_RANGED.apply(registryLookup)), "增强远程武器");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_ENHANCED_RANGED.apply(registryLookup)), "拥有所有的增强型远程武器");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_RANGED.apply(registryLookup)), "弓弩收藏家");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_RANGED.apply(registryLookup)), "拥有所有的远程武器");
			translationBuilder.add(getCommentKey("advancements." + SpontaneousReplace.DATA.getId()), "自然更替进度翻译");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.SR_ROOT.apply(registryLookup)), "自然更替");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.SR_ROOT.apply(registryLookup)), "游玩「自然更替」");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.FIND_A_SPIDER_BIOME.apply(registryLookup)), "蜘蛛群系");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.FIND_A_SPIDER_BIOME.apply(registryLookup)), "接触到自然演变的蜘蛛群系");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.KILL_A_NEW_SPIDER.apply(registryLookup)), "新的蜘蛛？");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.KILL_A_NEW_SPIDER.apply(registryLookup)), "击杀一只特殊的蜘蛛变体");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.SHOT_SPRAY_POISON_SPIDER.apply(registryLookup)), "来尝尝这个");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.SHOT_SPRAY_POISON_SPIDER.apply(registryLookup)), "使用箭射中喷吐毒蛛");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_DEPOISON_SPIDER_LEG.apply(registryLookup)), "新款辣条");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_DEPOISON_SPIDER_LEG.apply(registryLookup)), "烹饪一份蜘蛛腿");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_EFFECT_SPIDER_CAMOUFLAGE.apply(registryLookup)), "蜘蛛伪装");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_EFFECT_SPIDER_CAMOUFLAGE.apply(registryLookup)), "装备蜘蛛护皮套装以避免触发蜘蛛卵茧");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.KILL_ALL_SPIDERS.apply(registryLookup)), "节肢杀手");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.KILL_ALL_SPIDERS.apply(registryLookup)), "击杀所有种类的蜘蛛");
		}
	}
	
	final class ZhHk extends SilkLanguageProvider {
		public ZhHk(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
			super(dataOutput, "zh_hk", registryLookup);
		}
		
		@Override
		public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
			translationBuilder.add(getCommentKey("title"), "原生語言為簡體中文(zh_cn)");
			translationBuilder.add(getCommentKey("modmenu"), "Mod Menu 上可翻譯嘅模組描述與摘要");
			translationBuilder.add(getLocalizationNameKey(SpontaneousReplace.DATA), "§6自然更替");
			translationBuilder.add(getLocalizationSummaryKey(SpontaneousReplace.DATA), "通過新內容為玩家提供加強嘅原版冒險");
			translationBuilder.add(getLocalizationDescriptionKey(SpontaneousReplace.DATA), """
					§l模組簡介:§r
					　　狂風嗚咽，雷轟電鳴⋯在·Minecraft·嘅世界上嘅某種能量喚醒咗生物體內嘅潛能。
					　　五種元素嘅力量會帶你走向一切嘅起源。尋找，探索，擊敗或饒恕他們。
					　　你是會成為眾生物嘅敬仰者，還是無盡嘅屠殺者，一切皆是你嘅選擇⋯⋯
					§l模組願景:§r
					　　我希望做一個基於原版遊戲內核，不破壞原版遊戲玩法嘅玩法類模組。在這基礎上進行開發十分困難，一件物品是否加入，怎樣設計數據才不會破壞遊戲平衡性，這些都是開發者需要考慮嘅點。
					　　如果你覺得模組做嘅不錯，歡迎對我嘅項目進行贊助，或者對此模組進行翻譯，十分感謝你們!""");
			translationBuilder.add(getCommentKey("generic"), "通用模組組件翻譯");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, ""), "自然更替");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.mod"), "模組");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "target"), "0.5.X 目標");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "target"), "　　繼續完善新生物羣落，繼續添加原有·0.5.0·計劃但未實現嘅內容");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "changelog"), "更新日誌");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.synopsis"), "簡介");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "tab.synopsis.intro"), "§f§l模組簡介：");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.synopsis.intro"), """
					§r　　狂風嗚咽，雷轟電鳴⋯在·Minecraft·嘅世界上嘅某種能量喚醒咗生物體內嘅潛能。
					　　五種元素嘅力量會帶你走向一切嘅起源。尋找，探索，擊敗或饒恕他們。
					　　你是會成為眾生物嘅敬仰者，還是無盡嘅屠殺者，一切皆是你嘅選擇⋯⋯""");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "tab.synopsis.vision"), "§f§l模組願景：");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.synopsis.vision"),
					"　　我希望做一個基於原版遊戲內核，不破壞原版遊戲玩法嘅玩法類模組。在這基礎上進行開發十分困難，一件物品是否加入，怎樣設計數據才不會破壞遊戲平衡性，這些都是開發者需要考慮嘅點。\n" +
							"　　如果你覺得模組做嘅不錯，歡迎對我嘅項目進行贊助，或者對此模組進行翻譯，十分感謝你們!");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.config"), "設定");
			translationBuilder.add(widgetText(SpontaneousReplace.DATA, "homepage"), "模組官網");
			translationBuilder.add(widgetText(SpontaneousReplace.DATA, "support"), "支持我們");
			translationBuilder.add(widgetText(SpontaneousReplace.DATA, "community"), "官方社羣");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, ""), "自然更替設定⋯⋯");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, "update"), "模組更新設定");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, "update"), "");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, Configs.AUTO_SHOW_SETTINGS_BUTTON), "自動顯示設定按鈕");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, Configs.AUTO_SHOW_SETTINGS_BUTTON + ".on"), "如果安裝模組菜單則自動關閉選項界面按鈕");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, Configs.AUTO_SHOW_SETTINGS_BUTTON + ".off"), "始終在選項界面顯示設定按鈕");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, Configs.DISABLE_WARNING_ADVICE), "禁用“實驗性質設定”警告");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, Configs.DISABLE_WARNING_ADVICE + ".need_other_mod"), "需要其他模組");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, Configs.DISABLE_WARNING_ADVICE + ".need_other_mod"), "前往 https://modrinth.com/mod/dcwa 安裝此模組開啓功能");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "generator.classic"), "自然更替：");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "generator.snapshot"), "自然更替快照：");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "generator.non_version"), "過舊");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "placeholder"), "此功能尚未添加, 請繼續關注本模組首頁以及時獲取更新。");
			translationBuilder.add(getWorldPresetKey(WorldPresets.CLASSIC), "自然更替");
			translationBuilder.add(getWorldPresetKey(WorldPresets.SNAPSHOT), "自然更替快照");
			translationBuilder.add(ResourcesPack.getName(SpontaneousReplace.DATA), "§6「自然更替」默認資源包");
			translationBuilder.add(DataPack.getName(SpontaneousReplace.DATA), "§6「自然更替」默認數據包");
			translationBuilder.add(getCommentKey("effects"), "狀態效果翻譯");
			translationBuilder.add(StatusEffects.ACIDIZE.value(), "酸化");
			translationBuilder.add(StatusEffects.SPIDER_CAMOUFLAGE.value(), "蜘蛛偽裝");
			translationBuilder.add(getCommentKey("death message"), "死亡消息翻譯");
			translationBuilder.add("death.attack.acidize", "%1$s 溶解咗");
			translationBuilder.add("death.attack.acidize.player", "%1$s 同 %2$s 戰鬥時溶解咗");
			translationBuilder.add("death.attack.spider_toxin", "%1$s 冇有躲過 %2$s 嘅毒素");
			translationBuilder.add("death.attack.spider_toxin.item", "%1$s 被 %2$s 用 %3$s 發射嘅毒素殺死咗");
			translationBuilder.add(getCommentKey("itemGroup"), "物品組翻譯");
			translationBuilder.add(ItemGroups.BUILDING_BLOCKS, "「自然更替」建築方塊");
			translationBuilder.add(ItemGroups.COLORED_BLOCKS, "「自然更替」染色方塊");
			translationBuilder.add(ItemGroups.NATURAL, "「自然更替」自然方塊");
			translationBuilder.add(ItemGroups.FUNCTIONAL, "「自然更替」功能方塊");
			translationBuilder.add(ItemGroups.REDSTONE, "「自然更替」紅石方塊");
			translationBuilder.add(ItemGroups.TOOLS, "「自然更替」工具與實用物品");
			translationBuilder.add(ItemGroups.COMBAT, "「自然更替」戰鬥");
			translationBuilder.add(ItemGroups.FOOD_AND_DRINK, "「自然更替」食物與飲品");
			translationBuilder.add(ItemGroups.INGREDIENTS, "「自然更替」原材料");
			translationBuilder.add(ItemGroups.SPAWN_EGGS, "「自然更替」生成蛋");
			translationBuilder.add(getCommentKey("vanilla"), "原版拓展翻譯");
			translationBuilder.add(Items.COPPER_FOR_SMELTING_INGOT, "煉錠銅");
			translationBuilder.add(Items.REFINED_COPPER_INGOT, "精銅錠");
			translationBuilder.add(Items.REFINED_COPPER_NUGGET, "精銅粒");
			translationBuilder.add(Items.REFINED_COPPER_SHOVEL, "精銅鏟");
			translationBuilder.add(Items.REFINED_COPPER_PICKAXE, "精銅鎬");
			translationBuilder.add(Items.REFINED_COPPER_AXE, "精銅斧頭");
			translationBuilder.add(Items.REFINED_COPPER_HOE, "精銅鋤頭");
			translationBuilder.add(Items.REFINED_COPPER_SWORD, "精銅劍");
			translationBuilder.add(Items.REFINED_COPPER_HELMET, "精銅頭盔");
			translationBuilder.add(Items.REFINED_COPPER_CHESTPLATE, "精銅胸甲");
			translationBuilder.add(Items.REFINED_COPPER_LEGGINGS, "精銅護腳");
			translationBuilder.add(Items.REFINED_COPPER_BOOTS, "精銅靴");
			translationBuilder.add(Items.CUFE_ALLOY, "銅鐵合金");
			translationBuilder.add(Items.CUFE_ALLOY_INGOT, "銅鐵錠");
			translationBuilder.add(Items.CUFE_ALLOY_NUGGET, "銅鐵粒");
			translationBuilder.add(Items.CUFE_ALLOY_SHOVEL, "銅鐵鏟");
			translationBuilder.add(Items.CUFE_ALLOY_PICKAXE, "銅鐵鎬");
			translationBuilder.add(Items.CUFE_ALLOY_AXE, "銅鐵斧頭");
			translationBuilder.add(Items.CUFE_ALLOY_HOE, "銅鐵鋤頭");
			translationBuilder.add(Items.CUFE_ALLOY_SWORD, "銅鐵劍");
			translationBuilder.add(Items.CUFE_ALLOY_HELMET, "銅鐵頭盔");
			translationBuilder.add(Items.CUFE_ALLOY_CHESTPLATE, "銅鐵胸甲");
			translationBuilder.add(Items.CUFE_ALLOY_LEGGINGS, "銅鐵護腳");
			translationBuilder.add(Items.CUFE_ALLOY_BOOTS, "銅鐵靴");
			translationBuilder.add(Items.AUCU_ALLOY, "金銅合金");
			translationBuilder.add(Items.AUCU_ALLOY_INGOT, "金銅錠");
			translationBuilder.add(Items.AUCU_ALLOY_NUGGET, "金銅粒");
			translationBuilder.add(Items.AUCU_ALLOY_SHOVEL, "金銅鏟");
			translationBuilder.add(Items.AUCU_ALLOY_PICKAXE, "金銅鎬");
			translationBuilder.add(Items.AUCU_ALLOY_AXE, "金銅斧頭");
			translationBuilder.add(Items.AUCU_ALLOY_HOE, "金銅鋤頭");
			translationBuilder.add(Items.AUCU_ALLOY_SWORD, "金銅劍");
			translationBuilder.add(Items.AUCU_ALLOY_HELMET, "金銅頭盔");
			translationBuilder.add(Items.AUCU_ALLOY_CHESTPLATE, "金銅胸甲");
			translationBuilder.add(Items.AUCU_ALLOY_LEGGINGS, "金銅護腳");
			translationBuilder.add(Items.AUCU_ALLOY_BOOTS, "金銅靴");
			translationBuilder.add(Items.CLEAN_COAL, "精煤");
			translationBuilder.add(Items.PIG_IRON, "生鐵");
			translationBuilder.add(Items.STEEL_INGOT, "鋼錠");
			translationBuilder.add(Items.STEEL_SHOVEL, "鋼鏟");
			translationBuilder.add(Items.STEEL_PICKAXE, "鋼鎬");
			translationBuilder.add(Items.STEEL_AXE, "鋼斧頭");
			translationBuilder.add(Items.STEEL_HOE, "鋼鋤頭");
			translationBuilder.add(Items.STEEL_SWORD, "鋼劍");
			translationBuilder.add(Items.STEEL_HELMET, "鋼頭盔");
			translationBuilder.add(Items.STEEL_CHESTPLATE, "鋼胸甲");
			translationBuilder.add(Items.STEEL_LEGGINGS, "鋼護腳");
			translationBuilder.add(Items.STEEL_BOOTS, "鋼靴");
			translationBuilder.add(Items.STONEBALL, "石彈丸");
			translationBuilder.add(Items.STEEL_ARROW, "鋼箭矢");
			translationBuilder.add(Items.SLINGSHOT, "丫彈弓");
			translationBuilder.add(Items.RECURVE_BOW, "反曲弓");
			translationBuilder.add(Items.ARBALEST, "勁弩");
			translationBuilder.add(Items.COMPOUND_BOW, "複合弓");
			translationBuilder.add(Items.JUGER_REPEATING_CROSSBOW, "諸葛連弩");
			translationBuilder.add(Items.MARKS_CROSSBOW, "神臂弩");
			translationBuilder.add(Items.ARROWPROOF_VEST, "防箭衣");
			translationBuilder.add(Blocks.COPPER_FOR_SMELTING_INGOT_BLOCK, "煉錠銅磚");
			translationBuilder.add(Blocks.REFINED_COPPER_BLOCK, "精銅磚");
			translationBuilder.add(Blocks.CUFE_ALLOY_BLOCK, "銅鐵合金磚");
			translationBuilder.add(Blocks.CUFE_BLOCK, "銅鐵磚");
			translationBuilder.add(Blocks.AUCU_ALLOY_BLOCK, "金銅合金磚");
			translationBuilder.add(Blocks.AUCU_BLOCK, "金銅磚");
			translationBuilder.add(Blocks.PIG_IRON_BLOCK, "生鐵磚");
			translationBuilder.add(Blocks.STEEL_BLOCK, "鋼磚");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_REFINED_COPPER.value()), "裝備精铜盔甲");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_CUFE_ALLOY.value()), "裝備銅鐵盔甲");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_AUCU_ALLOY.value()), "裝備金銅盔甲");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_STEEL.value()), "裝備鋼盔甲");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.STONEBALL_THROW), "石彈丸被掟出");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.SLINGSHOT_THROW), "掟出聲");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.ARBALEST_LOADING_START), "勁弩搭箭");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.ARBALEST_LOADING_END), "勁弩裝填");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.ARBALEST_SHOOT), "勁弩發射");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.JUGER_REPEATING_CROSSBOW_LOADING_START), "諸葛連弩裝填");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.JUGER_REPEATING_CROSSBOW_LOADING_END), "諸葛連弩搭箭");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.JUGER_REPEATING_CROSSBOW_SHOOT), "諸葛連弩發射");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.MARKS_CROSSBOW_LOADING_START), "神臂弩搭箭");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.MARKS_CROSSBOW_LOADING_END), "神臂弩裝填");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.MARKS_CROSSBOW_SHOOT), "神臂弩發射");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_ARROWPROOF_VEST.value()), "裝備防箭衣");
			translationBuilder.add(getCommentKey(SpontaneousReplace.DATA.getId()), "自然更替翻譯");
			translationBuilder.add(Blocks.EERIE_REGOLITH, "陰森浮土");
			translationBuilder.add(Blocks.TREACHEROUS_SLUDGE, "詭譎污泥");
			translationBuilder.add(Blocks.EERIE_RIND, "陰森木殼");
			translationBuilder.add(Blocks.EERIE_BOUGH, "陰森木枝");
			translationBuilder.add(Blocks.TREACHEROUS_SAC, "詭譎囊");
			translationBuilder.add(Blocks.TREACHEROUS_VINES, "詭譎藤");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.TREACHEROUS_SAC_BREAK), "詭譎囊破裂");
			translationBuilder.add(getCommentKey(SpontaneousReplace.DATA.getId() + ".spider"), "自然更替蜘蛛部分翻譯");
			translationBuilder.add(Items.SPIDER_LEG, "蜘蛛腿");
			translationBuilder.add(Items.SPIDER_LEATHER, "蜘蛛護皮");
			translationBuilder.add(Items.SPIDER_FANG, "蜘蛛毒牙");
			translationBuilder.add(Items.COMPACT_GOSSAMER, "緻密蛛絲");
			translationBuilder.add(Items.STICKY_COMPACT_GOSSAMER, "黏密蛛絲");
			translationBuilder.add(Items.DEPOISON_SPIDER_LEG, "去毒蜘蛛腿");
			translationBuilder.add(Items.COMPACT_STRING, "緻密絲線");
			translationBuilder.add(Items.COMPOSITE_STRING, "複合絲線");
			translationBuilder.add(Items.COMPOSITE_FABRIC, "複合面料");
			translationBuilder.add(Items.SPIDER_LEATHER_CAP, "蜘蛛皮帽");
			translationBuilder.add(Items.SPIDER_LEATHER_TUNIC, "蜘蛛皮甲");
			translationBuilder.add(EntityTypes.SPIDER_LARVA, "幼蛛");
			translationBuilder.add(EntityTypes.GUARD_SPIDER, "蜘蛛衞兵");
			translationBuilder.add(EntityTypes.SPRAY_POISON_SPIDER, "噴吐毒蛛");
			translationBuilder.add(EntityTypes.WEAVING_WEB_SPIDER, "織網蜘蛛");
			translationBuilder.add(Items.SPIDER_LARVA_SPAWN_EGG, "幼蛛 生成蛋");
			translationBuilder.add(Items.GUARD_SPIDER_SPAWN_EGG, "蜘蛛衞兵 生成蛋");
			translationBuilder.add(Items.SPRAY_POISON_SPIDER_SPAWN_EGG, "噴吐毒蛛 生成蛋");
			translationBuilder.add(Items.WEAVING_WEB_SPIDER_SPAWN_EGG, "織網蜘蛛 生成蛋");
			translationBuilder.add(Blocks.COBWEBBY_SOIL, "絲壤");
			translationBuilder.add(Blocks.GOSSAMER_CARPET, "絲苔");
			translationBuilder.add(Blocks.GOSSAMERY_LEAVES, "覆絲樹葉");
			translationBuilder.add(Blocks.STICKY_COMPACT_COBWEB, "黏密蛛網");
			translationBuilder.add(Blocks.SPIDER_CHRYSALIS, "蜘蛛繭蛹");
			translationBuilder.add(Blocks.SPIDER_EGG_COCOON, "蜘蛛卵繭");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.SPRAY_TOXIN), "噴吐毒蛛噴吐毒素");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_SPIDER_LEATHER_ARMOR.value()), "裝備蜘蛛皮甲");
			translationBuilder.add(getCommentKey("advancements"), "進度翻譯");
			translationBuilder.add(getCommentKey("advancements.vanilla"), "Minecraft 拓展進度翻譯");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.VANILLA_ROOT.apply(registryLookup)), "Minecraft 拓展");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.VANILLA_ROOT.apply(registryLookup)), "遊玩來自《自然更替》為您提供嘅原版拓展");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_NEW_METAL.apply(registryLookup)), "大膽嘅嘗試");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_NEW_METAL.apply(registryLookup)), "嘗試製作一種新嘅金屬");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_REFINED_COPPER.apply(registryLookup)), "有用嘅銅，終於！");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_REFINED_COPPER.apply(registryLookup)), "冶煉出一塊精銅錠");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_REFINED_COPPER_PRODUCT.apply(registryLookup)), "“精銅”時代");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_REFINED_COPPER_PRODUCT.apply(registryLookup)), "使用精銅製品");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_ALLOY.apply(registryLookup)), "合金工藝");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_ALLOY.apply(registryLookup)), "冶煉出一塊合金錠");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.UPGRADE_IRON_PICKAXE.apply(registryLookup)), "再上一層樓");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.UPGRADE_IRON_PICKAXE.apply(registryLookup)), "把你嘅鐵鎬升級成銅鐵合金");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.UPGRADE_IRON_ARMOR.apply(registryLookup)), "Alloy Gear");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.UPGRADE_IRON_ARMOR.apply(registryLookup)), "打造銅鐵合金裝備來替換你嘅老舊裝甲");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_STEEL_PRODUCT.apply(registryLookup)), "工業時代");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_STEEL_PRODUCT.apply(registryLookup)), "使用鋼鐵製品");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_STEEL_ARMORS.apply(registryLookup)), "鋼鐵雄心");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_STEEL_ARMORS.apply(registryLookup)), "獲得一整套鋼鐵盔甲");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_NEW_METALS.apply(registryLookup)), "鍊金術師");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_NEW_METALS.apply(registryLookup)), "製作出所有新金屬");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_NEW_RANGED.apply(registryLookup)), "時代更替");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_NEW_RANGED.apply(registryLookup)), "嘗試製作一種新型嘅遠程裝備");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_ARROWPROOF_VEST.apply(registryLookup)), "防彈衣？");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_ARROWPROOF_VEST.apply(registryLookup)), "製作一種彈射物防禦裝備");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_SLINGSHOT.apply(registryLookup)), "能打鳥嗎？");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_SLINGSHOT.apply(registryLookup)), "使用一次丫彈弓");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_SLINGSHOT_WITH_ENDER_PEARL.apply(registryLookup)), "傳送門 3");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_SLINGSHOT_WITH_ENDER_PEARL.apply(registryLookup)), "使用丫彈弓發射末影珍珠");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_SLINGSHOT_WITH_POTION.apply(registryLookup)), "阿瓦達索命");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_SLINGSHOT_WITH_POTION.apply(registryLookup)), "使用丫彈弓發射藥水瓶");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "神奇嘅中國連弩");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "擁有一種獨特嘅中國連發弩");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_LEGEND_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "清空彈倉");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_LEGEND_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "找到傳説中嘅諸葛連弩");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_SHOT_1000_ARROWS.apply(registryLookup)), "箭雨");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_SHOT_1000_ARROWS.apply(registryLookup)), "使用諸葛連弩發射至少1000支箭");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_KILL_100_MONSTERS.apply(registryLookup)), "突擊手");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_KILL_100_MONSTERS.apply(registryLookup)), "使用諸葛連弩擊殺至少100只怪物");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_MARKS_CROSSBOW.apply(registryLookup)), "神奇嘅中國勁弩");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_MARKS_CROSSBOW.apply(registryLookup)), "使用一種強力嘅中國超遠程弩");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_LEGEND_MARKS_CROSSBOW.apply(registryLookup)), "目標鎖定");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_LEGEND_MARKS_CROSSBOW.apply(registryLookup)), "找到傳説中嘅神臂弩");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.MARKSMAN.apply(registryLookup)), "神射手");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.MARKSMAN.apply(registryLookup)), "喺至少 200 米外擊中標靶嘅靶心");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.SNIPING.apply(registryLookup)), "狙擊");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.SNIPING.apply(registryLookup)), "從至少 100 米之外射殺一個流浪者");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_BASIC_RANGED.apply(registryLookup)), "基礎遠程武器");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_BASIC_RANGED.apply(registryLookup)), "擁有所有嘅基礎型遠程武器");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_ENHANCED_RANGED.apply(registryLookup)), "增強遠程武器");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_ENHANCED_RANGED.apply(registryLookup)), "擁有所有嘅增強型遠程武器");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_RANGED.apply(registryLookup)), "弓弩收藏家");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_RANGED.apply(registryLookup)), "擁有所有嘅遠程武器");
			translationBuilder.add(getCommentKey("advancements." + SpontaneousReplace.DATA.getId()), "自然更替進度翻譯");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.SR_ROOT.apply(registryLookup)), "自然更替");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.SR_ROOT.apply(registryLookup)), "遊玩「自然更替」");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.FIND_A_SPIDER_BIOME.apply(registryLookup)), "蜘蛛羣落");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.FIND_A_SPIDER_BIOME.apply(registryLookup)), "接觸到自然演變嘅蜘蛛羣落");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.KILL_A_NEW_SPIDER.apply(registryLookup)), "新嘅蜘蛛？");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.KILL_A_NEW_SPIDER.apply(registryLookup)), "擊殺一隻特殊嘅蜘蛛變體");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.SHOT_SPRAY_POISON_SPIDER.apply(registryLookup)), "來嚐嚐這個");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.SHOT_SPRAY_POISON_SPIDER.apply(registryLookup)), "使用箭射中噴吐毒蛛");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_DEPOISON_SPIDER_LEG.apply(registryLookup)), "新式腐肉");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_DEPOISON_SPIDER_LEG.apply(registryLookup)), "烹飪一份蜘蛛腿");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_EFFECT_SPIDER_CAMOUFLAGE.apply(registryLookup)), "蜘蛛偽裝");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_EFFECT_SPIDER_CAMOUFLAGE.apply(registryLookup)), "裝備蜘蛛護皮套裝以避免觸發蜘蛛卵繭");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.KILL_ALL_SPIDERS.apply(registryLookup)), "節肢剋星");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.KILL_ALL_SPIDERS.apply(registryLookup)), "擊殺所有種類嘅蜘蛛");
		}
	}
	
	final class ZhTw extends SilkLanguageProvider {
		public ZhTw(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
			super(dataOutput, "zh_tw", registryLookup);
		}
		
		@Override
		public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
			translationBuilder.add(getCommentKey("title"), "原生語言為簡體中文(zh_cn)");
			translationBuilder.add(getCommentKey("modmenu"), "Mod Menu 上可翻譯的模組描述與摘要");
			translationBuilder.add(getLocalizationNameKey(SpontaneousReplace.DATA), "§6自然更替");
			translationBuilder.add(getLocalizationSummaryKey(SpontaneousReplace.DATA), "透過新內容為玩家提供加強的原版冒險");
			translationBuilder.add(getLocalizationDescriptionKey(SpontaneousReplace.DATA), """
					§l模組簡介:§r
					　　狂風嗚咽，雷轟電鳴...在·Minecraft·的世界上的某種能量喚醒了生物體內的潛能。
					　　五種元素的力量會帶你走向一切的起源。尋找，探索，擊敗或饒恕他們。
					　　你是會成為眾生物的敬仰者，還是無盡的屠殺者，一切皆是你的選擇...
					§l模組願景:§r
					　　我希望做一個基於原版遊戲核心，不破壞原版遊戲玩法的玩法類模組。在這基礎上進行開發十分困難，一件物品是否加入，怎樣設計資料才不會破壞遊戲平衡性，這些都是開發者需要考慮的點。
					　　如果你覺得模組做的不錯，歡迎對我的專案進行贊助，或者對此模組進行翻譯，十分感謝你們!""");
			translationBuilder.add(getCommentKey("generic"), "通用模組元件翻譯");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, ""), "自然更替");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.mod"), "模組");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "target"), "0.5.X 目標");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "target"), "　　繼續完善新生物生態域，繼續新增原有·0.5.0·計劃但未實現的內容");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "changelog"), "更新日誌");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.synopsis"), "簡介");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "tab.synopsis.intro"), "§f§l模組簡介：");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.synopsis.intro"), """
					§r　　狂風嗚咽，雷轟電鳴...在·Minecraft·的世界上的某種能量喚醒了生物體內的潛能。
					　　五種元素的力量會帶你走向一切的起源。尋找，探索，擊敗或饒恕他們。
					　　你是會成為眾生物的敬仰者，還是無盡的屠殺者，一切皆是你的選擇......""");
			translationBuilder.add(TextUtil.widgetTitle(SpontaneousReplace.DATA, "tab.synopsis.vision"), "§f§l模組願景：");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.synopsis.vision"),
					"　　我希望做一個基於原版遊戲核心，不破壞原版遊戲玩法的玩法類模組。在這基礎上進行開發十分困難，一件物品是否加入，怎樣設計資料才不會破壞遊戲平衡性，這些都是開發者需要考慮的點。\n" +
							"　　如果你覺得模組做的不錯，歡迎對我的專案進行贊助，或者對此模組進行翻譯，十分感謝你們!");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "tab.config"), "設定");
			translationBuilder.add(widgetText(SpontaneousReplace.DATA, "homepage"), "模組官網");
			translationBuilder.add(widgetText(SpontaneousReplace.DATA, "support"), "支援我們");
			translationBuilder.add(widgetText(SpontaneousReplace.DATA, "community"), "官方社群");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, ""), "自然更替設定...");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, "update"), "模組更新設定");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, "update"), "");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, Configs.AUTO_SHOW_SETTINGS_BUTTON), "自動顯示設定按鈕");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, Configs.AUTO_SHOW_SETTINGS_BUTTON + ".on"), "如果安裝模組選單則自動關閉選項介面按鈕");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, Configs.AUTO_SHOW_SETTINGS_BUTTON + ".off"), "始終在選項介面顯示設定按鈕");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, Configs.DISABLE_WARNING_ADVICE), "禁用“實驗性設定”警告");
			translationBuilder.add(TextUtil.configText(SpontaneousReplace.DATA, Configs.DISABLE_WARNING_ADVICE + ".need_other_mod"), "需要其他模組");
			translationBuilder.add(TextUtil.configTip(SpontaneousReplace.DATA, Configs.DISABLE_WARNING_ADVICE + ".need_other_mod"), "前往 https://modrinth.com/mod/dcwa 安裝此模組開啟功能");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "generator.classic"), "自然更替：");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "generator.snapshot"), "自然更替快照：");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "generator.non_version"), "過舊");
			translationBuilder.add(TextUtil.widgetText(SpontaneousReplace.DATA, "placeholder"), "此功能尚未新增, 請繼續關注本模組首頁以及時獲取更新。");
			translationBuilder.add(getWorldPresetKey(WorldPresets.CLASSIC), "自然更替");
			translationBuilder.add(getWorldPresetKey(WorldPresets.SNAPSHOT), "自然更替快照");
			translationBuilder.add(ResourcesPack.getName(SpontaneousReplace.DATA), "§6「自然更替」預設資源包");
			translationBuilder.add(DataPack.getName(SpontaneousReplace.DATA), "§6「自然更替」預設資料包");
			translationBuilder.add(getCommentKey("effects"), "狀態效果翻譯");
			translationBuilder.add(StatusEffects.ACIDIZE.value(), "酸化");
			translationBuilder.add(StatusEffects.SPIDER_CAMOUFLAGE.value(), "蜘蛛偽裝");
			translationBuilder.add(getCommentKey("death message"), "死亡訊息翻譯");
			translationBuilder.add("death.attack.acidize", "%1$s 溶解了");
			translationBuilder.add("death.attack.acidize.player", "%1$s 在與 %1$s 戰鬥時溶解了");
			translationBuilder.add("death.attack.spider_toxin", "%1$s 未能躲過 %2$s 的毒素");
			translationBuilder.add("death.attack.spider_toxin.item", "%1$s 被 %2$s 用 %3$s 打出的毒素殺死");
			translationBuilder.add(getCommentKey("itemGroup"), "物品組翻譯");
			translationBuilder.add(ItemGroups.BUILDING_BLOCKS, "「自然更替」建築方塊");
			translationBuilder.add(ItemGroups.COLORED_BLOCKS, "「自然更替」染色方塊");
			translationBuilder.add(ItemGroups.NATURAL, "「自然更替」自然方塊");
			translationBuilder.add(ItemGroups.FUNCTIONAL, "「自然更替」功能方塊");
			translationBuilder.add(ItemGroups.REDSTONE, "「自然更替」紅石方塊");
			translationBuilder.add(ItemGroups.TOOLS, "「自然更替」工具與實用物品");
			translationBuilder.add(ItemGroups.COMBAT, "「自然更替」戰鬥");
			translationBuilder.add(ItemGroups.FOOD_AND_DRINK, "「自然更替」食物與飲品");
			translationBuilder.add(ItemGroups.INGREDIENTS, "「自然更替」原材料");
			translationBuilder.add(ItemGroups.SPAWN_EGGS, "「自然更替」生怪蛋");
			translationBuilder.add(getCommentKey("vanilla"), "原版拓展翻譯");
			translationBuilder.add(Items.COPPER_FOR_SMELTING_INGOT, "煉錠銅");
			translationBuilder.add(Items.REFINED_COPPER_INGOT, "精銅錠");
			translationBuilder.add(Items.REFINED_COPPER_NUGGET, "精銅粒");
			translationBuilder.add(Items.REFINED_COPPER_SHOVEL, "精銅鏟");
			translationBuilder.add(Items.REFINED_COPPER_PICKAXE, "精銅鎬");
			translationBuilder.add(Items.REFINED_COPPER_AXE, "精銅斧");
			translationBuilder.add(Items.REFINED_COPPER_HOE, "精銅鋤");
			translationBuilder.add(Items.REFINED_COPPER_SWORD, "精銅劍");
			translationBuilder.add(Items.REFINED_COPPER_HELMET, "精銅頭盔");
			translationBuilder.add(Items.REFINED_COPPER_CHESTPLATE, "精銅胸甲");
			translationBuilder.add(Items.REFINED_COPPER_LEGGINGS, "精銅護腿");
			translationBuilder.add(Items.REFINED_COPPER_BOOTS, "精銅靴子");
			translationBuilder.add(Items.CUFE_ALLOY, "銅鐵合金");
			translationBuilder.add(Items.CUFE_ALLOY_INGOT, "銅鐵錠");
			translationBuilder.add(Items.CUFE_ALLOY_NUGGET, "銅鐵粒");
			translationBuilder.add(Items.CUFE_ALLOY_SHOVEL, "銅鐵鏟");
			translationBuilder.add(Items.CUFE_ALLOY_PICKAXE, "銅鐵鎬");
			translationBuilder.add(Items.CUFE_ALLOY_AXE, "銅鐵斧");
			translationBuilder.add(Items.CUFE_ALLOY_HOE, "銅鐵鋤");
			translationBuilder.add(Items.CUFE_ALLOY_SWORD, "銅鐵劍");
			translationBuilder.add(Items.CUFE_ALLOY_HELMET, "銅鐵頭盔");
			translationBuilder.add(Items.CUFE_ALLOY_CHESTPLATE, "銅鐵胸甲");
			translationBuilder.add(Items.CUFE_ALLOY_LEGGINGS, "銅鐵護腿");
			translationBuilder.add(Items.CUFE_ALLOY_BOOTS, "銅鐵靴子");
			translationBuilder.add(Items.AUCU_ALLOY, "金銅合金");
			translationBuilder.add(Items.AUCU_ALLOY_INGOT, "金銅錠");
			translationBuilder.add(Items.AUCU_ALLOY_NUGGET, "金銅粒");
			translationBuilder.add(Items.AUCU_ALLOY_SHOVEL, "金銅鏟");
			translationBuilder.add(Items.AUCU_ALLOY_PICKAXE, "金銅鎬");
			translationBuilder.add(Items.AUCU_ALLOY_AXE, "金銅斧");
			translationBuilder.add(Items.AUCU_ALLOY_HOE, "金銅鋤");
			translationBuilder.add(Items.AUCU_ALLOY_SWORD, "金銅劍");
			translationBuilder.add(Items.AUCU_ALLOY_HELMET, "金銅頭盔");
			translationBuilder.add(Items.AUCU_ALLOY_CHESTPLATE, "金銅胸甲");
			translationBuilder.add(Items.AUCU_ALLOY_LEGGINGS, "金銅護腿");
			translationBuilder.add(Items.AUCU_ALLOY_BOOTS, "金銅靴子");
			translationBuilder.add(Items.CLEAN_COAL, "精煤");
			translationBuilder.add(Items.PIG_IRON, "生鐵");
			translationBuilder.add(Items.STEEL_INGOT, "鋼錠");
			translationBuilder.add(Items.STEEL_SHOVEL, "鋼鏟");
			translationBuilder.add(Items.STEEL_PICKAXE, "鋼鎬");
			translationBuilder.add(Items.STEEL_AXE, "鋼斧");
			translationBuilder.add(Items.STEEL_HOE, "鋼鋤");
			translationBuilder.add(Items.STEEL_SWORD, "鋼劍");
			translationBuilder.add(Items.STEEL_HELMET, "鋼製頭盔");
			translationBuilder.add(Items.STEEL_CHESTPLATE, "鋼製胸甲");
			translationBuilder.add(Items.STEEL_LEGGINGS, "鋼製護腿");
			translationBuilder.add(Items.STEEL_BOOTS, "鋼製靴子");
			translationBuilder.add(Items.STONEBALL, "石彈");
			translationBuilder.add(Items.STEEL_ARROW, "鋼箭矢");
			translationBuilder.add(Items.SLINGSHOT, "丫彈弓");
			translationBuilder.add(Items.RECURVE_BOW, "反曲弓");
			translationBuilder.add(Items.ARBALEST, "勁弩");
			translationBuilder.add(Items.COMPOUND_BOW, "複合弓");
			translationBuilder.add(Items.JUGER_REPEATING_CROSSBOW, "諸葛連弩");
			translationBuilder.add(Items.MARKS_CROSSBOW, "神臂弩");
			translationBuilder.add(Items.ARROWPROOF_VEST, "防箭衣");
			translationBuilder.add(Blocks.COPPER_FOR_SMELTING_INGOT_BLOCK, "煉錠銅方塊");
			translationBuilder.add(Blocks.REFINED_COPPER_BLOCK, "精銅方塊");
			translationBuilder.add(Blocks.CUFE_ALLOY_BLOCK, "銅鐵合金方塊");
			translationBuilder.add(Blocks.CUFE_BLOCK, "銅鐵方塊");
			translationBuilder.add(Blocks.AUCU_ALLOY_BLOCK, "金銅合金方塊");
			translationBuilder.add(Blocks.AUCU_BLOCK, "金銅方塊");
			translationBuilder.add(Blocks.PIG_IRON_BLOCK, "生鐵方塊");
			translationBuilder.add(Blocks.STEEL_BLOCK, "鋼方塊");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_REFINED_COPPER.value()), "精铜盔甲裝備聲");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_CUFE_ALLOY.value()), "銅鐵盔甲裝備聲");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_AUCU_ALLOY.value()), "金銅盔甲裝備聲");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_STEEL.value()), "鋼製盔甲裝備聲");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.STONEBALL_THROW), "投擲石彈聲");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.SLINGSHOT_THROW), "投擲彈丸");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.ARBALEST_LOADING_START), "勁弩上弦");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.ARBALEST_LOADING_END), "勁弩裝填");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.ARBALEST_SHOOT), "勁弩發射");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.JUGER_REPEATING_CROSSBOW_LOADING_START), "諸葛連弩裝填");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.JUGER_REPEATING_CROSSBOW_LOADING_END), "諸葛連弩上弦");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.JUGER_REPEATING_CROSSBOW_SHOOT), "諸葛連弩發射");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.MARKS_CROSSBOW_LOADING_START), "神臂弩上弦");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.MARKS_CROSSBOW_LOADING_END), "神臂弩裝填");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.MARKS_CROSSBOW_SHOOT), "神臂弩發射");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_ARROWPROOF_VEST.value()), "防箭衣裝備聲");
			translationBuilder.add(getCommentKey(SpontaneousReplace.DATA.getId()), "自然更替翻譯");
			translationBuilder.add(Blocks.EERIE_REGOLITH, "陰森浮土");
			translationBuilder.add(Blocks.TREACHEROUS_SLUDGE, "詭譎汙泥");
			translationBuilder.add(Blocks.EERIE_RIND, "陰森木殼");
			translationBuilder.add(Blocks.EERIE_BOUGH, "陰森木枝");
			translationBuilder.add(Blocks.TREACHEROUS_SAC, "詭譎囊");
			translationBuilder.add(Blocks.TREACHEROUS_VINES, "詭譎藤");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.TREACHEROUS_SAC_BREAK), "詭譎囊破裂");
			translationBuilder.add(getCommentKey(SpontaneousReplace.DATA.getId() + ".spider"), "自然更替蜘蛛部分翻譯");
			translationBuilder.add(Items.SPIDER_LEG, "蜘蛛腿");
			translationBuilder.add(Items.SPIDER_LEATHER, "蜘蛛護皮");
			translationBuilder.add(Items.SPIDER_FANG, "蜘蛛毒牙");
			translationBuilder.add(Items.COMPACT_GOSSAMER, "緻密蛛絲");
			translationBuilder.add(Items.STICKY_COMPACT_GOSSAMER, "黏密蛛絲");
			translationBuilder.add(Items.DEPOISON_SPIDER_LEG, "去毒蜘蛛腿");
			translationBuilder.add(Items.COMPACT_STRING, "緻密絲線");
			translationBuilder.add(Items.COMPOSITE_STRING, "複合絲線");
			translationBuilder.add(Items.COMPOSITE_FABRIC, "複合面料");
			translationBuilder.add(Items.SPIDER_LEATHER_CAP, "蜘蛛皮帽");
			translationBuilder.add(Items.SPIDER_LEATHER_TUNIC, "蜘蛛皮甲");
			translationBuilder.add(EntityTypes.SPIDER_LARVA, "幼蛛");
			translationBuilder.add(EntityTypes.GUARD_SPIDER, "蜘蛛衛兵");
			translationBuilder.add(EntityTypes.SPRAY_POISON_SPIDER, "噴吐毒蛛");
			translationBuilder.add(EntityTypes.WEAVING_WEB_SPIDER, "織網蜘蛛");
			translationBuilder.add(Items.SPIDER_LARVA_SPAWN_EGG, "幼蛛 生怪蛋");
			translationBuilder.add(Items.GUARD_SPIDER_SPAWN_EGG, "蜘蛛衛兵 生怪蛋");
			translationBuilder.add(Items.SPRAY_POISON_SPIDER_SPAWN_EGG, "噴吐毒蛛 生怪蛋");
			translationBuilder.add(Items.WEAVING_WEB_SPIDER_SPAWN_EGG, "織網蜘蛛 生怪蛋");
			translationBuilder.add(Blocks.COBWEBBY_SOIL, "絲壤");
			translationBuilder.add(Blocks.GOSSAMER_CARPET, "覆地蛛絲");
			translationBuilder.add(Blocks.GOSSAMERY_LEAVES, "覆絲樹葉");
			translationBuilder.add(Blocks.STICKY_COMPACT_COBWEB, "黏密蛛網");
			translationBuilder.add(Blocks.SPIDER_CHRYSALIS, "蜘蛛繭蛹");
			translationBuilder.add(Blocks.SPIDER_EGG_COCOON, "蜘蛛卵繭");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.SPRAY_TOXIN), "噴吐毒蛛噴吐毒素");
			translationBuilder.add(getSoundEventSubtitlesKey(SoundEvents.EQUIP_SPIDER_LEATHER_ARMOR.value()), "蜘蛛皮甲裝備聲");
			translationBuilder.add(getCommentKey("advancements"), "進度翻譯");
			translationBuilder.add(getCommentKey("advancements.vanilla"), "Minecraft 拓展進度翻譯");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.VANILLA_ROOT.apply(registryLookup)), "Minecraft 拓展");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.VANILLA_ROOT.apply(registryLookup)), "遊玩來自《自然更替》為您提供的原版拓展");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_NEW_METAL.apply(registryLookup)), "大膽的嘗試");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_NEW_METAL.apply(registryLookup)), "嘗試製作一種新的金屬");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_REFINED_COPPER.apply(registryLookup)), "有用的銅，終於！");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_REFINED_COPPER.apply(registryLookup)), "冶煉出精銅錠");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_REFINED_COPPER_PRODUCT.apply(registryLookup)), "“精銅”時代");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_REFINED_COPPER_PRODUCT.apply(registryLookup)), "使用精銅製品");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_ALLOY.apply(registryLookup)), "合金工藝");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_ALLOY.apply(registryLookup)), "冶煉出合金錠");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.UPGRADE_IRON_PICKAXE.apply(registryLookup)), "再次升級");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.UPGRADE_IRON_PICKAXE.apply(registryLookup)), "把你的鐵鎬升級成銅鐵合金");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.UPGRADE_IRON_ARMOR.apply(registryLookup)), "潜龍谍影");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.UPGRADE_IRON_ARMOR.apply(registryLookup)), "打造銅鐵合金裝備來替換你的老舊裝甲");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_STEEL_PRODUCT.apply(registryLookup)), "工業時代");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_STEEL_PRODUCT.apply(registryLookup)), "使用鋼鐵製品");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_STEEL_ARMORS.apply(registryLookup)), "鋼鐵雄心");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_STEEL_ARMORS.apply(registryLookup)), "獲得一整套鋼鐵盔甲");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_NEW_METALS.apply(registryLookup)), "鍊金術師");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_NEW_METALS.apply(registryLookup)), "製作出所有新金屬");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_NEW_RANGED.apply(registryLookup)), "時代更替");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_NEW_RANGED.apply(registryLookup)), "嘗試製作一種新型的遠端裝備");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_ARROWPROOF_VEST.apply(registryLookup)), "防彈衣？");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_ARROWPROOF_VEST.apply(registryLookup)), "製作一種彈射物防禦裝備");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_SLINGSHOT.apply(registryLookup)), "能打鳥嗎？");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_SLINGSHOT.apply(registryLookup)), "使用一次丫彈弓");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_SLINGSHOT_WITH_ENDER_PEARL.apply(registryLookup)), "傳送門 3");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_SLINGSHOT_WITH_ENDER_PEARL.apply(registryLookup)), "使用丫彈弓發射末影珍珠");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_SLINGSHOT_WITH_POTION.apply(registryLookup)), "阿瓦達索命");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_SLINGSHOT_WITH_POTION.apply(registryLookup)), "使用丫彈弓發射藥水瓶");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "神奇的中國連弩");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "擁有一種獨特的中國連發弩");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_LEGEND_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "清空彈倉");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_LEGEND_JUGER_REPEATING_CROSSBOW.apply(registryLookup)), "找到傳說中的諸葛連弩");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_SHOT_1000_ARROWS.apply(registryLookup)), "箭雨");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_SHOT_1000_ARROWS.apply(registryLookup)), "使用諸葛連弩發射至少1000支箭");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_KILL_100_MONSTERS.apply(registryLookup)), "突擊手");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.USE_JUGER_REPEATING_CROSSBOW_KILL_100_MONSTERS.apply(registryLookup)), "使用諸葛連弩擊殺至少100只怪物");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_MARKS_CROSSBOW.apply(registryLookup)), "神奇的中國勁弩");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_MARKS_CROSSBOW.apply(registryLookup)), "使用一種強力的中國超遠端弩");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_LEGEND_MARKS_CROSSBOW.apply(registryLookup)), "目標鎖定");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_LEGEND_MARKS_CROSSBOW.apply(registryLookup)), "找到傳說中的神臂弩");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.MARKSMAN.apply(registryLookup)), "神射手");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.MARKSMAN.apply(registryLookup)), "從至少200公尺外擊中標靶的靶心");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.SNIPING.apply(registryLookup)), "狙擊");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.SNIPING.apply(registryLookup)), "在距100公尺遠外的地方射殺一隻流髑");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_BASIC_RANGED.apply(registryLookup)), "基礎遠端武器");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_BASIC_RANGED.apply(registryLookup)), "擁有所有的基礎型遠端武器");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_ENHANCED_RANGED.apply(registryLookup)), "增強遠端武器");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_ENHANCED_RANGED.apply(registryLookup)), "擁有所有的增強型遠端武器");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_ALL_RANGED.apply(registryLookup)), "弓弩收藏家");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_ALL_RANGED.apply(registryLookup)), "擁有所有的遠端武器");
			translationBuilder.add(getCommentKey("advancements." + SpontaneousReplace.DATA.getId()), "自然更替進度翻譯");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.SR_ROOT.apply(registryLookup)), "自然更替");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.SR_ROOT.apply(registryLookup)), "遊玩「自然更替」");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.FIND_A_SPIDER_BIOME.apply(registryLookup)), "蜘蛛生態域");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.FIND_A_SPIDER_BIOME.apply(registryLookup)), "接觸到自然演變的蜘蛛生態域");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.KILL_A_NEW_SPIDER.apply(registryLookup)), "新的蜘蛛？");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.KILL_A_NEW_SPIDER.apply(registryLookup)), "擊殺一隻特殊的蜘蛛變體");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.SHOT_SPRAY_POISON_SPIDER.apply(registryLookup)), "來嚐嚐這個");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.SHOT_SPRAY_POISON_SPIDER.apply(registryLookup)), "使用箭射中噴吐毒蛛");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_A_DEPOISON_SPIDER_LEG.apply(registryLookup)), "新式腐肉");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_A_DEPOISON_SPIDER_LEG.apply(registryLookup)), "烹飪一份蜘蛛腿");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.HAVE_EFFECT_SPIDER_CAMOUFLAGE.apply(registryLookup)), "蜘蛛偽裝");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.HAVE_EFFECT_SPIDER_CAMOUFLAGE.apply(registryLookup)), "裝備蜘蛛護皮套裝以避免觸發蜘蛛卵繭");
			translationBuilder.add(getAdvancementTitleKey(AdvancementGenerator.KILL_ALL_SPIDERS.apply(registryLookup)), "節肢剋星");
			translationBuilder.add(getAdvancementDescriptionKey(AdvancementGenerator.KILL_ALL_SPIDERS.apply(registryLookup)), "擊殺所有種類的蜘蛛");
		}
	}
}
