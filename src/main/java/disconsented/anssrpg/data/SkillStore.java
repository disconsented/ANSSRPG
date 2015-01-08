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
