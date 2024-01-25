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

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Position;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import pers.saikel0rado1iu.silk.api.registry.SilkEntityType;
import pers.saikel0rado1iu.silk.util.SpawnUtil;
import pers.saikel0rado1iu.sr.vanilla.ranged.projectile.steelarrow.SteelArrowEntity;
import pers.saikel0rado1iu.sr.vanilla.ranged.projectile.stoneball.StoneballEntity;
import pers.saikel0rado1iu.sr.variant.general.block.treacherous.TreacherousSacEntity;
import pers.saikel0rado1iu.sr.variant.spider.mob.guard.GuardSpiderData;
import pers.saikel0rado1iu.sr.variant.spider.mob.guard.GuardSpiderEntity;
import pers.saikel0rado1iu.sr.variant.spider.mob.larva.SpiderLarvaData;
import pers.saikel0rado1iu.sr.variant.spider.mob.larva.SpiderLarvaEntity;
import pers.saikel0rado1iu.sr.variant.spider.mob.spray.SprayPoisonSpiderData;
import pers.saikel0rado1iu.sr.variant.spider.mob.spray.SprayPoisonSpiderEntity;
import pers.saikel0rado1iu.sr.variant.spider.mob.spray.ToxinEntity;
import pers.saikel0rado1iu.sr.variant.spider.mob.weaving.WeavingWebSpiderData;
import pers.saikel0rado1iu.sr.variant.spider.mob.weaving.WeavingWebSpiderEntity;

/**
 * <h2 style="color:FFC800">自然更替的所有实体类型</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class EntityTypes extends SilkEntityType {
	public static final EntityType<StoneballEntity> STONEBALL = builder(FabricEntityTypeBuilder.<StoneballEntity>create(SpawnGroup.MISC, StoneballEntity::new)
			.dimensions(EntityDimensions.fixed(PROJECTILE_BOX, PROJECTILE_BOX)).trackRangeBlocks(PROJECTILE_RANGE).trackedUpdateRate(PROJECTILE_UPDATE_RATE).build())
			.otherRegister(entityType -> DispenserBlock.registerBehavior(Items.STONEBALL, new ProjectileDispenserBehavior() {
				@Override
				protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
					return new StoneballEntity(world, position.getX(), position.getY(), position.getZ());
				}
			})).build(SpontaneousReplace.DATA, "stoneball");
	public static final EntityType<SteelArrowEntity> STEEL_ARROW = builder(FabricEntityTypeBuilder.<SteelArrowEntity>create(SpawnGroup.MISC, SteelArrowEntity::new)
			.dimensions(EntityDimensions.fixed(PROJECTILE_BOX * 1.25F, PROJECTILE_BOX * 1.25F)).trackRangeBlocks(PROJECTILE_RANGE * 10).trackedUpdateRate(PROJECTILE_UPDATE_RATE).build())
			.otherRegister(entityType -> DispenserBlock.registerBehavior(Items.STEEL_ARROW, new ProjectileDispenserBehavior() {
				@Override
				protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
					SteelArrowEntity arrowEntity = new SteelArrowEntity(world, position.getX(), position.getY(), position.getZ(), stack);
					arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
					return arrowEntity;
				}
			})).build(SpontaneousReplace.DATA, "steel_arrow");
	public static final EntityType<TreacherousSacEntity> TREACHEROUS_SAC = builder(FabricEntityTypeBuilder.create(SpawnGroup.MISC, TreacherousSacEntity::new)
			.dimensions(EntityDimensions.fixed(1, 1)).build())
			.otherRegister(treacherousSacEntityEntityType -> FabricDefaultAttributeRegistry.register(treacherousSacEntityEntityType, TreacherousSacEntity.createAttributes()))
			.build(SpontaneousReplace.DATA, "treacherous_sac");
	public static final EntityType<SpiderLarvaEntity> SPIDER_LARVA = builder(FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.MONSTER).entityFactory(SpiderLarvaEntity::new)
			.dimensions(EntityDimensions.fixed(SpiderLarvaData.BOX_WEIGHT, SpiderLarvaData.BOX_HEIGHT)).build())
			.otherRegister(spiderLarvaEntityEntityType -> FabricDefaultAttributeRegistry.register(spiderLarvaEntityEntityType, SpiderLarvaEntity.createSpiderAttributes()))
			.build(SpontaneousReplace.DATA, SpiderLarvaData.ID);
	public static final EntityType<GuardSpiderEntity> GUARD_SPIDER = builder(FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.MONSTER).entityFactory(GuardSpiderEntity::new)
			.spawnRestriction(SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnUtil.builder(GuardSpiderEntity.class).monster().biome(biomeRegistryEntry -> biomeRegistryEntry.isIn(Tags.Biome.IS_SPIDER_BIOME)).build())
			.dimensions(EntityDimensions.fixed(GuardSpiderData.BOX_WEIGHT, GuardSpiderData.BOX_HEIGHT)).build())
			.otherRegister(guardSpiderEntityEntityType -> FabricDefaultAttributeRegistry.register(guardSpiderEntityEntityType, GuardSpiderEntity.createSpiderAttributes()))
			.build(SpontaneousReplace.DATA, GuardSpiderData.ID);
	public static final EntityType<ToxinEntity> TOXIN = builder(FabricEntityTypeBuilder.<ToxinEntity>create(SpawnGroup.MISC, ToxinEntity::new)
			.dimensions(EntityDimensions.fixed(PROJECTILE_BOX, PROJECTILE_BOX)).trackRangeBlocks(PROJECTILE_RANGE).trackedUpdateRate(PROJECTILE_UPDATE_RATE).build())
			.build(SpontaneousReplace.DATA, "toxin_projectile");
	public static final EntityType<SprayPoisonSpiderEntity> SPRAY_POISON_SPIDER = builder(FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.MONSTER).entityFactory(SprayPoisonSpiderEntity::new)
			.spawnRestriction(SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnUtil.builder(SprayPoisonSpiderEntity.class).monster().biome(biomeRegistryEntry -> biomeRegistryEntry.isIn(Tags.Biome.IS_SPIDER_BIOME)).build())
			.dimensions(EntityDimensions.fixed(SprayPoisonSpiderData.BOX_WEIGHT, SprayPoisonSpiderData.BOX_HEIGHT)).build())
			.otherRegister(sprayPoisonSpiderEntityEntityType -> FabricDefaultAttributeRegistry.register(sprayPoisonSpiderEntityEntityType, SprayPoisonSpiderEntity.createSpiderAttributes()))
			.build(SpontaneousReplace.DATA, SprayPoisonSpiderData.ID);
	public static final EntityType<WeavingWebSpiderEntity> WEAVING_WEB_SPIDER = builder(FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.MONSTER).entityFactory(WeavingWebSpiderEntity::new)
			.spawnRestriction(SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnUtil.builder(WeavingWebSpiderEntity.class).monster().biome(biomeRegistryEntry -> biomeRegistryEntry.isIn(Tags.Biome.IS_SPIDER_BIOME)).build())
			.dimensions(EntityDimensions.fixed(WeavingWebSpiderData.BOX_WEIGHT, WeavingWebSpiderData.BOX_HEIGHT)).build())
			.otherRegister(weavingWebSpiderEntityEntityType -> FabricDefaultAttributeRegistry.register(weavingWebSpiderEntityEntityType, WeavingWebSpiderEntity.createSpiderAttributes()))
			.build(SpontaneousReplace.DATA, WeavingWebSpiderData.ID);
	
	
}
