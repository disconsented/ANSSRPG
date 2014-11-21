package disconsented.anssrpg.config;
/**
 * Author: Abelistah
 */
import java.util.ArrayList;

import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.perk.ItemPerk;

public class ObjectStore {
    // The ArrayLists to be Serialized/Deserialized
    private ArrayList<ItemPerk> items = new ArrayList<ItemPerk>();
    private ArrayList<BlockPerk> blocks = new ArrayList<BlockPerk>();
    // And so forth
 
    public ArrayList<ItemPerk> getItemPerks() {
        return items;
    }
 
    public void setItemPerks(ArrayList<ItemPerk> itemPerks) {
        items = itemPerks;
    }
 
    public ArrayList<BlockPerk> getBlockPerks() {
        return blocks;
    }
 
    public void setBlockPerks(ArrayList<BlockPerk> blockPerks) {
        blocks = blockPerks;
    }
}