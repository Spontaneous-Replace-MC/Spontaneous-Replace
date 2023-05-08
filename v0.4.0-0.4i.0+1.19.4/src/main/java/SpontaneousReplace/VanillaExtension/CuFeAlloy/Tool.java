package SpontaneousReplace.VanillaExtension.CuFeAlloy;

import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.Generic.SRTools.*;

/**
 * <b style="color:FFC800"><font size="+2">Tool：铜铁工具类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加铜铁系列工具</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Tool {
    /**
     * 铜铁工具材料
     */
    public static final ToolMaterial CUFE_TOOL = new ToolMaterial();
    /**
     * 材料增伤: 2
     */
    public static final float MATERIAL_DAMAGE = getMaterialDamage(2);
    /**
     * 工具耐久: 500
     */
    public static final int DURABILITY = 500;
    /**
     * 附魔能力: 18
     */
    public static final int ENCHANTABILITY = 18;
    /**
     * 开采速度倍率: 6
     */
    public static final float MINING_SPEED_MULTIPLIER = 6;
    /**
     * 铜铁锹  4.5 | 1
     */
    public static ToolItem CUFE_ALLOY_SHOVEL = new ShovelItem(CUFE_TOOL, getDamage(4.5F), getSpeed(1), new Item.Settings());
    /**
     * 铜铁镐  4 | 1.2
     */
    public static ToolItem CUFE_ALLOY_PICKAXE = new PickaxeItem(CUFE_TOOL, (int) getDamage(4), getSpeed(1.2F), new Item.Settings());
    /**
     * 铜铁斧  9 | 0.9
     */
    public static ToolItem CUFE_ALLOY_AXE = new AxeItem(CUFE_TOOL, getDamage(9), getSpeed(0.9F), new Item.Settings());
    /**
     * 铜铁锄  1 | 3
     */
    public static ToolItem CUFE_ALLOY_HOE = new HoeItem(CUFE_TOOL, (int) getDamage(1), getSpeed(3), new Item.Settings());
    /**
     * 铜铁剑  6 | 1.6
     */
    public static ToolItem CUFE_ALLOY_SWORD = new SwordItem(CUFE_TOOL, (int) getDamage(6), getSpeed(1.6F), new Item.Settings());

    /**
     * 注册工具
     */
    public static void register() {
        // 注册铜铁锹
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cufe_alloy_shovel"), CUFE_ALLOY_SHOVEL);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_SHOVEL));
        // 注册铜铁镐
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cufe_alloy_pickaxe"), CUFE_ALLOY_PICKAXE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_PICKAXE));
        // 注册铜铁斧
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cufe_alloy_axe"), CUFE_ALLOY_AXE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_AXE));
        // 注册铜铁锄
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cufe_alloy_hoe"), CUFE_ALLOY_HOE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_HOE));
        // 注册铜铁剑
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cufe_alloy_sword"), CUFE_ALLOY_SWORD);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_SWORD));
    }
}

