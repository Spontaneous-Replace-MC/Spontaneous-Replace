package SpontaneousReplace.ConfigScreen;

import SpontaneousReplace.Generic.ConfigData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import static SpontaneousReplace.Generic.ConfigData.getModConfigText;
import static SpontaneousReplace.Generic.ConfigData.getSupportUsButton;

/**
 * <b style="color:FFC800"><font size="+2">ConfigScreen：配置屏幕</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">模组设置屏幕配置，当要设置模组选项时就可以使用此屏幕配置填充</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2022/12/24 ~ 2022/12/26
 */
@Environment(EnvType.CLIENT)
public class ConfigScreen extends Screen {
    public static final Text SR_CONFIG_SCREEN_TEXT = Text.translatable(getModConfigText("mod_config_button"));
    protected Screen parent;
    protected OptionListWidget list;

    /**
     * 提供函数, 所需要使用的所有特性都要在此函数中注册
     */
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        render(matrices, list, mouseX, mouseY, delta);
    }

    protected void render(MatrixStack matrices, OptionListWidget optionButtons, int mouseX, int mouseY, float tickDelta) {
        renderBackground(matrices);
        optionButtons.render(matrices, mouseX, mouseY, tickDelta);
        drawCenteredTextWithShadow(matrices, textRenderer, title, width / 2, 20, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, tickDelta);
    }

    /**
     * 配置函数, 所以要用到的按钮等都需要在此函数中注册
     */
    @Override
    protected void init() {
        super.init();
        // 读取配置文件
        ConfigData.readConfig();
        // 添加黑色透明窗口
        list = new OptionListWidget(client, width, height, 32, height - 32, 25);
        // 添加"支持我们"按钮
        addDrawableChild(getSupportUsButton(this));
        // 添加完成按钮
        addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (Button) -> {
                    // 使用断言消除 setScreen NullPointerException警告
                    assert client != null;
                    client.setScreen(parent);
                })
                .dimensions(width / 2 - 100, height - 26, 200, 20).build());
        // 添加所有配置选项按钮
        for (SimpleOption<?> option : ConfigData.CONFIG_OPTIONS)
            list.addSingleOptionEntry(option);
        addSelectableChild(list);
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
}
