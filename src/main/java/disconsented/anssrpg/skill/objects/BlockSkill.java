/**
 * 
 */
package disconsented.anssrpg.skill.objects;

import java.util.ArrayList;

/**
 * @author Disconsented
 *
 */
public class BlockSkill extends Skill {

	/**
	 * @param exp
	 * @param name
	 */	
	public BlockSkill(ArrayList<XPGain> exp, String name) {
		super(exp, name);
		// TODO Auto-generated constructor stub
	}
	public BlockSkill(){
		super();
		this.exp = new ArrayList<BlockXP>();
		exp.add(new BlockXP());
		exp.add(new BlockXP());
	}
}