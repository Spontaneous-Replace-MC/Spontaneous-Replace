package pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider.toxin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.SR_ID;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.Client.TOXIN_LAYER;

/**
 * <b style="color:FFC800"><font size="+2">ToxinRenderer：毒素渲染类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">毒素的所有渲染设置</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/01/07
 */
@Environment(EnvType.CLIENT)
public class ToxinRenderer
        extends EntityRenderer<ToxinEntity> {
    /**
     * 纹理路径
     */
    protected static final Identifier TEXTURE = new Identifier(SR_ID, "textures/entity/spider/toxin.png");
    /**
     * 毒素模型
     */
    protected final ToxinModel<ToxinEntity> model;

    /**
     * 构建渲染
     */
    public ToxinRenderer(EntityRendererFactory.Context context) {
        super(context);
        model = new ToxinModel<>(context.getPart(TOXIN_LAYER));
    }

    /**
     * 渲染
     */
    @Override
    public void render(ToxinEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0, 0.15f, 0.0);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, mobEntity.prevYaw, mobEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, mobEntity.prevPitch, mobEntity.getPitch())));
        model.setAngles(mobEntity, g, 0.0f, -0.1f, 0.0f, 0.0f);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(model.getLayer(TEXTURE));
        model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStack.pop();
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    /**
     * 获取纹理
     */
    @Override
    public Identifier getTexture(ToxinEntity entity) {
        return TEXTURE;
    }
}
