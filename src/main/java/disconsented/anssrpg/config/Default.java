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
import java.util.HashMap;

import disconsented.anssrpg.common.PotionDefinition;
import disconsented.anssrpg.objects.*;
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
                    new ArrayList<BNP>(){{add(new BNP( "iron_ore", new HashMap<String,String>()));}}));
            
            perks.addPerk(new BlockPerk("Unlock: Lapis Ore",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Mining", "4"));}},
                    "Allows you to mine Lapis Ore",
                    0,
                    new ArrayList<BNP>(){{add(new BNP("lapis_ore", new HashMap<String,String>()));}}));
            
            perks.addPerk(new BlockPerk("Unlock: Gold Ore",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Mining", "9"));}},
                    "Allows you to mine Gold Ore",
                    0,
                    new ArrayList<BNP>(){{add(new BNP("gold_ore", new HashMap<String,String>()));}}));
            
            perks.addPerk(new BlockPerk("Unlock: Redstone Ore",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Mining", "14"));}},
                    "Allows you to mine Redstone Ore",
                    0,
                    new ArrayList<BNP>(){{add(new BNP("lit_redstone_ore", new HashMap<String,String>()));}}));
            
            perks.addPerk(new BlockPerk("Unlock: Diamond Ore",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Mining", "19"));}},
                    "Allows you to mine Diamond Ore",
                    0,
                    new ArrayList<BNP>(){{add(new BNP("diamond_ore", new HashMap<String,String>()));}}));
            
            perks.addPerk(new BlockPerk("Unlock: Emerald Ore",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Mining", "24"));}},
                    "Allows you to mine Emerald Ore",
                    0,
                    new ArrayList<BNP>(){{add(new BNP("emerald_ore", new HashMap<String,String>()));}}));

            perks.addPerk(new ItemPerk("Unlock: Gold Armour", 
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Armouring", "4"));}},
                    "Allows you to craft Gold armour",
                    0,
                    new ArrayList<INM>(){{
                        add(new INM(null, "golden_helmet",0));
                        add(new INM(null, "golden_chestplate",0));
                        add(new INM(null, "golden_leggings",0));
                        add(new INM(null, "golden_boots",0));
                        }}));
            
            perks.addPerk(new ItemPerk("Unlock: Iron Armour",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Armouring", "9"));}},
                    "Allows you to craft Iron armour",
                    0,
                    new ArrayList<INM>(){{
                        add(new INM(null, "iron_helmet",0));
                        add(new INM(null, "iron_chestplate",0));
                        add(new INM(null, "iron_leggings",0));
                        add(new INM(null, "iron_boots",0));
                        }}));
            
            perks.addPerk(new ItemPerk("Unlock: Diamond Armour",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Armouring", "14"));}},
                    "Allows you to craft Diamond armour",
                    0,
                    new ArrayList<INM>(){{
                        add(new INM(null, "diamond_helmet",0));
                        add(new INM(null, "diamond_chestplate",0));
                        add(new INM(null, "diamond_leggings",0));
                        add(new INM(null, "diamond_boots",0));
                        }}));
            
            perks.addPerk(new EntityPerk("Creeper killer",
                    new ArrayList<Requirement>(){{add(new Requirement(Action.LEVEL_GREATER, "Slaying", "4"));}},
                    "Allows Creepers to be killed",
                    0,
                    new ArrayList<ENE>(){{add (new ENE(null,"Creeper",0));}}));

            perks.addPerk(new PotionSelfPerk("Health boost", null, "Provides a 10 second shield", 0,
                    new ArrayList<PotionDefinition>(){{
                add(new PotionDefinition(21,1,20));}},
                true, 20, 200));

            perks.addPerk(new TitlePerk("Completionist"));
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
            mining.exp.add(new BNEP("coal_ore", 5, null));
            mining.exp.add(new BNEP("iron_ore", 10, null));
            mining.exp.add(new BNEP("lapis_ore", 15, null));
            mining.exp.add(new BNEP("gold_ore", 20, null));
            mining.exp.add(new BNEP("redstone_ore", 25, null));
            mining.exp.add(new BNEP("diamond_ore", 30, null));
            mining.exp.add(new BNEP("emerald_ore", 35, null));
            mining.tool = "Pickaxe";
            skills.addBlockSkill(mining);
            
            // log == Oak,Spruce,Birch,Jungle
            // log2 == Acaica,DarkOak
            BlockSkill woodcutting = new BlockSkill();
            woodcutting.name = "Woodcutting";
            woodcutting.exp.add(new BNEP("log", 1, new HashMap<String, String>(){{put("variant","*");}}));
            woodcutting.exp.add(new BNEP("log2", 2,  new HashMap<String, String>(){{put("variant","*");}}));
            woodcutting.tool = "Axe";
            skills.addBlockSkill(woodcutting);
            
            BlockSkill digging = new BlockSkill();
            digging.name = "Digging";
            digging.exp.add(new BNEP("sand", 1, null));
            digging.exp.add(new BNEP("dirt", 1, null));
            digging.exp.add(new BNEP("grass", 1, null));
            digging.exp.add(new BNEP("clay", 2, null));
            digging.exp.add(new BNEP("hardened_clay", 2, null));
            digging.exp.add(new BNEP("stained_hardened_clay", 2, new HashMap<String, String>(){{put("variant","*");}}));
            digging.tool = "Spade";
            skills.addBlockSkill(digging);
            
            EntitySkill slaying = new EntitySkill();
            slaying.name = "Slaying";
            slaying.exp.add(new ENE(null, "Zombie", 5));
            slaying.exp.add(new ENE(null, "Skeleton", 10));
            slaying.exp.add(new ENE(null, "Spider", 10));
            slaying.exp.add(new ENE(null, "Creeper", 20));
            slaying.tool = "Sword";
            skills.addEntitySkill(slaying);
            
            ItemSkill armouring = new ItemSkill();
            armouring.name = "Armouring";
            //Leather
            armouring.exp.add(new INME(null, "leather_helmet", 10, 0));
            armouring.exp.add(new INME(null, "leather_chestplate", 10, 0));
            armouring.exp.add(new INME(null, "leather_leggings", 10, 0));
            armouring.exp.add(new INME(null, "leather_boots", 10, 0));
            //Gold
            armouring.exp.add(new INME(null, "golden_helmet", 15, 0));
            armouring.exp.add(new INME(null, "golden_chestplate", 15, 0));
            armouring.exp.add(new INME(null, "golden_leggings", 15, 0));
            armouring.exp.add(new INME(null, "golden_boots", 15, 0));
            //Iron
            armouring.exp.add(new INME(null, "iron_helmet", 20, 0));
            armouring.exp.add(new INME(null, "iron_chestplate", 20, 0));
            armouring.exp.add(new INME(null, "iron_leggings", 20, 0));
            armouring.exp.add(new INME(null, "iron_boots", 20, 0));
            //Diamond
            armouring.exp.add(new INME(null, "diamond_helmet", 30, 0));
            armouring.exp.add(new INME(null, "diamond_chestplate", 30, 0));
            armouring.exp.add(new INME(null, "diamond_leggings", 30, 0));
            armouring.exp.add(new INME(null, "diamond_boots", 30, 0));
            
            skills.addItemSkill(armouring);
            return skills;
        }
        
    }
    
}
