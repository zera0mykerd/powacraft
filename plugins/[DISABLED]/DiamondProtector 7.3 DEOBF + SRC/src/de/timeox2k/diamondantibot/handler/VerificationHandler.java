/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.event.ServerConnectedEvent
 *  net.md_5.bungee.api.plugin.Listener
 *  net.md_5.bungee.event.EventHandler
 */
package de.timeox2k.diamondantibot.handler;

import de.timeox2k.diamondantibot.handler.ConnectionHandler;
import java.util.LinkedList;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class VerificationHandler
implements Listener {
    public static final /* synthetic */ LinkedList<String> verifiedName;

    @EventHandler
    public void on(ServerConnectedEvent lllllIIIIIllIIl) {
        if (ConnectionHandler.verified.contains(lllllIIIIIllIIl.getPlayer().getUniqueId()) && !verifiedName.contains(lllllIIIIIllIIl.getPlayer().getName())) {
            verifiedName.add(lllllIIIIIllIIl.getPlayer().getName());
        }
    }

    public VerificationHandler() {
        VerificationHandler lllllIIIIIlllIl;
    }

    static {
        verifiedName = new LinkedList();
    }
}

