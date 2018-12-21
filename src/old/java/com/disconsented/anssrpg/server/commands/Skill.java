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
package com.disconsented.anssrpg.server.commands;

import com.disconsented.anssrpg.server.data.PlayerStore;
import com.disconsented.anssrpg.server.perk.Slug;
import com.disconsented.anssrpg.server.player.PlayerData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public void execute(MinecraftServer server, ICommandSender player, String[] arguments) throws CommandException {
        EntityPlayerMP user = (EntityPlayerMP) player;
        PlayerData playerData = PlayerStore.getPlayer(user.getPersistentID().toString());
        String toReturn = "You have the current perks: ";
        for (Slug slug : playerData.getPerkList()) {
            toReturn += "," + slug;
        }
        toReturn += "| Your skill XP: ";
        for (Map.Entry<String, Integer> skill : playerData.getSkillExp().entrySet()) {
            toReturn += skill.toString();
        }
        player.addChatMessage(new TextComponentString(toReturn));
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
