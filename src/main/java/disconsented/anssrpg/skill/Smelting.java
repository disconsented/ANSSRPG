package disconsented.anssrpg.skill;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;

public class Smelting {

    /*Two part skill basicly the same as item crafting*/
    @SubscribeEvent
    public void smeltingEvent(ItemSmeltedEvent event){
        event.smelting.stackSize = 60;
        System.out.println(event.isCancelable());
        System.out.println(event.getPhase());
        System.out.println(event.getResult());
        System.out.println(event.toString());
    }

}
