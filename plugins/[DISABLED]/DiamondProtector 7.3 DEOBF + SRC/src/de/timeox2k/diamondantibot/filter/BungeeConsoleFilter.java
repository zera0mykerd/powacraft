/*
 * Decompiled with CFR 0.150.
 */
package de.timeox2k.diamondantibot.filter;

import de.timeox2k.diamondantibot.DiamondProtector;
import de.timeox2k.diamondantibot.handler.ConnectionHandler;
import de.timeox2k.diamondantibot.handler.events.PlayerHandShake;
import de.timeox2k.diamondantibot.utils.RuntimeUtil;
import de.timeox2k.diamondantibot.utils.Validator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class BungeeConsoleFilter
implements Filter {
    public BungeeConsoleFilter() {
        BungeeConsoleFilter lllIlllIlIIIIlI;
    }

    @Override
    public boolean isLoggable(LogRecord lllIlllIIlIlllI) {
        BungeeConsoleFilter lllIlllIIllIllI;
        List<String> lllIlllIIllIlII = Arrays.asList("overflow in packet detected", "InitialHandler", "IllegalStateException:", "NativeIoException", "Connection reset by peer", "pinged", "connected", "InitialHandler - read timed out", "InitialHandler", "4554202f20485454502f312e310d0a48", "encountered exception: net.md_5.bungee.util.QuietException:", "Unexpected packet", "encountered exception: net.md_5.bungee.util.QuietException:", "Unexpected packet received during login process!", "InitialHandler is pinging", "InitialHandler has connected", "Connection reset by peer", "Unexpected packet received", "read timed out", "to process!", "Empty Packet!", "bad packet ID", "InitialHandler - encountered exception", "com.mojang.authlib.GameProfile@", "lost connection: Timed out", "lost connection: Disconnected", "Took too long to log in");
        List<String> lllIlllIIllIIll = Arrays.asList("overflow in packet detected", "Not expecting PING", "NativeIoException", "Connection reset by peer", "Cannot request protocol", "QuietException", "corrupted frame", "empty packet", "InitialHandler - read timed out", "4554202f20485454502f312e310d0a48", "encountered exception", "encountered exception: net.md_5.bungee.util.QuietException:", "Unexpected packet", "encountered exception: net.md_5.bungee.util.QuietException:", "Unexpected packet received during login process!", "Connection reset by peer", "Unexpected packet received", "read timed out", "Empty Packet!", "bad packet ID", "InitialHandler - encountered exception");
        String lllIlllIIllIIlI = lllIlllIIlIlllI.getMessage();
        Iterator<String> lllIlllIIllIIIl = new CopyOnWriteArrayList<String>(lllIlllIIllIlII).iterator();
        Iterator<String> lllIlllIIllIIII = new CopyOnWriteArrayList<String>(lllIlllIIllIIll).iterator();
        while (lllIlllIIllIIIl.hasNext()) {
            if (!lllIlllIIllIIlI.toLowerCase().contains(lllIlllIIllIIIl.next().toLowerCase())) continue;
            String lllIlllIIlllIII = lllIlllIIllIllI.getStringBetweenTwoChars(lllIlllIIllIIlI, "[", ":").replaceAll("/", "");
            if (Validator.validIP(lllIlllIIlllIII) && !PlayerHandShake.firstPingTime.containsKey(lllIlllIIlllIII) && !PlayerHandShake.firstPingCache.contains(lllIlllIIlllIII)) {
                RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -A INPUT -s ").append(lllIlllIIlllIII).append(" -j DROP")));
                PlayerHandShake.firstPingTime.put(lllIlllIIlllIII, System.currentTimeMillis());
                ConnectionHandler.blockLocal(lllIlllIIlllIII);
            }
            ++ConnectionHandler.connectionCounter;
            return false;
        }
        while (lllIlllIIllIIII.hasNext()) {
            if (!lllIlllIIllIIlI.toLowerCase().contains(lllIlllIIllIIII.next().toLowerCase())) continue;
            String lllIlllIIllIlll = lllIlllIIllIllI.getStringBetweenTwoChars(lllIlllIIllIIlI, "[", ":").replaceAll("/", "");
            if (Validator.validIP(lllIlllIIllIlll)) {
                ++ConnectionHandler.blockCounter;
                DiamondProtector.getBanManager().addBancount();
                DiamondProtector.getBanManager().banAddress(lllIlllIIllIlll, "InitalHandler failed");
            }
            return false;
        }
        return true;
    }

    public String getStringBetweenTwoChars(String lllIlllIIIllIll, String lllIlllIIIllIlI, String lllIlllIIIlllII) {
        try {
            int lllIlllIIlIIIlI;
            int lllIlllIIlIIIIl = lllIlllIIIllIll.indexOf(lllIlllIIIllIlI);
            if (lllIlllIIlIIIIl != -1 && (lllIlllIIlIIIlI = lllIlllIIIllIll.indexOf(lllIlllIIIlllII, lllIlllIIlIIIIl + lllIlllIIIllIlI.length())) != -1) {
                return lllIlllIIIllIll.substring(lllIlllIIlIIIIl + lllIlllIIIllIlI.length(), lllIlllIIlIIIlI);
            }
        }
        catch (Exception lllIlllIIlIIIII) {
            lllIlllIIlIIIII.printStackTrace();
        }
        return lllIlllIIIllIll;
    }
}

