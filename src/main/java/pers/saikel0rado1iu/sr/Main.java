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
import pers.saikel0rado1iu.silk.api.ModBasicData;
import pers.saikel0rado1iu.silk.api.ModMain;
import pers.saikel0rado1iu.silk.api.pack.DataPack;
import pers.saikel0rado1iu.silk.api.registry.*;
import pers.saikel0rado1iu.sr.data.*;

/**
 * <h2 style="color:FFC800">自然更替主类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class Main extends ModMain {
	public Main() {
		super(SpontaneousReplace.DATA);
	}
	
	@Override
	public void main(ModBasicData modBasicData) {
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
	public SilkWorldData worldData() {
		return new WorldData();
	}
	
	@Override
	public SilkSoundEvent soundEvents() {
		return new SoundEvents();
	}
	
	@Override
	public SilkStatusEffect statusEffects() {
		return new StatusEffects();
	}
	
	@Override
	public SilkEntityType entityTypes() {
		return new EntityTypes();
	}
	
	@Override
	public SilkBlockEntity blockEntities() {
		return new BlockEntities();
	}
	
	@Override
	public SilkParticleType particleTypes() {
		return new ParticleTypes();
	}
	
	@Override
	public DataPack modDataPack(ModBasicData mod, ResourcePackActivationType type) {
		return new DataPack(mod, type, "pack");
	}
}
