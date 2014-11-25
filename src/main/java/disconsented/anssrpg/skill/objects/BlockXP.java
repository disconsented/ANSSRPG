/**
 * 
 */
package disconsented.anssrpg.skill.objects;

import net.minecraft.block.Block;

/**
 * @author Disconsented
 *
 */
public class BlockXP extends XPGain {

	/**
	 * 
	 */
	private int exp = 0;
	private Block block;
	private String name = "default_name";
	
	public BlockXP() {
	}

	@Override
	public void touchUp() {
		block = (Block) Block.blockRegistry.getObject(name);

	}

}
