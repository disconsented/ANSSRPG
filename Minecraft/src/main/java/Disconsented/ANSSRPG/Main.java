package Disconsented.ANSSRPG;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
//import cpw.mods.fml.common.Mod.PreInit;    // used in 1.5.2
//import cpw.mods.fml.common.Mod.Init;       // used in 1.5.2
//import cpw.mods.fml.common.Mod.PostInit;   // used in 1.5.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
//import cpw.mods.fml.common.network.NetworkMod; // not used in 1.7

@Mod(modid="ANSSRPG", name="A Not So Simple RPG", version="0.0.1")
//@NetworkMod(clientSideRequired=true) // not used in 1.7
public class Main {
        // The instance of your mod that Forge uses.
        @Instance(value = "ANSSRPG")
        public static Main instance; //DONT DELETE THIs
       
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="Disconsented.ANSSRPG.client.ClientProxy", serverSide="Disconsented.ANSSRPG.CommonProxy")
        public static CommonProxy proxy;//DONT TOUCH THIS
        
        public static ArrayList<SkillInfo> skillInfo = new ArrayList<SkillInfo>(); //Has to be static
		public static double experienceCurve;
		public int numberOfSkills;
		public static boolean debugInfo = true;
       
		@EventHandler // used in 1.6.2
        //@PreInit    // used in 1.5.2
        public void preInit(FMLPreInitializationEvent event) {
			ArrayList skillNames = new ArrayList();
			ArrayList skillEntryCount = new ArrayList();
			ArrayList<Byte> type = new ArrayList<Byte>();
			ArrayList entityList = new ArrayList();
    		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    		config.load(); 
    		
    		debugInfo = config.get(config.CATEGORY_GENERAL, "Debug info", false).getBoolean(false);
    		experienceCurve = config.get(config.CATEGORY_GENERAL, "Player Experience curve", 1.3).getDouble(1.3);
    		numberOfSkills = config.get(config.CATEGORY_GENERAL, "Number of skills", 8,"You will probably need to delete your config if you lower this").getInt();
    		
    		for (int i = 0; i < numberOfSkills; i++){    			
    			skillNames.add(i,config.get("Base Skill", "Skill name "+i, "Default Name").getString());
    			skillEntryCount.add(i, config.get("Base Skill", "Number entries per skill" +i, 1).getInt());
    			type.add(i, (byte) config.get("Base Skill", "Skill Type" +i, 1, "1 = Block Breaking, 2 = Entity Damaging, 3 = Crafting").getInt());
    		}
    		
    			for (int i = 0; i < numberOfSkills; i++){ //Iteration loop for each skill
    				ArrayList exp = new ArrayList();
    				ArrayList req = new ArrayList();
    				ArrayList itemList = new ArrayList();

    				switch ((int)type.get(i)){
    				case 1: // Block breaking skills
    					for (int o = 0; o < Integer.parseInt(skillEntryCount.get(i).toString()); o++){
    						itemList.add( config.get((String) skillNames.get(i), "Block Name "+(o+1), "tile.").getString());//Name of blocks
    						req.add(config.get((String) skillNames.get(i), "Block Level Requirment"+(o+1), 1).getInt());//Level requirement
    						exp.add(config.get((String) skillNames.get(i), "Block Experience"+(o+1), 1).getInt());//Exp given
    						}
    					skillInfo.add(i,new SkillInfo(exp, req, skillNames.get(i).toString(), itemList, type.get(i)));
    						break;
    				case 2: // Entity killing skills
    					for (int o = 0; o < Integer.parseInt(skillEntryCount.get(i).toString()); o++){
    						exp.add(config.get((String) skillNames.get(i), "Entity Experience"+(o+1), 1).getInt());
    						req.add(config.get((String) skillNames.get(i), "Entity Level Requirment"+(o+1), 1).getInt());
    						itemList.add(config.get((String) skillNames.get(i), "Entity Name"+(o+1), "").getString());
    					}
    					skillInfo.add(i,new SkillInfo(exp, req, skillNames.get(i).toString(), itemList, type.get(i)));
    					break;
    				case 3: // Crafting skills
    					for (int o = 0; o < Integer.parseInt(skillEntryCount.get(i).toString()); o++){
    						exp.add(config.get((String) skillNames.get(i), "Item Experience"+(o+1), 1).getInt());
    						req.add(config.get((String) skillNames.get(i), "Item Level Requirment"+(o+1), 1).getInt());
    						itemList.add(config.get((String) skillNames.get(i), "Item Name"+(o+1), "item.").getString());
    					}
    					skillInfo.add(i,new SkillInfo(exp, req, skillNames.get(i).toString(), itemList, type.get(i)));
    					break;    				
    				}
    				

    			}    		
    		//JOptionPane.showMessageDialog(null,skillNames);
    		config.save();
        }
       
        @EventHandler // used in 1.6.2
        //@Init       // used in 1.5.2
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
                MinecraftForge.EVENT_BUS.register(new ANSSRPGEventHandler());
                FMLCommonHandler.instance().bus().register(new ANSSRPGEventHandler());
                //MiningSorting.loadRequirements();

        }
       
        @EventHandler // used in 1.6.2
        //@PostInit   // used in 1.5.2
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}