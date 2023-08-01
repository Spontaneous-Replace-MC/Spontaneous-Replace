package pres.saikel_orado.spontaneous_replace.mod.variant.general.block.eerierind;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.*;

/**
 * <b style="color:FFC800"><font size="+2">PowderSnowEerieRind：含细雪阴森木壳</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">内部盛有细雪的阴森木壳</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/22 20:30
 */
@SuppressWarnings("deprecation")
public class PowderSnowEerieRind extends LeveledEerieRind implements EerieRindShapeFix {
    public PowderSnowEerieRind(Settings settings) {
        super(settings, PowderSnowEerieRind.SNOW_PREDICATE, EerieRindBehavior.POWDER_SNOW_BEHAVIOR);
    }

    /**
     * 阴森木极寒，在阴森木壳中的实体会冻伤
     */
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this)) {
            entity.slowMovement(state, new Vec3d(0.9, 1.5, 0.9));
        }

        entity.setInPowderSnow(true);
        entity.setFrozenTicks(entity.getFrozenTicks() + 6);
        if (!world.isClient) {
            if (entity.isOnFire() && (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) || entity instanceof PlayerEntity) && entity.canModifyAt(world, pos)) {
                world.breakBlock(pos, false);
            }
            entity.setOnFire(false);
            if (entity.isFrozen()) entity.damage(entity.getDamageSources().freeze(), 1);
        }
    }

    /**
     * 会随机变成冻成冰
     */
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        world.setBlockState(pos, ICE_EERIE_RIND.getDefaultState());
        for (Direction direction : DIRECTIONS) {
            if (world.getBlockState(pos.offset(direction)).isOf(Blocks.WATER) && random.nextBoolean())
                world.setBlockState(pos.offset(direction), Blocks.ICE.getDefaultState());
        }
    }

    /**
     * 有随机刻
     */
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    /**
     * <p>如果上方方块变为含岩浆壳则替换细雪</p>
     * <p>如果上方方块变为含水壳则变为细雪</p>
     * <p>如果下方方块变为空气则传递细雪到下方</p>
     * <p>如果下方方块变为空壳则传递含细雪块到下方</p>
     */
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP) {
            if (neighborState.isOf(LAVA_EERIE_RIND)) {
                world.setBlockState(pos, LAVA_EERIE_RIND.getDefaultState(), 3);
                world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
                world.setBlockState(neighborPos, EERIE_RIND.getDefaultState(), 3);
            } else if (neighborState.isOf(WATER_EERIE_RIND) || neighborState.isOf(POWDER_SNOW_EERIE_RIND)) {
                int upLevel = Math.min(3, state.get(LeveledEerieRind.LEVEL) + neighborState.get(LeveledEerieRind.LEVEL));
                int posLevel = state.get(LeveledEerieRind.LEVEL) + neighborState.get(LeveledEerieRind.LEVEL) - MAX_LEVEL;
                world.setBlockState(pos, state.with(LeveledEerieRind.LEVEL, upLevel), 3);
                if (posLevel > 0)
                    world.setBlockState(neighborPos, state.with(LeveledEerieRind.LEVEL, posLevel), 3);
                else world.setBlockState(neighborPos, EERIE_RIND.getDefaultState(), 3);
            }
        }
        if (direction != Direction.DOWN)
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        if (neighborState.isAir()) {
            world.setBlockState(pos, EERIE_RIND.getDefaultState(), 3);
            if (isFull(state))
                world.setBlockState(neighborPos, Blocks.POWDER_SNOW.getDefaultState(), 3);
        }
        if (neighborState.isOf(EERIE_RIND)) {
            world.setBlockState(pos, EERIE_RIND.getDefaultState(), 3);
            world.setBlockState(neighborPos, state, 3);
        }
        if (neighborState.isOf(Blocks.CAULDRON)) {
            world.setBlockState(pos, EERIE_RIND.getDefaultState(), 3);
            world.setBlockState(neighborPos, Blocks.POWDER_SNOW_CAULDRON.getDefaultState().with(LeveledEerieRind.LEVEL, state.get(LeveledEerieRind.LEVEL)), 3);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return getEerieRindRaycastShape();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getEerieRindOutlineShape();
    }
}
