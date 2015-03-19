package disconsented.anssrpg.skill;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemCoal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import disconsented.anssrpg.common.Logging;

public class Smelting {
    /*Smelting event will always give XP to the person who is tagged on the stack*/

//    /*Two part skill basicly the same as item crafting*/
//    @SubscribeEvent
//    public void smeltingEvent(ItemSmeltedEvent event) {
//        event.smelting.stackSize = 60;
//        Logging.info(event.isCancelable());
//        Logging.info(event.getPhase());
//        Logging.info(event.getResult());
//        Logging.info(event.toString());
//    }
//    @SubscribeEvent
//    public void onPlayerOpenCrafting(PlayerOpenContainerEvent event) {
//        if (event.entityPlayer instanceof EntityPlayerMP) {
//            Container container = event.entityPlayer.openContainer;
//            if (container instanceof  net.minecraft.inventory.ContainerFurnace){
//                ItemStack stack = (ItemStack) container.inventoryItemStacks.get(0);
//                if (stack != null){
//                    if (FurnaceRecipes.smelting().getSmeltingResult(stack).getItem() instanceof ItemCoal){
//                        EntityItem entityitem = new EntityItem(event.entityPlayer.worldObj, event.entityPlayer.lastTickPosX, event.entityPlayer.lastTickPosY, event.entityPlayer.lastTickPosZ, stack);
//                        event.entityPlayer.worldObj.spawnEntityInWorld(entityitem);
//                        container.inventoryItemStacks.set(0, new ItemStack());
//                    }                    
//                }
//            }
//        }
//    }
}
