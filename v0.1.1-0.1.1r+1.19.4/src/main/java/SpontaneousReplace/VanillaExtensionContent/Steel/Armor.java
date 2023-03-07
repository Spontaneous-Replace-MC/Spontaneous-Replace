package SpontaneousReplace.VanillaExtensionContent.Steel;

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
 * <b style="color:FFC800"><font size="+2">Armor：钢制盔甲类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加钢制盔甲</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 5.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Armor {
    /**
     * 钢铁盔甲材料
     */
    public static final ArmorMaterial STEEL_ARMOR = new ArmorMaterial();

    /**
     * <p>盔甲护甲值系数</p>
     * <p>护甲值:18 | 3 7 6 2</p>
     */
    public static abstract class ProtectionAmount {
        public static int HEAD = 3;
        public static int BODY = 7;
        public static int LEGS = 6;
        public static int FEET = 2;
    }

    /**
     * 盔甲耐久系数：28
     */
    public static final int DURABILITY = 28;
    /**
     * 附魔能力：15
     */
    public static final int ENCHANTABILITY = 15;
    /**
     * 盔甲韧性：2
     */
    public static final float TOUGHNESS = 2;
    /**
     * 击退抗性：0.5
     */
    public static final float KNOCK_BACK_RESISTANCE = 0.5F;
    /**
     * 钢制头盔 308 | 3
     */
    public static final Item STEEL_HELMET = new ArmorItem(STEEL_ARMOR, ArmorItem.Type.HELMET, new Item.Settings());
    /**
     * 钢制胸甲 448 | 7
     */
    public static final Item STEEL_CHESTPLATE = new ArmorItem(STEEL_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Settings());
    /**
     * 钢制护腿 420 | 6
     */
    public static final Item STEEL_LEGGINGS = new ArmorItem(STEEL_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Settings());
    /**
     * 钢制靴子 364 | 2
     */
    public static final Item STEEL_BOOTS = new ArmorItem(STEEL_ARMOR, ArmorItem.Type.BOOTS, new Item.Settings());
    /**
     * 装备钢制装甲音效 ID
     */
    public static final Identifier EQUIP_STEEL_ID = new Identifier(MOD_ID + ":vanilla_extension_content.steel.equip");
    /**
     * 装备钢制装甲音效
     */
    public static SoundEvent EQUIP_STEEL = SoundEvent.of(EQUIP_STEEL_ID);

    /**
     * 注册盔甲
     */
    public static void register() {
        // 注册钢制头盔
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "steel_helmet"), STEEL_HELMET);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(STEEL_HELMET));
        // 注册钢制胸甲
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "steel_chestplate"), STEEL_CHESTPLATE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(STEEL_CHESTPLATE));
        // 注册钢制护腿
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "steel_leggings"), STEEL_LEGGINGS);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(STEEL_LEGGINGS));
        // 注册钢制靴子
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "steel_boots"), STEEL_BOOTS);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(STEEL_BOOTS));

        // 注册装备装甲音效
        Registry.register(Registries.SOUND_EVENT, EQUIP_STEEL_ID, EQUIP_STEEL);
    }
}
