/**
 * 
 */
package disconsented.anssrpg.skill.objects;

import java.util.ArrayList;

/**
 * @author Disconsented
 *
 */
public class EntitySkill extends Skill {

	/**
	 * @param exp
	 * @param name
	 */	
	public EntitySkill(ArrayList<XPGain> exp, String name) {
		super(exp, name);
		// TODO Auto-generated constructor stub
	}
	public EntitySkill(){
		super();
		ArrayList<EntityXP> entityXPs = new ArrayList<>();
		entityXPs.add(new EntityXP());
		entityXPs.add(new EntityXP());
		this.exp = entityXPs;
	}
}