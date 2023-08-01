package pres.saikel_orado.spontaneous_replace.mod.variant.spider.data;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;

/**
 * <b style="color:FFC800"><font size="+2">SRSpiderData：蜘蛛通用数据</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">专门用于蜘蛛群系内容专用数据</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 6.0
 * | 创建于 2023/2/3 15:42
 */
public final class SRSpiderData {
    /**
     * 蛛网“材料”
     * 地图颜色：浅灰；阻塞活塞
     */
    public static final AbstractBlock.Settings COBWEB = AbstractBlock.Settings.create()
            .mapColor(MapColor.WHITE_GRAY)
            .pistonBehavior(PistonBehavior.BLOCK);

    /**
     * 黏密蛛网“材料”
     * 地图颜色：浅黄；可以燃烧；透光；阻塞活塞
     */
    public static final AbstractBlock.Settings STICKY_COMPACT_COBWEB = AbstractBlock.Settings.create()
            .mapColor(MapColor.PALE_YELLOW)
            .burnable()
            .nonOpaque()
            .pistonBehavior(PistonBehavior.BLOCK);
    /**
     * 丝化土减速幅度
     */
    public static final float COBWEBBY_SOIL_DECELERATION = 0.75F;
    /**
     * 黏密蛛网强度倍率
     */
    public static final float STRENGTH_RADIO = 10;
    /**
     * 蜘蛛网强度
     */
    public static final float COBWEB_STRENGTH = 4;
    /**
     * 黏密蛛网强度
     */
    public static final float STICKY_COMPACT_COBWEB_STRENGTH = COBWEB_STRENGTH * STRENGTH_RADIO;
    /**
     * 黏密蛛网减速强度倍率
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
     * 黏密蛛网水平减速
     */
    public static final float STICKY_COMPACT_COBWEB_DECELERATION = COBWEB_DECELERATION / DECELERATION_RADIO;
    /**
     * 黏密蛛网垂直减速
     */
    public static final float STICKY_COMPACT_COBWEB_DROP = COBWEB_DROP / DECELERATION_RADIO;
    /**
     * 蜘蛛网挖掘增幅
     */
    public static final float COBWEB_MINING_SPEED = 15;
    /**
     * 黏密蛛网一级挖掘增幅
     */
    public static final float STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_1 = COBWEB_MINING_SPEED;
    /**
     * 黏密蛛网二级挖掘增幅
     */
    public static final float STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_2 = STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_1 * 10;
    /**
     * 黏密蛛网三级挖掘增幅
     */
    public static final float STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_3 = STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_2 * 10;
    /**
     * 黏密蛛网燃烧机会
     */
    public static final int STICKY_COMPACT_COBWEB_BURN_CHANCE = 30;
    /**
     * 黏密蛛网扩散机会
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
     * <p>SYSTEM_SET：特殊系统放置，用于检测是否是由系统放置并进行特殊放置</p>
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

        @Override
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

    private SRSpiderData() throws ExceptionInInitializerError {
        throw new ExceptionInInitializerError();
    }
}
