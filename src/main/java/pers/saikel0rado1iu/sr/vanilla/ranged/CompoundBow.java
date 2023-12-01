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

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import pers.saikel0rado1iu.silk.api.item.tool.weapon.ranged.Bow;
import pers.saikel0rado1iu.silk.util.TickUtil;

import java.util.Map;
import java.util.Optional;

/**
 * <p><b style="color:FFC800"><font size="+1">复合弓</font></b></p>
 * <p style="color:FFC800">添加一种性能极佳的强力远程武器</p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public class CompoundBow extends Bow {
	public CompoundBow(Settings settings) {
		super(settings);
	}
	
	@Override
	public Optional<Identifier> getHubOverlay() {
		return Optional.of(SPYGLASS_SCOPE);
	}
	
	@Override
	public float getMaxProjectileSpeed() {
		return 6;
	}
	
	@Override
	public float getUsingFovZoom() {
		return 1.5F;
	}
	
	@Override
	public float getMaxDamageMultiple() {
		return 2F;
	}
	
	@Override
	public int getMaxPullTicks() {
		return TickUtil.getTick(1.5F);
	}
	
	@Override
	public int getRange() {
		return super.getRange() * 2;
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		// 获取使用物品
		ItemStack itemStack = user.getStackInHand(hand);
		// 检查是否在创造模式或者拥有“无限”附魔
		boolean inCreateOrInfinity = user.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, itemStack) > 0;
		// 获取弹丸
		ItemStack userProjectile = user.getProjectileType(itemStack);
		// 检查玩家是否有弹丸，如果没有弹丸但在创造模式或者拥有“无限”附魔则使用默认弹丸
		if (!userProjectile.isEmpty() || inCreateOrInfinity) {
			if (userProjectile.isEmpty()) userProjectile = new ItemStack(Items.ARROW);
		}
		setProjectileId(itemStack, userProjectile);
		
		return super.use(world, user, hand);
	}
	
	/**
	 * 设置弹丸 ID 的 NBT 以供 JSON 渲染使用
	 */
	@Override
	public void setProjectileId(ItemStack stack, ItemStack useProjectile) {
		NbtCompound nbtCompound = stack.getOrCreateNbt();
		nbtCompound.putFloat(PROJECTILE_ID_KEY, 0);
		if (useProjectile != null) {
			if (useProjectile.isOf(Items.ARROW)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0);
			else if (useProjectile.isOf(Items.TIPPED_ARROW)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0.1F);
			else if (useProjectile.isOf(Items.SPECTRAL_ARROW)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0.2F);
		}
	}
	
	/**
	 * 获取 NBT 弹丸 ID 以供 JSON 渲染使用
	 */
	@Override
	public float getProjectileId(ItemStack stack) {
		NbtCompound nbtCompound = stack.getNbt();
		return nbtCompound != null ? nbtCompound.getFloat(PROJECTILE_ID_KEY) : 0;
	}
	
	/**
	 * 获取所有弹丸的 ID 与他们所对应的名称以供模型生成
	 */
	@Override
	public Map<Float, String> getAllProjectile() {
		return Map.of(0F, "arrow");
	}
}
