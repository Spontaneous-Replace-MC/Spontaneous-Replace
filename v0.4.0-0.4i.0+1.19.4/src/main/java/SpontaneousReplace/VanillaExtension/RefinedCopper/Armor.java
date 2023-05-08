package SpontaneousReplace.VanillaExtension.RefinedCopper;

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
 * <b style="color:FFC800"><font size="+2">Armor：精铜盔甲类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加精铜盔甲</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public abstract class Armor {
    /**
     * 精铜盔甲材料
     */
    public static final ArmorMaterial COPPER_ARMOR = new ArmorMaterial();

    /**
     * 盔甲耐久系数：12
     */
    public static final int DURABILITY = 12;

    /**
     * <p>盔甲护甲值系数</p>
     * <p>护甲值:13 | 2 5 4 2</p>
     */
    public static abstract class ProtectionAmount {
        public static int HEAD = 2;
        public static int BODY = 5;
        public static int LEGS = 4;
        public static int FEET = 2;
    }

    /**
     * 附魔能力：20
     */
    public static final int ENCHANTABILITY = 20;
    /**
     * 盔甲韧性：0.5
     */
    public static final float TOUGHNESS = 0.5F;
    /**
     * 击退抗性：0
     */
    public static final float KNOCK_BACK_RESISTANCE = 0;

    /**
     * 精铜头盔 132 | 2
     */
    public static final Item REFINED_COPPER_HELMET = new ArmorItem(COPPER_ARMOR, ArmorItem.Type.HELMET, new Item.Settings());
    /**
     * 精铜胸甲 192 | 5
     */
    public static final Item REFINED_COPPER_CHESTPLATE = new ArmorItem(COPPER_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Settings());
    /**
     * 精铜胸甲 192 | 5
     */
    public static final Item REFINED_COPPER_LEGGINGS = new ArmorItem(COPPER_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Settings());
    /**
     * 精铜靴子 156 | 2
     */
    public static final Item REFINED_COPPER_BOOTS = new ArmorItem(COPPER_ARMOR, ArmorItem.Type.BOOTS, new Item.Settings());

    /**
     * 装备精铜装甲音效 ID
     */
    public static final Identifier EQUIP_REFINED_COPPER_ID = new Identifier(MOD_ID + ":vanilla_extension.refined_copper.equip");
    /**
     * 装备精铜装甲音效
     */
    public static SoundEvent EQUIP_REFINED_COPPER = SoundEvent.of(EQUIP_REFINED_COPPER_ID);

    /**
     * 注册盔甲
     */
    public static void register() {
        // 注册精铜头盔
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "refined_copper_helmet"), REFINED_COPPER_HELMET);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(REFINED_COPPER_HELMET));
        // 注册精铜胸甲
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "refined_copper_chestplate"), REFINED_COPPER_CHESTPLATE);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(REFINED_COPPER_CHESTPLATE));
        // 注册精铜护腿
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "refined_copper_leggings"), REFINED_COPPER_LEGGINGS);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(REFINED_COPPER_LEGGINGS));
        // 注册精铜靴子
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "refined_copper_boots"), REFINED_COPPER_BOOTS);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(REFINED_COPPER_BOOTS));

        // 注册装备装甲音效
        Registry.register(Registries.SOUND_EVENT, EQUIP_REFINED_COPPER_ID, EQUIP_REFINED_COPPER);
    }
}
