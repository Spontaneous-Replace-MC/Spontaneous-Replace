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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtString;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import pers.saikel0rado1iu.silk.gen.data.family.EquipFamily;
import pers.saikel0rado1iu.silk.gen.data.recipe.NbtShapedRecipeJsonBuilder;
import pers.saikel0rado1iu.silk.util.TickUtil;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;

import java.util.List;

import static pers.saikel0rado1iu.silk.gen.data.SilkRecipeJsonBuilder.*;
import static pers.saikel0rado1iu.sr.data.Items.*;

/**
 * <h2 style="color:FFC800">自然更替的配方生成器</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class RecipeGenerator extends FabricRecipeProvider {
	public RecipeGenerator(FabricDataOutput output) {
		super(output);
	}
	
	@Override
	protected Identifier getRecipeIdentifier(Identifier identifier) {
		return new Identifier(SpontaneousReplace.DATA.getId(), identifier.getPath());
	}
	
	@Override
	public void generate(RecipeExporter exporter) {
		offer2x2CompactingRecipeWithRecipeGroup(exporter, RecipeCategory.MISC, Items.COPPER_INGOT, COPPER_FOR_SMELTING_INGOT);
		offerSmithingIngredient(exporter, Ingredient.ofItems(Items.COPPER_INGOT), Ingredient.ofItems(Items.COPPER_INGOT), RecipeCategory.MISC, COPPER_FOR_SMELTING_INGOT);
		offerSmelting(exporter, List.of(COPPER_FOR_SMELTING_INGOT), RecipeCategory.MISC, REFINED_COPPER_INGOT, 0.8F, TickUtil.getTick(20), getItemPath(REFINED_COPPER_INGOT));
		offerBlasting(exporter, List.of(COPPER_FOR_SMELTING_INGOT), RecipeCategory.MISC, REFINED_COPPER_INGOT, 0.8F, TickUtil.getTick(10), getItemPath(REFINED_COPPER_INGOT));
		offerSmeltingInOneJson(exporter, List.of(REFINED_COPPER_SHOVEL, REFINED_COPPER_PICKAXE, REFINED_COPPER_AXE, REFINED_COPPER_HOE, REFINED_COPPER_SWORD, REFINED_COPPER_HELMET, REFINED_COPPER_CHESTPLATE, REFINED_COPPER_LEGGINGS, REFINED_COPPER_BOOTS),
				RecipeCategory.MISC, REFINED_COPPER_NUGGET, 0.15F, TickUtil.getTick(20), getItemPath(REFINED_COPPER_NUGGET));
		offerBlastingInOneJson(exporter, List.of(REFINED_COPPER_SHOVEL, REFINED_COPPER_PICKAXE, REFINED_COPPER_AXE, REFINED_COPPER_HOE, REFINED_COPPER_SWORD, REFINED_COPPER_HELMET, REFINED_COPPER_CHESTPLATE, REFINED_COPPER_LEGGINGS, REFINED_COPPER_BOOTS),
				RecipeCategory.MISC, REFINED_COPPER_NUGGET, 0.15F, TickUtil.getTick(10), getItemPath(REFINED_COPPER_NUGGET));
		offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, REFINED_COPPER_NUGGET, RecipeCategory.MISC, REFINED_COPPER_INGOT,
				convertBetween(REFINED_COPPER_INGOT, REFINED_COPPER_NUGGET), getItemPath(REFINED_COPPER_INGOT), convertBetween(REFINED_COPPER_NUGGET, REFINED_COPPER_INGOT), getItemPath(REFINED_COPPER_NUGGET));
		offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, REFINED_COPPER_INGOT, RecipeCategory.BUILDING_BLOCKS, REFINED_COPPER_BLOCK,
				convertBetween(REFINED_COPPER_BLOCK, REFINED_COPPER_INGOT), getItemPath(REFINED_COPPER_BLOCK), convertBetween(REFINED_COPPER_INGOT, REFINED_COPPER_BLOCK), getItemPath(REFINED_COPPER_INGOT));
		generateEquipRecipe(exporter, Ingredient.ofItems(REFINED_COPPER_INGOT), EquipFamily.builder()
				.shovel(REFINED_COPPER_SHOVEL).pickaxe(REFINED_COPPER_PICKAXE).axe(REFINED_COPPER_AXE).hoe(REFINED_COPPER_HOE).sword(REFINED_COPPER_SWORD)
				.helmet(REFINED_COPPER_HELMET).chestplate(REFINED_COPPER_CHESTPLATE).leggings(REFINED_COPPER_LEGGINGS).boots(REFINED_COPPER_BOOTS).build());
		offer2x2CrossCompactingRecipe(exporter, RecipeCategory.MISC, Ingredient.ofItems(REFINED_COPPER_INGOT), Ingredient.ofItems(Items.IRON_INGOT), CUFE_ALLOY);
		offerSmithingIngredient(exporter, Ingredient.ofItems(REFINED_COPPER_INGOT), Ingredient.ofItems(Items.IRON_INGOT), RecipeCategory.MISC, CUFE_ALLOY);
		offerSmelting(exporter, List.of(CUFE_ALLOY), RecipeCategory.MISC, CUFE_ALLOY_INGOT, 0.8F, TickUtil.getTick(20), getItemPath(CUFE_ALLOY_INGOT));
		offerBlasting(exporter, List.of(CUFE_ALLOY), RecipeCategory.MISC, CUFE_ALLOY_INGOT, 0.8F, TickUtil.getTick(10), getItemPath(CUFE_ALLOY_INGOT));
		offerSmeltingInOneJson(exporter, List.of(CUFE_ALLOY_SHOVEL, CUFE_ALLOY_PICKAXE, CUFE_ALLOY_AXE, CUFE_ALLOY_HOE, CUFE_ALLOY_SWORD, CUFE_ALLOY_HELMET, CUFE_ALLOY_CHESTPLATE, CUFE_ALLOY_LEGGINGS, CUFE_ALLOY_BOOTS),
				RecipeCategory.MISC, CUFE_ALLOY_NUGGET, 0.15F, TickUtil.getTick(20), getItemPath(CUFE_ALLOY_NUGGET));
		offerBlastingInOneJson(exporter, List.of(CUFE_ALLOY_SHOVEL, CUFE_ALLOY_PICKAXE, CUFE_ALLOY_AXE, CUFE_ALLOY_HOE, CUFE_ALLOY_SWORD, CUFE_ALLOY_HELMET, CUFE_ALLOY_CHESTPLATE, CUFE_ALLOY_LEGGINGS, CUFE_ALLOY_BOOTS),
				RecipeCategory.MISC, CUFE_ALLOY_NUGGET, 0.15F, TickUtil.getTick(10), getItemPath(CUFE_ALLOY_NUGGET));
		offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, CUFE_ALLOY_NUGGET, RecipeCategory.MISC, CUFE_ALLOY_INGOT,
				convertBetween(CUFE_ALLOY_INGOT, CUFE_ALLOY_NUGGET), getItemPath(CUFE_ALLOY_INGOT), convertBetween(CUFE_ALLOY_NUGGET, CUFE_ALLOY_INGOT), getItemPath(CUFE_ALLOY_NUGGET));
		offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, CUFE_ALLOY_INGOT, RecipeCategory.BUILDING_BLOCKS, CUFE_ALLOY_BLOCK,
				convertBetween(CUFE_ALLOY_BLOCK, CUFE_ALLOY_INGOT), getItemPath(CUFE_ALLOY_BLOCK), convertBetween(CUFE_ALLOY_INGOT, CUFE_ALLOY_BLOCK), getItemPath(CUFE_ALLOY_INGOT));
		generateEquipRecipe(exporter, Ingredient.ofItems(CUFE_ALLOY_INGOT), EquipFamily.builder()
				.shovel(CUFE_ALLOY_SHOVEL).pickaxe(CUFE_ALLOY_PICKAXE).axe(CUFE_ALLOY_AXE).hoe(CUFE_ALLOY_HOE).sword(CUFE_ALLOY_SWORD)
				.helmet(CUFE_ALLOY_HELMET).chestplate(CUFE_ALLOY_CHESTPLATE).leggings(CUFE_ALLOY_LEGGINGS).boots(CUFE_ALLOY_BOOTS).build());
		offer2x2CrossCompactingRecipe(exporter, RecipeCategory.MISC, Ingredient.ofItems(REFINED_COPPER_INGOT), Ingredient.ofItems(Items.GOLD_INGOT), AUCU_ALLOY);
		offerSmithingIngredient(exporter, Ingredient.ofItems(REFINED_COPPER_INGOT), Ingredient.ofItems(Items.GOLD_INGOT), RecipeCategory.MISC, AUCU_ALLOY);
		offerSmelting(exporter, List.of(AUCU_ALLOY), RecipeCategory.MISC, AUCU_ALLOY_INGOT, 0.8F, TickUtil.getTick(20), getItemPath(AUCU_ALLOY_INGOT));
		offerBlasting(exporter, List.of(AUCU_ALLOY), RecipeCategory.MISC, AUCU_ALLOY_INGOT, 0.8F, TickUtil.getTick(10), getItemPath(AUCU_ALLOY_INGOT));
		offerSmeltingInOneJson(exporter, List.of(AUCU_ALLOY_SHOVEL, AUCU_ALLOY_PICKAXE, AUCU_ALLOY_AXE, AUCU_ALLOY_HOE, AUCU_ALLOY_SWORD, AUCU_ALLOY_HELMET, AUCU_ALLOY_CHESTPLATE, AUCU_ALLOY_LEGGINGS, AUCU_ALLOY_BOOTS),
				RecipeCategory.MISC, AUCU_ALLOY_NUGGET, 0.15F, TickUtil.getTick(20), getItemPath(AUCU_ALLOY_NUGGET));
		offerBlastingInOneJson(exporter, List.of(AUCU_ALLOY_SHOVEL, AUCU_ALLOY_PICKAXE, AUCU_ALLOY_AXE, AUCU_ALLOY_HOE, AUCU_ALLOY_SWORD, AUCU_ALLOY_HELMET, AUCU_ALLOY_CHESTPLATE, AUCU_ALLOY_LEGGINGS, AUCU_ALLOY_BOOTS),
				RecipeCategory.MISC, AUCU_ALLOY_NUGGET, 0.15F, TickUtil.getTick(10), getItemPath(AUCU_ALLOY_NUGGET));
		offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, AUCU_ALLOY_NUGGET, RecipeCategory.MISC, AUCU_ALLOY_INGOT,
				convertBetween(AUCU_ALLOY_INGOT, AUCU_ALLOY_NUGGET), getItemPath(AUCU_ALLOY_INGOT), convertBetween(AUCU_ALLOY_NUGGET, AUCU_ALLOY_INGOT), getItemPath(AUCU_ALLOY_NUGGET));
		offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, AUCU_ALLOY_INGOT, RecipeCategory.BUILDING_BLOCKS, AUCU_ALLOY_BLOCK,
				convertBetween(AUCU_ALLOY_BLOCK, AUCU_ALLOY_INGOT), getItemPath(AUCU_ALLOY_BLOCK), convertBetween(AUCU_ALLOY_INGOT, AUCU_ALLOY_BLOCK), getItemPath(AUCU_ALLOY_INGOT));
		generateEquipRecipe(exporter, Ingredient.ofItems(AUCU_ALLOY_INGOT), EquipFamily.builder()
				.shovel(AUCU_ALLOY_SHOVEL).pickaxe(AUCU_ALLOY_PICKAXE).axe(AUCU_ALLOY_AXE).hoe(AUCU_ALLOY_HOE).sword(AUCU_ALLOY_SWORD)
				.helmet(AUCU_ALLOY_HELMET).chestplate(AUCU_ALLOY_CHESTPLATE).leggings(AUCU_ALLOY_LEGGINGS).boots(AUCU_ALLOY_BOOTS).build());
		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, CLEAN_COAL).group(getItemPath(CLEAN_COAL)).input('#', Items.COAL).input('X', Items.WATER_BUCKET)
				.pattern("###")
				.pattern("#X#")
				.pattern("###")
				.criterion(hasItem(Items.COAL), conditionsFromItem(Items.COAL))
				.criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
				.offerTo(exporter, getItemPath(CLEAN_COAL) + "_less");
		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, CLEAN_COAL, 5).group(getItemPath(CLEAN_COAL)).input('#', Items.COAL_BLOCK).input('X', Items.WATER_BUCKET)
				.pattern("#X")
				.pattern("X#")
				.criterion(hasItem(Items.COAL), conditionsFromItem(Items.COAL))
				.criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
				.offerTo(exporter, getItemPath(CLEAN_COAL) + "_more");
		offer2x2CrossCompactingRecipe(exporter, RecipeCategory.MISC, Ingredient.ofItems(CLEAN_COAL), Ingredient.ofItems(Items.IRON_INGOT), PIG_IRON);
		offerSmithingIngredient(exporter, Ingredient.ofItems(CLEAN_COAL), Ingredient.ofItems(Items.IRON_INGOT), RecipeCategory.MISC, PIG_IRON);
		offerSmelting(exporter, List.of(PIG_IRON), RecipeCategory.MISC, STEEL_INGOT, 0.8F, TickUtil.getTick(20), getItemPath(STEEL_INGOT));
		offerBlasting(exporter, List.of(PIG_IRON), RecipeCategory.MISC, STEEL_INGOT, 0.8F, TickUtil.getTick(10), getItemPath(STEEL_INGOT));
		offerSmeltingInOneJson(exporter, List.of(STEEL_SHOVEL, STEEL_PICKAXE, STEEL_AXE, STEEL_HOE, STEEL_SWORD, STEEL_HELMET, STEEL_CHESTPLATE, STEEL_LEGGINGS, STEEL_BOOTS),
				RecipeCategory.MISC, Items.IRON_INGOT, 0.15F, TickUtil.getTick(20), getItemPath(Items.IRON_INGOT));
		offerBlastingInOneJson(exporter, List.of(STEEL_SHOVEL, STEEL_PICKAXE, STEEL_AXE, STEEL_HOE, STEEL_SWORD, STEEL_HELMET, STEEL_CHESTPLATE, STEEL_LEGGINGS, STEEL_BOOTS),
				RecipeCategory.MISC, Items.IRON_INGOT, 0.15F, TickUtil.getTick(10), getItemPath(Items.IRON_INGOT));
		offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, STEEL_INGOT, RecipeCategory.BUILDING_BLOCKS, STEEL_BLOCK,
				convertBetween(STEEL_BLOCK, STEEL_INGOT), getItemPath(STEEL_BLOCK), convertBetween(STEEL_INGOT, STEEL_BLOCK), getItemPath(STEEL_INGOT));
		generateEquipRecipe(exporter, Ingredient.ofItems(STEEL_INGOT), EquipFamily.builder()
				.shovel(STEEL_SHOVEL).pickaxe(STEEL_PICKAXE).axe(STEEL_AXE).hoe(STEEL_HOE).sword(STEEL_SWORD)
				.helmet(STEEL_HELMET).chestplate(STEEL_CHESTPLATE).leggings(STEEL_LEGGINGS).boots(STEEL_BOOTS).build());
		ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, STONEBALL, 4).group(getItemPath(STONEBALL)).input(Items.COBBLESTONE)
				.criterion(hasItem(Items.COBBLESTONE), conditionsFromItem(Items.COBBLESTONE))
				.offerTo(exporter);
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, STEEL_ARROW).group(getItemPath(STEEL_ARROW)).input('#', STEEL_INGOT).input('X', Items.IRON_INGOT)
				.pattern(" ##")
				.pattern(" X#")
				.pattern("#  ")
				.criterion(hasItem(STEEL_INGOT), conditionsFromItem(STEEL_INGOT))
				.criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
				.offerTo(exporter, getItemPath(STEEL_ARROW));
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, SLINGSHOT).group(getItemPath(SLINGSHOT)).input('#', Items.STICK).input('X', STICKY_COMPACT_GOSSAMER)
				.pattern("#X#")
				.pattern(" # ")
				.pattern(" # ")
				.criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
				.criterion(hasItem(STICKY_COMPACT_GOSSAMER), conditionsFromItem(STICKY_COMPACT_GOSSAMER))
				.offerTo(exporter, getItemPath(SLINGSHOT));
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, RECURVE_BOW).group(getItemPath(RECURVE_BOW)).input('#', COMPACT_STRING).input('X', Items.STRIPPED_SPRUCE_WOOD).input('@', Items.LEATHER)
				.pattern("@X#")
				.pattern("X #")
				.pattern("@X#")
				.criterion(hasItem(COMPACT_STRING), conditionsFromItem(COMPACT_STRING))
				.criterion(hasItem(Items.STRIPPED_SPRUCE_WOOD), conditionsFromItem(Items.STRIPPED_SPRUCE_WOOD))
				.criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
				.offerTo(exporter, getItemPath(RECURVE_BOW));
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ARBALEST).group(getItemPath(ARBALEST)).input('#', COMPACT_STRING).input('X', RECURVE_BOW).input('@', Items.STRIPPED_ACACIA_WOOD).input('$', Items.LEVER)
				.pattern("@X@")
				.pattern("#$#")
				.pattern(" @ ")
				.criterion(hasItem(COMPACT_STRING), conditionsFromItem(COMPACT_STRING))
				.criterion(hasItem(RECURVE_BOW), conditionsFromItem(RECURVE_BOW))
				.criterion(hasItem(Items.STRIPPED_ACACIA_WOOD), conditionsFromItem(Items.STRIPPED_ACACIA_WOOD))
				.offerTo(exporter, getItemPath(ARBALEST));
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, COMPOUND_BOW).group(getItemPath(COMPOUND_BOW)).input('#', COMPOSITE_STRING).input('X', STEEL_INGOT).input('@', Items.DIAMOND).input('$', Items.SPYGLASS)
				.pattern("$X@")
				.pattern("XX#")
				.pattern("@##")
				.criterion(hasItem(COMPOSITE_STRING), conditionsFromItem(COMPOSITE_STRING))
				.criterion(hasItem(STEEL_INGOT), conditionsFromItem(STEEL_INGOT))
				.criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
				.criterion(hasItem(Items.SPYGLASS), conditionsFromItem(Items.SPYGLASS))
				.offerTo(exporter, getItemPath(COMPOUND_BOW));
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, JUGER_REPEATING_CROSSBOW).group(getItemPath(JUGER_REPEATING_CROSSBOW)).input('#', COMPOSITE_STRING).input('X', ARBALEST).input('@', Items.STRIPPED_CRIMSON_HYPHAE).input('$', Items.CHEST)
				.pattern("$@#")
				.pattern("@X ")
				.pattern("# @")
				.criterion(hasItem(COMPOSITE_STRING), conditionsFromItem(COMPOSITE_STRING))
				.criterion(hasItem(ARBALEST), conditionsFromItem(ARBALEST))
				.criterion(hasItem(Items.STRIPPED_CRIMSON_HYPHAE), conditionsFromItem(Items.STRIPPED_CRIMSON_HYPHAE))
				.offerTo(exporter, getItemPath(JUGER_REPEATING_CROSSBOW));
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, MARKS_CROSSBOW).group(getItemPath(MARKS_CROSSBOW)).input('#', COMPOSITE_STRING).input('X', COMPOUND_BOW).input('@', Items.STRIPPED_WARPED_HYPHAE).input('$', Items.LEVER).input('&', Items.NETHERITE_INGOT)
				.pattern("$@&")
				.pattern("@X#")
				.pattern("&#@")
				.criterion(hasItem(COMPOSITE_STRING), conditionsFromItem(COMPOSITE_STRING))
				.criterion(hasItem(COMPOUND_BOW), conditionsFromItem(COMPOUND_BOW))
				.criterion(hasItem(Items.STRIPPED_WARPED_HYPHAE), conditionsFromItem(Items.STRIPPED_WARPED_HYPHAE))
				.criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
				.offerTo(exporter, getItemPath(MARKS_CROSSBOW));
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ARROWPROOF_VEST).group(getItemPath(ARROWPROOF_VEST)).input('#', COMPOSITE_FABRIC).input('X', STEEL_INGOT)
				.pattern("# #")
				.pattern("#X#")
				.pattern("#X#")
				.criterion(hasItem(COMPOSITE_FABRIC), conditionsFromItem(COMPOSITE_FABRIC))
				.criterion(hasItem(STEEL_INGOT), conditionsFromItem(STEEL_INGOT))
				.offerTo(exporter, getItemPath(ARROWPROOF_VEST));
		CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(SPIDER_LEG), RecipeCategory.FOOD, DEPOISON_SPIDER_LEG, 0.35F, TickUtil.getTick(10))
				.criterion(hasItem(SPIDER_LEG), conditionsFromItem(SPIDER_LEG))
				.offerTo(exporter, RecipeProvider.getSmeltingItemPath(DEPOISON_SPIDER_LEG));
		CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(SPIDER_LEG), RecipeCategory.FOOD, DEPOISON_SPIDER_LEG, 0.35F, TickUtil.getTick(5))
				.criterion(hasItem(SPIDER_LEG), conditionsFromItem(SPIDER_LEG))
				.offerTo(exporter, RecipeProvider.convertBetween(DEPOISON_SPIDER_LEG, Items.SMOKER));
		CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(SPIDER_LEG), RecipeCategory.FOOD, DEPOISON_SPIDER_LEG, 0.35F, TickUtil.getTick(30))
				.criterion(hasItem(SPIDER_LEG), conditionsFromItem(SPIDER_LEG))
				.offerTo(exporter, RecipeProvider.convertBetween(DEPOISON_SPIDER_LEG, Items.CAMPFIRE));
		offerCrossCompactingRecipe(exporter, RecipeCategory.MISC, Ingredient.ofItems(Items.STRING), Ingredient.ofItems(COMPACT_GOSSAMER), COMPACT_STRING);
		offerCrossCompactingRecipe(exporter, RecipeCategory.MISC, Ingredient.ofItems(COMPACT_STRING), Ingredient.ofItems(STICKY_COMPACT_GOSSAMER), COMPOSITE_STRING);
		offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, COMPOSITE_STRING, RecipeCategory.MISC, COMPOSITE_FABRIC,
				convertBetween(COMPOSITE_FABRIC, COMPOSITE_STRING), getItemPath(COMPOSITE_FABRIC), convertBetween(COMPOSITE_STRING, COMPOSITE_FABRIC), getItemPath(COMPOSITE_STRING));
		ItemStack out1 = new ItemStack(Items.TIPPED_ARROW, 4);
		out1.setSubNbt("Potion", NbtString.of("poison"));
		NbtShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, out1).group(getItemPath(out1.getItem())).input('#', SPIDER_FANG).input('X', Items.STICK).input('@', Items.FEATHER)
				.pattern("#")
				.pattern("X")
				.pattern("@")
				.criterion(hasItem(SPIDER_FANG), conditionsFromItem(SPIDER_FANG))
				.criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
				.criterion(hasItem(Items.FEATHER), conditionsFromItem(Items.FEATHER))
				.offerTo(exporter, new Identifier(SpontaneousReplace.DATA.getId(), "poison_tipped_arrow_with_spider_fang"));
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, SPIDER_LEATHER_CAP).group(getItemPath(SPIDER_LEATHER_CAP)).input('#', SPIDER_LEATHER).input('X', DEPOISON_SPIDER_LEG)
				.pattern("X#X")
				.pattern("# #")
				.criterion(hasItem(SPIDER_LEATHER), conditionsFromItem(SPIDER_LEATHER))
				.criterion(hasItem(DEPOISON_SPIDER_LEG), conditionsFromItem(DEPOISON_SPIDER_LEG))
				.offerTo(exporter, getItemPath(SPIDER_LEATHER_CAP));
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, SPIDER_LEATHER_TUNIC).group(getItemPath(SPIDER_LEATHER_TUNIC)).input('#', SPIDER_LEATHER).input('X', DEPOISON_SPIDER_LEG)
				.pattern("# #")
				.pattern("X#X")
				.pattern("X#X")
				.criterion(hasItem(SPIDER_LEATHER), conditionsFromItem(SPIDER_LEATHER))
				.criterion(hasItem(DEPOISON_SPIDER_LEG), conditionsFromItem(DEPOISON_SPIDER_LEG))
				.offerTo(exporter, getItemPath(SPIDER_LEATHER_TUNIC));
	}
}
