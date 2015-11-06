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

import disconsented.anssrpg.client.Data;
import disconsented.anssrpg.common.Reference;
import disconsented.anssrpg.gui.GUIExperience;
import disconsented.anssrpg.gui.GUIPerk;
import disconsented.anssrpg.gui.GUIStatus;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

public class ANSSRPG extends CommandBase {

    private List aliases;

    @SuppressWarnings("unchecked")
    public ANSSRPG() {
        this.aliases = new ArrayList();
        this.aliases.add("ANSSRPG");
        this.aliases.add("anssrpg");
        this.aliases.add("anss");
        this.aliases.add("ANSS");
    }

    @Override
    public String getCommandName() {
        return "ANSSRPG";
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender) {
        return "ANSSRPG -argument (-list perk/skill)";
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args) {
        switch (args[0]){
            case "0":
                Data.ticksLeft = 2;
                Data.screenToOpen = new GUIExperience();
                break;
            case "1":
                Data.ticksLeft = 2;
                Data.screenToOpen = new GUIPerk();
                break;
            case "2":
                Data.ticksLeft = 2;
                Data.screenToOpen = new GUIStatus();
                break;
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
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

