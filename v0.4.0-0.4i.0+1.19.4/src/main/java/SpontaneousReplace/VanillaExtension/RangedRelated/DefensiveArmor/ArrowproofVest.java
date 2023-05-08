package SpontaneousReplace.VanillaExtension.RangedRelated.DefensiveArmor;

import SpontaneousReplace.Generic.SRArmors;
import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.SpiderBiome.Items.Ingredients.COMPOSITE_FABRIC;

/**
 * <b style="color:FFC800"><font size="+2">ArrowproofVest：防箭衣</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加一种特殊的投掷物防御装备</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/19 16:46
 */
public abstract class ArrowproofVest {
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
            return EQUIP_ARROWPROOF_VEST;
        }

        /**
         * 获取修复材料
         *
         * @return 修复材料: 复合面料
         */
        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(COMPOSITE_FABRIC);
        }

        /**
         * 获取盔甲名称
         *
         * @return 盔甲名称: arrowproof_vest
         */
        @Override
        public String getName() {
            return "arrowproof_vest";
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
     * 防箭衣材料
     */
    public static final Material ARROWPROOF_VEST_MATERIAL = new Material();
    /**
     * 盔甲耐久系数：10
     */
    public static final int DURABILITY = 10;

    /**
     * <p>盔甲护甲值系数</p>
     * <p>护甲值:7 | 1 3 2 1</p>
     */
    public static abstract class ProtectionAmount {
        public static int HEAD = 1;
        public static int BODY = 3;
        public static int LEGS = 2;
        public static int FEET = 1;
    }

    /**
     * 附魔能力：9
     */
    public static final int ENCHANTABILITY = 9;
    /**
     * 盔甲韧性：5
     */
    public static final float TOUGHNESS = 5;
    /**
     * 击退抗性：0
     */
    public static final float KNOCK_BACK_RESISTANCE = 0;

    /**
     * 防箭衣 160 | 4
     */
    public static final Item ARROWPROOF_VEST = new ArrowproofVestItem(ARROWPROOF_VEST_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings());

    /**
     * 防箭衣默认颜色
     */
    public static final int DEFAULT_COLOR = 0xFFFFB3;

    /**
     * 装备防箭衣音效 ID
     */
    public static final Identifier EQUIP_ARROWPROOF_VEST_ID = new Identifier(MOD_ID + ":vanilla_extension.arrowproof_vest.equip");
    /**
     * 装备防箭衣音效
     */
    public static SoundEvent EQUIP_ARROWPROOF_VEST = SoundEvent.of(EQUIP_ARROWPROOF_VEST_ID);


    /**
     * 注册盔甲
     */
    public static void register() {
        // 注册防箭衣
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "arrowproof_vest"), ARROWPROOF_VEST);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.EQUIPMENT).register(content -> content.add(ARROWPROOF_VEST));

        // 注册装备装甲音效
        Registry.register(Registries.SOUND_EVENT, EQUIP_ARROWPROOF_VEST_ID, EQUIP_ARROWPROOF_VEST);
    }

    /**
     * 防箭衣物品设置，用于添加默认染色的物品提示
     */
    protected static class ArrowproofVestItem extends DyeableArmorItem {
        /**
         * 构建防箭衣
         */
        public ArrowproofVestItem(ArmorMaterial armorMaterial, Type type, Settings settings) {
            super(armorMaterial, type, settings);
        }

        /**
         * 设置防箭衣的默认染色物品提示
         */
        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            if (world != null) {
                if (((DyeableArmorItem) stack.getItem()).getColor(stack) == ArrowproofVest.DEFAULT_COLOR)
                    tooltip.add(Text.translatable("item.spontaneous_replace.dyeable_item.default_color").formatted(Formatting.BLUE));
            }

            super.appendTooltip(stack, world, tooltip, context);
        }
    }
}
