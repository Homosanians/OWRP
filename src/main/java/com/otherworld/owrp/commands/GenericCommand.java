package com.otherworld.owrp.commands;

import com.otherworld.owrp.AbstractCommand;
import com.otherworld.owrp.GenericCommandArgs;
import com.otherworld.owrp.OWRP;
import com.otherworld.owrp.dependencies.DependencyManager;
import com.otherworld.owrp.utils.ChatColorUtil;
import com.otherworld.owrp.utils.PlayerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GenericCommand extends AbstractCommand<OWRP> {

    private final OWRP plugin;
    private final GenericCommandArgs commandArgs;
    private final DependencyManager dependencyManager;

    public GenericCommand(OWRP plugin, GenericCommandArgs commandArgs) {

        super(plugin, commandArgs.commandName);

        setUsage(commandArgs.commandUsage);

        setPermission(commandArgs.commandPermission);
        setPermissionMessage(Objects.requireNonNull(plugin.getConfig().getString("Strings.noPrivilege")));

        registerCommand();

        this.plugin = plugin;
        this.commandArgs = commandArgs;
        this.dependencyManager = plugin.getExact(DependencyManager.class);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, String commandName, String[] args) {
        try {
            // Completes execution of the command if it is not sent by a player.
            if (commandArgs.chatRadius != -2 && !(sender instanceof Player)) {
                sender.sendMessage(ChatColorUtil.formatColor(Objects.requireNonNull(plugin.getConfig().getString("Strings.noConsole"))));
                return true;
            }

            // Check whether content arguments are present
            if (args.length == 0) {
                sender.sendMessage(ChatColorUtil.formatColor(Objects.requireNonNull(plugin.getConfig().getString("Strings.noArgs"))));
                return true;
            }

            List<String> messages = new ArrayList<>();

            Player player = (Player) sender;

            if (commandArgs.commandMessagesOutputMode.equals("random")) {
                int randomMessageIndex = new Random().nextInt(commandArgs.messages.size());
                messages.add(buildMessage(
                        commandArgs.messages.get(randomMessageIndex),
                        player,
                        args
                ));
            } else if (commandArgs.commandMessagesOutputMode.equals("single")) {
                messages.add(buildMessage(
                        commandArgs.messages.get(0),
                        player,
                        args
                ));
            }
            else {
                for (int i = 0; i < commandArgs.messages.size(); i++) {
                    messages.add(buildMessage(
                            commandArgs.messages.get(i),
                            player,
                            args
                    ));
                }
            }

            List<Player> addressees;

            if (commandArgs.chatRadius == -1) {
                addressees = PlayerUtil.getAllPlayersInWorld(player.getWorld());
            } else if (commandArgs.chatRadius == -2) {
                addressees = PlayerUtil.getAllPlayersInAllWorlds();
            } else {
                addressees = PlayerUtil.getPlayersWithin(player, commandArgs.chatRadius);
            }

            for (Player addressee : addressees) {
                for (String message : messages) {
                    addressee.sendMessage(ChatColorUtil.formatColor(message));
                }
            }
        } catch (Exception ex) {
            sender.sendMessage(ChatColorUtil.formatColor(
                    Objects.requireNonNull(plugin.getConfig().getString("Strings.errorOccurred"))));

            ex.printStackTrace();
        }

        return true;
    }

    private String buildMessage(String message, Player player, String[] args) {
        return message
                .replace("{playerName}", player.getName())
                .replace("{playerDisplayName}", (player.getDisplayName()))
                .replace("{message}", String.join(" ", args));
    }
}
