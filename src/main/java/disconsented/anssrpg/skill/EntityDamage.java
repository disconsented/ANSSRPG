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

public class EntityDamage {/*
    public void onLivingDeathEvent(LivingDeathEvent event) {
        if (event.source.getEntity() instanceof EntityPlayerMP){
            EntityPlayerMP player = (EntityPlayerMP) event.source.getEntity();
            PlayerData playerData = PlayerStore.getPlayer(player);
            ArrayList<Slug> slugList = PerkStore.getSlugs(event.entity);
            ArrayList<EntitySkill> skillStore = SkillStore.getInstance().getEntitySkill();
            Boolean requiresPerk = false;
            
            if (slugList != null){
                requiresPerk = true;
            }
            
            for(EntitySkill skill : skillStore){
                for(Triplet entry : skill.exp){
                    if(Utils.MatchEntity(event.entity, entry)){
                      if (requiresPerk) {
                          if (PlayerHandler.hasPerk(playerData, slugList)) {
                              PlayerHandler.awardToolXP(player, skill, entry.experience);
                              } else {
                                  PlayerHandler.taskFail(player);
                                  event.setCanceled(true);
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
            ArrayList<Slug> slugList = PerkStore.getSlugs(event.entity);
            ArrayList<EntitySkill> skillStore = SkillStore.getInstance().getEntitySkill();
            Boolean requiresPerk = false;
            
            if (slugList != null){
                requiresPerk = true;
            }
            
            for(EntitySkill skill : skillStore){
                for(Triplet entry : skill.exp){
                    if(Utils.MatchEntity(event.entity, entry)){
                      if (requiresPerk) {
                          if (!PlayerHandler.hasPerk(playerData, slugList) || !PlayerHandler.isWielding(skill, player)) {
                              PlayerHandler.taskFail(player);
                              event.ammount = 1;
                          }
                      }
                    }
                }
            }
        }
    }*/
}