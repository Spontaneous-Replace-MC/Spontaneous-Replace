package pres.saikel_orado.spontaneous_replace.mod.util;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

/**
 * <b style="color:FFC800"><font size="+2">SRArmor：自然更替通用盔甲类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">辅助盔甲的创建的数据直观和清晰</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/2/17 10:37
 */
public abstract class SRArmor implements ArmorMaterial {
    /**
     * 基础耐久基数
     */
    public static final int[] DURABILITY_BASE = {11, 16, 15, 13};
    /**
     * 击退抗性比例
     */
    public static final int KR_RATIO = 10;
    protected final String id;
    protected final Ingredient repairMaterial;
    protected final SoundEvent equipSound;
    protected final int durability;
    protected final int[] protectionAmount;
    protected final int enchantability;
    protected final float toughness;
    protected final float knockBackResistance;

    /**
     * 构建盔甲类
     *
     * @param id                  盔甲 id
     * @param repairMaterial      修复材料
     * @param equipSound          装备音效
     * @param durability          盔甲耐久系数
     * @param protectionAmount    盔甲护甲值系数
     * @param enchantability      附魔能力
     * @param toughness           盔甲韧性
     * @param knockBackResistance 击退抗性
     */
    protected SRArmor(String id, Ingredient repairMaterial, SoundEvent equipSound, int durability, int[] protectionAmount, int enchantability, float toughness, float knockBackResistance) {
        this.id = id;
        this.repairMaterial = repairMaterial;
        this.equipSound = equipSound;
        this.durability = durability;
        this.protectionAmount = protectionAmount;
        this.enchantability = enchantability;
        this.toughness = toughness;
        this.knockBackResistance = knockBackResistance;
    }

    /**
     * 获取头盔
     */
    public ArmorItem createHelmet(Item.Settings settings) {
        return new ArmorItem(this, ArmorItem.Type.HELMET, settings);
    }

    /**
     * 获取胸甲
     */
    public ArmorItem createChestPlate(Item.Settings settings) {
        return new ArmorItem(this, ArmorItem.Type.CHESTPLATE, settings);
    }

    /**
     * 获取腿甲
     */
    public ArmorItem createLeggings(Item.Settings settings) {
        return new ArmorItem(this, ArmorItem.Type.LEGGINGS, settings);
    }

    /**
     * 获取靴子
     */
    public ArmorItem createBoots(Item.Settings settings) {
        return new ArmorItem(this, ArmorItem.Type.BOOTS, settings);
    }

    /**
     * 获取装备耐久值
     *
     * @param type        装备槽
     * @param coefficient 耐久系数
     * @return 实际耐久值
     */
    private int getDurability(ArmorItem.Type type, int coefficient) {
        return DURABILITY_BASE[type.getEquipmentSlot().getEntitySlotId()] * coefficient;
    }

    /**
     * 获取装备护甲值
     *
     * @param type 装备槽
     * @param head 头盔
     * @param body 胸甲
     * @param legs 护腿
     * @param feet 靴子
     * @return 实际护甲值
     */
    private int getProtection(ArmorItem.Type type, int head, int body, int legs, int feet) {
        return new int[]{feet, legs, body, head}[type.getEquipmentSlot().getEntitySlotId()];
    }

    /**
     * 获取击退抗性
     *
     * @param kr 击退抗性
     * @return 击退抗性
     */
    private float getKnockBackResistance(float kr) {
        return kr / KR_RATIO;
    }

    /**
     * 获取盔甲耐久
     *
     * @param type 装备槽
     * @return 盔甲耐久值
     */
    @Override
    public int getDurability(ArmorItem.Type type) {
        return getDurability(type, durability);
    }

    /**
     * 获取盔甲护甲值
     *
     * @param type 装备槽
     * @return 盔甲护甲值
     */
    @Override
    public int getProtection(ArmorItem.Type type) {
        return getProtection(type, protectionAmount[0], protectionAmount[1], protectionAmount[2], protectionAmount[3]);
    }

    /**
     * 获取附魔能力
     *
     * @return 附魔能力
     */
    @Override
    public int getEnchantability() {
        return enchantability;
    }

    /**
     * 获取盔甲音效
     *
     * @return 盔甲音效
     */
    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    /**
     * 获取修复材料
     *
     * @return 修复材料
     */
    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial;
    }

    /**
     * 获取盔甲名称
     *
     * @return 盔甲 ID
     */
    @Override
    public String getName() {
        return id;
    }

    /**
     * 获取盔甲韧性
     *
     * @return 盔甲韧性
     */
    @Override
    public float getToughness() {
        return toughness;
    }

    /**
     * 获取击退抗性
     *
     * @return 击退抗性
     */
    @Override
    public float getKnockbackResistance() {
        return getKnockBackResistance(knockBackResistance);
    }
}