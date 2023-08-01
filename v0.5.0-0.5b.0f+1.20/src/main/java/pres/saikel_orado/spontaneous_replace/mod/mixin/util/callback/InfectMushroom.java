package pres.saikel_orado.spontaneous_replace.mod.mixin.util.callback;

import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pres.saikel_orado.spontaneous_replace.mod.util.callback.InfectMushroomCallback;

/**
 * <b style="color:FFC800"><font size="+2">InfectMushroom：菌类成长感染混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">将菌类成长感染回调混入至原版函数中</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/24 13:12
 */
@Mixin(MushroomPlantBlock.class)
abstract class InfectMushroom {
    @Inject(method = "trySpawningBigMushroom", at = @At(value = "INVOKE",
            target = "L net/minecraft/world/gen/feature/ConfiguredFeature;generate(L net/minecraft/world/StructureWorldAccess;L net/minecraft/world/gen/chunk/ChunkGenerator;L net/minecraft/util/math/random/Random;L net/minecraft/util/math/BlockPos;)Z"),
            cancellable = true)
    private void trySpawningBigMushroom(ServerWorld world, BlockPos pos, BlockState state, Random random, CallbackInfoReturnable<Boolean> cir) {
        InfectMushroomCallback.EVENT.invoker().interact(world, pos, state, random, (MushroomPlantBlock) (Object) this);
        cir.setReturnValue(true);
    }
}