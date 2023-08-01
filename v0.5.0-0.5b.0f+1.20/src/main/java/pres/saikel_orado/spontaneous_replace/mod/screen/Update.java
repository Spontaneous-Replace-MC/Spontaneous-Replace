package pres.saikel_orado.spontaneous_replace.mod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData;
import pres.saikel_orado.spontaneous_replace.mod.util.SRUpdateUtil;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static net.minecraft.screen.ScreenTexts.composeGenericOptionText;
import static net.minecraft.util.Util.OperatingSystem.WINDOWS;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.Config.Update.*;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.Config.getParent;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.getModConfigText;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.writeConfig;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.*;
import static pres.saikel_orado.spontaneous_replace.mod.screen.srbutton.SRScreen.*;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRUpdateUtil.*;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRUtil.getSeconds;

/**
 * <b style="color:FFC800"><font size="+2">Update：更新屏幕</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">用于显示更新日志或更新提示</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 5.0
 * | 创建于 2023/6/6 13:30
 */
@Environment(EnvType.CLIENT)
public enum Update {
    ;

    /**
     * 设置更新屏幕
     */
    public static void setUpdateScreen(Screen parent) {
        if (getShowScreen()) {
            switch (getUpdateState()) {
                case NEW_MC_VER -> MinecraftClient.getInstance().setScreen(new NewMCVerNotificationScreen(parent));
                case THIS_MC_VER -> MinecraftClient.getInstance().setScreen(new ThisMCVerNotificationScreen(parent));
                case MOD_LOG -> {
                    if (showChangelog) MinecraftClient.getInstance().setScreen(new ShowChangelogScreen(parent));
                    else UpdateToast.setToast(new ShowChangelogToast());
                }
                case STOP_UPDATE -> {
                    if (stopUpdatingWarning)
                        MinecraftClient.getInstance().setScreen(new StopUpdateWarningScreen(parent));
                    else UpdateToast.setToast(new StopUpdateWarningToast());
                }
                case UPDATE_FAIL -> {
                    if (updateSystemFailWarning)
                        MinecraftClient.getInstance().setScreen(new UpdateFailWarningScreen(parent));
                    else UpdateToast.setToast(new UpdateFailWarningToast());
                }
            }
        }
    }

    /**
     * 设置更新提示
     */
    public static void setUpdateToast() {
        if (getShowScreen()) {
            switch (getUpdateState()) {
                case NEW_MC_VER -> UpdateToast.setToast(new NewMCVerNotificationToast());
                case THIS_MC_VER -> UpdateToast.setToast(new ThisMCVerNotificationToast());
                case MOD_LOG -> UpdateToast.setToast(new ShowChangelogToast());
                case STOP_UPDATE -> UpdateToast.setToast(new StopUpdateWarningToast());
                case UPDATE_FAIL -> UpdateToast.setToast(new UpdateFailWarningToast());
            }
        }
    }

    /**
     * 新 MC 版本模组更新提示界面
     */
    public static class NewMCVerNotificationScreen extends UpdateScreen {
        protected final int[] transColor = {0xFF, 0, 0};
        protected MultilineText autoDownloadMessage;
        protected MultilineText autoUpdateMessage;
        protected boolean updating;

        /**
         * 构造更新屏幕类
         */
        public NewMCVerNotificationScreen(Screen parent) {
            super(parent, Text.translatable("title.spontaneous_replace.new_mc_ver_update_notification", getUpdateMinecraftVersion())
                    .setStyle(Style.EMPTY.withBold(true).withColor(SR_THEME_COLOR)));
        }

