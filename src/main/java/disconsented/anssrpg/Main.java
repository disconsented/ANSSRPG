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

import disconsented.anssrpg.commands.GUIRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import disconsented.anssrpg.commands.ANSSRPG;
import disconsented.anssrpg.commands.Perks;
import disconsented.anssrpg.commands.Skill;
import disconsented.anssrpg.common.Logging;
import disconsented.anssrpg.common.Reference;
import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.data.ToolRegistry;
import disconsented.anssrpg.event.FMLBUS;
import disconsented.anssrpg.event.ForgeBUS;
import disconsented.anssrpg.handler.SkillHandler;
import disconsented.anssrpg.network.Manager;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.player.PlayerFile;
import disconsented.anssrpg.skill.objects.BlockSkill;

import java.util.Map;

@Mod(modid = Reference.ID, name = Reference.NAME, version = Reference.VERSION, acceptableRemoteVersions = "*")
public class Main {
    @Mod.Instance(value = Reference.ID)
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.COMMONPROXY)
    public static CommonProxy proxy;//DONT TOUCH THIS

    public static SimpleNetworkWrapper snw;

    private final Settings settings = Settings.getInstance();


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) throws Exception {
        this.settings.load(event);
        if (MinecraftServer.getServer() != null && MinecraftServer.getServer().isDedicatedServer()) {
            Settings.isServer = true;
        } else {
            Settings.isServer = false;
            ClientCommandHandler.instance.registerCommand(new GUIRegistry());
        }
        
    }

    @Mod.EventHandler // used in 1.6.2
    //@Init       // used in 1.5.2
    public void load(FMLInitializationEvent event) {
    	Manager.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, Main.proxy);
        Main.proxy.registerRenderers();
        MinecraftForge.EVENT_BUS.register(new ForgeBUS());
        FMLCommonHandler.instance().bus().register(new FMLBUS());
        ToolRegistry.init();
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new Perks());
        event.registerServerCommand(new ANSSRPG());
        event.registerServerCommand(new Skill());
    }

    /**
     * Should allow single player saving as well as server shutdown saving
     *
     * @param event
     */
    @Mod.EventHandler
    public void onServerStoppingEvent(FMLServerStoppingEvent event) {
        for (Map.Entry<String, PlayerData> entry : PlayerStore.getInstance().getAllData().entrySet()) {
            PlayerData player = entry.getValue();
            PlayerFile.writePlayer(player);
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) throws Exception {
        if (Settings.isExternalConfig()){
            JsonConfigHandler.loadPerkAndSkill();
        } else {
            JsonConfigHandler.loadInternalConfig();            
        }
        
        if (Settings.getDebug()) {
            Logging.debug("ANSSRPG has the following perks registered");
            Logging.debug(PerkStore.getInstance().getPerks());
        }

        BlockSkill temp = new BlockSkill();
        for (int i = 0; i < 100; i++){
            double xp = SkillHandler.calculateExpForLevel(temp, i);
            long level = SkillHandler.calculateLevelForExp(temp, xp);
            Logging.debug("Int: "+i+"\n"
                    +"xp: "+xp+"\n"
                    +"Level: "+level);
        }
    }
}