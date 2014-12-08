/**
 * 
 */
package disconsented.anssrpg.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.player.PlayerData;

/**
 * @author Disconsented
 *
 */
public class Skill implements ICommand {

	/**
	 * 
	 */
	public Skill() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "skill";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getCommandAliases() {	
		List aliases = new ArrayList();
		aliases.add("skill");
		return aliases; 
	}

	@Override
	public void processCommand(ICommandSender player, String[] p_71515_2_) {
		EntityPlayerMP user = (EntityPlayerMP) player;
		PlayerData playerData = PlayerStore.getInstance().getPlayer(user.getPersistentID().toString());
		String toReturn = "You have the current perks: ";
		for (String perk : playerData.getPerkList()){
			toReturn += ","+perk;
		}
		toReturn += "| Your skill XP: ";
		for (Entry<String, Integer> skill : playerData.getSkillExp().entrySet()){
			toReturn += skill.toString();
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
