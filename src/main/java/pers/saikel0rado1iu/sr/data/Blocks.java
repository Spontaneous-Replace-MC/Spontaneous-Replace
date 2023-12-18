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

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import pers.saikel0rado1iu.silk.api.registry.SilkBlock;
import pers.saikel0rado1iu.sr.variant.general.block.eerie.*;
import pers.saikel0rado1iu.sr.variant.general.block.treacherous.TreacherousSac;
import pers.saikel0rado1iu.sr.variant.general.block.treacherous.TreacherousSludge;
import pers.saikel0rado1iu.sr.variant.general.block.treacherous.TreacherousVines;
import pers.saikel0rado1iu.sr.variant.general.block.treacherous.TreacherousVinesPlant;
import pers.saikel0rado1iu.sr.variant.spider.block.*;
import pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderData;

import static pers.saikel0rado1iu.silk.api.registry.SilkEntityType.POS_SHIFTING;
import static pers.saikel0rado1iu.sr.variant.general.block.treacherous.TreacherousSac.TREACHEROUS_SAC_LUMINANCE;
import static pers.saikel0rado1iu.sr.variant.general.block.treacherous.TreacherousVines.TREACHEROUS_VINES_LUMINANCE;
import static pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderData.STICKY_COMPACT_COBWEB_BURN_CHANCE;
import static pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderData.STICKY_COMPACT_COBWEB_SPREAD_CHANCE;

