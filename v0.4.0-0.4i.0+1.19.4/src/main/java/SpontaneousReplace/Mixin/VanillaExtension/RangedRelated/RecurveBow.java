package SpontaneousReplace.Mixin.VanillaExtension.RangedRelated;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

import static SpontaneousReplace.Generic.SRData.Player.getFovMultiplier;
import static SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon.RecurveBow.*;

/**
 * <b style="color:FFC800"><font size="+2">RecurveBow：反曲弓特性混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置反曲弓的附魔以及视角聚焦等</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/26 17:22
 */
@Mixin(Enchantment.class)
public abstract class RecurveBow {
    /**
     * 禁止实例化 RecurveBow 反曲弓特性混入类
     */
    private RecurveBow() {
        throw new Error("请检查是否实例化 RecurveBow 反曲弓特性混入类");
    }

    /**
     * 设置反曲弓物品在附魔属性是反曲弓可附魔属性则可附魔
     */
    @Inject(method = "isAcceptableItem", at = @At("RETURN"), cancellable = true)
    private void SetEnchantment(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(RECURVE_BOW)) {
            for (Enchantment enchantment : RECURVE_BOW_ENCHANTMENTS) {
                if (enchantment.getName(0).getString().equals(((Enchantment) (Object) this).getName(0).getString())) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    /**
     * 设置拉弓视角缩放
     */
    @Mixin(GameRenderer.class)
    abstract static class SetPullingScale implements AutoCloseable {
        /**
         * 禁止实例化 SetPullingScale 拉弓视角缩放混入类
         */
        private SetPullingScale() {
            throw new Error("请检查是否实例化 SetPullingScale 拉弓视角缩放混入类");
        }

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

