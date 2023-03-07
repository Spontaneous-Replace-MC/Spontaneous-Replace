package SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider;

import SpontaneousReplace.SpiderBiome.Generic.SRSpider.SRSpiderEntity;
import SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Toxin.ToxinEntity;
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
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.world.World;

import static SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Data.*;
import static SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Register.SPRAY_TOXIN;

/**
 * <b style="color:FFC800"><font size="+2">Entity：喷吐毒蛛实体类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">喷吐毒蛛的所有实体属性，逻辑控制</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/01/06
 */
public class Entity extends SRSpiderEntity implements RangedAttackMob {
    public AnimationState sprayingAnimationState = new AnimationState();

    /**
     * 构建喷吐毒蛛
     */
    protected Entity(EntityType<? extends SpiderEntity> entityType, World world) {
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
        return new Navigation(this, world);
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
    public void attack(LivingEntity target, float pullProgress) {
        world.sendEntityStatus(this, (byte) -EntityStatuses.PLAY_ATTACK_SOUND);
        ToxinEntity toxinEntity = new ToxinEntity(world, this);
        toxinEntity.setVelocity(
                target.getX() - getX(),
                target.getBodyY(0.33) - toxinEntity.getY() + Math.sqrt(Math.pow(target.getX() - getX(), 2) + Math.pow(target.getZ() - getZ(), 2)) * 0.2,
                target.getZ() - getZ(),
                1.5F, 5F);
        if (!isSilent()) {
            world.playSound(null, getX(), getY(), getZ(), SPRAY_TOXIN, getSoundCategory(), 1F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
        }
        world.spawnEntity(toxinEntity);
    }

    /**
     * <p>近战目标</p>
     * 当实体与目标距离小于近战攻击范围时被激怒尝试攻击目标，攻击目标后如果与目标距离小于近战取消范围则取消攻击
     */
    protected static class MeleeGoal extends AttackGoal {
        public MeleeGoal(SpiderEntity spider) {
            super(spider);

        }

        @Override
        public boolean canStart() {
            if (mob.getTarget() != null)
                return super.canStart() && mob.distanceTo(mob.getTarget()) < MELEE_ATTACK_RANGE;
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
            if (mob.getTarget() == null)
                return super.isInDanger();
            else if (mob.distanceTo(mob.getTarget()) > ESCAPE_RANGE)
                return false;
            return super.isInDanger() || mob.distanceTo(mob.getTarget()) < ESCAPE_RANGE;
        }
    }
}
