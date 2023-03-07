package SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Toxin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import static SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Register.TOXIN;
import static SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Register.TOXIN_PARTICLE;

/**
 * <b style="color:FFC800"><font size="+2">ToxinEntity：毒素实体类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">毒素的所有实体属性，逻辑控制</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/01/07
 */
public class ToxinEntity extends ProjectileEntity {
    /**
     * 构建毒素实体
     */
    public ToxinEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * 构建毒素实体
     */
    public ToxinEntity(World world, MobEntity owner) {
        this(TOXIN, world);
        setOwner(owner);
        setPosition(owner.getX() - (double) (owner.getWidth() + 1.0f) * 0.5 * (double) MathHelper.sin(owner.bodyYaw * ((float) Math.PI / 180)),
                owner.getEyeY() - (double) 0.1f, owner.getZ() + (double) (owner.getWidth() + 1.0f) * 0.5 * (double) MathHelper.cos(owner.bodyYaw * ((float) Math.PI / 180)));
    }

    @Override
    protected void initDataTracker() {
    }

    /**
     * 每刻计算
     */
    @Override
    public void tick() {
        super.tick();
        Vec3d vec3d = getVelocity();
        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        onCollision(hitResult);
        double d = getX() + vec3d.x;
        double e = getY() + vec3d.y;
        double f = getZ() + vec3d.z;
        updateRotation();
        if (world.getStatesInBox(getBoundingBox()).noneMatch(AbstractBlock.AbstractBlockState::isAir)) {
            discard();
            return;
        }
        if (isInsideWaterOrBubbleColumn()) {
            discard();
            return;
        }
        setVelocity(vec3d.multiply(1f));
        if (!hasNoGravity()) {
            setVelocity(getVelocity().add(0.0, -0.06f, 0.0));
        }
        setPosition(d, e, f);
    }

    /**
     * 如果命中实体
     */
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        net.minecraft.entity.Entity entity = entityHitResult.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.damage(getDamageSources().mobProjectile(this, (LivingEntity) entity), 1.0f);
            livingEntity.addStatusEffect((new StatusEffectInstance(StatusEffects.POISON, 20 * 15, 0)));
        }
    }

    /**
     * 如果命中方块
     */
    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!world.isClient) {
            discard();
        }
    }

    /**
     * 如果命中生成包
     */
    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        double d = packet.getVelocityX();
        double e = packet.getVelocityY();
        double f = packet.getVelocityZ();
        for (int i = 0; i < 7; ++i) {
            double g = 0.4 + 0.1 * (double) i;
            world.addParticle(TOXIN_PARTICLE, getX(), getY(), getZ(), d * g, e, f * g);
        }
        setVelocity(d, e, f);
    }
}
