/**
 * 
 */
package disconsented.anssrpg.commands;

import java.util.ArrayList;
import java.util.List;

import disconsented.anssrpg.common.Settings;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

/**
 * @author Disconsented
 *
 */
public class ConfigGUI implements ICommand {
	private List aliases;
	public ConfigGUI(){
		this.aliases = new ArrayList();
		this.aliases.add("ConfigGUI");
		this.aliases.add("CONFIGGUI");
		this.aliases.add("configgui");
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "ConfigGUI";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return "ConfigGUI";
	}

	@Override
	public List getCommandAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
		if (Settings.getInstance().isServer){ 
//    		throw new Exception("Trying to launch GUI on server");
    	}else{
    		disconsented.anssrpg.gui.Config.main();
    	}
		
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
