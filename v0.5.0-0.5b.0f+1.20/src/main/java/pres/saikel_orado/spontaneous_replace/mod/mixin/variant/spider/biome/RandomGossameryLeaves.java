package pres.saikel_orado.spontaneous_replace.mod.mixin.variant.spider.biome;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.GOSSAMERY_LEAVES;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.IS_SPIDER_BIOME;

/**
 * <b style="color:FFC800"><font size="+2">RandomGossameryLeaves：随机覆丝树叶</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">随机覆丝树叶</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/31 1:05
 */
@Mixin(FoliagePlacer.class)
abstract class RandomGossameryLeaves {
    @Unique
    private static WorldAccess world;
    @Unique
    private static BlockPos pos;
    @Unique
    private static Random random;

    @Inject(method = "placeFoliageBlock(L net/minecraft/world/TestableWorld;L net/minecraft/world/gen/foliage/FoliagePlacer$BlockPlacer;L net/minecraft/util/math/random/Random;L net/minecraft/world/gen/feature/TreeFeatureConfig;L net/minecraft/util/math/BlockPos;)Z", at = @At("HEAD"))
    private static void placeFoliageBlock(TestableWorld world, FoliagePlacer.BlockPlacer placer, Random random, TreeFeatureConfig config, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        RandomGossameryLeaves.world = (WorldAccess) world;
        RandomGossameryLeaves.pos = pos;
        RandomGossameryLeaves.random = random;
    }

    @ModifyArg(method = "placeFoliageBlock(L net/minecraft/world/TestableWorld;L net/minecraft/world/gen/foliage/FoliagePlacer$BlockPlacer;L net/minecraft/util/math/random/Random;L net/minecraft/world/gen/feature/TreeFeatureConfig;L net/minecraft/util/math/BlockPos;)Z",
            at = @At(value = "INVOKE", target = "L net/minecraft/world/gen/foliage/FoliagePlacer$BlockPlacer;placeBlock(L net/minecraft/util/math/BlockPos;L net/minecraft/block/BlockState;)V"), index = 1)
    private static BlockState placeBlock(BlockState state) {
        return world.getBiome(pos).isIn(IS_SPIDER_BIOME) && state.isOf(Blocks.DARK_OAK_LEAVES) && random.nextInt(3) == 0 ? GOSSAMERY_LEAVES.getDefaultState() : state;
    }
}
