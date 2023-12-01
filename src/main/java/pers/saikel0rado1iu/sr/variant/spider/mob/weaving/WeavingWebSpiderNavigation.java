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

package pers.saikel0rado1iu.sr.variant.spider.mob.weaving;

import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import pers.saikel0rado1iu.silk.util.MathUtil;

import static pers.saikel0rado1iu.sr.variant.spider.mob.weaving.WeavingWebSpiderData.TARGET_RANGE;
import static pers.saikel0rado1iu.sr.variant.spider.mob.weaving.WeavingWebSpiderData.WEAVE_TIME;

/**
 * <p><b style="color:FFC800"><font size="+1">织网蜘蛛寻路类</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public class WeavingWebSpiderNavigation extends MobNavigation {
	/**
	 * 获取导航实体，如果实体有目标，则禁用爬墙和目标追寻
	 */
	private final MobEntity mob;
	@Nullable
	private BlockPos targetPos;
	
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
		if (path != null) return startMovingAlong(path, speed);
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
		if (!(mob instanceof WeavingWebSpiderEntity spider && spider.weaveAnimeTime >= WEAVE_TIME)) {
			super.tick();
			return;
		}
		boolean flag = false;
		if (mob.getTarget() != null) flag = (mob.distanceTo(mob.getTarget()) > TARGET_RANGE);
		if (MathUtil.xor(flag, mob.getTarget() == null)) {
			if (isIdle() && targetPos != null) {
				if (!targetPos.isWithinDistance(entity.getPos(), entity.getWidth()) && (!(entity.getY() > (double) targetPos.getY()) || !BlockPos.ofFloored(targetPos.getX(), entity.getY(), targetPos.getZ()).isWithinDistance(entity.getPos(), entity.getWidth())))
					this.entity.getMoveControl().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), speed);
				else targetPos = null;
			}
		} else {
			Vec3d vec3d = NoPenaltyTargeting.find((PathAwareEntity) mob, 5, 4);
			if (vec3d != null) mob.getNavigation().startMovingTo(vec3d.x, vec3d.y, vec3d.z, speed);
		}
	}
}
