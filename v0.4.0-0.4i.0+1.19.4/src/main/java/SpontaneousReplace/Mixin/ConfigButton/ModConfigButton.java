package SpontaneousReplace.Mixin.ConfigButton;

import SpontaneousReplace.ConfigScreen.ConfigScreen;
import SpontaneousReplace.Generic.ConfigData;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static SpontaneousReplace.ConfigScreen.ConfigScreen.SR_CONFIG_SCREEN_TEXT;

/**
 * <b style="color:FFC800"><font size="+2">ModConfigButton：模组配置按钮</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">向游戏菜单界面注入模组配置按钮</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 < 2022/11/14
 */

@Mixin(GameMenuScreen.class)
public abstract class ModConfigButton extends Screen {
    /**
     * 禁止实例化 ConfigButton 模组配置按钮类
     */
    private ModConfigButton() {
        super(null);
        throw new Error("请检查是否实例化 ConfigButton 模组配置按钮类");
    }

    private GridWidget.Adder adder;

    /**
     * 重新绘制游戏菜单按钮并添加模组配置按钮
     *
     * @param ci 回调信息
     */
    @Inject(method = "initWidgets", at = @At(value = "INVOKE",
            target = "L net/minecraft/client/gui/widget/GridWidget$Adder;add(L net/minecraft/client/gui/widget/Widget;IL net/minecraft/client/gui/widget/Positioner;)L net/minecraft/client/gui/widget/Widget;",
            shift = At.Shift.AFTER))
    private void initWidgets(CallbackInfo ci) {
        if (!(FabricLoader.getInstance().isModLoaded("modmenu") && ConfigData.Config.autoShowConfigButton))
            adder.add(ButtonWidget.builder(SR_CONFIG_SCREEN_TEXT, (button) -> Objects.requireNonNull(client).setScreen(new ConfigScreen(this))).width(204).build(), 2);
    }

    /**
     * 截出游戏菜单中的按钮控件 adder
     */
    @ModifyVariable(method = "initWidgets", at = @At("STORE"), ordinal = 0)
    private GridWidget.Adder initWidgets(GridWidget.Adder adder) {
        return this.adder = adder;
    }
}