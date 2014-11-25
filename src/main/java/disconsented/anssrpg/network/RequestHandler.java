package disconsented.anssrpg.network;

import handler.PlayerHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import disconsented.anssrpg.Main;

public class RequestHandler implements IMessageHandler<Request, IMessage>{
	private String responceText;
	@Override
	public IMessage onMessage(Request message, MessageContext ctx) {
		System.out.println("Request recieved");		
		responceText = PlayerHandler.addPerk(message.slug, ctx.getServerHandler().playerEntity.getPersistentID().toString());
		return new Responce(responceText);
	}

}
