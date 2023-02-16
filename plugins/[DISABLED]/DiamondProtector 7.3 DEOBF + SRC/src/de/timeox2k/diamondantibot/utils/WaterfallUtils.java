/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 */
package de.timeox2k.diamondantibot.utils;

import org.apache.logging.log4j.LogManager;

public class WaterfallUtils {
    public WaterfallUtils() {
        WaterfallUtils lllllIIlIlllIIl;
    }

    public static boolean isLog4J() {
        try {
            LogManager.getRootLogger();
            return true;
        }
        catch (NoClassDefFoundError lllllIIlIllIlll) {
            return false;
        }
    }
}

