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

import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import static pers.saikel0rado1iu.sr.data.EntityTypes.TOXIN;
import static pers.saikel0rado1iu.sr.data.ParticleTypes.TOXIN_PARTICLE;

/**
 * <h2 style="color:FFC800">毒素实体类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class ToxinEntity extends ProjectileEntity {
	/**
	 * 构建毒素实体
	 */
	public ToxinEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
		super(entityType, world);
	}
	
	/**
	 * 构建毒素实体
	 */
	public ToxinEntity(World world, MobEntity owner) {
		this(TOXIN, world);
		setOwner(owner);
		setPosition(owner.getX() - (double) (owner.getWidth() + 1.0f) * 0.5 * (double) MathHelper.sin(owner.bodyYaw * ((float) Math.PI / 180)),
				owner.getEyeY() - (double) 0.1f, owner.getZ() + (double) (owner.getWidth() + 1.0f) * 0.5 * (double) MathHelper.cos(owner.bodyYaw * ((float) Math.PI / 180)));
	}
	
	@Override
	protected void initDataTracker() {
	}
	
	/**
	 * 每刻计算
	 */
	@Override
	public void tick() {
		super.tick();
		Vec3d vec3d = getVelocity();
		HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
		onCollision(hitResult);
		double d = getX() + vec3d.x;
		double e = getY() + vec3d.y;
		double f = getZ() + vec3d.z;
		updateRotation();
		if (getWorld().getStatesInBox(getBoundingBox()).noneMatch(AbstractBlock.AbstractBlockState::isAir)) {
			discard();
			return;
		}
		if (isInsideWaterOrBubbleColumn()) {
			discard();
			return;
		}
		setVelocity(vec3d.multiply(1f));
		if (!hasNoGravity()) setVelocity(getVelocity().add(0.0, -0.06f, 0.0));
		setPosition(d, e, f);
	}
	
	/**
	 * 如果命中实体
	 */
	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		net.minecraft.entity.Entity entity = entityHitResult.getEntity();
		if (entity instanceof LivingEntity livingEntity) {
			livingEntity.damage(getDamageSources().mobAttack((LivingEntity) getOwner()), 1.0f);
			livingEntity.addStatusEffect((new StatusEffectInstance(StatusEffects.POISON, 20 * 15, 0)));
		}
	}
	
	/**
	 * 如果命中方块
	 */
	@Override
	protected void onBlockHit(BlockHitResult blockHitResult) {
		super.onBlockHit(blockHitResult);
		if (!getWorld().isClient) discard();
	}
	
	/**
	 * 如果命中生成包
	 */
	@Override
	public void onSpawnPacket(EntitySpawnS2CPacket packet) {
		super.onSpawnPacket(packet);
		double d = packet.getVelocityX();
		double e = packet.getVelocityY();
		double f = packet.getVelocityZ();
		for (int i = 0; i < 7; ++i) {
			double g = 0.4 + 0.1 * (double) i;
			getWorld().addParticle(TOXIN_PARTICLE, getX(), getY(), getZ(), d * g, e, f * g);
		}
		setVelocity(d, e, f);
	}
}