        /**
         * 配置函数, 所以要用到的按钮等都需要在此函数中注册
         */
        @Override
        protected void init() {
            // 赞助按钮、官网按钮、关闭更新按钮、关闭新MC更新按钮、立即更新按钮、暂时不用按钮
            super.init();
            // 添加提示消息文本
            String updateModVersion = getUpdateModVersion().substring(getUpdateModVersion().indexOf("-") + 1);
            messageText = MultilineText.create(textRenderer, Text.translatable("text.spontaneous_replace.mod_update_notification", updateModVersion), screenWidth - 6);
            autoDownloadMessage = MultilineText.create(textRenderer, Text.translatable("text.spontaneous_replace.auto_download", updateModVersion), screenWidth - 6);
            autoUpdateMessage = MultilineText.create(textRenderer, Text.translatable("text.spontaneous_replace.auto_update", updateModVersion), screenWidth - 6);
            int fullButtonWidth = screenWidth - 6;
            int buttonHeight = 20;
            int buttonSpacing = buttonHeight + 3;
            int fullButtonX = (width - (screenWidth - 6)) / 2;
            int buttonY = (height - (height - screenHeight) / 2);
            int halfButtonWidth = fullButtonWidth / 2 - 1;
            int halfButtonX = fullButtonX + halfButtonWidth + 2;
            // 添加按钮
            if (getUpdatingFail()) {
                clearChildren();
                addDrawableChild(ButtonWidget.builder(Text.translatable("menu.returnToGame"), (button) -> {
                            resetCanCheckUpdate();
                            close();
                        })
                        .dimensions(fullButtonX, buttonY - buttonSpacing, fullButtonWidth, buttonHeight).build());
            } else {
                if (!updating) {
                    addDrawableChild(ButtonWidget.builder(SUPPORT_TEXT, SRConfigData.isZhCN ?
                                    ConfirmLinkScreen.opening(SR_SUPPORT_WEB_OF_CN, this, true)
                                    : ConfirmLinkScreen.opening(SR_SUPPORT_WEB, this, true))
                            .dimensions(fullButtonX, buttonY - buttonSpacing * 4, halfButtonWidth, buttonHeight).build());
                    addDrawableChild(ButtonWidget.builder(MOD_WEB_TEXT,
                                    ConfirmLinkScreen.opening(SR_OFFICIAL_WEB, getParent(), true))
                            .dimensions(halfButtonX, buttonY - buttonSpacing * 4, halfButtonWidth, buttonHeight).build());
                    addDrawableChild(ButtonWidget.builder(Text.of(""), (button) -> {
                                // 使用断言消除 setScreen NullPointerException警告
                                assert client != null;
                                client.setScreen(new ConfigScreen.Update(this));
                            })
                            .dimensions(fullButtonX, buttonY - buttonSpacing * 3, fullButtonWidth, buttonHeight).build());
                    ButtonWidget test;
                    addDrawableChild(test = ButtonWidget.builder(Text.of(""), (button) -> {
                                if (updateMode == (Util.getOperatingSystem().equals(WINDOWS) ? 2 : 1))
                                    updateMode = 0;
                                else updateMode++;
                                writeConfig();
                            })
                            .dimensions(fullButtonX, buttonY - buttonSpacing * 2, fullButtonWidth, buttonHeight).build());
                    test.active = false;
                    addDrawableChild(updateButton = ButtonWidget.builder(UPDATE_TEXT.copy().setStyle(Style.EMPTY.withBold(true)), (button) -> {
                                clearChildren();
                                int tempUpdateMode = updateMode;
                                updateMode = 0;
                                updating = updateMod(getUpdateLink());
                                updateMode = tempUpdateMode;
                                close();
                            })
                            .dimensions(fullButtonX, buttonY - buttonSpacing, halfButtonWidth, buttonHeight).build());
                    addDrawableChild(ButtonWidget.builder(RETURN_TEXT.copy().formatted(Formatting.GRAY), (button) -> {
                                resetCanCheckUpdate();
                                close();
                            })
                            .dimensions(halfButtonX, buttonY - buttonSpacing, halfButtonWidth, buttonHeight).build());
                }
            }
        }

        /**
         * 每刻修改颜色循环以达到在任何界面的循环速度一致
         */
        @Override
        public void tick() {
            super.tick();
            // 修改循环按钮颜色
            if (transColor[0] == 0xFF && transColor[2] == 0 && transColor[1] != 0xFF) transColor[1] += 17;
            else if (transColor[1] == 0xFF && transColor[2] == 0 && transColor[0] != 0) transColor[0] -= 17;
            else if (transColor[0] == 0 && transColor[1] == 0xFF && transColor[2] != 0xFF) transColor[2] += 17;
            else if (transColor[0] == 0 && transColor[2] == 0xFF && transColor[1] != 0) transColor[1] -= 17;
            else if (transColor[1] == 0 && transColor[2] == 0xFF && transColor[0] != 0xFF) transColor[0] += 17;
            else transColor[2] -= 17;
        }

