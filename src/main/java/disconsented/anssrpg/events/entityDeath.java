package disconsented.anssrpg.events;
/**
 * @author James
 * Handles when to add experience and blocking of events
 */
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.config.JsonHandler;
import disconsented.anssrpg.data.PlayerInformation;
	
    public class entityDeath{   	
    
    @SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
	if (event.source.getEntity() instanceof EntityPlayer){
		EntityPlayer player = (EntityPlayer) event.source.getEntity();	    		
		PlayerInformation playerInfo = PlayerInformation.get((EntityPlayer) event.source.getEntity());
		for (Integer skillLoop = 0; skillLoop < JsonHandler.getSkillList().skillInfo.size(); skillLoop++){ // Skill loop
			if (JsonHandler.getSkillList().skillInfo.get(skillLoop).type == (byte)2){
				entityCheck:
					for (Integer entityLoop = 0; entityLoop < JsonHandler.getSkillList().skillInfo.get(skillLoop).itemName.size(); entityLoop++){ // Block loop
						System.out.println(event.entity.getCommandSenderName()+"=/="+JsonHandler.getSkillList().skillInfo.get(skillLoop).itemName.get(entityLoop));
						if ( event.entity.getCommandSenderName().equals(JsonHandler.getSkillList().skillInfo.get(skillLoop).itemName.get(entityLoop)) ){    					
							playerInfo.addXP((Integer) JsonHandler.getSkillList().skillInfo.get(skillLoop).exp.get(entityLoop), JsonHandler.getSkillList().skillInfo.get(skillLoop).name);
							if (playerInfo.canLevelUp((Integer) JsonHandler.getSkillList().skillInfo.get(skillLoop).exp.get(entityLoop), JsonHandler.getSkillList().skillInfo.get(skillLoop).name)){
								player.addChatComponentMessage(new ChatComponentText("Your skill "+JsonHandler.getSkillList().skillInfo.get(skillLoop).name+" has reached level: "+ playerInfo.getLevel(playerInfo.getXP(JsonHandler.getSkillList().skillInfo.get(skillLoop).name))));
							}
							player.addChatComponentMessage(new ChatComponentText(JsonHandler.getSkillList().skillInfo.get(skillLoop).exp.get(entityLoop)+" experience added to: "+ JsonHandler.getSkillList().skillInfo.get(skillLoop).name));
							player.addChatComponentMessage(new ChatComponentText("Skill experience: "+playerInfo.getXP(JsonHandler.getSkillList().skillInfo.get(skillLoop).name)));
							break entityCheck;
							}
						}
			}
			}
		}
	}
}