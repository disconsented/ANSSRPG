package disconsented.anssrpg.config;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.perk.PerkStore;
import disconsented.anssrpg.perk.Requirement;
import disconsented.anssrpg.skill.SkillHandler;
/**
 * @author james
 * Json Config's
 */
public class JsonConfigHandler {
	static File skillFile = new File("config/ANSSRPG", "skill.cfg");
	static File perkFile = new File("config/ANSSRPG","perk.cfg");
	static File configFileLocation = new File("config/ANSSRPG");
	static String SKILL_LIST = "Skill List";
	static String SKILL_TYPE = "Skill Type";
	static String ENTITY_NAME = "Entity Name";
	static String ITEM_NAME = "Item Name";
	static String BLOCK_NAME = "Block Name";
	static String ENTRY_EXP = "Entry Experience";
	static String ENTRY_NAME = "Entry Name";
	static String SKILL_NAME = "Skill name";
	
	
//	static ArrayList skillNames = new ArrayList();
//	static ArrayList skillType = new ArrayList();
	
//	private static ArrayList jsonArraytoArrayList(JsonElement jsonElement){
//		ArrayList temp = new ArrayList();
//		for (int i = 0; i < jsonElement.getAsJsonArray().size(); i++){
//			temp.add(jsonElement.getAsJsonArray().get(i));
//		}
//		return temp;
//	}
	
	public static void loadPerkAndSkill(){
		//loadSkillConfig();
		//loadPerkConfig();
		createSkillConfig();
		createPerkConfig();
	}
//	private static void loadSkillConfig(){		
//		ArrayList tempEntry = new ArrayList();
//		ArrayList tempExp = new ArrayList();
//		File skillConfig = new File("config/ANSSRPG",name.replaceAll("[^A-Za-z0-9]", "")+".cfg");
//		try {			
//			JsonReader reader = new JsonReader (new FileReader(skillConfig));	
//			reader.setLenient(true);
//			reader.beginArray();
//			while (reader.hasNext()){
//				tempEntry.add(reader.nextString());
//			}
//			reader.endArray();
//			reader.beginArray();
//			while (reader.hasNext()){
//				tempExp.add(reader.nextInt());
//			}
//			reader.endArray();
//			reader.close();			
//		}
//		catch (IllegalStateException e){
//			if (Settings.getDebug()){
//				e.printStackTrace();
//			}
//		}
//		catch (Exception e){			
//			e.printStackTrace();
//			createSkillConfig(name);
//		}
//		SkillHandler.constructSkill(name,type,tempEntry,tempExp);
//	}
//	private static void createSkillConfig(){		
//		File skillConfig = new File("config/ANSSRPG",name.replaceAll("[^A-Za-z0-9]", "")+".cfg");	
//		 Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
//		 JsonPrimitive value1 = new JsonPrimitive("entry_ID_example");
//		 JsonPrimitive value2 = new JsonPrimitive(1);
//		 JsonArray example1 = new JsonArray();
//		 example1.add(value1);
//		 example1.add(value1);
//		 example1.add(value1);
//		 JsonArray example2 = new JsonArray();
//		 example2.add(value2);
//		 example2.add(value2);
//		 example2.add(value2);
//		 String json = gson.toJson(example1);  
//		 String json1 = gson.toJson(example2);
//		    
//		 try { 
//		  skillConfig.createNewFile();
//		  FileWriter writer = new FileWriter(skillConfig);  
//		  writer.write(json);
//		  writer.write(System.lineSeparator());
//		  writer.write(json1);
//		  writer.close();  		    
//		 } catch (Exception e) {  
//			 System.err.println("Exception when creating skill config");
//			 System.err.println(e.getLocalizedMessage());
//		  }  
//		}
	private static void createSkillConfig(){
		 Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
		 JsonObject obj = new JsonObject();
		 HashMap<String, DummySkill> temp = new HashMap<String, DummySkill>();
		 DummySkill instance = DummySkill.getInstance();
			temp.put("item",instance);
			temp.put("block",instance);
			temp.put("entity",instance);
		 String stringObject = gson.toJson(temp);	    
		 try { 
		  configFileLocation.mkdirs();
		  FileWriter writer = new FileWriter(perkFile);  
		  writer.write(stringObject);
		  writer.close();  
		    
		 } catch (Exception e) {  
			 System.err.println("Exception when creating perk config");
			 System.err.println(e.getLocalizedMessage());
		 }  
	}

	private static void createPerkConfig(){
		 Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
		 JsonObject obj = new JsonObject();
		 HashMap<String, DummyPerk> temp = new HashMap<String, DummyPerk>();
		 DummyPerk instance = DummyPerk.getInstance();
			temp.put("item",instance);
			temp.put("block",instance);
			temp.put("entity",instance);
		 String stringObject = gson.toJson(temp);	    
		 try { 
		  configFileLocation.mkdirs();
		  FileWriter writer = new FileWriter(perkFile);  
		  writer.write(stringObject);
		  writer.close();  
		    
		 } catch (Exception e) {  
			 System.err.println("Exception when creating perk config");
			 System.err.println(e.getLocalizedMessage());
		 }  
	}
//	private static void loadPerkConfig(){
//		Boolean go = true;
//		String name = null;
//		String type = null;
//		ArrayList tempName = new ArrayList();
//		ArrayList tempLevel = new ArrayList();
//		String unlock = null;
//		String description = null;
//		String nextName = null;
//		String nextPeek = null;
//		int pointCost = 0;
//		try{
//			JsonReader reader = new JsonReader (new FileReader(perkFile));	
//			reader.setLenient(true);
//			JsonToken BEGIN_OBJECT = reader.peek();
//			while(reader.peek() == BEGIN_OBJECT){
//				reader.beginObject();			
//
//				while (reader.hasNext()){
//					nextName = reader.nextName().toLowerCase();
//					if (nextName.equals("type")){
//						type = reader.nextString();
//					}else if (nextName.equals("name")){
//						name = reader.nextString();
//					}else if (nextName.equals("unlock")){
//						unlock = reader.nextString();
//					}else if (nextName.equals("requirementname")){
//						reader.beginArray();
//						while (reader.peek().toString().equals("STRING")){
//							tempName.add(reader.nextString());
//						}
//						reader.endArray();
//					}else if (nextName.equals("requirementlevel")){
//						reader.beginArray();
//						while (reader.peek().toString().equals("NUMBER")){
//							tempLevel.add(reader.nextInt());
//						}
//						reader.endArray();
//					}else if (nextName.equals("pointcost")){			
//						pointCost = reader.nextInt();
//					}else if (nextName.equals("description")){
//						description = reader.nextString();
//					}
//				}
//				reader.endObject();
//				if (Settings.getDebug()){
//					System.out.println("Attempting to construct the following perk");
//					System.out.println(type);
//					System.out.println(name);
//					System.out.println(unlock);
//					System.out.println(tempName);
//					System.out.println(tempLevel);
//					System.out.println(pointCost);
//					System.out.println(description);
//					System.out.println("");
//				}
//				DummyPerk temp = new DummyPerk(type, name, unlock, description,pointCost, tempName,tempLevel);
//				PerkStore.constructPerk(temp);
//				tempName.clear();
//				tempLevel.clear();
//			}
//			reader.close();			
//		}
//		catch(FileNotFoundException e){
//			createPerkConfig();
//		}
//		catch(Exception E){			
//			E.printStackTrace();
//		}
//	}
}
