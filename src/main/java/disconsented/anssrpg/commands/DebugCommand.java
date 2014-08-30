package disconsented.anssrpg.commands;

import java.util.ArrayList;
import java.util.List;

import disconsented.anssrpg.Main;
import disconsented.anssrpg.network.PerkInfo;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
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
	  for (int i = 0; i < astring.length; i++){
	  icommandsender.addChatMessage(new ChatComponentText(astring[i]));
	  }	 
	  ArrayList<String> temp1 = new ArrayList<String>();
	  ArrayList<Integer> temp2 = new ArrayList<Integer>();
	  temp1.add("A");
	  temp1.add("B");
	  temp1.add("C");
	  temp2.add(1);
	  temp2.add(2);
	  temp2.add(3);
	  Main.snw.sendTo(new PerkInfo("Name","Description", 5, temp1, temp2), (EntityPlayerMP)icommandsender);
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

