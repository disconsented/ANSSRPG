package com.disconsented.jrpg;

import com.google.gson.Gson;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.DimensionManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.UUID;

/**
 * JRPG representation of player information and all of the needed representations
 */
public class Player {
    private static HashMap<UUID, Player> players = new HashMap<>();
    private HashMap<String, Trait> traitList = new HashMap<>();
    private HashMap<String, Long> skills = new HashMap<>();
    private transient EntityPlayer player;

    public Player(EntityPlayer player) {
        this.player = player;
    }

    public Player() {
    }

    /**
     * Loads a players data from disk, creating a new object if it is otherwise needed
     *
     * @param player
     */
    public static void load(EntityPlayer player) {
        File location = new File(DimensionManager.getCurrentSaveRootDirectory() + "\\JRPG", player.getPersistentID().toString());
        if (location.exists()) {
            try {
                FileReader reader = new FileReader(location);
                Gson gson = new Gson();
                Player playerData = gson.fromJson(reader, Player.class);
                players.put(player.getPersistentID(), playerData);
                playerData.player = player;
                JRPG.log.info("Loaded %s from disk", playerData.getUUID());
            } catch (Exception e) {
                JRPG.log.error(e);
            }
        } else {
            players.put(player.getPersistentID(), new Player(player));
        }
    }

    /**
     * Increases a players experience for a given skill
     *
     * @param name   Skill name
     * @param amount The amount of exp to increase it by
     */
    public void awardExperience(String name, long amount, Skill skill) {
        Long exp = skills.getOrDefault(name, (long) 0);
        skills.put(name, exp + amount);
        double levelOld, levelNew;
        levelOld = skill.getLevelForExperience(exp);
        levelNew = skill.getLevelForExperience(exp + amount);
        if (levelNew > levelOld) {
            sendMessage(String.format("You've been awarded %d exp for %s, which has increased from [%s]=>[%s]", amount, name, levelOld, levelNew));
        } else {
            sendMessage(String.format("You've been awarded %d exp for %s", amount, name));
        }
    }

    /**
     * Helper function to send a message to a player
     *
     * @param message
     */
    public void sendMessage(String message) {
        player.sendMessage(new TextComponentString(message));
    }

    public void sendFail() {
        sendMessage("I can't let you do that dave.");
    }

    /**
     * @return the list of traits gained by a player
     */
    public HashMap<String, Trait> getTraits() {
        return traitList;
    }

    /**
     * Retrieves a loaded Player object
     *
     * @param uuid players persistent uuid
     * @return player object if found
     */
    public static Player getPlayer(UUID uuid) {
        return players.get(uuid);
    }

    /**
     * Returns if the player has a trait
     *
     * @param t
     * @return if the player has the trait t
     */
    public boolean hasTrait(Trait t) {
        return traitList.get(t.getName()) != null;
    }

    /**
     * Save the player to disk
     */
    public void save() {
        Player player = players.remove(this.player.getPersistentID());
        try {
            File location = new File(DimensionManager.getCurrentSaveRootDirectory() + "\\JRPG", getUUID().toString());
            location.mkdirs();
            Gson serialiser = new Gson();
            FileWriter writer = new FileWriter(location);
            writer.write(serialiser.toJson(player));
            writer.close();
            JRPG.log.info("Saved %s to disk", player.getUUID());
        } catch (Exception e) {
            JRPG.log.error(e);
        }
    }

    /**
     * Convenience function to return the players UUID
     *
     * @return
     */
    public UUID getUUID() {
        return player.getPersistentID();
    }
}
