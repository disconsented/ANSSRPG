/**
 * 
 */
package disconsented.anssrpg.data;

import java.util.ArrayList;

import disconsented.anssrpg.skill.objects.BlockSkill;
import disconsented.anssrpg.skill.objects.EntitySkill;
import disconsented.anssrpg.skill.objects.ItemSkill;


/**
 * @author Disconsented
 * Stores skill data
 */
public class SkillStore {
	private static SkillStore instance = null;
	private static ArrayList <BlockSkill> blockSkill = new ArrayList<BlockSkill>();
	private static ArrayList <EntitySkill> entitySkill = new ArrayList<EntitySkill>();
	private static ArrayList <ItemSkill> itemSkill = new ArrayList <ItemSkill>();
	protected SkillStore() {/* Exists only to defeat instantiation.*/}
	public static SkillStore getInstance() {
		if(instance == null) {
			instance = new SkillStore();
		}
		return instance;
	}
	public ArrayList<BlockSkill> getBlockSkill() {
		return blockSkill;
	}
	public ArrayList<EntitySkill> getEntitySkill() {
		return entitySkill;
	}
	public ArrayList<ItemSkill> getItemSkill() {
		return itemSkill;
	}
	public static void addSkill(BlockSkill skill) {		
		blockSkill.add(skill);
	}

}
