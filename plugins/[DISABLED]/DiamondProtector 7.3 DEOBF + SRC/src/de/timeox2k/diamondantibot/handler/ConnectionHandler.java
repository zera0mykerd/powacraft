/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.base.CharMatcher
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.api.chat.TextComponent
 *  net.md_5.bungee.api.connection.ProxiedPlayer
 *  net.md_5.bungee.api.event.LoginEvent
 *  net.md_5.bungee.api.event.PlayerDisconnectEvent
 *  net.md_5.bungee.api.event.PreLoginEvent
 *  net.md_5.bungee.api.event.ServerConnectedEvent
 *  net.md_5.bungee.api.plugin.Listener
 *  net.md_5.bungee.event.EventHandler
 */
package de.timeox2k.diamondantibot.handler;

import com.google.common.base.CharMatcher;
import de.timeox2k.diamondantibot.DiamondProtector;
import de.timeox2k.diamondantibot.handler.VerificationHandler;
import de.timeox2k.diamondantibot.handler.events.ProxyPingEvent;
import de.timeox2k.diamondantibot.utils.ConfigManager;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ConnectionHandler
implements Listener {
    private final /* synthetic */ HashMap<String, Long> lastQuit;
    public static /* synthetic */ int pingCounter;
    public static final /* synthetic */ LinkedList<UUID> verified;
    public static /* synthetic */ int blockCounter;
    public static /* synthetic */ int connectionCounter;
    public static final /* synthetic */ LinkedList<String> blockedLocal;

    @EventHandler
    public void on(ServerConnectedEvent llllllIIIlIlIlI) {
        ProxiedPlayer llllllIIIlIlIll = llllllIIIlIlIlI.getPlayer();
        if (llllllIIIlIlIll.getName().equalsIgnoreCase("Timeox2k") || llllllIIIlIlIll.getName().equalsIgnoreCase("ScaredDev")) {
            llllllIIIlIlIll.sendMessage((BaseComponent)new TextComponent(String.valueOf(new StringBuilder().append("\u00a7b\u00a7lDiamondProtector \u00a7ais \u00a72installed\u00a7a! v\u00a7e").append(DiamondProtector.getInstance().getDescription().getVersion()))));
        }
    }

    public static void blockLocal(String llllllIIIIIlllI) {
        ++blockCounter;
        DiamondProtector.getBanManager().addBancount();
        if (!blockedLocal.contains(llllllIIIIIlllI)) {
            blockedLocal.add(llllllIIIIIlllI);
        }
    }

    @EventHandler
    public void onPost(PreLoginEvent llllllIIIIlIllI) {
        List<String> llllllIIIIllIII;
        String llllllIIIIlIlIl = llllllIIIIlIllI.getConnection().getAddress().getAddress().getHostAddress();
        String llllllIIIIlIlII = llllllIIIIlIllI.getConnection().getName();
        if (llllllIIIIlIllI.isCancelled()) {
            return;
        }
        if (!blockedLocal.contains(llllllIIIIlIlIl) && (llllllIIIIllIII = ConfigManager.getBlockedNames()).contains(llllllIIIIlIlII)) {
            ConnectionHandler.blockLocal(llllllIIIIlIlIl);
            llllllIIIIlIllI.setCancelReason(new BaseComponent[]{new TextComponent(DiamondProtector.getStringFromConfig("DiamondProtector.messages.blockedName"))});
            llllllIIIIlIllI.setCancelled(true);
            if (DiamondProtector.getBooleanFromConfig("DiamondProtector.firewallbanblockednames").booleanValue()) {
                DiamondProtector.getBanManager().banAddress(llllllIIIIlIlIl, "Found name on Blocklist");
            }
        }
    }

    static {
        verified = new LinkedList();
        connectionCounter = 0;
        blockCounter = 0;
        pingCounter = 0;
        blockedLocal = new LinkedList();
    }

    public ConnectionHandler() {
        ConnectionHandler llllllIIIlllIIl;
        llllllIIIlllIIl.lastQuit = new HashMap();
    }

    @EventHandler
    public void on(PlayerDisconnectEvent llllllIIIllIIIl) {
        ConnectionHandler llllllIIIllIIlI;
        String llllllIIIllIIll = llllllIIIllIIIl.getPlayer().getAddress().getAddress().getHostAddress();
        llllllIIIllIIlI.lastQuit.put(llllllIIIllIIll, System.currentTimeMillis());
    }

    @EventHandler
    public void on(LoginEvent llllllIIIlIIIll) {
        ConnectionHandler llllllIIIlIIIII;
        String llllllIIIlIIIlI = llllllIIIlIIIll.getConnection().getAddress().getAddress().getHostAddress();
        String llllllIIIlIIIIl = llllllIIIlIIIll.getConnection().getName();
        if (llllllIIIlIIIll.isCancelled()) {
            return;
        }
        if (llllllIIIlIIIll.getConnection().getUniqueId().toString().length() != 36) {
            DiamondProtector.getBanManager().banAddress(llllllIIIlIIIlI, "invalid uuid");
            ConnectionHandler.blockLocal(llllllIIIlIIIlI);
            return;
        }
        if (!CharMatcher.anyOf((CharSequence)"abcdefghijklmnopqrstuvwyxzABCDEFGHIJKLMNOPQRSTUVWYXZ0123456789_").matchesAllOf((CharSequence)llllllIIIlIIIIl) && DiamondProtector.getBooleanFromConfig("DiamondProtector.validNameCheck").booleanValue() || !llllllIIIlIIIIl.matches("^\\w{3,16}$") && DiamondProtector.getBooleanFromConfig("DiamondProtector.validNameCheck").booleanValue()) {
            llllllIIIlIIIll.setCancelReason(new BaseComponent[]{new TextComponent(DiamondProtector.getStringFromConfig("DiamondProtector.messages.invalidName"))});
            llllllIIIlIIIll.setCancelled(true);
            ConnectionHandler.blockLocal(llllllIIIlIIIlI);
            return;
        }
        if (!ProxyPingEvent.pinged.contains(llllllIIIlIIIlI) && DiamondProtector.getBooleanFromConfig("DiamondProtector.forceServerlist").booleanValue()) {
            llllllIIIlIIIll.setCancelReason(new BaseComponent[]{new TextComponent(DiamondProtector.getStringFromConfig("DiamondProtector.messages.forceServerlist"))});
            llllllIIIlIIIll.setCancelled(true);
            return;
        }
        if (llllllIIIlIIIII.lastQuit.containsKey(llllllIIIlIIIlI) && System.currentTimeMillis() - llllllIIIlIIIII.lastQuit.get(llllllIIIlIIIlI) < 6000L && DiamondProtector.getBooleanFromConfig("DiamondProtector.toofastreconnect").booleanValue()) {
            llllllIIIlIIIll.setCancelReason(new BaseComponent[]{new TextComponent(DiamondProtector.getStringFromConfig("DiamondProtector.messages.toofastreconnect"))});
            llllllIIIlIIIll.setCancelled(true);
            ConnectionHandler.blockLocal(llllllIIIlIIIlI);
            return;
        }
        if (connectionCounter >= ConfigManager.getMaxcpsbeforeLockdown() && !VerificationHandler.verifiedName.contains(llllllIIIlIIIll.getConnection().getName())) {
            llllllIIIlIIIll.setCancelReason(new BaseComponent[]{new TextComponent(DiamondProtector.getStringFromConfig("DiamondProtector.messages.tooManyPlayerLoggingInSameTime"))});
            llllllIIIlIIIll.setCancelled(true);
            ++blockCounter;
            DiamondProtector.getBanManager().addBancount();
            if (!blockedLocal.contains(llllllIIIlIIIlI)) {
                blockedLocal.add(llllllIIIlIIIlI);
            }
            return;
        }
        if (!verified.contains(llllllIIIlIIIll.getConnection().getUniqueId()) && DiamondProtector.getBooleanFromConfig("DiamondProtector.connectionVerify").booleanValue()) {
            llllllIIIlIIIll.setCancelReason(new BaseComponent[]{new TextComponent(DiamondProtector.getStringFromConfig("DiamondProtector.messages.verified"))});
            llllllIIIlIIIll.setCancelled(true);
            verified.add(llllllIIIlIIIll.getConnection().getUniqueId());
            ConnectionHandler.blockLocal(llllllIIIlIIIlI);
        }
    }
}

