package pres.saikel_orado.spontaneous_replace.mod.variant.general.block.treacheroussac;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Arm;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.TREACHEROUS_SAC_BREAK;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.TREACHEROUS_SAC_ENTITY;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRUtil.getTick;
import static pres.saikel_orado.spontaneous_replace.mod.variant.general.data.SRVariantData.TREACHEROUS_SAC_EXPLODE_POWER;

/**
 * <b style="color:FFC800"><font size="+2">TreacherousSacEntity：诡谲囊实体</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">负责处理爆炸动画与爆炸延迟，并且通过减少方块实体优化性能</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/20 9:33
 */
public class TreacherousSacEntity extends LivingEntity {
    public final AnimationState explodeAnimationState = new AnimationState();
    protected LivingEntity detonator;
    protected int delay = 0;

    public TreacherousSacEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
        detonator = null;
    }

    protected TreacherousSacEntity(World world, LivingEntity detonator) {
        super(TREACHEROUS_SAC_ENTITY, world);
        this.detonator = detonator;
    }

    /**
     * 设置实体数值
     */
    public static DefaultAttributeContainer.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, Float.MAX_VALUE)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0);
    }

    /**
     * 延迟爆炸 1s
     */
    @Override
    public void tick() {
        if (!getWorld().isClient) {
            if (delay == 0)
                getWorld().playSound(null, getBlockPos(), TREACHEROUS_SAC_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (delay > getTick(1)) explode(getWorld(), getBlockPos(), detonator);
            else delay++;
        } else explodeAnimationState.startIfNotRunning(age);
    }

    /**
     * 爆炸处理：爆炸会产生一大片酸性气体云
     */
    public void explode(World world, BlockPos pos, @Nullable LivingEntity detonator) {
        world.emitGameEvent(detonator, GameEvent.EXPLODE, pos);
        float[] fixedPos = {pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F};
        world.createExplosion(null, fixedPos[0], fixedPos[1], fixedPos[2], TREACHEROUS_SAC_EXPLODE_POWER, World.ExplosionSourceType.BLOCK);
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(world, fixedPos[0], fixedPos[1], fixedPos[2]);
        if (detonator != null) areaEffectCloudEntity.setOwner(detonator);
        areaEffectCloudEntity.setRadius(3.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float) areaEffectCloudEntity.getDuration());
        areaEffectCloudEntity.setPotion(Potions.STRONG_POISON);
        areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffects.POISON, getTick(15), 1));
        areaEffectCloudEntity.setColor(0xFF6A00);
        world.spawnEntity(areaEffectCloudEntity);
        discard();
    }

    /**
     * 模仿方块：不处理移动
     */
    @Override
    public void move(MovementType movementType, Vec3d movement) {
    }

    /**
     * 模仿方块：不会被玩家伤害
     */
    @Override
    public boolean damage(DamageSource source, float amount) {
        return source.isOf(DamageTypes.GENERIC_KILL) && super.damage(source, amount);
    }

    /**
     * 模仿方块：不能拥有状态
     */
    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        return false;
    }

    /**
     * 模仿方块：对爆炸免疫
     */
    @Override
    public boolean isImmuneToExplosion() {
        return true;
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return List.of(ItemStack.EMPTY);
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }
}
