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

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderEntity;

import static pers.saikel0rado1iu.sr.data.SoundEvents.SPRAY_TOXIN;
import static pers.saikel0rado1iu.sr.variant.spider.mob.spray.SprayPoisonSpiderData.*;

/**
 * <h2 style="color:FFC800">喷吐毒蛛实体类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class SprayPoisonSpiderEntity extends SpiderEntity implements RangedAttackMob {
	protected final AnimationState sprayingAnimationState = new AnimationState();
	
	/**
	 * 构建喷吐毒蛛
	 */
	public SprayPoisonSpiderEntity(EntityType<? extends net.minecraft.entity.mob.SpiderEntity> entityType, World world) {
		super(entityType, world);
		setExpPoint(EXP_RADIO);
	}
	
	/**
	 * 设置实体数据
	 */
	public static DefaultAttributeContainer.Builder createSpiderAttributes() {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, HP)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, DAMAGE)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, SPEED_COEFFICIENT);
	}
	
	/**
	 * 重新分配目标逻辑
	 */
	@Override
	protected void initGoals() {
		super.initGoals();
		goalSelector.add(1, new MeleeGoal(this));
		goalSelector.add(3, new EscapeGoal(this, ESCAPE_SPEED_RADIO));
		goalSelector.add(5, new ProjectileAttackGoal(this, SPEED_RADIO, SHOOT_INTERVAL, MAX_SHOOT_RANGE));
	}
	
	/**
	 * 创建实体导航，如果没有覆盖此方法，该实体的移动则会出现问题
	 */
	@Override
	protected EntityNavigation createNavigation(World world) {
		return new SprayPoisonSpiderNavigation(this, world);
	}
	
	/**
	 * 设置攻击动画
	 *
	 * @param status 状态
	 */
	@Override
	public void handleStatus(byte status) {
		super.handleStatus(status);
		if (status == -EntityStatuses.PLAY_ATTACK_SOUND)
			sprayingAnimationState.start(age);
	}
	
	/**
	 * 设置远程攻击
	 *
	 * @param target       目标
	 * @param pullProgress 拖拉进度
	 */
	@Override
	public void shootAt(LivingEntity target, float pullProgress) {
		getWorld().sendEntityStatus(this, (byte) -EntityStatuses.PLAY_ATTACK_SOUND);
		ToxinEntity toxinEntity = new ToxinEntity(getWorld(), this);
		toxinEntity.setVelocity(
				target.getX() - getX(),
				target.getBodyY(0.33) - toxinEntity.getY() + Math.sqrt(Math.pow(target.getX() - getX(), 2) + Math.pow(target.getZ() - getZ(), 2)) * 0.2,
				target.getZ() - getZ(),
				1.5F, 5F);
		if (!isSilent())
			getWorld().playSound(null, getX(), getY(), getZ(), SPRAY_TOXIN, getSoundCategory(), 1F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
		getWorld().spawnEntity(toxinEntity);
	}
	
	/**
	 * <p>近战目标</p>
	 * 当实体与目标距离小于近战攻击范围时被激怒尝试攻击目标，攻击目标后如果与目标距离小于近战取消范围则取消攻击
	 */
	protected static class MeleeGoal extends AttackGoal {
		public MeleeGoal(net.minecraft.entity.mob.SpiderEntity spider) {
			super(spider);
		}
		
		@Override
		public boolean canStart() {
			if (mob.getTarget() != null) return super.canStart() && mob.distanceTo(mob.getTarget()) < MELEE_ATTACK_RANGE;
			return false;
		}
		
		@Override
		public boolean shouldContinue() {
			return super.shouldContinue() && mob.distanceTo(mob.getTarget()) > MELEE_CANCEL_RANGE;
		}
	}
	
	
	/**
	 * <p>逃离目标</p>
	 * 如果实体被激怒，实体与目标距离小于远程范围时逃离，而实体与目标距离大于远程范围则停止逃离
	 */
	protected static class EscapeGoal extends EscapeDangerGoal {
		public EscapeGoal(PathAwareEntity mob, double speed) {
			super(mob, speed);
		}
		
		@Override
		protected boolean isInDanger() {
			if (mob.getTarget() == null) return super.isInDanger();
			else if (mob.distanceTo(mob.getTarget()) > ESCAPE_RANGE) return false;
			return super.isInDanger() || mob.distanceTo(mob.getTarget()) < ESCAPE_RANGE;
		}
	}
}
