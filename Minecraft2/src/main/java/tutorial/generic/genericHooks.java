	package tutorial.generic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockDirt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
	
    public class genericHooks{
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
    	//Random randomGenerator = new Random();
             if (eventBreak.getPlayer() instanceof EntityPlayer) {
             EntityPlayer ent = (EntityPlayer) eventBreak.getPlayer();
             PlayerInformation playerInfo = PlayerInformation.get((EntityPlayer) eventBreak.getPlayer());
             
             if (Generic.debugInfo == true){
     		System.out.println("Generic.skillInfo.size" + Generic.skillInfo.size());
     		System.out.println(Generic.skillInfo);
     		for (int i = 0;i < 3;i++){
     			System.out.println(Generic.skillInfo.get(i).name);
     			System.out.println(Generic.skillInfo.get(i).exp);
     			System.out.println(Generic.skillInfo.get(i).req);
     			System.out.println(Generic.skillInfo.get(i).itemName);
     		}
     		}

     		//JOptionPane.showMessageDialog(null,Generic.skillInfo);
     		  for (Integer skillLoop = 0; skillLoop <= Generic.skillInfo.size()-1; skillLoop++){ // Skill loop
     			 System.out.println("SkillLoop:"+skillLoop);
               	if (Generic.skillInfo.get(skillLoop).type == (byte)1){
               		//System.out.println(Generic.skillInfo.get(skillLoop).blockName.size());
               		System.out.println("size:"+Generic.skillInfo.get(skillLoop).itemName.size());
               		blockCheck:
               		for (Integer blockLoop = 0; blockLoop <= Generic.skillInfo.get(skillLoop).itemName.size()-1; blockLoop++){ // Block loop
               			System.out.println("blockloop:"+blockLoop);
               			
               			if (eventBreak.block.getUnlocalizedName().equals(Generic.skillInfo.get(skillLoop).itemName.get(blockLoop))){
               				
               				ent.addChatComponentMessage(new ChatComponentText(Generic.skillInfo.get(skillLoop).exp.get(blockLoop)+" experience added to: "+ Generic.skillInfo.get(skillLoop).name));
               				playerInfo.addXP((Integer) Generic.skillInfo.get(skillLoop).exp.get(blockLoop), Generic.skillInfo.get(skillLoop).name);
               				if (Generic.debugInfo == true){
               					System.out.println("Player experience added:"+Generic.skillInfo.get(skillLoop).exp.get(blockLoop));
               					System.out.println("Player skill:"+Generic.skillInfo.get(skillLoop).name);
               					System.out.println("Player experience:"+playerInfo.getXP((String) Generic.skillInfo.get(skillLoop).name));
               				}
               				break blockCheck;
             			}
             			else
             			{
             				if (Generic.debugInfo == true){
             					System.out.println(eventBreak.block.getUnlocalizedName() +"=/="+ Generic.skillInfo.get(skillLoop).itemName.get(blockLoop));
             					System.out.println(eventBreak.block.getUnlocalizedName());
             			}
             				//ent.addChatComponentMessage(new ChatComponentText(""));
             			}
             		}
             	}
             }
         } 
    }
    @SubscribeEvent
    public void onPlayerOpenContainer(PlayerOpenContainerEvent event){
		EntityPlayer player = (EntityPlayer) event.entityPlayer;
        PlayerInformation playerInfo = PlayerInformation.get((EntityPlayer) event.entityPlayer);
        if (event.entityPlayer.openContainer.getClass().equals(ContainerWorkbench.class)){
        	for (Integer skillLoop = 0; skillLoop <= Generic.skillInfo.size()-1; skillLoop++){ // Skill loop
        		if (Generic.skillInfo.get(skillLoop).type == (byte)3){
        			//System.out.println("Skill type == 3");
        			entityCheck:
        				for (Integer entityLoop = 0; entityLoop <= Generic.skillInfo.get(skillLoop).itemName.size()-1; entityLoop++){ // Item loop
        					if (event.entityPlayer.openContainer.inventoryItemStacks.get(0) != null){   
        						int xpos = event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().indexOf("x");
        						int atpos = event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().indexOf("@");
        						System.out.println(event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().substring(xpos+1, atpos));
                
        						if ( event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().substring(xpos+1, atpos).equals(Generic.skillInfo.get(skillLoop).itemName.get(entityLoop)) ){
        							if (Generic.debugInfo == true){
        								System.out.println(event.entity.getCommandSenderName());
        								System.out.println( event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().substring(xpos+1, atpos));
        								System.out.println( Generic.skillInfo.get(skillLoop).itemName.get(entityLoop));
        							} 
        							if (playerInfo.getLevel(playerInfo.getXP(Generic.skillInfo.get(skillLoop).name)) <  (Integer)Generic.skillInfo.get(skillLoop).req.get(entityLoop)){
        							event.entityPlayer.closeScreen();
        							//playerInfo.addXP((Integer) Generic.skillInfo.get(skillLoop).exp.get(entityLoop), Generic.skillInfo.get(skillLoop).name);
        							player.addChatComponentMessage(new ChatComponentText("You are not skilled enough to craft this"));
        							if (Generic.debugInfo == true){
        								//System.out.println("Player experience:"+playerInfo.getXP((String) Generic.skillInfo.get(skillLoop).name));
        								}
        							}
        							break entityCheck;
        						}
        						else{
        							System.out.println(event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().substring(xpos+1, atpos)+"=/="+Generic.skillInfo.get(skillLoop).itemName.get(entityLoop));
        						}
			
        					}
        					else{
        						//System.out.println(event.entityPlayer.openContainer.inventoryItemStacks.get(0));
        					}
        				}
        		}
        	}
	}
    }
    @SubscribeEvent
    public void onItemCraftedEvent(ItemCraftedEvent event) {
		EntityPlayer player = event.player;
        PlayerInformation playerInfo = PlayerInformation.get(event.player);
    	for (Integer skillLoop = 0; skillLoop <= Generic.skillInfo.size()-1; skillLoop++){ // Skill loop
    		if (Generic.skillInfo.get(skillLoop).type == (byte)3){
    			System.out.println("Skill type == 3");
    			entityCheck:
    				for (Integer entityLoop = 0; entityLoop <= Generic.skillInfo.get(skillLoop).itemName.size()-1; entityLoop++){ // Item loop
    					System.out.println(event.crafting.getItem());
            
    						if ( event.crafting.getItem().getUnlocalizedName().equals(Generic.skillInfo.get(skillLoop).itemName.get(entityLoop)) ){
    							/*if (Generic.debugInfo == true){
    								System.out.println(event.entity.getCommandSenderName());
    								System.out.println( event.entityPlayer.openContainer.inventoryItemStacks.get(0).toString().substring(xpos+1, atpos));
    								System.out.println( Generic.skillInfo.get(skillLoop).itemName.get(entityLoop));
    							}*/    					
    							playerInfo.addXP((Integer) Generic.skillInfo.get(skillLoop).exp.get(entityLoop), Generic.skillInfo.get(skillLoop).name);
    							player.addChatComponentMessage(new ChatComponentText(Generic.skillInfo.get(skillLoop).exp.get(entityLoop)+" experience added to: "+ Generic.skillInfo.get(skillLoop).name));
    							if (Generic.debugInfo == true){
    								//System.out.println("Player experience:"+playerInfo.getXP((String) Generic.skillInfo.get(skillLoop).name));
    								}
    							break entityCheck;
    						}
    						else{
    							System.out.println(event.crafting.getItem().getUnlocalizedName()+"=/="+Generic.skillInfo.get(skillLoop).itemName.get(entityLoop));
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
			for(int i = 0; i < Generic.skillInfo.size()-1;i++ ){
				expMap.put(event.entity.getUniqueID()+Generic.skillInfo.get(i).name, playerInfo.getXP(Generic.skillInfo.get(i).name));
				System.out.println();
			}			
			//expMap.put(event.entity.getUniqueID(), playerInfo.getXP());
		}
			System.out.println(event.source.getEntity());
	    	if (event.source.getEntity() instanceof EntityPlayer){
	    		EntityPlayer player = (EntityPlayer) event.source.getEntity();
	    		
             PlayerInformation playerInfo = PlayerInformation.get((EntityPlayer) event.source.getEntity());
    		if (Generic.debugInfo == true){
    			System.out.println(event.entity.getCommandSenderName()); 
    		}
    		for (Integer skillLoop = 0; skillLoop <= Generic.skillInfo.size()-1; skillLoop++){ // Skill loop
    			if (Generic.skillInfo.get(skillLoop).type == (byte)2){
    				entityCheck:
    			for (Integer entityLoop = 0; entityLoop <= Generic.skillInfo.get(skillLoop).itemName.size()-1; entityLoop++){ // Block loop
    				System.out.println(event.entity.getCommandSenderName()+"=/="+Generic.skillInfo.get(skillLoop).itemName.get(entityLoop));
    				if ( event.entity.getCommandSenderName().equals(Generic.skillInfo.get(skillLoop).itemName.get(entityLoop)) ){
    					System.out.println(event.entity.getCommandSenderName());
    					System.out.println();
    					
    					playerInfo.addXP((Integer) Generic.skillInfo.get(skillLoop).exp.get(entityLoop), Generic.skillInfo.get(skillLoop).name);
    					player.addChatComponentMessage(new ChatComponentText(Generic.skillInfo.get(skillLoop).exp.get(entityLoop)+" experience added to: "+ Generic.skillInfo.get(skillLoop).name));
    					System.out.println("Player experience:"+playerInfo.getXP((String) Generic.skillInfo.get(skillLoop).name));
    					//event.source.getEntity().addChatComponentMessage(new ChatComponentText(Generic.skillInfo.get(skillLoop).exp.get(entityLoop)+"experience added to: "+ Generic.skillInfo.get(skillLoop).name));
    					break entityCheck;
    				}
    				
    			}
    			}
    		}
    	}
	}

    @SubscribeEvent
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event)
		{
    		
    		if (event.entity instanceof EntityPlayer)
    		{
    			System.out.println("Player Respawn detected");
    			PlayerInformation playerInfo = PlayerInformation.get((EntityPlayer) event.entity);
    			for(int i = 0; i < Generic.skillInfo.size();i++ ){
    				if(expMap.get(event.entity.getUniqueID()+Generic.skillInfo.get(i).name) != null ){
    					playerInfo.setXP((Integer) expMap.get(event.entity.getUniqueID()+Generic.skillInfo.get(i).name), Generic.skillInfo.get(i).name);
    				}
    			}
    		}
		}
	}