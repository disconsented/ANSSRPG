package disconsented.anssrpg.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import disconsented.anssrpg.config.ConfigurationHandler;

public class DataSave {
	Map expMap = new HashMap();
	ArrayList perks = new ArrayList();
	 @SubscribeEvent
		public void onEntityJoinWorldEvent(EntityJoinWorldEvent event){    		
	    		if (event.entity instanceof EntityPlayer){
	    			System.out.println("Player Respawn detected");
	    			PlayerInformation playerInfo = PlayerInformation.get((EntityPlayer) event.entity);
	    			for(int i = 0; i < ConfigurationHandler.skillInfo.size();i++ ){
	    				if(expMap.get(event.entity.getUniqueID()+ConfigurationHandler.skillInfo.get(i).name) != null ){
	    					playerInfo.setXP((Integer) expMap.get(event.entity.getUniqueID()+ConfigurationHandler.skillInfo.get(i).name), ConfigurationHandler.skillInfo.get(i).name);
	    				}
	    			}
	    		}
			}
	    @SubscribeEvent
		public void onLivingDeath(LivingDeathEvent event)
		{
			EntityPlayer entityPlayer;
			if (event.entityLiving instanceof EntityPlayer){
				PlayerInformation playerInfo = PlayerInformation.get((EntityPlayer) event.entity);
				for(int i = 0; i < ConfigurationHandler.skillInfo.size()-1;i++ ){
					expMap.put(event.entity.getUniqueID()+ConfigurationHandler.skillInfo.get(i).name, playerInfo.getXP(ConfigurationHandler.skillInfo.get(i).name));
				}			
			}
		}

}
