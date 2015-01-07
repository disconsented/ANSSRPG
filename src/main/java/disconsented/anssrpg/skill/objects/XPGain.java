/**
 * 
 */
package disconsented.anssrpg.skill.objects;

import com.google.gson.annotations.Expose;

/**
 * @author Disconsented
 *	
 */
public abstract class XPGain {
	
	public XPGain(){		
	}

	/**
	 * Super type abstract class
	 */
	@Expose
	protected int xp = 0;
	
	@Expose
	protected String name = "default_name";

	public int getXp() {
		return xp;
	}

	/**
	 * @param xp the xp to set
	 */
	public void setXp(int xp) {
		this.xp = xp;
	}
	/**
	 * 
	 */
	public abstract void touchUp();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
