package SpontaneousReplace.ConfigScreen;

import SpontaneousReplace.Generic.SRData;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import java.io.IOException;

import static SpontaneousReplace.Generic.ConfigData.getModConfigText;

/**
 * <b style="color:FFC800"><font size="+2">PlaceholderScreen：占位屏幕</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">占位屏幕配置, 当一个按钮的功能未添加时就能使用此屏幕配置填充</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 < 2022/11/14
 */
public class PlaceholderScreen extends Screen {
    protected Screen parent;
    public static ButtonWidget buttonWidget;
    protected MultilineText messageText;

    /**
     * 提供函数, 所需要使用的所有特性都要在此函数中注册
     */
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        messageText.drawCenterWithShadow(matrices, width / 2, height / 2 - 4 - 36, 8, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

    /**
     * 配置函数, 所以要用到的按钮等都需要在此函数中注册
     */
    @Override
    protected void init() {
        super.init();
        // 添加提示消息文本
        messageText = MultilineText.create(textRenderer, Text.translatable(getModConfigText("placeholder_text")), width - 100);
        // 添加官网跳转按钮
        addDrawableChild(ButtonWidget.builder(Text.translatable(getModConfigText("mod_web_button")), (Button) -> {
                    // 使用默认浏览器打开模组官网
                    try {
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + SRData.MOD_OFFICIAL_WEB);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (buttonWidget != null)
                        buttonWidget.visible = true;
                })
                .dimensions(width / 2 - 110, height / 2 + 12, 100, 20).build());
        // 添加返回按钮
        addDrawableChild(ButtonWidget.builder(ScreenTexts.CANCEL, (Button) -> {
                    // 使用断言消除 setScreen NullPointerException警告
                    assert client != null;
                    client.setScreen(parent);
                    if (buttonWidget != null)
                        buttonWidget.visible = true;
                })
                .dimensions(width / 2 + 10, height / 2 + 12, 100, 20).build());
    }

    /**
     * 屏幕构建
     *
     * @param parent 父窗口
     */
    public PlaceholderScreen(Screen parent) {
        super(Text.translatable(null));
        this.parent = parent;
    }
}
