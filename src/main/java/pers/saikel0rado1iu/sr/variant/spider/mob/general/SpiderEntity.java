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

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.List;

/**
 * <h2 style="color:FFC800">自然更替的蜘蛛实体类</h2>
 * <p style="color:FFC800">自然更替的蜘蛛的基础实体属性，逻辑控制</p>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public abstract class SpiderEntity extends net.minecraft.entity.mob.SpiderEntity {
	public final AnimationState walkingAnimationState = new AnimationState();
	public final AnimationState swimmingAnimationState = new AnimationState();
	public final AnimationState climbingAnimationState = new AnimationState();
	public final AnimationState jumpingAnimationState = new AnimationState();
	public final AnimationState attackingAnimationState = new AnimationState();
	public final AnimationState dyingAnimationState = new AnimationState();
	protected int jumpStopFlag = 0;
	
	public SpiderEntity(EntityType<? extends net.minecraft.entity.mob.SpiderEntity> entityType, World world) {
		super(entityType, world);
	}
	
	@Override
	public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
		return true;
	}
	
	@Override
	public boolean canSpawn(WorldView world) {
		return true;
	}
	
	/**
	 * 重载处理坠落伤害函数，使蜘蛛不再遭受坠落伤害
	 */
	@Override
	public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
		return false;
	}
	
	/**
	 * 设置生物动画
	 */
	@Override
	public void tick() {
		super.tick();
		// 只在客户端进行判断
		if (!getWorld().isClient) return;
		if (getHealth() > 0) {
			// 应该行走
			if (shouldWalk()) walkingAnimationState.startIfNotRunning(age);
			else walkingAnimationState.stop();
			// 应该游泳
			if (shouldSwim()) swimmingAnimationState.startIfNotRunning(age);
			else swimmingAnimationState.stop();
			// 应该跳跃
			if (shouldJump()) {
				jumpingAnimationState.startIfNotRunning(age);
			} else {
				// 为跳跃动画延长 2 Tick 以保证动画被完全播放
				if (jumpingAnimationState.isRunning()) jumpStopFlag++;
				if (jumpStopFlag > 2) {
					jumpingAnimationState.stop();
					jumpStopFlag = 0;
				}
			}
			// 是否在爬墙
			if (isClimbingWall()) climbingAnimationState.startIfNotRunning(age);
			else climbingAnimationState.stop();
			// 设置死亡动画
		} else {
			dyingAnimationState.startIfNotRunning(age);
			walkingAnimationState.startIfNotRunning(age);
		}
	}
	
	/**
	 * 重新分配目标逻辑
	 */
	@Override
	protected void initGoals() {
		goalSelector.add(1, new SwimGoal(this));
		goalSelector.add(5, new WanderAroundFarGoal(this, 0.8));
		goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
		goalSelector.add(6, new LookAroundGoal(this));
		targetSelector.add(1, new SpiderRevengeGoal(this).setGroupRevenge());
	}
	
	/**
	 * <p>重写实体初始化函数，使用一个内部类替换抽象祖父类实例</p>
	 * <p>然后使用方法句柄方式跳过父类调用祖父类实例的初始化代码重新编写自定义初始化</p>
	 */
	@SuppressWarnings("ConstantValue")
	@Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
		try {
			MethodType methodType = MethodType.methodType(EntityData.class, ServerWorldAccess.class, LocalDifficulty.class, SpawnReason.class, EntityData.class);
			MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(HostileEntity.class, MethodHandles.lookup());
			
			MethodHandle methodHandle = lookup.findSpecial(HostileEntity.class,
					HostileEntity.class.getName().contains("class_") ? "method_5943" : "initialize",
					methodType, HostileEntity.class).bindTo(this);
			
			return (EntityData) methodHandle.invoke(world, difficulty, spawnReason, entityData);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 设置攻击标志
	 *
	 * @param target 攻击目标
	 */
	@Override
	public boolean tryAttack(Entity target) {
		getWorld().sendEntityStatus(this, EntityStatuses.PLAY_ATTACK_SOUND);
		return super.tryAttack(target);
	}
	
	/**
	 * 设置攻击动画
	 *
	 * @param status 状态
	 */
	@Override
	public void handleStatus(byte status) {
		if (status == EntityStatuses.PLAY_ATTACK_SOUND)
			attackingAnimationState.start(age);
	}
	
	/**
	 * 设置经验点数
	 *
	 * @param radio 经验倍率
	 */
	@SuppressWarnings("lossy-conversions")
	protected void setExpPoint(double radio) {
		experiencePoints *= radio;
	}
	
	/**
	 * 设置经验点数
	 *
	 * @param expPoint 经验点
	 */
	@SuppressWarnings("unused")
	protected void setExpPoint(int expPoint) {
		experiencePoints = expPoint;
	}
	
	/**
	 * 是否应该行走
	 *
	 * @return 如果应该走则返回 true
	 */
	protected boolean shouldWalk() {
		return isOnGround() && getVelocity().horizontalLengthSquared() > 1.0E-6 && !isInsideWaterOrBubbleColumn();
	}
	
	/**
	 * 是否应该游泳
	 *
	 * @return 如果应该游则返回 true
	 */
	protected boolean shouldSwim() {
		return getVelocity().horizontalLengthSquared() > 1.0E-6 && isInsideWaterOrBubbleColumn();
	}
	
	/**
	 * 是否应该跳跃
	 *
	 * @return 如果应该跳则返回 true
	 */
	protected boolean shouldJump() {
		return !isOnGround() && !isInsideWaterOrBubbleColumn() && !isClimbingWall();
	}
	
	/**
	 * 设置默认攻击目标，最好在 initGoals() 函数中分配
	 */
	protected void setDefaultAttackGoals() {
		this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.4f));
		goalSelector.add(4, new AttackGoal(this));
	}
	
	/**
	 * 设置默认攻击主动攻击目标目标，最好在 initGoals() 函数中分配
	 */
	protected void setDefaultTargetGoals() {
		targetSelector.add(2, new TargetGoal<>(this, PlayerEntity.class));
		targetSelector.add(3, new TargetGoal<>(this, IronGolemEntity.class));
	}
	
	/**
	 * 蜘蛛默认攻击目标
	 */
	protected static class AttackGoal extends MeleeAttackGoal {
		public AttackGoal(net.minecraft.entity.mob.SpiderEntity spider) {
			super(spider, 1.0, true);
		}
		
		@Override
		public boolean canStart() {
			return super.canStart() && !mob.hasPassengers();
		}
		
		@Override
		@SuppressWarnings("deprecation")
		public boolean shouldContinue() {
			float f = mob.getBrightnessAtEyes();
			if (f >= 0.5f && mob.getRandom().nextInt(100) == 0) {
				mob.setTarget(null);
				return false;
			}
			return super.shouldContinue();
		}
	}
	
	/**
	 * 蜘蛛默认低光照主动攻击目标
	 */
	protected static class TargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
		public TargetGoal(net.minecraft.entity.mob.SpiderEntity spider, Class<T> targetEntityClass) {
			super(spider, targetEntityClass, true);
		}
		
		@Override
		@SuppressWarnings("deprecation")
		public boolean canStart() {
			float f = mob.getBrightnessAtEyes();
			if (f >= 0.5f) return false;
			return super.canStart();
		}
	}
	
	/**
	 * 所有自然更替的蜘蛛变体群体攻击目标
	 */
	protected static class SpiderRevengeGoal extends RevengeGoal {
		/**
		 * 创建受保护的 noHelpTypes 来越过私有的 noHelpTypes
		 */
		protected Class<?>[] noHelpTypes;
		
		/**
		 * 构建目标器
		 */
		public SpiderRevengeGoal(PathAwareEntity mob, Class<?>... noRevengeTypes) {
			super(mob, noRevengeTypes);
		}
		
		/**
		 * 覆盖设置群体复仇用来获取 noHelpTypes 参数
		 */
		@Override
		public RevengeGoal setGroupRevenge(Class<?>... noHelpTypes) {
			this.noHelpTypes = noHelpTypes;
			return super.setGroupRevenge(noHelpTypes);
		}
		
		/**
		 * 设置成所有自然更替的蜘蛛集体复仇
		 */
		@Override
		protected void callSameTypeForRevenge() {
			double d = getFollowRange();
			Box box = Box.from(mob.getPos()).expand(d, 10.0, d);
			List<? extends MobEntity> list = mob.getWorld().getEntitiesByClass(SpiderEntity.class, box, EntityPredicates.EXCEPT_SPECTATOR);
			for (MobEntity mobEntity : list) {
				if (mob == mobEntity || mobEntity.getTarget() != null || mobEntity.isTeammate(mob.getAttacker()))
					continue;
				if (noHelpTypes != null) {
					boolean bl = false;
					for (Class<?> aClass : noHelpTypes) {
						if (mobEntity.getClass() != aClass) continue;
						bl = true;
						break;
					}
					if (bl) continue;
				}
				setMobEntityTarget(mobEntity, mob.getAttacker());
			}
		}
	}
}
