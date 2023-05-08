package SpontaneousReplace.VanillaExtension.AuCuAlloy;

import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;

/**
 * <b style="color:FFC800"><font size="+2">Armor：金铜盔甲类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加金铜盔甲</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 5.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Armor {
    /**
     * 金铜盔甲材料
     */
    public static final ArmorMaterial AUCU_ARMOR = new ArmorMaterial();
    /**
     * 盔甲耐久系数：10
     */
    public static final int DURABILITY = 10;

    /**
     * <p>盔甲护甲值系数</p>
     * <p>护甲值:12 | 2 5 4 1</p>
     */
    public static abstract class ProtectionAmount {
        public static int HEAD = 2;
        public static int BODY = 5;
        public static int LEGS = 4;
        public static int FEET = 1;
    }

    /**
     * 附魔能力：30
     */
    public static final int ENCHANTABILITY = 30;
    /**
     * 盔甲韧性：0
     */
    public static final float TOUGHNESS = 0;
    /**
     * 击退抗性：0
     */
    public static final float KNOCK_BACK_RESISTANCE = 0;
    /**
     * 金铜头盔 110 | 2
     */
    public static final Item AUCU_ALLOY_HELMET = new ArmorItem(AUCU_ARMOR, ArmorItem.Type.HELMET, new Item.Settings());
    /**
     * 金铜胸甲 160 | 5
     */
    public static final Item AUCU_ALLOY_CHESTPLATE = new ArmorItem(AUCU_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Settings());
    /**
     * 金铜护腿 150 | 4
     */
    public static final Item AUCU_ALLOY_LEGGINGS = new ArmorItem(AUCU_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Settings());
    /**
     * 金铜靴子 130 | 1
     */
    public static final Item AUCU_ALLOY_BOOTS = new ArmorItem(AUCU_ARMOR, ArmorItem.Type.BOOTS, new Item.Settings());

    /**
     * 装备金铜装甲音效 ID
     */
    public static final Identifier EQUIP_AUCU_ALLOY_ID = new Identifier(MOD_ID + ":vanilla_extension.aucu_alloy.equip");
    /**
     * 装备金铜装甲音效
     */
    public static SoundEvent EQUIP_AUCU_ALLOY = SoundEvent.of(EQUIP_AUCU_ALLOY_ID);

    /**
     * 注册盔甲
     */
    public static void register() {
        // 注册金铜头盔
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "aucu_alloy_helmet"), AUCU_ALLOY_HELMET);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_HELMET));
        // 注册金铜胸甲
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "aucu_alloy_chestplate"), AUCU_ALLOY_CHESTPLATE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_CHESTPLATE));
        // 注册金铜护腿
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "aucu_alloy_leggings"), AUCU_ALLOY_LEGGINGS);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_LEGGINGS));
        // 注册金铜靴子
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "aucu_alloy_boots"), AUCU_ALLOY_BOOTS);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_BOOTS));

        // 注册装备装甲音效
        Registry.register(Registries.SOUND_EVENT, EQUIP_AUCU_ALLOY_ID, EQUIP_AUCU_ALLOY);
    }
}
