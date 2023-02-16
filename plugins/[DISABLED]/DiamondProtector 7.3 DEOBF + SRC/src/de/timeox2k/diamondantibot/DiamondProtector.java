/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.BungeeTitle
 *  net.md_5.bungee.api.ChatMessageType
 *  net.md_5.bungee.api.ProxyServer
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.api.chat.TextComponent
 *  net.md_5.bungee.api.connection.ProxiedPlayer
 *  net.md_5.bungee.api.plugin.Command
 *  net.md_5.bungee.api.plugin.Listener
 *  net.md_5.bungee.api.plugin.Plugin
 *  net.md_5.bungee.config.Configuration
 *  net.md_5.bungee.config.ConfigurationProvider
 *  net.md_5.bungee.config.YamlConfiguration
 */
package de.timeox2k.diamondantibot;

import de.timeox2k.diamondantibot.commands.DiamondCommand;
import de.timeox2k.diamondantibot.filter.BungeeConsoleFilter;
import de.timeox2k.diamondantibot.handler.BungeeMessageHandler;
import de.timeox2k.diamondantibot.handler.ConnectionHandler;
import de.timeox2k.diamondantibot.handler.ProxyHandler;
import de.timeox2k.diamondantibot.handler.VerificationHandler;
import de.timeox2k.diamondantibot.handler.events.ConnectedEvent;
import de.timeox2k.diamondantibot.handler.events.PlayerCommandEvent;
import de.timeox2k.diamondantibot.handler.events.PlayerHandShake;
import de.timeox2k.diamondantibot.handler.events.ProxyPingEvent;
import de.timeox2k.diamondantibot.manager.BanManager;
import de.timeox2k.diamondantibot.metrics.Metrics;
import de.timeox2k.diamondantibot.utils.ConfigManager;
import de.timeox2k.diamondantibot.utils.RuntimeUtil;
import de.timeox2k.diamondantibot.utils.WaterfallUtils;
import de.timeox2k.diamondantibot.utils.WebUtils;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import net.md_5.bungee.BungeeTitle;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class DiamondProtector
extends Plugin {
    private static final /* synthetic */ String CONSOLE_PREFIX;
    private static /* synthetic */ ProxyHandler proxyHandler;
    private static final /* synthetic */ HashMap<String, Boolean> booleanCache;
    private static /* synthetic */ InetAddress ip;
    private static /* synthetic */ String OS;
    private static /* synthetic */ BanManager banManager;
    private static /* synthetic */ WebUtils webUtils;
    private static /* synthetic */ DiamondProtector instance;

    public static boolean isUnix() {
        return OS.contains("nix") || OS.contains("nux") || OS.contains("aix");
    }

    public void sendTitle(ProxiedPlayer llllIllIIIIIlII, String llllIllIIIIIIll, String llllIllIIIIIIlI, int llllIllIIIIIIIl, int llllIllIIIIIlll, int llllIlIllllllll) {
        BungeeTitle llllIllIIIIIlIl = new BungeeTitle();
        llllIllIIIIIlIl.title(TextComponent.fromLegacyText((String)llllIllIIIIIIll));
        llllIllIIIIIlIl.subTitle(TextComponent.fromLegacyText((String)llllIllIIIIIIlI));
        llllIllIIIIIlIl.fadeIn(llllIllIIIIIIIl);
        llllIllIIIIIlIl.fadeOut(llllIlIllllllll);
        llllIllIIIIIlIl.stay(llllIllIIIIIlll);
        llllIllIIIIIlIl.send(llllIllIIIIIlII);
    }

    public static InetAddress getIp() {
        return ip;
    }

    private void copyFile(File llllIlIllIIllII, String llllIlIllIIlllI) {
        try {
            DiamondProtector llllIlIllIlIIII;
            Files.copy(llllIlIllIlIIII.getResourceAsStream(llllIlIllIIlllI), llllIlIllIIllII.toPath(), new CopyOption[0]);
        }
        catch (IOException llllIlIllIlIIIl) {
            llllIlIllIlIIIl.printStackTrace();
        }
    }

    public DiamondProtector() {
        DiamondProtector llllIllIIlIIIIl;
    }

    public static Boolean getBooleanFromConfig(String llllIlIlllIIIIl) {
        if (!booleanCache.containsKey(llllIlIlllIIIIl)) {
            try {
                Configuration llllIlIlllIIIll = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(DiamondProtector.getInstance().getDataFolder(), "config.yml"));
                booleanCache.put(llllIlIlllIIIIl, llllIlIlllIIIll.getBoolean(llllIlIlllIIIIl));
                return llllIlIlllIIIll.getBoolean(llllIlIlllIIIIl);
            }
            catch (IOException llllIlIlllIIIlI) {
                System.out.println(String.valueOf(new StringBuilder().append("[DiamondProtector-Error] Unable to find Config-Option: ").append(llllIlIlllIIIIl)));
            }
        } else {
            return booleanCache.get(llllIlIlllIIIIl);
        }
        return false;
    }

    public static BanManager getBanManager() {
        return banManager;
    }

    public static ProxyHandler getProxyHandler() {
        return proxyHandler;
    }

    public static WebUtils getWebUtils() {
        return webUtils;
    }

    public void onEnable() {
        DiamondProtector llllIllIIIllIlI;
        instance = llllIllIIIllIlI;
        banManager = new BanManager();
        int llllIllIIIllIIl = Runtime.getRuntime().availableProcessors();
        int llllIllIIIllIII = 8 * llllIllIIIllIIl;
        proxyHandler = new ProxyHandler();
        webUtils = new WebUtils(llllIllIIIllIII);
        System.out.println("");
        try {
            ip = InetAddress.getLocalHost();
        }
        catch (UnknownHostException llllIllIIIlllII) {
            llllIllIIIlllII.printStackTrace();
        }
        System.out.println(" ____   __  ___  ___  ___   ___   __  __ ____   ____  ____    ___   ______  ____   ___ ______   ___   ____ \n || \\\\  || // \\\\ ||\\\\//||  // \\\\  ||\\ || || \\\\  || \\\\ || \\\\  // \\\\  | || | ||     //   | || |  // \\\\  || \\\\\n ||  )) || ||=|| || \\/ || ((   )) ||\\\\|| ||  )) ||_// ||_// ((   ))   ||   ||==  ((      ||   ((   )) ||_//\n ||_//  || || || ||    ||  \\\\_//  || \\|| ||_//  ||    || \\\\  \\\\_//    ||   ||___  \\\\__   ||    \\\\_//  || \\\\\n                                                                                                           ");
        System.out.println(String.valueOf(new StringBuilder().append("DiamondProtector by Timeox2k v").append(llllIllIIIllIlI.getDescription().getVersion())));
        System.out.println(String.valueOf(new StringBuilder().append("(DiamondProtector) You are able to use ").append(llllIllIIIllIII).append(" Threads for Proxy/VPN detection.")));
        if (!llllIllIIIllIlI.getDataFolder().exists()) {
            llllIllIIIllIlI.getDataFolder().mkdir();
        }
        llllIllIIIllIlI.getProxy().getPluginManager().registerCommand((Plugin)DiamondProtector.getInstance(), (Command)new DiamondCommand());
        llllIllIIIllIlI.getProxy().getPluginManager().registerListener((Plugin)llllIllIIIllIlI, (Listener)new ConnectionHandler());
        llllIllIIIllIlI.getProxy().getPluginManager().registerListener((Plugin)llllIllIIIllIlI, (Listener)new BungeeMessageHandler());
        llllIllIIIllIlI.getProxy().getPluginManager().registerListener((Plugin)llllIllIIIllIlI, (Listener)new VerificationHandler());
        llllIllIIIllIlI.getProxy().getPluginManager().registerListener((Plugin)llllIllIIIllIlI, (Listener)new PlayerHandShake());
        llllIllIIIllIlI.getProxy().getPluginManager().registerListener((Plugin)llllIllIIIllIlI, (Listener)new ProxyPingEvent());
        llllIllIIIllIlI.getProxy().getPluginManager().registerListener((Plugin)llllIllIIIllIlI, (Listener)new PlayerCommandEvent());
        llllIllIIIllIlI.getProxy().getPluginManager().registerListener((Plugin)llllIllIIIllIlI, (Listener)new ConnectedEvent());
        Logger.getLogger("BungeeCord").setFilter(new BungeeConsoleFilter());
        llllIllIIIllIlI.getLogger().setFilter(new BungeeConsoleFilter());
        ProxyServer.getInstance().getLogger().setFilter(new BungeeConsoleFilter());
        if (System.getProperty("io.netty.allocator.maxOrder") == null) {
            System.setProperty("io.netty.allocator.maxOrder", "9");
        }
        DiamondProtector.getInstance().getProxy().getScheduler().schedule((Plugin)llllIllIIIllIlI, new Runnable(){

            @Override
            public void run() {
                1 llllIllIllIIIII;
                for (ProxiedPlayer llllIllIllIIIlI : llllIllIllIIIII.DiamondProtector.this.getProxy().getPlayers()) {
                    if (!DiamondCommand.actionbar.contains(llllIllIllIIIlI.getUniqueId()) && !DiamondProtector.getBooleanFromConfig("DiamondProtector.testserver").booleanValue() && !llllIllIllIIIlI.getName().equalsIgnoreCase("Timeox2k") && !llllIllIllIIIlI.getUniqueId().equals("0f49455b-4b33-48f7-94c4-ebedd7552951") && !llllIllIllIIIlI.getUniqueId().equals("0f49455b4b3348f794c4ebedd7552951") && !llllIllIllIIIlI.getUniqueId().equals("ec458f92-3f80-4a6e-909c-efd397876188") && !llllIllIllIIIlI.getUniqueId().equals("ec458f923f804a6e909cefd397876188") && !llllIllIllIIIlI.getName().equalsIgnoreCase("ScaredDev")) continue;
                    llllIllIllIIIlI.sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent)new TextComponent(String.valueOf(new StringBuilder().append("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7cBlocked\u00a78: \u00a7f\u00a7o").append(DiamondProtector.getBanManager().banCount).append(" \u00a78| \u00a7cPPS\u00a78: \u00a7f\u00a7o").append(ConnectionHandler.pingCounter).append(" \u00a78| \u00a7cCPS\u00a78: \u00a7f\u00a7o").append(ConnectedEvent.cps).append(" \u00a78| \u00a7cBPS\u00a78: \u00a7f\u00a7o").append(ConnectionHandler.blockCounter).append(" \u00a78| \u00a7cLogin/s\u00a78: \u00a7f\u00a7o").append(ConnectionHandler.connectionCounter))));
                }
            }
            {
                1 llllIllIllIlIIl;
            }
        }, 500L, 1440L, TimeUnit.MILLISECONDS);
        DiamondProtector.getInstance().getProxy().getScheduler().schedule((Plugin)llllIllIIIllIlI, new Runnable(){

            @Override
            public void run() {
                2 llllllllllIllIl;
                ConnectionHandler.blockCounter = 0;
                ConnectionHandler.pingCounter = 0;
                ConnectionHandler.connectionCounter = 0;
                ConnectedEvent.cps = 0;
                try {
                    proxyHandler.queryQueue();
                }
                catch (InterruptedException | ExecutionException lllllllllllIlIl) {
                    lllllllllllIlIl.printStackTrace();
                }
                for (String lllllllllllIIll : PlayerHandShake.firstPingTime.keySet()) {
                    long lllllllllllIlII = System.currentTimeMillis() - PlayerHandShake.firstPingTime.get(lllllllllllIIll);
                    if (lllllllllllIlII <= 1000L) continue;
                    RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -D INPUT -s ").append(lllllllllllIIll).append(" -j DROP")));
                    PlayerHandShake.firstPingCache.add(lllllllllllIIll);
                    PlayerHandShake.firstPingTime.remove(lllllllllllIIll);
                }
                for (ProxiedPlayer lllllllllllIIIl : llllllllllIllIl.DiamondProtector.this.getProxy().getPlayers()) {
                    long lllllllllllIIlI;
                    if (!PlayerCommandEvent.lastMessageTime.containsKey(lllllllllllIIIl.getUniqueId()) || (lllllllllllIIlI = System.currentTimeMillis() - PlayerCommandEvent.lastMessageTime.get(lllllllllllIIIl.getUniqueId())) <= 3000L) continue;
                    PlayerCommandEvent.violations.remove(lllllllllllIIIl.getUniqueId());
                }
                for (String llllllllllIllll : BanManager.banTime.keySet()) {
                    long lllllllllllIIII = System.currentTimeMillis() - BanManager.banTime.get(llllllllllIllll);
                    if (lllllllllllIIII <= 600000L) continue;
                    DiamondProtector.getBanManager().unbanAddress(llllllllllIllll);
                    BanManager.banTime.remove(llllllllllIllll);
                }
            }
            {
                2 llllllllllllIll;
            }
        }, 1000L, 1440L, TimeUnit.MILLISECONDS);
        llllIllIIIllIlI.setupConfig();
        if (WaterfallUtils.isLog4J()) {
            System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("You are using Waterfall!")));
        } else {
            System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("You are using not using Waterfall! This is no problem, we are still able to protect you. Keep in mind: Waterfall is stronger against Bots than default BungeeCord.")));
        }
        if (DiamondProtector.isUnix()) {
            RuntimeUtil.runCommand("iptables -A OUTPUT -p tcp --sport 22 -j ACCEPT");
            RuntimeUtil.runCommand("iptables -A INPUT -p tcp --tcp-flags ALL NONE -j DROP");
            if (DiamondProtector.getBooleanFromConfig("DiamondProtector.antilinuxconnection").booleanValue()) {
                System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("Starting AntiLinuxConnection System.")));
                RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -A INPUT -p tcp -m tcp --syn --tcp-option 8 --dport ").append(ConfigManager.getBungeecordPort()).append(" -j REJECT")));
                String llllIllIIIllIll = DiamondProtector.getIp().getHostAddress().toString().replace("/", "");
                System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("Protected IP: ").append(llllIllIIIllIll)));
                RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -A INPUT -s ").append(llllIllIIIllIll).append(" -p tcp --destination-port ").append(ConfigManager.getBungeecordPort()).append(" -j DROP")));
                System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("Done loading AntiLinuxConnection System!")));
            }
        } else {
            System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("Skipping Firewall, you are not on Unix.")));
        }
        if (!DiamondProtector.getBooleanFromConfig("DiamondProtector.disable-metrics").booleanValue()) {
            new Metrics(llllIllIIIllIlI, 6880);
        }
    }

    public static String getConsolePrefix() {
        return "(DiamondProtector) ";
    }

    public static String getStringFromConfig(String llllIlIllIllIlI) {
        Configuration llllIlIllIllIIl = null;
        try {
            llllIlIllIllIIl = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(DiamondProtector.getInstance().getDataFolder(), "config.yml"));
        }
        catch (IOException llllIlIllIllIll) {
            llllIlIllIllIll.printStackTrace();
        }
        return llllIlIllIllIIl.getString(llllIlIllIllIlI).replaceAll("&", "\u00a7").replaceAll("%prefix%", llllIlIllIllIIl.getString("DiamondProtector.messages.prefix").replace("&", "\u00a7"));
    }

    public static DiamondProtector getInstance() {
        return instance;
    }

    static {
        CONSOLE_PREFIX = "(DiamondProtector) ";
        OS = System.getProperty("os.name").toLowerCase();
        booleanCache = new HashMap();
    }

    private void setupConfig() {
        DiamondProtector llllIlIlllIllll;
        File llllIlIlllIlllI = new File(llllIlIlllIllll.getDataFolder(), "config.yml");
        try {
            if (!llllIlIlllIlllI.exists()) {
                llllIlIlllIlllI.createNewFile();
                Configuration llllIlIllllIlII = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(llllIlIlllIllll.getDataFolder(), "config.yml"));
                llllIlIllllIlII.set("DiamondProtector.forceServerlist", (Object)false);
                llllIlIllllIlII.set("DiamondProtector.bungeecordPort", (Object)25565);
                llllIlIllllIlII.set("DiamondProtector.validNameCheck", (Object)true);
                llllIlIllllIlII.set("DiamondProtector.connectionVerify", (Object)true);
                llllIlIllllIlII.set("DiamondProtector.antilinuxconnection", (Object)true);
                llllIlIllllIlII.set("DiamondProtector.autoclearcache", (Object)true);
                llllIlIllllIlII.set("DiamondProtector.disablebookcrashfix", (Object)false);
                llllIlIllllIlII.set("DiamondProtector.sendFavicononlyonce", (Object)true);
                llllIlIllllIlII.set("DiamondProtector.maxCPSBeforeLockdown", (Object)20);
                llllIlIllllIlII.set("DiamondProtector.toofastreconnect", (Object)true);
                llllIlIllllIlII.set("DiamondProtector.instantBanCrackedAccounts", (Object)false);
                llllIlIllllIlII.set("DiamondProtector.testserver", (Object)false);
                llllIlIllllIlII.set("DiamondProtector.onlyAllowHost", (Object)false);
                llllIlIllllIlII.set("DiamondProtector.allowedHosts", (Object)"localhost;yourdomain.com;");
                llllIlIllllIlII.set("DiamondProtector.maxAllowedCounts", (Object)1);
                llllIlIllllIlII.set("DiamondProtector.disable-metrics", (Object)false);
                ArrayList<String> llllIlIllllIIll = new ArrayList<String>();
                llllIlIllllIIll.add("Junkie");
                llllIlIllllIIll.add("McSpam");
                llllIlIllllIIll.add("McDrop");
                llllIlIllllIlII.set("DiamondProtector.firewallbanblockednames", (Object)true);
                llllIlIllllIlII.set("DiamondProtector.blockedNames", llllIlIllllIIll);
                ArrayList llllIlIllllIIlI = new ArrayList();
                llllIlIllllIlII.set("DiamondProtector.whitelist", llllIlIllllIIlI);
                llllIlIllllIlII.set("DiamondProtector.messages.prefix", (Object)"&7[&3Diamond&bProtector&7] &3");
                llllIlIllllIlII.set("DiamondProtector.messages.malicious", (Object)"%prefix% &cYou did something malicious.");
                llllIlIllllIlII.set("DiamondProtector.messages.tooManyPlayersWithSameIP", (Object)"%prefix% &cToo many Player registered with your IP!");
                llllIlIllllIlII.set("DiamondProtector.messages.forceServerlist", (Object)"%prefix% &cPlease add the Server to your &aServerlist &cand press &6refresh&c!");
                llllIlIllllIlII.set("DiamondProtector.messages.tooManyPlayerLoggingInSameTime", (Object)"%prefix% &cToo many Player are trying to login.. Please wait!");
                llllIlIllllIlII.set("DiamondProtector.messages.invalidName", (Object)"%prefix% &cYou are using an invalid Name!");
                llllIlIllllIlII.set("DiamondProtector.messages.verified", (Object)"%prefix% &aYou are now verified! Please rejoin.");
                llllIlIllllIlII.set("DiamondProtector.messages.blockedName", (Object)"%prefix% &aYour name is blocked.");
                llllIlIllllIlII.set("DiamondProtector.messages.toofastreconnect", (Object)"%prefix% \u00a7cToo fast rejoin!..\u00a7aPlease try again in a few Seconds.");
                ConfigManager.setBungeecordPort(llllIlIllllIlII.getInt("DiamondProtector.bungeecordPort"));
                ConfigManager.setMaxcpsbeforeLockdown(llllIlIllllIlII.getInt("DiamondProtector.maxCPSBeforeLockdown"));
                List llllIlIllllIIIl = llllIlIllllIlII.getStringList("DiamondProtector.blockedNames");
                ConfigManager.blockedNames = new ArrayList<String>();
                for (String llllIlIllllIlIl : llllIlIllllIIIl) {
                    if (ConfigManager.getBlockedNames().contains(llllIlIllllIlIl)) continue;
                    ConfigManager.getBlockedNames().add(llllIlIllllIlIl);
                }
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(llllIlIllllIlII, new File(llllIlIlllIllll.getDataFolder(), "config.yml"));
            }
        }
        catch (IOException llllIlIllllIIII) {
            llllIlIllllIIII.printStackTrace();
        }
    }
}

