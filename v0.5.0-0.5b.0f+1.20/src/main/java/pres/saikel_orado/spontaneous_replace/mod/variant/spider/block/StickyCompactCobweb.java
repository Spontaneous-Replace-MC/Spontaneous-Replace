package pres.saikel_orado.spontaneous_replace.mod.variant.spider.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderData;

/**
 * <b style="color:FFC800"><font size="+2">StickyCompactCobweb：黏密蛛网方块类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">确定黏密蛛网方块的特殊方块特性</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/1/24 12:11
 */
@SuppressWarnings("deprecation")
public class StickyCompactCobweb extends Block {
    /**
     * 构建黏密蛛网
     *
     * @param settings 设置
     */
    public StickyCompactCobweb(Settings settings) {
        super(settings);
    }

    /**
     * 如果非蜘蛛实体冲突则减速
     */
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof SpiderEntity))
            entity.slowMovement(state, new Vec3d(SRSpiderData.STICKY_COMPACT_COBWEB_DECELERATION, SRSpiderData.STICKY_COMPACT_COBWEB_DROP, SRSpiderData.STICKY_COMPACT_COBWEB_DECELERATION));
    }
}
