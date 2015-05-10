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

import java.util.Map.Entry;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
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
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import disconsented.anssrpg.commands.ANSSRPG;
import disconsented.anssrpg.commands.ConfigGUI;
import disconsented.anssrpg.commands.Perks;
import disconsented.anssrpg.common.Logging;
import disconsented.anssrpg.common.Reference;
import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.config.PerkContainer;
import disconsented.anssrpg.config.SkillContainer;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.data.ToolRegistry;
import disconsented.anssrpg.event.FMLBUS;
import disconsented.anssrpg.event.ForgeBUS;
import disconsented.anssrpg.handler.SkillHandler;
import disconsented.anssrpg.network.Manager;
import disconsented.anssrpg.network.PerkInfo;
import disconsented.anssrpg.network.PerkInfoHandler;
import disconsented.anssrpg.network.Request;
import disconsented.anssrpg.network.RequestHandler;
import disconsented.anssrpg.network.Responce;
import disconsented.anssrpg.network.ResponceHandler;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.player.PlayerFile;
import disconsented.anssrpg.skill.objects.BlockSkill;

@Mod(modid = Reference.ID, name = Reference.NAME, version = Reference.VERSION, acceptableRemoteVersions = "*")
public class Main {
    @Instance(value = Reference.ID)
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.COMMONPROXY)
    public static CommonProxy proxy;//DONT TOUCH THIS

    public static SimpleNetworkWrapper snw;

    private Settings settings = Settings.getInstance();


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) throws Exception {
        settings.load(event);
        if (MinecraftServer.getServer() != null && MinecraftServer.getServer().isDedicatedServer()) {
            settings.isServer = true;
        } else {
            settings.isServer = false;
            ClientCommandHandler.instance.registerCommand(new ConfigGUI());
        }
        
    }

    @EventHandler // used in 1.6.2
    //@Init       // used in 1.5.2
    public void load(FMLInitializationEvent event) {
    	Manager.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
        proxy.registerRenderers();
        MinecraftForge.EVENT_BUS.register(new ForgeBUS());
        FMLCommonHandler.instance().bus().register(new FMLBUS());
        ToolRegistry.init();
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        //event.registerServerCommand(new ANSSRPG());
        event.registerServerCommand(new Perks());
        event.registerServerCommand(new ANSSRPG());
        event.registerServerCommand(new disconsented.anssrpg.commands.Skill());
    }

    /**
     * Should allow single player saving as well as server shutdown saving
     *
     * @param event
     */
    @EventHandler
    public void onServerStoppingEvent(FMLServerStoppingEvent event) {
        for (Entry<String, PlayerData> entry : PlayerStore.getInstance().getAllData().entrySet()) {
            PlayerData player = entry.getValue();
            PlayerFile.writePlayer(player);
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) throws Exception {
        if (settings.isExternalConfig()){
            JsonConfigHandler.loadPerkAndSkill();
        } else {
            PerkContainer perkContainer = disconsented.anssrpg.config.Default.getPerkInstance();
            SkillContainer skillContainer = disconsented.anssrpg.config.Default.getSkillInstance();
            perkContainer.touchUp();
            skillContainer.touchUp();
            JsonConfigHandler.createPerkConfig(perkContainer);
            JsonConfigHandler.createSkillConfig(skillContainer);
        }
        
        if (Settings.getDebug()) {
            Logging.debug("ANSSRPG has the following perks registered");
            Logging.debug(PerkStore.getInstance().getPerks());
        }

        BlockSkill temp = new BlockSkill();
        for (int i = 0; i < 100; i++){
            double xp = SkillHandler.calculateExpForLevel(temp, i);
            long level = SkillHandler.calulteLevelForExp(temp, xp);
            Logging.debug("Int: "+i+"\n"
                    +"xp: "+xp+"\n"
                    +"Level: "+level);
        }
    }
}