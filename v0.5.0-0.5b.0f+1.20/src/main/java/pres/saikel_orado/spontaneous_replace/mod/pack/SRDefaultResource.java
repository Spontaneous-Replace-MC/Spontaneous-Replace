package pres.saikel_orado.spontaneous_replace.mod.pack;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.SR_ID;

/**
 * <b style="color:FFC800"><font size="+2">SRDefaultResource：默认资源包类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">加载《自然更替》的默认资源包</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/2/19 17:41
 */
public final class SRDefaultResource {
    private SRDefaultResource() throws ExceptionInInitializerError {
        throw new ExceptionInInitializerError();
    }

    public static void initialization() {
        if (FabricLoader.getInstance().getModContainer(SR_ID).isPresent())
            ResourceManagerHelper.registerBuiltinResourcePack(new Identifier("default_resource"),
                    FabricLoader.getInstance().getModContainer(SR_ID).get(),
                    Text.translatable("resourcePack.spontaneous_replace.title"),
                    ResourcePackActivationType.ALWAYS_ENABLED);
    }
}
