package disconsented.anssrpg.player;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.minecraft.server.MinecraftServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.data.DataSave;
import disconsented.anssrpg.data.PlayerStore;

public class PlayerFile {	
	
	
	
	public static void loadPlayer(String playerID) {
		File dataFolder = Settings.getInstance().getFolder();
		dataFolder.mkdirs();
		String temp = dataFolder.getParentFile().getPath();
		File dataLocation = new File(dataFolder, playerID);
		try{
			FileReader reader = new FileReader(dataLocation);
            Gson gson = new GsonBuilder().create();
            PlayerData player = gson.fromJson(reader, PlayerData.class);
            PlayerStore.addPlayer(player);
            }
		catch(FileNotFoundException e){
			DataSave.createPlayer(playerID);
			if (Settings.getInstance().getDebug()){
				e.printStackTrace();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Write all of the player data to a file
	 * 
	 */

	public static void writePlayer(PlayerData player){
		File dataFolder = Settings.getInstance().getFolder();
		File dataLocation = new File(dataFolder, player.getPlayerID());
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
