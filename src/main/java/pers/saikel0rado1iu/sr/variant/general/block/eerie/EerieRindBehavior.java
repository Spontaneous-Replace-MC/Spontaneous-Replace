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
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import static pers.saikel0rado1iu.sr.data.Blocks.*;
import static pers.saikel0rado1iu.sr.data.Tags.Block.EERIE_RINDS;

/**
 * <p><b style="color:FFC800"><font size="+1">阴森木枝行为</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
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
			return ActionResult.PASS;
		} else {
			if (!world.isClient) {
				if (cantUse(world, pos)) return ActionResult.PASS;
				ItemStack itemStack = new ItemStack(Blocks.SHULKER_BOX);
				if (stack.hasNbt()) {
					itemStack.setNbt(Objects.requireNonNull(stack.getNbt()).copy());
				}
				
				player.setStackInHand(hand, itemStack);
				player.incrementStat(Stats.CLEAN_SHULKER_BOX);
				LeveledEerieRind.decrementFluidLevel(state, world, pos);
			}
			
			return ActionResult.success(world.isClient);
		}
	};
	CauldronBehavior CLEAN_BANNER = (state, world, pos, player, hand, stack) -> {
		if (BannerBlockEntity.getPatternCount(stack) <= 0) {
			return ActionResult.PASS;
		} else {
			if (!world.isClient) {
				if (cantUse(world, pos)) return ActionResult.PASS;
				ItemStack itemStack = stack.copyWithCount(1);
				BannerBlockEntity.loadFromItemStack(itemStack);
				if (!player.getAbilities().creativeMode) {
					stack.decrement(1);
				}
				
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
			
			return ActionResult.success(world.isClient);
		}
	};
	CauldronBehavior CLEAN_DYEABLE_ITEM = (state, world, pos, player, hand, stack) -> {
		Item item = stack.getItem();
		if (!(item instanceof DyeableItem dyeableItem)) {
			return ActionResult.PASS;
		} else if (!dyeableItem.hasColor(stack)) {
			return ActionResult.PASS;
		} else {
			if (!world.isClient) {
				if (cantUse(world, pos)) return ActionResult.PASS;
				dyeableItem.removeColor(stack);
				player.incrementStat(Stats.CLEAN_ARMOR);
				LeveledEerieRind.decrementFluidLevel(state, world, pos);
			}
			
			return ActionResult.success(world.isClient);
		}
	};
	
	static CauldronBehavior.CauldronBehaviorMap createMap(String name) {
		Object2ObjectOpenHashMap<Item, CauldronBehavior> object2ObjectOpenHashMap = new Object2ObjectOpenHashMap<>();
		object2ObjectOpenHashMap.defaultReturnValue((state, world, pos, player, hand, stack) -> ActionResult.PASS);
		CauldronBehavior.CauldronBehaviorMap cauldronBehaviorMap = new CauldronBehavior.CauldronBehaviorMap(name, object2ObjectOpenHashMap);
		BEHAVIOR_MAPS.put(name, cauldronBehaviorMap);
		return cauldronBehaviorMap;
	}
	
	static void registerBehavior() {
		registerBucketBehavior(EMPTY_BEHAVIOR.map());
		EMPTY_BEHAVIOR.map().put(Items.POTION, (state, world, pos, player, hand, stack) -> {
			if (PotionUtil.getPotion(stack) != Potions.WATER) {
				return ActionResult.PASS;
			} else {
				if (!world.isClient) {
					if (cantUse(world, pos)) return ActionResult.PASS;
					Item item = stack.getItem();
					player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
					player.incrementStat(Stats.USE_CAULDRON);
					player.incrementStat(Stats.USED.getOrCreateStat(item));
					world.setBlockState(pos, WATER_EERIE_RIND.getDefaultState());
					world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
					world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
				}
				
				return ActionResult.success(world.isClient);
			}
		});
		registerBucketBehavior(WATER_BEHAVIOR.map());
		WATER_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.WATER_BUCKET), (blockState) -> blockState.get(LeveledEerieRind.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL));
		WATER_BEHAVIOR.map().put(Items.GLASS_BOTTLE, (state, world, pos, player, hand, stack) -> {
			if (!world.isClient) {
				if (cantUse(world, pos)) return ActionResult.PASS;
				Item item = stack.getItem();
				player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER)));
				player.incrementStat(Stats.USE_CAULDRON);
				player.incrementStat(Stats.USED.getOrCreateStat(item));
				LeveledEerieRind.decrementFluidLevel(state, world, pos);
				world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
			}
			
			return ActionResult.success(world.isClient);
		});
		WATER_BEHAVIOR.map().put(Items.POTION, (state, world, pos, player, hand, stack) -> {
			if (state.get(LeveledEerieRind.LEVEL) != 3 && PotionUtil.getPotion(stack) == Potions.WATER) {
				if (!world.isClient) {
					if (cantUse(world, pos)) return ActionResult.PASS;
					player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
					player.incrementStat(Stats.USE_CAULDRON);
					player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
					world.setBlockState(pos, state.cycle(LeveledEerieRind.LEVEL));
					world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
					world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
				}
				
				return ActionResult.success(world.isClient);
			} else {
				return ActionResult.PASS;
			}
		});
		WATER_BEHAVIOR.map().put(Items.LEATHER_BOOTS, CLEAN_DYEABLE_ITEM);
		WATER_BEHAVIOR.map().put(Items.LEATHER_LEGGINGS, CLEAN_DYEABLE_ITEM);
		WATER_BEHAVIOR.map().put(Items.LEATHER_CHESTPLATE, CLEAN_DYEABLE_ITEM);
		WATER_BEHAVIOR.map().put(Items.LEATHER_HELMET, CLEAN_DYEABLE_ITEM);
		WATER_BEHAVIOR.map().put(Items.LEATHER_HORSE_ARMOR, CLEAN_DYEABLE_ITEM);
		WATER_BEHAVIOR.map().put(Items.WHITE_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.GRAY_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.BLACK_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.BLUE_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.BROWN_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.CYAN_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.GREEN_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.LIGHT_BLUE_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.LIGHT_GRAY_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.LIME_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.MAGENTA_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.ORANGE_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.PINK_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.PURPLE_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.RED_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.YELLOW_BANNER, CLEAN_BANNER);
		WATER_BEHAVIOR.map().put(Items.WHITE_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.GRAY_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.BLACK_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.BLUE_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.BROWN_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.CYAN_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.GREEN_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.LIGHT_BLUE_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.LIGHT_GRAY_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.LIME_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.MAGENTA_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.ORANGE_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.PINK_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.PURPLE_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.RED_SHULKER_BOX, CLEAN_SHULKER_BOX);
		WATER_BEHAVIOR.map().put(Items.YELLOW_SHULKER_BOX, CLEAN_SHULKER_BOX);
		LAVA_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.LAVA_BUCKET), (blockState) -> true, SoundEvents.ITEM_BUCKET_FILL_LAVA));
		registerBucketBehavior(LAVA_BEHAVIOR.map());
		POWDER_SNOW_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.POWDER_SNOW_BUCKET), (blockState) -> blockState.get(LeveledEerieRind.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL_POWDER_SNOW));
		registerBucketBehavior(POWDER_SNOW_BEHAVIOR.map());
	}
	
	static void registerBucketBehavior(Map<Item, CauldronBehavior> behavior) {
		behavior.put(Items.LAVA_BUCKET, FILL_WITH_LAVA);
		behavior.put(Items.WATER_BUCKET, FILL_WITH_WATER);
		behavior.put(Items.POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);
	}
	
	static ActionResult emptyCauldron(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, ItemStack output, Predicate<BlockState> fullPredicate, SoundEvent soundEvent) {
		if (!fullPredicate.test(state)) {
			return ActionResult.PASS;
		} else {
			if (!world.isClient) {
				if (cantUse(world, pos)) return ActionResult.PASS;
				Item item = stack.getItem();
				player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, output));
				player.incrementStat(Stats.USE_CAULDRON);
				player.incrementStat(Stats.USED.getOrCreateStat(item));
				world.setBlockState(pos, EERIE_RIND.getDefaultState());
				world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
			}
			
			return ActionResult.success(world.isClient);
		}
	}
	
	static ActionResult fillCauldron(World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, BlockState state, SoundEvent soundEvent) {
		if (!world.isClient) {
			if (cantUse(world, pos)) return ActionResult.PASS;
			Item item = stack.getItem();
			player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BUCKET)));
			player.incrementStat(Stats.FILL_CAULDRON);
			player.incrementStat(Stats.USED.getOrCreateStat(item));
			world.setBlockState(pos, state);
			world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
			world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
		}
		
		return ActionResult.success(world.isClient);
	}
	
	static boolean cantUse(World world, BlockPos pos) {
		return !((world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP)
				|| world.getBlockState(pos.down()).isIn(BlockTags.CAULDRONS)
				|| world.getBlockState(pos.down()).isIn(EERIE_RINDS))
				&& (!world.getBlockState(pos.up()).isSideSolidFullSquare(world, pos.up(), Direction.DOWN)
				&& !world.getBlockState(pos.up()).isIn(EERIE_RINDS)));
	}
	
	@SuppressWarnings("unused")
	ActionResult interact(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack);
}
