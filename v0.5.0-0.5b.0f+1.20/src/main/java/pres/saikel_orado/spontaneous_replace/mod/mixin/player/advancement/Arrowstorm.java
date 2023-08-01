package pres.saikel_orado.spontaneous_replace.mod.mixin.player.advancement;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.Advancements.ARROWSTORM;

/**
 * <b style="color:FFC800"><font size="+2">Arrowstorm：箭雨进度判断混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">判断箭雨进度是否完成并将其写入玩家 NBT</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/5/19 10:06
 */
@Mixin(PlayerEntity.class)
abstract class Arrowstorm extends LivingEntity {
    private Arrowstorm(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * 箭雨数量
     */
    @Unique
    private int arrowstormNum;

    /**
     * 获取箭雨已射出箭矢数
     */
    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    private void getArrowNum(NbtCompound nbt, CallbackInfo ci) {
        arrowstormNum = nbt.getInt(ARROWSTORM);
    }

    /**
     * 设置箭雨已射出箭矢数
     */
    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void setArrowstormNum(NbtCompound nbt, CallbackInfo ci) {
        if (arrowstormNum >= 1002)
            arrowstormNum = 1000;
        nbt.putInt(ARROWSTORM, arrowstormNum += nbt.getInt(ARROWSTORM));
    }
}
