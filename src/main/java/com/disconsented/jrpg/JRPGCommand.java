package com.disconsented.jrpg;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JRPGCommand extends CommandBase {

    private static ITextComponent textComputerSaysNo = new TextComponentString("Computer says no");
    private static ITextComponent textSkillNotImplemented = new TextComponentString("Skill is not yet implemented");
    private static ITextComponent textSkillUsage = new TextComponentString("Skill is not yet implemented");
    private static ITextComponent textTraitUsage = new TextComponentString("TRAIT USAGE PLACEHOLDER");
    private static ITextComponent textUnknownUsage = new TextComponentString("Sorry I didn't understand that");
    private static ITextComponent textSomethingBroke = new TextComponentString("Something broke, check the logs");
    private static ITextComponent textTraitAddUsage = new TextComponentString("Adds trait to self usage: [trait_name]");


    private static List aliases = Arrays.asList("JRPG", "jrpg", "j");

    @Override
    public String getName() {
        return "JRPG";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "[trait|skill]";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            sender.sendMessage(textUnknownUsage);
            return;
        }

        switch (args[0]) {
            case "trait":
                traitCommand(server, sender, Arrays.copyOfRange(args, 1, args.length));
                break;
            case "skill":
                skillCommand(server, sender, Arrays.copyOfRange(args, 1, args.length));
                break;
            case "admin":
                adminCommand(server, sender, Arrays.copyOfRange(args, 1, args.length));
                break;
            default:
                sender.sendMessage(textComputerSaysNo);
        }
    }

    // reload configs
    private void adminCommand(MinecraftServer server, ICommandSender sender, String[] copyOfRange) {
        sender.sendMessage(textSkillNotImplemented);
    }

    private void skillCommand(MinecraftServer server, ICommandSender sender, String[] copyOfRange) {
        sender.sendMessage(textSkillNotImplemented);
    }

    private void traitCommand(MinecraftServer server, ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(textTraitUsage);
            return;
        }
        switch (args[0]) {
            case "test":
                break;
            case "add":
                if (args.length < 2) {
                    sender.sendMessage(textTraitAddUsage);
                    return;
                }
                try {
                    Player player = Player.getPlayer(getCommandSenderAsPlayer(sender));
                    ArrayList<Trait> allTraits = Trait.getAllTraits();
                    for (Trait trait : allTraits) {
                        if (trait.getName().equals(args[1])) {
                            player.addTrait(trait);
                            sender.sendMessage(new TextComponentString("Added " + trait.getName()));
                        }
                    }


                } catch (PlayerNotFoundException e) {
                    sender.sendMessage(textSomethingBroke);
                    JRPG.log.error(e);
                }
                break;
            default:
                sender.sendMessage(textUnknownUsage);
        }
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }
}

