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
	@Expose
	public ArrayList exp;
	/**  
	 * @param exp - ArrayList for the exp using XPGain objects
	 * @param name - Name of the skill
	 */
	public Skill (ArrayList<XPGain> exp, String name) {
		this.exp = exp;
		this.name = name;
	}
	public Skill(){}
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
	/**
	 * @return the exp
	 */
	public ArrayList<XPGain> getExp() {
		return exp;
	}
	/**
	 * @param exp the exp to set
	 */
	public void setExp(ArrayList<XPGain> exp) {
		this.exp = exp;
	}	
}