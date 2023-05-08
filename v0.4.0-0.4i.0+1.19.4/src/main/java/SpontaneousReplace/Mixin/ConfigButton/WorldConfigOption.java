package SpontaneousReplace.Mixin.ConfigButton;

import SpontaneousReplace.ConfigScreen.WorldConfigScreen;
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

import static SpontaneousReplace.Generic.ConfigData.CreateWorldScreenData.worldConfigScreen;
import static SpontaneousReplace.Generic.ConfigData.getModConfigText;

/**
 * <b style="color:FFC800"><font size="+2">WorldConfigOption：世界配置设置</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">向高级世界选项界面注入模组世界配置按钮</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 < 2022/11/14
 */
@Mixin(targets = "net.minecraft.client.gui.screen.world.CreateWorldScreen$MoreTab")
public abstract class WorldConfigOption extends GridScreenTab {
    /**
     * 禁止实例化 WorldConfigOption 世界配置按钮类
     */
    private WorldConfigOption() {
        super(null);
        throw new Error("请检查是否实例化 WorldConfigOption 世界配置按钮类");
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
                (Button) -> MinecraftClient.getInstance().setScreen(new WorldConfigScreen(worldConfigScreen))).width(210).build());
    }
}

/**
 * 为了截取世界配置按钮的父屏幕对象
 */
@Mixin(CreateWorldScreen.class)
abstract class WorldConfig extends Screen {
    /**
     * 禁止实例化 WorldConfigOption 世界配置按钮类
     */
    private WorldConfig() {
        super(null);
        throw new Error("请检查是否实例化 WorldConfigOption 世界配置按钮类");
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
