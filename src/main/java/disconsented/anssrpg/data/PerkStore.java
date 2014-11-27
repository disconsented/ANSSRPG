package disconsented.anssrpg.data;

import java.util.ArrayList;
import java.util.HashMap;

import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.perk.EntityPerk;
import disconsented.anssrpg.perk.ItemPerk;
import disconsented.anssrpg.perk.Perk;

/**
 * Author: Disconsented
 * Just stores and retrieves perks
 */
public class PerkStore {
	private static ArrayList<Perk> perks = new ArrayList<Perk>();
	private static HashMap<String, ArrayList<BlockPerk>> blockMap = new HashMap<String, ArrayList<BlockPerk>>();
	private static HashMap<String, ArrayList<EntityPerk>> entityMap = new HashMap<String, ArrayList<EntityPerk>>();
	private static HashMap<String, ArrayList<ItemPerk>> itemMap = new HashMap<String, ArrayList<ItemPerk>>();
	private static HashMap<String,Perk> perksMap = new HashMap<String,Perk>();
	private static PerkStore instance = null;
	protected PerkStore() {/* Exists only to defeat instantiation.*/}
	public static PerkStore getInstance() {
		if(instance == null) {
			instance = new PerkStore();
		}
		return instance;
	}
	public ArrayList<Perk> getPerks(){
		return perks;
	}
	public void addPerk(Perk perk){
		perks.add(perk);
		perksMap.put(perk.perkSlug,perk);
	}
	public static Perk getPerk(String perkSlug) {		
		return perksMap.get(perkSlug);
	}
	public static ArrayList<BlockPerk> getPerksForBlock(String unlocalisedName){
		return blockMap.get(unlocalisedName);	
	}
	public static void putBlockPerk(BlockPerk perk){
		perks.add(perk);
		if (blockMap.containsKey(perk.getBlock().getUnlocalizedName())){
			blockMap.get(perk.getBlock().getUnlocalizedName()).add(perk);
		}
		else
		{
			ArrayList<BlockPerk> temp = new ArrayList<BlockPerk>();
			temp.add(perk);
			blockMap.put(perk.getBlock().getUnlocalizedName(), temp);
		}
	}
	public static ArrayList<EntityPerk> getPerksForEntity(
			String commandSenderName) {
		return entityMap.get(commandSenderName);
	}
}
