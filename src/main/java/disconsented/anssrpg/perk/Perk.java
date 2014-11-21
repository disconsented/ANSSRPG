/**
 * Author: Disconsented
 * Supertpye for perks
 */
package disconsented.anssrpg.perk;

import java.util.ArrayList;
import java.util.HashMap;

import scala.reflect.internal.Trees.This;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;

public abstract class Perk {
	
	public String name;
	public ArrayList<Requirement> requirements = new ArrayList<Requirement>();
	public String perkSlug;
	public String description;
	public int pointCost;
	
	private String getSlug(String name){		
		return name.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
	}
	
/**
 * 
 * @param name - Name of the perk
 * @param description - Description
 * @param pointCost - Cost in points to unlock
 * @param requirement - Requirment object
 */
	public Perk (){}
	public abstract void construct(HashMap map);
	protected void setCore(HashMap map)
	{
		this.setName(map.get("name").toString());
		this.setRequirements((ArrayList<Requirement>) map.get("requirements"));
		this.setDescription(map.get("description").toString());
		this.setPointCost((int) map.get("pointcost"));
	}
	protected void setName(String name){this.name = name; this.perkSlug = getSlug(name);} 
	protected void setRequirements(ArrayList<Requirement> requirments){this.requirements = requirments;}
	protected void setDescription(String description){this.description = description;}
	protected void setPointCost(int pointCost){this.pointCost = pointCost;}
	public String getName(){return name;}
	public ArrayList getRequirements(){return requirements;}
	public String getDescription(){return description;}
	public int getPointCost(){return pointCost;}
	public String getSlug(){return perkSlug;}

}
