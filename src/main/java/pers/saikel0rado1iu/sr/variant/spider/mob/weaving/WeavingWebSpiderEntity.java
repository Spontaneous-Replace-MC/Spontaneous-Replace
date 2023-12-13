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

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderEntity;

import static pers.saikel0rado1iu.sr.variant.spider.mob.weaving.WeavingWebSpiderData.*;

/**
 * <h2 style="color:FFC800">织网蜘蛛实体类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class WeavingWebSpiderEntity extends SpiderEntity {
	protected final AnimationState weavingAnimationState = new AnimationState();
	protected int weaveInterval = 0;
	protected int weaveWebCount = 0;
	protected boolean canPlayWeave = true;
	protected int weaveAnimeTime = 0;
	
	public WeavingWebSpiderEntity(EntityType<? extends net.minecraft.entity.mob.SpiderEntity> entityType, World world) {
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
	 * 设置生物动画
	 */
	@Override
	public void tick() {
		super.tick();
		// 需要同时在服务端和客户端进行
		weaveAnimeTime++;
		// 只在服务端进行判断
		if (!getWorld().isClient) {
			weaveInterval++;
			if (canPlayWeave) {
				canPlayWeave = false;
				weaveAnimeTime = 0;
			}
		}
	}
	
	/**
	 * 重新分配目标逻辑
	 */
	@Override
	protected void initGoals() {
		super.initGoals();
		this.goalSelector.add(4, new WeaveGoal(this));
	}
	
	/**
	 * 创建实体导航，如果没有覆盖此方法，该实体的移动则会出现问题
	 */
	@Override
	protected EntityNavigation createNavigation(World world) {
		return new WeavingWebSpiderNavigation(this, world);
	}
	
	/**
	 * 设置筑网动画
	 *
	 * @param status 状态
	 */
	@Override
	public void handleStatus(byte status) {
		// 进行筑网动画判断以便判断是否重置判断参数和播放动画
		if (status == EntityStatuses.PLAY_ATTACK_SOUND)
			weavingAnimationState.start(age);
	}
	
	/**
	 * 写入剩余蛛网数量
	 */
	@Override
	public NbtCompound writeNbt(NbtCompound nbt) {
		nbt.putInt("weaveWebCount", weaveWebCount);
		return super.writeNbt(nbt);
	}
	
	/**
	 * 读取剩余蛛网数量
	 */
	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		weaveWebCount = nbt.getInt("weaveWebCount");
	}
	
	/**
	 * <p>攻击伤害为 0，也就是不会攻击，但是会织网。</p>
	 * <p>只有在与目标距离大于 4 时被激怒才会接近目标，</p>
	 * <p>如果被激怒且与目标距离小于 4 时织下一张网，并且织网冷却时间 40 tick 且最多织 15 张网</p>
	 */
	static class WeaveGoal extends AttackGoal {
		public WeaveGoal(net.minecraft.entity.mob.SpiderEntity spider) {
			super(spider);
		}
		
		@Override
		public boolean canStart() {
			if (mob.getTarget() == null)
				return super.canStart();
			WeavingWebSpiderEntity entity = (WeavingWebSpiderEntity) mob;
			return super.canStart() && mob.distanceTo(mob.getTarget()) > TARGET_RANGE && entity.weaveAnimeTime >= WEAVE_INTERVAL;
		}
		
		@Override
		public boolean shouldContinue() {
			// 如果有目标
			if (mob.getTarget() != null)
				// 满足以下条件进行判断
				if (mob instanceof WeavingWebSpiderEntity entity) {
					// 如果正在播放动画
					if (entity.weaveAnimeTime < WEAVE_TIME)
						return false;
						// 如果没有播放动画
					else if (entity.weaveInterval >= WEAVE_INTERVAL && entity.weaveWebCount < WEAVE_COUNT &&
							(mob.distanceTo(mob.getTarget()) > TARGET_RANGE - 1 && mob.distanceTo(mob.getTarget()) < TARGET_RANGE + 1)) {
						Random random = mob.getRandom();
						World world = mob.getWorld();
						int i = MathHelper.floor(mob.getX() - 1.0 + random.nextDouble() * 2.0);
						int j = MathHelper.floor(mob.getY() + random.nextDouble() * 2.0);
						int k = MathHelper.floor(mob.getZ() - 1.0 + random.nextDouble() * 2.0);
						BlockPos blockPos = new BlockPos(i, j, k);
						BlockState blockState = world.getBlockState(blockPos);
						BlockPos blockPos2 = blockPos.down();
						BlockState blockState2 = world.getBlockState(blockPos2);
						BlockState blockState3 = Blocks.COBWEB.getDefaultState();
						// 满足条件则筑网
						if (this.canWeaveOn(world, blockPos, blockState3 = Block.postProcessState(blockState3, mob.getWorld(), blockPos), blockState, blockState2, blockPos2)) {
							// 设置筑网条件
							entity.weaveInterval = 0;
							entity.weaveWebCount++;
							entity.canPlayWeave = true;
							world.sendEntityStatus(entity, EntityStatuses.PLAY_ATTACK_SOUND);
							// 设置筑网动画判断
							world.setBlockState(blockPos, blockState3, Block.NOTIFY_ALL);
							world.emitGameEvent(GameEvent.BLOCK_PLACE, blockPos, GameEvent.Emitter.of(mob, blockState3));
						}
					}
				}
			return super.shouldContinue();
		}
		
		protected boolean canWeaveOn(World world, BlockPos posAbove, BlockState carriedState, BlockState stateAbove, BlockState state, BlockPos pos) {
			return stateAbove.isAir() && !state.isAir() && !state.isOf(Blocks.BEDROCK) && state.isFullCube(world, pos) && carriedState.canPlaceAt(world, posAbove) && world.getOtherEntities(mob, Box.from(Vec3d.of(posAbove))).isEmpty();
		}
	}
}
