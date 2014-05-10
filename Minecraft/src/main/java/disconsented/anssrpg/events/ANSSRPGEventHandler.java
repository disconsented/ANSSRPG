package disconsented.anssrpg.events;
/**
 * @author James
 * Handles when to add experience and blocking of events
 */
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import disconsented.anssrpg.config.ConfigurationHandler;
import disconsented.anssrpg.data.PlayerInformation;
	
    public class ANSSRPGEventHandler{
    Map expMap = new HashMap(); 
    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event) {
                    if (event.entity instanceof EntityPlayer) {
                            if (PlayerInformation.get((EntityPlayer) event.entity) == null)
                                    PlayerInformation.register((EntityPlayer) event.entity);
                    }
            }
    private void doCheck(Event event){
    	
    }
    
    @SubscribeEvent
    public void onBreakevent(BreakEvent eventBreak){  
             if (eventBreak.getPlayer() instanceof EntityPlayer) {
             EntityPlayer ent = eventBreak.getPlayer();
             PlayerInformation playerInfo = PlayerInformation.get(eventBreak.getPlayer());
     		  for (Integer skillLoop = 0; skillLoop < ConfigurationHandler.skillInfo.size(); skillLoop++){ // Skill loop
     			  System.out.println(ConfigurationHandler.skillInfo.get(skillLoop).itemName);
               	if ((byte)ConfigurationHandler.skillInfo.get(skillLoop).type == (byte)1){
               		
               		blockCheck:	for (Integer blockLoop = 0; blockLoop < ConfigurationHandler.skillInfo.get(skillLoop).itemName.size(); blockLoop++){ // Block loop     
               			if (eventBreak.block.getUnlocalizedName().equals(ConfigurationHandler.skillInfo.get(skillLoop).itemName.get(blockLoop))){             				
               				if (playerInfo.getLevel(playerInfo.getXP(ConfigurationHandler.skillInfo.get(skillLoop).name)) < (Integer)ConfigurationHandler.skillInfo.get(skillLoop).req.get(blockLoop)){
               					ent.addChatComponentMessage(new ChatComponentText("Your skill ("+playerInfo.getLevel(playerInfo.getXP(ConfigurationHandler.skillInfo.get(skillLoop).name))+") is not high enough to break "+eventBreak.block.getLocalizedName()+"("+ConfigurationHandler.skillInfo.get(skillLoop).req.get(blockLoop)+")"));
               					eventBreak.setCanceled(true);
               				}else{
               					ent.addChatComponentMessage(new ChatComponentText(ConfigurationHandler.skillInfo.get(skillLoop).exp.get(blockLoop)+" experience added to: "+ ConfigurationHandler.skillInfo.get(skillLoop).name));
               					playerInfo.addXP((Integer) ConfigurationHandler.skillInfo.get(skillLoop).exp.get(blockLoop), ConfigurationHandler.skillInfo.get(skillLoop).name);
               					if (playerInfo.canLevelUp((Integer) ConfigurationHandler.skillInfo.get(skillLoop).exp.get(blockLoop), ConfigurationHandler.skillInfo.get(skillLoop).name)){
               						ent.addChatComponentMessage(new ChatComponentText("Your skill "+ConfigurationHandler.skillInfo.get(skillLoop).name+" has reached level: "+ playerInfo.getLevel(playerInfo.getXP(ConfigurationHandler.skillInfo.get(skillLoop).name))));
               					}
               				}
               				break blockCheck;
             			}
             		}
             	}
             }
         } 
    }
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
    	
    
    @SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
	if (event.source.getEntity() instanceof EntityPlayer){
	EntityPlayer player = (EntityPlayer) event.source.getEntity();	    		
    PlayerInformation playerInfo = PlayerInformation.get((EntityPlayer) event.source.getEntity());
    for (Integer skillLoop = 0; skillLoop < ConfigurationHandler.skillInfo.size(); skillLoop++){ // Skill loop
    	if (ConfigurationHandler.skillInfo.get(skillLoop).type == (byte)2){
    		entityCheck:
    		for (Integer entityLoop = 0; entityLoop < ConfigurationHandler.skillInfo.get(skillLoop).itemName.size(); entityLoop++){ // Block loop
    			System.out.println(event.entity.getCommandSenderName()+"=/="+ConfigurationHandler.skillInfo.get(skillLoop).itemName.get(entityLoop));
    			if ( event.entity.getCommandSenderName().equals(ConfigurationHandler.skillInfo.get(skillLoop).itemName.get(entityLoop)) ){    					
    				playerInfo.addXP((Integer) ConfigurationHandler.skillInfo.get(skillLoop).exp.get(entityLoop), ConfigurationHandler.skillInfo.get(skillLoop).name);
    				if (playerInfo.canLevelUp((Integer) ConfigurationHandler.skillInfo.get(skillLoop).exp.get(entityLoop), ConfigurationHandler.skillInfo.get(skillLoop).name)){
    					player.addChatComponentMessage(new ChatComponentText("Your skill "+ConfigurationHandler.skillInfo.get(skillLoop).name+" has reached level: "+ playerInfo.getLevel(playerInfo.getXP(ConfigurationHandler.skillInfo.get(skillLoop).name))));
    				}
    				player.addChatComponentMessage(new ChatComponentText(ConfigurationHandler.skillInfo.get(skillLoop).exp.get(entityLoop)+" experience added to: "+ ConfigurationHandler.skillInfo.get(skillLoop).name));
    				player.addChatComponentMessage(new ChatComponentText("Skill experience: "+playerInfo.getXP(ConfigurationHandler.skillInfo.get(skillLoop).name)));
    				break entityCheck;
    			}
    		}
    	}
    }
   }
}
}