package pres.saikel_orado.spontaneous_replace.mod.variant.general.tree.treacherous_tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.HashSet;
import java.util.Set;

/**
 * <b style="color:FFC800"><font size="+2">TreacherousFoliagePlacer：诡谲木树叶放置器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加诡谲木树叶放置器</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/30 20:43
 */
public class TreacherousFoliagePlacer extends FoliagePlacer {
    public static final Codec<TreacherousFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            fillFoliagePlacerFields(instance).apply(instance, TreacherousFoliagePlacer::new));
    public static FoliagePlacerType<TreacherousFoliagePlacer> TREACHEROUS_FOLIAGE_PLACER;

    public TreacherousFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return TREACHEROUS_FOLIAGE_PLACER;
    }

    /**
     * 这是用于生成树叶的主要方法。
     */
    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        BlockPos center = treeNode.getCenter().down(2);
        Set<BlockPos> placePosSet = new HashSet<>();
        placePosSet.add(center.north());
        placePosSet.add(center.north().east());
        placePosSet.add(center.south(2));
        placePosSet.add(center.south(2).east());
        placePosSet.add(center.east(2));
        placePosSet.add(center.east(2).south());
        placePosSet.add(center.west());
        placePosSet.add(center.west().south());
        for (BlockPos placePos : placePosSet) {
            BlockPos pos = placePos;
            while (world.testBlockState(pos, AbstractBlock.AbstractBlockState::isAir)) {
                placeFoliageBlock(world, placer, random, config, pos);
                pos = pos.down();
            }
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return 0;
    }

    /**
     * 用于在创建叶子正方形时排除某些位置，例如角。
     */
    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}
