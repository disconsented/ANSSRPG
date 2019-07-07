package com.disconsented.jrpg.vanilla;

import com.disconsented.jrpg.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventBlockBreak {

    @SubscribeEvent
    public static void breakEvent(BlockEvent.BreakEvent event) {
        // Don't want to process fake players at this point
        if (event.getPlayer() instanceof FakePlayer) {
            return;
        }
        Player player = Player.getPlayer(event.getPlayer());
        if (player == null) {
            return;
        }

        for (TraitBlockBreak trait : TraitBlockBreak.storage) {
            if (trait.datum.matches(event.getState())) {
                event.setCanceled(true);
                player.sendFail();
                return;
            }
        }

        SkillBlockBreak.storage.forEach(skillBlockBreak -> {
            skillBlockBreak.entries.forEach(blockDatum -> {
                if (blockDatum.matches(event.getState())) {
                    player.awardExperience(blockDatum.experience, skillBlockBreak);
                }
            });
        });
    }
}
