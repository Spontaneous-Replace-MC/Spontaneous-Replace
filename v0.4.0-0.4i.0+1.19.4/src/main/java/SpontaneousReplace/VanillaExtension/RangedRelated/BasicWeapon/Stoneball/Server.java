package SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon.Stoneball;

import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.*;

/**
 * <b style="color:FFC800"><font size="+2">Server：石弹服务端注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">注册石弹的服务端</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/26 15:58
 */
public abstract class Server {
    /**
     * 石弹：最大堆叠与雪球相同的可投掷物
     */
    public static final Item STONEBALL = new StoneballItem(new Item.Settings().maxCount(Items.SNOWBALL.getMaxCount()));
    /**
     * 石弹实体
     */
    public static final EntityType<StoneballEntity> STONEBALL_ENTITY = FabricEntityTypeBuilder
            .<StoneballEntity>create(SpawnGroup.MISC, StoneballEntity::new)
            .dimensions(EntityDimensions.fixed(PROJECTILE_BOX, PROJECTILE_BOX))
            .trackRangeBlocks(PROJECTILE_RANGE)
            .trackedUpdateRate(PROJECTILE_UPDATE_RATE)
            .build();
    /**
     * 石弹投掷音效 ID
     */
    public static final Identifier STONEBALL_THROW_ID = new Identifier(MOD_ID + ":vanilla_extension.stoneball.throw");
    /**
     * 石弹投掷音效
     */
    public static SoundEvent STONEBALL_THROW = SoundEvent.of(STONEBALL_THROW_ID);

    /**
     * 注册石弹
     */
    public static void register() {
        // 注册石弹
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "stoneball"), STONEBALL);
        Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, "stoneball"), STONEBALL_ENTITY);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(STONEBALL));
        // 注册石弹音效
        Registry.register(Registries.SOUND_EVENT, STONEBALL_THROW_ID, STONEBALL_THROW);
    }
}
