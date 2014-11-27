package disconsented.anssrpg.commands;

import java.util.ArrayList;
import java.util.List;

import disconsented.anssrpg.Main;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ANSSRPG implements ICommand
{
  private List aliases;
  public ANSSRPG()
  {
    this.aliases = new ArrayList();
    this.aliases.add("ANSSRPG");
    this.aliases.add("anssrpg");
    this.aliases.add("anss");
    this.aliases.add("ANSS");
  }

  @Override
  public String getCommandName()
  {
    return "ANSSRPG";
  }

  @Override
  public String getCommandUsage(ICommandSender icommandsender)
  {
    return "ANSSRPG -argument (-list perk/skill)";
  }

  @Override
  public List getCommandAliases()
  {
    return this.aliases;
  }

  @Override
  public void processCommand(ICommandSender player, String[] astring)
  {
	  String UUID = ((EntityPlayerMP) player).getPersistentID().toString();
	  switch(astring[0]){	  
	  case "-perks":
		  ((EntityPlayer) player).openGui(Main.instance, 0, null, 0, 0, 0);  
		  break;
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

