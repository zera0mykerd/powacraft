/*
 * Decompiled with CFR 0.150.
 */
package de.timeox2k.diamondantibot.handler.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AntiAltModule {
    public static final /* synthetic */ HashMap<String, ArrayList<String>> accountsPerIP;

    public AntiAltModule() {
        AntiAltModule llllIIIllIllllI;
    }

    public static ArrayList<String> getAccountsByIP(String llllIIIllIIlIlI) {
        if (accountsPerIP.containsKey(llllIIIllIIlIlI)) {
            ArrayList<String> llllIIIllIIlIll = accountsPerIP.get(llllIIIllIIlIlI);
            return llllIIIllIIlIll;
        }
        return (ArrayList)Arrays.asList("");
    }

    static {
        accountsPerIP = new HashMap();
    }

    public static int getAccountCountPerIP(String llllIIIllIIllll) {
        if (accountsPerIP.containsKey(llllIIIllIIllll)) {
            ArrayList<String> llllIIIllIlIIIl = accountsPerIP.get(llllIIIllIIllll);
            return llllIIIllIlIIIl.size();
        }
        return 0;
    }

    public static void handleAccount(String llllIIIllIllIII, String llllIIIllIlIlll) {
        if (accountsPerIP.containsKey(llllIIIllIlIlll)) {
            ArrayList<String> llllIIIllIllIlI = accountsPerIP.get(llllIIIllIlIlll);
            if (!llllIIIllIllIlI.contains(llllIIIllIllIII)) {
                llllIIIllIllIlI.add(llllIIIllIllIII);
            }
            accountsPerIP.put(llllIIIllIlIlll, llllIIIllIllIlI);
        } else {
            ArrayList<String> llllIIIllIllIIl = new ArrayList<String>();
            llllIIIllIllIIl.add(llllIIIllIllIII);
            accountsPerIP.put(llllIIIllIlIlll, llllIIIllIllIIl);
        }
    }
}

