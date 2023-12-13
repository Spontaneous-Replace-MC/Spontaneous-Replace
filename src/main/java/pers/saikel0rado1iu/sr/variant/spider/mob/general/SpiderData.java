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

package pers.saikel0rado1iu.sr.variant.spider.mob.general;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;

/**
 * <h2 style="color:FFC800">自然更替的蜘蛛通用数据</h2>
 * <p style="color:FFC800">专门用于自然更替的蜘蛛群系内容专用数据</p>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface SpiderData {
	/**
	 * 蛛网“材料”
	 * 地图颜色：浅灰；阻塞活塞
	 */
	AbstractBlock.Settings COBWEB = AbstractBlock.Settings.create()
			.mapColor(MapColor.WHITE_GRAY)
			.pistonBehavior(PistonBehavior.BLOCK);
	
	/**
	 * 黏密蛛网“材料”
	 * 地图颜色：浅黄；可以燃烧；透光；阻塞活塞
	 */
	AbstractBlock.Settings STICKY_COMPACT_COBWEB = AbstractBlock.Settings.create()
			.mapColor(MapColor.PALE_YELLOW)
			.burnable()
			.nonOpaque()
			.pistonBehavior(PistonBehavior.BLOCK);
	/**
	 * 丝化土减速幅度
	 */
	float COBWEBBY_SOIL_DECELERATION = 0.75F;
	/**
	 * 黏密蛛网强度倍率
	 */
	float STRENGTH_RADIO = 10;
	/**
	 * 蜘蛛网强度
	 */
	float COBWEB_STRENGTH = 4;
	/**
	 * 黏密蛛网强度
	 */
	float STICKY_COMPACT_COBWEB_STRENGTH = COBWEB_STRENGTH * STRENGTH_RADIO;
	/**
	 * 黏密蛛网减速强度倍率
	 */
	float DECELERATION_RADIO = 3;
	/**
	 * 蜘蛛网水平减速
	 */
	float COBWEB_DECELERATION = 0.25F;
	/**
	 * 蜘蛛网垂直减速
	 */
	float COBWEB_DROP = 0.05F;
	/**
	 * 黏密蛛网水平减速
	 */
	float STICKY_COMPACT_COBWEB_DECELERATION = COBWEB_DECELERATION / DECELERATION_RADIO;
	/**
	 * 黏密蛛网垂直减速
	 */
	float STICKY_COMPACT_COBWEB_DROP = COBWEB_DROP / DECELERATION_RADIO;
	/**
	 * 蜘蛛网挖掘增幅
	 */
	float COBWEB_MINING_SPEED = 15;
	/**
	 * 黏密蛛网一级挖掘增幅
	 */
	float STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_1 = COBWEB_MINING_SPEED;
	/**
	 * 黏密蛛网二级挖掘增幅
	 */
	float STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_2 = STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_1 * 10;
	/**
	 * 黏密蛛网三级挖掘增幅
	 */
	float STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_3 = STICKY_COMPACT_COBWEB_DROP_MINING_SPEED_LEVEL_2 * 10;
	/**
	 * 黏密蛛网燃烧机会
	 */
	int STICKY_COMPACT_COBWEB_BURN_CHANCE = 5;
	/**
	 * 黏密蛛网扩散机会
	 */
	int STICKY_COMPACT_COBWEB_SPREAD_CHANCE = 20;
	/**
	 * 茧蛹样式属性
	 */
	EnumProperty<ChrysalisStyle> CHRYSALIS_STYLE = EnumProperty.of("style", ChrysalisStyle.class);
	/**
	 * 蜘蛛眼颜色
	 */
	int SPIDER_EYES_COLOR = 0xA80E0E;
	
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
	enum ChrysalisStyle implements StringIdentifiable {
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
}
