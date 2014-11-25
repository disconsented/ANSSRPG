 package handler;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.data.DataSave;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.perk.Perk;
import disconsented.anssrpg.player.PlayerData;
 /**
  * @author James
  * Handles the data that is stored on players (experience for skills and perks)
  */

    public final class PlayerHandler{
            static double levelCurve = Settings.getLevelCurve();
             public static void sendFail(EntityPlayerMP player){
            	 player.addChatComponentMessage(new ChatComponentText("You are not skilled enough to preform this action"));
             }
          	 public static void addXP(int amount, String skillName, EntityPlayerMP player){
          		 String playerID = player.getPersistentID().toString();
          		 setXP(amount + getXP(skillName,playerID),skillName, playerID);
          		 player.addChatComponentMessage(new ChatComponentText("Congratulations you have gained "+amount+" XP for "+skillName)); 
          		 if (canLevelUp(amount, skillName, playerID)){
          			player.addChatComponentMessage(new ChatComponentText("Congratulations your skill "+skillName+" has leveled up!"));
          		 }
          	 }
          	 public static boolean canLevelUp(int newExp, String skillName, String playerID)
          	 {
          		 int temp = getXP(skillName, playerID);
          		 if ((newExp + temp >= Math.pow(levelCurve, getLevel((Integer) temp)+1))){
          			return true;
           		 }
           		 else{
           			return false;
           		 }				
          	 }
          	 
          	 public static void setXP(int ammount, String skillName, String playerID){
          		DataSave.getPlayerData(playerID).skillExp.put(skillName, ammount);
          	 }       
       
          	 public static int getXP(String skillName, String playerID){
          		 int amount = 0;
          		 if (DataSave.getPlayerData(playerID).skillExp.get(skillName) != null){
          			 amount = (int) DataSave.getPlayerData(playerID).skillExp.get(skillName);
          		 }
          		 return amount;
          	 }
          	 
          	 public static int getLevel(int xp){        	
          		 return (int) (Math.log10((double)xp)/Math.log10(levelCurve));
          	 }
          	 public static boolean hasPerk(Perk perk, String playerID){          		 
				return DataSave.getPlayerData(playerID).perkList.contains(perk.perkSlug);          		 
          	 }
          	public static void addPerk(Perk perk, String playerID){
          		DataSave.getPlayerData(playerID).perkList.add(perk.perkSlug);
          	 }
          	public static String addPerk(String perkSlug, String playerID){
          		String toReturn = null;
          		PlayerData player = DataSave.getPlayerData(playerID);
          		Perk temp = PerkStore.getPerk(perkSlug);
          		if (player.points <= temp.pointCost){
          			PlayerHandler.removePoints(temp.pointCost, player);
          			DataSave.getPlayerData(playerID).perkList.add(perkSlug);
          			toReturn = "Success";
          		}
          		else{
          			toReturn = "Insuffient Points";
          		}
          		return toReturn;
          	 }
          	private static void removePoints(int pointCost, PlayerData temp) {
				temp.points -= pointCost;
			}
			public static ArrayList getPerks(String UUID){
          		try {
          			return DataSave.getPlayerData(UUID).perkList;
          		}catch(NullPointerException e){
          			return null;
          		}
          	}
    }
     