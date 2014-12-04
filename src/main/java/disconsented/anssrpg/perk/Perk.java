/**
 * Author: Disconsented
 * Supertpye for perks
 */
package disconsented.anssrpg.perk;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

public abstract class Perk {
	
	@Expose
	public String name = "default_name";
	@Expose
	public ArrayList<Requirement> requirements = new ArrayList<Requirement>();	
	public String perkSlug;//Not exposed as its made based on the name
	@Expose
	public String description = "default_description";
	@Expose
	public int pointCost = 0;
	
	private String getSlug(String name){		
		return name.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
	}

	public Perk(){
		this.requirements.add(new Requirement(Requirement.Action.HAVE, "skill_name", "6"));
		this.requirements.add(new Requirement(Requirement.Action.HAVE, "skill_name", "6"));
		this.requirements.add(new Requirement(Requirement.Action.HAVE, "skill_name", "6"));
	}
	/**
	 *
	 * @param name - Name of the perk
	 * @param description - Description
	 * @param pointCost - Cost in points to unlock
	 * @param requirement - Requirment object
	 */
	public Perk (String name, ArrayList<Requirement> requirements, String description, int pointCost){
		this.name = name;
		this.requirements = requirements;
		this.description = description;
		this.pointCost = pointCost;
	}

	public abstract void touchUp(); //For converting strings into object references after deserialisation
	protected void setName(String name){this.name = name; this.perkSlug = getSlug(name);} 
	protected void setRequirements(ArrayList<Requirement> requirments){this.requirements = requirments;}
	protected void setDescription(String description){this.description = description;}
	protected void setPointCost(int pointCost){this.pointCost = pointCost;}
	protected void setSlug(){perkSlug = name.toLowerCase().replaceAll("[^A-Za-z0-9]", "");}
	public String getName(){return name;}
	public ArrayList getRequirements(){return requirements;}
	public String getDescription(){return description;}
	public int getPointCost(){return pointCost;}
	public String getSlug(){return perkSlug;}
	@Override
	public String toString() {		
		return this.name+"|"+this.description+"|"+this.pointCost+"|"+this.requirements.toString();
	}

}
