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

import disconsented.anssrpg.compat.tconstruct.test;
import disconsented.anssrpg.core.Support;
import disconsented.anssrpg.core.client.ClientTick;
import disconsented.anssrpg.core.client.commands.ANSSRPG;
import disconsented.anssrpg.core.client.commands.GUIRegistry;
import disconsented.anssrpg.core.server.Global;
import disconsented.anssrpg.core.server.commands.Perks;
import disconsented.anssrpg.core.server.commands.Skill;
import disconsented.anssrpg.core.server.common.Logging;
import disconsented.anssrpg.core.server.common.Reference;
import disconsented.anssrpg.core.server.common.Settings;
import disconsented.anssrpg.core.server.config.JsonConfigHandler;
import disconsented.anssrpg.core.server.data.PerkStore;
import disconsented.anssrpg.core.server.data.PlayerStore;
import disconsented.anssrpg.core.server.data.ToolRegistry;
import disconsented.anssrpg.core.server.event.FMLBUS;
import disconsented.anssrpg.core.server.event.ForgeBUS;
import disconsented.anssrpg.core.server.network.Manager;
import disconsented.anssrpg.core.server.player.PlayerData;
import disconsented.anssrpg.core.server.player.PlayerFile;
import disconsented.anssrpg.core.server.skill.objects.BlockSkill;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

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
        if (Global.getMinecraftServer() != null && Global.getMinecraftServer().isDedicatedServer()) {
            Settings.isServer = true;
        } else {
            Settings.isServer = false;
            ClientCommandHandler.instance.registerCommand(new GUIRegistry());
            ClientCommandHandler.instance.registerCommand(new ANSSRPG());
        }

    }

    @Mod.EventHandler // used in 1.6.2
    //@Init       // used in 1.5.2
    public void load(FMLInitializationEvent event) {
        Manager.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, Main.proxy);
        Main.proxy.registerRenderers();
        MinecraftForge.EVENT_BUS.register(new ForgeBUS());
        MinecraftForge.EVENT_BUS.register(new test());
        FMLCommonHandler.instance().bus().register(new ClientTick());
        FMLCommonHandler.instance().bus().register(new FMLBUS());
        ToolRegistry.init();

        Support.loadMods();
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new Perks());
        event.registerServerCommand(new Skill());
    }

    /**
     * Should allow single player saving as well as server shutdown saving.     *
     *
     * @param event The Server stopping event.
     */
    @Mod.EventHandler
    public void onServerStoppingEvent(FMLServerStoppingEvent event) {
        for (Map.Entry<String, PlayerData> entry : PlayerStore.getAllData().entrySet()) {
            PlayerData player = entry.getValue();
            PlayerFile.writePlayer(player);
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) throws Exception {
        if (Settings.isExternalConfig()) {
            JsonConfigHandler.loadPerkAndSkill();
            Support.loadExternalConfig();
        } else {
            JsonConfigHandler.loadInternalConfig();
            Support.loadInternalConfig();
        }

        if (Settings.getDebug()) {
            Logging.debug("ANSSRPG has the following perks registered");
            Logging.debug(PerkStore.getInstance().getPerks());
        }
    }
}