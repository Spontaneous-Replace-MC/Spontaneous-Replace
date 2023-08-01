package pres.saikel_orado.spontaneous_replace.mod.mixin.button;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.tab.GridScreenTab;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import pres.saikel_orado.spontaneous_replace.mod.screen.WorldConfigScreen;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.CreateWorldScreenData.worldConfigScreen;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.getModConfigText;

/**
 * <b style="color:FFC800"><font size="+2">WorldConfigOption：世界配置设置</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">向高级世界选项界面注入模组世界配置按钮</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 < 2022/11/14
 */
@Mixin(targets = "net.minecraft.client.gui.screen.world.CreateWorldScreen$MoreTab")
abstract class WorldConfigOption extends GridScreenTab {
    private WorldConfigOption(Text title) {
        super(title);
    }

    /**
     * 注入世界配置按钮
     *
     * @param ci 回调信息
     */
    @Inject(method = "<init>(L net/minecraft/client/gui/screen/world/CreateWorldScreen;)V", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void injected(CreateWorldScreen createWorldScreen, CallbackInfo ci, GridWidget.Adder adder) {
        // 添加按钮
        adder.add(ButtonWidget.builder(Text.translatable(getModConfigText("world_option_button")),
                (button) -> MinecraftClient.getInstance().setScreen(new WorldConfigScreen(worldConfigScreen))).width(210).build());
    }
}

/**
 * 为了截取世界配置按钮的父屏幕对象
 */
@Mixin(CreateWorldScreen.class)
abstract class WorldConfig extends Screen {
    private WorldConfig(Text title) {
        super(title);
    }

    /**
     * 截取父屏幕
     *
     * @param ci 回调信息
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void injected(CallbackInfo ci) {
        // 截取父屏幕
        worldConfigScreen = this;
    }
}
