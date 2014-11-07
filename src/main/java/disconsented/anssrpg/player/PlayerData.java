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
	public ArrayList<String> perkList = new ArrayList<String>();
	public HashMap skillExp = new HashMap();
	public String playerID;
	public int points;

	public PlayerData(ArrayList perkList, HashMap skillExp, String playerID, int points){
		this.perkList = perkList;
		this.skillExp = skillExp;
		this.playerID = playerID; 
		this.points = points;
	}
}