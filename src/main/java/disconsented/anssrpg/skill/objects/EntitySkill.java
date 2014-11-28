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
	private ArrayList<EntityXP> exp = new ArrayList<>();
	
	public EntitySkill(){
		super();
		
		exp.add(new EntityXP());
		exp.add(new EntityXP());
	}

	@Override
	public void touchUp() {
		for (int i = 0; i < exp.size(); i++){
			EntityXP thing = (EntityXP) exp.get(i);
			thing.touchUp();
		}
	}

	public ArrayList<EntityXP> getExp() {
		return exp;
	}
}