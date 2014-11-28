/**
 * 
 */
package disconsented.anssrpg.skill.objects;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

/**
 * @author Disconsented
 *
 */
public class BlockSkill extends Skill {
	@Expose
	private ArrayList<BlockXP> exp = new ArrayList<BlockXP>();

	public BlockSkill(){
		super();		
		exp.add(new BlockXP());
		exp.add(new BlockXP());
	}
	public void touchUp() {
		for (int i = 0; i < exp.size(); i++){
			BlockXP thing = (BlockXP) exp.get(i);
			thing.touchUp();
		}
	}
	public ArrayList<BlockXP> getExp() {
		return exp;
	}
}