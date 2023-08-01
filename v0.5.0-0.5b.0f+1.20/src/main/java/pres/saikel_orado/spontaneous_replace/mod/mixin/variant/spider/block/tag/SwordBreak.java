package pres.saikel_orado.spontaneous_replace.mod.mixin.variant.spider.block.tag;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.COBWEBS;
import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderData.COBWEB_MINING_SPEED;

/**
 * <b style="color:FFC800"><font size="+2">SwordBreak：剑破坏混入</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置 cobwebs 方块标签的剑破坏加速</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/17 14:30
 */
@Mixin(SwordItem.class)
abstract class SwordBreak {
    /**
     * 使用测试，如果方块是 cobwebs 方块标签则返回 true
     *
     * @param state 方块状态
     * @param cir   可返回回调信息
     */
    @Inject(method = "isSuitableFor", at = @At("RETURN"), cancellable = true)
    public void isSuitableFor(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(COBWEBS))
            cir.setReturnValue(true);
    }

    /**
     * 获取挖掘速度增幅，如果方块是 cobwebs 方块标签则返回增幅倍数
     *
     * @param state 方块状态
     * @param cir   可返回回调信息
     */
    @Inject(method = "getMiningSpeedMultiplier", at = @At("RETURN"), cancellable = true)
    public void getMiningSpeedMultiplier(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
        if (state.isIn(COBWEBS))
            cir.setReturnValue(COBWEB_MINING_SPEED);
    }
}
