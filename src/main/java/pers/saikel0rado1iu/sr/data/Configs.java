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

package pers.saikel0rado1iu.sr.data;

import net.fabricmc.loader.api.FabricLoader;
import pers.saikel0rado1iu.silk.util.config.ConfigData;
import pers.saikel0rado1iu.silk.util.update.UpdateData;

/**
 * <h2 style="color:FFC800">自然更替的配置数据类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface Configs {
	ConfigData DEV_CONFIGS = ConfigData.builder(SpontaneousReplace.DATA).type(ConfigData.Type.DEV).build()
			.addSwitch(DevKeys.DISABLE_MOD_BUTTON, false);
	String AUTO_SHOW_SETTINGS_BUTTON = "auto_show_settings_button";
	String DISABLE_WARNING_ADVICE = "disable_warning_advice";
	ConfigData CONFIGS = ConfigData.builder(SpontaneousReplace.DATA).build()
			.addSwitch(AUTO_SHOW_SETTINGS_BUTTON, false)
			.addOption(DISABLE_WARNING_ADVICE, DisableWarningAdvice.NEED_OTHER_MOD)
			.addSubConfigs("dev", DEV_CONFIGS);
	UpdateData UPDATE_DATA = new UpdateData(SpontaneousReplace.DATA, Configs.CONFIGS);
	
	static boolean canShowSettingsButton() {
		return !(FabricLoader.getInstance().isModLoaded("modmenu") && CONFIGS.getConfig(AUTO_SHOW_SETTINGS_BUTTON, Boolean.class));
	}
	
	enum DisableWarningAdvice {
		NEED_OTHER_MOD
	}
	
	interface DevKeys {
		String DISABLE_MOD_BUTTON = "disable_mod_button";
	}
}
