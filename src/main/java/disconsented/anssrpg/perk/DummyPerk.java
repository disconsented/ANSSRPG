/**
 * 
 */
package disconsented.anssrpg.perk;

import java.util.ArrayList;

import net.minecraft.entity.Entity;

/**
 * @author j
 *
 */
public class DummyPerk {
	String type;
	String name;
	String unlock;
	ArrayList requirementName;
	ArrayList requirementLevel;
	int pointCost;
	String description;
	/**
	 * 
	 * @param type - The type of Perk being created ("Block", "Item", or "Entity") 
	 * @param name - The name of the perk
	 * @param unlock - What the perk unlocks
	 * @param description - The description of the perk (useful for adding lore)
	 * @param pointCost - The point cost to obtain the perk
	 * @param requirementName - The name of the associated skill
	 * @param requirementLevel - The level required of the associated skill
	 * N.B. requirementName and requirementLevel's entry positions do matter (index 0 on one is paired with index 0 on the other)
	 */
	public DummyPerk (String type, String name, String unlock, String description, int pointCost, ArrayList requirementName, ArrayList requirementLevel){
		this.type = type;
		this.name = name;
		this.unlock = unlock;
		this.requirementName = requirementName;
		this.requirementLevel = requirementLevel;
		this.pointCost = pointCost;
		this.description = description;
	}

}
