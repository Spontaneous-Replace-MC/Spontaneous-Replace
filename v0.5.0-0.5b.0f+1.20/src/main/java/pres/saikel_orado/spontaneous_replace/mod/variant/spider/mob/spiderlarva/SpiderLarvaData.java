package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spiderlarva;

/**
 * <b style="color:FFC800"><font size="+2">SpiderLarvaData：幼蛛数据类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">幼蛛的所有数据</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/21 10:54
 */
public final class SpiderLarvaData {
    /**
     * 幼蛛 ID
     */
    public static final String ID = "spider_larva";
    /**
     * 血量
     */
    public static final float HP = 6;
    /**
     * 伤害
     */
    public static final float DAMAGE = 1;
    /**
     * 移速系数 | 移动速度: ~4.20m/s
     */
    public static final float SPEED_COEFFICIENT = 0.4F;
    /**
     * 经验倍率
     */
    public static final float EXP_RADIO = 0.5F;
    /**
     * 模型阴影半径
     */
    public static final float MODEL_SHADOW = 0.35F;
    /**
     * 模型缩放
     */
    public static final float MODEL_SCALE = 0.5F;
    /**
     * 碰撞箱宽
     */
    public static final float BOX_WEIGHT = 0.75F;
    /**
     * 碰撞箱高
     */
    public static final float BOX_HEIGHT = 0.5F;
    /**
     * 蜘蛛皮肤颜色
     */
    public static final int SPIDER_SKIN_COLOR = 0xFFFDE6;
    /**
     * 攻击范围
     */
    public static final float ATTACK_RANGE = 3;
    /**
     * 撤退范围
     */
    public static final float RETREAT_RANGE = 1F;

    private SpiderLarvaData() throws ExceptionInInitializerError {
        throw new ExceptionInInitializerError();
    }
}