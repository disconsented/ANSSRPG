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
package disconsented.anssrpg.skill;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.data.SkillStore;
import disconsented.anssrpg.handler.PlayerHandler;
import disconsented.anssrpg.perk.Slug;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.skill.objects.ItemSkill;
import disconsented.anssrpg.skill.objects.ItemXP;

/**
 * @author James
 * Handles crafting, both gaining XP and restricting use
 * PlayerOpenContainer needs to handle blocking, checking for the output and what is in the crafting matrix (Maybe to expensive to compute/?) - For now just checking output, remember to ask about matrix gating in feedback
 * I need to add a onCraftingMatrix changed event
 * ItemCrafted handles just giving XP
 */
    public class ItemCrafting{
    	@SubscribeEvent //Assumed that crafting wasn't blocked
	    public void onItemCraftedEvent(ItemCraftedEvent event) { 
    		if(event.player instanceof EntityPlayerMP) {
    			EntityPlayerMP playerMP = (EntityPlayerMP)event.player;
    			PlayerData player = PlayerStore.getInstance().getPlayer(playerMP.getUniqueID().toString());
    			Item item = (Item) ((ItemStack) event.player.openContainer.inventoryItemStacks.get(0)).getItem();
    			ArrayList<Slug> entitylist = PerkStore.getInstance().getSlugs(item);
    			boolean requiresPerk = false;
    			if (entitylist != null){
    				requiresPerk = true;
    			}
    			for (ItemSkill skill : SkillStore.getInstance().getItemSkill()) {
    				ArrayList<ItemXP> temp = skill.getExp();
    				for (int i = 0; i < temp.size(); i++){
    					Item compareItem = ((ItemXP) temp.get(i)).getItem();
    					if(item.equals(compareItem)) {
    						if (requiresPerk){
    							if (PlayerHandler.hasPerk(player, entitylist)){
    									PlayerHandler.awardXP(player, skill.name, temp.get(i).getXp(), playerMP);
    							}
    						}
    						else
    						{
    								PlayerHandler.awardXP(player, skill.name, temp.get(i).getXp(), playerMP);
    						}
    					}
    				}
    			}
    		}
        	
    	}
    	@SubscribeEvent
    	public void onPlayerOpenContainer(PlayerOpenContainerEvent event){    		
    		if(event.entityPlayer instanceof EntityPlayerMP) {
    			if(event.entityPlayer.openContainer instanceof net.minecraft.inventory.ContainerWorkbench && 
    					event.entityPlayer.openContainer.inventoryItemStacks.get(0) != null){
	    			EntityPlayerMP playerMP = (EntityPlayerMP)event.entityPlayer;
	    			PlayerData player = PlayerStore.getInstance().getPlayer(playerMP.getUniqueID().toString());
	    			Item item = (Item) ((ItemStack) event.entityPlayer.openContainer.inventoryItemStacks.get(0)).getItem();
	    			ArrayList<Slug> entitylist = PerkStore.getInstance().getSlugs(item);
	    			boolean requiresPerk = false;
	    			if (entitylist != null){
	    				requiresPerk = true;
	    			}
	    			for (ItemSkill skill : SkillStore.getInstance().getItemSkill()) {
	    				ArrayList<ItemXP> temp = skill.getExp();
	    				for (int i = 0; i < temp.size(); i++){
	    					Item compareItem = ((ItemXP) temp.get(i)).getItem();
	    					if(item.equals(compareItem)) {
	    						if (requiresPerk){
	    							if (PlayerHandler.hasPerk(player, entitylist)){

	    							}
	    							else
	    							{
	    								PlayerHandler.taskFail((EntityPlayer) playerMP);
	    								event.entityPlayer.closeScreen();
	    								break;
	    							}
	    						}
	    					}
	    				}
	    			}
    		}
    		}
	        	
    	} 
    }	    
    