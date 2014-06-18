/**
 * 
 */
package disconsented.anssrpg.config;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import disconsented.anssrpg.data.PerkObject;
import disconsented.anssrpg.data.SkillHandler;
import disconsented.anssrpg.data.SkillObject;
/**
 * @author james
 * Json Config's
 */
public class JsonConfigHandler {
	static File configFile = new File("config/ANSSRPG", "main.cfg");
	static String DEBUG_INFO="Debug Info";
	static String LEVEL_CURVE = "Level Curve";
	static String SKILL_LIST = "Skill List";
	static String SKILL_TYPE = "Skill Type";
	static String ENTITY_NAME = "Entity Name";
	static String ITEM_NAME = "Item Name";
	static String BLOCK_NAME = "Block Name";
	static String ENTRY_EXP = "Entry Experience";
	static String ENTRY_NAME = "Entry Name";
	static String PRINTOUT_ITEM = "Printout items";
	static String PRINTOUT_BLOCK = "Printout blocks";
	static String PRINTOUT_ENTITY = "Printout entitys";
	static boolean printOutItem = false;
	static boolean printOutBlock = false;
	static boolean printOutEntity = false;
	static boolean debug = false;
	static double levelCurve = 1.6;
	
	
	static ArrayList skillNames = new ArrayList();
	static ArrayList skillType = new ArrayList();
	
