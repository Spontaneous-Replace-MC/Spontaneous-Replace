package pres.saikel_orado.spontaneous_replace.mod.variant.general.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConnectingBlock;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.POS_SHIFTING;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.EERIE_RINDS;

/**
 * <b style="color:FFC800"><font size="+2">EerieRind：阴森木枝</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">一种被感染的原木类方块</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/18 23:55
 */
@SuppressWarnings("deprecation")
public class EerieBough extends ConnectingBlock {
    public EerieBough(Settings settings) {
        super(0.25F, settings);
        this.setDefaultState(stateManager.getDefaultState()
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false));
    }

    /**
     * 随机灵魂粒子效果
     */
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (random.nextInt(5) == 0) {
            java.util.Random randomValue = new java.util.Random();
            world.addParticle(ParticleTypes.SCULK_SOUL,
                    pos.getX() + POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5),
                    pos.getY() + POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5),
                    pos.getZ() + POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5),
                    randomValue.nextDouble(-0.03, 0.03),
                    randomValue.nextDouble(0, 0.05),
                    randomValue.nextDouble(-0.03, 0.03));
        }
    }

    /**
     * <p>毁坏改木枝方块上关联的所有木枝方块</p>
     * <p>Warning! 由于使用了递归可能产生重大程序问题</p>
     */
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (neighborState.isAir()) {
            Set<BlockPos> allBoughSet = getAllBlock(world, neighborPos, null, new HashSet<>());
            allBoughSet.remove(neighborPos);
            for (BlockPos blockPos : allBoughSet) {
                if (breakBough(world, blockPos, null, new HashSet<>()))
                    world.breakBlock(blockPos, true);
            }
        }
        return withConnectionProperties(world, pos);
    }

    /**
     * 获取所有关联木枝方块的位置
     */
    protected Set<BlockPos> getAllBlock(WorldAccess world, BlockPos pos, @Nullable Direction direction, Set<BlockPos> list) {
        if (!list.contains(pos)) list.add(pos);
        else return list;
        if (world.getBlockState(pos.up()).isOf(this) || world.getBlockState(pos.up()).isIn(EERIE_RINDS)) {
            getAllBlock(world, pos.up(), null, list);
        }
        if (world.getBlockState(pos.down()).isOf(this) || world.getBlockState(pos.down()).isIn(EERIE_RINDS)) {
            getAllBlock(world, pos.down(), null, list);
        }
        for (Direction side : Direction.Type.HORIZONTAL) {
            BlockPos sidePos = pos.offset(side);
            switch (side) {
                case NORTH -> {
                    if (direction != Direction.SOUTH && (world.getBlockState(sidePos).isOf(this) || world.getBlockState(sidePos).isIn(EERIE_RINDS)))
                        getAllBlock(world, sidePos, side, list);
                }
                case SOUTH -> {
                    if (direction != Direction.NORTH && (world.getBlockState(sidePos).isOf(this) || world.getBlockState(sidePos).isIn(EERIE_RINDS)))
                        getAllBlock(world, sidePos, side, list);
                }
                case EAST -> {
                    if (direction != Direction.WEST && (world.getBlockState(sidePos).isOf(this) || world.getBlockState(sidePos).isIn(EERIE_RINDS)))
                        getAllBlock(world, sidePos, side, list);
                }
                case WEST -> {
                    if (direction != Direction.EAST && (world.getBlockState(sidePos).isOf(this) || world.getBlockState(sidePos).isIn(EERIE_RINDS)))
                        getAllBlock(world, sidePos, side, list);
                }
                default -> {
                    if ((world.getBlockState(sidePos).isOf(this) || world.getBlockState(sidePos).isIn(EERIE_RINDS)))
                        getAllBlock(world, sidePos, side, list);
                }
            }
        }
        return list;
    }

    /**
     * 破坏应该被破坏的方块
     */
    @SuppressWarnings("SameParameterValue")
    protected boolean breakBough(WorldAccess world, BlockPos pos, @Nullable Direction direction, Set<BlockPos> list) {
        if (!list.contains(pos)) list.add(pos);
        else
            return list.stream().allMatch(element -> world.getBlockState(element.down()).isAir() || world.getBlockState(element.down()).isOf(this));

        if (world.getBlockState(pos.up()).isOf(this) || world.getBlockState(pos.up()).isIn(EERIE_RINDS)) {
            getAllBlock(world, pos.up(), null, list);
        }
        if (world.getBlockState(pos.down()).isOf(this) || world.getBlockState(pos.down()).isIn(EERIE_RINDS)) {
            getAllBlock(world, pos.down(), null, list);
        }
        for (Direction side : Direction.Type.HORIZONTAL) {
            BlockPos sidePos = pos.offset(side);
            switch (side) {
                case NORTH -> {
                    if (direction != Direction.SOUTH && (world.getBlockState(sidePos).isOf(this) || world.getBlockState(sidePos).isIn(EERIE_RINDS)))
                        getAllBlock(world, sidePos, side, list);
                }
                case SOUTH -> {
                    if (direction != Direction.NORTH && (world.getBlockState(sidePos).isOf(this) || world.getBlockState(sidePos).isIn(EERIE_RINDS)))
                        getAllBlock(world, sidePos, side, list);
                }
                case EAST -> {
                    if (direction != Direction.WEST && (world.getBlockState(sidePos).isOf(this) || world.getBlockState(sidePos).isIn(EERIE_RINDS)))
                        getAllBlock(world, sidePos, side, list);
                }
                case WEST -> {
                    if (direction != Direction.EAST && (world.getBlockState(sidePos).isOf(this) || world.getBlockState(sidePos).isIn(EERIE_RINDS)))
                        getAllBlock(world, sidePos, side, list);
                }
                default -> {
                    if ((world.getBlockState(sidePos).isOf(this) || world.getBlockState(sidePos).isIn(EERIE_RINDS)))
                        getAllBlock(world, sidePos, side, list);
                }
            }
        }

        return list.stream().allMatch(element -> world.getBlockState(element.down()).isAir() || world.getBlockState(element.down()).isOf(this));
    }

    /**
     * <p>设置连接状态：</p>
     * <p>如果连接其他树木方块则和其他方块的连接无效</p>
     * <p>如果未连接到树木方块则只有上下连接</p>
     */
    public BlockState withConnectionProperties(BlockView world, BlockPos pos) {
        BlockState[] aroundBlocks = {
                world.getBlockState(pos.down()),
                world.getBlockState(pos.up()),
                world.getBlockState(pos.north()),
                world.getBlockState(pos.east()),
                world.getBlockState(pos.south()),
                world.getBlockState(pos.west())};
        List<Integer> connections = Arrays.asList(0, 0, 0, 0, 0, 0);
        // 有树木方块的部分为 1; 空气为 0; 其他方块为 -1
        for (int count = 0; count < aroundBlocks.length; count++) {
            if (aroundBlocks[count].isOf(this) || aroundBlocks[count].isIn(EERIE_RINDS)) connections.set(count, 1);
            else if (!aroundBlocks[count].isAir()) connections.set(count, -1);
        }
        // 都是木头
        if (connections.stream().allMatch(element -> element == 1)) {
            return getDefaultState()
                    .with(DOWN, true)
                    .with(UP, true)
                    .with(NORTH, true)
                    .with(EAST, true)
                    .with(SOUTH, true)
                    .with(WEST, true);
            // 没有木头连接
        } else if (connections.stream().allMatch(element -> element != 1)) {
            return getDefaultState()
                    .with(DOWN, true)
                    .with(UP, true)
                    .with(NORTH, false)
                    .with(EAST, false)
                    .with(SOUTH, false)
                    .with(WEST, false);
            // 只有有木头的部分连接
        } else {
            return getDefaultState()
                    .with(DOWN, connections.get(0) != 0)
                    .with(UP, connections.get(1) == 1)
                    .with(NORTH, connections.get(2) == 1)
                    .with(EAST, connections.get(3) == 1)
                    .with(SOUTH, connections.get(4) == 1)
                    .with(WEST, connections.get(5) == 1);
        }
    }

    /**
     * <p>设置可以放置在……</p>
     * <p>如果方块的两个方向有木枝则这两个方向叠加的块是木枝则不能放置</p>
     * <p>如果方块底部没有方块且四周没有木枝则不能放置</p>
     */
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (!world.getBlockState(pos).isAir()) return false;
        boolean upHave = world.getBlockState(pos.up()).isOf(this);
        boolean downHave = world.getBlockState(pos.down()).isOf(this);
        boolean northHave = world.getBlockState(pos.north()).isOf(this);
        boolean southHave = world.getBlockState(pos.south()).isOf(this);
        boolean eastHave = world.getBlockState(pos.east()).isOf(this);
        boolean westHave = world.getBlockState(pos.west()).isOf(this);
        if (upHave || downHave || northHave || southHave || eastHave || westHave) {
            if (northHave && eastHave) {
                return !world.getBlockState(pos.north().east()).isOf(this);
            } else if (northHave && westHave) {
                return !world.getBlockState(pos.north().west()).isOf(this);
            } else if (southHave && eastHave) {
                return !world.getBlockState(pos.south().east()).isOf(this);
            } else if (southHave && westHave) {
                return !world.getBlockState(pos.south().west()).isOf(this);
            } else if (upHave && northHave) {
                return !world.getBlockState(pos.up().north()).isOf(this);
            } else if (upHave && southHave) {
                return !world.getBlockState(pos.up().south()).isOf(this);
            } else if (upHave && eastHave) {
                return !world.getBlockState(pos.up().east()).isOf(this);
            } else if (upHave && westHave) {
                return !world.getBlockState(pos.up().west()).isOf(this);
            } else if (downHave && northHave) {
                return !world.getBlockState(pos.down().north()).isOf(this);
            } else if (downHave && southHave) {
                return !world.getBlockState(pos.down().south()).isOf(this);
            } else if (downHave && eastHave) {
                return !world.getBlockState(pos.down().east()).isOf(this);
            } else if (downHave && westHave) {
                return !world.getBlockState(pos.down().west()).isOf(this);
            }
        } else if (!world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP)) {
            for (Direction side : Direction.Type.HORIZONTAL) {
                if (world.getBlockState(pos.offset(side)).isIn(EERIE_RINDS))
                    return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.withConnectionProperties(ctx.getWorld(), ctx.getBlockPos());
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
}
