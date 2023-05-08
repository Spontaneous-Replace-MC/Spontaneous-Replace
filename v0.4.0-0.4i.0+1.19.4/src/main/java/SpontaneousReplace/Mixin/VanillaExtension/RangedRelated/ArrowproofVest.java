package SpontaneousReplace.Mixin.VanillaExtension.RangedRelated;

import net.minecraft.client.color.item.ItemColors;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static SpontaneousReplace.VanillaExtension.RangedRelated.DefensiveArmor.ArrowproofVest.ARROWPROOF_VEST;
import static SpontaneousReplace.VanillaExtension.RangedRelated.DefensiveArmor.ArrowproofVest.DEFAULT_COLOR;

/**
 * <b style="color:FFC800"><font size="+2">ArrowproofVest：防箭衣特性混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置防箭衣的默认颜色于物品颜色</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/20 20:07
 */
@Mixin(ItemColors.class)
public abstract class ArrowproofVest {
    /**
     * 禁止实例化 ArrowproofVest 防箭衣特性混入类
     */
    private ArrowproofVest() {
        throw new Error("请检查是否实例化 ArrowproofVest 防箭衣特性混入类");
    }

    /**
     * 注册防箭衣为可染色物品
     */
    @ModifyVariable(method = "create", at = @At("STORE"), ordinal = 0)
    private static ItemColors setItemColor(ItemColors itemColors) {
        itemColors.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack), ARROWPROOF_VEST);
        return itemColors;
    }

    /**
     * 设置默认防箭衣颜色
     */
    @Mixin(ItemStack.class)
    abstract static class SetDefaultColor {
        /**
         * 禁止实例化 SetDefaultColor 设置默认防箭衣颜色类
         */
        private SetDefaultColor() {
            throw new Error("请检查是否实例化 SetDefaultColor 设置默认防箭衣颜色类");
        }

        /**
         * 设置物品颜色
         */
        @Inject(method = "getItem", at = @At("RETURN"), cancellable = true)
        private void setItemColor(CallbackInfoReturnable<Item> cir) {
            Item item = cir.getReturnValue();
            if (item == ARROWPROOF_VEST && ((DyeableArmorItem) item).getColor((ItemStack) (Object) this) == DyeableItem.DEFAULT_COLOR)
                ((DyeableArmorItem) item).setColor((ItemStack) (Object) this, DEFAULT_COLOR);
            cir.setReturnValue(item);
        }
    }

    /**
     * 设置防箭衣在未染色情况下混合颜色不会混合默认颜色
     */
    @Mixin(DyeableItem.class)
    interface setColorBlend {
        @Inject(method = "blendAndSetColor", at = @At("RETURN"), cancellable = true)
        private static void blendAndSetColor(ItemStack stack, List<DyeItem> colors, CallbackInfoReturnable<ItemStack> cir) {
            Item item = stack.getItem();
            if (item == ARROWPROOF_VEST && ((DyeableArmorItem) item).getColor(stack) == DEFAULT_COLOR) {
                float[] blenderColor = colors.get(0).getColor().getColorComponents();
                int color = 0;
                color += (int) (blenderColor[0] * 0xFF) << 16;
                color += (int) (blenderColor[1] * 0xFF) << 8;
                color += (int) (blenderColor[2] * 0xFF);
                ((DyeableArmorItem) item).setColor(stack, color);
                cir.setReturnValue(stack);
            }
        }
    }
}
