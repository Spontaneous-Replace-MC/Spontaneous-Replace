package pres.saikel_orado.spontaneous_replace.mod.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.terraformersmc.modmenu.gui.widget.UpdateAvailableBadge;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.render.*;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static net.minecraft.client.gui.screen.Screen.OPTIONS_BACKGROUND_TEXTURE;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.Config.Update.*;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRConfigData.*;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.*;

/**
 * <b style="color:FFC800"><font size="+2">SRUpdateUtil：更新工具</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">模组更新工具，负责有关更新的一切数据与工具</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 6.0
 * | 创建于 2023/6/3 15:50
 */
@Environment(EnvType.CLIENT)
public final class SRUpdateUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String UPDATE_BASIC_LINK = "https://api.modrinth.com/v2/project/" + SR_SLUG + "/version?loaders=[%22fabric%22]";
    public static final Date UPDATE_DATE;
    private static final Path JAR_PATH;

    static {
        try {
            UPDATE_DATE = utcConvert(new SimpleDateFormat(DATE_FORMAT).parse(SR_UPDATE_DATE));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            JAR_PATH = Path.of(SRUpdateUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Path MODS_PATH = Path.of(JAR_PATH.toString().substring(0, JAR_PATH.toString().lastIndexOf(File.separator)));
    public static final String MOB_JAR_NAME = JAR_PATH.toString().substring(JAR_PATH.toString().lastIndexOf(FileSystems.getDefault().getSeparator()) + 1);
    public static final String BAT_NAME = "SR-Update.bat";
    public static final Path BAT_PATH = Path.of(MODS_PATH + FileSystems.getDefault().getSeparator() + BAT_NAME);
    public static final ScheduledExecutorService UPDATE_THREAD_POOL = new ScheduledThreadPoolExecutor(0,
            new BasicThreadFactory.Builder().daemon(true).build());
    private static UpdateState updateState = UpdateState.NONE;
    private static boolean showChangelog = true;
    private static boolean showScreen = true;
    private static boolean canCheckUpdate = true;
    private static boolean updatingFail = false;
    private static DownloadThread thread;
    private static URL updateBasicLink;
    private static String updateModVer;
    private static String updateMinecraftVer;

    public enum UpdateState {NONE, NEW_MC_VER, THIS_MC_VER, MOD_LOG, STOP_UPDATE, UPDATE_FAIL}

    /**
     * 重置能检查更新
     */
    public static void resetCanCheckUpdate() {
        canCheckUpdate = true;
    }

    /**
     * 设置能检查更新
     */
    public static void setCanCheckUpdate() {
        canCheckUpdate = false;
    }

    /**
     * 获取更新模组版本
     */
    public static String getUpdateModVersion() {
        return updateModVer;
    }

    /**
     * 获取更新 MC 版本
     */
    public static String getUpdateMinecraftVersion() {
        return updateMinecraftVer;
    }

    /**
     * 获取更新进度
     */
    public static float getUpdateProgress() {
        return thread != null ? thread.getUpdateProgress() : 100;
    }

    /**
     * 获取更新状态
     */
    public static UpdateState getUpdateState() {
        return updateState;
    }

    /**
     * 获取是否更新失败
     */
    public static boolean getUpdatingFail() {
        return updatingFail;
    }

    /**
     * 获取更新链接
     */
    public static URL getUpdateLink() {
        return switch (updateMode) {
            default -> getManualDownloadUpdateLink();
            case 1 -> getAutoDownloadUpdateLink();
            case 2 -> getAutoUpdateLink();
        };
    }

    /**
     * 更新模组函数
     */
    public static boolean updateMod(URL downloadLink) {
        if (downloadLink == null) {
            updatingFail = true;
            return true;
        }
        return switch (updateMode) {
            default -> manualDownload(downloadLink);
            case 1 -> autoDownload(downloadLink);
            case 2 -> autoUpdate(downloadLink);
        };
    }

    /**
     * 读取更新日志
     *
     * @return 用于显示日志的文本列表组件
     */
    public static SRUpdateUtil.TextListWidget readChangelog(MinecraftClient client, int width, int height, int top, int bottom, int left, int itemHeight) {
        try (InputStream inputStream = isChinese() ?
                SRUpdateUtil.class.getResourceAsStream("/assets/" + SR_ID + "/log/zh_cn.txt")
                : SRUpdateUtil.class.getResourceAsStream("/assets/" + SR_ID + "/log/en_us.txt")) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8));
            StringBuilder log = new StringBuilder();
            for (int c; (c = bufferedReader.read()) != -1; )
                log.append((char) c);
            log = new StringBuilder(log.toString().replaceAll("\r\n", "\n"));
            if (isChinese())
                log = new StringBuilder(log.toString().replaceAll("\t", "—"));
            else log = new StringBuilder(log.toString().replaceAll("\t", "  "));
            // 注册文字列表控件
            SRUpdateUtil.TextListWidget changelogWidget = new SRUpdateUtil.TextListWidget(client,
                    width, height, top, bottom, itemHeight, log.toString());
            changelogWidget.setLeftPos(left);

            // 返回更新日志组件
            return changelogWidget;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 写入是否显示更新日志的信息
     */
    public static void writeShowChangelog(PrintWriter printWriter) {
        printWriter.println("LastUpdateTime: " + SR_UPDATE_DATE);
    }

    /**
     * 读取是否显示更新日志的信息
     */
    public static void readShowChangelog(BufferedReader bufferedReader) throws IOException {
        // 跳过配置选项名
        int optionName = bufferedReader.read();
        while (optionName != ':' && optionName != -1)
            optionName = bufferedReader.read();
        if (optionName == -1) return;
        try {
            showChangelog = UPDATE_DATE.after(utcConvert(new SimpleDateFormat(DATE_FORMAT).parse(bufferedReader.readLine().replaceAll(" ", ""))));
        } catch (ParseException e) {
            showChangelog = true;
        }
    }

    /**
     * 设置已经显示更新屏幕
     */
    public static void setShowScreen() {
        showScreen = false;
    }

    /**
     * 复位已经显示更新屏幕
     */
    public static void resetShowScreen() {
        showScreen = true;
    }

    /**
     * 获取是否显示更新屏幕
     */
    public static boolean getShowScreen() {
        return showScreen;
    }

    private static Date utcConvert(Date cstTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cstTime);
        calendar.add(Calendar.HOUR_OF_DAY, -8);
        calendar.add(Calendar.MINUTE, 30);
        return calendar.getTime();
    }

    @SuppressWarnings("SameParameterValue")
    private static int compareMinecraftVersion(String originalVer, String compareVer) {
        originalVer = StringUtils.substringBefore(originalVer, "-");
        compareVer = StringUtils.substringBefore(compareVer, "-");
        if (originalVer.contains("w") || originalVer.contains("a") || compareVer.contains("w") || compareVer.contains("a"))
            return 0;

        ArrayList<String> originalVerList = new ArrayList<>(Arrays.asList(originalVer.split("\\.")));
        originalVer = originalVer.replaceAll("\\.", "");
        if (originalVerList.size() < 3) originalVer += "0";
        ArrayList<String> compareVerList = new ArrayList<>(Arrays.asList(compareVer.split("\\.")));
        compareVer = compareVer.replaceAll("\\.", "");
        if (compareVerList.size() < 3) compareVer += "0";

        return Integer.compare(Integer.parseInt(compareVer), Integer.parseInt(originalVer));
    }

    private static String getLatestMinecraftVersion(String[] dataArr) {
        List<String> updateVer = new ArrayList<>();
        for (String data : dataArr) {
            if (!updateVer.isEmpty() || data.contains("\"game_versions\"")) {
                updateVer.add(data
                        .replaceAll("\"", "")
                        .replaceAll("\\[", "")
                        .replaceAll("]", ""));
                if (data.contains("]"))
                    break;
            }
        }
        // 设置更新版本
        updateVer.set(0, StringUtils.substringAfter(updateVer.get(0), ":"));
        String latestMinecraftVer = "";
        for (int count = 0; count < updateVer.size() - 1; count++) {
            latestMinecraftVer = compareMinecraftVersion(updateVer.get(count), updateVer.get(count + 1)) < 0 ?
                    updateVer.get(count) : updateVer.get(count + 1);
        }
        return latestMinecraftVer;
    }

    private static UpdateState checkUpdate() {
        UpdateState state;
        if (updateChannel == 2) {
            state = checkAlphaUpdate();
            if (state != UpdateState.NONE && state != UpdateState.UPDATE_FAIL)
                return state;
        }
        if (updateChannel == 1) {
            state = checkBetaUpdate();
            if (state != UpdateState.NONE && state != UpdateState.UPDATE_FAIL)
                return state;
        }
        state = checkReleaseUpdate();
        if (state != UpdateState.NONE && state != UpdateState.UPDATE_FAIL)
            updatingFail = false;
        return state;
    }

    private static UpdateState checkReleaseUpdate() {
        // 检查是否已停止更新
        if (!SR_UPDATING) return UpdateState.STOP_UPDATE;

        // 检查是否联网，如未联网则不进行更新检查
        try {
            URL testOnline = new URL("https://www.microsoft.com");
            HttpsURLConnection connection = (HttpsURLConnection) testOnline.openConnection();
            connection.setConnectTimeout(100);
            connection.getResponseCode();
        } catch (UnknownHostException unknownHostException) {
            return UpdateState.NONE;
        } catch (IOException ignored) {
        }

        // 判断是否有更新
        try {
            String checkUpdateBasic = UPDATE_BASIC_LINK + "&version_type=release";
            URL checkUpdateLink;
            if (checkNewMCVersionMod) {
                checkUpdateLink = new URL(checkUpdateBasic);
                updateBasicLink = checkUpdateLink;
                checkUpdateLink.openConnection().setConnectTimeout(100);
                // 通过 URL 的 openStream 方法获取 URL 对象所表示的自愿字节输入流
                InputStream is = checkUpdateLink.openStream();
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                // 为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
                String data = br.readLine();//读取数据
                // 判断更新
                String[] strings = data.split(",");
                String updateTime = "";
                String updateVer = getLatestMinecraftVersion(strings);
                for (String string : strings) {
                    if (string.contains("\"date_published\"")) {
                        updateTime = string;
                        break;
                    }
                }
                // 设置更新时间
                updateTime = StringUtils.substringAfter(updateTime, ":");
                updateTime = updateTime.replaceAll("\"", "");
                Date updateDate = new SimpleDateFormat(DATE_FORMAT).parse(updateTime);
                if (compareMinecraftVersion(MC_VER, updateVer) > 0) return UpdateState.NEW_MC_VER;
                if (UPDATE_DATE.before(updateDate)) {
                    if (updateVer.equals(MC_VER))
                        return UpdateState.THIS_MC_VER;
                    return UpdateState.NEW_MC_VER;
                }
            } else {
                checkUpdateLink = new URL(checkUpdateBasic + "&game_versions=[%22" + MC_VER + "%22]");
                updateBasicLink = checkUpdateLink;
                checkUpdateLink.openConnection().setConnectTimeout(100);
                // 通过 URL 的 openStream 方法获取 URL 对象所表示的自愿字节输入流
                InputStream is = checkUpdateLink.openStream();
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                // 为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
                String data = br.readLine();//读取数据

                String[] strings = data.split(",");
                for (String string : strings) {
                    if (string.contains("\"date_published\"")) {
                        data = string;
                        break;
                    }
                }

                data = StringUtils.substringAfter(data, ":");
                data = data.replaceAll("\"", "");
                Date updateDate = new SimpleDateFormat(DATE_FORMAT).parse(data);
                if (UPDATE_DATE.before(updateDate))
                    return UpdateState.THIS_MC_VER;
            }
            readConfig();
            if (showChangelog) {
                writeConfig();
                return UpdateState.MOD_LOG;
            }
            return UpdateState.NONE;
        } catch (IOException | ParseException e) {
            return UpdateState.UPDATE_FAIL;
        }
    }

    private static UpdateState checkBetaUpdate() {
        // 检查是否已停止更新
        if (!SR_UPDATING) return UpdateState.STOP_UPDATE;

        // 检查是否联网，如未联网则不进行更新检查
        try {
            URL testOnline = new URL("https://www.microsoft.com");
            HttpsURLConnection connection = (HttpsURLConnection) testOnline.openConnection();
            connection.setConnectTimeout(100);
            connection.getResponseCode();
        } catch (UnknownHostException unknownHostException) {
            return UpdateState.NONE;
        } catch (IOException ignored) {
        }

        // 判断是否有更新
        try {
            String checkUpdateBasic = UPDATE_BASIC_LINK + "&version_type=beta";
            URL checkUpdateLink;
            if (checkNewMCVersionMod) {
                checkUpdateLink = new URL(checkUpdateBasic);
                updateBasicLink = checkUpdateLink;
                checkUpdateLink.openConnection().setConnectTimeout(100);
                // 通过 URL 的 openStream 方法获取 URL 对象所表示的自愿字节输入流
                InputStream is = checkUpdateLink.openStream();
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                // 为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
                String data = br.readLine();//读取数据
                // 判断更新
                String[] strings = data.split(",");
                String updateTime = "";
                String updateVer = getLatestMinecraftVersion(strings);
                for (String string : strings) {
                    if (string.contains("\"date_published\"")) {
                        updateTime = string;
                        break;
                    }
                }
                // 设置更新时间
                updateTime = StringUtils.substringAfter(updateTime, ":");
                updateTime = updateTime.replaceAll("\"", "");
                Date updateDate = new SimpleDateFormat(DATE_FORMAT).parse(updateTime);
                if (compareMinecraftVersion(MC_VER, updateVer) > 0) return UpdateState.NEW_MC_VER;
                if (UPDATE_DATE.before(updateDate)) {
                    if (updateVer.equals(MC_VER))
                        return UpdateState.THIS_MC_VER;
                    return UpdateState.NEW_MC_VER;
                }
            } else {
                checkUpdateLink = new URL(checkUpdateBasic + "&game_versions=[%22" + MC_VER + "%22]");
                updateBasicLink = checkUpdateLink;
                checkUpdateLink.openConnection().setConnectTimeout(100);
                // 通过 URL 的 openStream 方法获取 URL 对象所表示的自愿字节输入流
                InputStream is = checkUpdateLink.openStream();
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                // 为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
                String data = br.readLine();//读取数据

                String[] strings = data.split(",");
                for (String string : strings) {
                    if (string.contains("\"date_published\"")) {
                        data = string;
                        break;
                    }
                }

                data = StringUtils.substringAfter(data, ":");
                data = data.replaceAll("\"", "");
                Date updateDate = new SimpleDateFormat(DATE_FORMAT).parse(data);
                if (UPDATE_DATE.before(updateDate))
                    return UpdateState.THIS_MC_VER;
            }
            readConfig();
            if (showChangelog) {
                writeConfig();
                return UpdateState.MOD_LOG;
            }
            return UpdateState.NONE;
        } catch (IOException | ParseException e) {
            return UpdateState.UPDATE_FAIL;
        }
    }

    private static UpdateState checkAlphaUpdate() {
        // 检查是否已停止更新
        if (!SR_UPDATING) return UpdateState.STOP_UPDATE;

        // 检查是否联网，如未联网则不进行更新检查
        try {
            URL testOnline = new URL("https://www.microsoft.com");
            HttpsURLConnection connection = (HttpsURLConnection) testOnline.openConnection();
            connection.setConnectTimeout(100);
            connection.getResponseCode();
        } catch (UnknownHostException unknownHostException) {
            return UpdateState.NONE;
        } catch (IOException ignored) {
        }

        // 判断是否有更新
        try {
            String checkUpdateBasic = UPDATE_BASIC_LINK + "&version_type=alpha";
            URL checkUpdateLink;
            if (checkNewMCVersionMod) {
                checkUpdateLink = new URL(checkUpdateBasic);
                updateBasicLink = checkUpdateLink;
                checkUpdateLink.openConnection().setConnectTimeout(100);
                // 通过 URL 的 openStream 方法获取 URL 对象所表示的自愿字节输入流
                InputStream is = checkUpdateLink.openStream();
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                // 为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
                String data = br.readLine();//读取数据
                // 判断更新
                String[] strings = data.split(",");
                String updateTime = "";
                String updateVer = getLatestMinecraftVersion(strings);
                for (String string : strings) {
                    if (string.contains("\"date_published\"")) {
                        updateTime = string;
                        break;
                    }
                }
                // 设置更新时间
                updateTime = StringUtils.substringAfter(updateTime, ":");
                updateTime = updateTime.replaceAll("\"", "");
                Date updateDate = new SimpleDateFormat(DATE_FORMAT).parse(updateTime);
                if (compareMinecraftVersion(MC_VER, updateVer) > 0) return UpdateState.NEW_MC_VER;
                if (UPDATE_DATE.before(updateDate)) {
                    if (updateVer.equals(MC_VER))
                        return UpdateState.THIS_MC_VER;
                    return UpdateState.NEW_MC_VER;
                }
            } else {
                checkUpdateLink = new URL(checkUpdateBasic + "&game_versions=[%22" + MC_VER + "%22]");
                updateBasicLink = checkUpdateLink;
                checkUpdateLink.openConnection().setConnectTimeout(100);
                // 通过 URL 的 openStream 方法获取 URL 对象所表示的自愿字节输入流
                InputStream is = checkUpdateLink.openStream();
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                // 为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
                String data = br.readLine();//读取数据

                String[] strings = data.split(",");
                for (String string : strings) {
                    if (string.contains("\"date_published\"")) {
                        data = string;
                        break;
                    }
                }

                data = StringUtils.substringAfter(data, ":");
                data = data.replaceAll("\"", "");
                Date updateDate = new SimpleDateFormat(DATE_FORMAT).parse(data);
                if (UPDATE_DATE.before(updateDate))
                    return UpdateState.THIS_MC_VER;
            }
            readConfig();
            if (showChangelog) {
                writeConfig();
                return UpdateState.MOD_LOG;
            }
            return UpdateState.NONE;
        } catch (IOException | ParseException e) {
            return UpdateState.UPDATE_FAIL;
        }
    }

    private static URL getManualDownloadUpdateLink() {
        try {
            return new URL("https://modrinth.com/mod/" + SR_SLUG + "/version/" + getUpdateModVersion());
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private static URL getAutoDownloadUpdateLink() {
        try {
            updateBasicLink.openConnection().setConnectTimeout(100);
            // 通过 URL 的 openStream 方法获取 URL 对象所表示的自愿字节输入流
            InputStream is = updateBasicLink.openStream();
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            // 为字符输入流添加缓冲
            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();//读取数据

            String[] strings = data.split(",");
            for (String string : strings) {
                if (string.contains("\"url\"")) {
                    data = string;
                    break;
                }
            }

            data = StringUtils.substringAfter(data, ":");
            data = data.replaceAll("\"", "");
            return new URL(data);
        } catch (IOException e) {
            return null;
        }
    }

    private static URL getAutoUpdateLink() {
        return getAutoDownloadUpdateLink();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static boolean manualDownload(URL downloadLink) {
        // 使用默认浏览器打开模组官网
        ConfirmLinkScreen.opening(downloadLink.toString(), null, true);
        // 返回 false 表示不下载
        return false;
    }

    private static boolean autoDownload(URL downloadLink) {
        UPDATE_THREAD_POOL.execute(thread = new DownloadThread(downloadLink));

        return true;
    }

    private static boolean autoUpdate(URL downloadLink) {
        // 下载文件
        autoDownload(downloadLink);
        // 创建自动删除旧文件的批处理文件
        try (DirectoryStream<Path> children = Files.newDirectoryStream(MODS_PATH)) {
            boolean hasFile = false;
            // 遍历 config 文件夹查询
            for (Path child : children) {
                if (child.equals(BAT_PATH)) {
                    hasFile = true;
                    break;
                }
            }
            // 如果不存在配置文件则读取模组配置
            if (!hasFile) Files.createFile(BAT_PATH);

            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(BAT_PATH,
                    StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
                 PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
                printWriter.println("chcp 65001");
                printWriter.println("@echo off");
                printWriter.println("");
                printWriter.println("cls");
                printWriter.println("echo.");
                printWriter.println("echo 执行《自然更替》更新清理中……");
                printWriter.println("echo Performing Spontaneous-Replace update cleaning...");
                printWriter.println("echo.");
                printWriter.println("");
                printWriter.println("for /l %%i in (1, 1, 50000) do (");
                printWriter.println("\tif exist \"" + MOB_JAR_NAME + "\" (");
                printWriter.println("\t\tdel /q \"" + MOB_JAR_NAME + "\"");
                printWriter.println("\t) else (");
                printWriter.println("\t\tcls");
                printWriter.println("\t\techo.");
                printWriter.println("\t\techo 更新《自然更替》成功，请启动游戏开始游玩");
                printWriter.println("\t\techo Successfully updated Spontaneous-Replace, please start the game and start playing");
                printWriter.println("\t\techo.");
                printWriter.println("\t\tchoice /t 5 /d y /n >nul");
                printWriter.println("\t\tdel /q \"" + BAT_NAME + "\"");
                printWriter.println("\t\texit");
                printWriter.println("\t)");
                printWriter.println(")");
                printWriter.println("");
                printWriter.println("cls");
                printWriter.println("echo.");
                printWriter.println("echo 卸载旧版本《自然更替》失败，请手动删除旧版本《自然更替》");
                printWriter.println("echo Failed to uninstall the old Spontaneous-Replace version. Please manually delete the old Spontaneous-Replace version");
                printWriter.println("echo.");
                printWriter.println("pause");
                printWriter.println("start explorer \"" + MODS_PATH + "\"");
                printWriter.println("del /q \"" + BAT_NAME + "\"");
                printWriter.println("exit");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    private static void setUpdateModVersion() {
        if (updateBasicLink == null) {
            updateModVer = "";
            return;
        }
        try {
            updateBasicLink.openConnection().setConnectTimeout(100);
            // 通过 URL 的 openStream 方法获取 URL 对象所表示的自愿字节输入流
            InputStream is = updateBasicLink.openStream();
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            // 为字符输入流添加缓冲
            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();//读取数据

            String[] strings = data.split(",");
            for (String string : strings) {
                if (string.contains("\"version_number\"")) {
                    data = string;
                    break;
                }
            }

            data = StringUtils.substringAfter(data, ":");
            data = data.replaceAll("\"", "");
            updateModVer = data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setUpdateMinecraftVersion() {
        if (updateBasicLink == null) {
            updateMinecraftVer = "";
            return;
        }
        try {
            updateBasicLink.openConnection().setConnectTimeout(100);
            // 通过 URL 的 openStream 方法获取 URL 对象所表示的自愿字节输入流
            InputStream is = updateBasicLink.openStream();
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            // 为字符输入流添加缓冲
            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();//读取数据
            updateMinecraftVer = getLatestMinecraftVersion(data.split(","));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 下载模组文件线程
     */
    private static class DownloadThread extends Thread {
        private final URL downloadLink;
        private static float updateProgress;

        private DownloadThread(URL downloadLink) {
            this.downloadLink = downloadLink;
            updateProgress = 0;
        }

        public float getUpdateProgress() {
            return updateProgress;
        }

        @Override
        public void run() {
            InputStream inputStream;
            OutputStream outputStream;
            try {
                // 打开连接
                HttpsURLConnection con = (HttpsURLConnection) downloadLink.openConnection();
                // 请求超时:5s
                con.setConnectTimeout(5 * 1000);

                con.setRequestMethod("GET");
                long size = con.getContentLength();

                inputStream = con.getInputStream();
                byte[] bytes = new byte[1024];
                // 读取到的数据长度
                int length;
                outputStream = Files.newOutputStream(Path.of(MODS_PATH + FileSystems.getDefault().getSeparator()
                        + downloadLink.getPath().substring(downloadLink.getPath().lastIndexOf('/'))));
                // 读取
                int fileSize = 0;
                while ((length = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, length);
                    updateProgress = (fileSize += 1024) / size < 1 ? (float) fileSize / size * 100 : 99.99F;
                }
                updateProgress = 100;

                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                updatingFail = true;
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 检查更新线程
     */
    public static class CheckUpdateThread extends Thread {

        @Override
        public void run() {
            if (canCheckUpdate) {
                UpdateState state;
                state = checkUpdate();
                setUpdateModVersion();
                setUpdateMinecraftVersion();

                updateState = state;
            }
        }
    }

    /**
     * 文本列表组件
     */
    @Environment(EnvType.CLIENT)
    public static class TextListWidget extends EntryListWidget<TextListWidget.TextEntry> {

        private final String text;
        private final TextRenderer textRenderer;

        public TextListWidget(MinecraftClient client, int width, int height, int top, int bottom, int entryHeight, String text) {
            super(client, width, height, top, bottom, entryHeight);
            this.text = text;
            textRenderer = client.textRenderer;
        }

        @Override
        public TextEntry getSelectedOrNull() {
            return null;
        }

        @Override
        public int getRowWidth() {
            return width - 10;
        }

        @Override
        protected int getScrollbarPositionX() {
            return width - 6 + left;
        }

        @Override
        public void appendNarrations(NarrationMessageBuilder builder) {
        }

        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            clearEntries();
            if (!text.isEmpty()) {
                for (OrderedText line : textRenderer.wrapLines(Text.literal(text), getRowWidth() - 5)) {
                    children().add(new TextEntry(line, this));
                }
            }

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();

            {
                RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
                RenderSystem.setShaderTexture(0, OPTIONS_BACKGROUND_TEXTURE);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
                bufferBuilder.vertex(left, bottom, 0.0D).texture(left / 32.0F, (bottom + (int) getScrollAmount()) / 32.0F).color(32, 32, 32, 255).next();
                bufferBuilder.vertex(right, bottom, 0.0D).texture(right / 32.0F, (bottom + (int) getScrollAmount()) / 32.0F).color(32, 32, 32, 255).next();
                bufferBuilder.vertex(right, top, 0.0D).texture(right / 32.0F, (top + (int) getScrollAmount()) / 32.0F).color(32, 32, 32, 255).next();
                bufferBuilder.vertex(left, top, 0.0D).texture(left / 32.0F, (top + (int) getScrollAmount()) / 32.0F).color(32, 32, 32, 255).next();
                tessellator.draw();
            }

            RenderSystem.depthFunc(515);
            RenderSystem.disableDepthTest();
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ZERO, GlStateManager.DstFactor.ONE);
            RenderSystem.setShader(GameRenderer::getPositionColorProgram);

            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
            bufferBuilder.vertex(left, (top + 4), 0.0D).color(0, 0, 0, 0).next();
            bufferBuilder.vertex(right, (top + 4), 0.0D).color(0, 0, 0, 0).next();
            bufferBuilder.vertex(right, top, 0.0D).color(0, 0, 0, 255).next();
            bufferBuilder.vertex(left, top, 0.0D).color(0, 0, 0, 255).next();
            bufferBuilder.vertex(left, bottom, 0.0D).color(0, 0, 0, 255).next();
            bufferBuilder.vertex(right, bottom, 0.0D).color(0, 0, 0, 255).next();
            bufferBuilder.vertex(right, (bottom - 4), 0.0D).color(0, 0, 0, 0).next();
            bufferBuilder.vertex(left, (bottom - 4), 0.0D).color(0, 0, 0, 0).next();
            tessellator.draw();

            renderList(context, mouseX, mouseY, delta);
            renderScrollBar(bufferBuilder, tessellator);

            RenderSystem.disableBlend();
        }

        public void renderScrollBar(BufferBuilder bufferBuilder, Tessellator tessellator) {
            int scrollbarStartX = getScrollbarPositionX();
            int scrollbarEndX = scrollbarStartX + 6;
            int maxScroll = getMaxScroll();
            if (maxScroll > 0) {
                int p = (int) ((float) ((bottom - top) * (bottom - top)) / (float) getMaxPosition());
                p = MathHelper.clamp(p, 32, bottom - top - 8);
                int q = (int) getScrollAmount() * (bottom - top - p) / maxScroll + top;
                if (q < top) {
                    q = top;
                }

                RenderSystem.setShader(GameRenderer::getPositionColorProgram);
                bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
                bufferBuilder.vertex(scrollbarStartX, bottom, 0.0D).color(0, 0, 0, 255).next();
                bufferBuilder.vertex(scrollbarEndX, bottom, 0.0D).color(0, 0, 0, 255).next();
                bufferBuilder.vertex(scrollbarEndX, top, 0.0D).color(0, 0, 0, 255).next();
                bufferBuilder.vertex(scrollbarStartX, top, 0.0D).color(0, 0, 0, 255).next();
                bufferBuilder.vertex(scrollbarStartX, q + p, 0.0D).color(128, 128, 128, 255).next();
                bufferBuilder.vertex(scrollbarEndX, q + p, 0.0D).color(128, 128, 128, 255).next();
                bufferBuilder.vertex(scrollbarEndX, q, 0.0D).color(128, 128, 128, 255).next();
                bufferBuilder.vertex(scrollbarStartX, q, 0.0D).color(128, 128, 128, 255).next();
                bufferBuilder.vertex(scrollbarStartX, q + p - 1, 0.0D).color(192, 192, 192, 255).next();
                bufferBuilder.vertex(scrollbarEndX - 1, q + p - 1, 0.0D).color(192, 192, 192, 255).next();
                bufferBuilder.vertex(scrollbarEndX - 1, q, 0.0D).color(192, 192, 192, 255).next();
                bufferBuilder.vertex(scrollbarStartX, q, 0.0D).color(192, 192, 192, 255).next();
                tessellator.draw();
            }
        }

        /**
         * 文本控件
         */
        @Environment(EnvType.CLIENT)
        public class TextEntry extends ElementListWidget.Entry<TextListWidget.TextEntry> {
            private final TextListWidget widget;
            protected final OrderedText text;
            protected final int indent;
            public final boolean updateTextEntry = false;

            public TextEntry(OrderedText text, TextListWidget widget, int indent) {
                this.text = text;
                this.widget = widget;
                this.indent = indent;
            }

            public TextEntry(OrderedText text, TextListWidget widget) {
                this(text, widget, 0);
            }

            @Override
            public void render(DrawContext context, int index, int y, int x, int itemWidth, int itemHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
                if (widget.top > y || widget.bottom - textRenderer.fontHeight < y) {
                    return;
                }
                if (updateTextEntry) {
                    UpdateAvailableBadge.renderBadge(context, x + indent, y);
                    x += 11;
                }
                context.drawTextWithShadow(textRenderer, text, x + indent, y, 0xFFFFFF);
            }

            @Override
            public List<? extends Element> children() {
                return Collections.emptyList();
            }

            @Override
            public List<? extends Selectable> selectableChildren() {
                return Collections.emptyList();
            }
        }
    }

    private SRUpdateUtil() throws ExceptionInInitializerError {
        throw new ExceptionInInitializerError();
    }
}
