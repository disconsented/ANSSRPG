/**
 * 
 */
package disconsented.anssrpg.perk;

import java.util.HashMap;

import net.minecraft.item.Item;

/**
 * @author Disconsented
 *
 */
public class ItemPerk extends Perk {
	Item item;
	
	@Override
	public void construct(HashMap map) {
		this.setCore(map);
		this.setItem(item);
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	protected void setItem(Item item) {
		this.item = item;
	}

}
