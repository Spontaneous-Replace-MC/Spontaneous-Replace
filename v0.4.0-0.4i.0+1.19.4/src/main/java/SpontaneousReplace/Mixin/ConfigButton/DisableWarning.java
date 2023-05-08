package SpontaneousReplace.Mixin.ConfigButton;

import SpontaneousReplace.Generic.ConfigData;
import net.minecraft.server.integrated.IntegratedServerLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * <b style="color:FFC800"><font size="+2">DisableWarning：禁用警告</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">禁用任何有关实验性功能的警告</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/3/20 21:29
 */
@Mixin(IntegratedServerLoader.class)
public abstract class DisableWarning {
    /**
     * 禁用创建建议
     */
    @ModifyVariable(method = "tryLoad", at = @At("HEAD"), argsOnly = true, index = 4)
    private static boolean disableCreationAdvice(boolean flag) {
        return ConfigData.Config.disableWarningAdvice || flag;
    }

    /**
     * 禁用加载建议
     */
    @ModifyVariable(method = "start(L net/minecraft/client/gui/screen/Screen;L java/lang/String;ZZ)V",
            at = @At("HEAD"), argsOnly = true, index = 4)
    private boolean disableLoadAdvice(boolean flag) {
        return !ConfigData.Config.disableWarningAdvice && flag;
    }
}
