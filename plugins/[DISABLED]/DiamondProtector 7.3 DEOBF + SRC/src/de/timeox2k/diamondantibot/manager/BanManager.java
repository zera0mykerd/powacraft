/*
 * Decompiled with CFR 0.150.
 */
package de.timeox2k.diamondantibot.manager;

import de.timeox2k.diamondantibot.DiamondProtector;
import de.timeox2k.diamondantibot.manager.WhitelistManager;
import de.timeox2k.diamondantibot.utils.RuntimeUtil;
import de.timeox2k.diamondantibot.utils.Validator;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class BanManager {
    public static final /* synthetic */ ConcurrentHashMap<String, Long> banTime;
    public /* synthetic */ int banCount;
    public static final /* synthetic */ ArrayList<String> cache;

    public BanManager() {
        BanManager lllIllllIIIlIII;
        lllIllllIIIlIII.banCount = 0;
    }

    public void banAddress(String lllIllllIIIIIlI, String lllIllllIIIIIIl) {
        if (Validator.validIP(lllIllllIIIIIlI)) {
            if (!cache.contains(lllIllllIIIIIlI) && !WhitelistManager.isWhitelisted(lllIllllIIIIIlI).booleanValue()) {
                BanManager lllIllllIIIIIll;
                ++lllIllllIIIIIll.banCount;
                RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -A INPUT -s ").append(lllIllllIIIIIlI).append(" -j DROP")));
                System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("We found and blocked the IP: ").append(lllIllllIIIIIlI).append(" <").append(lllIllllIIIIIIl).append(">")));
                cache.add(lllIllllIIIIIlI);
                banTime.put(lllIllllIIIIIlI, System.currentTimeMillis());
            }
            if (cache.size() > 10000) {
                System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("Cleared the Ban-Cache to make sure everything works fast.")));
                cache.clear();
            }
        }
    }

    public void addBancount() {
        BanManager lllIlllIlllllII;
        ++lllIlllIlllllII.banCount;
    }

    public void unbanAddress(String lllIlllIlllIlIl) {
        if (Validator.validIP(lllIlllIlllIlIl)) {
            BanManager lllIlllIlllIllI;
            --lllIlllIlllIllI.banCount;
            RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -D INPUT -s ").append(lllIlllIlllIlIl).append(" -j DROP")));
            System.out.println(String.valueOf(new StringBuilder().append(DiamondProtector.getConsolePrefix()).append("We unblocked the IP: ").append(lllIlllIlllIlIl)));
        }
    }

    static {
        cache = new ArrayList();
        banTime = new ConcurrentHashMap();
    }
}

