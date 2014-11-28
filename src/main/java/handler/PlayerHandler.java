 package handler;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.perk.EntityPerk;
import disconsented.anssrpg.perk.Perk;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.skill.objects.BlockXP;
 /**
  * @author Disconsented
  * Handles the data that is stored on players (experience for skills and perks)
  */

    public final class PlayerHandler{
    	
    	public static PlayerData getPlayer(String playerID){
    		return PlayerStore.getPlayer(playerID);
    	}
    	public static void addPerk(Perk perk, PlayerData player){
    		player.getPerkList().add(perk.getSlug());
    	}
    	
    	public static void addPerk(String perkSlug, PlayerData player){
    		player.getPerkList().add(perkSlug);
    	}    	
    	public static void addXp(Integer num, String skillName, PlayerData player){
    		player.getSkillExp().put(skillName, player.getSkillExp().get(skillName) + num);
    	}
    	
    	public static boolean hasPerk(Perk perk, PlayerData player){
			return player.getPerkList().contains(perk.getSlug());
		}
    	
    	public static int getPoints(PlayerData player){
    		return player.getPoints();
    	}
		public static boolean hasPerk(PlayerData player, ArrayList<? extends Perk> blocklist) {
			for (int i = 0; i < blocklist.size(); i++)
			{
				if(player.getPerkList().contains(blocklist.get(i))){
					return true;
				}
			}
			return false;
		}
		public static void awardXP(PlayerData player, String name, int value, EntityPlayer playerEntity) {
			if (player.getSkillExp().get(name) != null){
			player.getSkillExp().put(name, 
					new Integer(player.getSkillExp().get(name).intValue() + value));
			}
			else
			{
				player.getSkillExp().put(name, value);
			}
			playerEntity.addChatComponentMessage(new ChatComponentText("You have been awared with "+value+" xp"));
		}
		public static void taskFail(EntityPlayer player) {			
			player.addChatComponentMessage(new ChatComponentText("You are unable to preform this task"));
		}
   }