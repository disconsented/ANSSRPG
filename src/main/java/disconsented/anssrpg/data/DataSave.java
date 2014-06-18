package disconsented.anssrpg.data;
/**
 * Handles non-shut down saving and loading of player data
 * Holds the player hashmap
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import disconsented.anssrpg.config.JsonConfigHandler;

/**
 * 
 * @author James
 *
 *onEntityJoinWorld - Probably not needed
 *onLivingDeath - Saves data
 */
public class DataSave {
	static HashMap players = new HashMap();
	
	public static PlayerData getPlayerData(String playerID){
		return (PlayerData) players.get(playerID);		
	}
	public static void addPlayer(PlayerData player, String PlayerID){
		players.put(PlayerID,player);
	}
	/**
	 * Load player data
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerLoggedInEvent(PlayerLoggedInEvent event){
		PlayerFile.loadPlayer(event.player.getPersistentID().toString());
	}
	/**
	 * Saves player data
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerLoggedOutEvent(PlayerLoggedOutEvent event){
		PlayerFile.writePlayer((PlayerData) players.get(event.player.getPersistentID().toString()));
		
	}
	/**
	 * Saves player data (crash damage mitigation)
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerRespawnEvent (PlayerRespawnEvent event){
		PlayerFile.writePlayer((PlayerData) players.get(event.player.getPersistentID().toString()));
	}

}
