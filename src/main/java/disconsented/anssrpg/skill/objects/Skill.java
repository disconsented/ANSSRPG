package disconsented.anssrpg.skill.objects;
/**
 * @author James
 * Handles storing the information for each skill
 */

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

public abstract class Skill {
	@Expose
	public String name = "default_skillname";
	/**  
	 * @param exp - ArrayList for the exp using XPGain objects
	 * @param name - Name of the skill
	 */
	
	public Skill(){
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}	
	public abstract void touchUp();
	public abstract ArrayList getExp();
	public abstract void setExp(ArrayList exp);
}