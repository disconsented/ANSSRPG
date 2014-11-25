package disconsented.anssrpg.config;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.perk.EntityPerk;
import disconsented.anssrpg.perk.ItemPerk;
import disconsented.anssrpg.skill.objects.BlockSkill;
/**
 * @author Disconsented, Abelistah
 * Json Config's
 */
public class JsonConfigHandler {
	static File skillFile = new File("config/ANSSRPG", "skill.cfg");
	static File perkFile = new File("config/ANSSRPG","perk.cfg");
	static File configFileLocation = new File("config/ANSSRPG");

	private static PerkStore perkStore;	
	public static SkillStore skillStore;
	
	public static void loadPerkAndSkill(){
		createPerkConfig();
		loadPerkConfig();
		createSkillConfig();
		loadSkillConfig();
	}
	public static void createPerkConfig(){
		perkStore = new PerkStore();
		perkStore.addItemPerk(new ItemPerk());
		perkStore.addItemPerk(new ItemPerk());
		perkStore.addBlockPerk(new BlockPerk());
		perkStore.addBlockPerk(new BlockPerk());
		perkStore.addEntityPerk(new EntityPerk());
		perkStore.addEntityPerk(new EntityPerk());

        try {
         configFileLocation.mkdirs();
                Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
                Writer osWriter = new OutputStreamWriter(new FileOutputStream(perkFile));
                gson.toJson(perkStore, osWriter);
                osWriter.close();

        } catch (Exception e) {
                System.err.println("Exception when creating perk config");
                System.err.println(e.getLocalizedMessage());
        }
	}
	private static void loadPerkConfig(){
	       try {	
	               Gson gson = new Gson();
	               Type objectStoreType = new TypeToken<PerkStore>(){}.getType();
	               Reader isReader = new InputStreamReader(new FileInputStream(perkFile));
	               perkStore = gson.fromJson(isReader, objectStoreType);
	               isReader.close();	
	       }
	       catch (IOException iox) {
	               iox.printStackTrace();
	       }
	}
	public static void createSkillConfig(){
		skillStore = new SkillStore();
        ArrayList<BlockSkill> blocks = new ArrayList<BlockSkill>();
        blocks.add(new BlockSkill());
        blocks.add(new BlockSkill());
       skillStore.setBlock(blocks);
        try {
         configFileLocation.mkdirs();
                Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
                Writer osWriter = new OutputStreamWriter(new FileOutputStream(skillFile));
                gson.toJson(skillStore, osWriter);
                osWriter.close();
          
        } catch (Exception e) {  
                System.err.println("Exception when creating skill config");
                System.err.println(e.getLocalizedMessage());
        }  
	}
	private static void loadSkillConfig(){
	       try {	
	               Gson gson = new Gson();
	               Type objectStoreType = new TypeToken<PerkStore>(){}.getType();
	               Reader isReader = new InputStreamReader(new FileInputStream(skillFile));
	               skillStore = gson.fromJson(isReader, objectStoreType);
	               isReader.close();	
	       }
	       catch (IOException iox) {
	               iox.printStackTrace();
	       }
	}
}
