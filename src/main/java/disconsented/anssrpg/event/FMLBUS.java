/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package disconsented.anssrpg.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import disconsented.anssrpg.data.DataSave;
import disconsented.anssrpg.handler.PlayerHandler;
import disconsented.anssrpg.skill.ItemCrafting;
import disconsented.anssrpg.task.TaskMaster;

/**
 * @author Disconsented
 *
 */
public class FMLBUS {
    
    @SubscribeEvent
    public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
        new DataSave().onPlayerRespawnEvent(event);
    }
    
    @SubscribeEvent
    public void onPlayerLoggedOutEvent(PlayerLoggedOutEvent event) {
        new DataSave().onPlayerLoggedOutEvent(event);
    }

    @SubscribeEvent
    public void onPlayerLoggedInEvent(PlayerLoggedInEvent event) {
        new DataSave().onPlayerLoggedInEvent(event);
        new PlayerHandler().reactivatePerks(event);
    }
    
    @SubscribeEvent
    public void onItemCraftedEvent(ItemCraftedEvent event) {
        new ItemCrafting().onItemCraftedEvent(event);
    }
    
    @SubscribeEvent 
    public void onTickEvent(TickEvent event){
    	TaskMaster.getInstance().process(event);
    }
}
