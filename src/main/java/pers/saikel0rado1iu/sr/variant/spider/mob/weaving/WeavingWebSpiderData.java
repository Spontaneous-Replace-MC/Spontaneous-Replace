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

package pers.saikel0rado1iu.sr.variant.spider.mob.weaving;

import pers.saikel0rado1iu.silk.util.TickUtil;

/**
 * <h2 style="color:FFC800">织网蜘蛛数据类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface WeavingWebSpiderData {
	/**
	 * 喷吐毒蛛 ID
	 */
	String ID = "weaving_web_spider";
	/**
	 * 血量
	 */
	float HP = 16;
	/**
	 * 伤害
	 */
	float DAMAGE = 0;
	/**
	 * 移速系数 | 移动速度: ~4.20m/s
	 */
	float SPEED_COEFFICIENT = 0.4F;
	/**
	 * 经验倍率
	 */
	float EXP_RADIO = 1.25F;
	/**
	 * 模型阴影半径
	 */
	float MODEL_SHADOW = 0.775F;
	/**
	 * 碰撞箱宽
	 */
	float BOX_WEIGHT = 1.4F;
	/**
	 * 碰撞箱高
	 */
	float BOX_HEIGHT = 0.9F;
	/**
	 * 蜘蛛皮肤颜色
	 */
	int SPIDER_SKIN_COLOR = 0x404040;
	/**
	 * 与目标范围
	 */
	float TARGET_RANGE = 4;
	/**
	 * 织网时长
	 */
	int WEAVE_TIME = TickUtil.getTick(1.5F);
	/**
	 * 织网间隔
	 */
	int WEAVE_INTERVAL = TickUtil.getTick(3);
	/**
	 * 织网个数
	 */
	int WEAVE_COUNT = 15;
}
