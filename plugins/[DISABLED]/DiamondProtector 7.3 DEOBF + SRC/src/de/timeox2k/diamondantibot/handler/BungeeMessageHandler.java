/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.api.chat.TextComponent
 *  net.md_5.bungee.api.connection.ProxiedPlayer
 *  net.md_5.bungee.api.event.PluginMessageEvent
 *  net.md_5.bungee.api.plugin.Listener
 *  net.md_5.bungee.api.plugin.Plugin
 *  net.md_5.bungee.event.EventHandler
 */
package de.timeox2k.diamondantibot.handler;

import de.timeox2k.diamondantibot.DiamondProtector;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class BungeeMessageHandler
implements Listener {
    private final /* synthetic */ String prefix = "\u00a77[\u00a73Diamond\u00a7bProtector\u00a77] \u00a73";
    private /* synthetic */ HashMap<String, Integer> packetCount;

    @EventHandler
    public void on(final PluginMessageEvent lllllllllIIlIlI) {
        if (!(lllllllllIIlIlI.getSender() instanceof ProxiedPlayer)) {
            return;
        }
        if (!DiamondProtector.getBooleanFromConfig("DiamondProtector.disablebookcrashfix").booleanValue()) {
            ProxiedPlayer lllllllllIlIIII = (ProxiedPlayer)lllllllllIIlIlI.getSender();
            final String lllllllllIIllll = lllllllllIIlIlI.getSender().getAddress().getAddress().getHostAddress();
            int lllllllllIIlllI = lllllllllIIlIlI.getTag().length();
            if (lllllllllIIlllI > 16 || lllllllllIIlllI < 3) {
                lllllllllIIlIlI.setCancelled(true);
                lllllllllIIlIlI.getSender().disconnect((BaseComponent)new TextComponent(DiamondProtector.getStringFromConfig("DiamondProtector.messages.malicious")));
                DiamondProtector.getBanManager().addBancount();
            }
            if (lllllllllIIlIlI.getTag().equalsIgnoreCase("MC|BEdit") || lllllllllIIlIlI.getTag().equalsIgnoreCase("MC|BSign")) {
                BungeeMessageHandler lllllllllIIllIl;
                if (!lllllllllIIllIl.packetCount.containsKey(lllllllllIIllll)) {
                    lllllllllIIllIl.packetCount.put(lllllllllIIllll, 1);
                    DiamondProtector.getInstance().getProxy().getScheduler().schedule((Plugin)DiamondProtector.getInstance(), new Runnable(){
                        {
                            1 lllIllIlIIlllIl;
                        }

                        @Override
                        public void run() {
                            1 lllIllIlIIlIlll;
                            if ((Integer)lllIllIlIIlIlll.BungeeMessageHandler.this.packetCount.get(lllIllIlIIlIlll.lllllllllIIllll) > 1) {
                                lllIllIlIIlIlll.lllllllllIIlIlI.getSender().disconnect((BaseComponent)new TextComponent(DiamondProtector.getStringFromConfig("DiamondProtector.messages.malicious")));
                                lllIllIlIIlIlll.lllllllllIIlIlI.setCancelled(true);
                                DiamondProtector.getBanManager().addBancount();
                            }
                            lllIllIlIIlIlll.BungeeMessageHandler.this.packetCount.remove(lllIllIlIIlIlll.lllllllllIIllll);
                        }
                    }, 700L, TimeUnit.MILLISECONDS);
                } else {
                    lllllllllIIllIl.packetCount.put(lllllllllIIllll, lllllllllIIllIl.packetCount.get(lllllllllIIllll) + 1);
                }
            }
        }
    }

    public BungeeMessageHandler() {
        BungeeMessageHandler lllllllllIlIllI;
        lllllllllIlIllI.packetCount = new HashMap();
        lllllllllIlIllI.prefix = "\u00a77[\u00a73Diamond\u00a7bProtector\u00a77] \u00a73";
    }
}

