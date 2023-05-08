package SpontaneousReplace.VanillaExtension.RangedRelated.EnhancedWeapon.FullPowerSteelArrow;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.world.World;

/**
 * <b style="color:FFC800"><font size="+2">Item：全威力钢箭</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加一种超大威力以及超高精度的箭矢</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/5/1 21:04
 */
public class Item extends net.minecraft.item.Item {
    /**
     * 构建全威力钢箭
     */
    public Item(Settings settings) {
        super(settings);
    }

    /**
     * 创建箭矢
     */
    public PersistentProjectileEntity createArrow(World world, LivingEntity shooter) {
        return new Entity(world, shooter);
    }
}
