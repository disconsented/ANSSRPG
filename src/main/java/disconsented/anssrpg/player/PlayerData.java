package disconsented.anssrpg.player;
/**
 * @author James
 * For storing data about players
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import disconsented.anssrpg.perk.Perk;
import net.minecraft.item.Item;

public class PlayerData {
	private ArrayList<String> perkList = new ArrayList<String>();
	private HashMap<String, Integer> skillExp = new HashMap<String, Integer>();
	private String playerID;
	private int points;

	public PlayerData(ArrayList perkList, HashMap skillExp, String playerID, int points){
		this.perkList = perkList;
		this.skillExp = skillExp;
		this.playerID = playerID; 
		this.points = points;
	}

	public ArrayList<String> getPerkList() {
		return perkList;
	}

	public void setPerkList(ArrayList<String> perkList) {
		this.perkList = perkList;
	}

	public HashMap<String, Integer> getSkillExp() {
		return skillExp;
	}

	public void setSkillExp(HashMap<String, Integer> skillExp) {
		this.skillExp = skillExp;
	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}