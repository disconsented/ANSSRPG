package disconsented.anssrpg.commands;

import java.util.ArrayList;
import java.util.List;

import disconsented.anssrpg.Main;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class DebugCommand implements ICommand
{
  private List aliases;
  public DebugCommand()
  {
    this.aliases = new ArrayList();
    this.aliases.add("sample");
    this.aliases.add("sam");
    //this.aliases.add("addPerk");
  }

  @Override
  public String getCommandName()
  {
    return "sample";
  }

  @Override
  public String getCommandUsage(ICommandSender icommandsender)
  {
    return "sample <text>";
  }

  @Override
  public List getCommandAliases()
  {
    return this.aliases;
  }

  @Override
  public void processCommand(ICommandSender icommandsender, String[] astring)
  {
	  EntityPlayerSP test = (EntityPlayerSP)icommandsender;
	  test.openGui(Main.instance, 0, null, 0, 0, 0);
	  
	  for (int i = 0; i < astring.length; i++){
	  icommandsender.addChatMessage(new ChatComponentText(astring[i]));
	  }
	  
   
  }

  @Override
  public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
  {
    return true;
  }

  @Override
  public List addTabCompletionOptions(ICommandSender icommandsender,
      String[] astring)
  {
    return null;
  }

  @Override
  public boolean isUsernameIndex(String[] astring, int i)
  {
    return false;
  }

  @Override
  public int compareTo(Object o)
  {
    return 0;
  }
}

