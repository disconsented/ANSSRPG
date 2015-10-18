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
package disconsented.anssrpg.server.data;

import disconsented.anssrpg.server.objects.*;
import disconsented.anssrpg.server.perk.*;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author: Disconsented
 * Just stores and retrieves perks
 */
public class PerkStore {
    private static final ArrayList<Perk> perks = new ArrayList<Perk>();
    /*String needs to be a name unique to each entity type*/
    private static final HashMap<String, ArrayList<BlockPerk>> blockMap = new HashMap<String, ArrayList<BlockPerk>>();
    private static final HashMap<String, ArrayList<EntityPerk>> entityMap = new HashMap<String, ArrayList<EntityPerk>>();
    private static final HashMap<String, ArrayList<ItemPerk>> itemMap = new HashMap<String, ArrayList<ItemPerk>>();
    private static final ArrayList<TitlePerk> titlePerks = new ArrayList<TitlePerk>();
    private static final ArrayList<PotionSelfPerk> potionSelf = new ArrayList<PotionSelfPerk>();
    
    private static final HashMap<String, Perk> perksMap = new HashMap<String, Perk>();
    private static PerkStore instance;

    private PerkStore() {}

    //Adds a perk to the complete list
    public static void addPerk(Perk perk) {
        PerkStore.perksMap.put(perk.getSlug().getSlug(), perk);
    }

    public static void Clear() {
        PerkStore.perks.clear();
        PerkStore.blockMap.clear();
        PerkStore.entityMap.clear();
        PerkStore.itemMap.clear();
        PerkStore.perksMap.clear();
    }

    public static PerkStore getInstance() {
        if (PerkStore.instance == null) {
            PerkStore.instance = new PerkStore();
        }
        return PerkStore.instance;
    }

    public static Perk getPerk(String perkSlug) {
        return PerkStore.perksMap.get(perkSlug);
    }

    public static ArrayList<Perk> getPerks() {
        return PerkStore.perks;
    }

    /**
     * Takes a Block and returns an ArrayList of the associated perks
     * @param block
     * @return
     */
    public static ArrayList<BlockPerk> getPerks(Block block){
        return PerkStore.blockMap.get(block.getUnlocalizedName());
    }

    /**
     * Takes a Item and returns an ArrayList of the associated perks
     * @param item
     * @return
     */
    public static ArrayList<ItemPerk> getPerks(Item item){
        return PerkStore.itemMap.get(item.getUnlocalizedName());
    }

    /**
     * Takes a Entity(in the form of a class object) and returns an ArrayList of the associated perks
     * @param entity
     * @return
     */
    public static ArrayList<EntityPerk> getPerks(Class entity){
        return PerkStore.entityMap.get(entity.getSimpleName());
    }


    /**
     * Safely stores any perk that is used in a map
     * @param abstractMap
     * @param perk
     * @param name - Name that storage is based off
     */
    private static <t> void putPerk(AbstractMap<String, ArrayList<t>> abstractMap, t perk, String name){
        ArrayList<t> cachePerkList = abstractMap.get(name);
        if (cachePerkList != null){
            cachePerkList.add(perk);
        } else {
            ArrayList<t> newPerkList = new ArrayList<t>();
            newPerkList.add(perk);
            abstractMap.put(name, newPerkList);
        }
    }

    /**
     * Stores BlockPerks based on the unlocalized names of the objects in its collection
     * @param block: BlockPerk
     */
    public static void putPerk(BlockPerk block) {
        PerkStore.perks.add(block);
        for (BNP object : block.blocks){
            Block cache = object.block;
            PerkStore.putPerk(PerkStore.blockMap, block, cache.getUnlocalizedName());
        }

    }

    /**
     * Stores BlockPerks based on the class names of the objects in its collection
     * Class names are used as there is no registry that provides objects unlike for items and blocks
     * @param entity: EntityPerk
     */
    public static void putPerk(EntityPerk entity) {
        PerkStore.perks.add(entity);
        for (ENE object : entity.entities){
            Class<Entity> cache = object.entity;
            PerkStore.putPerk(PerkStore.entityMap, entity, cache.getSimpleName());
        }

    }

    /**
     * Stores BlockPerks based on the unlocalized names of the objects in its collection
     * @param item: ItemPerk
     */
    public static void putPerk(ItemPerk item) {
        PerkStore.perks.add(item);
        for (INM object : item.items){
            Item cache = object.item;
            PerkStore.putPerk(PerkStore.itemMap, item, cache.getUnlocalizedName());
        }

    }
    
    public static void putPerk(TitlePerk title) {
        PerkStore.titlePerks.add(title);

    }


    public static void putPerk(PotionSelfPerk effect) {
        PerkStore.perks.add(effect);
        
    }
    
}
