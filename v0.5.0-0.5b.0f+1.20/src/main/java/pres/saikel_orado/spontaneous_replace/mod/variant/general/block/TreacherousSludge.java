package pres.saikel_orado.spontaneous_replace.mod.variant.general.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * <b style="color:FFC800"><font size="+2">TreacherousSludge：诡谲污泥</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">一种被感染的装饰性泥土类方块</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/19 20:27
 */
@SuppressWarnings("deprecation")
public class TreacherousSludge extends Block {
    public TreacherousSludge(Settings settings) {
        super(settings);
    }

    /**
     * 设置可放置树苗
     */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();
        if (hit.getSide() == Direction.UP && stack.isIn(ItemTags.SAPLINGS)) {
            world.playSound(null, pos, SoundEvents.ITEM_CROP_PLANT, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos.up(), ((BlockItem) item).getBlock().getDefaultState());
            if (!world.isClient) {
                if (!player.isCreative()) stack.decrement(1);
            }

            player.incrementStat(Stats.USED.getOrCreateStat(item));
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
