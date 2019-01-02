package com.disconsented.jrpg.vanilla;

import com.disconsented.jrpg.Datum;
import com.disconsented.jrpg.Player;
import com.disconsented.jrpg.Skill;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class SkillBlockBreak extends Skill {

    @SubscribeEvent
    public static void Break(BlockEvent.BreakEvent event) {
        Player player = Player.getPlayer(event.getPlayer());
        player.awardExperience(1337, new SkillBlockBreak());
    }

    @Override
    public void load() {

    }

    public void setDefault() {
        storage.add(this);
        name = "TestName";
        base = 1;
        modifier = (float) 1.1;
        BlockDatum test = new BlockDatum();
        test.resourceLocation = new ResourceLocation("", "anvil");
        entries.add(new BlockDatum());
    }

    class BlockDatum extends Datum {
        transient Block block;
        ResourceLocation resourceLocation;
    }


}

