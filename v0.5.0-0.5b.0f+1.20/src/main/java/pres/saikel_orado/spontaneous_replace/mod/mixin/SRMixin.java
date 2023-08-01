package pres.saikel_orado.spontaneous_replace.mod.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.SR_LOGGER;

/**
 * <b style="color:FFC800"><font size="+2">SRMixin：自然更替混入主类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">模组的主混入类，用来执行特殊或重要的注入操作</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 < 2022/11/14
 */
@Mixin(TitleScreen.class)
abstract class SRMixin {
    /**
     * 加载测试
     *
     * @param ci 回调信息
     */
    @Inject(method = "init()V", at = @At("HEAD"))
    private void init(CallbackInfo ci) {
        SR_LOGGER.info("自然更替 Mixin 组件加载测试输出");
    }
}
