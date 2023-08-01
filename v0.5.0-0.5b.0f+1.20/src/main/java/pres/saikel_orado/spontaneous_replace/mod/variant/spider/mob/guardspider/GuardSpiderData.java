package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.guardspider;

/**
 * <b style="color:FFC800"><font size="+2">GuardSpiderData：蜘蛛卫兵数据类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">蜘蛛卫兵的所有数据</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/2/18 19:23
 */
public final class GuardSpiderData {
    /**
     * 蜘蛛卫兵 ID
     */
    public static final String ID = "guard_spider";
    /**
     * 血量
     */
    public static final float HP = 20;
    /**
     * 伤害
     */
    public static final float DAMAGE = 4;
    /**
     * 移速系数 | 移动速度: ~2.25m/s
     */
    public static final float SPEED_COEFFICIENT = 0.3F;
    /**
     * 击退抗性
     */
    public static final float KNOCK_BACK_RESISTANCE = 0.3F;
    /**
     * 护甲值
     */
    public static final float ARMOR = 4;
    /**
     * 经验倍率
     */
    public static final float EXP_RADIO = 2;
    /**
     * 模型阴影半径
     */
    public static final float MODEL_SHADOW = 0.875F;
    /**
     * 模型缩放
     */
    public static final float MODEL_SCALE = 1.2F;
    /**
     * 碰撞箱宽
     */
    public static final float BOX_WEIGHT = 1.75F;
    /**
     * 碰撞箱高
     */
    public static final float BOX_HEIGHT = 1;
    /**
     * 蜘蛛皮肤颜色
     */
    public static final int SPIDER_SKIN_COLOR = 0x4D4600;

    private GuardSpiderData() throws ExceptionInInitializerError {
        throw new ExceptionInInitializerError();
    }
}
