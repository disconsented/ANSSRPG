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
package disconsented.anssrpg.commands;

import disconsented.anssrpg.common.Reference;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.gameevent.TickEvent.Type;

public class ANSSRPG extends CommandBase {
    private final List aliases;

    public ANSSRPG() {
        aliases = new ArrayList();
        aliases.add("ANSSRPG");
        aliases.add("anssrpg");
        aliases.add("anss");
        aliases.add("ANSS");
    }

    @Override
    public String getCommandName() {
        return "ANSSRPG";
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        return "ANSSRPG -argument (-list perk/skill)";
    }

    @Override
    public List getCommandAliases() {
        return aliases;
    }

    @Override
    public void processCommand(ICommandSender player, String[] astring) {       
        EntityPlayerMP playerMP = (EntityPlayerMP) player;
        playerMP.openGui(Reference.ID, Integer.parseInt(astring[0]), null, 0, 0, 0);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
        return true;
    }


    @Override
    public boolean isUsernameIndex(String[] astring, int i) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}

