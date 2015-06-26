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
package disconsented.anssrpg.config;

import java.util.ArrayList;

import scala.actors.threadpool.Arrays;
import disconsented.anssrpg.common.PotionDefinition;
import disconsented.anssrpg.common.Quad;
import disconsented.anssrpg.common.Pair;
import disconsented.anssrpg.common.Triplet;
import disconsented.anssrpg.perk.*;
import disconsented.anssrpg.perk.Requirement.Action;
import disconsented.anssrpg.skill.objects.*;

/**
 * Contains the information for the default configs so it can be referenced as needed
 * Needs to be filled out, suggestions needed ;)
 */
public class Default {
    private static PerkContainer perks = null;
    private static SkillContainer skills = null;
    private Default(){}
    
    public static PerkContainer getPerkInstance(){
        if (perks != null){
            return perks;
        } else {
            perks = new PerkContainer();            
            
            perks.addPerk(new BlockPerk("Unlock: Iron Ore",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Mining", "4"));}},
                    "Allows you to mine Iron Ore",
                    0,
                    new ArrayList<Pair>(){{add(new Pair(null, "iron_ore"));}}));
            
            perks.addPerk(new BlockPerk("Unlock: Lapis Ore",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Mining", "4"));}},
                    "Allows you to mine Iron Ore",
                    0,
                    new ArrayList<Pair>(){{add(new Pair(null, "lapis_ore"));}}));
            
            perks.addPerk(new BlockPerk("Unlock: Gold Ore",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Mining", "9"));}},
                    "Allows you to mine Iron Ore",
                    0,
                    new ArrayList<Pair>(){{add(new Pair(null, "gold_ore"));}}));
            
            perks.addPerk(new BlockPerk("Unlock: Redstone Ore",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Mining", "14"));}},
                    "Allows you to mine Iron Ore",
                    0,
                    new ArrayList<Pair>(){{add(new Pair(null, "redstone_ore"));}}));
            
            perks.addPerk(new BlockPerk("Unlock: Diamond Ore",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Mining", "19"));}},
                    "Allows you to mine Iron Ore",
                    0,
                    new ArrayList<Pair>(){{add(new Pair(null, "diamond_ore"));}}));
            
            perks.addPerk(new BlockPerk("Unlock: Emerald Ore",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Mining", "24"));}},
                    "Allows you to mine Iron Ore",
                    0,
                    new ArrayList<Pair>(){{add(new Pair(null, "emerald_ore"));}}));
            
            perks.addPerk(new ItemPerk("Unlock: Gold Armour", 
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Armouring", "4"));}},
                    "Allows you to craft Gold armour",
                    0,
                    new ArrayList<Pair>(){{
                        add(new Pair(null, "golden_helmet"));
                        add(new Pair(null, "golden_chestplate"));
                        add(new Pair(null, "golden_leggings"));
                        add(new Pair(null, "golden_boots"));
                        }}));
            
            perks.addPerk(new ItemPerk("Unlock: Iron Armour",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Armouring", "9"));}},
                    "Allows you to craft Iron armour",
                    0,
                    new ArrayList<Pair>(){{
                        add(new Pair(null, "iron_helmet"));
                        add(new Pair(null, "iron_chestplate"));
                        add(new Pair(null, "iron_leggings"));
                        add(new Pair(null, "iron_boots"));
                        }}));
            
            perks.addPerk(new ItemPerk("Unlock: Diamond Armour",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Armouring", "14"));}},
                    "Allows you to craft Diamond armour",
                    0,
                    new ArrayList<Pair>(){{
                        add(new Pair(null, "diamond_helmet"));
                        add(new Pair(null, "diamond_chestplate"));
                        add(new Pair(null, "diamond_leggings"));
                        add(new Pair(null, "diamond_boots"));
                        }}));
            
            perks.addPerk(new EntityPerk("Creeper killer",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Slaying", "4"));}},
                    "Allows Creepers to be killed",
                    0,
                    new ArrayList<Pair>(){{add (new Pair(null,"Creeper"));}}));
            
            perks.addPerk(new PotionSelfPerk("Health boost", null, "Provides a 10 second shield", 0, 
                    new ArrayList<PotionDefinition>(){{
                add(new PotionDefinition(21,1,200));}},
                false, 1));
            
            return perks;
            
        }
            
    }
    
