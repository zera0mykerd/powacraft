/*
 * Decompiled with CFR 0.150.
 */
package de.timeox2k.diamondantibot.handler;

import de.timeox2k.diamondantibot.DiamondProtector;
import de.timeox2k.diamondantibot.handler.ConnectionHandler;
import de.timeox2k.diamondantibot.manager.BanManager;
import de.timeox2k.diamondantibot.utils.Validator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

public class ProxyHandler {
    private /* synthetic */ boolean checking;
    public static final /* synthetic */ LinkedList<String> queue;
    private final /* synthetic */ LinkedList<String> checkCache;

    public ProxyHandler() {
        ProxyHandler lllIllIllIlIllI;
        lllIllIllIlIllI.checkCache = new LinkedList();
        lllIllIllIlIllI.checking = false;
    }

    public static LinkedList<String> getQueue() {
        return queue;
    }

    static {
        queue = new LinkedList();
    }

    public void checkProxy(String lllIllIllIlIIlI) {
        ProxyHandler lllIllIllIlIIIl;
        if (!(ConnectionHandler.blockedLocal.contains(lllIllIllIlIIlI) || queue.contains(lllIllIllIlIIlI) || BanManager.cache.contains(lllIllIllIlIIlI) || lllIllIllIlIIIl.checkCache.contains(lllIllIllIlIIlI))) {
            queue.add(lllIllIllIlIIlI);
        }
    }

    public void queryQueue() throws InterruptedException, ExecutionException {
        ProxyHandler lllIllIllIIlIII;
        if (!lllIllIllIIlIII.checking && !queue.isEmpty()) {
            lllIllIllIIlIII.checking = true;
            Iterator<String> lllIllIllIIlIIl = new CopyOnWriteArrayList<String>(queue).iterator();
            while (lllIllIllIIlIIl.hasNext()) {
                String lllIllIllIIlIlI = lllIllIllIIlIIl.next().toLowerCase();
                if (!Validator.validIP(lllIllIllIIlIlI) || ConnectionHandler.blockedLocal.contains(lllIllIllIIlIlI)) continue;
                String lllIllIllIIlIll = DiamondProtector.getWebUtils().getResponseFromURLAsync(String.valueOf(new StringBuilder().append("https://blackbox.ipinfo.app/lookup/").append(lllIllIllIIlIlI)));
                if (lllIllIllIIlIll != null && !lllIllIllIIlIll.isEmpty() && lllIllIllIIlIll.contains("Y")) {
                    DiamondProtector.getBanManager().banAddress(lllIllIllIIlIlI, "vpn/proxy detected");
                    ++ConnectionHandler.blockCounter;
                    DiamondProtector.getBanManager().addBancount();
                }
                queue.remove(lllIllIllIIlIlI);
                lllIllIllIIlIII.checkCache.add(lllIllIllIIlIlI);
            }
            queue.clear();
            lllIllIllIIlIII.checking = false;
        }
    }
}

