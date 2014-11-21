/**
 * 
 */
package disconsented.anssrpg.perk;

import java.util.HashMap;

import net.minecraft.entity.Entity;

/**
 * @author Disconsented
 *
 */
public class EntityPerk extends Perk {
	Entity entity;
	
	@Override
	public void construct(HashMap map) {
		this.setCore(map);
		this.setEntity((Entity) map.get("entity"));
	}

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

}
