/*
 * Decompiled with CFR 0.150.
 */
package me.scareddev.crashfix.init.event;

import me.scareddev.crashfix.init.event.impl.CommandListener;
import me.scareddev.crashfix.init.event.impl.ExploitFix;
import me.scareddev.crashfix.init.event.impl.JoinListener;

public class CrashFixEventManager {
    public CrashFixEventManager() {
        new ExploitFix();
        new CommandListener();
        new JoinListener();
    }
}

