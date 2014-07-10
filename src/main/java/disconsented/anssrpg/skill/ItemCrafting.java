package disconsented.anssrpg.skill;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import disconsented.anssrpg.perk.PerkStore;
import disconsented.anssrpg.player.PlayerHandler;

/**
 * @author James
 * Handles crafting, both gaining XP and restricting use
 */



	
    public class ItemCrafting{
    	@SubscribeEvent
    	public void onPlayerOpenContainer(PlayerOpenContainerEvent event){
    	Boolean requiresPerk = false;
    	if (event.getPlayer() instanceof EntityPlayerMP){
    		if(PerkStore.getBlockPerkList((Block)event.block) != null){
    			requiresPerk = true;
    		}
    		for (int i = 0; i < SkillHandler.getSkillList().size(); i++){
    			Skill temp = (Skill)SkillHandler.getSkillList().get(i);
    			if(temp.type == 1){
    				int blockIndex = SkillHandler.indexOfBlock((Block)event.block, i); 
    				if (requiresPerk){
    					if (requiresPerk){
	    						if (PlayerHandler.hasPerk(event.block, SkillHandler.getSkillName(i))){//Player can mine
	        						//PlayerHandler.addXP(SkillHandler.getXP(blockIndex, i), SkillHandler.getSkillName(i), (EntityPlayerMP) event.getPlayer());
	        					}else{    					
	        						PlayerHandler.sendFail((EntityPlayerMP) event.getPlayer());
	        						//event.setCanceled(true);
	        					}
    					}else{
    						PlayerHandler.addXP(SkillHandler.getXP(blockIndex, i), SkillHandler.getSkillName(i), (EntityPlayerMP) event.getPlayer());
    					}
    				}    				
    			}
    		}
    	}
    }
}
    
    	/*@SubscribeEvent
        public void onPlayerOpenContainer(PlayerOpenContainerEvent event){ //Used for canceling the crafting of items
    		if (event.entityPlayer instanceof EntityPlayerMP){
        		EntityPlayerMP player = (EntityPlayerMP) event.entityPlayer;
        		for(int i = 0; i < SkillHandler.skillCount(); i++){
        			int itemIndex = SkillHandler.indexOfItem((Item)event.entityPlayer.openContainer.inventoryItemStacks.get(0));
        			if (itemIndex != -1){
        				if (PlayerHandler.hasPerk((Item)event.entityPlayer.openContainer.inventoryItemStacks.get(0), SkillHandler.getSkillName(i))){//Player can craft
        					//PlayerHandler.addXP(SkillHandler.getXP(itemIndex, i), SkillHandler.getSkillName(i), player);
        					}
        				else{    					
        					PlayerHandler.sendFail(player);
        					event.entityPlayer.closeScreen();
        					}
        				}
        			}
        		}
        	}
    @SubscribeEvent
    public void onItemCraftedEvent(ItemCraftedEvent event) { // Used for giving players experience from crafting
    	if (event.player instanceof EntityPlayerMP){
    		EntityPlayerMP player = (EntityPlayerMP) event.player;
    		for(int i = 0; i < SkillHandler.skillCount(); i++){
    			int itemIndex = SkillHandler.indexOfItem((Item)player.openContainer.inventoryItemStacks.get(0));
    			if (itemIndex != -1){
    				if (PlayerHandler.hasPerk((Item)event.player.openContainer.inventoryItemStacks.get(0), SkillHandler.getSkillName(i))){//Player can craft
    					PlayerHandler.addXP(SkillHandler.getXP(itemIndex, i), SkillHandler.getSkillName(i), player);
    					}
    				}
    			}
    		}
    	}
    
    }*/