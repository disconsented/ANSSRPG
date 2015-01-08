/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr
Copyright (c) 2015 Abelistah

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package disconsented.anssrpg.config;
/**
 * Author: Abelistah
 */
import java.util.*;

import com.google.gson.annotations.Expose;

import disconsented.anssrpg.data.DataSave;
import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.perk.EntityPerk;
import disconsented.anssrpg.perk.ItemPerk;

public class PerkStore {
    @Expose
    private ArrayList<ItemPerk> items = new ArrayList<>();
    @Expose
    private ArrayList<BlockPerk> blocks = new ArrayList<>();
    @Expose
    private ArrayList<EntityPerk> entities = new ArrayList<>();
 
    public ArrayList<ItemPerk> getItems() {
        return this.items;
    }
 
    public void setItems(ArrayList<ItemPerk> items) {
        this.items = items;
    }
 
    public ArrayList<BlockPerk> getBlocks() {
        return this.blocks;
    }
 
    public void setBlocks(ArrayList<BlockPerk> blocks) {
        this.blocks = blocks;
    }
    
    public ArrayList<EntityPerk> getEntities(){
    	return this.entities;
    }
    
    public void setEntities(ArrayList<EntityPerk> entities){
    	this.entities = entities;
    }

    public void addItemPerk(ItemPerk itemPerk) { this.items.add(itemPerk); }
    public void addBlockPerk(BlockPerk blockPerk) { this.blocks.add(blockPerk); }
    public void addEntityPerk(EntityPerk entityPerk) { this.entities.add(entityPerk); }

	public void touchUp() {
		for (ItemPerk item : items){
			item.touchUp();
			disconsented.anssrpg.data.PerkStore.putItemPerk(item);
			disconsented.anssrpg.data.PerkStore.addPerk(item);			
		}
		for (BlockPerk block : blocks){
			block.touchUp();
			disconsented.anssrpg.data.PerkStore.putBlockPerk(block);
			disconsented.anssrpg.data.PerkStore.addPerk(block);	
		}
		for(EntityPerk entity : entities){
			entity.touchUp();
			disconsented.anssrpg.data.PerkStore.putEntityPerk(entity);
			disconsented.anssrpg.data.PerkStore.addPerk(entity);	
		}
	}
}