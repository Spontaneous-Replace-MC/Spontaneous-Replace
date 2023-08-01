package pres.saikel_orado.spontaneous_replace.mod.data;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pres.saikel_orado.spontaneous_replace.mod.util.SRBow;
import pres.saikel_orado.spontaneous_replace.mod.util.SRCrossbow;

/**
 * <b style="color:FFC800"><font size="+2">SRData：自然更替数据</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">负责模组大部分内容可能用到的特殊数据</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 18.0
 * | 创建于 2022 /11/14 ~ 2022/12/8
 */
public final class SRData {
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static final String MC_VER = FabricLoader.getInstance().getModContainer("minecraft").get().getMetadata().getVersion().getFriendlyString();
    public static final String SR_ID = "spontaneous_replace";
    public static final String SR_SLUG = SR_ID.replace('_', '-');
    public static final Logger SR_LOGGER = LoggerFactory.getLogger(SR_ID);
    public static final int SR_THEME_COLOR = 0xFFC800;
    public static final boolean SR_UPDATING = true;
    public static final String SR_UPDATE_DATE = "2023-07-31T23:15:0.0Z";
    public static final String SR_OFFICIAL_WEB = "https://modrinth.com/mod/spontaneous-replace";
    public static final String SR_SUPPORT_WEB = "https://www.patreon.com/GameGeek_Saikel";
    public static final String SR_SUPPORT_WEB_OF_CN = "https://afdian.net/a/GameGeek_Saikel";
    public static final String SR_COMMUNITY_WEB = "https://discord.gg/ChRbMFgVw3";
    public static final Identifier SR_ICON_ID = new Identifier(SR_ID, "icon.png");
    public static final int TICK = 20;
    public static final float POS_SHIFTING = 0.5F;
    public static final float PROJECTILE_BOX = 0.25F;
    public static final int PROJECTILE_RANGE = 4;
    public static final int PROJECTILE_UPDATE_RATE = 20;

    /**
     * 玩家数据
     */
    @SuppressWarnings("unused")
    public final static class Player {
        public static final double WALK_SPEED = getVelocitySpeed(4.317);
        public static final double SPRINT_SPEED = getVelocitySpeed(5.612);
        public static final double SNEAK_SPEED = getVelocitySpeed(1.295);
        public static final double FLY_SPEED = getVelocitySpeed(10.89);
        public static final double SPRINT_FLY_SPEED = getVelocitySpeed(21.78);
        public static final double FALL_SPEED = getVelocitySpeed(78.4);

        public static final double SPEED_CONVERSION_RATIO = 3.05;

        /**
         * 获取 Velocity 速度
         *
         * @param metrePerSecond 米/秒
         * @return Velocity 速度
         */
        public static double getVelocitySpeed(double metrePerSecond) {
            return metrePerSecond * SPEED_CONVERSION_RATIO;
        }

        /**
         * 获取 Velocity 速度
         *
         * @param velocity Velocity 加速度参数
         * @return Velocity 速度
         */
        public static double getVelocitySpeed(Vec3d velocity) {
            return Math.sqrt(Math.pow(velocity.getX() * 100, 2) + Math.pow(velocity.getZ() * 100, 2));
        }

        /**
         * 获取米/秒
         *
         * @param velocitySpeed Velocity 速度
         * @return 米/秒
         */
        public static double getMetrePerSecond(double velocitySpeed) {
            return velocitySpeed * SPEED_CONVERSION_RATIO;
        }

        /**
         * 获取使用物品时的视场角缩放倍数
         *
         * @param player        玩家，使用者
         * @param fovMultiplier 原视场角倍数
         * @param bow           判断使用的弓
         * @param scaleMultiple 缩放倍数
         * @return 使用物品时的当前视场角缩放倍数
         */
        public static float getFovMultiplier(ClientPlayerEntity player, double fovMultiplier, SRBow bow, double scaleMultiple) {
            ItemStack stack = player.getActiveItem();
            double fovMulti = 1;
            if (player.isSprinting()) fovMulti = 1.15;
            if (stack.isOf(bow)) {
                return (float) (fovMultiplier - (fovMultiplier - scaleMultiple * fovMulti) * ((SRBow) stack.getItem()).getPullProgress(stack.getMaxUseTime() - player.getItemUseTimeLeft()));
            }
            return (float) fovMultiplier;
        }

        /**
         * 获取使用物品时的视场角缩放倍数
         *
         * @param player        玩家，使用者
         * @param fovMultiplier 原视场角倍数
         * @param crossbow      判断使用的弩
         * @param scaleMultiple 缩放倍数
         * @return 使用物品时的当前视场角缩放倍数
         */
        public static float getFovMultiplier(ClientPlayerEntity player, double fovMultiplier, SRCrossbow crossbow, double scaleMultiple) {
            ItemStack stack = player.getMainHandStack();
            if (!stack.isOf(crossbow)) stack = player.getOffHandStack();
            double fovMulti = 1;
            if (player.isSprinting()) fovMulti = 1.15;
            if (stack.isOf(crossbow) && SRCrossbow.isCharged(stack)) {
                return (float) (fovMultiplier - (fovMultiplier - scaleMultiple * fovMulti));
            }
            return (float) fovMultiplier;
        }

        private Player() throws ExceptionInInitializerError {
            throw new ExceptionInInitializerError();
        }
    }

    /**
     * 成就数据
     */
    public static final class Advancements {
        public static final String ARROWSTORM = "Arrowstorm";
        public static final String RIFLEMAN = "Rifleman";

        private Advancements() throws ExceptionInInitializerError {
            throw new ExceptionInInitializerError();
        }
    }

    private SRData() throws ExceptionInInitializerError {
        throw new ExceptionInInitializerError();
    }
}