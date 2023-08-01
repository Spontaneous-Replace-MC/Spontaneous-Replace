package pres.saikel_orado.spontaneous_replace.mod.mixin.vanilla.rangedrelated;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import pres.saikel_orado.spontaneous_replace.mod.util.SRCrossbow;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.projectile.fullpowersteelarrow.FullPowerSteelArrowEntity;

import java.util.Objects;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.Player.getFovMultiplier;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RangedRelated.MARKS_CROSSBOW;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.MarksCrossbow.CHARGED_SCALE_MULTIPLE;

/**
 * <b style="color:FFC800"><font size="+2">MarksCrossbow：神臂弩特性混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置神臂弩的附魔以及视角聚焦等</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 7.0
 * | 创建于 2023/5/3 10:37
 */
final class MarksCrossbow {
    /**
     * 设置神臂弩的破盾效果
     */
    @Mixin(LivingEntity.class)
    abstract static class BreakShield {
        @Shadow
        public abstract void damageShield(float amount);

        @Shadow
        protected abstract void takeShieldHit(LivingEntity attacker);

        @Shadow
        public abstract ItemStack getActiveItem();

        @Unique
        private DamageSource damageSource;

        /**
         * 设置盾牌阻挡无效
         */
        @Inject(method = "blockedByShield", at = @At("RETURN"), cancellable = true)
        private void setBreak(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
            if (source.getSource() instanceof FullPowerSteelArrowEntity entity
                    && entity.getPierceLevel() == 0)
                cir.setReturnValue(false);
        }

        /**
         * 获取伤害源
         */
        @ModifyVariable(method = "damage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
        private DamageSource getSource(DamageSource source) {
            return damageSource = source;
        }

        /**
         * 设置伤害
         */
        @SuppressWarnings("DataFlowIssue")
        @ModifyVariable(method = "damage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
        private float setDamage(float amount) {
            if (damageSource.getSource() instanceof FullPowerSteelArrowEntity entity &&
                    !(damageSource.getAttacker() instanceof FullPowerSteelArrowEntity)) {
                // 如果全威力钢箭伤害小于 40 则伤害为 40；则最小伤害为 40
                amount = Math.max(amount, 40);
                if (entity.getPierceLevel() == 0 && getActiveItem().isOf(Items.SHIELD)) {
                    damageShield(amount / 2);
                    takeShieldHit((LivingEntity) damageSource.getAttacker());
                    ((PlayerEntity) (Object) this).disableShield(true);

                    return amount / 2;
                }
            }
            return amount;
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
                    && !player.handSwinging && itemStack.isOf(MARKS_CROSSBOW) && SRCrossbow.isCharged(itemStack))
                cir.setReturnValue(BipedEntityModel.ArmPose.BOW_AND_ARROW);
        }
    }

    /**
     * 设置发射前视场角缩放
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
         * 设置发射前视场角缩放
         */
        @Inject(method = "tick",
                at = @At(value = "INVOKE",
                        target = "L net/minecraft/client/render/GameRenderer;updateFovMultiplier()V",
                        shift = At.Shift.AFTER))
        private void setScale(CallbackInfo ci) {
            if (client.options.getPerspective().isFirstPerson())
                fovMultiplier = getFovMultiplier(Objects.requireNonNull(client.player), fovMultiplier, MARKS_CROSSBOW, CHARGED_SCALE_MULTIPLE);
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
            ItemStack mainHandStack = Objects.requireNonNull(client.player).getMainHandStack();
            ItemStack offHandStack = Objects.requireNonNull(client.player).getOffHandStack();
            if (client.options.getPerspective().isFirstPerson()
                    && ((mainHandStack.isOf(MARKS_CROSSBOW) && SRCrossbow.isCharged(mainHandStack))
                    || (offHandStack.isOf(MARKS_CROSSBOW) && SRCrossbow.isCharged(offHandStack))))
                renderSpyglassOverlay(context, 1);
        }
    }

    /**
     * 设置鼠标移动系数
     */
    @Mixin(Mouse.class)
    abstract static class MouseMove {
        /**
         * 所需的各字段
         */
        @Shadow
        @Final
        private MinecraftClient client;

        /**
         * 设置移动
         */
        @ModifyArgs(method = "updateMouse", at = @At(value = "INVOKE",
                target = "L net/minecraft/client/tutorial/TutorialManager;onUpdateMouse(DD)V"))
        private void setMove(org.spongepowered.asm.mixin.injection.invoke.arg.Args args) {
            ItemStack mainHandStack = Objects.requireNonNull(client.player).getMainHandStack();
            ItemStack offHandStack = Objects.requireNonNull(client.player).getOffHandStack();
            if (client.options.getPerspective().isFirstPerson()
                    && ((mainHandStack.isOf(MARKS_CROSSBOW) && SRCrossbow.isCharged(mainHandStack))
                    || (offHandStack.isOf(MARKS_CROSSBOW) && SRCrossbow.isCharged(offHandStack)))) {
                args.set(0, (double) args.get(0) / 8);
                args.set(1, (double) args.get(1) / 8);
            }
        }

        /**
         * 设置朝向
         */
        @ModifyArgs(method = "updateMouse", at = @At(value = "INVOKE",
                target = "L net/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V"))
        private void setLookDirection(Args args) {
            ItemStack mainHandStack = Objects.requireNonNull(client.player).getMainHandStack();
            ItemStack offHandStack = Objects.requireNonNull(client.player).getOffHandStack();
            if (client.options.getPerspective().isFirstPerson()
                    && ((mainHandStack.isOf(MARKS_CROSSBOW) && SRCrossbow.isCharged(mainHandStack))
                    || (offHandStack.isOf(MARKS_CROSSBOW) && SRCrossbow.isCharged(offHandStack)))) {
                args.set(0, (double) args.get(0) / 8);
                args.set(1, (double) args.get(1) / 8);
            }
        }
    }
}
