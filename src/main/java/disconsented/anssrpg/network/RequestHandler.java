package disconsented.anssrpg.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import disconsented.anssrpg.Main;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.handler.PlayerHandler;

public class RequestHandler implements IMessageHandler<Request, IMessage>{
	private String responceText;
	@Override
	public IMessage onMessage(Request message, MessageContext ctx) {
		//PlayerData player = PlayerStore.getInstance().getPlayer(ctx.)
		//responceText = PlayerHandler.addPerk(null, null);
		return new Responce(responceText);
	}

}
