package SpontaneousReplace.SpiderBiome.Blocks.SpiderChrysalis;

import SpontaneousReplace.Generic.SRItemGroup;
import SpontaneousReplace.SpiderBiome.Generic.SpiderData;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.SpiderBiome.Generic.SpiderData.COBWEB_STRENGTH;

/**
 * <b style="color:FFC800"><font size="+2">Server：蜘蛛茧蛹服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">蜘蛛茧蛹的服务端内容注册</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/1/26 16:00
 */
public abstract class Server {
    /**
     * 蜘蛛茧蛹 ID
     */
    protected static final String ID = "spider_chrysalis";

    /**
     * 蜘蛛茧蛹方块
     * <p>材料：蛛网；需要工具采集；非不透明方块；强度为蜘蛛网强度</p>
     */
    public static final Block SPIDER_CHRYSALIS = new Block(FabricBlockSettings
            .of(SpiderData.COBWEB)
            .requiresTool()
            .nonOpaque()
            .strength(COBWEB_STRENGTH));
    /**
     * 蜘蛛茧蛹物品
     */
    public static final BlockItem SPIDER_CHRYSALIS_ITEM = new BlockItem(SPIDER_CHRYSALIS, new FabricItemSettings());

    public static void register() {
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, ID), SPIDER_CHRYSALIS);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, ID), SPIDER_CHRYSALIS_ITEM);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.BLOCK).register(content -> content.add(SPIDER_CHRYSALIS_ITEM));
    }
}
