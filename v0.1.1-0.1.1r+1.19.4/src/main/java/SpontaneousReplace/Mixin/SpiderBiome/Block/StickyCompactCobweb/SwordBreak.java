package SpontaneousReplace.Mixin.SpiderBiome.Block.StickyCompactCobweb;

import SpontaneousReplace.Generic.ConfigData;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static SpontaneousReplace.SpiderBiome.Block.StickyCompactCobweb.Register.STICKY_COMPACT_COBWEB;
import static SpontaneousReplace.SpiderBiome.Generic.SpiderData.COBWEB_MINING_SPEED;

/**
 * <b style="color:FFC800"><font size="+2">SwordBreak：剑破坏混入</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置粘密蛛网的剑破坏加速</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/1/24 21:29
 */
@Mixin(SwordItem.class)
public abstract class SwordBreak {
    /**
     * 禁止实例化 SwordBreak 剑破坏混入
     */
    private SwordBreak() {
        throw new Error("请检查是否实例化 SwordBreak 剑破坏混入");
    }

    /**
     * 使用测试，如果方块是蜘蛛茧蛹则返回 true
     *
     * @param state 方块状态
     * @param cir   可返回回调信息
     */
    @Inject(method = "isSuitableFor", at = @At("RETURN"), cancellable = true)
    public void isSuitableFor(BlockState state, CallbackInfoReturnable<Boolean> cir) {
            if (state.isOf(STICKY_COMPACT_COBWEB))
                cir.setReturnValue(true);
    }

    /**
     * 获取挖掘速度增幅，如果方块是蜘蛛茧蛹则返回增幅倍数
     *
     * @param state 方块状态
     * @param cir   可返回回调信息
     */
    @Inject(method = "getMiningSpeedMultiplier", at = @At("RETURN"), cancellable = true)
    public void getMiningSpeedMultiplier(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
            if (state.isOf(STICKY_COMPACT_COBWEB))
                cir.setReturnValue(COBWEB_MINING_SPEED);
    }

}
