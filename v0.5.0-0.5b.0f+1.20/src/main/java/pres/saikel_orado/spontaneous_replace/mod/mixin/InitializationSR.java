package pres.saikel_orado.spontaneous_replace.mod.mixin;

import pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData;
import net.minecraft.client.resource.language.LanguageManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * <b style="color:FFC800"><font size="+2">InitializationSR：自然更替初始化混入</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">模组初始化操作，所有操作将在游戏资源加载时进行</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 5.0
 * | 创建于 2022/12/24 ~ 2022/12/26
 */
@Mixin(LanguageManager.class)
abstract class InitializationSR {
    /**
     * 在游戏资源初始化过程中确定语言
     *
     * @param ci 回调信息
     */
    @Inject(method = "reload", at = @At("TAIL"))
    private void determineLanguage(CallbackInfo ci) {
        SRConfigData.language = ((LanguageManager) (Object) this).getLanguage();
        SRConfigData.isZhCN = "zh_cn".equals(SRConfigData.language);
    }

    /**
     * 在游戏资源初始化过程中读取模组配置文件
     *
     * @param ci 回调信息
     */
    @Inject(method = "reload", at = @At("TAIL"))
    private void loadConfig(CallbackInfo ci) {
        SRConfigData.readConfig();
    }
}
