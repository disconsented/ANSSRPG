package com.disconsented.jrpg.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

abstract class BlockDatum {
    public Map<String, String> properties = new HashMap<>();
    protected String name;
    transient Block block;


    BlockDatum() {
    }

    boolean matches(IBlockState state) {
        if (state.getBlock() != block) {
            return false;
        }

        for (Map.Entry<String, String> entry : this.properties.entrySet()) {
            Optional<Map.Entry<IProperty<?>, Comparable<?>>> variant = state.getProperties().entrySet().stream().filter(prop -> prop.getKey().getName().equalsIgnoreCase(entry.getKey())).findFirst();
            if (variant.isPresent() && !variant.get().getValue().toString().equals(entry.getValue())) {
                return false;
            }
        }

        return true;
    }
}

class BlockSkillDatum extends BlockDatum {
    long experience;

}

class BlockTraitDatum extends BlockDatum {

}