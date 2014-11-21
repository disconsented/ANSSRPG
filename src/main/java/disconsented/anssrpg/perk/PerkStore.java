package disconsented.anssrpg.perk;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author: Disconsented
 * Just stores and retrieves perks
 */
public class PerkStore {
	private static ArrayList<Perk> perks = new ArrayList<Perk>();
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
}
