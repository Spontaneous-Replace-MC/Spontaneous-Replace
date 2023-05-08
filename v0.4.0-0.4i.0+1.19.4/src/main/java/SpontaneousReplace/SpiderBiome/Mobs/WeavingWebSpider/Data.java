package SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider;

import static SpontaneousReplace.Generic.SRData.getTick;

/**
 * <b style="color:FFC800"><font size="+2">Data：织网蜘蛛数据类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">织网蜘蛛的所有数据</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/2/19 15:08
 */
public abstract class Data {
    /**
     * 禁止实例化 Data 织网蜘蛛数据类
     */
    private Data() {
        throw new Error("请检查是否实例化 Data 织网蜘蛛数据类");
    }

    /**
     * 喷吐毒蛛 ID
     */
    public static final String ID = "weaving_web_spider";
    /**
     * 血量
     */
    public static final float HP = 16;
    /**
     * 伤害
     */
    public static final float DAMAGE = 0;
    /**
     * 移速系数 | 移动速度: ~4.20m/s
     */
    public static final float SPEED_COEFFICIENT = 0.4F;
    /**
     * 经验倍率
     */
    public static final float EXP_RADIO = 1.25F;
    /**
     * 模型阴影半径
     */
    public static final float MODEL_SHADOW = 0.775F;
    /**
     * 碰撞箱宽
     */
    public static final float BOX_WEIGHT = 1.4F;
    /**
     * 碰撞箱高
     */
    public static final float BOX_HEIGHT = 0.9F;
    /**
     * 蜘蛛皮肤颜色
     */
    public static final int SPIDER_SKIN_COLOR = 0x404040;
    /**
     * 与目标范围
     */
    public static final float TARGET_RANGE = 4;
    /**
     * 织网时长
     */
    public static final int WEAVE_TIME = getTick(1.5);
    /**
     * 织网间隔
     */
    public static final int WEAVE_INTERVAL = getTick(3);
    /**
     * 织网个数
     */
    public static final int WEAVE_COUNT = 15;
}