        /**
         * 渲染提供函数, 所需要使用的所有特性都要在此函数中注册
         */
        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            super.render(context, mouseX, mouseY, delta);
            int buttonHeight = 20;
            if (getUpdatingFail()) {
                init();
                context.drawCenteredTextWithShadow(textRenderer,
                        UPDATING_FAIL_TEXT,
                        width / 2,
                        (height - screenHeight) / 2 + 20 + 72 + (height - (height - screenHeight + 20 + 72) - buttonHeight - 3) / 2 - 6,
                        0xFFFFFF);
            } else {
                if (!updating) {
                    // 渲染提示消息
                    messageText.drawWithShadow(context, (width - screenWidth) / 2 + 3, height / 2 - 9, 10, 0xFFFFFF);
                    // 修改循环按钮颜色
                    int color = transColor[0] << 16 | transColor[1] << 8 | transColor[2];
                    updateButton.setMessage(UPDATE_TEXT.copy().setStyle(Style.EMPTY.withBold(true).withColor(color)));
                    // 更新配置按钮文本
                    int buttonSpacing = buttonHeight + 3;
                    int buttonY = (height - (height - screenHeight) / 2);
                    Text updateConfigButtonText = composeGenericOptionText(Text.translatable(UPDATE_CONFIG_BUTTON_TEXT), Text.of(""));
                    context.drawTextWithShadow(textRenderer, updateConfigButtonText,
                            (width - (textRenderer.getWidth(updateConfigButtonText))) / 2,
                            buttonY - buttonSpacing * 3 + 6, 0xFFFFFF);
                    // 更新方式按钮文本
                    Text updateModeOptionText = Text.translatable(getModConfigText("update_mode.manual_download"));
                    Text updateModeText = composeGenericOptionText(Text.translatable(getModConfigText("update_mode")), updateModeOptionText)
                            .setStyle(Style.EMPTY.withColor(Formatting.GRAY));
                    context.drawTextWithShadow(textRenderer, updateModeText,
                            (width - (textRenderer.getWidth(updateModeText))) / 2,
                            buttonY - buttonSpacing * 2 + 6, 0xFFFFFF);
                }
            }
        }
    }

    /**
     * 此 MC 版本模组更新提示界面
     */
    public static class ThisMCVerNotificationScreen extends UpdateScreen {
        protected final int[] transColor = {0xFF, 0, 0};
        protected MultilineText autoDownloadMessage;
        protected MultilineText autoUpdateMessage;
        protected boolean updating;

        /**
         * 构造更新屏幕类
         */
        public ThisMCVerNotificationScreen(Screen parent) {
            super(parent, Text.translatable("title.spontaneous_replace.this_mc_ver_update_notification")
                    .setStyle(Style.EMPTY.withBold(true).withColor(SR_THEME_COLOR)));
        }

        /**
         * 配置函数, 所以要用到的按钮等都需要在此函数中注册
         */
        @Override
        protected void init() {
            // 赞助按钮、官网按钮、关闭更新按钮、关闭新MC更新按钮、立即更新按钮、暂时不用按钮
            super.init();
            // 添加提示消息文本
            String updateModVersion = getUpdateModVersion().substring(getUpdateModVersion().indexOf("-") + 1);
            messageText = MultilineText.create(textRenderer, Text.translatable("text.spontaneous_replace.mod_update_notification", updateModVersion), screenWidth - 6);
            autoDownloadMessage = MultilineText.create(textRenderer, Text.translatable("text.spontaneous_replace.auto_download", updateModVersion), screenWidth - 6);
            autoUpdateMessage = MultilineText.create(textRenderer, Text.translatable("text.spontaneous_replace.auto_update", updateModVersion), screenWidth - 6);
            int fullButtonWidth = screenWidth - 6;
            int buttonHeight = 20;
            int buttonSpacing = buttonHeight + 3;
            int fullButtonX = (width - (screenWidth - 6)) / 2;
            int buttonY = (height - (height - screenHeight) / 2);
            int halfButtonWidth = fullButtonWidth / 2 - 1;
            int halfButtonX = fullButtonX + halfButtonWidth + 2;
            // 添加按钮
            if (getUpdatingFail()) {
                clearChildren();
                addDrawableChild(ButtonWidget.builder(Text.translatable("menu.returnToGame"), (button) -> {
                            resetCanCheckUpdate();
                            close();
                        })
                        .dimensions(fullButtonX, buttonY - buttonSpacing, fullButtonWidth, buttonHeight).build());
            } else {
                if (!updating) {
                    addDrawableChild(ButtonWidget.builder(SUPPORT_TEXT, SRConfigData.isZhCN ?
                                    ConfirmLinkScreen.opening(SR_SUPPORT_WEB_OF_CN, this, true)
                                    : ConfirmLinkScreen.opening(SR_SUPPORT_WEB, this, true))
                            .dimensions(fullButtonX, buttonY - buttonSpacing * 4, halfButtonWidth, buttonHeight).build());
                    addDrawableChild(ButtonWidget.builder(COMMUNITY_TEXT,
                                    ConfirmLinkScreen.opening(SR_COMMUNITY_WEB, getParent(), true))
                            .dimensions(halfButtonX, buttonY - buttonSpacing * 4, halfButtonWidth, buttonHeight).build());
                    addDrawableChild(ButtonWidget.builder(Text.of(""), (button) -> {
                                // 使用断言消除 setScreen NullPointerException警告
                                assert client != null;
                                client.setScreen(new ConfigScreen.Update(this));
                            })
                            .dimensions(fullButtonX, buttonY - buttonSpacing * 3, fullButtonWidth, buttonHeight).build());
                    addDrawableChild(ButtonWidget.builder(Text.of(""), (button) -> {
                                if (updateMode == (Util.getOperatingSystem().equals(WINDOWS) ? 2 : 1))
                                    updateMode = 0;
                                else updateMode++;
                                writeConfig();
                            })
                            .dimensions(fullButtonX, buttonY - buttonSpacing * 2, fullButtonWidth, buttonHeight).build());
                    addDrawableChild(updateButton = ButtonWidget.builder(UPDATE_TEXT.copy().setStyle(Style.EMPTY.withBold(true)), (button) -> {
                                clearChildren();
                                updating = updateMod(getUpdateLink());
                                if (updateMode == 0) close();
                            })
                            .dimensions(fullButtonX, buttonY - buttonSpacing, halfButtonWidth, buttonHeight).build());
                    addDrawableChild(ButtonWidget.builder(RETURN_TEXT.copy().formatted(Formatting.GRAY), (button) -> {
                                resetCanCheckUpdate();
                                close();
                            })
                            .dimensions(halfButtonX, buttonY - buttonSpacing, halfButtonWidth, buttonHeight).build());
                } else if (getUpdateProgress() == 100) {
                    if (updateMode == 1) {
                        addDrawableChild(ButtonWidget.builder(AUTO_DOWNLOAD_DONE_TEXT, (button) -> {
                                    Util.getOperatingSystem().open(new File(MODS_PATH.toString()));
                                    Objects.requireNonNull(client).scheduleStop();
                                })
                                .dimensions(fullButtonX, buttonY - buttonSpacing * 2, fullButtonWidth, buttonHeight).build());
                        addDrawableChild(ButtonWidget.builder(RETURN_TEXT.copy().formatted(Formatting.GRAY), (button) -> {
                                    resetCanCheckUpdate();
                                    close();
                                })
                                .dimensions(fullButtonX, buttonY - buttonSpacing, fullButtonWidth, buttonHeight).build());
                    } else
                        addDrawableChild(ButtonWidget.builder(AUTO_UPDATE_DONE_TEXT, (button) -> {
                                    // 使用断言消除 setScreen NullPointerException警告
                                    try {
                                        Runtime.getRuntime().exec("cmd /A /C start \"\" /D \""
                                                + MODS_PATH + "\" \"" + BAT_NAME + "\" CHCP 65001 ");
                                        assert client != null;
                                        client.scheduleStop();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                                .dimensions(fullButtonX, (int) (buttonY - buttonSpacing * 1.5), fullButtonWidth, buttonHeight).build());
                }
            }
        }

        /**
         * 每刻修改颜色循环以达到在任何界面的循环速度一致
         */
        @Override
        public void tick() {
            super.tick();
            // 修改循环按钮颜色
            if (transColor[0] == 0xFF && transColor[2] == 0 && transColor[1] != 0xFF) transColor[1] += 17;
            else if (transColor[1] == 0xFF && transColor[2] == 0 && transColor[0] != 0) transColor[0] -= 17;
            else if (transColor[0] == 0 && transColor[1] == 0xFF && transColor[2] != 0xFF) transColor[2] += 17;
            else if (transColor[0] == 0 && transColor[2] == 0xFF && transColor[1] != 0) transColor[1] -= 17;
            else if (transColor[1] == 0 && transColor[2] == 0xFF && transColor[0] != 0xFF) transColor[0] += 17;
            else transColor[2] -= 17;
        }

        /**
         * 渲染提供函数, 所需要使用的所有特性都要在此函数中注册
         */
        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            super.render(context, mouseX, mouseY, delta);
            int buttonHeight = 20;
            if (getUpdatingFail()) {
                init();
                context.drawCenteredTextWithShadow(textRenderer,
                        UPDATING_FAIL_TEXT,
                        width / 2,
                        (height - screenHeight) / 2 + 20 + 72 + (height - (height - screenHeight + 20 + 72) - buttonHeight - 3) / 2 - 6,
                        0xFFFFFF);
            } else {
                if (!updating) {
                    // 渲染提示消息
                    messageText.drawWithShadow(context, (width - screenWidth) / 2 + 3, height / 2 - 9, 10, 0xFFFFFF);
                    // 修改循环按钮颜色
                    int color = transColor[0] << 16 | transColor[1] << 8 | transColor[2];
                    updateButton.setMessage(UPDATE_TEXT.copy().setStyle(Style.EMPTY.withBold(true).withColor(color)));
                    // 更新配置按钮文本
                    int buttonSpacing = buttonHeight + 3;
                    int buttonY = (height - (height - screenHeight) / 2);
                    Text updateConfigButtonText = composeGenericOptionText(Text.translatable(UPDATE_CONFIG_BUTTON_TEXT), Text.of(""));
                    context.drawTextWithShadow(textRenderer, updateConfigButtonText,
                            (width - (textRenderer.getWidth(updateConfigButtonText))) / 2,
                            buttonY - buttonSpacing * 3 + 6, 0xFFFFFF);
                    // 更新方式按钮文本
                    Text updateModeOptionText;
                    switch (updateMode) {
                        case 0 ->
                                updateModeOptionText = Text.translatable(getModConfigText("update_mode.manual_download"));
                        case 1 ->
                                updateModeOptionText = Text.translatable(getModConfigText("update_mode.auto_download"));
                        case 2 -> updateModeOptionText = Text.translatable(getModConfigText("update_mode.auto_update"));
                        default -> updateModeOptionText = Text.literal(Integer.toString(updateMode));
                    }
                    Text updateModeText = composeGenericOptionText(Text.translatable(getModConfigText("update_mode")), updateModeOptionText);
                    context.drawTextWithShadow(textRenderer, updateModeText,
                            (width - (textRenderer.getWidth(updateModeText))) / 2,
                            buttonY - buttonSpacing * 2 + 6, 0xFFFFFF);
                } else {
                    if (getUpdateProgress() < 100) {
                        context.drawCenteredTextWithShadow(textRenderer,
                                Text.translatable("text.spontaneous_replace.updating", String.format("%.2f", getUpdateProgress())),
                                width / 2,
                                (height - screenHeight) / 2 + 20 + 72 + (height - (height - screenHeight + 20 + 72)) / 2 - 6,
                                0xFFFFFF);
                    } else {
                        init();
                        if (updateMode == 1) autoDownloadMessage.drawCenterWithShadow(context, width / 2,
                                (height - screenHeight) / 2 + 20 + 72 + (height - (height - screenHeight + 20 + 72) - buttonHeight * 2 - 3) / 2 - 12, 10, 0xFFFFFF);
                        else autoUpdateMessage.drawCenterWithShadow(context, width / 2,
                                (int) ((height - screenHeight) / 2 + 20 + 72 + (height - (height - screenHeight + 20 + 72) - buttonHeight * 1.5) / 2 - 12), 10, 0xFFFFFF);

                    }
                }
            }
        }
    }

    /**
     * 模组日志展示界面
     */
    public static class ShowChangelogScreen extends UpdateScreen {
        protected SRUpdateUtil.TextListWidget changelogWidget;

        /**
         * 构造更新屏幕类
         */
        public ShowChangelogScreen(Screen parent) {
            super(parent, Text.translatable("title.spontaneous_replace.log")
                    .setStyle(Style.EMPTY.withBold(true).withColor(SR_THEME_COLOR)));
        }

        /**
         * 配置函数, 所以要用到的按钮等都需要在此函数中注册
         */
        @Override
        protected void init() {
            // 赞助按钮、官网按钮、关闭更新按钮、关闭新MC更新按钮、立即更新按钮、暂时不用按钮
            super.init();
            // 添加提示消息文本
            int fullButtonWidth = screenWidth - 6;
            int buttonHeight = 20;
            int buttonSpacing = buttonHeight + 3;
            int fullButtonX = (width - (screenWidth - 6)) / 2;
            int buttonY = (height - (height - screenHeight) / 2);
            int halfButtonWidth = fullButtonWidth / 2 - 1;
            int halfButtonX = fullButtonX + halfButtonWidth + 2;
            addSelectableChild(changelogWidget = SRUpdateUtil.readChangelog(client,
                    screenWidth - 6, screenHeight - 6,
                    (height - screenHeight) / 2 + 20 + 72 + buttonSpacing * 2 - buttonHeight,
                    buttonY - buttonSpacing - 3,
                    fullButtonX, 12));
            // 添加按钮
            addDrawableChild(ButtonWidget.builder(SUPPORT_TEXT, SRConfigData.isZhCN ?
                            ConfirmLinkScreen.opening(SR_SUPPORT_WEB_OF_CN, this, true)
                            : ConfirmLinkScreen.opening(SR_SUPPORT_WEB, this, true))
                    .dimensions(fullButtonX, (height - screenHeight) / 2 + 20 + 72 + buttonSpacing - buttonHeight, halfButtonWidth, buttonHeight).build());
            addDrawableChild(ButtonWidget.builder(COMMUNITY_TEXT,
                            ConfirmLinkScreen.opening(SR_COMMUNITY_WEB, getParent(), true))
                    .dimensions(halfButtonX, (height - screenHeight) / 2 + 20 + 72 + buttonSpacing - buttonHeight, halfButtonWidth, buttonHeight).build());
            addDrawableChild(ButtonWidget.builder(Text.translatable("menu.returnToGame"), (button) -> {
                        resetCanCheckUpdate();
                        close();
                    })
                    .dimensions(fullButtonX, buttonY - buttonSpacing, fullButtonWidth, buttonHeight).build());
        }

        /**
         * 渲染提供函数, 所需要使用的所有特性都要在此函数中注册
         */
        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            super.render(context, mouseX, mouseY, delta);
            changelogWidget.render(context, mouseX, mouseY, delta);
        }
    }

    /**
     * 停止更新警告界面
     */
    public static class StopUpdateWarningScreen extends UpdateScreen {
        /**
         * 构造更新屏幕类
         */
        public StopUpdateWarningScreen(Screen parent) {
            super(parent, Text.translatable("title.spontaneous_replace.stop_update_warning")
                    .setStyle(Style.EMPTY.withBold(true).withColor(SR_THEME_COLOR)));
        }

        /**
         * 配置函数, 所以要用到的按钮等都需要在此函数中注册
         */
        @Override
        protected void init() {
            // 赞助按钮、官网按钮、关闭更新按钮、关闭新MC更新按钮、立即更新按钮、暂时不用按钮
            super.init();
            // 添加提示消息文本
            messageText = MultilineText.create(textRenderer, Text.translatable("text.spontaneous_replace.stop_update_warning")
                    .setStyle(Style.EMPTY.withBold(true).withColor(Formatting.RED)), screenWidth - 6);
            int fullButtonWidth = screenWidth - 6;
            int buttonHeight = 20;
            int buttonSpacing = buttonHeight + 3;
            int fullButtonX = (width - (screenWidth - 6)) / 2;
            int buttonY = (height - (height - screenHeight) / 2);
            int halfButtonWidth = fullButtonWidth / 2 - 1;
            int halfButtonX = fullButtonX + halfButtonWidth + 2;
            // 添加按钮
            addDrawableChild(ButtonWidget.builder(MOD_WEB_TEXT,
                            ConfirmLinkScreen.opening(SR_OFFICIAL_WEB, getParent(), true))
                    .dimensions(fullButtonX, (height - screenHeight) / 2 + 20 + 72 + buttonSpacing - buttonHeight, halfButtonWidth, buttonHeight).build());
            addDrawableChild(ButtonWidget.builder(COMMUNITY_TEXT,
                            ConfirmLinkScreen.opening(SR_COMMUNITY_WEB, getParent(), true))
                    .dimensions(halfButtonX, (height - screenHeight) / 2 + 20 + 72 + buttonSpacing - buttonHeight, halfButtonWidth, buttonHeight).build());
            addDrawableChild(ButtonWidget.builder(Text.translatable("menu.returnToGame"), (button) -> {
                        resetCanCheckUpdate();
                        close();
                    })
                    .dimensions(fullButtonX, buttonY - buttonSpacing, fullButtonWidth, buttonHeight).build());
        }

        /**
         * 渲染提供函数, 所需要使用的所有特性都要在此函数中注册
         */
        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            super.render(context, mouseX, mouseY, delta);
            messageText.drawCenterWithShadow(context,
                    width / 2,
                    (height - screenHeight) / 2 + 20 + 72 + (height - (height - screenHeight + 20 + 72)) / 2 - 6);
        }
    }

    /**
     * 更新系统失效警告界面
     */
    public static class UpdateFailWarningScreen extends UpdateScreen {
        /**
         * 构造更新屏幕类
         */
        public UpdateFailWarningScreen(Screen parent) {
            super(parent, Text.translatable("title.spontaneous_replace.update_fail_warning")
                    .setStyle(Style.EMPTY.withBold(true).withColor(SR_THEME_COLOR)));
        }

        /**
         * 配置函数, 所以要用到的按钮等都需要在此函数中注册
         */
        @Override
        protected void init() {
            // 赞助按钮、官网按钮、关闭更新按钮、关闭新MC更新按钮、立即更新按钮、暂时不用按钮
            super.init();
            // 添加提示消息文本
            messageText = MultilineText.create(textRenderer, Text.translatable("text.spontaneous_replace.update_fail_warning")
                    .setStyle(Style.EMPTY.withBold(true).withColor(Formatting.RED)), screenWidth - 6);
            int fullButtonWidth = screenWidth - 6;
            int buttonHeight = 20;
            int buttonSpacing = buttonHeight + 3;
            int fullButtonX = (width - (screenWidth - 6)) / 2;
            int buttonY = (height - (height - screenHeight) / 2);
            int halfButtonWidth = fullButtonWidth / 2 - 1;
            int halfButtonX = fullButtonX + halfButtonWidth + 2;
            // 添加按钮
            addDrawableChild(ButtonWidget.builder(MOD_WEB_TEXT,
                            ConfirmLinkScreen.opening(SR_OFFICIAL_WEB, getParent(), true))
                    .dimensions(fullButtonX, (height - screenHeight) / 2 + 20 + 72 + buttonSpacing - buttonHeight, halfButtonWidth, buttonHeight).build());
            addDrawableChild(ButtonWidget.builder(COMMUNITY_TEXT,
                            ConfirmLinkScreen.opening(SR_COMMUNITY_WEB, getParent(), true))
                    .dimensions(halfButtonX, (height - screenHeight) / 2 + 20 + 72 + buttonSpacing - buttonHeight, halfButtonWidth, buttonHeight).build());
            addDrawableChild(ButtonWidget.builder(Text.translatable("menu.returnToGame"), (button) -> {
                        resetCanCheckUpdate();
                        close();
                    })
                    .dimensions(fullButtonX, buttonY - buttonSpacing, fullButtonWidth, buttonHeight).build());
        }

        /**
         * 渲染提供函数, 所需要使用的所有特性都要在此函数中注册
         */
        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            super.render(context, mouseX, mouseY, delta);
            messageText.drawCenterWithShadow(context,
                    width / 2,
                    (height - screenHeight) / 2 + 20 + 72 + (height - (height - screenHeight + 20 + 72)) / 2 - 6);
        }
    }

    /**
     * 基础更新屏幕类，所有更新提示都在此基础上拓展
     */
    public abstract static class UpdateScreen extends Screen {
        public static final Text UPDATE_TEXT = Text.translatable("text.spontaneous_replace.update_now");
        public static final Text RETURN_TEXT = Text.translatable("text.spontaneous_replace.not_update");
        public static final Text AUTO_DOWNLOAD_DONE_TEXT = Text.translatable("text.spontaneous_replace.auto_download_done").setStyle(Style.EMPTY.withBold(true));
        public static final Text AUTO_UPDATE_DONE_TEXT = Text.translatable("text.spontaneous_replace.auto_update_done").setStyle(Style.EMPTY.withBold(true));
        public static final Text UPDATING_FAIL_TEXT = Text.translatable(getModConfigText("updating_fail")).setStyle(Style.EMPTY.withBold(true));
        public int screenWidth;
        public int screenHeight;
        protected final Screen parent;
        protected final Text title;
        protected ButtonWidget updateButton;
        protected MultilineText messageText;

        protected UpdateScreen(Screen parent, Text title) {
            super(Text.of(""));
            this.parent = parent;
            this.title = title;
            setCanCheckUpdate();
            setShowScreen();
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
         * 提供函数, 注册背景屏幕大小标题与模组图标
         */
        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            renderBackground(context, mouseX, mouseY, delta);
            context.drawTextWithShadow(textRenderer, title,
                    (width - textRenderer.getWidth(title)) / 2,
                    (height - screenHeight) / 2 + 5, 0xFFFFFF);
            RenderSystem.setShaderTexture(0, SR_ICON_ID);
            context.drawTexture(SR_ICON_ID, width / 2 - 36, (height - screenHeight) / 2 + 18, 0, 0.0F, 0.0F, 72, 72, 72, 72);
            super.render(context, mouseX, mouseY, delta);
        }

        /**
         * 渲染背景
         */
        protected void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
            if (Objects.requireNonNull(client).world != null)
                context.fillGradient(0, 0, width, height, -1072689136, -804253680);
            renderBackgroundTexture(context, mouseX, mouseY, delta);
        }

        /**
         * 渲染背景纹理
         */
        protected void renderBackgroundTexture(DrawContext context, int mouseX, int mouseY, float delta) {
            ButtonWidget.builder(Text.of(""), (button) -> {
            }).dimensions((width - (screenWidth + 6)) / 2,
                    (height - (screenHeight + 6)) / 2,
                    screenWidth + 6,
                    screenHeight + 6).build().render(context, mouseX, mouseY, delta);
            RenderSystem.setShaderTexture(0, OPTIONS_BACKGROUND_TEXTURE);
            RenderSystem.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
            context.drawTexture(OPTIONS_BACKGROUND_TEXTURE, (width - screenWidth) / 2, (height - screenHeight) / 2, 0, 0.0F, 0.0F, screenWidth, screenHeight, 32, 32);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }

        /**
         * 配置函数, 设置屏幕大小参数
         */
        @Override
        protected void init() {
            super.init();
            screenWidth = (int) (width / 2.5);
            screenHeight = (int) (height * 0.9);
        }
    }

    /**
     * 新 MC 版本模组更新提示
     */
    public static class NewMCVerNotificationToast extends UpdateToast {
        public NewMCVerNotificationToast() {
            super(Text.translatable("title.spontaneous_replace.new_mc_ver_update_notification", getUpdateMinecraftVersion())
                            .setStyle(Style.EMPTY.withBold(true).withColor(SR_THEME_COLOR)),
                    Text.translatable("text.spontaneous_replace.mod_update_notification",
                            getUpdateModVersion().substring(getUpdateModVersion().indexOf("-") + 1)));
        }
    }

    /**
     * 此 MC 版本模组更新提示
     */
    public static class ThisMCVerNotificationToast extends UpdateToast {
        public ThisMCVerNotificationToast() {
            super(Text.translatable("title.spontaneous_replace.this_mc_ver_update_notification")
                            .setStyle(Style.EMPTY.withBold(true).withColor(SR_THEME_COLOR)),
                    Text.translatable("text.spontaneous_replace.mod_update_notification",
                            getUpdateModVersion().substring(getUpdateModVersion().indexOf("-") + 1)));
        }
    }

    /**
     * 模组日志展示提示
     */
    public static class ShowChangelogToast extends UpdateToast {
        public ShowChangelogToast() {
            super(Text.translatable("title.spontaneous_replace.log")
                            .setStyle(Style.EMPTY.withBold(true).withColor(SR_THEME_COLOR)),
                    Text.translatable("text.spontaneous_replace.log").setStyle(Style.EMPTY));
        }
    }

    /**
     * 停止更新警告提示
     */
    public static class StopUpdateWarningToast extends UpdateToast {
        public StopUpdateWarningToast() {
            super(Text.translatable("title.spontaneous_replace.stop_update_warning")
                            .setStyle(Style.EMPTY.withBold(true).withColor(SR_THEME_COLOR)),
                    Text.translatable("text.spontaneous_replace.stop_update_warning")
                            .setStyle(Style.EMPTY.withBold(true).withColor(Formatting.RED)));
        }
    }

    /**
     * 更新系统失效警告提示
     */
    public static class UpdateFailWarningToast extends UpdateToast {
        public UpdateFailWarningToast() {
            super(Text.translatable("title.spontaneous_replace.update_fail_warning")
                            .setStyle(Style.EMPTY.withBold(true).withColor(SR_THEME_COLOR)),
                    Text.translatable("text.spontaneous_replace.update_fail_warning")
                            .setStyle(Style.EMPTY.withBold(true).withColor(Formatting.RED)));
        }
    }

    /**
     * 基础更新提示类，所有更新提示书签都在此基础上扩展
     */
    public abstract static class UpdateToast implements Toast {
        protected int time;
        protected final Text title;
        protected final Text message;

        @Override
        public Visibility draw(DrawContext context, ToastManager manager, long startTime) {
            time++;
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, TEXTURE);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            context.drawTexture(TEXTURE, -28, 0, 0, 0, getWidth(), getHeight());
            RenderSystem.setShaderTexture(0, TEXTURE);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            context.drawTexture(TEXTURE, 0, 0, 0, 0, getWidth(), getHeight());
            RenderSystem.setShaderTexture(0, SR_ICON_ID);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            context.drawTexture(SR_ICON_ID, -30 + 6, 4, 0, 0, 24, 24, 24, 24);
            if (getSeconds(time) < 30) {
                context.drawText(manager.getClient().textRenderer, title,
                        (getWidth() - manager.getClient().textRenderer.getWidth(title)) / 2,
                        (getHeight() - manager.getClient().textRenderer.fontHeight) / 2, SR_THEME_COLOR, false);
            } else {
                MultilineText.create(manager.getClient().textRenderer, message, getWidth() - 6)
                        .draw(context, 4, getHeight() / 2 - manager.getClient().textRenderer.fontHeight, 10, 0xFFFFFF);
            }
            return getSeconds(time) < 60 ? Visibility.SHOW : Visibility.HIDE;
        }

        public static void setToast(UpdateToast updateToast) {
            ToastManager toastManager = MinecraftClient.getInstance().getToastManager();
            UpdateToast haveToast = toastManager.getToast(updateToast.getClass(), Toast.TYPE);
            if (haveToast == null) toastManager.add(updateToast);
        }

        public UpdateToast(Text title, Text message) {
            this.title = title;
            this.message = message;
            setCanCheckUpdate();
            setShowScreen();
        }
    }
}
