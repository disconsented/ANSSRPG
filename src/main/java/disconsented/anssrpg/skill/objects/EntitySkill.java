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
public class EntitySkill extends Skill {
	@Expose
	private ArrayList<EntityXP> exp = new ArrayList<EntityXP>();
	
	public EntitySkill(){
		exp.add(new EntityXP());
		exp.add(new EntityXP());
	}

	@Override
	public void touchUp() {
		for (XPGain xp : exp){
			EntityXP thing = new EntityXP();
			thing.name = xp.name;
			thing.xp = xp.xp;
			thing.touchUp();
		}
	}

	@Override
	public ArrayList<EntityXP> getExp() {
		return exp;
	}

	@Override
	public void setExp(ArrayList exp) {
		this.exp = exp;
		
	}
}