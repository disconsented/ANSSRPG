package Disconsented.ANSSRPG;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
	
    public class ANSSRPGEventHandler{
    Map expMap = new HashMap(); 
    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event) {
                    if (event.entity instanceof EntityPlayer) {
                            if (PlayerInformation.get((EntityPlayer) event.entity) == null)
                                    PlayerInformation.register((EntityPlayer) event.entity);
                    }
            }
    @SubscribeEvent
    public void onBreakevent(BreakEvent eventBreak){     
             if (eventBreak.getPlayer() instanceof EntityPlayer) {
             EntityPlayer ent = eventBreak.getPlayer();
             PlayerInformation playerInfo = PlayerInformation.get(eventBreak.getPlayer());
     		  for (Integer skillLoop = 0; skillLoop <= Main.skillInfo.size()-1; skillLoop++){ // Skill loop
               	if (Main.skillInfo.get(skillLoop).type == (byte)1){
               		blockCheck:
               		for (Integer blockLoop = 0; blockLoop <= Main.skillInfo.get(skillLoop).itemName.size()-1; blockLoop++){ // Block loop               			
               			if (eventBreak.block.getUnlocalizedName().equals(Main.skillInfo.get(skillLoop).itemName.get(blockLoop))){
               				if (playerInfo.getLevel(playerInfo.getXP(Main.skillInfo.get(skillLoop).name)) < (Integer) Main.skillInfo.get(skillLoop).req.get(blockLoop)){
               					ent.addChatComponentMessage(new ChatComponentText("Your skill ("+playerInfo.getLevel(playerInfo.getXP(Main.skillInfo.get(skillLoop).name))+") is not high enough to break "+eventBreak.block.getLocalizedName()+"("+Main.skillInfo.get(skillLoop).req.get(blockLoop)+")"));
               					eventBreak.setCanceled(true);
               				}else{
               				ent.addChatComponentMessage(new ChatComponentText(Main.skillInfo.get(skillLoop).exp.get(blockLoop)+" experience added to: "+ Main.skillInfo.get(skillLoop).name));
               				playerInfo.addXP((Integer) Main.skillInfo.get(skillLoop).exp.get(blockLoop), Main.skillInfo.get(skillLoop).name);
        					if (playerInfo.canLevelUp((Integer) Main.skillInfo.get(skillLoop).exp.get(blockLoop), Main.skillInfo.get(skillLoop).name)){
        						ent.addChatComponentMessage(new ChatComponentText("Your skill "+Main.skillInfo.get(skillLoop).name+" has reached level: "+ playerInfo.getLevel(playerInfo.getXP(Main.skillInfo.get(skillLoop).name))));
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
    public void onPlayerOpenContainer(PlayerOpenContainerEvent event){ //Used for canceling the crafting of items, may (aka probably) delete items. This may be a forge bug
		EntityPlayer player = event.entityPlayer;
        PlayerInformation playerInfo = PlayerInformation.get(event.entityPlayer);
        if (event.entityPlayer.openContainer.getClass().equals(ContainerWorkbench.class)){
        	for (Integer skillLoop = 0; skillLoop <= Main.skillInfo.size()-1; skillLoop++){ // Skill loop
        		if (Main.skillInfo.get(skillLoop).type == (byte)3){
        			entityCheck:
        				for (Integer entityLoop = 0; entityLoop <= Main.skillInfo.get(skillLoop).itemName.size()-1; entityLoop++){ // Item loop
        					if (event.entityPlayer.openContainer.inventoryItemStacks.get(0) != null){   
        						int xpos = event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().indexOf("x");
        						int atpos = event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().indexOf("@");              
        						if ( event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().substring(xpos+1, atpos).equals(Main.skillInfo.get(skillLoop).itemName.get(entityLoop)) ){
        							if (playerInfo.getLevel(playerInfo.getXP(Main.skillInfo.get(skillLoop).name)) <  (Integer)Main.skillInfo.get(skillLoop).req.get(entityLoop)){
        							event.entityPlayer.closeScreen();
        							player.addChatComponentMessage(new ChatComponentText("Your skill ("+playerInfo.getLevel(playerInfo.getXP(Main.skillInfo.get(skillLoop).name))+") is not high enough to craft "+event.entityPlayer.openContainer.getSlot(0)+"("+Main.skillInfo.get(skillLoop).req.get(entityLoop)+")"));
        							}
        							break entityCheck;
        						}
        						else{
        							System.out.println(event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().substring(xpos+1, atpos)+"=/="+Main.skillInfo.get(skillLoop).itemName.get(entityLoop));
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
    	for (Integer skillLoop = 0; skillLoop <= Main.skillInfo.size()-1; skillLoop++){ // Skill loop
    		if (Main.skillInfo.get(skillLoop).type == (byte)3){
    			entityCheck:
    				for (Integer entityLoop = 0; entityLoop <= Main.skillInfo.get(skillLoop).itemName.size()-1; entityLoop++){ // Item loop         
    						if ( event.crafting.getItem().getUnlocalizedName().equals(Main.skillInfo.get(skillLoop).itemName.get(entityLoop)) ){ 					
    							playerInfo.addXP((Integer) Main.skillInfo.get(skillLoop).exp.get(entityLoop), Main.skillInfo.get(skillLoop).name);
    							player.addChatComponentMessage(new ChatComponentText(Main.skillInfo.get(skillLoop).exp.get(entityLoop)+" experience added to: "+ Main.skillInfo.get(skillLoop).name));
    							player.addChatComponentMessage(new ChatComponentText("Skill experience: "+playerInfo.getXP(Main.skillInfo.get(skillLoop).name)));
            					if (playerInfo.canLevelUp((Integer) Main.skillInfo.get(skillLoop).exp.get(entityLoop), Main.skillInfo.get(skillLoop).name)){
            						player.addChatComponentMessage(new ChatComponentText("Your skill "+Main.skillInfo.get(skillLoop).name+" has reached level: "+ playerInfo.getLevel(playerInfo.getXP(Main.skillInfo.get(skillLoop).name))));
            					}
    							break entityCheck;
    						}
    						else{
    							System.out.println(event.crafting.getItem().getUnlocalizedName()+"=/="+Main.skillInfo.get(skillLoop).itemName.get(entityLoop));
    						}
    				}

    			}
    		}
    	}
    	
    
    @SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		EntityPlayer entityPlayer;
		if (event.entityLiving instanceof EntityPlayer){
			PlayerInformation playerInfo = PlayerInformation.get((EntityPlayer) event.entity);
			for(int i = 0; i < Main.skillInfo.size()-1;i++ ){
				expMap.put(event.entity.getUniqueID()+Main.skillInfo.get(i).name, playerInfo.getXP(Main.skillInfo.get(i).name));
			}			
		}
			System.out.println(event.source.getEntity());
	    	if (event.source.getEntity() instanceof EntityPlayer){
	    		EntityPlayer player = (EntityPlayer) event.source.getEntity();	    		
             PlayerInformation playerInfo = PlayerInformation.get((EntityPlayer) event.source.getEntity());
    		for (Integer skillLoop = 0; skillLoop <= Main.skillInfo.size()-1; skillLoop++){ // Skill loop
    			if (Main.skillInfo.get(skillLoop).type == (byte)2){
    				entityCheck:
    			for (Integer entityLoop = 0; entityLoop <= Main.skillInfo.get(skillLoop).itemName.size()-1; entityLoop++){ // Block loop
    				System.out.println(event.entity.getCommandSenderName()+"=/="+Main.skillInfo.get(skillLoop).itemName.get(entityLoop));
    				if ( event.entity.getCommandSenderName().equals(Main.skillInfo.get(skillLoop).itemName.get(entityLoop)) ){    					
    					playerInfo.addXP((Integer) Main.skillInfo.get(skillLoop).exp.get(entityLoop), Main.skillInfo.get(skillLoop).name);
    					if (playerInfo.canLevelUp((Integer) Main.skillInfo.get(skillLoop).exp.get(entityLoop), Main.skillInfo.get(skillLoop).name)){
    						player.addChatComponentMessage(new ChatComponentText("Your skill "+Main.skillInfo.get(skillLoop).name+" has reached level: "+ playerInfo.getLevel(playerInfo.getXP(Main.skillInfo.get(skillLoop).name))));
    					}
    					player.addChatComponentMessage(new ChatComponentText(Main.skillInfo.get(skillLoop).exp.get(entityLoop)+" experience added to: "+ Main.skillInfo.get(skillLoop).name));
    					player.addChatComponentMessage(new ChatComponentText("Skill experience: "+playerInfo.getXP(Main.skillInfo.get(skillLoop).name)));
    					break entityCheck;
    				}
    				
    			}
    			}
    		}
    	}
	}

    @SubscribeEvent
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event){    		
    		if (event.entity instanceof EntityPlayer){
    			System.out.println("Player Respawn detected");
    			PlayerInformation playerInfo = PlayerInformation.get((EntityPlayer) event.entity);
    			for(int i = 0; i < Main.skillInfo.size();i++ ){
    				if(expMap.get(event.entity.getUniqueID()+Main.skillInfo.get(i).name) != null ){
    					playerInfo.setXP((Integer) expMap.get(event.entity.getUniqueID()+Main.skillInfo.get(i).name), Main.skillInfo.get(i).name);
    				}
    			}
    		}
		}
    }