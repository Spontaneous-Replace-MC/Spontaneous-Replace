package pres.saikel_orado.spontaneous_replace.mod.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.getSupportUsButton;

/**
 * <b style="color:FFC800"><font size="+2">WorldConfigScreen：世界配置屏幕</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">模组世界设置屏幕配置，当要设置模组选项时就可以使用此屏幕配置填充</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 5.0
 * | 创建于 2023/2/14 11:26
 */
@Environment(EnvType.CLIENT)
public class WorldConfigScreen extends Screen {
    protected final Screen parent;
    protected OptionListWidget list;

    /**
     * 提供函数, 所需要使用的所有特性都要在此函数中注册
     */
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.render(context, list, mouseX, mouseY, delta);
    }

    protected void render(DrawContext context, OptionListWidget optionButtons, int mouseX, int mouseY, float tickDelta) {
        this.renderBackground(context);
        optionButtons.render(context, mouseX, mouseY, tickDelta);
        context.drawCenteredTextWithShadow(textRenderer, title, width / 2, 20, 0xFFFFFF);
        super.render(context, mouseX, mouseY, tickDelta);
    }

    /**
     * 配置函数, 所以要用到的按钮等都需要在此函数中注册
     */
    @Override
    protected void init() {
        super.init();
        // 添加黑色透明窗口
        list = new OptionListWidget(client, width, height, 32, height - 32, 25);
        // 添加"支持我们"按钮
        addDrawableChild(getSupportUsButton(this));
        // 添加完成按钮
        addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> close())
                .dimensions(width / 2 - 100, height - 26, 200, 20).build());
        // 添加所有配置选项按钮
        list.addSingleOptionEntry(SRConfigData.CONFIG_OPTIONS[2][0]);
        addSelectableChild(list);
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
    public WorldConfigScreen(Screen parent) {
        super(Text.translatable(SRConfigData.getModConfigText("world_option_button")));
        this.parent = parent;
    }
}