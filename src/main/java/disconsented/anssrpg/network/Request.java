package disconsented.anssrpg.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class Request implements IMessage{
    public int extremelyImportantInteger;

    public Request() {}

    public Request(int a) {
        this.extremelyImportantInteger = a;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(extremelyImportantInteger);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.extremelyImportantInteger = buf.readInt();
    }
}
