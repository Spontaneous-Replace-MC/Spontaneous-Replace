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

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;
import pers.saikel0rado1iu.sr.data.Blocks;
import pers.saikel0rado1iu.sr.data.ItemGroups;
import pers.saikel0rado1iu.sr.data.Items;

import static pers.saikel0rado1iu.silk.gen.data.SilkModelGenerator.*;

/**
 * <p><b style="color:FFC800"><font size="+1">自然更替的模型生成器</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public final class ModelGenerator extends FabricModelProvider {
	public ModelGenerator(FabricDataOutput output) {
		super(output);
	}
	
	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		blockStateModelGenerator.registerSimpleCubeAll(Blocks.REFINED_COPPER_BLOCK);
		blockStateModelGenerator.registerSimpleCubeAll(Blocks.CUFE_ALLOY_BLOCK);
		blockStateModelGenerator.registerSimpleCubeAll(Blocks.AUCU_ALLOY_BLOCK);
		blockStateModelGenerator.registerSimpleCubeAll(Blocks.STEEL_BLOCK);
		blockStateModelGenerator.registerSingleton(Blocks.EERIE_REGOLITH, TexturedModel.CUBE_BOTTOM_TOP);
		blockStateModelGenerator.registerSimpleCubeAll(Blocks.TREACHEROUS_SLUDGE);
		registerTopSoil(blockStateModelGenerator, Blocks.COBWEBBY_SOIL);
		blockStateModelGenerator.registerSimpleCubeAll(Blocks.TREACHEROUS_SAC);
		registerVines(blockStateModelGenerator, Blocks.TREACHEROUS_VINES, Blocks.TREACHEROUS_VINES_PLANT, BlockStateModelGenerator.TintType.NOT_TINTED);
		registerCarpet(blockStateModelGenerator, Blocks.GOSSAMER_CARPET, false);
		blockStateModelGenerator.registerTintableCross(Blocks.STICKY_COMPACT_COBWEB, BlockStateModelGenerator.TintType.NOT_TINTED);
	}
	
	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		registerItemGroup(itemModelGenerator, ItemGroups.BUILDING_BLOCKS);
		registerItemGroup(itemModelGenerator, ItemGroups.COLORED_BLOCKS);
		registerItemGroup(itemModelGenerator, ItemGroups.NATURAL);
		registerItemGroup(itemModelGenerator, ItemGroups.FUNCTIONAL);
		registerItemGroup(itemModelGenerator, ItemGroups.REDSTONE);
		registerItemGroup(itemModelGenerator, ItemGroups.TOOLS);
		registerItemGroup(itemModelGenerator, ItemGroups.COMBAT);
		registerItemGroup(itemModelGenerator, ItemGroups.FOOD_AND_DRINK);
		registerItemGroup(itemModelGenerator, ItemGroups.INGREDIENTS);
		registerItemGroup(itemModelGenerator, ItemGroups.SPAWN_EGGS);
		itemModelGenerator.register(Items.ICON, Models.GENERATED);
		itemModelGenerator.register(Items.COPPER_FOR_SMELTING_INGOT, Models.GENERATED);
		itemModelGenerator.register(Items.REFINED_COPPER_INGOT, Models.GENERATED);
		itemModelGenerator.register(Items.REFINED_COPPER_NUGGET, Models.GENERATED);
		itemModelGenerator.register(Items.REFINED_COPPER_SHOVEL, Models.GENERATED);
		itemModelGenerator.register(Items.REFINED_COPPER_PICKAXE, Models.GENERATED);
		itemModelGenerator.register(Items.REFINED_COPPER_AXE, Models.GENERATED);
		itemModelGenerator.register(Items.REFINED_COPPER_HOE, Models.GENERATED);
		itemModelGenerator.register(Items.REFINED_COPPER_SWORD, Models.GENERATED);
		itemModelGenerator.registerArmor(Items.REFINED_COPPER_HELMET);
		itemModelGenerator.registerArmor(Items.REFINED_COPPER_CHESTPLATE);
		itemModelGenerator.registerArmor(Items.REFINED_COPPER_LEGGINGS);
		itemModelGenerator.registerArmor(Items.REFINED_COPPER_BOOTS);
		itemModelGenerator.register(Items.CUFE_ALLOY, Models.GENERATED);
		itemModelGenerator.register(Items.CUFE_ALLOY_INGOT, Models.GENERATED);
		itemModelGenerator.register(Items.CUFE_ALLOY_NUGGET, Models.GENERATED);
		itemModelGenerator.register(Items.CUFE_ALLOY_SHOVEL, Models.GENERATED);
		itemModelGenerator.register(Items.CUFE_ALLOY_PICKAXE, Models.GENERATED);
		itemModelGenerator.register(Items.CUFE_ALLOY_AXE, Models.GENERATED);
		itemModelGenerator.register(Items.CUFE_ALLOY_HOE, Models.GENERATED);
		itemModelGenerator.register(Items.CUFE_ALLOY_SWORD, Models.GENERATED);
		itemModelGenerator.registerArmor(Items.CUFE_ALLOY_HELMET);
		itemModelGenerator.registerArmor(Items.CUFE_ALLOY_CHESTPLATE);
		itemModelGenerator.registerArmor(Items.CUFE_ALLOY_LEGGINGS);
		itemModelGenerator.registerArmor(Items.CUFE_ALLOY_BOOTS);
		itemModelGenerator.register(Items.AUCU_ALLOY, Models.GENERATED);
		itemModelGenerator.register(Items.AUCU_ALLOY_INGOT, Models.GENERATED);
		itemModelGenerator.register(Items.AUCU_ALLOY_NUGGET, Models.GENERATED);
		itemModelGenerator.register(Items.AUCU_ALLOY_SHOVEL, Models.GENERATED);
		itemModelGenerator.register(Items.AUCU_ALLOY_PICKAXE, Models.GENERATED);
		itemModelGenerator.register(Items.AUCU_ALLOY_AXE, Models.GENERATED);
		itemModelGenerator.register(Items.AUCU_ALLOY_HOE, Models.GENERATED);
		itemModelGenerator.register(Items.AUCU_ALLOY_SWORD, Models.GENERATED);
		itemModelGenerator.registerArmor(Items.AUCU_ALLOY_HELMET);
		itemModelGenerator.registerArmor(Items.AUCU_ALLOY_CHESTPLATE);
		itemModelGenerator.registerArmor(Items.AUCU_ALLOY_LEGGINGS);
		itemModelGenerator.registerArmor(Items.AUCU_ALLOY_BOOTS);
		itemModelGenerator.register(Items.CLEAN_COAL, Models.GENERATED);
		itemModelGenerator.register(Items.PIG_IRON, Models.GENERATED);
		itemModelGenerator.register(Items.STEEL_INGOT, Models.GENERATED);
		itemModelGenerator.register(Items.STEEL_SHOVEL, Models.GENERATED);
		itemModelGenerator.register(Items.STEEL_PICKAXE, Models.GENERATED);
		itemModelGenerator.register(Items.STEEL_AXE, Models.GENERATED);
		itemModelGenerator.register(Items.STEEL_HOE, Models.GENERATED);
		itemModelGenerator.register(Items.STEEL_SWORD, Models.GENERATED);
		itemModelGenerator.registerArmor(Items.STEEL_HELMET);
		itemModelGenerator.registerArmor(Items.STEEL_CHESTPLATE);
		itemModelGenerator.registerArmor(Items.STEEL_LEGGINGS);
		itemModelGenerator.registerArmor(Items.STEEL_BOOTS);
		itemModelGenerator.register(Items.STONEBALL, Models.GENERATED);
		itemModelGenerator.register(Items.STEEL_ARROW, Models.GENERATED);
		JsonObject slingshotDisplay = new JsonObject();
		slingshotDisplay.add(ModelTransformationMode.THIRD_PERSON_RIGHT_HAND.asString(), modelTransModeJson(new float[]{0, 0, -45}, new float[]{0, 2, 1.25F}, new float[]{0.8F, 0.8F, 0.8F}));
		slingshotDisplay.add(ModelTransformationMode.THIRD_PERSON_LEFT_HAND.asString(), modelTransModeJson(new float[]{0, 0, 45}, new float[]{0, 2, 1.25F}, new float[]{0.8F, 0.8F, 0.8F}));
		slingshotDisplay.add(ModelTransformationMode.FIRST_PERSON_RIGHT_HAND.asString(), modelTransModeJson(new float[]{0, 0, -45}, new float[]{2, 4, -1.25F}, new float[]{0.68F, 0.68F, 0.68F}));
		slingshotDisplay.add(ModelTransformationMode.FIRST_PERSON_LEFT_HAND.asString(), modelTransModeJson(new float[]{0, 0, 45}, new float[]{2, 4, -1.25F}, new float[]{0.68F, 0.68F, 0.68F}));
		registerCustomDisplayBowJson(itemModelGenerator, Items.SLINGSHOT, slingshotDisplay, new float[]{0, 0.65F, 0.9F});
		JsonObject recurveBowDisplay = new JsonObject();
		recurveBowDisplay.add(ModelTransformationMode.THIRD_PERSON_RIGHT_HAND.asString(), modelTransModeJson(new float[]{170, -60, -145}, new float[]{-1.5F, -2, 2.5F}, new float[]{0.75F, 0.75F, 0.75F}));
		recurveBowDisplay.add(ModelTransformationMode.THIRD_PERSON_LEFT_HAND.asString(), modelTransModeJson(new float[]{170, 120, 145}, new float[]{-1.5F, -2, 2.5F}, new float[]{0.75F, 0.75F, 0.75F}));
		recurveBowDisplay.add(ModelTransformationMode.FIRST_PERSON_RIGHT_HAND.asString(), modelTransModeJson(new float[]{0, -90, 25}, new float[]{1.13F, 3.2F, 1.13F}, new float[]{0.58F, 0.58F, 0.58F}));
		recurveBowDisplay.add(ModelTransformationMode.FIRST_PERSON_LEFT_HAND.asString(), modelTransModeJson(new float[]{0, 90, -25}, new float[]{1.13F, 3.2F, 1.13F}, new float[]{0.58F, 0.58F, 0.58F}));
		registerCustomDisplayBowJson(itemModelGenerator, Items.RECURVE_BOW, recurveBowDisplay, new float[]{0, 0.65F, 0.9F});
		registerCrossbowJson(itemModelGenerator, Items.ARBALEST, new float[]{0, 0.58F, 1});
		JsonObject compoundBowDisplay = new JsonObject();
		compoundBowDisplay.add(ModelTransformationMode.THIRD_PERSON_RIGHT_HAND.asString(), modelTransModeJson(new float[]{170, -60, -145}, new float[]{-1.5F, -1.75F, 3}, new float[]{0.9F, 0.9F, 0.9F}));
		compoundBowDisplay.add(ModelTransformationMode.THIRD_PERSON_LEFT_HAND.asString(), modelTransModeJson(new float[]{170, 120, 145}, new float[]{-1.5F, -1.75F, 3}, new float[]{0.9F, 0.9F, 0.9F}));
		compoundBowDisplay.add(ModelTransformationMode.FIRST_PERSON_RIGHT_HAND.asString(), modelTransModeJson(new float[]{0, -90, 25}, new float[]{1.13F, 3.2F, 1.13F}, new float[]{0.68F, 0.68F, 0.68F}));
		compoundBowDisplay.add(ModelTransformationMode.FIRST_PERSON_LEFT_HAND.asString(), modelTransModeJson(new float[]{0, 90, -25}, new float[]{1.13F, 3.2F, 1.13F}, new float[]{0.68F, 0.68F, 0.68F}));
		registerCustomDisplayBowJson(itemModelGenerator, Items.COMPOUND_BOW, compoundBowDisplay, new float[]{0, 0.65F, 0.9F});
		registerOverlayItemJson(itemModelGenerator, Items.ARROWPROOF_VEST);
		registerBlockItem(itemModelGenerator, Items.EERIE_RIND);
		registerBlockItem(itemModelGenerator, Items.EERIE_BOUGH);
		itemModelGenerator.register(Items.SPIDER_LEG, Models.GENERATED);
		itemModelGenerator.register(Items.SPIDER_LEATHER, Models.GENERATED);
		itemModelGenerator.register(Items.SPIDER_FANG, Models.GENERATED);
		itemModelGenerator.register(Items.COMPACT_GOSSAMER, Models.GENERATED);
		itemModelGenerator.register(Items.STICKY_COMPACT_GOSSAMER, Models.GENERATED);
		itemModelGenerator.register(Items.DEPOISON_SPIDER_LEG, Models.GENERATED);
		itemModelGenerator.register(Items.COMPACT_STRING, Models.GENERATED);
		itemModelGenerator.register(Items.COMPOSITE_STRING, Models.GENERATED);
		itemModelGenerator.register(Items.COMPOSITE_FABRIC, Models.GENERATED);
		itemModelGenerator.register(Items.SPIDER_LEATHER_CAP, Models.GENERATED);
		itemModelGenerator.register(Items.SPIDER_LEATHER_TUNIC, Models.GENERATED);
		itemModelGenerator.register(Items.SPIDER_LARVA_SPAWN_EGG, TEMPLATE_SPAWN_EGG);
		itemModelGenerator.register(Items.GUARD_SPIDER_SPAWN_EGG, TEMPLATE_SPAWN_EGG);
		itemModelGenerator.register(Items.SPRAY_POISON_SPIDER_SPAWN_EGG, TEMPLATE_SPAWN_EGG);
		itemModelGenerator.register(Items.WEAVING_WEB_SPIDER_SPAWN_EGG, TEMPLATE_SPAWN_EGG);
		registerBlockItem(itemModelGenerator, Items.GOSSAMERY_LEAVES);
		itemModelGenerator.register(Items.SPIDER_CHRYSALIS, Models.GENERATED);
		itemModelGenerator.register(Items.SPIDER_EGG_COCOON, Models.GENERATED);
	}
}