package SpontaneousReplace.VanillaExtension.CuFeAlloy;

import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;

/**
 * <b style="color:FFC800"><font size="+2">Items：铜铁物品类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加铜铁系列物品</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Items {
    /**
     * 铜铁合金: 由精铜锭加上铁锭在锻造台打造而成或在工作台合成
     */
    public static final Item CUFE_ALLOY = new Item(new FabricItemSettings());
    /**
     * 铜铁锭: 由铜铁合金在在高炉烧炼 200 刻而成, 烧炼经验 0.9
     */
    public static final Item CUFE_ALLOY_INGOT = new Item(new FabricItemSettings());
    /**
     * 铜铁粒: 由铜铁锭在工作台拆解或铜铁装备烧炼而成, 烧炼经验 0.1
     */
    public static final Item CUFE_ALLOY_NUGGET = new Item(new FabricItemSettings());

    /**
     * 注册物品
     */
    public static void register() {
        // 注册铜铁合金
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cufe_alloy"), CUFE_ALLOY);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(CUFE_ALLOY));
        // 注册铜铁锭
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cufe_alloy_ingot"), CUFE_ALLOY_INGOT);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(CUFE_ALLOY_INGOT));
        // 注册铜铁粒
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cufe_alloy_nugget"), CUFE_ALLOY_NUGGET);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(CUFE_ALLOY_NUGGET));
    }
}
