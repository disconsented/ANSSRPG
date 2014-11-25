/**
 * 
 */
package handler;

import java.util.ArrayList;

import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.data.PerkStore;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;

/**
 * @author Disconsented
 *
 */
public class SkillHandler {
	private static PerkStore instance = PerkStore.getInstance();
	/**
	 * Check that a block requires a perk
	 */
	public boolean getRequiresPerk(Block block){
		return false;
	}
	
	/**
	 * Check that a item requires a perk
	 */
	public boolean getRequiresPerk(Item item){
		return false;
	}
	/**
	 * Check that a entity requires a perk
	 */
	public boolean getRequiresPerk(Class entity){
		return false;
	}
}
