package SpontaneousReplace.Mixin.VanillaExtension.RangedRelated;

import SpontaneousReplace.Generic.SRCrossbow;
import SpontaneousReplace.VanillaExtension.RangedRelated.EnhancedWeapon.FullPowerSteelArrow.Entity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Objects;

import static SpontaneousReplace.Generic.SRData.Player.getFovMultiplier;
import static SpontaneousReplace.VanillaExtension.RangedRelated.EnhancedWeapon.MarksCrossbow.*;

/**
 * <b style="color:FFC800"><font size="+2">MarksCrossbow：神臂弩特性混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置神臂弩的附魔以及视角聚焦等</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/5/3 10:37
 */
@Mixin(Enchantment.class)
public class MarksCrossbow {
    /**
     * 禁止实例化 MarksCrossbow 神臂弩特性混入类
     */
    private MarksCrossbow() {
        throw new Error("请检查是否实例化 MarksCrossbow 神臂弩特性混入类");
    }

    /**
     * 设置神臂弩物品在附魔属性是神臂弩可附魔属性则可附魔
     */
    @Inject(method = "isAcceptableItem", at = @At("RETURN"), cancellable = true)
    private void SetEnchantment(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(MARKS_CROSSBOW)) {
            for (Enchantment enchantment : MARKS_CROSSBOW_ENCHANTMENTS) {
                if (enchantment.getName(0).getString().equals(((Enchantment) (Object) this).getName(0).getString())) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

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

        /**
         * 禁止实例化 BreakShield 破盾效果混入类
         */
        private BreakShield() {
            throw new Error("请检查是否实例化 BreakShield 破盾效果混入类");
        }

        private DamageSource damageSource;

        /**
         * 设置盾牌阻挡无效
         */
        @Inject(method = "blockedByShield", at = @At("RETURN"), cancellable = true)
        private void setBreak(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
            if (source.getSource() instanceof Entity entity
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
        @ModifyVariable(method = "damage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
        private float setDamage(float amount) {
            if (damageSource.getSource() instanceof Entity entity &&
                    !(damageSource.getAttacker() instanceof Entity)) {
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
     * <p>设置第一人称弩的渲染</p>
     * <p>由于兼容性原因采取最原始粗暴的 Mixin 方式</p>
     */
    @Mixin(HeldItemRenderer.class)
    abstract static class FirstPersonRender {
        /**
         * 禁止实例化 FirstPersonRender 第一人称渲染混入类
         */
        private FirstPersonRender() {
            throw new Error("请检查是否实例化 FirstPersonRender 第一人称渲染混入类");
        }

        @Shadow
        protected abstract void applyEquipOffset(MatrixStack matrices, Arm arm, float equipProgress);

        @Shadow
        public abstract void renderItem(LivingEntity entity, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light);

        /**
         * 设置第一人称物品渲染
         */
        @Inject(method = "renderFirstPersonItem", at = @At("HEAD"), cancellable = true)
        private void set(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
            if (!player.isUsingSpyglass()) {
                boolean bl = hand == Hand.MAIN_HAND;
                Arm arm = bl ? player.getMainArm() : player.getMainArm().getOpposite();
                matrices.push();
                if (!item.isEmpty() && item.isOf(MARKS_CROSSBOW) && SRCrossbow.isCharged(item)) {
                    boolean bl2;
                    float f;
                    float g;
                    float h;
                    float j;
                    bl2 = arm == Arm.RIGHT;
                    int l;
                    float m;
                    l = bl2 ? 1 : -1;
                    this.applyEquipOffset(matrices, arm, equipProgress);
                    matrices.translate((float) l * -0.2785682F, 0.18344387F, 0.15731531F);
                    matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-13.935F));
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) l * 35.3F));
                    matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float) l * -9.785F));
                    m = 0;
                    f = m / 20.0F;
                    f = (f * f + f * 2.0F) / 3.0F;
                    if (f > 1.0F) {
                        f = 1.0F;
                    }
                    if (f > 0.1F) {
                        g = MathHelper.sin((m - 0.1F) * 1.3F);
                        h = f - 0.1F;
                        j = g * h;
                        matrices.translate(j * 0.0F, j * 0.004F, j * 0.0F);
                    }
                    matrices.translate(f * 0.0F, f * 0.0F, f * 0.04F);
                    matrices.scale(1.0F, 1.0F, 1.0F + f * 0.2F);
                    matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees((float) l * 45.0F));
                    renderItem(player, item, bl2 ? ModelTransformationMode.FIRST_PERSON_RIGHT_HAND : ModelTransformationMode.FIRST_PERSON_LEFT_HAND, !bl2, matrices, vertexConsumers, light);
                    matrices.pop();
                    ci.cancel();
                }
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
         * 禁止实例化 ThirdPersonRender 第三人称渲染混入类
         */
        private ThirdPersonRender() {
            throw new Error("请检查是否实例化 ThirdPersonRender 第三人称渲染混入类");
        }

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
         * 禁止实例化 SetPullingScale 设置发射前视角缩放混入类
         */
        private SetPullingScale() {
            throw new Error("请检查是否实例化 SetPullingScale 设置发射前视角缩放混入类");
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
    abstract static class RenderSpyglassHud extends DrawableHelper {
        /**
         * 禁止实例化 RenderSpyglassHud 望远镜抬头显示混入类
         */
        private RenderSpyglassHud() {
            throw new Error("请检查是否实例化 RenderSpyglassHud 望远镜抬头显示混入类");
        }

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
        protected abstract void renderSpyglassOverlay(MatrixStack matrices, float scale);

        /**
         * 设置渲染
         */
        @Inject(method = "render", at = @At(value = "INVOKE",
                target = "L net/minecraft/client/option/Perspective;isFirstPerson()Z", shift = At.Shift.BY))
        private void setRender(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            ItemStack mainHandStack = Objects.requireNonNull(client.player).getMainHandStack();
            ItemStack offHandStack = Objects.requireNonNull(client.player).getOffHandStack();
            if (client.options.getPerspective().isFirstPerson()
                    && ((mainHandStack.isOf(MARKS_CROSSBOW) && SRCrossbow.isCharged(mainHandStack))
                    || (offHandStack.isOf(MARKS_CROSSBOW) && SRCrossbow.isCharged(offHandStack))))
                renderSpyglassOverlay(matrices, 1);
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
         * 禁止实例化 MouseMove 鼠标移动系数混入类
         */
        private MouseMove() {
            throw new Error("请检查是否实例化 MouseMove 鼠标移动系数混入类");
        }

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
