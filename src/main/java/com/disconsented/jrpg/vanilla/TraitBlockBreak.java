package com.disconsented.jrpg.vanilla;

import com.disconsented.jrpg.JRPG;
import com.disconsented.jrpg.Trait;
import com.google.common.reflect.TypeToken;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TraitBlockBreak extends Trait {
    protected static transient ArrayList<TraitBlockBreak> storage = new ArrayList<>();
    protected BlockTraitDatum datum;
    protected transient String name;

    static ArrayList<TraitBlockBreak> getTraits(IBlockState state) {
        return new ArrayList<>(storage.parallelStream().filter(traitBlockBreak -> traitBlockBreak.datum.matches(state)).collect(Collectors.toList()));
    }

    @Override
    public void load() {
        storage.forEach(traitBlockBreak -> {
            traitBlockBreak.datum.block = Block.REGISTRY.getObject(new ResourceLocation(traitBlockBreak.datum.name));
            if (traitBlockBreak.datum == null) {
                JRPG.log.error(traitBlockBreak.name + " did not resolve into a block");
            } else {
                JRPG.log.debug(traitBlockBreak.name + " found");
            }
        });
        JRPG.log.info("loaded " + getClass().getCanonicalName());

    }

    public void setDefault() {
        storage.add(this);
        name = "Gimmie Birch";

        datum = new BlockTraitDatum();
        datum.name = "minecraft:log";
        datum.properties.put("variant", "birch");

    }

    @Override
    public Type getTypeToken() {
        return new TypeToken<ArrayList<TraitBlockBreak>>() {
        }.getType();
    }
}
