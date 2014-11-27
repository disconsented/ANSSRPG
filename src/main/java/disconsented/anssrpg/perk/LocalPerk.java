/**
 * 
 */
package disconsented.anssrpg.perk;

import java.util.ArrayList;

public class LocalPerk {
	
	public String name;
	public ArrayList <String> requirements = new ArrayList();
	public String perkSlug;
	public String description;
	public int pointCost;
	
	private String getSlug(String name){		
		return name.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
	}
	
/**
 * 
 * @param name - Name of the perk
 * @param unlockBlock - What it is unlocking
 * @param description - Description
 * @param pointCost - Cost in points to unlock
 * @param requirement - 
 */
	public LocalPerk (String name, String description, int pointCost, ArrayList requirements){
		this.perkSlug = getSlug(name);
		this.name = name;
		this.requirements = requirements;
		this.pointCost = pointCost;
		this.description = description;
	}
}
