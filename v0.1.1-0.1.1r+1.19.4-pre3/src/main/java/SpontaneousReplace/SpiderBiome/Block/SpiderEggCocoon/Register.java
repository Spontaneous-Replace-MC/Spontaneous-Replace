package SpontaneousReplace.SpiderBiome.Block.SpiderEggCocoon;

import SpontaneousReplace.Generic.SRItemGroup;
import SpontaneousReplace.SpiderBiome.Generic.SpiderData;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.SpiderBiome.Generic.SpiderData.COBWEB_STRENGTH;

/**
 * <b style="color:FFC800"><font size="+2">Register：蜘蛛卵茧服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">蜘蛛卵茧的服务端内容注册</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/2/3 15:36
 */
public abstract class Register {
    /**
     * 蜘蛛茧蛹 ID
     */
    protected static final String ID = "spider_egg_cocoon";

    /**
     * 蜘蛛卵茧方块
     * <p>材料：蛛网；需要工具采集；非不透明方块；强度为蜘蛛网</p>
     */
    public static final Block SPIDER_EGG_COCOON = new Block(FabricBlockSettings
            .of(SpiderData.COBWEB)
            .requiresTool()
            .nonOpaque()
            .strength(COBWEB_STRENGTH));
    /**
     * 蜘蛛卵茧实体
     */
    public static final BlockEntityType<BlockEntity> SPIDER_EGG_COCOON_ENTITY = FabricBlockEntityTypeBuilder
            .create(BlockEntity::new, SPIDER_EGG_COCOON)
            .build();
    /**
     * 蜘蛛卵茧物品
     */
    public static final BlockItem SPIDER_EGG_COCOON_ITEM = new BlockItem(SPIDER_EGG_COCOON, new FabricItemSettings());

    public static void register() {
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, ID), SPIDER_EGG_COCOON);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, ID), SPIDER_EGG_COCOON_ENTITY);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, ID), SPIDER_EGG_COCOON_ITEM);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.BLOCK).register(content -> content.add(SPIDER_EGG_COCOON_ITEM));
    }
}
