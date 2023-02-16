/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ServerPing
 *  net.md_5.bungee.api.event.ProxyPingEvent
 *  net.md_5.bungee.api.plugin.Listener
 *  net.md_5.bungee.event.EventHandler
 */
package de.timeox2k.diamondantibot.handler.events;

import de.timeox2k.diamondantibot.DiamondProtector;
import java.util.LinkedList;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPingEvent
implements Listener {
    public static final /* synthetic */ LinkedList<String> pinged;

    public ProxyPingEvent() {
        ProxyPingEvent llllIIllIIIlllI;
    }

    @EventHandler
    public void on(net.md_5.bungee.api.event.ProxyPingEvent llllIIllIIIIlIl) {
        String llllIIllIIIIllI = llllIIllIIIIlIl.getConnection().getAddress().getAddress().getHostAddress();
        if (DiamondProtector.getBooleanFromConfig("DiamondProtector.sendFavicononlyone").booleanValue() && pinged.contains(llllIIllIIIIllI)) {
            ServerPing llllIIllIIIlIIl = llllIIllIIIIlIl.getResponse();
            llllIIllIIIlIIl.setFavicon("");
            llllIIllIIIIlIl.setResponse(llllIIllIIIlIIl);
        }
        if (!pinged.contains(llllIIllIIIIllI) && DiamondProtector.getBooleanFromConfig("DiamondProtector.forceServerlist").booleanValue()) {
            pinged.add(llllIIllIIIIllI);
        }
    }

    static {
        pinged = new LinkedList();
    }
}

