package SpontaneousReplace;

import SpontaneousReplace.Generic.ConfigData;
import SpontaneousReplace.Generic.SRItemGroup;
import SpontaneousReplace.Packs.DefaultData;
import SpontaneousReplace.Packs.DefaultResource;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static SpontaneousReplace.Generic.SRData.MOD_ID;

/**
 * <b style="color:FFC800"><font size="+2">SRMain：自然更替主类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">Fabric Loader 最先调用的类，通常用于进行最终注册和模组最先操作</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 < 2022/11/14
 */
public class SRMain implements ModInitializer {
    /**
     * <p>此记录器用于将文本写入控制台和日志文件.</p>
     * <p>使用您的 Mod ID 作为记录器的名称被认为是最佳做法.</p>
     * <p>这样一来，很清楚哪个 Mod 写了信息,警告及错误.</p>
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    /**
     * <p>只要 Minecraft 处于 mod-load-ready(模组-加载-准备) 状态, 此代码就会运行.</p>
     * <p>但是, 有些东西（比如资源）可能仍然未初始化.</p>
     * <p style="color:DD0000">!谨慎操作!</p>
     */
    @Override
    public void onInitialize() {
        LOGGER.info("感谢游玩本模组! —— Saikel Orado");

        // 注册模组，如果不注册则模组会崩溃
        SRItemGroup.register();
        RegisterServer.register();
        // 如果模组内容启用则注册绝大部分模组内容
        ConfigData.ReadConfig();
        if (ConfigData.Config.ModSwitch) {
            DefaultResource.initialization();
            DefaultData.initialization();
        }
    }
}