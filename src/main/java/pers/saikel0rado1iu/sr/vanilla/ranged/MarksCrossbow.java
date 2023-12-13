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

package pers.saikel0rado1iu.sr.vanilla.ranged;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import pers.saikel0rado1iu.silk.api.item.CustomEnchantment;
import pers.saikel0rado1iu.silk.api.item.tool.UsingMovementMultiplier;
import pers.saikel0rado1iu.silk.api.item.tool.weapon.BreakingShield;
import pers.saikel0rado1iu.silk.api.item.tool.weapon.ranged.Crossbow;
import pers.saikel0rado1iu.silk.util.TickUtil;
import pers.saikel0rado1iu.sr.vanilla.ranged.projectile.steelarrow.SteelArrowEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static pers.saikel0rado1iu.sr.data.Items.STEEL_ARROW;
import static pers.saikel0rado1iu.sr.data.SoundEvents.*;

/**
 * <h2 style="color:FFC800">神臂弩</font></b></p>
 * <p style="color:FFC800">添加一种极大威力的超远程攻击武器</p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class MarksCrossbow extends Crossbow implements BreakingShield, CustomEnchantment, UsingMovementMultiplier {
	public MarksCrossbow(Settings settings) {
		super(settings);
	}
	
	/**
	 * 是否已发射完毕
	 */
	public static boolean isShootEnd(ItemStack stack) {
		NbtCompound nbtCompound = stack.getNbt();
		return nbtCompound != null && nbtCompound.getBoolean("ShootEnd");
	}
	
	/**
	 * 设置是否已发射完毕
	 */
	public static void setShootEnd(ItemStack stack, boolean end) {
		NbtCompound nbtCompound = stack.getOrCreateNbt();
		nbtCompound.putBoolean("ShootEnd", end);
	}
	
	@Override
	protected PersistentProjectileEntity createArrow(World world, LivingEntity entity, ItemStack crossbow, ItemStack arrow) {
		// 创建箭实体
		PersistentProjectileEntity persistentProjectileEntity = STEEL_ARROW.createArrow(world, entity);
		// 如果是玩家则设置暴击
		if (entity instanceof PlayerEntity) persistentProjectileEntity.setCritical(true);
		// 设置声音
		persistentProjectileEntity.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
		// 设置由弩射击
		persistentProjectileEntity.setShotFromCrossbow(true);
		// 设置“穿透”效果
		int piercingLevel = EnchantmentHelper.getLevel(Enchantments.PIERCING, crossbow);
		if (piercingLevel > 0) persistentProjectileEntity.setPierceLevel((byte) piercingLevel);
		
		return persistentProjectileEntity;
	}
	
	@Override
	public float getMaxProjectileSpeed() {
		return 15;
	}
	
	@Override
	public float getHoldingFovZoom() {
		return 1.9F;
	}
	
	@Override
	public Optional<Identifier> getHubOverlay() {
		return Optional.of(SPYGLASS_SCOPE);
	}
	
	@Override
	public boolean onlyFirstPerson() {
		return true;
	}
	
	@Override
	public float getHoldingMovementMultiple() {
		return 0;
	}
	
	@Override
	public int getMaxUseTicks() {
		return TickUtil.getTick(10);
	}
	
	@Override
	public int getRange() {
		return super.getRange() * 5;
	}
	
	@Override
	protected SoundEvent getQuickChargeSound(int stage) {
		if (stage == 1) return MARKS_CROSSBOW_QUICK_CHARGE_1;
		else if (stage == 2) return MARKS_CROSSBOW_QUICK_CHARGE_2;
		else if (stage >= 3) return MARKS_CROSSBOW_QUICK_CHARGE_3;
		else return MARKS_CROSSBOW_LOADING_START;
	}
	
	@Override
	public float getFiringError() {
		return 0;
	}
	
	@Override
	protected SoundEvent getLoadingSound() {
		return MARKS_CROSSBOW_LOADING_MIDDLE;
	}
	
	@Override
	protected SoundEvent getLoadingEndSound() {
		return MARKS_CROSSBOW_LOADING_END;
	}
	
	@Override
	protected SoundEvent getShootSound() {
		return MARKS_CROSSBOW_SHOOT;
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}
	
	@Override
	public void shootAllProjectile(World world, LivingEntity entity, Hand hand, ItemStack stack, float speed, float divergence) {
		setShootEnd(stack, true);
		super.shootAllProjectile(world, entity, hand, stack, speed, divergence);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (!isCharged(user.getStackInHand(hand))) setShootEnd(user.getStackInHand(hand), false);
		return super.use(world, user, hand);
	}
	
	/**
	 * 设置弹丸 ID 的 NBT 以供 JSON 渲染使用
	 */
	@Override
	public void setProjectileId(ItemStack stack, ItemStack useProjectile) {
	}
	
	/**
	 * 获取 NBT 弹丸 ID 以供 JSON 渲染使用
	 */
	@Override
	public float getProjectileId(ItemStack stack) {
		return 0;
	}
	
	/**
	 * 获取所有弹丸的 ID 与他们所对应的名称以供模型生成
	 */
	@Override
	public Map<Float, String> getAllProjectile() {
		return Map.of(0F, "arrow");
	}
	
	@Override
	public Predicate<ItemStack> getHeldProjectiles() {
		return getProjectiles();
	}
	
	@Override
	public Predicate<ItemStack> getProjectiles() {
		return (stack) -> stack.isOf(STEEL_ARROW);
	}
	
	/**
	 * <p>神臂弩可用附魔：</p>
	 * <p>穿透、快速装填、耐久、消失诅咒、经验修补</p>
	 */
	@Override
	public List<Enchantment> getEnchantments() {
		return Arrays.asList(Enchantments.PIERCING,
				Enchantments.QUICK_CHARGE,
				Enchantments.UNBREAKING,
				Enchantments.VANISHING_CURSE,
				Enchantments.MENDING);
	}
	
	@Override
	public float getUsingMovementMultiple() {
		return 0;
	}
	
	@Override
	public boolean canBreaking(DamageSource damageSource) {
		return damageSource.getSource() instanceof SteelArrowEntity && !(damageSource.getAttacker() instanceof SteelArrowEntity);
	}
	
	@Override
	public boolean throughShield() {
		return true;
	}
	
	@Override
	public float getHurtDamage(float v) {
		return v / 2;
	}
	
	@Override
	public float getShieldDamage(float v) {
		return v / 2;
	}
}
