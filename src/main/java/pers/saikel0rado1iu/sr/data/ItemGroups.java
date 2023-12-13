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

import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import pers.saikel0rado1iu.silk.api.item.ItemGroupCreator;

/**
 * <h2 style="color:FFC800">自然更替中所有的物品组</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public interface ItemGroups {
	RegistryKey<ItemGroup> BUILDING_BLOCKS = ItemGroupCreator.create(SpontaneousReplace.DATA, "_0_building_blocks");
	RegistryKey<ItemGroup> COLORED_BLOCKS = ItemGroupCreator.create(SpontaneousReplace.DATA, "_1_colored_blocks");
	RegistryKey<ItemGroup> NATURAL = ItemGroupCreator.create(SpontaneousReplace.DATA, "_2_natural_blocks");
	RegistryKey<ItemGroup> FUNCTIONAL = ItemGroupCreator.create(SpontaneousReplace.DATA, "_3_functional_blocks");
	RegistryKey<ItemGroup> REDSTONE = ItemGroupCreator.create(SpontaneousReplace.DATA, "_4_redstone_blocks");
	RegistryKey<ItemGroup> TOOLS = ItemGroupCreator.create(SpontaneousReplace.DATA, "_5_tools_and_utilities");
	RegistryKey<ItemGroup> COMBAT = ItemGroupCreator.create(SpontaneousReplace.DATA, "_6_combat");
	RegistryKey<ItemGroup> FOOD_AND_DRINK = ItemGroupCreator.create(SpontaneousReplace.DATA, "_7_food_and_drinks");
	RegistryKey<ItemGroup> INGREDIENTS = ItemGroupCreator.create(SpontaneousReplace.DATA, "_8_ingredients");
	RegistryKey<ItemGroup> SPAWN_EGGS = ItemGroupCreator.create(SpontaneousReplace.DATA, "_9_spawn_eggs");
}
