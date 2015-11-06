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
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Disconsented
 */
public class Perks extends CommandBase {

    private List aliases;

    @SuppressWarnings("unchecked")
    public Perks() {
        this.aliases = new ArrayList();
        this.aliases.add("perk");
        this.aliases.add("perks");
    }

    @Override
    public int compareTo(Object arg0) {
        return 0;
    }

    @Override
    public String getCommandName() {
        return "perk";
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender) {
        return null;
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args) {
        EntityPlayer player = (EntityPlayer) commandSender;
        String UUID = player.getPersistentID().toString();
        String toReturn = "";
        PlayerData playerdata = PlayerHandler.getPlayer(UUID);
        switch (args[0].toLowerCase()) {
            case "list":
                switch (args[1].toLowerCase()) {
                    case "current":
                        for (Slug perk : PlayerHandler.getPlayer(player.getUniqueID().toString()).getPerkList()) {
                            toReturn += PerkStore.getPerk(perk.getSlug()) + ",";
                        }
                        break;
                    case "all":
                        for (Perk perk : PerkStore.getPerks()) {
                            toReturn += perk.getSlug() + ",";
                        }
                        break;
                    default:
                        toReturn = args[1] + " is an invalid argument";
                        break;
                }
                break;
            case "obtain":
                if (args.length >= 2) {
                    toReturn = PlayerHandler.addPerk(args[1], playerdata);
                }
                break;
            case "getinfo":
                if ((args.length >= 2)) {
                    toReturn = PerkStore.getPerk(args[1]).toString();
                }
                break;
            case "convertpoints":
                if (Settings.getPointsMode() == 2) {
                    if (args.length > 2) {
                        if (player.experienceLevel >= Integer.parseInt(args[2])) {
                            player.experienceLevel -= Integer.parseInt(args[2]);
                            playerdata.setPoints(playerdata.getPoints() + Integer.parseInt(args[2]));
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
                if (args.length >= 2) {
                    toReturn = PlayerHandler.activatePerk(player, playerdata, args[1]);
                }

                break;
        }

        player.addChatMessage(new ChatComponentText(toReturn));
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
    public int getRequiredPermissionLevel() {
        return 0;
    }

}
