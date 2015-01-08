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

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.data.SkillStore;
import disconsented.anssrpg.handler.PlayerHandler;
import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.skill.objects.BlockSkill;
import disconsented.anssrpg.skill.objects.BlockXP;
import disconsented.anssrpg.skill.objects.XPGain;
	
    public class BlockBreaking{   
    	/**
    	 * Author Disconsented
    	 */
    @SubscribeEvent
    public void onBreakevent(BreakEvent event){    	
    	if(event.getPlayer() instanceof EntityPlayerMP){
			EntityPlayerMP playerMP = (EntityPlayerMP) event.getPlayer();
    		PlayerData player = PlayerStore.getInstance().getPlayer(event.getPlayer().getUniqueID().toString());
    		ArrayList<BlockPerk> blockList = PerkStore.getPerksForBlock(event.block.getUnlocalizedName());
    		boolean requiresPerk = false;
    		if (blockList != null){
    			requiresPerk = true;
    		}    		
    		for (BlockSkill skill : SkillStore.getInstance().getBlockSkill()){
    			ArrayList<BlockXP> temp = skill.getExp();
    			for (int i = 0; i < temp.size(); i++){
    				if(((BlockXP) temp.get(i)).getBlock().equals(event.block)){	  
	    				if (requiresPerk){
	    					if (PlayerHandler.hasPerk(player, blockList)){
	    						PlayerHandler.awardXP(player, skill.name, temp.get(i).getXp(), playerMP);
	    					}
	    					else
	    					{
	    						PlayerHandler.taskFail(event.getPlayer());
	    						event.setCanceled(true);
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