package pres.saikel_orado.spontaneous_replace.mod.mixin.variant.spider.block;

import net.minecraft.block.BlockState;
import net.minecraft.client.particle.BlockDustParticle;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.GOSSAMERY_LEAVES;

/**
 * <b style="color:FFC800"><font size="+2">GossameryLeaves：覆丝树叶混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">混入覆丝树叶有关的一切特性</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/17 14:49
 */
final class GossameryLeaves {
    /**
     * 设置粒子不被染色
     */
    @Mixin(BlockDustParticle.class)
    abstract static class SetParticle extends SpriteBillboardParticle {
        @Inject(method = "<init>(L net/minecraft/client/world/ClientWorld;D D D D D D L net/minecraft/block/BlockState;L net/minecraft/util/math/BlockPos;)V",
                at = @At("TAIL"))
        private void setColorDefault(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, BlockState state, BlockPos blockPos, CallbackInfo ci) {
            if (state.isOf(GOSSAMERY_LEAVES)) {
                red = 0.6F;
                green = 0.6F;
                blue = 0.6F;
            }
        }

        private SetParticle(ClientWorld clientWorld, double d, double e, double f) {
            super(clientWorld, d, e, f);
        }
    }
}
