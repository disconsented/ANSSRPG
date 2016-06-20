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
package disconsented.anssrpg.server.skill;
/**
 * @author James
 * Handles when to add experience and entitying of events
 */

import disconsented.anssrpg.server.common.Logging;
import disconsented.anssrpg.server.common.Settings;
import disconsented.anssrpg.server.common.Utils;
import disconsented.anssrpg.server.config.storage.ENE;
import disconsented.anssrpg.server.data.PerkStore;
import disconsented.anssrpg.server.data.PlayerStore;
import disconsented.anssrpg.server.data.SkillStore;
import disconsented.anssrpg.server.handler.PlayerHandler;
import disconsented.anssrpg.server.perk.EntityPerk;
import disconsented.anssrpg.server.player.PlayerData;
import disconsented.anssrpg.server.skill.objects.EntitySkill;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.ArrayList;

public class EntityDamage {
    public void onLivingDeathEvent(LivingDeathEvent event) {
        boolean isFakePlayer = event.getSource().getEntity() instanceof FakePlayer;
        if (isFakePlayer && !Settings.isBlockFakePlayers()) {
            return;
        }
        if (event.getSource().getEntity() instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.getSource().getEntity();
            PlayerData playerData = PlayerStore.getPlayer(player);
            ArrayList<EntityPerk> perkList = PerkStore.getPerks(event.getSource().getEntity().getClass());
            ArrayList<EntitySkill> skillStore = SkillStore.getInstance().getEntitySkill();

            for (EntitySkill skill : skillStore) {
                for (ENE entry : skill.exp) {
                    if (Utils.MatchObject(event.getSource().getEntity().getClass(), entry.entity)) {
                        if (requiresPerk(perkList, event.getSource().getEntity())) {
                            if (PlayerHandler.hasPerk(playerData, perkList)) {
                                PlayerHandler.awardToolXP(player, skill, entry.experience);
                            } else {
                                if (!isFakePlayer) {
                                    PlayerHandler.taskFail(player);
                                    event.getSource().getEntity().captureDrops = true;
                                    return;
                                } else {
                                    if (Settings.isBlockFakePlayers()) {
                                        Logging.debug("Fake player blocked at " + player.chunkCoordX + "," + player.chunkCoordY + "," + player.chunkCoordZ);
                                        event.getSource().getEntity().captureDrops = true;
                                        return;
                                    }
                                }
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
        if (event.getSource().getEntity() instanceof FakePlayer) {
            if (Settings.isBlockFakePlayers()) {
                event.setCanceled(true);
            }
            return;
        } else if (event.getSource().getEntity() instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.getSource().getEntity();
            PlayerData playerData = PlayerStore.getPlayer(player);
            ArrayList<EntityPerk> perkList = PerkStore.getPerks(event.getSource().getEntity().getClass());
            ArrayList<EntitySkill> skillStore = SkillStore.getInstance().getEntitySkill();


            for (EntitySkill skill : skillStore) {
                for (ENE entry : skill.exp) {
                    if (Utils.MatchObject(event.getSource().getEntity().getClass(), entry.entity)) {
                        if (this.requiresPerk(perkList, event.getSource().getEntity())) {
                            if (!PlayerHandler.hasPerk(playerData, perkList) || !PlayerHandler.isWielding(skill, player)) {
                                PlayerHandler.taskFail(player);
                                event.setAmount(1);//TODO: Correct damage to account for when less than this
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean requiresPerk(ArrayList<EntityPerk> perkList, Entity entity) {
        if (perkList != null) {
            for (EntityPerk perk : perkList) {
                for (ENE definition : perk.entities) {
                    if (Utils.MatchObject(definition.entity, entity.getClass())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}