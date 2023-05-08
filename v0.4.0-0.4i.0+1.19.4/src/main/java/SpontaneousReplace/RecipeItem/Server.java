package SpontaneousReplace.RecipeItem;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;

/**
 * <b style="color:FFC800"><font size="+2">Server：合成表物品服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门集中注册合成表占位替换物品的服务端</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/4/12 10:35
 */
public abstract class Server {
    /**
     * 用于合成的假剧毒之箭，其作用为占位以便被替换为真正的剧毒之箭，无法通过除命令外的其他方式获取
     */
    public static final Item POISON_ARROW = new Item(new FabricItemSettings());

    public static void register() {
        // 注册合成假毒箭
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "poison_arrow"), POISON_ARROW);
    }
}
