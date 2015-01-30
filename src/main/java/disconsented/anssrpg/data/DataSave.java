/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package disconsented.anssrpg.data;
/**
 * Handles non-shut down saving and loading of player data
 * Holds the player hashmap
 */

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.perk.Perk;
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
	public static PlayerData getPlayerData(String playerID){
		PlayerData player = PlayerStore.getInstance().getPlayer(playerID);
		if (player != null){
			return player;
		}else{
			createPlayer(playerID);
			return PlayerStore.getInstance().getPlayer(playerID);
		}
	}
	public static void addPlayer(PlayerData player, String PlayerID){
		PlayerStore.getInstance().addPlayer(player);
	}
	public static void createPlayer(String playerID){
		ArrayList tempAL = new ArrayList();
		HashMap tempHM = new HashMap();
		PlayerData temp = new PlayerData(tempAL, tempHM, playerID, 0);
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
	    System.out.println(event.player.getDisplayName());
	    
	    if (event.player instanceof EntityPlayerMP){
	         EntityPlayerMP player = (EntityPlayerMP) event.player;
	         String playerData = player.playerData;
	         System.out.println("Maybe this works/? "+ playerData);
	    }
		if (Settings.getDebug()){
			System.out.println("Player "+event.player.getCommandSenderName()+" with UUID:"+event.player.getPersistentID().toString()+"has logged in");
			System.out.println("Loading player data");
		}		
		PlayerFile.loadPlayer(event.player.getPersistentID().toString());
		ArrayList<Perk> temp = PerkStore.getInstance().getPerks();
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
		PlayerFile.writePlayer(PlayerStore.getInstance().getPlayer(event.player.getPersistentID().toString()));
		PlayerStore.getInstance().getAllData().remove(event.player.getPersistentID().toString());
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
		PlayerFile.writePlayer(PlayerStore.getInstance().getPlayer(event.player.getPersistentID().toString()));
	}	
}
