package SpontaneousReplace.Mixin.SpiderBiome.Block.SpiderChrysalis;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static SpontaneousReplace.SpiderBiome.Blocks.SpiderChrysalis.Server.SPIDER_CHRYSALIS;
import static SpontaneousReplace.SpiderBiome.Generic.SpiderData.COBWEB_MINING_SPEED;

/**
 * <b style="color:FFC800"><font size="+2">ShearsBreak：剪刀破坏混入</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置蜘蛛茧蛹的剪刀破坏加速</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/1/30 19:02
 */
@Mixin(ShearsItem.class)
public abstract class ShearsBreak {
    /**
     * 禁止实例化 ShearsBreak 剪刀破坏混入
     */
    private ShearsBreak() {
        throw new Error("请检查是否实例化 ShearsBreak 剪刀破坏混入");
    }

    /**
     * 矿物测试，如果方块是蜘蛛茧蛹则返回 true
     *
     * @param cir 可返回回调信息
     */
    @Inject(method = "postMine", at = @At("RETURN"), cancellable = true)
    public void postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(state.isOf(SPIDER_CHRYSALIS));
    }

    /**
     * 适用测试，如果方块是蜘蛛茧蛹则返回 true
     *
     * @param state 方块状态
     * @param cir   可返回回调信息
     */
    @Inject(method = "isSuitableFor", at = @At("RETURN"), cancellable = true)
    public void isSuitableFor(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isOf(SPIDER_CHRYSALIS))
            cir.setReturnValue(true);
    }

    /**
     * 获取挖掘速度增幅，如果方块是蜘蛛茧蛹则返回增幅倍数
     *
     * @param cir 可返回回调信息
     */
    @Inject(method = "getMiningSpeedMultiplier", at = @At("HEAD"), cancellable = true)
    public void getMiningSpeedMultiplier(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
        if (state.isOf(SPIDER_CHRYSALIS))
            cir.setReturnValue(COBWEB_MINING_SPEED);
    }
}