package com.otherworld.owrp.commands;

import com.otherworld.owrp.AbstractCommand;
import com.otherworld.owrp.GenericCommandArgs;
import com.otherworld.owrp.OWRP;
import com.otherworld.owrp.utils.PlayerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class GenericCommand extends AbstractCommand<OWRP> {

    private final OWRP plugin;
    private final GenericCommandArgs commandArgs;

    public GenericCommand(OWRP plugin, GenericCommandArgs commandArgs) {

        super(plugin, commandArgs.commandName);

        setUsage(commandArgs.commandUsage);
        setPermission(commandArgs.commandPermission);
        setPermissionMessage(Objects.requireNonNull(plugin.getConfig().getString("Strings.noPrivilege")));

        registerCommand();

        this.plugin = plugin;
        this.commandArgs = commandArgs;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        // Completes execution of the command if it is not sent by a player.
        if (commandArgs.chatRadius != -2 && !(sender instanceof Player)) {
            sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("Strings.noConsole")));
            return true;
        }

        // Check whether content arguments are present
        if (args.length == 0) {
            sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("Strings.noArgs")));
            return true;
        }

        if (false) {
            sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("Strings.noPrivilege")));
            return true;
        }

        if (false) {
            sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("Strings.errorOccurred")));
            return true;
        }

        Player player = (Player) sender;
        List<Player> addressees = PlayerUtil.getPlayersWithin(player, commandArgs.chatRadius);
        String content = commandArgs.message
                .replace("{player}", ((Player) sender).getDisplayName())
                .replace("{message}", args[0]);

        for (Player addressee : addressees)
        {
            // <content> (<player>)
            addressee.sendMessage(content);
        }

        return true;
    }
}
