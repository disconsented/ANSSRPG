package disconsented.anssrpg.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import disconsented.anssrpg.Main;
import disconsented.anssrpg.player.PlayerHandler;

public class RequestHandler implements IMessageHandler<Request, IMessage>{

	@Override
	public IMessage onMessage(Request message, MessageContext ctx) {
		System.out.println("Request recieved");
		Main.snw.sendTo(new Responce(PlayerHandler.addPerk(message.slug, ctx.getServerHandler().playerEntity.getPersistentID().toString())), ctx.getServerHandler().playerEntity);
		return null;
	}

}
