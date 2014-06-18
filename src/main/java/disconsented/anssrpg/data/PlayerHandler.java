 package disconsented.anssrpg.data;

import disconsented.anssrpg.config.JsonConfigHandler;
 /**
  * @author James
  * Handles the data that is stored on players (experience for skills and perks)
  */

    public final class PlayerHandler{
            double levelCurve = JsonConfigHandler.getLevelCurve();
     
          	 public void addXP(int amount, String skillName, String playerID){
          		 setXP(amount + getXP(skillName,playerID),skillName, playerID);
          	 }
          	 public boolean canLevelUp(int newExp, String skillName, String playerID)
          	 {
          		 int temp = getXP(skillName, playerID);
          		 if ((newExp + temp >= Math.pow(levelCurve, getLevel((Integer) temp)+1))){
          			return true;
           		 }
           		 else{
           			return false;
           		 }				
          	 }
          	 
          	 public void setXP(int ammount, String skillName, String playerID){
          		DataSave.getPlayerData(playerID).skillExp.put(skillName, ammount);
          	 }       
       
          	 public int getXP(String skillName, String playerID){
          		 return (int) DataSave.getPlayerData(playerID).skillExp.get(skillName);
          	 }
          	 
          	 public int getLevel(int xp){        	
          		 return (int) (Math.log10((double)xp)/Math.log10(levelCurve));
          	 }
          	 public boolean hasPerk(PerkObject perk, String playerID){          		 
				return DataSave.getPlayerData(playerID).perkList.contains(perk);
          		 
          	 }
          	 public void addPerk(PerkObject perk, String playerID){
          		DataSave.getPlayerData(playerID).perkList.add(perk);
          	 }
    }
     
