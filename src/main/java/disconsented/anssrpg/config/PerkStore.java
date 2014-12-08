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