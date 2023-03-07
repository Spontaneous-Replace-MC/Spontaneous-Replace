package SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider;

import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.Generic.SRData.getEgg;
import static SpontaneousReplace.SpiderBiome.Generic.SpiderData.SPIDER_EYES_COLOR;
import static SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider.Data.*;

/**
 * <b style="color:FFC800"><font size="+2">Register：织网蜘蛛服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">织网蜘蛛的服务端内容注册</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/1/18 19:41
 */
public abstract class Register {
    /**
     * 织网蜘蛛实体
     */
    public static EntityType<Entity> WEAVING_WEB_SPIDER = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, Entity::new)
            .dimensions(EntityDimensions.fixed(BOX_WEIGHT, BOX_HEIGHT))
            .build();
    /**
     * 织网蜘蛛刷怪蛋
     */
    public static final Item WEAVING_WEB_SPIDER_SPAWN_EGG = new SpawnEggItem(WEAVING_WEB_SPIDER, SPIDER_SKIN_COLOR, SPIDER_EYES_COLOR, new Item.Settings());

    public static void register() {
        Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, ID), WEAVING_WEB_SPIDER);
        FabricDefaultAttributeRegistry.register(WEAVING_WEB_SPIDER, Entity.createSpiderAttributes());
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, getEgg(ID)), WEAVING_WEB_SPIDER_SPAWN_EGG);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.MISC).register(content -> content.add(WEAVING_WEB_SPIDER_SPAWN_EGG));
    }
}