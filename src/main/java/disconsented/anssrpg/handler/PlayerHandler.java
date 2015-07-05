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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

/**
 * @author Disconsented
 *         Handles the data that is stored on players (experience for skills and perks)
 */

public final class PlayerHandler {

    public static void addPerk(Perk perk, PlayerData player) {
        player.getPerkList().add(perk.slug);
    }

    /**
     * Checks the requirements for a perk and adds it to the player if they meet them
     * @param perkSlug
     * @param player
     * @return the result
     */
    public static String addPerk(String perkSlug, PlayerData player) {
        Perk perk = PerkStore.getPerk(perkSlug);
        if (perk == null){
            return "No perk with that slug was found"; 
        }
        //Cached here for readability
        int level = 0;
        int reqLevel = 0;
        ArrayList<Requirement> requirements = perk.getRequirements();
        for (Requirement req : requirements) {
            if(req.action == Requirement.Action.LEVEL_EQUALS || req.action == Requirement.Action.LEVEL_GREATER || req.action == Requirement.Action.LEVEL_LESS){
                level = player.getSkillLevel(SkillHandler.getSkill(req.name));
                reqLevel = Integer.parseInt(req.extraData);
            }
            switch(req.action){
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
                    if (!(level == reqLevel)){
                        return "Unable to grant perk, " + req.name + "'s level did not equal " + req.extraData;
                    }
                    break;
                case LEVEL_GREATER:
                    if(level < reqLevel){
                        return "Unable to grant perk, " + req.name + "'s level was less than " + req.extraData;
                    }
                    break;
                case LEVEL_LESS:
                    if(level > reqLevel){
                        return "Unable to grant perk, " + req.name + "'s level did not equal " + req.extraData;
                    }
                    break;
            }
        }
        if(player.getPerkList().contains(perkSlug)){
            return "Already have "+ perkSlug;
        } else {
            return "All requirements meet; Granting " + perkSlug;
        }
    }

    public static void addXp(Integer num, String skillName, PlayerData player) {
        player.getSkillExp().put(skillName, player.getSkillExp().get(skillName) + num);
    }

    public static void awardXP(EntityPlayer playerEntity, Skill skill, int exp) {
        PlayerData player = getPlayer(playerEntity.getUniqueID().toString());
        Integer cacheExp = player.getSkillExp().get(skill.name);
        long levelOld;
        long levelNew = -1;
        if(cacheExp != null){
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
            for(Slug current : player.getPerkList()) {
                if(slug.getSlug().equals(current.getSlug()))
                return true;
            }
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
    

    public static String activatePerk(EntityPlayerMP p2, PlayerData playerData,
            String perkSlug) {
        ActivePerk cachePerk = (ActivePerk) PerkStore.getPerk(perkSlug);
        if(hasPerk((Perk) cachePerk, playerData)){
            cachePerk.activate(p2, null);
            return "Sucess";
        }
        return "Failure (Missing "+((Perk)cachePerk).getSlug().getSlug()+ ")";
        
    }

    public void reactivatePerks(PlayerLoggedInEvent event) {
        activateDataPerks((EntityPlayerMP) event.player);
        activateNbtPerks((EntityPlayerMP) event.player);       
        
    }
    
    public void activateDataPerks(EntityPlayerMP player){
        activateDataPerks(player, getPlayer(player.getUniqueID()));
    }
    
    public void activateDataPerks(EntityPlayerMP player, PlayerData playerData){
        for (Slug slug : playerData.getActivePerks()){
            activatePerk(player, playerData, slug.getSlug());
        }
    }
    
    public void activateNbtPerks(EntityPlayerMP player){
        activateNbtPerks(player, getPlayer(player.getUniqueID()));
    }
    
    public void activateNbtPerks(EntityPlayerMP player, PlayerData playerData){
        NBTTagList list = player.getEntityData().getTagList(TaskTrackPlayer.tagName, 8);
        for (int i = 0; i < list.tagCount(); i++){
            activatePerk(player, playerData, list.getStringTagAt(i));
        }
    }
    
    public void checkPlayerSkills(PlayerLoggedInEvent event){
        PlayerData data = getPlayer(event.player.getUniqueID());
        HashMap<String,Integer> map = data.getSkillExp();
        for (Skill skill :SkillStore.getSkills()){
            if(map.get(skill.name) == null){
                map.put(skill.name, new Integer(0));
            }
        }
    }

}