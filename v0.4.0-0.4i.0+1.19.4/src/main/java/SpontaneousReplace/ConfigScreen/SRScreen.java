package SpontaneousReplace.ConfigScreen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tab.Tab;
import net.minecraft.client.gui.tab.TabManager;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.gui.widget.SimplePositioningWidget;
import net.minecraft.client.gui.widget.TabNavigationWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import static SpontaneousReplace.ConfigScreen.SRConfigTab.isSRConfigTab;
import static SpontaneousReplace.ConfigScreen.SRModTab.isSRModTab;
import static SpontaneousReplace.ConfigScreen.SRSynopsisTab.isSRSynopsisTab;
import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static com.mojang.blaze3d.systems.RenderSystem.setShaderColor;
import static com.mojang.blaze3d.systems.RenderSystem.setShaderTexture;
import static net.minecraft.client.gui.screen.world.CreateWorldScreen.FOOTER_SEPARATOR_TEXTURE;

/**
 * <b style="color:FFC800"><font size="+2">SRScreen：《自然更替》模组屏幕</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">显示展现《自然更替》模组介绍、内容和配置的屏幕。当要设置模组按钮时就可以使用此屏幕配置填充</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/3/14 21:21
 */
@Environment(EnvType.CLIENT)
public class SRScreen extends Screen {
    public static final Text SR_BUTTON_TEXT = Text.translatable("text.spontaneous_replace.mod_button");
    public static final Text SR_LICENSES_TEXT = Text.translatable("text.spontaneous_replace.licenses");
    public static final Identifier SR_BACKGROUND_ID = new Identifier(MOD_ID, "textures/gui/sr.png");

    protected Screen parent;
    protected TabNavigationWidget tabNavigation;
    protected final TabManager tabManager = new TabManager(this::addDrawableChild, this::remove);
    protected GridWidget grid;
    protected SRModTab srModTab = new SRModTab();
    protected SRSynopsisTab srSynopsisTab = new SRSynopsisTab();
    protected SRConfigTab srConfigTab = new SRConfigTab();
    protected int tempSize = width * height;
    public int tabIndex = 1;

    /**
     * 配置函数, 所以要用到的按钮等都需要在此函数中注册
     */
    @Override
    protected void init() {
        super.init();
        grid = (new GridWidget()).setColumnSpacing(10);
        GridWidget.Adder adder = grid.createAdder(2);
        adder.add(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
            // 使用断言消除 setScreen NullPointerException警告
            assert client != null;
            client.setScreen(parent);
        }).build());
        grid.forEachChild((child) -> {
            child.setNavigationOrder(1);
            addDrawableChild(child);
        });
        tabNavigation = TabNavigationWidget.builder(tabManager, width)
                .tabs(new Tab[]{srModTab, srSynopsisTab, srConfigTab}).build();
        addDrawableChild(tabNavigation);
        tabNavigation.selectTab(tabIndex, false);
        tabNavigationInit();
        srModTab.init(client, width, height, 12);
        srConfigTab.init(client, width, height, 32, 25);
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
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        setShaderTexture(0, FOOTER_SEPARATOR_TEXTURE);
        drawTexture(matrices, 0, MathHelper.roundUpToMultiple(height - 36 - 2, 2), 0.0F, 0.0F, width, 2, 32, 2);
        drawTextWithShadow(matrices, textRenderer, SR_LICENSES_TEXT, width - textRenderer.getWidth(SR_LICENSES_TEXT), height - 12, 0xFFFFFF);
        if (FabricLoader.getInstance().getModContainer(MOD_ID).isPresent())
            drawTextWithShadow(matrices, textRenderer, FabricLoader.getInstance().getModContainer(MOD_ID).get().getMetadata().getVersion().getFriendlyString(), 0, height - 12, 0xFFFFFF);
        if (srModTab.setButtonVisible(isSRModTab(tabManager))) {
            if (!children().contains(srModTab.modWebButton))
                addDrawableChild(srModTab.modWebButton);
            if (!children().contains(srModTab.supportButton))
                addDrawableChild(srModTab.supportButton);
            if (!children().contains(srModTab.communityButton))
                addDrawableChild(srModTab.communityButton);
            if (!children().contains(srModTab.textListWidget))
                addSelectableChild(srModTab.textListWidget);
            if (!children().contains(srModTab.targetWidget))
                addSelectableChild(srModTab.targetWidget);
            remove(srConfigTab.list);
            srModTab.render(matrices, mouseX, mouseY, delta);
            if (tempSize == width * height)
                tabIndex = 0;
        }
        if (isSRSynopsisTab(tabManager)) {
            remove(srConfigTab.list);
            srSynopsisTab.render(matrices, textRenderer, width, height);
            if (tempSize == width * height)
                tabIndex = 1;
        }
        if (isSRConfigTab(tabManager)) {
            if (!children().contains(srConfigTab.list))
                addSelectableChild(srConfigTab.list);
            srConfigTab.render(matrices, mouseX, mouseY, delta);
            if (tempSize == width * height)
                tabIndex = 2;
        }
        if (tempSize != width * height) {
            tabNavigation.selectTab(tabIndex, false);
            tempSize = width * height;
        }
        super.render(matrices, mouseX, mouseY, delta);
    }

    /**
     * 渲染背景纹理
     */
    @Override
    public void renderBackgroundTexture(MatrixStack matrices) {
        setShaderTexture(0, SR_BACKGROUND_ID);
        setShaderColor(0.5F, 0.5F, 0.5F, 1.0F);
        drawTexture(matrices, 0, -37, 0, 0.0F, 0.0F, width, height, width, height);
        setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        setShaderTexture(0, LIGHT_DIRT_BACKGROUND_TEXTURE);
        drawTexture(matrices, 0, height - 36, 0, 0.0F, 0.0F, width, height, 32, 32);
    }

    /**
     * 每刻运行
     */
    @Override
    public void tick() {
        tabManager.tick();
    }

    /**
     * 屏幕构建
     *
     * @param parent 父窗口
     */
    public SRScreen(Screen parent) {
        super(Text.translatable(""));
        this.parent = parent;
    }
}
