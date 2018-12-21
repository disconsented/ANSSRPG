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
package com.disconsented.anssrpg.server.skill;
/**
 * @author James
 * Handles when to add experience and blocking of events
 */

import com.google.common.collect.ImmutableMap;
import com.disconsented.anssrpg.server.common.Logging;
import com.disconsented.anssrpg.server.common.Settings;
import com.disconsented.anssrpg.server.common.Utils;
import com.disconsented.anssrpg.server.config.storage.BNEP;
import com.disconsented.anssrpg.server.config.storage.BNP;
import com.disconsented.anssrpg.server.data.PerkStore;
import com.disconsented.anssrpg.server.data.PlayerStore;
import com.disconsented.anssrpg.server.data.SkillStore;
import com.disconsented.anssrpg.server.handler.PlayerHandler;
import com.disconsented.anssrpg.server.perk.BlockPerk;
import com.disconsented.anssrpg.server.player.PlayerData;
import com.disconsented.anssrpg.server.skill.objects.BlockSkill;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.BlockEvent;

import java.util.ArrayList;

public class BlockBreaking {

    public void onBreakEvent(BlockEvent.BreakEvent event) {
        boolean isFakePlayer = event.getPlayer() instanceof FakePlayer;
        if (isFakePlayer && !Settings.isBlockFakePlayers()) {
            return;
        }
        if (event.getPlayer() instanceof EntityPlayerMP) {
            Block block = event.getState().getBlock();
            ImmutableMap<IProperty<?>, Comparable<?>> properties = event.getState().getProperties();
            EntityPlayerMP player = (EntityPlayerMP) event.getPlayer();
            PlayerData playerData = PlayerStore.getPlayer(player);
            ArrayList<BlockPerk> perkList = PerkStore.getPerks(block);
            ArrayList<BlockSkill> skillStore = SkillStore.getInstance().getBlockSkill();

            for (BlockSkill skill : skillStore) {
                for (BNEP entry : skill.exp) {
                    if (Utils.MatchObject(entry.block, entry.properties, block, properties)) {
                        if (this.requiresPerk(perkList, block, properties)) {
                            if (PlayerHandler.hasPerk(playerData, perkList)) {
                                PlayerHandler.awardToolXP(player, skill, entry.experience);
                            } else {
                                if (!isFakePlayer) {
                                    PlayerHandler.taskFail(player);
                                    event.setCanceled(true);
                                    return;
                                } else {
                                    if (Settings.isBlockFakePlayers()) {
                                        Logging.debug("Fake player blocked at " + player.chunkCoordX + "," + player.chunkCoordY + "," + player.chunkCoordZ);
                                        event.setCanceled(true);
                                        return;
                                    }
                                }
                                return;
                            }
                        } else {
                            PlayerHandler.awardToolXP(player, skill, entry.experience);
                            return;
                        }
                    }
                }
            }
        }
    }

    private boolean requiresPerk(ArrayList<BlockPerk> perkList, Block block, ImmutableMap<IProperty<?>, Comparable<?>> properties) {
        if (perkList != null) {
            for (BlockPerk perk : perkList) {
                for (BNP definition : perk.blocks) {
                    if (Utils.MatchObject(definition.block, definition.properties, block, properties)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


