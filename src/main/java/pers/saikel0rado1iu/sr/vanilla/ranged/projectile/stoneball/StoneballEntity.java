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

package pers.saikel0rado1iu.sr.vanilla.ranged.projectile.stoneball;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import pers.saikel0rado1iu.sr.data.EntityTypes;
import pers.saikel0rado1iu.sr.data.Items;

/**
 * <h2 style="color:FFC800">石弹实体</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class StoneballEntity extends ThrownItemEntity {
	protected int punchLevel = 0;
	protected Vec3d velocity = null;
	
	public StoneballEntity(EntityType<? extends StoneballEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public StoneballEntity(World world, LivingEntity owner) {
		super(EntityTypes.STONEBALL, owner, world);
	}
	
	public StoneballEntity(World world, double x, double y, double z) {
		super(EntityTypes.STONEBALL, x, y, z, world);
	}
	
	/**
	 * 获取默认物品
	 */
	@Override
	protected Item getDefaultItem() {
		return Items.STONEBALL;
	}
	
	/**
	 * 获取粒子参数
	 */
	private ParticleEffect getParticleParameters() {
		return new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(getDefaultItem()));
	}
	
	/**
	 * 句柄状态
	 */
	@Override
	public void handleStatus(byte status) {
		if (status == 3) {
			ParticleEffect particleEffect = getParticleParameters();
			for (int i = 0; i < 8; ++i) getWorld().addParticle(particleEffect, getX(), getY(), getZ(), 0, 0, 0);
		}
	}
	
	/**
	 * 在击中实体时的操作
	 */
	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		Entity entity = entityHitResult.getEntity();
		// 设置生物实体击退
		if (punchLevel > 0 && entity instanceof LivingEntity) ((LivingEntity) entity).takeKnockback(punchLevel * 2, -velocity.getX(), -velocity.getZ());
		entity.damage(getDamageSources().thrown(this, getOwner()), 0.01F);
	}
	
	/**
	 * 在碰撞时
	 */
	@Override
	protected void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		if (!getWorld().isClient) {
			getWorld().sendEntityStatus(this, (byte) 3);
			discard();
		}
	}
	
	/**
	 * 设置"冲击"等级
	 */
	public void setPunchLevel(int level, Vec3d speed) {
		punchLevel = level;
		velocity = speed;
	}
}
