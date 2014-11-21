/**
 * 
 */
package disconsented.anssrpg.perk;

/**
 * @author Disconsented
 * Object for requirements
 */
public class Requirement {
	/*
	 * HAVE - Has a perk
	 * DONT - Doesn't have a perk
	 * LEVEL_EQUALS - Level == x
	 * LEVEL_GREATER - Level > x 
	 * LEVEL_LESS - Level < x
	 */
	
	public enum Action {HAVE,DONT,LEVEL_EQUALS,LEVEL_GREATER,LEVEL_LESS}
	public Action action;
	public String name;
	public String extraData;
	public Requirement(Action action, String name, String extraData)
	{
		this.action = action;
		this.name = name;
		this.extraData = extraData;
	}
}
