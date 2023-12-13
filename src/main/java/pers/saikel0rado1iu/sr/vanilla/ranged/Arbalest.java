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

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import pers.saikel0rado1iu.silk.api.item.tool.weapon.ranged.Crossbow;
import pers.saikel0rado1iu.silk.util.TickUtil;

import java.util.Map;

import static pers.saikel0rado1iu.sr.data.SoundEvents.*;

/**
 * <h2 style="color:FFC800">劲弩</font></b></p>
 * <p style="color:FFC800">一种比普通弩更强的基础远程武器</p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class Arbalest extends Crossbow {
	public Arbalest(Settings settings) {
		super(settings);
	}
	
	@Override
	public float getMaxProjectileSpeed() {
		return 4.5F;
	}
	
	@Override
	public float getHoldingFovZoom() {
		return 1.15F;
	}
	
	@Override
	public float getMaxDamageMultiple() {
		return 1.5F;
	}
	
	@Override
	public int getMaxUseTicks() {
		return TickUtil.getTick(1);
	}
	
	@Override
	protected SoundEvent getQuickChargeSound(int stage) {
		return switch (stage) {
			case 1 -> ARBALEST_QUICK_CHARGE_1;
			case 2 -> ARBALEST_QUICK_CHARGE_2;
			case 3 -> ARBALEST_QUICK_CHARGE_3;
			default -> ARBALEST_LOADING_START;
		};
	}
	
	@Override
	protected SoundEvent getLoadingSound() {
		return ARBALEST_LOADING_MIDDLE;
	}
	
	@Override
	protected SoundEvent getLoadingEndSound() {
		return ARBALEST_LOADING_END;
	}
	
	@Override
	protected SoundEvent getShootSound() {
		return ARBALEST_SHOOT;
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
		NbtCompound nbtCompound = stack.getOrCreateNbt();
		nbtCompound.putFloat(PROJECTILE_ID_KEY, 0);
		if (!getAllProjectile(stack).isEmpty()) {
			ItemStack useProjectile = getAllProjectile(stack).get(0);
			if (useProjectile.isOf(Items.ARROW)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0);
			else if (useProjectile.isOf(Items.TIPPED_ARROW)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0.1F);
			else if (useProjectile.isOf(Items.SPECTRAL_ARROW)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0.2F);
			else if (useProjectile.isOf(Items.FIREWORK_ROCKET)) nbtCompound.putFloat(PROJECTILE_ID_KEY, 0.3F);
		}
		return nbtCompound.getFloat(PROJECTILE_ID_KEY);
	}
	
	/**
	 * 获取所有弹丸的 ID 与他们所对应的名称以供模型生成
	 */
	@Override
	public Map<Float, String> getAllProjectile() {
		return Map.of(0.0F, "arrow", 0.3F, "firework");
	}
}
