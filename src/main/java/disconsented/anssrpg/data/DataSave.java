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
package disconsented.anssrpg.data;
/**
 * Handles non-shut down saving and loading of player data
 * Holds the player hashmap
 */

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import disconsented.anssrpg.common.Logging;
import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.player.PlayerFile;

/**
 * @author James
 *         onEntityJoinWorld - Probably not needed
 *         onLivingDeath - Saves data
 */
public class DataSave {
    public static void addPlayer(PlayerData player, String PlayerID) {
        PlayerStore.getInstance();
        PlayerStore.addPlayer(player);
    }

    public static void createPlayer(String playerID) {
        ArrayList tempAL = new ArrayList();
        HashMap tempHM = new HashMap();
        PlayerData temp = new PlayerData(tempAL, tempHM, playerID, 0);
        addPlayer(temp, playerID);
        tempAL.clear();
        tempHM.clear();
    }

    public static PlayerData getPlayerData(String playerID) {
        PlayerStore.getInstance();
        PlayerData player = PlayerStore.getPlayer(playerID);
        if (player != null) {
            return player;
        } else {
            createPlayer(playerID);
            PlayerStore.getInstance();
            return PlayerStore.getPlayer(playerID);
        }
    }

    /**
     * Load player data
     *
     * @param event
     */
    public void onPlayerLoggedInEvent(PlayerLoggedInEvent event) {
        if (Settings.getDebug()) {
            Logging.debug("Player " + event.player.getDisplayName() + " with UUID:" + event.player.getPersistentID().toString() + "has logged in");
            Logging.debug("Loading player data");
        }
        PlayerFile.loadPlayer(event.player.getPersistentID().toString());
        PerkStore.getInstance();
        PerkStore.getPerks();
    }

    /**
     * Saves player data
     *
     * @param event
     */
    public void onPlayerLoggedOutEvent(PlayerLoggedOutEvent event) {
        if (Settings.getDebug()) {
            Logging.debug("Player " + event.player.getDisplayName() + " with UUID:" + event.player.getPersistentID().toString() + "has logged out");
            Logging.debug("Saving player data");
        }
        PlayerFile.writePlayer(PlayerStore.getInstance().getPlayer(event.player.getPersistentID().toString()));
        PlayerStore.getInstance();
        PlayerStore.getAllData().remove(event.player.getPersistentID().toString());
    }

    /**
     * Saves player data (crash damage mitigation)
     *
     * @param event
     */
    public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
        if (Settings.getDebug()) {
            Logging.debug("Player " + event.player.getDisplayName() + " with UUID:" + event.player.getPersistentID().toString() + "has respawned");
            Logging.debug("Saving player data");
        }
        PlayerFile.writePlayer(PlayerStore.getInstance().getPlayer(event.player.getPersistentID().toString()));
    }
}