    public static SkillContainer getSkillInstance(){
        if (skills != null){
            return skills;
        } else {
            skills = new SkillContainer();
            
            BlockSkill mining = new BlockSkill();
            mining.name = "Mining";
            mining.exp.add(new Quad(null, "coal_ore", 5, 0));
            mining.exp.add(new Quad(null, "iron_ore", 10, 0));
            mining.exp.add(new Quad(null, "lapis_ore", 15, 0));
            mining.exp.add(new Quad(null, "gold_ore", 20, 0));
            mining.exp.add(new Quad(null, "redstone_ore", 25, 0));
            mining.exp.add(new Quad(null, "diamond_ore", 30, 0));
            mining.exp.add(new Quad(null, "emerald_ore", 35, 0));
            mining.tool = "Pickaxe";
            skills.addBlockSkill(mining);
            
            /* log == Oak,Spruce,Birch,Jungle
             * log2 ==Acaica,DarkOak*/
            BlockSkill woodcutting = new BlockSkill();
            woodcutting.name = "Woodcutting";
            woodcutting.exp.add(new Quad(null, "log", 1, 0));
            woodcutting.exp.add(new Quad(null, "log", 2, 1));
            woodcutting.exp.add(new Quad(null, "log", 3, 2));
            woodcutting.exp.add(new Quad(null, "log", 4, 3));
            woodcutting.exp.add(new Quad(null, "log2", 5, 0));
            woodcutting.exp.add(new Quad(null, "log2", 6, 1));
            woodcutting.tool = "Axe";
            skills.addBlockSkill(woodcutting);
            
            BlockSkill digging = new BlockSkill();
            digging.name = "Digging";
            digging.exp.add(new Quad(null, "sand", 1, 0));
            digging.exp.add(new Quad(null, "dirt", 1, 0));
            digging.exp.add(new Quad(null, "grass", 1, 0));
            digging.exp.add(new Quad(null, "clay", 2, 0));
            digging.exp.add(new Quad(null, "hardened_clay", 2, 0));
            digging.exp.add(new Quad(null, "stained_hardened_clay", 2, -1));
            digging.tool = "Spade";
            skills.addBlockSkill(digging);
            
            EntitySkill slaying = new EntitySkill();
            slaying.name = "Slaying";
            slaying.exp.add(new Triplet(null, "Zombie", 5));
            slaying.exp.add(new Triplet(null, "Skeleton", 10));
            slaying.exp.add(new Triplet(null, "Spider", 10));
            slaying.exp.add(new Triplet(null, "Creeper", 20));
            slaying.tool = "Sword";
            skills.addEntitySkill(slaying);
            
            ItemSkill armouring = new ItemSkill();
            armouring.name = "Armouring";
            //Leather
            armouring.exp.add(new Quad(null, "leather_helmet", 10, 0));
            armouring.exp.add(new Quad(null, "leather_chestplate", 10, 0));
            armouring.exp.add(new Quad(null, "leather_leggings", 10, 0));
            armouring.exp.add(new Quad(null, "leather_boots", 10, 0));
            //Gold
            armouring.exp.add(new Quad(null, "golden_helmet", 15, 0));
            armouring.exp.add(new Quad(null, "golden_chestplate", 15, 0));
            armouring.exp.add(new Quad(null, "golden_leggings", 15, 0));
            armouring.exp.add(new Quad(null, "golden_boots", 15, 0));
            //Iron
            armouring.exp.add(new Quad(null, "iron_helmet", 20, 0));
            armouring.exp.add(new Quad(null, "iron_chestplate", 20, 0));
            armouring.exp.add(new Quad(null, "iron_leggings", 20, 0));
            armouring.exp.add(new Quad(null, "iron_boots", 20, 0));
            //Diamond
            armouring.exp.add(new Quad(null, "diamond_helmet", 30, 0));
            armouring.exp.add(new Quad(null, "diamond_chestplate", 30, 0));
            armouring.exp.add(new Quad(null, "diamond_leggings", 30, 0));
            armouring.exp.add(new Quad(null, "diamond_boots", 30, 0));
            
            skills.addItemSkill(armouring);
            
            return skills;
        }
        
    }
    
}
