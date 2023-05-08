package SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider;

import SpontaneousReplace.Generic.SRItemGroup;
import SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Toxin.ToxinEntity;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.*;
import static SpontaneousReplace.SpiderBiome.Generic.SpiderData.SPIDER_EYES_COLOR;
import static SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Data.*;

/**
 * <b style="color:FFC800"><font size="+2">Server：喷吐毒蛛服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">喷吐毒蛛的服务端内容注册</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2023/01/06
 */
public class Server {
    /**
     * 喷吐毒蛛实体
     */
    public static EntityType<Entity> SPRAY_POISON_SPIDER = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, Entity::new)
            .dimensions(EntityDimensions.fixed(BOX_WEIGHT, BOX_HEIGHT))
            .build();
    /**
     * 喷吐毒蛛刷怪蛋
     */
    public static final Item SPRAY_POISON_SPIDER_SPAWN_EGG = new SpawnEggItem(SPRAY_POISON_SPIDER, SPIDER_SKIN_COLOR, SPIDER_EYES_COLOR, new Item.Settings());
    /**
     * 毒素实体
     */
    public static final EntityType<ToxinEntity> TOXIN = FabricEntityTypeBuilder.<ToxinEntity>create(SpawnGroup.MISC, ToxinEntity::new)
            .dimensions(EntityDimensions.fixed(PROJECTILE_BOX, PROJECTILE_BOX))
            .trackRangeBlocks(PROJECTILE_RANGE)
            .trackedUpdateRate(PROJECTILE_UPDATE_RATE)
            .build();
    /**
     * 毒素粒子
     */
    public static final DefaultParticleType TOXIN_PARTICLE = FabricParticleTypes.simple();
    /**
     * 喷吐毒素音效字幕 ID
     */
    public static final Identifier SPRAY_TOXIN_ID = new Identifier("spontaneous_replace:spider_biome.spray_poison_spider.spray_toxin");
    /**
     * 喷吐毒素音效
     */
    public static SoundEvent SPRAY_TOXIN = SoundEvent.of(SPRAY_TOXIN_ID);

    public static void register() {
        // 注册喷吐毒蛛
        Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, ID), SPRAY_POISON_SPIDER);
        FabricDefaultAttributeRegistry.register(SPRAY_POISON_SPIDER, Entity.createSpiderAttributes());
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, getEgg(ID)), SPRAY_POISON_SPIDER_SPAWN_EGG);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.SPAWN_EGGS).register(content -> content.add(SPRAY_POISON_SPIDER_SPAWN_EGG));
        // 注册毒素与毒素粒子
        Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, "spider_toxin_projectile"), TOXIN);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "toxin"), TOXIN_PARTICLE);
        // 注册喷吐音效
        Registry.register(Registries.SOUND_EVENT, SPRAY_TOXIN_ID, SPRAY_TOXIN);
    }
}
