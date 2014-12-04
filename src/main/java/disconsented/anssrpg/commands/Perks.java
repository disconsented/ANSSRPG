/**
 * 
 */
package disconsented.anssrpg.commands;

import java.util.ArrayList;
import java.util.List;

import disconsented.anssrpg.common.Settings;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.handler.PlayerHandler;
import disconsented.anssrpg.perk.Perk;
import disconsented.anssrpg.player.PlayerData;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

/**
 * @author Disconsented
 *
 */	
public class Perks implements ICommand {
	private List aliases;
	/**
	 * 
	 */
	public Perks() {
		this.aliases = new ArrayList();
		this.aliases.add("perk");
		this.aliases.add("perks");
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "perk";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getCommandAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public void processCommand(ICommandSender player, String[] arguments) {
		String UUID = ((EntityPlayerMP) player).getPersistentID().toString();
		String toReturn = "";
		EntityPlayerMP p2 = (EntityPlayerMP) player;
		PlayerData playerdata = PlayerHandler.getPlayer(UUID);
		switch(arguments[0].toLowerCase()){
		case "list":			
			switch(arguments[1].toLowerCase()){
			case "current":
				for(String perk :PlayerHandler.getPlayer(p2.getUniqueID().toString()).getPerkList()){
					toReturn += PerkStore.getInstance().getPerk(perk)+",";
				}
			break;
			case "all":
				for (Perk perk : PerkStore.getInstance().getPerks()){
					toReturn += perk.perkSlug+",";
				}
				break;
				default:
					toReturn = arguments[1]+" is an invalid argument";
				break;
			}			
			break;
		case "obtain":
			if (arguments.length >= 2){
				toReturn = PlayerHandler.addPerk(arguments[1].toString(), playerdata);
			}
			break;
		case "getinfo":
			if ((arguments.length >= 2)){				
				toReturn = PerkStore.getInstance().getPerk(arguments[1]).toString();
			}
			break;
		case "convertpoints":
			if(Settings.getInstance().getPointsMode() == 2){
				if (arguments.length > 2){
					if(p2.experienceLevel >= Integer.parseInt(arguments[2])){
						p2.experienceLevel -= Integer.parseInt(arguments[2]);
						playerdata.setPoints(playerdata.getPoints() + Integer.parseInt(arguments[2]));
					}
					else
					{
						toReturn = "Invalid Argument";
					}
				}
				else
				{
					toReturn = "Invalid Argument";
				}
			}
			else
			{
				toReturn = "option unavaliable";
			}
			break;
		}
		player.addChatMessage(new ChatComponentText(toReturn));
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_,
			String[] p_71516_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		// TODO Auto-generated method stub
		return false;
	}

}
