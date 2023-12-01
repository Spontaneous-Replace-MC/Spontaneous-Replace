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

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import pers.saikel0rado1iu.silk.api.registry.SilkEntityType;
import pers.saikel0rado1iu.sr.vanilla.ranged.projectile.steelarrow.SteelArrowRender;
import pers.saikel0rado1iu.sr.variant.general.block.treacherous.TreacherousSacRenderer;
import pers.saikel0rado1iu.sr.variant.spider.mob.guard.GuardSpiderRenderer;
import pers.saikel0rado1iu.sr.variant.spider.mob.larva.SpiderLarvaRenderer;
import pers.saikel0rado1iu.sr.variant.spider.mob.spray.SprayPoisonSpiderRenderer;
import pers.saikel0rado1iu.sr.variant.spider.mob.spray.ToxinRenderer;
import pers.saikel0rado1iu.sr.variant.spider.mob.weaving.WeavingWebSpiderRenderer;

import static pers.saikel0rado1iu.sr.data.EntityTypes.*;

/**
 * <p><b style="color:FFC800"><font size="+1">自然更替的所有实体的客户端注册</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public class EntityTypes extends SilkEntityType {
	static {
		clientRegister(STONEBALL, stoneballEntityEntityType -> EntityRendererRegistry.register(STONEBALL, FlyingItemEntityRenderer::new));
		clientRegister(STEEL_ARROW, steelArrowEntityEntityType -> EntityRendererRegistry.register(STEEL_ARROW, SteelArrowRender::new));
		clientRegister(TREACHEROUS_SAC, treacherousSacEntityEntityType -> EntityRendererRegistry.register(treacherousSacEntityEntityType, TreacherousSacRenderer::new));
		clientRegister(SPIDER_LARVA, spiderLarvaEntityEntityType -> EntityRendererRegistry.register(spiderLarvaEntityEntityType, SpiderLarvaRenderer::new));
		clientRegister(GUARD_SPIDER, guardSpiderEntityEntityType -> EntityRendererRegistry.register(guardSpiderEntityEntityType, GuardSpiderRenderer::new));
		clientRegister(TOXIN, toxinEntityEntityType -> EntityRendererRegistry.register(toxinEntityEntityType, ToxinRenderer::new));
		clientRegister(SPRAY_POISON_SPIDER, sprayPoisonSpiderEntityEntityType -> EntityRendererRegistry.register(sprayPoisonSpiderEntityEntityType, SprayPoisonSpiderRenderer::new));
		clientRegister(WEAVING_WEB_SPIDER, weavingWebSpiderEntityEntityType -> EntityRendererRegistry.register(weavingWebSpiderEntityEntityType, WeavingWebSpiderRenderer::new));
	}
}
