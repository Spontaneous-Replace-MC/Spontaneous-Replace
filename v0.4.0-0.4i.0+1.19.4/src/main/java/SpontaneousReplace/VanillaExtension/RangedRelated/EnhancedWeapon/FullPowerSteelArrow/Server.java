package SpontaneousReplace.VanillaExtension.RangedRelated.EnhancedWeapon.FullPowerSteelArrow;

import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.*;

/**
 * <b style="color:FFC800"><font size="+2">Server：全威力钢箭服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">注册全威力钢箭的服务端</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/5/1 21:01
 */
public abstract class Server {
    /**
     * 全威力钢箭，最大堆积 4
     */
    public static final Item FULL_POWER_STEEL_ARROW = new Item(new net.minecraft.item.Item.Settings().maxCount(4));
    /**
     * 全威力钢箭实体
     */
    public static final EntityType<Entity> FULL_POWER_STEEL_ARROW_ENTITY = FabricEntityTypeBuilder
            .<Entity>create(SpawnGroup.MISC, Entity::new)
            .dimensions(EntityDimensions.fixed(PROJECTILE_BOX * 1.25F, PROJECTILE_BOX * 1.25F))
            .trackRangeBlocks(PROJECTILE_RANGE * 10)
            .trackedUpdateRate(PROJECTILE_UPDATE_RATE)
            .build();

    /**
     * 注册全威力钢箭
     */
    public static void register() {
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "full_power_steel_arrow"), FULL_POWER_STEEL_ARROW);
        Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, "full_power_steel_arrow"), FULL_POWER_STEEL_ARROW_ENTITY);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(FULL_POWER_STEEL_ARROW));
    }
}
