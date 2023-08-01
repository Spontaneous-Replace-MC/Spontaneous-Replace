package pres.saikel_orado.spontaneous_replace.mod.data;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.WorldCreator;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.apache.commons.lang3.StringUtils;
import pres.saikel_orado.spontaneous_replace.mod.screen.ConfigScreen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import static net.minecraft.util.Util.OperatingSystem.WINDOWS;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.*;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRUpdateUtil.readShowChangelog;
import static pres.saikel_orado.spontaneous_replace.mod.util.SRUpdateUtil.writeShowChangelog;

/**
 * <b style="color:FFC800"><font size="+2">SRConfigData：自然更替配置数据</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">负责模组所有的配置数据</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 7.0
 * | 创建于 2023/2/15 11:02
 */
public final class SRConfigData {
    /**
     * 检查是否为中文
     */
    public static boolean isZhCN = false;

    /**
     * 获取当前语言
     */
    public static String language = "en_us";

    /**
     * 创建世界屏幕数据
     */
    public final static class CreateWorldScreenData {
        /**
         * 世界配置父屏幕
         */
        public static Screen worldConfigScreen = null;
        /**
         * 世界创造器
         */
        public static WorldCreator worldCreator = null;
        /**
         * 自定义类型
         */
        public static ButtonWidget WorldCustomize = null;

        private CreateWorldScreenData() throws ExceptionInInitializerError {
            throw new ExceptionInInitializerError();
        }
    }

    /**
     * 开发者配置数据
     */
    public final static class DevConfig {
        private DevConfig() throws ExceptionInInitializerError {
            throw new ExceptionInInitializerError();
        }

        /**
         * 配置选项文本
         */
        public static final String[] CONFIG_TEXT = new String[]{
                "DisableModButton"
        };

        public static boolean disableModButton = false;

        /**
         * 所有配置集合
         */
        @SuppressWarnings("ConstantValue")
        public static final boolean[] CONFIG_OPTIONS = new boolean[]{disableModButton};
    }

    /**
     * 配置界面按钮数据
     */
    public final static class Config {
        public static final int CONFIG_BUTTON_INDEX = 0;
        public static final int CONFIGS_START = 1;
        public static final int UPDATE_CONFIGS_INDEX = 4;
        private static Screen parent = null;
        public static boolean autoShowConfigButton = false;
        public static boolean modSwitch = true;
        public static boolean temporarySRAdventure = true;
        public static boolean disableWarningAdvice = false;

        /**
         * 设置父屏幕
         */
        public static void setParent(Screen screen) {
            parent = screen;
        }

        /**
         * 获取父屏幕
         */
        public static Screen getParent() {
            return parent;
        }

        /**
         * 更新配置数据类
         */
        public final static class Update {
            public static final String UPDATE_CONFIG_BUTTON_TEXT = getModConfigText("update_config_button");
            /**
             * 是否开启更新提示
             */
            public static boolean updateNotification = true;
            /**
             * 是否显示更新日志
             */
            public static boolean showChangelog = true;
            /**
             * 是否检查新 MC 版本模组更新
             */
            public static boolean checkNewMCVersionMod = true;
            /**
             * 是否关闭停止更新警告
             */
            public static boolean stopUpdatingWarning = true;
            /**
             * 是否关闭更新系统失效警告
             */
            public static boolean updateSystemFailWarning = true;
            /**
             * 选择更新方式：手动下载、自动下载、自动更新
             */
            public static int updateMode = 0;
            /**
             * 选择更新通道：正式版、测试版、开发版
             */
            public static int updateChannel = 0;

            private Update() throws ExceptionInInitializerError {
                throw new ExceptionInInitializerError();
            }
        }

        private Config() throws ExceptionInInitializerError {
            throw new ExceptionInInitializerError();
        }
    }

    /**
     * 配置文件
     */
    private static final Path CONFIG_FILE = Path.of(FabricLoader.getInstance().getConfigDir() + FileSystems.getDefault().getSeparator() + "SpontaneousReplace.config");

