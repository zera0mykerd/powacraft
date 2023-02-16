/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.api.chat.TextComponent
 *  net.md_5.bungee.api.connection.ProxiedPlayer
 *  net.md_5.bungee.api.event.ChatEvent
 *  net.md_5.bungee.api.plugin.Listener
 *  net.md_5.bungee.event.EventHandler
 */
package de.timeox2k.diamondantibot.handler.events;

import de.timeox2k.diamondantibot.DiamondProtector;
import java.util.HashMap;
import java.util.UUID;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerCommandEvent
implements Listener {
    public static /* synthetic */ HashMap<UUID, Long> lastMessageTime;
    public static /* synthetic */ HashMap<UUID, Integer> violations;

    @EventHandler
    public void onChat(ChatEvent lllllIIIIIIIlll) {
        if (lllllIIIIIIIlll.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer lllllIIIIIIlIIl = (ProxiedPlayer)lllllIIIIIIIlll.getSender();
            if (lllllIIIIIIIlll.isCommand() || lllllIIIIIIIlll.isProxyCommand()) {
                if (lllllIIIIIIlIIl.hasPermission("DiamondProtector.bypassChat")) {
                    return;
                }
                if (lllllIIIIIIIlll.isCancelled() && lllllIIIIIIIlll.isProxyCommand() && (lllllIIIIIIIlll.getMessage().equalsIgnoreCase("/bungee") || lllllIIIIIIIlll.getMessage().equalsIgnoreCase("/dp") || lllllIIIIIIIlll.getMessage().equalsIgnoreCase("/dp") || lllllIIIIIIIlll.getMessage().equalsIgnoreCase("/diamondprotector") || lllllIIIIIIIlll.getMessage().equalsIgnoreCase("/antibot") || lllllIIIIIIIlll.getMessage().equalsIgnoreCase("/flamecord"))) {
                    DiamondProtector.getInstance().sendTitle(lllllIIIIIIlIIl, String.valueOf(new StringBuilder().append("\u00a7bDiamondProtector v").append(DiamondProtector.getInstance().getDescription().getVersion())), "\u00a77By \u00a7cTimeox2k", 20, 20, 20);
                    lllllIIIIIIlIIl.sendMessage((BaseComponent)new TextComponent("\u00a78\u00a7m--------\u00a7b\u00a7lDiamondProtector\u00a78\u00a7m--------"));
                    lllllIIIIIIlIIl.sendMessage((BaseComponent)new TextComponent(String.valueOf(new StringBuilder().append("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a77This Server is protected by \u00a7cDiamond\u00a7bProtector \u00a77v\u00a76").append(DiamondProtector.getInstance().getDescription().getVersion()).append(" \u00a77by \u00a7cTimeox2k\u00a77. \u00a77The \u00a77next \u00a77Generation \u00a77of \u00a77AntiBot \u00a77against \u00a77NullPing\u00a77, \u00a77DDoS \u00a77(\u00a77Automatic \u00a77Iptables\u00a77-\u00a77Setup\u00a77)"))));
                    lllllIIIIIIlIIl.sendMessage((BaseComponent)new TextComponent("\u00a78\u00a7m--------\u00a7b\u00a7lDiamondProtector\u00a78\u00a7m--------"));
                }
                if (!lastMessageTime.containsKey(lllllIIIIIIlIIl.getUniqueId())) {
                    lastMessageTime.put(lllllIIIIIIlIIl.getUniqueId(), System.currentTimeMillis());
                } else {
                    long lllllIIIIIIlIlI = System.currentTimeMillis() - lastMessageTime.get(lllllIIIIIIlIIl.getUniqueId());
                    if (lllllIIIIIIlIlI < 500L) {
                        if (lllllIIIIIIlIlI < 250L) {
                            if (!violations.containsKey(lllllIIIIIIlIIl.getUniqueId())) {
                                violations.put(lllllIIIIIIlIIl.getUniqueId(), 1);
                            } else {
                                violations.put(lllllIIIIIIlIIl.getUniqueId(), violations.get(lllllIIIIIIlIIl.getUniqueId()) + 1);
                                if (violations.get(lllllIIIIIIlIIl.getUniqueId()) > 5) {
                                    lllllIIIIIIlIIl.disconnect((BaseComponent)new TextComponent("\u00a77[\u00a7cDiamondProtector\u00a77]\n\n\u00a7cHow about not spamming Commands to crash the Proxy?"));
                                    for (ProxiedPlayer lllllIIIIIIlIll : DiamondProtector.getInstance().getProxy().getPlayers()) {
                                        if (!lllllIIIIIIlIll.hasPermission("diamondprotector.admin") && !lllllIIIIIIlIll.getName().equalsIgnoreCase("Timeox2k")) continue;
                                        lllllIIIIIIlIll.sendMessage((BaseComponent)new TextComponent(String.valueOf(new StringBuilder().append("\u00a77[\u00a7bDiamondProtector\u00a77] \u00a7cThe Player \u00a74").append(lllllIIIIIIlIIl.getName()).append(" \u00a7ctried to Crash the Server with \u00a74\u00a7lCommandFlood \u00a7cand got kicked. (Diff: ").append(lllllIIIIIIlIlI).append(")"))));
                                    }
                                }
                            }
                        }
                        lllllIIIIIIIlll.setCancelled(true);
                        lllllIIIIIIlIIl.sendMessage((BaseComponent)new TextComponent(DiamondProtector.getStringFromConfig("DiamondProtector.messages.malicious")));
                    }
                    lastMessageTime.put(lllllIIIIIIlIIl.getUniqueId(), System.currentTimeMillis());
                }
            }
        }
    }

    static {
        lastMessageTime = new HashMap();
        violations = new HashMap();
    }

    public PlayerCommandEvent() {
        PlayerCommandEvent lllllIIIIIlIIIl;
    }
}

