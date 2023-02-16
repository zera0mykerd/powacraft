/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 */
package me.scareddev.crashfix.init.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import org.bukkit.Bukkit;

public class ServerTPS {
    public final String getTPS() {
        String string = Bukkit.getServer().getClass().getPackage().getName();
        String string2 = string.substring(string.lastIndexOf(46) + 1);
        Class<?> class_ = null;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Object object = null;
        Field field = null;
        try {
            class_ = Class.forName(String.valueOf(new StringBuilder().append("net.minecraft.server.").append(string2).append(".MinecraftServer")));
            object = class_.getMethod("getServer", new Class[0]).invoke(null, new Object[0]);
            field = object.getClass().getField("recentTps");
        }
        catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
        double[] arrd = null;
        try {
            assert (field != null);
            arrd = (double[])field.get(object);
        }
        catch (IllegalAccessException | IllegalArgumentException exception) {
            exception.printStackTrace();
        }
        assert (arrd != null);
        return decimalFormat.format(arrd[0]);
    }
}

