/**
 * 
 */
package disconsented.anssrpg.data;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;

/**
 * @author James
 * Handles giving perks, requirements's etc
 * Types:
 * 1 - Unlock perk
 * 2 - potion effect perk
 * 
 */ 
public class PerkObject {
	public String name;
	public ArrayList requirement = new ArrayList();
	public String perkSlug;
	public Block unlockBlock;
	public Item unlockItem;
	public Entity unlockEntity;
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
	public PerkObject (String name, Block unlockBlock, String description, int pointCost, ArrayList requirement){
		this.perkSlug = getSlug(name);
		this.name = name;
		this.unlockBlock = unlockBlock;
		this.requirement = requirement;
		this.pointCost = pointCost;
		this.description = description;
	}
	public PerkObject (String name, Item unlockItem, String description, int pointCost, ArrayList requirement){
		this.perkSlug = getSlug(name);
		this.name = name;
		this.unlockItem = unlockItem;
		this.requirement = requirement;
		this.pointCost = pointCost;
		this.description = description;
	}
	public PerkObject (String name, Entity unlockEntity, String description, int pointCost, ArrayList requirement){
		this.perkSlug = getSlug(name);
		this.name = name;
		this.unlockEntity = unlockEntity;
		this.requirement = requirement;
		this.pointCost = pointCost;
		this.description = description;
	}

}
