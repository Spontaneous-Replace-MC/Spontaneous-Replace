package SpontaneousReplace.SpiderBiome;

import SpontaneousReplace.Generic.ConfigData;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.tag.BiomeTags;

import static SpontaneousReplace.SpiderBiome.Generic.SpiderData.SPIDER_BIOMES;
import static SpontaneousReplace.SpiderBiome.Mobs.GuardSpider.Server.GUARD_SPIDER;
import static SpontaneousReplace.SpiderBiome.Mobs.SprayPoisonSpider.Server.SPRAY_POISON_SPIDER;
import static SpontaneousReplace.SpiderBiome.Mobs.WeavingWebSpider.Server.WEAVING_WEB_SPIDER;

/**
 * <b style="color:FFC800"><font size="+2">temporary_spider_spawn：临时蜘蛛生成</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">用于创造临时的“蜘蛛生物群系”</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2023/2/9 19:28
 */
public class temporary_spider_spawn {
    public static void register() {
        if (ConfigData.Config.modSwitch)
            if (ConfigData.Config.temporarySRAdventure) {
                //海洋、蘑菇岛和远古城市不应该出现蜘蛛
                BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld()
                                .and(BiomeSelectors.tag(BiomeTags.IS_OCEAN).negate())
                                .and(BiomeSelectors.tag(BiomeTags.WITHOUT_PATROL_SPAWNS).negate())
                                .and(BiomeSelectors.tag(BiomeTags.ANCIENT_CITY_HAS_STRUCTURE).negate())
                                .and(BiomeSelectors.excludeByKey(SPIDER_BIOMES)),
                        SpawnGroup.MONSTER, GUARD_SPIDER, 5, 1, 2);
                BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld()
                                .and(BiomeSelectors.tag(BiomeTags.IS_OCEAN).negate())
                                .and(BiomeSelectors.tag(BiomeTags.WITHOUT_PATROL_SPAWNS).negate())
                                .and(BiomeSelectors.tag(BiomeTags.ANCIENT_CITY_HAS_STRUCTURE).negate())
                                .and(BiomeSelectors.excludeByKey(SPIDER_BIOMES)),
                        SpawnGroup.MONSTER, SPRAY_POISON_SPIDER, 5, 1, 2);
                BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld()
                                .and(BiomeSelectors.tag(BiomeTags.IS_OCEAN).negate())
                                .and(BiomeSelectors.tag(BiomeTags.WITHOUT_PATROL_SPAWNS).negate())
                                .and(BiomeSelectors.tag(BiomeTags.ANCIENT_CITY_HAS_STRUCTURE).negate())
                                .and(BiomeSelectors.excludeByKey(SPIDER_BIOMES)),
                        SpawnGroup.MONSTER, WEAVING_WEB_SPIDER, 5, 1, 2);
            }
    }
}