    /**
     * 所有配置选项集合
     */
    public static final SimpleOption<?>[][] CONFIG_OPTIONS = new SimpleOption[][]{
            // 自动显示配置按钮
            {SimpleOption.ofBoolean(
                    getModConfigText("auto_show_config_button"),
                    (value) -> Tooltip.of(Text.translatable(getModConfigTip("auto_show_config_button." + (value ? "on" : "off")))),
                    Config.autoShowConfigButton, (value) -> {
                        Config.autoShowConfigButton = value;
                        writeConfig();
                    })},
            // 模组内容开关
            {SimpleOption.ofBoolean(
                    getModConfigText("mod_switch"),
                    (value) -> Tooltip.of(Text.translatable(getModConfigTip("mod_switch." + (value ? "on" : "off")))),
                    Config.modSwitch, (value) -> {
                        Config.modSwitch = value;
                        writeConfig();
                    })},
            // 临时《自然更替》冒险
            {SimpleOption.ofBoolean(
                    getModConfigText("temporary_sr_adventure"),
                    (value) -> Tooltip.of(Text.translatable(getModConfigTip("temporary_sr_adventure." + (value ? "on" : "off")))),
                    Config.temporarySRAdventure, (value) -> {
                        Config.temporarySRAdventure = value;
                        writeConfig();
                    })},
            // 禁用“实验性设置”警告
            {SimpleOption.ofBoolean(
                    getModConfigText("disable_warning_advice"),
                    (value) -> Tooltip.of(Text.translatable(getModConfigTip("disable_warning_advice." + (value ? "on" : "off")))),
                    Config.disableWarningAdvice, (value) -> {
                        Config.disableWarningAdvice = value;
                        writeConfig();
                    })},
            // 更新选项界面按钮
            {
                    SimpleOption.ofBoolean(
                            getModConfigText("update_config_button"),
                            SimpleOption.constantTooltip(Text.of("")),
                            (optionText, value) -> Text.of(""),
                            false,
                            (value) -> MinecraftClient.getInstance().setScreen(new ConfigScreen.Update(Config.parent))),
                    SimpleOption.ofBoolean(
                            getModConfigText("update_notification"),
                            (value) -> Tooltip.of(Text.translatable(getModConfigTip("update_notification." + (value ? "on" : "off")))),
                            Config.Update.updateNotification, (value) -> {
                                Config.Update.updateNotification = value;
                                writeConfig();
                            }),
                    SimpleOption.ofBoolean(
                            getModConfigText("show_changelog"),
                            (value) -> Tooltip.of(Text.translatable(getModConfigTip("show_changelog." + (value ? "on" : "off")))),
                            Config.Update.showChangelog, (value) -> {
                                Config.Update.showChangelog = value;
                                writeConfig();
                            }),
                    SimpleOption.ofBoolean(
                            getModConfigText("check_new_mc_version_mod"),
                            (value) -> Tooltip.of(Text.translatable(getModConfigTip("check_new_mc_version_mod." + (value ? "on" : "off")))),
                            Config.Update.checkNewMCVersionMod, (value) -> {
                                Config.Update.checkNewMCVersionMod = value;
                                writeConfig();
                            }),
                    SimpleOption.ofBoolean(
                            getModConfigText("stop_updating_warning"),
                            (value) -> Tooltip.of(Text.translatable(getModConfigTip("stop_updating_warning." + (value ? "on" : "off")))),
                            Config.Update.stopUpdatingWarning, (value) -> {
                                Config.Update.stopUpdatingWarning = value;
                                writeConfig();
                            }),
                    SimpleOption.ofBoolean(
                            getModConfigText("update_system_fail_warning"),
                            (value) -> Tooltip.of(Text.translatable(getModConfigTip("update_system_fail_warning." + (value ? "on" : "off")))),
                            Config.Update.updateSystemFailWarning, (value) -> {
                                Config.Update.updateSystemFailWarning = value;
                                writeConfig();
                            }),
                    new SimpleOption<>(
                            getModConfigText("update_mode"),
                            (value) -> switch (value) {
                                case 0 -> Tooltip.of(Text.translatable(getModConfigTip("update_mode.manual_download")));
                                case 1 -> Tooltip.of(Text.translatable(getModConfigTip("update_mode.auto_download")));
                                case 2 -> Tooltip.of(Text.translatable(getModConfigTip("update_mode.auto_update")));
                                default -> Tooltip.of(Text.of(""));
                            },
                            (optionText, value) -> switch (value) {
                                case 0 -> Text.translatable(getModConfigText("update_mode.manual_download"));
                                case 1 -> Text.translatable(getModConfigText("update_mode.auto_download"));
                                case 2 -> Text.translatable(getModConfigText("update_mode.auto_update"));
                                default -> Text.literal(Integer.toString(value));
                            },
                            new SimpleOption.MaxSuppliableIntCallbacks(Config.Update.updateMode, () -> Util.getOperatingSystem().equals(WINDOWS) ? 2 : 1, 2),
                            Config.Update.updateMode,
                            (value) -> {
                                Config.Update.updateMode = value;
                                writeConfig();
                            }),
                    new SimpleOption<>(
                            getModConfigText("update_channel"),
                            (value) -> switch (value) {
                                case 0 -> Tooltip.of(Text.translatable(getModConfigTip("update_channel.release")));
                                case 1 -> Tooltip.of(Text.translatable(getModConfigTip("update_channel.beta")));
                                case 2 -> Tooltip.of(Text.translatable(getModConfigTip("update_channel.alpha")));
                                default -> Tooltip.of(Text.of(""));
                            },
                            (optionText, value) -> switch (value) {
                                case 0 -> Text.translatable(getModConfigText("update_channel.release"));
                                case 1 -> Text.translatable(getModConfigText("update_channel.beta"));
                                case 2 -> Text.translatable(getModConfigText("update_channel.alpha"));
                                default -> Text.literal(Integer.toString(value));
                            },
                            new SimpleOption.MaxSuppliableIntCallbacks(Config.Update.updateChannel, () -> 2, 2),
                            Config.Update.updateChannel,
                            (value) -> {
                                Config.Update.updateChannel = value;
                                writeConfig();
                            })}
    };
    /**
     * 配置选项文本
     */
    public static final String[][] CONFIG_OPTIONS_TEXT = new String[][]{
            {"AutoShowConfigButton"},
            {"ModSwitch"},
            {"TemporarySRAdventure"},
            {"DisableWarningAdvice"},
            {"***Update**", "UpdateNotification", "ShowChangelog", "CheckNewMCVersionMod", "StopUpdatingWarning", "UpdateSystemFailWarning", "UpdateMode", "UpdateChannel"}
    };

