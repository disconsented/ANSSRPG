package disconsented.anssrpg.events;
/**
 * @author James
 * Handles when to add experience and blocking of events
 */
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.config.JsonHandler;
import disconsented.anssrpg.data.PlayerInformation;
	
    public class blockBreaking{   
    @SubscribeEvent
    public void onBreakevent(BreakEvent eventBreak){  
             if (eventBreak.getPlayer() instanceof EntityPlayer) {
             EntityPlayer ent = eventBreak.getPlayer();
             PlayerInformation playerInfo = PlayerInformation.get(eventBreak.getPlayer());
     		  for (Integer skillLoop = 0; skillLoop < JsonHandler.getSkillList().size(); skillLoop++){ // Skill loop
     			  System.out.println(JsonHandler.getSkillList().get(skillLoop).itemName);
               	if ((byte)JsonHandler.getSkillList().get(skillLoop).type == (byte)1){
               		
               		blockCheck:	for (Integer blockLoop = 0; blockLoop < JsonHandler.getSkillList().get(skillLoop).itemName.size(); blockLoop++){ // Block loop     
               			if (eventBreak.block.getUnlocalizedName().equals(JsonHandler.getSkillList().get(skillLoop).itemName.get(blockLoop))){             				
               				if (playerInfo.getLevel(playerInfo.getXP(JsonHandler.getSkillList().get(skillLoop).name)) < (Integer)JsonHandler.getSkillList().get(skillLoop).req.get(blockLoop)){
               					ent.addChatComponentMessage(new ChatComponentText("Your skill ("+playerInfo.getLevel(playerInfo.getXP(JsonHandler.getSkillList().get(skillLoop).name))+") is not high enough to break "+eventBreak.block.getLocalizedName()+"("+JsonHandler.getSkillList().get(skillLoop).req.get(blockLoop)+")"));
               					eventBreak.setCanceled(true);
               				}else{
               					ent.addChatComponentMessage(new ChatComponentText(JsonHandler.getSkillList().get(skillLoop).exp.get(blockLoop)+" experience added to: "+ JsonHandler.getSkillList().get(skillLoop).name));
               					playerInfo.addXP((Integer) JsonHandler.getSkillList().get(skillLoop).exp.get(blockLoop), JsonHandler.getSkillList().get(skillLoop).name);
               					if (playerInfo.canLevelUp((Integer) JsonHandler.getSkillList().get(skillLoop).exp.get(blockLoop), JsonHandler.getSkillList().get(skillLoop).name)){
               						ent.addChatComponentMessage(new ChatComponentText("Your skill "+JsonHandler.getSkillList().get(skillLoop).name+" has reached level: "+ playerInfo.getLevel(playerInfo.getXP(JsonHandler.getSkillList().get(skillLoop).name))));
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