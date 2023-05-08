package SpontaneousReplace.Mixin.ConfigButton;

import SpontaneousReplace.ConfigScreen.ConfigScreen;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static SpontaneousReplace.Generic.ConfigData.Config;
import static SpontaneousReplace.Generic.ConfigData.getModConfigText;

/**
 * <b style="color:FFC800"><font size="+2">ModConfigButton：模组配置按钮</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">向设置界面注入模组配置按钮</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 < 2022/11/14
 */
@Mixin(OptionsScreen.class)
public abstract class ModConfigButton extends Screen {
    /**
     * 禁止实例化 ModConfigButton 模组配置按钮类
     */
    private ModConfigButton() {
        super(null);
        throw new Error("请检查是否实例化 ModConfigButton 模组配置按钮类");
    }

    /**
     * 注入模组配置按钮
     *
     * @param ci 回调信息
     */
    @Inject(method = "init()V", at = @At("HEAD"))
    private void init(CallbackInfo ci) {
        // 如果开启了自动显示配置按钮则自动显示此按钮
        if (!(Config.AutoShowConfigButton && FabricLoader.getInstance().isModLoaded("modmenu"))) {
            // 如果玩家添加了 Physics Mod 或者 Physics Mod Pro
            if (FabricLoader.getInstance().isModLoaded("physicsmod") || FabricLoader.getInstance().isModLoaded("physicsmodpro"))
                ConfigScreen.buttonWidget = addDrawableChild(ButtonWidget.builder(Text.translatable(getModConfigText("mod_config_button")),
                        (Button) -> MinecraftClient.getInstance().setScreen(new ConfigScreen(this))).dimensions(width / 2 + 5, height / 6 + 144 - 6, 150, 20).build());
                // 如果玩家没有添加了 Physics Mod 或者 Physics Mod Pro
            else {
                ConfigScreen.buttonWidget = addDrawableChild(ButtonWidget.builder(Text.translatable(getModConfigText("mod_config_button")),
                        (Button) -> MinecraftClient.getInstance().setScreen(new ConfigScreen(this))).dimensions(width / 2 - 155, height / 6 + 24 - 6, 310, 20).build());
            }
        }
    }
}
