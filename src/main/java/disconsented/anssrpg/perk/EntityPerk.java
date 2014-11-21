/**
 * 
 */
package disconsented.anssrpg.perk;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.annotations.Expose;

import net.minecraft.entity.Entity;

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
	Entity entity;
	@Expose
	String entityName;
	/**
	 * @return the entity
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	protected void setEntity(Entity entity) {
		this.entity = entity;
	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub
		
	}

}
