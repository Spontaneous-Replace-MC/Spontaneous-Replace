package pres.saikel_orado.spontaneous_replace.mod.mixin.update;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.Config.Update.updateNotification;
import static pres.saikel_orado.spontaneous_replace.mod.screen.Update.setUpdateScreen;
import static pres.saikel_orado.spontaneous_replace.mod.screen.Update.setUpdateToast;

/**
 * <b style="color:FFC800"><font size="+2">UpdateInTitle：标题屏幕混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">将更新日志与更新提示在标题屏幕中显示</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/6/6 13:51
 */
@Mixin(TitleScreen.class)
abstract class UpdateInTitle {
    /**
     * 检测是否显示更新
     */
    @Inject(method = "tick", at = @At("RETURN"))
    private void tick(CallbackInfo ci) {
        if (updateNotification)
            setUpdateScreen((TitleScreen) (Object) this);
        else setUpdateToast();
    }
}
