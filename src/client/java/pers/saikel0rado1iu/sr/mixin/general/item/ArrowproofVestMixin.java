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

package pers.saikel0rado1iu.sr.mixin.general.item;

import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Colors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import pers.saikel0rado1iu.sr.data.Items;
import pers.saikel0rado1iu.sr.vanilla.ranged.armor.ArrowproofVest;

/**
 * <h2 style="color:FFC800">防箭衣混入</h2>
 * <p style="color:FFC800">使防箭衣服的染色功能正常可用</p>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
@Mixin(ArmorFeatureRenderer.class)
abstract class ArrowproofVestMixin {
	@Unique
	private ItemStack stack;
	
	@ModifyVariable(method = "renderArmor", at = @At("STORE"), ordinal = 0)
	private ItemStack getItemStack(ItemStack stack) {
		return this.stack = stack;
	}
	
	@ModifyVariable(method = "renderArmor", at = @At("STORE"), ordinal = 1)
	private int renderArmor(int value) {
		return !stack.getItem().getName().equals(Items.ARROWPROOF_VEST.getName()) ? value
				: stack.isIn(ItemTags.DYEABLE) ? DyedColorComponent.getColor(stack, ArrowproofVest.COLOR) : Colors.WHITE;
	}
}
