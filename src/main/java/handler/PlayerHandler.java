 package handler;

import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.perk.Perk;
import disconsented.anssrpg.player.PlayerData;
 /**
  * @author Disconsented
  * Handles the data that is stored on players (experience for skills and perks)
  */

    public final class PlayerHandler{
    	public static PlayerData getPlayer(String playerID){
    		return PlayerStore.getPlayer(playerID);
    	}
    	public static void addPerk(Perk perk, PlayerData player){
    		player.getPerkList().add(perk.getSlug());}
    	public static void addPerk(String perkSlug, PlayerData player){
    		player.getPerkList().add(perkSlug);
    	}    	
    	public static void addXp(Integer num, String skillName, PlayerData player){
    		player.getSkillExp().put(skillName, player.getSkillExp().get(skillName) + num);}
    	
    	public static boolean hasPerk(Perk perk, PlayerData player){
			return player.getPerkList().contains(perk.getSlug());}
    	
    	public static int getPoints(PlayerData player){
    		return player.getPoints();}
    }