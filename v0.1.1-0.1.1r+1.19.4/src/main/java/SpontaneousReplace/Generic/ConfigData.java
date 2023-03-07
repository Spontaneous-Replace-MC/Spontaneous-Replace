package SpontaneousReplace.Generic;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

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
 * @version 2.0
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
    public static boolean IsZhCN = false;

    /**
     * 世界配置父屏幕
     */
    public static Screen worldConfigScreen = null;

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

        public static boolean ModSwitch = true;
        public static boolean TemporarySRAdventure = true;
        public static boolean AutoShowConfigButton = false;
    }

    /**
     * 配置文件
     */
    protected static final Path configFile = Paths.get("config" + FileSystems.getDefault().getSeparator() + "SpontaneousReplace.config");

    /**
     * 获取“支持我们”按钮
     *
     * @param screen       需要添加的屏幕
     * @param buttonWidget 按钮组件
     * @return “支持我们”按钮
     */
    public static ButtonWidget getSupportUsButton(Screen screen, ButtonWidget buttonWidget) {
        return ButtonWidget.builder(Text.translatable(getModConfigText("support_us")), (Button) -> {
            // 使用默认浏览器打开模组官网
            try {
                // 判断用户是否使用简体中文(在中国大陆)
                if (ConfigData.IsZhCN)
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + MOD_SUPPORT_WEB_OF_CN);
                else
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + MOD_SUPPORT_WEB);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (buttonWidget != null)
                buttonWidget.visible = true;
        }).dimensions(screen.width - 75, 6, 70, 20).build();
    }

    /**
     * 自动显示配置按钮
     */
    private static final SimpleOption<Boolean> AutoShowConfigButton = SimpleOption.ofBoolean(
            getModConfigText("auto_show_config_button"),
            SimpleOption.constantTooltip(Text.translatable(getModConfigTip("auto_show_config_button"))),
            false, value -> Config.AutoShowConfigButton = value);
    /**
     * 模组内容开关
     */
    private static final SimpleOption<Boolean> ModSwitch = SimpleOption.ofBoolean(
            getModConfigText("mod_switch"),
            SimpleOption.constantTooltip(Text.translatable(getModConfigTip("mod_switch"))),
            true, value -> Config.ModSwitch = value);
    /**
     * 临时《自然更替》冒险
     */
    private static final SimpleOption<Boolean> TemporarySRAdventure = SimpleOption.ofBoolean(
            getModConfigText("temporary_sr_adventure"),
            SimpleOption.constantTooltip(Text.translatable(getModConfigTip("temporary_sr_adventure"))),
            true, value -> Config.TemporarySRAdventure = value);
    public static SimpleOption<?>[] ConfigOptions = new SimpleOption[]{
            AutoShowConfigButton, ModSwitch, TemporarySRAdventure
    };
    public static String[] ConfigOptionsText = new String[]{
            "AutoShowConfigButton", "ModSwitch", "TemporarySRAdventure"
    };
    public static final int OptionCount = ConfigOptions.length;

    /**
     * 保存模组配置文件
     */
    public static void WriteConfig() {
        // 打开并截断配置文件数据
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(configFile, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            // 写入模组配置
            for (int count = 0; count < OptionCount; count++) {
                printWriter.print(ConfigOptionsText[count]);
                printWriter.print(':');
                printWriter.println(ConfigOptions[count].getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取模组配置文件
     */
    public static void ReadConfig() {
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
                for (int count = 0; count < OptionCount; count++) {
                    // 跳过配置选项名
                    int OptionName = bufferedReader.read();
                    while (OptionName != ':' && OptionName != -1)
                        OptionName = bufferedReader.read();
                    if (OptionName == -1)
                        break;
                    // 一个一个选项赋值
                    switch (count) {
                        case 0 -> AutoShowConfigButton.setValue(Boolean.parseBoolean(bufferedReader.readLine()));
                        case 1 ->
                                ModSwitch.setValue(Config.ModSwitch = Boolean.parseBoolean(bufferedReader.readLine()));
                        case 2 ->
                                TemporarySRAdventure.setValue(Config.TemporarySRAdventure = Boolean.parseBoolean(bufferedReader.readLine()));
                    }
                }
                // 没有则创建一个默认配置文件
            } else {
                Files.createFile(configFile);
                WriteConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
