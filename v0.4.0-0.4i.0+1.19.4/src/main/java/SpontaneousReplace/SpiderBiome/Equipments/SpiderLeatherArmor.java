package SpontaneousReplace.SpiderBiome.Equipments;

import SpontaneousReplace.Generic.SRArmors;
import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.SpiderBiome.Items.Drops.SPIDER_LEATHER;

/**
 * <b style="color:FFC800"><font size="+2">SpiderLeatherArmor：蜘蛛护皮套装</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">蜘蛛护皮套装</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/3/26 18:19
 */
public abstract class SpiderLeatherArmor {
    public static class Material implements ArmorMaterial {
        /**
         * 获取盔甲耐久
         *
         * @param type 装备槽
         * @return 盔甲耐久值
         */
        @Override
        public int getDurability(ArmorItem.Type type) {
            return SRArmors.getDurability(type, DURABILITY);
        }

        /**
         * 获取盔甲护甲值
         *
         * @param type 装备槽
         * @return 盔甲护甲值
         */
        @Override
        public int getProtection(ArmorItem.Type type) {
            return SRArmors.getProtection(type,
                    ProtectionAmount.HEAD,
                    ProtectionAmount.BODY,
                    ProtectionAmount.LEGS,
                    ProtectionAmount.FEET);
        }

        /**
         * 获取附魔能力
         *
         * @return 附魔能力
         */
        @Override
        public int getEnchantability() {
            return ENCHANTABILITY;
        }

        /**
         * 获取盔甲音效
         *
         * @return 盔甲音效: 蜘蛛皮甲
         */
        @Override
        public SoundEvent getEquipSound() {
            return EQUIP_SPIDER_LEATHER_ARMOR;
        }

        /**
         * 获取修复材料
         *
         * @return 修复材料: 蜘蛛护皮
         */
        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(SPIDER_LEATHER);
        }

        /**
         * 获取盔甲名称
         *
         * @return 盔甲名称: spider_leather_tunic
         */
        @Override
        public String getName() {
            return "spider_leather_tunic";
        }

        /**
         * 获取盔甲韧性
         *
         * @return 盔甲韧性
         */
        @Override
        public float getToughness() {
            return TOUGHNESS;
        }

        /**
         * 获取击退抗性
         *
         * @return 击退抗性
         */
        @Override
        public float getKnockbackResistance() {
            return SRArmors.getKnockBackResistance(KNOCK_BACK_RESISTANCE);
        }
    }

    /**
     * 蜘蛛护皮材料
     */
    public static final Material SPIDER_LEATHER_ARMOR = new Material();
    /**
     * 盔甲耐久系数：6
     */
    public static final int DURABILITY = 6;

    /**
     * <p>盔甲护甲值系数</p>
     * <p>护甲值:10 | 2 4 3 1</p>
     */
    public static abstract class ProtectionAmount {
        public static int HEAD = 2;
        public static int BODY = 4;
        public static int LEGS = 3;
        public static int FEET = 1;
    }

    /**
     * 附魔能力：15
     */
    public static final int ENCHANTABILITY = 15;
    /**
     * 盔甲韧性：0
     */
    public static final float TOUGHNESS = 0;
    /**
     * 击退抗性：0
     */
    public static final float KNOCK_BACK_RESISTANCE = 0;

    /**
     * 蜘蛛皮帽 66 | 2
     */
    public static final Item SPIDER_LEATHER_CAP = new ArmorItem(SPIDER_LEATHER_ARMOR, ArmorItem.Type.HELMET, new Item.Settings());
    /**
     * 蜘蛛皮甲 96 | 4
     */
    public static final Item SPIDER_LEATHER_TUNIC = new ArmorItem(SPIDER_LEATHER_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Settings());

    /**
     * 装备蜘蛛皮甲音效 ID
     */
    public static final Identifier EQUIP_SPIDER_LEATHER_ARMOR_ID = new Identifier(MOD_ID + ":spider_biome.spider_leather_armor.equip");
    /**
     * 装备蜘蛛皮甲音效
     */
    public static SoundEvent EQUIP_SPIDER_LEATHER_ARMOR = SoundEvent.of(EQUIP_SPIDER_LEATHER_ARMOR_ID);

    /**
     * 注册盔甲
     */
    public static void register() {
        // 注册蜘蛛皮帽
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "spider_leather_cap"), SPIDER_LEATHER_CAP);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(SPIDER_LEATHER_CAP));
        // 注册蜘蛛皮甲
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "spider_leather_tunic"), SPIDER_LEATHER_TUNIC);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(SPIDER_LEATHER_TUNIC));

        // 注册装备装甲音效
        Registry.register(Registries.SOUND_EVENT, EQUIP_SPIDER_LEATHER_ARMOR_ID, EQUIP_SPIDER_LEATHER_ARMOR);
    }
}
