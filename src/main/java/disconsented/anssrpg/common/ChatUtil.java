package disconsented.anssrpg.common;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import disconsented.anssrpg.network.PacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;
import java.util.List;

public class ChatUtil {

    /**
     * @author tterrag1098
     *
     * Ripped from EnderCore (and slightly altered)
     */
    public static class PacketNoSpamChat implements IMessage {

        private IChatComponent[] chatLines;

        public PacketNoSpamChat() {
            chatLines = new IChatComponent[0];
        }

        private PacketNoSpamChat(IChatComponent... lines) {
            // this is guaranteed to be >1 length by accessing methods
            this.chatLines = lines;
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeInt(chatLines.length);
            for (IChatComponent c : chatLines) {
                ByteBufUtils.writeUTF8String(buf, IChatComponent.Serializer.func_150696_a(c));
            }
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            chatLines = new IChatComponent[buf.readInt()];
            for (int i = 0; i < chatLines.length; i++) {
                chatLines[i] = IChatComponent.Serializer.func_150699_a(ByteBufUtils.readUTF8String(buf));
            }
        }

        public static class Handler implements IMessageHandler<PacketNoSpamChat, IMessage> {

            @Override
            public IMessage onMessage(PacketNoSpamChat message, MessageContext ctx) {
                sendNoSpamMessages(message.chatLines);
                return null;
            }
        }
    }

    private static final int DELETION_ID = 2525277;
    private static int lastAdded;

    private static void sendNoSpamMessages(IChatComponent[] messages) {
        GuiNewChat chat = Minecraft.getMinecraft().ingameGUI.getChatGUI();
        for (int i = DELETION_ID + messages.length - 1; i <= lastAdded; i++) {
            chat.deleteChatLine(i);
        }
        for (int i = 0; i < messages.length; i++) {
            chat.printChatMessageWithOptionalDeletion(messages[i], DELETION_ID + i);
        }
        lastAdded = DELETION_ID + messages.length - 1;
    }

    /**
     * Returns a standard {@link ChatComponentText} for the given {@link String}.
     *
     * @param s
     *          The string to wrap.
     * @return An {@link IChatComponent} containing the string.
     */
    public static IChatComponent wrap(String s) {
        return new ChatComponentText(s);
    }

    /**
     * @see #wrap(String)
     */
    public static IChatComponent[] wrap(String... s) {
        IChatComponent[] ret = new IChatComponent[s.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = wrap(s[i]);
        }
        return ret;
    }

    public static IChatComponent[] wrap(ArrayList<String> s) {
        IChatComponent[] ret = new IChatComponent[s.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = wrap(s.get(i));
        }
        return ret;
    }

    /**
     * Returns a translatable chat component for the given string and format args.
     *
     * @param s
     *          The string to format
     * @param args
     *          The args to apply to the format
     */
    public static IChatComponent wrapFormatted(String s, Object... args) {
        return new ChatComponentTranslation(s, args);
    }

    /**
     * Simply sends the passed lines to the player in a chat message.
     *
     * @param player
     *          The player to send the chat to
     * @param lines
     *          The lines to send
     */
    public static void sendChat(EntityPlayer player, String... lines) {
        sendChat(player, wrap(lines));
    }

    /**
     * Localizes the lines before sending them.
     *
     * @see #sendChat(EntityPlayer, String...)
     */
    public static void sendChatUnloc(EntityPlayer player, String... unlocLines) {
        sendChat(player, localizeAll(unlocLines));
    }

    /**
     * Sends all passed chat components to the player.
     *
     * @param player
     *          The player to send the chat lines to.
     * @param lines
     *          The {@link IChatComponent chat components} to send.yes
     */
    public static void sendChat(EntityPlayer player, IChatComponent... lines) {
        for (IChatComponent c : lines) {
            player.addChatComponentMessage(c);
        }
    }

