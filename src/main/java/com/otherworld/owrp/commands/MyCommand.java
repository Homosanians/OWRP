package com.otherworld.owrp.commands;

import com.otherworld.owrp.AMyCommand;
import com.otherworld.owrp.OWRP;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class MyCommand extends AMyCommand<OWRP> {

    public MyCommand(OWRP plugin) {

        super(plugin, "myCommand");
        setLabel("myCum");
        setName("myComman");
        setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
        setUsage("/myCommand <param1> <param2>");
        setAliases("mc");
        setPermission("perm.myCommand");
        setPermissionMessage("nop!");
        addTabbComplete(0, "test");// /commande test
        addTabbComplete(0, "test2");// /commande test2
        addTabbComplete(1, "subParam");// /commande <arg1> subParam

        registerCommand();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player){
            Player sender = (Player) commandSender;
            sender.sendMessage("player");
        }else{
            commandSender.sendMessage("no player");
        }

        return true;
    }
}