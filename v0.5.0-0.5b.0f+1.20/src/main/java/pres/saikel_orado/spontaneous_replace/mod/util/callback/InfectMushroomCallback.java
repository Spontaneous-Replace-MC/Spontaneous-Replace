package pres.saikel_orado.spontaneous_replace.mod.util.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

/**
 * <b style="color:FFC800"><font size="+2">InfectMushroomCallback：菌类成长感染回调</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">将菌类的生长感染成你想要的植物</font></b></i></p>
 * <p><i><b style="color:FFC800"><font size="+1"> - SUCCESS 退出后续处理过程，因为是替换操作，所以不会有什么前后操作</font></b></i></p>
 * <p><i><b style="color:FFC800"><font size="+1"> - PASS 回落到后续处理过程，如果没有其他的监听器了，则默认为 SUCCESS。</font></b></i></p>
 * <p><i><b style="color:FFC800"><font size="+1"> - FAIL 退出后续处理过程。</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/19 17:02
 */
public interface InfectMushroomCallback {
    Event<InfectMushroomCallback> EVENT = EventFactory.createArrayBacked(InfectMushroomCallback.class,
            (listeners) -> (world, pos, state, random, mushroomPlant) -> {
                for (InfectMushroomCallback listener : listeners) {
                    ActionResult result = listener.interact(world, pos, state, random, mushroomPlant);

                    if (result != ActionResult.PASS) return result;
                }
                return ActionResult.PASS;
            });

    ActionResult interact(ServerWorld world, BlockPos pos, BlockState state, Random random, MushroomPlantBlock mushroomPlant);
}
