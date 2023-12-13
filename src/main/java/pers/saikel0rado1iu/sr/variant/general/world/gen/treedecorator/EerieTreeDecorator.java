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

package pers.saikel0rado1iu.sr.variant.general.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import pers.saikel0rado1iu.sr.data.word.TreeDecoratorTypes;

import static pers.saikel0rado1iu.sr.data.Blocks.EERIE_BOUGH;

/**
 * <h2 style="color:FFC800">阴森木根树木装饰器</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class EerieTreeDecorator extends TreeDecorator {
	public static final EerieTreeDecorator INSTANCE = new EerieTreeDecorator();
	public static final Codec<EerieTreeDecorator> CODEC = Codec.unit(() -> INSTANCE);
	
	private EerieTreeDecorator() {
	}
	
	@Override
	protected TreeDecoratorType<?> getType() {
		return TreeDecoratorTypes.EERIE_ROOT_TREE_DECORATOR;
	}
	
	@Override
	public void generate(Generator generator) {
		BlockPos pos = generator.getLogPositions().get(0).up();
		for (Direction side : Direction.Type.HORIZONTAL) {
			BlockPos targetPosition = pos.offset(side, 1);
			if (generator.getWorld().testBlockState(targetPosition.down(), (state -> state.isSideSolidFullSquare((BlockView) generator.getWorld(), targetPosition.down(), Direction.UP))))
				generator.replace(targetPosition, EERIE_BOUGH.withConnectionProperties((BlockView) generator.getWorld(), targetPosition));
		}
	}
}