    /**
     * 判断是否在使用中文
     */
    public static boolean isChinese() {
        return "zh_cn".equals(language) || "zh_hk".equals(language) || "zh_tw".equals(language);
    }

    /**
     * 获取“支持我们”按钮
     *
     * @param screen 需要添加的屏幕
     * @return “支持我们”按钮
     */
    public static ButtonWidget getSupportUsButton(Screen screen) {
        return ButtonWidget.builder(Text.translatable(getModConfigText("support_us")),
                        SRConfigData.isZhCN ?
                                ConfirmLinkScreen.opening(SR_SUPPORT_WEB_OF_CN, screen, true)
                                : ConfirmLinkScreen.opening(SR_SUPPORT_WEB, screen, true))
                .dimensions(screen.width - 75, 6, 70, 20).build();
    }

    /**
     * 写入注释与配置文件版本
     *
     * @param printWriter 写入文件
     */
    public static void writeCommentAndVersion(PrintWriter printWriter) {
        printWriter.println("# !WARNING! The typesetting of the configuration cannot be modified, and you can only modify the content after the colon. All options are included in the configuration");
        printWriter.println("# ！警告！配置的排版不可修改，您只能修改冒号后的选项。配置中会包含所有的选项");
        printWriter.println();
        printWriter.println("ConfigVersion: 2");
        printWriter.println();
    }

    /**
     * 跳过注释并返回配置文件版本
     *
     * @param bufferedReader 配置文件缓存读取
     * @return 返回配置版本信息
     * @throws IOException 解决警告
     */
    public static int skipCommentReturnVersion(BufferedReader bufferedReader) throws IOException {
        // 跳过注释
        bufferedReader.readLine();
        bufferedReader.readLine();
        bufferedReader.readLine();
        // 读取版本信息
        int optionName = bufferedReader.read();
        while (optionName != ':' && optionName != -1)
            optionName = bufferedReader.read();
        // 如果不是数字则返回 0
        String str = bufferedReader.readLine().replaceAll(" ", "");
        if (!StringUtils.isNumeric(str))
            return 0;
        int version = Integer.parseInt(str);
        // 跳过空行
        bufferedReader.readLine();

        return version;
    }

