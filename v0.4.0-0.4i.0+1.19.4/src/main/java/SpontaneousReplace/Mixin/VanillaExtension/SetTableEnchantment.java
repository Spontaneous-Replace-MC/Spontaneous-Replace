package SpontaneousReplace.Mixin.VanillaExtension;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Iterator;

/**
 * <b style="color:FFC800"><font size="+2">SetTableEnchantment：设置附魔台附魔</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置所有物品都可以进行的附魔台附魔</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/24 15:28
 */
@Mixin(EnchantmentHelper.class)
public abstract class SetTableEnchantment {
    /**
     * 禁止实例化 SetTableEnchantment 设置丫弹弓台附魔类
     */
    private SetTableEnchantment() {
        throw new Error("请检查是否实例化 SetTableEnchantment 设置丫弹弓台附魔类");
    }

    /**
     * 截取附魔以供使用
     */
    private static Enchantment enchantment = null;

    /**
     * 截取附魔属性以供使用
     */
    @Redirect(method = "getPossibleEntries", at = @At(value = "INVOKE", target = "L java/util/Iterator;next()L java/lang/Object;"))
    private static <E> E getEnchantment(Iterator<E> instance) {
        E e = instance.next();
        enchantment = (Enchantment) e;
        return e;
    }

    /**
     * 设置附魔判断为 Enchantment.isAcceptableItem() 而不是 EnchantmentTarget.isAcceptableItem()
     */
    @Redirect(method = "getPossibleEntries", at = @At(value = "INVOKE", target = "L net/minecraft/enchantment/EnchantmentTarget;isAcceptableItem(L net/minecraft/item/Item;)Z"))
    private static boolean set(EnchantmentTarget instance, Item item) {
        return enchantment.isAcceptableItem(new ItemStack(item));
    }
}
