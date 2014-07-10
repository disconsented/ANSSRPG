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
	public ArrayList<Perk> perkList = new ArrayList<Perk>();
	public HashMap skillExp = new HashMap();
	public String playerID;

	public PlayerData(ArrayList perkList, HashMap skillExp, String playerID){
		this.perkList = perkList;
		this.skillExp = skillExp;
		this.playerID = playerID; 
	}
}