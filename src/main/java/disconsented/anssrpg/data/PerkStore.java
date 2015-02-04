/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr

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
package disconsented.anssrpg.data;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.perk.EntityPerk;
import disconsented.anssrpg.perk.ItemPerk;
import disconsented.anssrpg.perk.Perk;
import disconsented.anssrpg.perk.Slug;
import disconsented.anssrpg.perk.TitlePerk;

/**
 * Author: Disconsented
 * Just stores and retrieves perks
 */
public class PerkStore {
	private static ArrayList<Perk> perks = new ArrayList<Perk>();
	/*String needs to be a name unique to each object type*/
	private static HashMap<String, ArrayList<Slug>> blockMap = new HashMap<String, ArrayList<Slug>>();
	private static HashMap<String, ArrayList<Slug>> entityMap = new HashMap<String, ArrayList<Slug>>();
	private static HashMap<String, ArrayList<Slug>> itemMap = new HashMap<String, ArrayList<Slug>>();
	private static ArrayList<TitlePerk> titlePerks = new ArrayList<TitlePerk>();
	
	private static HashMap<Slug,Perk> perksMap = new HashMap<Slug,Perk>();
	private static PerkStore instance = null;
	private PerkStore() {/* Exists only to defeat instantiation.*/}
	public static PerkStore getInstance() {
		if(instance == null) {
			instance = new PerkStore();
		}
		return instance;
	}
	//Adds a perk to the complete list
    public static void addPerk(Perk perk){		
    	perksMap.put(perk.slug, perk);
    }
    public static ArrayList<Perk> getPerks(){
		return perks;
	}
	public static Perk getPerk(String perkSlug) {		
		return perksMap.get(perkSlug);
	}
	public static void putPerk(BlockPerk block){
		perks.add(block);
		if (blockMap.containsKey(block.getBlock().getUnlocalizedName())){
			blockMap.get(block.getBlock().getUnlocalizedName()).add(block.slug);
		}
		else
		{
			ArrayList<Slug> temp = new ArrayList<Slug>();
			temp.add(block.slug);
			blockMap.put(block.getBlock().getUnlocalizedName(), temp);
		}
	}
	public static void putPerk(ItemPerk item) {
		perks.add(item);
		if (itemMap.containsKey(item.getItem().getUnlocalizedName())){
			itemMap.get(item.getItem().getUnlocalizedName()).add(item.slug);
		}
		else
		{
			ArrayList<Slug> temp = new ArrayList<Slug>();
			temp.add(item.slug);
			itemMap.put(item.getItem().getUnlocalizedName(), temp);
		}
	}
	public static void putPerk(EntityPerk entity) {
		perks.add(entity);
		if (entityMap.containsKey(entity.getEntity().getSimpleName())){
			entityMap.get(entity.getEntity().getSimpleName()).add(entity.slug);
		}
		else
		{
			ArrayList<Slug> temp = new ArrayList<Slug>();
			temp.add(entity.slug);
			entityMap.put(entity.getEntity().getSimpleName(), temp);
		}		
		
	}
	public static void putPerk(TitlePerk title) {
	    titlePerks.add(title);
        
    }
	public static ArrayList<Slug> getSlugs(Block block){
	    return blockMap.get(block.getUnlocalizedName());   
	    
	}
	public static ArrayList<Slug> getSlugs(Entity entity){
	    return entityMap.get(entity.getCommandSenderName());
        
    }
	public static ArrayList<Slug> getSlugs(Item item){
	    return itemMap.get(item.getUnlocalizedName());
        
    }
	
	public static void Clear(){
		perks.clear();
		blockMap.clear();
		entityMap.clear();
		itemMap.clear();
		perksMap.clear();
	}
}
