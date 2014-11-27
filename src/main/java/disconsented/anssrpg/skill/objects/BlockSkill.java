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
	public BlockSkill(ArrayList<BlockXP> exp, String name) {
		super(exp, name);
		// TODO Auto-generated constructor stub
	}
	public BlockSkill(){
		super();
		ArrayList<BlockXP> blockXPs = new ArrayList<>();
		blockXPs.add(new BlockXP());
		blockXPs.add(new BlockXP());
		this.exp = blockXPs;
	}
	public void touchUp() {
		for (int i = 0; i < exp.size(); i++){
			BlockXP thing = (BlockXP) exp.get(i);
			thing.touchUp();
		}
	}
}