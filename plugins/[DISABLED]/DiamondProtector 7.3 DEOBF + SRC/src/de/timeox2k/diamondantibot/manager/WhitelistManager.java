/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.config.Configuration
 *  net.md_5.bungee.config.ConfigurationProvider
 *  net.md_5.bungee.config.YamlConfiguration
 */
package de.timeox2k.diamondantibot.manager;

import de.timeox2k.diamondantibot.DiamondProtector;
import java.io.File;
import java.io.IOException;
import java.util.List;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class WhitelistManager {
    public WhitelistManager() {
        WhitelistManager lllIllIIIlIIIIl;
    }

    public static Boolean isWhitelisted(String lllIllIIIIlIIII) {
        try {
            Configuration lllIllIIIIlIIll = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(DiamondProtector.getInstance().getDataFolder(), "config.yml"));
            List lllIllIIIIlIIlI = lllIllIIIIlIIll.getStringList("DiamondProtector.whitelist");
            return lllIllIIIIlIIlI.contains(lllIllIIIIlIIII);
        }
        catch (IOException lllIllIIIIlIIIl) {
            lllIllIIIIlIIIl.printStackTrace();
            return false;
        }
    }

    public static void addToWhitelist(String lllIllIIIIllIIl) {
        try {
            Configuration lllIllIIIIlllIl = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(DiamondProtector.getInstance().getDataFolder(), "config.yml"));
            List lllIllIIIIlllII = lllIllIIIIlllIl.getStringList("DiamondProtector.whitelist");
            if (!lllIllIIIIlllII.contains(lllIllIIIIllIIl)) {
                lllIllIIIIlllII.add(lllIllIIIIllIIl);
                lllIllIIIIlllIl.set("DiamondProtector.whitelist", (Object)lllIllIIIIlllII);
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(lllIllIIIIlllIl, new File(DiamondProtector.getInstance().getDataFolder(), "config.yml"));
                System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("Added ").append(lllIllIIIIllIIl).append(" to whitelist.")));
            }
        }
        catch (IOException lllIllIIIIllIll) {
            lllIllIIIIllIll.printStackTrace();
        }
    }

    public static void removeFromWhitelist(String lllIllIIIIIIlIl) {
        try {
            Configuration lllIllIIIIIlIIl = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(DiamondProtector.getInstance().getDataFolder(), "config.yml"));
            List lllIllIIIIIlIII = lllIllIIIIIlIIl.getStringList("DiamondProtector.whitelist");
            if (lllIllIIIIIlIII.contains(lllIllIIIIIIlIl)) {
                lllIllIIIIIlIII.remove(lllIllIIIIIIlIl);
                lllIllIIIIIlIIl.set("DiamondProtector.whitelist", (Object)lllIllIIIIIlIII);
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(lllIllIIIIIlIIl, new File(DiamondProtector.getInstance().getDataFolder(), "config.yml"));
                System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("Removed ").append(lllIllIIIIIIlIl).append(" from whitelist.")));
            }
        }
        catch (IOException lllIllIIIIIIlll) {
            lllIllIIIIIIlll.printStackTrace();
        }
    }
}

