package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spiderlarva;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.world.World;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderEntity;

import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spiderlarva.SpiderLarvaData.*;

/**
 * <b style="color:FFC800"><font size="+2">SpiderLarvaEntity：幼蛛实体类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">幼蛛的所有实体属性，逻辑控制</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/21 10:55
 */
public class SpiderLarvaEntity extends SRSpiderEntity {
    public SpiderLarvaEntity(EntityType<? extends SpiderEntity> entityType, World world) {
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
        public FightGoal(SpiderEntity spider) {
            super(spider);

        }

        @Override
        public boolean canStart() {
            if (mob.getTarget() != null)
                return super.canStart() && mob.distanceTo(mob.getTarget()) < ATTACK_RANGE;
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
            if (mob.getTarget() == null)
                return super.isInDanger();
            else if (mob.distanceTo(mob.getTarget()) > ATTACK_RANGE)
                return false;
            return super.isInDanger() || mob.distanceTo(mob.getTarget()) < ATTACK_RANGE;
        }
    }
}
