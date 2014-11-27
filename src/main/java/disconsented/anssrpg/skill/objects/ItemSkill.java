/**
 * 
 */
package disconsented.anssrpg.skill.objects;

import java.util.ArrayList;

/**
 * @author Disconsented
 *
 */
public class ItemSkill extends Skill {

	/**
	 * @param exp
	 * @param name
	 */	
	public ItemSkill(ArrayList<XPGain> exp, String name) {
		super(exp, name);
		// TODO Auto-generated constructor stub
	}
	public ItemSkill(){
		super();
		ArrayList<ItemXP> itemXPs = new ArrayList<>();
		itemXPs.add(new ItemXP());
		itemXPs.add(new ItemXP());
		this.exp = itemXPs;
	}
}