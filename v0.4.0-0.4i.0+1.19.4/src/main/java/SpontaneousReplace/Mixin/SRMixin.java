package SpontaneousReplace.Mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import SpontaneousReplace.SRMain;

/**
 * <b style="color:FFC800"><font size="+2">SRMixin：自然更替混入主类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">模组的主混入类，用来执行特殊或重要的注入操作</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 < 2022/11/14
 */
@Mixin(TitleScreen.class)
public abstract class SRMixin {
    /**
     * 禁止实例化 SRMixin 自然更替主混入
     */
    private SRMixin() {
        throw new Error("请检查是否实例化 SRMixin 自然更替主混入");
    }

    /**
     * 加载测试
     *
     * @param ci 回调信息
     */
    @Inject(method = "init()V", at = @At("HEAD"))
    private void init(CallbackInfo ci) {
        SRMain.LOGGER.info("自然更替 Mixin 组件加载测试输出");
    }
}
