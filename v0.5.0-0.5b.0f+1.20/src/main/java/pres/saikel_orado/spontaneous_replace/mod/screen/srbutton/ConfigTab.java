package pres.saikel_orado.spontaneous_replace.mod.screen.srbutton;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tab.GridScreenTab;
import net.minecraft.client.gui.tab.TabManager;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData;

import java.util.Objects;

import static com.mojang.blaze3d.systems.RenderSystem.disableScissor;
import static net.minecraft.client.gui.screen.world.CreateWorldScreen.LIGHT_DIRT_BACKGROUND_TEXTURE;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.Config.CONFIG_BUTTON_INDEX;
import static pres.saikel_orado.spontaneous_replace.mod.screen.srbutton.SRScreen.SR_BACKGROUND_ID;

/**
 * <b style="color:FFC800"><font size="+2">ConfigTab：自然更替设置选项</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">自然更替模组按钮的设置选项</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 5.0
 * | 创建于 2023/3/17 15:55
 */
@Environment(EnvType.CLIENT)
final class ConfigTab extends GridScreenTab {
    /**
     * 修改了背景显示的列表控件
     */
    @Environment(EnvType.CLIENT)
    public static class ListWidget extends OptionListWidget {
        public ListWidget(MinecraftClient minecraftClient, int i, int j, int k, int l, int m) {
            super(minecraftClient, i, j, k, l, m);
        }

        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            renderBackground(context);
            int scrollbarPositionX = getScrollbarPositionX();
            int posX = scrollbarPositionX + 6;
            {
                RenderSystem.setShaderTexture(0, SR_BACKGROUND_ID);
                RenderSystem.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
                context.drawTexture(SR_BACKGROUND_ID, 0, -37, 0, 0.0F, 0.0F, width, height, width, height);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }

            int rowLeft = getRowLeft();
            int scrollAmount = top + 4 - (int) getScrollAmount();
            enableScissor(context);
            {
                renderHeader(context, rowLeft, scrollAmount);
            }

            renderList(context, mouseX, mouseY, delta);
            disableScissor();
            {
                RenderSystem.setShaderTexture(0, LIGHT_DIRT_BACKGROUND_TEXTURE);
                context.drawTexture(LIGHT_DIRT_BACKGROUND_TEXTURE, left, 0, 0.0F, 0.0F, width, top, 32, 32);
                context.fillGradient(left, top, right, top + 4, -16777216, 0);
            }

            int maxScroll = getMaxScroll();
            if (maxScroll > 0) {
                int pos = (int) ((float) ((bottom - top) * (bottom - top)) / (float) getMaxPosition());
                pos = MathHelper.clamp(pos, 32, bottom - top - 8);
                int o = (int) getScrollAmount() * (bottom - top - pos) / maxScroll + top;
                if (o < top) {
                    o = top;
                }

                context.fill(scrollbarPositionX, top, posX, bottom, -16777216);
                context.fill(scrollbarPositionX, o, posX, o + pos, -8355712);
                context.fill(scrollbarPositionX, o, posX - 1, o + pos - 1, -4144960);
            }

            renderDecorations(context, mouseX, mouseY);
            RenderSystem.disableBlend();
        }
    }

    public static final Text SR_CONFIG_TEXT = Text.translatable("text.spontaneous_replace.mod_config");
    public ListWidget list;

    /**
     * <p>渲染提供函数, 所需要使用的所有特性都要在此函数中注册</p>
     * !!!注意在 Screen 中添加 addSelectableChild() 注册 list
     */
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        list.render(context, mouseX, mouseY, delta);
    }

    /**
     * 配置函数, 所以要用到的按钮等都需要在此函数中注册
     */
    public void init(MinecraftClient client, int width, int height, int top, int itemHeight) {
        // 读取配置文件
        SRConfigData.readConfig();
        list = new ListWidget(client, width, height, top, height - top, itemHeight);
        for (SimpleOption<?>[] option : SRConfigData.CONFIG_OPTIONS)
            list.addSingleOptionEntry(option[CONFIG_BUTTON_INDEX]);
    }

    /**
     * 是否是这个按钮选项卡
     */
    public static boolean isConfigTab(TabManager tabManager) {
        return Objects.requireNonNull(tabManager.getCurrentTab()).getTitle().equals(SR_CONFIG_TEXT);
    }

    /**
     * 选项卡构建
     */
    public ConfigTab() {
        super(SR_CONFIG_TEXT);
    }
}
