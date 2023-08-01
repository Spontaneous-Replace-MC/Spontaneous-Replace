package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider;

import static pres.saikel_orado.spontaneous_replace.mod.util.SRUtil.getTick;

/**
 * <b style="color:FFC800"><font size="+2">SprayPoisonSpiderData：喷吐毒蛛数据类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">喷吐毒蛛的所有数据</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/2/18 20:24
 */
public final class SprayPoisonSpiderData {
    /**
     * 喷吐毒蛛 ID
     */
    public static final String ID = "spray_poison_spider";
    /**
     * 血量
     */
    public static final float HP = 12;
    /**
     * 伤害
     */
    public static final float DAMAGE = 2;
    /**
     * 移速系数 | 移动速度: ~2.25m/s
     */
    public static final float SPEED_COEFFICIENT = 0.3F;
    /**
     * 经验倍率
     */
    public static final float EXP_RADIO = 1.5F;
    /**
     * 模型阴影半径
     */
    public static final float MODEL_SHADOW = 0.75F;
    /**
     * 模型缩放
     */
    public static final float MODEL_SCALE = 0.9F;
    /**
     * 碰撞箱宽
     */
    public static final float BOX_WEIGHT = 1.5F;
    /**
     * 碰撞箱高
     */
    public static final float BOX_HEIGHT = 0.85F;
    /**
     * 蜘蛛皮肤颜色
     */
    public static final int SPIDER_SKIN_COLOR = 0x0F5000;
    /**
     * 逃离速度增幅
     */
    public static final float ESCAPE_SPEED_RADIO = 1.5F;
    /**
     * 速度增幅
     */
    public static final float SPEED_RADIO = 1.25F;
    /**
     * 射击间隔
     */
    public static final int SHOOT_INTERVAL = getTick(2);
    /**
     * 最大射击范围
     */
    public static final float MAX_SHOOT_RANGE = 20;
    /**
     * 近战攻击范围
     */
    public static final float MELEE_ATTACK_RANGE = 3;
    /**
     * 近战取消范围
     */
    public static final float MELEE_CANCEL_RANGE = 2.5F;
    /**
     * 逃离范围
     */
    public static final float ESCAPE_RANGE = 7;

    private SprayPoisonSpiderData() throws ExceptionInInitializerError {
        throw new ExceptionInInitializerError();
    }
}
