package disconsented.anssrpg;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import disconsented.anssrpg.commands.ANSSRPG;
import disconsented.anssrpg.commands.DebugCommand;
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.data.DataSave;
import disconsented.anssrpg.data.RegisterWriter;
import disconsented.anssrpg.perk.Perk;
import disconsented.anssrpg.perk.PerkStore;
import disconsented.anssrpg.skill.BlockBreaking;
import disconsented.anssrpg.skill.SkillHandler;

@Mod(modid="ANSSRPG", name="A Not So Simple RPG", version="TC2")
public class Main {
        @Instance(value = "ANSSRPG")
        public static Main instance;

        @SidedProxy(clientSide="disconsented.anssrpg.client.ClientProxy", serverSide="disconsented.anssrpg.CommonProxy")
        public static CommonProxy proxy;//DONT TOUCH THIS
        
       
		@EventHandler
        public void preInit(FMLPreInitializationEvent event) {
			//ConfigurationHandler.loadAndSave();
			JsonConfigHandler.loadConfigs();
        }
       
        @EventHandler
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
                MinecraftForge.EVENT_BUS.register(new BlockBreaking());
                MinecraftForge.EVENT_BUS.register(new DataSave());
                }
        @EventHandler
        public void serverLoad(FMLServerStartingEvent event)
        {
          event.registerServerCommand(new DebugCommand());
          event.registerServerCommand(new ANSSRPG());
        }
       
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
        	 //event.registerServerCommand(new DebugCommand());
        	System.out.println(Block.blockRegistry.getObject("coal_ore"));
        	System.out.println(Block.blockRegistry.getObject("iron_ore"));
        	System.out.println(Block.blockRegistry.getObject("redstone_ore"));
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
        		System.out.println("entity");
        		RegisterWriter.Write("entity registry is being written to disk");
        	}
        }
}