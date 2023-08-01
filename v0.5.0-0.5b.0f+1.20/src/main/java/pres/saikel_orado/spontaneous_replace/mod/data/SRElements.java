package pres.saikel_orado.spontaneous_replace.mod.data;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import pres.saikel_orado.spontaneous_replace.mod.effect.SpiderCamouflage;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.basicweapon.Arbalest;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.basicweapon.RecurveBow;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.basicweapon.Slingshot;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.CompoundBow;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.JugerRepeatingCrossbow;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.MarksCrossbow;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.projectile.fullpowersteelarrow.FullPowerSteelArrowEntity;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.projectile.fullpowersteelarrow.FullPowerSteelArrowItem;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.projectile.stoneball.StoneballEntity;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.projectile.stoneball.StoneballItem;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.block.*;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.block.eerierind.EerieRind;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.block.eerierind.LavaEerieRind;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.block.eerierind.PowderSnowEerieRind;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.block.eerierind.WaterEerieRind;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.block.treacheroussac.TreacherousSac;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.block.treacheroussac.TreacherousSacEntity;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.block.*;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderData;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.guardspider.GuardSpiderData;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.guardspider.GuardSpiderEntity;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spiderlarva.SpiderLarvaData;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spiderlarva.SpiderLarvaEntity;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider.SprayPoisonSpiderData;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider.SprayPoisonSpiderEntity;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider.toxin.ToxinEntity;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.weavingwebspider.WeavingWebSpiderData;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.weavingwebspider.WeavingWebSpiderEntity;

import java.util.List;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.*;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRUtil.getSaturationRatio;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRUtil.getTick;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.aucualloy.AuCuArmor.AUCU_ARMOR;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.aucualloy.AuCuTool.AUCU_TOOL;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.cufealloy.CuFeArmor.CUFE_ARMOR;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.cufealloy.CuFeTool.CUFE_TOOL;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.armor.ArrowproofVest.ARROWPROOF_VEST_MATERIAL;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.basicweapon.Arbalest.ARBALEST_MAX_DAMAGE;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.basicweapon.RecurveBow.RECURVE_BOW_MAX_DAMAGE;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.basicweapon.Slingshot.SLINGSHOT_MAX_DAMAGE;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.CompoundBow.COMPOUND_BOW_MAX_DAMAGE;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.JugerRepeatingCrossbow.JUGER_REPEATING_CROSSBOW_MAX_DAMAGE;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.MarksCrossbow.MARKS_CROSSBOW_MAX_DAMAGE;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.refinedcopper.RefinedCopperArmor.COPPER_ARMOR;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.refinedcopper.RefinedCopperTool.COPPER_TOOL;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.steel.SteelArmor.STEEL_ARMOR;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.steel.SteelTool.STEEL_TOOL;
import static pres.saikel_orado.spontaneous_replace.mod.variant.general.block.TreacherousVines.TREACHEROUS_VINES_LUMINANCE;
import static pres.saikel_orado.spontaneous_replace.mod.variant.general.block.treacheroussac.TreacherousSac.TREACHEROUS_SAC_LUMINANCE;
import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderData.SPIDER_EYES_COLOR;
import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.equipment.SpiderLeatherArmor.SPIDER_LEATHER_ARMOR;
import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider.SprayPoisonSpiderData.*;

/**
 * <b style="color:FFC800"><font size="+2">SRElements：自然更替元素</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">集中定义自然更替中所有需要的元素组件。包括但不限于物品、实体、效果等</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/7/6 21:40
 */
public enum SRElements {
    ;

    public static final class Special {
        public static final Item SR_ICON = new Item(new FabricItemSettings());

        private Special() throws ExceptionInInitializerError {
            throw new ExceptionInInitializerError();
        }
    }

    public static final class Effects {
        /**
         * 蜘蛛伪装状态效果
         */
        public static final StatusEffect SPIDER_CAMOUFLAGE = new SpiderCamouflage();

        private Effects() throws ExceptionInInitializerError {
            throw new ExceptionInInitializerError();
        }
    }

    public static final class RecipeItems {
        /**
         * 用于合成的假剧毒之箭，其作用为占位以便被替换为真正的剧毒之箭，无法通过除命令外的其他方式获取
         */
        public static final Item POISON_ARROW = new Item(new FabricItemSettings());

        private RecipeItems() throws ExceptionInInitializerError {
            throw new ExceptionInInitializerError();
        }
    }

    public static final class Vanilla {
        /**
         * 合金锻造模具
         */
        public static final Item ALLOY_FORGING_DIE = new SmithingTemplateItem(Text.translatable("item." + SR_ID + ".alloy_forging_die." + "applies_to").setStyle(Style.EMPTY.withColor(Formatting.BLUE)), Text.translatable("item." + SR_ID + ".alloy_forging_die." + "ingredients").setStyle(Style.EMPTY.withColor(Formatting.BLUE)), Text.translatable("item." + SR_ID + ".alloy_forging_die." + "title").setStyle(Style.EMPTY.withColor(Formatting.GRAY)), Text.translatable("item." + SR_ID + ".alloy_forging_die." + "base_slot_description"), Text.translatable("item." + SR_ID + ".alloy_forging_die." + "additions_slot_description"), List.of(new Identifier("item/empty_slot_ingot")), List.of(new Identifier("item/empty_slot_ingot")));

