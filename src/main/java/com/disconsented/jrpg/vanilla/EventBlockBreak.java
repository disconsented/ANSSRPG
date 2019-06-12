package com.disconsented.jrpg.vanilla;

import com.disconsented.jrpg.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

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

        SkillBlockBreak.storage.forEach(skillBlockBreak -> {
            skillBlockBreak.entries.forEach(blockDatum -> {
                if (blockDatum.matches(event.getState())) {
                    List<TraitBlockBreak> traits = TraitBlockBreak.getTraits(event.getState());
                    // trait is required
                    if (traits.size() > 0) {
                        for (TraitBlockBreak trait : traits) {
                            if (player.hasTrait(trait)) {
                                player.awardExperience(blockDatum.experience, skillBlockBreak);
                            } else {
                                player.sendFail();
                                event.setCanceled(true);
                            }
                            continue;
                        }
                    } else {
                        player.awardExperience(blockDatum.experience, skillBlockBreak);
                    }
                }
            });
        });
    }
}
