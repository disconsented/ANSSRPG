/*
 * Copyright (c)  2015-2016, James Kerr
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package disconsented.anssrpg.compat.tconstruct;

import disconsented.anssrpg.core.server.common.Logging;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

public class test {
    public test(){}

    @SubscribeEvent
    public void onPlayerOpenCrafting(PlayerOpenContainerEvent event) {
        if (event.getEntity() instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) event.getEntity();
        }
    }
    @SubscribeEvent
    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event) {
        Logging.debug("Crafted!");
        if(event.craftMatrix.getStackInSlot(0) != null && event.craftMatrix.getStackInSlot(0).getItem() instanceof ToolCore){
            for (int i = 1; i < 6; i++) {
                ItemStack stack = event.craftMatrix.getStackInSlot(i);
                if(stack != null ){
                    if(stack.getItem() instanceof ToolPart) {
                        ToolPart part = (ToolPart) stack.getItem();
                        String material = stack.getTagCompound().getString("Material");
                        Logging.debug(material + ":" + part.getCost() / 144);
                    } else {
                        //String material = stack.getTagCompound().getString("Material");
                        Logging.debug(stack.getItem().getUnlocalizedName());
                    }
                }
            }

        } else {
            for (int i = 0; i < 5; i++) {
                ItemStack stack = event.craftMatrix.getStackInSlot(i);
                if(stack != null && stack.getItem() instanceof ToolPart){
                    ToolPart part = (ToolPart) stack.getItem();
                    String material = stack.getTagCompound().getString("Material");
                    Logging.debug(material + ":" + part.getCost()/144);

                }
            }
        }
    }
}
