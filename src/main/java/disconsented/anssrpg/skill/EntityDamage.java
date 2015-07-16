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

import disconsented.anssrpg.common.ObjectPerkDefinition;
import disconsented.anssrpg.perk.EntityPerk;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import disconsented.anssrpg.common.Triplet;
import disconsented.anssrpg.common.Utils;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.data.SkillStore;
import disconsented.anssrpg.handler.PlayerHandler;
import disconsented.anssrpg.perk.Slug;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.skill.objects.EntitySkill;

public class EntityDamage {
    public void onLivingDeathEvent(LivingDeathEvent event) {
        if (event.source.getEntity() instanceof EntityPlayerMP){
            EntityPlayerMP player = (EntityPlayerMP) event.source.getEntity();
            PlayerData playerData = PlayerStore.getPlayer(player);
            ArrayList<EntityPerk> perkList = PerkStore.getPerks(event.entity.getClass());
            ArrayList<EntitySkill> skillStore = SkillStore.getInstance().getEntitySkill();

            for(EntitySkill skill : skillStore){
                for(Triplet entry : skill.exp){
                    if(Utils.MatchObject(event.entity.getClass(), entry.object)){
                      if (requiresPerk(perkList, event.entity)) {
                          if (PlayerHandler.hasPerk(playerData, perkList)) {
                                    PlayerHandler.awardToolXP(player, skill, entry.experience);
                              } else {
                                  PlayerHandler.taskFail(player);
                              }
                          } else {
                              PlayerHandler.awardToolXP(player, skill, entry.experience);
                          }
                    }
                }
            }
        }
    }
    public void onLivingHurtEvent(LivingHurtEvent event) {
        if (event.source.getEntity() instanceof EntityPlayerMP){
            EntityPlayerMP player = (EntityPlayerMP) event.source.getEntity();
            PlayerData playerData = PlayerStore.getPlayer(player);
            ArrayList<EntityPerk> perkList = PerkStore.getPerks(event.entity.getClass());
            ArrayList<EntitySkill> skillStore = SkillStore.getInstance().getEntitySkill();

            
            for(EntitySkill skill : skillStore){
                for(Triplet entry : skill.exp){
                    if(Utils.MatchObject(event.entity.getClass(), entry.object)){
                      if (requiresPerk(perkList, event.entity)) {
                          if (!PlayerHandler.hasPerk(playerData, perkList) || !PlayerHandler.isWielding(skill, player)) {
                              PlayerHandler.taskFail(player);
                              event.ammount = 1;
                          }
                      }
                    }
                }
            }
        }
    }

    private static boolean requiresPerk(ArrayList<EntityPerk> perkList, Entity entity){
        if(perkList != null) {
            for (EntityPerk perk : perkList) {
                for (ObjectPerkDefinition<Class> definition : perk.entities)
                {
                    if(Utils.MatchObject(definition.object, entity.getClass())){
                        return true;
                    }
                }
            }
        }
        return  false;
    }
}