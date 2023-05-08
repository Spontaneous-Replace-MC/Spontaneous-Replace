package SpontaneousReplace.Mixin.SpiderBiome.Block.SpiderChrysalis;

import SpontaneousReplace.SpiderBiome.Generic.SpiderData;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ChunkRegion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

import static SpontaneousReplace.SpiderBiome.Blocks.SpiderChrysalis.Block.getRandomStyle;
import static SpontaneousReplace.SpiderBiome.Blocks.SpiderChrysalis.Block.isDoubleBlock;
import static SpontaneousReplace.SpiderBiome.Blocks.SpiderChrysalis.Server.SPIDER_CHRYSALIS;
import static SpontaneousReplace.SpiderBiome.Generic.SpiderData.CHRYSALIS_STYLE;

/**
 * <b style="color:FFC800"><font size="+2">SetBlock：放置方块混入</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">修改由地物放置系统放置蜘蛛茧蛹时使用与玩家放置方块一样的效果</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/10 16:55
 */
@Mixin(ChunkRegion.class)
public abstract class SetBlock {
    /**
     * 禁止实例化 SetBlock 放置方块混入类
     */
    private SetBlock() {
        throw new Error("请检查是否实例化 SetBlock 放置方块混入类");
    }

    /**
     * 截取设置方块状态函数自定义方块放置
     */
    @Shadow
    public abstract boolean setBlockState(BlockPos pos, BlockState state, int flags, int maxUpdateDepth);

    /**
     * 截取以便仿制原版 setBlock()
     */
    @Shadow
    public abstract boolean isValidForSetBlock(BlockPos pos);

    /**
     * 放置方块
     *
     * @param cir 回调函数
     */
    @Inject(method = "setBlockState", at = @At("HEAD"), cancellable = true)
    private void setBlock(BlockPos pos, BlockState state, int flags, int maxUpdateDepth, CallbackInfoReturnable<Boolean> cir) {
        if (!isValidForSetBlock(pos)) {
            cir.setReturnValue(false);
        } else {
            if (state.equals(SPIDER_CHRYSALIS.getDefaultState())) {
                SpiderData.ChrysalisStyle style = getRandomStyle();
                Direction horizontalDirection;
                {
                    int random = new Random().nextInt(4);
                    switch (random) {
                        case 0 -> horizontalDirection = Direction.NORTH;
                        case 1 -> horizontalDirection = Direction.WEST;
                        case 2 -> horizontalDirection = Direction.EAST;
                        default -> horizontalDirection = Direction.SOUTH;
                    }
                }
                // 保证如果方块被方块阻挡则只能放下小型样式方块
                Direction verticalDirection = null;
                {
                    if (isDoubleBlock(style)) {
                        if ((((ChunkRegion) (Object) this).getBlockState(pos.down()).isFullCube(((ChunkRegion) (Object) this), pos) || state.isIn(BlockTags.LEAVES))
                                && ((ChunkRegion) (Object) this).getBlockState(pos.up()).isOf(Blocks.AIR))
                            verticalDirection = Direction.UP;
                        else if ((((ChunkRegion) (Object) this).getBlockState(pos.up()).isFullCube(((ChunkRegion) (Object) this), pos) || state.isIn(BlockTags.LEAVES))
                                && ((ChunkRegion) (Object) this).getBlockState(pos.down()).isOf(Blocks.AIR))
                            verticalDirection = Direction.DOWN;
                        else style = SpiderData.ChrysalisStyle.SMALL;
                    }
                    if (!isDoubleBlock(style)) {
                        if (((ChunkRegion) (Object) this).getBlockState(pos.down()).isFullCube(((ChunkRegion) (Object) this), pos) || state.isIn(BlockTags.LEAVES))
                            verticalDirection = Direction.UP;
                        else if (((ChunkRegion) (Object) this).getBlockState(pos.up()).isFullCube(((ChunkRegion) (Object) this), pos) || state.isIn(BlockTags.LEAVES))
                            verticalDirection = Direction.DOWN;
                    }
                }

                if (isDoubleBlock(style)) {
                    if (verticalDirection == Direction.UP) {
                        if (style == SpiderData.ChrysalisStyle.DEFAULT) {
                            setBlockState(pos.up(), state.with(CHRYSALIS_STYLE, SpiderData.ChrysalisStyle.PLACEHOLDER_SHORT)
                                            .with(Properties.HORIZONTAL_FACING, horizontalDirection)
                                            .with(Properties.VERTICAL_DIRECTION, verticalDirection),
                                    flags, maxUpdateDepth);
                        } else {
                            setBlockState(pos.up(), state.with(CHRYSALIS_STYLE, SpiderData.ChrysalisStyle.PLACEHOLDER)
                                            .with(Properties.HORIZONTAL_FACING, horizontalDirection)
                                            .with(Properties.VERTICAL_DIRECTION, verticalDirection),
                                    flags, maxUpdateDepth);
                        }
                        cir.setReturnValue(setBlockState(pos, state.with(CHRYSALIS_STYLE, style)
                                        .with(Properties.HORIZONTAL_FACING, horizontalDirection)
                                        .with(Properties.VERTICAL_DIRECTION, verticalDirection),
                                flags, maxUpdateDepth));
                    } else {
                        if (style == SpiderData.ChrysalisStyle.DEFAULT) {
                            setBlockState(pos.down(), state.with(CHRYSALIS_STYLE, SpiderData.ChrysalisStyle.PLACEHOLDER_SHORT)
                                            .with(Properties.HORIZONTAL_FACING, horizontalDirection)
                                            .with(Properties.VERTICAL_DIRECTION, verticalDirection),
                                    flags, maxUpdateDepth);
                        } else {
                            setBlockState(pos.down(), state.with(CHRYSALIS_STYLE, SpiderData.ChrysalisStyle.PLACEHOLDER)
                                            .with(Properties.HORIZONTAL_FACING, horizontalDirection)
                                            .with(Properties.VERTICAL_DIRECTION, verticalDirection),
                                    flags, maxUpdateDepth);
                        }
                        cir.setReturnValue(setBlockState(pos, state.with(CHRYSALIS_STYLE, style)
                                        .with(Properties.HORIZONTAL_FACING, horizontalDirection)
                                        .with(Properties.VERTICAL_DIRECTION, verticalDirection),
                                flags, maxUpdateDepth));
                    }
                }
                cir.setReturnValue(setBlockState(pos, state.with(CHRYSALIS_STYLE, style)
                                .with(Properties.HORIZONTAL_FACING, horizontalDirection)
                                .with(Properties.VERTICAL_DIRECTION, verticalDirection),
                        flags, maxUpdateDepth));
            }
        }
    }
}
