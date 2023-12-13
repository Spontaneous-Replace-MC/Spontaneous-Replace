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

package pers.saikel0rado1iu.sr.variant.spider.mob.spray;

import pers.saikel0rado1iu.silk.util.TickUtil;

/**
 * <h2 style="color:FFC800">喷吐毒蛛数据类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface SprayPoisonSpiderData {
	/**
	 * 喷吐毒蛛 ID
	 */
	String ID = "spray_poison_spider";
	/**
	 * 血量
	 */
	float HP = 12;
	/**
	 * 伤害
	 */
	float DAMAGE = 2;
	/**
	 * 移速系数 | 移动速度: ~2.25m/s
	 */
	float SPEED_COEFFICIENT = 0.3F;
	/**
	 * 经验倍率
	 */
	float EXP_RADIO = 1.5F;
	/**
	 * 模型阴影半径
	 */
	float MODEL_SHADOW = 0.75F;
	/**
	 * 模型缩放
	 */
	float MODEL_SCALE = 0.9F;
	/**
	 * 碰撞箱宽
	 */
	float BOX_WEIGHT = 1.5F;
	/**
	 * 碰撞箱高
	 */
	float BOX_HEIGHT = 0.85F;
	/**
	 * 蜘蛛皮肤颜色
	 */
	int SPIDER_SKIN_COLOR = 0x0F5000;
	/**
	 * 逃离速度增幅
	 */
	float ESCAPE_SPEED_RADIO = 1.5F;
	/**
	 * 速度增幅
	 */
	float SPEED_RADIO = 1.25F;
	/**
	 * 射击间隔
	 */
	int SHOOT_INTERVAL = TickUtil.getTick(2);
	/**
	 * 最大射击范围
	 */
	float MAX_SHOOT_RANGE = 20;
	/**
	 * 近战攻击范围
	 */
	float MELEE_ATTACK_RANGE = 3;
	/**
	 * 近战取消范围
	 */
	float MELEE_CANCEL_RANGE = 2.5F;
	/**
	 * 逃离范围
	 */
	float ESCAPE_RANGE = 7;
}
