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
package disconsented.anssrpg.server.config;

import disconsented.anssrpg.server.config.storage.*;
import disconsented.anssrpg.server.perk.*;
import disconsented.anssrpg.server.skill.objects.BlockSkill;
import disconsented.anssrpg.server.skill.objects.EntitySkill;
import disconsented.anssrpg.server.skill.objects.ItemSkill;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contains the information for the default configs so it can be referenced as needed
 * Needs to be filled out, suggestions needed ;)
 */
public class Default {
    private static PerkContainer perks;
    private static SkillContainer skills;

    private Default() {
    }

    public static PerkContainer getPerkInstance() {
        if (Default.perks != null) {
            return Default.perks;
        } else {
            Default.perks = new PerkContainer();
            Default.perks.addPerk(new BlockPerk("Unlock: Iron Ore",
                    new ArrayList<Requirement>() {{
                        this.add(new Requirement(Requirement.Action.LEVEL_GREATER, "Mining", "4"));
                    }},
                    "Allows you to mine Iron Ore",
                    new ArrayList<BNP>() {{
                        this.add(new BNP("iron_ore", new HashMap<String, String>()));
                    }}));

            Default.perks.addPerk(new BlockPerk("Unlock: Lapis Ore",
                    new ArrayList<Requirement>() {{
                        this.add(new Requirement(Requirement.Action.LEVEL_GREATER, "Mining", "4"));
                    }},
                    "Allows you to mine Lapis Ore",
                    new ArrayList<BNP>() {{
                        this.add(new BNP("lapis_ore", new HashMap<String, String>()));
                    }}));

            Default.perks.addPerk(new BlockPerk("Unlock: Gold Ore",
                    new ArrayList<Requirement>() {{
                        this.add(new Requirement(Requirement.Action.LEVEL_GREATER, "Mining", "9"));
                    }},
                    "Allows you to mine Gold Ore",
                    new ArrayList<BNP>() {{
                        this.add(new BNP("gold_ore", new HashMap<String, String>()));
                    }}));

            Default.perks.addPerk(new BlockPerk("Unlock: Redstone Ore",
                    new ArrayList<Requirement>() {{
                        this.add(new Requirement(Requirement.Action.LEVEL_GREATER, "Mining", "14"));
                    }},
                    "Allows you to mine Redstone Ore",
                    new ArrayList<BNP>() {{
                        this.add(new BNP("lit_redstone_ore", new HashMap<String, String>()));
                    }}));

            Default.perks.addPerk(new BlockPerk("Unlock: Diamond Ore",
                    new ArrayList<Requirement>() {{
                        this.add(new Requirement(Requirement.Action.LEVEL_GREATER, "Mining", "19"));
                    }},
                    "Allows you to mine Diamond Ore",
                    new ArrayList<BNP>() {{
                        this.add(new BNP("diamond_ore", new HashMap<String, String>()));
                    }}));

            Default.perks.addPerk(new BlockPerk("Unlock: Emerald Ore",
                    new ArrayList<Requirement>() {{
                        this.add(new Requirement(Requirement.Action.LEVEL_GREATER, "Mining", "24"));
                    }},
                    "Allows you to mine Emerald Ore",
                    new ArrayList<BNP>() {{
                        this.add(new BNP("emerald_ore", new HashMap<String, String>()));
                    }}));

            Default.perks.addPerk(new ItemPerk("Unlock: Gold Armour",
                    new ArrayList<Requirement>() {{
                        this.add(new Requirement(Requirement.Action.LEVEL_GREATER, "Armouring", "4"));
                    }},
                    "Allows you to craft Gold armour",
                    new ArrayList<INM>() {{
                        this.add(new INM(null, "golden_helmet", 0));
                        this.add(new INM(null, "golden_chestplate", 0));
                        this.add(new INM(null, "golden_leggings", 0));
                        this.add(new INM(null, "golden_boots", 0));
                    }}));

            Default.perks.addPerk(new ItemPerk("Unlock: Iron Armour",
                    new ArrayList<Requirement>() {{
                        this.add(new Requirement(Requirement.Action.LEVEL_GREATER, "Armouring", "9"));
                    }},
                    "Allows you to craft Iron armour",
                    new ArrayList<INM>() {{
                        this.add(new INM(null, "iron_helmet", 0));
                        this.add(new INM(null, "iron_chestplate", 0));
                        this.add(new INM(null, "iron_leggings", 0));
                        this.add(new INM(null, "iron_boots", 0));
                    }}));

            Default.perks.addPerk(new ItemPerk("Unlock: Diamond Armour",
                    new ArrayList<Requirement>() {{
                        this.add(new Requirement(Requirement.Action.LEVEL_GREATER, "Armouring", "14"));
                    }},
                    "Allows you to craft Diamond armour",
                    new ArrayList<INM>() {{
                        this.add(new INM(null, "diamond_helmet", 0));
                        this.add(new INM(null, "diamond_chestplate", 0));
                        this.add(new INM(null, "diamond_leggings", 0));
                        this.add(new INM(null, "diamond_boots", 0));
                    }}));

            Default.perks.addPerk(new EntityPerk("Creeper killer",
                    new ArrayList<Requirement>() {{
                        this.add(new Requirement(Requirement.Action.LEVEL_GREATER, "Slaying", "4"));
                    }},
                    "Allows Creepers to be killed",
                    new ArrayList<ENE>() {{
                        this.add(new ENE(null, "Creeper", 0));
                    }}));

            Default.perks.addPerk(new PotionSelfPerk("Health boost", null, "Provides a 10 second shield",
                    new ArrayList<PotionDefinition>() {{
                        this.add(new PotionDefinition("minecraft:health_boost", 1, 20));
                    }},
                    true, 20, 200));

            Default.perks.addPerk(new TitlePerk("Completionist"));
            return Default.perks;

        }

    }

