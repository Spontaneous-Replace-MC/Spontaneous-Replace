package SpontaneousReplace.Packs;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;

/**
 * <b style="color:FFC800"><font size="+2">DefaultData：默认数据包类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">加载《自然更替》的默认数据包</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/2/21 9:38
 */
public class DefaultData {
    public static void initialization() {
        if (FabricLoader.getInstance().getModContainer(MOD_ID).isPresent())
            ResourceManagerHelper.registerBuiltinResourcePack(new Identifier("default_data"),
                    FabricLoader.getInstance().getModContainer(MOD_ID).get(),
                    Text.translatable("dataPack.spontaneous_replace.title"),
                    ResourcePackActivationType.ALWAYS_ENABLED);
    }
}
