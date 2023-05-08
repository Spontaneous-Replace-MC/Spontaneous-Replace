package SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon.Stoneball;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import static SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon.Stoneball.Server.STONEBALL;
import static SpontaneousReplace.VanillaExtension.RangedRelated.BasicWeapon.Stoneball.Server.STONEBALL_ENTITY;


/**
 * <b style="color:FFC800"><font size="+2">StoneballEntity：石弹实体</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">设置石弹的实体操作</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/4/24 22:20
 */
public class StoneballEntity extends ThrownItemEntity {
    // region 构建石弹实体
    public StoneballEntity(EntityType<? extends StoneballEntity> entityType, World world) {
        super(entityType, world);
    }

    public StoneballEntity(World world, LivingEntity owner) {
        super(STONEBALL_ENTITY, owner, world);
    }

    public StoneballEntity(World world, double x, double y, double z) {
        super(STONEBALL_ENTITY, x, y, z, world);
    }
    // endregion

    protected int punchLevel = 0;
    protected Vec3d velocity = null;

    /**
     * 获取默认物品
     */
    protected Item getDefaultItem() {
        return STONEBALL;
    }

    /**
     * 获取粒子参数
     */
    private ParticleEffect getParticleParameters() {
        return new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(getDefaultItem()));
    }

    /**
     * 句柄状态
     */
    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for (int i = 0; i < 8; ++i) {
                this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    /**
     * 在击中实体时的操作
     */
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        // 设置生物实体击退
        if (punchLevel > 0 && entity instanceof LivingEntity)
            ((LivingEntity) entity).takeKnockback(punchLevel, -velocity.getX(), -velocity.getZ());
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 0.01F);
    }

    /**
     * 在碰撞时
     */
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte) 3);
            this.discard();
        }

    }

    /**
     * 设置"冲击"等级
     */
    public void setPunchLevel(int level, Vec3d speed) {
        punchLevel = level;
        velocity = speed;
    }
}
