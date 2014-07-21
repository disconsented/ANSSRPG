package disconsented.anssrpg.skill;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import disconsented.anssrpg.perk.PerkStore;
import disconsented.anssrpg.player.PlayerHandler;

/**
 * @author James
 * Handles crafting, both gaining XP and restricting use
 * PlayerOpenContainer needs to handle blocking, checking for the output and what is in the crafting matrix (Maybe to expensive to compute/?) - For now just checking output, remember to ask about matrix gating in feedback
 * I need to add a onCraftingMatrix changed event
 * ItemCrafted handles just giving XP
 */
    public class ItemCrafting{
    	@SubscribeEvent
    	public void onPlayerOpenContainer(PlayerOpenContainerEvent event){
        	Boolean requiresPerk = false;
	        	if (event.entityPlayer instanceof EntityPlayerMP){
	        		EntityPlayerMP player = (EntityPlayerMP) event.entityPlayer;
	        		if (event.entityPlayer.openContainer instanceof net.minecraft.inventory.ContainerWorkbench){
		        		if(event.entityPlayer.openContainer.inventoryItemStacks.get(0) != null){
		        			Item item = ((ItemStack)event.entityPlayer.openContainer.inventoryItemStacks.get(0)).getItem();
			        		if(PerkStore.getItemPerkList(item) != null){
			        			requiresPerk = true;
			        		}
			        		for (int i = 0; i < SkillHandler.getSkillList().size(); i++){
			        			Skill temp = (Skill)SkillHandler.getSkillList().get(i);
			        			if(temp.type == 3){
			        				int itemIndex = SkillHandler.indexOfItem(item, i);
			        				if (itemIndex != -1 || requiresPerk){
			        					if (requiresPerk){
			    	    						if (!PlayerHandler.hasPerk(item, SkillHandler.getSkillName(i))){//Player can mine
			    	    							PlayerHandler.sendFail(player);
			    	    							player.closeScreen();
			    	    						}
			        					}    				
			        				}
			        			}
			        		}
		        		}
	        		}	
	        	}
    	}
    	@SubscribeEvent //Assumed that crafting wasn't blocked
	    public void onItemCraftedEvent(ItemCraftedEvent event) { // Used for giving players experience from crafting
        	if (event.player instanceof EntityPlayerMP){
        		EntityPlayerMP player = (EntityPlayerMP) event.player;
	        			Item item = event.crafting.getItem();
		        		for (int i = 0; i < SkillHandler.getSkillList().size(); i++){
		        			Skill temp = (Skill)SkillHandler.getSkillList().get(i);
		        			if(temp.type == 3){
		        				int itemIndex = SkillHandler.indexOfItem(item, i);
		        				if (itemIndex != -1){		    	    						
		        					PlayerHandler.addXP(SkillHandler.getXP(itemIndex, i), SkillHandler.getSkillName(i), player);
		        				}   				
		        			}
		        		}
        	}
    	} 
    }	    
    