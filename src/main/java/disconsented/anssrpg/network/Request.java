package disconsented.anssrpg.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class Request implements IMessage{
    public String slug;
    public String uuid;

    public Request() {}

    public Request(String slug, String uuid) {
        this.slug = slug;
    }

    @Override
    public void toBytes(ByteBuf buf) {
    	ByteBufUtils.writeUTF8String(buf, slug);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.slug = ByteBufUtils.readUTF8String(buf);
    }
}
