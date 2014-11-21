/**
 * 
 */
package disconsented.anssrpg.config;

import java.util.ArrayList;

import disconsented.anssrpg.perk.Requirement;

/**
 * @author Disconsented
 * Used soley for creating Json objects
 */
public class DummyPerk {
	public String name;
	public String unlock;
	public ArrayList<Requirement> requirements;
	public int pointCost;
	public String description;
	private static DummyPerk instance;
	/**
	 * 
	 * @param name - The name of the perk
	 * @param unlock - What the perk unlocks
	 * @param description - The description of the perk (useful for adding lore)
	 * @param pointCost - The point cost to obtain the perk
	 * @param requirements - Magical objects of awesomeness
	 */
	private DummyPerk (String name, String unlock, String description, int pointCost, ArrayList <Requirement>requirements){
		this.name = name;
		this.unlock = unlock;
		this.requirements = requirements;
		this.pointCost = pointCost;
		this.description = description;
	}
	public static DummyPerk getInstance()
	{
		if (instance == null)
		{
			instance = new DummyPerk("Example Name","dirt","this is an example",90001,
					new ArrayList<Requirement>());
			instance.requirements.add(new Requirement(Requirement.Action.LEVEL_GREATER,"mining","6"));
			instance.requirements.add(new Requirement(Requirement.Action.LEVEL_GREATER,"mining","6"));
		}
		return instance;
	}
}
