package com.otherworld.owrp;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import com.otherworld.owrp.commands.*;

public final class OWRP extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Starting up.");
        this.getCommand("me").setExecutor((CommandExecutor) new MeCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Shutting down.");
    }
}
