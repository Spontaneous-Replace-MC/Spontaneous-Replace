package SpontaneousReplace.ConfigScreen;

import SpontaneousReplace.Generic.ConfigData;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.tab.GridScreenTab;
import net.minecraft.client.gui.tab.TabManager;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import java.util.Objects;

import static SpontaneousReplace.ConfigScreen.SRScreen.SR_BACKGROUND_ID;

/**
 * <b style="color:FFC800"><font size="+2">SRConfigTab：自然更替设置选项</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">自然更替模组按钮的设置选项</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/3/17 15:55
 */
@Environment(EnvType.CLIENT)
public class SRConfigTab extends GridScreenTab {
    /**
     * 修改了背景显示的列表控件
     */
    @Environment(EnvType.CLIENT)
    public static class ListWidget extends OptionListWidget {
        public ListWidget(MinecraftClient minecraftClient, int i, int j, int k, int l, int m) {
            super(minecraftClient, i, j, k, l, m);
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            renderBackground(matrices);
            int scrollbarPositionX = getScrollbarPositionX();
            int posX = scrollbarPositionX + 6;
            {
                RenderSystem.setShaderTexture(0, SR_BACKGROUND_ID);
                RenderSystem.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
                drawTexture(matrices, 0, -37, 0, 0.0F, 0.0F, width, height, width, height);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }

            int rowLeft = getRowLeft();
            int scrollAmount = top + 4 - (int) getScrollAmount();
            enableScissor();
            {
                renderHeader(matrices, rowLeft, scrollAmount);
            }

            renderList(matrices, mouseX, mouseY, delta);
            disableScissor();
            {
                RenderSystem.setShaderTexture(0, DrawableHelper.LIGHT_DIRT_BACKGROUND_TEXTURE);
                drawTexture(matrices, left, 0, 0.0F, 0.0F, width, top, 32, 32);
                fillGradient(matrices, left, top, right, top + 4, -16777216, 0);
            }

            int maxScroll = getMaxScroll();
            if (maxScroll > 0) {
                int pos = (int) ((float) ((bottom - top) * (bottom - top)) / (float) getMaxPosition());
                pos = MathHelper.clamp(pos, 32, bottom - top - 8);
                int o = (int) getScrollAmount() * (bottom - top - pos) / maxScroll + top;
                if (o < top) {
                    o = top;
                }

                fill(matrices, scrollbarPositionX, top, posX, bottom, -16777216);
                fill(matrices, scrollbarPositionX, o, posX, o + pos, -8355712);
                fill(matrices, scrollbarPositionX, o, posX - 1, o + pos - 1, -4144960);
            }

            renderDecorations(matrices, mouseX, mouseY);
            RenderSystem.disableBlend();
        }
    }

    public static final Text SR_CONFIG_TEXT = Text.translatable("text.spontaneous_replace.mod_config");
    public ListWidget list;

    /**
     * <p>渲染提供函数, 所需要使用的所有特性都要在此函数中注册</p>
     * !!!注意在 Screen 中添加 addSelectableChild() 注册 list
     */
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        list.render(matrices, mouseX, mouseY, delta);
    }

    /**
     * 配置函数, 所以要用到的按钮等都需要在此函数中注册
     */
    public void init(MinecraftClient client, int width, int height, int top, int itemHeight) {
        // 读取配置文件
        ConfigData.readConfig();
        list = new ListWidget(client, width, height, top, height - top, itemHeight);
        for (SimpleOption<?> option : ConfigData.CONFIG_OPTIONS)
            list.addSingleOptionEntry(option);
    }

    /**
     * 是否是这个按钮选项卡
     */
    public static boolean isSRConfigTab(TabManager tabManager) {
        return Objects.requireNonNull(tabManager.getCurrentTab()).getTitle().equals(SR_CONFIG_TEXT);
    }

    /**
     * 选项卡构建
     */
    public SRConfigTab() {
        super(SR_CONFIG_TEXT);
    }
}
