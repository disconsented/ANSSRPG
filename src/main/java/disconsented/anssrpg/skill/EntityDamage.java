package disconsented.anssrpg.skill;
/**
 * @author James
 * Handles when to add experience and entitying of events
 */
import handler.PlayerHandler;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.data.SkillStore;
import disconsented.anssrpg.perk.EntityPerk;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.skill.objects.EntitySkill;
import disconsented.anssrpg.skill.objects.EntityXP;
	
    public class EntityDamage{   	
    	/**
    	 * Author Disconsented
    	 */
    @SubscribeEvent
    public void onLivingHurtEvent(LivingHurtEvent event){
	    	if(event.source.getEntity() instanceof EntityPlayerMP){
			PlayerData player = PlayerStore.getInstance().getPlayer(event.source.getEntity().getUniqueID().toString());
			ArrayList<EntityPerk> entitylist = PerkStore.getPerksForEntity(event.entity.getCommandSenderName());
			boolean requiresPerk = false;
			if (entitylist != null){
				requiresPerk = true;
			}    		
			for (EntitySkill skill : SkillStore.getInstance().getEntitySkill()){
				ArrayList<EntityXP> temp = skill.exp;
				for (int i = 0; i < temp.size(); i++){
					Class entityClass = temp.get(i).getEntity();
					if(event.entity.getClass().equals(entityClass)){	  
	    				if (requiresPerk){
	    					if (PlayerHandler.hasPerk(player, entitylist)){
	    						PlayerHandler.awardXP(player, skill.name, temp.get(i).getXp());
	    					}
	    					else
	    					{
	    						PlayerHandler.taskFail((EntityPlayer) event.source.getEntity());
	    						event.ammount = 0;
	    					}
	    				}
	    				else
	    				{
	    					PlayerHandler.awardXP(player, skill.name, temp.get(i).getXp());
	    				}
				}
			}
		}
	}
    }	
  }
