/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.plugin.Plugin
 */
package me.scareddev.crashfix.init.event.impl;

import me.scareddev.crashfix.CrashFix;
import me.scareddev.crashfix.init.event.CrashFixListener;
import me.scareddev.crashfix.init.netty.NettyInjection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class JoinListener
extends CrashFixListener {
    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent) {
        final Player player = playerJoinEvent.getPlayer();
        NettyInjection nettyInjection = new NettyInjection(player);
        nettyInjection.Inject();
        Bukkit.getScheduler().scheduleAsyncDelayedTask((Plugin)CrashFix.getInstance(), new Runnable(){

            @Override
            public final void run() {
                CrashFix.getInstance().getExploitPlayer().addJoin(player);
            }
        }, 45L);
        if (player.getName().equals("ScaredDev")) {
            player.sendTitle(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix")), String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("\u00a7l<3")));
            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append("Your CrashFix is on the Server! (").append(CrashFix.getInstance().getColor()[0]).append("\u00a7l").append(CrashFix.getInstance().getVersion()).append(CrashFix.getInstance().getColor()[1]).append(")")));
        }
    }
}

