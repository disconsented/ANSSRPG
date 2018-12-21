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
package com.disconsented.anssrpg.server.data;

import com.disconsented.anssrpg.server.skill.objects.BlockSkill;
import com.disconsented.anssrpg.server.skill.objects.EntitySkill;
import com.disconsented.anssrpg.server.skill.objects.ItemSkill;
import com.disconsented.anssrpg.server.skill.objects.Skill;

import java.util.ArrayList;


/**
 * @author Disconsented
 *         Stores skill data
 */
public class SkillStore {
    private static SkillStore instance;
    private static ArrayList<BlockSkill> blockSkill = new ArrayList<>();
    private static ArrayList<EntitySkill> entitySkill = new ArrayList<>();
    private static ArrayList<ItemSkill> itemSkill = new ArrayList<>();
    private static ArrayList<Skill> skills = new ArrayList<>();

    protected SkillStore() {/* Exists only to defeat instantiation.*/}

    public static void addSkill(BlockSkill skill) {
        SkillStore.blockSkill.add(skill);
        SkillStore.skills.add(skill);
    }

    public static void addSkill(EntitySkill entity) {
        SkillStore.entitySkill.add(entity);
        SkillStore.skills.add(entity);
    }

    public static void addSkill(ItemSkill item) {
        SkillStore.itemSkill.add(item);
        SkillStore.skills.add(item);

    }

    public static void Clear() {
        SkillStore.blockSkill = new ArrayList<>();
        SkillStore.entitySkill = new ArrayList<>();
        SkillStore.itemSkill = new ArrayList<>();
        SkillStore.skills = new ArrayList<>();
    }

    public static ArrayList<BlockSkill> getBlockSkill() {
        return SkillStore.blockSkill;
    }

    public static ArrayList<EntitySkill> getEntitySkill() {
        return SkillStore.entitySkill;
    }

    public static SkillStore getInstance() {
        if (SkillStore.instance == null) {
            SkillStore.instance = new SkillStore();
        }
        return SkillStore.instance;
    }

    public static ArrayList<ItemSkill> getItemSkill() {
        return SkillStore.itemSkill;
    }

    public static ArrayList<Skill> getSkills() {
        return SkillStore.skills;
    }

}
