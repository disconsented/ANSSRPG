package disconsented.anssrpg.commands;

import java.util.ArrayList;
import java.util.List;

import disconsented.anssrpg.perk.PerkStore;
import disconsented.anssrpg.player.PlayerHandler;
import disconsented.anssrpg.skill.Skill;
import disconsented.anssrpg.skill.SkillHandler;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

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
	  case "-list":
		  if (astring[1].equals("perk")){
			  player.addChatMessage(new ChatComponentText(PerkStore.getRegisteredPerks().keySet().toString()));
		  }else if(astring[1].equals("skill")){
			  for(int i = 0; i < SkillHandler.getSkillList().size(); i++){
				  player.addChatMessage(new ChatComponentText(SkillHandler.getSkillName(i)));
			  }	
		  }	  
		  break;
	  case "-addPerk":
		  	PlayerHandler.addPerk(astring[1], ((EntityPlayerMP) player).getPersistentID().toString());
		  break;
	  case "-get":
			  if (astring[1].equals("perks")){	
				  if (PlayerHandler.getPerks(UUID) != null){
					  ArrayList temp = PlayerHandler.getPerks(UUID);
					  for (int i = 0; i < temp.size(); i++){
						  player.addChatMessage(new ChatComponentText(temp.get(i).toString()));
					  }
				  }
				  else {
					  player.addChatMessage(new ChatComponentText("No perks found"));
				  }
			  }else if(astring[1].equals("skillInfo")){
				  for (int i = 0; i < SkillHandler.getSkillList().size(); i++){
					  Skill temp = (Skill) SkillHandler.getSkillList().get(i);
					  if (temp.name.equals(astring[2])){
						  player.addChatMessage(new ChatComponentText(temp.name));
						  player.addChatMessage(new ChatComponentText(Integer.toString(temp.type)));
						  player.addChatMessage(new ChatComponentText(temp.exp.toString()));
						  player.addChatMessage(new ChatComponentText(temp.entryName.toString()));						  
					  }
				  }				  
			  }
			  else if (astring[1].toLowerCase().equals("uuid")){
				  player.addChatMessage(new ChatComponentText(UUID));
			  }
			  else if(astring[1].toLowerCase().equals("perkXP")){
				  PlayerHandler.getXP(astring[2], UUID);
			  }
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

