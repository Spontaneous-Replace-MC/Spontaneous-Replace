package pres.saikel_orado.spontaneous_replace.mod.util;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;

/**
 * <b style="color:FFC800"><font size="+2">SRTool：自然更替通用工具类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">辅助工具的创建的数据直观和清晰</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/2/17 10:37
 */
public abstract class SRTool implements ToolMaterial {
    /**
     * 基础伤害
     */
    public static final int BASE_DAMAGE = 1;
    /**
     * 基础攻速
     */
    public static final int BASE_SPEED = 4;
    protected final Ingredient repairMaterial;
    protected final float materialDamage;
    protected final int durability;
    protected final int enchantability;
    protected final int miningLevel;
    protected final float miningSpeedMultiplier;

    /**
     * 构建工具
     *
     * @param repairMaterial        修复物品
     * @param materialDamage        材料增伤
     * @param durability            工具耐久
     * @param enchantability        附魔能力
     * @param miningLevel           开采等级
     * @param miningSpeedMultiplier 开采速度倍率
     */
    protected SRTool(Ingredient repairMaterial, float materialDamage, int durability, int enchantability, int miningLevel, float miningSpeedMultiplier) {
        this.repairMaterial = repairMaterial;
        this.materialDamage = materialDamage;
        this.durability = durability;
        this.enchantability = enchantability;
        this.miningLevel = miningLevel;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
    }

    /**
     * 获取锹
     */
    public ShovelItem createShovel(float damage, Item.Settings settings) {
        return new ShovelItem(this, getDamage(damage), getSpeed(1), settings);
    }

    /**
     * 获取镐
     */
    public PickaxeItem createPickaxe(float damage, Item.Settings settings) {
        return new PickaxeItem(this, (int) getDamage(damage), getSpeed(1.2F), settings);
    }

    /**
     * 获取斧
     */
    public AxeItem createAxe(float damage, float speed, Item.Settings settings) {
        return new AxeItem(this, getDamage(damage), getSpeed(speed), settings);
    }

    /**
     * 获取锄
     */
    public HoeItem createHoe(float speed, Item.Settings settings) {
        return new HoeItem(this, (int) getDamage(1), getSpeed(speed), settings);
    }

    /**
     * 获取剑
     */
    public SwordItem createSword(float damage, Item.Settings settings) {
        return new SwordItem(this, (int) getDamage(damage), getSpeed(1.6F), settings);
    }

    /**
     * 获取伤害
     *
     * @param damage 攻击伤害
     * @return 实际设置数据
     */
    private float getDamage(float damage) {
        return damage - BASE_DAMAGE - materialDamage;
    }

    /**
     * 获取攻速
     *
     * @param speed 攻击速度
     * @return 实际设置攻速
     */
    private float getSpeed(float speed) {
        return speed - BASE_SPEED;
    }

    /**
     * 获取工具耐久
     *
     * @return 工具耐久
     */
    @Override
    public int getDurability() {
        return durability;
    }

    /**
     * 获取开采速度倍率
     *
     * @return 开采速度倍率
     */
    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeedMultiplier;
    }

    /**
     * 获取材料增伤
     *
     * @return 材料增伤
     */
    @Override
    public float getAttackDamage() {
        return materialDamage;
    }

    /**
     * 获取开采等级
     *
     * @return 开采等级
     */
    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    /**
     * 获取附魔等能力
     *
     * @return 附魔能力
     */
    @Override
    public int getEnchantability() {
        return enchantability;
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
}