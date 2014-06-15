package disconsented.anssrpg.events;
/**
 * @author James
 * Handles when to add experience and blocking of events
 */
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.config.ConfigurationHandler;
import disconsented.anssrpg.data.PlayerInformation;
	
    public class entityDeath{   	
    
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