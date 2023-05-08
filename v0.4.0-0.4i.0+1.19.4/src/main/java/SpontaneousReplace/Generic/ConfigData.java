package SpontaneousReplace.Generic;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.WorldCreator;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import static SpontaneousReplace.Generic.SRData.*;

/**
 * <b style="color:FFC800"><font size="+2">ConfigData：自然更替配置数据</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">负责模组所有的配置数据</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 4.0
 * | 创建于 2023/2/15 11:02
 */
public abstract class ConfigData {
    /**
     * 禁止实例化 ConfigData 配置数据类
     */
    private ConfigData() {
        throw new Error("请检查是否实例化 ConfigData 配置数据类");
    }

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
    public abstract static class CreateWorldScreenData {
        /**
         * 禁止实例化 CreateWorldScreenData 创建世界屏幕数据类
         */
        private CreateWorldScreenData() {
            throw new Error("请检查是否实例化 CreateWorldScreenData 创建世界屏幕数据类");
        }

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
    }

    /**
     * 开发者配置数据
     */
    public abstract static class DevConfig {
        /**
         * 禁止实例化 DevConfig 开发者配置数据类
         */
        private DevConfig() {
            throw new Error("请检查是否实例化 DevConfig 开发者配置数据类");
        }

        /**
         * 配置选项文本
         */
        public static final String[] CONFIG_TEXT = new String[]{
                "DisableModButton"
        };

        /**
         * 配置选项数量
         */
        public static final int OPTION_COUNT = CONFIG_TEXT.length;

        public static boolean disableModButton = false;

        /**
         * 所有配置集合
         */
        public static final boolean[] CONFIG_OPTIONS = new boolean[]{disableModButton};
    }

    /**
     * 配置界面按钮数据
     */
    public abstract static class Config {
        /**
         * 禁止实例化 Data 配置数据类
         */
        private Config() {
            throw new Error("请检查是否实例化 Data 配置数据类");
        }

        public static boolean autoShowConfigButton = false;
        public static boolean modSwitch = true;
        public static boolean temporarySRAdventure = true;
        public static boolean disableWarningAdvice = false;
    }

    /**
     * 配置文件
     */
    protected static final Path configFile = Paths.get("config" + FileSystems.getDefault().getSeparator() + "SpontaneousReplace.config");

    /**
     * 获取“支持我们”按钮
     *
     * @param screen 需要添加的屏幕
     * @return “支持我们”按钮
     */
    public static ButtonWidget getSupportUsButton(Screen screen) {
        return ButtonWidget.builder(Text.translatable(getModConfigText("support_us")), (Button) -> {
            // 使用默认浏览器打开模组官网
            try {
                // 判断用户是否使用简体中文(在中国大陆)
                if (ConfigData.isZhCN)
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + MOD_SUPPORT_WEB_OF_CN);
                else
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + MOD_SUPPORT_WEB);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).dimensions(screen.width - 75, 6, 70, 20).build();
    }

    /**
     * 自动显示配置按钮
     */
    private static final SimpleOption<Boolean> AUTO_SHOW_CONFIG_BUTTON = SimpleOption.ofBoolean(
            getModConfigText("auto_show_config_button"),
            SimpleOption.constantTooltip(Text.translatable(getModConfigTip("auto_show_config_button"))),
            Config.autoShowConfigButton, (value) -> {
                Config.autoShowConfigButton = value;
                writeConfig();
            });
    /**
     * 模组内容开关
     */
    private static final SimpleOption<Boolean> MOD_SWITCH = SimpleOption.ofBoolean(
            getModConfigText("mod_switch"),
            SimpleOption.constantTooltip(Text.translatable(getModConfigTip("mod_switch"))),
            Config.modSwitch, (value) -> {
                Config.modSwitch = value;
                writeConfig();
            });
    /**
     * 临时《自然更替》冒险
     */
    private static final SimpleOption<Boolean> TEMPORARY_SR_ADVENTURE = SimpleOption.ofBoolean(
            getModConfigText("temporary_sr_adventure"),
            SimpleOption.constantTooltip(Text.translatable(getModConfigTip("temporary_sr_adventure"))),
            Config.temporarySRAdventure, (value) -> {
                Config.temporarySRAdventure = value;
                writeConfig();
            });
    /**
     * 禁用“实验性设置”警告
     */
    private static final SimpleOption<Boolean> DISABLE_WARNING_ADVICE = SimpleOption.ofBoolean(
            getModConfigText("disable_warning_advice"),
            SimpleOption.constantTooltip(Text.translatable(getModConfigTip("disable_warning_advice"))),
            Config.disableWarningAdvice, (value) -> {
                Config.disableWarningAdvice = value;
                writeConfig();
            });
    /**
     * 所有配置选项集合
     */
    public static final SimpleOption<?>[] CONFIG_OPTIONS = new SimpleOption[]{
            AUTO_SHOW_CONFIG_BUTTON, MOD_SWITCH, TEMPORARY_SR_ADVENTURE, DISABLE_WARNING_ADVICE
    };
    /**
     * 配置选项文本
     */
    public static final String[] CONFIG_OPTIONS_TEXT = new String[]{
            "AutoShowConfigButton", "ModSwitch", "TemporarySRAdventure", "DisableWarningAdvice"
    };
    /**
     * 配置选项数量
     */
    public static final int OPTION_COUNT = CONFIG_OPTIONS.length;


