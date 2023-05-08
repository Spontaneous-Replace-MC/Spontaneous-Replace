package SpontaneousReplace.VanillaExtension.Steel;

import SpontaneousReplace.Generic.SRData;
import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;

/**
 * <b style="color:FFC800"><font size="+2">Items：钢制物品类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加钢制系列物品</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Items {
    /**
     * 精煤: 由煤炭快加上水桶在锻造台打造而成或在工作台合成, 可以作为燃料, 烧炼时长是煤炭的两倍
     */
    public static final Item CLEAN_COAL = new Item(new FabricItemSettings());
    /**
     * 生铁: 由铁锭加上精煤在锻造台打造而成或在工作台合成
     */
    public static final Item PIG_IRON = new Item(new FabricItemSettings());
    /**
     * 钢锭: 由炼锭铜在高炉烧炼 200 刻而成, 烧炼经验 0.9
     */
    public static final Item STEEL_INGOT = new Item(new FabricItemSettings());

    /**
     * 注册物品
     */
    public static void register() {
        // 注册精煤
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "clean_coal"), CLEAN_COAL);
        FuelRegistry.INSTANCE.add(CLEAN_COAL, SRData.getTick(160));
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(CLEAN_COAL));
        // 注册生铁
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "pig_iron"), PIG_IRON);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(PIG_IRON));
        // 注册钢锭
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "steel_ingot"), STEEL_INGOT);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(STEEL_INGOT));
    }
}
