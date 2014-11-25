package disconsented.anssrpg.skill;
/**
 * @author James
 * Handles when to add experience and blocking of events
 */
import handler.PlayerHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.data.PerkStore;
	
    public class EntityDamage{   	
//    @SubscribeEvent
//    public void onLivingHurtEvent(LivingHurtEvent event){
//    	Boolean requiresPerk = false;
//    	if (event.source.getEntity() instanceof EntityPlayerMP){
//    		if(PerkStore.getEntityPerkList(event.entity.getClass()) != null){
//    			requiresPerk = true;
//    		}
//    		for (int i = 0; i < SkillHandler.getSkillList().size(); i++){
//    			Skill temp = (Skill)SkillHandler.getSkillList().get(i);
//    			if(temp.type == 2){
//    				int blockIndex = SkillHandler.indexOfEntity(event.entity, i);
//    				if (blockIndex != -1 || requiresPerk){
//    					if (requiresPerk){
//	    						if (PlayerHandler.hasPerk(event.entity.getClass(), SkillHandler.getSkillName(i))){//Player can mine
//	        						PlayerHandler.addXP(SkillHandler.getXP(blockIndex, i), SkillHandler.getSkillName(i), (EntityPlayerMP) event.source.getEntity());
//	        					}else{    					
//	        						PlayerHandler.sendFail((EntityPlayerMP) event.source.getEntity());
//	        						event.ammount = (1);
//	        					}
//    					}else{
//    						PlayerHandler.addXP(SkillHandler.getXP(blockIndex, i), SkillHandler.getSkillName(i), (EntityPlayerMP) event.source.getEntity());
//    					}
//    				}    				
//    			}
//    		}
//    	}
//    }	
  }
