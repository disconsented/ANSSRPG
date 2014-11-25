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
	private ArrayList<BlockSkill> block = new ArrayList<BlockSkill>();

	/**
	 * @return the block
	 */
	public ArrayList<BlockSkill> getBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(ArrayList<BlockSkill> block) {
		this.block = block;
	}
	

}
