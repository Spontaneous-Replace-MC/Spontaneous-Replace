package SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider;

import SpontaneousReplace.SpiderBiome.Generic.SRSpider.SRSpiderRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Data.*;

/**
 * <b style="color:FFC800"><font size="+2">Eyes：喷吐毒蛛渲染类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">喷吐毒蛛的所有渲染设置</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/01/06
 */
@Environment(EnvType.CLIENT)
public class Renderer<T extends Entity> extends SRSpiderRenderer<T> {
    /**
     * 构建渲染
     */
    public Renderer(EntityRendererFactory.Context context) {
        super(context, new Model<>(context.getPart(Client.SPRAY_POISON_SPIDER_LAYER)), MODEL_SHADOW);
        this.addFeature(new Eyes<>(this));
    }

    /**
     * 调整模型缩放
     */
    @Override
    protected void scale(T entity, MatrixStack matrices, float amount) {
        matrices.scale(MODEL_SCALE, MODEL_SCALE, MODEL_SCALE);
    }

    @Override
    public Identifier getTexture(T spiderEntity) {
        return new Identifier(MOD_ID, "textures/entity/spider/" + ID + ".png");
    }
}
