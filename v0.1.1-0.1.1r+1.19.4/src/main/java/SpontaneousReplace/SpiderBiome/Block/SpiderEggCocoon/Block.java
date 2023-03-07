package SpontaneousReplace.SpiderBiome.Block.SpiderEggCocoon;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static SpontaneousReplace.SpiderBiome.Block.SpiderEggCocoon.Register.SPIDER_EGG_COCOON_ENTITY;

/**
 * <b style="color:FFC800"><font size="+2">Block：蜘蛛卵茧方块类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">为蜘蛛卵茧方块实体创建一个关联方块</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/2/3 15:35
 */
public class Block extends BlockWithEntity {
    /**
     * <p>构建蜘蛛卵茧</p>
     * <p>垂直方向：上</p>
     *
     * @param settings 设置
     */
    public Block(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.VERTICAL_DIRECTION, Direction.UP));
    }

    /**
     * 创建蜘蛛卵茧实体
     */
    @Nullable
    @Override
    public net.minecraft.block.entity.BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntity(pos, state);
    }

    /**
     * 方块追加属性
     *
     * @param builder 构建器
     */
    @Override
    protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
        builder.add(Properties.VERTICAL_DIRECTION);
    }

    /**
     * 获取渲染类型
     */
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    /**
     * 获取放置方向
     */
    @Nullable
    protected static Direction getDirectionToPlaceAt(WorldView world, BlockPos pos, Direction direction) {
        Direction direction2;
        if (canPlaceAtWithDirection(world, pos, direction)) {
            direction2 = direction;
        } else if (canPlaceAtWithDirection(world, pos, direction.getOpposite())) {
            direction2 = direction.getOpposite();
        } else {
            return null;
        }
        return direction2;
    }

    /**
     * 获取放置状态
     */
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = getDirectionToPlaceAt(ctx.getWorld(), ctx.getBlockPos(), ctx.getVerticalPlayerLookDirection().getOpposite());
        return direction == null ? null : Objects.requireNonNull(super.getPlacementState(ctx)).with(Properties.VERTICAL_DIRECTION, direction);
    }

    /**
     * 如果蜘蛛卵茧的附着方块被破坏则破坏蜘蛛卵茧
     */
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction.getAxis() == Direction.Axis.Y) {
            if (state.get(Properties.VERTICAL_DIRECTION) == Direction.UP && pos.down().getY() == neighborPos.getY() && neighborState.isOf(Blocks.AIR))
                return Blocks.AIR.getDefaultState();
            else if (state.get(Properties.VERTICAL_DIRECTION) == Direction.DOWN && pos.up().getY() == neighborPos.getY() && neighborState.isOf(Blocks.AIR))
                return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    /**
     * 设置每 Tick 运行函数
     */
    @Nullable
    @Override
    public <T extends net.minecraft.block.entity.BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return Block.checkType(type, SPIDER_EGG_COCOON_ENTITY, world.isClient ? BlockEntity::clientTick : BlockEntity::serverTick);
    }

    /**
     * 能放置方向
     */
    protected static boolean canPlaceAtWithDirection(WorldView world, BlockPos pos, Direction direction) {
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction);
    }
}
