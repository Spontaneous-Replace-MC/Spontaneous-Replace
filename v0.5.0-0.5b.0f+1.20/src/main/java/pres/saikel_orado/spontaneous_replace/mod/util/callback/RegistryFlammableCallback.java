package pres.saikel_orado.spontaneous_replace.mod.util.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import net.minecraft.util.ActionResult;

/**
 * <b style="color:FFC800"><font size="+2">RegistryFlammableCallback：注册易燃物方块回调</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">将你的自制方块加入原版易燃物注册中</font></b></i></p>
 * <p><i><b style="color:FFC800"><font size="+1"> - SUCCESS 退出后续处理过程，然后继续进行原版易燃物注册</font></b></i></p>
 * <p><i><b style="color:FFC800"><font size="+1"> - PASS 回落到后续处理过程，如果没有其他的监听器了，则默认为 SUCCESS。</font></b></i></p>
 * <p><i><b style="color:FFC800"><font size="+1"> - FAIL 不影响任何操作。</font></b></i></p>
 * <p><b style="color:FF0000">WARNING</b> Using this callback requires {@link FireBlock#registerDefaultFlammables()} to be added after the callback registry before it can be properly registered.</p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/19 14:37
 */
public interface RegistryFlammableCallback {
    Event<RegistryFlammableCallback> EVENT = EventFactory.createArrayBacked(RegistryFlammableCallback.class,
            (listeners) -> (func) -> {
                for (RegistryFlammableCallback listener : listeners) {
                    ActionResult result = listener.interact(func);

                    if (result != ActionResult.PASS) return result;
                }
                return ActionResult.PASS;
            });

    ActionResult interact(Flammable<Block, Integer, Integer> flammable);

    @FunctionalInterface
    interface Flammable<T, B, S> {
        void register(T block, B burnChance, S spreadChance);
    }
}
