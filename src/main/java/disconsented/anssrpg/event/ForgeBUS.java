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

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.skill.BlockBreaking;
import disconsented.anssrpg.skill.EntityDamage;
import disconsented.anssrpg.skill.ItemCrafting;

/**
 * @author Disconsented
 *
 */
public class ForgeBUS {
    @SubscribeEvent
    public void onBreakEvent(BreakEvent event) {
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
    
//    @SubscribeEvent
//    public void OnPlayerInteract(PlayerInteractEvent event){
//        
//    }
    
}
