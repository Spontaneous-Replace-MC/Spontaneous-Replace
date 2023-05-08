package SpontaneousReplace.Generic;

import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;

import java.util.List;
import java.util.Optional;

/**
 * <b style="color:FFC800"><font size="+2">SRMobSpawn：自然更替生物生成</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">控制自然更替生物的特殊生成方式</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/3/11 22:12
 */
public abstract class SRMobSpawn {
    /**
     * 禁止实例化 SRMobSpawn 生物生成类
     */
    private SRMobSpawn() {
        throw new Error("请检查是否实例化 SRMobSpawn 生物生成类");
    }

    /**
     * 是否只在夜晚生成
     *
     * @param entity 生成实体
     * @return 是否生成实体
     */
    public static boolean onlySpawnInNight(Entity entity) {
        return entity.getEntityWorld().isNight();
    }

    /**
     * 是否只在白天生成
     *
     * @param entity 生成实体
     * @return 是否生成实体
     */
    public static boolean onlySpawnInDay(Entity entity) {
        return entity.getEntityWorld().isDay();
    }

    /**
     * 如果不在生物群系则只在夜晚生成
     *
     * @param world     世界访问
     * @param entity    生成实体
     * @param biomeList 生物群系判断列表
     * @return 是否生成实体
     */
    public static boolean ifNotBiomeOnlySpawnInNight(WorldAccess world, Entity entity, List<RegistryKey<Biome>> biomeList) {
        Optional<RegistryKey<Biome>> spawnBiomeKey = world.getBiome(entity.getBlockPos()).getKey();
        if (spawnBiomeKey.isPresent()) {
            if (entity.getEntityWorld().isDay())
                return biomeList.contains(spawnBiomeKey.get());
            else return true;
        }
        return false;
    }

    /**
     * 如果不在生物群系则只在白天生成
     *
     * @param world     世界访问
     * @param entity    生成实体
     * @param biomeList 生物群系判断列表
     * @return 是否生成实体
     */
    public static boolean ifNotBiomeOnlySpawnInDay(WorldAccess world, Entity entity, List<RegistryKey<Biome>> biomeList) {
        Optional<RegistryKey<Biome>> spawnBiomeKey = world.getBiome(entity.getBlockPos()).getKey();
        if (spawnBiomeKey.isPresent()) {
            if (entity.getEntityWorld().isNight())
                return biomeList.contains(spawnBiomeKey.get());
            else return true;
        }
        return false;
    }

    /**
     * 如果在生物群系则只在夜晚生成
     *
     * @param world     世界访问
     * @param entity    生成实体
     * @param biomeList 生物群系判断列表
     * @return 是否生成实体
     */
    public static boolean ifInBiomeOnlySpawnInNight(WorldAccess world, Entity entity, List<RegistryKey<Biome>> biomeList) {
        Optional<RegistryKey<Biome>> spawnBiomeKey = world.getBiome(entity.getBlockPos()).getKey();
        if (spawnBiomeKey.isPresent()) {
            if (entity.getEntityWorld().isDay())
                return !biomeList.contains(spawnBiomeKey.get());
            else return true;
        }
        return false;
    }

    /**
     * 如果在生物群系则只在白天生成
     *
     * @param world     世界访问
     * @param entity    生成实体
     * @param biomeList 生物群系判断列表
     * @return 是否生成实体
     */
    public static boolean ifInBiomeOnlySpawnInDay(WorldAccess world, Entity entity, List<RegistryKey<Biome>> biomeList) {
        Optional<RegistryKey<Biome>> spawnBiomeKey = world.getBiome(entity.getBlockPos()).getKey();
        if (spawnBiomeKey.isPresent()) {
            if (entity.getEntityWorld().isNight())
                return !biomeList.contains(spawnBiomeKey.get());
            else return true;
        }
        return false;
    }

    /**
     * 是否只在特殊时间段生成
     *
     * @param entity  生成实体
     * @param minTime 最小时间，游戏时间刻
     * @param maxTime 最大时间，游戏时间刻
     * @return 是否生成实体
     */
    public static boolean onlySpawnInTime(Entity entity, int minTime, int maxTime) {
        long time = entity.getEntityWorld().getLunarTime();
        return time > minTime || time < maxTime;
    }

    /**
     * 如果不在生物群系则只在特殊时间段生成
     *
     * @param world     世界访问
     * @param entity    生成实体
     * @param biomeList 生物群系判断列表
     * @param minTime   最小时间，游戏时间刻
     * @param maxTime   最大时间，游戏时间刻
     * @return 是否生成实体
     */
    public static boolean ifNotBiomeOnlySpawnInTime(WorldAccess world, Entity entity, List<RegistryKey<Biome>> biomeList, int minTime, int maxTime) {
        long time = entity.getEntityWorld().getLunarTime();
        Optional<RegistryKey<Biome>> spawnBiomeKey = world.getBiome(entity.getBlockPos()).getKey();
        if (spawnBiomeKey.isPresent()) {
            if (!(time > minTime || time < maxTime))
                return biomeList.contains(spawnBiomeKey.get());
            else return true;
        }
        return false;
    }

    /**
     * 如果在生物群系则只在特殊时间段生成
     *
     * @param world     世界访问
     * @param entity    生成实体
     * @param biomeList 生物群系判断列表
     * @param minTime   最小时间，游戏时间刻
     * @param maxTime   最大时间，游戏时间刻
     * @return 是否生成实体
     */
    public static boolean ifInBiomeOnlySpawnInTime(WorldAccess world, Entity entity, List<RegistryKey<Biome>> biomeList, int minTime, int maxTime) {
        long time = entity.getEntityWorld().getLunarTime();
        Optional<RegistryKey<Biome>> spawnBiomeKey = world.getBiome(entity.getBlockPos()).getKey();
        if (spawnBiomeKey.isPresent()) {
            if (!(time > minTime || time < maxTime))
                return !biomeList.contains(spawnBiomeKey.get());
            else return true;
        }
        return false;
    }
}

