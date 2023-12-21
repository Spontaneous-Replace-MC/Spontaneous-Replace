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

package pers.saikel0rado1iu.sr.data;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import pers.saikel0rado1iu.silk.api.ModBasicData;
import pers.saikel0rado1iu.silk.api.registry.SilkStatusEffect;
import pers.saikel0rado1iu.sr.variant.general.effect.Acidize;
import pers.saikel0rado1iu.sr.variant.spider.effect.SpiderCamouflage;

/**
 * <h2 style="color:FFC800">自然更替的所有状态效果</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class StatusEffects extends SilkStatusEffect {
	public static final RegistryEntry<StatusEffect> SPIDER_CAMOUFLAGE = register(new SpiderCamouflage(), "spider_camouflage");
	public static final RegistryEntry<StatusEffect> ACIDIZE = register(new Acidize(), "acidize");
	
	private static RegistryEntry<StatusEffect> register(StatusEffect statusEffect, String id) {
		return Registry.registerReference(Registries.STATUS_EFFECT, new Identifier(((ModBasicData) SpontaneousReplace.DATA).getId(), id), statusEffect);
	}
}
