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

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderEntity;

/**
 * <h2 style="color:FFC800">蜘蛛卫兵实体类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class GuardSpiderEntity extends SpiderEntity {
	/**
	 * 构建实体
	 */
	public GuardSpiderEntity(EntityType<? extends net.minecraft.entity.mob.SpiderEntity> entityType, World world) {
		super(entityType, world);
		setExpPoint(GuardSpiderData.EXP_RADIO);
	}
	
	/**
	 * 设置实体数值
	 */
	public static DefaultAttributeContainer.Builder createSpiderAttributes() {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, GuardSpiderData.HP)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, GuardSpiderData.DAMAGE)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, GuardSpiderData.SPEED_COEFFICIENT)
				.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, GuardSpiderData.KNOCK_BACK_RESISTANCE)
				.add(EntityAttributes.GENERIC_ARMOR, GuardSpiderData.ARMOR);
	}
	
	/**
	 * 重新分配目标逻辑
	 */
	@Override
	protected void initGoals() {
		super.initGoals();
		setDefaultAttackGoals();
		setDefaultTargetGoals();
	}
}
