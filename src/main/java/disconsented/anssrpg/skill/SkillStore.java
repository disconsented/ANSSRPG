/**
 * 
 */
package disconsented.anssrpg.skill;


/**
 * @author Disconsented
 * Stores skill data
 */
public class SkillStore {
	private static SkillStore instance = null;
	protected SkillStore() {/* Exists only to defeat instantiation.*/}
	public static SkillStore getInstance() {
		if(instance == null) {
			instance = new SkillStore();
		}
		return instance;
	}

}
