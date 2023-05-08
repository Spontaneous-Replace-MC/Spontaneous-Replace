package SpontaneousReplace.Mixin.VanillaExtension.RangedRelated;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon.Slingshot.SLINGSHOT;
import static SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon.Slingshot.SLINGSHOT_ENCHANTMENTS;

/**
 * <b style="color:FFC800"><font size="+2">Slingshot：丫弹弓特性混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置丫弹弓的附魔以及视角聚焦等</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/24 12:38
 */
@Mixin(Enchantment.class)
public abstract class Slingshot {
    /**
     * 禁止实例化 Slingshot 丫弹弓特性混入类
     */
    private Slingshot() {
        throw new Error("请检查是否实例化 Slingshot 丫弹弓特性混入类");
    }

    /**
     * 设置丫弹弓物品在附魔属性是丫弹弓可附魔属性则可附魔
     */
    @Inject(method = "isAcceptableItem", at = @At("RETURN"), cancellable = true)
    private void set(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(SLINGSHOT)) {
            for (Enchantment enchantment : SLINGSHOT_ENCHANTMENTS) {
                if (enchantment.getName(0).getString().equals(((Enchantment) (Object) this).getName(0).getString()))
                    cir.setReturnValue(true);
            }
        }
    }
}
