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
/**
 *
 */
package disconsented.anssrpg.server.data;

import disconsented.anssrpg.server.player.PlayerData;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayerMP;

/**
 * @author Disconsented
 *         Stores player's into a hashmap with the Key being their UUID
 */
public class PlayerStore {
    private static final HashMap<String, PlayerData> data = new HashMap<String, PlayerData>();

    private static PlayerStore instance;

    protected PlayerStore() {/* Exists only to defeat instantiation.*/}

    public static void addPlayer(PlayerData player) {
        PlayerStore.data.put(player.getPlayerID(), player);
    }

    public static HashMap<String, PlayerData> getAllData() {
        return PlayerStore.data;
    }

    public static PlayerStore getInstance() {
        if (PlayerStore.instance == null) {
            PlayerStore.instance = new PlayerStore();
        }
        return PlayerStore.instance;
    }

    public static PlayerData getPlayer(String playerID) {
        return PlayerStore.data.get(playerID);
    }

    public static PlayerData getPlayer(EntityPlayerMP player) {
        return PlayerStore.data.get(player.getUniqueID().toString());
    }

}
