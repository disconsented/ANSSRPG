package disconsented.anssrpg.skill;
/**
 * @author James
 * Handles when to add experience and blocking of events
 */
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.perk.PerkStore;
import disconsented.anssrpg.player.PlayerHandler;
	
    public class BlockBreaking{   
    	/**
    	 * Instance of EntityPlayerMP
    	 * Get required perks for entry
    	 * 		NullCheck
    	 * 		
    	 * Iterate through all skills of type (1)
    	 * If it requires perk
    	 * 		Check if player has it
    	 * 			Do add xp/fail depending on what happens
    	 * If it doesn't
    	 * 			Add xp
    	 * @param eventBreak
    	 */
    @SubscribeEvent
    public void onBreakevent(BreakEvent event){ 
    	Boolean requiresPerk = false;
    	if (event.getPlayer() instanceof EntityPlayerMP){
    		if(PerkStore.getBlockPerkList(event.block) != null){
    			requiresPerk = true;
    		}
    		for (int i = 0; i < SkillHandler.getSkillList().size(); i++){
    			Skill temp = (Skill)SkillHandler.getSkillList().get(i);
    			if(temp.type == 1){
    				int blockIndex = SkillHandler.indexOfBlock(event.block, i);
    				if (blockIndex != -1 || requiresPerk){
    					if (requiresPerk){
	    						if (PlayerHandler.hasPerk(event.block, SkillHandler.getSkillName(i))){//Player can mine
	        						PlayerHandler.addXP(SkillHandler.getXP(blockIndex, i), SkillHandler.getSkillName(i), (EntityPlayerMP) event.getPlayer());
	        					}else{    					
	        						PlayerHandler.sendFail((EntityPlayerMP) event.getPlayer());
	        						event.setCanceled(true);
	        					}
    					}else{
    						PlayerHandler.addXP(SkillHandler.getXP(blockIndex, i), SkillHandler.getSkillName(i), (EntityPlayerMP) event.getPlayer());
    					}
    				}    				
    			}
    		}
    	}
    }	
}