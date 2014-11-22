package disconsented.anssrpg.config;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import disconsented.anssrpg.perk.ItemPerk;
/**
 * @author Disconsented, Abelistah
 * Json Config's
 */
public class JsonConfigHandler {
	static File skillFile = new File("config/ANSSRPG", "skill.cfg");
	static File perkFile = new File("config/ANSSRPG","perk.cfg");
	static File configFileLocation = new File("config/ANSSRPG");

	private static ObjectStore objectStore;	
	
	public static void loadPerkAndSkill(){
		createPerkConfig();
		loadPerkConfig();
	}
	public static void createPerkConfig(){
		objectStore = new ObjectStore();
        ArrayList<ItemPerk> items = new ArrayList<>();
       items.add(new ItemPerk());
       items.add(new ItemPerk());
       objectStore.setItemPerks(items);
        try {
         configFileLocation.mkdirs();
                Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
                Writer osWriter = new OutputStreamWriter(new FileOutputStream(perkFile));
                gson.toJson(objectStore, osWriter);
                osWriter.close();
          
        } catch (Exception e) {  
                System.err.println("Exception when creating perk config");
                System.err.println(e.getLocalizedMessage());
        }  
	}
	private static void loadPerkConfig(){
	       try {
	
	               Gson gson = new Gson();
	               Type objectStoreType = new TypeToken<ObjectStore>(){}.getType();
	               Reader isReader = new InputStreamReader(new FileInputStream(perkFile));
	               objectStore = gson.fromJson(isReader, objectStoreType);
	               isReader.close();
	
	       }
	       catch (IOException iox) {
	               iox.printStackTrace();
	       }
	}
}
