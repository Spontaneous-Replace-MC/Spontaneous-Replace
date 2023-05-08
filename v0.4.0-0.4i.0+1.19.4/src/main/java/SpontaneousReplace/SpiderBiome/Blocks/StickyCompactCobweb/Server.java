package SpontaneousReplace.SpiderBiome.Blocks.StickyCompactCobweb;

import SpontaneousReplace.Generic.SRItemGroup;
import SpontaneousReplace.SpiderBiome.Generic.SpiderData;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.FireBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.SpiderBiome.Generic.SpiderData.STICKY_COMPACT_COBWEB_STRENGTH;

/**
 * <b style="color:FFC800"><font size="+2">Server：黏密蛛网服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">黏密蛛网的服务端内容注册</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/1/24 13:21
 */
public abstract class Server {
    /**
     * 黏密蛛网 ID
     */
    protected static final String ID = "sticky_compact_cobweb";
    /**
     * 黏密蛛网方块
     * <p>材料：黏密蛛网；无碰撞体积；需要工具采集；强度为黏密蛛网强度</p>
     */
    public static final net.minecraft.block.Block STICKY_COMPACT_COBWEB = new Block(FabricBlockSettings
            .of(SpiderData.STICKY_COMPACT_COBWEB)
            .noCollision()
            .requiresTool()
            .strength(STICKY_COMPACT_COBWEB_STRENGTH));
    /**
     * 黏密蛛网物品
     */
    public static final BlockItem STICKY_COMPACT_COBWEB_ITEM = new BlockItem(STICKY_COMPACT_COBWEB, new FabricItemSettings());

    public static void register() {
        FireBlock.registerDefaultFlammables();
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, ID), STICKY_COMPACT_COBWEB);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, ID), STICKY_COMPACT_COBWEB_ITEM);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.BLOCK).register(content -> content.add(STICKY_COMPACT_COBWEB_ITEM));
    }
}
