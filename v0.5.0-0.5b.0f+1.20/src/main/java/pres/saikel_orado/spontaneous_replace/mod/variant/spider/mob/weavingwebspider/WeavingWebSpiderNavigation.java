package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.weavingwebspider;

import pres.saikel_orado.spontaneous_replace.java.Functions;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.weavingwebspider.WeavingWebSpiderData.TARGET_RANGE;
import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.weavingwebspider.WeavingWebSpiderData.WEAVE_TIME;

/**
 * <b style="color:FFC800"><font size="+2">WeavingWebSpiderNavigation：织网蜘蛛导航类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">用作织网蜘蛛的寻路导航</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2023/1/19 18:44
 */
public class WeavingWebSpiderNavigation extends MobNavigation {
    @Nullable
    private BlockPos targetPos;
    /**
     * 获取导航实体，如果实体有目标，则禁用爬墙和目标追寻
     */
    private final MobEntity mob;

    /**
     * 构建寻路
     */
    public WeavingWebSpiderNavigation(MobEntity mobEntity, World world) {
        super(mobEntity, world);
        mob = mobEntity;
    }

    /**
     * 查找路径
     */
    @Override
    public Path findPathTo(BlockPos target, int distance) {
        targetPos = target;
        return super.findPathTo(target, distance);
    }

    /**
     * 查找路径
     */
    @Override
    public Path findPathTo(net.minecraft.entity.Entity entity, int distance) {
        targetPos = entity.getBlockPos();
        return super.findPathTo(entity, distance);
    }

    /**
     * 开始移动到
     */
    @Override
    public boolean startMovingTo(net.minecraft.entity.Entity entity, double speed) {
        Path path = findPathTo(entity, 0);
        if (path != null) {
            return startMovingAlong(path, speed);
        }
        targetPos = entity.getBlockPos();
        this.speed = speed;
        return true;
    }

    /**
     * 每 Tick 进行判断
     */
    @Override
    public void tick() {
        // 如果没有播放筑网动画
        if (mob instanceof WeavingWebSpiderEntity spider && spider.weaveAnimeTime >= WEAVE_TIME) {
            boolean flag = false;
            if (mob.getTarget() != null)
                flag = (mob.distanceTo(mob.getTarget()) > TARGET_RANGE);
            if (Functions.xor(flag, mob.getTarget() == null)) {
                if (isIdle()) {
                    if (targetPos != null) {
                        if (!targetPos.isWithinDistance(entity.getPos(), entity.getWidth()) && (!(entity.getY() > (double) targetPos.getY()) || !BlockPos.ofFloored(targetPos.getX(), entity.getY(), targetPos.getZ()).isWithinDistance(entity.getPos(), entity.getWidth()))) {
                            this.entity.getMoveControl().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), speed);
                        } else {
                            targetPos = null;
                        }
                    }
                    return;
                }
            } else {
                Vec3d vec3d = NoPenaltyTargeting.find((PathAwareEntity) mob, 5, 4);
                if (vec3d != null)
                    mob.getNavigation().startMovingTo(vec3d.x, vec3d.y, vec3d.z, speed);
            }
        }
        super.tick();
    }
}
