package pres.saikel_orado.spontaneous_replace.mod.variant.spider.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderData;

/**
 * <b style="color:FFC800"><font size="+2">GossameryLeaves：覆丝树叶</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">一种特殊的装饰性树叶类方块</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/16 21:52
 */
public class GossameryLeaves extends LeavesBlock {
    public GossameryLeaves(Settings settings) {
        super(settings);
    }

    /**
     * 在方块上减速 25%
     */
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!(entity instanceof SpiderEntity))
            entity.slowMovement(state, new Vec3d(1 - SRSpiderData.COBWEB_DECELERATION, 1 - SRSpiderData.COBWEB_DECELERATION, 1 - SRSpiderData.COBWEB_DECELERATION));

        super.onSteppedOn(world, pos, state, entity);
    }
}
