package disconsented.anssrpg.gui;

import cpw.mods.fml.common.gameevent.PlayerEvent;
import disconsented.anssrpg.Main;

public class TestEvent {
	public void onBlockActivatedEvent (PlayerEvent.ItemPickupEvent  event){
		event.player.openGui(Main.instance, 0, event.player.getEntityWorld(), 0, 0, 0);
		System.out.println("Blag");
	}
}
