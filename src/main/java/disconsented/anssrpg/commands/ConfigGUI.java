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

import disconsented.anssrpg.common.Settings;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Disconsented
 */
public class ConfigGUI implements ICommand {
    private List aliases;

    public ConfigGUI() {
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
    public void processCommand(ICommandSender sender, String[] p_71515_2_) {
        if (Settings.getInstance().isServer) {
//    		throw new Exception("Trying to launch GUI on server");
        } else {
            //disconsented.anssrpg.gui.Config.main();
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
