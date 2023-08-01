package pres.saikel_orado.spontaneous_replace.mod.mixin.recipe.item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.RecipeItems.POISON_ARROW;

/**
 * <b style="color:FFC800"><font size="+2">PoisonArrow：剧毒之箭合成表混入</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">将合成表中的假剧毒之箭替换为真正的剧毒之箭以做到视觉上的合成</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2023/4/12 10:22
 */
@Mixin(ShapedRecipe.class)
abstract class PoisonArrow {
    /**
     * 使用 @Shadow 截出 output 合成物品以供判断替换
     */
    @Final
    @Shadow
    ItemStack output;

    /**
     * 将识别到的假合成物品替换为真的合成物品以实现合成
     *
     * @param cir 返回回调信息
     */
    @Inject(method = "getOutput", at = @At("HEAD"), cancellable = true)
    private void craft(DynamicRegistryManager registryManager, CallbackInfoReturnable<ItemStack> cir) {
        if (output.isOf(POISON_ARROW)) {
            ItemStack itemStack = new ItemStack(Items.TIPPED_ARROW, output.getCount());
            PotionUtil.setPotion(itemStack, Potions.POISON);
            cir.setReturnValue(itemStack);
        }
    }
}
