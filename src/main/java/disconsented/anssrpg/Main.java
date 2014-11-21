package disconsented.anssrpg;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
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
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import disconsented.anssrpg.commands.ANSSRPG;
import disconsented.anssrpg.commands.DebugCommand;
import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.data.DataSave;
import disconsented.anssrpg.network.PerkInfo;
import disconsented.anssrpg.network.PerkInfoHandler;
import disconsented.anssrpg.network.Request;
import disconsented.anssrpg.network.RequestHandler;
import disconsented.anssrpg.network.Responce;
import disconsented.anssrpg.network.ResponceHandler;
import disconsented.anssrpg.perk.PerkStore;
import disconsented.anssrpg.skill.BlockBreaking;
import disconsented.anssrpg.skill.EntityDamage;
import disconsented.anssrpg.skill.ItemCrafting;
import disconsented.anssrpg.skill.SkillHandler;

@Mod(modid="ANSSRPG", name="A Not So Simple RPG", version="TC3")
//@NetworkMod(clientSideRequired=true) // not used in 1.7
public class Main {
        // The instance of your mod that Forge uses.
        @Instance(value = "ANSSRPG")
        public static Main instance; //DONT DELETE THIs

        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="disconsented.anssrpg.client.ClientProxy", serverSide="disconsented.anssrpg.CommonProxy")
        public static CommonProxy proxy;//DONT TOUCH THIS
        
        public static SimpleNetworkWrapper snw;
        
        private Settings settings = Settings.getInstance();
        
       
		@EventHandler // used in 1.6.2
        //@PreInit    // used in 1.5.2
        public void preInit(FMLPreInitializationEvent event) {
			Configuration config = new Configuration(event.getSuggestedConfigurationFile());
			config.load();
			settings.setLevelCurve(config.get(config.CATEGORY_GENERAL, "Level Curve", 1.3).getDouble());
			settings.setDebug(config.get(config.CATEGORY_GENERAL, "debug", false).getBoolean(false));
			config.save();
			
			snw = NetworkRegistry.INSTANCE.newSimpleChannel("ANSSRPG");
			snw.registerMessage(ResponceHandler.class, Responce.class, 0, Side.SERVER); 
			snw.registerMessage(PerkInfoHandler.class, PerkInfo.class, 1, Side.CLIENT); 
			snw.registerMessage(RequestHandler.class, Request.class, 2, Side.SERVER); 
        }
       
        @EventHandler // used in 1.6.2
        //@Init       // used in 1.5.2
        public void load(FMLInitializationEvent event) {
        		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
                proxy.registerRenderers();
                MinecraftForge.EVENT_BUS.register(new BlockBreaking());     
                MinecraftForge.EVENT_BUS.register(new EntityDamage());
                MinecraftForge.EVENT_BUS.register(new ItemCrafting());
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
        	JsonConfigHandler.loadPerkAndSkill(); //loaded in here so that other mods have their stuff loaded
        	if (Settings.getDebug()){
	        	System.out.println("ANSSRPG has the following skills registered:");
	        	for	(int i = 0; i < SkillHandler.getSkillList().size(); i++){
	        		System.out.println(SkillHandler.getSkillName(i));
	        	}
	        	System.out.println("ANSSRPG has the following perks registered");
	        	System.out.println(PerkStore.getInstance().getPerks());
	        	System.out.println();
        	}
        }
}