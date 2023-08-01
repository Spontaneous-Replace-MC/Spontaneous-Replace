package pres.saikel_orado.spontaneous_replace.mod.mixin.util.callback;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pres.saikel_orado.spontaneous_replace.mod.util.callback.RegistryFlammableCallback;

/**
 * <b style="color:FFC800"><font size="+2">RegistryFlammable：注册易燃物混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">将注册易燃物回调混入至原版函数中</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/19 14:43
 */
@Mixin(FireBlock.class)
abstract class RegistryFlammable {
    /**
     * 截取的注册函数
     */
    @Shadow
    protected abstract void registerFlammableBlock(Block block, int burnChance, int spreadChance);

    /**
     * 将易燃物方块注册到默认易燃物中
     *
     * @param ci 回调信息
     */
    @SuppressWarnings("DataFlowIssue")
    @Inject(method = "registerDefaultFlammables", at = @At("HEAD"))
    private static void registerDefaultFlammables(CallbackInfo ci) {
        RegistryFlammableCallback.EVENT.invoker().interact(
                ((RegistryFlammable) (Object) Blocks.FIRE)::registerFlammableBlock);
    }
}
