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
public class ItemSkill extends Skill {
	
	@Expose
	private ArrayList<ItemXP> exp = new ArrayList<ItemXP>();
	
	public ItemSkill(){	
		exp.add(new ItemXP());
		exp.add(new ItemXP());
	}

	@Override
	public void touchUp() {
		for (XPGain xp : exp){
			ItemXP thing = new ItemXP();
			thing.name = xp.name;
			thing.xp = xp.xp;
			thing.touchUp();
		}
		
	}

	@Override
	public ArrayList<ItemXP> getExp() {
		return exp;
	}

	@Override
	public void setExp(ArrayList exp) {
		this.exp = exp;
		
	}

}