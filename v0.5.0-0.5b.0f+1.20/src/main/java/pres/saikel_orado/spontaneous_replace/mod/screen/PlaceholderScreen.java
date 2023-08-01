package pres.saikel_orado.spontaneous_replace.mod.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import pres.saikel_orado.spontaneous_replace.mod.data.SRData;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.getModConfigText;

/**
 * <b style="color:FFC800"><font size="+2">PlaceholderScreen：占位屏幕</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">占位屏幕配置, 当一个按钮的功能未添加时就能使用此屏幕配置填充</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 6.0
 * | 创建于 < 2022/11/14
 */
@Environment(EnvType.CLIENT)
public class PlaceholderScreen extends Screen {
    protected final Screen parent;
    protected MultilineText messageText;

    /**
     * 提供函数, 所需要使用的所有特性都要在此函数中注册
     */
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        messageText.drawCenterWithShadow(context, width / 2, height / 2 - 4 - 36, 8, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
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
        addDrawableChild(ButtonWidget.builder(Text.translatable(getModConfigText("mod_web_button")),
                        ConfirmLinkScreen.opening(SRData.SR_OFFICIAL_WEB, this, true))
                .dimensions(width / 2 - 145, height / 2 + 12, 140, 20).build());
        // 添加返回按钮
        addDrawableChild(ButtonWidget.builder(ScreenTexts.CANCEL, (button) -> close())
                .dimensions(width / 2 + 5, height / 2 + 12, 140, 20).build());
    }

    /**
     * 返回父屏幕
     */
    @SuppressWarnings("DataFlowIssue")
    @Override
    public void close() {
        client.setScreen(parent);
    }

    /**
     * 屏幕构建
     *
     * @param parent 父窗口
     */
    public PlaceholderScreen(Screen parent) {
        super(Text.of(""));
        this.parent = parent;
    }
}
