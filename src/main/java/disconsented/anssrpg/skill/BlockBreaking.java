package disconsented.anssrpg.skill;
/**
 * @author James
 * Handles when to add experience and blocking of events
 */
import handler.PlayerHandler;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.data.SkillStore;
import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.skill.objects.BlockSkill;
import disconsented.anssrpg.skill.objects.BlockXP;
	
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
    				if(temp.get(i).getBlock().equals(event.block)){	  
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