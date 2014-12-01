/**
 * 
 */
package disconsented.anssrpg.skill.objects;

import com.google.gson.annotations.Expose;

import net.minecraft.entity.EntityList;

/**
 * @author Disconsented
 *
 */
public class EntityXP extends XPGain {

	/**
	 * 
	 */
	private Class entity;
	
	public EntityXP() {
	}

	@Override
	public void touchUp() {
		entity = (Class) EntityList.stringToClassMapping.get(name);

	}
	public Class getEntity(){
		return entity;
	}
}
