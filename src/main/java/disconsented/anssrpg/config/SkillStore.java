/**
 * 
 */
package disconsented.anssrpg.config;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

import disconsented.anssrpg.skill.objects.BlockSkill;

/**
 * @author Disconsented
 *
 */
public class SkillStore {

	/**
	 * 
	 */
	@Expose
	private ArrayList<BlockSkill> blocks = new ArrayList<>();

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

	public void addBlockSkill(BlockSkill blockSkill) { this.blocks.add(blockSkill); }

	public void touchUp() {
		for (BlockSkill block : this.blocks){
			block.touchUp();
			disconsented.anssrpg.data.SkillStore.addSkill(block);
		}
		
	}
	

}
