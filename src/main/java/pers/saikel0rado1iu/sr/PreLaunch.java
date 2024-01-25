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

import pers.saikel0rado1iu.silk.api.ModBasicData;
import pers.saikel0rado1iu.silk.api.ModPreLaunch;
import pers.saikel0rado1iu.silk.api.callback.RegisterChunkGeneratorCodecCallback;
import pers.saikel0rado1iu.silk.api.registry.gen.world.ChunkGeneratorCodecRegistry;
import pers.saikel0rado1iu.silk.api.registry.gen.world.MultiNoiseBiomeSourceParameterListPresetRegistry;
import pers.saikel0rado1iu.sr.data.Configs;
import pers.saikel0rado1iu.sr.data.SpontaneousReplace;
import pers.saikel0rado1iu.sr.gen.world.MultiNoiseBiomeSourceParameterLists;
import pers.saikel0rado1iu.sr.gen.world.chunk.ClassicChunkGenerator;
import pers.saikel0rado1iu.sr.gen.world.chunk.SnapshotChunkGenerator;

/**
 * <h2 style="color:FFC800">自然更替需要最先运行的主类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class PreLaunch extends ModPreLaunch {
	public PreLaunch() {
		super(SpontaneousReplace.DATA);
	}
	
	@Override
	public void preLaunch(ModBasicData modBasicData) {
		Configs.CONFIGS.reader().load();
		MultiNoiseBiomeSourceParameterListPresetRegistry.add(() -> MultiNoiseBiomeSourceParameterLists.Preset.CLASSIC);
		MultiNoiseBiomeSourceParameterListPresetRegistry.add(() -> MultiNoiseBiomeSourceParameterLists.Preset.SNAPSHOT);
		ChunkGeneratorCodecRegistry.add(() -> new RegisterChunkGeneratorCodecCallback.Data(ClassicChunkGenerator.CODEC, SpontaneousReplace.DATA, "classic"));
		ChunkGeneratorCodecRegistry.add(() -> new RegisterChunkGeneratorCodecCallback.Data(SnapshotChunkGenerator.CODEC, SpontaneousReplace.DATA, "snapshot"));
	}
}
