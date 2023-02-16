/*
 * Decompiled with CFR 0.150.
 */
package de.timeox2k.diamondantibot.utils;

import java.util.List;

public class ConfigManager {
    private static /* synthetic */ int BUNGEECORD_PORT;
    public static /* synthetic */ List<String> blockedNames;
    private static /* synthetic */ int MAXCPSBEFORE_LOCKDOWN;

    public static int getMaxcpsbeforeLockdown() {
        return MAXCPSBEFORE_LOCKDOWN;
    }

    static {
        BUNGEECORD_PORT = 25565;
        MAXCPSBEFORE_LOCKDOWN = 20;
    }

    public static void setBungeecordPort(int lllllIIIIlIIIll) {
        BUNGEECORD_PORT = lllllIIIIlIIIll;
    }

    public ConfigManager() {
        ConfigManager lllllIIIIlIIlIl;
    }

    public static void setMaxcpsbeforeLockdown(int lllllIIIIIlllll) {
        MAXCPSBEFORE_LOCKDOWN = lllllIIIIIlllll;
    }

    public static int getBungeecordPort() {
        return BUNGEECORD_PORT;
    }

    public static List<String> getBlockedNames() {
        return blockedNames;
    }
}

