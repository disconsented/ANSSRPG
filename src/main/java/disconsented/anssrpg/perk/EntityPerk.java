/**
 * 
 */
package disconsented.anssrpg.perk;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.annotations.Expose;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.world.World;

/**
 * @author Disconsented
 *
 */
public class EntityPerk extends Perk {

	public EntityPerk(String name, ArrayList<Requirement> requirements,
			String description, int pointCost) {
		super(name, requirements, description, pointCost);
		// TODO Auto-generated constructor stub
	}
	public EntityPerk() {
		super();
	}
	Class entity;
	@Expose
	String entityName = "default_entityName";
	/**
	 * @return the entity
	 */
	public Object getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	protected void setEntity(Class entity) {
		this.entity = entity;
	}

	@Override
	public void touchUp() {
			this.entity = EntityList.stringToClassMapping.get(entityName).getClass();
	}

}
