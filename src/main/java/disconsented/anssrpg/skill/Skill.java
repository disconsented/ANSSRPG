package disconsented.anssrpg.skill;
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

public class Skill {
	
	public String name;
	
	public int type; //Handles the skill type block breaking[1](onBreakEvent), Entity Damaging[2](), Crafting[3](ItemCraftedEvent),  
	public ArrayList exp = new ArrayList();
	public ArrayList entryName = new ArrayList();
	/**
	 *  The position of the entries of the ArrayList's matter
	 * @param exp - ArrayList for the exp
	 * @param req - Deprecated
	 * @param name - Name of the skill
	 * @param entryName - Name of the entry (
	 * @param type - Skill Type
	 */
	public Skill (ArrayList exp, String name, ArrayList entryName, int type) {
		this.exp = exp;
		this.name = name;
		this.entryName = entryName;
		this.type = type;
	}
	

}