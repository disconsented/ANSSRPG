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

import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.perk.EntityPerk;
import disconsented.anssrpg.perk.ItemPerk;
import disconsented.anssrpg.perk.Perk;

/**
 * Author: Disconsented
 * Just stores and retrieves perks
 */
public class PerkStore {
	private static ArrayList<Perk> perks = new ArrayList<Perk>();
	private static HashMap<String, ArrayList<BlockPerk>> blockMap = new HashMap<String, ArrayList<BlockPerk>>();
	private static HashMap<String, ArrayList<EntityPerk>> entityMap = new HashMap<String, ArrayList<EntityPerk>>();
	private static HashMap<String, ArrayList<ItemPerk>> itemMap = new HashMap<String, ArrayList<ItemPerk>>();
	private static HashMap<String,Perk> perksMap = new HashMap<String,Perk>();
	private static PerkStore instance = null;
	protected PerkStore() {/* Exists only to defeat instantiation.*/}
	public static PerkStore getInstance() {
		if(instance == null) {
			instance = new PerkStore();
		}
		return instance;
	}
	public static ArrayList<Perk> getPerks(){
		return perks;
	}
	public static void addPerk(Perk perk){
		
		perksMap.put(perk.perkSlug,perk);
	}
	public static Perk getPerk(String perkSlug) {		
		return perksMap.get(perkSlug);
	}
	public static void putBlockPerk(BlockPerk perk){
		perks.add(perk);
		if (blockMap.containsKey(perk.getBlock().getUnlocalizedName())){
			blockMap.get(perk.getBlock().getUnlocalizedName()).add(perk);
		}
		else
		{
			ArrayList<BlockPerk> temp = new ArrayList<BlockPerk>();
			temp.add(perk);
			blockMap.put(perk.getBlock().getUnlocalizedName(), temp);
		}
	}
	public static void putItemPerk(ItemPerk item) {
		perks.add(item);
		if (itemMap.containsKey(item.getItem().getUnlocalizedName())){
			itemMap.get(item.getItem().getUnlocalizedName()).add(item);
		}
		else
		{
			ArrayList<ItemPerk> temp = new ArrayList<ItemPerk>();
			temp.add(item);
			itemMap.put(item.getItem().getUnlocalizedName(), temp);
		}
	}
	public static void putEntityPerk(EntityPerk entity) {
		perks.add(entity);
		if (entityMap.containsKey(entity.getEntity().getSimpleName())){
			entityMap.get(entity.getEntity().getSimpleName()).add(entity);
		}
		else
		{
			ArrayList<EntityPerk> temp = new ArrayList<EntityPerk>();
			temp.add(entity);
			entityMap.put(entity.getEntity().getSimpleName(), temp);
		}		
		
	}
	public static ArrayList<BlockPerk> getPerksForBlock(String unlocalisedName){
		return blockMap.get(unlocalisedName);	
	}
	public static ArrayList<EntityPerk> getPerksForEntity(
			String commandSenderName) {
		return entityMap.get(commandSenderName);
	}
	public static ArrayList<ItemPerk> getPerksForItem(String unlocalizedName) {		
		return itemMap.get(unlocalizedName);
	}
	public static void Clear(){
		perks = new ArrayList<Perk>();
		blockMap = new HashMap<String, ArrayList<BlockPerk>>();
		entityMap = new HashMap<String, ArrayList<EntityPerk>>();
		itemMap = new HashMap<String, ArrayList<ItemPerk>>();
		perksMap = new HashMap<String,Perk>();
	}
}
