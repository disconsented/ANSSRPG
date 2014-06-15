package disconsented.anssrpg;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
import disconsented.anssrpg.config.JsonHandler;
import disconsented.anssrpg.data.PerkObject;
import disconsented.anssrpg.data.RegisterWriter;
//import cpw.mods.fml.common.network.NetworkMod; // not used in 1.7
import disconsented.anssrpg.events.constructingEntity;

@Mod(modid="ANSSRPG", name="A Not So Simple RPG", version="0.0.1")
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
			JsonHandler.loadConfig();
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
        	System.exit(0);
        	if (JsonHandler.getPrintItem()){
        		System.out.println("item");
        		RegisterWriter.Write("item");
        	}
        	if (JsonHandler.getPrintBlock()){
        		System.out.println("block");
        		RegisterWriter.Write("block");
        	}
        	if (JsonHandler.getPrintEntity()){
        		System.out.println("entity");
        		RegisterWriter.Write("entity");
        	}
        }
}