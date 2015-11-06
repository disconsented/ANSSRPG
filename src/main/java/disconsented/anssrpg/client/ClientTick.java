package disconsented.anssrpg.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;

public class ClientTick {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (Data.screenToOpen != null && Data.ticksLeft == 0) {
            Minecraft.getMinecraft().displayGuiScreen(Data.screenToOpen);
            Data.screenToOpen = null;
        } else if (Data.ticksLeft > 0)
            Data.ticksLeft--;
    }
}
