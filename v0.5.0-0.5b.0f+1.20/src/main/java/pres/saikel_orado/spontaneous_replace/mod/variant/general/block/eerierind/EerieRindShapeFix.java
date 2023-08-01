package pres.saikel_orado.spontaneous_replace.mod.variant.general.block.eerierind;

import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import static net.minecraft.block.Block.createCuboidShape;

/**
 * <b style="color:FFC800"><font size="+2">EerieRindShapeFix：阴森木壳轮廓修复</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">重新绘制炼药锅的轮廓与碰撞体积</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/22 22:27
 */
public interface EerieRindShapeFix {
    VoxelShape RAYCAST_SHAPE = createCuboidShape(2.0, -2.0, 2.0, 14.0, 16.0, 14.0);
    VoxelShape OUTLINE_SHAPE = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), VoxelShapes.union(RAYCAST_SHAPE), BooleanBiFunction.ONLY_FIRST);

    default VoxelShape getEerieRindOutlineShape() {
        return OUTLINE_SHAPE;
    }

    default VoxelShape getEerieRindRaycastShape() {
        return RAYCAST_SHAPE;
    }
}
