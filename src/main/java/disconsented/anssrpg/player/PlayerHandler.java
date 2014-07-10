 package disconsented.anssrpg.player;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import disconsented.anssrpg.Settings;
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.data.DataSave;
import disconsented.anssrpg.perk.Perk;
import disconsented.anssrpg.perk.PerkStore;
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
          		 int amount = 0;
          		 if (DataSave.getPlayerData(playerID).skillExp.get(skillName) != null){
          			 amount = (int) DataSave.getPlayerData(playerID).skillExp.get(skillName);
          		 }
          		 return amount;
          	 }
          	 
          	 public static int getLevel(int xp){        	
          		 return (int) (Math.log10((double)xp)/Math.log10(levelCurve));
          	 }
          	 public boolean hasPerk(Perk perk, String playerID){          		 
				return DataSave.getPlayerData(playerID).perkList.contains(perk);          		 
          	 }
          	public static boolean hasPerk(Block block, String playerID){
          		boolean has = false; 
          		if (PerkStore.getBlockPerkList(block) != null){
          			for (int x = 0; x < PerkStore.getBlockPerkList(block).size(); x++){
          				if (DataSave.getPlayerData(playerID).perkList.contains(PerkStore.getBlockPerkList(block))){
          					has = true;
          				}
          				else{
          					if (Settings.getDebug()){
          						System.out.println(DataSave.getPlayerData(playerID).perkList.toString());
          						System.out.println(PerkStore.getBlockPerkList(block));
          					}
          				}
          		}
          		}
				return has;          		 
          	 }
          	public static boolean hasPerk(Item item, String playerID){
          		boolean has = false;
          		for(int i = 0;i < DataSave.getPlayerData(playerID).perkList.size(); i++){
          			if (DataSave.getPlayerData(playerID).perkList.get(i).unlockItem == item){
          				has = true;
          			}
          		}
				return has;          		 
          	 }
          	public static boolean hasPerk(Entity entity, String playerID){
          		boolean has = false;
          		for(int i = 0;i < DataSave.getPlayerData(playerID).perkList.size(); i++){
          			if (DataSave.getPlayerData(playerID).perkList.get(i).unlockEntity == entity){
          				has = true;
          			}
          		}
				return has;          		 
          	 }
          	 public static void addPerk(Perk perk, String playerID){
          		DataSave.getPlayerData(playerID).perkList.add(perk);
          	 }
          	public static void addPerk(String perkSlug, String playerID){
          		DataSave.getPlayerData(playerID).perkList.add(PerkStore.getPerk(perkSlug));
          	 }
          	public static ArrayList getPerks(String UUID){
          		try {
          			return DataSave.getPlayerData(UUID).perkList;
          		}catch(NullPointerException e){
          			return null;
          		}
          	}
    }
     
