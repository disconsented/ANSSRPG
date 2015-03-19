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

import com.google.gson.annotations.Expose;
import disconsented.anssrpg.skill.objects.BlockSkill;
import disconsented.anssrpg.skill.objects.EntitySkill;
import disconsented.anssrpg.skill.objects.ItemSkill;

import java.util.ArrayList;
/**
 * Used to safely retain data for saving and loading skills before initalising them for use
 * @author Disconsented 
 */
public class SkillContainer {

    @Expose
    private ArrayList<BlockSkill> blocks = new ArrayList<BlockSkill>();
    @Expose
    private ArrayList<EntitySkill> entites = new ArrayList<EntitySkill>();
    @Expose
    private ArrayList<ItemSkill> items = new ArrayList<ItemSkill>();


    public void addBlockSkill(BlockSkill blockSkill) {
        blocks.add(blockSkill);
    }


    public void addEntitySkill(EntitySkill entitySkill) {
        entites.add(entitySkill);
    }


    public void addItemSkill(ItemSkill itemSkill) {
        items.add(itemSkill);
    }

    public ArrayList<BlockSkill> getBlock() {
        return blocks;
    }

    public void setBlock(ArrayList<BlockSkill> block) {
        blocks = block;
    }

    public void touchUp() {
        for (BlockSkill block : blocks) {
            block.touchUp();
            disconsented.anssrpg.data.SkillStore.addSkill(block);
        }
        for (EntitySkill entity : entites) {
            entity.touchUp();
            disconsented.anssrpg.data.SkillStore.addSkill(entity);
        }
        for (ItemSkill item : items) {
            item.touchUp();
            disconsented.anssrpg.data.SkillStore.addSkill(item);
        }

    }
}
