package disconsented.anssrpg.events;
/**
 * @author James
 * Handles when to add experience and blocking of events
 */
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.Item;
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
	
    public class blockBreaking{   
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
}