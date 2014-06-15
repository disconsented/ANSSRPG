package disconsented.anssrpg.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import disconsented.anssrpg.config.ConfigurationHandler;
import disconsented.anssrpg.data.PlayerInformation;

/**
 * @author James
 * Handles crafting, both gaining XP and restricting use
 */



	
    public class itemCrafting{
    	
    @SubscribeEvent
    public void onPlayerOpenContainer(PlayerOpenContainerEvent event){ //Used for canceling the crafting of items
		EntityPlayer player = event.entityPlayer;
        PlayerInformation playerInfo = PlayerInformation.get(event.entityPlayer);
        if (event.entityPlayer.openContainer.getClass().equals(ContainerWorkbench.class)){
        	for (Integer skillLoop = 0; skillLoop < ConfigurationHandler.skillInfo.size(); skillLoop++){ // Skill loop
        		if (ConfigurationHandler.skillInfo.get(skillLoop).type == (byte)3){
        			entityCheck:
        				for (Integer entityLoop = 0; entityLoop <= ConfigurationHandler.skillInfo.get(skillLoop).itemName.size()-1; entityLoop++){ // Item loop
        					if (event.entityPlayer.openContainer.inventoryItemStacks.get(0) != null){   
        						int xpos = event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().indexOf("x");
        						int atpos = event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().indexOf("@");              
        						if ( event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().substring(xpos+1, atpos).equals(ConfigurationHandler.skillInfo.get(skillLoop).itemName.get(entityLoop)) ){
        							if (playerInfo.getLevel(playerInfo.getXP(ConfigurationHandler.skillInfo.get(skillLoop).name)) <  (Integer)ConfigurationHandler.skillInfo.get(skillLoop).req.get(entityLoop)){
        							event.entityPlayer.closeScreen();
        							player.addChatComponentMessage(new ChatComponentText("Your skill ("+playerInfo.getLevel(playerInfo.getXP(ConfigurationHandler.skillInfo.get(skillLoop).name))+") is not high enough to craft "+ConfigurationHandler.skillInfo.get(skillLoop).itemName.get(entityLoop)+"("+ConfigurationHandler.skillInfo.get(skillLoop).req.get(entityLoop)+")"));
        							}
        							break entityCheck;
        						}
        						else{
        							System.out.println(event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().substring(xpos+1, atpos)+"=/="+ConfigurationHandler.skillInfo.get(skillLoop).itemName.get(entityLoop));
        						}
			
        					}
        				}
        		}
        	}
        }
    }
    @SubscribeEvent
    public void onItemCraftedEvent(ItemCraftedEvent event) { // Used for giving players experience from crafting
		EntityPlayer player = event.player;
        PlayerInformation playerInfo = PlayerInformation.get(event.player);
    	for (Integer skillLoop = 0; skillLoop < ConfigurationHandler.skillInfo.size(); skillLoop++){ // Skill loop
    		System.out.println("Skill Loop:"+skillLoop+ConfigurationHandler.skillInfo.get(skillLoop).name);
    		if (ConfigurationHandler.skillInfo.get(skillLoop).type == (byte)3){
    			entityCheck:
    				for (Integer entityLoop = 0; entityLoop < ConfigurationHandler.skillInfo.get(skillLoop).itemName.size(); entityLoop++){ // Item loop   
    					System.out.println("yo");
    						if ( event.crafting.getItem().getUnlocalizedName().equals(ConfigurationHandler.skillInfo.get(skillLoop).itemName.get(entityLoop)) ){ 					
    							playerInfo.addXP((Integer) ConfigurationHandler.skillInfo.get(skillLoop).exp.get(entityLoop), ConfigurationHandler.skillInfo.get(skillLoop).name);
    							System.out.println(skillLoop);
    							player.addChatComponentMessage(new ChatComponentText(ConfigurationHandler.skillInfo.get(skillLoop).exp.get(entityLoop)+" experience added to: "+ ConfigurationHandler.skillInfo.get(skillLoop).name));
    							player.addChatComponentMessage(new ChatComponentText("Skill experience: "+playerInfo.getXP(ConfigurationHandler.skillInfo.get(skillLoop).name)));
            					if (playerInfo.canLevelUp((Integer) ConfigurationHandler.skillInfo.get(skillLoop).exp.get(entityLoop), ConfigurationHandler.skillInfo.get(skillLoop).name)){
            						player.addChatComponentMessage(new ChatComponentText("Your skill "+ConfigurationHandler.skillInfo.get(skillLoop).name+" has reached level: "+ playerInfo.getLevel(playerInfo.getXP(ConfigurationHandler.skillInfo.get(skillLoop).name))));
            					}
    							break entityCheck;
    						}
    						else{
    							System.out.println(event.crafting.getItem().getUnlocalizedName()+"=/="+ConfigurationHandler.skillInfo.get(skillLoop).itemName.get(entityLoop));
    						}
    				}

    			}
    		}
    	}
}