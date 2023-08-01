package pres.saikel_orado.spontaneous_replace.mod;

import com.google.common.collect.Sets;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.particle.SpitParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import pres.saikel_orado.spontaneous_replace.mod.data.SRItemGroups;
import pres.saikel_orado.spontaneous_replace.mod.util.SRBow;
import pres.saikel_orado.spontaneous_replace.mod.util.SRCrossbow;
import pres.saikel_orado.spontaneous_replace.mod.util.callback.InfectSaplingCallback;
import pres.saikel_orado.spontaneous_replace.mod.util.callback.RegistryFlammableCallback;
import pres.saikel_orado.spontaneous_replace.mod.util.callback.SimpleBlockFeatureCreateCallback;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.basicweapon.Arbalest;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.basicweapon.RecurveBow;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.basicweapon.Slingshot;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.CompoundBow;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.JugerRepeatingCrossbow;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.MarksCrossbow;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.projectile.fullpowersteelarrow.FullPowerSteelArrowEntity;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.projectile.fullpowersteelarrow.FullPowerSteelArrowRender;
import pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.projectile.stoneball.StoneballEntity;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.block.eerierind.EerieRindBehavior;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.block.treacheroussac.TreacherousSacEntity;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.block.treacheroussac.TreacherousSacModel;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.block.treacheroussac.TreacherousSacRenderer;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.tree.eerie_tree.EerieFoliagePlacer;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.tree.eerie_tree.EerieTreeDecorator;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.tree.treacherous_tree.TreacherousFoliagePlacer;
import pres.saikel_orado.spontaneous_replace.mod.variant.general.tree.treacherous_tree.TreacherousTrunkPlacer;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderData;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.guardspider.GuardSpiderData;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.guardspider.GuardSpiderEntity;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.guardspider.GuardSpiderModel;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.guardspider.GuardSpiderRenderer;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spiderlarva.SpiderLarvaData;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spiderlarva.SpiderLarvaEntity;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spiderlarva.SpiderLarvaModel;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spiderlarva.SpiderLarvaRenderer;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider.SprayPoisonSpiderData;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider.SprayPoisonSpiderEntity;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider.SprayPoisonSpiderModel;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider.SprayPoisonSpiderRenderer;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider.toxin.ToxinModel;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.spraypoisonspider.toxin.ToxinRenderer;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.weavingwebspider.WeavingWebSpiderData;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.weavingwebspider.WeavingWebSpiderEntity;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.weavingwebspider.WeavingWebSpiderModel;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.mob.weavingwebspider.WeavingWebSpiderRenderer;

import java.util.Collections;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.SR_ID;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Effects.SPIDER_CAMOUFLAGE;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.RecipeItems.POISON_ARROW;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Special.SR_ICON;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.ALLOY_FORGING_DIE;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.AuCuAlloy.*;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.CuFeAlloy.*;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RangedRelated.*;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RefinedCopper.*;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.Steel.*;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Client.TREACHEROUS_SAC_LAYER;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.*;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.*;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.STICKY_COMPACT_COBWEB;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.Client.*;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRCrossbow.isCharged;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRUtil.getEgg;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRUtil.getTick;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.JugerRepeatingCrossbow.getIsShoot;
import static pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.enhancedweapon.MarksCrossbow.isShootEnd;
import static pres.saikel_orado.spontaneous_replace.mod.variant.general.tree.eerie_tree.EerieFoliagePlacer.EERIE_BOUGH_FOLIAGE_PLACER;
import static pres.saikel_orado.spontaneous_replace.mod.variant.general.tree.eerie_tree.EerieTreeDecorator.EERIE_ROOT_TREE_DECORATOR;
import static pres.saikel_orado.spontaneous_replace.mod.variant.general.tree.treacherous_tree.TreacherousFoliagePlacer.TREACHEROUS_FOLIAGE_PLACER;
import static pres.saikel_orado.spontaneous_replace.mod.variant.general.tree.treacherous_tree.TreacherousTrunkPlacer.TREACHEROUS_TRUNK_PLACER;
import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.block.SpiderChrysalis.getRandomStyle;
import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.block.SpiderChrysalis.isDoubleBlock;
import static pres.saikel_orado.spontaneous_replace.mod.variant.spider.data.SRSpiderData.*;

/**
 * <b style="color:FFC800"><font size="+2">Registrar：自然更替注册器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">统一注册自然更替所有的注册项</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/6 15:17
 */
enum Registrar {
    ;

    interface Server {
        static void register() {
            Data.registerServer();
            Vanilla.registerServer();
            Variant.registerServer();
            Special.registerServer();
        }
    }

    interface Client {
        static void register() {
            Data.registerClient();
            Vanilla.registerClient();
            Variant.registerClient();
            Special.registerClient();
        }
    }

    private interface Data {
        private static void registerServer() {
            SRItemGroups.register();
        }

        private static void registerClient() {
        }
    }

    private interface Special {
        private static void registerServer() {
            FireBlock.registerDefaultFlammables();
            RecipeItem.registerServer();
            Effect.registerServer();
            Registry.register(Registries.ITEM, new Identifier(SR_ID, "sr_icon"), SR_ICON);
        }

        private static void registerClient() {
        }

