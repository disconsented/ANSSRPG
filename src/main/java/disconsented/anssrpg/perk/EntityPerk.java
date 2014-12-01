/**
 * 
 */
package disconsented.anssrpg.perk;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

import net.minecraft.entity.EntityList;

/**
 * @author Disconsented
 *
 */
public class EntityPerk extends Perk {

	public EntityPerk() {
		super();
	}

	public EntityPerk(String name, ArrayList<Requirement> requirements,
			String description, int pointCost) {
		super(name, requirements, description, pointCost);
		// TODO Auto-generated constructor stub
	}
	Class entity;
	@Expose
	String entityName = "default_entityName";
	/**
	 * @return the entity
	 */
	public Class getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	protected void setEntity(Class entity) {
		this.entity = entity;
	}

	@Override
	public void touchUp () {
			this.entity = (Class) EntityList.stringToClassMapping.get(entityName);
			if (entity == null){
				throw new NullPointerException();
			}
	}

}
