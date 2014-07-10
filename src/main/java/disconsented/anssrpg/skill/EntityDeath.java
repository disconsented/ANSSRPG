package disconsented.anssrpg.skill;
/**
 * @author James
 * Handles when to add experience and blocking of events
 */
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.perk.PerkStore;
import disconsented.anssrpg.player.PlayerHandler;
	
    public class EntityDeath{   	
    
    @SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{

    	Boolean requiresPerk = false;
    	if (event.source.getSourceOfDamage() instanceof EntityPlayerMP){
    		if(PerkStore.getEntityPerkList((Entity)event.entity) != null){
    			requiresPerk = true;
    		}
    		for (int i = 0; i < SkillHandler.getSkillList().size(); i++){
    			Skill temp = (Skill)SkillHandler.getSkillList().get(i);
    			if(temp.type == 1){
    				int blockIndex = SkillHandler.indexOfEntity((Entity)event.entity, i); 
    				if (blockIndex != -1 || requiresPerk){
    					if (requiresPerk){
	    						if (PlayerHandler.hasPerk(event.entity, SkillHandler.getSkillName(i))){//Player can mine
	        						PlayerHandler.addXP(SkillHandler.getXP(blockIndex, i), SkillHandler.getSkillName(i), (EntityPlayerMP) event.source.getEntity());
	        					}else{    					
	        						PlayerHandler.sendFail((EntityPlayerMP) event.source.getEntity());
	        						//event.setCanceled(true);
	        					}
    					}else{
    						PlayerHandler.addXP(SkillHandler.getXP(blockIndex, i), SkillHandler.getSkillName(i), (EntityPlayerMP) event.source.getEntity());
    					}
    				}    				
    			}
    		}
    	}
    }	
}