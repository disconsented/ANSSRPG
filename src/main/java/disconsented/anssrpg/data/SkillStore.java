/**
 * 
 */
package disconsented.anssrpg.data;

import java.util.ArrayList;

import disconsented.anssrpg.skill.objects.BlockSkill;
import disconsented.anssrpg.skill.objects.EntitySkill;
import disconsented.anssrpg.skill.objects.ItemSkill;
import disconsented.anssrpg.skill.objects.Skill;


/**
 * @author Disconsented
 * Stores skill data
 */
public class SkillStore {
	private static SkillStore instance = null;
	private static ArrayList <BlockSkill> blockSkill = new ArrayList<BlockSkill>();
	private static ArrayList <EntitySkill> entitySkill = new ArrayList<EntitySkill>();
	private static ArrayList <ItemSkill> itemSkill = new ArrayList <ItemSkill>();
	private static ArrayList <Skill> skills = new ArrayList <Skill>();
	protected SkillStore() {/* Exists only to defeat instantiation.*/}
	public static SkillStore getInstance() {
		if(instance == null) {
			instance = new SkillStore();
		}
		return instance;
	}
	public static ArrayList<BlockSkill> getBlockSkill() {
		return blockSkill;
	}
	public static ArrayList<EntitySkill> getEntitySkill() {
		return entitySkill;
	}
	public static ArrayList<ItemSkill> getItemSkill() {
		return itemSkill;
	}
	public static void addSkill(BlockSkill skill) {		
		blockSkill.add(skill);
		skills.add(skill);
	}
	public static void addSkill(EntitySkill entity) {
		entitySkill.add(entity);
		skills.add(entity);
	}
	public static void addSkill(ItemSkill item) {
		itemSkill.add(item);
		skills.add(item);
		
	}
	public static void Clear() {
		blockSkill = new ArrayList<BlockSkill>();
		entitySkill = new ArrayList<EntitySkill>();
		itemSkill = new ArrayList <ItemSkill>();
		skills = new ArrayList <Skill>();
	}
	public static ArrayList<Skill> getSkills() {
		return skills;
	}

}
