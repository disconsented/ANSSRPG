package disconsented.anssrpg.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PlayerFile {
	static String PERK_LIST = "Perk List";
	static String SKILL_LIST = "Skill List";
	
	
	public static void loadPlayer(String playerID) {		
		File playerFile = new File("anssdat/",playerID+".dat");
		JSONParser parser = new JSONParser();		 
		try {	 
			Object obj = parser.parse(new FileReader(playerFile));	 
			JSONObject jsonObject = (JSONObject) obj;	 
			
			// loop array
			JSONObject skillMap =  (JSONObject) jsonObject.get(SKILL_LIST);
			//JOptionPane.showMessageDialog(null, skillMap);
			
			JSONArray perkList =  (JSONArray) jsonObject.get(PERK_LIST);
			//JOptionPane.showMessageDialog(null, perkList);
			PlayerData temp = new PlayerData(perkList, skillMap, playerID);
			DataSave.addPlayer(temp, playerID);
	 
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.err.println("Player data not found for " + playerID);
			System.err.println("Creating file and passing blank data");
			ArrayList perkList = new ArrayList();
			HashMap skillMap = new HashMap();
			PlayerData temp = new PlayerData(perkList, skillMap, playerID);
			DataSave.addPlayer(temp, playerID);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		
	}
	/**
	 * Write all of the player data to a file
	 * 
	 */

	public static void writePlayer(PlayerData player){	
		File dataLocation = new File("anssdat");
		File playerFile = new File("anssdat",player.playerID+".dat");
		JSONObject obj = new JSONObject();
		obj.put(PERK_LIST, player.perkList);
		obj.put(SKILL_LIST, player.skillExp);		
		
		try {			 
			if (!dataLocation.mkdirs())
			{
			   System.err.println("Could not create parent directories ");
			}
			FileWriter file = new FileWriter(playerFile);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
			//JOptionPane.showMessageDialog(null, playerFile);
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
