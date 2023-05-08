package JavaGeneric;

/**
 * <b style="color:FFC800"><font size="+2">Functions：Java 通用函数</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">所有 Java 类都可以使用的通用函数</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/2/15 11:04
 */
public abstract class Functions {
    /**
     * 禁止实例化 Functions 通用函数类
     */
    private Functions() {
        throw new Error("请检查是否实例化 Functions 通用函数类");
    }

    /**
     * 异或运算
     *
     * @param a 判断 1
     * @param b 判断 2
     * @return 异或结果
     */
    public static boolean xor(boolean a, boolean b) {
        return (a || b) && !(a && b);
    }

    /**
     * 为解决大量 double 2 float 转换带来的代码难看臃肿
     *
     * @param v double 类型值
     * @return float 类型值
     */
    public static float toFloat(double v) {
        return (float) v;
    }

    /**
     * 转换弧度
     *
     * @param angle 角度
     * @return float 类型的弧度
     */
    public static float toRadians(double angle) {
        return toFloat(Math.toRadians(angle));
    }
}
