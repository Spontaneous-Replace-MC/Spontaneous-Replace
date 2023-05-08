package SpontaneousReplace.VanillaExtensionContent.RefinedCopper;

import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.Generic.SRTools.*;

/**
 * <b style="color:FFC800"><font size="+2">Tool：精铜工具类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加精铜系列工具</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Tool {
    /**
     * 精铜工具材料
     */
    public static final ToolMaterial COPPER_TOOL = new ToolMaterial();
    /**
     * 材料增伤: 1
     */
    public static final float MATERIAL_DAMAGE = getMaterialDamage(1);
    /**
     * 工具耐久: 200
     */
    public static final int DURABILITY = 200;
    /**
     * 附魔能力: 18
     */
    public static final int ENCHANTABILITY = 18;
    /**
     * 开采速度倍率: 5
     */
    public static final float MINING_SPEED_MULTIPLIER = 5;
    /**
     * 精铜锹  3.5 | 1
     */
    public static ToolItem REFINED_COPPER_SHOVEL = new ShovelItem(COPPER_TOOL, getDamage(3.5F), getSpeed(1), new Item.Settings());
    /**
     * 精铜镐  3 | 1.2
     */
    public static ToolItem REFINED_COPPER_PICKAXE = new PickaxeItem(COPPER_TOOL, (int) getDamage(3), getSpeed(1.2F), new Item.Settings());
    /**
     * 精铜斧  9 | 0.9
     */
    public static ToolItem REFINED_COPPER_AXE = new AxeItem(COPPER_TOOL, getDamage(9), getSpeed(0.9F), new Item.Settings());
    /**
     * 精铜锄  1 | 2
     */
    public static ToolItem REFINED_COPPER_HOE = new HoeItem(COPPER_TOOL, (int) getDamage(1), getSpeed(2), new Item.Settings());
    /**
     * 精铜剑  6 | 1.6
     */
    public static ToolItem REFINED_COPPER_SWORD = new SwordItem(COPPER_TOOL, (int) getDamage(6), getSpeed(1.6F), new Item.Settings());

    /**
     * 注册工具
     */
    public static void register() {
        // 注册精铜锹
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "refined_copper_shovel"), REFINED_COPPER_SHOVEL);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(REFINED_COPPER_SHOVEL));
        // 注册精铜镐
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "refined_copper_pickaxe"), REFINED_COPPER_PICKAXE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(REFINED_COPPER_PICKAXE));
        // 注册精铜斧
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "refined_copper_axe"), REFINED_COPPER_AXE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(REFINED_COPPER_AXE));
        // 注册精铜锄
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "refined_copper_hoe"), REFINED_COPPER_HOE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(REFINED_COPPER_HOE));
        // 注册精铜剑
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "refined_copper_sword"), REFINED_COPPER_SWORD);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(REFINED_COPPER_SWORD));
    }
}
