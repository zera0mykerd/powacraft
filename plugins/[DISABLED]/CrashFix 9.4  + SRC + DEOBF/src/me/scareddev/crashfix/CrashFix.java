/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  org.bukkit.Bukkit
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.java.JavaPlugin
 */
package me.scareddev.crashfix;

import io.netty.channel.Channel;
import me.scareddev.crashfix.init.CrashFixCommand;
import me.scareddev.crashfix.init.event.CrashFixEventManager;
import me.scareddev.crashfix.init.player.ExploitPlayer;
import me.scareddev.crashfix.init.reflection.ServerTPS;
import me.scareddev.crashfix.init.runnable.ClearTask;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CrashFix
extends JavaPlugin {
    private final /* synthetic */ String[] Color;
    private /* synthetic */ String Design;
    private final /* synthetic */ ExploitPlayer exploitPlayer;
    public static /* synthetic */ CrashFix Instance;

    public final ExploitPlayer getExploitPlayer() {
        return this.exploitPlayer;
    }

    public final String getPrefix() {
        return this.Design;
    }

    public final void sendMessage(String string) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.hasPermission("anticrash.notify") || player.getName().equals("ScaredDev")) {
                player.sendMessage(String.valueOf(new StringBuilder().append(this.getPrefix()).append(string)));
            }
        });
    }

    public final void onDisable() {
        Bukkit.getScheduler().cancelTasks((Plugin)CrashFix.getInstance());
    }

    public final String[] getColor() {
        return this.Color;
    }

    public final ServerTPS getServerTps() {
        return new ServerTPS();
    }

    public final boolean Books() {
        return CrashFix.getInstance().getConfig().getBoolean("settings.BookUse");
    }

    public final void onEnable() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            CraftPlayer craftPlayer = (CraftPlayer)player;
            Channel channel = craftPlayer.getHandle().playerConnection.networkManager.channel;
            if (channel.isOpen()) {
                channel.close();
            }
        });
        Instance = this;
        Bukkit.getScheduler().scheduleAsyncDelayedTask((Plugin)CrashFix.getInstance(), new Runnable(){

            @Override
            public final synchronized void run() {
                try {
                    block25: {
                        block24: {
                            block23: {
                                block22: {
                                    block21: {
                                        block20: {
                                            block19: {
                                                block18: {
                                                    block17: {
                                                        int n;
                                                        int n2;
                                                        block16: {
                                                            String string;
                                                            block15: {
                                                                block14: {
                                                                    block11: {
                                                                        block10: {
                                                                            block12: {
                                                                                block13: {
                                                                                    block9: {
                                                                                        CrashFix.this.getConfig().options().copyDefaults(true);
                                                                                        CrashFix.this.saveConfig();
                                                                                        string = CrashFix.this.getConfig().getString("design");
                                                                                        n2 = -1;
                                                                                        n = string.hashCode();
                                                                                        if (n == -2032180703) break block9;
                                                                                        if (n == -1955522002) break block10;
                                                                                        if (n == 2041946) break block11;
                                                                                        if (n == 2455926) break block12;
                                                                                        if (n == 68081379) break block13;
                                                                                        if (n == 295261985) break block14;
                                                                                        if (n == 1001698774) break block15;
                                                                                        break block16;
                                                                                    }
                                                                                    if (string.equals("DEFAULT")) {
                                                                                        n2 = 0;
                                                                                    }
                                                                                    break block16;
                                                                                }
                                                                                if (string.equals("GREEN")) {
                                                                                    n2 = 1;
                                                                                }
                                                                                break block16;
                                                                            }
                                                                            if (string.equals("PINK")) {
                                                                                n2 = 2;
                                                                            }
                                                                            break block16;
                                                                        }
                                                                        if (string.equals("ORANGE")) {
                                                                            n2 = 3;
                                                                        }
                                                                        break block16;
                                                                    }
                                                                    if (string.equals("BLUE")) {
                                                                        n2 = 4;
                                                                    }
                                                                    break block16;
                                                                }
                                                                if (string.equals("BLACK_AND_WHITE")) {
                                                                    n2 = 5;
                                                                }
                                                                break block16;
                                                            }
                                                            if (string.equals("GREEN_2")) {
                                                                n2 = 6;
                                                            }
                                                        }
                                                        n = n2;
                                                        if (n == 0) break block17;
                                                        if (n == 1) break block18;
                                                        if (n == 2) break block19;
                                                        if (n == 3) break block20;
                                                        if (n == 4) break block21;
                                                        if (n == 5) break block22;
                                                        if (n == 6) break block23;
                                                        break block24;
                                                    }
                                                    CrashFix.this.Design = "\u00a78\u00d7 \u00a74\u00a7lCrashFix \u00a78\u00bb \u00a7c";
                                                    ((CrashFix)CrashFix.this).Color[0] = "\u00a74";
                                                    ((CrashFix)CrashFix.this).Color[1] = "\u00a7c";
                                                    break block25;
                                                }
                                                CrashFix.this.Design = "\u00a78\u00d7 \u00a72\u00a7lCrashFix \u00a78\u00bb \u00a7a";
                                                ((CrashFix)CrashFix.this).Color[0] = "\u00a72";
                                                ((CrashFix)CrashFix.this).Color[1] = "\u00a7a";
                                                break block25;
                                            }
                                            CrashFix.this.Design = "\u00a78\u00d7 \u00a75\u00a7lCrashFix \u00a78\u00bb \u00a7d";
                                            ((CrashFix)CrashFix.this).Color[0] = "\u00a75";
                                            ((CrashFix)CrashFix.this).Color[1] = "\u00a7d";
                                            break block25;
                                        }
                                        CrashFix.this.Design = "\u00a78\u00d7 \u00a76\u00a7lCrashFix \u00a78\u00bb \u00a7e";
                                        ((CrashFix)CrashFix.this).Color[0] = "\u00a76";
                                        ((CrashFix)CrashFix.this).Color[1] = "\u00a7e";
                                        break block25;
                                    }
                                    CrashFix.this.Design = "\u00a78\u00d7 \u00a73\u00a7lCrashFix \u00a78\u00bb \u00a7b";
                                    ((CrashFix)CrashFix.this).Color[0] = "\u00a73";
                                    ((CrashFix)CrashFix.this).Color[1] = "\u00a7b";
                                    break block25;
                                }
                                CrashFix.this.Design = "\u00a78\u00d7 \u00a70\u00a7lCrashFix \u00a78\u00bb \u00a7f";
                                ((CrashFix)CrashFix.this).Color[0] = "\u00a70";
                                ((CrashFix)CrashFix.this).Color[1] = "\u00a7f";
                                break block25;
                            }
                            CrashFix.this.Design = "\u00a78\u00d7 \u00a7a\u00a7lCrashFix \u00a78\u00bb \u00a77";
                            ((CrashFix)CrashFix.this).Color[0] = "\u00a7a";
                            ((CrashFix)CrashFix.this).Color[1] = "\u00a77";
                            break block25;
                        }
                        Bukkit.getConsoleSender().sendMessage(String.valueOf(new StringBuilder().append(CrashFix.this.getPrefix()).append("Invalid Design... set to Default.")));
                        CrashFix.this.Design = "\u00a78\u00d7 \u00a74\u00a7lCrashFix \u00a78\u00bb \u00a7c";
                        ((CrashFix)CrashFix.this).Color[0] = "\u00a74";
                        ((CrashFix)CrashFix.this).Color[1] = "\u00a7c";
                    }
                    new CrashFixEventManager();
                    CrashFix.this.getCommand("crashfix").setExecutor((CommandExecutor)new CrashFixCommand());
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    Bukkit.broadcastMessage((String)String.valueOf(new StringBuilder().append(CrashFix.this.getPrefix()).append("Error to load CrashFix. Please contact ScaredDev!")));
                }
                Bukkit.getConsoleSender().sendMessage(String.valueOf(new StringBuilder().append(CrashFix.this.getColor()[1]).append("\n _____               _    ______ _      \n/  __ \\             | |   |  ___(_)     \n| /  \\/_ __ __ _ ___| |__ | |_   ___  __\n| |   | '__/ _` / __| '_ \\|  _| | \\ \\/ /\n| \\__/\\ | | (_| \\__ \\ | | | |   | |>  < \n \\____/_|  \\__,_|___/_| |_\\_|   |_/_/\\_\\\n                                        \n  _              _____                        _ _____             \n | |            / ____|                      | |  __ \\            \n | |__  _   _  | (___   ___ __ _ _ __ ___  __| | |  | | _____   __\n | '_ \\| | | |  \\___ \\ / __/ _` | '__/ _ \\/ _` | |  | |/ _ \\ \\ / /\n | |_) | |_| |  ____) | (_| (_| | | |  __/ (_| | |__| |  __/\\ V / \n |_.__/ \\__, | |_____/ \\___\\__,_|_|  \\___|\\__,_|_____/ \\___| \\_/  \n         __/ |                                                    \n        |___/                                                     \n")));
            }
        }, 1L);
        Bukkit.getScheduler().scheduleAsyncRepeatingTask((Plugin)this, (Runnable)new ClearTask(), 0L, 20L);
    }

    public static CrashFix getInstance() {
        return Instance;
    }

    public CrashFix() {
        this.Design = "";
        this.Color = new String[]{"", ""};
        this.exploitPlayer = new ExploitPlayer();
    }

    public final String getVersion() {
        return CrashFix.getInstance().getDescription().getVersion();
    }
}