/**
 * <h2 style="color:FFC800">自然更替的所有的方块</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public final class Blocks extends SilkBlock {
	public static final Block COPPER_FOR_SMELTING_INGOT_BLOCK = builder(new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.RAW_COPPER_BLOCK)))
			.build(SpontaneousReplace.DATA, "copper_for_smelting_ingot_block");
	public static final Block REFINED_COPPER_BLOCK = builder(new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.COPPER_BLOCK)))
			.build(SpontaneousReplace.DATA, "refined_copper_block");
	public static final Block CUFE_ALLOY_BLOCK = builder(new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.RAW_IRON_BLOCK)))
			.build(SpontaneousReplace.DATA, "cufe_alloy_block");
	public static final Block CUFE_BLOCK = builder(new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.IRON_BLOCK)))
			.build(SpontaneousReplace.DATA, "cufe_block");
	public static final Block AUCU_ALLOY_BLOCK = builder(new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.RAW_GOLD_BLOCK)))
			.build(SpontaneousReplace.DATA, "aucu_alloy_block");
	public static final Block AUCU_BLOCK = builder(new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.GOLD_BLOCK)))
			.build(SpontaneousReplace.DATA, "aucu_block");
	public static final Block PIG_IRON_BLOCK = builder(new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.IRON_BLOCK)))
			.build(SpontaneousReplace.DATA, "pig_iron_block");
	public static final Block STEEL_BLOCK = builder(new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.DIAMOND_BLOCK).sounds(BlockSoundGroup.METAL)))
			.build(SpontaneousReplace.DATA, "steel_block");
	public static final EerieRegolith EERIE_REGOLITH = builder(new EerieRegolith(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.PODZOL).mapColor(MapColor.BLACK)))
			.build(SpontaneousReplace.DATA, "eerie_regolith");
	public static final TreacherousSludge TREACHEROUS_SLUDGE = builder(new TreacherousSludge(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.MUD).mapColor(MapColor.TERRACOTTA_ORANGE)))
			.build(SpontaneousReplace.DATA, "treacherous_sludge");
	public static final CobwebbySoil COBWEBBY_SOIL = builder(new CobwebbySoil(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.MYCELIUM).mapColor(MapColor.WHITE)))
			.build(SpontaneousReplace.DATA, "cobwebby_soil");
	public static final EerieRind EERIE_RIND = builder(new EerieRind(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.CAULDRON).nonOpaque().sounds(BlockSoundGroup.COPPER).mapColor(MapColor.BLACK)))
			.build(SpontaneousReplace.DATA, "eerie_rind");
	public static final Block ICE_EERIE_RIND = builder(new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.CAULDRON).slipperiness(net.minecraft.block.Blocks.ICE.getSlipperiness()).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.LIGHT_BLUE)) {
		/**
		 * 随机灵魂粒子效果
		 */
		@Override
		public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
			super.randomDisplayTick(state, world, pos, random);
			if (random.nextInt(3) == 0) {
				java.util.Random randomValue = new java.util.Random();
				world.addParticle(ParticleTypes.SCULK_SOUL,
						pos.getX() + POS_SHIFTING + randomValue.nextDouble(-1, 1),
						pos.getY() + POS_SHIFTING + randomValue.nextDouble(-1, 1),
						pos.getZ() + POS_SHIFTING + randomValue.nextDouble(-1, 1),
						randomValue.nextDouble(-0.07, 0.07),
						randomValue.nextDouble(0, 0.1),
						randomValue.nextDouble(-0.07, 0.07));
			}
		}
	})
			.build(SpontaneousReplace.DATA, "ice_eerie_rind");
	public static final LavaEerieRind LAVA_EERIE_RIND = builder(new LavaEerieRind(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.LAVA_CAULDRON).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.BRIGHT_RED)))
			.build(SpontaneousReplace.DATA, "lava_eerie_rind");
	public static final WaterEerieRind WATER_EERIE_RIND = builder(new WaterEerieRind(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.WATER_CAULDRON).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.WATER_BLUE)))
			.build(SpontaneousReplace.DATA, "water_eerie_rind");
	public static final PowderSnowEerieRind POWDER_SNOW_EERIE_RIND = builder(new PowderSnowEerieRind(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.POWDER_SNOW_CAULDRON).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.WHITE)))
			.build(SpontaneousReplace.DATA, "powder_snow_eerie_rind");
	public static final EerieBough EERIE_BOUGH = builder(new EerieBough(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.DIAMOND_BLOCK).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.LIGHT_GRAY)))
			.build(SpontaneousReplace.DATA, "eerie_bough");
	public static final TreacherousSac TREACHEROUS_SAC = builder(new TreacherousSac(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.DARK_OAK_LOG).strength(net.minecraft.block.Blocks.DARK_OAK_LOG.getHardness() / 2, 0).luminance(TREACHEROUS_SAC_LUMINANCE).mapColor(MapColor.BROWN)))
			.build(SpontaneousReplace.DATA, "treacherous_sac");
	public static final TreacherousVinesPlant TREACHEROUS_VINES_PLANT = builder(new TreacherousVinesPlant(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.WEEPING_VINES_PLANT).luminance(TREACHEROUS_VINES_LUMINANCE).mapColor(MapColor.ORANGE)))
			.build(SpontaneousReplace.DATA, "treacherous_vines_plant");
	public static final TreacherousVines TREACHEROUS_VINES = builder(new TreacherousVines(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.WEEPING_VINES).luminance(TREACHEROUS_VINES_LUMINANCE).mapColor(MapColor.ORANGE)))
			.build(SpontaneousReplace.DATA, "treacherous_vines");
	public static final GossamerCarpet GOSSAMER_CARPET = builder(new GossamerCarpet(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.MOSS_CARPET).noCollision().nonOpaque().mapColor(MapColor.WHITE_GRAY)))
			.build(SpontaneousReplace.DATA, "gossamer_carpet");
	public static final GossameryLeaves GOSSAMERY_LEAVES = builder(new GossameryLeaves(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.OAK_LEAVES).mapColor(MapColor.WHITE_GRAY)))
			.build(SpontaneousReplace.DATA, "gossamery_leaves");
	public static final StickyCompactCobweb STICKY_COMPACT_COBWEB = builder(new StickyCompactCobweb(FabricBlockSettings.copyOf(SpiderData.STICKY_COMPACT_COBWEB).noCollision().requiresTool().strength(SpiderData.STICKY_COMPACT_COBWEB_STRENGTH)))
			.otherRegister(stickyCompactCobweb -> FlammableBlockRegistry.getDefaultInstance().add(stickyCompactCobweb, STICKY_COMPACT_COBWEB_BURN_CHANCE, STICKY_COMPACT_COBWEB_SPREAD_CHANCE))
			.build(SpontaneousReplace.DATA, "sticky_compact_cobweb");
	public static final SpiderChrysalis SPIDER_CHRYSALIS = builder(new SpiderChrysalis(FabricBlockSettings.copyOf(SpiderData.COBWEB).requiresTool().nonOpaque().strength(SpiderData.COBWEB_STRENGTH)))
			.build(SpontaneousReplace.DATA, "spider_chrysalis");
	public static final SpiderEggCocoon SPIDER_EGG_COCOON = builder(new SpiderEggCocoon(FabricBlockSettings.copyOf(SpiderData.COBWEB).requiresTool().nonOpaque().strength(SpiderData.COBWEB_STRENGTH)))
			.build(SpontaneousReplace.DATA, "spider_egg_cocoon");
}
