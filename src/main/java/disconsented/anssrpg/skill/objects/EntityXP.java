/**
 * 
 */
package disconsented.anssrpg.skill.objects;

import net.minecraft.entity.EntityList;

/**
 * @author Disconsented
 *
 */
public class EntityXP extends XPGain {

	/**
	 * 
	 */
	private int exp = 0;
	private Object entity;
	private String name = "default_name";
	
	public EntityXP() {
	}

	@Override
	public void touchUp() {
		entity = EntityList.stringToClassMapping.get(name);

	}

}
