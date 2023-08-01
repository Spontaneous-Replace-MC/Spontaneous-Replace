package pres.saikel_orado.spontaneous_replace.mod.mixin.player.armor;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RangedRelated.ARROWPROOF_VEST;

/**
 * <b style="color:FFC800"><font size="+2">ArrowproofVest：防箭衣混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">注册防箭衣的 50% 弹射物保护</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/4/19 18:05
 */
@Mixin(PlayerEntity.class)
abstract class ArrowproofVest extends LivingEntity {
    private ArrowproofVest(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * 截取伤害来源以供使用
     */
    @Unique
    private static DamageSource damageSource = null;


    /**
     * 截取伤害来源
     */
    @Inject(method = "applyDamage", at = @At("HEAD"))
    private void getSource(DamageSource source, float amount, CallbackInfo ci) {
        damageSource = source;
    }

    /**
     * 设置防箭衣的弹射物保护
     *
     * @return 如果装备防箭衣并是弹射物伤害则减半伤害
     */
    @ModifyVariable(method = "applyDamage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private float setProtect(float amount) {
        return getEquippedStack(EquipmentSlot.CHEST).isOf(ARROWPROOF_VEST) && damageSource.isIn(DamageTypeTags.IS_PROJECTILE) ? amount / 2 : amount;
    }
}
