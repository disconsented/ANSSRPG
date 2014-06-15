/**
 * 
 */
package disconsented.anssrpg.config;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * @author james
 * Json Config reader
 */
public class JsonHandler {
	static File configFile = new File("config/ANSSRPG", "main.cfg");
	static String DEBUG_INFO="Debug Info";
	static String LEVEL_CURVE = "Level Curve";
	static String SKILL_LIST = "Skill List";
	static String PRINTOUT_ITEM = "Printout items";
	static String PRINTOUT_BLOCK = "Printout blocks";
	static String PRINTOUT_ENTITY = "Printout entitys";
	static boolean printOutItem = false;
	static boolean printOutBlock = false;
	static boolean printOutEntity = false;
	static boolean debug = false;
	static double levelCurve = 1.6;
	
	
	
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
		}
	}
	public static void createConfig(){		
		JSONObject obj = new JSONObject();
		obj.put(DEBUG_INFO, false);
		obj.put(LEVEL_CURVE, 1.6);		
		obj.put(PRINTOUT_ITEM, false);
		obj.put(PRINTOUT_BLOCK, false);
		obj.put(PRINTOUT_ENTITY, false);
		
		JSONArray list = new JSONArray();
		for (int i = 0; i < 4; i++){
		list.add("Example Skill-#");
		}
		obj.put(SKILL_LIST, list);
		try {
			 
			FileWriter file = new FileWriter(configFile);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		System.out.print(obj); 

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
}
