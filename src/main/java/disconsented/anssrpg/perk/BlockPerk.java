/**
 * 
 */
package disconsented.anssrpg.perk;

import java.util.HashMap;

import net.minecraft.block.Block;

/**
 * @author Disconsented
 *
 */
public class BlockPerk extends Perk {
	Block block;
	
	@Override
	public void construct(HashMap map) {
		this.setCore(map);
		this.setBlock((Block) map.get("block"));
	}

	/**
	 * @return 
	 */
	public Block getBlock() {		
		return block;
	}

	/**
	 * @param 
	 */
	protected void setBlock(Block block) {
		this.block = block;
	}

}
