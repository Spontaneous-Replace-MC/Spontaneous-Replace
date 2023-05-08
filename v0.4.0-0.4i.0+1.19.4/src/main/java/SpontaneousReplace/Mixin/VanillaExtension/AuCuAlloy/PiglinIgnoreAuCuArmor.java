package SpontaneousReplace.Mixin.VanillaExtension.AuCuAlloy;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static SpontaneousReplace.VanillaExtension.AuCuAlloy.Armor.AUCU_ARMOR;

/**
 * <b style="color:FFC800"><font size="+2">PiglinIgnoreAuCuArmor：猪灵忽视金铜装甲混入</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">让猪灵忽视装备金铜装甲的实体</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/2/10 18:58
 */
@Mixin(PiglinBrain.class)
public abstract class PiglinIgnoreAuCuArmor {
    /**
     * 禁止实例化 PiglinIgnoreAuCuArmor 猪灵忽略金铜装甲混入
     */
    private PiglinIgnoreAuCuArmor() {
        throw new Error("请检查是否实例化 PiglinIgnoreAuCuArmor 猪灵忽略金铜装甲混入");
    }

    /**
     * 当穿戴金铜装甲时忽略实体并返回 true
     *
     * @param entity 行动实体
     * @param cir    可返回回调信息
     */
    @Inject(method = "wearsGoldArmor", at = @At("HEAD"), cancellable = true)
    private static void wearsAuCuArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        Iterable<ItemStack> iterable = entity.getArmorItems();
        for (ItemStack itemStack : iterable) {
            Item item = itemStack.getItem();
            if (!(item instanceof ArmorItem) || ((ArmorItem) item).getMaterial() != AUCU_ARMOR) continue;
            cir.setReturnValue(true);
        }
    }
}
