package disconsented.anssrpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import net.minecraftforge.common.MinecraftForge;
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
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.data.PlayerData;
import disconsented.anssrpg.data.PlayerFile;
import disconsented.anssrpg.data.RegisterWriter;
//import cpw.mods.fml.common.network.NetworkMod; // not used in 1.7
import disconsented.anssrpg.events.constructingEntity;

@Mod(modid="ANSSRPG", name="A Not So Simple RPG", version="TC2")
//@NetworkMod(clientSideRequired=true) // not used in 1.7
public class Main {
        // The instance of your mod that Forge uses.
        @Instance(value = "ANSSRPG")
        public static Main instance; //DONT DELETE THIs
       
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="disconsented.anssrpg.client.ClientProxy", serverSide="disconsented.anssrpg.CommonProxy")
        public static CommonProxy proxy;//DONT TOUCH THIS
        
       
		@EventHandler // used in 1.6.2
        //@PreInit    // used in 1.5.2
        public void preInit(FMLPreInitializationEvent event) {
			//ConfigurationHandler.loadAndSave();
			JsonConfigHandler.loadConfig();
			JsonConfigHandler.loadSkillConfig();
        }
       
        @EventHandler // used in 1.6.2
        //@Init       // used in 1.5.2
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
                MinecraftForge.EVENT_BUS.register(new constructingEntity());
                FMLCommonHandler.instance().bus().register(new constructingEntity());
                
                
                //MiningSorting.loadRequirements();

        }
       
        @EventHandler // used in 1.6.2
        //@PostInit   // used in 1.5.2
        public void postInit(FMLPostInitializationEvent event) {
        	if (JsonConfigHandler.getPrintItem()){
        		System.out.println("item");
        		RegisterWriter.Write("item");
        	}
        	if (JsonConfigHandler.getPrintBlock()){
        		System.out.println("block");
        		RegisterWriter.Write("block");
        	}
        	if (JsonConfigHandler.getPrintEntity()){
        		System.out.println("entity");
        		RegisterWriter.Write("entity");
        	}
        }
}