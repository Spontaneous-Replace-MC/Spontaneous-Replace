package pres.saikel_orado.spontaneous_replace.mod.variant.general.tree.eerie_tree;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.EERIE_BOUGH;

/**
 * <b style="color:FFC800"><font size="+2">EerieTreeDecorator：阴森木根树木装饰器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加阴森木根树木装饰器</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/29 16:34
 */
public class EerieTreeDecorator extends TreeDecorator {
    public static final EerieTreeDecorator INSTANCE = new EerieTreeDecorator();
    public static final Codec<EerieTreeDecorator> CODEC = Codec.unit(() -> INSTANCE);
    public static TreeDecoratorType<EerieTreeDecorator> EERIE_ROOT_TREE_DECORATOR;

    private EerieTreeDecorator() {
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return EERIE_ROOT_TREE_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
        BlockPos pos = generator.getLogPositions().get(0).up();
        for (Direction side : Direction.Type.HORIZONTAL) {
            BlockPos targetPosition = pos.offset(side, 1);
            if (generator.getWorld().testBlockState(targetPosition.down(), (state -> state.isSideSolidFullSquare((BlockView) generator.getWorld(), targetPosition.down(), Direction.UP))))
                generator.replace(targetPosition, EERIE_BOUGH.withConnectionProperties((BlockView) generator.getWorld(), targetPosition));
        }
    }
}
