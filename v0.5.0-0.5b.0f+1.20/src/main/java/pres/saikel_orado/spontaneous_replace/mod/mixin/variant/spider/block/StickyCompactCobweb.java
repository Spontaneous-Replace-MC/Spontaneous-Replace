package pres.saikel_orado.spontaneous_replace.mod.mixin.variant.spider.block;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.STICKY_COMPACT_COBWEB;
import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderData.*;

/**
 * <b style="color:FFC800"><font size="+2">StickyCompactCobweb：黏密蛛网混入类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">混入黏密蛛网有关的一切特性</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/17 14:50
 */
final class StickyCompactCobweb {
    /**
     * 设置黏密蛛网在火焰附加的武器下的采集效率
     */
    @Mixin(PlayerEntity.class)
    abstract static class FireWeaponBreak {
        /**
         * 截取的判断方块参数
         */
        @Unique
        public BlockState blockState;

        /**
         * 截取方块破坏速度，如果方块是黏密蛛网，则根据工具的火焰附魔等级来提升效率
         * <p>一级火焰附加可以对黏密蛛网提升一把剑的采集效率</p>
         * <p>二级火焰附加可以对黏密蛛网提升十把剑的采集效率</p>
         * <p>十级火焰附加可以对黏密蛛网提升百把剑的采集效率</p>
         *
         * @param breakingSpeed 破坏速度
         * @return 返回新的破坏速度
         */
        @ModifyVariable(method = "getBlockBreakingSpeed", at = @At("STORE"), ordinal = 0)
        private float getBreakingSpeed(float breakingSpeed) {
            if (SRConfigData.Config.modSwitch) if (breakingSpeed > 1.0f && blockState.isOf(STICKY_COMPACT_COBWEB)) {
                int level = EnchantmentHelper.getFireAspect(((PlayerEntity) (Object) this));
                ItemStack itemStack = ((PlayerEntity) (Object) this).getMainHandStack();
                if (level > 0 && !itemStack.isEmpty()) {
                    return switch (level) {
                        case 1 -> breakingSpeed + STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_1;
                        case 2 -> breakingSpeed + STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_2;
                        case 10 -> breakingSpeed + STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_3;
                        default -> breakingSpeed + (level * STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_2);
                    };
                }
            }
            return breakingSpeed;
        }

        /**
         * 截取 getBlockBreakingSpeed() 函数中的 BlockState 参数以供设置挖掘加速使用
         *
         * @param block 方块状态
         */
        @Inject(method = "getBlockBreakingSpeed", at = @At("HEAD"))
        private void getBlockState(BlockState block, CallbackInfoReturnable<Float> cir) {
            blockState = block;
        }
    }
}
