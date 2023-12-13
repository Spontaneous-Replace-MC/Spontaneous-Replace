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

package pers.saikel0rado1iu.sr.variant.spider.mob.larva;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderEntity;

import static pers.saikel0rado1iu.sr.variant.spider.mob.larva.SpiderLarvaData.ATTACK_RANGE;
import static pers.saikel0rado1iu.sr.variant.spider.mob.larva.SpiderLarvaData.RETREAT_RANGE;

/**
 * <h2 style="color:FFC800">幼蛛实体类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class SpiderLarvaEntity extends SpiderEntity {
	public SpiderLarvaEntity(EntityType<? extends net.minecraft.entity.mob.SpiderEntity> entityType, World world) {
		super(entityType, world);
		setExpPoint(SpiderLarvaData.EXP_RADIO);
	}
	
	/**
	 * 设置实体数值
	 */
	public static DefaultAttributeContainer.Builder createSpiderAttributes() {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, SpiderLarvaData.HP)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, SpiderLarvaData.DAMAGE)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, SpiderLarvaData.SPEED_COEFFICIENT);
	}
	
	/**
	 * 修正眼睛高度
	 */
	@Override
	protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
		return super.getActiveEyeHeight(pose, dimensions) / 2;
	}
	
	/**
	 * 重新分配目标逻辑
	 */
	@Override
	protected void initGoals() {
		super.initGoals();
		goalSelector.add(1, new FightGoal(this));
		goalSelector.add(3, new RetreatGoal(this, 1));
	}
	
	/**
	 * <p>战斗目标</p>
	 * 当实体与目标距离小于近战攻击范围时被激怒尝试攻击目标，攻击目标后如果与目标距离小于近战取消范围则取消攻击
	 */
	protected static class FightGoal extends AttackGoal {
		public FightGoal(net.minecraft.entity.mob.SpiderEntity spider) {
			super(spider);
		}
		
		@Override
		public boolean canStart() {
			if (mob.getTarget() != null) return super.canStart() && mob.distanceTo(mob.getTarget()) < ATTACK_RANGE;
			return false;
		}
		
		@Override
		public boolean shouldContinue() {
			return super.shouldContinue() && mob.distanceTo(mob.getTarget()) > RETREAT_RANGE;
		}
	}
	
	/**
	 * <p>撤退目标</p>
	 * 如果实体被激怒，实体与目标距离小于撤退范围时逃离，而实体与目标距离大于远程范围则停止撤退
	 */
	protected static class RetreatGoal extends EscapeDangerGoal {
		public RetreatGoal(PathAwareEntity mob, double speed) {
			super(mob, speed);
		}
		
		@Override
		protected boolean isInDanger() {
			if (mob.getTarget() == null) return super.isInDanger();
			else if (mob.distanceTo(mob.getTarget()) > ATTACK_RANGE) return false;
			return super.isInDanger() || mob.distanceTo(mob.getTarget()) < ATTACK_RANGE;
		}
	}
}
