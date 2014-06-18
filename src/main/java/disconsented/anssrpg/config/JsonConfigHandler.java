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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import disconsented.anssrpg.data.PerkObject;
import disconsented.anssrpg.data.SkillInfo;
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
	
	static ArrayList<SkillInfo> skillList = new ArrayList<SkillInfo>();
	static ArrayList skillNames = new ArrayList();
	static ArrayList skillType = new ArrayList();
	
	
	
	public static void loadConfig() {	
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
			JSONArray msg = (JSONArray) jsonObject.get(SKILL_LIST);
			System.out.println(msg);
			Iterator<String> iterator = msg.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
				skillNames.add(iterator.next());
			}
	 
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
	public static void createConfig(){		
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
	public static void loadSkillConfig(){
		for(int i = 0;i < skillNames.size();i++){
			JSONParser parser = new JSONParser();		 
			try {	
					ArrayList tempEntryName = new ArrayList();// Temps, remember to clear em after storing the data in the main array
					ArrayList tempEXP = new ArrayList();
					
					File skillConfig = new File("config/ANSSRPG",skillNames.get(i).toString().toLowerCase().replaceAll("[^A-Za-z0-9]", "")+".cfg");
					Object obj = parser.parse(new FileReader(skillConfig));	 
					JSONObject jsonObject = (JSONObject) obj;
					JSONArray entryID = (JSONArray) jsonObject.get(BLOCK_NAME);
						entryID = (JSONArray) jsonObject.get(ENTRY_NAME);					// loop array
	
						tempEntryName = entryID;
						System.out.println("###############################################");
						System.out.println(entryID);
					
					JSONArray exp = (JSONArray) jsonObject.get(ENTRY_EXP);
					System.out.println(exp);
					Iterator<String> iterator1 = exp.iterator();
					while (iterator1.hasNext()) {
						System.out.println(iterator1.next());
						tempEXP.add(iterator1.next());
					}
					System.out.println("TEMP ENTRY NAMES: "+tempEntryName);
					System.out.println("TEMP EXP: "+tempEXP);
		 
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					createSkillConfig();
				} catch (IOException e) {
					e.printStackTrace();
					createSkillConfig();
				} catch (ParseException e) {
					e.printStackTrace();
					createSkillConfig();
				} catch (NullPointerException e){
					e.printStackTrace();
					createSkillConfig();
				}
			}
	}
	public static void createSkillConfig(){		
		for(int i = 0;i < skillNames.size();i++){
			File skillConfig = new File("config/ANSSRPG",skillNames.get(i).toString().toLowerCase().replaceAll("[^A-Za-z0-9]", "")+".cfg");
			JSONObject obj = new JSONObject();
			JSONArray listEntryName = new JSONArray();
			JSONArray listEntryXP = new JSONArray();
			for ( int x = 0;i < 4; i++){
				listEntryName.add("REPLACEMEH");
			}
			for ( int x = 0;i < 4; i++){
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
		}
	public void createPerkConfig(){
		JSONObject obj = new JSONObject();
		try {
			 
			FileWriter file = new FileWriter(configFile);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadPerkConfig(){
		
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
	public static ArrayList getSkillList(){
		return skillList;
	}
	public static double getLevelCurve() {
		return levelCurve;
	}
}
