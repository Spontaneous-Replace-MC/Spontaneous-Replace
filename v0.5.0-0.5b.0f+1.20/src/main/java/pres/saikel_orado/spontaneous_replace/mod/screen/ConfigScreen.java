package pres.saikel_orado.spontaneous_replace.mod.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.Config.*;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.Config.Update.UPDATE_CONFIG_BUTTON_TEXT;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.getModConfigText;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.getSupportUsButton;

/**
 * <b style="color:FFC800"><font size="+2">ConfigScreen：配置屏幕</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">模组设置屏幕配置，当要设置模组选项时就可以使用此屏幕配置填充</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 6.0
 * | 创建于 2022/12/24 ~ 2022/12/26
 */
@Environment(EnvType.CLIENT)
public class ConfigScreen extends Screen {
    public static final Text SR_CONFIG_SCREEN_TEXT = Text.translatable(getModConfigText("mod_config_button"));
    protected final Screen parent;
    protected OptionListWidget list;

    /**
     * 提供函数, 所需要使用的所有特性都要在此函数中注册
     */
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        render(context, list, mouseX, mouseY, delta);
    }

    protected void render(DrawContext context, OptionListWidget optionButtons, int mouseX, int mouseY, float tickDelta) {
        renderBackground(context);
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
        SRConfigData.Config.setParent(this);
        // 读取配置文件
        SRConfigData.readConfig();
        // 添加黑色透明窗口
        list = new OptionListWidget(client, width, height, 32, height - 32, 25);
        // 添加"支持我们"按钮
        addDrawableChild(getSupportUsButton(this));
        // 添加完成按钮
        addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> close())
                .dimensions(width / 2 - 100, height - 26, 200, 20).build());
        // 添加所有配置选项按钮
        for (SimpleOption<?>[] option : SRConfigData.CONFIG_OPTIONS)
            list.addSingleOptionEntry(option[CONFIG_BUTTON_INDEX]);
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
    public ConfigScreen(Screen parent) {
        super(SR_CONFIG_SCREEN_TEXT);
        this.parent = parent;
    }

    /**
     * 更新配置屏幕
     */
    public static class Update extends Screen {
        protected final Screen parent;
        protected OptionListWidget list;

        /**
         * 提供函数, 所需要使用的所有特性都要在此函数中注册
         */
        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            render(context, list, mouseX, mouseY, delta);
        }

        protected void render(DrawContext context, OptionListWidget optionButtons, int mouseX, int mouseY, float tickDelta) {
            renderBackground(context);
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
            SRConfigData.Config.setParent(parent);
            // 读取配置文件
            SRConfigData.readConfig();
            // 添加黑色透明窗口
            list = new OptionListWidget(client, width, height, 32, height - 32, 25);
            // 添加"支持我们"按钮
            addDrawableChild(getSupportUsButton(this));
            // 添加完成按钮
            addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> close())
                    .dimensions(width / 2 - 100, height - 26, 200, 20).build());
            // 添加所有配置选项按钮
            for (int count = CONFIGS_START; count < SRConfigData.CONFIG_OPTIONS[UPDATE_CONFIGS_INDEX].length; count++)
                list.addSingleOptionEntry(SRConfigData.CONFIG_OPTIONS[UPDATE_CONFIGS_INDEX][count]);
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
        public Update(Screen parent) {
            super(Text.translatable(UPDATE_CONFIG_BUTTON_TEXT));
            this.parent = parent;
        }
    }
}
