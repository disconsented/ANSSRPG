package disconsented.anssrpg.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import disconsented.anssrpg.data.DataSave;
import disconsented.anssrpg.handler.PlayerHandler;
import disconsented.anssrpg.skill.BlockBreaking;
import disconsented.anssrpg.skill.EntityDamage;
import disconsented.anssrpg.skill.ItemCrafting;
import disconsented.anssrpg.task.TaskMaster;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.event.world.BlockEvent;

public class EventHandler {

    public static class FMLBUS {

        @SubscribeEvent
        public void onPlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
            new DataSave().onPlayerRespawnEvent(event);
        }

        @SubscribeEvent
        public void onPlayerLoggedOutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
            new DataSave().onPlayerLoggedOutEvent(event);
        }

        @SubscribeEvent
        public void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
            new DataSave().onPlayerLoggedInEvent(event);
            new PlayerHandler().reactivatePerks(event);
            new PlayerHandler().checkPlayerSkills(event);
        }

        @SubscribeEvent
        public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event) {
            new ItemCrafting().onItemCraftedEvent(event);
        }

        @SubscribeEvent
        public void onTickEvent(TickEvent event) {
            TaskMaster.getInstance().process(event);
        }
    }

    public static class ForgeBUS {

        @SubscribeEvent
        public void onBreakEvent(BlockEvent.BreakEvent event) {
            new BlockBreaking().onBreakEvent(event);
        }

        @SubscribeEvent
        public void onLivingHurtEvent(LivingHurtEvent event) {
            new EntityDamage().onLivingHurtEvent(event);
        }

        @SubscribeEvent
        public void onLivingDeathEvent(LivingDeathEvent event) {
            new EntityDamage().onLivingDeathEvent(event);
        }

        @SubscribeEvent
        public void onPlayerOpenCrafting(PlayerOpenContainerEvent event) {
            new ItemCrafting().onPlayerOpenCrafting(event);
        }
    }
}