        interface RecipeItem {
            private static void registerServer() {
                // 注册合成假毒箭
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "poison_arrow"), POISON_ARROW);
            }
        }

        interface Effect {
            private static void registerServer() {
                // 注册蜘蛛伪装状态效果
                Registry.register(Registries.STATUS_EFFECT, new Identifier(SR_ID, "spider_camouflage"), SPIDER_CAMOUFLAGE);
            }
        }
    }

    private interface Vanilla {
        private static void registerServer() {
            RefinedCopper.registerServer();
            CuFeAlloy.registerServer();
            AuCuAlloy.registerServer();
            Steel.registerServer();
            RangedRelated.registerServer();
            Registry.register(Registries.ITEM, new Identifier(SR_ID, "alloy_forging_die"), ALLOY_FORGING_DIE);
            ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(ALLOY_FORGING_DIE));
        }

        private static void registerClient() {
            RangedRelated.registerClient();
        }

        interface RefinedCopper {
            private static void registerServer() {
                registerItems();
                registerTools();
                registerArmor();
            }

            private static void registerItems() {
                // 注册炼锭铜
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "copper_for_smelting_ingot"), COPPER_FOR_SMELTING_INGOT);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(COPPER_FOR_SMELTING_INGOT));
                // 注册精铜锭
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "refined_copper_ingot"), REFINED_COPPER_INGOT);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(REFINED_COPPER_INGOT));
                // 注册精铜粒
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "refined_copper_nugget"), REFINED_COPPER_NUGGET);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(REFINED_COPPER_NUGGET));
                // 注册精铜块
                Registry.register(Registries.BLOCK, new Identifier(SR_ID, "refined_copper_block"), REFINED_COPPER_BLOCK);
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "refined_copper_block"), REFINED_COPPER_BLOCK_ITEM);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(REFINED_COPPER_BLOCK_ITEM));
            }

            private static void registerTools() {
                // 注册精铜锹
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "refined_copper_shovel"), REFINED_COPPER_SHOVEL);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(REFINED_COPPER_SHOVEL));
                // 注册精铜镐
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "refined_copper_pickaxe"), REFINED_COPPER_PICKAXE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(REFINED_COPPER_PICKAXE));
                // 注册精铜斧
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "refined_copper_axe"), REFINED_COPPER_AXE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(REFINED_COPPER_AXE));
                // 注册精铜锄
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "refined_copper_hoe"), REFINED_COPPER_HOE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(REFINED_COPPER_HOE));
                // 注册精铜剑
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "refined_copper_sword"), REFINED_COPPER_SWORD);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(REFINED_COPPER_SWORD));
            }

            private static void registerArmor() {
                // 注册精铜头盔
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "refined_copper_helmet"), REFINED_COPPER_HELMET);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(REFINED_COPPER_HELMET));
                // 注册精铜胸甲
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "refined_copper_chestplate"), REFINED_COPPER_CHESTPLATE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(REFINED_COPPER_CHESTPLATE));
                // 注册精铜护腿
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "refined_copper_leggings"), REFINED_COPPER_LEGGINGS);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(REFINED_COPPER_LEGGINGS));
                // 注册精铜靴子
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "refined_copper_boots"), REFINED_COPPER_BOOTS);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(REFINED_COPPER_BOOTS));

                // 注册装备装甲音效
                Registry.register(Registries.SOUND_EVENT, EQUIP_REFINED_COPPER_ID, EQUIP_REFINED_COPPER);
            }
        }

        interface CuFeAlloy {
            private static void registerServer() {
                registerItems();
                registerTools();
                registerArmor();
            }

            private static void registerItems() {
                // 注册铜铁合金
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "cufe_alloy"), CUFE_ALLOY);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(CUFE_ALLOY));
                // 注册铜铁锭
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "cufe_alloy_ingot"), CUFE_ALLOY_INGOT);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(CUFE_ALLOY_INGOT));
                // 注册铜铁粒
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "cufe_alloy_nugget"), CUFE_ALLOY_NUGGET);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(CUFE_ALLOY_NUGGET));
                // 注册铜铁块
                Registry.register(Registries.BLOCK, new Identifier(SR_ID, "cufe_alloy_block"), CUFE_ALLOY_BLOCK);
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "cufe_alloy_block"), CUFE_ALLOY_BLOCK_ITEM);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(CUFE_ALLOY_BLOCK_ITEM));
            }

            private static void registerTools() {
                // 注册铜铁锹
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "cufe_alloy_shovel"), CUFE_ALLOY_SHOVEL);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_SHOVEL));
                // 注册铜铁镐
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "cufe_alloy_pickaxe"), CUFE_ALLOY_PICKAXE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_PICKAXE));
                // 注册铜铁斧
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "cufe_alloy_axe"), CUFE_ALLOY_AXE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_AXE));
                // 注册铜铁锄
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "cufe_alloy_hoe"), CUFE_ALLOY_HOE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_HOE));
                // 注册铜铁剑
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "cufe_alloy_sword"), CUFE_ALLOY_SWORD);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_SWORD));
            }

            private static void registerArmor() {
                // 注册铜铁头盔
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "cufe_alloy_helmet"), CUFE_ALLOY_HELMET);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_HELMET));
                // 注册铜铁胸甲
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "cufe_alloy_chestplate"), CUFE_ALLOY_CHESTPLATE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_CHESTPLATE));
                // 注册铜铁护腿
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "cufe_alloy_leggings"), CUFE_ALLOY_LEGGINGS);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_LEGGINGS));
                // 注册铜铁靴子
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "cufe_alloy_boots"), CUFE_ALLOY_BOOTS);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(CUFE_ALLOY_BOOTS));

                // 注册装备装甲音效
                Registry.register(Registries.SOUND_EVENT, EQUIP_CUFE_ALLOY_ID, EQUIP_CUFE_ALLOY);
            }
        }

        interface AuCuAlloy {
            private static void registerServer() {
                registerItems();
                registerTools();
                registerArmor();
            }

            private static void registerItems() {
                // 注册金铜合金
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "aucu_alloy"), AUCU_ALLOY);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(AUCU_ALLOY));
                // 注册金铜锭
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "aucu_alloy_ingot"), AUCU_ALLOY_INGOT);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(AUCU_ALLOY_INGOT));
                // 注册金铜粒
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "aucu_alloy_nugget"), AUCU_ALLOY_NUGGET);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(AUCU_ALLOY_NUGGET));
                // 注册金铜块
                Registry.register(Registries.BLOCK, new Identifier(SR_ID, "aucu_alloy_block"), AUCU_ALLOY_BLOCK);
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "aucu_alloy_block"), AUCU_ALLOY_BLOCK_ITEM);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(AUCU_ALLOY_BLOCK_ITEM));
            }

            private static void registerTools() {
                // 注册金铜锹
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "aucu_alloy_shovel"), AUCU_ALLOY_SHOVEL);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_SHOVEL));
                // 注册金铜镐
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "aucu_alloy_pickaxe"), AUCU_ALLOY_PICKAXE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_PICKAXE));
                // 注册金铜斧
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "aucu_alloy_axe"), AUCU_ALLOY_AXE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_AXE));
                // 注册金铜锄
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "aucu_alloy_hoe"), AUCU_ALLOY_HOE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_HOE));
                // 注册金铜剑
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "aucu_alloy_sword"), AUCU_ALLOY_SWORD);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_SWORD));
            }

            private static void registerArmor() {
                // 注册金铜头盔
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "aucu_alloy_helmet"), AUCU_ALLOY_HELMET);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_HELMET));
                // 注册金铜胸甲
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "aucu_alloy_chestplate"), AUCU_ALLOY_CHESTPLATE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_CHESTPLATE));
                // 注册金铜护腿
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "aucu_alloy_leggings"), AUCU_ALLOY_LEGGINGS);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_LEGGINGS));
                // 注册金铜靴子
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "aucu_alloy_boots"), AUCU_ALLOY_BOOTS);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(AUCU_ALLOY_BOOTS));

                // 注册装备装甲音效
                Registry.register(Registries.SOUND_EVENT, EQUIP_AUCU_ALLOY_ID, EQUIP_AUCU_ALLOY);
            }
        }

        interface Steel {
            private static void registerServer() {
                registerItems();
                registerTools();
                registerArmor();
            }

            private static void registerItems() {
                // 注册精煤
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "clean_coal"), CLEAN_COAL);
                FuelRegistry.INSTANCE.add(CLEAN_COAL, getTick(160));
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(CLEAN_COAL));
                // 注册生铁
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "pig_iron"), PIG_IRON);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(PIG_IRON));
                // 注册钢锭
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "steel_ingot"), STEEL_INGOT);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(STEEL_INGOT));
                // 注册钢块
                Registry.register(Registries.BLOCK, new Identifier(SR_ID, "steel_block"), STEEL_BLOCK);
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "steel_block"), STEEL_BLOCK_ITEM);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(STEEL_BLOCK_ITEM));
            }

            private static void registerTools() {
                // 注册钢锹
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "steel_shovel"), STEEL_SHOVEL);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(STEEL_SHOVEL));
                // 注册钢镐
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "steel_pickaxe"), STEEL_PICKAXE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(STEEL_PICKAXE));
                // 注册钢斧
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "steel_axe"), STEEL_AXE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(STEEL_AXE));
                // 注册钢锄
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "steel_hoe"), STEEL_HOE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(STEEL_HOE));
                // 注册钢剑
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "steel_sword"), STEEL_SWORD);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(STEEL_SWORD));
            }

            private static void registerArmor() {
                // 注册钢制头盔
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "steel_helmet"), STEEL_HELMET);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(STEEL_HELMET));
                // 注册钢制胸甲
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "steel_chestplate"), STEEL_CHESTPLATE);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(STEEL_CHESTPLATE));
                // 注册钢制护腿
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "steel_leggings"), STEEL_LEGGINGS);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(STEEL_LEGGINGS));
                // 注册钢制靴子
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "steel_boots"), STEEL_BOOTS);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(STEEL_BOOTS));

                // 注册装备装甲音效
                Registry.register(Registries.SOUND_EVENT, EQUIP_STEEL_ID, EQUIP_STEEL);
            }
        }

        interface RangedRelated {
            private static void registerServer() {
                Projectile.registerServer();
                BasicWeapon.registerServer();
                EnhancedWeapon.registerServer();
                Armor.registerServer();
            }

            private static void registerClient() {
                Projectile.registerClient();
                BasicWeapon.registerClient();
                EnhancedWeapon.registerClient();
                Armor.registerClient();
            }

            interface Projectile {
                private static void registerServer() {
                    // 注册石弹
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "stoneball"), STONEBALL);
                    Registry.register(Registries.ENTITY_TYPE, new Identifier(SR_ID, "stoneball"), STONEBALL_ENTITY);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(STONEBALL));
                    // 注册发射器发射石弹
                    DispenserBlock.registerBehavior(STONEBALL, new ProjectileDispenserBehavior() {
                        @Override
                        protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                            return new StoneballEntity(world, position.getX(), position.getY(), position.getZ());
                        }
                    });
                    // 注册石弹音效
                    Registry.register(Registries.SOUND_EVENT, STONEBALL_THROW_ID, STONEBALL_THROW);
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "full_power_steel_arrow"), FULL_POWER_STEEL_ARROW);
                    Registry.register(Registries.ENTITY_TYPE, new Identifier(SR_ID, "full_power_steel_arrow"), FULL_POWER_STEEL_ARROW_ENTITY);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(FULL_POWER_STEEL_ARROW));
                    // 注册发射器发射箭矢
                    DispenserBlock.registerBehavior(FULL_POWER_STEEL_ARROW, new ProjectileDispenserBehavior() {
                        @Override
                        protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                            FullPowerSteelArrowEntity arrowEntity = new FullPowerSteelArrowEntity(world, position.getX(), position.getY(), position.getZ());
                            arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                            return arrowEntity;
                        }
                    });
                }

                private static void registerClient() {
                    // 注册石弹渲染
                    EntityRendererRegistry.register(STONEBALL_ENTITY, FlyingItemEntityRenderer::new);
                    // 注册全威力钢箭
                    EntityRendererRegistry.register(FULL_POWER_STEEL_ARROW_ENTITY, FullPowerSteelArrowRender::new);
                }
            }

            interface BasicWeapon {
                private static void registerServer() {
                    // 注册丫弹弓
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "slingshot"), SLINGSHOT);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(SLINGSHOT));
                    // 注册丫弹弓投掷音效
                    Registry.register(Registries.SOUND_EVENT, SLINGSHOT_THROW_ID, SLINGSHOT_THROW);
                    // 注册反曲弓
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "recurve_bow"), RECURVE_BOW);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(RECURVE_BOW));
                    // 注册劲弩
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "arbalest"), ARBALEST);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(ARBALEST));
                    // 注册劲弩音效
                    Registry.register(Registries.SOUND_EVENT, ARBALEST_LOADING_START_ID, ARBALEST_LOADING_START);
                    Registry.register(Registries.SOUND_EVENT, ARBALEST_LOADING_MIDDLE_ID, ARBALEST_LOADING_MIDDLE);
                    Registry.register(Registries.SOUND_EVENT, ARBALEST_LOADING_END_ID, ARBALEST_LOADING_END);
                    Registry.register(Registries.SOUND_EVENT, ARBALEST_SHOOT_ID, ARBALEST_SHOOT);
                    Registry.register(Registries.SOUND_EVENT, ARBALEST_QUICK_CHARGE_1_ID, ARBALEST_QUICK_CHARGE_1);
                    Registry.register(Registries.SOUND_EVENT, ARBALEST_QUICK_CHARGE_2_ID, ARBALEST_QUICK_CHARGE_2);
                    Registry.register(Registries.SOUND_EVENT, ARBALEST_QUICK_CHARGE_3_ID, ARBALEST_QUICK_CHARGE_3);
                }

                private static void registerClient() {
                    // 注册丫弹弓谓词
                    ModelPredicateProviderRegistry.register(SLINGSHOT, new Identifier("pulling"), (stack, world, entity, seed) -> {
                        if (entity == null) return 0;
                        return entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0;
                    });
                    ModelPredicateProviderRegistry.register(SLINGSHOT, new Identifier("pull"), (stack, world, entity, seed) -> {
                        if (entity == null) return 0.0F;
                        return entity.getActiveItem() != stack ? 0.0F : ((SRBow) stack.getItem()).getPullProgress(stack.getMaxUseTime() - entity.getItemUseTimeLeft());
                    });
                    ModelPredicateProviderRegistry.register(SLINGSHOT, new Identifier("pellet"), (stack, world, entity, seed) -> {
                        if (entity == null) return 0;
                        return entity.getActiveItem() != stack ? 0 : ((Slingshot) stack.getItem()).getBulletId(stack);
                    });
                    // 注册反曲弓谓词
                    ModelPredicateProviderRegistry.register(RECURVE_BOW, new Identifier("pulling"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0;
                    });
                    ModelPredicateProviderRegistry.register(RECURVE_BOW, new Identifier("pull"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0.0F;
                        return entity.getActiveItem() != stack ? 0.0F : ((SRBow) stack.getItem()).getPullProgress(stack.getMaxUseTime() - entity.getItemUseTimeLeft());
                    });
                    ModelPredicateProviderRegistry.register(RECURVE_BOW, new Identifier("arrow"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return entity.getActiveItem() != stack ? 0 : ((RecurveBow) stack.getItem()).getBulletId(stack);
                    });
                    // 注册劲弩谓词
                    ModelPredicateProviderRegistry.register(ARBALEST, new Identifier("pulling"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0;
                    });
                    ModelPredicateProviderRegistry.register(ARBALEST, new Identifier("pull"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0.0F;
                        return entity.getActiveItem() != stack ? 0.0F : ((SRCrossbow) stack.getItem()).getPullProgress(stack.getMaxUseTime() - entity.getItemUseTimeLeft(), stack);
                    });
                    ModelPredicateProviderRegistry.register(ARBALEST, new Identifier("charged"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return isCharged(stack) ? 1 : 0;
                    });
                    ModelPredicateProviderRegistry.register(ARBALEST, new Identifier("bullet"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return ((Arbalest) stack.getItem()).getBulletId(stack);
                    });
                }
            }

            interface EnhancedWeapon {
                private static void registerServer() {
                    // 注册复合弓
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "compound_bow"), COMPOUND_BOW);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(COMPOUND_BOW));
                    // 注册诸葛连弩
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "juger_repeating_crossbow"), JUGER_REPEATING_CROSSBOW);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(JUGER_REPEATING_CROSSBOW));
                    // 注册诸葛连弩音效
                    Registry.register(Registries.SOUND_EVENT, JUGER_REPEATING_CROSSBOW_LOADING_START_ID, JUGER_REPEATING_CROSSBOW_LOADING_START);
                    Registry.register(Registries.SOUND_EVENT, JUGER_REPEATING_CROSSBOW_LOADING_END_ID, JUGER_REPEATING_CROSSBOW_LOADING_END);
                    Registry.register(Registries.SOUND_EVENT, JUGER_REPEATING_CROSSBOW_SHOOT_ID, JUGER_REPEATING_CROSSBOW_SHOOT);
                    // 注册神臂弩
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "marks_crossbow"), MARKS_CROSSBOW);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(MARKS_CROSSBOW));
                    // 注册神臂弩音效
                    Registry.register(Registries.SOUND_EVENT, MARKS_CROSSBOW_LOADING_START_ID, MARKS_CROSSBOW_LOADING_START);
                    Registry.register(Registries.SOUND_EVENT, MARKS_CROSSBOW_LOADING_MIDDLE_ID, MARKS_CROSSBOW_LOADING_MIDDLE);
                    Registry.register(Registries.SOUND_EVENT, MARKS_CROSSBOW_LOADING_END_ID, MARKS_CROSSBOW_LOADING_END);
                    Registry.register(Registries.SOUND_EVENT, MARKS_CROSSBOW_SHOOT_ID, MARKS_CROSSBOW_SHOOT);
                    Registry.register(Registries.SOUND_EVENT, MARKS_CROSSBOW_QUICK_CHARGE_1_ID, MARKS_CROSSBOW_QUICK_CHARGE_1);
                    Registry.register(Registries.SOUND_EVENT, MARKS_CROSSBOW_QUICK_CHARGE_2_ID, MARKS_CROSSBOW_QUICK_CHARGE_2);
                    Registry.register(Registries.SOUND_EVENT, MARKS_CROSSBOW_QUICK_CHARGE_3_ID, MARKS_CROSSBOW_QUICK_CHARGE_3);
                }

                private static void registerClient() {
                    // 注册复合弓谓词
                    ModelPredicateProviderRegistry.register(COMPOUND_BOW, new Identifier("pulling"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0;
                    });
                    ModelPredicateProviderRegistry.register(COMPOUND_BOW, new Identifier("pull"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0.0F;
                        return entity.getActiveItem() != stack ? 0.0F : ((SRBow) stack.getItem()).getPullProgress(stack.getMaxUseTime() - entity.getItemUseTimeLeft());
                    });
                    ModelPredicateProviderRegistry.register(COMPOUND_BOW, new Identifier("arrow"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return entity.getActiveItem() != stack ? 0 : ((CompoundBow) stack.getItem()).getBulletId(stack);
                    });
                    // 注册诸葛连弩谓词
                    ModelPredicateProviderRegistry.register(JUGER_REPEATING_CROSSBOW, new Identifier("pulling"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0;
                    });
                    ModelPredicateProviderRegistry.register(JUGER_REPEATING_CROSSBOW, new Identifier("pull"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0.0F;
                        return entity.getActiveItem() != stack ? 0.0F :
                                ((SRCrossbow) stack.getItem()).getPullProgress(stack.getMaxUseTime() - entity.getItemUseTimeLeft(), stack)
                                        * ((JugerRepeatingCrossbow) stack.getItem()).getCanLoadPulletNum(entity, stack) % 1;
                    });
                    ModelPredicateProviderRegistry.register(JUGER_REPEATING_CROSSBOW, new Identifier("charged"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return isCharged(stack) ? 1 : 0;
                    });
                    ModelPredicateProviderRegistry.register(JUGER_REPEATING_CROSSBOW, new Identifier("shoot"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return getIsShoot(stack) ? 1 : 0;
                    });
                    ModelPredicateProviderRegistry.register(JUGER_REPEATING_CROSSBOW, new Identifier("bullet"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return ((JugerRepeatingCrossbow) stack.getItem()).getBulletId(stack);
                    });
                    // 注册神臂弩谓词
                    ModelPredicateProviderRegistry.register(MARKS_CROSSBOW, new Identifier("pulling"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0;
                    });
                    ModelPredicateProviderRegistry.register(MARKS_CROSSBOW, new Identifier("pull"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0.0F;
                        return entity.getActiveItem() != stack ? 0.0F : ((SRCrossbow) stack.getItem()).getPullProgress(stack.getMaxUseTime() - entity.getItemUseTimeLeft(), stack);
                    });
                    ModelPredicateProviderRegistry.register(MARKS_CROSSBOW, new Identifier("charged"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return isCharged(stack) ? 1 : 0;
                    });
                    ModelPredicateProviderRegistry.register(MARKS_CROSSBOW, new Identifier("shoot_end"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return isShootEnd(stack) ? 1 : 0;
                    });
                    ModelPredicateProviderRegistry.register(MARKS_CROSSBOW, new Identifier("bullet"), (stack, world, entity, seed) -> {
                        if (entity == null)
                            return 0;
                        return ((MarksCrossbow) stack.getItem()).getBulletId(stack);
                    });
                }
            }

            interface Armor {
                private static void registerServer() {
                    // 注册防箭衣
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "arrowproof_vest"), ARROWPROOF_VEST);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(ARROWPROOF_VEST));

                    // 注册装备装甲音效
                    Registry.register(Registries.SOUND_EVENT, EQUIP_ARROWPROOF_VEST_ID, EQUIP_ARROWPROOF_VEST);
                }

                private static void registerClient() {
                    // 注册防箭衣染色
                    ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack)), ARROWPROOF_VEST);
                }
            }
        }
    }

    private interface Variant {
        private static void registerServer() {
            General.registerServer();
            Spider.registerServer();
        }

        private static void registerClient() {
            General.registerClient();
            Spider.registerClient();
        }

        interface General {
            private static void registerServer() {
                Block.registerServer();
            }

            private static void registerClient() {
                Block.registerClient();
            }

            interface Block {
                @SuppressWarnings("MapOrSetKeyShouldOverrideHashCodeEquals")
                private static void registerServer() {
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "eerie_regolith"), EERIE_REGOLITH);
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "eerie_regolith"), EERIE_REGOLITH_ITEM);
                    InfectSaplingCallback.EVENT.register(((world, pos, state, random, sapling) -> {
                        if (world.getBlockState(pos.down()).isOf(EERIE_REGOLITH)) {
                            world.setBlockState(pos, Blocks.AIR.getDefaultState());
                            StraightTrunkPlacer straightTrunkPlacer = new StraightTrunkPlacer(5, 1, 0);
                            EerieFoliagePlacer eerieFoliagePlacer = new EerieFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), 7);
                            TreeFeatureConfig treeFeatureConfig = new TreeFeatureConfig.Builder(
                                    BlockStateProvider.of(EERIE_RIND),
                                    straightTrunkPlacer,
                                    BlockStateProvider.of(EERIE_BOUGH),
                                    eerieFoliagePlacer,
                                    new TwoLayersFeatureSize(1, 0, 1))
                                    .dirtProvider(BlockStateProvider.of(EERIE_REGOLITH))
                                    .decorators(Collections.singletonList(EerieTreeDecorator.INSTANCE))
                                    .build();
                            int truckHeight = random.nextBetween(5, 5 + 1);
                            straightTrunkPlacer.generate(world, world::setBlockState, random, truckHeight, pos, treeFeatureConfig);
                            EerieTreeDecorator.INSTANCE.generate(new TreeDecorator.Generator(world, world::setBlockState, random, Sets.newHashSet(pos.down(), pos, pos.up()), Sets.newHashSet(), Sets.newHashSet(pos.down())));
                            eerieFoliagePlacer.generate(world, new FoliagePlacer.BlockPlacer() {
                                        @Override
                                        public void placeBlock(BlockPos pos, BlockState state) {
                                            world.setBlockState(pos, state);
                                        }

                                        @Override
                                        public boolean hasPlacedBlock(BlockPos pos) {
                                            return false;
                                        }
                                    }, random, treeFeatureConfig, truckHeight,
                                    new FoliagePlacer.TreeNode(pos.offset(Direction.UP, truckHeight), 3, false), 7, 3);
                        }
                        return ActionResult.PASS;
                    }));
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(EERIE_REGOLITH_ITEM));
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "treacherous_sludge"), TREACHEROUS_SLUDGE);
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "treacherous_sludge"), TREACHEROUS_SLUDGE_ITEM);
                    InfectSaplingCallback.EVENT.register(((world, pos, state, random, sapling) -> {
                        if (world.getBlockState(pos.down()).isOf(TREACHEROUS_SLUDGE)) {
                            world.setBlockState(pos, Blocks.AIR.getDefaultState());
                            TreacherousTrunkPlacer treacherousTrunkPlacer = new TreacherousTrunkPlacer(5, 2, 0);
                            TreacherousFoliagePlacer treacherousFoliagePlacer = new TreacherousFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0));
                            TreeFeatureConfig treeFeatureConfig = new TreeFeatureConfig.Builder(
                                    BlockStateProvider.of(TREACHEROUS_SAC),
                                    treacherousTrunkPlacer,
                                    BlockStateProvider.of(TREACHEROUS_VINES_PLANT),
                                    treacherousFoliagePlacer,
                                    new TwoLayersFeatureSize(1, 0, 1))
                                    .build();
                            int truckHeight = random.nextBetween(5, 5 + 2);
                            treacherousTrunkPlacer.generate(world, world::setBlockState, random, truckHeight, pos, treeFeatureConfig);
                            treacherousFoliagePlacer.generate(world, new FoliagePlacer.BlockPlacer() {
                                        @Override
                                        public void placeBlock(BlockPos pos, BlockState state) {
                                            if (world.getBlockState(pos.down()).isAir()) world.setBlockState(pos, state);
                                            else world.setBlockState(pos, TREACHEROUS_VINES.getDefaultState());
                                        }

                                        @Override
                                        public boolean hasPlacedBlock(BlockPos pos) {
                                            return false;
                                        }
                                    }, random, treeFeatureConfig, truckHeight,
                                    new FoliagePlacer.TreeNode(pos.offset(Direction.UP, truckHeight), 0, false), 0, 0);
                            world.setBlockState(pos.down(), TREACHEROUS_SLUDGE.getDefaultState());
                        }
                        return ActionResult.PASS;
                    }));
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(TREACHEROUS_SLUDGE_ITEM));
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "water_eerie_rind"), WATER_EERIE_RIND);
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "powder_snow_eerie_rind"), POWDER_SNOW_EERIE_RIND);
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "ice_eerie_rind"), ICE_EERIE_RIND);
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "lava_eerie_rind"), LAVA_EERIE_RIND);
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "eerie_rind"), EERIE_RIND);
                    Item.BLOCK_ITEMS.put(WATER_EERIE_RIND, EERIE_RIND_ITEM);
                    Item.BLOCK_ITEMS.put(POWDER_SNOW_EERIE_RIND, EERIE_RIND_ITEM);
                    Item.BLOCK_ITEMS.put(ICE_EERIE_RIND, EERIE_RIND_ITEM);
                    Item.BLOCK_ITEMS.put(LAVA_EERIE_RIND, EERIE_RIND_ITEM);
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "eerie_rind"), EERIE_RIND_ITEM);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(EERIE_RIND_ITEM));
                    EerieRindBehavior.registerBehavior();
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "eerie_bough"), EERIE_BOUGH);
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "eerie_bough"), EERIE_BOUGH_ITEM);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(EERIE_BOUGH_ITEM));
                    Registry.register(Registries.SOUND_EVENT, TREACHEROUS_SAC_BREAK_ID, TREACHEROUS_SAC_BREAK);
                    Registry.register(Registries.ENTITY_TYPE, new Identifier(SR_ID, "treacherous_sac"), TREACHEROUS_SAC_ENTITY);
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "treacherous_sac"), TREACHEROUS_SAC);
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "treacherous_sac"), TREACHEROUS_SAC_ITEM);
                    FabricDefaultAttributeRegistry.register(TREACHEROUS_SAC_ENTITY, TreacherousSacEntity.createAttributes());
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(TREACHEROUS_SAC_ITEM));
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "treacherous_vines_plant"), TREACHEROUS_VINES_PLANT);
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "treacherous_vines"), TREACHEROUS_VINES);
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "treacherous_vines"), TREACHEROUS_VINES_ITEM);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(TREACHEROUS_VINES_ITEM));

                    EERIE_ROOT_TREE_DECORATOR = Registry.register(Registries.TREE_DECORATOR_TYPE, new Identifier(SR_ID, "eerie_root"), new TreeDecoratorType<>(EerieTreeDecorator.CODEC));
                    EERIE_BOUGH_FOLIAGE_PLACER = Registry.register(Registries.FOLIAGE_PLACER_TYPE, new Identifier(SR_ID, "eerie_bough"), new FoliagePlacerType<>(EerieFoliagePlacer.CODEC));
                    TREACHEROUS_TRUNK_PLACER = Registry.register(Registries.TRUNK_PLACER_TYPE, new Identifier(SR_ID, "treacherous_trunk"), new TrunkPlacerType<>(TreacherousTrunkPlacer.CODEC));
                    TREACHEROUS_FOLIAGE_PLACER = Registry.register(Registries.FOLIAGE_PLACER_TYPE, new Identifier(SR_ID, "treacherous_vines"), new FoliagePlacerType<>(TreacherousFoliagePlacer.CODEC));
                }

                private static void registerClient() {
                    ColorProviderRegistry.BLOCK.register(((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getWaterColor(world, pos) : -1), WATER_EERIE_RIND);
                    EntityModelLayerRegistry.registerModelLayer(TREACHEROUS_SAC_LAYER, TreacherousSacModel::getTexturedModelData);
                    EntityRendererRegistry.register(TREACHEROUS_SAC_ENTITY, TreacherousSacRenderer::new);
                    BlockRenderLayerMap.INSTANCE.putBlock(TREACHEROUS_VINES_PLANT, RenderLayer.getCutout());
                    BlockRenderLayerMap.INSTANCE.putBlock(TREACHEROUS_VINES, RenderLayer.getCutout());
                }
            }
        }

        interface Spider {
            private static void registerServer() {
                registerItems();
                registerEquipment();
                Block.registerServer();
                Mob.registerServer();
            }

            private static void registerClient() {
                Block.registerClient();
                Mob.registerClient();
            }

            interface Block {
                private static void registerServer() {
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "cobwebby_soil"), COBWEBBY_SOIL);
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "cobwebby_soil"), COBWEBBY_SOIL_ITEM);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(COBWEBBY_SOIL_ITEM));
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "gossamer_carpet"), GOSSAMER_CARPET);
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "gossamer_carpet"), GOSSAMER_CARPET_ITEM);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(GOSSAMER_CARPET_ITEM));
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "gossamery_leaves"), GOSSAMERY_LEAVES);
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "gossamery_leaves"), GOSSAMERY_LEAVES_ITEM);
                    RegistryFlammableCallback.EVENT.register(flammable -> {
                        flammable.register(GOSSAMERY_LEAVES, STICKY_COMPACT_COBWEB_BURN_CHANCE, STICKY_COMPACT_COBWEB_SPREAD_CHANCE);
                        return ActionResult.PASS;
                    });
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(GOSSAMERY_LEAVES_ITEM));
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "sticky_compact_cobweb"), STICKY_COMPACT_COBWEB);
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "sticky_compact_cobweb"), STICKY_COMPACT_COBWEB_ITEM);
                    RegistryFlammableCallback.EVENT.register(flammable -> {
                        flammable.register(STICKY_COMPACT_COBWEB, STICKY_COMPACT_COBWEB_BURN_CHANCE, STICKY_COMPACT_COBWEB_SPREAD_CHANCE);
                        return ActionResult.PASS;
                    });
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(STICKY_COMPACT_COBWEB_ITEM));
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "spider_chrysalis"), SPIDER_CHRYSALIS);
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "spider_chrysalis"), SPIDER_CHRYSALIS_ITEM);
                    SimpleBlockFeatureCreateCallback.EVENT.register(((world, pos, state, returnBoolean) -> {
                        if (!state.equals(SPIDER_CHRYSALIS.getDefaultState()))
                            return new SimpleBlockFeatureCreateCallback.ReturnValue(ActionResult.PASS, returnBoolean);
                        SRSpiderData.ChrysalisStyle style = getRandomStyle();
                        Random random = Random.create();
                        Direction horizontalDirection;
                        {
                            int nextInt = random.nextInt(4);
                            switch (nextInt) {
                                case 0 -> horizontalDirection = Direction.NORTH;
                                case 1 -> horizontalDirection = Direction.WEST;
                                case 2 -> horizontalDirection = Direction.EAST;
                                default -> horizontalDirection = Direction.SOUTH;
                            }
                        }
                        // 保证如果方块被方块阻挡则只能放下小型样式方块
                        Direction verticalDirection = null;
                        {
                            if (isDoubleBlock(style)) {
                                if ((state.isSideSolidFullSquare(world, pos.down(), Direction.UP) || world.getBlockState(pos.down()).isIn(BlockTags.LEAVES))
                                        && world.getBlockState(pos.up()).isOf(Blocks.AIR))
                                    verticalDirection = Direction.UP;
                                else if ((state.isSideSolidFullSquare(world, pos.up(), Direction.DOWN) || world.getBlockState(pos.up()).isIn(BlockTags.LEAVES))
                                        && world.getBlockState(pos.down()).isOf(Blocks.AIR))
                                    verticalDirection = Direction.DOWN;
                                else style = SRSpiderData.ChrysalisStyle.SMALL;
                            }
                            if (!isDoubleBlock(style)) {
                                if (state.isSideSolidFullSquare(world, pos.down(), Direction.UP) || world.getBlockState(pos.down()).isIn(BlockTags.LEAVES))
                                    verticalDirection = Direction.UP;
                                else if (state.isSideSolidFullSquare(world, pos.up(), Direction.DOWN) || world.getBlockState(pos.up()).isIn(BlockTags.LEAVES))
                                    verticalDirection = Direction.DOWN;
                            }
                        }
                        // 如果因为未知原因 verticalDirection 为 null 则替换为 AIR
                        if (verticalDirection == null)
                            return new SimpleBlockFeatureCreateCallback.ReturnValue(ActionResult.PASS, world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2));
                            // 设置环境方块
                        else {
                            BlockPos downPos = pos.down();
                            if (state.isSideSolidFullSquare(world, downPos, Direction.UP)) {
                                if (world.getBlockState(downPos).isIn(BlockTags.LEAVES))
                                    world.setBlockState(downPos, GOSSAMERY_LEAVES.getDefaultState(), 2);
                                else world.setBlockState(downPos, COBWEBBY_SOIL.getDefaultState(), 2);
                                COBWEBBY_SOIL.spread(world, downPos);
                            } else world.setBlockState(pos.up(), GOSSAMERY_LEAVES.getDefaultState(), 2);
                        }
                        // 设置双高块
                        if (isDoubleBlock(style)) {
                            if (verticalDirection == Direction.UP) {
                                if (style == SRSpiderData.ChrysalisStyle.DEFAULT) {
                                    world.setBlockState(pos.up(), state.with(CHRYSALIS_STYLE, SRSpiderData.ChrysalisStyle.PLACEHOLDER_SHORT)
                                            .with(Properties.HORIZONTAL_FACING, horizontalDirection)
                                            .with(Properties.VERTICAL_DIRECTION, verticalDirection), 2);
                                } else {
                                    world.setBlockState(pos.up(), state.with(CHRYSALIS_STYLE, SRSpiderData.ChrysalisStyle.PLACEHOLDER)
                                            .with(Properties.HORIZONTAL_FACING, horizontalDirection)
                                            .with(Properties.VERTICAL_DIRECTION, verticalDirection), 2);
                                }
                            } else {
                                if (style == SRSpiderData.ChrysalisStyle.DEFAULT) {
                                    world.setBlockState(pos.down(), state.with(CHRYSALIS_STYLE, SRSpiderData.ChrysalisStyle.PLACEHOLDER_SHORT)
                                            .with(Properties.HORIZONTAL_FACING, horizontalDirection)
                                            .with(Properties.VERTICAL_DIRECTION, verticalDirection), 2);
                                } else {
                                    world.setBlockState(pos.down(), state.with(CHRYSALIS_STYLE, SRSpiderData.ChrysalisStyle.PLACEHOLDER)
                                            .with(Properties.HORIZONTAL_FACING, horizontalDirection)
                                            .with(Properties.VERTICAL_DIRECTION, verticalDirection), 2);
                                }
                            }
                            return new SimpleBlockFeatureCreateCallback.ReturnValue(ActionResult.PASS, world.setBlockState(pos, state.with(CHRYSALIS_STYLE, style)
                                    .with(Properties.HORIZONTAL_FACING, horizontalDirection)
                                    .with(Properties.VERTICAL_DIRECTION, verticalDirection), 2));
                        }
                        return new SimpleBlockFeatureCreateCallback.ReturnValue(ActionResult.PASS, world.setBlockState(pos, state.with(CHRYSALIS_STYLE, style)
                                .with(Properties.HORIZONTAL_FACING, horizontalDirection)
                                .with(Properties.VERTICAL_DIRECTION, verticalDirection), 2));
                    }));
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(SPIDER_CHRYSALIS_ITEM));
                    Registry.register(Registries.BLOCK, new Identifier(SR_ID, "spider_egg_cocoon"), SPIDER_EGG_COCOON);
                    Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(SR_ID, "spider_egg_cocoon"), SPIDER_EGG_COCOON_ENTITY);
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, "spider_egg_cocoon"), SPIDER_EGG_COCOON_ITEM);
                    SimpleBlockFeatureCreateCallback.EVENT.register(((world, pos, state, returnBoolean) -> {
                        if (!state.isOf(SPIDER_EGG_COCOON))
                            return new SimpleBlockFeatureCreateCallback.ReturnValue(ActionResult.PASS, returnBoolean);
                        BlockPos downPos = pos.down();
                        boolean flag = !world.getBlockState(downPos).isIn(BlockTags.LEAVES);
                        if (flag) COBWEBBY_SOIL.spread(world, downPos);
                        return new SimpleBlockFeatureCreateCallback.ReturnValue(ActionResult.PASS, flag ?
                                world.setBlockState(downPos, COBWEBBY_SOIL.getDefaultState(), 2) : world.setBlockState(downPos, GOSSAMERY_LEAVES.getDefaultState(), 2));
                    }));
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.BLOCK).register(content -> content.add(SPIDER_EGG_COCOON_ITEM));
                }

                private static void registerClient() {
                    BlockRenderLayerMap.INSTANCE.putBlock(GOSSAMER_CARPET, RenderLayer.getCutout());
                    BlockRenderLayerMap.INSTANCE.putBlock(GOSSAMERY_LEAVES, RenderLayer.getCutoutMipped());
                    ColorProviderRegistry.BLOCK.register(((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor()), GOSSAMERY_LEAVES);
                    ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> FoliageColors.getDefaultColor()), GOSSAMERY_LEAVES_ITEM);
                    BlockRenderLayerMap.INSTANCE.putBlock(STICKY_COMPACT_COBWEB, RenderLayer.getCutout());
                }
            }

            interface Mob {
                private static void registerServer() {
                    // 注册幼蛛
                    Registry.register(Registries.ENTITY_TYPE, new Identifier(SR_ID, SpiderLarvaData.ID), SPIDER_LARVA);
                    FabricDefaultAttributeRegistry.register(SPIDER_LARVA, SpiderLarvaEntity.createSpiderAttributes());
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, getEgg(SpiderLarvaData.ID)), SPIDER_LARVA_SPAWN_EGG);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.SPAWN_EGGS).register(content -> content.add(SPIDER_LARVA_SPAWN_EGG));
                    // 注册蜘蛛卫兵
                    Registry.register(Registries.ENTITY_TYPE, new Identifier(SR_ID, GuardSpiderData.ID), GUARD_SPIDER);
                    FabricDefaultAttributeRegistry.register(GUARD_SPIDER, GuardSpiderEntity.createSpiderAttributes());
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, getEgg(GuardSpiderData.ID)), GUARD_SPIDER_SPAWN_EGG);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.SPAWN_EGGS).register(content -> content.add(GUARD_SPIDER_SPAWN_EGG));
                    // 注册喷吐毒蛛
                    Registry.register(Registries.ENTITY_TYPE, new Identifier(SR_ID, SprayPoisonSpiderData.ID), SPRAY_POISON_SPIDER);
                    FabricDefaultAttributeRegistry.register(SPRAY_POISON_SPIDER, SprayPoisonSpiderEntity.createSpiderAttributes());
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, getEgg(SprayPoisonSpiderData.ID)), SPRAY_POISON_SPIDER_SPAWN_EGG);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.SPAWN_EGGS).register(content -> content.add(SPRAY_POISON_SPIDER_SPAWN_EGG));
                    // 注册毒素与毒素粒子
                    Registry.register(Registries.ENTITY_TYPE, new Identifier(SR_ID, "spider_toxin_projectile"), TOXIN);
                    Registry.register(Registries.PARTICLE_TYPE, new Identifier(SR_ID, "toxin"), TOXIN_PARTICLE);
                    // 注册喷吐音效
                    Registry.register(Registries.SOUND_EVENT, SPRAY_TOXIN_ID, SPRAY_TOXIN);
                    Registry.register(Registries.ENTITY_TYPE, new Identifier(SR_ID, WeavingWebSpiderData.ID), WEAVING_WEB_SPIDER);
                    FabricDefaultAttributeRegistry.register(WEAVING_WEB_SPIDER, WeavingWebSpiderEntity.createSpiderAttributes());
                    Registry.register(Registries.ITEM, new Identifier(SR_ID, getEgg(WeavingWebSpiderData.ID)), WEAVING_WEB_SPIDER_SPAWN_EGG);
                    ItemGroupEvents.modifyEntriesEvent(SRItemGroups.SPAWN_EGGS).register(content -> content.add(WEAVING_WEB_SPIDER_SPAWN_EGG));
                }

                private static void registerClient() {
                    // 注册幼蛛
                    EntityRendererRegistry.register(SPIDER_LARVA, SpiderLarvaRenderer::new);
                    EntityModelLayerRegistry.registerModelLayer(SPIDER_LARVA_LAYER, SpiderLarvaModel::getTexturedModelData);
                    // 注册蜘蛛卫兵
                    EntityRendererRegistry.register(GUARD_SPIDER, GuardSpiderRenderer::new);
                    EntityModelLayerRegistry.registerModelLayer(GUARD_SPIDER_LAYER, GuardSpiderModel::getTexturedModelData);
                    // 注册喷吐毒蛛
                    EntityRendererRegistry.register(SPRAY_POISON_SPIDER, SprayPoisonSpiderRenderer::new);
                    EntityModelLayerRegistry.registerModelLayer(SPRAY_POISON_SPIDER_LAYER, SprayPoisonSpiderModel::getTexturedModelData);
                    // 注册弹射物毒素
                    EntityRendererRegistry.register(TOXIN, ToxinRenderer::new);
                    EntityModelLayerRegistry.registerModelLayer(TOXIN_LAYER, ToxinModel::getTexturedModelData);
                    ParticleFactoryRegistry.getInstance().register(TOXIN_PARTICLE, SpitParticle.Factory::new);
                    EntityRendererRegistry.register(WEAVING_WEB_SPIDER, WeavingWebSpiderRenderer::new);
                    EntityModelLayerRegistry.registerModelLayer(WEAVING_WEB_SPIDER_LAYER, WeavingWebSpiderModel::getTexturedModelData);
                }
            }

            private static void registerItems() {
                // 注册蜘蛛腿
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "spider_leg"), SPIDER_LEG);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.FOOD).register(content -> content.add(SPIDER_LEG));
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(SPIDER_LEG));
                // 注册蜘蛛护皮
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "spider_leather"), SPIDER_LEATHER);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(SPIDER_LEATHER));
                // 注册蜘蛛毒牙
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "spider_fang"), SPIDER_FANG);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(SPIDER_FANG));
                // 注册致密蛛丝
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "compact_gossamer"), COMPACT_GOSSAMER);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(COMPACT_GOSSAMER));
                // 注册黏密蛛丝
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "sticky_compact_gossamer"), STICKY_COMPACT_GOSSAMER);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(STICKY_COMPACT_GOSSAMER));
                // 注册去毒蜘蛛腿
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "depoison_spider_leg"), DEPOISON_SPIDER_LEG);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.FOOD).register(content -> content.add(DEPOISON_SPIDER_LEG));
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(DEPOISON_SPIDER_LEG));
                // 注册致密丝线
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "compact_string"), COMPACT_STRING);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(COMPACT_STRING));
                // 注册复合丝线
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "composite_string"), COMPOSITE_STRING);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(COMPOSITE_STRING));
                // 注册复合面料
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "composite_fabric"), COMPOSITE_FABRIC);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.INGREDIENTS).register(content -> content.add(COMPOSITE_FABRIC));
            }

            private static void registerEquipment() {
                // 注册蜘蛛皮帽
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "spider_leather_cap"), SPIDER_LEATHER_CAP);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(SPIDER_LEATHER_CAP));
                // 注册蜘蛛皮甲
                Registry.register(Registries.ITEM, new Identifier(SR_ID, "spider_leather_tunic"), SPIDER_LEATHER_TUNIC);
                ItemGroupEvents.modifyEntriesEvent(SRItemGroups.EQUIPMENT).register(content -> content.add(SPIDER_LEATHER_TUNIC));

                // 注册装备装甲音效
                Registry.register(Registries.SOUND_EVENT, EQUIP_SPIDER_LEATHER_ARMOR_ID, EQUIP_SPIDER_LEATHER_ARMOR);
            }
        }
    }
}
