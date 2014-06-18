 package disconsented.anssrpg.data;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import disconsented.anssrpg.config.JsonConfigHandler;
 /**
  * @author James
  * Handles the data that is stored on players (experience for skills and perks)
  */

    public final class PlayerHandler{
            static double levelCurve = JsonConfigHandler.getLevelCurve();
             public static void sendFail(EntityPlayerMP player){
            	 player.addChatComponentMessage(new ChatComponentText("You are not skilled enough to preform this action"));
             }
          	 public static void addXP(int amount, String skillName, EntityPlayerMP player){
          		 String playerID = player.getPersistentID().toString();
          		 setXP(amount + getXP(skillName,playerID),skillName, playerID);
          		 player.addChatComponentMessage(new ChatComponentText("Congratulations you have gained "+amount+" XP for "+skillName)); 
          		 if (canLevelUp(amount, skillName, playerID)){
          			player.addChatComponentMessage(new ChatComponentText("Congratulations your skill "+skillName+"has leveled up!"));
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
          		 return (int) DataSave.getPlayerData(playerID).skillExp.get(skillName);
          	 }
          	 
          	 public static int getLevel(int xp){        	
          		 return (int) (Math.log10((double)xp)/Math.log10(levelCurve));
          	 }
          	 public boolean hasPerk(PerkObject perk, String playerID){          		 
				return DataSave.getPlayerData(playerID).perkList.contains(perk);          		 
          	 }
          	public static boolean hasPerk(Block block, String playerID){
          		boolean has = false;
          		for(int i = 0;i < DataSave.getPlayerData(playerID).perkList.size(); i++){
          			if (DataSave.getPlayerData(playerID).perkList.get(i).unlockBlock == block){
          				has = true;
          			}
          		}
				return has;          		 
          	 }
          	 public void addPerk(PerkObject perk, String playerID){
          		DataSave.getPlayerData(playerID).perkList.add(perk);
          	 }
    }
     
