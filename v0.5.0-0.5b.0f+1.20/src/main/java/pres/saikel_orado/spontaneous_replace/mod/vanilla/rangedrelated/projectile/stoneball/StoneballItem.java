package pres.saikel_orado.spontaneous_replace.mod.vanilla.rangedrelated.projectile.stoneball;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Vanilla.RangedRelated.STONEBALL_THROW;

/**
 * <b style="color:FFC800"><font size="+2">StoneballItem：石弹</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加一种新型的特殊抛掷物品</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 3.0
 * | 创建于 2023/4/24 22:17
 */
public class StoneballItem extends Item {
    /**
     * 构建石弹
     */
    public StoneballItem(Settings settings) {
        super(settings);
    }

    /**
     * 设置使用操作
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        // 播放音效
        world.playSound(null, user.getX(), user.getY(), user.getZ(), STONEBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            // 生成实体
            StoneballEntity entity = new StoneballEntity(world, user);
            entity.setItem(itemStack);
            entity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(entity);
        }

        // 创建事件计数
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient);
    }
}
