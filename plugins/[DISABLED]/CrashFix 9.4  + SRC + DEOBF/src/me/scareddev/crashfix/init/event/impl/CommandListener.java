/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.player.PlayerCommandPreprocessEvent
 */
package me.scareddev.crashfix.init.event.impl;

import me.scareddev.crashfix.CrashFix;
import me.scareddev.crashfix.init.event.CrashFixListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener
extends CrashFixListener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        Player player = playerCommandPreprocessEvent.getPlayer();
        String string = playerCommandPreprocessEvent.getMessage();
        if (string == null) {
            return;
        }
        if (string.equalsIgnoreCase("me") || string.equalsIgnoreCase("calc") && !player.isOp()) {
            playerCommandPreprocessEvent.setCancelled(true);
            return;
        }
        if (string.split(" ")[0].contains(":") && !player.isOp()) {
            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append("Please don\u00b4t use : \u00a7cin your Commands!")));
            playerCommandPreprocessEvent.setCancelled(true);
        }
    }
}

