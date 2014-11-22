package disconsented.anssrpg.config;
/**
 * Author: Abelistah
 */
import java.util.*;
 
import com.google.gson.annotations.Expose;
import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.perk.ItemPerk;
 
public class ObjectStore {
    // The ArrayLists to be Serialized/Deserialized
    @Expose
    private List<ItemPerk> items = new LinkedList<>();
 
    private List<BlockPerk> blocks = new LinkedList<>();
    // And so forth
 
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
}