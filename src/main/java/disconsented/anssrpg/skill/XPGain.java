/**
 * 
 */
package disconsented.anssrpg.skill;

/**
 * @author Disconsented
 *	
 */
public class XPGain {

	/**
	 * 
	 */
	public String name;
	public int xp;
	
	public XPGain(String name, int xp)
	{
		this.name = name;
		this.xp = xp;
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

	/**
	 * @return the xp
	 */
	public int getXp() {
		return xp;
	}

	/**
	 * @param xp the xp to set
	 */
	public void setXp(int xp) {
		this.xp = xp;
	}

}
