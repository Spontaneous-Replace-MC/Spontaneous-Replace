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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.TagProvider;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.WorldPresetTags;
import pers.saikel0rado1iu.silk.api.block.SilkBlockTags;
import pers.saikel0rado1iu.silk.api.entity.SilkEntityTypeTags;
import pers.saikel0rado1iu.sr.data.*;
import pers.saikel0rado1iu.sr.gen.world.WorldPresets;
import pers.saikel0rado1iu.sr.variant.spider.world.gen.biome.SpiderBiomeKeys;

import java.util.concurrent.CompletableFuture;

import static pers.saikel0rado1iu.silk.gen.data.SilkTagGenerator.putSpawnGroupEntityInTags;

/**
 * <p><b style="color:FFC800"><font size="+1">自然更替的标签生成器</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public interface TagGeneration {
	final class Item extends FabricTagProvider.ItemTagProvider {
		public Item(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
			super(output, completableFuture);
		}
		
		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(ItemTags.COALS)
					.add(Items.CLEAN_COAL);
			getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
					.add(Items.REFINED_COPPER_HELMET)
					.add(Items.REFINED_COPPER_CHESTPLATE)
					.add(Items.REFINED_COPPER_LEGGINGS)
					.add(Items.REFINED_COPPER_BOOTS)
					.add(Items.CUFE_ALLOY_HELMET)
					.add(Items.CUFE_ALLOY_CHESTPLATE)
					.add(Items.CUFE_ALLOY_LEGGINGS)
					.add(Items.CUFE_ALLOY_BOOTS)
					.add(Items.AUCU_ALLOY_HELMET)
					.add(Items.AUCU_ALLOY_CHESTPLATE)
					.add(Items.AUCU_ALLOY_LEGGINGS)
					.add(Items.AUCU_ALLOY_BOOTS)
					.add(Items.STEEL_HELMET)
					.add(Items.STEEL_CHESTPLATE)
					.add(Items.STEEL_LEGGINGS)
					.add(Items.STEEL_BOOTS);
		}
	}
	
	final class Block extends FabricTagProvider.BlockTagProvider {
		public Block(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
			super(output, completableFuture);
		}
		
		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
					.add(Blocks.REFINED_COPPER_BLOCK)
					.add(Blocks.CUFE_ALLOY_BLOCK)
					.add(Blocks.AUCU_ALLOY_BLOCK)
					.add(Blocks.STEEL_BLOCK)
					.add(Blocks.EERIE_RIND)
					.add(Blocks.ICE_EERIE_RIND)
					.add(Blocks.LAVA_EERIE_RIND)
					.add(Blocks.POWDER_SNOW_EERIE_RIND)
					.add(Blocks.WATER_EERIE_RIND)
					.add(Blocks.EERIE_BOUGH);
			getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
					.add(Blocks.REFINED_COPPER_BLOCK)
					.add(Blocks.CUFE_ALLOY_BLOCK);
			getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
					.add(Blocks.AUCU_ALLOY_BLOCK)
					.add(Blocks.STEEL_BLOCK)
					.add(Blocks.EERIE_RIND)
					.add(Blocks.ICE_EERIE_RIND)
					.add(Blocks.LAVA_EERIE_RIND)
					.add(Blocks.POWDER_SNOW_EERIE_RIND)
					.add(Blocks.WATER_EERIE_RIND)
					.add(Blocks.EERIE_BOUGH);
			getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
					.add(Blocks.CUFE_ALLOY_BLOCK)
					.add(Blocks.AUCU_ALLOY_BLOCK)
					.add(Blocks.STEEL_BLOCK);
			getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
					.add(Blocks.EERIE_REGOLITH)
					.add(Blocks.TREACHEROUS_SLUDGE)
					.add(Blocks.COBWEBBY_SOIL);
			getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
					.add(Blocks.TREACHEROUS_SAC)
					.add(Blocks.TREACHEROUS_VINES_PLANT)
					.add(Blocks.TREACHEROUS_VINES);
			getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
					.add(Blocks.GOSSAMER_CARPET);
			getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
					.add(Blocks.GOSSAMER_CARPET);
			getOrCreateTagBuilder(BlockTags.CLIMBABLE)
					.add(Blocks.TREACHEROUS_VINES_PLANT)
					.add(Blocks.TREACHEROUS_VINES);
			getOrCreateTagBuilder(BlockTags.CAULDRONS)
					.add(Blocks.EERIE_RIND)
					.add(Blocks.LAVA_EERIE_RIND)
					.add(Blocks.POWDER_SNOW_EERIE_RIND)
					.add(Blocks.WATER_EERIE_RIND);
			getOrCreateTagBuilder(BlockTags.DIRT)
					.add(Blocks.COBWEBBY_SOIL);
			getOrCreateTagBuilder(BlockTags.LEAVES)
					.add(Blocks.GOSSAMERY_LEAVES);
			getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE)
					.add(Blocks.EERIE_REGOLITH)
					.add(Blocks.TREACHEROUS_SLUDGE)
					.add(Blocks.COBWEBBY_SOIL);
			getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
					.add(Blocks.EERIE_REGOLITH)
					.add(Blocks.TREACHEROUS_SLUDGE)
					.add(Blocks.COBWEBBY_SOIL)
					.add(Blocks.GOSSAMER_CARPET);
			getOrCreateTagBuilder(BlockTags.NETHER_CARVER_REPLACEABLES)
					.add(Blocks.EERIE_REGOLITH)
					.add(Blocks.TREACHEROUS_SLUDGE)
					.add(Blocks.COBWEBBY_SOIL)
					.add(Blocks.GOSSAMER_CARPET);
			getOrCreateTagBuilder(BlockTags.SCULK_REPLACEABLE)
					.add(Blocks.EERIE_REGOLITH)
					.add(Blocks.TREACHEROUS_SLUDGE)
					.add(Blocks.COBWEBBY_SOIL)
					.add(Blocks.GOSSAMER_CARPET);
			getOrCreateTagBuilder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
					.add(Blocks.GOSSAMER_CARPET);
			getOrCreateTagBuilder(BlockTags.DAMPENS_VIBRATIONS)
					.add(Blocks.GOSSAMER_CARPET);
			getOrCreateTagBuilder(SilkBlockTags.COBWEBS)
					.add(Blocks.STICKY_COMPACT_COBWEB)
					.add(Blocks.SPIDER_CHRYSALIS)
					.add(Blocks.SPIDER_EGG_COCOON);
			getOrCreateTagBuilder(Tags.Block.EERIE_RINDS)
					.add(Blocks.EERIE_RIND)
					.add(Blocks.ICE_EERIE_RIND)
					.add(Blocks.LAVA_EERIE_RIND)
					.add(Blocks.POWDER_SNOW_EERIE_RIND)
					.add(Blocks.WATER_EERIE_RIND);
			getOrCreateTagBuilder(Tags.Block.COBWEBBY_BLOCKS)
					.add(Blocks.COBWEBBY_SOIL)
					.add(Blocks.GOSSAMERY_LEAVES)
					.add(Blocks.GOSSAMER_CARPET)
					.add(Blocks.SPIDER_CHRYSALIS)
					.add(Blocks.SPIDER_EGG_COCOON);
		}
	}
	
	final class EntityType extends FabricTagProvider.EntityTypeTagProvider {
		public EntityType(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
			super(output, completableFuture);
		}
		
		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(EntityTypeTags.ARROWS)
					.add(EntityTypes.STEEL_ARROW);
			putSpawnGroupEntityInTags(this::getOrCreateTagBuilder, SilkEntityTypeTags.MONSTERS, SpawnGroup.MONSTER, SpontaneousReplace.DATA);
		}
	}
	
	final class Biome extends TagProvider<net.minecraft.world.biome.Biome> {
		public Biome(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
			super(output, RegistryKeys.BIOME, registryLookupFuture);
		}
		
		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(Tags.Biome.IS_SPIDER_BIOME)
					.add(SpiderBiomeKeys.CREEPY_SPIDER_FOREST);
		}
	}
	
	final class WorldPreset extends TagProvider<net.minecraft.world.gen.WorldPreset> {
		public WorldPreset(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
			super(output, RegistryKeys.WORLD_PRESET, registryLookupFuture);
		}
		
		@Override
		protected void configure(RegistryWrapper.WrapperLookup lookup) {
			getOrCreateTagBuilder(WorldPresetTags.NORMAL).addOptional(WorldPresets.CLASSIC.getValue());
			getOrCreateTagBuilder(WorldPresetTags.EXTENDED).addOptional(WorldPresets.SNAPSHOT.getValue());
		}
	}
}
