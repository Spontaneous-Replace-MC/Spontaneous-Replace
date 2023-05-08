package SpontaneousReplace;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * <b style="color:FFC800"><font size="+2">SRClient：自然更替客户端初始化</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">通常进行模组的客户端内容注册</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 < 2023/1/5
 */
@Environment(EnvType.CLIENT)
public class SRClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RegisterClient.register();
    }
}
