package SpontaneousReplace.VanillaExtensionContent.CuFeAlloy;

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
 * <b style="color:FFC800"><font size="+2">Armor：铜铁盔甲类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加铜铁盔甲</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 5.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Armor {
    /**
     * 铜铁盔甲材料
     */
    public static final ArmorMaterial CUFE_ARMOR = new ArmorMaterial();
    /**
     * 盔甲耐久系数：20
     */
    public static final int DURABILITY = 20;

    /**
     * <p>盔甲护甲值系数</p>
     * <p>护甲值:15 | 2 6 5 2</p>
     */
    public static abstract class ProtectionAmount {
        public static int HEAD = 2;
        public static int BODY = 6;
        public static int LEGS = 5;
        public static int FEET = 2;
    }

    /**
     * 附魔能力：20
     */
    public static final int ENCHANTABILITY = 20;
    /**
     * 盔甲韧性：2
     */
    public static final float TOUGHNESS = 2;
    /**
     * 击退抗性：0
     */
    public static final float KNOCK_BACK_RESISTANCE = 0;

    /**
     * 铜铁头盔 220 | 2
     */
    public static final Item CUFE_ALLOY_HELMET = new ArmorItem(CUFE_ARMOR, ArmorItem.Type.HELMET, new Item.Settings());
    /**
     * 铜铁胸甲 320 | 6
     */
    public static final Item CUFE_ALLOY_CHESTPLATE = new ArmorItem(CUFE_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Settings());
    /**
     * 铜铁护腿 300 | 5
     */
    public static final Item CUFE_ALLOY_LEGGINGS = new ArmorItem(CUFE_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Settings());
    /**
     * 铜铁靴子 260 | 2
     */
    public static final Item CUFE_ALLOY_BOOTS = new ArmorItem(CUFE_ARMOR, ArmorItem.Type.BOOTS, new Item.Settings());

    /**
     * 装备铜铁装甲音效 ID
     */
    public static final Identifier EQUIP_CUFE_ALLOY_ID = new Identifier(MOD_ID + ":vanilla_extension_content.cufe_alloy.equip");
    /**
     * 装备铜铁装甲音效
     */
    public static SoundEvent EQUIP_CUFE_ALLOY = SoundEvent.of(EQUIP_CUFE_ALLOY_ID);

    /**
     * 注册盔甲
     */
    public static void register() {
        // 注册铜铁头盔
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cufe_alloy_helmet"), CUFE_ALLOY_HELMET);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_HELMET));
        // 注册铜铁胸甲
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cufe_alloy_chestplate"), CUFE_ALLOY_CHESTPLATE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_CHESTPLATE));
        // 注册铜铁护腿
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cufe_alloy_leggings"), CUFE_ALLOY_LEGGINGS);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_LEGGINGS));
        // 注册铜铁靴子
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cufe_alloy_boots"), CUFE_ALLOY_BOOTS);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_BOOTS));

        // 注册装备装甲音效
        Registry.register(Registries.SOUND_EVENT, EQUIP_CUFE_ALLOY_ID, EQUIP_CUFE_ALLOY);
    }
}
