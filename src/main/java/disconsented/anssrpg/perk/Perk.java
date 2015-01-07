/**
/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
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
