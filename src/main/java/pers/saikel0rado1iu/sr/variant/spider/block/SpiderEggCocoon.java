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

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Objects;
import java.util.Random;

import static pers.saikel0rado1iu.silk.api.registry.SilkEntityType.POS_SHIFTING;
import static pers.saikel0rado1iu.sr.data.BlockEntities.SPIDER_EGG_COCOON_ENTITY;
import static pers.saikel0rado1iu.sr.data.EntityTypes.SPIDER_LARVA;
import static pers.saikel0rado1iu.sr.data.StatusEffects.SPIDER_CAMOUFLAGE;
import static pers.saikel0rado1iu.sr.variant.spider.block.SpiderEggCocoon.Entity.triggered;

/**
 * <h2 style="color:FFC800">蜘蛛卵茧</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class SpiderEggCocoon extends BlockWithEntity {
	/**
	 * <p>构建蜘蛛卵茧</p>
	 * <p>垂直方向：上</p>
	 *
	 * @param settings 设置
	 */
	public SpiderEggCocoon(Settings settings) {
		super(settings);
		setDefaultState(getDefaultState().with(Properties.VERTICAL_DIRECTION, Direction.UP));
	}
	
	/**
	 * 获取放置方向
	 */
	protected static Direction getDirectionToPlaceAt(WorldView world, BlockPos pos, Direction direction) {
		Direction direction2;
		if (canPlaceAtWithDirection(world, pos, direction)) direction2 = direction;
		else if (canPlaceAtWithDirection(world, pos, direction.getOpposite())) direction2 = direction.getOpposite();
		else return null;
		return direction2;
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
	 * 创建蜘蛛卵茧实体
	 */
	@Override
	public net.minecraft.block.entity.BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new Entity(pos, state);
	}
	
	/**
	 * 方块一旦被破坏立刻生成蜘蛛
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
		triggered(world, pos);
	}
	
	/**
	 * 方块追加属性
	 *
	 * @param builder 构建器
	 */
	@Override
	protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
		builder.add(Properties.VERTICAL_DIRECTION);
	}
	
	/**
	 * 获取渲染类型
	 */
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
	
	/**
	 * 获取放置状态
	 */
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction direction = getDirectionToPlaceAt(ctx.getWorld(), ctx.getBlockPos(), ctx.getVerticalPlayerLookDirection().getOpposite());
		return direction == null ? null : Objects.requireNonNull(super.getPlacementState(ctx)).with(Properties.VERTICAL_DIRECTION, direction);
	}
	
	/**
	 * 如果蜘蛛卵茧的附着方块被破坏则破坏蜘蛛卵茧
	 */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction.getAxis() == Direction.Axis.Y) {
			if (state.get(Properties.VERTICAL_DIRECTION) == Direction.UP && pos.down().getY() == neighborPos.getY() && neighborState.isOf(Blocks.AIR)) {
				triggered((World) world, pos);
			} else if (state.get(Properties.VERTICAL_DIRECTION) == Direction.DOWN && pos.up().getY() == neighborPos.getY() && neighborState.isOf(Blocks.AIR)) {
				triggered((World) world, pos);
			}
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	/**
	 * 设置每 Tick 运行函数
	 */
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return SpiderEggCocoon.validateTicker(type, SPIDER_EGG_COCOON_ENTITY, world.isClient ? Entity::clientTick : Entity::serverTick);
	}
	
	/**
	 * 蜘蛛卵茧方块实体
	 */
	public static class Entity extends BlockEntity {
		/**
		 * 检测范围
		 */
		protected static final float RANGE = 7;
		/**
		 * 粒子个数
		 */
		protected static final int PARTICLE_COUNT = 20;
		
		/**
		 * 构建蜘蛛卵茧实体
		 */
		public Entity(BlockPos pos, BlockState state) {
			super(SPIDER_EGG_COCOON_ENTITY, pos, state);
		}
		
		/**
		 * <p>服务端每 Tick 运行函数</p>
		 * <p>生成蜘蛛</p>
		 */
		public static void serverTick(World world, BlockPos pos, BlockState ignoredState, Entity ignoredBlockEntity) {
			if (canTrigger(world, pos))
				triggered(world, pos);
		}
		
		/**
		 * <p>客户端每 Tick 运行函数</p>
		 * <p>生成音效，粒子</p>
		 */
		public static void clientTick(World world, BlockPos pos, BlockState ignoredState, Entity ignoredBlockEntity) {
			if (canTrigger(world, pos))
				triggered(world, pos);
		}
		
		/**
		 * 是否可以触发
		 */
		protected static boolean canTrigger(World world, BlockPos pos) {
			PlayerEntity player = world.getClosestPlayer(pos.getX() + POS_SHIFTING, pos.getY() + POS_SHIFTING, pos.getZ() + POS_SHIFTING, RANGE, false);
			if (player != null) {
				// 判断是否有“蜘蛛伪装”效果
				boolean haventSpiderCamouflage = true;
				for (StatusEffectInstance statusEffectInstance : player.getStatusEffects()) {
					if (statusEffectInstance.getEffectType() == SPIDER_CAMOUFLAGE)
						haventSpiderCamouflage = false;
				}
				// 如果没有“蜘蛛伪装”效果
				if (haventSpiderCamouflage) {
					// 如果玩家在触发范围内
					return world.isPlayerInRange((double) pos.getX() + POS_SHIFTING, (double) pos.getY() + POS_SHIFTING, (double) pos.getZ() + POS_SHIFTING, RANGE)
							&& !player.isCreative();
				}
			}
			return false;
		}
		
		/**
		 * 触发蜘蛛卵茧并破坏蜘蛛卵茧
		 */
		public static void triggered(World world, BlockPos pos) {
			world.playSound(null, pos, SoundEvents.ENTITY_SPIDER_AMBIENT, SoundCategory.BLOCKS, 1, 1);
			if (world.isClient) {
				Random random = new Random();
				for (int i = 0; i < PARTICLE_COUNT; i++) {
					world.addParticle(ParticleTypes.CLOUD,
							pos.getX() + POS_SHIFTING + random.nextDouble(-1, 1),
							pos.getY() + POS_SHIFTING + random.nextDouble(-1, 1),
							pos.getZ() + POS_SHIFTING + random.nextDouble(-1, 1),
							0, 0, 0);
				}
			} else {
				spawnSpiderEntity(SPIDER_LARVA.create(world), world, pos);
				spawnSpiderEntity(SPIDER_LARVA.create(world), world, pos);
				spawnSpiderEntity(SPIDER_LARVA.create(world), world, pos);
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
		
		/**
		 * 在蜘蛛卵茧位置生成蜘蛛实体
		 *
		 * @param spider 生成的蜘蛛实体
		 */
		public static void spawnSpiderEntity(HostileEntity spider, World world, BlockPos pos) {
			Objects.requireNonNull(spider).initialize((ServerWorld) world, world.getLocalDifficulty(pos), SpawnReason.NATURAL, null, null);
			spider.refreshPositionAndAngles(pos, 0, 0);
			
			((ServerWorld) world).spawnEntityAndPassengers(spider);
		}
	}
}
