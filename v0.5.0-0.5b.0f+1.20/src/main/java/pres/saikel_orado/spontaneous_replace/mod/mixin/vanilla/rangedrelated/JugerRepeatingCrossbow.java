package pres.saikel_orado.spontaneous_replace.mod.mixin.vanilla.rangedrelated;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pres.saikel_orado.spontaneous_replace.mod.util.SRCrossbow;

import java.util.Objects;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RangedRelated.JUGER_REPEATING_CROSSBOW;

/**
 * <b style="color:FFC800"><font size="+2">JugerRepeatingCrossbow：诸葛连弩特性混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置诸葛连弩的附魔以及视角聚焦等</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/4/28 15:54
 */
final class JugerRepeatingCrossbow {
    /**
     * <p>设置第一人称弩的渲染</p>
     * <p>由于兼容性原因采取最原始粗暴的 Mixin 方式</p>
     */
    @Mixin(HeldItemRenderer.class)
    abstract static class FirstPersonRender {
        @Shadow
        @Final
        private MinecraftClient client;

        @Shadow
        protected abstract void applyEquipOffset(MatrixStack matrices, Arm arm, float equipProgress);

        @Shadow
        protected abstract void applySwingOffset(MatrixStack matrices, Arm arm, float swingProgress);

        @Shadow
        public abstract void renderItem(LivingEntity entity, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light);

        /**
         * 设置第一人称物品渲染
         */
        @Inject(method = "renderFirstPersonItem", at = @At("HEAD"), cancellable = true)
        private void set(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
            if (!player.isUsingSpyglass() && !item.isEmpty() && item.isOf(JUGER_REPEATING_CROSSBOW)) {
                boolean bl = hand == Hand.MAIN_HAND;
                Arm arm = bl ? player.getMainArm() : player.getMainArm().getOpposite();
                matrices.push();
                boolean bl2;
                float f;
                float g;
                float h;
                float j;
                bl2 = SRCrossbow.isCharged(item);
                boolean bl3 = arm == Arm.RIGHT;
                int i = bl3 ? 1 : -1;
                if (player.isUsingItem() && player.getItemUseTimeLeft() > 0 && player.getActiveHand() == hand) {
                    applyEquipOffset(matrices, arm, equipProgress);
                    matrices.translate((float) i * -0.4785682F, -0.094387F, 0.05731531F);
                    matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-11.935F));
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) i * 65.3F));
                    matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float) i * -9.785F));
                    f = (float) item.getMaxUseTime() - ((float) Objects.requireNonNull(client.player).getItemUseTimeLeft() - tickDelta + 1.0F);
                    g = f / (float) ((pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.JugerRepeatingCrossbow) player.getActiveItem().getItem()).getPullTime(item);
                    if (g > 1.0F) {
                        g = 1.0F;
                    }

                    if (g > 0.1F) {
                        h = MathHelper.sin((f - 0.1F) * 1.3F);
                        j = g - 0.1F;
                        float k = h * j;
                        matrices.translate(k * 0.0F, k * 0.004F, k * 0.0F);
                    }

                    matrices.translate(g * 0.0F, g * 0.0F, g * 0.04F);
                    matrices.scale(1.0F, 1.0F, 1.0F + g * 0.2F);
                    matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees((float) i * 45.0F));
                } else {
                    f = -0.4F * MathHelper.sin(MathHelper.sqrt(swingProgress) * 3.1415927F);
                    g = 0.2F * MathHelper.sin(MathHelper.sqrt(swingProgress) * 6.2831855F);
                    h = -0.2F * MathHelper.sin(swingProgress * 3.1415927F);
                    matrices.translate((float) i * f, g, h);
                    applyEquipOffset(matrices, arm, equipProgress);
                    applySwingOffset(matrices, arm, swingProgress);
                    if (bl2 && swingProgress < 0.001F && bl) {
                        matrices.translate((float) i * -0.641864F, 0.0F, 0.0F);
                        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) i * 10.0F));
                    }
                }

                renderItem(player, item, bl3 ? ModelTransformationMode.FIRST_PERSON_RIGHT_HAND : ModelTransformationMode.FIRST_PERSON_LEFT_HAND, !bl3, matrices, vertexConsumers, light);

                matrices.pop();
                ci.cancel();
            }
        }
    }

    /**
     * <p>设置第三人称渲染</p>
     * <p>由于兼容性原因采取最原始粗暴的 Mixin 方式</p>
     */
    @Mixin(PlayerEntityRenderer.class)
    abstract static class ThirdPersonRender {
        /**
         * 设置第三人称物品渲染
         */
        @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
        private static void set(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
            ItemStack itemStack = player.getStackInHand(hand);
            if (!itemStack.isEmpty()
                    && !player.handSwinging && itemStack.isOf(JUGER_REPEATING_CROSSBOW) && SRCrossbow.isCharged(itemStack))
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}

