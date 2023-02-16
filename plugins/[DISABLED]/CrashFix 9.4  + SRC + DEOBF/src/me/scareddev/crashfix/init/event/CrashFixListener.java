/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 */
package me.scareddev.crashfix.init.event;

import me.scareddev.crashfix.CrashFix;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class CrashFixListener
implements Listener {
    public CrashFixListener() {
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)CrashFix.getInstance());
    }
}

