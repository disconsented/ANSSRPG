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