    /**
     * 保存模组配置文件
     */
    public static void writeConfig() {
        // 打开并截断配置文件数据
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(configFile, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            // 写入模组配置
            // 写入注释与版本信息
            writeCommentAndVersion(printWriter);
            // 写入玩家配置项目
            printWriter.println("-----Gamer Config-----");
            for (int count = 0; count < OPTION_COUNT; count++) {
                printWriter.print(CONFIG_OPTIONS_TEXT[count]);
                printWriter.print(':');
                printWriter.println(CONFIG_OPTIONS[count].getValue());
            }
            printWriter.println();
            // 写入开发者配置项目
            printWriter.println("-----Developer Config-----");
            for (int count = 0; count < DevConfig.OPTION_COUNT; count++) {
                printWriter.print(DevConfig.CONFIG_TEXT[count]);
                printWriter.print(':');
                printWriter.println(DevConfig.CONFIG_OPTIONS[count]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取模组配置文件
     */
    public static void readConfig() {
        Path parent = Paths.get("config");
        // 遍历 config 文件夹查找是否已经存在配置文件
        try (DirectoryStream<Path> children = Files.newDirectoryStream(parent)) {
            boolean hasFile = false;
            // 遍历 config 文件夹查询
            for (Path child : children) {
                if (child.toString().equals("config" + FileSystems.getDefault().getSeparator() + "SpontaneousReplace.config"))
                    hasFile = true;
            }
            // 如果已经存在配置文件则读取模组配置
            if (hasFile) {
                BufferedReader bufferedReader = Files.newBufferedReader(configFile, StandardCharsets.UTF_8);
                // 读取配置文件版本
                switch (SkipCommentReturnVersion(bufferedReader)) {
                    case 2 -> readVersion2(bufferedReader);
                    default -> writeConfig();
                }
                // 没有则创建一个默认配置文件
            } else {
                Files.createFile(configFile);
                writeConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        printWriter.println("ConfigVersion:2");
        printWriter.println();
    }

    /**
     * 跳过注释并返回配置文件版本
     *
     * @param bufferedReader 配置文件缓存读取
     * @return 返回配置版本信息
     * @throws IOException 解决警告
     */
    public static int SkipCommentReturnVersion(BufferedReader bufferedReader) throws IOException {
        // 跳过注释
        bufferedReader.readLine();
        bufferedReader.readLine();
        bufferedReader.readLine();
        // 读取版本信息
        int OptionName = bufferedReader.read();
        while (OptionName != ':' && OptionName != -1)
            OptionName = bufferedReader.read();
        // 如果不是数字则返回 0
        String str = bufferedReader.readLine();
        if (!StringUtils.isNumeric(str))
            return 0;
        int version = Integer.parseInt(str);
        // 跳过空行
        bufferedReader.readLine();

        return version;
    }

    /**
     * 读取 2.0 的版本模组配置文件
     *
     * @param bufferedReader 配置文件缓存读取
     * @throws IOException 解决警告
     */
    public static void readVersion2(BufferedReader bufferedReader) throws IOException {
        // 读取玩家配置数据
        bufferedReader.readLine();
        for (int count = 0; count < OPTION_COUNT; count++) {
            // 跳过配置选项名
            int OptionName = bufferedReader.read();
            while (OptionName != ':' && OptionName != '\n')
                OptionName = bufferedReader.read();
            if (OptionName == '\n')
                break;
            // 一个一个选项赋值
            switch (count) {
                case 0 ->
                        AUTO_SHOW_CONFIG_BUTTON.setValue(Config.autoShowConfigButton = Boolean.parseBoolean(bufferedReader.readLine()));
                case 1 -> MOD_SWITCH.setValue(Config.modSwitch = Boolean.parseBoolean(bufferedReader.readLine()));
                case 2 ->
                        TEMPORARY_SR_ADVENTURE.setValue(Config.temporarySRAdventure = Boolean.parseBoolean(bufferedReader.readLine()));
                case 3 ->
                        DISABLE_WARNING_ADVICE.setValue(Config.disableWarningAdvice = Boolean.parseBoolean(bufferedReader.readLine()));
            }
        }
        // 读取开发者配置数据
        bufferedReader.readLine();
        for (int count = 0; count < DevConfig.OPTION_COUNT; count++) {
            // 跳过配置选项名
            int OptionName = bufferedReader.read();
            while (OptionName != ':' && OptionName != -1)
                OptionName = bufferedReader.read();
            if (OptionName == -1)
                break;
            // 一个一个选项赋值
            switch (count) {
                case 0 ->
                        DevConfig.CONFIG_OPTIONS[0] = DevConfig.disableModButton = Boolean.parseBoolean(bufferedReader.readLine());
            }
        }
    }


    /**
     * 获取模组配置文本翻译键
     *
     * @param key 翻译键
     * @return 模组配置文本翻译键
     */
    public static String getModConfigText(String key) {
        return "setting_text." + MOD_ID + '.' + key;
    }

    /**
     * 获取模组配置提示翻译键
     *
     * @param key 翻译键
     * @return 模组配置提示翻译键
     */
    public static String getModConfigTip(String key) {
        return "setting_tooltip." + MOD_ID + '.' + key;
    }
}