	public static void loadConfigs(){
		loadConfig();
		for(int i = 0; i < skillNames.size();i++){
			loadSkillConfig(skillNames.get(i).toString());
		}
	}
	private static void loadConfig() {	
		JSONParser parser = new JSONParser();		 
		try {	 
			Object obj = parser.parse(new FileReader(configFile));	 
			JSONObject jsonObject = (JSONObject) obj;
	 
			debug = (boolean) jsonObject.get(DEBUG_INFO);
			System.out.println("ANSSRPG | "+DEBUG_INFO+ " = " + debug);
	 
			levelCurve = (double) jsonObject.get(LEVEL_CURVE);
			System.out.println("ANSSRPG | "+LEVEL_CURVE+ " = " + levelCurve);
			
			printOutItem = (boolean) jsonObject.get(PRINTOUT_ITEM);
			System.out.println("ANSSRPG | "+PRINTOUT_ITEM+ " = " + printOutItem);
			
			printOutBlock = (boolean) jsonObject.get(PRINTOUT_BLOCK);
			System.out.println("ANSSRPG | "+PRINTOUT_BLOCK+ " = " + printOutBlock);
			
			printOutEntity = (boolean) jsonObject.get(PRINTOUT_ENTITY);
			System.out.println("ANSSRPG | "+PRINTOUT_ENTITY+ " = " + printOutEntity);
	 
			// loop array
			skillNames = (JSONArray) jsonObject.get(SKILL_LIST);
			
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			createConfig();
		} catch (IOException e) {
			e.printStackTrace();
			createConfig();
		} catch (ParseException e) {
			e.printStackTrace();
			createConfig();
		} catch (NullPointerException e){
			e.printStackTrace();
			createConfig();
		}
	}
	private static void createConfig(){		
		JSONObject obj = new JSONObject();
		obj.put(DEBUG_INFO, false);
		obj.put(LEVEL_CURVE, 1.6);		
		obj.put(PRINTOUT_ITEM, false);
		obj.put(PRINTOUT_BLOCK, false);
		obj.put(PRINTOUT_ENTITY, false);
		
		JSONArray listSkillName = new JSONArray();
		for (int i = 0; i < 4; i++){
			listSkillName.add("Example Skill");
		}
		JSONArray listSkillType = new JSONArray();
		for ( int i = 0;i < 4; i++){
			listSkillType.add(1);
		}
		obj.put(SKILL_LIST, listSkillName);
		obj.put(SKILL_TYPE, listSkillType);
		try {			 
			FileWriter file = new FileWriter(configFile);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
	 
		} catch (IOException e) {
		}
	}
	private static void loadSkillConfig(String name){		
		JSONParser parser = new JSONParser();		 
		try {	//If it isn't successful its just skipped
				ArrayList tempEntryName = new ArrayList();// Temps, remember to clear em after storing the data in the main array
				ArrayList tempEXP = new ArrayList();
				
				int tempType = 1;
					
				File skillConfig = new File("config/ANSSRPG",name.replaceAll("[^A-Za-z0-9]", "")+".cfg");
				Object obj = parser.parse(new FileReader(skillConfig));	 
				JSONObject jsonObject = (JSONObject) obj;
				JSONArray entryID = (JSONArray) jsonObject.get(BLOCK_NAME);
				tempType = Integer.parseInt(jsonObject.get(SKILL_TYPE).toString());
					
				entryID = (JSONArray) jsonObject.get(ENTRY_NAME);					// loop array
	
				tempEntryName = entryID; //testing directly placing the data into the arraylist form json
				//JOptionPane.showMessageDialog(null, tempEntryName);
					
				tempEXP = (JSONArray) jsonObject.get(ENTRY_EXP);

				tempEntryName = (JSONArray) jsonObject.get(ENTRY_EXP);
				SkillObject tempObject = new SkillObject(tempEXP, name, tempEXP, 0);
				SkillHandler.add(tempObject);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				createSkillConfig(name);
			} catch (IOException e) {
				e.printStackTrace();
				createSkillConfig(name);
			} catch (ParseException e) {
				e.printStackTrace();
				createSkillConfig(name);
			} catch (NullPointerException e){
				e.printStackTrace();
				createSkillConfig(name);
			}
	}
	private static void createSkillConfig(String name){		
		File skillConfig = new File("config/ANSSRPG",name.replaceAll("[^A-Za-z0-9]", "")+".cfg");
		JSONObject obj = new JSONObject();
		obj.put(SKILL_TYPE, 1);
		JSONArray listEntryName = new JSONArray();
		JSONArray listEntryXP = new JSONArray();
		for ( int i = 0;i < 4; i++){
			listEntryName.add("REPLACEMEH");
		}
		for ( int i = 0;i < 4; i++){
			listEntryXP.add(1);
		}		
	
		obj.put(ENTRY_NAME,listEntryName);		
		obj.put(ENTRY_EXP,listEntryXP);
		try {		 
				FileWriter file = new FileWriter(skillConfig);
				file.write(obj.toJSONString());
				file.flush();
				file.close();	 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	/**
	 * Single file for all perks
	 * each obj entry will be a perk
	 */
	private void createPerkConfig(){
		JSONObject obj = new JSONObject();		
		//String name, Block unlockBlock, String description, int pointCost, ArrayList requirementName, ArrayList requirementLevel
		ArrayList tempName = new ArrayList();
		ArrayList tempLevel = new ArrayList();
		tempName.add("Foo");
		tempLevel.add(1);
		tempName.add("Bar");
		tempLevel.add(5);
		tempName.add("FooBar");
		tempLevel.add(10);
		PerkObject temp = new PerkObject("Mine Diamond", Blocks.diamond_ore, "Ermagawd diminds!", 5, tempName, tempLevel);
		ArrayList tempName2 = new ArrayList();
		ArrayList tempLevel2 = new ArrayList();
		tempName2.add("ooF");
		tempLevel.add(1);
		tempName2.add("raB");
		tempLevel.add(5);
		tempName2.add("RabFoo");
		tempLevel.add(10);
		PerkObject temp2 = new PerkObject("Mine Diamond", Blocks.diamond_ore, "Ermagawd diminds!", 5, tempName, tempLevel);
		obj.put("Example 1", temp);
		obj.put("Example 2", temp2);
		
		try {
			 
			FileWriter file = new FileWriter(configFile);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void loadPerkConfig(){
		
	}
	
	public static boolean getDebug(){
		return  debug;
	}
	public static boolean getPrintItem(){
		return printOutItem;
	}
	public static boolean getPrintBlock(){
		return printOutBlock;
	}
	public static boolean getPrintEntity(){
		return printOutEntity;
	}
	public static double getLevelCurve() {
		return levelCurve;
	}
}
