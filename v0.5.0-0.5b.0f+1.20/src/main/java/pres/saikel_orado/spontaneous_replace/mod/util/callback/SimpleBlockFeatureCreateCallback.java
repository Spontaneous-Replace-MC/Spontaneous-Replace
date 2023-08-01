package pres.saikel_orado.spontaneous_replace.mod.util.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

/**
 * <b style="color:FFC800"><font size="+2">SimpleBlockFeatureCreateCallback：简单块地物创建回调</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">当创建你的特殊地物时伴随着的随机状态或环境设置</font></b></i></p>
 * <p><i><b style="color:FFC800"><font size="+1"> - SUCCESS 退出后续处理过程，由于是修改放置行为返回值所以不会有什么前后操作</font></b></i></p>
 * <p><i><b style="color:FFC800"><font size="+1"> - PASS 回落到后续处理过程，如果没有其他的监听器了，则默认为 SUCCESS。</font></b></i></p>
 * <p><i><b style="color:FFC800"><font size="+1"> - FAIL 退出后续处理过程。</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/19 17:07
 */
public interface SimpleBlockFeatureCreateCallback {
    Event<SimpleBlockFeatureCreateCallback> EVENT = EventFactory.createArrayBacked(SimpleBlockFeatureCreateCallback.class,
            (listeners) -> (world, pos, state, returnBoolean) -> {
                for (SimpleBlockFeatureCreateCallback listener : listeners) {
                    ReturnValue returnValue = listener.interact(world, pos, state, returnBoolean);
                    ActionResult result = returnValue.result();

                    if (result != ActionResult.PASS) return returnValue;
                }
                return new ReturnValue(ActionResult.PASS, returnBoolean);
            });

    ReturnValue interact(StructureWorldAccess world, BlockPos pos, BlockState state, boolean returnBoolean);

    record ReturnValue(ActionResult result, boolean returnBoolean) {
    }
}
