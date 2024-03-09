/*
 * This file is part of Silk API.
 * Copyright (C) 2023 Saikel Orado Liu
 *
 * Silk API is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Silk API is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Silk API. If not, see <https://www.gnu.org/licenses/>.
 */

package pers.saikel0rado1iu.sr.data;

import net.minecraft.component.DataComponentType;
import pers.saikel0rado1iu.silk.api.registry.SilkDataComponentType;
import pers.saikel0rado1iu.sr.vanilla.ranged.JugerRepeatingCrossbow;
import pers.saikel0rado1iu.sr.vanilla.ranged.MarksCrossbow;

/**
 * <h2 style="color:FFC800">自然更替的所有的数据组件类型</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class DataComponentTypes extends SilkDataComponentType {
	public static final DataComponentType<MarksCrossbow.ShootEndComponent> SHOOT_END = builder(MarksCrossbow.ShootEndComponent.class,
			builder -> builder.codec(MarksCrossbow.ShootEndComponent.CODEC))
			.build(SpontaneousReplace.DATA, "shoot_end");
	public static final DataComponentType<JugerRepeatingCrossbow.IsShootComponent> IS_SHOOT = builder(JugerRepeatingCrossbow.IsShootComponent.class,
			builder -> builder.codec(JugerRepeatingCrossbow.IsShootComponent.CODEC))
			.build(SpontaneousReplace.DATA, "is_shoot");
	public static final DataComponentType<JugerRepeatingCrossbow.ChargedProjectileNumComponent> CHARGED_PROJECTILE_NUM = builder(JugerRepeatingCrossbow.ChargedProjectileNumComponent.class,
			builder -> builder.codec(JugerRepeatingCrossbow.ChargedProjectileNumComponent.CODEC))
			.build(SpontaneousReplace.DATA, "charged_projectile_num");
}
