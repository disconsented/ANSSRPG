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
		super();		
		exp.add(new ItemXP());
		exp.add(new ItemXP());
	}

	@Override
	public void touchUp() {
		for (int i = 0; i < exp.size(); i++){
			ItemXP thing = (ItemXP) exp.get(i);
			thing.touchUp();
		}
		
	}

	public ArrayList<ItemXP> getExp() {
		return exp;
	}
}