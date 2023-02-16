/*
 * Decompiled with CFR 0.150.
 */
package de.timeox2k.diamondantibot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

public class WebReader
implements Callable<String> {
    private final /* synthetic */ String target;

    @Override
    public String call() {
        try {
            WebReader lllllIllIllIlll;
            URL lllllIllIllllIl = new URL(lllllIllIllIlll.target);
            URLConnection lllllIllIllllII = lllllIllIllllIl.openConnection();
            lllllIllIllllII.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            BufferedReader lllllIllIlllIll = new BufferedReader(new InputStreamReader(lllllIllIllllII.getInputStream()));
            String lllllIllIlllIlI = lllllIllIlllIll.readLine();
            lllllIllIlllIll.close();
            return lllllIllIlllIlI;
        }
        catch (IOException lllllIllIlllIIl) {
            System.out.println("[DiamondProtector] Unable to reach VPN-Detection Service (Is your Firewall blocking it?)");
            return "";
        }
    }

    public WebReader(String lllllIlllIIIlIl) {
        WebReader lllllIlllIIIllI;
        lllllIlllIIIllI.target = lllllIlllIIIlIl;
    }
}

