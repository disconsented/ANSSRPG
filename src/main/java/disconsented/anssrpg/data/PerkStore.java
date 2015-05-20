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

import disconsented.anssrpg.common.Pair;
import disconsented.anssrpg.common.Triplet;
import disconsented.anssrpg.perk.*;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;

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
    private static HashMap<String, ArrayList<Slug>> interactionBlockMap = new HashMap<String, ArrayList<Slug>>();
    private static ArrayList<PotionSelfPerk> potionSelf = new ArrayList<PotionSelfPerk>();
    
    private static HashMap<String, Perk> perksMap = new HashMap<String, Perk>();
    private static PerkStore instance = null;

    private PerkStore() {/* Exists only to defeat instantiation.*/}

    //Adds a perk to the complete list
    public static void addPerk(Perk perk) {
        perksMap.put(perk.getSlug().getSlug(), perk);
    }

    public static void Clear() {
        perks.clear();
        blockMap.clear();
        entityMap.clear();
        itemMap.clear();
        perksMap.clear();
        interactionBlockMap.clear();
    }

    public static PerkStore getInstance() {
        if (instance == null) {
            instance = new PerkStore();
        }
        return instance;
    }

    public static Perk getPerk(String perkSlug) {
        return perksMap.get(perkSlug);
    }

    public static ArrayList<Perk> getPerks() {
        return perks;
    }

    public static ArrayList<Slug> getSlugs(Block block) {
        return blockMap.get(block.getUnlocalizedName());

    }

    public static ArrayList<Slug> getSlugs(Entity entity) {
        return entityMap.get(entity.getClass().getSimpleName());

    }

    public static ArrayList<Slug> getSlugs(Item item) {
        return itemMap.get(item.getUnlocalizedName());

    }
    
    public static ArrayList<Slug> getSlugs(PlayerInteractEvent event){
        return new ArrayList<Slug>();
    }

    public static void putPerk(BlockPerk block) {
        perks.add(block);
        for (Pair object : block.blocks){
            Block cache = (Block) object.object;
            if (blockMap.containsKey(cache.getUnlocalizedName())) {
                blockMap.get(cache.getUnlocalizedName()).add(block.slug);
            } else {
                ArrayList<Slug> temp = new ArrayList<Slug>();
                temp.add(block.slug);
                blockMap.put(cache.getUnlocalizedName(), temp);
            }
        }
        
    }

    public static void putPerk(EntityPerk entity) {
        perks.add(entity);
        for (Pair object : entity.entities){
            Class cache = (Class) object.object;
            if (entityMap.containsKey(cache.getSimpleName())) {
                entityMap.get(cache.getSimpleName()).add(entity.slug);
            } else {
                ArrayList<Slug> temp = new ArrayList<Slug>();
                temp.add(entity.slug);
                entityMap.put(cache.getSimpleName(), temp);
            }
        }

    }

    public static void putPerk(ItemPerk item) {
        perks.add(item);
        for (Pair object : item.items){
            Item cache = (Item) object.object;
            if (itemMap.containsKey(cache.getUnlocalizedName())) {
                itemMap.get(cache.getUnlocalizedName()).add(item.slug);
            } else {
                ArrayList<Slug> temp = new ArrayList<Slug>();
                temp.add(item.slug);
                itemMap.put(cache.getUnlocalizedName(), temp);
            }
        }
        
    }
    
    public static void putPerk(TitlePerk title) {
        titlePerks.add(title);

    }

    public static void putPerk(InteractionPerk interaction) {
        perks.add(interaction);
        for (Triplet object : interaction.blocks){
            Block cache = (Block) object.object;
            if (interactionBlockMap.containsKey(cache.getUnlocalizedName())) {
                interactionBlockMap.get(cache.getUnlocalizedName()).add(interaction.slug);
            } else {
                ArrayList<Slug> temp = new ArrayList<Slug>();
                temp.add(interaction.slug);
                interactionBlockMap.put(cache.getUnlocalizedName(), temp);
            }
        } 
        
    }

    public static void putPerk(PotionSelfPerk effect) {
        perks.add(effect);
        
    }
    
}
