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
package disconsented.anssrpg.server.handler;

import disconsented.anssrpg.server.data.DataSave;
import disconsented.anssrpg.server.data.PerkStore;
import disconsented.anssrpg.server.data.PlayerStore;
import disconsented.anssrpg.server.data.SkillStore;
import disconsented.anssrpg.server.perk.ActivePerk;
import disconsented.anssrpg.server.perk.Perk;
import disconsented.anssrpg.server.perk.Requirement;
import disconsented.anssrpg.server.perk.Requirement.Action;
import disconsented.anssrpg.server.perk.Slug;
import disconsented.anssrpg.server.player.PlayerData;
import disconsented.anssrpg.server.skill.objects.Skill;
import disconsented.anssrpg.server.skill.objects.ToolSkill;
import disconsented.anssrpg.server.task.TaskTrackPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author Disconsented
 *         Handles the data that is stored on players (experience for skills and perks).
 *         //TODO: Remove redundant references and change all methods to never directly interfact with minecraft storage.
 */

public final class PlayerHandler {

    public static String addPerk(String perkSlug, EntityPlayerMP player) {
        return PlayerHandler.addPerk(perkSlug, PlayerHandler.getPlayer(player.getUniqueID()));
    }

    /**
     * Checks the requirements for a perk and adds it to the player if they meet them.
     *
     * @param perkSlug Slug of the perk to add to the player.
     * @param player   The PlayerData object.
     * @return The result.
     */
    public static String addPerk(String perkSlug, PlayerData player) {
        Perk perk = PerkStore.getPerk(perkSlug);
        if (perk == null) {
            return "No perk with that slug was found";
        }
        //Cached here for readability.
        int level = 0;
        int reqLevel = 0;
        ArrayList<Requirement> requirements = perk.getRequirements();
        for (Requirement req : requirements) {
            if (req.action == Action.LEVEL_EQUALS || req.action == Action.LEVEL_GREATER || req.action == Action.LEVEL_LESS) {
                level = player.getSkillLevel(SkillHandler.getSkill(req.name));
                reqLevel = Integer.parseInt(req.extraData);
            }
            switch (req.action) {
                case DONT:
                    if (player.getPerkList().contains(req.getNameAsSlug())) {
                        return "Unable to grant perk, " + req.name + " was found on the player";
                    }
                    break;
                case HAVE:
                    if (!player.getPerkList().contains(req.getNameAsSlug())) {
                        return "Unable to grant perk, " + req.name + " could not be found on the player";
                    }
                    break;
                case LEVEL_EQUALS:
                    if (!(level == reqLevel)) {
                        return "Unable to grant perk, " + req.name + "'s level did not equal " + req.extraData;
                    }
                    break;
                case LEVEL_GREATER:
                    if (level < reqLevel) {
                        return "Unable to grant perk, " + req.name + "'s level was less than " + req.extraData;
                    }
                    break;
                case LEVEL_LESS:
                    if (level > reqLevel) {
                        return "Unable to grant perk, " + req.name + "'s level did not equal " + req.extraData;
                    }
                    break;
            }
        }
        if (player.getPerkList().contains(perkSlug)) {
            return "Already have " + perkSlug;
        } else {
            player.getPerkList().add(perk.getSlug());
            return "All requirements meet; Granting " + perkSlug;
        }
    }

    /**
     * Safe method to add experience to a player.
     *
     * @param playerEntity The player object that associated data is retrieved for.
     * @param skill        The Skill that will have its experience added to.
     * @param exp          The Experience that will be added.
     */
    //TODO: Refactor this to use PlayerData and create another method to encapsulate it. Maybe split it up into other private methods.
    public static void awardXP(EntityPlayer playerEntity, Skill skill, int exp) {
        PlayerData player = PlayerHandler.getPlayer(playerEntity.getUniqueID().toString());
        Integer cacheExp = player.getSkillExp().get(skill.name);
        long levelOld;
        long levelNew = -1;
        if (cacheExp != null) {
            levelOld = SkillHandler.calculateLevelForExp(skill, cacheExp);
        } else {
            levelOld = 0;
        }

        if (cacheExp != null) {
            player.getSkillExp().put(skill.name, exp + cacheExp);
            levelNew = SkillHandler.calculateLevelForExp(skill, exp + cacheExp);
        } else {
            player.getSkillExp().put(skill.name, exp);
            levelNew = SkillHandler.calculateLevelForExp(skill, exp);
        }
        playerEntity.addChatComponentMessage(new TextComponentString("You have been awared with " + exp + " exp"));
        /* Check for level up
         * If leveled up send info
         */

        if (levelNew > levelOld) {
            playerEntity.addChatComponentMessage(new TextComponentString("Your skill " + skill.name + " has leveled up to " + levelNew));
        }
    }