        public static final class RefinedCopper {
            /**
             * 炼锭铜: 由四个铜锭在工作台合成
             */
            public static final Item COPPER_FOR_SMELTING_INGOT = new Item(new FabricItemSettings());
            /**
             * 精铜锭: 由炼锭铜在高炉烧炼 200 刻而成, 烧炼经验 0.8
             */
            public static final Item REFINED_COPPER_INGOT = new Item(new FabricItemSettings());
            /**
             * 精铜粒: 由精铜锭在工作台拆解或精铜装备烧炼而成, 烧炼经验 0.1
             */
            public static final Item REFINED_COPPER_NUGGET = new Item(new FabricItemSettings());
            /**
             * 精铜块方块：属性与铜块相同
             */
            public static final Block REFINED_COPPER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK));
            /**
             * 精铜块：由铜铁锭在工作台合成而成
             */
            public static final BlockItem REFINED_COPPER_BLOCK_ITEM = new BlockItem(REFINED_COPPER_BLOCK, new FabricItemSettings());
            /**
             * 精铜锹  3.5 | 1
             */
            public static final ToolItem REFINED_COPPER_SHOVEL = COPPER_TOOL.createShovel(3.5F, new FabricItemSettings());
            /**
             * 精铜镐  3 | 1.2
             */
            public static final ToolItem REFINED_COPPER_PICKAXE = COPPER_TOOL.createPickaxe(3, new FabricItemSettings());
            /**
             * 精铜斧  9 | 0.9
             */
            public static final ToolItem REFINED_COPPER_AXE = COPPER_TOOL.createAxe(9, 0.9F, new FabricItemSettings());
            /**
             * 精铜锄  1 | 2
             */
            public static final ToolItem REFINED_COPPER_HOE = COPPER_TOOL.createHoe(2, new FabricItemSettings());
            /**
             * 精铜剑  6 | 1.6
             */
            public static final ToolItem REFINED_COPPER_SWORD = COPPER_TOOL.createSword(6, new FabricItemSettings());
            /**
             * 装备精铜装甲音效 ID
             */
            public static final Identifier EQUIP_REFINED_COPPER_ID = new Identifier(SR_ID + ":vanilla_extension.refined_copper.equip");
            /**
             * 装备精铜装甲音效
             */
            public static final SoundEvent EQUIP_REFINED_COPPER = SoundEvent.of(EQUIP_REFINED_COPPER_ID);
            /**
             * 精铜头盔 132 | 2
             */
            public static final Item REFINED_COPPER_HELMET = COPPER_ARMOR.createHelmet(new FabricItemSettings());
            /**
             * 精铜胸甲 192 | 5
             */
            public static final Item REFINED_COPPER_CHESTPLATE = COPPER_ARMOR.createChestPlate(new FabricItemSettings());
            /**
             * 精铜护腿 180 | 4
             */
            public static final Item REFINED_COPPER_LEGGINGS = COPPER_ARMOR.createLeggings(new FabricItemSettings());
            /**
             * 精铜靴子 156 | 2
             */
            public static final Item REFINED_COPPER_BOOTS = COPPER_ARMOR.createBoots(new FabricItemSettings());

