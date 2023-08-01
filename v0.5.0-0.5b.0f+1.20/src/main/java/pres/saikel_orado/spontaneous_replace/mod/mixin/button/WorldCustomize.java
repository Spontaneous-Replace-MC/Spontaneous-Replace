package pres.saikel_orado.spontaneous_replace.mod.mixin.button;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.screen.world.WorldCreator;
import net.minecraft.client.gui.tab.GridScreenTab;
import net.minecraft.client.gui.tab.TabManager;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pres.saikel_orado.spontaneous_replace.mod.screen.PlaceholderScreen;

import java.util.Objects;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.CreateWorldScreenData.WorldCustomize;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.CreateWorldScreenData.worldCreator;

/**
 * <b style="color:FFC800"><font size="+2">WorldCustomize：世界自定义混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">使得自然更替世界配置能够自定义</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2023/3/11 16:32
 */
@Mixin(targets = "net.minecraft.client.gui.screen.world.CreateWorldScreen$WorldTab")
abstract class WorldCustomize extends GridScreenTab {
    private WorldCustomize(Text title) {
        super(title);
    }

    /**
     * 使用 @Shader 取消自定义按钮的访问限制
     */
    @Final
    @Shadow
    private ButtonWidget customizeButton;

    /**
     * 设置默认世界预设
     */
    @ModifyVariable(method = "<init>", at = @At("STORE"), ordinal = 0)
    private CyclingButtonWidget<WorldCreator.WorldType> init(CyclingButtonWidget<WorldCreator.WorldType> cyclingButtonWidget) {
        while (!cyclingButtonWidget.getValue().getName().equals(Text.translatable("generator.spontaneous_replace.sr_world"))) {
            cyclingButtonWidget.onPress();
        }
        return cyclingButtonWidget;
    }

    /**
     * 处理在合适的时机正确地显示模组自带的自定义按钮
     *
     * @param ci 回调信息
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        WorldCustomize.setPosition(customizeButton.getX(), customizeButton.getY());
        WorldCustomize.visible = worldCreator.getWorldType().getName().equals(
                Text.translatable("generator.spontaneous_replace.sr_world"))
                || worldCreator.getWorldType().getName().equals(
                Text.translatable("generator.spontaneous_replace.sr_world_snapshot"));
        customizeButton.visible = !WorldCustomize.visible;
    }
}

/**
 * 添加自定义世界按钮、获取世界构造器对象并解决非世界选项卡显示问题
 */
@Mixin(CreateWorldScreen.class)
abstract class WorldAddCustomize extends Screen {
    private WorldAddCustomize(Text title) {
        super(title);
    }

    /**
     * 使用 @Shader 取消选项管理器的访问限制
     */
    @Final
    @Shadow
    private TabManager tabManager;

    /**
     * 添加自定义世界按钮并获取世界构造器对象
     *
     * @param ci 回调信息
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        worldCreator = ((CreateWorldScreen) (Object) this).getWorldCreator();
        WorldCustomize = addDrawableChild(ButtonWidget.builder(Text.translatable("selectWorld.customizeType"),
                (button) -> MinecraftClient.getInstance().setScreen(new PlaceholderScreen(this))).build());
        WorldCustomize.visible = false;
    }

    /**
     * 如果聚焦不在世界选项卡则取消显示世界自定义按钮
     *
     * @param ci 回调信息
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        if (!Objects.requireNonNull(tabManager.getCurrentTab()).getTitle().equals(
                Text.translatable("createWorld.tab.world.title")))
            WorldCustomize.visible = false;
    }
}
