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

package pers.saikel0rado1iu.sr.mixin.screen;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pers.saikel0rado1iu.silk.util.TextUtil;
import pers.saikel0rado1iu.sr.data.Configs;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;
import pers.saikel0rado1iu.sr.screen.SpontaneousReplaceScreen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static pers.saikel0rado1iu.silk.util.config.ConfigData.CHARSET;
import static pers.saikel0rado1iu.sr.data.Configs.*;

/**
 * <p><b style="color:FFC800"><font size="+1">设置自然更替模组屏幕到标题屏幕</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
@Mixin(TitleScreen.class)
abstract class SpontaneousReplaceScreenMixin extends Screen {
	private SpontaneousReplaceScreenMixin(Text title) {
		super(title);
	}
	
	@Unique
	private static boolean is5Button() {
		if (DEV_CONFIGS.getConfig(DevKeys.DISABLE_MOD_BUTTON, Boolean.class)) return false;
		try {
			if (!CONFIGS.getConfig(AUTO_SHOW_SETTINGS_BUTTON, Boolean.class)) {
				if (FabricLoader.getInstance().isModLoaded("modmenu")) {
					Path path = Paths.get(FabricLoader.getInstance().getConfigDir().toString(), "modmenu.json");
					JsonObject modMenuJson = (JsonObject) JsonParser.parseString(Files.readString(path, CHARSET));
					String style = modMenuJson.get("mods_button_style").getAsString();
					return "classic".equals(style);
				}
				return false;
			}
			return false;
		} catch (IOException e) {
			return true;
		}
	}
	
	@Inject(method = "initWidgetsNormal", at = @At("TAIL"))
	private void addButton(int y, int spacingY, CallbackInfo ci) {
		if (Configs.canShowSettingsButton()) {
			addDrawableChild(ButtonWidget.builder(Text.translatable(TextUtil.widgetText(SpontaneousReplace.DATA, "")), button -> MinecraftClient.getInstance().setScreen(new SpontaneousReplaceScreen(this)))
					.dimensions(width / 2 - 100, y - (is5Button() ? spacingY * 2 : spacingY), 200, 20).build());
		}
	}
	
	@ModifyArg(method = "init", at = @At(value = "INVOKE", target = "L net/minecraft/client/realms/gui/screen/RealmsNotificationsScreen;init(L net/minecraft/client/MinecraftClient;II)V"), index = 2)
	private int fixRealms(int height) {
		return is5Button() ? height + 40 : height;
	}
	
	@ModifyVariable(method = "initWidgetsNormal(II)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
	private int ifHasModMenu(int y) {
		return is5Button() ? y + (int) ((double) y / width * width / y * 10) : y;
	}
}
