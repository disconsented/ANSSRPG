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
package disconsented.anssrpg.handler;

import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import disconsented.anssrpg.common.ChatUtil;
import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.data.DataSave;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.data.SkillStore;
import disconsented.anssrpg.perk.ActivePerk;
import disconsented.anssrpg.perk.Perk;
import disconsented.anssrpg.perk.Requirement;
import disconsented.anssrpg.perk.Slug;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.skill.objects.Skill;
import disconsented.anssrpg.skill.objects.ToolSkill;
import disconsented.anssrpg.task.TaskTrackPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author Disconsented
 *         Handles the data that is stored on players (experience for skills and perks)
 */

public final class PlayerHandler {

    public static void addPerk(Perk perk, PlayerData player) {
        player.getPerkList().add(perk.slug);
    }

    public static String addPerk(String perkSlug, EntityPlayer player) {
        return addPerk(perkSlug, getPlayer(player.getUniqueID()));
    }

    /**
     * Checks the requirements for a perk and adds it to the player if they meet them
     *
     * @param perkSlug
     * @param player
     * @return the result
     */
    @SuppressWarnings({"unchecked", "SuspiciousMethodCalls"})
    public static String addPerk(String perkSlug, PlayerData player) {
        Perk perk = PerkStore.getPerk(perkSlug);
        if (perk == null) {
            return "No perk with that slug was found";
        }
        //Cached here for readability
        int level = 0;
        int reqLevel = 0;
        ArrayList<Requirement> requirements = perk.getRequirements();
        for (Requirement req : requirements) {
            if (req.action == Requirement.Action.LEVEL_EQUALS || req.action == Requirement.Action.LEVEL_GREATER || req.action == Requirement.Action.LEVEL_LESS) {
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
                    if (!player.getPerkList().contains(req.getNameAsSlug()))
                        return "Unable to grant perk, " + req.name + " could not be found on the player";
                    break;
                case LEVEL_EQUALS:
                    if (!(level == reqLevel))
                        return "Unable to grant perk, " + req.name + "'s level did not equal " + req.extraData;
                    break;
                case LEVEL_GREATER:
                    if (level < reqLevel)
                        return "Unable to grant perk, " + req.name + "'s level was less than " + req.extraData;
                    break;
                case LEVEL_LESS:
                    if (level > reqLevel)
                        return "Unable to grant perk, " + req.name + "'s level did not equal " + req.extraData;
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

    public static void addXp(Integer num, String skillName, PlayerData player) {
        player.getSkillExp().put(skillName, player.getSkillExp().get(skillName) + num);
    }

    public static void awardXP(EntityPlayer playerEntity, Skill skill, int exp) {
        ArrayList<String> msgToSend = new ArrayList<>();
        PlayerData player = getPlayer(playerEntity.getUniqueID().toString());
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
        msgToSend.add("+" + exp + "exp -> " + player.getSkillExp().get(skill.name) + " total");

        if (levelNew > levelOld) {
            msgToSend.add("Your skill " + skill.name + " has leveled up to " + levelNew);

            if (Settings.getPointsMode() == 1) {
                player.setPoints(player.getPoints() + 1);
                msgToSend.add("You have recieved 1 perk point for leveling up");
            }
        }
//        if (msgToSend.size() > 1)
            ChatUtil.sendNoSpamClient(msgToSend);
    }

    public static void awardToolXP(EntityPlayer playerEntity, ToolSkill skill, int exp) {
        if (isWielding(skill, playerEntity)) {
            awardXP(playerEntity, skill, exp);
        } else {
            taskFail(playerEntity);
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
        return getPlayer(uniqueID.toString());
    }

    public static int getPoints(PlayerData player) {
        return player.getPoints();
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
     * Takes a player and perkList and will return true if they have any of the perks in the list
     *
     * @param player
     * @param perkList
     * @return result
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

    public static void taskFail(EntityPlayer player) {
        player.addChatComponentMessage(new ChatComponentText("You are unable to preform this task"));
    }

    /**
     * Checks that the entity is weilding the associated tool
     * If a tool is not required always returns true
     *
     * @param skill
     * @param player
     * @return
     */
    public static boolean isWielding(ToolSkill skill, EntityPlayer player) {
        if (skill.toolClass == net.minecraft.item.Item.class) {
            return true;
        } else if (player.getCurrentEquippedItem() == null) {
            return skill.toolClass == null;
        } else {
            return skill.toolClass.isInstance(player.getCurrentEquippedItem().getItem());
        }
    }


    public static String activatePerk(EntityPlayer player, PlayerData playerData, String perkSlug) {
        ActivePerk cachePerk = (ActivePerk) PerkStore.getPerk(perkSlug);
        if (hasPerk((Perk) cachePerk, playerData)) {
            cachePerk.activate(player, null);
            return "Sucess";
        }
        return "Failure (Missing " + ((Perk) cachePerk).getSlug().getSlug() + ")";

    }

    public void reactivatePerks(PlayerLoggedInEvent event) {
        activateDataPerks(event.player);
        activateNbtPerks(event.player);
    }

    public void activateDataPerks(EntityPlayer player) {
        activateDataPerks(player, getPlayer(player.getUniqueID()));
    }

    public void activateDataPerks(EntityPlayer player, PlayerData playerData) {
        for (Slug slug : playerData.getActivePerks()) {
            activatePerk(player, playerData, slug.getSlug());
        }
    }

    public void activateNbtPerks(EntityPlayer player) {
        activateNbtPerks(player, getPlayer(player.getUniqueID()));
    }

    public void activateNbtPerks(EntityPlayer player, PlayerData playerData) {
        NBTTagList list = player.getEntityData().getTagList(TaskTrackPlayer.tagName, 8);
        for (int i = 0; i < list.tagCount(); i++) {
            activatePerk(player, playerData, list.getStringTagAt(i));
        }
    }

    public void checkPlayerSkills(PlayerLoggedInEvent event) {
        PlayerData data = getPlayer(event.player.getUniqueID());
        HashMap<String, Integer> map = data.getSkillExp();
        for (Skill skill : SkillStore.getSkills()) {
            if (map.get(skill.name) == null) {
                map.put(skill.name, new Integer(0));
            }
        }
    }

}