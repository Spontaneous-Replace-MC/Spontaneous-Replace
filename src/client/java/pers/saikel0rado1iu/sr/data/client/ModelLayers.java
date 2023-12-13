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

import com.google.common.collect.Sets;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import pers.saikel0rado1iu.silk.api.registry.SilkModelLayer;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;
import pers.saikel0rado1iu.sr.variant.general.block.treacherous.TreacherousSacModel;
import pers.saikel0rado1iu.sr.variant.spider.mob.guard.GuardSpiderData;
import pers.saikel0rado1iu.sr.variant.spider.mob.guard.GuardSpiderModel;
import pers.saikel0rado1iu.sr.variant.spider.mob.larva.SpiderLarvaData;
import pers.saikel0rado1iu.sr.variant.spider.mob.larva.SpiderLarvaModel;
import pers.saikel0rado1iu.sr.variant.spider.mob.spray.SprayPoisonSpiderData;
import pers.saikel0rado1iu.sr.variant.spider.mob.spray.SprayPoisonSpiderModel;
import pers.saikel0rado1iu.sr.variant.spider.mob.spray.ToxinModel;
import pers.saikel0rado1iu.sr.variant.spider.mob.weaving.WeavingWebSpiderData;
import pers.saikel0rado1iu.sr.variant.spider.mob.weaving.WeavingWebSpiderModel;

import java.util.Set;

/**
 * <h2 style="color:FFC800">自然更替的所有的模型图层</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class ModelLayers extends SilkModelLayer {
	public static final Set<EntityModelLayer> MODEL_LAYERS = Sets.newHashSetWithExpectedSize(8);
	public static final EntityModelLayer TREACHEROUS_SAC_LAYER = builder(new EntityModelLayer(new Identifier(SpontaneousReplace.DATA.getId(), "treacherous_sac"), "main"))
			.put(MODEL_LAYERS).build(TreacherousSacModel::getTexturedModelData);
	public static final EntityModelLayer SPIDER_LARVA_LAYER = builder(new EntityModelLayer(new Identifier(SpontaneousReplace.DATA.getId(), SpiderLarvaData.ID), "main"))
			.put(MODEL_LAYERS).build(SpiderLarvaModel::getTexturedModelData);
	public static final EntityModelLayer GUARD_SPIDER_LAYER = builder(new EntityModelLayer(new Identifier(SpontaneousReplace.DATA.getId(), GuardSpiderData.ID), "main"))
			.put(MODEL_LAYERS).build(GuardSpiderModel::getTexturedModelData);
	public static final EntityModelLayer TOXIN_LAYER = builder(new EntityModelLayer(new Identifier(SpontaneousReplace.DATA.getId(), "toxin_projectile"), "main"))
			.put(MODEL_LAYERS).build(ToxinModel::getTexturedModelData);
	public static final EntityModelLayer SPRAY_POISON_SPIDER_LAYER = builder(new EntityModelLayer(new Identifier(SpontaneousReplace.DATA.getId(), SprayPoisonSpiderData.ID), "main"))
			.put(MODEL_LAYERS).build(SprayPoisonSpiderModel::getTexturedModelData);
	public static final EntityModelLayer WEAVING_WEB_SPIDER_LAYER = builder(new EntityModelLayer(new Identifier(SpontaneousReplace.DATA.getId(), WeavingWebSpiderData.ID), "main"))
			.put(MODEL_LAYERS).build(WeavingWebSpiderModel::getTexturedModelData);
}
