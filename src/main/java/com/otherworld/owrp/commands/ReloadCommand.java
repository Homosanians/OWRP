package com.otherworld.owrp.commands;

import com.otherworld.owrp.OWRP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    private OWRP plugin;

    public ReloadCommand(OWRP plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        plugin.reloadConfig();

        sender.sendMessage("Конфиг перезагружен.");

        return true;
    }
}