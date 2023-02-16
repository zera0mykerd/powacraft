/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.plugin.Plugin
 *  net.md_5.bungee.config.Configuration
 *  net.md_5.bungee.config.ConfigurationProvider
 *  net.md_5.bungee.config.YamlConfiguration
 */
package de.timeox2k.diamondantibot.metrics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Metrics {
    private /* synthetic */ boolean logResponseStatusText;
    private /* synthetic */ boolean logErrors;
    private final /* synthetic */ MetricsBase metricsBase;
    private /* synthetic */ String serverUUID;
    private final /* synthetic */ Plugin plugin;
    private /* synthetic */ boolean logSentData;
    private /* synthetic */ boolean enabled;

    private void writeFile(File lllllIIlIIIlllI, String ... lllllIIlIIIllIl) throws IOException {
        try (BufferedWriter lllllIIlIIlIIlI = new BufferedWriter(new FileWriter(lllllIIlIIIlllI));){
            for (String lllllIIlIIlIIll : lllllIIlIIIllIl) {
                lllllIIlIIlIIlI.write(lllllIIlIIlIIll);
                lllllIIlIIlIIlI.newLine();
            }
        }
    }

    private void appendPlatformData(JsonObjectBuilder lllllIIIllllIll) {
        Metrics lllllIIIlllllII;
        lllllIIIllllIll.appendField("playerAmount", lllllIIIlllllII.plugin.getProxy().getOnlineCount());
        lllllIIIllllIll.appendField("managedServers", lllllIIIlllllII.plugin.getProxy().getServers().size());
        lllllIIIllllIll.appendField("onlineMode", lllllIIIlllllII.plugin.getProxy().getConfig().isOnlineMode() ? 1 : 0);
        lllllIIIllllIll.appendField("bungeecordVersion", lllllIIIlllllII.plugin.getProxy().getVersion());
        lllllIIIllllIll.appendField("javaVersion", System.getProperty("java.version"));
        lllllIIIllllIll.appendField("osName", System.getProperty("os.name"));
        lllllIIIllllIll.appendField("osArch", System.getProperty("os.arch"));
        lllllIIIllllIll.appendField("osVersion", System.getProperty("os.version"));
        lllllIIIllllIll.appendField("coreCount", Runtime.getRuntime().availableProcessors());
    }

    public Metrics(Plugin lllllIIlIlIllII, int lllllIIlIlIlllI) {
        Metrics lllllIIlIllIIII;
        lllllIIlIllIIII.logErrors = false;
        lllllIIlIllIIII.plugin = lllllIIlIlIllII;
        try {
            lllllIIlIllIIII.loadConfig();
        }
        catch (IOException lllllIIlIllIIIl) {
            lllllIIlIlIllII.getLogger().log(Level.WARNING, "Failed to load bStats config!", lllllIIlIllIIIl);
            lllllIIlIllIIII.metricsBase = null;
            return;
        }
        lllllIIlIllIIII.metricsBase = new MetricsBase("bungeecord", lllllIIlIllIIII.serverUUID, lllllIIlIlIlllI, lllllIIlIllIIII.enabled, lllllIIlIllIIII::appendPlatformData, lllllIIlIllIIII::appendServiceData, null, () -> true, (lllllIIIllIlIII, lllllIIIllIIlll) -> {
            Metrics lllllIIIllIIllI;
            lllllIIIllIIllI.plugin.getLogger().log(Level.WARNING, (String)lllllIIIllIlIII, (Throwable)lllllIIIllIIlll);
        }, lllllIIIllIllll -> {
            Metrics lllllIIIllIlllI;
            lllllIIIllIlllI.plugin.getLogger().log(Level.INFO, (String)lllllIIIllIllll);
        }, lllllIIlIllIIII.logErrors, lllllIIlIllIIII.logSentData, lllllIIlIllIIII.logResponseStatusText);
    }

    public void addCustomChart(CustomChart lllllIIIlllllll) {
        Metrics lllllIIlIIIIIlI;
        lllllIIlIIIIIlI.metricsBase.addCustomChart(lllllIIIlllllll);
    }

    private void loadConfig() throws IOException {
        Metrics lllllIIlIlIIlIl;
        File lllllIIlIlIIlII = new File(lllllIIlIlIIlIl.plugin.getDataFolder().getParentFile(), "bStats");
        lllllIIlIlIIlII.mkdirs();
        File lllllIIlIlIIIll = new File(lllllIIlIlIIlII, "config.yml");
        if (!lllllIIlIlIIIll.exists()) {
            lllllIIlIlIIlIl.writeFile(lllllIIlIlIIIll, "# bStats (https://bStats.org) collects some basic information for plugin authors, like how", "# many people use their plugin and their total player count. It's recommended to keep bStats", "# enabled, but if you're not comfortable with this, you can turn this setting off. There is no", "# performance penalty associated with having metrics enabled, and data sent to bStats is fully", "# anonymous.", "enabled: true", String.valueOf(new StringBuilder().append("serverUuid: \"").append(UUID.randomUUID()).append("\"")), "logFailedRequests: false", "logSentData: false", "logResponseStatusText: false");
        }
        Configuration lllllIIlIlIIIlI = ConfigurationProvider.getProvider(YamlConfiguration.class).load(lllllIIlIlIIIll);
        lllllIIlIlIIlIl.enabled = lllllIIlIlIIIlI.getBoolean("enabled", true);
        lllllIIlIlIIlIl.serverUUID = lllllIIlIlIIIlI.getString("serverUuid");
        lllllIIlIlIIlIl.logErrors = lllllIIlIlIIIlI.getBoolean("logFailedRequests", false);
        lllllIIlIlIIlIl.logSentData = lllllIIlIlIIIlI.getBoolean("logSentData", false);
        lllllIIlIlIIlIl.logResponseStatusText = lllllIIlIlIIIlI.getBoolean("logResponseStatusText", false);
    }

    private void appendServiceData(JsonObjectBuilder lllllIIIlllIIll) {
        Metrics lllllIIIlllIlII;
        lllllIIIlllIIll.appendField("pluginVersion", lllllIIIlllIlII.plugin.getDescription().getVersion());
    }

    public static class AdvancedBarChart
    extends CustomChart {
        private final /* synthetic */ Callable<Map<String, int[]>> callable;

        public AdvancedBarChart(String llllIlIIIIlIIIl, Callable<Map<String, int[]>> llllIlIIIIlIIll) {
            super(llllIlIIIIlIIIl);
            AdvancedBarChart llllIlIIIIlIIlI;
            llllIlIIIIlIIlI.callable = llllIlIIIIlIIll;
        }

        @Override
        protected JsonObjectBuilder.JsonObject getChartData() throws Exception {
            AdvancedBarChart llllIlIIIIIIlII;
            JsonObjectBuilder llllIlIIIIIIlll = new JsonObjectBuilder();
            Map<String, int[]> llllIlIIIIIIllI = llllIlIIIIIIlII.callable.call();
            if (llllIlIIIIIIllI == null || llllIlIIIIIIllI.isEmpty()) {
                return null;
            }
            boolean llllIlIIIIIIlIl = true;
            for (Map.Entry<String, int[]> llllIlIIIIIlIIl : llllIlIIIIIIllI.entrySet()) {
                if (llllIlIIIIIlIIl.getValue().length == 0) continue;
                llllIlIIIIIIlIl = false;
                llllIlIIIIIIlll.appendField(llllIlIIIIIlIIl.getKey(), llllIlIIIIIlIIl.getValue());
            }
            if (llllIlIIIIIIlIl) {
                return null;
            }
            return new JsonObjectBuilder().appendField("values", llllIlIIIIIIlll.build()).build();
        }
    }

    public static class SimplePie
    extends CustomChart {
        private final /* synthetic */ Callable<String> callable;

        @Override
        protected JsonObjectBuilder.JsonObject getChartData() throws Exception {
            SimplePie llllIIlllIllllI;
            String llllIIlllIlllll = llllIIlllIllllI.callable.call();
            if (llllIIlllIlllll == null || llllIIlllIlllll.isEmpty()) {
                return null;
            }
            return new JsonObjectBuilder().appendField("value", llllIIlllIlllll).build();
        }

        public SimplePie(String llllIIllllIIlll, Callable<String> llllIIllllIIIll) {
            super(llllIIllllIIlll);
            SimplePie llllIIllllIlIII;
            llllIIllllIlIII.callable = llllIIllllIIIll;
        }
    }

    public static class DrilldownPie
    extends CustomChart {
        private final /* synthetic */ Callable<Map<String, Map<String, Integer>>> callable;

        @Override
        public JsonObjectBuilder.JsonObject getChartData() throws Exception {
            DrilldownPie llllIIlIIIIIIIl;
            JsonObjectBuilder llllIIlIIIIIIII = new JsonObjectBuilder();
            Map<String, Map<String, Integer>> llllIIIllllllll = llllIIlIIIIIIIl.callable.call();
            if (llllIIIllllllll == null || llllIIIllllllll.isEmpty()) {
                return null;
            }
            boolean llllIIIlllllllI = true;
            for (Map.Entry<String, Map<String, Integer>> llllIIlIIIIIIlI : llllIIIllllllll.entrySet()) {
                JsonObjectBuilder llllIIlIIIIIlII = new JsonObjectBuilder();
                boolean llllIIlIIIIIIll = true;
                for (Map.Entry<String, Integer> llllIIlIIIIIlIl : llllIIIllllllll.get(llllIIlIIIIIIlI.getKey()).entrySet()) {
                    llllIIlIIIIIlII.appendField(llllIIlIIIIIlIl.getKey(), llllIIlIIIIIlIl.getValue());
                    llllIIlIIIIIIll = false;
                }
                if (llllIIlIIIIIIll) continue;
                llllIIIlllllllI = false;
                llllIIlIIIIIIII.appendField(llllIIlIIIIIIlI.getKey(), llllIIlIIIIIlII.build());
            }
            if (llllIIIlllllllI) {
                return null;
            }
            return new JsonObjectBuilder().appendField("values", llllIIlIIIIIIII.build()).build();
        }

        public DrilldownPie(String llllIIlIIIlIIIl, Callable<Map<String, Map<String, Integer>>> llllIIlIIIlIIII) {
            super(llllIIlIIIlIIIl);
            DrilldownPie llllIIlIIIlIIlI;
            llllIIlIIIlIIlI.callable = llllIIlIIIlIIII;
        }
    }

    public static class SingleLineChart
    extends CustomChart {
        private final /* synthetic */ Callable<Integer> callable;

        public SingleLineChart(String llllIIlIlIlIIIl, Callable<Integer> llllIIlIlIIllIl) {
            super(llllIIlIlIlIIIl);
            SingleLineChart llllIIlIlIIllll;
            llllIIlIlIIllll.callable = llllIIlIlIIllIl;
        }

        @Override
        protected JsonObjectBuilder.JsonObject getChartData() throws Exception {
            SingleLineChart llllIIlIlIIlIII;
            int llllIIlIlIIlIIl = llllIIlIlIIlIII.callable.call();
            if (llllIIlIlIIlIIl == 0) {
                return null;
            }
            return new JsonObjectBuilder().appendField("value", llllIIlIlIIlIIl).build();
        }
    }

    public static class MultiLineChart
    extends CustomChart {
        private final /* synthetic */ Callable<Map<String, Integer>> callable;

        public MultiLineChart(String llllIIlllIIIlll, Callable<Map<String, Integer>> llllIIlllIIIllI) {
            super(llllIIlllIIIlll);
            MultiLineChart llllIIlllIIlIII;
            llllIIlllIIlIII.callable = llllIIlllIIIllI;
        }

        @Override
        protected JsonObjectBuilder.JsonObject getChartData() throws Exception {
            MultiLineChart llllIIllIllIlll;
            JsonObjectBuilder llllIIllIlllIlI = new JsonObjectBuilder();
            Map<String, Integer> llllIIllIlllIIl = llllIIllIllIlll.callable.call();
            if (llllIIllIlllIIl == null || llllIIllIlllIIl.isEmpty()) {
                return null;
            }
            boolean llllIIllIlllIII = true;
            for (Map.Entry<String, Integer> llllIIllIllllII : llllIIllIlllIIl.entrySet()) {
                if (llllIIllIllllII.getValue() == 0) continue;
                llllIIllIlllIII = false;
                llllIIllIlllIlI.appendField(llllIIllIllllII.getKey(), llllIIllIllllII.getValue());
            }
            if (llllIIllIlllIII) {
                return null;
            }
            return new JsonObjectBuilder().appendField("values", llllIIllIlllIlI.build()).build();
        }
    }

    public static class SimpleBarChart
    extends CustomChart {
        private final /* synthetic */ Callable<Map<String, Integer>> callable;

        public SimpleBarChart(String lllllllIllllllI, Callable<Map<String, Integer>> lllllllIlllllIl) {
            super(lllllllIllllllI);
            SimpleBarChart lllllllIlllllll;
            lllllllIlllllll.callable = lllllllIlllllIl;
        }

        @Override
        protected JsonObjectBuilder.JsonObject getChartData() throws Exception {
            SimpleBarChart lllllllIlllIllI;
            JsonObjectBuilder lllllllIlllIlIl = new JsonObjectBuilder();
            Map<String, Integer> lllllllIlllIlII = lllllllIlllIllI.callable.call();
            if (lllllllIlllIlII == null || lllllllIlllIlII.isEmpty()) {
                return null;
            }
            for (Map.Entry<String, Integer> lllllllIlllIlll : lllllllIlllIlII.entrySet()) {
                lllllllIlllIlIl.appendField(lllllllIlllIlll.getKey(), new int[]{lllllllIlllIlll.getValue()});
            }
            return new JsonObjectBuilder().appendField("values", lllllllIlllIlIl.build()).build();
        }
    }

    public static abstract class CustomChart {
        private final /* synthetic */ String chartId;

        protected abstract JsonObjectBuilder.JsonObject getChartData() throws Exception;

        public JsonObjectBuilder.JsonObject getRequestJsonObject(BiConsumer<String, Throwable> lllIllIIlIllIII, boolean lllIllIIlIlIIll) {
            CustomChart lllIllIIlIllIIl;
            JsonObjectBuilder lllIllIIlIlIllI = new JsonObjectBuilder();
            lllIllIIlIlIllI.appendField("chartId", lllIllIIlIllIIl.chartId);
            try {
                JsonObjectBuilder.JsonObject lllIllIIlIllIll = lllIllIIlIllIIl.getChartData();
                if (lllIllIIlIllIll == null) {
                    return null;
                }
                lllIllIIlIlIllI.appendField("data", lllIllIIlIllIll);
            }
            catch (Throwable lllIllIIlIllIlI) {
                if (lllIllIIlIlIIll) {
                    lllIllIIlIllIII.accept(String.valueOf(new StringBuilder().append("Failed to get data for custom chart with id ").append(lllIllIIlIllIIl.chartId)), lllIllIIlIllIlI);
                }
                return null;
            }
            return lllIllIIlIlIllI.build();
        }

        protected CustomChart(String lllIllIIllIIIIl) {
            CustomChart lllIllIIllIIlII;
            if (lllIllIIllIIIIl == null) {
                throw new IllegalArgumentException("chartId must not be null");
            }
            lllIllIIllIIlII.chartId = lllIllIIllIIIIl;
        }
    }

    public static class MetricsBase {
        private final /* synthetic */ Supplier<Boolean> checkServiceEnabledSupplier;
        private final /* synthetic */ int serviceId;
        private final /* synthetic */ Consumer<Runnable> submitTaskConsumer;
        private final /* synthetic */ Consumer<JsonObjectBuilder> appendPlatformDataConsumer;
        private final /* synthetic */ String serverUuid;
        private final /* synthetic */ boolean logErrors;
        private final /* synthetic */ Consumer<String> infoLogger;
        private final /* synthetic */ boolean logSentData;
        private final /* synthetic */ Set<CustomChart> customCharts;
        private final /* synthetic */ BiConsumer<String, Throwable> errorLogger;
        private static final /* synthetic */ ScheduledExecutorService scheduler;
        private final /* synthetic */ boolean enabled;
        private final /* synthetic */ String platform;
        private final /* synthetic */ boolean logResponseStatusText;
        private final /* synthetic */ Consumer<JsonObjectBuilder> appendServiceDataConsumer;
        public static final /* synthetic */ String METRICS_VERSION;
        private static final /* synthetic */ String REPORT_URL;

        private static byte[] compress(String lllllIlIIIllIII) throws IOException {
            if (lllllIlIIIllIII == null) {
                return null;
            }
            ByteArrayOutputStream lllllIlIIIllIIl = new ByteArrayOutputStream();
            try (GZIPOutputStream lllllIlIIIllIll = new GZIPOutputStream(lllllIlIIIllIIl);){
                lllllIlIIIllIll.write(lllllIlIIIllIII.getBytes(StandardCharsets.UTF_8));
            }
            return lllllIlIIIllIIl.toByteArray();
        }

        private void checkRelocation() {
            if (System.getProperty("bstats.relocatecheck") == null || !System.getProperty("bstats.relocatecheck").equals("false")) {
                String lllllIlIIlIIlll = new String(new byte[]{111, 114, 103, 46, 98, 115, 116, 97, 116, 115});
                String lllllIlIIlIIllI = new String(new byte[]{121, 111, 117, 114, 46, 112, 97, 99, 107, 97, 103, 101});
                if (MetricsBase.class.getPackage().getName().startsWith(lllllIlIIlIIlll) || MetricsBase.class.getPackage().getName().startsWith(lllllIlIIlIIllI)) {
                    throw new IllegalStateException("bStats Metrics class has not been relocated correctly!");
                }
            }
        }

        private void startSubmitting() {
            MetricsBase lllllIlIlIlllIl;
            Runnable lllllIlIllIIIII = () -> {
                MetricsBase lllllIIlllllllI;
                if (!lllllIIlllllllI.enabled || !lllllIIlllllllI.checkServiceEnabledSupplier.get().booleanValue()) {
                    scheduler.shutdown();
                    return;
                }
                if (lllllIIlllllllI.submitTaskConsumer != null) {
                    lllllIIlllllllI.submitTaskConsumer.accept(lllllIIlllllllI::submitData);
                } else {
                    lllllIIlllllllI.submitData();
                }
            };
            long lllllIlIlIlllll = (long)(60000.0 * (3.0 + Math.random() * 3.0));
            long lllllIlIlIllllI = (long)(60000.0 * (Math.random() * 30.0));
            scheduler.schedule(lllllIlIllIIIII, lllllIlIlIlllll, TimeUnit.MILLISECONDS);
            scheduler.scheduleAtFixedRate(lllllIlIllIIIII, lllllIlIlIlllll + lllllIlIlIllllI, 1800000L, TimeUnit.MILLISECONDS);
        }

        static {
            REPORT_URL = "https://bStats.org/api/v2/data/%s";
            METRICS_VERSION = "2.2.1";
            scheduler = Executors.newScheduledThreadPool(1, lllllIIlllllIlI -> new Thread(lllllIIlllllIlI, "bStats-Metrics"));
        }

        public void addCustomChart(CustomChart lllllIlIllIlIII) {
            MetricsBase lllllIlIllIlIIl;
            lllllIlIllIlIIl.customCharts.add(lllllIlIllIlIII);
        }

        private void submitData() {
            MetricsBase lllllIlIlIIllll;
            JsonObjectBuilder lllllIlIlIlIIll = new JsonObjectBuilder();
            lllllIlIlIIllll.appendPlatformDataConsumer.accept(lllllIlIlIlIIll);
            JsonObjectBuilder lllllIlIlIlIIlI = new JsonObjectBuilder();
            lllllIlIlIIllll.appendServiceDataConsumer.accept(lllllIlIlIlIIlI);
            JsonObjectBuilder.JsonObject[] lllllIlIlIlIIIl = (JsonObjectBuilder.JsonObject[])lllllIlIlIIllll.customCharts.stream().map(lllllIlIIIIIIII -> {
                MetricsBase lllllIlIIIIIIIl;
                return lllllIlIIIIIIII.getRequestJsonObject(lllllIlIIIIIIIl.errorLogger, lllllIlIIIIIIIl.logErrors);
            }).filter(Objects::nonNull).toArray(JsonObjectBuilder.JsonObject[]::new);
            lllllIlIlIlIIlI.appendField("id", lllllIlIlIIllll.serviceId);
            lllllIlIlIlIIlI.appendField("customCharts", lllllIlIlIlIIIl);
            lllllIlIlIlIIll.appendField("service", lllllIlIlIlIIlI.build());
            lllllIlIlIlIIll.appendField("serverUUID", lllllIlIlIIllll.serverUuid);
            lllllIlIlIlIIll.appendField("metricsVersion", "2.2.1");
            JsonObjectBuilder.JsonObject lllllIlIlIlIIII = lllllIlIlIlIIll.build();
            scheduler.execute(() -> {
                block2: {
                    MetricsBase lllllIlIIIIllIl;
                    try {
                        lllllIlIIIIllIl.sendData(lllllIlIlIlIIII);
                    }
                    catch (Exception lllllIlIIIIlllI) {
                        if (!lllllIlIIIIllIl.logErrors) break block2;
                        lllllIlIIIIllIl.errorLogger.accept("Could not submit bStats metrics data", lllllIlIIIIlllI);
                    }
                }
            });
        }

        private void sendData(JsonObjectBuilder.JsonObject lllllIlIIlllIlI) throws Exception {
            MetricsBase lllllIlIIlllIll;
            if (lllllIlIIlllIll.logSentData) {
                lllllIlIIlllIll.infoLogger.accept(String.valueOf(new StringBuilder().append("Sent bStats metrics data: ").append(lllllIlIIlllIlI.toString())));
            }
            String lllllIlIIlllIIl = String.format("https://bStats.org/api/v2/data/%s", lllllIlIIlllIll.platform);
            HttpsURLConnection lllllIlIIlllIII = (HttpsURLConnection)new URL(lllllIlIIlllIIl).openConnection();
            byte[] lllllIlIIllIlll = MetricsBase.compress(lllllIlIIlllIlI.toString());
            lllllIlIIlllIII.setRequestMethod("POST");
            lllllIlIIlllIII.addRequestProperty("Accept", "application/json");
            lllllIlIIlllIII.addRequestProperty("Connection", "close");
            lllllIlIIlllIII.addRequestProperty("Content-Encoding", "gzip");
            lllllIlIIlllIII.addRequestProperty("Content-Length", String.valueOf(lllllIlIIllIlll.length));
            lllllIlIIlllIII.setRequestProperty("Content-Type", "application/json");
            lllllIlIIlllIII.setRequestProperty("User-Agent", "Metrics-Service/1");
            lllllIlIIlllIII.setDoOutput(true);
            try (DataOutputStream lllllIlIIlllllI = new DataOutputStream(lllllIlIIlllIII.getOutputStream());){
                lllllIlIIlllllI.write(lllllIlIIllIlll);
            }
            StringBuilder lllllIlIIllIllI = new StringBuilder();
            try (BufferedReader lllllIlIIllllII = new BufferedReader(new InputStreamReader(lllllIlIIlllIII.getInputStream()));){
                String lllllIlIIllllIl;
                while ((lllllIlIIllllIl = lllllIlIIllllII.readLine()) != null) {
                    lllllIlIIllIllI.append(lllllIlIIllllIl);
                }
            }
            if (lllllIlIIlllIll.logResponseStatusText) {
                lllllIlIIlllIll.infoLogger.accept(String.valueOf(new StringBuilder().append("Sent data to bStats and received response: ").append((Object)lllllIlIIllIllI)));
            }
        }

        public MetricsBase(String lllllIllIIIIllI, String lllllIllIIIIlIl, int lllllIllIIIIlII, boolean lllllIllIIIIIll, Consumer<JsonObjectBuilder> lllllIllIIIIIlI, Consumer<JsonObjectBuilder> lllllIlIlllIIll, Consumer<Runnable> lllllIlIlllIIlI, Supplier<Boolean> lllllIlIlllIIIl, BiConsumer<String, Throwable> lllllIlIllllllI, Consumer<String> lllllIlIllIllll, boolean lllllIlIlllllII, boolean lllllIlIllIllIl, boolean lllllIlIllllIlI) {
            MetricsBase lllllIllIIIIlll;
            lllllIllIIIIlll.customCharts = new HashSet<CustomChart>();
            lllllIllIIIIlll.platform = lllllIllIIIIllI;
            lllllIllIIIIlll.serverUuid = lllllIllIIIIlIl;
            lllllIllIIIIlll.serviceId = lllllIllIIIIlII;
            lllllIllIIIIlll.enabled = lllllIllIIIIIll;
            lllllIllIIIIlll.appendPlatformDataConsumer = lllllIllIIIIIlI;
            lllllIllIIIIlll.appendServiceDataConsumer = lllllIlIlllIIll;
            lllllIllIIIIlll.submitTaskConsumer = lllllIlIlllIIlI;
            lllllIllIIIIlll.checkServiceEnabledSupplier = lllllIlIlllIIIl;
            lllllIllIIIIlll.errorLogger = lllllIlIllllllI;
            lllllIllIIIIlll.infoLogger = lllllIlIllIllll;
            lllllIllIIIIlll.logErrors = lllllIlIlllllII;
            lllllIllIIIIlll.logSentData = lllllIlIllIllIl;
            lllllIllIIIIlll.logResponseStatusText = lllllIlIllllIlI;
            lllllIllIIIIlll.checkRelocation();
            if (lllllIllIIIIIll) {
                lllllIllIIIIlll.startSubmitting();
            }
        }
    }

    public static class AdvancedPie
    extends CustomChart {
        private final /* synthetic */ Callable<Map<String, Integer>> callable;

        @Override
        protected JsonObjectBuilder.JsonObject getChartData() throws Exception {
            AdvancedPie llllIIIIlIllIIl;
            JsonObjectBuilder llllIIIIlIlllII = new JsonObjectBuilder();
            Map<String, Integer> llllIIIIlIllIll = llllIIIIlIllIIl.callable.call();
            if (llllIIIIlIllIll == null || llllIIIIlIllIll.isEmpty()) {
                return null;
            }
            boolean llllIIIIlIllIlI = true;
            for (Map.Entry<String, Integer> llllIIIIlIllllI : llllIIIIlIllIll.entrySet()) {
                if (llllIIIIlIllllI.getValue() == 0) continue;
                llllIIIIlIllIlI = false;
                llllIIIIlIlllII.appendField(llllIIIIlIllllI.getKey(), llllIIIIlIllllI.getValue());
            }
            if (llllIIIIlIllIlI) {
                return null;
            }
            return new JsonObjectBuilder().appendField("values", llllIIIIlIlllII.build()).build();
        }

        public AdvancedPie(String llllIIIIllIIllI, Callable<Map<String, Integer>> llllIIIIllIlIII) {
            super(llllIIIIllIIllI);
            AdvancedPie llllIIIIllIIlll;
            llllIIIIllIIlll.callable = llllIIIIllIlIII;
        }
    }

    public static class JsonObjectBuilder {
        private /* synthetic */ StringBuilder builder;
        private /* synthetic */ boolean hasAtLeastOneField;

        public JsonObjectBuilder() {
            JsonObjectBuilder llllIIIIIlIllll;
            llllIIIIIlIllll.builder = new StringBuilder();
            llllIIIIIlIllll.hasAtLeastOneField = false;
            llllIIIIIlIllll.builder.append("{");
        }

        public JsonObjectBuilder appendField(String lllIlllllllllII, int[] lllIlllllllIlll) {
            JsonObjectBuilder lllIllllllllIIl;
            if (lllIlllllllIlll == null) {
                throw new IllegalArgumentException("JSON values must not be null");
            }
            String lllIllllllllIlI = Arrays.stream(lllIlllllllIlll).mapToObj(String::valueOf).collect(Collectors.joining(","));
            lllIllllllllIIl.appendFieldUnescaped(lllIlllllllllII, String.valueOf(new StringBuilder().append("[").append(lllIllllllllIlI).append("]")));
            return lllIllllllllIIl;
        }

        private void appendFieldUnescaped(String lllIllllllIIIlI, String lllIllllllIIlII) {
            JsonObjectBuilder lllIllllllIIIll;
            if (lllIllllllIIIll.builder == null) {
                throw new IllegalStateException("JSON has already been built");
            }
            if (lllIllllllIIIlI == null) {
                throw new IllegalArgumentException("JSON key must not be null");
            }
            if (lllIllllllIIIll.hasAtLeastOneField) {
                lllIllllllIIIll.builder.append(",");
            }
            lllIllllllIIIll.builder.append("\"").append(JsonObjectBuilder.escape(lllIllllllIIIlI)).append("\":").append(lllIllllllIIlII);
            lllIllllllIIIll.hasAtLeastOneField = true;
        }

        private static String escape(String lllIlllllIlIIlI) {
            StringBuilder lllIlllllIlIIll = new StringBuilder();
            for (int lllIlllllIlIlIl = 0; lllIlllllIlIlIl < lllIlllllIlIIlI.length(); ++lllIlllllIlIlIl) {
                char lllIlllllIlIllI = lllIlllllIlIIlI.charAt(lllIlllllIlIlIl);
                if (lllIlllllIlIllI == '\"') {
                    lllIlllllIlIIll.append("\\\"");
                    continue;
                }
                if (lllIlllllIlIllI == '\\') {
                    lllIlllllIlIIll.append("\\\\");
                    continue;
                }
                if (lllIlllllIlIllI <= '\u000f') {
                    lllIlllllIlIIll.append("\\u000").append(Integer.toHexString(lllIlllllIlIllI));
                    continue;
                }
                if (lllIlllllIlIllI <= '\u001f') {
                    lllIlllllIlIIll.append("\\u00").append(Integer.toHexString(lllIlllllIlIllI));
                    continue;
                }
                lllIlllllIlIIll.append(lllIlllllIlIllI);
            }
            return String.valueOf(lllIlllllIlIIll);
        }

        public JsonObjectBuilder appendNull(String llllIIIIIlIlIIl) {
            JsonObjectBuilder llllIIIIIlIllII;
            llllIIIIIlIllII.appendFieldUnescaped(llllIIIIIlIlIIl, "null");
            return llllIIIIIlIllII;
        }

        public JsonObjectBuilder appendField(String llllIIIIIIllIll, int llllIIIIIIllIlI) {
            JsonObjectBuilder llllIIIIIIlllII;
            llllIIIIIIlllII.appendFieldUnescaped(llllIIIIIIllIll, String.valueOf(llllIIIIIIllIlI));
            return llllIIIIIIlllII;
        }

        public JsonObjectBuilder appendField(String llllIIIIIlIIIIl, String llllIIIIIlIIIII) {
            JsonObjectBuilder llllIIIIIlIIlIl;
            if (llllIIIIIlIIIII == null) {
                throw new IllegalArgumentException("JSON value must not be null");
            }
            llllIIIIIlIIlIl.appendFieldUnescaped(llllIIIIIlIIIIl, String.valueOf(new StringBuilder().append("\"").append(JsonObjectBuilder.escape(llllIIIIIlIIIII)).append("\"")));
            return llllIIIIIlIIlIl;
        }

        public JsonObjectBuilder appendField(String llllIIIIIIIIlII, String[] llllIIIIIIIIIll) {
            JsonObjectBuilder llllIIIIIIIlIIl;
            if (llllIIIIIIIIIll == null) {
                throw new IllegalArgumentException("JSON values must not be null");
            }
            String llllIIIIIIIIllI = Arrays.stream(llllIIIIIIIIIll).map(lllIlllllIIllII -> String.valueOf(new StringBuilder().append("\"").append(JsonObjectBuilder.escape(lllIlllllIIllII)).append("\""))).collect(Collectors.joining(","));
            llllIIIIIIIlIIl.appendFieldUnescaped(llllIIIIIIIIlII, String.valueOf(new StringBuilder().append("[").append(llllIIIIIIIIllI).append("]")));
            return llllIIIIIIIlIIl;
        }

        public JsonObject build() {
            JsonObjectBuilder lllIlllllIlllII;
            if (lllIlllllIlllII.builder == null) {
                throw new IllegalStateException("JSON has already been built");
            }
            JsonObject lllIlllllIlllIl = new JsonObject(String.valueOf(lllIlllllIlllII.builder.append("}")));
            lllIlllllIlllII.builder = null;
            return lllIlllllIlllIl;
        }

        public JsonObjectBuilder appendField(String llllIIIIIIlIIlI, JsonObject llllIIIIIIIlllI) {
            JsonObjectBuilder llllIIIIIIlIIll;
            if (llllIIIIIIIlllI == null) {
                throw new IllegalArgumentException("JSON object must not be null");
            }
            llllIIIIIIlIIll.appendFieldUnescaped(llllIIIIIIlIIlI, llllIIIIIIIlllI.toString());
            return llllIIIIIIlIIll;
        }

        public JsonObjectBuilder appendField(String lllIllllllIllII, JsonObject[] lllIllllllIlIll) {
            JsonObjectBuilder lllIlllllllIIIl;
            if (lllIllllllIlIll == null) {
                throw new IllegalArgumentException("JSON values must not be null");
            }
            String lllIllllllIlllI = Arrays.stream(lllIllllllIlIll).map(JsonObject::toString).collect(Collectors.joining(","));
            lllIlllllllIIIl.appendFieldUnescaped(lllIllllllIllII, String.valueOf(new StringBuilder().append("[").append(lllIllllllIlllI).append("]")));
            return lllIlllllllIIIl;
        }

        public static class JsonObject {
            private final /* synthetic */ String value;

            public String toString() {
                JsonObject llllIIlIIlIIIIl;
                return llllIIlIIlIIIIl.value;
            }

            private JsonObject(String llllIIlIIlIIIll) {
                JsonObject llllIIlIIlIIlII;
                llllIIlIIlIIlII.value = llllIIlIIlIIIll;
            }
        }
    }
}

