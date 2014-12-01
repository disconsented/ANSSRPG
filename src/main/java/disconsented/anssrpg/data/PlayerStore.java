/**
 * 
 */
package disconsented.anssrpg.data;

import java.util.HashMap;

import disconsented.anssrpg.player.PlayerData;

/**
 * @author Disconsented
 * Stores player's into a hashmap with the Key being their UUID
 */
public class PlayerStore {
	private static HashMap <String, PlayerData> data = new HashMap<String, PlayerData>();
	
	private static PlayerStore instance = null;
	
	protected PlayerStore() {/* Exists only to defeat instantiation.*/}
	
	public static PlayerStore getInstance() {
		if(instance == null) {
			instance = new PlayerStore();
		}
		return instance;
	}
	public static void addPlayer(PlayerData player) {
		data.put(player.getPlayerID(), player);
	}
	public static PlayerData getPlayer(String playerID)
	{
		return data.get(playerID);
	}

}
