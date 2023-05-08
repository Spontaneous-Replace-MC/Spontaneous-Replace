package SpontaneousReplace.SpiderBiome.Generic;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.world.Difficulty;

/**
 * <b style="color:FFC800"><font size="+2">SpiderData：蜘蛛通用数据</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门用于蜘蛛群系内容专用数据</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/2/3 15:42
 */
public abstract class SpiderData {
    /**
     * 禁止实例化 SpiderData 蜘蛛数据类
     */
    private SpiderData() {
        throw new Error("请检查是否实例化 SpiderData 蜘蛛数据类");
    }

    /**
     * 蛛网“材料”
     * 地图颜色：浅灰；阻塞活塞
     */
    public static final Material COBWEB = new FabricMaterialBuilder(
            MapColor.WHITE_GRAY)
            .blocksPistons()
            .build();

    /**
     * 粘密蛛网“材料”
     * 地图颜色：浅黄；可以燃烧；透光；阻塞活塞
     */
    public static final Material STICKY_COMPACT_COBWEB = new FabricMaterialBuilder(
            MapColor.PALE_YELLOW)
            .burnable()
            .lightPassesThrough()
            .blocksPistons()
            .build();

    /**
     * 游戏难度
     */
    public static Difficulty difficulty = null;
    /**
     * 粘密蛛网强度倍率
     */
    public static final float STRENGTH_RADIO = 10;
    /**
     * 蜘蛛网强度
     */
    public static final float COBWEB_STRENGTH = 4;
    /**
     * 粘密蛛网强度
     */
    public static final float STICKY_COMPACT_COBWEB_STRENGTH = COBWEB_STRENGTH * STRENGTH_RADIO;
    /**
     * 粘密蛛网减速强度倍率
     */
    public static final float DECELERATION_RADIO = 3;
    /**
     * 蜘蛛网水平减速
     */
    public static final float COBWEB_DECELERATION = 0.25F;
    /**
     * 蜘蛛网垂直减速
     */
    public static final float COBWEB_DROP = 0.05F;
    /**
     * 粘密蛛网水平减速
     */
    public static final float STICKY_COMPACT_COBWEB_DECELERATION = COBWEB_DECELERATION / DECELERATION_RADIO;
    /**
     * 粘密蛛网垂直减速
     */
    public static final float STICKY_COMPACT_COBWEB_DROP = COBWEB_DROP / DECELERATION_RADIO;
    /**
     * 蜘蛛网挖掘增幅
     */
    public static final float COBWEB_MINING_SPEED = 15;
    /**
     * 粘密蛛网一级挖掘增幅
     */
    public static final float STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_1 = COBWEB_MINING_SPEED;
    /**
     * 粘密蛛网二级挖掘增幅
     */
    public static final float STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_2 = STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_1 * 10;
    /**
     * 粘密蛛网三级挖掘增幅
     */
    public static final float STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_3 = STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_2 * 10;
    /**
     * 粘密蛛网燃烧机会
     */
    public static final int STICKY_COMPACT_COBWEB_BURN_CHANCE = 30;
    /**
     * 粘密蛛网扩散机会
     */
    public static final int STICKY_COMPACT_COBWEB_SPREAD_CHANCE = 60;

    /**
     * <p>茧蛹样式：有 10 个样式</p>
     * <p>DEFAULT：默认</p>
     * <p>LARGE：大型</p>
     * <p>SMALL：小型</p>
     * <p>HUMANOID：类人型</p>
     * <p>VILLAGER：村民型</p>
     * <p>CHICKEN：小鸡型</p>
     * <p>CREEPER：苦力怕</p>
     * <p>IRON_GOLEM：铁傀儡</p>
     * <p>PLACEHOLDER：占位模型</p>
     * <p>PLACEHOLDER_SHORT：短式占位模型</p>
     */
    public enum ChrysalisStyle implements StringIdentifiable {
        DEFAULT("default"),
        LARGE("large"),
        SMALL("small"),
        HUMANOID("humanoid"),
        VILLAGER("villager"),
        CHICKEN("chicken"),
        CREEPER("creeper"),
        IRON_GOLEM("iron_golem"),
        PLACEHOLDER("placeholder"),
        PLACEHOLDER_SHORT("placeholder_short");

        private final String style;

        ChrysalisStyle(String name) {
            style = name;
        }

        public String toString() {
            return style;
        }

        @Override
        public String asString() {
            return style;
        }
    }

    /**
     * 茧蛹样式属性
     */
    public static final EnumProperty<ChrysalisStyle> CHRYSALIS_STYLE = EnumProperty.of("style", ChrysalisStyle.class);

    /**
     * 蜘蛛眼颜色
     */
    public static final int SPIDER_EYES_COLOR = 0xA80E0E;
}
