package com.otherworld.owrp.commands;

import com.otherworld.owrp.OWRP;
import com.otherworld.owrp.dependencies.DependencyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ReloadCommand implements CommandExecutor {
    private final OWRP plugin;

    public ReloadCommand(OWRP plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        plugin.reloadConfig();

        sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("Strings.pluginReload")));

        return true;
    }
}
