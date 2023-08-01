package pres.saikel_orado.spontaneous_replace.mod.variant.general.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WeepingVinesBlock;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.POS_SHIFTING;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.TREACHEROUS_VINES_PLANT;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRUtil.getTick;
import static pres.saikel_orado.spontaneous_replace.mod.variant.general.data.SRVariantData.TREACHEROUS_PLANT_STABILITY;

/**
 * <b style="color:FFC800"><font size="+2">TreacherousVines：诡谲藤</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">一种被感染的藤蔓类方块</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/18 23:58
 */
@SuppressWarnings("deprecation")
public class TreacherousVines extends WeepingVinesBlock {
    public static final int TREACHEROUS_VINES_LUMINANCE = 7;

    public TreacherousVines(Settings settings) {
        super(settings);
    }

    @Override
    protected Block getPlant() {
        return TREACHEROUS_VINES_PLANT;
    }

    /**
     * 随机酸气粒子效果
     */
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (random.nextInt(5) == 0) {
            java.util.Random randomValue = new java.util.Random();
            int color = 0xFF6A00;
            world.addParticle(new AreaEffectCloudEntity(world, 0, 0, 0).getParticleType(),
                    pos.getX() + POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5),
                    pos.getY() + POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5),
                    pos.getZ() + POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5),
                    (float) (color >> 16 & 255) / 255.0F,
                    (float) (color >> 8 & 255) / 255.0F,
                    (float) (0) / 255.0F);
        }
    }

    /**
     * 玩家接触会中毒
     */
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        if (!world.isClient && entity instanceof LivingEntity living
                && new Random().nextInt((int) Math.pow(TREACHEROUS_PLANT_STABILITY, 3)) == 0)
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, getTick(1)));
    }
}
