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

package pers.saikel0rado1iu.sr.variant.spider.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderData;

import java.util.Objects;
import java.util.Random;

/**
 * <p><b style="color:FFC800"><font size="+1">蜘蛛茧蛹</font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 */
public class SpiderChrysalis extends HorizontalFacingBlock {
	/**
	 * 蜘蛛茧蛹方块样式
	 */
	protected SpiderData.ChrysalisStyle style = SpiderData.ChrysalisStyle.PLACEHOLDER;
	
	/**
	 * <p>构建蜘蛛茧蛹</p>
	 * <p>茧蛹风格：占位</p>
	 * <p>水平朝向：北</p>
	 * <p>垂直方向：上</p>
	 *
	 * @param settings 设置
	 */
	public SpiderChrysalis(Settings settings) {
		super(settings);
		setDefaultState(getDefaultState()
				.with(SpiderData.CHRYSALIS_STYLE, SpiderData.ChrysalisStyle.PLACEHOLDER)
				.with(Properties.HORIZONTAL_FACING, Direction.NORTH)
				.with(Properties.VERTICAL_DIRECTION, Direction.UP));
	}
	
	/**
	 * 获取随机茧蛹样式
	 *
	 * @return 随机茧蛹风格
	 */
	public static SpiderData.ChrysalisStyle getRandomStyle() {
		int random = new Random().nextInt(100);
		// 22%
		if (random > 77) return SpiderData.ChrysalisStyle.DEFAULT;
			// 22%
		else if (random > 55) return SpiderData.ChrysalisStyle.LARGE;
			// 22%
		else if (random > 33) return SpiderData.ChrysalisStyle.SMALL;
			// 12%
		else if (random > 21) return SpiderData.ChrysalisStyle.HUMANOID;
			// 10%
		else if (random > 11) return SpiderData.ChrysalisStyle.VILLAGER;
			// 10%
		else if (random > 1) return SpiderData.ChrysalisStyle.CHICKEN;
			// 1%
		else if (random > 0) return SpiderData.ChrysalisStyle.CREEPER;
			// 1%
		else return SpiderData.ChrysalisStyle.IRON_GOLEM;
	}
	
	/**
	 * 获取放置方向
	 */
	@Nullable
	private static Direction getDirectionToPlaceAt(WorldView world, BlockPos pos, Direction direction) {
		Direction direction2;
		if (canPlaceAtWithDirection(world, pos, direction)) {
			direction2 = direction;
		} else if (canPlaceAtWithDirection(world, pos, direction.getOpposite())) {
			direction2 = direction.getOpposite();
		} else {
			return null;
		}
		return direction2;
	}
	
	/**
	 * 是双高块
	 */
	public static boolean isDoubleBlock(SpiderData.ChrysalisStyle style) {
		return style != SpiderData.ChrysalisStyle.SMALL && style != SpiderData.ChrysalisStyle.CHICKEN && style != SpiderData.ChrysalisStyle.PLACEHOLDER;
	}
	
	/**
	 * 能放置方向
	 */
	protected static boolean canPlaceAtWithDirection(WorldView world, BlockPos pos, Direction direction) {
		BlockPos blockPos = pos.offset(direction.getOpposite());
		BlockState blockState = world.getBlockState(blockPos);
		return blockState.isSideSolidFullSquare(world, blockPos, direction) || blockState.isIn(BlockTags.LEAVES);
	}
	
