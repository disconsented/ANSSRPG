package disconsented.anssrpg.data;
/**
 * @author James
 * Handles storing the information for each skill
 * Types:
 * 1 - Block breaking (OnBreakEvent)
 * 2 - Entity killing (onEntityDeath), not blocked
 * 3 - Crafting (onPlayerOpenContainer and onItemCraftedEvent) for blocking and exp respectivley 
 */

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;

public class SkillObject {
	
	public String name;
	
	public int type; //Handles the skill type block breaking[1](onBreakEvent), Entity Damaging[2](), Crafting[3](ItemCraftedEvent),  
	public ArrayList exp = new ArrayList();
	public ArrayList entryName = new ArrayList();
	/**
	 * 
	 * @param exp
	 * @param req
	 * @param name 
	 * @param itemName
	 * @param type
	 */
	public SkillObject (ArrayList exp, String name, ArrayList entryName, int type) {
		this.exp = exp;
		this.name = name;
		this.entryName = entryName;
		this.type = type;
	}
	

}