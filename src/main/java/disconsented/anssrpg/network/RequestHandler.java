package disconsented.anssrpg.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class RequestHandler implements IMessageHandler<Request, IMessage>{

	@Override
	public IMessage onMessage(Request message, MessageContext ctx) {
		System.out.println(message.extremelyImportantInteger);
		System.out.println(message.toString());
		System.out.println(ctx.toString());
		return null;
	}

}
