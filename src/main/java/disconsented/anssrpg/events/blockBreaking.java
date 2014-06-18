package disconsented.anssrpg.events;
/**
 * @author James
 * Handles when to add experience and blocking of events
 */
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.data.PlayerHandler;
import disconsented.anssrpg.data.SkillHandler;
import disconsented.anssrpg.data.SkillObject;
	
    public class blockBreaking{   
    	/**
    	 * If the break is a MP Player
    	 * Iterate through all skills of type 1
    	 * 		Check that what was broken is in the skill via iteration
    	 * 			if player can mine it
    	 * 				give xp
    	 * 			else
    	 * 				don't give xp and block event
    	 * @param eventBreak
    	 */
    @SubscribeEvent
    public void onBreakevent(BreakEvent event){  
    	if (event.getPlayer() instanceof EntityPlayerMP){
    		for(int i = 0; i < SkillHandler.skillCount(); i++){
    			int blockIndex = SkillHandler.indexOfBlock(1,event.block);
    			if (blockIndex != -1){
    				if (PlayerHandler.hasPerk(event.block, SkillHandler.getSkillName(i))){//Player can mine
    					PlayerHandler.addXP(SkillHandler.getXP(blockIndex, i), SkillHandler.getSkillName(i), (EntityPlayerMP) event.getPlayer());
    					}
    				else{    					
    					PlayerHandler.sendFail((EntityPlayerMP) event.getPlayer());
    					event.setCanceled(true);
    					}
    				}
    			}
    		}
    	}
    }