    /**
     * Awards experience to a player where it the tool needs to be taken into account.
     *
     * @param playerEntity The player to have the experience added to.
     * @param skill        The skill that will have the experience added to.
     * @param exp          The experience that needs to be added.
     */
    public static void awardToolXP(EntityPlayer playerEntity, ToolSkill skill, int exp) {
        if (PlayerHandler.isWielding(skill, playerEntity)) {
            PlayerHandler.awardXP(playerEntity, skill, exp);
        } else {
            PlayerHandler.taskFail(playerEntity);
        }
    }

    public static PlayerData getPlayer(String playerID) {
        PlayerData player = PlayerStore.getPlayer(playerID);
        if (player == null) {
            DataSave.createPlayer(playerID);
            player = PlayerStore.getPlayer(playerID);
        }
        return player;
    }

    public static PlayerData getPlayer(UUID uniqueID) {
        return PlayerHandler.getPlayer(uniqueID.toString());
    }

    public static boolean hasPerk(Perk perk, PlayerData player) {
        return player.getPerkList().contains(perk.getSlug());
    }

    /*public static boolean hasPerk(PlayerData player, ArrayList<Slug> slugList) {
        if(player == null || slugList == null){//Null check
            return false;
        }
        for (Slug slug : slugList) {
            for(Slug current : player.getPerkList()) {
                if(slug.getSlug().equals(current.getSlug()))
                return true;
            }
        }
        return false;
    }*/

    /**
     * Takes a player and perkList and will return true if they have any of the perks in the list.
     *
     * @param player   The player data to check that contains the perk.
     * @param perkList The perk list to check against.
     * @return result The result.
     */
    public static boolean hasPerk(PlayerData player, ArrayList<? extends Perk> perkList) {
        if (player == null || perkList == null) {//Null check
            return false;
        }
        for (Perk perk : perkList) {
            for (Slug slug : player.getPerkList()) {
                if (slug.getSlug().equals(perk.getSlug().getSlug())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sends a failure message to the player.
     * TODO: Language support
     *
     * @param player The player to send the message to.
     */
    public static void taskFail(EntityPlayer player) {
        player.addChatComponentMessage(new TextComponentString("You are unable to preform this task"));
    }

    /**
     * Sends a failure message to the player for when they have the wrong tool.
     *
     * @param player The player to send the message to.
     */
    public static void toolFail(EntityPlayer player) {
        player.addChatComponentMessage(new TextComponentString("You do not have the right tool for this task"));
    }

    /**
     * Checks that the entity is weilding the associated tool.
     *
     * @param skill  The ToolSkill that is for checking the tool against.
     * @param player The player object to check against.
     * @return The result. If a tool is not required it will return true.
     */
    public static boolean isWielding(ToolSkill skill, EntityPlayer player) {
        /*if(skill.toolClass == Item.class){//TODO:Fix
            return true;
        } else if(player.getCurrentEquippedItem() == null){
            return skill.toolClass == null;
        } else {        
            return skill.toolClass.isInstance(player.getCurrentEquippedItem().getItem());
        }*/
        return true;
    }


    /**
     * 'Activates' a perk for a player.
     *
     * @param entityPlayer The player to activate the perk on.
     * @param playerData   The player data to record the perk onto.
     * @param perkSlug     The slug of the perk to activate.
     * @return The result (in text).
     */
    public static String activatePerk(EntityPlayerMP entityPlayer, PlayerData playerData,
                                      String perkSlug) {
        ActivePerk cachePerk = (ActivePerk) PerkStore.getPerk(perkSlug);
        if (PlayerHandler.hasPerk((Perk) cachePerk, playerData)) {
            cachePerk.activate(entityPlayer, null);
            return "Sucess";
        }
        return "Failure (Missing " + ((Perk) cachePerk).getSlug().getSlug() + ")";

    }

    public void reactivatePerks(PlayerEvent.PlayerLoggedInEvent event) {
        this.activateDataPerks((EntityPlayerMP) event.player);
        this.activateNbtPerks((EntityPlayerMP) event.player);

    }

    public void activateDataPerks(EntityPlayerMP player) {
        this.activateDataPerks(player, PlayerHandler.getPlayer(player.getUniqueID()));
    }

    public void activateDataPerks(EntityPlayerMP player, PlayerData playerData) {
        for (Slug slug : playerData.getActivePerks()) {
            PlayerHandler.activatePerk(player, playerData, slug.getSlug());
        }
    }

    public void activateNbtPerks(EntityPlayerMP player) {
        this.activateNbtPerks(player, PlayerHandler.getPlayer(player.getUniqueID()));
    }

    public void activateNbtPerks(EntityPlayerMP player, PlayerData playerData) {
        NBTTagList list = player.getEntityData().getTagList(TaskTrackPlayer.tagName, 8);
        for (int i = 0; i < list.tagCount(); i++) {
            PlayerHandler.activatePerk(player, playerData, list.getStringTagAt(i));
        }
    }

    public void checkPlayerSkills(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerData data = PlayerHandler.getPlayer(event.player.getUniqueID());
        HashMap<String, Integer> map = data.getSkillExp();
        for (Skill skill : SkillStore.getSkills()) {
            if (map.get(skill.name) == null) {
                map.put(skill.name, new Integer(0));
            }
        }
    }

}