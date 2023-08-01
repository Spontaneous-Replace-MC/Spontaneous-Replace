package pres.saikel_orado.spontaneous_replace.mod.mixin.vanilla.rangedrelated;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.Player.getFovMultiplier;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RangedRelated.RECURVE_BOW;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.basicweapon.RecurveBow.PULLING_SCALE_MULTIPLE;

/**
 * <b style="color:FFC800"><font size="+2">RecurveBow：反曲弓特性混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置反曲弓的附魔以及视角聚焦等</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/4/26 17:22
 */
final class RecurveBow {
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
            fovMultiplier = getFovMultiplier(Objects.requireNonNull(client.player), fovMultiplier, RECURVE_BOW, PULLING_SCALE_MULTIPLE);
        }
    }
}

