package disconsented.anssrpg.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class Responce implements IMessage{
    public String responce;

    public Responce() {}

    public Responce(String responce) {
        this.responce = responce;
    }

    @Override
    public void toBytes(ByteBuf buf) {
    	ByteBufUtils.writeUTF8String(buf, responce);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.responce = ByteBufUtils.readUTF8String(buf);
    }
}
