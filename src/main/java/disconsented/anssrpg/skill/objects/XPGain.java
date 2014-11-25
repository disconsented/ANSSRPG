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

	/**
	 * Super type abstract class
	 */
	@Expose
	private int xp = 0;
	
	@Expose
	private String name = "default_name";

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

}
