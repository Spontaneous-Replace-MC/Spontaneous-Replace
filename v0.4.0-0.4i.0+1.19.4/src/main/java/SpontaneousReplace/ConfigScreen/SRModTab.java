package SpontaneousReplace.ConfigScreen;

import SpontaneousReplace.Generic.ConfigData;
import SpontaneousReplace.Generic.SRData;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.terraformersmc.modmenu.gui.widget.UpdateAvailableBadge;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.tab.GridScreenTab;
import net.minecraft.client.gui.tab.TabManager;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static SpontaneousReplace.ConfigScreen.SRScreen.SR_BACKGROUND_ID;
import static SpontaneousReplace.Generic.ConfigData.language;
import static SpontaneousReplace.Generic.SRData.*;
import static net.minecraft.client.gui.DrawableHelper.drawCenteredTextWithShadow;
import static net.minecraft.client.gui.DrawableHelper.drawTexture;

/**
 * <b style="color:FFC800"><font size="+2">SRModTab：自然更替模组选项</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">自然更替模组按钮的模组选项</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/3/15 23:14
 */
@Environment(EnvType.CLIENT)
public class SRModTab extends GridScreenTab {
    /**
     * 文本列表组件
     */
    @Environment(EnvType.CLIENT)
    public static class TextListWidget extends EntryListWidget<TextListWidget.TextEntry> {

        private final String text;
        private final TextRenderer textRenderer;

        public TextListWidget(MinecraftClient client, int width, int height, int top, int bottom, int entryHeight, String text) {
            super(client, width, height, top, bottom, entryHeight);
            this.text = text;
            textRenderer = client.textRenderer;
        }

        @Override
        public TextEntry getSelectedOrNull() {
            return null;
        }

        @Override
        public int getRowWidth() {
            return width - 10;
        }

        @Override
        protected int getScrollbarPositionX() {
            return width - 6 + left;
        }

        @Override
        public void appendNarrations(NarrationMessageBuilder builder) {
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            clearEntries();
            if (!text.isEmpty()) {
                for (OrderedText line : textRenderer.wrapLines(Text.literal(text), getRowWidth() - 5)) {
                    children().add(new TextEntry(line, this));
                }
            }

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();

            {
                RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
                RenderSystem.setShaderTexture(0, DrawableHelper.OPTIONS_BACKGROUND_TEXTURE);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
                bufferBuilder.vertex(left, bottom, 0.0D).texture(left / 32.0F, (bottom + (int) getScrollAmount()) / 32.0F).color(32, 32, 32, 255).next();
                bufferBuilder.vertex(right, bottom, 0.0D).texture(right / 32.0F, (bottom + (int) getScrollAmount()) / 32.0F).color(32, 32, 32, 255).next();
                bufferBuilder.vertex(right, top, 0.0D).texture(right / 32.0F, (top + (int) getScrollAmount()) / 32.0F).color(32, 32, 32, 255).next();
                bufferBuilder.vertex(left, top, 0.0D).texture(left / 32.0F, (top + (int) getScrollAmount()) / 32.0F).color(32, 32, 32, 255).next();
                tessellator.draw();
            }

            RenderSystem.depthFunc(515);
            RenderSystem.disableDepthTest();
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ZERO, GlStateManager.DstFactor.ONE);
            RenderSystem.setShader(GameRenderer::getPositionColorProgram);

            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
            bufferBuilder.vertex(left, (top + 4), 0.0D).color(0, 0, 0, 0).next();
            bufferBuilder.vertex(right, (top + 4), 0.0D).color(0, 0, 0, 0).next();
            bufferBuilder.vertex(right, top, 0.0D).color(0, 0, 0, 255).next();
            bufferBuilder.vertex(left, top, 0.0D).color(0, 0, 0, 255).next();
            bufferBuilder.vertex(left, bottom, 0.0D).color(0, 0, 0, 255).next();
            bufferBuilder.vertex(right, bottom, 0.0D).color(0, 0, 0, 255).next();
            bufferBuilder.vertex(right, (bottom - 4), 0.0D).color(0, 0, 0, 0).next();
            bufferBuilder.vertex(left, (bottom - 4), 0.0D).color(0, 0, 0, 0).next();
            tessellator.draw();

            renderList(matrices, mouseX, mouseY, delta);
            renderScrollBar(bufferBuilder, tessellator);

            RenderSystem.disableBlend();
        }

