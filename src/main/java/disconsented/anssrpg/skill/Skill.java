package disconsented.anssrpg.skill;
/**
 * @author James
 * Handles storing the information for each skill
 */

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;

public abstract class Skill {
	
	public String name;
	public ArrayList<XPGain> exp = new ArrayList<XPGain>();
	/**  
	 * @param exp - ArrayList for the exp using XPGain objects
	 * @param name - Name of the skill
	 */
	public Skill (ArrayList exp, String name) {
		this.exp = exp;
		this.name = name;
	}
	

}