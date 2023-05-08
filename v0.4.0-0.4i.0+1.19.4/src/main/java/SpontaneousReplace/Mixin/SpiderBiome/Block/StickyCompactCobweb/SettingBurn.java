package SpontaneousReplace.Mixin.SpiderBiome.Block.StickyCompactCobweb;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static SpontaneousReplace.SpiderBiome.Blocks.StickyCompactCobweb.Server.STICKY_COMPACT_COBWEB;
import static SpontaneousReplace.SpiderBiome.Generic.SpiderData.STICKY_COMPACT_COBWEB_BURN_CHANCE;
import static SpontaneousReplace.SpiderBiome.Generic.SpiderData.STICKY_COMPACT_COBWEB_SPREAD_CHANCE;

/**
 * <b style="color:FFC800"><font size="+2">SettingBurn：黏密蛛网燃烧混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">黏密蛛网设置的燃烧的状态</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2023/1/24 19:23
 */
@Mixin(FireBlock.class)
public abstract class SettingBurn {
    /**
     * 禁止实例化 SettingBurn 燃烧混入
     */
    private SettingBurn() {
        throw new Error("请检查是否实例化 SettingBurn 燃烧混入");
    }

    /**
     * 截取的注册函数
     */
    @Shadow
    protected abstract void registerFlammableBlock(Block block, int burnChance, int spreadChance);

    /**
     * 将黏密蛛网注册到默认易燃物中
     *
     * @param ci 回调信息
     */
    @Inject(method = "registerDefaultFlammables", at = @At("RETURN"))
    private static void registerDefaultFlammables(CallbackInfo ci) {
        ((SettingBurn) (Object) Blocks.FIRE).registerFlammableBlock(STICKY_COMPACT_COBWEB, STICKY_COMPACT_COBWEB_BURN_CHANCE, STICKY_COMPACT_COBWEB_SPREAD_CHANCE);
    }
}