        public void renderScrollBar(BufferBuilder bufferBuilder, Tessellator tessellator) {
            int scrollbarStartX = getScrollbarPositionX();
            int scrollbarEndX = scrollbarStartX + 6;
            int maxScroll = getMaxScroll();
            if (maxScroll > 0) {
                int p = (int) ((float) ((bottom - top) * (bottom - top)) / (float) getMaxPosition());
                p = MathHelper.clamp(p, 32, bottom - top - 8);
                int q = (int) getScrollAmount() * (bottom - top - p) / maxScroll + top;
                if (q < top) {
                    q = top;
                }

                RenderSystem.setShader(GameRenderer::getPositionColorProgram);
                bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
                bufferBuilder.vertex(scrollbarStartX, bottom, 0.0D).color(0, 0, 0, 255).next();
                bufferBuilder.vertex(scrollbarEndX, bottom, 0.0D).color(0, 0, 0, 255).next();
                bufferBuilder.vertex(scrollbarEndX, top, 0.0D).color(0, 0, 0, 255).next();
                bufferBuilder.vertex(scrollbarStartX, top, 0.0D).color(0, 0, 0, 255).next();
                bufferBuilder.vertex(scrollbarStartX, q + p, 0.0D).color(128, 128, 128, 255).next();
                bufferBuilder.vertex(scrollbarEndX, q + p, 0.0D).color(128, 128, 128, 255).next();
                bufferBuilder.vertex(scrollbarEndX, q, 0.0D).color(128, 128, 128, 255).next();
                bufferBuilder.vertex(scrollbarStartX, q, 0.0D).color(128, 128, 128, 255).next();
                bufferBuilder.vertex(scrollbarStartX, q + p - 1, 0.0D).color(192, 192, 192, 255).next();
                bufferBuilder.vertex(scrollbarEndX - 1, q + p - 1, 0.0D).color(192, 192, 192, 255).next();
                bufferBuilder.vertex(scrollbarEndX - 1, q, 0.0D).color(192, 192, 192, 255).next();
                bufferBuilder.vertex(scrollbarStartX, q, 0.0D).color(192, 192, 192, 255).next();
                tessellator.draw();
            }
        }

        /**
         * 文本控件
         */
        @Environment(EnvType.CLIENT)
        public class TextEntry extends ElementListWidget.Entry<TextListWidget.TextEntry> {
            private final TextListWidget widget;
            protected OrderedText text;
            protected int indent;
            public boolean updateTextEntry = false;

            public TextEntry(OrderedText text, TextListWidget widget, int indent) {
                this.text = text;
                this.widget = widget;
                this.indent = indent;
            }

            public TextEntry(OrderedText text, TextListWidget widget) {
                this(text, widget, 0);
            }

            public TextEntry setUpdateTextEntry() {
                updateTextEntry = true;
                return this;
            }

            @Override
            public void render(MatrixStack matrices, int index, int y, int x, int itemWidth, int itemHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
                if (widget.top > y || widget.bottom - textRenderer.fontHeight < y) {
                    return;
                }
                if (updateTextEntry) {
                    UpdateAvailableBadge.renderBadge(matrices, x + indent, y);
                    x += 11;
                }
                textRenderer.drawWithShadow(matrices, text, x + indent, y, 0xFFFFFF);
            }

            @Override
            public List<? extends Element> children() {
                return Collections.emptyList();
            }

