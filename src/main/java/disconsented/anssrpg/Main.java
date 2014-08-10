package disconsented.anssrpg;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import disconsented.anssrpg.commands.ANSSRPG;
import disconsented.anssrpg.commands.DebugCommand;
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.data.DataSave;
import disconsented.anssrpg.data.RegisterWriter;
import disconsented.anssrpg.gui.TestEvent;
import disconsented.anssrpg.perk.PerkStore;
import disconsented.anssrpg.skill.BlockBreaking;
import disconsented.anssrpg.skill.EntityDamage;
import disconsented.anssrpg.skill.ItemCrafting;
import disconsented.anssrpg.skill.SkillHandler;

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
			JsonConfigHandler.loadConfigs();
        }
		
		@EventHandler
	    public void init(FMLInitializationEvent event) {
	        NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
	    }
       
        @EventHandler // used in 1.6.2
        //@Init       // used in 1.5.2
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
                MinecraftForge.EVENT_BUS.register(new BlockBreaking());     
                MinecraftForge.EVENT_BUS.register(new EntityDamage());
                MinecraftForge.EVENT_BUS.register(new ItemCrafting());
                MinecraftForge.EVENT_BUS.register(new TestEvent());
                FMLCommonHandler.instance().bus().register(new TestEvent());
                FMLCommonHandler.instance().bus().register(new ItemCrafting());
                FMLCommonHandler.instance().bus().register(new DataSave());
                }
        @EventHandler
        public void serverLoad(FMLServerStartingEvent event)
        {
          event.registerServerCommand(new DebugCommand());
          event.registerServerCommand(new ANSSRPG());
        }
       
        @EventHandler // used in 1.6.2
        //@PostInit   // used in 1.5.2
        public void postInit(FMLPostInitializationEvent event) {
        	 //event.registerServerCommand(new DebugCommand());
        	JsonConfigHandler.loadPerkAndSkill();
        	if (Settings.getDebug()){
	        	System.out.println("ANSSRPG has the following skills registered:");
	        	for	(int i = 0; i < SkillHandler.getSkillList().size(); i++){
	        		System.out.println(SkillHandler.getSkillName(i));
	        	}
	        	System.out.println("ANSSRPG has the following perks registered");
	        	System.out.println(PerkStore.getRegisteredPerks());
	        	System.out.println();
        	}
        	if (Settings.getPrintItem()){
        		System.out.println("item registry is being written to disk");
        		RegisterWriter.Write("item");
        	}
        	if (Settings.getPrintBlock()){
        		System.out.println("block registry is being written to disk");
        		RegisterWriter.Write("block");
        	}
        	if (Settings.getPrintEntity()){
        		System.out.println("entity registry is being written to disk");
        		RegisterWriter.Write("entity");
        	}
        }
}