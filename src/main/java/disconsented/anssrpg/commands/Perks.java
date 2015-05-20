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
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.handler.PlayerHandler;
import disconsented.anssrpg.perk.Perk;
import disconsented.anssrpg.perk.Slug;
import disconsented.anssrpg.player.PlayerData;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Disconsented
 */
public class Perks implements ICommand {
    private List aliases;

    /**
     *
     */
    public Perks() {
        this.aliases = new ArrayList();
        this.aliases.add("perk");
        this.aliases.add("perks");
    }

    @Override
    public int compareTo(Object arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getCommandName() {
        // TODO Auto-generated method stub
        return "perk";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List getCommandAliases() {
        // TODO Auto-generated method stub
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender player, String[] arguments) {
        String UUID = ((EntityPlayerMP) player).getPersistentID().toString();
        String toReturn = "";
        EntityPlayerMP p2 = (EntityPlayerMP) player;
        PlayerData playerdata = PlayerHandler.getPlayer(UUID);
        switch (arguments[0].toLowerCase()) {
            case "list":
                switch (arguments[1].toLowerCase()) {
                    case "current":
                        for (Slug perk : PlayerHandler.getPlayer(p2.getUniqueID().toString()).getPerkList()) {
                            toReturn += PerkStore.getInstance().getPerk(perk.getSlug().toString()) + ",";
                        }
                        break;
                    case "all":
                        for (Perk perk : PerkStore.getInstance().getPerks()) {
                            toReturn += perk.getSlug()+ ",";
                        }
                        break;
                    default:
                        toReturn = arguments[1] + " is an invalid argument";
                        break;
                }
                break;
            case "obtain":
                if (arguments.length >= 2) {
                    toReturn = PlayerHandler.addPerk(arguments[1].toString(), playerdata);
                }
                break;
            case "getinfo":
                if ((arguments.length >= 2)) {
                    toReturn = PerkStore.getInstance().getPerk(arguments[1]).toString();
                }
                break;
            case "convertpoints":
                if (Settings.getInstance().getPointsMode() == 2) {
                    if (arguments.length > 2) {
                        if (p2.experienceLevel >= Integer.parseInt(arguments[2])) {
                            p2.experienceLevel -= Integer.parseInt(arguments[2]);
                            playerdata.setPoints(playerdata.getPoints() + Integer.parseInt(arguments[2]));
                        } else {
                            toReturn = "Invalid Argument";
                        }
                    } else {
                        toReturn = "Invalid Argument";
                    }
                } else {
                    toReturn = "option unavaliable";
                }
                break;
            case "activate":
                if (arguments.length >= 2){
                    PlayerHandler.activatePerk(p2, playerdata, arguments[1]);
                }
                
                break;
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

}
