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
import java.util.ArrayList;

import com.google.gson.annotations.Expose;

import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.perk.EntityPerk;
import disconsented.anssrpg.perk.ItemPerk;
import disconsented.anssrpg.perk.RegexBlockPerk;
import disconsented.anssrpg.perk.RegexEntityPerk;
import disconsented.anssrpg.perk.RegexItemPerk;
import disconsented.anssrpg.perk.TitlePerk;

public class PerkStore {
    @Expose
    private ArrayList<ItemPerk> items = new ArrayList<>();
    @Expose
    private ArrayList<BlockPerk> blocks = new ArrayList<>();
    @Expose
    private ArrayList<EntityPerk> entities = new ArrayList<>();
    @Expose 
    private ArrayList<TitlePerk> titles = new ArrayList<>();
    @Expose 
    private ArrayList<RegexBlockPerk> regexBlocks = new ArrayList<>();
    @Expose
    private ArrayList<RegexItemPerk> regexItems = new ArrayList<>();
    @Expose 
    private ArrayList<RegexEntityPerk> regexEntitys = new ArrayList<>();
    public PerkStore(){
        items.add(new ItemPerk());
        blocks.add(new BlockPerk());
        entities.add(new EntityPerk());
        titles.add(new TitlePerk());
        regexBlocks.add(new RegexBlockPerk());
        regexItems.add(new RegexItemPerk());
        regexEntitys.add(new RegexEntityPerk());
    }

    public void addBlockPerk(BlockPerk blockPerk) { this.blocks.add(blockPerk); }
    public void addEntityPerk(EntityPerk entityPerk) { this.entities.add(entityPerk); }
    public void addItemPerk(ItemPerk itemPerk) { this.items.add(itemPerk); }
    public void addRegexBlockPerk(RegexBlockPerk blockPerk) { this.regexBlocks.add(blockPerk); }
    public void addRegexEntityPerk(RegexEntityPerk entityPerk) { this.regexEntitys.add(entityPerk); }
    public void addRegexItemPerk(RegexItemPerk itemPerk) { this.regexItems.add(itemPerk); }
    public void addRegexTitlePerk(TitlePerk titlePerk){ this.titles.add(titlePerk); }

	public void touchUp() {
		for (ItemPerk item : items){
			item.touchUp();
			if (item.getItem() != null){
			    disconsented.anssrpg.data.PerkStore.putPerk(item);
			    disconsented.anssrpg.data.PerkStore.addPerk(item);
			}else{
			    System.err.println(item.name+"'s object is null. Skipping");
			}
		}
		for (BlockPerk block : blocks){
			block.touchUp();
			if (block.getBlock() != null){
			    disconsented.anssrpg.data.PerkStore.putPerk(block);
			    disconsented.anssrpg.data.PerkStore.addPerk(block);
			}else{
                System.err.println(block.name+"'s object is null. Skipping");
            }
		}
		for(EntityPerk entity : entities){
			entity.touchUp();
			if (entity.getEntity() != null){
    			disconsented.anssrpg.data.PerkStore.putPerk(entity);
    			disconsented.anssrpg.data.PerkStore.addPerk(entity);
			}else{
                System.err.println(entity.name+"'s object is null. Skipping");
            }
		}
		for(TitlePerk title : titles){
		    if (title.getTitle() != null){
		        disconsented.anssrpg.data.PerkStore.putPerk(title);
                disconsented.anssrpg.data.PerkStore.addPerk(title);
		    }else{
		        System.err.println(title.name+"s object is null. Skipping");
		    }
		    
		}
		for(RegexItemPerk item : regexItems){
		    
		}
		for(RegexBlockPerk block : regexBlocks){
            
        }
		for(RegexEntityPerk entityPerk : regexEntitys){
            
        }

	}
}