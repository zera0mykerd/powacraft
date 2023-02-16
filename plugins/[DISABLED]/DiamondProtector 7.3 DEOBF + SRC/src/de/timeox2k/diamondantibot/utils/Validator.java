/*
 * Decompiled with CFR 0.150.
 */
package de.timeox2k.diamondantibot.utils;

public class Validator {
    public static boolean validIP(String lllIllIIlllllII) {
        if (lllIllIIlllllII == null || lllIllIIlllllII.length() < 7 || lllIllIIlllllII.length() > 15) {
            return false;
        }
        try {
            int lllIllIIlllllll = 0;
            int lllIllIIllllllI = lllIllIIlllllII.indexOf(46);
            if (lllIllIIllllllI == -1 || lllIllIIlllllII.charAt(lllIllIIlllllll) == '-' || Integer.parseInt(lllIllIIlllllII.substring(lllIllIIlllllll, lllIllIIllllllI)) > 255) {
                return false;
            }
            if ((lllIllIIlllllll = lllIllIIlllllII.indexOf(46, ++lllIllIIllllllI)) == -1 || lllIllIIlllllII.charAt(lllIllIIllllllI) == '-' || Integer.parseInt(lllIllIIlllllII.substring(lllIllIIllllllI, lllIllIIlllllll)) > 255) {
                return false;
            }
            return (lllIllIIllllllI = lllIllIIlllllII.indexOf(46, ++lllIllIIlllllll)) != -1 && lllIllIIlllllII.charAt(lllIllIIlllllll) != '-' && Integer.parseInt(lllIllIIlllllII.substring(lllIllIIlllllll, lllIllIIllllllI)) <= 255 && lllIllIIlllllII.charAt(++lllIllIIllllllI) != '-' && Integer.parseInt(lllIllIIlllllII.substring(lllIllIIllllllI, lllIllIIlllllII.length())) <= 255 && lllIllIIlllllII.charAt(lllIllIIlllllII.length() - 1) != '.';
        }
        catch (NumberFormatException lllIllIIlllllIl) {
            return false;
        }
    }

    public Validator() {
        Validator lllIllIlIIIIIll;
    }
}

