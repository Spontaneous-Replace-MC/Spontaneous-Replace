package pres.saikel_orado.spontaneous_replace.mod.data;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.SR_ID;

/**
 * <b style="color:FFC800"><font size="+2">SRItemGroups：自然更替物品组</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">负责模组所有的物品组图标以及物品组</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 5.0
 * | 创建于 2023/2/15 13:56
 */
public final class SRItemGroups {
    private SRItemGroups() throws ExceptionInInitializerError {
        throw new ExceptionInInitializerError();
    }

    /**
     * SR 物品组图标
     */
    private interface Icon {
        Item EQUIPMENT = new Item(new FabricItemSettings());
        Item INGREDIENTS = new Item(new FabricItemSettings());
        Item BLOCK = new Item(new FabricItemSettings());
        Item FOOD = new Item(new FabricItemSettings());
        Item SPAWN_EGGS = new Item(new FabricItemSettings());
    }

    /**
     * SR 物品组实例
     */
    private interface Group {
        ItemGroup EQUIPMENT = FabricItemGroup.builder()
                .icon(() -> new ItemStack(Icon.EQUIPMENT))
                .displayName(getItemGroupText("equipment")).build();
        ItemGroup INGREDIENTS = FabricItemGroup.builder()
                .icon(() -> new ItemStack(Icon.INGREDIENTS))
                .displayName(getItemGroupText("ingredients")).build();
        ItemGroup BLOCK = FabricItemGroup.builder()
                .icon(() -> new ItemStack(Icon.BLOCK))
                .displayName(getItemGroupText("block")).build();
        ItemGroup FOOD = FabricItemGroup.builder()
                .icon(() -> new ItemStack(Icon.FOOD))
                .displayName(getItemGroupText("food")).build();
        ItemGroup SPAWN_EGGS = FabricItemGroup.builder()
                .icon(() -> new ItemStack(Icon.SPAWN_EGGS))
                .displayName(getItemGroupText("spawn_eggs")).build();
    }

    public static final RegistryKey<ItemGroup> EQUIPMENT = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(SR_ID, "equipment"));
    public static final RegistryKey<ItemGroup> INGREDIENTS = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(SR_ID, "ingredients"));
    public static final RegistryKey<ItemGroup> BLOCK = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(SR_ID, "block"));
    public static final RegistryKey<ItemGroup> FOOD = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(SR_ID, "food"));
    public static final RegistryKey<ItemGroup> SPAWN_EGGS = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(SR_ID, "spawn_eggs"));

    /**
     * 获取物品组文本
     */
    private static Text getItemGroupText(String string) {
        return Text.translatable("itemGroup." + SR_ID + "." + string);
    }

    /**
     * 注册物品组
     */
    public static void register() {
        Registry.register(Registries.ITEM, new Identifier(SR_ID, "group_icon_equipment"), Icon.EQUIPMENT);
        Registry.register(Registries.ITEM, new Identifier(SR_ID, "group_icon_ingredients"), Icon.INGREDIENTS);
        Registry.register(Registries.ITEM, new Identifier(SR_ID, "group_icon_block"), Icon.BLOCK);
        Registry.register(Registries.ITEM, new Identifier(SR_ID, "group_icon_food"), Icon.FOOD);
        Registry.register(Registries.ITEM, new Identifier(SR_ID, "group_icon_spawn_eggs"), Icon.SPAWN_EGGS);
        Registry.register(Registries.ITEM_GROUP, new Identifier(SR_ID, "equipment"), Group.EQUIPMENT);
        Registry.register(Registries.ITEM_GROUP, new Identifier(SR_ID, "ingredients"), Group.INGREDIENTS);
        Registry.register(Registries.ITEM_GROUP, new Identifier(SR_ID, "block"), Group.BLOCK);
        Registry.register(Registries.ITEM_GROUP, new Identifier(SR_ID, "food"), Group.FOOD);
        Registry.register(Registries.ITEM_GROUP, new Identifier(SR_ID, "spawn_eggs"), Group.SPAWN_EGGS);
    }
}
