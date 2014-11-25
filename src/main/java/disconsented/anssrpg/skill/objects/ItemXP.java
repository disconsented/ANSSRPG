/**
 * 
 */
package disconsented.anssrpg.skill.objects;

import net.minecraft.item.Item;

/**
 * @author Disconsented
 *
 */
public class ItemXP extends XPGain {

	/**
	 * 
	 */
	private int exp = 0;
	private Item item;
	private String name = "default_name";
	
	public ItemXP() {
	}

	@Override
	public void touchUp() {
		item = (Item) Item.itemRegistry.getObject(name);

	}

}
