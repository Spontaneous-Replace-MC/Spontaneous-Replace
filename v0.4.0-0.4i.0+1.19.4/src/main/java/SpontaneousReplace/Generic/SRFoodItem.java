package SpontaneousReplace.Generic;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

/**
 * <b style="color:FFC800"><font size="+2">SRFoodItem：自然更替食物物品</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">负责创造具有特殊进食动画的食物物品</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/3/31 16:18
 */
@Environment(EnvType.CLIENT)
public abstract class SRFoodItem {
    /**
     * 禁止实例化 SRFoodItem 食物物品类
     */
    private SRFoodItem() {
        throw new Error("请检查是否实例化 SRFoodItem 食物物品类");
    }

    /**
     * 注册进食进度谓词
     */
    public static void register() {
        ModelPredicateProviderRegistry.register(new Identifier("eating"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0;
            }
            return entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0;
        });
        ModelPredicateProviderRegistry.register(new Identifier("eat"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            return entity.getActiveItem() != stack ? 0.0F : ((float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / stack.getMaxUseTime());
        });
    }
}
