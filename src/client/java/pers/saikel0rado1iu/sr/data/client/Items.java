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

package pers.saikel0rado1iu.sr.data.client;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.DyeableItem;
import net.minecraft.util.Identifier;
import pers.saikel0rado1iu.silk.api.item.tool.weapon.ranged.BowModelPredicateProvider;
import pers.saikel0rado1iu.silk.api.item.tool.weapon.ranged.Crossbow;
import pers.saikel0rado1iu.silk.api.item.tool.weapon.ranged.CrossbowModelPredicateProvider;
import pers.saikel0rado1iu.silk.api.registry.SilkItem;
import pers.saikel0rado1iu.sr.vanilla.ranged.JugerRepeatingCrossbow;
import pers.saikel0rado1iu.sr.vanilla.ranged.MarksCrossbow;
import pers.saikel0rado1iu.sr.vanilla.ranged.armor.ArrowproofVest;

import static net.minecraft.item.DyeableItem.DEFAULT_COLOR;
import static pers.saikel0rado1iu.sr.data.Items.*;

/**
 * <h2 style="color:FFC800">自然更替的所有物品的客户端注册</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class Items extends SilkItem {
	static {
		clientRegister(SLINGSHOT, BowModelPredicateProvider::register);
		clientRegister(RECURVE_BOW, BowModelPredicateProvider::register);
		clientRegister(ARBALEST, CrossbowModelPredicateProvider::register);
		clientRegister(COMPOUND_BOW, BowModelPredicateProvider::register);
		clientRegister(JUGER_REPEATING_CROSSBOW, jugerRepeatingCrossbow -> {
			ModelPredicateProviderRegistry.register(jugerRepeatingCrossbow, new Identifier(Crossbow.PULLING_KEY), (stack, world, entity, seed) -> {
				if (entity == null) return 0;
				return entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0;
			});
			ModelPredicateProviderRegistry.register(jugerRepeatingCrossbow, new Identifier(Crossbow.PULL_KEY), (stack, world, entity, seed) -> {
				if (entity == null) return 0.0F;
				return entity.getActiveItem() != stack ? 0.0F : ((Crossbow) stack.getItem()).getUsingProgress(stack.getMaxUseTime() - entity.getItemUseTimeLeft(), stack)
						* ((JugerRepeatingCrossbow) stack.getItem()).getCanLoadPulletNum(entity, stack) % 1;
			});
			ModelPredicateProviderRegistry.register(jugerRepeatingCrossbow, new Identifier(Crossbow.CHARGED_KEY.toLowerCase()), (stack, world, entity, seed) -> {
				if (entity == null) return 0;
				return Crossbow.isCharged(stack) ? 1 : 0;
			});
			ModelPredicateProviderRegistry.register(jugerRepeatingCrossbow, new Identifier("shoot"), (stack, world, entity, seed) -> {
				if (entity == null) return 0;
				return JugerRepeatingCrossbow.getIsShoot(stack) ? 1 : 0;
			});
			ModelPredicateProviderRegistry.register(jugerRepeatingCrossbow, new Identifier(Crossbow.PROJECTILE_ID_KEY.toLowerCase()), (stack, world, entity, seed) -> {
				if (entity == null) return 0;
				return ((JugerRepeatingCrossbow) stack.getItem()).getProjectileId(stack);
			});
		});
		clientRegister(MARKS_CROSSBOW, marksCrossbow -> {
			CrossbowModelPredicateProvider.register(marksCrossbow);
			ModelPredicateProviderRegistry.register(MARKS_CROSSBOW, new Identifier("shoot_end"), (stack, world, entity, seed) -> {
				if (entity == null) return 0;
				return MarksCrossbow.isShootEnd(stack) ? 1 : 0;
			});
		});
		clientRegister(ARROWPROOF_VEST, dyeableArmorItem -> ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> tintIndex > 0 ? -1 :
				(DyeableItem.getColor(stack) == DEFAULT_COLOR ? ArrowproofVest.COLOR : DyeableItem.getColor(stack))), ARROWPROOF_VEST));
	}
}
