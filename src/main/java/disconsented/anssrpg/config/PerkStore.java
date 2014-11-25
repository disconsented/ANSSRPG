package disconsented.anssrpg.config;
/**
 * Author: Abelistah
 */
import java.util.*;

import com.google.gson.annotations.Expose;

import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.perk.EntityPerk;
import disconsented.anssrpg.perk.ItemPerk;
 
public class PerkStore {
    // The ArrayLists to be Serialized/Deserialized
    @Expose
    private List<ItemPerk> items = new LinkedList<>();
    
    @Expose
    private List<BlockPerk> blocks = new LinkedList<>();
    // And so forth
    
    @Expose
    private List<EntityPerk> entities = new LinkedList<>();
 
    public List<ItemPerk> getItemPerks() {
        return items;
    }
 
    public void setItemPerks(List<ItemPerk> itemPerks) {
        items = itemPerks;
    }
 
    public List<BlockPerk> getBlockPerks() {
        return blocks;
    }
 
    public void setBlockPerks(List<BlockPerk> blockPerks) {
        blocks = blockPerks;
    }
    
    public List<EntityPerk> getEntityPerks(){
    	return entities;
    }
    
    public void setEntityPerks(List<EntityPerk> entities){
    	this.entities = entities;
    }
}