/*
 * MIT License
 *
 * Copyright (c) 2023 GameGeek-Saikel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package pers.saikel0rado1iu.sr.variant.spider.mob.guard;

/**
 * <p><b style="color:FFC800"><font size="+1">蜘蛛卫兵数据类</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public interface GuardSpiderData {
	/**
	 * 蜘蛛卫兵 ID
	 */
	String ID = "guard_spider";
	/**
	 * 血量
	 */
	float HP = 20;
	/**
	 * 伤害
	 */
	float DAMAGE = 4;
	/**
	 * 移速系数 | 移动速度: ~2.25m/s
	 */
	float SPEED_COEFFICIENT = 0.3F;
	/**
	 * 击退抗性
	 */
	float KNOCK_BACK_RESISTANCE = 0.3F;
	/**
	 * 护甲值
	 */
	float ARMOR = 4;
	/**
	 * 经验倍率
	 */
	float EXP_RADIO = 2;
	/**
	 * 模型阴影半径
	 */
	float MODEL_SHADOW = 0.875F;
	/**
	 * 模型缩放
	 */
	float MODEL_SCALE = 1.2F;
	/**
	 * 碰撞箱宽
	 */
	float BOX_WEIGHT = 1.75F;
	/**
	 * 碰撞箱高
	 */
	float BOX_HEIGHT = 1;
	/**
	 * 蜘蛛皮肤颜色
	 */
	int SPIDER_SKIN_COLOR = 0x4D4600;
}
