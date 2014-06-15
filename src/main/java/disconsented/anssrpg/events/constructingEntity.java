package disconsented.anssrpg.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.data.PlayerInformation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

/**
 * @author James
 * Not %100 sure what this does, its just copy pasta code
 */
    public class constructingEntity{ 
    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event) {
                    if (event.entity instanceof EntityPlayer) {
                            if (PlayerInformation.get((EntityPlayer) event.entity) == null)
                                    PlayerInformation.register((EntityPlayer) event.entity);
                    }
            }
}