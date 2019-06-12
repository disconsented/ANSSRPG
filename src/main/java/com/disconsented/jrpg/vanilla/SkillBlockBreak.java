package com.disconsented.jrpg.vanilla;

import com.disconsented.jrpg.JRPG;
import com.disconsented.jrpg.Skill;
import com.disconsented.jrpg.SkillDatum;
import com.google.common.reflect.TypeToken;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;


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
        base = 1;
        modifier = (float) 1.1;
        BlockSkillDatum test = new BlockSkillDatum();
        test.name = "minecraft:log";
        test.properties.put("variant", "oak");
        test.experience = 10;
        entries.add(test);
    }

    class BlockSkillDatum extends SkillDatum {
        transient Block block;


        BlockSkillDatum() {
        }

        //
        boolean matches(IBlockState state) {
            if (state.getBlock() != block) {
                return false;
            }

            for (Map.Entry<String, String> entry : this.properties.entrySet()) {
                Optional<Map.Entry<IProperty<?>, Comparable<?>>> variant = state.getProperties().entrySet().stream().filter(prop -> prop.getKey().getName().equalsIgnoreCase(entry.getKey())).findFirst();
                if (variant.isPresent()) {
                    if (!variant.get().getValue().toString().equals(entry.getValue())) {
                        return false;
                    }
                }
            }

            return true;
        }
    }


}

