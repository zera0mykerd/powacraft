/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.Marker
 *  org.apache.logging.log4j.core.Filter
 *  org.apache.logging.log4j.core.Filter$Result
 *  org.apache.logging.log4j.core.LifeCycle$State
 *  org.apache.logging.log4j.core.LogEvent
 *  org.apache.logging.log4j.core.Logger
 *  org.apache.logging.log4j.message.Message
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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LifeCycle;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

public class WaterfallFilter
implements Filter {
    public Filter.Result checkMessage(String lllllllIlIIlIIl) {
        WaterfallFilter lllllllIlIIlIlI;
        List<String> lllllllIlIIlllI = Arrays.asList("overflow in packet detected", "InitialHandler", "IllegalStateException:", "NativeIoException", "Connection reset by peer", "pinged", "connected", "InitialHandler - read timed out", "InitialHandler", "4554202f20485454502f312e310d0a48", "encountered exception: net.md_5.bungee.util.QuietException:", "Unexpected packet", "encountered exception: net.md_5.bungee.util.QuietException:", "Unexpected packet received during login process!", "InitialHandler is pinging", "InitialHandler has connected", "Connection reset by peer", "Unexpected packet received", "read timed out", "to process!", "Empty Packet!", "bad packet ID", "InitialHandler - encountered exception", "com.mojang.authlib.GameProfile@", "lost connection: Timed out", "lost connection: Disconnected", "Took too long to log in");
        List<String> lllllllIlIIllIl = Arrays.asList("overflow in packet detected", "Not expecting PING", "NativeIoException", "Connection reset by peer", "Cannot request protocol", "QuietException", "corrupted frame", "empty packet", "InitialHandler - read timed out", "4554202f20485454502f312e310d0a48", "encountered exception", "encountered exception: net.md_5.bungee.util.QuietException:", "Unexpected packet", "encountered exception: net.md_5.bungee.util.QuietException:", "Unexpected packet received during login process!", "Connection reset by peer", "Unexpected packet received", "read timed out", "Empty Packet!", "bad packet ID", "InitialHandler - encountered exception");
        Iterator<String> lllllllIlIIllII = new CopyOnWriteArrayList<String>(lllllllIlIIlllI).iterator();
        Iterator<String> lllllllIlIIlIll = new CopyOnWriteArrayList<String>(lllllllIlIIllIl).iterator();
        if (lllllllIlIIlIIl.toLowerCase().contains("InitialHandler - IllegalStateException: Not expecting PING")) {
            String lllllllIlIlIIll = lllllllIlIIlIlI.getStringBetweenTwoChars(lllllllIlIIlIIl, "[", ":").replaceAll("/", "");
            ++ConnectionHandler.blockCounter;
            DiamondProtector.getBanManager().addBancount();
            DiamondProtector.getBanManager().banAddress(lllllllIlIlIIll, "Not expecting Ping");
            return Filter.Result.DENY;
        }
        while (lllllllIlIIllII.hasNext()) {
            if (!lllllllIlIIlIIl.toLowerCase().contains(lllllllIlIIllII.next().toLowerCase())) continue;
            String lllllllIlIlIIlI = lllllllIlIIlIlI.getStringBetweenTwoChars(lllllllIlIIlIIl, "[", ":").replaceAll("/", "");
            if (Validator.validIP(lllllllIlIlIIlI) && !PlayerHandShake.firstPingTime.containsKey(lllllllIlIlIIlI) && !PlayerHandShake.firstPingCache.contains(lllllllIlIlIIlI)) {
                RuntimeUtil.runCommand(String.valueOf(new StringBuilder().append("iptables -A INPUT -s ").append(lllllllIlIlIIlI).append(" -j DROP")));
                PlayerHandShake.firstPingTime.put(lllllllIlIlIIlI, System.currentTimeMillis());
                ConnectionHandler.blockLocal(lllllllIlIlIIlI);
            }
            ++ConnectionHandler.connectionCounter;
            return Filter.Result.DENY;
        }
        while (lllllllIlIIlIll.hasNext()) {
            if (!lllllllIlIIlIIl.toLowerCase().contains(lllllllIlIIlIll.next().toLowerCase())) continue;
            String lllllllIlIlIIIl = lllllllIlIIlIlI.getStringBetweenTwoChars(lllllllIlIIlIIl, "[", ":").replaceAll("/", "");
            if (Validator.validIP(lllllllIlIlIIIl)) {
                ++ConnectionHandler.blockCounter;
                DiamondProtector.getBanManager().addBancount();
                DiamondProtector.getBanManager().banAddress(lllllllIlIlIIIl, "InitalHandler failed");
            }
            return Filter.Result.DENY;
        }
        return Filter.Result.NEUTRAL;
    }

    public Filter.Result filter(Logger llllllIllIIlIII, Level llllllIllIIIlll, Marker llllllIllIIIllI, String llllllIllIIIlIl, Object llllllIllIIIlII, Object llllllIllIIIIll, Object llllllIllIIIIlI, Object llllllIllIIIIIl, Object llllllIllIIIIII, Object llllllIlIllllll, Object llllllIlIlllllI, Object llllllIlIllllIl) {
        WaterfallFilter llllllIllIIlIIl;
        return llllllIllIIlIIl.checkMessage(llllllIllIIIlIl);
    }

    public Filter.Result getOnMismatch() {
        return null;
    }

    public Filter.Result filter(Logger llllllIlIllIlll, Level llllllIlIllIllI, Marker llllllIlIllIlIl, String llllllIlIlIlIIl, Object llllllIlIllIIll, Object llllllIlIllIIlI, Object llllllIlIllIIIl, Object llllllIlIllIIII, Object llllllIlIlIllll, Object llllllIlIlIlllI, Object llllllIlIlIllIl, Object llllllIlIlIllII, Object llllllIlIlIlIll) {
        WaterfallFilter llllllIlIlllIII;
        return llllllIlIlllIII.checkMessage(llllllIlIlIlIIl);
    }

    public Filter.Result filter(Logger lllllllIIIllIIl, Level lllllllIIIllIII, Marker lllllllIIIlIlll, String lllllllIIIlIIlI, Object lllllllIIIlIlIl, Object lllllllIIIlIlII) {
        WaterfallFilter lllllllIIIllIlI;
        return lllllllIIIllIlI.checkMessage(lllllllIIIlIIlI);
    }

    public Filter.Result filter(Logger lllllllIIIIIIlI, Level lllllllIIIIIIIl, Marker lllllllIIIIIIII, String llllllIllllllll, Object llllllIlllllllI, Object llllllIllllllIl, Object llllllIllllllII, Object llllllIlllllIll) {
        WaterfallFilter lllllllIIIIIIll;
        return lllllllIIIIIIll.checkMessage(llllllIllllllll);
    }

    public boolean isStarted() {
        return false;
    }

    public Filter.Result filter(Logger llllllIlIIlIlII, Level llllllIlIIlIIll, Marker llllllIlIIlIIlI, Object llllllIlIIlIIIl, Throwable llllllIlIIlIIII) {
        return null;
    }

    public Filter.Result filter(Logger llllllIlIlIIlIl, Level llllllIlIlIIlII, Marker llllllIlIlIIIll, String llllllIlIlIIIlI, Object llllllIlIlIIIIl, Object llllllIlIlIIIII, Object llllllIlIIlllll, Object llllllIlIIllllI, Object llllllIlIIlllIl, Object llllllIlIIlllII, Object llllllIlIIllIll, Object llllllIlIIllIlI, Object llllllIlIIllIIl, Object llllllIlIIllIII) {
        WaterfallFilter llllllIlIIlIlll;
        return llllllIlIIlIlll.checkMessage(llllllIlIlIIIlI);
    }

    public Filter.Result filter(Logger lllllllIIlIIIll, Level lllllllIIlIIIlI, Marker lllllllIIlIIIIl, String lllllllIIIlllIl, Object lllllllIIIlllll) {
        WaterfallFilter lllllllIIIllllI;
        return lllllllIIIllllI.checkMessage(lllllllIIIlllIl);
    }

    public Filter.Result filter(Logger llllllIlllIIlll, Level llllllIlllIIllI, Marker llllllIlllIIlIl, String llllllIlllIIlII, Object llllllIlllIIIll, Object llllllIlllIIIlI, Object llllllIlllIIIIl, Object llllllIlllIIIII, Object llllllIllIlllll, Object llllllIllIllllI) {
        WaterfallFilter llllllIlllIlIII;
        return llllllIlllIlIII.checkMessage(llllllIlllIIlII);
    }

    public Filter.Result filter(Logger llllllIllIllIII, Level llllllIllIlIlll, Marker llllllIllIlIllI, String llllllIllIlIlIl, Object llllllIllIlIlII, Object llllllIllIlIIll, Object llllllIllIlIIlI, Object llllllIllIlIIIl, Object llllllIllIlIIII, Object llllllIllIIllll, Object llllllIllIIlllI) {
        WaterfallFilter llllllIllIllIIl;
        return llllllIllIllIIl.checkMessage(llllllIllIlIlIl);
    }

    public Filter.Result filter(Logger llllllIlIIIlllI, Level llllllIlIIIllIl, Marker llllllIlIIIllII, Message llllllIlIIIlIll, Throwable llllllIlIIIlIlI) {
        return null;
    }

    public Filter.Result filter(LogEvent llllllIlIIIlIII) {
        return null;
    }

    public void stop() {
    }

    public Filter.Result filter(Logger lllllllIIIIlllI, Level lllllllIIIIllIl, Marker lllllllIIIIllII, String lllllllIIIIlIll, Object lllllllIIIIlIlI, Object lllllllIIIIlIIl, Object lllllllIIIIlIII) {
        WaterfallFilter lllllllIIIIIlll;
        return lllllllIIIIIlll.checkMessage(lllllllIIIIlIll);
    }

    public Filter.Result filter(Logger lllllllIIlIllIl, Level lllllllIIlIllII, Marker lllllllIIlIlIll, String lllllllIIlIIlll, Object ... lllllllIIlIlIIl) {
        WaterfallFilter lllllllIIlIlIII;
        return lllllllIIlIlIII.checkMessage(lllllllIIlIIlll);
    }

    public LifeCycle.State getState() {
        return null;
    }

    public void initialize() {
    }

    public Filter.Result filter(Logger llllllIllllIlIl, Level llllllIllllIlII, Marker llllllIllllIIll, String llllllIllllIIlI, Object llllllIllllIIIl, Object llllllIllllIIII, Object llllllIlllIllll, Object llllllIlllIlllI, Object llllllIlllIllIl) {
        WaterfallFilter llllllIllllIllI;
        return llllllIllllIllI.checkMessage(llllllIllllIIlI);
    }

    public WaterfallFilter() {
        WaterfallFilter lllllllIlIlllII;
    }

    public boolean isStopped() {
        return false;
    }

    public String getStringBetweenTwoChars(String lllllllIIllIlll, String lllllllIIllIllI, String lllllllIIlllIII) {
        try {
            int lllllllIIlllllI;
            int lllllllIIllllIl = lllllllIIllIlll.indexOf(lllllllIIllIllI);
            if (lllllllIIllllIl != -1 && (lllllllIIlllllI = lllllllIIllIlll.indexOf(lllllllIIlllIII, lllllllIIllllIl + lllllllIIllIllI.length())) != -1) {
                return lllllllIIllIlll.substring(lllllllIIllllIl + lllllllIIllIllI.length(), lllllllIIlllllI);
            }
        }
        catch (Exception lllllllIIllllII) {
            lllllllIIllllII.printStackTrace();
        }
        return lllllllIIllIlll;
    }

    public Filter.Result getOnMatch() {
        return null;
    }

    public void start() {
    }
}

