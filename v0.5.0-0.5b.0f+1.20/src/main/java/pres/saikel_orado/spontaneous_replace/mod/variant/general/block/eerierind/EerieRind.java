package pres.saikel_orado.spontaneous_replace.mod.variant.general.block.eerierind;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
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
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.POS_SHIFTING;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.*;

/**
 * <b style="color:FFC800"><font size="+2">EerieRind：阴森木壳</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">一种被感染的原木类方块，十分特殊，具有炼药锅的特性</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/22 19:09
 */
@SuppressWarnings("deprecation")
public class EerieRind extends AbstractCauldronBlock implements EerieRindShapeFix {
    public static final int MAX_LEVEL = 3;
    private static final float FILL_WITH_RAIN_CHANCE = 0.05F;
    private static final float FILL_WITH_SNOW_CHANCE = 0.1F;

    public EerieRind(AbstractBlock.Settings settings) {
        super(settings, EerieRindBehavior.EMPTY_BEHAVIOR);
    }

    /**
     * 阴森木极寒，在阴森木壳中的实体会冻伤
     */
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this)) {
            entity.slowMovement(state, new Vec3d(0.9, 1.5, 0.9));
        }

        entity.setInPowderSnow(true);
        entity.setFrozenTicks(entity.getFrozenTicks() + 2);
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

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        BlockPos upPos = pos.up();
        BlockState upState = world.getBlockState(upPos);
        if (upState.isOf(Blocks.LAVA)) {
            world.setBlockState(pos, LAVA_EERIE_RIND.getDefaultState(), 3);
            world.setBlockState(upPos, Blocks.AIR.getDefaultState(), 3);
        } else if (upState.isOf(Blocks.WATER)) {
            world.setBlockState(pos, WATER_EERIE_RIND.getDefaultState(), 3);
            world.setBlockState(upPos, Blocks.AIR.getDefaultState(), 3);
        } else if (upState.isOf(Blocks.POWDER_SNOW)) {
            world.setBlockState(pos, POWDER_SNOW_EERIE_RIND.getDefaultState(), 3);
            world.setBlockState(upPos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState
            neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction != Direction.UP)
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        BlockPos downPos = pos.down();
        BlockState downBlock = world.getBlockState(downPos);
        if ((neighborState.isIn(EERIE_RINDS) || neighborState.isOf(Blocks.LAVA) || neighborState.isOf(Blocks.WATER) || neighborState.isOf(Blocks.POWDER_SNOW))
                && !neighborState.isOf(EERIE_RIND)) {
            if (neighborState.isOf(Blocks.LAVA)) {
                world.setBlockState(pos, LAVA_EERIE_RIND.getDefaultState(), 3);
                world.setBlockState(neighborPos, Blocks.AIR.getDefaultState(), 3);
            } else if (neighborState.isOf(Blocks.WATER)) {
                world.setBlockState(pos, WATER_EERIE_RIND.getDefaultState(), 3);
                world.setBlockState(neighborPos, Blocks.AIR.getDefaultState(), 3);
            } else if (neighborState.isOf(Blocks.POWDER_SNOW)) {
                world.setBlockState(pos, POWDER_SNOW_EERIE_RIND.getDefaultState(), 3);
                world.setBlockState(neighborPos, Blocks.AIR.getDefaultState(), 3);
            } else {
                world.setBlockState(pos, neighborState, 3);
                world.setBlockState(neighborPos, state, 3);
            }
        }
        if (downBlock.isOf(Blocks.LAVA)) {
            if (neighborState.isOf(WATER_EERIE_RIND) || neighborState.isOf(POWDER_SNOW_EERIE_RIND)) {
                world.setBlockState(downPos, Blocks.OBSIDIAN.getDefaultState(), 3);
                world.playSound(null, downPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
                world.setBlockState(pos, getDefaultState(), 3);
            }
        } else if (downBlock.isOf(Blocks.WATER)) {
            if (neighborState.isOf(LAVA_EERIE_RIND)) {
                world.setBlockState(downPos, Blocks.COBBLESTONE.getDefaultState(), 3);
                world.playSound(null, downPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
                world.setBlockState(pos, getDefaultState(), 3);
            } else if (neighborState.isOf(POWDER_SNOW_EERIE_RIND)) {
                world.setBlockState(pos, WATER_EERIE_RIND.getDefaultState(), 3);
            }
        } else if (downBlock.isOf(Blocks.POWDER_SNOW)) {
            if (neighborState.isOf(LAVA_EERIE_RIND)) {
                world.setBlockState(downPos, Blocks.LAVA.getDefaultState(), 3);
                world.playSound(null, downPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
                world.setBlockState(pos, getDefaultState(), 3);
            } else if (neighborState.isOf(WATER_EERIE_RIND)) {
                world.setBlockState(pos, POWDER_SNOW_EERIE_RIND.getDefaultState(), 3);
            }
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    /**
     * 随机灵魂粒子效果
     */
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (random.nextInt(3) == 0) {
            java.util.Random randomValue = new java.util.Random();
            world.addParticle(ParticleTypes.SCULK_SOUL,
                    pos.getX() + POS_SHIFTING + randomValue.nextDouble(-1, 1),
                    pos.getY() + POS_SHIFTING + randomValue.nextDouble(-1, 1),
                    pos.getZ() + POS_SHIFTING + randomValue.nextDouble(-1, 1),
                    randomValue.nextDouble(-0.07, 0.07),
                    randomValue.nextDouble(0, 0.1),
                    randomValue.nextDouble(-0.07, 0.07));
        }
    }

    /**
     * 是否满了
     */
    @Override
    public boolean isFull(BlockState state) {
        return false;
    }

    /**
     * <p>降水游戏刻</p>
     * <p>如果底下有完整方块则可以操作</p>
     */
    @Override
    public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
        if (!world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP))
            return;
        if (canFillWithPrecipitation(world, precipitation)) {
            if (precipitation == Biome.Precipitation.RAIN) {
                world.setBlockState(pos, WATER_EERIE_RIND.getDefaultState());
                world.emitGameEvent(null, GameEvent.BLOCK_CHANGE, pos);
            } else if (precipitation == Biome.Precipitation.SNOW) {
                world.setBlockState(pos, POWDER_SNOW_EERIE_RIND.getDefaultState());
                world.emitGameEvent(null, GameEvent.BLOCK_CHANGE, pos);
            }
        }
    }

    /**
     * 可以通过滴水石锥填充
     */
    @Override
    protected boolean canBeFilledByDripstone(Fluid fluid) {
        return true;
    }

    /**
     * 如果底下有完整方块则可以通过滴水石锥填充
     */
    @Override
    protected void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid) {
        if (!world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP))
            return;
        BlockState blockState;
        if (fluid == Fluids.WATER) {
            blockState = WATER_EERIE_RIND.getDefaultState();
            world.setBlockState(pos, blockState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
            world.syncWorldEvent(1047, pos, 0);
        } else if (fluid == Fluids.LAVA) {
            blockState = LAVA_EERIE_RIND.getDefaultState();
            world.setBlockState(pos, blockState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
            world.syncWorldEvent(1046, pos, 0);
        }
    }

    /**
     * 可以充满降水
     */
    protected static boolean canFillWithPrecipitation(World world, Biome.Precipitation precipitation) {
        if (precipitation == Biome.Precipitation.RAIN) {
            return world.getRandom().nextFloat() < FILL_WITH_RAIN_CHANCE;
        } else if (precipitation == Biome.Precipitation.SNOW) {
            return world.getRandom().nextFloat() < FILL_WITH_SNOW_CHANCE;
        } else {
            return false;
        }
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
