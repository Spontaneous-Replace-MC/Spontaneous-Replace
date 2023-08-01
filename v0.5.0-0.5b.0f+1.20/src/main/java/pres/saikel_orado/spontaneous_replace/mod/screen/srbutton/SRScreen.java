package pres.saikel_orado.spontaneous_replace.mod.screen.srbutton;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tab.Tab;
import net.minecraft.client.gui.tab.TabManager;
import net.minecraft.client.gui.widget.*;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData;

import static com.mojang.blaze3d.systems.RenderSystem.setShaderColor;
import static com.mojang.blaze3d.systems.RenderSystem.setShaderTexture;
import static net.minecraft.client.gui.screen.world.CreateWorldScreen.FOOTER_SEPARATOR_TEXTURE;
import static net.minecraft.client.gui.screen.world.CreateWorldScreen.LIGHT_DIRT_BACKGROUND_TEXTURE;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.SR_ID;
import static pres.saikel_orado.spontaneous_replace.mod.screen.Update.setUpdateScreen;
import static pres.saikel_orado.spontaneous_replace.mod.screen.srbutton.ConfigTab.isConfigTab;
import static pres.saikel_orado.spontaneous_replace.mod.screen.srbutton.ModTab.isModTab;
import static pres.saikel_orado.spontaneous_replace.mod.screen.srbutton.SynopsisTab.isSynopsisTab;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRUpdateUtil.resetShowScreen;

/**
 * <b style="color:FFC800"><font size="+2">SRScreen：《自然更替》模组屏幕</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">显示展现《自然更替》模组介绍、内容和配置的屏幕。当要设置模组按钮时就可以使用此屏幕配置填充</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 5.0
 * | 创建于 2023/3/14 21:21
 */
@Environment(EnvType.CLIENT)
public class SRScreen extends Screen {
    public static final Text SR_BUTTON_TEXT = Text.translatable("text.spontaneous_replace.mod_button");
    public static final Text SR_LICENSES_TEXT = Text.translatable("text.spontaneous_replace.licenses");
    public static final Text SR_MOD_TEXT = Text.translatable("text.spontaneous_replace.mod");
    public static final Text SR_TARGET_TITLE = Text.translatable("title.spontaneous_replace.target");
    public static final Text SR_TARGET_TEXT = Text.translatable("text.spontaneous_replace.target");
    public static final Text SR_LOG_TITLE = Text.translatable("title.spontaneous_replace.log");
    public static final Text MOD_WEB_TEXT = Text.translatable("text.spontaneous_replace.mod_web");
    public static final Text SUPPORT_TEXT = Text.translatable("text.spontaneous_replace.support");
    public static final Text COMMUNITY_TEXT = Text.translatable("text.spontaneous_replace.community");
    public static final Identifier SR_BACKGROUND_ID = new Identifier(SR_ID, "textures/gui/sr.png");

    protected final Screen parent;
    protected TabNavigationWidget tabNavigation;
    protected final TabManager tabManager = new TabManager(this::addDrawableChild, this::remove);
    protected GridWidget grid;
    protected final ModTab modTab = new ModTab();
    protected final SynopsisTab synopsisTab = new SynopsisTab();
    protected final ConfigTab configTab = new ConfigTab();
    protected PressableTextWidget modVersion;
    protected int tempSize = width * height;
    public int tabIndex = 1;

    /**
     * 配置函数, 所以要用到的按钮等都需要在此函数中注册
     */
    @Override
    protected void init() {
        super.init();
        SRConfigData.Config.setParent(this);
        grid = (new GridWidget()).setColumnSpacing(10);
        GridWidget.Adder adder = grid.createAdder(2);
        adder.add(ButtonWidget.builder(ScreenTexts.DONE, (button) -> close()).build());
        grid.forEachChild((child) -> {
            child.setNavigationOrder(1);
            addDrawableChild(child);
        });
        tabNavigation = TabNavigationWidget.builder(tabManager, width)
                .tabs(new Tab[]{modTab, synopsisTab, configTab}).build();
        addDrawableChild(tabNavigation);
        tabNavigation.selectTab(tabIndex, false);
        tabNavigationInit();
        modTab.init(client, width, height, 12);
        configTab.init(client, width, height, 32, 25);
        // 可点击文本
        if (FabricLoader.getInstance().getModContainer(SR_ID).isPresent()) {
            String modVerString = FabricLoader.getInstance().getModContainer(SR_ID).get().getMetadata().getVersion().getFriendlyString();
            modVersion = new PressableTextWidget(0, height - 12,
                    textRenderer.getWidth(modVerString), textRenderer.fontHeight,
                    Text.of(modVerString), (buttonWidget) -> resetShowScreen(), textRenderer);
            addDrawableChild(modVersion);
        }
    }

