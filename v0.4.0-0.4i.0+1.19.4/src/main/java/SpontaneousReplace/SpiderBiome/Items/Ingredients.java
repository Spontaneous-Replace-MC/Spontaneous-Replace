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

import static SpontaneousReplace.Generic.SRData.*;

/**
 * <b style="color:FFC800"><font size="+2">Ingredients：蜘蛛群系原材料</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加蜘蛛群系原材料</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 2.0
 * | 创建于 2023/3/26 15:40
 */
public abstract class Ingredients {
    /**
     * <p>去毒蜘蛛腿食物参数：</p>
     * <p>饱食度：5</p>
     * <p>饱和度：3</p>
     * <p>25% 中毒 Ⅰ 5s</p>
     * <p>是肉</p>
     */
    public static final FoodComponent DEPOISON_SPIDER_LEG_FC = (new FoodComponent.Builder())
            .hunger(5)
            .saturationModifier(getSaturationRatio(3))
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, getTick(5), 0), 0.25F)
            .meat()
            .build();
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

    public static void register() {
        // 注册去毒蜘蛛腿
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "depoison_spider_leg"), DEPOISON_SPIDER_LEG);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.FOOD).register(content -> content.add(DEPOISON_SPIDER_LEG));
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(DEPOISON_SPIDER_LEG));
        // 注册致密丝线
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "compact_string"), COMPACT_STRING);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(COMPACT_STRING));
        // 注册复合丝线
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "composite_string"), COMPOSITE_STRING);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(COMPOSITE_STRING));
        // 注册复合面料
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "composite_fabric"), COMPOSITE_FABRIC);
        ItemGroupEvents.modifyEntriesEvent(SRItemGroup.INGREDIENTS).register(content -> content.add(COMPOSITE_FABRIC));
    }
}
