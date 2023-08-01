package pres.saikel_orado.spontaneous_replace.mod.mixin.vanilla.rangedrelated;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.Player.getFovMultiplier;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RangedRelated.COMPOUND_BOW;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.CompoundBow.PULLING_SCALE_MULTIPLE;

/**
 * <b style="color:FFC800"><font size="+2">CompoundBow：复合弓特性混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置复合弓的附魔以及视角聚焦等</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2023/4/27 17:42
 */
final class CompoundBow {
    /**
     * 设置拉弓视角缩放
     */
    @Mixin(GameRenderer.class)
    abstract static class SetPullingScale implements AutoCloseable {
        /**
         * 截取 MC 客户端以供使用
         */
        @Final
        @Shadow
        MinecraftClient client;

        /**
         * 获取视场角缩放倍数
         */
        @Shadow
        private float fovMultiplier;

        /**
         * 设置拉弓时视场角缩放
         */
        @Inject(method = "tick",
                at = @At(value = "INVOKE",
                        target = "L net/minecraft/client/render/GameRenderer;updateFovMultiplier()V",
                        shift = At.Shift.AFTER))
        private void setScale(CallbackInfo ci) {
            fovMultiplier = getFovMultiplier(Objects.requireNonNull(client.player), fovMultiplier, COMPOUND_BOW, PULLING_SCALE_MULTIPLE);
        }
    }

    /**
     * 设置望远镜抬头显示
     */
    @Mixin(InGameHud.class)
    abstract static class RenderSpyglassHud {
        /**
         * 截取 MC 客户端以供使用
         */
        @Final
        @Shadow
        private MinecraftClient client;

        /**
         * 截取望远镜渲染叠加
         */
        @Shadow
        protected abstract void renderSpyglassOverlay(DrawContext context, float scale);

        /**
         * 设置渲染
         */
        @Inject(method = "render", at = @At(value = "INVOKE",
                target = "L net/minecraft/client/option/Perspective;isFirstPerson()Z", shift = At.Shift.BY))
        private void setRender(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (client.options.getPerspective().isFirstPerson()
                    && Objects.requireNonNull(client.player).getActiveItem().isOf(COMPOUND_BOW))
                renderSpyglassOverlay(context, 1);
        }
    }
}
