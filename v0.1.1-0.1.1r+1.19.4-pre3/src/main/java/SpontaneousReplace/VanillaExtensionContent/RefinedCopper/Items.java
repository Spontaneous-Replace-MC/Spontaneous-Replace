package SpontaneousReplace.VanillaExtensionContent.RefinedCopper;

import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;

/**
 * <b style="color:FFC800"><font size="+2">Items：精铜物品类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加精铜系列物品</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Items {
    /**
     * 炼锭铜: 由四个铜锭在工作台合成
     */
    public static final Item COPPER_FOR_SMELTING_INGOT = new Item(new FabricItemSettings());
    /**
     * 精铜锭: 由炼锭铜在高炉烧炼 200 刻而成, 烧炼经验 0.8
     */
    public static final Item REFINED_COPPER_INGOT = new Item(new FabricItemSettings());
    /**
     * 精铜粒: 由精铜锭在工作台拆解或精铜装备烧炼而成, 烧炼经验 0.1
     */
    public static final Item REFINED_COPPER_NUGGET = new Item(new FabricItemSettings());

    /**
     * 注册物品
     */
    public static void register() {
        // 注册炼锭铜
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "copper_for_smelting_ingot"), COPPER_FOR_SMELTING_INGOT);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.MISC).register(content -> content.add(COPPER_FOR_SMELTING_INGOT));
        // 注册精铜锭
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "refined_copper_ingot"), REFINED_COPPER_INGOT);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.MISC).register(content -> content.add(REFINED_COPPER_INGOT));
        // 注册精铜粒
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "refined_copper_nugget"), REFINED_COPPER_NUGGET);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.MISC).register(content -> content.add(REFINED_COPPER_NUGGET));
    }
}
