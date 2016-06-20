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
package disconsented.anssrpg.client.commands;

import disconsented.anssrpg.server.common.Logging;
import disconsented.anssrpg.server.common.Settings;
import disconsented.anssrpg.client.gui.RegistryReader;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Disconsented
 */
public class GUIRegistry extends CommandBase {
    private final List aliases;

    public GUIRegistry() {
        aliases = new ArrayList();
        aliases.add("GUIRegistry");
        aliases.add("REGISTRY");
        aliases.add("registry");
    }

    @Override
    public String getCommandName() {
        // TODO Auto-generated method stub
        return "GUIRegistry";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        // TODO Auto-generated method stub
        return "GUIRegistry";
    }

    @Override
    public List getCommandAliases() {
        // TODO Auto-generated method stub
        return aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender player, String[] arguments) throws CommandException {
        if (Settings.isServer) {
            Logging.error("Tried to open the registry GUI on the server please report this");
        } else {
            RegistryReader.getInstance().show();
        }

    }



    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        // TODO Auto-generated method stub
        return false;
    }

}
