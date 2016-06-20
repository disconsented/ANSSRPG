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
package disconsented.anssrpg.config;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

import disconsented.anssrpg.common.Logging;
import disconsented.anssrpg.data.SkillStore;
import disconsented.anssrpg.skill.objects.BlockSkill;
import disconsented.anssrpg.skill.objects.EntitySkill;
import disconsented.anssrpg.skill.objects.ItemSkill;
import disconsented.anssrpg.skill.objects.Skill;
/**
 * Used to safely retain data for saving and loading skills before initalising them for use
 * @author Disconsented 
 */
public class SkillContainer {

    @Expose
    private ArrayList<BlockSkill> blocks = new ArrayList<>();
    @Expose
    private final ArrayList<EntitySkill> entites = new ArrayList<>();
    @Expose
    private final ArrayList<ItemSkill> items = new ArrayList<>();


    public void addBlockSkill(BlockSkill blockSkill) {
        this.blocks.add(blockSkill);
    }


    public void addEntitySkill(EntitySkill entitySkill) {
        this.entites.add(entitySkill);
    }


    public void addItemSkill(ItemSkill itemSkill) {
        this.items.add(itemSkill);
    }

    public ArrayList<BlockSkill> getBlock() {
        return this.blocks;
    }

    public void setBlock(ArrayList<BlockSkill> block) {
        this.blocks = block;
    }

    public void touchUp() {
        ArrayList<Skill> skills = new ArrayList<>();
        skills.addAll(this.blocks);
        skills.addAll(this.entites);
        skills.addAll(this.items);
        
        for(Skill skillCurrent : skills){
            for(Skill skillName : skills){
                if (skillCurrent.name.equals(skillName.name)){
                    if (skillCurrent.base == skillName.base && skillCurrent.mod == skillName.mod){
                        Logging.debug(skillCurrent.name + " has been detected as a compound skill");
                    } else {
                        Logging.error(skillCurrent.name + " has been detected as a compound skill but does not match other definations; Removing");
                        this.matchAndRemove(skillCurrent);
                    }
                }
            }
        }        
        for (BlockSkill block : this.blocks) {
            block.touchUp();
            SkillStore.addSkill(block);
        }
        for (EntitySkill entity : this.entites) {
            entity.touchUp();
            SkillStore.addSkill(entity);
        }
        for (ItemSkill item : this.items) {
            item.touchUp();
            SkillStore.addSkill(item);
        }

    }
    
    private void matchAndRemove(Skill skill){
        for (BlockSkill block : this.blocks) {
            this.blocks.remove(skill);
            if(block.name == skill.name){
                this.blocks.remove(block);
            }
        }
        for (EntitySkill entity : this.entites) {
            this.entites.remove(skill);
            if(entity.name == skill.name){
                this.entites.remove(entity);
            }
        }
        for (ItemSkill item : this.items) {
            this.items.remove(skill);
            if(item.name == skill.name){
                this.items.remove(item);
            }
        }
    }
}
