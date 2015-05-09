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

import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.data.DataSave;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.perk.Perk;
import disconsented.anssrpg.perk.Requirement;
import disconsented.anssrpg.perk.Slug;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.skill.objects.Skill;
import disconsented.anssrpg.skill.objects.ToolSkill;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Disconsented
 *         Handles the data that is stored on players (experience for skills and perks)
 */

public final class PlayerHandler {

    public static void addPerk(Perk perk, PlayerData player) {
        player.getPerkList().add(perk.slug);
    }

    public static String addPerk(String perkSlug, PlayerData player) {
        /*Get Perk
         * Check requirements
         * Logic requirements
         * Check points
         * deduct points
         * attribute perk
         * */
        String toReturn = "error";
        Perk perk = PerkStore.getPerk(perkSlug);
        ArrayList<Requirement> requirements = perk.getRequirements();
        boolean cont = true;
        for (Requirement req : requirements) {
            switch (req.action) {
                case DONT:
                    if (player.getPerkList().contains(req.getNameAsSlug())) {
                        cont = false;
                        toReturn = "Unable to grant perk, " + req.name + " was found on the player";
                    }
                    break;
                case HAVE:
                    if (!player.getPerkList().contains(req.getNameAsSlug())) {
                        cont = false;
                        toReturn = "Unable to grant perk," + req.name + " could not be found on the player";
                    }
                    break;
                case LEVEL_EQUALS:
                    if (!(player.getSkillExp().get(req.name) == Integer.parseInt(req.extraData))) {
                        cont = false;
                        toReturn = "Unable to grant perk," + req.name + "'s level did not equal " + req.extraData;
                    }
                    break;
                case LEVEL_GREATER:
                    if (player.getSkillExp().get(req.name) < Integer.parseInt(req.extraData)) {
                        cont = false;
                        toReturn = "Unable to grant perk," + req.name + "'s level was less than " + req.extraData;
                    }
                    break;
                case LEVEL_LESS:
                    if (player.getSkillExp().get(req.name) > Integer.parseInt(req.extraData)) {
                        cont = false;
                        toReturn = "Unable to grant perk," + req.name + "'s level did not equal " + req.extraData;
                    }
                    break;
                default:
                    cont = false;
                    break;
            }
            if (!cont) {
                break;
            }
        }
        if (cont && !player.getPerkList().contains(perkSlug)) {
            addPerk(perk, player);
            toReturn = "All conditions meet, granting perk";
        }
        return toReturn;
    }

    public static void addXp(Integer num, String skillName, PlayerData player) {
        player.getSkillExp().put(skillName, player.getSkillExp().get(skillName) + num);
    }

    public static void awardXP(EntityPlayer playerEntity, Skill skill, int exp) {
        PlayerData player = getPlayer(playerEntity.getUniqueID().toString());
        Integer cacheExp = player.getSkillExp().get(skill.name);
        long levelOld = SkillHandler.calulteLevelForExp(skill, exp);
        long levelNew = 0;
        
        if (cacheExp != null) {
            player.getSkillExp().put(skill.name, exp + cacheExp);
            levelNew = SkillHandler.calulteLevelForExp(skill, exp + cacheExp);
        } else {
            player.getSkillExp().put(skill.name, exp);
            levelNew = SkillHandler.calulteLevelForExp(skill, exp);
        }
        playerEntity.addChatComponentMessage(new ChatComponentText("You have been awared with " + exp + " exp"));
        /* Check for level up
         * If leveled up send info
         */
        
        if (levelNew > levelOld) {
            playerEntity.addChatComponentMessage(new ChatComponentText("Your skill " + skill.name + " has leveled up to " + levelNew));
            
            if (Settings.getPointsMode() == 1) {
                player.setPoints(player.getPoints() + 1);
                playerEntity.addChatComponentMessage(new ChatComponentText("You have recieved 1 perk point for leveling up"));
            }
        }        
    }
    public static void awardToolXP(EntityPlayer playerEntity, ToolSkill skill, int exp){
        if(isWielding(skill, playerEntity)){
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

    public static boolean hasPerk(PlayerData player, ArrayList<Slug> slugList) {
        for (Slug slug : slugList) {
            if (player.getPerkList().contains(slug)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static void taskFail(EntityPlayer player) {
        player.addChatComponentMessage(new ChatComponentText("You are unable to preform this task"));
    }
    
    public static boolean isWielding(ToolSkill skill, EntityPlayer player){
        if(skill.toolClass == net.minecraft.item.Item.class){
            return true;
        } else if(player.getCurrentEquippedItem() == null){
            return skill.toolClass == null;
        } else {        
            return skill.toolClass.isInstance(player.getCurrentEquippedItem().getItem());
        }
    }
}