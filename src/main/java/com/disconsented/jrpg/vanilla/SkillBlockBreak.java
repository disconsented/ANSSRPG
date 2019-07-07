package com.disconsented.jrpg.vanilla;

import com.disconsented.jrpg.JRPG;
import com.disconsented.jrpg.Skill;
import com.google.common.reflect.TypeToken;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class SkillBlockBreak extends Skill {
    static transient ArrayList<SkillBlockBreak> storage = new ArrayList<>();
    ArrayList<BlockSkillDatum> entries = new ArrayList<>();

    public SkillBlockBreak() {
    }

    public static ArrayList<SkillBlockBreak> getSkills() {
        return storage;
    }

    @Override
    public ArrayList<? extends Skill> getStorage() {
        return storage;
    }

    @Override
    protected void setStorage(ArrayList mstorage) {
        storage = mstorage;
    }

    @Override
    public void load() {
        storage.forEach(skillBlockBreak -> {
            entries.forEach(datum -> {
                datum.block = Block.REGISTRY.getObject(new ResourceLocation(datum.name));
                if (datum.block == null) {
                    JRPG.log.error(datum.name + " did not resolve into a block");
                } else {
                    JRPG.log.debug(datum.name + " found");
                }
            });
        });

        JRPG.log.info("loaded " + getClass().getCanonicalName());
    }

    @Override
    public Type getTypeToken() {
        return new TypeToken<ArrayList<SkillBlockBreak>>() {
        }.getType();
    }

    public void setDefault() {
        storage.add(this);
        name = "mining";
        base = 2;
        modifier = (float) 2;
        BlockSkillDatum testOak = new BlockSkillDatum();
        testOak.name = "minecraft:log";
        testOak.properties.put("variant", "oak");
        testOak.experience = 10;
        entries.add(testOak);

        BlockSkillDatum testBirch = new BlockSkillDatum();
        testBirch.name = "minecraft:log";
        testBirch.properties.put("variant", "birch");
        testBirch.experience = 20;
        entries.add(testBirch);


    }


}

