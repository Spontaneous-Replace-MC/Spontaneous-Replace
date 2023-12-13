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

package pers.saikel0rado1iu.sr.variant.general.block.treacherous;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Arm;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import pers.saikel0rado1iu.silk.util.TickUtil;
import pers.saikel0rado1iu.sr.data.StatusEffects;

import java.util.List;

import static pers.saikel0rado1iu.sr.data.EntityTypes.TREACHEROUS_SAC;
import static pers.saikel0rado1iu.sr.data.SoundEvents.TREACHEROUS_SAC_BREAK;
import static pers.saikel0rado1iu.sr.variant.general.VariantData.TREACHEROUS_SAC_EXPLODE_POWER;

/**
 * <h2 style="color:FFC800">诡谲囊实体</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class TreacherousSacEntity extends LivingEntity {
	public final AnimationState explodeAnimationState = new AnimationState();
	protected LivingEntity detonator;
	protected int delay = 0;
	
	public TreacherousSacEntity(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
		detonator = null;
	}
	
	protected TreacherousSacEntity(World world, LivingEntity detonator) {
		super(TREACHEROUS_SAC, world);
		this.detonator = detonator;
	}
	
	/**
	 * 设置实体数值
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return LivingEntity.createLivingAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, Float.MAX_VALUE)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0);
	}
	
	/**
	 * 延迟爆炸 1s
	 */
	@Override
	public void tick() {
		if (!getWorld().isClient) {
			if (delay == 0)
				getWorld().playSound(null, getBlockPos(), TREACHEROUS_SAC_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (delay > TickUtil.getTick(1)) explode(getWorld(), getBlockPos(), detonator);
			else delay++;
		} else explodeAnimationState.startIfNotRunning(age);
	}
	
	/**
	 * 爆炸处理：爆炸会产生一大片酸性气体云
	 */
	public void explode(World world, BlockPos pos, @Nullable LivingEntity detonator) {
		world.emitGameEvent(detonator, GameEvent.EXPLODE, pos);
		float[] fixedPos = {pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F};
		world.createExplosion(null, fixedPos[0], fixedPos[1], fixedPos[2], TREACHEROUS_SAC_EXPLODE_POWER, World.ExplosionSourceType.BLOCK);
		AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(world, fixedPos[0], fixedPos[1], fixedPos[2]);
		if (detonator != null) areaEffectCloudEntity.setOwner(detonator);
		areaEffectCloudEntity.setRadius(3.0F);
		areaEffectCloudEntity.setRadiusOnUse(-0.5F);
		areaEffectCloudEntity.setWaitTime(10);
		areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float) areaEffectCloudEntity.getDuration());
		areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffects.ACIDIZE, TickUtil.getTick(15), 1));
		world.spawnEntity(areaEffectCloudEntity);
		discard();
	}
	
	/**
	 * 模仿方块：不处理移动
	 */
	@Override
	public void move(MovementType movementType, Vec3d movement) {
	}
	
	/**
	 * 模仿方块：不会被玩家伤害
	 */
	@Override
	public boolean damage(DamageSource source, float amount) {
		return source.isOf(DamageTypes.GENERIC_KILL) && super.damage(source, amount);
	}
	
	/**
	 * 模仿方块：不能拥有状态
	 */
	@Override
	public boolean canHaveStatusEffect(StatusEffectInstance effect) {
		return false;
	}
	
	/**
	 * 模仿方块：对爆炸免疫
	 */
	@Override
	public boolean isImmuneToExplosion() {
		return true;
	}
	
	@Override
	public Iterable<ItemStack> getArmorItems() {
		return List.of(ItemStack.EMPTY);
	}
	
	@Override
	public ItemStack getEquippedStack(EquipmentSlot slot) {
		return ItemStack.EMPTY;
	}
	
	@Override
	public void equipStack(EquipmentSlot slot, ItemStack stack) {
	}
	
	@Override
	public Arm getMainArm() {
		return Arm.RIGHT;
	}
}
