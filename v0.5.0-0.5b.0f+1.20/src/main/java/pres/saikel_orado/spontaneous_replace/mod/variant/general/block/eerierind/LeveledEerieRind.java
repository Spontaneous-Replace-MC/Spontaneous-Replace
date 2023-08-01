package pres.saikel_orado.spontaneous_replace.mod.variant.general.block.eerierind;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;

import java.util.Map;
import java.util.function.Predicate;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.POS_SHIFTING;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.EERIE_RIND;

/**
 * <b style="color:FFC800"><font size="+2">LeveledEerieRind：可调整液面阴森木壳</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">修复阴森木壳的液面递减问题</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/23 20:32
 */
public abstract class LeveledEerieRind extends LeveledCauldronBlock {
    public LeveledEerieRind(Settings settings, Predicate<Biome.Precipitation> precipitationPredicate, Map<Item, CauldronBehavior> behaviorMap) {
        super(settings, precipitationPredicate, behaviorMap);
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

    @Override
    protected void onFireCollision(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state);
    }

    public static void decrementFluidLevel(BlockState state, World world, BlockPos pos) {
        int i = state.get(LEVEL) - 1;
        BlockState blockState = i == 0 ? EERIE_RIND.getDefaultState() : state.with(LEVEL, i);
        world.setBlockState(pos, blockState);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
    }
}
