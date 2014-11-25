package disconsented.anssrpg.skill;
/**
 * @author James
 * Handles when to add experience and blocking of events
 */
import handler.PlayerHandler;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.Main;
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.data.PerkStore;
	
    public class BlockBreaking{   
    	/**
    	 * Instance of EntityPlayerMP
    	 * Get required perks for entry
    	 * 		NullCheck
    	 * 		
    	 * Iterate through all skills of type (1)
    	 * If it requires perk
    	 * 		Check if player has it
    	 * 			Do add xp/fail depending on what happens
    	 * If it doesn't
    	 * 			Add xp
    	 * @param eventBreak
    	 */
    @SubscribeEvent
    public void onBreakevent(BreakEvent event){    	
    	
    }	
}