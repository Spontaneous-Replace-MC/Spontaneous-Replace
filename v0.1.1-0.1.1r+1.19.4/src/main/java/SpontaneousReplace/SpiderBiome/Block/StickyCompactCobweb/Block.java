package SpontaneousReplace.SpiderBiome.Block.StickyCompactCobweb;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import static SpontaneousReplace.SpiderBiome.Generic.SpiderData.STICKY_COMPACT_COBWEB_DECELERATION;
import static SpontaneousReplace.SpiderBiome.Generic.SpiderData.STICKY_COMPACT_COBWEB_DROP;

/**
 * <b style="color:FFC800"><font size="+2">Block：粘密蛛网方块类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">确定粘密蛛网方块的特殊方块特性</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/1/24 12:11
 */
public class Block extends net.minecraft.block.Block {
    /**
     * 构建粘密蛛网
     *
     * @param settings 设置
     */
    public Block(Settings settings) {
        super(settings);
    }

    /**
     * 如果非蜘蛛实体冲突则减速
     */
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof SpiderEntity))
            entity.slowMovement(state, new Vec3d(STICKY_COMPACT_COBWEB_DECELERATION, STICKY_COMPACT_COBWEB_DROP, STICKY_COMPACT_COBWEB_DECELERATION));
    }
}
