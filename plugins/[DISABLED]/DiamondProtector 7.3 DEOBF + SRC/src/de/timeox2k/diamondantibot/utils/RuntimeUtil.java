/*
 * Decompiled with CFR 0.150.
 */
package de.timeox2k.diamondantibot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RuntimeUtil {
    public static String runCommand(String llllIIIlIIlIIll) {
        Runtime llllIIIlIIlIlIl = Runtime.getRuntime();
        StringBuilder llllIIIlIIlIlII = new StringBuilder();
        try {
            String llllIIIlIIllIII;
            String llllIIIlIIllIIl;
            Process llllIIIlIIlllII = llllIIIlIIlIlIl.exec(llllIIIlIIlIIll);
            BufferedReader llllIIIlIIllIll = new BufferedReader(new InputStreamReader(llllIIIlIIlllII.getInputStream()));
            BufferedReader llllIIIlIIllIlI = new BufferedReader(new InputStreamReader(llllIIIlIIlllII.getErrorStream()));
            while ((llllIIIlIIllIIl = llllIIIlIIllIll.readLine()) != null) {
                llllIIIlIIlIlII.append(llllIIIlIIllIIl);
            }
            while ((llllIIIlIIllIII = llllIIIlIIllIlI.readLine()) != null) {
                llllIIIlIIlIlII.append(llllIIIlIIllIII);
            }
        }
        catch (IOException llllIIIlIIlIlll) {
            return "We are unable to run a Linux command, looks like iptables is not installed or you run our Plugin dockerized.";
        }
        return String.valueOf(llllIIIlIIlIlII);
    }

    public RuntimeUtil() {
        RuntimeUtil llllIIIlIlIIllI;
    }
}

