package SpontaneousReplace.VanillaExtensionContent.AuCuAlloy;

import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;

/**
 * <b style="color:FFC800"><font size="+2">Items：金铜物品类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加金铜系列物品</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Items {
    /**
     * 金铜合金: 由精铜锭加上铁锭在锻造台打造而成或在工作台合成
     */
    public static final Item AUCU_ALLOY = new Item(new FabricItemSettings());
    /**
     * 金铜锭: 由金铜合金在在高炉烧炼 200 刻而成, 烧炼经验 0.9
     */
    public static final Item AUCU_ALLOY_INGOT = new Item(new FabricItemSettings());
    /**
     * 金铜粒: 由金铜锭在工作台拆解或金铜装备烧炼而成, 烧炼经验 0.1
     */
    public static final Item AUCU_ALLOY_NUGGET = new Item(new FabricItemSettings());

    /**
     * 注册物品
     */
    public static void register() {
        // 注册金铜合金
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "aucu_alloy"), AUCU_ALLOY);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.MISC).register(content -> content.add(AUCU_ALLOY));
        // 注册金铜锭
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "aucu_alloy_ingot"), AUCU_ALLOY_INGOT);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.MISC).register(content -> content.add(AUCU_ALLOY_INGOT));
        // 注册金铜粒
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "aucu_alloy_nugget"), AUCU_ALLOY_NUGGET);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.MISC).register(content -> content.add(AUCU_ALLOY_NUGGET));
    }
}