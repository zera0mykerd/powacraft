/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.DecoderException
 *  io.netty.handler.codec.MessageToMessageDecoder
 *  net.md_5.bungee.api.ChatColor
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  net.minecraft.server.v1_8_R3.NBTTagList
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketDataSerializer
 *  net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace
 *  net.minecraft.server.v1_8_R3.PacketPlayInChat
 *  net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying$PacketPlayInLook
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying$PacketPlayInPosition
 *  net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot
 *  net.minecraft.server.v1_8_R3.PacketPlayInSetCreativeSlot
 *  net.minecraft.server.v1_8_R3.PacketPlayInWindowClick
 *  org.bukkit.Bukkit
 *  org.bukkit.GameMode
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryType
 *  org.bukkit.inventory.ItemStack
 */
package me.scareddev.crashfix.init.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import me.scareddev.crashfix.CrashFix;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.PacketPlayInChat;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot;
import net.minecraft.server.v1_8_R3.PacketPlayInSetCreativeSlot;
import net.minecraft.server.v1_8_R3.PacketPlayInWindowClick;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class NettyInjection {
    private final /* synthetic */ List<net.minecraft.server.v1_8_R3.ItemStack> SignedBook;
    private final /* synthetic */ Map<String, Long> LastPackets;
    private final /* synthetic */ Player player;
    public static final /* synthetic */ Map<String, Long> LookPackets;
    public static final /* synthetic */ Map<String, Long> Position;
    private final /* synthetic */ Map<String, Long> Packets;
    public static final /* synthetic */ Map<String, Long> Fly;
    public static final /* synthetic */ Map<String, Long> SetCreatives;
    public static final /* synthetic */ Map<String, Long> WindowClick;
    public static final /* synthetic */ Map<String, Long> CustomPayLoad;
    private /* synthetic */ Channel channel;
    private final /* synthetic */ Map<Player, Long> ChatTime;
    private final /* synthetic */ Map<CraftItemStack, Long> OpenTook;
    private final /* synthetic */ Map<Player, ArrayList<String>> alreadySent;
    private final /* synthetic */ Map<Player, Long> CustomPayLoadTime;
    public static final /* synthetic */ Map<String, Long> ArmAnimation;
    public static final /* synthetic */ Map<String, Long> BookPackets;

    public final void Inject() {
        CraftPlayer craftPlayer = (CraftPlayer)this.player;
        this.channel = craftPlayer.getHandle().playerConnection.networkManager.channel;
        this.channel.pipeline().addAfter("decoder", "CrashFix", (ChannelHandler)new MessageToMessageDecoder<Packet<?>>(){

            protected void decode(ChannelHandlerContext channelHandlerContext, Packet<?> packet, List<Object> list) throws Exception {
                if (NettyInjection.this.channel.isOpen()) {
                    if (CrashFix.getInstance().getExploitPlayer().contains(NettyInjection.this.player)) {
                        NettyInjection.this.channel.close();
                        return;
                    }
                    try {
                        try {
                            String string;
                            String string2;
                            NBTTagList nBTTagList;
                            Object object;
                            PacketPlayInChat packetPlayInChat;
                            if (packet == null) {
                                CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "NULL_PACKET", "Invalid Packet");
                                NettyInjection.this.channel.close();
                                return;
                            }
                            if (!NettyInjection.this.Packets.containsKey(NettyInjection.this.player.getName())) {
                                NettyInjection.this.Packets.put(NettyInjection.this.player.getName(), 0L);
                            }
                            if (!NettyInjection.this.LastPackets.containsKey(NettyInjection.this.player.getName())) {
                                NettyInjection.this.LastPackets.put(NettyInjection.this.player.getName(), System.currentTimeMillis());
                            }
                            if (System.currentTimeMillis() - (Long)NettyInjection.this.LastPackets.get(NettyInjection.this.player.getName()) >= 1000L) {
                                NettyInjection.this.LastPackets.replace(NettyInjection.this.player.getName(), System.currentTimeMillis());
                                NettyInjection.this.Packets.put(NettyInjection.this.player.getName(), 0L);
                            } else {
                                NettyInjection.this.Packets.replace(NettyInjection.this.player.getName(), (Long)NettyInjection.this.Packets.get(NettyInjection.this.player.getName()) + 1L);
                            }
                            if ((Long)NettyInjection.this.Packets.get(NettyInjection.this.player.getName()) > 500L) {
                                CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "PACKET_SPAM", String.valueOf(new StringBuilder().append("Amount \u00a78(").append(CrashFix.getInstance().getColor()[0]).append(NettyInjection.this.Packets.get(NettyInjection.this.player.getName())).append("\u00a78)")));
                                CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                NettyInjection.this.channel.close();
                                return;
                            }
                            if (packet instanceof PacketPlayInChat) {
                                packetPlayInChat = (PacketPlayInChat)packet;
                                if (packetPlayInChat.a().startsWith("/") && (packetPlayInChat.a().contains("pex promote") || packetPlayInChat.a().contains("calc") || packetPlayInChat.a().contains("eval") || packetPlayInChat.a().contains("pex demote") || packetPlayInChat.a().contains("solve"))) {
                                    if (NettyInjection.this.ChatTime.containsKey((Object)NettyInjection.this.player) && (Long)NettyInjection.this.ChatTime.get((Object)NettyInjection.this.player) > System.currentTimeMillis()) {
                                        NettyInjection.this.player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append("Don\u00b4t try it Baby ;)")));
                                        NettyInjection.this.player.playSound(NettyInjection.this.player.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
                                        return;
                                    }
                                    CrashFix.getInstance().sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[0]).append(" ").append(NettyInjection.this.player.getName()).append(CrashFix.getInstance().getColor()[1]).append(" tried Crash Command!")));
                                    Bukkit.getConsoleSender().sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[0]).append(" ").append(NettyInjection.this.player.getName()).append(CrashFix.getInstance().getColor()[1]).append(" tried Crash Command!")));
                                    NettyInjection.this.player.sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getPrefix()).append("Don\u00b4t try it Baby ;)")));
                                    NettyInjection.this.player.playSound(NettyInjection.this.player.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
                                    NettyInjection.this.ChatTime.put(NettyInjection.this.player, System.currentTimeMillis() + 3000L);
                                    return;
                                }
                                if (packetPlayInChat.a() == null) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CHAT", "NullPoint");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (packetPlayInChat.a().length() > 100 || packetPlayInChat.a().length() < 1) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CHAT", "Invalid Characters-Length");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                            }
                            if (packet instanceof PacketPlayInCustomPayload) {
                                boolean bl;
                                packetPlayInChat = (PacketPlayInCustomPayload)packet;
                                if (packetPlayInChat.b().writerIndex() > 32767) {
                                    return;
                                }
                                if (!CustomPayLoad.containsKey(NettyInjection.this.player.getName())) {
                                    CustomPayLoad.put(NettyInjection.this.player.getName(), 0L);
                                } else {
                                    CustomPayLoad.replace(NettyInjection.this.player.getName(), CustomPayLoad.get(NettyInjection.this.player.getName()) + 1L);
                                }
                                if (CustomPayLoad.get(NettyInjection.this.player.getName()) > 25L) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", String.valueOf(new StringBuilder().append("PacketSpam \u00a78(").append(CrashFix.getInstance().getColor()[0]).append(CustomPayLoad.get(NettyInjection.this.player.getName())).append("\u00a78)")));
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (CrashFix.getInstance().getExploitPlayer().containsJoined(NettyInjection.this.player) && NettyInjection.this.CustomPayLoadTime.containsKey((Object)NettyInjection.this.player) && NettyInjection.this.player.getOpenInventory().getType() != InventoryType.ANVIL && NettyInjection.this.player.getOpenInventory().getType() != InventoryType.BEACON && NettyInjection.this.player.getOpenInventory().getType() != InventoryType.MERCHANT && (Long)NettyInjection.this.CustomPayLoadTime.get((Object)NettyInjection.this.player) > System.currentTimeMillis()) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "Sending too fast CustomPayLoad");
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                NettyInjection.this.CustomPayLoadTime.put(NettyInjection.this.player, System.currentTimeMillis() + 750L);
                                if (packetPlayInChat.b().writableBytes() != 0) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "Invalid WritableBytes");
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                object = packetPlayInChat.a();
                                if (packetPlayInChat.a().getBytes().length != 8 && packetPlayInChat.a().getBytes().length != 11 && packetPlayInChat.a().getBytes().length != 3 && packetPlayInChat.a().getBytes().length != 10 && packetPlayInChat.a().getBytes().length != 5 && packetPlayInChat.a().getBytes().length != 16 && NettyInjection.this.player.getOpenInventory().getType() != InventoryType.ANVIL && NettyInjection.this.player.getOpenInventory().getType() != InventoryType.BEACON && !((String)object).equalsIgnoreCase("MC|AdvCdm")) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", String.valueOf(new StringBuilder().append("Invalid Bytes | ").append(packetPlayInChat.a().getBytes().length)));
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (((String)object).equals("MC|TrSel") && NettyInjection.this.player.getOpenInventory().getType() != InventoryType.MERCHANT) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "Invalid Merchant");
                                    NettyInjection.this.channel.close();
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    return;
                                }
                                if (object == null) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "Invalid Channel");
                                    NettyInjection.this.channel.close();
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    return;
                                }
                                if (!(!CrashFix.getInstance().getExploitPlayer().containsJoined(NettyInjection.this.player) || ((String)object).equals("MC|Brand") || ((String)object).equals("LMC") || ((String)object).equals("MC|BEdit") || ((String)object).equals("MC|BSign") || ((String)object).equals("BungeeCord") || NettyInjection.this.player.getOpenInventory().getType() == InventoryType.ANVIL || NettyInjection.this.player.getOpenInventory().getType() == InventoryType.BEACON || NettyInjection.this.player.getOpenInventory().getType() == InventoryType.MERCHANT || ((String)object).equalsIgnoreCase("MC|AdvCdm"))) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", String.valueOf(new StringBuilder().append("Sending PayLoad but don't needed \u00a78(").append(CrashFix.getInstance().getColor()[0]).append((String)object).append("\u00a78)")));
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    return;
                                }
                                if (packetPlayInChat.b().capacity() >= 27000) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", String.valueOf(new StringBuilder().append(packetPlayInChat.b().capacity()).append(" -> 27000 | Capacity")));
                                    NettyInjection.this.channel.close();
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    return;
                                }
                                if (CrashFix.getInstance().getExploitPlayer().containsJoined(NettyInjection.this.player) && ((String)object).equals("REGISTER")) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "Invalid Register | Forge?");
                                    NettyInjection.this.channel.close();
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    return;
                                }
                                if (((String)object).equalsIgnoreCase("MC|BSign") || ((String)object).equals("MC|BEdit")) {
                                    PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.wrappedBuffer((ByteBuf)packetPlayInChat.b()));
                                    nBTTagList = packetDataSerializer.i();
                                    if (NettyInjection.this.SignedBook.contains((Object)nBTTagList)) {
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "Edit a Signed Book?");
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                }
                                boolean bl2 = bl = ((String)object).equals("MC|BEdit") || ((String)object).equals("MC|BSign") || ((String)object).equals("MC|BOpen");
                                if (bl && NettyInjection.this.player.getItemInHand().getType() != Material.WRITTEN_BOOK && NettyInjection.this.player.getItemInHand().getType() != Material.BOOK_AND_QUILL) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "Invalid Book in Hand");
                                    NettyInjection.this.channel.close();
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    return;
                                }
                                try {
                                    if (bl) {
                                        nBTTagList = new PacketDataSerializer(Unpooled.wrappedBuffer((ByteBuf)packetPlayInChat.b()));
                                        net.minecraft.server.v1_8_R3.ItemStack itemStack = nBTTagList.i();
                                        string2 = itemStack.hasTag() ? itemStack.getTag() : null;
                                        Object object2 = string = string2 != null && string2.hasKeyOfType("pages", 9) ? string2.getList("pages", 8) : null;
                                        if (!CrashFix.getInstance().Books()) {
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "Books are Disabled");
                                            NettyInjection.this.channel.close();
                                            return;
                                        }
                                        if (itemStack.getName().getBytes().length > 30) {
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", String.valueOf(new StringBuilder().append(itemStack.getName().getBytes().length).append(" -> 30 | NBytes")));
                                            NettyInjection.this.channel.close();
                                            return;
                                        }
                                        if (string2 == null || string2.isEmpty()) {
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "Invalid NBTTag");
                                            NettyInjection.this.channel.close();
                                            return;
                                        }
                                        if (string2.hasKey(":[{extra:[{") || string2.toString().contains(":[{extra:[{")) {
                                            itemStack.getTag().remove("pages");
                                            itemStack.getTag().remove("author");
                                            itemStack.getTag().remove("title");
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "OnePacketCrasher");
                                            CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                            return;
                                        }
                                        if (string != null && (string.size() > 50 || string.size() < 0)) {
                                            itemStack.getTag().remove("pages");
                                            itemStack.getTag().remove("author");
                                            itemStack.getTag().remove("title");
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", String.valueOf(new StringBuilder().append(string.size()).append(" -> 50 | Pages")));
                                            CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                            return;
                                        }
                                        for (int i = 0; i < ((NBTTagList)Objects.requireNonNull(string)).size(); ++i) {
                                            String string3 = ChatColor.stripColor((String)string.getString(i).replace("\\+", ""));
                                            if (string3.contains(":[{extra:[{") || string3.contains("extra:") || string3.contains("{")) {
                                                itemStack.getTag().remove("pages");
                                                itemStack.getTag().remove("author");
                                                itemStack.getTag().remove("title");
                                                CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "OnePacketCrasher");
                                                CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                                return;
                                            }
                                            if (string.size() <= 0) {
                                                itemStack.getTag().remove("pages");
                                                itemStack.getTag().remove("author");
                                                itemStack.getTag().remove("title");
                                                CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "Written Book with 0 Pages?");
                                                CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                                return;
                                            }
                                            if (string3.length() <= 265) continue;
                                            itemStack.getTag().remove("pages");
                                            itemStack.getTag().remove("author");
                                            itemStack.getTag().remove("title");
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", String.valueOf(new StringBuilder().append(string3.length()).append(" -> 295 | Characters")));
                                            CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                            return;
                                        }
                                    }
                                }
                                catch (Exception exception) {
                                    CrashFix.getInstance().sendMessage(String.valueOf(new StringBuilder().append(CrashFix.getInstance().getColor()[1]).append(" The Player ").append(CrashFix.getInstance().getColor()[0]).append(NettyInjection.this.player.getName()).append(CrashFix.getInstance().getColor()[1]).append(" sending Exceptions in CustomPayLoad!")));
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if ((double)packetPlayInChat.b().writerIndex() > 13437.5) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", String.valueOf(new StringBuilder().append(packetPlayInChat.b().writerIndex()).append(" -> 13437.5 | WriterIndex")));
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    return;
                                }
                                if (((String)object).equalsIgnoreCase("MC|ItemName") && NettyInjection.this.player.getOpenInventory().getType() != InventoryType.ANVIL) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "MC|ItemName | Invalid Channel");
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    return;
                                }
                                if (!packetPlayInChat.b().isReadable()) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "Readable | Invalid");
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    return;
                                }
                                if ("UNREGISTER".equals(packetPlayInChat.a())) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "CUSTOM_PAYLOAD", "UNREGISTER | Invalid Channel");
                                    CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                    return;
                                }
                            }
                            if (packet instanceof PacketPlayInWindowClick) {
                                packetPlayInChat = (PacketPlayInWindowClick)packet;
                                object = CraftItemStack.asCraftMirror((net.minecraft.server.v1_8_R3.ItemStack)packetPlayInChat.e());
                                NBTTagCompound nBTTagCompound = packetPlayInChat.e().hasTag() ? packetPlayInChat.e().getTag() : null;
                                NBTTagList nBTTagList2 = nBTTagList = nBTTagCompound != null && nBTTagCompound.hasKeyOfType("pages", 9) ? nBTTagCompound.getList("pages", 8) : null;
                                if (!(CrashFix.getInstance().Books() || object.getType() != Material.WRITTEN_BOOK && object.getType() != Material.BOOK_AND_QUILL)) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", "Books are Disabled");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (object.getType() == Material.WRITTEN_BOOK || object.getType() == Material.BOOK_AND_QUILL) {
                                    if (!WindowClick.containsKey(NettyInjection.this.player.getName())) {
                                        WindowClick.put(NettyInjection.this.player.getName(), 0L);
                                    } else {
                                        WindowClick.remove(NettyInjection.this.player.getName(), WindowClick.get(NettyInjection.this.player.getName()) + 1L);
                                    }
                                    if (WindowClick.get(NettyInjection.this.player.getName()) >= 8L) {
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", String.valueOf(new StringBuilder().append("PacketSpam \u00a78(").append(CrashFix.getInstance().getColor()[0]).append(WindowClick.get(NettyInjection.this.player.getName())).append("\u00a78)")));
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                }
                                if (object.getType() == Material.WRITTEN_BOOK && nBTTagCompound == null) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", "Invalid NBTTag");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (!NettyInjection.this.player.getGameMode().equals((Object)GameMode.CREATIVE) && NettyInjection.this.player.getOpenInventory().getType() == InventoryType.CREATIVE) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", "Invalid Inventory");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (object.getType() == Material.WRITTEN_BOOK && object.getMaxStackSize() > 64) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", "Stacked Itmes (W_B)");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (object.getType() == Material.BOOK_AND_QUILL && object.getMaxStackSize() > 64) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", "Stacked Itmes (B_A_Q)");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (object.getType() == Material.BOOK_AND_QUILL || object.getType() == Material.WRITTEN_BOOK) {
                                    assert (nBTTagCompound != null);
                                    if (nBTTagCompound.hasKey(":[{extra:[{") || nBTTagCompound.toString().contains(":[{extra:[{")) {
                                        nBTTagCompound.remove("pages");
                                        nBTTagCompound.remove("author");
                                        nBTTagCompound.remove("title");
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", "OnePacketCrasher");
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                    assert (nBTTagList != null);
                                    if (nBTTagList.size() > 50 || nBTTagList.size() < 0) {
                                        nBTTagCompound.remove("pages");
                                        nBTTagCompound.remove("author");
                                        nBTTagCompound.remove("title");
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", String.valueOf(new StringBuilder().append(nBTTagList.size()).append(" -> 50 | Pages")));
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                    if (nBTTagList.size() > 1 && !object.hasItemMeta()) {
                                        nBTTagCompound.remove("pages");
                                        nBTTagCompound.remove("author");
                                        nBTTagCompound.remove("title");
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", "Invalid ItemMeta (Pages)");
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                    int n = packetPlayInChat.b();
                                    if (packetPlayInChat.e() != null && (n < 0 || n > 250)) {
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", "Invalid Slots");
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                    for (int i = 0; i < nBTTagList.size(); ++i) {
                                        string = ChatColor.stripColor((String)nBTTagList.getString(i).replaceAll("\\+", ""));
                                        if (string.contains(":[{extra:[{") || string.contains("extra:")) {
                                            nBTTagCompound.remove("pages");
                                            nBTTagCompound.remove("author");
                                            nBTTagCompound.remove("title");
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", "OnePacketCrasher");
                                            NettyInjection.this.channel.close();
                                            return;
                                        }
                                        if (nBTTagList.size() <= 0) {
                                            nBTTagCompound.remove("pages");
                                            nBTTagCompound.remove("author");
                                            nBTTagCompound.remove("title");
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", "Written Book with 0 Pages?");
                                            CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                            return;
                                        }
                                        if (string.length() > 0 && !object.hasItemMeta()) {
                                            nBTTagCompound.remove("pages");
                                            nBTTagCompound.remove("author");
                                            nBTTagCompound.remove("title");
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", "Invalid ItemMeta (Characters)");
                                            NettyInjection.this.channel.close();
                                            return;
                                        }
                                        if (string.length() <= 265) continue;
                                        nBTTagCompound.remove("pages");
                                        nBTTagCompound.remove("author");
                                        nBTTagCompound.remove("title");
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "WINDOW_CLICK", String.valueOf(new StringBuilder().append(string.length()).append(" -> 265 | Characters")));
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                }
                            }
                            if (packet instanceof PacketPlayInBlockPlace) {
                                int n;
                                packetPlayInChat = (PacketPlayInBlockPlace)packet;
                                object = CraftItemStack.asCraftMirror((net.minecraft.server.v1_8_R3.ItemStack)packetPlayInChat.getItemStack());
                                NBTTagCompound nBTTagCompound = packetPlayInChat.getItemStack().hasTag() ? packetPlayInChat.getItemStack().getTag() : null;
                                NBTTagList nBTTagList3 = nBTTagList = nBTTagCompound != null && nBTTagCompound.hasKeyOfType("pages", 9) ? nBTTagCompound.getList("pages", 8) : null;
                                if (!(CrashFix.getInstance().Books() || object.getType() != Material.WRITTEN_BOOK && object.getType() != Material.BOOK_AND_QUILL)) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", "Books are Disabled");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (object.getType() == Material.WRITTEN_BOOK && nBTTagCompound == null) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", "Invalid NBTTag");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (object.getType() == Material.WRITTEN_BOOK || object.getType() == Material.BOOK_AND_QUILL) {
                                    if (!NettyInjection.this.player.getItemInHand().getType().equals((Object)Material.WRITTEN_BOOK) && !NettyInjection.this.player.getItemInHand().getType().equals((Object)Material.BOOK_AND_QUILL)) {
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", "Invalid Book in Hand");
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                    if (NettyInjection.this.OpenTook.containsKey(object) && (Long)NettyInjection.this.OpenTook.get(object) > System.currentTimeMillis()) {
                                        NettyInjection.this.player.getInventory().removeItem(new ItemStack[]{object});
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", "Edit Book too fast");
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                    NettyInjection.this.OpenTook.put(object, System.currentTimeMillis() + 115L);
                                    if (!BookPackets.containsKey(NettyInjection.this.player.getName())) {
                                        BookPackets.put(NettyInjection.this.player.getName(), 0L);
                                    } else {
                                        BookPackets.replace(NettyInjection.this.player.getName(), BookPackets.get(NettyInjection.this.player.getName()) + 1L);
                                    }
                                    if (BookPackets.get(NettyInjection.this.player.getName()) > 14L) {
                                        NettyInjection.this.player.getInventory().removeItem(new ItemStack[]{object});
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", String.valueOf(new StringBuilder().append("PacketSpam \u00a78(").append(CrashFix.getInstance().getColor()[0]).append(BookPackets.get(NettyInjection.this.player.getName())).append("\u00a78)")));
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                }
                                if (packetPlayInChat.getFace() != 1 && packetPlayInChat.getFace() != 255 && packetPlayInChat.getFace() != 0 && packetPlayInChat.getFace() != 5 && packetPlayInChat.getFace() != 3 && packetPlayInChat.getFace() != 4 && packetPlayInChat.getFace() != 2 && packetPlayInChat.getFace() < 0 && packetPlayInChat.getFace() > 255) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", String.valueOf(new StringBuilder().append("Invalid Face \u00a78(\u00a74").append(packetPlayInChat.getFace()).append("\u00a78)")));
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (object.getType() == Material.FIREWORK) {
                                    if (NettyInjection.this.player.getItemInHand().getType() != Material.FIREWORK) {
                                        NettyInjection.this.player.getInventory().removeItem(new ItemStack[]{object});
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", "Invalid Firework");
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                    if (!BookPackets.containsKey(NettyInjection.this.player.getName())) {
                                        BookPackets.put(NettyInjection.this.player.getName(), 0L);
                                    } else {
                                        BookPackets.replace(NettyInjection.this.player.getName(), BookPackets.get(NettyInjection.this.player.getName()) + 1L);
                                    }
                                    if (BookPackets.get(NettyInjection.this.player.getName()) > 30L) {
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", String.valueOf(new StringBuilder().append("PacketSpam \u00a78(").append(CrashFix.getInstance().getColor()[1]).append("Firework\u00a78)")));
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                }
                                if (object.getType() == Material.FIREWORK && (n = object.getAmount()) > 64) {
                                    NettyInjection.this.player.getInventory().removeItem(new ItemStack[]{object});
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", String.valueOf(new StringBuilder().append("Too many Stacked Items \u00a78(").append(CrashFix.getInstance().getColor()[1]).append("Firework\u00a78)")));
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (object.getType() == Material.BOOK_AND_QUILL || object.getType() == Material.WRITTEN_BOOK) {
                                    assert (nBTTagCompound != null);
                                    if (nBTTagCompound.hasKey(":[{extra:[{") || Objects.requireNonNull(nBTTagCompound).toString().contains(":[{extra:[{")) {
                                        nBTTagCompound.remove("pages");
                                        nBTTagCompound.remove("author");
                                        nBTTagCompound.remove("title");
                                        NettyInjection.this.player.getInventory().removeItem(new ItemStack[]{object});
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", "OnePacketCrasher");
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                    assert (nBTTagList != null);
                                    if (nBTTagList.size() > 50 || nBTTagList.size() < 0) {
                                        nBTTagCompound.remove("pages");
                                        nBTTagCompound.remove("author");
                                        nBTTagCompound.remove("title");
                                        NettyInjection.this.player.getInventory().removeItem(new ItemStack[]{object});
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", String.valueOf(new StringBuilder().append(nBTTagList.size()).append(" -> 50 | Pages")));
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                    if (nBTTagList.size() > 1 && !object.hasItemMeta()) {
                                        nBTTagCompound.remove("pages");
                                        nBTTagCompound.remove("author");
                                        nBTTagCompound.remove("title");
                                        NettyInjection.this.player.getInventory().removeItem(new ItemStack[]{object});
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", "Invalid ItemMeta (Pages)");
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                    for (int i = 0; i < nBTTagList.size(); ++i) {
                                        string2 = ChatColor.stripColor((String)nBTTagList.getString(i).replaceAll("\\+", ""));
                                        if (string2.contains(":[{extra:[{") || string2.contains("extra:")) {
                                            nBTTagCompound.remove("pages");
                                            nBTTagCompound.remove("author");
                                            nBTTagCompound.remove("title");
                                            NettyInjection.this.player.getInventory().removeItem(new ItemStack[]{object});
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", "OnePacketCrasher");
                                            NettyInjection.this.channel.close();
                                            return;
                                        }
                                        if (string2.length() > 0 && !object.hasItemMeta()) {
                                            nBTTagCompound.remove("pages");
                                            nBTTagCompound.remove("author");
                                            nBTTagCompound.remove("title");
                                            NettyInjection.this.player.getInventory().removeItem(new ItemStack[]{object});
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", "Invalid ItemMeta (Characters)");
                                            NettyInjection.this.channel.close();
                                            return;
                                        }
                                        if (nBTTagList.size() <= 0) {
                                            nBTTagCompound.remove("pages");
                                            nBTTagCompound.remove("author");
                                            nBTTagCompound.remove("title");
                                            NettyInjection.this.player.getInventory().removeItem(new ItemStack[]{object});
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", "Written Book with 0 Pages?");
                                            CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                            return;
                                        }
                                        if (string2.length() <= 265) continue;
                                        nBTTagCompound.remove("pages");
                                        nBTTagCompound.remove("author");
                                        nBTTagCompound.remove("title");
                                        NettyInjection.this.player.getInventory().removeItem(new ItemStack[]{object});
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "BLOCK_PLACE", String.valueOf(new StringBuilder().append(string2.length()).append(" -> 265 | Characters")));
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                }
                            }
                            if (packet instanceof PacketPlayInSetCreativeSlot) {
                                packetPlayInChat = (PacketPlayInSetCreativeSlot)packet;
                                object = CraftItemStack.asCraftMirror((net.minecraft.server.v1_8_R3.ItemStack)packetPlayInChat.getItemStack());
                                NBTTagCompound nBTTagCompound = packetPlayInChat.getItemStack().hasTag() ? packetPlayInChat.getItemStack().getTag() : null;
                                NBTTagList nBTTagList4 = nBTTagList = nBTTagCompound != null && nBTTagCompound.hasKeyOfType("pages", 9) ? nBTTagCompound.getList("pages", 8) : null;
                                if (CrashFix.getInstance().getExploitPlayer().contains(NettyInjection.this.player)) {
                                    ((CraftPlayer)((NettyInjection)NettyInjection.this).player).getHandle().playerConnection.networkManager.channel.close();
                                    return;
                                }
                                if (!NettyInjection.this.player.getGameMode().equals((Object)GameMode.CREATIVE)) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "SET_CREATIVE_SLOT", "GameMode == Survival");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (NettyInjection.this.player.getOpenInventory() == null) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "SET_CREATIVE_SLOT", "Invalid Inventory");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (!CrashFix.getInstance().Books() && (object.getType().equals((Object)Material.WRITTEN_BOOK) || object.getType().equals((Object)Material.BOOK_AND_QUILL))) {
                                    NettyInjection.this.player.getInventory().remove(Material.WRITTEN_BOOK);
                                    NettyInjection.this.player.getInventory().remove(Material.BOOK_AND_QUILL);
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "SET_CREATIVE_SLOT", "Books are Disabled");
                                    return;
                                }
                                if (object.getType() == Material.WRITTEN_BOOK && nBTTagCompound == null) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "SET_CREATIVE_SLOT", "Invalid NBTTag");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (object.getType() == Material.BOOK_AND_QUILL || object.getType() == Material.WRITTEN_BOOK) {
                                    assert (nBTTagCompound != null);
                                    if (nBTTagCompound.hasKey(":[{extra:[{") || nBTTagCompound.toString().contains(":[{extra:[{")) {
                                        nBTTagCompound.remove("pages");
                                        nBTTagCompound.remove("author");
                                        nBTTagCompound.remove("title");
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "SET_CREATIVE_SLOT", "OnePacketCrasher");
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                    assert (nBTTagList != null);
                                    if (nBTTagList.size() > 50 || nBTTagList.size() < 0) {
                                        nBTTagCompound.remove("pages");
                                        nBTTagCompound.remove("author");
                                        nBTTagCompound.remove("title");
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "SET_CREATIVE_SLOT", String.valueOf(new StringBuilder().append(nBTTagList.size()).append(" -> 50 | Pages")));
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                    if (nBTTagList.size() > 1 && !object.hasItemMeta()) {
                                        nBTTagCompound.remove("pages");
                                        nBTTagCompound.remove("author");
                                        nBTTagCompound.remove("title");
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "SET_CREATIVE_SLOT", "Invalid ItemMeta (Pages)");
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                    for (int i = 0; i < nBTTagList.size(); ++i) {
                                        string2 = ChatColor.stripColor((String)nBTTagList.getString(i).replaceAll("\\+", ""));
                                        if (string2.contains(":[{extra:[{") || string2.contains("extra:")) {
                                            nBTTagCompound.remove("pages");
                                            nBTTagCompound.remove("author");
                                            nBTTagCompound.remove("title");
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "SET_CREATIVE_SLOT", "OnePacketCrasher");
                                            NettyInjection.this.channel.close();
                                            return;
                                        }
                                        if (string2.length() > 0 && !object.hasItemMeta()) {
                                            nBTTagCompound.remove("pages");
                                            nBTTagCompound.remove("author");
                                            nBTTagCompound.remove("title");
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "SET_CREATIVE_SLOT", "Invalid ItemMeta (Characters)");
                                            NettyInjection.this.channel.close();
                                            return;
                                        }
                                        if (nBTTagList.size() <= 0) {
                                            nBTTagCompound.remove("pages");
                                            nBTTagCompound.remove("author");
                                            nBTTagCompound.remove("title");
                                            CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "SET_CREATIVE_SLOT", "Written Book with 0 Pages?");
                                            CrashFix.getInstance().getExploitPlayer().add(NettyInjection.this.player);
                                            return;
                                        }
                                        if (string2.length() <= 265) continue;
                                        nBTTagCompound.remove("pages");
                                        nBTTagCompound.remove("author");
                                        nBTTagCompound.remove("title");
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "SET_CREATIVE_SLOT", String.valueOf(new StringBuilder().append(string2.length()).append(" -> 265 | Characters")));
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                }
                                if (object.getType() == Material.BOOK_AND_QUILL || object.getType() == Material.WRITTEN_BOOK) {
                                    if (!SetCreatives.containsKey(NettyInjection.this.player.getName())) {
                                        SetCreatives.put(NettyInjection.this.player.getName(), 0L);
                                    } else {
                                        SetCreatives.replace(NettyInjection.this.player.getName(), SetCreatives.get(NettyInjection.this.player.getName()) + 1L);
                                    }
                                    if (SetCreatives.get(NettyInjection.this.player.getName()) > 15L) {
                                        CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "SET_CREATIVE_SLOT", String.valueOf(new StringBuilder().append("PacketSpam \u00a78(").append(CrashFix.getInstance().getColor()[0]).append(SetCreatives.get(NettyInjection.this.player.getName())).append("\u00a78)")));
                                        NettyInjection.this.channel.close();
                                        return;
                                    }
                                }
                            }
                            if (packet instanceof PacketPlayInHeldItemSlot) {
                                packetPlayInChat = (PacketPlayInHeldItemSlot)packet;
                                if (CrashFix.getInstance().getExploitPlayer().contains(NettyInjection.this.player)) {
                                    ((CraftPlayer)((NettyInjection)NettyInjection.this).player).getHandle().playerConnection.networkManager.channel.close();
                                    return;
                                }
                                if (!(CrashFix.Instance.Books() || NettyInjection.this.player.getItemInHand().getType() != Material.BOOK_AND_QUILL && NettyInjection.this.player.getItemInHand().getType() != Material.WRITTEN_BOOK)) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "HELD_ITEM_SLOT", "Books are Disabled");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (packetPlayInChat.a() > 8 || packetPlayInChat.a() < 0) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "HELD_ITEM_SLOT", String.valueOf(new StringBuilder().append("Invalid Slot \u00a78(").append(CrashFix.getInstance().getColor()[0]).append(packetPlayInChat.a()).append("\u00a78)")));
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                            }
                            if (packet instanceof PacketPlayInFlying) {
                                packetPlayInChat = (PacketPlayInFlying)packet;
                                if (!Fly.containsKey(NettyInjection.this.player.getName())) {
                                    Fly.put(NettyInjection.this.player.getName(), 0L);
                                } else {
                                    Fly.replace(NettyInjection.this.player.getName(), Fly.get(NettyInjection.this.player.getName()) + 1L);
                                }
                                if (Fly.get(NettyInjection.this.player.getName()) > 750L) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "FLY_CRASH", "PacketSpam");
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if ((double)packetPlayInChat.e() < -90.0 || (double)packetPlayInChat.e() > 90.0) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "FLY_CRASH", String.valueOf(new StringBuilder().append("Invalid Position \u00a78(").append(CrashFix.getInstance().getColor()[1]).append("E/").append(packetPlayInChat.e()).append("\u00a78)")));
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                            }
                            if (packet instanceof PacketPlayInArmAnimation) {
                                if (!ArmAnimation.containsKey(NettyInjection.this.player.getName())) {
                                    ArmAnimation.put(NettyInjection.this.player.getName(), 0L);
                                } else {
                                    ArmAnimation.replace(NettyInjection.this.player.getName(), ArmAnimation.get(NettyInjection.this.player.getName()) + 1L);
                                }
                                if (ArmAnimation.get(NettyInjection.this.player.getName()) > 350L) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "SWING_ARM", String.valueOf(new StringBuilder().append("PacketSpam \u00a78(").append(CrashFix.getInstance().getColor()[0]).append(ArmAnimation.get(NettyInjection.this.player.getName())).append("\u00a78)")));
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                            }
                            if (packet instanceof PacketPlayInFlying.PacketPlayInPosition) {
                                packetPlayInChat = (PacketPlayInFlying.PacketPlayInPosition)packet;
                                if (!Position.containsKey(NettyInjection.this.player.getName())) {
                                    Position.put(NettyInjection.this.player.getName(), 0L);
                                } else {
                                    Position.replace(NettyInjection.this.player.getName(), Position.get(NettyInjection.this.player.getName()) + 1L);
                                }
                                if (Position.get(NettyInjection.this.player.getName()) > 280L) {
                                    NettyInjection.this.channel.close();
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "POSITION", String.valueOf(new StringBuilder().append("PacketSpam \u00a78(").append(CrashFix.getInstance().getColor()[0]).append(Position.get(NettyInjection.this.player.getName())).append("\u00a78)")));
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if ((double)packetPlayInChat.e() != 70.0 && (double)packetPlayInChat.e() != 0.0) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "POSITION", String.valueOf(new StringBuilder().append("Invalid Position \u00a78(").append(CrashFix.getInstance().getColor()[0]).append("E/").append(packetPlayInChat.e()).append("\u00a78)")));
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if ((double)packetPlayInChat.d() != 70.0 && (double)packetPlayInChat.d() != 0.0) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "POSITION", String.valueOf(new StringBuilder().append("Invalid Position \u00a78(").append(CrashFix.getInstance().getColor()[0]).append("D/").append(packetPlayInChat.d()).append("\u00a78)")));
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                            }
                            if (packet instanceof PacketPlayInFlying.PacketPlayInLook) {
                                packetPlayInChat = (PacketPlayInFlying.PacketPlayInLook)packet;
                                if (!LookPackets.containsKey(NettyInjection.this.player.getName())) {
                                    LookPackets.put(NettyInjection.this.player.getName(), 0L);
                                } else {
                                    LookPackets.replace(NettyInjection.this.player.getName(), LookPackets.get(NettyInjection.this.player.getName()) + 1L);
                                }
                                if (LookPackets.get(NettyInjection.this.player.getName()) > 300L) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "LOOK", String.valueOf(new StringBuilder().append("PacketSpam \u00a78(").append(CrashFix.getInstance().getColor()[0]).append(LookPackets.get(NettyInjection.this.player.getName())).append("\u00a78)")));
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if ((double)packetPlayInChat.e() > 90.0 || (double)packetPlayInChat.e() < -90.0) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "POSITION", String.valueOf(new StringBuilder().append("Invalid Look \u00a78(").append(CrashFix.getInstance().getColor()[0]).append("E/").append(packetPlayInChat.e()).append("\u00a78)")));
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (!packetPlayInChat.h()) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "POSITION", String.valueOf(new StringBuilder().append("Invalid Look \u00a78(").append(CrashFix.getInstance().getColor()[0]).append("H/").append(packetPlayInChat.h()).append("\u00a78)")));
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                                if (packetPlayInChat.a() != 0.0 || packetPlayInChat.b() != 0.0 || packetPlayInChat.c() != 0.0) {
                                    CrashFix.getInstance().getExploitPlayer().kick(NettyInjection.this.player, "LOOK", String.valueOf(new StringBuilder().append("Invalid Look \u00a78(").append(CrashFix.getInstance().getColor()[0]).append("A/").append(packetPlayInChat.a()).append("\u00a78 /").append(CrashFix.getInstance().getColor()[0]).append("B/").append(packetPlayInChat.b()).append("\u00a78)")));
                                    NettyInjection.this.channel.close();
                                    return;
                                }
                            }
                        }
                        catch (DecoderException decoderException) {}
                    }
                    catch (NullPointerException nullPointerException) {
                        // empty catch block
                    }
                }
                list.add(packet);
            }
        });
    }

    public NettyInjection(Player player) {
        this.alreadySent = new HashMap<Player, ArrayList<String>>();
        this.ChatTime = new HashMap<Player, Long>();
        this.CustomPayLoadTime = new HashMap<Player, Long>();
        this.SignedBook = new ArrayList<net.minecraft.server.v1_8_R3.ItemStack>();
        this.OpenTook = new HashMap<CraftItemStack, Long>();
        this.Packets = new HashMap<String, Long>();
        this.LastPackets = new HashMap<String, Long>();
        this.player = player;
    }

    static {
        ArmAnimation = new HashMap<String, Long>();
        CustomPayLoad = new HashMap<String, Long>();
        WindowClick = new HashMap<String, Long>();
        BookPackets = new HashMap<String, Long>();
        Position = new HashMap<String, Long>();
        Fly = new HashMap<String, Long>();
        SetCreatives = new HashMap<String, Long>();
        LookPackets = new HashMap<String, Long>();
    }
}

