package disconsented.anssrpg.skill;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import disconsented.anssrpg.Main;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.data.SkillStore;
import disconsented.anssrpg.handler.PlayerHandler;
import disconsented.anssrpg.perk.EntityPerk;
import disconsented.anssrpg.perk.ItemPerk;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.skill.objects.EntitySkill;
import disconsented.anssrpg.skill.objects.EntityXP;
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
    	@SubscribeEvent
    	public void onPlayerOpenContainer(PlayerOpenContainerEvent event){    		
    		if(event.entityPlayer instanceof EntityPlayerMP) {
    			if(event.entityPlayer.openContainer instanceof net.minecraft.inventory.ContainerWorkbench && 
    					event.entityPlayer.openContainer.inventoryItemStacks.get(0) != null){
	    			EntityPlayerMP playerMP = (EntityPlayerMP)event.entityPlayer;
	    			PlayerData player = PlayerStore.getInstance().getPlayer(playerMP.getUniqueID().toString());
	    			Item item = (Item) ((ItemStack) event.entityPlayer.openContainer.inventoryItemStacks.get(0)).getItem();
	    			ArrayList<ItemPerk> entitylist = PerkStore.getPerksForItem(item.getUnlocalizedName());
	    			boolean requiresPerk = false;
	    			if (entitylist != null){
	    				requiresPerk = true;
	    			}
	    			for (ItemSkill skill : SkillStore.getInstance().getItemSkill()) {
	    				ArrayList<ItemXP> temp = skill.getExp();
	    				for (int i = 0; i < temp.size(); i++){
	    					Item compareItem = temp.get(i).getItem();
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
    	@SubscribeEvent //Assumed that crafting wasn't blocked
	    public void onItemCraftedEvent(ItemCraftedEvent event) { 
    		if(event.player instanceof EntityPlayerMP) {
    			EntityPlayerMP playerMP = (EntityPlayerMP)event.player;
    			PlayerData player = PlayerStore.getInstance().getPlayer(playerMP.getUniqueID().toString());
    			Item item = (Item) ((ItemStack) event.player.openContainer.inventoryItemStacks.get(0)).getItem();
    			ArrayList<ItemPerk> entitylist = PerkStore.getPerksForItem(item.getUnlocalizedName());
    			boolean requiresPerk = false;
    			if (entitylist != null){
    				requiresPerk = true;
    			}
    			for (ItemSkill skill : SkillStore.getInstance().getItemSkill()) {
    				ArrayList<ItemXP> temp = skill.getExp();
    				for (int i = 0; i < temp.size(); i++){
    					Item compareItem = temp.get(i).getItem();
    					if(item.equals(compareItem)) {
    						if (requiresPerk){
    							if (PlayerHandler.hasPerk(player, entitylist)){
//    								if (event.entity.isDead){
    									PlayerHandler.awardXP(player, skill.name, temp.get(i).getXp(), playerMP);
//    								}
    							}
//    							else
//    							{
//    								PlayerHandler.taskFail((EntityPlayer) playerMP);
//    								event.player.closeScreen();
//    							}
    						}
    						else
    						{
//    							if (event.entity.isDead){
    								PlayerHandler.awardXP(player, skill.name, temp.get(i).getXp(), playerMP);
//    							}
    						}
    					}
    				}
    			}
    		}
        	
    	} 
    }	    
    