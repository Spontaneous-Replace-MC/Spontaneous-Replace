package SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * <b style="color:FFC800"><font size="+2">Navigation: 喷吐毒蛛导航类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">用作喷吐毒蛛的寻路导航</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/1/15 17:13
 */
public class Navigation extends MobNavigation {
    @Nullable
    protected BlockPos targetPos;
    /**
     * 获取导航实体，如果实体有目标，则禁用爬墙和目标追寻
     */
    protected final MobEntity mobEntity;

    /**
     * 构建寻路
     */
    public Navigation(MobEntity mobEntity, World world) {
        super(mobEntity, world);
        this.mobEntity = mobEntity;
    }

    /**
     * 查找路径
     */
    @Override
    public Path findPathTo(BlockPos target, int distance) {
        this.targetPos = target;
        return super.findPathTo(target, distance);
    }

    /**
     * 查找路径
     */
    @Override
    public Path findPathTo(net.minecraft.entity.Entity entity, int distance) {
        this.targetPos = entity.getBlockPos();
        return super.findPathTo(entity, distance);
    }

    /**
     * 开始移动到
     */
    @Override
    public boolean startMovingTo(Entity entity, double speed) {
        if (mobEntity.getTarget() == null) {
            Path path = findPathTo(entity, 0);
            if (path != null) {
                return this.startMovingAlong(path, speed);
            }
            targetPos = entity.getBlockPos();
            this.speed = speed;
            return true;
        }
        return super.startMovingTo(entity, speed);
    }

    /**
     * 每 Tick 进行判断
     */
    @Override
    public void tick() {
        if (mobEntity.getTarget() == null)
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
        super.tick();
    }
}