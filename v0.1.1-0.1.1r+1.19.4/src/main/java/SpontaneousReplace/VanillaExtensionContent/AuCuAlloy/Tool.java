package SpontaneousReplace.VanillaExtensionContent.AuCuAlloy;

import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.Generic.SRTools.*;

/**
 * <b style="color:FFC800"><font size="+2">Tool：金铜工具类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加金铜系列工具</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Tool {
    /**
     * 金铜工具材料
     */
    public static final ToolMaterial AUCU_TOOL = new ToolMaterial();
    /**
     * 材料增伤: 1
     */
    public static final float MATERIAL_DAMAGE = getMaterialDamage(1);
    /**
     * 工具耐久: 131
     */
    public static final int DURABILITY = 131;
    /**
     * 附魔能力: 27
     */
    public static final int ENCHANTABILITY = 27;
    /**
     * 开采速度倍率: 10
     */
    public static final float MINING_SPEED_MULTIPLIER = 10;

    /**
     * 金铜锹  3.5 | 1
     */
    public static ToolItem AUCU_ALLOY_SHOVEL = new ShovelItem(AUCU_TOOL, getDamage(3.5F), getSpeed(1), new Item.Settings());
    /**
     * 金铜镐  2 | 1.2
     */
    public static ToolItem AUCU_ALLOY_PICKAXE = new PickaxeItem(AUCU_TOOL, (int) getDamage(2), getSpeed(1.2F), new Item.Settings());
    /**
     * 金铜斧  9 | 0.8
     */
    public static ToolItem AUCU_ALLOY_AXE = new AxeItem(AUCU_TOOL, getDamage(9), getSpeed(0.8F), new Item.Settings());
    /**
     * 金铜锄  1 | 2
     */
    public static ToolItem AUCU_ALLOY_HOE = new HoeItem(AUCU_TOOL, (int) getDamage(1), getSpeed(2), new Item.Settings());
    /**
     * 金铜剑  5 | 1.6
     */
    public static ToolItem AUCU_ALLOY_SWORD = new SwordItem(AUCU_TOOL, (int) getDamage(5), getSpeed(1.6F), new Item.Settings());

    /**
     * 注册工具
     */
    public static void register() {
        // 注册金铜锹
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "aucu_alloy_shovel"), AUCU_ALLOY_SHOVEL);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_SHOVEL));
        // 注册金铜镐
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "aucu_alloy_pickaxe"), AUCU_ALLOY_PICKAXE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_PICKAXE));
        // 注册金铜斧
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "aucu_alloy_axe"), AUCU_ALLOY_AXE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_AXE));
        // 注册金铜锄
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "aucu_alloy_hoe"), AUCU_ALLOY_HOE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_HOE));
        // 注册金铜剑
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "aucu_alloy_sword"), AUCU_ALLOY_SWORD);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_SWORD));
    }
}