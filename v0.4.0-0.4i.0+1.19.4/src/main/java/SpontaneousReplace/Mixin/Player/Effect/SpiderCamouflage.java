package SpontaneousReplace.Mixin.Player.Effect;

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

import static SpontaneousReplace.Effects.Server.SPIDER_CAMOUFLAGE;
import static SpontaneousReplace.SpiderBiome.Equipments.SpiderLeatherArmor.SPIDER_LEATHER_CAP;
import static SpontaneousReplace.SpiderBiome.Equipments.SpiderLeatherArmor.SPIDER_LEATHER_TUNIC;
import static net.minecraft.entity.effect.StatusEffectInstance.INFINITE;

/**
 * <b style="color:FFC800"><font size="+2">SpiderCamouflage：蜘蛛伪装混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">如果穿着蜘蛛护皮套装则获得蜘蛛伪装效果</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/4/12 17:04
 */
@Mixin(PlayerEntity.class)
public abstract class SpiderCamouflage extends LivingEntity {
    /**
     * 禁止实例化 SpiderCamouflage 蜘蛛伪装混入类
     */
    private SpiderCamouflage(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
        throw new Error("请检查是否实例化 SpiderCamouflage 蜘蛛伪装混入类");
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
