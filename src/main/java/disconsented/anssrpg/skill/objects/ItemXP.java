/**
 * 
 */
package disconsented.anssrpg.skill.objects;

import com.google.gson.annotations.Expose;

import net.minecraft.item.Item;

/**
 * @author Disconsented
 *
 */
public class ItemXP extends XPGain {

	/**
	 * 
	 */
	private Item item;
	
	public ItemXP() {
	}

	@Override
	public void touchUp() {
		item = (Item) Item.itemRegistry.getObject(name);

	}
	public Item getItem(){
		return item;
	}

}
