package com.disconsented.jrpg.vanilla;

import com.disconsented.jrpg.Player;
import com.disconsented.jrpg.Skill;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class BlockBreakSkill extends Skill {


    @SubscribeEvent
    public static void Break(BlockEvent.BreakEvent event) {
        Player player = Player.getPlayer(event.getPlayer());
        player.awardExperience(1337, new BlockBreakSkill());
    }
}

