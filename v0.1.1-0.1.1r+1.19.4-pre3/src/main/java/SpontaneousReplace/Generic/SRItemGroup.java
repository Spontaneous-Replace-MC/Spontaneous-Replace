package SpontaneousReplace.Generic;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;

/**
 * <b style="color:FFC800"><font size="+2">SRItemGroup：物品组</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">负责模组所有的物品组图标以及物品组</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/2/15 13:56
 */
public abstract class SRItemGroup {
    /**
     * 禁止实例化 ItemGroup 物品组类
     */
    private SRItemGroup() {
        throw new Error("请检查是否实例化 ItemGroup 物品组类");
    }

    /**
     * SR 物品组图标
     */
    private abstract static class Icon {
        public static final Item EQUIPMENT = new Item(new FabricItemSettings());
        public static final Item MISC = new Item(new FabricItemSettings());
        public static final Item BLOCK = new Item(new FabricItemSettings());
    }

    public static final ItemGroup EQUIPMENT = FabricItemGroup.builder(
            new Identifier(MOD_ID, "equipment")).icon(() -> new ItemStack(Icon.EQUIPMENT)).build();
    public static final ItemGroup MISC = FabricItemGroup.builder(
            new Identifier(MOD_ID, "misc")).icon(() -> new ItemStack(Icon.MISC)).build();
    public static final ItemGroup BLOCK = FabricItemGroup.builder(
            new Identifier(MOD_ID, "block")).icon(() -> new ItemStack(Icon.BLOCK)).build();

    public static String getString(ItemGroup group) {
        if (group == EQUIPMENT)
            return "equipment";
        else if (group == MISC)
            return "misc";
        else if (group == BLOCK)
            return "block";
        return "";
    }

    /**
     * 注册物品组
     */
    public static void register() {
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "group_iron_equipment"), Icon.EQUIPMENT);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "group_iron_misc"), Icon.MISC);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "group_iron_block"), Icon.BLOCK);
    }
}
