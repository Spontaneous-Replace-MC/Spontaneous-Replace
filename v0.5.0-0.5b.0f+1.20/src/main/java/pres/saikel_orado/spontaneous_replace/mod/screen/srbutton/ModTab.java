package pres.saikel_orado.spontaneous_replace.mod.screen.srbutton;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.tab.GridScreenTab;
import net.minecraft.client.gui.tab.TabManager;
import net.minecraft.client.gui.widget.ButtonWidget;
import pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData;
import pres.saikel_orado.spontaneous_replace.mod.util.SRUpdateUtil;

import java.util.Objects;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.Config.getParent;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.*;
import static pres.saikel_orado.spontaneous_replace.mod.screen.srbutton.SRScreen.*;

/**
 * <b style="color:FFC800"><font size="+2">ModTab：自然更替模组选项</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">自然更替模组按钮的模组选项</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 6.0
 * | 创建于 2023/3/15 23:14
 */
@Environment(EnvType.CLIENT)
final class ModTab extends GridScreenTab {
    public ButtonWidget modWebButton;
    public ButtonWidget supportButton;
    public ButtonWidget communityButton;
    public SRUpdateUtil.TextListWidget changelogWidget;
    public SRUpdateUtil.TextListWidget targetWidget;
    public MinecraftClient client;
    public int width;
    public int height;

    /**
     * <p>渲染提供函数, 所需要使用的所有特性都要在此函数中注册</p>
     * <P>!!!注意在 Screen 中添加 addSelectableChild() 注册 textListWidget 和 targetWidget</P>
     * <p>!!!注意在 Screen 中添加 addDrawableChild() 注册模组主页、官方社群和支持我们按钮</p>
     */
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        RenderSystem.setShaderTexture(0, SR_BACKGROUND_ID);
        RenderSystem.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
        context.drawTexture(SR_BACKGROUND_ID, 0, -37, 0, 0.0F, 0.0F, width, height, width, height);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, SR_ICON_ID);
        context.drawTexture(SR_ICON_ID, width / 60, 24 + 5, 0, 0.0F, 0.0F, 72, 72, 72, 72);
        context.drawCenteredTextWithShadow(client.textRenderer, SR_LOG_TITLE, width - width / 4, 24 + 5, 0xFFFFFF);
        context.drawCenteredTextWithShadow(client.textRenderer, SR_TARGET_TITLE, width / 4, 24 + 5 + 72 + 5, 0xFFFFFF);
        changelogWidget.render(context, mouseX, mouseY, delta);
        targetWidget.render(context, mouseX, mouseY, delta);
    }

    /**
     * <p>配置函数, 所以要用到的按钮等都需要在此函数中注册</p>
     */
    public void init(MinecraftClient client, int width, int height, int itemHeight) {
        this.client = client;
        this.width = width;
        this.height = height;
        int buttonX = width / 30 + 72;
        modWebButton = ButtonWidget.builder(MOD_WEB_TEXT,
                        ConfirmLinkScreen.opening(SR_OFFICIAL_WEB, getParent(), true))
                .dimensions(buttonX, 24 + 5, width / 2 - width / 60 - buttonX, 20).build();
        supportButton = ButtonWidget.builder(SUPPORT_TEXT, SRConfigData.isZhCN ?
                        ConfirmLinkScreen.opening(SR_SUPPORT_WEB_OF_CN, getParent(), true)
                        : ConfirmLinkScreen.opening(SR_SUPPORT_WEB, getParent(), true))
                .dimensions(buttonX, ((24 + 5) + (24 + 5 + 72 - 20)) / 2, width / 2 - width / 60 - buttonX, 20).build();
        communityButton = ButtonWidget.builder(COMMUNITY_TEXT,
                        ConfirmLinkScreen.opening(SR_COMMUNITY_WEB, getParent(), true))
                .dimensions(buttonX, 24 + 5 + 72 - 20, width / 2 - width / 60 - buttonX, 20).build();
        modWebButton.visible = supportButton.visible = communityButton.visible = false;
        changelogWidget = SRUpdateUtil.readChangelog(client, width / 2 - width / 30, height, 24 + 5 + 10, height - 37 - 5, width / 2 + width / 60, itemHeight);
        targetWidget = new SRUpdateUtil.TextListWidget(client, width / 2 - width / 30,
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
    public static boolean isModTab(TabManager tabManager) {
        return Objects.requireNonNull(tabManager.getCurrentTab()).getTitle().equals(SR_MOD_TEXT);
    }

    /**
     * 选项卡构建
     */
    public ModTab() {
        super(SR_MOD_TEXT);
    }
}
