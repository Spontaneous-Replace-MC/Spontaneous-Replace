package pres.saikel_orado.spontaneous_replace.mod.mixin.button;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.option.CreditsAndAttributionScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PressableTextWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData;
import pres.saikel_orado.spontaneous_replace.mod.screen.srbutton.SRScreen;

import java.util.Objects;

import static com.terraformersmc.modmenu.config.ModMenuConfig.MODS_BUTTON_STYLE;
import static com.terraformersmc.modmenu.config.ModMenuConfig.TitleMenuButtonStyle.CLASSIC;
import static net.minecraft.client.gui.screen.TitleScreen.COPYRIGHT;
import static pres.saikel_orado.spontaneous_replace.mod.screen.srbutton.SRScreen.SR_BUTTON_TEXT;

/**
 * <b style="color:FFC800"><font size="+2">SRButton：模组配置按钮</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">向设置界面注入模组配置按钮</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 6.0
 * | 创建于 < 2022/11/14
 */

@Mixin(TitleScreen.class)
abstract class SRButton extends Screen {
    private SRButton(Text title) {
        super(title);
    }

    /**
     * 如果未安装 Mod Menu 模组菜单模组则重新绘制版权文本
     *
     * @param ci 回调信息
     */
    @Inject(method = "init", at = @At("HEAD"))
    private void init(CallbackInfo ci) {
        // 获取原始高度
        int originHeight = height;
        // 如果玩家未添加了 Mod Menu 模组菜单模组
        if (!FabricLoader.getInstance().isModLoaded("modmenu")) {
            // 缩减原按键高度
            height += height / 4;
            // 恢复版权文本按钮显示按钮
            int textWidth = textRenderer.getWidth(COPYRIGHT);
            addDrawableChild(new PressableTextWidget(width - textWidth - 2, originHeight - 10, textWidth, 10, COPYRIGHT,
                    (button) -> Objects.requireNonNull(client).setScreen(new CreditsAndAttributionScreen(this)), textRenderer));
        }
    }

    /**
     * 修复 Realms 控件高度
     *
     * @param height 原高度
     * @return 修复高度
     */
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "L net/minecraft/client/realms/gui/screen/RealmsNotificationsScreen;init(L net/minecraft/client/MinecraftClient;II)V"), index = 2)
    private int fixRealms(int height) {
        if (!SRConfigData.DevConfig.disableModButton) {
            if (FabricLoader.getInstance().isModLoaded("modmenu")
                    && !SRConfigData.Config.autoShowConfigButton
                    && MODS_BUTTON_STYLE.getValue().equals(CLASSIC))
                return height + 40;
        }
        return height;
    }

    /**
     * 注入模组配置按钮
     *
     * @param y        Y 轴高度
     * @param spacingY Y 轴间距
     * @param ci       回调信息
     */
    @Inject(method = "initWidgetsNormal", at = @At("TAIL"))
    private void initWidgetsNormal(int y, int spacingY, CallbackInfo ci) {
        // 如果开发者未取消了显示按钮
        if (!SRConfigData.DevConfig.disableModButton) {
            // 如果开启了自动显示配置按钮则自动显示此按钮
            if (!(FabricLoader.getInstance().isModLoaded("modmenu") && SRConfigData.Config.autoShowConfigButton)) {
                // 添加 自然更替 按钮
                addDrawableChild(ButtonWidget.builder(SR_BUTTON_TEXT,
                                (button) -> MinecraftClient.getInstance().setScreen(new SRScreen(this)))
                        .dimensions(width / 2 - 100,
                                y - (FabricLoader.getInstance().isModLoaded("modmenu") && MODS_BUTTON_STYLE.getValue().equals(CLASSIC) ?
                                        spacingY * 2 : spacingY),
                                200, 20)
                        .build());
            }
        }
    }

    /**
     * 如果开发者开启显示或玩家添加了 Mod Menu 模组菜单模组并关闭自动按钮显示则降低 Y 轴高度
     *
     * @param y Y 轴高度
     * @return 如果玩家添加了 Mod Menu 模组菜单模组则降低由窗口长宽比值决定的高度
     */
    @ModifyVariable(method = "initWidgetsNormal(II)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int ifHasModMenu(int y) {
        if (SRConfigData.DevConfig.disableModButton)
            return y;
        return (FabricLoader.getInstance().isModLoaded("modmenu")
                && !SRConfigData.Config.autoShowConfigButton
                && MODS_BUTTON_STYLE.getValue().equals(CLASSIC)) ?
                y + (int) ((double) y / width * width / y * 10) : y;
    }
}
