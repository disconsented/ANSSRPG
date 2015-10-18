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
package disconsented.anssrpg.server.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import disconsented.anssrpg.server.common.Logging;
import disconsented.anssrpg.server.common.Settings;
import disconsented.anssrpg.server.data.PlayerStore;

import java.io.*;

public class PlayerFile {


    public static void loadPlayer(String playerID) {
        File dataLocation = new File(Settings.getFolder(), playerID+".json");
        if (dataLocation.exists()){
            try {
                FileReader reader = new FileReader(dataLocation);
                Gson gson = new GsonBuilder().create();
                PlayerData player = gson.fromJson(reader, PlayerData.class);
                PlayerStore.addPlayer(player);
            } catch (Exception e) {
                Logging.error(e.getStackTrace());
            }
        } else {
            Logging.debug(playerID+"'s file does not exist");
        }
    }

    /**
     * Write all of the player data to a file
     */

    public static void writePlayer(PlayerData player) {
        if(player != null) {
            try {
                File dataFolder = Settings.getFolder();
                File dataLocation = new File(dataFolder, player.getPlayerID() + ".json");
                dataFolder.mkdirs();
                Gson gson = new Gson();
                String json = gson.toJson(player);
                FileWriter writer = new FileWriter(dataLocation);
                writer.write(json);
                writer.close();
            } catch (Exception e) {
                Logging.error(e.getStackTrace());
            }
        } else{
            Logging.debug("Null player object detected, please report this");
        }
    }
}