    /**
     * 保存模组配置文件
     */
    public static void writeConfig() {
        // 打开并截断配置文件数据
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(CONFIG_FILE, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            // 写入模组配置
            // 写入注释与版本信息
            writeCommentAndVersion(printWriter);
            // 写入玩家配置项目
            printWriter.println("----- Gamer Config -----");
            for (int arr = 0; arr < CONFIG_OPTIONS.length; arr++) {
                for (int count = CONFIG_OPTIONS[arr].length > 1 ? 1 : 0;
                     count < CONFIG_OPTIONS[arr].length; count++) {
                    printWriter.print(CONFIG_OPTIONS_TEXT[arr][count]);
                    printWriter.print(": ");// 一个一个选项赋值
                    switch (arr) {
                        case 0 -> {
                            if (count == 0) printWriter.println(Config.autoShowConfigButton);
                            else printWriter.println();
                        }
                        case 1 -> {
                            if (count == 0) printWriter.println(Config.modSwitch);
                            else printWriter.println();
                        }
                        case 2 -> {
                            if (count == 0) printWriter.println(Config.temporarySRAdventure);
                            else printWriter.println();
                        }
                        case 3 -> {
                            if (count == 0) printWriter.println(Config.disableWarningAdvice);
                            else printWriter.println();
                        }
                        case 4 -> {
                            switch (count) {
                                case 1 -> printWriter.println(Config.Update.updateNotification);
                                case 2 -> printWriter.println(Config.Update.showChangelog);
                                case 3 -> printWriter.println(Config.Update.checkNewMCVersionMod);
                                case 4 -> printWriter.println(Config.Update.stopUpdatingWarning);
                                case 5 -> printWriter.println(Config.Update.updateSystemFailWarning);
                                case 6 -> printWriter.println(Config.Update.updateMode);
                                case 7 -> printWriter.println(Config.Update.updateChannel);
                                default -> printWriter.println();
                            }
                        }
                        default -> printWriter.println();
                    }
                }
            }
            printWriter.println();
            // 写入开发者配置项目
            printWriter.println("----- Developer Config -----");
            for (int count = 0; count < DevConfig.CONFIG_OPTIONS.length; count++) {
                printWriter.print(DevConfig.CONFIG_TEXT[count]);
                printWriter.print(':');
                printWriter.println(DevConfig.CONFIG_OPTIONS[count]);
            }
            printWriter.println();
            // 写入模组数据项目
            printWriter.println("----- Mod GuardSpiderData -----");
            writeShowChangelog(printWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取模组配置文件
     */
    public static void readConfig() {
        Path parent = FabricLoader.getInstance().getConfigDir();
        // 遍历 config 文件夹查找是否已经存在配置文件
        try (DirectoryStream<Path> children = Files.newDirectoryStream(parent)) {
            boolean hasFile = false;
            // 遍历 config 文件夹查询
            for (Path child : children) {
                if (child.equals(CONFIG_FILE)) {
                    hasFile = true;
                    break;
                }
            }
            // 如果已经存在配置文件则读取模组配置
            if (hasFile) {
                BufferedReader bufferedReader = Files.newBufferedReader(CONFIG_FILE, StandardCharsets.UTF_8);
                // 读取配置文件版本
                //noinspection SwitchStatementWithTooFewBranches
                switch (skipCommentReturnVersion(bufferedReader)) {
                    case 2 -> readVersion2(bufferedReader);
                    default -> writeConfig();
                }
                // 没有则创建一个默认配置文件
            } else {
                Files.createFile(CONFIG_FILE);
                writeConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取 2.0 的版本模组配置文件
     *
     * @param bufferedReader 配置文件缓存读取
     * @throws IOException 解决警告
     */
    @SuppressWarnings("unchecked")
    public static void readVersion2(BufferedReader bufferedReader) throws IOException {
        // 读取玩家配置数据
        bufferedReader.readLine();
        for (int arr = 0; arr < CONFIG_OPTIONS.length; arr++) {
            for (int count = CONFIG_OPTIONS[arr].length > 1 ? 1 : 0;
                 count < CONFIG_OPTIONS[arr].length; count++) {
                // 跳过配置选项名
                int optionName = bufferedReader.read();
                while (optionName != ':' && optionName != '\n')
                    optionName = bufferedReader.read();
                if (optionName == '\n')
                    break;
                // 一个一个选项赋值
                switch (arr) {
                    case 0 -> {
                        if (count == 0) {
                            ((SimpleOption<Boolean>) CONFIG_OPTIONS[arr][count])
                                    .setValue(Config.autoShowConfigButton = Boolean.parseBoolean(bufferedReader.readLine().replaceAll(" ", "")));
                        } else bufferedReader.readLine();
                    }
                    case 1 -> {
                        if (count == 0) {
                            ((SimpleOption<Boolean>) CONFIG_OPTIONS[arr][count])
                                    .setValue(Config.modSwitch = Boolean.parseBoolean(bufferedReader.readLine().replaceAll(" ", "")));
                        } else bufferedReader.readLine();
                    }
                    case 2 -> {
                        if (count == 0) {
                            ((SimpleOption<Boolean>) CONFIG_OPTIONS[arr][count])
                                    .setValue(Config.temporarySRAdventure = Boolean.parseBoolean(bufferedReader.readLine().replaceAll(" ", "")));
                        } else bufferedReader.readLine();
                    }
                    case 3 -> {
                        if (count == 0) {
                            ((SimpleOption<Boolean>) CONFIG_OPTIONS[arr][count])
                                    .setValue(Config.disableWarningAdvice = Boolean.parseBoolean(bufferedReader.readLine().replaceAll(" ", "")));
                        } else bufferedReader.readLine();
                    }
                    case 4 -> {
                        switch (count) {
                            case 1 -> ((SimpleOption<Boolean>) CONFIG_OPTIONS[arr][count])
                                    .setValue(Config.Update.updateNotification = Boolean.parseBoolean(bufferedReader.readLine().replaceAll(" ", "")));
                            case 2 -> ((SimpleOption<Boolean>) CONFIG_OPTIONS[arr][count])
                                    .setValue(Config.Update.showChangelog = Boolean.parseBoolean(bufferedReader.readLine().replaceAll(" ", "")));
                            case 3 -> ((SimpleOption<Boolean>) CONFIG_OPTIONS[arr][count])
                                    .setValue(Config.Update.checkNewMCVersionMod = Boolean.parseBoolean(bufferedReader.readLine().replaceAll(" ", "")));
                            case 4 -> ((SimpleOption<Boolean>) CONFIG_OPTIONS[arr][count])
                                    .setValue(Config.Update.stopUpdatingWarning = Boolean.parseBoolean(bufferedReader.readLine().replaceAll(" ", "")));
                            case 5 -> ((SimpleOption<Boolean>) CONFIG_OPTIONS[arr][count])
                                    .setValue(Config.Update.updateSystemFailWarning = Boolean.parseBoolean(bufferedReader.readLine().replaceAll(" ", "")));
                            case 6 -> ((SimpleOption<Integer>) CONFIG_OPTIONS[arr][count])
                                    .setValue(Config.Update.updateMode = Integer.parseInt(bufferedReader.readLine().replaceAll(" ", "")));
                            case 7 -> ((SimpleOption<Integer>) CONFIG_OPTIONS[arr][count])
                                    .setValue(Config.Update.updateChannel = Integer.parseInt(bufferedReader.readLine().replaceAll(" ", "")));
                            default -> bufferedReader.readLine();
                        }
                    }
                    default -> bufferedReader.readLine();
                }
            }
        }
        // 读取开发者配置数据
        bufferedReader.readLine();
        for (int count = 0; count < DevConfig.CONFIG_OPTIONS.length; count++) {
            // 跳过配置选项名
            int optionName = bufferedReader.read();
            while (optionName != ':' && optionName != -1)
                optionName = bufferedReader.read();
            if (optionName == -1)
                break;
            // 一个一个选项赋值
            //noinspection SwitchStatementWithTooFewBranches
            switch (count) {
                //noinspection DataFlowIssue
                case 0 ->
                        DevConfig.CONFIG_OPTIONS[0] = DevConfig.disableModButton = Boolean.parseBoolean(bufferedReader.readLine().replaceAll(" ", ""));
                default -> bufferedReader.readLine();
            }
        }
        // 读取模组数据
        bufferedReader.readLine();
        readShowChangelog(bufferedReader);
    }


    /**
     * 获取模组配置文本翻译键
     *
     * @param key 翻译键
     * @return 模组配置文本翻译键
     */
    public static String getModConfigText(String key) {
        return "setting_text." + SR_ID + '.' + key;
    }

    /**
     * 获取模组配置提示翻译键
     *
     * @param key 翻译键
     * @return 模组配置提示翻译键
     */
    public static String getModConfigTip(String key) {
        return "setting_tooltip." + SR_ID + '.' + key;
    }

    private SRConfigData() throws ExceptionInInitializerError {
        throw new ExceptionInInitializerError();
    }
}
