package SpontaneousReplace.ConfigScreen;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

/**
 * <b style="color:FFC800"><font size="+2">AddModMenuButton：添加 ModMenu 按钮</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加 ModMenu 菜单按钮支持</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 < 2022/11/14
 */
public class AddModMenuButton implements ModMenuApi {
    /**
     * 添加模组菜单按钮
     * @return 配置屏幕
     */
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ConfigScreen::new;
    }
}

