package pres.saikel_orado.spontaneous_replace.mod.variant.general.block.treacheroussac;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.SR_ID;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Client.TREACHEROUS_SAC_LAYER;

/**
 * <b style="color:FFC800"><font size="+2">TreacherousSacRenderer：诡谲囊渲染器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">渲染诡谲囊实体</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/20 10:06
 */
@Environment(EnvType.CLIENT)
public class TreacherousSacRenderer<T extends TreacherousSacEntity> extends LivingEntityRenderer<T, TreacherousSacModel<T>> {
    /**
     * 不渲染实体阴影
     */
    public TreacherousSacRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new TreacherousSacModel<>(ctx.getPart(TREACHEROUS_SAC_LAYER)), 0);
    }

    @SuppressWarnings("unused")
    protected TreacherousSacRenderer(EntityRendererFactory.Context ctx, TreacherousSacModel<T> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    /**
     * 取消显示名称
     */
    @Override
    protected boolean hasLabel(T livingEntity) {
        return false;
    }

    @Override
    public Identifier getTexture(T entity) {
        return new Identifier(SR_ID, "textures/entity/variant/treacherous_sac.png");
    }
}
