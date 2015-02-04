/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
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
import disconsented.anssrpg.perk.Slug;
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
			ArrayList<Slug> entitylist = PerkStore.getInstance().getSlugs(event.entity);
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
							}
							else
							{
								PlayerHandler.taskFail((EntityPlayer) playerMP);
								event.ammount = 1;
							}
						}
						else
						{
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
			ArrayList<Slug> entitylist = PerkStore.getInstance().getSlugs(event.entity);
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