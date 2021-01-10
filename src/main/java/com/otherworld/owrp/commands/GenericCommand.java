package com.otherworld.owrp.commands;

import com.otherworld.owrp.GenericCommandArgs;
import com.otherworld.owrp.OWRP;
import com.otherworld.owrp.utils.ChatColorUtil;
import com.otherworld.owrp.utils.PlayerUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class GenericCommand implements CommandExecutor {
    private final OWRP plugin;
    private final GenericCommandArgs commandArgs;

    public GenericCommand(OWRP plugin, GenericCommandArgs commandArgs) {
        this.plugin = plugin;
        this.commandArgs = commandArgs;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Completes execution of the command if it is not sent by a player.
        if (commandArgs.radius != -2 && !(sender instanceof Player)) {
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
        List<Player> addressees = PlayerUtil.getPlayersWithin(player, commandArgs.radius);
        String content = String.join(" ", args) + " " + commandArgs.message;

        for (Player addressee : addressees)
        {
            // <content> (<player>)
            addressee.sendMessage(content);
        }

        return true;
    }
}
