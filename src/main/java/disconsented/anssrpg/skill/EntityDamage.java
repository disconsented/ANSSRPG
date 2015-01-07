package disconsented.anssrpg.skill;
/**
 * @author James
 * Handles when to add experience and entitying of events
 */
import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.data.SkillStore;
import disconsented.anssrpg.handler.PlayerHandler;
import disconsented.anssrpg.perk.EntityPerk;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.skill.objects.EntitySkill;
import disconsented.anssrpg.skill.objects.EntityXP;
import disconsented.anssrpg.skill.objects.XPGain;
	
    public class EntityDamage {
    	/**
    	 * Author Disconsented
    	 */
    @SubscribeEvent
    public void onLivingHurtEvent(LivingHurtEvent event){
		if(event.source.getEntity() instanceof EntityPlayerMP) {
			EntityPlayerMP playerMP = (EntityPlayerMP)event.source.getEntity();
			PlayerData player = PlayerStore.getInstance().getPlayer(playerMP.getUniqueID().toString());
			ArrayList<EntityPerk> entitylist = PerkStore.getPerksForEntity(event.entity.getClass().getSimpleName());
			boolean requiresPerk = false;
			if (entitylist != null){
				requiresPerk = true;
			}
			for (EntitySkill skill : SkillStore.getInstance().getEntitySkill()) {
				ArrayList<EntityXP> temp = skill.getExp();
				for (int i = 0; i < temp.size(); i++){
					Class entityClass = ((EntityXP) temp.get(i)).getEntity();
					if(event.entity.getClass().equals(entityClass)) {
						if (requiresPerk){
							if (PlayerHandler.hasPerk(player, entitylist)){
//								if (event.entity.isDead){
//									PlayerHandler.awardXP(player, skill.name, temp.get(i).getXp(), playerMP);
//								}
							}
							else
							{
								PlayerHandler.taskFail((EntityPlayer) playerMP);
								event.ammount = 1;
							}
						}
						else
						{
//							if (event.entity.isDead){
//								PlayerHandler.awardXP(player, skill.name, temp.get(i).getXp(), playerMP);
//							}
						}
					}
				}
			}
		}
	}
    
    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event){
    	if(event.source.getEntity() instanceof EntityPlayerMP) {
			EntityPlayerMP playerMP = (EntityPlayerMP)event.source.getEntity();
			PlayerData player = PlayerStore.getInstance().getPlayer(playerMP.getUniqueID().toString());
			ArrayList<EntityPerk> entitylist = PerkStore.getPerksForEntity(event.entity.getClass().getSimpleName());
			boolean requiresPerk = false;
			if (entitylist != null){
				requiresPerk = true;
			}
			for (EntitySkill skill : SkillStore.getInstance().getEntitySkill()) {
				ArrayList<EntityXP> temp = skill.getExp();
				for (int i = 0; i < temp.size(); i++){
					Class entityClass = ((EntityXP) temp.get(i)).getEntity();
					if(event.entity.getClass().equals(entityClass)) {
						if (requiresPerk){
							if (PlayerHandler.hasPerk(player, entitylist)){
									PlayerHandler.awardXP(player, skill.name, temp.get(i).getXp(), playerMP);
							}							
						}
						else
						{
								PlayerHandler.awardXP(player, skill.name, temp.get(i).getXp(), playerMP);
						}
					}
				}
			}
		}
    }
}