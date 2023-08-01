package pres.saikel_orado.spontaneous_replace.mod.mixin.vanilla;

import com.google.common.collect.Lists;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pres.saikel_orado.spontaneous_replace.mod.util.SRBow;
import pres.saikel_orado.spontaneous_replace.mod.util.SRCrossbow;

import java.util.Iterator;
import java.util.List;

/**
 * <b style="color:FFC800"><font size="+2">SetEnchantment：设置弓弩附魔</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置自然更替所有弓弩都可以进行的附魔</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/24 15:28
 */
@Mixin(Enchantment.class)
abstract class SetEnchantment {
    /**
     * 设置弓弩在附魔属性是弓弩可附魔属性则可附魔
     */
    @Inject(method = "isAcceptableItem", at = @At("RETURN"), cancellable = true)
    private void setEnchantment(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof SRBow bow) {
            for (Enchantment enchantment : bow.ENCHANTMENTS) {
                if (enchantment.getName(0).getString().equals(((Enchantment) (Object) this).getName(0).getString())) {
                    cir.setReturnValue(true);
                }
            }
        } else if (stack.getItem() instanceof SRCrossbow crossbow) {
            for (Enchantment enchantment : crossbow.ENCHANTMENTS) {
                if (enchantment.getName(0).getString().equals(((Enchantment) (Object) this).getName(0).getString())) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    /**
     * 设置所有物品都可以进行的附魔台附魔</font></b></i></p>
     */
    @Mixin(EnchantmentHelper.class)
    abstract static class SetTableEnchantment {
        /**
         * 设置附魔判断为 Enchantment.isAcceptableItem() 而不是 EnchantmentTarget.isAcceptableItem()
         */
        @Inject(method = "getPossibleEntries", at = @At("RETURN"), cancellable = true)
        private static void setEnchantment(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
            List<EnchantmentLevelEntry> list = Lists.newArrayList();
            boolean bl = stack.isOf(Items.BOOK);
            Iterator<?> iterator = Registries.ENCHANTMENT.iterator();

            while (true) {
                Enchantment enchantment;
                do {
                    do {
                        do {
                            if (!iterator.hasNext()) {
                                cir.setReturnValue(list);
                                return;
                            }
                            enchantment = (Enchantment) iterator.next();
                        } while (enchantment.isTreasure() && !treasureAllowed);
                    } while (!enchantment.isAvailableForRandomSelection());
                } while (!enchantment.isAcceptableItem(stack) && !bl);

                for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                    if (power >= enchantment.getMinPower(i) && power <= enchantment.getMaxPower(i)) {
                        list.add(new EnchantmentLevelEntry(enchantment, i));
                        break;
                    }
                }
            }
        }
    }
}