	/**
	 * 方块追加属性
	 *
	 * @param builder 构建器
	 */
	@Override
	protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
		builder.add(SpiderData.CHRYSALIS_STYLE, Properties.HORIZONTAL_FACING, Properties.VERTICAL_DIRECTION);
	}
	
	/**
	 * 获取放置状态，随机放置方块不同样式
	 */
	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockPos blockPos = ctx.getBlockPos();
		World world = ctx.getWorld();
		if (world.isClient)
			style = getRandomStyle();
		// 获取方向
		Direction direction = getDirectionToPlaceAt(world, blockPos, ctx.getVerticalPlayerLookDirection().getOpposite());
		if (direction == null)
			return null;
		else if (direction == Direction.UP && world.getBlockState(blockPos.down()).isOf(this))
			return null;
		else if (world.getBlockState(blockPos.up()).isOf(this))
			return null;
		// 保证如果方块被方块阻挡则只能放下小型样式方块
		if (isDoubleBlock(style)) {
			if (direction == Direction.UP) {
				if (!(blockPos.getY() < world.getTopY() - 1 && world.getBlockState(blockPos.up()).canReplace(ctx))) {
					return Objects.requireNonNull(super.getPlacementState(ctx))
							.with(SpiderData.CHRYSALIS_STYLE, SpiderData.ChrysalisStyle.SMALL)
							.with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite())
							.with(Properties.VERTICAL_DIRECTION, direction);
				}
			} else if (!(blockPos.getY() > world.getBottomY() + 1 && world.getBlockState(blockPos.down()).canReplace(ctx))) {
				return Objects.requireNonNull(super.getPlacementState(ctx))
						.with(SpiderData.CHRYSALIS_STYLE, SpiderData.ChrysalisStyle.SMALL)
						.with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite())
						.with(Properties.VERTICAL_DIRECTION, direction);
			}
		}
		return Objects.requireNonNull(super.getPlacementState(ctx))
				.with(SpiderData.CHRYSALIS_STYLE, style)
				.with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite())
				.with(Properties.VERTICAL_DIRECTION, direction);
	}
	
	/**
	 * 获取方块的轮廓形状
	 */
	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		VoxelShape placeholderShortVoxelShape = null;
		if (state.get(SpiderData.CHRYSALIS_STYLE) == SpiderData.ChrysalisStyle.PLACEHOLDER_SHORT) {
			if (state.get(Properties.VERTICAL_DIRECTION) == Direction.UP) {
				placeholderShortVoxelShape = VoxelShapes.cuboid(0.0625, -0.0625, 0.0625, 0.9375, 0.5, 0.9375);
			} else {
				placeholderShortVoxelShape = VoxelShapes.cuboid(0.0625, 0.5, 0.0625, 0.9375, 1.0625, 0.9375);
			}
		}
		
		if (state.get(Properties.VERTICAL_DIRECTION) == Direction.UP) {
			return switch (state.get(SpiderData.CHRYSALIS_STYLE)) {
				case PLACEHOLDER, IRON_GOLEM, HUMANOID, VILLAGER, CREEPER -> VoxelShapes.cuboid(0, 0, 0, 1, 1, 1);
				case DEFAULT -> VoxelShapes.cuboid(0, 0, 0, 1, 0.9375, 1);
				case LARGE -> VoxelShapes.cuboid(-0.125, 0, -0.125, 1.125, 1.5, 1.125);
				case SMALL, CHICKEN -> VoxelShapes.cuboid(0.0625, 0, 0.0625, 0.9375, 1, 0.9375);
				case PLACEHOLDER_SHORT -> placeholderShortVoxelShape;
			};
		} else {
			return switch (state.get(SpiderData.CHRYSALIS_STYLE)) {
				case PLACEHOLDER, IRON_GOLEM, HUMANOID, VILLAGER, CREEPER -> VoxelShapes.cuboid(0, 0, 0, 1, 1, 1);
				case DEFAULT -> VoxelShapes.cuboid(0, 0.0625, 0, 1, 1, 1);
				case LARGE -> VoxelShapes.cuboid(-0.125, -0.5, -0.125, 1.125, 1, 1.125);
				case SMALL, CHICKEN -> VoxelShapes.cuboid(0.0625, 0, 0.0625, 0.9375, 1, 0.9375);
				case PLACEHOLDER_SHORT -> placeholderShortVoxelShape;
			};
		}
	}
	
	/**
	 * 方块能放在？
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return canPlaceAtWithDirection(world, pos, state.get(Properties.VERTICAL_DIRECTION));
	}
	
	/**
	 * 当双高方块被放置时放置碰撞箱占位方块
	 */
	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		if (isDoubleBlock(state.get(SpiderData.CHRYSALIS_STYLE))) {
			BlockPos blockPos = pos.up();
			Direction blockState = Direction.UP;
			if (state.get(Properties.VERTICAL_DIRECTION) == Direction.DOWN) {
				blockPos = pos.down();
				blockState = Direction.DOWN;
			}
			if (state.get(SpiderData.CHRYSALIS_STYLE) == SpiderData.ChrysalisStyle.DEFAULT)
				world.setBlockState(blockPos, getDefaultState().with(SpiderData.CHRYSALIS_STYLE, SpiderData.ChrysalisStyle.PLACEHOLDER_SHORT).with(Properties.VERTICAL_DIRECTION, blockState), net.minecraft.block.Block.NOTIFY_ALL);
			else
				world.setBlockState(blockPos, getDefaultState().with(SpiderData.CHRYSALIS_STYLE, SpiderData.ChrysalisStyle.PLACEHOLDER).with(Properties.VERTICAL_DIRECTION, blockState), net.minecraft.block.Block.NOTIFY_ALL);
		} else super.onPlaced(world, pos, state, placer, itemStack);
	}
	
	/**
	 * 在双高块其中一块被破坏时移除另一块
	 */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (isDoubleBlock(state.get(SpiderData.CHRYSALIS_STYLE))) {
			if (direction.getAxis() == Direction.Axis.Y && neighborState.isOf(Blocks.AIR))
				return Blocks.AIR.getDefaultState();
		} else {
			if (direction.getAxis() == Direction.Axis.Y) {
				if (state.get(Properties.VERTICAL_DIRECTION) == Direction.UP && pos.down().getY() == neighborPos.getY() && neighborState.isOf(Blocks.AIR))
					return Blocks.AIR.getDefaultState();
				else if (state.get(Properties.VERTICAL_DIRECTION) == Direction.DOWN && pos.up().getY() == neighborPos.getY() && neighborState.isOf(Blocks.AIR))
					return Blocks.AIR.getDefaultState();
			}
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	/**
	 * 在方块被破坏后双高块取消一个方块掉落
	 */
	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockPos blockPos = pos.down();
		BlockState blockState;
		if (!world.isClient && player.isCreative()) {
			if (state.get(Properties.VERTICAL_DIRECTION) == Direction.UP && (blockState = world.getBlockState(blockPos)).isOf(state.getBlock())) {
				world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), net.minecraft.block.Block.NOTIFY_ALL | net.minecraft.block.Block.SKIP_DROPS);
				world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, net.minecraft.block.Block.getRawIdFromState(blockState));
			} else if (state.get(Properties.VERTICAL_DIRECTION) == Direction.DOWN && (blockState = world.getBlockState(blockPos = pos.up())).isOf(state.getBlock())) {
				world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), net.minecraft.block.Block.NOTIFY_ALL | net.minecraft.block.Block.SKIP_DROPS);
				world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, net.minecraft.block.Block.getRawIdFromState(blockState));
			}
		}
		super.onBreak(world, pos, state, player);
	}
}
