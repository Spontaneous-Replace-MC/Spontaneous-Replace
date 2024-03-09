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

package pers.saikel0rado1iu.sr.variant.general.block.eerie;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Map;
import java.util.function.Predicate;

import static pers.saikel0rado1iu.sr.data.Blocks.*;
import static pers.saikel0rado1iu.sr.data.Tags.Block.EERIE_RINDS;

/**
 * <h2 style="color:FFC800">阴森木枝行为</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface EerieRindBehavior {
	Map<String, CauldronBehavior.CauldronBehaviorMap> BEHAVIOR_MAPS = new Object2ObjectArrayMap<>();
	CauldronBehavior.CauldronBehaviorMap EMPTY_BEHAVIOR = createMap("empty");
	CauldronBehavior.CauldronBehaviorMap WATER_BEHAVIOR = createMap("water");
	CauldronBehavior.CauldronBehaviorMap LAVA_BEHAVIOR = createMap("lava");
	CauldronBehavior.CauldronBehaviorMap POWDER_SNOW_BEHAVIOR = createMap("powder_snow");
	CauldronBehavior FILL_WITH_WATER = (state, world, pos, player, hand, stack) -> fillCauldron(world, pos, player, hand, stack, WATER_EERIE_RIND.getDefaultState().with(LeveledEerieRind.LEVEL, 3), SoundEvents.ITEM_BUCKET_EMPTY);
	CauldronBehavior FILL_WITH_LAVA = (state, world, pos, player, hand, stack) -> fillCauldron(world, pos, player, hand, stack, LAVA_EERIE_RIND.getDefaultState(), SoundEvents.ITEM_BUCKET_EMPTY_LAVA);
	CauldronBehavior FILL_WITH_POWDER_SNOW = (state, world, pos, player, hand, stack) -> fillCauldron(world, pos, player, hand, stack, POWDER_SNOW_EERIE_RIND.getDefaultState().with(LeveledEerieRind.LEVEL, 3), SoundEvents.ITEM_BUCKET_EMPTY_POWDER_SNOW);
	CauldronBehavior CLEAN_SHULKER_BOX = (state, world, pos, player, hand, stack) -> {
		Block block = Block.getBlockFromItem(stack.getItem());
		if (!(block instanceof ShulkerBoxBlock)) {
			return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		}
		if (!world.isClient) {
			player.setStackInHand(hand, stack.copyComponentsToNewStack(Blocks.SHULKER_BOX, 1));
			player.incrementStat(Stats.CLEAN_SHULKER_BOX);
			LeveledEerieRind.decrementFluidLevel(state, world, pos);
		}
		return ItemActionResult.success(world.isClient);
	};
	CauldronBehavior CLEAN_BANNER = (state, world, pos, player, hand, stack) -> {
		BannerPatternsComponent bannerPatternsComponent = stack.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT);
		if (bannerPatternsComponent.layers().isEmpty()) {
			return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		}
		if (!world.isClient) {
			ItemStack itemStack = stack.copyWithCount(1);
			itemStack.set(DataComponentTypes.BANNER_PATTERNS, bannerPatternsComponent.withoutTopLayer());
			stack.decrementUnlessCreative(1, player);
			if (stack.isEmpty()) {
				player.setStackInHand(hand, itemStack);
			} else if (player.getInventory().insertStack(itemStack)) {
				player.playerScreenHandler.syncState();
			} else {
				player.dropItem(itemStack, false);
			}
			player.incrementStat(Stats.CLEAN_BANNER);
			LeveledEerieRind.decrementFluidLevel(state, world, pos);
		}
		return ItemActionResult.success(world.isClient);
	};
	CauldronBehavior CLEAN_DYEABLE_ITEM = (state, world, pos, player, hand, stack) -> {
		if (!stack.isIn(ItemTags.DYEABLE)) {
			return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		}
		if (!stack.contains(DataComponentTypes.DYED_COLOR)) {
			return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		}
		if (!world.isClient) {
			stack.remove(DataComponentTypes.DYED_COLOR);
			player.incrementStat(Stats.CLEAN_ARMOR);
			LeveledEerieRind.decrementFluidLevel(state, world, pos);
		}
		return ItemActionResult.success(world.isClient);
	};
	
	static CauldronBehavior.CauldronBehaviorMap createMap(String name) {
		Object2ObjectOpenHashMap<Item, CauldronBehavior> object2ObjectOpenHashMap = new Object2ObjectOpenHashMap<>();
		object2ObjectOpenHashMap.defaultReturnValue((state, world, pos, player, hand, stack) -> ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION);
		CauldronBehavior.CauldronBehaviorMap cauldronBehaviorMap = new CauldronBehavior.CauldronBehaviorMap(name, object2ObjectOpenHashMap);
		BEHAVIOR_MAPS.put(name, cauldronBehaviorMap);
		return cauldronBehaviorMap;
	}
	
	static void registerBehavior() {
		Map<Item, CauldronBehavior> empty = EMPTY_BEHAVIOR.map();
		registerBucketBehavior(empty);
		empty.put(Items.POTION, (state, world, pos, player, hand, stack) -> {
			PotionContentsComponent potionContentsComponent = stack.get(DataComponentTypes.POTION_CONTENTS);
			if (potionContentsComponent == null || !potionContentsComponent.matches(Potions.WATER)) {
				return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
			}
			if (!world.isClient) {
				Item item = stack.getItem();
				player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
				player.incrementStat(Stats.USE_CAULDRON);
				player.incrementStat(Stats.USED.getOrCreateStat(item));
				world.setBlockState(pos, WATER_EERIE_RIND.getDefaultState());
				world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1, 1);
				world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
			}
			return ItemActionResult.success(world.isClient);
		});
		Map<Item, CauldronBehavior> water = WATER_BEHAVIOR.map();
		registerBucketBehavior(water);
		water.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.WATER_BUCKET), statex -> statex.get(LeveledEerieRind.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL));
		water.put(Items.GLASS_BOTTLE, (state, world, pos, player, hand, stack) -> {
			if (!world.isClient) {
				Item item = stack.getItem();
				player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, PotionContentsComponent.createStack(Items.POTION, Potions.WATER)));
				player.incrementStat(Stats.USE_CAULDRON);
				player.incrementStat(Stats.USED.getOrCreateStat(item));
				LeveledEerieRind.decrementFluidLevel(state, world, pos);
				world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1, 1);
				world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
			}
			return ItemActionResult.success(world.isClient);
		});
		water.put(Items.POTION, (state, world, pos, player, hand, stack) -> {
			if (state.get(LeveledEerieRind.LEVEL) == 3) {
				return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
			}
			PotionContentsComponent potionContentsComponent = stack.get(DataComponentTypes.POTION_CONTENTS);
			if (potionContentsComponent == null || !potionContentsComponent.matches(Potions.WATER)) {
				return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
			}
			if (!world.isClient) {
				player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
				player.incrementStat(Stats.USE_CAULDRON);
				player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
				world.setBlockState(pos, state.cycle(LeveledEerieRind.LEVEL));
				world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1, 1);
				world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
			}
			return ItemActionResult.success(world.isClient);
		});
		water.put(Items.LEATHER_BOOTS, CLEAN_DYEABLE_ITEM);
		water.put(Items.LEATHER_LEGGINGS, CLEAN_DYEABLE_ITEM);
		water.put(Items.LEATHER_CHESTPLATE, CLEAN_DYEABLE_ITEM);
		water.put(Items.LEATHER_HELMET, CLEAN_DYEABLE_ITEM);
		water.put(Items.LEATHER_HORSE_ARMOR, CLEAN_DYEABLE_ITEM);
		water.put(Items.WOLF_ARMOR, CLEAN_DYEABLE_ITEM);
		water.put(Items.WHITE_BANNER, CLEAN_BANNER);
		water.put(Items.GRAY_BANNER, CLEAN_BANNER);
		water.put(Items.BLACK_BANNER, CLEAN_BANNER);
		water.put(Items.BLUE_BANNER, CLEAN_BANNER);
		water.put(Items.BROWN_BANNER, CLEAN_BANNER);
		water.put(Items.CYAN_BANNER, CLEAN_BANNER);
		water.put(Items.GREEN_BANNER, CLEAN_BANNER);
		water.put(Items.LIGHT_BLUE_BANNER, CLEAN_BANNER);
		water.put(Items.LIGHT_GRAY_BANNER, CLEAN_BANNER);
		water.put(Items.LIME_BANNER, CLEAN_BANNER);
		water.put(Items.MAGENTA_BANNER, CLEAN_BANNER);
		water.put(Items.ORANGE_BANNER, CLEAN_BANNER);
		water.put(Items.PINK_BANNER, CLEAN_BANNER);
		water.put(Items.PURPLE_BANNER, CLEAN_BANNER);
		water.put(Items.RED_BANNER, CLEAN_BANNER);
		water.put(Items.YELLOW_BANNER, CLEAN_BANNER);
		water.put(Items.WHITE_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.GRAY_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.BLACK_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.BLUE_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.BROWN_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.CYAN_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.GREEN_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.LIGHT_BLUE_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.LIGHT_GRAY_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.LIME_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.MAGENTA_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.ORANGE_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.PINK_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.PURPLE_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.RED_SHULKER_BOX, CLEAN_SHULKER_BOX);
		water.put(Items.YELLOW_SHULKER_BOX, CLEAN_SHULKER_BOX);
		Map<Item, CauldronBehavior> lava = LAVA_BEHAVIOR.map();
		lava.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.LAVA_BUCKET), statex -> true, SoundEvents.ITEM_BUCKET_FILL_LAVA));
		registerBucketBehavior(lava);
		Map<Item, CauldronBehavior> powderSnow = POWDER_SNOW_BEHAVIOR.map();
		powderSnow.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.POWDER_SNOW_BUCKET), statex -> statex.get(LeveledEerieRind.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL_POWDER_SNOW));
		registerBucketBehavior(powderSnow);
	}
	
	static void registerBucketBehavior(Map<Item, CauldronBehavior> behavior) {
		behavior.put(Items.LAVA_BUCKET, FILL_WITH_LAVA);
		behavior.put(Items.WATER_BUCKET, FILL_WITH_WATER);
		behavior.put(Items.POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);
	}
	
	static ItemActionResult emptyCauldron(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, ItemStack output, Predicate<BlockState> fullPredicate, SoundEvent soundEvent) {
		if (!fullPredicate.test(state)) {
			return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		} else {
			if (!world.isClient) {
				if (cantUse(world, pos)) return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
				Item item = stack.getItem();
				player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, output));
				player.incrementStat(Stats.USE_CAULDRON);
				player.incrementStat(Stats.USED.getOrCreateStat(item));
				world.setBlockState(pos, EERIE_RIND.getDefaultState());
				world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
			}
			
			return ItemActionResult.success(world.isClient);
		}
	}
	
	static ItemActionResult fillCauldron(World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, BlockState state, SoundEvent soundEvent) {
		if (!world.isClient) {
			if (cantUse(world, pos)) return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
			Item item = stack.getItem();
			player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BUCKET)));
			player.incrementStat(Stats.FILL_CAULDRON);
			player.incrementStat(Stats.USED.getOrCreateStat(item));
			world.setBlockState(pos, state);
			world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
			world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
		}
		
		return ItemActionResult.success(world.isClient);
	}
	
	static boolean cantUse(World world, BlockPos pos) {
		return !((world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP)
				|| world.getBlockState(pos.down()).isIn(BlockTags.CAULDRONS)
				|| world.getBlockState(pos.down()).isIn(EERIE_RINDS))
				&& (!world.getBlockState(pos.up()).isSideSolidFullSquare(world, pos.up(), Direction.DOWN)
				&& !world.getBlockState(pos.up()).isIn(EERIE_RINDS)));
	}
	
	@SuppressWarnings("unused")
	ItemActionResult interact(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack);
}
