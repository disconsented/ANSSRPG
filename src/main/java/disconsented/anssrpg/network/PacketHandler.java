package disconsented.anssrpg.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import disconsented.anssrpg.common.ChatUtil;
import disconsented.anssrpg.common.Reference;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper(Reference.ID);

    public static void init() {
        INSTANCE.registerMessage(ChatUtil.PacketNoSpamChat.Handler.class, ChatUtil.PacketNoSpamChat.class, 0, Side.CLIENT);
    }

    public static void sendToAllAround(IMessage message, TileEntity te, int range) {
        INSTANCE.sendToAllAround(message, new NetworkRegistry.TargetPoint(te.getWorldObj().provider.dimensionId, te.xCoord, te.yCoord, te.zCoord, range));
    }

    public static void sendToAllAround(IMessage message, TileEntity te) {
        sendToAllAround(message, te, 64);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player) {
        INSTANCE.sendTo(message, player);
    }
}
