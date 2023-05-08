package SpontaneousReplace.VanillaExtension.Steel;

import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.Generic.SRTools.*;

/**
 * <b style="color:FFC800"><font size="+2">Tool：钢制工具类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加钢制系列工具</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Tool {
    /**
     * 钢铁工具材料
     */
    public static final ToolMaterial STEEL_TOOL = new ToolMaterial();
    /**
     * 材料增伤: 3
     */
    public static final float MATERIAL_DAMAGE = getMaterialDamage(3);
    /**
     * 工具耐久: 1000
     */
    public static final int DURABILITY = 1000;
    /**
     * 附魔能力: 14
     */
    public static final int ENCHANTABILITY = 14;
    /**
     * 开采速度倍率: 7
     */
    public static final float MINING_SPEED_MULTIPLIER = 7;
    /**
     * 钢锹  5.5 | 1
     */
    public static ToolItem STEEL_SHOVEL = new ShovelItem(STEEL_TOOL, getDamage(5.5F), getSpeed(1), new Item.Settings());
    /**
     * 钢镐  5 | 1.2
     */
    public static ToolItem STEEL_PICKAXE = new PickaxeItem(STEEL_TOOL, (int) getDamage(5), getSpeed(1.2F), new Item.Settings());
    /**
     * 钢斧  9 | 0.9
     */
    public static ToolItem STEEL_AXE = new AxeItem(STEEL_TOOL, getDamage(9), getSpeed(0.9F), new Item.Settings());
    /**
     * 钢锄  1 | 4
     */
    public static ToolItem STEEL_HOE = new HoeItem(STEEL_TOOL, (int) getDamage(1), getSpeed(4), new Item.Settings());
    /**
     * 钢剑  7 | 1.6
     */
    public static ToolItem STEEL_SWORD = new SwordItem(STEEL_TOOL, (int) getDamage(7), getSpeed(1.6F), new Item.Settings());

    /**
     * 注册工具
     */
    public static void register() {
        // 注册钢锹
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "steel_shovel"), STEEL_SHOVEL);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(STEEL_SHOVEL));
        // 注册钢镐
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "steel_pickaxe"), STEEL_PICKAXE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(STEEL_PICKAXE));
        // 注册钢斧
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "steel_axe"), STEEL_AXE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(STEEL_AXE));
        // 注册钢锄
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "steel_hoe"), STEEL_HOE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(STEEL_HOE));
        // 注册钢剑
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "steel_sword"), STEEL_SWORD);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(STEEL_SWORD));
    }
}

