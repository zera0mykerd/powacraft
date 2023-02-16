/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.CommandSender
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.api.chat.TextComponent
 *  net.md_5.bungee.api.connection.ProxiedPlayer
 *  net.md_5.bungee.api.plugin.Command
 */
package de.timeox2k.diamondantibot.commands;

import de.timeox2k.diamondantibot.DiamondProtector;
import de.timeox2k.diamondantibot.manager.WhitelistManager;
import de.timeox2k.diamondantibot.utils.ConfigManager;
import de.timeox2k.diamondantibot.utils.RuntimeUtil;
import de.timeox2k.diamondantibot.utils.Validator;
import java.util.LinkedList;
import java.util.UUID;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class DiamondCommand
extends Command {
    private final /* synthetic */ String prefix = "\u00a7b\u00a7lDiamondProtector \u00a78\u00bb ";
    public static /* synthetic */ LinkedList<UUID> actionbar;
    public static /* synthetic */ LinkedList<String> bannedList;

    static {
        actionbar = new LinkedList();
        bannedList = new LinkedList();
    }

    public DiamondCommand() {
        super("diamond", "", new String[]{"antibot", "diamondprotector", "antybot", "dp", "aegis", "flamecord", "2ls", "bungee", "waterfall", "firewall", "bungeecord"});
        DiamondCommand lllIlIlllIIIlll;
        lllIlIlllIIIlll.prefix = "\u00a7b\u00a7lDiamondProtector \u00a78\u00bb ";
    }

    public void execute(CommandSender lllIlIllIlllIll, String[] lllIlIllIlllIII) {
        if (lllIlIllIlllIll instanceof ProxiedPlayer) {
            ProxiedPlayer lllIlIllIllllll = (ProxiedPlayer)lllIlIllIlllIll;
            if (lllIlIllIllllll.hasPermission("diamondprotector.admin")) {
                if (lllIlIllIlllIII.length > 0) {
                    if (lllIlIllIlllIII[0].equalsIgnoreCase("flush")) {
                        RuntimeUtil.runCommand("iptables -F");
                        if (DiamondProtector.getBooleanFromConfig("DiamondProtector.antilinuxconnection").booleanValue()) {
                            RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -A INPUT -p tcp -m tcp --syn --tcp-option 8 --dport ").append(ConfigManager.getBungeecordPort()).append(" -j REJECT")));
                        }
                        lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7aYou flushed the IPTables"));
                    } else if (lllIlIllIlllIII[0].equalsIgnoreCase("ban")) {
                        if (lllIlIllIlllIII.length > 1) {
                            ProxiedPlayer lllIlIlllIIIIIl = DiamondProtector.getInstance().getProxy().getPlayer(lllIlIllIlllIII[1]);
                            if (lllIlIlllIIIIIl != null) {
                                RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -A INPUT -s ").append(lllIlIlllIIIIIl.getAddress().getAddress().getHostAddress()).append(" -j DROP")));
                                lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent(String.valueOf(new StringBuilder().append("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7aYou blocked the IP: \u00a7e").append(lllIlIllIlllIII[1]).append(" \u00a7afrom Player \u00a7e").append(lllIlIlllIIIIIl.getAddress().getAddress().getHostAddress()).append("\u00a7a!"))));
                                bannedList.add(lllIlIlllIIIIIl.getAddress().getAddress().getHostAddress());
                            } else if (Validator.validIP(lllIlIllIlllIII[1])) {
                                RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -A INPUT -s ").append(lllIlIllIlllIII[1]).append(" -j DROP")));
                                lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent(String.valueOf(new StringBuilder().append("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7aYou blocked the IP: \u00a7e").append(lllIlIllIlllIII[1]))));
                                bannedList.add(lllIlIllIlllIII[1]);
                            } else {
                                lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7cArgument is not valid or not a Player"));
                            }
                        } else {
                            lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp ban [IP/Onlineplayer] \u00a78- \u00a72Ban-IP."));
                        }
                    } else if (lllIlIllIlllIII[0].equalsIgnoreCase("unban")) {
                        if (lllIlIllIlllIII.length >= 1) {
                            RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -D INPUT -s ").append(lllIlIllIlllIII[1]).append(" -j DROP")));
                            if (bannedList.contains(lllIlIllIlllIII[1])) {
                                bannedList.remove(lllIlIllIlllIII[1]);
                            }
                            lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent(String.valueOf(new StringBuilder().append("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7aYou unblocked the IP: \u00a7e").append(lllIlIllIlllIII[1]))));
                        } else {
                            lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp unban [IP] \u00a78- \u00a72Unban-IP."));
                        }
                    } else if (lllIlIllIlllIII[0].equalsIgnoreCase("stats")) {
                        if (actionbar.contains(lllIlIllIllllll.getUniqueId())) {
                            actionbar.remove(lllIlIllIllllll.getUniqueId());
                            lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7cYou disabled the Stats."));
                        } else {
                            actionbar.add(lllIlIllIllllll.getUniqueId());
                            lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7aYou enabled the Stats."));
                        }
                    } else if (lllIlIllIlllIII[0].equalsIgnoreCase("whitelist")) {
                        if (lllIlIllIlllIII.length >= 1) {
                            if (Validator.validIP(lllIlIllIlllIII[1])) {
                                if (!WhitelistManager.isWhitelisted(lllIlIllIlllIII[1]).booleanValue()) {
                                    WhitelistManager.addToWhitelist(lllIlIllIlllIII[1]);
                                    lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent(String.valueOf(new StringBuilder().append("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7aAdded ").append(lllIlIllIlllIII[1]).append(" to whitelist."))));
                                } else {
                                    lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent(String.valueOf(new StringBuilder().append("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7aRemoved ").append(lllIlIllIlllIII[1]).append(" from whitelist."))));
                                    WhitelistManager.removeFromWhitelist(lllIlIllIlllIII[1]);
                                }
                            } else {
                                lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7cThis is not a valid IP."));
                            }
                        } else {
                            lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7c/dp whitelist [IP] - Whitelist IP"));
                        }
                    } else if (lllIlIllIlllIII[0].equalsIgnoreCase("lookup")) {
                        if (lllIlIllIlllIII.length > 1) {
                            ProxiedPlayer lllIlIlllIIIIII = DiamondProtector.getInstance().getProxy().getPlayer(lllIlIllIlllIII[1]);
                            if (lllIlIlllIIIIII != null) {
                                lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent(String.valueOf(new StringBuilder().append("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7aIP of ").append(lllIlIlllIIIIII.getName()).append("\u00a77> \u00a7e\u00a7l").append(lllIlIlllIIIIII.getAddress().getAddress().getHostAddress()))));
                            } else {
                                lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a7cPlayer is not online."));
                            }
                        } else {
                            lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp lookup [Player] \u00a78- \u00a72See IP of Player."));
                        }
                    } else {
                        lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a=====\u00a73Diamond\u00a7bProtector\u00a7a====="));
                        lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp flush \u00a78- \u00a72Flush iptables."));
                        lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp ban [IP] \u00a78- \u00a72Ban-IP."));
                        lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp unban [IP] \u00a78- \u00a72Unban-IP."));
                        lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp stats \u00a78- \u00a72Actionbar stats."));
                        lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp whitelist [IP] \u00a78- \u00a72Whitelist an IP."));
                        lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp lookup [Player] \u00a78- \u00a72See IP of Player."));
                        lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a=====\u00a73Diamond\u00a7bProtector\u00a7a====="));
                    }
                } else {
                    lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a=====\u00a73Diamond\u00a7bProtector\u00a7a====="));
                    lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp flush \u00a78- \u00a72Flush iptables."));
                    lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp ban [IP] \u00a78- \u00a72Ban-IP."));
                    lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp unban [IP] \u00a78- \u00a72Unban-IP."));
                    lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp stats \u00a78- \u00a72Actionbar stats."));
                    lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp whitelist [IP] \u00a78- \u00a72Whitelist an IP."));
                    lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a/dp lookup [Player] \u00a78- \u00a72See IP of Player."));
                    lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a7a=====\u00a73Diamond\u00a7bProtector\u00a7a====="));
                }
            } else {
                DiamondProtector.getInstance().sendTitle(lllIlIllIllllll, String.valueOf(new StringBuilder().append("\u00a7bDiamondProtector v").append(DiamondProtector.getInstance().getDescription().getVersion())), "\u00a77By \u00a7cTimeox2k", 20, 20, 20);
                lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a78\u00a7m--------\u00a7b\u00a7lDiamondProtector\u00a78\u00a7m--------"));
                lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent(String.valueOf(new StringBuilder().append("\u00a7b\u00a7lDiamondProtector \u00a78\u00bb \u00a77This Server is protected by \u00a7cDiamond\u00a7bProtector \u00a77v\u00a76").append(DiamondProtector.getInstance().getDescription().getVersion()).append(" \u00a77by \u00a7cTimeox2k\u00a77. \u00a77The \u00a77next \u00a77Generation \u00a77of \u00a77AntiBot \u00a77against \u00a77NullPing\u00a77, \u00a77DDoS \u00a77(\u00a77Automatic \u00a77Iptables\u00a77-\u00a77Setup\u00a77)"))));
                lllIlIllIllllll.sendMessage((BaseComponent)new TextComponent("\u00a78\u00a7m--------\u00a7b\u00a7lDiamondProtector\u00a78\u00a7m--------"));
            }
        } else if (lllIlIllIlllIII.length > 0) {
            if (lllIlIllIlllIII[0].equalsIgnoreCase("flush")) {
                RuntimeUtil.runCommand("iptables -F");
                if (DiamondProtector.getBooleanFromConfig("DiamondProtector.antilinuxconnection").booleanValue()) {
                    RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -A INPUT -p tcp -m tcp --syn --tcp-option 8 --dport ").append(ConfigManager.getBungeecordPort()).append(" -j REJECT")));
                }
                System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("You flushed the IPTables.")));
            } else if (lllIlIllIlllIII[0].equalsIgnoreCase("ban")) {
                if (lllIlIllIlllIII.length > 1) {
                    ProxiedPlayer lllIlIllIlllllI = DiamondProtector.getInstance().getProxy().getPlayer(lllIlIllIlllIII[1]);
                    if (lllIlIllIlllllI != null) {
                        RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -A INPUT -s ").append(lllIlIllIlllllI.getAddress().getAddress().getHostAddress()).append(" -j DROP")));
                        System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("You blocked the IP: \u00a7e").append(lllIlIllIlllIII[1]).append(" from Player ").append(lllIlIllIlllllI.getAddress().getAddress().getHostAddress())));
                    } else if (Validator.validIP(lllIlIllIlllIII[1])) {
                        RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -A INPUT -s ").append(lllIlIllIlllIII[1]).append(" -j DROP")));
                        System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("You unblocked the IP ").append(lllIlIllIlllIII[1])));
                    } else {
                        System.out.println("Invalid Inpout");
                    }
                } else {
                    System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("/dp ban [IP] - Ban-IP")));
                }
            } else if (lllIlIllIlllIII[0].equalsIgnoreCase("unban")) {
                if (lllIlIllIlllIII.length >= 1) {
                    if (Validator.validIP(lllIlIllIlllIII[1])) {
                        RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -D INPUT -s ").append(lllIlIllIlllIII[1]).append(" -j DROP")));
                        System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("You unblocked the IP: ").append(lllIlIllIlllIII[1])));
                    } else {
                        System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("This is not a valid IP")));
                    }
                } else {
                    System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("/dp unban [IP] - Unban-IP")));
                }
            } else if (lllIlIllIlllIII[0].equalsIgnoreCase("whitelist")) {
                if (lllIlIllIlllIII.length >= 1) {
                    if (Validator.validIP(lllIlIllIlllIII[1])) {
                        if (!WhitelistManager.isWhitelisted(lllIlIllIlllIII[1]).booleanValue()) {
                            WhitelistManager.addToWhitelist(lllIlIllIlllIII[1]);
                        } else {
                            WhitelistManager.removeFromWhitelist(lllIlIllIlllIII[1]);
                        }
                    } else {
                        System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("This is not a valid IP")));
                    }
                } else {
                    System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("/dp whitelist [IP] - Whitelist IP")));
                }
            } else if (lllIlIllIlllIII[0].equalsIgnoreCase("lookup")) {
                if (lllIlIllIlllIII.length > 1) {
                    ProxiedPlayer lllIlIllIllllIl = DiamondProtector.getInstance().getProxy().getPlayer(lllIlIllIlllIII[1]);
                    if (lllIlIllIllllIl != null) {
                        System.out.println(String.valueOf(new StringBuilder().append("IP of Player ").append(lllIlIllIllllIl.getName()).append(" is ").append(lllIlIllIllllIl.getAddress().getAddress().getHostAddress())));
                    } else {
                        System.out.println("Player is not online");
                    }
                } else {
                    System.out.println("/dp lookup [Player] - See IP of Player");
                }
            } else {
                System.out.println("=====DiamondProtector=====");
                System.out.println("/dp flush - Flush iptables.");
                System.out.println("/dp ban [IP] - Ban-IP");
                System.out.println("/dp unban [IP] - Unban-IP");
                System.out.println("/dp whitelist [IP] - Whitelist IP");
                System.out.println("/dp lookup [Player] - See IP of Player");
                System.out.println("=====DiamondProtector=====");
            }
        } else {
            System.out.println("=====DiamondProtector=====");
            System.out.println("/dp flush - Flush iptables.");
            System.out.println("/dp ban [IP] - Ban-IP");
            System.out.println("/dp unban [IP] - Unban-IP");
            System.out.println("/dp whitelist [IP] - Whitelist IP");
            System.out.println("/dp lookup [Player] - See IP of Player");
            System.out.println("=====DiamondProtector=====");
        }
    }
}

