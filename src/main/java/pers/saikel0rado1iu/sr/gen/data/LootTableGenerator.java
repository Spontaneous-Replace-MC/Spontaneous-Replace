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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import pers.saikel0rado1iu.sr.data.Blocks;
import pers.saikel0rado1iu.sr.data.EntityTypes;
import pers.saikel0rado1iu.sr.data.Items;

import java.util.function.BiConsumer;

import static net.minecraft.block.Blocks.*;
import static net.minecraft.item.Items.STRING;
import static pers.saikel0rado1iu.silk.gen.data.SilkLootTableGenerator.*;

/**
 * <h2 style="color:FFC800">自然更替的战利品表生成器</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface LootTableGenerator {
	final class Block extends FabricBlockLootTableProvider {
		public Block(FabricDataOutput dataOutput) {
			super(dataOutput);
		}
		
		@Override
		public void generate() {
			addDrop(Blocks.REFINED_COPPER_BLOCK);
			addDrop(Blocks.CUFE_ALLOY_BLOCK);
			addDrop(Blocks.AUCU_ALLOY_BLOCK);
			addDrop(Blocks.STEEL_BLOCK);
			addDropWithSilkTouch(Blocks.EERIE_REGOLITH, GRAVEL);
			addDropWithSilkTouch(Blocks.TREACHEROUS_SLUDGE, MUD);
			addDropWithSilkTouch(Blocks.COBWEBBY_SOIL, DIRT);
			addDrop(Blocks.EERIE_RIND);
			addBlockDrop(this::addDrop, Blocks.ICE_EERIE_RIND, Blocks.EERIE_RIND, ICE);
			addDrop(Blocks.LAVA_EERIE_RIND, Blocks.EERIE_RIND);
			addDrop(Blocks.WATER_EERIE_RIND, Blocks.EERIE_RIND);
			addDrop(Blocks.POWDER_SNOW_EERIE_RIND, Blocks.EERIE_RIND);
			addDrop(Blocks.EERIE_BOUGH);
			addDrop(Blocks.TREACHEROUS_SAC);
			addVinePlantDrop(Blocks.TREACHEROUS_VINES_PLANT, Blocks.TREACHEROUS_VINES);
			addVinePlantDrop(Blocks.TREACHEROUS_VINES, Blocks.TREACHEROUS_VINES);
			addDrop(Blocks.GOSSAMER_CARPET, STRING);
			addDrop(Blocks.GOSSAMERY_LEAVES, leavesDrops(Blocks.GOSSAMERY_LEAVES, OAK_LEAVES, 0.05F, 0.0625F, 0.0833F, 0.1f));
			addDrop(Blocks.STICKY_COMPACT_COBWEB, dropsWithShears(Items.STICKY_COMPACT_GOSSAMER));
		}
	}
	
	final class Entity extends SimpleFabricLootTableProvider {
		public Entity(FabricDataOutput dataOutput) {
			super(dataOutput, LootContextTypes.ENTITY);
		}
		
		@Override
		public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
			addEntityDrop(exporter, EntityTypes.GUARD_SPIDER, LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_LEG)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 2)))
									.apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_LEATHER)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(-1, 1))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1)))));
			addEntityDrop(exporter, EntityTypes.SPRAY_POISON_SPIDER, LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_LEG)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 2)))
									.apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_FANG)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(-1, 1))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1)))));
			addEntityDrop(exporter, EntityTypes.WEAVING_WEB_SPIDER, LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_LEG)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 2)))
									.apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.COMPACT_GOSSAMER)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(-1, 1))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1)))));
		}
	}
}