    public static SkillContainer getSkillInstance() {
        if (Default.skills != null) {
            return Default.skills;
        } else {
            Default.skills = new SkillContainer();
            BlockSkill mining = new BlockSkill();
            mining.name = "Mining";
            mining.exp.add(new BNEP("coal_ore", 5, null));
            mining.exp.add(new BNEP("iron_ore", 10, null));
            mining.exp.add(new BNEP("lapis_ore", 15, null));
            mining.exp.add(new BNEP("gold_ore", 20, null));
            mining.exp.add(new BNEP("lit_redstone_ore", 25, null));
            mining.exp.add(new BNEP("diamond_ore", 30, null));
            mining.exp.add(new BNEP("emerald_ore", 35, null));
            mining.tool = "Pickaxe";
            Default.skills.addBlockSkill(mining);

            // log == Oak,Spruce,Birch,Jungle
            // log2 == Acaica,DarkOak
            BlockSkill woodcutting = new BlockSkill();
            woodcutting.name = "Woodcutting";
            woodcutting.exp.add(new BNEP("log", 1, new HashMap<String, String>() {{
                this.put("variant", "*");
            }}));
            woodcutting.exp.add(new BNEP("log2", 2, new HashMap<String, String>() {{
                this.put("variant", "*");
            }}));
            woodcutting.tool = "Axe";
            Default.skills.addBlockSkill(woodcutting);

            BlockSkill digging = new BlockSkill();
            digging.name = "Digging";
            digging.exp.add(new BNEP("sand", 1, null));
            digging.exp.add(new BNEP("dirt", 1, null));
            digging.exp.add(new BNEP("grass", 1, null));
            digging.exp.add(new BNEP("clay", 2, null));
            digging.exp.add(new BNEP("hardened_clay", 2, null));
            digging.exp.add(new BNEP("stained_hardened_clay", 2, new HashMap<String, String>() {{
                this.put("color", "*");
            }}));
            digging.tool = "Spade";
            Default.skills.addBlockSkill(digging);

            EntitySkill slaying = new EntitySkill();
            slaying.name = "Slaying";
            slaying.exp.add(new ENE(null, "Zombie", 5));
            slaying.exp.add(new ENE(null, "Skeleton", 10));
            slaying.exp.add(new ENE(null, "Spider", 10));
            slaying.exp.add(new ENE(null, "Creeper", 20));
            slaying.tool = "Sword";
            Default.skills.addEntitySkill(slaying);

            ItemSkill armouring = new ItemSkill();
            armouring.name = "Armouring";
            //Leather
            armouring.exp.add(new INME(null, "leather_helmet", 0, 10));
            armouring.exp.add(new INME(null, "leather_chestplate", 0, 10));
            armouring.exp.add(new INME(null, "leather_leggings", 0, 10));
            armouring.exp.add(new INME(null, "leather_boots", 0, 10));
            //Gold
            armouring.exp.add(new INME(null, "golden_helmet", 0, 15));
            armouring.exp.add(new INME(null, "golden_chestplate", 0, 15));
            armouring.exp.add(new INME(null, "golden_leggings", 0, 15));
            armouring.exp.add(new INME(null, "golden_boots", 0, 15));
            //Iron
            armouring.exp.add(new INME(null, "iron_helmet", 0, 20));
            armouring.exp.add(new INME(null, "iron_chestplate", 0, 20));
            armouring.exp.add(new INME(null, "iron_leggings", 0, 20));
            armouring.exp.add(new INME(null, "iron_boots", 0, 20));
            //Diamond
            armouring.exp.add(new INME(null, "diamond_helmet", 0, 30));
            armouring.exp.add(new INME(null, "diamond_chestplate", 0, 30));
            armouring.exp.add(new INME(null, "diamond_leggings", 0, 30));
            armouring.exp.add(new INME(null, "diamond_boots", 0, 30));

            Default.skills.addItemSkill(armouring);
            return Default.skills;
        }

    }

}
