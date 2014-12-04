 package disconsented.anssrpg.handler;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.data.DataSave;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.perk.EntityPerk;
import disconsented.anssrpg.perk.Perk;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.skill.objects.BlockXP;
import disconsented.anssrpg.perk.Requirement;
 /**
  * @author Disconsented
  * Handles the data that is stored on players (experience for skills and perks)
  */

    public final class PlayerHandler{
    	
    	public static PlayerData getPlayer(String playerID){
    		PlayerData player = PlayerStore.getPlayer(playerID);
    		if (player == null){
    			DataSave.createPlayer(playerID);
    			player = PlayerStore.getPlayer(playerID);
    		}
    		return player;
    	}
    	public static void addPerk(Perk perk, PlayerData player){
    		player.getPerkList().add(perk.getSlug());
    	}
    	
    	public static String addPerk(String perkSlug, PlayerData player){
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
    		for(Requirement req : requirements){    			
    			switch(req.action){
				case DONT:
					if(player.getPerkList().contains(req.getNameAsSlug())){
						cont = false;
						toReturn = "Unable to grant perk, "+req.name+" was found on the player";
					}
					break;
				case HAVE:
					if (!player.getPerkList().contains(req.getNameAsSlug())){
						cont = false;
						toReturn = "Unable to grant perk,"+req.name+" could not be found on the player";
					}
					break;
				case LEVEL_EQUALS:
					if (!(player.getSkillExp().get(req.name) == Integer.parseInt(req.extraData))){
						cont = false;
						toReturn = "Unable to grant perk,"+perkSlug+"'s level did not equal "+req.extraData;
					}
					break;
				case LEVEL_GREATER:
					if (player.getSkillExp().get(req.name) < Integer.parseInt(req.extraData)){
						cont = false;
						toReturn = "Unable to grant perk,"+perkSlug+"'s level was less than "+req.extraData;
					}
					break;
				case LEVEL_LESS:
					if (player.getSkillExp().get(req.name) > Integer.parseInt(req.extraData)){
						cont = false;
						toReturn = "Unable to grant perk,"+perkSlug+"'s level did not equal "+req.extraData;
					}
					break;
				default:
					cont = false;
					break;    			
    			}
    			if (!cont){
    				break;
    			}
    		}
    		if(cont){
    			player.getPerkList().add(perkSlug);
    			toReturn = "All conditions meet, granting perk";
    		}
			return toReturn;
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
			/*Check for level up
			 * 	If leveled up send info
			 *  
			*/
			int temp = player.getSkillExp().get(name);
			if ((value + temp >= Math.pow(Settings.getInstance().getLevelCurve(), getLevel((Integer) temp)+1))){
				playerEntity.addChatComponentMessage(new ChatComponentText("Your skill "+name+" has leveled up to "+getLevel(temp)));
				if (Settings.getInstance().getPointsMode() == 1){
					player.setPoints(player.getPoints() + 1);
					playerEntity.addChatComponentMessage(new ChatComponentText("You have recieved 1 perk point for leveling up"));
				}
			} 
			
						
		}
		public static void taskFail(EntityPlayer player) {			
			player.addChatComponentMessage(new ChatComponentText("You are unable to preform this task"));
		}
		public static int getLevel(int xp){
			return (int) (Math.log10((double)xp)/Math.log10(Settings.getInstance().getLevelCurve()));
		}
   }