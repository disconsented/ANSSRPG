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
	ArrayList<BlockXP> exp = new ArrayList<BlockXP>();
	public BlockSkill(){
		exp.add(new BlockXP());
		exp.add(new BlockXP());
	}
	public void touchUp() {
		for (XPGain xp : exp){
			BlockXP thing = new BlockXP();
			thing.name = xp.name;
			thing.xp = xp.xp;
			thing.touchUp();
		}
	}
	@Override
	public ArrayList<BlockXP> getExp() {
		return exp;
	}
	@Override
	public void setExp(ArrayList exp) {
		this.exp = exp;		
	}
}