    /**
     * Localizes the strings before sending them.
     *
     * @see #sendNoSpamClient(String...)
     */
    public static void sendNoSpamClientUnloc(String... unlocLines) {
        sendNoSpamClient(localizeAll(unlocLines));
    }

    /**
     * Same as {@link #sendNoSpamClient(IChatComponent...)}, but wraps the Strings
     * automatically.
     *
     * @param lines
     *          The chat lines to send
     * @see #wrap(String)
     */
    public static void sendNoSpamClient(String... lines) {
        sendNoSpamClient(wrap(lines));
    }

    /**
     * Same as {@link #sendNoSpamClient(IChatComponent...)}, but wraps the ArrayList
     * automatically.
     *
     * @param lines
     *          The chat lines to send
     * @see #wrap(String)
     */
    public static void sendNoSpamClient(ArrayList<String> lines) {
        sendNoSpamClient(wrap(lines));
    }


    /**
     * Skips the packet sending, unsafe to call on servers.
     *
     * @see #sendNoSpam(EntityPlayerMP, IChatComponent...)
     */
    public static void sendNoSpamClient(IChatComponent... lines) {
        sendNoSpamMessages(lines);
    }

    /**
     * Localizes the strings before sending them.
     *
     * @see #sendNoSpam(EntityPlayer, String...)
     */
    public static void sendNoSpamUnloc(EntityPlayer player, String... unlocLines) {
        sendNoSpam(player, localizeAll(unlocLines));
    }

    /**
     * @see #wrap(String)
     * @see #sendNoSpam(EntityPlayer, IChatComponent...)
     */
    public static void sendNoSpam(EntityPlayer player, String... lines) {
        sendNoSpam(player, wrap(lines));
    }

    /**
     * First checks if the player is instanceof {@link EntityPlayerMP} before
     * casting.
     *
     * @see #sendNoSpam(EntityPlayerMP, IChatComponent...)
     */
    public static void sendNoSpam(EntityPlayer player, IChatComponent... lines) {
        if (player instanceof EntityPlayerMP) {
            sendNoSpam((EntityPlayerMP) player, lines);
        }
    }

    /**
     * Localizes the strings before sending them.
     *
     * @see #sendNoSpam(EntityPlayerMP, String...)
     */
    public static void sendNoSpamUnloc(EntityPlayerMP player, String... unlocLines) {
        sendNoSpam(player, localizeAll(unlocLines));
    }

    /**
     * @see #wrap(String)
     * @see #sendNoSpam(EntityPlayerMP, IChatComponent...)
     */
    public static void sendNoSpam(EntityPlayerMP player, String... lines) {
        sendNoSpam(player, wrap(lines));
    }

    /**
     * Sends a chat message to the client, deleting past messages also sent via
     * this method.
     * <p>
     * Credit to RWTema for the idea
     *
     * @param player
     *          The player to send the chat message to
     * @param lines
     *          The chat lines to send.
     */
    public static void sendNoSpam(EntityPlayerMP player, IChatComponent... lines) {
        if (lines.length > 0)
            PacketHandler.INSTANCE.sendTo(new PacketNoSpamChat(lines), player);
    }

    public static String getFormattedText(String string) {
        return string.replaceAll("&", "\u00A7");
    }

    public static String localize(String key, Object ... format) {
        return getFormattedText(StatCollector.translateToLocalFormatted(key, format));
    }
    
    /**
     * Localizes all strings in a list, using the prefix.
     *
     * @param unloc
     *          The list of unlocalized strings.
     * @return A list of localized versions of the passed strings.
     */
    public static List<String> localizeAll(List<String> unloc) {
        List<String> ret = Lists.newArrayList();
        for (String s : unloc)
            ret.add(localize(s));

        return ret;
    }

    public static String[] localizeAll(String... unloc) {
        String[] ret = new String[unloc.length];
        for (int i = 0; i < ret.length; i++)
            ret[i] = localize(unloc[i]);

        return ret;
    }
}
