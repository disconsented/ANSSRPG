package disconsented.anssrpg.skill;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import disconsented.anssrpg.common.Logging;

public class Smelting {

    /*Two part skill basicly the same as item crafting*/
    @SubscribeEvent
    public void smeltingEvent(ItemSmeltedEvent event) {
        event.smelting.stackSize = 60;
        Logging.info(event.isCancelable());
        Logging.info(event.getPhase());
        Logging.info(event.getResult());
        Logging.info(event.toString());
    }

}
