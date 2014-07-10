package disconsented.anssrpg.debug;

import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.player.PlayerFile;

public class DebugEvent {
	@SubscribeEvent
	public void onPlayerLoggedInEvent(PlayerLoggedInEvent event){
		if (event.player instanceof EntityPlayerMP){
			
		}
	}
}
