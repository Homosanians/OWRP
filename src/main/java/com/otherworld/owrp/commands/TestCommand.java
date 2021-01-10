package com.otherworld.owrp.commands;

import com.otherworld.owrp.GenericCommandArgs;
import com.otherworld.owrp.OWRP;
import com.otherworld.owrp.utils.ChatColorUtil;
import com.otherworld.owrp.utils.PlayerUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.ArrayList;

public class TestCommand extends BukkitCommand {
    public TestCommand(String name) {
        super(name);
        this.description = "Find the last time a player was online";
        this.usageMessage = "/seen <player>";
        this.setPermission("minesmash.commands.seen");
        this.setAliases(new ArrayList<String>());
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
//        if (!sender.hasPermission(this.getPermission())) {
//            sender.sendMessage(ChatColor.RED + "You don't have permission.");
//            return true;
//        }

        sender.sendMessage("kekw");
        return true;
    }
}