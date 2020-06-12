package com.otherworld.owrp;

import com.otherworld.owrp.commands.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class OWRP extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("me").setExecutor(new MeCommand(this));
        getCommand("do").setExecutor(new DoCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