            @Override
            public List<? extends Selectable> selectableChildren() {
                return Collections.emptyList();
            }
        }
    }

    public static final Text SR_MOD_TEXT = Text.translatable("text.spontaneous_replace.mod");
    public static final Identifier ICON_ID = new Identifier(MOD_ID, "icon.png");
    public static final Text SR_TARGET_TITLE = Text.translatable("title.spontaneous_replace.target");
    public static final Text SR_TARGET_TEXT = Text.translatable("text.spontaneous_replace.target");
    public static final Text SR_LOG_TITLE = Text.translatable("title.spontaneous_replace.log");
    public static final Text MOD_WEB_TEXT = Text.translatable("text.spontaneous_replace.mod_web");
    public static final Text SUPPORT_TEXT = Text.translatable("text.spontaneous_replace.support");
    public static final Text COMMUNITY_TEXT = Text.translatable("text.spontaneous_replace.community");
    public ButtonWidget modWebButton;
    public ButtonWidget supportButton;
    public ButtonWidget communityButton;
    public TextListWidget textListWidget;
    public TextListWidget targetWidget;
    public MinecraftClient client;
    public int width;
    public int height;

    /**
     * <p>渲染提供函数, 所需要使用的所有特性都要在此函数中注册</p>
     * <P>!!!注意在 Screen 中添加 addSelectableChild() 注册 textListWidget 和 targetWidget</P>
     * <p>!!!注意在 Screen 中添加 addDrawableChild() 注册模组主页、官方社群和支持我们按钮</p>
     */
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.setShaderTexture(0, SR_BACKGROUND_ID);
        RenderSystem.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
        drawTexture(matrices, 0, -37, 0, 0.0F, 0.0F, width, height, width, height);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, ICON_ID);
        drawTexture(matrices, width / 60, 24 + 5, 0, 0.0F, 0.0F, 72, 72, 72, 72);
        drawCenteredTextWithShadow(matrices, client.textRenderer, SR_LOG_TITLE, width - width / 4, 24 + 5, 0xFFFFFF);
        drawCenteredTextWithShadow(matrices, client.textRenderer, SR_TARGET_TITLE, width / 4, 24 + 5 + 72 + 5, 0xFFFFFF);
        textListWidget.render(matrices, mouseX, mouseY, delta);
        targetWidget.render(matrices, mouseX, mouseY, delta);
    }

    /**
     * <p>配置函数, 所以要用到的按钮等都需要在此函数中注册</p>
     */
    public void init(MinecraftClient client, int width, int height, int itemHeight) {
        this.client = client;
        this.width = width;
        this.height = height;
        int buttonX = width / 30 + 72;
        modWebButton = ButtonWidget.builder(MOD_WEB_TEXT, (Button) -> {
            // 使用默认浏览器打开模组官网
            try {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + SRData.MOD_OFFICIAL_WEB);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).dimensions(buttonX, 24 + 5, width / 2 - width / 60 - buttonX, 20).build();
        supportButton = ButtonWidget.builder(SUPPORT_TEXT, (Button) -> {
            // 使用默认浏览器打开模组官网
            try {
                // 判断用户是否使用简体中文(在中国大陆)
                if (ConfigData.isZhCN)
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + MOD_SUPPORT_WEB_OF_CN);
                else
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + MOD_SUPPORT_WEB);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).dimensions(buttonX, ((24 + 5) + (24 + 5 + 72 - 20)) / 2, width / 2 - width / 60 - buttonX, 20).build();
        communityButton = ButtonWidget.builder(COMMUNITY_TEXT, (Button) -> {
            // 使用默认浏览器打开模组官网
            try {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + MOD_COMMUNITY_WEB);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).dimensions(buttonX, 24 + 5 + 72 - 20, width / 2 - width / 60 - buttonX, 20).build();
        modWebButton.visible = supportButton.visible = communityButton.visible = false;
        // 读取更新日志
        InputStream inputStream;
        if (language.equals("zh_cn") || language.equals("zh_hk") || language.equals("zh_tw"))
            inputStream = getClass().getResourceAsStream("/assets/" + MOD_ID + "/log/zh_cn.txt");
        else inputStream = getClass().getResourceAsStream("/assets/" + MOD_ID + "/log/en_us.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8));
        StringBuilder log = new StringBuilder();
        try {
            for (int c; (c = bufferedReader.read()) != -1; )
                log.append((char) c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log = new StringBuilder(log.toString().replaceAll("\r\n", "\n"));
        if (language.equals("zh_cn") || language.equals("zh_hk") || language.equals("zh_tw"))
            log = new StringBuilder(log.toString().replaceAll("\t", "—"));
        else log = new StringBuilder(log.toString().replaceAll("\t", "  "));
        // 注册文字列表控件
        textListWidget = new TextListWidget(client, width / 2 - width / 30,
                height, 24 + 5 + 10, height - 37 - 5, itemHeight, log.toString());
        textListWidget.setLeftPos(width / 2 + width / 60);
        targetWidget = new TextListWidget(client, width / 2 - width / 30,
                height, 24 + 5 + 72 + 15, height - 37 - 5, itemHeight, SR_TARGET_TEXT.getString());
        targetWidget.setLeftPos(width / 60);
    }

    /**
     * 设置按钮可见
     */
    public boolean setButtonVisible(boolean flag) {
        return modWebButton.visible = supportButton.visible = communityButton.visible = flag;
    }

    /**
     * 是否是这个按钮选项卡
     */
    public static boolean isSRModTab(TabManager tabManager) {
        return Objects.requireNonNull(tabManager.getCurrentTab()).getTitle().equals(SR_MOD_TEXT);
    }

    /**
     * 选项卡构建
     */
    public SRModTab() {
        super(SR_MOD_TEXT);
    }
}
