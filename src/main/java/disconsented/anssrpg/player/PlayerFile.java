package disconsented.anssrpg.player;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import net.minecraft.world.WorldSavedData;

public class PlayerFile {
	static String PERK_LIST = "Perk List";
	static String SKILL_LIST = "Skill List";
	
	
	public static void loadPlayer(String playerID) {
	}
	/**
	 * Write all of the player data to a file
	 * 
	 */

	public static void writePlayer(PlayerData player){	
		File dataFolder = new File("data");
		File dataLocation = new File(dataFolder, player.playerID);
		dataFolder.mkdirs();
		Gson gson = new Gson();  
		String json = gson.toJson(player);
		try {
			//write converted json data to a file named "file.json"
			FileWriter writer = new FileWriter(dataLocation);
			writer.write(json);
			writer.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
