package SpontaneousReplace.SpiderBiome.Block.SpiderEggCocoon;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Random;

import static SpontaneousReplace.Generic.SRData.POS_SHIFTING;
import static SpontaneousReplace.SpiderBiome.Block.SpiderEggCocoon.Register.SPIDER_EGG_COCOON_ENTITY;
import static SpontaneousReplace.SpiderBiome.Mobs.GuardSpider.Register.GUARD_SPIDER;
import static SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Register.SPRAY_POISON_SPIDER;
import static SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider.Register.WEAVING_WEB_SPIDER;

/**
 * <b style="color:FFC800"><font size="+2">BlockEntity：蜘蛛卵茧方块实体类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">确定蜘蛛卵茧方块的特殊方块特性</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/2/3 18:17
 */
public class BlockEntity extends net.minecraft.block.entity.BlockEntity {
    /**
     * 构建蜘蛛卵茧实体
     */
    public BlockEntity(BlockPos pos, BlockState state) {
        super(SPIDER_EGG_COCOON_ENTITY, pos, state);
    }

    /**
     * 检测范围
     */
    protected static final float RANGE = 7;
    /**
     * 粒子个数
     */
    protected static final int PARTICLE_COUNT = 20;

    /**
     * <p>服务端每 Tick 运行函数</p>
     * <p>生成蜘蛛</p>
     */
    public static void serverTick(World world, BlockPos pos, BlockState ignoredState, BlockEntity ignoredBlockEntity) {
        if (isPlayerInRange(world, pos)) {
            PlayerEntity player = world.getClosestPlayer(pos.getX() + POS_SHIFTING, pos.getY() + POS_SHIFTING, pos.getZ() + POS_SHIFTING, RANGE, false);
            if (!Objects.requireNonNull(player).isCreative()) {
                spawnSpiderEntity(GUARD_SPIDER.create(world), world, pos);
                spawnSpiderEntity(SPRAY_POISON_SPIDER.create(world), world, pos.east());
                spawnSpiderEntity(WEAVING_WEB_SPIDER.create(world), world, pos.west());
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

    /**
     * <p>客户端每 Tick 运行函数</p>
     * <p>生成音效，粒子</p>
     */
    public static void clientTick(World world, BlockPos pos, BlockState ignoredState, BlockEntity ignoredBlockEntity) {
        if (isPlayerInRange(world, pos)) {
            PlayerEntity player = world.getClosestPlayer(pos.getX() + POS_SHIFTING, pos.getY() + POS_SHIFTING, pos.getZ() + POS_SHIFTING, RANGE, false);
            if (!Objects.requireNonNull(player).isCreative()) {
                world.playSound(null, pos, SoundEvents.ENTITY_SPIDER_AMBIENT, SoundCategory.BLOCKS, 10, 0);
                Random random = new Random();
                for (int i = 0; i < PARTICLE_COUNT; i++) {
                    world.addParticle(ParticleTypes.CLOUD,
                            pos.getX() + POS_SHIFTING + random.nextDouble(-1, 1),
                            pos.getY() + POS_SHIFTING + random.nextDouble(-1, 1),
                            pos.getZ() + POS_SHIFTING + random.nextDouble(-1, 1),
                            0, 0, 0);
                }
            }
        }
    }

    /**
     * 玩家在范围内
     */
    protected static boolean isPlayerInRange(World world, BlockPos pos) {
        return world.isPlayerInRange((double) pos.getX() + POS_SHIFTING, (double) pos.getY() + POS_SHIFTING, (double) pos.getZ() + POS_SHIFTING, RANGE);
    }

    /**
     * 在蜘蛛卵茧位置生成蜘蛛实体
     *
     * @param spider 生成的蜘蛛实体
     */
    protected static void spawnSpiderEntity(HostileEntity spider, World world, BlockPos pos) {
        Objects.requireNonNull(spider).initialize((ServerWorld) world, world.getLocalDifficulty(pos), SpawnReason.NATURAL, null, null);
        BlockPos nowPos = new BlockPos(pos);
        for (int y = 0; y < world.getHeight(); y++) {
            if (world.getBlockState(nowPos).isOf(Blocks.AIR)) {
                spider.refreshPositionAndAngles(nowPos, 0, 0);
                break;
            }
            nowPos = nowPos.up();
        }
        ((ServerWorld) world).spawnEntityAndPassengers(spider);
    }
}
