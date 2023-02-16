/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.api.chat.TextComponent
 *  net.md_5.bungee.api.event.PlayerHandshakeEvent
 *  net.md_5.bungee.api.plugin.Listener
 *  net.md_5.bungee.event.EventHandler
 */
package de.timeox2k.diamondantibot.handler.events;

import de.timeox2k.diamondantibot.DiamondProtector;
import de.timeox2k.diamondantibot.commands.DiamondCommand;
import de.timeox2k.diamondantibot.handler.ConnectionHandler;
import de.timeox2k.diamondantibot.handler.events.AntiAltModule;
import de.timeox2k.diamondantibot.utils.ConfigManager;
import de.timeox2k.diamondantibot.utils.RuntimeUtil;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerHandshakeEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerHandShake
implements Listener {
    public static /* synthetic */ ConcurrentHashMap<String, Long> firstPingTime;
    public static /* synthetic */ LinkedList<String> firstPingCache;

    @EventHandler
    public void on(PlayerHandshakeEvent llllIlIIllllIIl) {
        String llllIlIIllllIII = llllIlIIllllIIl.getConnection().getAddress().getAddress().getHostAddress();
        int llllIlIIlllIlll = llllIlIIllllIIl.getHandshake().getRequestedProtocol();
        String llllIlIIlllIllI = llllIlIIllllIIl.getHandshake().getHost();
        if (llllIlIIlllIlll == 1) {
            ++ConnectionHandler.connectionCounter;
        } else if (llllIlIIlllIlll == 2) {
            ++ConnectionHandler.pingCounter;
        } else {
            llllIlIIllllIIl.getConnection().disconnect(new BaseComponent[0]);
            DiamondProtector.getBanManager().banAddress(llllIlIIllllIII, "invalid protocol");
            ConnectionHandler.blockLocal(llllIlIIllllIII);
            return;
        }
        if (llllIlIIllllIIl.getConnection().isLegacy()) {
            llllIlIIllllIIl.getConnection().disconnect(new BaseComponent[0]);
            DiamondProtector.getBanManager().banAddress(llllIlIIllllIII, "LegacyCon");
            ConnectionHandler.blockLocal(llllIlIIllllIII);
        }
        if (llllIlIIllllIIl.getConnection().getUniqueId().toString().length() != 36) {
            llllIlIIllllIIl.getConnection().disconnect(new BaseComponent[0]);
            DiamondProtector.getBanManager().banAddress(llllIlIIllllIII, "Invalid UUID");
            ConnectionHandler.blockLocal(llllIlIIllllIII);
            return;
        }
        if (llllIlIIllllIIl.getConnection().getUniqueId() == null || llllIlIIllllIIl.getConnection().getName() == null) {
            llllIlIIllllIIl.getConnection().disconnect(new BaseComponent[0]);
            DiamondProtector.getBanManager().banAddress(llllIlIIllllIII, "Invalid ConnectionContent");
            ConnectionHandler.blockLocal(llllIlIIllllIII);
            return;
        }
        if (llllIlIIllllIIl.getConnection().getName().length() < 3 || llllIlIIllllIIl.getConnection().getName().length() >= 16) {
            llllIlIIllllIIl.getConnection().disconnect(new BaseComponent[0]);
            DiamondProtector.getBanManager().banAddress(llllIlIIllllIII, "Impossible Username");
            ConnectionHandler.blockLocal(llllIlIIllllIII);
            return;
        }
        if (llllIlIIllllIIl.getConnection().getVersion() >= 46 && llllIlIIllllIIl.getConnection().getVersion() <= 800) {
            llllIlIIllllIIl.getConnection().disconnect(new BaseComponent[0]);
            DiamondProtector.getBanManager().banAddress(llllIlIIllllIII, "Invalid version");
            ConnectionHandler.blockLocal(llllIlIIllllIII);
            return;
        }
        if (DiamondProtector.getBooleanFromConfig("DiamondProtector.instantBanCrackedAccounts").booleanValue() && !llllIlIIllllIIl.getConnection().isOnlineMode()) {
            llllIlIIllllIIl.getConnection().disconnect(new BaseComponent[0]);
            DiamondProtector.getBanManager().banAddress(llllIlIIllllIII, "wrong onlinemode");
            ConnectionHandler.blockLocal(llllIlIIllllIII);
        }
        if (llllIlIIllllIIl.getHandshake().getPort() != ConfigManager.getBungeecordPort() || llllIlIIllllIIl.getHandshake().getPort() > 65535) {
            llllIlIIllllIIl.getConnection().disconnect(new BaseComponent[0]);
            DiamondProtector.getBanManager().banAddress(llllIlIIllllIII, "invalid handshake");
            ConnectionHandler.blockLocal(llllIlIIllllIII);
            return;
        }
        if (DiamondProtector.getBooleanFromConfig("DiamondProtector.onlyAllowHost").booleanValue()) {
            String[] llllIlIIlllllll = DiamondProtector.getStringFromConfig("DiamondProtector.allowedHosts").split(";");
            boolean llllIlIIllllllI = false;
            for (String llllIlIlIIIIIII : llllIlIIlllllll) {
                if (!llllIlIIlllIllI.equalsIgnoreCase(llllIlIlIIIIIII)) continue;
                llllIlIIllllllI = true;
            }
            if (!llllIlIIllllllI) {
                llllIlIIllllIIl.getConnection().disconnect(new BaseComponent[0]);
                DiamondProtector.getBanManager().banAddress(llllIlIIllllIII, "invalid hostname");
                ConnectionHandler.blockLocal(llllIlIIllllIII);
                return;
            }
        }
        if (!firstPingTime.containsKey(llllIlIIllllIII) && !firstPingCache.contains(llllIlIIllllIII)) {
            RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -A INPUT -s ").append(llllIlIIllllIII).append(" -j DROP")));
            firstPingTime.put(llllIlIIllllIII, System.currentTimeMillis());
            ConnectionHandler.blockLocal(llllIlIIllllIII);
            return;
        }
        if (DiamondCommand.bannedList.contains(llllIlIIllllIII)) {
            llllIlIIllllIIl.getConnection().disconnect(new BaseComponent[0]);
            RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -A INPUT -s ").append(llllIlIIllllIII).append(" -j DROP")));
        }
        if (llllIlIIlllIlll == 1) {
            DiamondProtector.getProxyHandler().checkProxy(llllIlIIllllIII);
        }
        String llllIlIIlllIlIl = llllIlIIllllIIl.getConnection().getName();
        AntiAltModule.handleAccount(llllIlIIllllIII, llllIlIIlllIlIl);
        if (AntiAltModule.getAccountCountPerIP(llllIlIIllllIII) >= Integer.parseInt(DiamondProtector.getStringFromConfig("DiamondProtector.maxAllowedCounts")) - 1 && AntiAltModule.getAccountCountPerIP(llllIlIIllllIII) < 10) {
            ArrayList<String> llllIlIIlllllII = AntiAltModule.accountsPerIP.get(llllIlIIllllIII);
            StringBuilder llllIlIIllllIll = new StringBuilder();
            for (int llllIlIIlllllIl = 0; llllIlIIlllllIl < llllIlIIlllllII.size(); ++llllIlIIlllllIl) {
                llllIlIIllllIll.append(String.valueOf(new StringBuilder().append("\u00a77").append(llllIlIIlllllII.get(llllIlIIlllllIl)).append(" \u00a77: ")));
            }
            llllIlIIllllIIl.getConnection().disconnect((BaseComponent)new TextComponent(String.valueOf(new StringBuilder().append("\u00a7bDiamondProtector\n\nYou can only join with following Names:\n\u00a77").append(String.valueOf(llllIlIIllllIll)))));
            ConnectionHandler.blockLocal(llllIlIIllllIII);
        } else if (AntiAltModule.getAccountCountPerIP(llllIlIIllllIII) > 10) {
            llllIlIIllllIIl.getConnection().disconnect(new BaseComponent[0]);
            DiamondProtector.getBanManager().banAddress(llllIlIIllllIII, "invalid account-count");
            ConnectionHandler.blockLocal(llllIlIIllllIII);
        }
    }

    static {
        firstPingCache = new LinkedList();
        firstPingTime = new ConcurrentHashMap();
    }

    public PlayerHandShake() {
        PlayerHandShake llllIlIlIIIllII;
    }
}

