package disconsented.anssrpg.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import disconsented.anssrpg.gui.PerkGUI;
import disconsented.anssrpg.perk.LocalPerk;

public class PerkInfoHandler implements IMessageHandler<PerkInfo, IMessage>{

	@Override
	public IMessage onMessage(PerkInfo message, MessageContext ctx) {
		PerkGUI.addPerk(new LocalPerk(message.name, message.description, message.pointCost, message.requirements));
		return null;
	}

}