    /**
     * 初始化选项卡导肮
     */
    public void tabNavigationInit() {
        if (tabNavigation != null && grid != null) {
            tabNavigation.setWidth(width);
            tabNavigation.init();
            grid.refreshPositions();
            SimplePositioningWidget.setPos(grid, 0, height - 36, width, 36);
            int bottom = tabNavigation.getNavigationFocus().getBottom();
            ScreenRect screenRect = new ScreenRect(0, bottom, width, grid.getY() - bottom);
            tabManager.setTabArea(screenRect);
        }
    }

    /**
     * 提供函数, 所需要使用的所有特性都要在此函数中注册
     */
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        setShaderTexture(0, FOOTER_SEPARATOR_TEXTURE);
        context.drawTexture(FOOTER_SEPARATOR_TEXTURE, 0, MathHelper.roundUpToMultiple(height - 36 - 2, 2), 0.0F, 0.0F, width, 2, 32, 2);
        context.drawTextWithShadow(textRenderer, SR_LICENSES_TEXT, width - textRenderer.getWidth(SR_LICENSES_TEXT), height - 12, 0xFFFFFF);
        if (modVersion.isMouseOver(mouseX, mouseY)) {
            context.drawTextWithShadow(textRenderer, Text.translatable("text.spontaneous_replace.check_update"),
                    mouseX, mouseY - textRenderer.fontHeight * 2, 0xCCCCCC);
        }
        if (modTab.setButtonVisible(isModTab(tabManager))) {
            if (!children().contains(modTab.modWebButton))
                addDrawableChild(modTab.modWebButton);
            if (!children().contains(modTab.supportButton))
                addDrawableChild(modTab.supportButton);
            if (!children().contains(modTab.communityButton))
                addDrawableChild(modTab.communityButton);
            if (!children().contains(modTab.changelogWidget))
                addSelectableChild(modTab.changelogWidget);
            if (!children().contains(modTab.targetWidget))
                addSelectableChild(modTab.targetWidget);
            remove(configTab.list);
            modTab.render(context, mouseX, mouseY, delta);
            if (tempSize == width * height)
                tabIndex = 0;
        }
        if (isSynopsisTab(tabManager)) {
            remove(configTab.list);
            synopsisTab.render(context, textRenderer, width, height);
            if (tempSize == width * height)
                tabIndex = 1;
        }
        if (isConfigTab(tabManager)) {
            if (!children().contains(configTab.list))
                addSelectableChild(configTab.list);
            configTab.render(context, mouseX, mouseY, delta);
            if (tempSize == width * height)
                tabIndex = 2;
        }
        if (tempSize != width * height) {
            tabNavigation.selectTab(tabIndex, false);
            tempSize = width * height;
        }
        super.render(context, mouseX, mouseY, delta);
    }

    /**
     * 渲染背景纹理
     */
    @Override
    public void renderBackgroundTexture(DrawContext context) {
        setShaderTexture(0, SR_BACKGROUND_ID);
        setShaderColor(0.5F, 0.5F, 0.5F, 1.0F);
        context.drawTexture(SR_BACKGROUND_ID, 0, -37, 0, 0.0F, 0.0F, width, height, width, height);
        setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        setShaderTexture(0, LIGHT_DIRT_BACKGROUND_TEXTURE);
        context.drawTexture(LIGHT_DIRT_BACKGROUND_TEXTURE, 0, height - 36, 0, 0.0F, 0.0F, width, height, 32, 32);
    }

    /**
     * 每刻运行
     */
    @Override
    public void tick() {
        tabManager.tick();
        setUpdateScreen(this);
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
    public SRScreen(Screen parent) {
        super(Text.of(""));
        this.parent = parent;
    }
}
