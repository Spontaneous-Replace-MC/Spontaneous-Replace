package pres.saikel_orado.spontaneous_replace.mod.variant.general.tree.eerie_tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.block.EerieBough;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.EERIE_BOUGH;

/**
 * <b style="color:FFC800"><font size="+2">EerieFoliagePlacer：阴森木枝树叶放置器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加阴森木枝树叶放置器</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/29 18:06
 */
public class EerieFoliagePlacer extends BlobFoliagePlacer {
    public static final Codec<EerieFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            fillFoliagePlacerFields(instance)
                    .and(Codec.intRange(0, 16).fieldOf("height").forGetter(placer -> placer.height))
                    .apply(instance, EerieFoliagePlacer::new));

    public static FoliagePlacerType<EerieFoliagePlacer> EERIE_BOUGH_FOLIAGE_PLACER;

    public EerieFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset, height);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return super.getType();
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        BlockPos center = treeNode.getCenter().down(3);
        for (int count = 0; count < foliageHeight * radius * radius * 5; count++) {
            BlockPos pos = new BlockPos(
                    center.getX() + random.nextBetween(-radius, radius),
                    center.getY() + random.nextInt(foliageHeight),
                    center.getZ() + random.nextBetween(-radius, radius));
            if (EERIE_BOUGH.canPlaceAt(((WorldView) world).getBlockState(pos), (WorldView) world, pos)) {
                BlockState blockState = config.foliageProvider.get(random, pos);
                if (blockState.contains(Properties.WATERLOGGED)) {
                    blockState = blockState.with(Properties.WATERLOGGED, world.testFluidState(pos, fluidState -> fluidState.isEqualAndStill(Fluids.WATER)));
                }
                if (blockState.getBlock() instanceof EerieBough) {
                    placer.placeBlock(pos, EERIE_BOUGH.withConnectionProperties((BlockView) world, pos));
                    for (Direction direction : Direction.Type.HORIZONTAL) {
                        BlockPos directionPos = pos.offset(direction);
                        BlockState directionState = ((WorldView) world).getBlockState(directionPos);
                        if (directionState.getBlock() instanceof EerieBough)
                            placer.placeBlock(directionPos, EERIE_BOUGH.withConnectionProperties((BlockView) world, directionPos));
                    }
                    for (Direction direction : Direction.Type.VERTICAL) {
                        BlockPos directionPos = pos.offset(direction);
                        BlockState directionState = ((WorldView) world).getBlockState(directionPos);
                        if (directionState.getBlock() instanceof EerieBough)
                            placer.placeBlock(directionPos, EERIE_BOUGH.withConnectionProperties((BlockView) world, directionPos));
                    }
                }
            }
        }
    }
}
