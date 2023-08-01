package pres.saikel_orado.spontaneous_replace.mod.variant.spider.data;


import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.List;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.IS_SPIDER_BIOME;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRMobSpawn.ifNotBiomeOnlySpawnInNight;

/**
 * <b style="color:FFC800"><font size="+2">SRSpiderEntity：《自然更替》蜘蛛实体类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">《自然更替》蜘蛛的基础实体属性，逻辑控制</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 6.0
 * | 创建于 2023/2/18 15:22
 */
@SuppressWarnings({"unused", "deprecation"})
public abstract class SRSpiderEntity extends SpiderEntity {
    /**
     * 行走动画状态
     */
    // region 变量声明
    public final AnimationState walkingAnimationState = new AnimationState();
    public final AnimationState swimmingAnimationState = new AnimationState();
    public final AnimationState climbingAnimationState = new AnimationState();
    public final AnimationState jumpingAnimationState = new AnimationState();
    public final AnimationState attackingAnimationState = new AnimationState();
    public final AnimationState dyingAnimationState = new AnimationState();
    protected int jumpStopFlag = 0;
    // endregion

    public SRSpiderEntity(EntityType<? extends SpiderEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * 设置自然生成属性、设置
     *
     * @param world       世界访问
     * @param spawnReason 生成原因
     */
    @Override
    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        return ifNotBiomeOnlySpawnInNight(world, this, IS_SPIDER_BIOME)
                && getPathfindingFavor(getBlockPos(), world) >= 0;
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
        if (getWorld().isClient) {
            if (getHealth() > 0) {
                // 应该行走
                if (shouldWalk())
                    walkingAnimationState.startIfNotRunning(age);
                else walkingAnimationState.stop();
                // 应该游泳
                if (shouldSwim())
                    swimmingAnimationState.startIfNotRunning(age);
                else swimmingAnimationState.stop();
                // 应该跳跃
                if (shouldJump())
                    jumpingAnimationState.startIfNotRunning(age);
                else {
                    // 为跳跃动画延长 2 Tick 以保证动画被完全播放
                    if (jumpingAnimationState.isRunning())
                        jumpStopFlag++;
                    if (jumpStopFlag > 2) {
                        jumpingAnimationState.stop();
                        jumpStopFlag = 0;
                    }
                }
                // 是否在爬墙
                if (isClimbingWall())
                    climbingAnimationState.startIfNotRunning(age);
                else climbingAnimationState.stop();
                // 设置死亡动画
            } else {
                dyingAnimationState.startIfNotRunning(age);
                walkingAnimationState.startIfNotRunning(age);
            }
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
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        try {
            MethodType methodType = MethodType.methodType(EntityData.class, ServerWorldAccess.class, LocalDifficulty.class, SpawnReason.class, EntityData.class, NbtCompound.class);
            MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(HostileEntity.class, MethodHandles.lookup());

            MethodHandle methodHandle = lookup.findSpecial(HostileEntity.class,
                    HostileEntity.class.getName().contains("class_") ? "method_5943" : "initialize",
                    methodType, HostileEntity.class).bindTo(this);

            return (EntityData) methodHandle.invoke(world, difficulty, spawnReason, entityData, entityNbt);
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
    protected void setExpPoint(double radio) {
        experiencePoints *= radio;
    }

    /**
     * 设置经验点数
     *
     * @param expPoint 经验点
     */
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
        public AttackGoal(SpiderEntity spider) {
            super(spider, 1.0, true);
        }

        @Override
        public boolean canStart() {
            return super.canStart() && !mob.hasPassengers();
        }

        @Override
        public boolean shouldContinue() {
            float f = mob.getBrightnessAtEyes();
            if (f >= 0.5f && mob.getRandom().nextInt(100) == 0) {
                mob.setTarget(null);
                return false;
            }
            return super.shouldContinue();
        }

        @Override
        protected double getSquaredMaxAttackDistance(LivingEntity entity) {
            return 4.0f + entity.getWidth();
        }
    }

    /**
     * 蜘蛛默认低光照主动攻击目标
     */
    protected static class TargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
        public TargetGoal(SpiderEntity spider, Class<T> targetEntityClass) {
            super(spider, targetEntityClass, true);
        }

        @Override
        public boolean canStart() {
            float f = mob.getBrightnessAtEyes();
            if (f >= 0.5f) {
                return false;
            }
            return super.canStart();
        }
    }

    /**
     * 所有《自然更替》蜘蛛变体群体攻击目标
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
         * 设置成所有《自然更替》蜘蛛集体复仇
         */
        @Override
        protected void callSameTypeForRevenge() {
            double d = getFollowRange();
            Box box = Box.from(mob.getPos()).expand(d, 10.0, d);
            List<? extends MobEntity> list = mob.getWorld().getEntitiesByClass(SRSpiderEntity.class, box, EntityPredicates.EXCEPT_SPECTATOR);
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
