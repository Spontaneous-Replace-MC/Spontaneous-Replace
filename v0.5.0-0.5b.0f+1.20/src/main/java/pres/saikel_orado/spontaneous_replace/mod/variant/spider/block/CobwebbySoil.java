package pres.saikel_orado.spontaneous_replace.mod.variant.spider.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.world.event.GameEvent;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.GOSSAMER_CARPET;
import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderData.COBWEBBY_SOIL_DECELERATION;

/**
 * <b style="color:FFC800"><font size="+2">CobwebbySoil：丝化土</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">一种特殊的装饰性泥土类方块</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/17 20:11
 */
@SuppressWarnings("deprecation")
public class CobwebbySoil extends SpreadableBlock {
    public CobwebbySoil(Settings settings) {
        super(settings);
    }

    /**
     * 可以被锹铲为草径
     */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();
        if (world.getBlockState(pos.up()).isAir() && item instanceof ShovelItem) {
            world.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isClient) {
                world.setBlockState(pos, Blocks.DIRT_PATH.getDefaultState(), 11);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, Blocks.DIRT_PATH.getDefaultState()));
                stack.damage(1, player, (p) -> p.sendToolBreakStatus(hand));
            }

            player.incrementStat(Stats.USED.getOrCreateStat(item));
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    /**
     * 在方块上减速 50%
     */
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!(entity instanceof SpiderEntity))
            entity.slowMovement(state, new Vec3d(COBWEBBY_SOIL_DECELERATION, COBWEBBY_SOIL_DECELERATION, COBWEBBY_SOIL_DECELERATION));

        super.onSteppedOn(world, pos, state, entity);
    }

    /**
     * <p>丝化土会向周围 3 * 3 范围的泥土类方块转化为泥土</p>
     * <p>如果方块上方是泥土类方块或液体则转变为泥土</p>
     */
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBlockState(pos.up()).isIn(BlockTags.DIRT) || !world.getFluidState(pos.up()).isOf(Fluids.EMPTY)) {
            world.setBlockState(pos, Blocks.DIRT.getDefaultState());
        } else {
            BlockState blockState = GOSSAMER_CARPET.getDefaultState();

            for (int i = 0; i < 4; ++i) {
                BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                if (blockPos != pos && (world.getBlockState(blockPos).isOf(Blocks.DIRT) || world.getBlockState(blockPos).isOf(Blocks.GRASS_BLOCK)) && canSpread(blockState, world, blockPos)) {
                    world.setBlockState(blockPos.up(), blockState);
                }
            }
        }
    }

    /**
     * <p>丝化土会向周围 3 * 3 范围的泥土类方块转化为泥土</p>
     * <p>如果方块上方是泥土类方块或液体则转变为泥土</p>
     */
    public void spread(StructureWorldAccess world, BlockPos pos) {
        BlockState blockState = GOSSAMER_CARPET.getDefaultState();

        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                for (int z = 0; z < 3; ++z) {
                    BlockPos blockPos = pos.add(x - 1, y - 1, z - 1);
                    if (blockPos != pos && (world.getBlockState(blockPos).isOf(Blocks.DIRT) || world.getBlockState(blockPos).isOf(Blocks.GRASS_BLOCK)) && canSpread(blockState, world, blockPos)) {
                        world.setBlockState(blockPos.up(), blockState, 2);
                    }
                }
            }
        }
    }

    protected static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.SNOW) && blockState.get(SnowBlock.LAYERS) == 1) {
            return true;
        } else if (blockState.getFluidState().getLevel() == 8) {
            return false;
        } else {
            int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
            return i < world.getMaxLightLevel();
        }
    }

    protected static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return canSurvive(state, world, pos) && !world.getFluidState(blockPos).isIn(FluidTags.WATER);
    }
}
