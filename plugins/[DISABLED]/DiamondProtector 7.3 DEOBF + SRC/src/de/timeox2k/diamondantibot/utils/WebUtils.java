/*
 * Decompiled with CFR 0.150.
 */
package de.timeox2k.diamondantibot.utils;

import de.timeox2k.diamondantibot.utils.WebReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebUtils {
    private /* synthetic */ ExecutorService pool;

    public String getResponseFromURLAsync(String llllIIIIIllIllI) throws InterruptedException, ExecutionException {
        WebUtils llllIIIIIllIlII;
        Future<String> llllIIIIIllIlIl = llllIIIIIllIlII.pool.submit(new WebReader(llllIIIIIllIllI));
        return llllIIIIIllIlIl.get();
    }

    public WebUtils(int llllIIIIIlllIll) {
        WebUtils llllIIIIIlllllI;
        llllIIIIIlllllI.pool = Executors.newFixedThreadPool(llllIIIIIlllIll);
    }
}

