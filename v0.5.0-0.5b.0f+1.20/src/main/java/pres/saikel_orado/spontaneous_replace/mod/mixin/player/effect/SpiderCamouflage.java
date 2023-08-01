package pres.saikel_orado.spontaneous_replace.mod.mixin.player.effect;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.entity.effect.StatusEffectInstance.INFINITE;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Effects.SPIDER_CAMOUFLAGE;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.SPIDER_LEATHER_CAP;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.SPIDER_LEATHER_TUNIC;

/**
 * <b style="color:FFC800"><font size="+2">SpiderCamouflage：蜘蛛伪装混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">如果穿着蜘蛛护皮套装则获得蜘蛛伪装效果</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2023/4/12 17:04
 */
@Mixin(PlayerEntity.class)
abstract class SpiderCamouflage extends LivingEntity {
    private SpiderCamouflage(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * 检测是否装备整套蜘蛛护皮套装
     *
     * @param ci 回调信息
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void setEffect(CallbackInfo ci) {
        StatusEffectInstance effect = new StatusEffectInstance(SPIDER_CAMOUFLAGE, INFINITE);
        if (getEquippedStack(EquipmentSlot.HEAD).isOf(SPIDER_LEATHER_CAP)
                && getEquippedStack(EquipmentSlot.CHEST).isOf(SPIDER_LEATHER_TUNIC)) {
            if (!getStatusEffects().contains(effect))
                addStatusEffect(effect);
        } else getStatusEffects().remove(effect);
    }
}
