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

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import pers.saikel0rado1iu.silk.api.registry.SilkSoundEvent;

/**
 * <h2 style="color:FFC800">自然更替的所有的声音事件</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class SoundEvents extends SilkSoundEvent {
	public static final RegistryEntry.Reference<SoundEvent> EQUIP_REFINED_COPPER = referenceBuilder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.copper.equip"))).build();
	public static final RegistryEntry.Reference<SoundEvent> EQUIP_CUFE_ALLOY = referenceBuilder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.cufe.equip"))).build();
	public static final RegistryEntry.Reference<SoundEvent> EQUIP_AUCU_ALLOY = referenceBuilder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.aucu.equip"))).build();
	public static final RegistryEntry.Reference<SoundEvent> EQUIP_STEEL = referenceBuilder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.steel.equip"))).build();
	public static final SoundEvent STONEBALL_THROW = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.stoneball.throw"))).build();
	public static final SoundEvent SLINGSHOT_THROW = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.slingshot.throw"))).build();
	public static final SoundEvent ARBALEST_LOADING_START = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.arbalest.loading.start"))).build();
	public static final SoundEvent ARBALEST_LOADING_MIDDLE = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.arbalest.loading.middle"))).build();
	public static final SoundEvent ARBALEST_LOADING_END = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.arbalest.loading.end"))).build();
	public static final SoundEvent ARBALEST_QUICK_CHARGE_1 = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.arbalest.quick_charge.1"))).build();
	public static final SoundEvent ARBALEST_QUICK_CHARGE_2 = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.arbalest.quick_charge.2"))).build();
	public static final SoundEvent ARBALEST_QUICK_CHARGE_3 = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.arbalest.quick_charge.3"))).build();
	public static final SoundEvent ARBALEST_SHOOT = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.arbalest.shoot"))).build();
	public static final SoundEvent JUGER_REPEATING_CROSSBOW_LOADING_START = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.juger_repeating_crossbow.loading.start"))).build();
	public static final SoundEvent JUGER_REPEATING_CROSSBOW_LOADING_END = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.juger_repeating_crossbow.loading.end"))).build();
	public static final SoundEvent JUGER_REPEATING_CROSSBOW_SHOOT = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.juger_repeating_crossbow.shoot"))).build();
	public static final SoundEvent MARKS_CROSSBOW_LOADING_START = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.marks_crossbow.loading.start"))).build();
	public static final SoundEvent MARKS_CROSSBOW_LOADING_MIDDLE = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.marks_crossbow.loading.middle"))).build();
	public static final SoundEvent MARKS_CROSSBOW_LOADING_END = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.marks_crossbow.loading.end"))).build();
	public static final SoundEvent MARKS_CROSSBOW_QUICK_CHARGE_1 = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.marks_crossbow.quick_charge.1"))).build();
	public static final SoundEvent MARKS_CROSSBOW_QUICK_CHARGE_2 = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.marks_crossbow.quick_charge.2"))).build();
	public static final SoundEvent MARKS_CROSSBOW_QUICK_CHARGE_3 = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.marks_crossbow.quick_charge.3"))).build();
	public static final SoundEvent MARKS_CROSSBOW_SHOOT = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.marks_crossbow.shoot"))).build();
	public static final RegistryEntry.Reference<SoundEvent> EQUIP_ARROWPROOF_VEST = referenceBuilder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "vanilla.arrowproof_vest.equip"))).build();
	public static final SoundEvent TREACHEROUS_SAC_BREAK = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "variant.treacherous_sac.break"))).build();
	public static final SoundEvent SPRAY_TOXIN = builder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "spider.spray_poison_spider.spray_toxin"))).build();
	public static final RegistryEntry.Reference<SoundEvent> EQUIP_SPIDER_LEATHER_ARMOR = referenceBuilder(SoundEvent.of(new Identifier(SpontaneousReplace.DATA.getId(), "spider.spider_leather_armor.equip"))).build();
}
