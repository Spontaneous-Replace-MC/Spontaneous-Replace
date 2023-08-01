package pres.saikel_orado.spontaneous_replace.mod.mixin.util.callback;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pres.saikel_orado.spontaneous_replace.mod.util.callback.InfectSaplingCallback;

/**
 * <b style="color:FFC800"><font size="+2">InfectSapling：树苗成长感染混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">将树苗成长感染回调混入至原版函数中</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/24 12:56
 */
@Mixin(SaplingBlock.class)
abstract class InfectSapling {
    @Inject(method = "generate", at = @At(value = "INVOKE",
            target = "L net/minecraft/block/sapling/SaplingGenerator;generate(L net/minecraft/server/world/ServerWorld;L net/minecraft/world/gen/chunk/ChunkGenerator;L net/minecraft/util/math/BlockPos;L net/minecraft/block/BlockState;L net/minecraft/util/math/random/Random;)Z"),
            cancellable = true)
    private void generate(ServerWorld world, BlockPos pos, BlockState state, Random random, CallbackInfo ci) {
        InfectSaplingCallback.EVENT.invoker().interact(world, pos, state, random, (SaplingBlock) (Object) this);
        ci.cancel();
    }
}
