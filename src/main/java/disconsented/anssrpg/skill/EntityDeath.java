package disconsented.anssrpg.skill;
/**
 * @author James
 * Handles when to add experience and blocking of events
 */
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.player.PlayerHandler;
	
    public class EntityDeath{   	
    
    @SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
    	if (event.source.getSourceOfDamage() instanceof EntityPlayerMP){
    		EntityPlayerMP player = (EntityPlayerMP) event.source.getSourceOfDamage();
    		for(int i = 0; i < SkillHandler.skillCount(); i++){
    			int entityIndex = SkillHandler.indexOfEntity(event.entity);
    			if (entityIndex != -1){
    				if (PlayerHandler.hasPerk(event.entity, SkillHandler.getSkillName(i))){//Player can mine
    					PlayerHandler.addXP(SkillHandler.getXP(entityIndex, i), SkillHandler.getSkillName(i), player);
    					}
    				else{    					
    					PlayerHandler.sendFail(player);
    					event.setCanceled(true);
    					}
    				}
    			}
    		}
    	}
}