            private RefinedCopper() throws ExceptionInInitializerError {
                throw new ExceptionInInitializerError();
            }
        }

        public static final class CuFeAlloy {
            /**
             * 铜铁合金: 由精铜锭加上铁锭在锻造台打造而成或在工作台合成
             */
            public static final Item CUFE_ALLOY = new Item(new FabricItemSettings());
            /**
             * 铜铁锭: 由铜铁合金在在高炉烧炼 200 刻而成, 烧炼经验 0.9
             */
            public static final Item CUFE_ALLOY_INGOT = new Item(new FabricItemSettings());
            /**
             * 铜铁粒: 由铜铁锭在工作台拆解或铜铁装备烧炼而成, 烧炼经验 0.1
             */
            public static final Item CUFE_ALLOY_NUGGET = new Item(new FabricItemSettings());
            /**
             * 铜铁块方块：属性与铁块相同
             */
            public static final Block CUFE_ALLOY_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
            /**
             * 铜铁块：由铜铁锭在工作台合成而成
             */
            public static final BlockItem CUFE_ALLOY_BLOCK_ITEM = new BlockItem(CUFE_ALLOY_BLOCK, new FabricItemSettings());
            /**
             * 铜铁锹  4.5 | 1
             */
            public static final ToolItem CUFE_ALLOY_SHOVEL = CUFE_TOOL.createShovel(4.5F, new FabricItemSettings());
            /**
             * 铜铁镐  4 | 1.2
             */
            public static final ToolItem CUFE_ALLOY_PICKAXE = CUFE_TOOL.createPickaxe(4, new FabricItemSettings());
            /**
             * 铜铁斧  9 | 0.9
             */
            public static final ToolItem CUFE_ALLOY_AXE = CUFE_TOOL.createAxe(9, 0.9F, new FabricItemSettings());
            /**
             * 铜铁锄  1 | 3
             */
            public static final ToolItem CUFE_ALLOY_HOE = CUFE_TOOL.createHoe(3, new FabricItemSettings());
            /**
             * 铜铁剑  6 | 1.6
             */
            public static final ToolItem CUFE_ALLOY_SWORD = CUFE_TOOL.createSword(6, new FabricItemSettings());
            /**
             * 装备铜铁装甲音效 ID
             */
            public static final Identifier EQUIP_CUFE_ALLOY_ID = new Identifier(SR_ID + ":vanilla_extension.cufe_alloy.equip");
            /**
             * 装备铜铁装甲音效
             */
            public static final SoundEvent EQUIP_CUFE_ALLOY = SoundEvent.of(EQUIP_CUFE_ALLOY_ID);
            /**
             * 铜铁头盔 220 | 2
             */
            public static final Item CUFE_ALLOY_HELMET = CUFE_ARMOR.createHelmet(new FabricItemSettings());
            /**
             * 铜铁胸甲 320 | 6
             */
            public static final Item CUFE_ALLOY_CHESTPLATE = CUFE_ARMOR.createChestPlate(new FabricItemSettings());
            /**
             * 铜铁护腿 300 | 5
             */
            public static final Item CUFE_ALLOY_LEGGINGS = CUFE_ARMOR.createLeggings(new FabricItemSettings());
            /**
             * 铜铁靴子 260 | 2
             */
            public static final Item CUFE_ALLOY_BOOTS = CUFE_ARMOR.createBoots(new FabricItemSettings());

            private CuFeAlloy() throws ExceptionInInitializerError {
                throw new ExceptionInInitializerError();
            }
        }

        public static final class AuCuAlloy {
            /**
             * 金铜合金: 由精铜锭加上铁锭在锻造台打造而成或在工作台合成
             */
            public static final Item AUCU_ALLOY = new Item(new FabricItemSettings());
            /**
             * 金铜锭: 由金铜合金在在高炉烧炼 200 刻而成, 烧炼经验 0.9
             */
            public static final Item AUCU_ALLOY_INGOT = new Item(new FabricItemSettings());
            /**
             * 金铜粒: 由金铜锭在工作台拆解或金铜装备烧炼而成, 烧炼经验 0.1
             */
            public static final Item AUCU_ALLOY_NUGGET = new Item(new FabricItemSettings());
            /**
             * 金铜块方块：属性与金块相同
             */
            public static final Block AUCU_ALLOY_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK));
            /**
             * 金铜块：由金铜锭在工作台合成而成
             */
            public static final BlockItem AUCU_ALLOY_BLOCK_ITEM = new BlockItem(AUCU_ALLOY_BLOCK, new FabricItemSettings());
            /**
             * 金铜锹  3.5 | 1
             */
            public static final ToolItem AUCU_ALLOY_SHOVEL = AUCU_TOOL.createShovel(3.5F, new FabricItemSettings());
            /**
             * 金铜镐  2 | 1.2
             */
            public static final ToolItem AUCU_ALLOY_PICKAXE = AUCU_TOOL.createPickaxe(2, new FabricItemSettings());
            /**
             * 金铜斧  9 | 0.8
             */
            public static final ToolItem AUCU_ALLOY_AXE = AUCU_TOOL.createAxe(9, 0.8F, new FabricItemSettings());
            /**
             * 金铜锄  1 | 2
             */
            public static final ToolItem AUCU_ALLOY_HOE = AUCU_TOOL.createHoe(2, new FabricItemSettings());
            /**
             * 金铜剑  5 | 1.6
             */
            public static final ToolItem AUCU_ALLOY_SWORD = AUCU_TOOL.createSword(5, new FabricItemSettings());
            /**
             * 装备金铜装甲音效 ID
             */
            public static final Identifier EQUIP_AUCU_ALLOY_ID = new Identifier(SR_ID + ":vanilla_extension.aucu_alloy.equip");
            /**
             * 装备金铜装甲音效
             */
            public static final SoundEvent EQUIP_AUCU_ALLOY = SoundEvent.of(EQUIP_AUCU_ALLOY_ID);
            /**
             * 金铜头盔 110 | 2
             */
            public static final Item AUCU_ALLOY_HELMET = AUCU_ARMOR.createHelmet(new FabricItemSettings());
            /**
             * 金铜胸甲 160 | 5
             */
            public static final Item AUCU_ALLOY_CHESTPLATE = AUCU_ARMOR.createChestPlate(new FabricItemSettings());
            /**
             * 金铜护腿 150 | 4
             */
            public static final Item AUCU_ALLOY_LEGGINGS = AUCU_ARMOR.createLeggings(new FabricItemSettings());
            /**
             * 金铜靴子 130 | 1
             */
            public static final Item AUCU_ALLOY_BOOTS = AUCU_ARMOR.createBoots(new FabricItemSettings());

            private AuCuAlloy() throws ExceptionInInitializerError {
                throw new ExceptionInInitializerError();
            }
        }

        public static final class Steel {
            /**
             * 精煤: 由煤炭快加上水桶在锻造台打造而成或在工作台合成, 可以作为燃料, 烧炼时长是煤炭的两倍
             */
            public static final Item CLEAN_COAL = new Item(new FabricItemSettings());
            /**
             * 生铁: 由铁锭加上精煤在锻造台打造而成或在工作台合成
             */
            public static final Item PIG_IRON = new Item(new FabricItemSettings());
            /**
             * 钢锭: 由炼锭铜在高炉烧炼 200 刻而成, 烧炼经验 0.9
             */
            public static final Item STEEL_INGOT = new Item(new FabricItemSettings());
            /**
             * 钢块方块：属性与钻石块相同
             */
            public static final Block STEEL_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK).sounds(BlockSoundGroup.METAL));
            /**
             * 钢块：由金铜锭在工作台合成而成
             */
            public static final BlockItem STEEL_BLOCK_ITEM = new BlockItem(STEEL_BLOCK, new FabricItemSettings());
            /**
             * 钢锹  5.5 | 1
             */
            public static final ToolItem STEEL_SHOVEL = STEEL_TOOL.createShovel(5.5F, new FabricItemSettings());
            /**
             * 钢镐  5 | 1.2
             */
            public static final ToolItem STEEL_PICKAXE = STEEL_TOOL.createPickaxe(5, new FabricItemSettings());
            /**
             * 钢斧  9 | 0.9
             */
            public static final ToolItem STEEL_AXE = STEEL_TOOL.createAxe(9, 0.9F, new FabricItemSettings());
            /**
             * 钢锄  1 | 4
             */
            public static final ToolItem STEEL_HOE = STEEL_TOOL.createHoe(4, new FabricItemSettings());
            /**
             * 钢剑  7 | 1.6
             */
            public static final ToolItem STEEL_SWORD = STEEL_TOOL.createSword(7, new FabricItemSettings());
            /**
             * 装备钢制装甲音效 ID
             */
            public static final Identifier EQUIP_STEEL_ID = new Identifier(SR_ID + ":vanilla_extension.steel.equip");
            /**
             * 装备钢制装甲音效
             */
            public static final SoundEvent EQUIP_STEEL = SoundEvent.of(EQUIP_STEEL_ID);
            /**
             * 钢制头盔 308 | 3
             */
            public static final Item STEEL_HELMET = STEEL_ARMOR.createHelmet(new FabricItemSettings());
            /**
             * 钢制胸甲 448 | 7
             */
            public static final Item STEEL_CHESTPLATE = STEEL_ARMOR.createChestPlate(new FabricItemSettings());
            /**
             * 钢制护腿 420 | 6
             */
            public static final Item STEEL_LEGGINGS = STEEL_ARMOR.createLeggings(new FabricItemSettings());
            /**
             * 钢制靴子 364 | 2
             */
            public static final Item STEEL_BOOTS = STEEL_ARMOR.createBoots(new FabricItemSettings());

            private Steel() throws ExceptionInInitializerError {
                throw new ExceptionInInitializerError();
            }
        }

        public static final class RangedRelated {
            /**
             * 石弹：最大堆叠与雪球相同的可投掷物
             */
            public static final Item STONEBALL = new StoneballItem(new FabricItemSettings().maxCount(Items.SNOWBALL.getMaxCount()));
            /**
             * 石弹实体
             */
            public static final EntityType<StoneballEntity> STONEBALL_ENTITY = FabricEntityTypeBuilder.<StoneballEntity>create(SpawnGroup.MISC, StoneballEntity::new).dimensions(EntityDimensions.fixed(PROJECTILE_BOX, PROJECTILE_BOX)).trackRangeBlocks(PROJECTILE_RANGE).trackedUpdateRate(PROJECTILE_UPDATE_RATE).build();
            /**
             * 石弹投掷音效 ID
             */
            public static final Identifier STONEBALL_THROW_ID = new Identifier(SR_ID + ":vanilla_extension.stoneball.throw");
            /**
             * 石弹投掷音效
             */
            public static final SoundEvent STONEBALL_THROW = SoundEvent.of(STONEBALL_THROW_ID);
            /**
             * 全威力钢箭，最大堆积 4
             */
            public static final FullPowerSteelArrowItem FULL_POWER_STEEL_ARROW = new FullPowerSteelArrowItem(new FabricItemSettings().maxCount(4));
            /**
             * 全威力钢箭实体
             */
            public static final EntityType<FullPowerSteelArrowEntity> FULL_POWER_STEEL_ARROW_ENTITY = FabricEntityTypeBuilder.<FullPowerSteelArrowEntity>create(SpawnGroup.MISC, FullPowerSteelArrowEntity::new).dimensions(EntityDimensions.fixed(PROJECTILE_BOX * 1.25F, PROJECTILE_BOX * 1.25F)).trackRangeBlocks(PROJECTILE_RANGE * 10).trackedUpdateRate(PROJECTILE_UPDATE_RATE).build();
            /**
             * 丫弹弓投掷音效 ID
             */
            public static final Identifier SLINGSHOT_THROW_ID = new Identifier(SR_ID + ":vanilla_extension.slingshot.throw");
            /**
             * 丫弹弓投掷音效
             */
            public static final SoundEvent SLINGSHOT_THROW = SoundEvent.of(SLINGSHOT_THROW_ID);
            /**
             * <p>丫弹弓：</p>
             * <p>无攻击力</p>
             * <p>拉弓用时为 0.5 秒</p>
             * <p>拉弓时无缩放</p>
             * <p>最大基础出弹速度为 2</p>
             * <p>使用时不减速</p>
             * <p>耐久为弓的 50%</p>
             */
            public static final Item SLINGSHOT = new Slingshot(new FabricItemSettings().maxDamageIfAbsent(SLINGSHOT_MAX_DAMAGE));
            /**
             * <p>反曲弓：</p>
             * <p>攻击力为弓的 1.5 倍</p>
             * <p>拉弓用时为 0.75 秒</p>
             * <p>拉弓缩放为 0.85</p>
             * <p>最大出箭速度为 4</p>
             * <p>使用速度为正常速度的 50%</p>
             * <p>耐久为弓的 2 倍</p>
             */
            public static final RecurveBow RECURVE_BOW = new RecurveBow(new FabricItemSettings().maxDamageIfAbsent(RECURVE_BOW_MAX_DAMAGE));
            /**
             * 劲弩装填开始音效 ID
             */
            public static final Identifier ARBALEST_LOADING_START_ID = new Identifier(SR_ID + ":vanilla_extension.arbalest.loading.start");
            /**
             * 劲弩装填开始音效
             */
            public static final SoundEvent ARBALEST_LOADING_START = SoundEvent.of(ARBALEST_LOADING_START_ID);
            /**
             * 劲弩装填中音效 ID
             */
            public static final Identifier ARBALEST_LOADING_MIDDLE_ID = new Identifier(SR_ID + ":vanilla_extension.arbalest.loading.middle");
            /**
             * 劲弩装填中音效
             */
            public static final SoundEvent ARBALEST_LOADING_MIDDLE = SoundEvent.of(ARBALEST_LOADING_MIDDLE_ID);
            /**
             * 劲弩装填结束音效 ID
             */
            public static final Identifier ARBALEST_LOADING_END_ID = new Identifier(SR_ID + ":vanilla_extension.arbalest.loading.end");
            /**
             * 劲弩装填结束音效
             */
            public static final SoundEvent ARBALEST_LOADING_END = SoundEvent.of(ARBALEST_LOADING_END_ID);
            /**
             * 劲弩发射音效 ID
             */
            public static final Identifier ARBALEST_SHOOT_ID = new Identifier(SR_ID + ":vanilla_extension.arbalest.shoot");
            /**
             * 劲弩发射音效
             */
            public static final SoundEvent ARBALEST_SHOOT = SoundEvent.of(ARBALEST_SHOOT_ID);
            /**
             * 劲弩快速装填Ⅰ音效 ID
             */
            public static final Identifier ARBALEST_QUICK_CHARGE_1_ID = new Identifier(SR_ID + ":vanilla_extension.arbalest.quick_charge.1");
            /**
             * 劲弩快速装填Ⅰ音效
             */
            public static final SoundEvent ARBALEST_QUICK_CHARGE_1 = SoundEvent.of(ARBALEST_QUICK_CHARGE_1_ID);
            /**
             * 劲弩快速装填Ⅱ音效 ID
             */
            public static final Identifier ARBALEST_QUICK_CHARGE_2_ID = new Identifier(SR_ID + ":vanilla_extension.arbalest.quick_charge.2");
            /**
             * 劲弩快速装填Ⅱ音效
             */
            public static final SoundEvent ARBALEST_QUICK_CHARGE_2 = SoundEvent.of(ARBALEST_QUICK_CHARGE_2_ID);
            /**
             * 劲弩快速装填Ⅲ音效 ID
             */
            public static final Identifier ARBALEST_QUICK_CHARGE_3_ID = new Identifier(SR_ID + ":vanilla_extension.arbalest.quick_charge.3");
            /**
             * 劲弩快速装填Ⅲ音效
             */
            public static final SoundEvent ARBALEST_QUICK_CHARGE_3 = SoundEvent.of(ARBALEST_QUICK_CHARGE_3_ID);
            /**
             * <p>劲弩：</p>
             * <p>攻击力为弩的 1.5 倍</p>
             * <p>张弩用时为 1 秒</p>
             * <p>拉弓缩放为 0.85</p>
             * <p>最大出箭速度为 4.5</p>
             * <p>使用速度为默认速度</p>
             * <p>耐久为弩的 2 倍</p>
             */
            public static final Arbalest ARBALEST = new Arbalest(new FabricItemSettings().maxDamageIfAbsent(ARBALEST_MAX_DAMAGE));
            /**
             * <p>复合弓：</p>
             * <p>攻击力为弓的 2 倍</p>
             * <p>拉弓用时为 1.5 秒</p>
             * <p>拉弓缩放为 0.5</p>
             * <p>最大出箭速度为 6</p>
             * <p>使用速度为默认使用速度</p>
             * <p>耐久为弓的 2 倍</p>
             */
            public static final CompoundBow COMPOUND_BOW = new CompoundBow(new FabricItemSettings().maxDamageIfAbsent(COMPOUND_BOW_MAX_DAMAGE));
            /**
             * 诸葛连弩装填开始音效 ID
             */
            public static final Identifier JUGER_REPEATING_CROSSBOW_LOADING_START_ID = new Identifier(SR_ID + ":vanilla_extension.juger_repeating_crossbow.loading.start");
            /**
             * 诸葛连弩装填开始音效
             */
            public static final SoundEvent JUGER_REPEATING_CROSSBOW_LOADING_START = SoundEvent.of(JUGER_REPEATING_CROSSBOW_LOADING_START_ID);
            /**
             * 诸葛连弩装填结束音效 ID
             */
            public static final Identifier JUGER_REPEATING_CROSSBOW_LOADING_END_ID = new Identifier(SR_ID + ":vanilla_extension.juger_repeating_crossbow.loading.end");
            /**
             * 诸葛连弩装填结束音效
             */
            public static final SoundEvent JUGER_REPEATING_CROSSBOW_LOADING_END = SoundEvent.of(JUGER_REPEATING_CROSSBOW_LOADING_END_ID);
            /**
             * 诸葛连弩发射音效 ID
             */
            public static final Identifier JUGER_REPEATING_CROSSBOW_SHOOT_ID = new Identifier(SR_ID + ":vanilla_extension.juger_repeating_crossbow.shoot");
            /**
             * 诸葛连弩发射音效
             */
            public static final SoundEvent JUGER_REPEATING_CROSSBOW_SHOOT = SoundEvent.of(JUGER_REPEATING_CROSSBOW_SHOOT_ID);
            /**
             * <p>诸葛连弩：</p>
             * <p>攻击力为弩的 1.25 倍</p>
             * <p>装弹用时为 10 秒</p>
             * <p>最多装填 10 发弹药</p>
             * <p>拉弓缩放为 1</p>
             * <p>最大出箭速度为 3.5</p>
             * <p>使用速度为默认速度</p>
             * <p>耐久为弩的 3 倍</p>
             * <p>防火，无法烧毁</p>
             */
            public static final JugerRepeatingCrossbow JUGER_REPEATING_CROSSBOW = new JugerRepeatingCrossbow(new FabricItemSettings().maxDamageIfAbsent(JUGER_REPEATING_CROSSBOW_MAX_DAMAGE).fireproof());
            /**
             * 神臂弩装填开始音效 ID
             */
            public static final Identifier MARKS_CROSSBOW_LOADING_START_ID = new Identifier(SR_ID + ":vanilla_extension.marks_crossbow.loading.start");
            /**
             * 神臂弩装填开始音效
             */
            public static final SoundEvent MARKS_CROSSBOW_LOADING_START = SoundEvent.of(MARKS_CROSSBOW_LOADING_START_ID);
            /**
             * 神臂弩装填中音效 ID
             */
            public static final Identifier MARKS_CROSSBOW_LOADING_MIDDLE_ID = new Identifier(SR_ID + ":vanilla_extension.marks_crossbow.loading.middle");
            /**
             * 神臂弩装填中音效
             */
            public static final SoundEvent MARKS_CROSSBOW_LOADING_MIDDLE = SoundEvent.of(MARKS_CROSSBOW_LOADING_MIDDLE_ID);
            /**
             * 神臂弩装填结束音效 ID
             */
            public static final Identifier MARKS_CROSSBOW_LOADING_END_ID = new Identifier(SR_ID + ":vanilla_extension.marks_crossbow.loading.end");
            /**
             * 神臂弩装填结束音效
             */
            public static final SoundEvent MARKS_CROSSBOW_LOADING_END = SoundEvent.of(MARKS_CROSSBOW_LOADING_END_ID);
            /**
             * 神臂弩发射音效 ID
             */
            public static final Identifier MARKS_CROSSBOW_SHOOT_ID = new Identifier(SR_ID + ":vanilla_extension.marks_crossbow.shoot");
            /**
             * 神臂弩发射音效
             */
            public static final SoundEvent MARKS_CROSSBOW_SHOOT = SoundEvent.of(MARKS_CROSSBOW_SHOOT_ID);
            /**
             * 神臂弩快速装填Ⅰ音效 ID
             */
            public static final Identifier MARKS_CROSSBOW_QUICK_CHARGE_1_ID = new Identifier(SR_ID + ":vanilla_extension.marks_crossbow.quick_charge.1");
            /**
             * 神臂弩快速装填Ⅰ音效
             */
            public static final SoundEvent MARKS_CROSSBOW_QUICK_CHARGE_1 = SoundEvent.of(MARKS_CROSSBOW_QUICK_CHARGE_1_ID);
            /**
             * 神臂弩快速装填Ⅱ音效 ID
             */
            public static final Identifier MARKS_CROSSBOW_QUICK_CHARGE_2_ID = new Identifier(SR_ID + ":vanilla_extension.marks_crossbow.quick_charge.2");
            /**
             * 神臂弩快速装填Ⅱ音效
             */
            public static final SoundEvent MARKS_CROSSBOW_QUICK_CHARGE_2 = SoundEvent.of(MARKS_CROSSBOW_QUICK_CHARGE_2_ID);
            /**
             * 神臂弩快速装填Ⅲ音效 ID
             */
            public static final Identifier MARKS_CROSSBOW_QUICK_CHARGE_3_ID = new Identifier(SR_ID + ":vanilla_extension.marks_crossbow.quick_charge.3");
            /**
             * 神臂弩快速装填Ⅲ音效
             */
            public static final SoundEvent MARKS_CROSSBOW_QUICK_CHARGE_3 = SoundEvent.of(MARKS_CROSSBOW_QUICK_CHARGE_3_ID);
            /**
             * <p>神臂弩：</p>
             * <p>攻击力为弩的 5 倍</p>
             * <p>张弩用时为 10 秒</p>
             * <p>拉弓缩放为 0.1</p>
             * <p>最大出箭速度为 10</p>
             * <p>使用速度为 0</p>
             * <p>耐久为弩的 3 倍</p>
             * <p>防火，无法烧毁</p>
             */
            public static final MarksCrossbow MARKS_CROSSBOW = new MarksCrossbow(new FabricItemSettings().maxDamageIfAbsent(MARKS_CROSSBOW_MAX_DAMAGE).fireproof());
            /**
             * 装备防箭衣音效 ID
             */
            public static final Identifier EQUIP_ARROWPROOF_VEST_ID = new Identifier(SR_ID + ":vanilla_extension.arrowproof_vest.equip");
            /**
             * 装备防箭衣音效
             */
            public static final SoundEvent EQUIP_ARROWPROOF_VEST = SoundEvent.of(EQUIP_ARROWPROOF_VEST_ID);
            /**
             * 防箭衣 160 | 4
             */
            public static final Item ARROWPROOF_VEST = ARROWPROOF_VEST_MATERIAL.createChestPlate(new FabricItemSettings());

            private RangedRelated() throws ExceptionInInitializerError {
                throw new ExceptionInInitializerError();
            }
        }

        private Vanilla() throws ExceptionInInitializerError {
            throw new ExceptionInInitializerError();
        }
    }

    public static final class Variant {
        /**
         * <p>方块标签：阴森木壳</p>
         * <p>在此标签中的方块会被识别为阴森木壳的一种</p>
         */
        public static final TagKey<Block> EERIE_RINDS = TagKey.of(RegistryKeys.BLOCK, new Identifier(SR_ID, "eerie_rinds"));
        /**
         * 阴森浮土方块
         */
        public static final EerieRegolith EERIE_REGOLITH = new EerieRegolith(FabricBlockSettings.copyOf(Blocks.PODZOL).mapColor(MapColor.BLACK));
        /**
         * 阴森浮土物品
         */
        public static final BlockItem EERIE_REGOLITH_ITEM = new BlockItem(EERIE_REGOLITH, new FabricItemSettings());
        /**
         * 阴森木壳(含水)方块
         */
        public static final WaterEerieRind WATER_EERIE_RIND = new WaterEerieRind(FabricBlockSettings.copyOf(Blocks.WATER_CAULDRON).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.WATER_BLUE));
        /**
         * 阴森木壳(含雪)方块
         */
        public static final PowderSnowEerieRind POWDER_SNOW_EERIE_RIND = new PowderSnowEerieRind(FabricBlockSettings.copyOf(Blocks.POWDER_SNOW_CAULDRON).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.WHITE));
        /**
         * 阴森木壳(结冰)方块
         */
        public static final Block ICE_EERIE_RIND = new Block(FabricBlockSettings.copyOf(Blocks.CAULDRON).slipperiness(Blocks.ICE.getSlipperiness()).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.LIGHT_BLUE)) {
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
        };
        /**
         * 阴森木壳(含岩浆)方块
         */
        public static final LavaEerieRind LAVA_EERIE_RIND = new LavaEerieRind(FabricBlockSettings.copyOf(Blocks.LAVA_CAULDRON).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.BRIGHT_RED));
        /**
         * 阴森木壳方块
         */
        public static final EerieRind EERIE_RIND = new EerieRind(FabricBlockSettings.copyOf(Blocks.CAULDRON).nonOpaque().sounds(BlockSoundGroup.COPPER).mapColor(MapColor.BLACK));
        /**
         * 阴森木壳物品: 防火
         */
        public static final BlockItem EERIE_RIND_ITEM = new BlockItem(EERIE_RIND, new FabricItemSettings().fireproof());
        /**
         * 阴森木枝方块
         */
        public static final EerieBough EERIE_BOUGH = new EerieBough(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.LIGHT_GRAY));
        /**
         * 阴森木枝物品: 防火
         */
        public static final BlockItem EERIE_BOUGH_ITEM = new BlockItem(EERIE_BOUGH, new FabricItemSettings().fireproof());
        /**
         * 诡谲污泥方块
         */
        public static final TreacherousSludge TREACHEROUS_SLUDGE = new TreacherousSludge(FabricBlockSettings.copyOf(Blocks.MUD).mapColor(MapColor.TERRACOTTA_ORANGE));
        /**
         * 诡谲污泥物品
         */
        public static final BlockItem TREACHEROUS_SLUDGE_ITEM = new BlockItem(TREACHEROUS_SLUDGE, new FabricItemSettings());
        /**
         * 诡谲囊破裂音效 ID
         */
        public static final Identifier TREACHEROUS_SAC_BREAK_ID = new Identifier(SR_ID + ":variant.treacherous_sac.break");
        /**
         * 诡谲囊破裂音效
         */
        public static final SoundEvent TREACHEROUS_SAC_BREAK = SoundEvent.of(TREACHEROUS_SAC_BREAK_ID);
        /**
         * 诡谲囊方块
         */
        public static final TreacherousSac TREACHEROUS_SAC = new TreacherousSac(FabricBlockSettings.copyOf(Blocks.DARK_OAK_LOG).strength(Blocks.DARK_OAK_LOG.getHardness() / 2, 0).luminance(TREACHEROUS_SAC_LUMINANCE).mapColor(MapColor.BROWN));
        /**
         * 诡谲囊实体
         */
        public static final EntityType<TreacherousSacEntity> TREACHEROUS_SAC_ENTITY = FabricEntityTypeBuilder.create(SpawnGroup.MISC, TreacherousSacEntity::new).dimensions(EntityDimensions.fixed(1, 1)).build();
        /**
         * 诡谲囊物品
         */
        public static final BlockItem TREACHEROUS_SAC_ITEM = new BlockItem(TREACHEROUS_SAC, new FabricItemSettings());
        /**
         * 诡谲藤植株方块
         */
        public static final TreacherousVinesPlant TREACHEROUS_VINES_PLANT = new TreacherousVinesPlant(FabricBlockSettings.copyOf(Blocks.WEEPING_VINES_PLANT).luminance(TREACHEROUS_VINES_LUMINANCE).mapColor(MapColor.ORANGE));
        /**
         * 诡谲藤方块
         */
        public static final TreacherousVines TREACHEROUS_VINES = new TreacherousVines(FabricBlockSettings.copyOf(Blocks.WEEPING_VINES).luminance(TREACHEROUS_VINES_LUMINANCE).mapColor(MapColor.ORANGE));
        /**
         * 诡谲藤物品
         */
        public static final BlockItem TREACHEROUS_VINES_ITEM = new BlockItem(TREACHEROUS_VINES, new FabricItemSettings());

        @Environment(EnvType.CLIENT)
        public static final class Client {
            public static final EntityModelLayer TREACHEROUS_SAC_LAYER = new EntityModelLayer(new Identifier(SR_ID, "treacherous_sac"), "main");

            private Client() throws ExceptionInInitializerError {
                throw new ExceptionInInitializerError();
            }
        }

        public static final class Spider {
            /**
             * <p>方块标签：蛛网</p>
             * <p>在此标签中的方块使用剪刀或剑会加速破坏</p>
             */
            public static final TagKey<Block> COBWEBS = TagKey.of(RegistryKeys.BLOCK, new Identifier(SR_ID, "cobwebs"));
            /**
             * <p>生物群系标签：是蜘蛛生物群系</p>
             * <p>在此标签中的生物群系生成自然更替蜘蛛变种采用特殊生成方式</p>
             */
            public static final TagKey<Biome> IS_SPIDER_BIOME = TagKey.of(RegistryKeys.BIOME, new Identifier(SR_ID, "is_spider_biome"));
            /**
             * <p>蜘蛛腿食物参数：</p>
             * <p>饱食度：4</p>
             * <p>100% 中毒 Ⅱ 10s</p>
             * <p>是肉</p>
             */
            public static final FoodComponent SPIDER_LEG_FC = (new FoodComponent.Builder()).hunger(4).statusEffect(new StatusEffectInstance(StatusEffects.POISON, getTick(10), 1), 1).meat().build();
            /**
             * 自然更替特殊蜘蛛变种特有掉落物：蜘蛛腿
             */
            public static final Item SPIDER_LEG = new Item(new FabricItemSettings().food(SPIDER_LEG_FC));
            /**
             * 蜘蛛卫兵特有掉落物：蜘蛛护皮
             */
            public static final Item SPIDER_LEATHER = new Item(new FabricItemSettings());
            /**
             * 喷吐毒蛛特有掉落物：蜘蛛毒牙
             */
            public static final Item SPIDER_FANG = new Item(new FabricItemSettings());
            /**
             * 织网蜘蛛特有掉落物：致密蛛丝
             */
            public static final Item COMPACT_GOSSAMER = new Item(new FabricItemSettings());
            /**
             * 黏密蛛网掉落物：黏密蛛丝
             */
            public static final Item STICKY_COMPACT_GOSSAMER = new Item(new FabricItemSettings());
            /**
             * <p>去毒蜘蛛腿食物参数：</p>
             * <p>饱食度：5</p>
             * <p>饱和度：3</p>
             * <p>25% 中毒 Ⅰ 5s</p>
             * <p>是肉</p>
             */
            public static final FoodComponent DEPOISON_SPIDER_LEG_FC = (new FoodComponent.Builder()).hunger(5).saturationModifier(getSaturationRatio(3)).statusEffect(new StatusEffectInstance(StatusEffects.POISON, getTick(5), 0), 0.25F).meat().build();
            /**
             * 去毒蜘蛛腿：由蜘蛛腿烹饪而成
             */
            public static final Item DEPOISON_SPIDER_LEG = new Item(new FabricItemSettings().food(DEPOISON_SPIDER_LEG_FC));
            /**
             * 致密丝线：由致密蛛丝合成而来
             */
            public static final Item COMPACT_STRING = new Item(new FabricItemSettings());
            /**
             * 复合丝线：由致密丝线与黏密蛛丝合成而来
             */
            public static final Item COMPOSITE_STRING = new Item(new FabricItemSettings());
            /**
             * 复合面料：由复合丝线与白色地毯合成而来
             */
            public static final Item COMPOSITE_FABRIC = new Item(new FabricItemSettings());
            /**
             * 黏密蛛网方块
             * <p>材料：黏密蛛网；无碰撞体积；需要工具采集；强度为黏密蛛网强度</p>
             */
            public static final Block STICKY_COMPACT_COBWEB = new StickyCompactCobweb(FabricBlockSettings.copyOf(SRSpiderData.STICKY_COMPACT_COBWEB).noCollision().requiresTool().strength(SRSpiderData.STICKY_COMPACT_COBWEB_STRENGTH));
            /**
             * 黏密蛛网物品
             */
            public static final BlockItem STICKY_COMPACT_COBWEB_ITEM = new BlockItem(STICKY_COMPACT_COBWEB, new FabricItemSettings());
            /**
             * 蜘蛛茧蛹方块
             * <p>材料：蛛网；需要工具采集；非不透明方块；强度为蜘蛛网强度</p>
             */
            public static final SpiderChrysalis SPIDER_CHRYSALIS = new SpiderChrysalis(FabricBlockSettings.copyOf(SRSpiderData.COBWEB).requiresTool().nonOpaque().strength(SRSpiderData.COBWEB_STRENGTH));
            /**
             * 蜘蛛茧蛹物品
             */
            public static final BlockItem SPIDER_CHRYSALIS_ITEM = new BlockItem(SPIDER_CHRYSALIS, new FabricItemSettings());
            /**
             * 蜘蛛卵茧方块
             * <p>材料：蛛网；需要工具采集；非不透明方块；强度为蜘蛛网</p>
             */
            public static final SpiderEggCocoon SPIDER_EGG_COCOON = new SpiderEggCocoon(FabricBlockSettings.copyOf(SRSpiderData.COBWEB).requiresTool().nonOpaque().strength(SRSpiderData.COBWEB_STRENGTH));
            /**
             * 蜘蛛卵茧实体
             */
            public static final BlockEntityType<SpiderEggCocoonEntity> SPIDER_EGG_COCOON_ENTITY = FabricBlockEntityTypeBuilder.create(SpiderEggCocoonEntity::new, SPIDER_EGG_COCOON).build();
            /**
             * 蜘蛛卵茧物品
             */
            public static final BlockItem SPIDER_EGG_COCOON_ITEM = new BlockItem(SPIDER_EGG_COCOON, new FabricItemSettings());
            /**
             * 丝化土方块
             */
            public static final CobwebbySoil COBWEBBY_SOIL = new CobwebbySoil(FabricBlockSettings.copyOf(Blocks.MYCELIUM).mapColor(MapColor.WHITE));
            /**
             * 丝化土物品
             */
            public static final BlockItem COBWEBBY_SOIL_ITEM = new BlockItem(COBWEBBY_SOIL, new FabricItemSettings());
            /**
             * 覆地蛛丝方块
             */
            public static final GossamerCarpet GOSSAMER_CARPET = new GossamerCarpet(FabricBlockSettings.copyOf(Blocks.MOSS_CARPET).noCollision().nonOpaque().mapColor(MapColor.WHITE_GRAY));
            /**
             * 覆地蛛丝物品
             */
            public static final BlockItem GOSSAMER_CARPET_ITEM = new BlockItem(GOSSAMER_CARPET, new FabricItemSettings());
            /**
             * 覆丝树叶方块
             */
            public static final GossameryLeaves GOSSAMERY_LEAVES = new GossameryLeaves(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).mapColor(MapColor.WHITE_GRAY));
            /**
             * 覆丝树叶物品
             */
            public static final BlockItem GOSSAMERY_LEAVES_ITEM = new BlockItem(GOSSAMERY_LEAVES, new FabricItemSettings());
            /**
             * 装备蜘蛛皮甲音效 ID
             */
            public static final Identifier EQUIP_SPIDER_LEATHER_ARMOR_ID = new Identifier(SR_ID + ":spider_biome.spider_leather_armor.equip");
            /**
             * 装备蜘蛛皮甲音效
             */
            public static final SoundEvent EQUIP_SPIDER_LEATHER_ARMOR = SoundEvent.of(EQUIP_SPIDER_LEATHER_ARMOR_ID);
            /**
             * 蜘蛛皮帽 66 | 2
             */
            public static final Item SPIDER_LEATHER_CAP = SPIDER_LEATHER_ARMOR.createHelmet(new FabricItemSettings());
            /**
             * 蜘蛛皮甲 96 | 4
             */
            public static final Item SPIDER_LEATHER_TUNIC = SPIDER_LEATHER_ARMOR.createChestPlate(new FabricItemSettings());
            /**
             * 幼蛛实体
             */
            public static final EntityType<SpiderLarvaEntity> SPIDER_LARVA = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SpiderLarvaEntity::new).dimensions(EntityDimensions.fixed(SpiderLarvaData.BOX_WEIGHT, SpiderLarvaData.BOX_HEIGHT)).build();
            /**
             * 幼蛛刷怪蛋
             */
            public static final Item SPIDER_LARVA_SPAWN_EGG = new SpawnEggItem(SPIDER_LARVA, SpiderLarvaData.SPIDER_SKIN_COLOR, SRSpiderData.SPIDER_EYES_COLOR, new FabricItemSettings());
            /**
             * 蜘蛛卫兵实体
             */
            public static final EntityType<GuardSpiderEntity> GUARD_SPIDER = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GuardSpiderEntity::new).dimensions(EntityDimensions.fixed(GuardSpiderData.BOX_WEIGHT, GuardSpiderData.BOX_HEIGHT)).build();
            /**
             * 蜘蛛卫兵刷怪蛋
             */
            public static final Item GUARD_SPIDER_SPAWN_EGG = new SpawnEggItem(GUARD_SPIDER, GuardSpiderData.SPIDER_SKIN_COLOR, SPIDER_EYES_COLOR, new FabricItemSettings());
            /**
             * 喷吐毒蛛实体
             */
            public static final EntityType<SprayPoisonSpiderEntity> SPRAY_POISON_SPIDER = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SprayPoisonSpiderEntity::new).dimensions(EntityDimensions.fixed(BOX_WEIGHT, BOX_HEIGHT)).build();
            /**
             * 喷吐毒蛛刷怪蛋
             */
            public static final Item SPRAY_POISON_SPIDER_SPAWN_EGG = new SpawnEggItem(SPRAY_POISON_SPIDER, SPIDER_SKIN_COLOR, SRSpiderData.SPIDER_EYES_COLOR, new FabricItemSettings());
            /**
             * 毒素实体
             */
            public static final EntityType<ToxinEntity> TOXIN = FabricEntityTypeBuilder.<ToxinEntity>create(SpawnGroup.MISC, ToxinEntity::new).dimensions(EntityDimensions.fixed(PROJECTILE_BOX, PROJECTILE_BOX)).trackRangeBlocks(PROJECTILE_RANGE).trackedUpdateRate(PROJECTILE_UPDATE_RATE).build();
            /**
             * 毒素粒子
             */
            public static final DefaultParticleType TOXIN_PARTICLE = FabricParticleTypes.simple();
            /**
             * 喷吐毒素音效字幕 ID
             */
            public static final Identifier SPRAY_TOXIN_ID = new Identifier("spontaneous_replace:spider_biome.spray_poison_spider.spray_toxin");
            /**
             * 喷吐毒素音效
             */
            public static final SoundEvent SPRAY_TOXIN = SoundEvent.of(SPRAY_TOXIN_ID);
            /**
             * 织网蜘蛛实体
             */
            public static final EntityType<WeavingWebSpiderEntity> WEAVING_WEB_SPIDER = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, WeavingWebSpiderEntity::new).dimensions(EntityDimensions.fixed(WeavingWebSpiderData.BOX_WEIGHT, WeavingWebSpiderData.BOX_HEIGHT)).build();
            /**
             * 织网蜘蛛刷怪蛋
             */
            public static final Item WEAVING_WEB_SPIDER_SPAWN_EGG = new SpawnEggItem(WEAVING_WEB_SPIDER, WeavingWebSpiderData.SPIDER_SKIN_COLOR, SRSpiderData.SPIDER_EYES_COLOR, new FabricItemSettings());

            @Environment(EnvType.CLIENT)
            public static final class Client {
                public static final EntityModelLayer SPIDER_LARVA_LAYER = new EntityModelLayer(new Identifier(SR_ID, SpiderLarvaData.ID), "main");
                public static final EntityModelLayer GUARD_SPIDER_LAYER = new EntityModelLayer(new Identifier(SR_ID, GuardSpiderData.ID), "main");
                public static final EntityModelLayer WEAVING_WEB_SPIDER_LAYER = new EntityModelLayer(new Identifier(SR_ID, WeavingWebSpiderData.ID), "main");
                /**
                 * 喷吐毒蛛
                 */
                public static final EntityModelLayer SPRAY_POISON_SPIDER_LAYER = new EntityModelLayer(new Identifier(SR_ID, SprayPoisonSpiderData.ID), "main");
                /**
                 * 毒素
                 */
                public static final EntityModelLayer TOXIN_LAYER = new EntityModelLayer(new Identifier(SR_ID, "spider_toxin_projectile"), "main");

                private Client() throws ExceptionInInitializerError {
                    throw new ExceptionInInitializerError();
                }
            }

            private Spider() throws ExceptionInInitializerError {
                throw new ExceptionInInitializerError();
            }
        }

        private Variant() throws ExceptionInInitializerError {
            throw new ExceptionInInitializerError();
        }
    }
}