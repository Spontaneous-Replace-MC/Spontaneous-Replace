package pres.saikel_orado.spontaneous_replace.mod.mixin.util.callback;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.SimpleBlockFeature;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pres.saikel_orado.spontaneous_replace.mod.util.callback.SimpleBlockFeatureCreateCallback;

/**
 * <b style="color:FFC800"><font size="+2">SimpleBlockFeatureCreate：地物创建混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">将地物创建回调混入至原版函数中</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/24 14:20
 */
@Mixin(SimpleBlockFeature.class)
abstract class SimpleBlockFeatureCreate implements WorldView {
    @Inject(method = "generate", at = @At("RETURN"), cancellable = true)
    private void generate(FeatureContext<SimpleBlockFeatureConfig> context, CallbackInfoReturnable<Boolean> cir) {
        SimpleBlockFeatureConfig simpleBlockFeatureConfig = context.getConfig();
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        BlockState state = simpleBlockFeatureConfig.toPlace().get(context.getRandom(), pos);
        SimpleBlockFeatureCreateCallback.ReturnValue returnValue =
                SimpleBlockFeatureCreateCallback.EVENT.invoker().interact(world, pos, state, cir.getReturnValue());
        cir.setReturnValue(returnValue.returnBoolean());
    }
}
