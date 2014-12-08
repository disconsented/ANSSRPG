/**
 *
 */
package disconsented.anssrpg.perk;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

import net.minecraft.block.Block;

/**
 * @author Disconsented
 *
 */
public class BlockPerk extends Perk {

	public BlockPerk() { super(); }

	public BlockPerk(String name, ArrayList<Requirement> requirements,
			String description, int pointCost) {
		super(name, requirements, description, pointCost);
		// TODO Auto-generated constructor stub
	}
	Block block;

	@Expose
	String blockName = "default_blockName";
	/**
	 * @return
	 */
	public Block getBlock() { return block; }

	/**
	 * @param
	 */
	protected void setBlock(Block block) { this.block = block; }

	@Override
	public void touchUp() {	
		this.block = (Block) Block.blockRegistry.getObject(blockName); 
		this.setSlug();
	}
}
