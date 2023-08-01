package pres.saikel_orado.spontaneous_replace.mod.variant.general.block.eerierind;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.*;
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
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.*;

/**
 * <b style="color:FFC800"><font size="+2">EerieRindBehavior：阴森木枝行为</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置阴森木枝的交互，模仿炼药锅</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/22 21:20
 */
public interface EerieRindBehavior {
    Map<Item, CauldronBehavior> EMPTY_BEHAVIOR = createMap();
    Map<Item, CauldronBehavior> WATER_BEHAVIOR = createMap();
    Map<Item, CauldronBehavior> LAVA_BEHAVIOR = createMap();
    Map<Item, CauldronBehavior> POWDER_SNOW_BEHAVIOR = createMap();
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

    static Object2ObjectOpenHashMap<Item, CauldronBehavior> createMap() {
        return Util.make(new Object2ObjectOpenHashMap<>(), (map) -> map.defaultReturnValue(
                ((state, world, pos, player, hand, stack) -> ActionResult.PASS)));
    }

    @SuppressWarnings("unused")
    ActionResult interact(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack);

    static void registerBehavior() {
        registerBucketBehavior(EMPTY_BEHAVIOR);
        EMPTY_BEHAVIOR.put(Items.POTION, (state, world, pos, player, hand, stack) -> {
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
        registerBucketBehavior(WATER_BEHAVIOR);
        WATER_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.WATER_BUCKET), (blockState) -> blockState.get(LeveledEerieRind.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL));
        WATER_BEHAVIOR.put(Items.GLASS_BOTTLE, (state, world, pos, player, hand, stack) -> {
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
        WATER_BEHAVIOR.put(Items.POTION, (state, world, pos, player, hand, stack) -> {
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
        WATER_BEHAVIOR.put(Items.LEATHER_BOOTS, CLEAN_DYEABLE_ITEM);
        WATER_BEHAVIOR.put(Items.LEATHER_LEGGINGS, CLEAN_DYEABLE_ITEM);
        WATER_BEHAVIOR.put(Items.LEATHER_CHESTPLATE, CLEAN_DYEABLE_ITEM);
        WATER_BEHAVIOR.put(Items.LEATHER_HELMET, CLEAN_DYEABLE_ITEM);
        WATER_BEHAVIOR.put(Items.LEATHER_HORSE_ARMOR, CLEAN_DYEABLE_ITEM);
        WATER_BEHAVIOR.put(Items.WHITE_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.GRAY_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.BLACK_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.BLUE_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.BROWN_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.CYAN_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.GREEN_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.LIGHT_BLUE_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.LIGHT_GRAY_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.LIME_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.MAGENTA_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.ORANGE_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.PINK_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.PURPLE_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.RED_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.YELLOW_BANNER, CLEAN_BANNER);
        WATER_BEHAVIOR.put(Items.WHITE_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.GRAY_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.BLACK_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.BLUE_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.BROWN_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.CYAN_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.GREEN_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.LIGHT_BLUE_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.LIGHT_GRAY_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.LIME_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.MAGENTA_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.ORANGE_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.PINK_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.PURPLE_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.RED_SHULKER_BOX, CLEAN_SHULKER_BOX);
        WATER_BEHAVIOR.put(Items.YELLOW_SHULKER_BOX, CLEAN_SHULKER_BOX);
        LAVA_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.LAVA_BUCKET), (blockState) -> true, SoundEvents.ITEM_BUCKET_FILL_LAVA));
        registerBucketBehavior(LAVA_BEHAVIOR);
        POWDER_SNOW_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.POWDER_SNOW_BUCKET), (blockState) -> blockState.get(LeveledEerieRind.LEVEL) == 3, SoundEvents.ITEM_BUCKET_FILL_POWDER_SNOW));
        registerBucketBehavior(POWDER_SNOW_BEHAVIOR);
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
}
