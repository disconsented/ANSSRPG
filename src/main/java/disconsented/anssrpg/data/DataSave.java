package disconsented.anssrpg.data;
/**
 * Handles non-shut down saving and loading of player data
 * Holds the player hashmap
 */

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import disconsented.anssrpg.Settings;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.player.PlayerFile;

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
		if (players.get(playerID) != null){
			return (PlayerData) players.get(playerID);
		}else{
			createPlayer(playerID);
			return (PlayerData) players.get(playerID);
		}
	}
	public static void addPlayer(PlayerData player, String PlayerID){
		players.put(PlayerID,player);
	}
	public static void createPlayer(String playerID){
		ArrayList tempAL = new ArrayList();
		HashMap tempHM = new HashMap();
		PlayerData temp = new PlayerData(tempAL, tempHM, playerID);
		addPlayer(temp, playerID);
		tempAL.clear();
		tempHM.clear();
	}
	/**
	 * Load player data
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerLoggedInEvent(PlayerLoggedInEvent event){
		if (Settings.getDebug()){
			System.out.println("Player "+event.player.getCommandSenderName()+" with UUID:"+event.player.getPersistentID().toString()+"has logged in");
			System.out.println("Loading player data");
		}		
		PlayerFile.loadPlayer(event.player.getPersistentID().toString());		
	}
	/**
	 * Saves player data
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerLoggedOutEvent(PlayerLoggedOutEvent event){
		if (Settings.getDebug()){
			System.out.println("Player "+event.player.getCommandSenderName()+" with UUID:"+event.player.getPersistentID().toString()+"has logged out");
			System.out.println("Saving player data");
		}
		PlayerFile.writePlayer((PlayerData) players.get(event.player.getPersistentID().toString()));		
	}
	/**
	 * Saves player data (crash damage mitigation)
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerRespawnEvent (PlayerRespawnEvent event){
		if (Settings.getDebug()){
			System.out.println("Player "+event.player.getCommandSenderName()+" with UUID:"+event.player.getPersistentID().toString()+"has respawned");
			System.out.println("Saving player data");
		}
		PlayerFile.writePlayer((PlayerData) players.get(event.player.getPersistentID().toString()));
	}	
}