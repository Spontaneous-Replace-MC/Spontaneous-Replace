package SpontaneousReplace.SpiderBiome.Items;

import SpontaneousReplace.Generic.SRItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static SpontaneousReplace.Generic.SRData.MOD_ID;
import static SpontaneousReplace.Generic.SRData.getTick;

/**
 * <b style="color:FFC800"><font size="+2">Drops：蜘蛛生物掉落物</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加蜘蛛生物掉落物</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/3/25 15:54
 */
public abstract class Drops {
    /**
     * <p>蜘蛛腿食物参数：</p>
     * <p>饱食度：4</p>
     * <p>100% 中毒 Ⅱ 10s</p>
     * <p>是肉</p>
     */
    public static final FoodComponent SPIDER_LEG_FC = (new FoodComponent.Builder())
            .hunger(4)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, getTick(10), 1), 1)
            .meat()
            .build();
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

    public static void register() {
        // 注册蜘蛛腿
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "spider_leg"), SPIDER_LEG);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.FOOD).register(content -> content.add(SPIDER_LEG));
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(SPIDER_LEG));
        // 注册蜘蛛护皮
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "spider_leather"), SPIDER_LEATHER);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(SPIDER_LEATHER));
        // 注册蜘蛛毒牙
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "spider_fang"), SPIDER_FANG);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(SPIDER_FANG));
        // 注册致密蛛丝
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "compact_gossamer"), COMPACT_GOSSAMER);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(COMPACT_GOSSAMER));

        // 注册黏密蛛丝
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "sticky_compact_gossamer"), STICKY_COMPACT_GOSSAMER);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(STICKY_COMPACT_GOSSAMER));
    }
}
