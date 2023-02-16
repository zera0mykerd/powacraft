/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.event.ClientConnectEvent
 *  net.md_5.bungee.api.event.ServerConnectedEvent
 *  net.md_5.bungee.api.plugin.Listener
 *  net.md_5.bungee.event.EventHandler
 */
package de.timeox2k.diamondantibot.handler.events;

import de.timeox2k.diamondantibot.handler.ConnectionHandler;
import java.util.LinkedList;
import net.md_5.bungee.api.event.ClientConnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ConnectedEvent
implements Listener {
    private final /* synthetic */ LinkedList<String> pingCache;
    public static /* synthetic */ int cps;
    private final /* synthetic */ LinkedList<String> whitelisted;

    static {
        cps = 0;
    }

    @EventHandler
    public void on(ServerConnectedEvent llllIlllIllIlll) {
        ConnectedEvent llllIlllIlllIII;
        String llllIlllIlllIIl = llllIlllIllIlll.getPlayer().getAddress().getAddress().getHostAddress();
        if (!llllIlllIlllIII.whitelisted.contains(llllIlllIlllIIl)) {
            llllIlllIlllIII.whitelisted.add(llllIlllIlllIIl);
        }
    }

    @EventHandler
    public void on(ClientConnectEvent llllIlllIllIIII) {
        ConnectedEvent llllIlllIllIIIl;
        String[] llllIlllIlIllll = llllIlllIllIIII.getSocketAddress().toString().replace("/", "").split(":");
        String llllIlllIlIlllI = llllIlllIlIllll[0];
        ++cps;
        if (llllIlllIllIIII.isCancelled()) {
            return;
        }
        if (cps > 100 && !llllIlllIllIIIl.whitelisted.contains(llllIlllIlIlllI)) {
            llllIlllIllIIII.setCancelled(true);
            ConnectionHandler.blockLocal(llllIlllIlIlllI);
            return;
        }
        if (!llllIlllIllIIIl.pingCache.contains(llllIlllIlIlllI)) {
            ConnectionHandler.blockLocal(llllIlllIlIlllI);
            llllIlllIllIIII.setCancelled(true);
            llllIlllIllIIIl.pingCache.add(llllIlllIlIlllI);
        }
    }

    public ConnectedEvent() {
        ConnectedEvent llllIlllIllllll;
        llllIlllIllllll.pingCache = new LinkedList();
        llllIlllIllllll.whitelisted = new LinkedList();
    }
}

