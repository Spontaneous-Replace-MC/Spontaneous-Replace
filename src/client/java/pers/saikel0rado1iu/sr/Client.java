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

package pers.saikel0rado1iu.sr;

import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.world.WorldCreator;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import pers.saikel0rado1iu.silk.api.ModBasicData;
import pers.saikel0rado1iu.silk.api.ModClient;
import pers.saikel0rado1iu.silk.api.callback.GameMenuScreenAddButtonCallback;
import pers.saikel0rado1iu.silk.api.callback.WorldPresetCustomButtonCallback;
import pers.saikel0rado1iu.silk.api.callback.WorldPresetSetDefaultCallback;
import pers.saikel0rado1iu.silk.api.pack.ResourcesPack;
import pers.saikel0rado1iu.silk.api.registry.*;
import pers.saikel0rado1iu.silk.gen.data.SilkLanguageProvider;
import pers.saikel0rado1iu.silk.util.TextUtil;
import pers.saikel0rado1iu.silk.util.update.UpdateSystem;
import pers.saikel0rado1iu.sr.data.Configs;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;
import pers.saikel0rado1iu.sr.data.client.*;
import pers.saikel0rado1iu.sr.gen.world.WorldPresets;
import pers.saikel0rado1iu.sr.screen.ConfigScreen;
import pers.saikel0rado1iu.sr.screen.PlaceholderScreen;

/**
 * <h2 style="color:FFC800">自然更替客户端类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class Client extends ModClient {
	public Client() {
		super(SpontaneousReplace.DATA);
	}
	
	@Override
	public void client(ModBasicData modBasicData) {
		UpdateSystem.registryUpdate(Configs.UPDATE_DATA, true);
		GameMenuScreenAddButtonCallback.EVENT.register((adder, client, screen) -> {
			if (Configs.canShowSettingsButton()) {
				adder.add(ButtonWidget.builder(Text.translatable(TextUtil.configText(SpontaneousReplace.DATA, "")),
						button -> client.setScreen(new ConfigScreen(screen))).width(204).build(), 2);
			}
		});
		WorldPresetCustomButtonCallback.EVENT.register((worldType, minecraftClient, screen) ->
				worldType.getName().equals(Text.translatable(SilkLanguageProvider.getWorldPresetKey(WorldPresets.CLASSIC))) ?
						button -> MinecraftClient.getInstance().setScreen(new PlaceholderScreen(screen)) : null
		);
		WorldPresetCustomButtonCallback.EVENT.register((worldType, minecraftClient, screen) ->
				worldType.getName().equals(Text.translatable(SilkLanguageProvider.getWorldPresetKey(WorldPresets.SNAPSHOT))) ?
						button -> MinecraftClient.getInstance().setScreen(new PlaceholderScreen(screen)) : null
		);
		WorldPresetSetDefaultCallback.EVENT.register(worldCreator -> {
			for (WorldCreator.WorldType worldType : worldCreator.getNormalWorldTypes())
				if (worldType.getName().equals(Text.translatable(SilkLanguageProvider.getWorldPresetKey(WorldPresets.CLASSIC)))) return worldType;
			return worldCreator.getWorldType();
		});
	}
	
	@Override
	public SilkItem items() {
		return new Items();
	}
	
	@Override
	public SilkBlock blocks() {
		return new Blocks();
	}
	
	@Override
	public SilkEntityType entityTypes() {
		return new EntityTypes();
	}
	
	@Override
	public SilkParticleType particleTypes() {
		return new ParticleTypes();
	}
	
	@Override
	public SilkModelLayer modelLayers() {
		return new ModelLayers();
	}
	
	@Override
	public ResourcesPack modResourcesPack(ModBasicData mod, ResourcePackActivationType type) {
		return new ResourcesPack(mod, type, "pack");
	}
}
