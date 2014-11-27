/**
 * 
 */
package disconsented.anssrpg.skill.objects;

import com.google.gson.annotations.Expose;

import net.minecraft.block.Block;

/**
 * @author Disconsented
 *
 */
public class BlockXP extends XPGain {

	/**
	 * 
	 */
	@Expose
	private int exp = 0;
	private Block block;
	@Expose
	private String name = "default_name";
	
	public BlockXP() {
	}

	@Override
	public void touchUp() {
		block = (Block) Block.blockRegistry.getObject(name);

	}

	public Block getBlock() {
		return block;
	}

}
