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
package disconsented.anssrpg.config;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

import disconsented.anssrpg.skill.objects.BlockSkill;
import disconsented.anssrpg.skill.objects.EntitySkill;
import disconsented.anssrpg.skill.objects.ItemSkill;

/**
 * @author Disconsented
 *
 */
public class SkillStore {

	/**
	 * 
	 */
	@Expose
	private ArrayList<BlockSkill> blocks = new ArrayList<BlockSkill>();
	@Expose
	private ArrayList<EntitySkill> entites = new ArrayList<EntitySkill>();
	@Expose
	private ArrayList<ItemSkill> items = new ArrayList<ItemSkill>();

	/**
	 * @return the block
	 */
	public ArrayList<BlockSkill> getBlock() {
		return this.blocks;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(ArrayList<BlockSkill> block) {
		this.blocks = block;
	}
	

	public void touchUp() {
		for (BlockSkill block : this.blocks){
			block.touchUp();
			disconsented.anssrpg.data.SkillStore.addSkill(block);
		}
		for (EntitySkill entity : this.entites){
			entity.touchUp();
			disconsented.anssrpg.data.SkillStore.addSkill(entity);
		}
		for (ItemSkill item : this.items){
			item.touchUp();
			disconsented.anssrpg.data.SkillStore.addSkill(item);
		}
	
	}

	public void addBlockSkill(BlockSkill blockSkill) { this.blocks.add(blockSkill); }
	
	public void addEntitySkill(EntitySkill entitySkill) { this.entites.add(entitySkill); }

	public void addItemSkill(ItemSkill itemSkill) { this.items.add(itemSkill); }
}
