/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
/**
 *
 */
package disconsented.anssrpg.commands;

import disconsented.anssrpg.data.PlayerStore;
import disconsented.anssrpg.perk.Slug;
import disconsented.anssrpg.player.PlayerData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author Disconsented
 */
public class Skill extends CommandBase {

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
        for (Slug slug : playerData.getPerkList()) {
            toReturn += "," + slug;
        }
        toReturn += "| Your skill XP: ";
        for (Entry<String, Integer> skill : playerData.getSkillExp().entrySet()) {
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

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

}
