package pres.saikel_orado.spontaneous_replace.mod.variant.general.block.eerierind;

import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.*;

/**
 * <b style="color:FFC800"><font size="+2">LavaEerieRind：含岩浆阴森木壳</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">内部盛有岩浆的阴森木壳</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/22 20:33
 */
@SuppressWarnings("deprecation")
public class LavaEerieRind extends AbstractCauldronBlock implements EerieRindShapeFix {
    public LavaEerieRind(Settings settings) {
        super(settings, EerieRindBehavior.LAVA_BEHAVIOR);
    }

    /**
     * 阴森木极寒，如在阴森木壳中含有岩浆，实体则不会冻伤也不会烧伤
     */
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
    }

    /**
     * <p>如果上方方块变为含非岩浆壳则传递熄灭</p>
     * <p>如果下方方块变为空气则传递岩浆到下方</p>
     * <p>如果下方方块变为空壳则传递岩浆到下方</p>
     */
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && (neighborState.isOf(WATER_EERIE_RIND) || neighborState.isOf(POWDER_SNOW_EERIE_RIND))) {
            world.setBlockState(neighborPos, EERIE_RIND.getDefaultState(), 3);
            world.playSound(null, neighborPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
        }
        if (direction != Direction.DOWN)
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        if (neighborState.isAir()) {
            world.setBlockState(pos, EERIE_RIND.getDefaultState(), 3);
            world.setBlockState(neighborPos, Blocks.LAVA.getDefaultState(), 3);
        }
        if (neighborState.isOf(EERIE_RIND)) {
            world.setBlockState(pos, EERIE_RIND.getDefaultState(), 3);
            world.setBlockState(neighborPos, state, 3);
        }
        if (neighborState.isOf(Blocks.CAULDRON)) {
            world.setBlockState(pos, EERIE_RIND.getDefaultState(), 3);
            world.setBlockState(neighborPos, Blocks.LAVA_CAULDRON.getDefaultState(), 3);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected double getFluidHeight(BlockState state) {
        return 0.9375;
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return 3;
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
