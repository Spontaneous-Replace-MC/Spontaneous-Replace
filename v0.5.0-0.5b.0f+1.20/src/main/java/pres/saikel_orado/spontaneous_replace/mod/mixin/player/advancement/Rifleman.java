package pres.saikel_orado.spontaneous_replace.mod.mixin.player.advancement;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.Advancements.RIFLEMAN;

/**
 * <b style="color:FFC800"><font size="+2">Rifleman：突击手进度判断混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">判断突击手进度是否完成并将其写入玩家 NBT</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/5/19 14:51
 */
@Mixin(PlayerEntity.class)
abstract class Rifleman extends LivingEntity {
    private Rifleman(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * 击杀数量
     */
    @Unique
    private int killedNum;

    /**
     * 获取击杀数
     */
    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    private void getArrowNum(NbtCompound nbt, CallbackInfo ci) {
        killedNum = nbt.getInt(RIFLEMAN);
    }

    /**
     * 设置击杀数
     */
    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void setArrowstormNum(NbtCompound nbt, CallbackInfo ci) {
        if (killedNum >= 100)
            killedNum = 100;
        nbt.putInt(RIFLEMAN, killedNum += nbt.getInt(RIFLEMAN));
    }

    /**
     * 设置击杀数添加
     */
    @Mixin(PersistentProjectileEntity.class)
    abstract static class Add extends ProjectileEntity {
        private Add(EntityType<? extends ProjectileEntity> entityType, World world) {
            super(entityType, world);
        }

        /**
         * 是诸葛连弩发射
         */
        @Unique
        private boolean isJgShot;

        /**
         * 获取判断标志
         */
        @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
        private void getFlag(NbtCompound nbt, CallbackInfo ci) {
            isJgShot = nbt.getBoolean("JgShot");
        }

        /**
         * 添加击杀数量
         */
        @Inject(method = "onEntityHit", at = @At("RETURN"))
        private void addKilledNum(EntityHitResult entityHitResult, CallbackInfo ci) {
            if (isJgShot && entityHitResult.getEntity() instanceof HostileEntity hostileEntity
                    && hostileEntity.getHealth() <= 0) {
                if (getOwner() instanceof PlayerEntity player) {
                    NbtCompound nbtCompound = new NbtCompound();
                    nbtCompound.putInt(RIFLEMAN, 1);
                    player.writeCustomDataToNbt(nbtCompound);
                }
            }
        }
    }
}
