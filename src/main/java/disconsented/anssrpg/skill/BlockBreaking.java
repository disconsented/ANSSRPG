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
 * Handles when to add experience and blocking of events
 */

import java.util.ArrayList;
import java.util.Map;

import disconsented.anssrpg.common.*;
import disconsented.anssrpg.objects.BNEP;
import disconsented.anssrpg.objects.BNP;
import disconsented.anssrpg.perk.BlockPerk;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.data.SkillStore;
import disconsented.anssrpg.handler.PlayerHandler;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.skill.objects.BlockSkill;

public class BlockBreaking {

    public void onBreakEvent(BreakEvent event) {
        boolean isFakePlayer = event.getPlayer() instanceof FakePlayer;
        if (isFakePlayer && !Settings.isBlockFakePlayers()){
            return;
        }
        if (event.getPlayer() instanceof EntityPlayerMP){
            Block block = event.state.getBlock();
            Map<String, String> properties = event.state.getProperties();
            EntityPlayerMP player = (EntityPlayerMP) event.getPlayer();
            PlayerData playerData = PlayerStore.getPlayer(player);
            ArrayList<BlockPerk> perkList = PerkStore.getPerks(block);
            ArrayList<BlockSkill> skillStore = SkillStore.getInstance().getBlockSkill();

            for(BlockSkill skill : skillStore){
                for(BNEP entry : skill.exp){
                    if(Utils.MatchObject(entry.block, entry.properties, block, properties)){
                      if (requiresPerk(perkList, block, properties)) {
                          if (PlayerHandler.hasPerk(playerData, perkList)) {
                              PlayerHandler.awardToolXP(player, skill, entry.experience);
                              } else {
                                if (!isFakePlayer) {
                                    PlayerHandler.taskFail(player);
                                    event.setCanceled(true);
                                    return;
                                } else {
                                    if (Settings.isBlockFakePlayers()){
                                        Logging.debug("Fake player blocked at "+player.chunkCoordX+","+player.chunkCoordY+","+player.chunkCoordZ);
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

   private boolean requiresPerk(ArrayList<BlockPerk> perkList, Block block, Map<String,String> properties){
        if(perkList != null) {
            for (BlockPerk perk : perkList) {
                for (BNP definition : perk.blocks)
                {
                    if(Utils.MatchObject(definition.block, definition.properties, block, properties)){
                        return true;
                    }
                }
            }
        }
        return  false;
    }
}


