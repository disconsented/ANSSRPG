/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package disconsented.anssrpg;

import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraftforge.client.ClientCommandHandler;
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
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import disconsented.anssrpg.commands.ANSSRPG;
import disconsented.anssrpg.commands.ConfigGUI;
import disconsented.anssrpg.commands.Perks;
import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.data.DataSave;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.handler.SkillHandler;
import disconsented.anssrpg.network.PerkInfo;
import disconsented.anssrpg.network.PerkInfoHandler;
import disconsented.anssrpg.network.Request;
import disconsented.anssrpg.network.RequestHandler;
import disconsented.anssrpg.network.Responce;
import disconsented.anssrpg.network.ResponceHandler;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.player.PlayerFile;
import disconsented.anssrpg.skill.BlockBreaking;
import disconsented.anssrpg.skill.EntityDamage;
import disconsented.anssrpg.skill.ItemCrafting;

@Mod(modid="ANSSRPG", name="A Not So Simple RPG", version="DEV6")
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
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){
				settings.isServer = true;
			}else{
				settings.isServer = false;
			}
			
			Configuration config = new Configuration(event.getSuggestedConfigurationFile());
			config.load();
			settings.setLevelCurve(config.get(config.CATEGORY_GENERAL, "Level Curve", 1.3).getDouble());
			settings.setDebug(config.get(config.CATEGORY_GENERAL, "debug", false).getBoolean(false));
			settings.setPointsMode(config.get(config.CATEGORY_GENERAL, "Points Mode", 1,"0 = disabled, 1 = based on assrpg xp, 2 = convert vanilla levels to points").getInt());
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
          //event.registerServerCommand(new ANSSRPG());
        	event.registerServerCommand(new Perks());
        	event.registerServerCommand(new disconsented.anssrpg.commands.Skill());
        	ClientCommandHandler.instance.registerCommand(new ConfigGUI());
        }
        
        /**
    	 * Should allow single player saving as well as server shutdown saving
    	 * @param event
    	 */
    	@EventHandler
    	public void onServerStoppingEvent (FMLServerStoppingEvent event){
    		for(Entry<String, PlayerData> entry : PlayerStore.getInstance().getAllData().entrySet()){
    			PlayerData player = entry.getValue();
    			PlayerFile.writePlayer(player);
    		}
    	}
        @EventHandler // used in 1.6.2
        //@PostInit   // used in 1.5.2
        public void postInit(FMLPostInitializationEvent event) throws Exception {
        	JsonConfigHandler.loadPerkAndSkill(); //loaded in here so that other mods have their stuff loaded
        	if (Settings.getDebug()){
	        	System.out.println("ANSSRPG has the following perks registered");
	        	System.out.println(PerkStore.getInstance().getPerks());
	        	System.out.println();
        	}  
//        	disconsented.anssrpg.gui.Config.main();
        }
}