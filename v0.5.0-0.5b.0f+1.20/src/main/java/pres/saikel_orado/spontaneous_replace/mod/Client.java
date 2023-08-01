package pres.saikel_orado.spontaneous_replace.mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData;
import pres.saikel_orado.spontaneous_replace.mod.util.SRUpdateUtil;
import pres.saikel_orado.spontaneous_replace.mod.pack.SRDefaultResource;

import java.util.concurrent.TimeUnit;

import static pres.saikel_orado.spontaneous_replace.mod.util.SRUpdateUtil.UPDATE_THREAD_POOL;

/**
 * <b style="color:FFC800"><font size="+2">Client：自然更替客户端初始化</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">通常进行模组的客户端内容注册</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 < 2023/1/5
 */
@Environment(EnvType.CLIENT)
public final class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 如果模组内容启用则注册绝大部分模组内容
        SRConfigData.readConfig();
        if (SRConfigData.Config.modSwitch)
            SRDefaultResource.initialization();

        // 启动检查更新线程
        UPDATE_THREAD_POOL.scheduleAtFixedRate(new SRUpdateUtil.CheckUpdateThread(), 0, 1, TimeUnit.MINUTES);

        Registrar.Client.register();
    }
}
