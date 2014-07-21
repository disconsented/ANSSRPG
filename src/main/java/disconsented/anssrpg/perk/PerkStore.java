package disconsented.anssrpg.perk;

import java.util.ArrayList;
import java.util.HashMap;

import disconsented.anssrpg.Settings;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;

public class PerkStore {
	static HashMap <String, Perk> perkRegistry = new HashMap();
	static HashMap <Item, ArrayList> itemRegistry = new HashMap();
	static HashMap <Object, ArrayList> entityRegistry = new HashMap();
	static HashMap <Block, ArrayList> blockRegistry = new HashMap();
	
	public static void addPerk(Perk perk){
		perkRegistry.put(perk.perkSlug, perk);
		if (perk.unlockBlock != null){
			try{
				blockRegistry.get(perk.unlockBlock).add(perk);
			}
			catch(NullPointerException e){
				ArrayList temp = new ArrayList();
				temp.add(perk);
				blockRegistry.put(perk.unlockBlock, temp);
				if (Settings.getDebug()){					
					System.err.println("No existing perks for " + perk.unlockBlock);
				}
			}			
		}
		if (perk.unlockItem != null){
			try{
				itemRegistry.get(perk.unlockBlock).add(perk);
			}
			catch(NullPointerException e){
				ArrayList temp = new ArrayList();
				temp.add(perk);
				itemRegistry.put(perk.unlockItem, temp);
				if (Settings.getDebug()){					
					System.err.println("No existing perks for " + perk.unlockItem);
				}
			}
		}
		if (perk.unlockEntity != null){
			try{
				entityRegistry.get(perk.unlockBlock).add(perk);
			}
			catch(NullPointerException e){
				ArrayList temp = new ArrayList();
				temp.add(perk);
				entityRegistry.put(perk.unlockEntity, temp);
				if (Settings.getDebug()){					
					System.err.println("No existing perks for " + perk.unlockEntity);
				}
			}
		}
		if(Settings.getDebug()){
			System.out.println(perk.name);
			System.out.println(perk.perkSlug);
			System.out.println(perk.description);
			System.out.println(perk.pointCost);
			System.out.println(perk.type);
			System.out.println(perk.unlock);
		}
	}
	public static void constructPerk(DummyPerk temp) throws NoSuchFieldException{
		Object tempEntity;
		Block tempBlock;
		Item tempItem;
		Perk tempPerk = null;
		
		switch(temp.type.toLowerCase()){		
		case "block":
			tempBlock = (Block) Block.blockRegistry.getObject(temp.unlock);
			tempPerk = new Perk(temp.name, tempBlock, temp.description, temp.pointCost, temp.requirementName, temp.requirementLevel);
			break;		
		case "entity":
		    tempEntity = EntityList.stringToClassMapping.get(temp.unlock);
		    tempPerk = new Perk(temp.name, tempEntity, temp.description, temp.pointCost, temp.requirementName, temp.requirementLevel);
			break;
		case "item":
			tempItem = (Item) Item.itemRegistry.getObject(temp.unlock);
			tempPerk = new Perk(temp.name, tempItem, temp.description, temp.pointCost, temp.requirementName, temp.requirementLevel);
			break;			
		default:
			throw new NoSuchFieldException();
		}
		addPerk(tempPerk);
			
	}
	public static Perk getPerk(String slug){
		return perkRegistry.get(slug);
	}
	public static HashMap getRegisteredPerks(){
		return perkRegistry;
	}
	public static ArrayList getItemPerkList(Item item){
		return itemRegistry.get(item);		
	}
	public static ArrayList getBlockPerkList(Block block){
		return blockRegistry.get(block);		
	}
	public static ArrayList getEntityPerkList(Object entity){
		return entityRegistry.get(entity);		
	}
}
