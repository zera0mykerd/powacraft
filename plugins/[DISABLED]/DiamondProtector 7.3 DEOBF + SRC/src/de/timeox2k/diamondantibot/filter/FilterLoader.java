/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.BungeeCord
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.core.Filter
 *  org.apache.logging.log4j.core.Logger
 */
package de.timeox2k.diamondantibot.filter;

import de.timeox2k.diamondantibot.filter.BungeeConsoleFilter;
import de.timeox2k.diamondantibot.filter.WaterfallFilter;
import de.timeox2k.diamondantibot.utils.WaterfallUtils;
import java.util.logging.Logger;
import net.md_5.bungee.BungeeCord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Filter;

public class FilterLoader {
    public FilterLoader() {
        FilterLoader llllllIIlIIIIlI;
    }

    public static void loadFilter() {
        if (WaterfallUtils.isLog4J()) {
            ((org.apache.logging.log4j.core.Logger)LogManager.getRootLogger()).addFilter((Filter)new WaterfallFilter());
        } else {
            Logger llllllIIIllllll = BungeeCord.getInstance().getLogger();
            llllllIIIllllll.setFilter(new BungeeConsoleFilter());
        }
    }
}

