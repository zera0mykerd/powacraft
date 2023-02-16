/*
 * Decompiled with CFR 0.150.
 */
package me.scareddev.crashfix.init.runnable;

import me.scareddev.crashfix.init.netty.NettyInjection;

public class ClearTask
implements Runnable {
    @Override
    public final void run() {
        NettyInjection.Position.clear();
        NettyInjection.Fly.clear();
        NettyInjection.ArmAnimation.clear();
        NettyInjection.CustomPayLoad.clear();
        NettyInjection.WindowClick.clear();
        NettyInjection.BookPackets.clear();
        NettyInjection.LookPackets.clear();
        NettyInjection.SetCreatives.clear();
    }
}

